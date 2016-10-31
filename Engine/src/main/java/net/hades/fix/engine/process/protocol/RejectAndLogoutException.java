/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import net.hades.fix.message.type.SessionRejectReason;


/**
 * Rejects and Logout the FIX session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class RejectAndLogoutException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private final SessionRejectReason rejectReason;
    private final String msgType;
    private Integer tagNum;
    private final Integer seqNum;
    
    public RejectAndLogoutException(SessionRejectReason rejectReason, Integer seqNum, String msgType, String message) {
        super(message);
        this.rejectReason = rejectReason;
	this.seqNum = seqNum;
        this.msgType = msgType;
    }
    
    public RejectAndLogoutException(SessionRejectReason rejectReason, Integer seqNum, String msgType, int tagNum, String message) {
        super(message);
        this.rejectReason = rejectReason;
	this.seqNum = seqNum;
        this.msgType = msgType;
        this.tagNum = tagNum;
    }

    public SessionRejectReason getRejectReason() {
	return rejectReason;
    }

    public String getMsgType() {
	return msgType;
    }

    public Integer getTagNum() {
	return tagNum;
    }

    public Integer getSeqNum() {
	return seqNum;
    }

}
