/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

import net.hades.fix.message.BinaryMessage;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

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
	    BadFormatMsgException ex =  new BadFormatMsgException(SessionRejectReason.SendingTimeAccuracyProblem, errMsg);
	    ex.setSeqNum(msg.getHeader().getMsgSeqNum());
	    throw ex;
	}
	return msg;
    }
    
    protected FIXMsg validateIncoming(BinaryMessage message) throws InvalidMsgException, BadFormatMsgException, DisconnectSessionException {
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
	validateIncomingMessageAddress(msg);
	if (!isMsgSendTimeValid(msg)) {
	    String errMsg = "SendingTime accuracy problem.";
	    Log.severe(errMsg);
	    BadFormatMsgException ex =  new BadFormatMsgException(SessionRejectReason.SendingTimeAccuracyProblem, errMsg);
	    ex.setSeqNum(msg.getHeader().getMsgSeqNum());
	    throw ex;
	}
	return msg;
    }

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
    
    private void validateIncomingMessageAddress(FIXMsg message) throws BadFormatMsgException {
	if (message != null) {
	    if (!protocol.targetCompID.equals(message.getHeader().getSenderCompID())) {
		String errMsg = "SenderCompID [" + message.getHeader().getSenderCompID() + "] does not matches the session configured ["
			+ protocol.targetCompID + "]";
		Log.severe(errMsg);
		throw new BadFormatMsgException(SessionRejectReason.CompIDProblem, TagNum.SenderCompID.getValue(), errMsg);
	    }
	    if (protocol.targetSubID != null && (message.getHeader().getSenderSubID() == null || !protocol.targetSubID.equals(message.getHeader().getSenderSubID()))) {
		String errMsg = "SenderSubID [" + message.getHeader().getSenderSubID() + "] does not matches the session configured ["
			+ protocol.targetSubID + "]";
		Log.severe(errMsg);
		throw new BadFormatMsgException(SessionRejectReason.CompIDProblem, TagNum.SenderSubID.getValue(), errMsg);
	    }
	    if (protocol.targetLocationID != null && (message.getHeader().getSenderLocationID() == null || !protocol.targetLocationID.equals(message.getHeader().getSenderLocationID()))) {
		String errMsg = "SenderLocationID [" + message.getHeader().getSenderLocationID() + "] does not matches the session configured ["
			+ protocol.targetLocationID + "]";
		Log.severe(errMsg);
		throw new BadFormatMsgException(SessionRejectReason.CompIDProblem, TagNum.SenderLocationID.getValue(), errMsg);
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

