/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogoutReceiveClientStatus.java
 *
 * $Id: LogoutReceiveClientStatus.java,v 1.12 2011-04-03 08:00:07 vrotaru Exp $
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
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.engine.process.protocol.state.Status;
import net.hades.fix.message.LogoutMsg;

import java.util.logging.Logger;

/**
 * Processing state in which the client receives a Logout message. <br/>
 * The outcome states are:
 * <ul>
 *      <li>IDLE - reset the transport connection
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 */
public class LogoutReceiveClientStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(LogoutReceiveClientStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.LOGOUT_RECEIVE;

    private boolean expected;

    private LifeCycleCode lifeCycleCode;

    public LogoutReceiveClientStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status;
        LogoutMsg msg = (LogoutMsg) message;
        String errMsg = "Logout message received with reason : " + (msg.getText() != null ? msg.getText() : "None");
        
        stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                BaseSeverityType.FATAL, AlertCode.LOGOUT_RCVD, errMsg, null)));
        LOGGER.severe(errMsg);
        
        if (expected) {
            status = stateProcessor.getStatus(ProtocolState.IDLE);
            stateProcessor.getTimers().resetLogoutTimeoutTask();
            if (ProtocolState.INITIALISED.equals(stateProcessor.getProcessingStage())) {
                stateProcessor.getTimers().getLogoutTimeoutTask().setInitial(true);
            }
            if (stateProcessor.getProtocol().getConfiguration().getResetSeqAtLogout()) {
                stateProcessor.getProtocol().resetSequences();
            }

            if (lifeCycleCode != null) {
                stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                        LifeCycleType.PROTOCOL_CLIENT.name(),
                        lifeCycleCode.name()));
                if (LifeCycleCode.FIX_SESSION_RESTART.equals(lifeCycleCode)) {
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
            stateProcessor.setProcessingStage(ProtocolState.LOGGEDOUT);
        } else {
            stateProcessor.getTimers().resetLogonTimeoutTask();
            status = stateProcessor.getStatus(ProtocolState.LOGOUT_SEND);
            ((LogoutSendClientStatus) status).setLifeCycleCode(LifeCycleCode.FIX_SESSION_RESTART);
            ((LogoutSendClientStatus) status).setExpectLogout(false);
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.LOGOUT_RECEIVE.name();
    }

    @Override
    public void recycle() {
        message = null;
        lifeCycleCode = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }

    public void setExpected(boolean expected) {
        this.expected = expected;
    }
    
}
