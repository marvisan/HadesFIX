/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NavigableMap;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.commons.exception.ExceptionUtil;

import net.hades.fix.message.BinaryMessage;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.HeartbeatMsg;
import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.LogoutMsg;
import net.hades.fix.message.RejectMsg;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.SequenceResetMsg;
import net.hades.fix.message.TestRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SessionStatus;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;


/**
 * Super class for message processors.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class MessageProcessor {

    private static final Logger Log = Logger.getLogger(MessageProcessor.class.getName());
    
    public static final long MAX_CLOCK_DEVIANCE_MILLIS = 2 * 60 * 1000;
    public static final int MAX_NUM_OF_RESENDS = 100;

    protected final Protocol protocol;

    public MessageProcessor(Protocol protocol) {
	this.protocol = protocol;
    }

    public List<FIXMsg> processLoggedonStateMessageRcvd(BinaryMessage received) throws InvalidMsgException, DisconnectSessionException {
	List<FIXMsg> responses = new ArrayList<>();
	try {
	    protocol.getTimers().startTestRequestTimeoutTask();
	    FIXMsg msg = validateIncoming(received);
	    int expSeqNum = protocol.getRxSeqNo();
	    int msgSeqNum = msg.getHeader().getMsgSeqNum();
	    // check sequence reset special case
	    if (MsgType.SequenceReset.getValue().equals(msg.getHeader().getMsgType())) {
		handleSequenceReset(msg, responses);
		return responses;
	    }
	    if (msgSeqNum < expSeqNum) {
		if (msg.getHeader().getPossDupFlag()) {
		    if (msg.getHeader().getOrigSendingTime() == null) {
			if (BeginString.FIX_4_2.compareTo(msg.getHeader().getBeginString()) >= 0) {
			    throw new BadFormatMsgException(SessionRejectReason.ValueOuOfRange, msg.getHeader().getMsgSeqNum(), msg.getHeader().getMsgType(),
				    "Required tag OrigSendingTime missing");
			} else {
			    Log.log(Level.WARNING, "Message discarded : {0} OrigSendingTime is empty.", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
			}
		    } else if (msg.getHeader().getOrigSendingTime().after(msg.getHeader().getSendingTime())) {
			throw new RejectAndLogoutException(SessionRejectReason.SendingTimeAccuracyProblem, msgSeqNum, msg.getHeader().getMsgType(),
				"OrigSendingTime later than SendingTime");
		    } else {
			if (msgSeqNum >= expSeqNum) {
			    protocol.relayMessage(msg);
			} else {
			    Log.log(Level.INFO, "Message discarded : {0}", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
			}
		    }
		} else {
		    throw new LogoutSessionException(String.format("MsgSeqNum too low, expecting [%s] but received [%s]", expSeqNum, msgSeqNum));
		}
	    } else if (msgSeqNum > expSeqNum) {
		responses.add(buildResendRequest(expSeqNum, msgSeqNum));
		return responses;
	    }
	    // msg seq Okay
	    if (MsgType.Reject.getValue().equals(msg.getHeader().getMsgType())) {
		handleReject(msg, responses);
	    } else if (MsgType.Heartbeat.getValue().equals(msg.getHeader().getMsgType())) {
		handleHeartbeat(msg, responses);
	    } else if (MsgType.TestRequest.getValue().equals(msg.getHeader().getMsgType())) {
		handleTestRequest(msg, responses);
	    } else if (MsgType.ResendRequest.getValue().equals(msg.getHeader().getMsgType())) {
		handleResendRequest(msg, responses);
	    } else if (MsgType.Logon.getValue().equals(msg.getHeader().getMsgType())) {
		if (((LogonMsg) msg).getResetSeqNumFlag() != null && ((LogonMsg) msg).getResetSeqNumFlag()) {
		    protocol.setRxSeqNo(msg.getHeader().getMsgSeqNum());
		    protocol.setTxSeqNo(msg.getHeader().getMsgSeqNum());
		    protocol.getHistoryCache().clear();
		    protocol.getHistoryCache().save();
		    LogonMsg reset = MessageFiller.buildResetSeqNumLogonMsg(protocol);
		    responses.add(reset);
		} else {
		    Log.log(Level.WARNING, "Login message unexpectedly received  : {0}", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
		    protocol.getNextRxSeqNo();
		}
	    } else if (MsgType.Logout.getValue().equals(msg.getHeader().getMsgType())) {
		LogoutMsg message = (LogoutMsg) msg;
		Log.log(Level.INFO, "Logout received with status: {0} reason : {1}", new Object[] {getLogoutStatus(message), message.getText()});
		protocol.getNextRxSeqNo();
		LogoutMsg logout = MessageFiller.buildLogoutMsg(protocol, SessionStatus.LogoutComplete);
		responses.add(logout);
		protocol.setProtocolState(ProtocolState.LOGGEDOUT);
	    } else {
		protocol.relayMessage(msg);
		protocol.getNextRxSeqNo();
		protocol.setProtocolState(ProtocolState.LOGGEDON);	    
	    }
	    return responses;
	} catch (InvalidMsgException ex) {
	    Log.log(Level.SEVERE, "Garbled message received : {0}. Error was: {1}. Ignored",
		    new Object[]{MsgUtil.getPrintableRawFixMessage(received.getRawMessage()), ExceptionUtil.getStackTrace(ex)});
	    return responses;
	} catch (BadFormatMsgException ex) {
	    Log.log(Level.SEVERE, "Message rejected Reason : {0}. SeqNum : {1} Message : {2}", new Object[]{ex.getRejectReason().name(), ex.getSeqNum(), ex.getMessage()});
	    RejectMsg reject = MessageFiller.buildRejectMsg(protocol, ex.getSeqNum(), SessionRejectReason.RequiredTagMissing, 0, ex.getMessage());
	    responses.add(reject);
	    protocol.getNextRxSeqNo();
	} catch (RejectAndLogoutException ex) {
	    Log.log(Level.SEVERE, "Message rejected Reason : {0}. SeqNum : {1} Message : {2}", new Object[]{ex.getRejectReason().name(), ex.getSeqNum(), ex.getMessage()});
	    RejectMsg reject = MessageFiller.buildRejectMsg(protocol, ex.getSeqNum(), ex.getRejectReason(), 0, ex.getMessage());
	    responses.add(reject);
	    LogoutMsg logout = MessageFiller.buildLogoutMsg(protocol, ex.getMessage());
	    responses.add(logout);
	    protocol.setProtocolState(ProtocolState.LOGGEDOUT);
	    protocol.getTimers().startLogoutTimerTask();
	    protocol.getTimers().stopHeartbeatTimeoutTask();
	    protocol.getTimers().stopTestRequestTimeoutTask();
	    protocol.getNextRxSeqNo();
	} catch (LogoutSessionException ex) {
	    LogoutMsg logout = MessageFiller.buildLogoutMsg(protocol, ex.getMessage());
	    responses.add(logout);
	    protocol.setProtocolState(ProtocolState.LOGGEDOUT);
	    protocol.getTimers().startLogoutTimerTask();
	    protocol.getTimers().stopHeartbeatTimeoutTask();
	    protocol.getTimers().stopTestRequestTimeoutTask();
	    protocol.getNextRxSeqNo();
	}
	return responses;
    }
     
    public List<FIXMsg> processLoggedoutStateMessageRcvd(BinaryMessage received) throws InvalidMsgException, DisconnectSessionException {
	List<FIXMsg> responses = new ArrayList<>();
	try {
	    protocol.getTimers().stopTestRequestTimeoutTask();
	    FIXMsg msg = validateIncomingLogout(received);
	    // msg seq Okay
	    if (MsgType.Logout.getValue().equals(msg.getHeader().getMsgType())) {
		protocol.getTimers().stopLogoutTimeoutTask();
		handleLogout(msg);
		protocol.getNextRxSeqNo();
		throw new DisconnectSessionException("Session disconnected");
	    } else if (MsgType.ResendRequest.getValue().equals(msg.getHeader().getMsgType())) {
		protocol.getTimers().startLogoutTimerTask();
		handleResendRequest(msg, responses);
		protocol.getNextRxSeqNo();
	    } else if (MsgType.Heartbeat.getValue().equals(msg.getHeader().getMsgType()) || MsgType.TestRequest.getValue().equals(msg.getHeader().getMsgType())) {
		// discard
		protocol.getNextRxSeqNo();
	    } else {
		// disconnect
		throw new DisconnectSessionException(String.format("Unexpected message received in Logout state : {%s}", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage())));
	    }
	    return responses;
	} catch (InvalidMsgException ex) {
	    Log.log(Level.SEVERE, "Garbled message received : {0}. Error was: {1}. Ignored",
		    new Object[]{MsgUtil.getPrintableRawFixMessage(received.getRawMessage()), ExceptionUtil.getStackTrace(ex)});
	    return responses;
	} catch (BadFormatMsgException ex) {
	    Log.log(Level.SEVERE, "Message rejected Reason : {0}. SeqNum : {1} Message : {2}", new Object[]{ex.getRejectReason().name(), ex.getSeqNum(), ex.getMessage()});
	    RejectMsg reject = MessageFiller.buildRejectMsg(protocol, ex.getSeqNum(), SessionRejectReason.RequiredTagMissing, 0, ex.getMessage());
	    responses.add(reject);
	    protocol.getNextRxSeqNo();
	}
	return responses;
    }
    
    public List<FIXMsg> processLogoutMessageTrns(FIXMsg received) {
	if (MsgType.Logout.getValue().equals(received.getHeader().getMsgType())) {
	    protocol.setProtocolState(ProtocolState.LOGGEDOUT);
	    protocol.getTimers().startLogoutTimerTask();
	}
	return new ArrayList<>();
    }

    protected FIXMsg validateIncomingLogon(BinaryMessage message) throws InvalidMsgException, BadFormatMsgException, DisconnectSessionException {
	byte[] byteFixMsg = message.getRawMessage();
	FIXMsg msg = FIXMsgBuilder.build(byteFixMsg);
	if (msg.getHeader().getMsgSeqNum() == null || msg.getHeader().getMsgSeqNum() <= 0) {
	    throw new InvalidMsgException("No sequence number set for this message");
	}
	if (protocol.getMaxMsgSize() > 0 && byteFixMsg.length > protocol.getMaxMsgSize()) {
	    // discard messages larger than maxMsgSize
	    String errMsg = String.format("Discarded message with size [%s] greater than allowed size [%s].", byteFixMsg.length, protocol.getMaxMsgSize());
	    throw new BadFormatMsgException(SessionRejectReason.Other, msg.getHeader().getMsgSeqNum(), msg.getHeader().getMsgType(), errMsg);
	}
	validateIncomingLogonMessageAddress(msg);
	if (!isMsgSendTimeValid(msg)) {
	    String errMsg = "SendingTime accuracy problem.";
	    Log.severe(errMsg);
	    throw new BadFormatMsgException(SessionRejectReason.SendingTimeAccuracyProblem, msg.getHeader().getMsgSeqNum(), msg.getHeader().getMsgType(), errMsg);
	}
	return msg;
    }
    
    protected FIXMsg validateIncomingLogout(BinaryMessage message) throws InvalidMsgException, BadFormatMsgException, DisconnectSessionException {
	byte[] byteFixMsg = message.getRawMessage();
	FIXMsg msg = FIXMsgBuilder.build(byteFixMsg);
	if (msg.getHeader().getMsgSeqNum() == null || msg.getHeader().getMsgSeqNum() <= 0) {
	    throw new InvalidMsgException("No sequence number set for this message");
	}
	if (protocol.getMaxMsgSize() > 0 && byteFixMsg.length > protocol.getMaxMsgSize()) {
	    // discard messages larger than maxMsgSize
	    String errMsg = String.format("Discarded message with size [%s] greater than allowed size [%s].", byteFixMsg.length, protocol.getMaxMsgSize());
	    throw new BadFormatMsgException(SessionRejectReason.Other, msg.getHeader().getMsgSeqNum(), msg.getHeader().getMsgType(), errMsg);
	}
	validateIncomingLogoutMessageAddress(msg);
	if (!isMsgSendTimeValid(msg)) {
	    String errMsg = "SendingTime accuracy problem.";
	    Log.severe(errMsg);
	    throw new BadFormatMsgException(SessionRejectReason.SendingTimeAccuracyProblem, msg.getHeader().getMsgSeqNum(), msg.getHeader().getMsgType(), errMsg);
	}
	return msg;
    }

    protected FIXMsg validateIncoming(BinaryMessage message) throws InvalidMsgException, BadFormatMsgException, DisconnectSessionException, RejectAndLogoutException {
	byte[] byteFixMsg = message.getRawMessage();
	FIXMsg msg = FIXMsgBuilder.build(byteFixMsg);
	if (msg.getHeader().getMsgSeqNum() == null || msg.getHeader().getMsgSeqNum() <= 0) {
	    throw new InvalidMsgException("No sequence number set for this message");
	}
	if (protocol.getMaxMsgSize() > 0 && byteFixMsg.length > protocol.getMaxMsgSize()) {
	    // discard messages larger than maxMsgSize
	    String errMsg = String.format("Discarded message with size [%s] greater than allowed size [%s].", byteFixMsg.length, protocol.getMaxMsgSize());
	    protocol.getNextRxSeqNo();
	    throw new BadFormatMsgException(SessionRejectReason.Other, msg.getHeader().getMsgSeqNum(), msg.getHeader().getMsgType(), errMsg);
	}
	validateIncomingMessageAddress(msg);
	if (!isMsgSendTimeValid(msg)) {
	    String errMsg = "SendingTime accuracy problem.";
	    Log.severe(errMsg);
	    protocol.getNextRxSeqNo();
	    throw new RejectAndLogoutException(SessionRejectReason.SendingTimeAccuracyProblem, msg.getHeader().getMsgSeqNum(), msg.getHeader().getMsgType(), errMsg);
	}
	return msg;
    }

    protected void handleSequenceReset(FIXMsg msg, List<FIXMsg> responses) throws LogoutSessionException, DisconnectSessionException, InvalidMsgException {
	Boolean gapFill = ((SequenceResetMsg) msg).getGapFillFlag();
	int expSeqNum = protocol.getRxSeqNo();
	int msgSeqNum = msg.getHeader().getMsgSeqNum();
	int newSeqNo = ((SequenceResetMsg) msg).getNewSeqNo();
	if (gapFill != null && gapFill) {
	    if (newSeqNo > msgSeqNum) {
		if (msgSeqNum > expSeqNum) {
		    responses.add(buildResendRequest(expSeqNum, msgSeqNum));
		}
		if (msgSeqNum == expSeqNum) {
		    protocol.setRxSeqNo(newSeqNo);
		} else if (msg.getHeader().getPossDupFlag() != null && msg.getHeader().getPossDupFlag()) {
		    Log.log(Level.INFO, "Message discarded : {0}", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
		} else {
		    throw new LogoutSessionException(String.format("Invalid SequenceReset message: [%s]. Disconnecting session.",
			    new Object[]{MsgUtil.getPrintableRawFixMessage(msg.getRawMessage())}));
		}
	    } else if (msgSeqNum == expSeqNum) {
		RejectMsg reject = MessageFiller.buildRejectMsg(protocol, msgSeqNum, SessionRejectReason.ValueOuOfRange, 0,
			String.format("Attempt to lower sequence number, invalie value NewSeqNum=", new Object[]{newSeqNo}));
		responses.add(reject);
		protocol.getNextRxSeqNo();
	    } else {
		Log.log(Level.WARNING, "Message discarded : {0} OrigSendingTime is empty.", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
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
    }
    
    protected void handleReject(FIXMsg msg, List<FIXMsg> responses) {
	Log.log(Level.WARNING, "Message rejected : {0}", MsgUtil.getPrintableRawFixMessage(msg.getRawMessage()));
	protocol.getNextRxSeqNo();
    }
    
    protected void handleHeartbeat(FIXMsg msg, List<FIXMsg> responses) {
	HeartbeatMsg heartbeat = (HeartbeatMsg) msg;
	if (heartbeat.getTestReqID() != null) {
	    if (heartbeat.getTestReqID().equals(protocol.getLastTestReqID())) {
		protocol.getTimers().stopInputTimeoutTask();
	    }
	}
	protocol.getTimers().stopTestRequestTimeoutTask();
	protocol.getNextRxSeqNo();
    }
    
    protected void handleTestRequest(FIXMsg msg, List<FIXMsg> responses) throws InvalidMsgException {
	TestRequestMsg testRequest = (TestRequestMsg) msg;
	HeartbeatMsg heartbeat;
	if (testRequest.getTestReqID() != null && !testRequest.getTestReqID().isEmpty()) {
	    heartbeat = MessageFiller.buildHeartbeatMsg(protocol, testRequest.getTestReqID());
	} else {
	    heartbeat = MessageFiller.buildHeartbeatMsg(protocol);
	}
	protocol.getNextRxSeqNo();
	responses.add(heartbeat);
    }
    
    protected void handleResendRequest(FIXMsg msg, List<FIXMsg> responses) throws InvalidMsgException {
	ResendRequestMsg resendRequest = (ResendRequestMsg) msg;
	if (resendRequest.getHeader().getPossDupFlag() != null && resendRequest.getHeader().getPossDupFlag()) {
	    if (protocol.getLastResendRequest() != null) {
		if (protocol.getLastResendRequest().getBeginSeqNo().equals(resendRequest.getBeginSeqNo())) {
		    // ignore duplicate
		    return;
		} else {
		    protocol.setLastResendRequest(resendRequest);
		}
	    } else {
		protocol.setLastResendRequest(resendRequest);
	    }
	} else {
	    protocol.setLastResendRequest(resendRequest);
	}
	int beginSeqNo = resendRequest.getBeginSeqNo();
	int endSeqNo = resendRequest.getEndSeqNo();
	if (protocol.getConfiguration().getResendEndSeqNum() != null && protocol.getConfiguration().getResendEndSeqNum().equals(resendRequest.getEndSeqNo())) {
	    endSeqNo = protocol.getTxSeqNo();
	}
	NavigableMap<Integer, FIXMsg> messages = protocol.getHistoryCache().getMessages(beginSeqNo, endSeqNo);
	messages.values().stream().forEach((message) -> {
	    responses.add(message);
	});
	protocol.getNextRxSeqNo();
    }
  
    protected ResendRequestMsg buildResendRequest(int expSeqNum, int msgSeqNum) throws DisconnectSessionException, InvalidMsgException {
	if (protocol.getGap() == null) {
	    protocol.setGap(new SeqGap(expSeqNum, msgSeqNum));
	}
	if (protocol.getGap().getFirst() == expSeqNum) {
	    protocol.getGap().resend();
	    if (protocol.getGap().getResend() < MAX_NUM_OF_RESENDS) {
		ResendRequestMsg resendRequest = MessageFiller.buildResendRequestMsg(protocol);
		resendRequest.setBeginSeqNo(protocol.getGap().getFirst());
		if (protocol.getConfiguration().getResendEndSeqNum() != null) {
		    resendRequest.setEndSeqNo(protocol.getConfiguration().getResendEndSeqNum());
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
	    if (protocol.getConfiguration().getResendEndSeqNum() != null) {
		resendRequest.setEndSeqNo(protocol.getConfiguration().getResendEndSeqNum());
	    } else {
		resendRequest.setEndSeqNo(protocol.getGap().getLast());
	    }
	    return resendRequest;
	}
    }
    
    protected void handleLogout(FIXMsg msg) throws InvalidMsgException {
	LogoutMsg logout = (LogoutMsg) msg;
	Log.log(Level.INFO, "Logout response received. Reason : {0} Text {1}", new Object[] {getLogoutStatus(logout), logout.getText()});
    }
  
    protected String getLogoutStatus(LogoutMsg logout) {
	if (MsgUtil.compare(logout.getHeader().getBeginString(), BeginString.FIXT_1_1) >= 0 && logout.getHeader().getApplVerID() != null) {
	    if (MsgUtil.compare(logout.getHeader().getApplVerID(), ApplVerID.FIX50SP1) >= 0) {
		return logout.getSessionStatus() != null ? logout.getSessionStatus().name() : "";
	    }
	}
	return "";
    }

    //-------------------------------------------------------------------------------------------------------

    private void validateIncomingLogonMessageAddress(FIXMsg message) throws DisconnectSessionException {
	if (message != null) {
	    if (!protocol.targetCompID.equals(message.getHeader().getSenderCompID())) {
		String errMsg = "SenderCompID [" + message.getHeader().getSenderCompID() + "] does not matches the session configured ["
			+ protocol.targetCompID + "]";
		Log.severe(errMsg);
		throw new DisconnectSessionException(errMsg);
	    }
	    if (protocol.targetSubID != null && (message.getHeader().getSenderSubID() == null || !protocol.targetSubID.equals(message.getHeader().getSenderSubID()))) {
		String errMsg = "SenderSubID [" + message.getHeader().getSenderSubID() + "] does not matches the session configured ["
			+ protocol.targetSubID + "]";
		Log.severe(errMsg);
		throw new DisconnectSessionException(errMsg);
	    }
	    if (protocol.targetLocationID != null && (message.getHeader().getSenderLocationID() == null || !protocol.targetLocationID.equals(message.getHeader().getSenderLocationID()))) {
		String errMsg = "SenderLocationID [" + message.getHeader().getSenderLocationID() + "] does not matches the session configured ["
			+ protocol.targetLocationID + "]";
		Log.severe(errMsg);
		throw new DisconnectSessionException(errMsg);
	    }
	}
    }
    
    private void validateIncomingLogoutMessageAddress(FIXMsg message) throws DisconnectSessionException {
	if (message != null) {
	    if (!protocol.targetCompID.equals(message.getHeader().getSenderCompID())) {
		String errMsg = "SenderCompID [" + message.getHeader().getSenderCompID() + "] does not matches the session configured ["
			+ protocol.targetCompID + "]";
		Log.severe(errMsg);
		throw new DisconnectSessionException(errMsg);
	    }
	    if (protocol.targetSubID != null && (message.getHeader().getSenderSubID() == null || !protocol.targetSubID.equals(message.getHeader().getSenderSubID()))) {
		String errMsg = "SenderSubID [" + message.getHeader().getSenderSubID() + "] does not matches the session configured ["
			+ protocol.targetSubID + "]";
		Log.severe(errMsg);
		throw new DisconnectSessionException(errMsg);
	    }
	    if (protocol.targetLocationID != null && (message.getHeader().getSenderLocationID() == null || !protocol.targetLocationID.equals(message.getHeader().getSenderLocationID()))) {
		String errMsg = "SenderLocationID [" + message.getHeader().getSenderLocationID() + "] does not matches the session configured ["
			+ protocol.targetLocationID + "]";
		Log.severe(errMsg);
		throw new DisconnectSessionException(errMsg);
	    }
	}
    }
    
    private void validateIncomingMessageAddress(FIXMsg message) throws RejectAndLogoutException {
	if (message != null) {
	    if (!protocol.targetCompID.equals(message.getHeader().getSenderCompID())) {
		String errMsg = "SenderCompID [" + message.getHeader().getSenderCompID() + "] does not matches the session configured ["
			+ protocol.targetCompID + "]";
		Log.severe(errMsg);
		protocol.getNextRxSeqNo();
		throw new RejectAndLogoutException(SessionRejectReason.CompIDProblem, message.getHeader().getMsgSeqNum(), 
			message.getHeader().getMsgType(), TagNum.SenderCompID.getValue(), errMsg);
	    } else if (protocol.targetSubID != null && (message.getHeader().getSenderSubID() == null || !protocol.targetSubID.equals(message.getHeader().getSenderSubID()))) {
		String errMsg = "SenderSubID [" + message.getHeader().getSenderSubID() + "] does not matches the session configured ["
			+ protocol.targetSubID + "]";
		Log.severe(errMsg);
		protocol.getNextRxSeqNo();
		throw new RejectAndLogoutException(SessionRejectReason.CompIDProblem, message.getHeader().getMsgSeqNum(),
			message.getHeader().getMsgType(), TagNum.SenderSubID.getValue(), errMsg);
	    } else if (protocol.targetLocationID != null && (message.getHeader().getSenderLocationID() == null || !protocol.targetLocationID.equals(message.getHeader().getSenderLocationID()))) {
		String errMsg = "SenderLocationID [" + message.getHeader().getSenderLocationID() + "] does not matches the session configured ["
			+ protocol.targetLocationID + "]";
		Log.severe(errMsg);
		protocol.getNextRxSeqNo();
		throw new RejectAndLogoutException(SessionRejectReason.CompIDProblem, message.getHeader().getMsgSeqNum(),
			message.getHeader().getMsgType(), TagNum.SenderLocationID.getValue(), errMsg);
	    }
	}
    }

    private boolean isMsgSendTimeValid(FIXMsg message) {
	Date sendTime = message.getHeader().getSendingTime();
	Calendar cal = Calendar.getInstance();
	cal.setTime(sendTime);
	cal.setTimeZone(TimeZone.getTimeZone("UTC"));
	long now = cal.getTimeInMillis();
	return Math.abs(now - sendTime.getTime()) <= MAX_CLOCK_DEVIANCE_MILLIS;
    }
  
}

