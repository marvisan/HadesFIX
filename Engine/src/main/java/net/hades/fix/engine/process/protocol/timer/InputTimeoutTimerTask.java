/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InputTimeoutTimerTask.java
 *
 * $Id: InputTimeoutTimerTask.java,v 1.1 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.timer;

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
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.client.FIXClientStateProcessor;
import net.hades.fix.engine.process.protocol.state.StateProcessor;

import java.util.logging.Logger;

/**
 * Timer task that resets the protocol if no message has been received in a predefined interval of time.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class InputTimeoutTimerTask extends EngineTimerTask {

    private static final Logger LOGGER = Logger.getLogger(InputTimeoutTimerTask.class.getName());

    private long heartbeatOffset;

    public InputTimeoutTimerTask(StateProcessor stateProcessor) {
        super(stateProcessor);
        this.heartbeatOffset = stateProcessor.getProtocol().getConfiguration().getHeartBtOffset().longValue();
    }

    @Override
    public void run() {
        if (ProcessingStage.LOGGEDON.equals(stateProcessor.getProcessingStage())) {
            stateProcessor.setStatus(ProtocolState.IDLE);
            while (!ProtocolState.IDLE.equals(stateProcessor.getStatus().getProtocolState())) {
                stateProcessor.setStatus(ProtocolState.IDLE);
            }
            String logMsg = "Input queue timer expired in [" + heartbeatOffset + "] seconds. Killing the session.";
            LOGGER.severe(logMsg);

            if (stateProcessor instanceof FIXClientStateProcessor) {
                stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                        Alert.createAlert("InputTimeoutTimer", ComponentType.FIXClient.name(), BaseSeverityType.FATAL, AlertCode.INPUT_TIMEOUT_ERROR, logMsg, null)));
                stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                        LifeCycleType.PROTOCOL_CLIENT.name(), LifeCycleCode.FIX_SESSION_RESTART.name()));
            } else {
                stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                        Alert.createAlert("InputTimeoutTimer", ComponentType.FIXServer.name(), BaseSeverityType.FATAL, AlertCode.INPUT_TIMEOUT_ERROR, logMsg, null)));
                stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                        LifeCycleType.PROTOCOL_SERVER.name(), LifeCycleCode.FIX_SESSION_RESTART.name()));
            }
            stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
            stateProcessor.resetAllTimerTasks();
        }
    }
}
