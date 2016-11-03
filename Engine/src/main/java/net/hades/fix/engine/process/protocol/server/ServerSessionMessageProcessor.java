/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.security.PasswordBank;
import net.hades.fix.engine.process.protocol.DisconnectSessionException;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.MessageProcessor;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.RejectAndLogoutException;
import net.hades.fix.engine.process.protocol.SeqGap;
import net.hades.fix.message.BinaryMessage;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.LogoutMsg;
import net.hades.fix.message.RejectMsg;
import net.hades.fix.message.ResendRequestMsg;
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
public class ServerSessionMessageProcessor extends MessageProcessor {

    private static final Logger Log = Logger.getLogger(ServerSessionMessageProcessor.class.getName());

    public ServerSessionMessageProcessor(FixServer protocol) {
	super(protocol);
    }

    //------------------------------- MESSAGES -------------------------------------------

    public List<FIXMsg> processInitStateMessageRcvd(BinaryMessage received) throws InvalidMsgException, DisconnectSessionException, RejectAndLogoutException {
	List<FIXMsg> responses = new ArrayList<>();
	String text = "";
	try {
	    FIXMsg msg = validateIncomingLogon(received);
	    if (!MsgType.Logon.getValue().equals(msg.getHeader().getMsgType())) {
		throw new DisconnectSessionException(String.format("Expected Logon message [%s] but received [%s]", 
			MsgType.Logon.name(), MsgType.valueFor(msg.getHeader().getMsgType())));
	    }
	    protocol.getTimers().startTestRequestTimeoutTask();
	    int expSeqNum = protocol.getRxSeqNo();
	    int msgSeqNum = msg.getHeader().getMsgSeqNum();
	    // next expected seq number
	    if (!isAuthenticated((LogonMsg) msg)) {
		throw new DisconnectSessionException(String.format("Logon message [%s] cannot be authenticated", MsgType.valueFor(msg.getHeader().getMsgType())));
	    }
	    if (MsgUtil.compare(protocol.getVersion().getBeginString(), BeginString.FIX_4_4) >= 0
                && protocol.getConfiguration().getEnableNextExpMsgSeqNum() != null
                && protocol.getConfiguration().getEnableNextExpMsgSeqNum()
                && ((LogonMsg) msg).getNextExpectedMsgSeqNum() != null) {
		int nextExpSeqNum = ((LogonMsg) msg).getNextExpectedMsgSeqNum();
		int nextTxSeqNum = protocol.getNextTxSeqNo() + 1;
		if (nextExpSeqNum < nextTxSeqNum) {
		    NavigableMap<Integer, FIXMsg> messages = protocol.getHistoryCache().getMessages(nextExpSeqNum, nextTxSeqNum - 1);
		    messages.values().stream().forEach((message) -> {
			responses.add(message);
		    });
		} else if (nextExpSeqNum > nextTxSeqNum) {
		    LogoutMsg logout = MessageFiller.buildLogoutMsg(protocol, "NextExpectedMsgSeqNum greater than the next seq number");
		    responses.add(logout);
		    protocol.setProtocolState(ProtocolState.LOGGEDOUT);
		    protocol.getTimers().startLogoutTimerTask();
		    return responses;
		}
	    } else {
		if (msgSeqNum > expSeqNum) {
		    protocol.setGap(new SeqGap(expSeqNum, msgSeqNum));
		    ResendRequestMsg resendRequest = MessageFiller.buildResendRequestMsg(protocol);
		    resendRequest.setBeginSeqNo(protocol.getGap().getFirst());
		    if (protocol.getConfiguration().getResendEndSeqNum() != null) {
			resendRequest.setEndSeqNo(protocol.getConfiguration().getResendEndSeqNum());
		    } else {
			resendRequest.setEndSeqNo(protocol.getGap().getLast());
		    }
		    responses.add(resendRequest);
		} else if (msgSeqNum < expSeqNum) {
		    throw new BadFormatMsgException(SessionRejectReason.ValueOuOfRange, msg.getHeader().getMsgSeqNum(), msg.getHeader().getMsgType(),
			    String.format("MsgSeqNum too low, expecting [%s] but received [%s]", expSeqNum, msgSeqNum));
		}
	    }
	    
	    // override configured protocol version if set
	    overrideProtocolVersion((LogonMsg) msg);
	    overrideHeartbeatInterval((LogonMsg) msg);
	    addMessageTypeGroups((LogonMsg) msg);
	    overrideMaxMessageSize((LogonMsg) msg);
	    setTestSession((LogonMsg) msg);
	    setFixTransSession((LogonMsg) msg);
	    protocol.getNextRxSeqNo();
	    protocol.setProtocolState(ProtocolState.LOGGEDON);
	    return responses;
	} catch (InvalidMsgException ex) {
	    Log.log(Level.SEVERE, "Garbled message received : {0}. Error was: {1}",
		    new Object[]{MsgUtil.getPrintableRawFixMessage(received.getRawMessage()), ExceptionUtil.getStackTrace(ex)});
	    text = "Garbled message received";
	} catch (BadFormatMsgException ex) {
	    Log.log(Level.SEVERE, "Message rejected Reason : {0}. SeqNum : {1} Message : {2}", new Object[]{ex.getRejectReason().name(), ex.getSeqNum(), ex.getMessage()});
	    RejectMsg reject = MessageFiller.buildRejectMsg(protocol, ex.getSeqNum(), SessionRejectReason.RequiredTagMissing, 0, ex.getMessage());
	    text = ex.getMessage();
	    responses.add(reject);
	    protocol.getNextRxSeqNo();
	}
	// all errors log out in this state
	LogoutMsg logout = MessageFiller.buildLogoutMsg(protocol, SessionStatus.SessionActive);
	logout.setText(text);
	responses.add(logout);
	protocol.setProtocolState(ProtocolState.LOGGEDOUT);
	protocol.getTimers().startLogoutTimerTask();
	return responses;
    }

