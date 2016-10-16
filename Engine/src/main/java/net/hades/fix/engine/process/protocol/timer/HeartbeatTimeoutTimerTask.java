/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HeartbeatTimeoutTimerTask.java
 *
 * $Id: HeartbeatTimeoutTimerTask.java,v 1.1 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.timer;

import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.server.FIXServerStateProcessor;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.message.HeartbeatMsg;
import net.hades.fix.message.exception.InvalidMsgException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Timer task that send a Heartbeat message to the counterparty if there is no activity for the
 * configured interval.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class HeartbeatTimeoutTimerTask extends EngineTimerTask {

    private static final Logger LOGGER = Logger.getLogger(HeartbeatTimeoutTimerTask.class.getName());

    private long allowedInterval;

    public HeartbeatTimeoutTimerTask(StateProcessor stateProcessor) {
        super(stateProcessor);
        this.allowedInterval = (long) (0.05d * (double) stateProcessor.getProtocol().getConfiguration().getHeartBtInt()) * 1000;
    }

    @Override
    public void run() {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest("Heartbeat timer initiated.");
        }

        if (ProcessingStage.LOGGEDON.equals(stateProcessor.getProcessingStage())) {
            try {
                long currentTimestamp = System.currentTimeMillis();
                if (Math.abs(currentTimestamp - stateProcessor.getHeartbeatTimestamp().getTimestamp()) > allowedInterval) {
                    sendHeartbeatRequest();
                    stateProcessor.getHeartbeatTimestamp().setTimestamp(currentTimestamp);
                }
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Heartbeat timer task interrupted. Message was : {0}", ex.toString());
            } catch (InvalidMsgException ex) {
                handleBuildMessageError(ex);
            }
        }
    }

    private void sendHeartbeatRequest() throws InvalidMsgException, InterruptedException {
        HeartbeatMsg heartbeatMsg = MessageFiller.buildHeartbeatMsg(stateProcessor.getProtocol());
        stateProcessor.getTimers().startInputTimeoutTask();
        stateProcessor.sendProtocolMessage(heartbeatMsg);
    }

    private void handleBuildMessageError(Exception ex) {
        String msg = "Invalid message build : " + ex.getMessage();
        if (stateProcessor instanceof FIXServerStateProcessor) {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("HeartbeatTimeoutTimer", ComponentType.FIXClient.toString(),
                            BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, ex.toString(), ex)));
        } else {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("HeartbeatTimeoutTimer", ComponentType.FIXServer.toString(),
                    BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, ex.toString(), ex)));
        }
        LOGGER.severe(msg);
    }
}
