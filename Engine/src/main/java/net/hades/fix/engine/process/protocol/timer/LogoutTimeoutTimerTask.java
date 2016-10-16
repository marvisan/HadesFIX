/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogoutTimeoutTimerTask.java
 *
 * $Id: LogoutTimeoutTimerTask.java,v 1.1 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.timer;

import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.SeqSet;
import net.hades.fix.engine.process.protocol.state.StateProcessor;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.protocol.client.FIXClientStateProcessor;

/**
 * Timer task that restart the session if the logout does not arrive in <code>logoutTimeout</code>
 * seconds.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class LogoutTimeoutTimerTask extends EngineTimerTask {

    private static final Logger LOGGER = Logger.getLogger(LogoutTimeoutTimerTask.class.getName());

    private boolean initial;

    private int timeout;

    public LogoutTimeoutTimerTask(StateProcessor stateProcessor) {
        super(stateProcessor);
        this.timeout = stateProcessor.getProtocol().getConfiguration().getLogoutTimeout();
    }

    @Override
    public void run() {
        String logMsg = "No response received for the Logout message in [" + timeout + "] seconds. Restarting the session.";
        LOGGER.severe(logMsg);
        stateProcessor.setStatus(ProtocolState.IDLE);
        // we spin until the state is IDLE
        while (!ProtocolState.IDLE.equals(stateProcessor.getStatus().getProtocolState())) {
            stateProcessor.setStatus(ProtocolState.IDLE);
        }

        SeqSet startSessSeq = stateProcessor.getProtocol().getSessStartSeqSet();
        if (initial) {
            stateProcessor.getProtocol().setRxSeqNo(startSessSeq.getRxSeq());
            stateProcessor.getProtocol().setTxSeqNo(startSessSeq.getTxSeq());
        }
        LOGGER.log(Level.INFO, "Session sequences set to : RX : {0} TX : {1}", new Object[]{stateProcessor.getProtocol().getRxSeqNo() + 1, stateProcessor.getProtocol().getTxSeqNo() + 1});

        if (stateProcessor instanceof FIXClientStateProcessor) {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("LogoutTimeoutTimer", ComponentType.FIXClient.name(), BaseSeverityType.FATAL, AlertCode.LOGOUT_TIMEOUT_ERROR, logMsg, null)));
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(
                    new LifeCycleEvent(stateProcessor.getProtocol(), LifeCycleType.PROTOCOL_CLIENT.name(), LifeCycleCode.FIX_SESSION_RESTART.name()));
        } else {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("LogoutTimeoutTimer", ComponentType.FIXServer.name(), BaseSeverityType.FATAL, AlertCode.LOGOUT_TIMEOUT_ERROR, logMsg, null)));
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(
                    new LifeCycleEvent(stateProcessor.getProtocol(), LifeCycleType.PROTOCOL_SERVER.name(), LifeCycleCode.FIX_SESSION_RESTART.name()));
        }
        stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
        stateProcessor.resetAllTimerTasks();
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }
}
