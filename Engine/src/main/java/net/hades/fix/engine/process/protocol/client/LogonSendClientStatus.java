/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LoginSendStatus.java
 *
 * $Id: LogonSendClientStatus.java,v 1.14 2011-04-04 05:41:34 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.client;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.SeqSet;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.engine.process.protocol.state.Status;
import net.hades.fix.message.LogonMsg;
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
 * @version $Revision: 1.14 $
 */
public class LogonSendClientStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(LogonSendClientStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.LOGON_SEND;

    public LogonSendClientStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status;
        try {
            if (ProcessingStage.RESET.equals(stateProcessor.getProcessingStage())) {
                stateProcessor.getProtocol().setTxSeqNo(0);
                status = sendLogonResetSeqNumMsg();
                stateProcessor.setProcessingStage(ProcessingStage.LOGGEDON);
                stateProcessor.setProcessingStage(ProcessingStage.LOGGEDON);
            } else {
                status = sendLogonMsg();
            }
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
        if (ProcessingStage.INITIALISED.equals(stateProcessor.getProcessingStage())) {
            stateProcessor.getProtocol().setSessStartSeqSet(new SeqSet(stateProcessor.getProtocol().getRxSeqNo(), stateProcessor.getProtocol().getTxSeqNo()));
        }
        LogonMsg msg = MessageFiller.buildLogonMsg(stateProcessor.getProtocol());
        stateProcessor.sendProtocolMessage(msg);
        stateProcessor.getTimers().startLogonTimerTask();
        
        return stateProcessor.getStatus(ProtocolState.PROCESSING);
    }

    private Status sendLogonResetSeqNumMsg() throws InvalidMsgException, InterruptedException {
        stateProcessor.getProtocol().setTxSeqNo(0);
        LogonMsg msg = MessageFiller.buildResetSeqNumLogonMsg(stateProcessor.getProtocol());
        msg.setUsername(null);
        msg.setPassword(null);
        stateProcessor.sendProtocolMessage(msg);

        return stateProcessor.getStatus(ProtocolState.PROCESSING);
    }

    private Status handleBuildMessageError(Exception ex) throws InterruptedException {
        String msg = "Invalid message build : " + ex.getMessage();
        stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                BaseSeverityType.FATAL, AlertCode.MSG_FORMAT_ERROR, ex.toString(), ex)));
        LOGGER.severe(msg);
        stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                LifeCycleType.PROTOCOL_CLIENT.name(),
                LifeCycleCode.FIX_SESSION_RESTART.name()));
        stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
        
        return stateProcessor.getStatus(ProtocolState.IDLE);
    }

}
