/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.timer;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.engine.process.protocol.Protocol;

import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.message.HeartbeatMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;

/**
 * Timer task that send a Heartbeat message to the counterparty if there is no activity for the configured interval.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class HeartbeatTimeoutTimerTask extends EngineTimerTask {

    private static final Logger Log = Logger.getLogger(HeartbeatTimeoutTimerTask.class.getName());

    public HeartbeatTimeoutTimerTask(Protocol protocol) {
	super(protocol);
    }

    @Override
    public void run() {
	if (Log.isLoggable(Level.FINEST)) {
	    Log.finest("Heartbeat timeout timer initiated.");
	}

	if (ProcessingStage.LOGGEDON.equals(protocol.getProcessingStage())) {
	    try {
		sendHeartbeat();
	    } catch (InterruptedException ex) {
		Log.log(Level.WARNING, "Heartbeat timer task interrupted", ex);
	    }
	}
    }

    private void sendHeartbeat() throws InterruptedException {
	HeartbeatMsg heartbeatMsg;
	try {
	    heartbeatMsg = MessageFiller.buildHeartbeatMsg(protocol);
	    protocol.getTimers().startHeartbeatTimeoutTask();
	    protocol.writeToTransport(heartbeatMsg);
	} catch (InvalidMsgException | TagNotPresentException | BadFormatMsgException ex) {
	    Log.log(Level.SEVERE, "Heartbeat message could not be built", ex);
	}
    }
}