    //-------------------------------------------------------------------------------------
       
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
	    protocol.getTimers().getTimeouts().setHtbtTimeout(message.getHeartBtInt());
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
		    sessMsgTypes = new ArrayList<>();
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

    private boolean isAuthenticated(LogonMsg message) {
	if (BeginString.FIX_4_3.compareTo(message.getHeader().getBeginString()) <= 0) {
            if (protocol.getConfiguration().getAuthenticationInfo() != null && protocol.getConfiguration().getAuthenticationInfo().getLoginUsername() != null) {
                if (message.getUsername() != null && message.getPassword() != null) {
                    if (!message.getUsername().equals(protocol.getConfiguration().getAuthenticationInfo().getLoginUsername())) {
                        return false;
                    } else {
                        char[] password = PasswordBank.getInstance().getEntryValue(protocol.getConfiguration().getAuthenticationInfo().getLoginPassword());
                        if (!message.getPassword().equals(String.valueOf(password))) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        } else {
            if (protocol.getConfiguration().getAuthenticationInfo() != null && protocol.getConfiguration().getAuthenticationInfo().getLoginUsername() != null) {
                if (message.getRawData() != null && message.getRawData().length > 0) {
                    char[] password = PasswordBank.getInstance().getEntryValue(protocol.getConfiguration().getAuthenticationInfo().getLoginPassword());
                    if (password == null) {
                         String logMsg = "Authentication has been enabled but there is no entry in the PasswordBank for [" + 
                                 protocol.getConfiguration().getAuthenticationInfo().getLoginUsername() + "].";
                        Log.log(Level.SEVERE, logMsg);
                        return false;
                    } else {
                        String clientPassword = new String(message.getRawData());
                        if (!clientPassword.equals(new String(password))) {
                            return false;
                        }
                    }
                }
            }
        }
	return true;
    }

    
}
