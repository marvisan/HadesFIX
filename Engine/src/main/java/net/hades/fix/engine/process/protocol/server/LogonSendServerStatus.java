/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LoginSendStatus.java
 *
 * $Id: LogonSendServerStatus.java,v 1.13 2011-04-03 08:00:05 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.server;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.engine.process.protocol.state.Status;
import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.exception.InvalidMsgException;

import java.util.logging.Logger;

/**
 * Implements the FIX client login state send. <br/>
 * The outcome states are:
 * <ul>
 *      <li>LOGON_RECEIVE - after the logon message is sent
 *      <li>IDLE - in case of error will shutdown session
 * </ul>
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.13 $
 */
public class LogonSendServerStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(LogonSendServerStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.LOGON_SEND;

    public LogonSendServerStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status;
        try {
            if (ProcessingStage.RESET.equals(processingStage)) {
                status = sendLogonResetSeqNumMsg();
            } else {
                status = sendLogonMsg();
            }
            stateProcessor.setProcessingStage(ProcessingStage.LOGGEDON);
        } catch (InvalidMsgException ex) {
            status = handleBuildMessageError(ex);
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.LOGON_SEND.name();
    }

    @Override
    public void recycle() {
        message = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }

    private Status sendLogonMsg() throws InvalidMsgException, InterruptedException {
        LogonMsg msg = MessageFiller.buildLogonMsg(stateProcessor.getProtocol());
        try {
            msg.setUsername(null);
            msg.setPassword(null);
        } catch (UnsupportedOperationException ex) {
            msg.setRawData(null);
            msg.setRawDataLength(null);
        }
        stateProcessor.sendProtocolMessage(msg);
        ResendRequestMsg resendRequest = stateProcessor.getGapRequestMessage();
        if (resendRequest != null) {
            // if there is a resend request message then send it immediately after login
            resendRequest.getHeader().setMsgSeqNum(stateProcessor.getProtocol().getNextTxSeqNo());
            stateProcessor.sendProtocolMessage(stateProcessor.getGapRequestMessage());
            stateProcessor.setGapRequestMessage(null);
        }

        return stateProcessor.getStatus(ProtocolState.PROCESSING);
    }

    private Status sendLogonResetSeqNumMsg() throws InvalidMsgException, InterruptedException {
        stateProcessor.getProtocol().setTxSeqNo(0);
        LogonMsg msg = MessageFiller.buildResetSeqNumLogonMsg(stateProcessor.getProtocol());
        try {
            msg.setUsername(null);
            msg.setPassword(null);
        } catch (UnsupportedOperationException ex) {
            msg.setRawData(null);
            msg.setRawDataLength(null);
        }
        stateProcessor.sendProtocolMessage(msg);

        return stateProcessor.getStatus(ProtocolState.PROCESSING);
    }

    private Status handleBuildMessageError(Exception ex) throws InterruptedException {
        String msg = "Invalid message build : " + ex.getMessage();
        stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                BaseSeverityType.FATAL, AlertCode.MSG_FORMAT_ERROR, ex.toString(), ex)));
        LOGGER.severe(msg);
        
        return stateProcessor.getStatus(ProtocolState.IDLE);
    }
}
