/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.mgmt.alert;

/**
 * Code for alert. Used in taken a decision.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public enum AlertCode {

    COMPONENT_STARTED,
    TRANSP_CONNECT,
    TRANSP_DISCONNECT,
    NUM_MAX_RETRIES_EXCEEDED,
    TRANSPORT_ERROR,
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
