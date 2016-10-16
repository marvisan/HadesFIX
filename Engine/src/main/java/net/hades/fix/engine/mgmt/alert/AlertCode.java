/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BaseSeverityType.java
 *
 * $Id: AlertCode.java,v 1.14 2011-04-07 09:57:51 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.alert;

/**
 * Code for alert. Used in taken a decision.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.14 $
 * @created 10/07/2009, 6:12:28 PM
 */
public enum AlertCode {

    COMPONENT_STARTED,
    TRANSP_CONNECT,
    TRANSP_DISCONNECT,
    NUM_MAX_RETRIES_EXCEEDED,
    TRANSP_ABORT,
    LOGON_TIMEOUT_ERROR,
    LOGOUT_TIMEOUT_ERROR,
    INPUT_TIMEOUT_ERROR,
    HEARTBEAT_TIMEOUT_ERROR,
    TEST_REQUEST_TIMEOUT_ERROR,
    LOGOUT_RCVD,
    LOGOUT_SEND,
    PROTOCOL_ERROR,
    MSG_FORMAT_ERROR,
    MSG_SEQUENCE_ERROR,
    MSG_REJECTED,
    HANDLER_PROCESSING_ERROR,
    PRODUCER_STREAM_ERROR,
    BUFFER_OVERFLOW,
    MGMT_COMMAND_ERROR,
    SCHED_TASK_EXEC_ERROR,
    SESSION_RECONNECT_DISABLED,
    SESSION_DESTROYED,
    THREAD_INTERRUPTED,
    CONFIGURATION_ERROR,;
    
    private static final long serialVersionUID = 1L;
}
