/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NavigableMap;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.protocol.server.LogoutReceiveServerStatus;

import net.hades.fix.message.BinaryMessage;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.HeartbeatMsg;
import net.hades.fix.message.LogoutMsg;
import net.hades.fix.message.RejectMsg;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.SequenceResetMsg;
import net.hades.fix.message.TestRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import static java.awt.SystemColor.text;

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

    protected FIXMsg validateIncomingLogin(BinaryMessage message) throws InvalidMsgException, BadFormatMsgException, DisconnectSessionException {
	byte[] byteFixMsg = message.getRawMessage();
	FIXMsg msg = FIXMsgBuilder.build(byteFixMsg);
	if (msg.getHeader().getMsgSeqNum() == null || msg.getHeader().getMsgSeqNum() <= 0) {
	    throw new InvalidMsgException("No sequence number set for this message");
	}
	if (protocol.getMaxMsgSize() > 0 && byteFixMsg.length > protocol.getMaxMsgSize()) {
	    // discard messages larger than maxMsgSize
	    String errMsg = String.format("Discarded message with size [%s] greater than allowed size [%s].", byteFixMsg.length, protocol.getMaxMsgSize());
	    BadFormatMsgException ex = new BadFormatMsgException(SessionRejectReason.Other, errMsg);
	    ex.setSeqNum(msg.getHeader().getMsgSeqNum());
	    throw ex;
	}
	validateIncomingLoginMessageAddress(msg);
	if (!isMsgSendTimeValid(msg)) {
	    String errMsg = "SendingTime accuracy problem.";
	    Log.severe(errMsg);
	    BadFormatMsgException ex = new BadFormatMsgException(SessionRejectReason.SendingTimeAccuracyProblem, errMsg);
	    ex.setSeqNum(msg.getHeader().getMsgSeqNum());
	    throw ex;
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

    //-------------------------------------------------------------------------------------------------------

    private void validateIncomingLoginMessageAddress(FIXMsg message) throws DisconnectSessionException {
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

