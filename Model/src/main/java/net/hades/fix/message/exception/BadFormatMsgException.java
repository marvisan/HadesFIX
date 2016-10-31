/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BadFormatMsgException.java
 *
 * $Id: BadFormatMsgException.java,v 1.5 2010-02-25 08:37:46 vrotaru Exp $
 */
package net.hades.fix.message.exception;

import net.hades.fix.message.type.SessionRejectReason;

/**
 * The received message is not in a supported format.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 20/08/2008, 19:06:31
 */
public class BadFormatMsgException extends Exception {

    private static final long serialVersionUID = 1L;

    private SessionRejectReason rejectReason;
    private String msgType;
    private Integer tagNum;
    private Integer seqNum;
    

    public BadFormatMsgException(String message) {
        super(message);
    }
        
    public BadFormatMsgException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public BadFormatMsgException(SessionRejectReason rejectReason, String message) {
        super(message);
        this.rejectReason = rejectReason;
    }

    public BadFormatMsgException(SessionRejectReason rejectReason, int tagNum, String message) {
        super(message);
        this.rejectReason = rejectReason;
        this.tagNum = tagNum;
    }

    public BadFormatMsgException(SessionRejectReason rejectReason, String msgType, String message) {
        super(message);
        this.rejectReason = rejectReason;
        this.msgType = msgType;
        this.tagNum = tagNum;
    }
    
    public BadFormatMsgException(SessionRejectReason rejectReason, String msgType, int tagNum, String message) {
        super(message);
        this.rejectReason = rejectReason;
        this.msgType = msgType;
        this.tagNum = tagNum;
    }
    

    public String getMsgType() {
        return msgType;
    }

    public SessionRejectReason getRejectReason() {
        return rejectReason;
    }

    public Integer getTagNum() {
        return tagNum;
    }

    public Integer getSeqNum() {
	return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
	this.seqNum = seqNum;
    }
    
}
