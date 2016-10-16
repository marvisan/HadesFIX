/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ClientState.java
 *
 * $Id: ProtocolState.java,v 1.4 2011-04-03 08:00:07 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

/**
 * State of the client FIX engine machine.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 */
public enum ProtocolState {

    IDLE,
    LOGON_RECEIVE,
    LOGON_SEND,
    LOGON_RESET_SEQ_NUM_SEND,
    LOGOUT_RECEIVE,
    LOGOUT_SEND,
    HEARTBEAT_RECEIVE,
    HEARTBEAT_SEND,
    PROCESSING,
    REJECT_RECEIVE,
    REJECT_SEND,
    RESEND_REQUEST_RECEIVE,
    RESEND_REQUEST_SEND,
    RESEND_GAP_MESSAGES,
    SEQUENCE_RESET_RECEIVE,
    SEQUENCE_RESET_SEND,
    TEST_REQUEST_RECEIVE,
    TEST_REQUEST_SEND;

    private static final long serialVersionUID = 1L;
}
