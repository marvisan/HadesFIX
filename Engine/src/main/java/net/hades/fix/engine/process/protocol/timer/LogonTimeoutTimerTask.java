/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogonTimeoutTimerTask.java
 *
 * $Id: LogonTimeoutTimerTask.java,v 1.2 2011-04-04 05:41:34 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.timer;

import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.protocol.ProtocolState;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.protocol.client.FIXClientStateProcessor;
import net.hades.fix.engine.process.protocol.state.StateProcessor;

/**
 * Timer task that restart the session if the logon does not arrive in <code>logonTimeout</code>
 * seconds.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public class LogonTimeoutTimerTask extends EngineTimerTask {

    private static final Logger LOGGER = Logger.getLogger(LogonTimeoutTimerTask.class.getName());

    private int logonTimeout;

    public LogonTimeoutTimerTask(StateProcessor stateProcessor) {
        super(stateProcessor);
        this.logonTimeout = stateProcessor.getProtocol().getConfiguration().getLogonTimeout();
    }

    @Override
    public void run() {
        stateProcessor.setStatus(ProtocolState.IDLE);

        String logMsg = "No response received for the Logon message in [" + logonTimeout + "] seconds. Restarting the session.";
        LOGGER.severe(logMsg);

        if (stateProcessor instanceof FIXClientStateProcessor) {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("LogonTimeoutTimer", ComponentType.FIXClient.name(), BaseSeverityType.FATAL, AlertCode.LOGOUT_TIMEOUT_ERROR, logMsg, null)));
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                    LifeCycleType.PROTOCOL_CLIENT.name(), LifeCycleCode.FIX_SESSION_RESTART.name()));
        } else {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("LogonTimeoutTimer", ComponentType.FIXServer.name(), BaseSeverityType.FATAL, AlertCode.LOGOUT_TIMEOUT_ERROR, logMsg, null)));
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                    LifeCycleType.PROTOCOL_SERVER.name(), LifeCycleCode.FIX_SESSION_RESTART.name()));
        }
        stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
        stateProcessor.resetAllTimerTasks();
    }
}
