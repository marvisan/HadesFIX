/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogoutSendClientStatus.java
 *
 * $Id: LogoutSendClientStatus.java,v 1.15 2011-04-03 08:00:07 vrotaru Exp $
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
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.engine.process.protocol.state.Status;
import net.hades.fix.message.LogoutMsg;
import net.hades.fix.message.exception.InvalidMsgException;

import java.util.logging.Logger;

/**
 * Implements the FIX client logout state send. <br/>
 * The outcome states are:
 * <ul>
 *      <li>IDLE - in case of error will shutdown session
 * </ul>
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.15 $
 */
public class LogoutSendClientStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(LogoutSendClientStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.LOGOUT_SEND;

    private String text;

    private byte[] encodedText;

    private LifeCycleCode lifeCycleCode;

    private boolean expectLogout;

    public LogoutSendClientStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status;
        try {
            status = sendLogoutMsg();
        } catch (InvalidMsgException ex) {
            status = handleBuildMessageError(ex);
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.LOGOUT_SEND.name();
    }

    @Override
    public void recycle() {
        expectLogout = false;
        message = null;
        text = null;
        encodedText = null;
        lifeCycleCode = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLifeCycleCode(LifeCycleCode lifeCycleCode) {
        this.lifeCycleCode = lifeCycleCode;
    }

    public void setExpectLogout(boolean expectLogout) {
        this.expectLogout = expectLogout;
    }

    private Status sendLogoutMsg() throws InvalidMsgException, InterruptedException {
        Status result;
        LogoutMsg msg = MessageFiller.buildLogoutMsg(stateProcessor.getProtocol());
        msg.setText(text);
        msg.setEncodedText(encodedText);
        stateProcessor.sendProtocolMessage(msg);

        if (stateProcessor.getProtocol().getConfiguration().getResetSeqAtLogout()) {
            stateProcessor.getProtocol().resetSequences();
        }
        stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                        BaseSeverityType.RECOVERABLE, AlertCode.LOGOUT_SEND, text, null)));
        if (expectLogout) {
            ((LogoutReceiveClientStatus) stateProcessor.getStatus(ProtocolState.LOGOUT_RECEIVE)).setExpected(true);
            result = stateProcessor.getStatus(ProtocolState.PROCESSING);
            stateProcessor.getTimers().startLogoutTimerTask();
        } else {
            if (lifeCycleCode != null) {
                stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                        LifeCycleType.PROTOCOL_CLIENT.name(),
                        lifeCycleCode.name()));
                if (LifeCycleCode.FIX_SESSION_RESTART.equals(lifeCycleCode)) {
                    stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                            LifeCycleType.PROTOCOL_CLIENT.name(),
                            LifeCycleCode.FIX_SESSION_RESTART.name()));
                    stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
                } else {
                    stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.ShutdownNow));
                }
            } else {
                stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                        LifeCycleType.PROTOCOL_CLIENT.name(),
                        LifeCycleCode.FIX_SESSION_RESTART.name()));
                stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
            }
            result = stateProcessor.getStatus(ProtocolState.IDLE);
        }
        
        return result;
    }

    private Status handleBuildMessageError(Exception ex) throws InterruptedException {
        String msg = "Invalid message build : " + ex.getMessage();
        stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, ex.toString(), ex)));
        LOGGER.severe(msg);
        
        return stateProcessor.getStatus(ProtocolState.PROCESSING);
    }
}
