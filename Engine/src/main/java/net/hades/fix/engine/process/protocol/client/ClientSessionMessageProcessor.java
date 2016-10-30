/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.process.protocol.DisconnectSessionException;
import net.hades.fix.engine.process.protocol.LogoutSessionException;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.MessageProcessor;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.SeqGap;
import net.hades.fix.engine.process.protocol.SeqSet;
import net.hades.fix.message.BinaryMessage;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.LogoutMsg;
import net.hades.fix.message.RejectMsg;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.SequenceResetMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SessionStatus;
import net.hades.fix.message.util.MsgUtil;

/**
 * Fix initiator side of messages.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ClientSessionMessageProcessor extends MessageProcessor {

    private static final Logger Log = Logger.getLogger(ClientSessionMessageProcessor.class.getName());

    public ClientSessionMessageProcessor(FixClient protocol) {
	super(protocol);
    }

    //------------------------------- MESSAGES -------------------------------------------
    
    public FIXMsg processLoginSend() {
	try {
	    protocol.setSessStartSeqSet(new SeqSet(protocol.getRxSeqNo(), protocol.getTxSeqNo()));
	    return MessageFiller.buildLogonMsg(protocol);
	} catch (InvalidMsgException ex) {
	    Log.log(Level.SEVERE, "Fata error buiklding LoginMsg", ex);
	}
	return null;
    }

    public List<FIXMsg> processLoginRcvd(BinaryMessage received) throws InvalidMsgException, DisconnectSessionException {
	List<FIXMsg> responses = new ArrayList<>();
	try {
	    FIXMsg msg = validateIncomingLogin(received);
	    if (!MsgType.Logon.getValue().equals(msg.getHeader().getMsgType())) {
		throw new BadFormatMsgException(SessionRejectReason.InvalidMessageType, msg.getHeader().getMsgType(),
			String.format("Expected Logon message [%s] but received [%s]", MsgType.Logon.name(), MsgType.valueFor(msg.getHeader().getMsgType())));
	    }
	    protocol.getTimers().stopLogonTimeoutTask();
	    int expSeqNum = protocol.getRxSeqNo();
	    int msgSeqNum = msg.getHeader().getMsgSeqNum();
	    if (msgSeqNum < expSeqNum) {
		throw new BadFormatMsgException(SessionRejectReason.ValueOuOfRange, msg.getHeader().getMsgType(),
			String.format("MsgSeqNum too low, expecting [%s] but received [%s]", expSeqNum, msgSeqNum));
	    }
	    // override configured protocol version if set
	    overrideProtocolVersion((LogonMsg) msg);
	    overrideHeartbeatInterval((LogonMsg) msg);
	    addMessageTypeGroups((LogonMsg) msg);
	    overrideMaxMessageSize((LogonMsg) msg);
	    setTestSession((LogonMsg) msg);
	    setFixTransSession((LogonMsg) msg);

	    if (msgSeqNum > expSeqNum) {
		protocol.setGap(new SeqGap(expSeqNum, msgSeqNum));
		ResendRequestMsg resendRequest = MessageFiller.buildResendRequestMsg(protocol);
		resendRequest.setBeginSeqNo(protocol.getGap().getFirst());
		if (protocol.getConfiguration().getResendEndSeqNum() != null && !protocol.getConfiguration().getResendEndSeqNum().isEmpty()) {
		    resendRequest.setEndSeqNo(new Integer(protocol.getConfiguration().getResendEndSeqNum()));
		} else {
		    resendRequest.setEndSeqNo(protocol.getGap().getLast());
		}
		responses.add(resendRequest);
	    }
	    protocol.setProcessingStage(ProcessingStage.LOGGEDON);
	    return responses;
	} catch (InvalidMsgException ex) {
	    Log.log(Level.SEVERE, "Garbled message received : {0}. Error was: {1}",
		    new Object[]{MsgUtil.getPrintableRawFixMessage(received.getRawMessage()), ExceptionUtil.getStackTrace(ex)});
	    return responses;
	} catch (BadFormatMsgException ex) {
	    Log.log(Level.SEVERE, "Message rejected Reason : {0}. SeqNum : {1} Message : {2}", new Object[]{ex.getRejectReason().name(), ex.getSeqNum(), ex.getMessage()});
	    RejectMsg reject = MessageFiller.buildRejectMsg(protocol, ex.getSeqNum(), SessionRejectReason.RequiredTagMissing, 0, ex.getMessage());
	    responses.add(reject);
	    protocol.getNextRxSeqNo();
	}
	LogoutMsg logout = MessageFiller.buildLogoutMsg(protocol);
	responses.add(logout);
	protocol.setProcessingStage(ProcessingStage.LOGGEDOUT);
	protocol.getTimers().startLogoutTimerTask();
	return responses;
    }

    public List<FIXMsg> processMessageRcvd(BinaryMessage received) throws InvalidMsgException, DisconnectSessionException {
	List<FIXMsg> responses = new ArrayList<>();
	try {
	    protocol.getTimers().stopTestRequestTimeoutTask();
	    FIXMsg msg = validateIncoming(received);
	    int expSeqNum = protocol.getRxSeqNo();
	    int msgSeqNum = msg.getHeader().getMsgSeqNum();
	    // check sequence reset special case
	    if (MsgType.SequenceReset.getValue().equals(msg.getHeader().getMsgType())) {
		Boolean gapFill = ((SequenceResetMsg) msg).getGapFillFlag();
		int newSeqNo = ((SequenceResetMsg) msg).getNewSeqNo();
		if (gapFill != null && gapFill) {
		    if (newSeqNo > msgSeqNum) {
			if (msgSeqNum > expSeqNum) {
			    responses.add(buildResendRequest(expSeqNum, msgSeqNum));
			} if  (msgSeqNum == expSeqNum) {
			    protocol.setRxSeqNo(newSeqNo);
			} else {
			    if (msg.getHeader().getPossDupFlag() != null && msg.getHeader().getPossDupFlag()) {
				Log.log(Level.INFO, "Message discarded : {0}", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
			    } else {
				throw new LogoutSessionException(String.format("Invalid SequenceReset message: [%s]. Disconnecting session.", 
					new Object[] {MsgUtil.getPrintableRawFixMessage(msg.getRawMessage())}));
			    }
			}
		    } else {
			if (msgSeqNum == expSeqNum) {
			    RejectMsg reject = MessageFiller.buildRejectMsg(protocol, msgSeqNum, SessionRejectReason.ValueOuOfRange, 0,
				    String.format("Attempt to lower sequence number, invalie value NewSeqNum=", new Object[]{newSeqNo}));
			    responses.add(reject);
			    protocol.getNextRxSeqNo();
			} else {
			    Log.log(Level.WARNING, "Message discarded : {0} OrigSendingTime is empty.", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
			}
		    }
		} else {
		    if (newSeqNo > expSeqNum) {
			protocol.setRxSeqNo(newSeqNo);
		    } else if (newSeqNo == expSeqNum) {
			Log.log(Level.WARNING, "Message has the expected sequenece number : {0}", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
		    } else if (newSeqNo < expSeqNum) {
			RejectMsg reject = MessageFiller.buildRejectMsg(protocol, msgSeqNum, SessionRejectReason.ValueOuOfRange, 0,
				String.format("Attempt to lower sequence number, invalie value NewSeqNum=", new Object[]{newSeqNo}));
			responses.add(reject);
		    }
		}
		return responses;
	    }
	    if (msgSeqNum < expSeqNum) {
		if (msg.getHeader().getPossDupFlag()) {
		    if (msg.getHeader().getOrigSendingTime() == null) {
			if (BeginString.FIX_4_2.compareTo(msg.getHeader().getBeginString()) >= 0) {
			    BadFormatMsgException ex = new BadFormatMsgException(SessionRejectReason.ValueOuOfRange, msg.getHeader().getMsgType(),
				    "OrigSebdingTime not set on the resend message");
			    ex.setSeqNum(msg.getHeader().getMsgSeqNum());
			    throw ex;
			} else {
			    Log.log(Level.WARNING, "Message discarded : {0} OrigSendingTime is empty.", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
			}
		    } else if (msg.getHeader().getOrigSendingTime().after(msg.getHeader().getSendingTime())) {
			BadFormatMsgException ex = new BadFormatMsgException(SessionRejectReason.ValueOuOfRange, msg.getHeader().getMsgType(),
				"OrigSebdingTime later than SendingTime");
			ex.setSeqNum(msg.getHeader().getMsgSeqNum());
			throw ex;
		    } else {
			if (msgSeqNum >= expSeqNum) {
			    protocol.relayMessage(msg);
			} else {
			    Log.log(Level.INFO, "Message discarded : {0}", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
			}
		    }
		} else {
		    BadFormatMsgException ex = new BadFormatMsgException(SessionRejectReason.ValueOuOfRange, msg.getHeader().getMsgType(),
			    String.format("MsgSeqNum too low, expecting [%s] but received [%s]", expSeqNum, msgSeqNum));
		    ex.setSeqNum(msg.getHeader().getMsgSeqNum());
		    throw ex;
		}
	    } else if (msgSeqNum > expSeqNum) {
		responses.add(buildResendRequest(expSeqNum, msgSeqNum));
		return responses;
	    }
	    // msg seq Okay
	    if (MsgType.SequenceReset.getValue().equals(msg.getHeader().getMsgType())) {
		
	    }

	    protocol.setProcessingStage(ProcessingStage.LOGGEDON);
	    return responses;
	} catch (InvalidMsgException ex) {
	    Log.log(Level.SEVERE, "Garbled message received : {0}. Error was: {1}",
		    new Object[]{MsgUtil.getPrintableRawFixMessage(received.getRawMessage()), ExceptionUtil.getStackTrace(ex)});
	    return responses;
	} catch (BadFormatMsgException ex) {
	    Log.log(Level.SEVERE, "Message rejected Reason : {0}. SeqNum : {1} Message : {2}", new Object[]{ex.getRejectReason().name(), ex.getSeqNum(), ex.getMessage()});
	    RejectMsg reject = MessageFiller.buildRejectMsg(protocol, ex.getSeqNum(), SessionRejectReason.RequiredTagMissing, 0, ex.getMessage());
	    responses.add(reject);
	    protocol.getNextRxSeqNo();
	} catch (LogoutSessionException ex) {
	    Log.log(Level.SEVERE, "Disconnect session. Error was {0}", ex.getMessage());
	}
	LogoutMsg logout = MessageFiller.buildLogoutMsg(protocol);
	responses.add(logout);
	protocol.setProcessingStage(ProcessingStage.LOGGEDOUT);
	protocol.getTimers().startLogoutTimerTask();
	return responses;
    }

    //-------------------------------------------------------------------------------------
    
    private ResendRequestMsg buildResendRequest(int expSeqNum, int msgSeqNum) throws DisconnectSessionException, InvalidMsgException {
	if (protocol.getGap() == null) {
	    protocol.setGap(new SeqGap(expSeqNum, msgSeqNum));
	}
	if (protocol.getGap().getFirst() == expSeqNum) {
	    protocol.getGap().resend();
	    if (protocol.getGap().getResend() < MAX_NUM_OF_RESENDS) {
		ResendRequestMsg resendRequest = MessageFiller.buildResendRequestMsg(protocol);
		resendRequest.setBeginSeqNo(protocol.getGap().getFirst());
		if (protocol.getConfiguration().getResendEndSeqNum() != null && !protocol.getConfiguration().getResendEndSeqNum().isEmpty()) {
		    resendRequest.setEndSeqNo(new Integer(protocol.getConfiguration().getResendEndSeqNum()));
		} else {
		    resendRequest.setEndSeqNo(protocol.getGap().getLast());
		}
		resendRequest.getHeader().setPossDupFlag(Boolean.TRUE);
		return resendRequest;
	    } else {
		throw new DisconnectSessionException("Could not recover from an infinite ResendRequest loop");
	    }
	} else {
	    protocol.setGap(new SeqGap(expSeqNum, msgSeqNum));
	    ResendRequestMsg resendRequest = MessageFiller.buildResendRequestMsg(protocol);
	    resendRequest.setBeginSeqNo(protocol.getGap().getFirst());
	    if (protocol.getConfiguration().getResendEndSeqNum() != null && !protocol.getConfiguration().getResendEndSeqNum().isEmpty()) {
		resendRequest.setEndSeqNo(new Integer(protocol.getConfiguration().getResendEndSeqNum()));
	    } else {
		resendRequest.setEndSeqNo(protocol.getGap().getLast());
	    }
	    return resendRequest;
	}
    }
    
    private void overrideProtocolVersion(LogonMsg message) {
	if (MsgUtil.compare(message.getHeader().getBeginString(), BeginString.FIXT_1_1) >= 0 && message.getDefaultApplVerID() != null) {
	    if (MsgUtil.compare(message.getDefaultApplVerID(), ApplVerID.FIX50) >= 0) {
		protocol.getVersion().setApplVerID(message.getDefaultApplVerID());
	    }
	    if (MsgUtil.compare(message.getDefaultApplVerID(), ApplVerID.FIX50SP1) >= 0) {
		if (message.getDefaultApplExtID() != null) {
		    protocol.getVersion().setApplExtID(message.getDefaultApplExtID());
		}
		if (message.getDefaultCstmApplVerID() != null) {
		    protocol.getVersion().setCstmApplVerID(message.getDefaultCstmApplVerID());
		}
	    }
	}
    }

    private void overrideHeartbeatInterval(LogonMsg message) {
	if (message.getHeartBtInt() != null) {
	    protocol.getConfiguration().setHeartBtInt(message.getHeartBtInt());
	}
    }

    private void addMessageTypeGroups(LogonMsg message) throws InvalidMsgException {
	if (MsgUtil.compare(protocol.getVersion().getBeginString(), BeginString.FIX_4_2) >= 0) {
	    if (message.getMaxMessageSize() != null) {
		protocol.getConfiguration().setMaxMsgLen(message.getMaxMessageSize());
	    }
	    if (message.getNoMsgTypes() != null && message.getNoMsgTypes() > 0) {
		List<MsgTypeGroup> sessMsgTypes;
		if (protocol.getMsgTypes() != null && protocol.getMsgTypes().length > 0) {
		    sessMsgTypes = Arrays.asList(protocol.getMsgTypes());
		} else {
		    sessMsgTypes = new ArrayList<MsgTypeGroup>();
		}
		for (MsgTypeGroup msgType : message.getMsgTypeGroups()) {
		    boolean found = false;
		    if (protocol.getMsgTypes() != null && protocol.getMsgTypes().length > 0) {
			for (MsgTypeGroup sessMsgType : sessMsgTypes) {
			    if (sessMsgType.getRefMsgType().equals(msgType.getRefMsgType())) {
				// override the session level config msg types
				sessMsgType.setMsgDirection(msgType.getMsgDirection());
				if (BeginString.FIXT_1_1.equals(message.getHeader().getBeginString())) {
				    if (MsgUtil.compare(protocol.getVersion().getApplVerID(), ApplVerID.FIX50) >= 0) {
					if (msgType.getRefApplVerID() != null) {
					    sessMsgType.setRefApplVerID(msgType.getRefApplVerID());
					}
					if (msgType.getRefCstmApplVerID() != null) {
					    sessMsgType.setRefCstmApplVerID(msgType.getRefCstmApplVerID());
					}
				    }
				    if (MsgUtil.compare(message.getDefaultApplVerID(), ApplVerID.FIX50SP1) >= 0) {
					if (msgType.getRefApplExtID() != null) {
					    sessMsgType.setRefApplExtID(msgType.getRefApplExtID());
					}
					if (msgType.getDefaultVerIndicator() != null) {
					    sessMsgType.setDefaultVerIndicator(msgType.getDefaultVerIndicator());
					}
				    }
				}
				found = true;
				break;
			    }
			}
		    }
		    if (!found) {
			MsgTypeGroup group = MessageFiller.createMsgTypeGroupForVersion(protocol);
			group.setRefMsgType(msgType.getRefMsgType());
			group.setMsgDirection(msgType.getMsgDirection());
			if (BeginString.FIXT_1_1.equals(message.getHeader().getBeginString())) {
			    if (MsgUtil.compare(protocol.getVersion().getApplVerID(), ApplVerID.FIX50) >= 0) {
				if (msgType.getRefApplVerID() != null) {
				    group.setRefApplVerID(msgType.getRefApplVerID());
				}
				if (msgType.getRefCstmApplVerID() != null) {
				    group.setRefCstmApplVerID(msgType.getRefCstmApplVerID());
				}
			    }
			    if (MsgUtil.compare(message.getDefaultApplVerID(), ApplVerID.FIX50SP1) >= 0) {
				if (msgType.getRefApplExtID() != null) {
				    group.setRefApplExtID(msgType.getRefApplExtID());
				}
				if (msgType.getDefaultVerIndicator() != null) {
				    group.setDefaultVerIndicator(msgType.getDefaultVerIndicator());
				}
			    }
			}
			sessMsgTypes.add(group);
		    }
		}
		protocol.setMsgTypes(sessMsgTypes.toArray(new MsgTypeGroup[sessMsgTypes.size()]));
	    }
	}
    }

    private void overrideMaxMessageSize(LogonMsg message) {
	if (BeginString.FIX_4_3.compareTo(message.getHeader().getBeginString()) <= 0) {
	    if (message.getMaxMessageSize() != null) {
		protocol.getConfiguration().setMaxMsgLen(message.getMaxMessageSize());
	    }
	}
    }

    private void setTestSession(LogonMsg message) {
	if (BeginString.FIX_4_3.compareTo(message.getHeader().getBeginString()) <= 0) {
	    if (message.getTestMessageIndicator() != null) {
		protocol.setTestSession(message.getTestMessageIndicator());
	    }
	}
    }

    private void setFixTransSession(LogonMsg message) throws DisconnectSessionException {
	if (BeginString.FIXT_1_1.compareTo(message.getHeader().getBeginString()) <= 0) {
	    if (message.getSessionStatus() != null) {
		SessionStatus sessStat = message.getSessionStatus();
		if (SessionStatus.InvalidUsernameOrPassword.equals(sessStat) || SessionStatus.AccountLocked.equals(sessStat)
			|| SessionStatus.LogonsAreNotAllowed.equals(sessStat) || SessionStatus.NewPasswordNotComplying.equals(sessStat)
			|| SessionStatus.PasswordExpired.equals(sessStat)) {
		    throw new DisconnectSessionException(sessStat.name());
		}
	    }
	    if (message.getDefaultApplVerID() != null) {
		protocol.getConfiguration().setDefaultApplVerID(message.getDefaultApplVerID().getValue());
	    }
	    if (message.getDefaultApplExtID() != null) {
		protocol.getConfiguration().setDefaultApplExtID(message.getDefaultApplExtID());
	    }
	    if (message.getDefaultCstmApplVerID() != null) {
		protocol.getConfiguration().setDefaultCstmApplVerID(message.getDefaultCstmApplVerID());
	    }
	}
    }

    
}
