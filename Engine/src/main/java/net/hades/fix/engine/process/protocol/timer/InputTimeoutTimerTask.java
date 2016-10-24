/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.timer;

import java.util.logging.Logger;

import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.Protocol;
import net.hades.fix.engine.process.protocol.client.FixClient;
import net.hades.fix.engine.process.protocol.server.FixServer;

/**
 * Timer task that resets the protocol if no message has been received in a predefined interval of time.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class InputTimeoutTimerTask extends EngineTimerTask {

    private static final Logger Log = Logger.getLogger(InputTimeoutTimerTask.class.getName());

    public InputTimeoutTimerTask(Protocol protocol) {
	super(protocol);
    }

    @Override
    public void run() {
	if (ProcessingStage.LOGGEDON.equals(protocol.getProcessingStage())) {
	    String logMsg = "Input queue timer expired. Killing the session.";
	    Log.severe(logMsg);

	    if (protocol instanceof FixClient) {
		protocol.getSessionCoordinator().onAlertEvent(new AlertEvent(this,
			Alert.createAlert("InputTimeoutTimer", FixClient.class.getSimpleName(), BaseSeverityType.FATAL, AlertCode.INPUT_TIMEOUT_ERROR, logMsg, null)));
		protocol.getSessionCoordinator().onLifeCycleEvent(new LifeCycleEvent(protocol,
			LifeCycleType.PROTOCOL_CLIENT.name(), LifeCycleCode.FIX_SESSION_SHUTDOWN.name()));
	    } else {
		protocol.getSessionCoordinator().onAlertEvent(new AlertEvent(this,
			Alert.createAlert("InputTimeoutTimer", FixServer.class.getSimpleName(), BaseSeverityType.FATAL, AlertCode.INPUT_TIMEOUT_ERROR, logMsg, null)));
		protocol.getSessionCoordinator().onLifeCycleEvent(new LifeCycleEvent(protocol,
			LifeCycleType.PROTOCOL_SERVER.name(), LifeCycleCode.FIX_SESSION_SHUTDOWN.name()));
	    }
	    protocol.getSessionCoordinator().shutdownImmediate();
	}
    }
}
