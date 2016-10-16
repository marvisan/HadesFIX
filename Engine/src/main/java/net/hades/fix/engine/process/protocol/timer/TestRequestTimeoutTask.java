/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestRequestTimeoutTask.java
 *
 * $Id: TestRequestTimeoutTask.java,v 1.1 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.timer;

import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.client.FIXClientStateProcessor;
import net.hades.fix.engine.process.protocol.state.HeartbeatReceiveStatus;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.message.TestRequestMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.util.format.DateFormatter;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Timer task that sends a TestRequest message to the counterparty if there is no response in the
 * configured interval.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class TestRequestTimeoutTask extends EngineTimerTask {

    private static final Logger LOGGER = Logger.getLogger(TestRequestTimeoutTask.class.getName());

    private long allowedInterval;

    public TestRequestTimeoutTask(StateProcessor stateProcessor) {
        super(stateProcessor);
        this.allowedInterval = (long) (0.5d * (double) stateProcessor.getProtocol().getConfiguration().getHeartBtInt()) * 1000;
    }

    @Override
    public void run() {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest("Test request timer initiated.");
        }

        if (ProcessingStage.LOGGEDON.equals(stateProcessor.getProcessingStage())) {
            try {
                long currentTimestamp = System.currentTimeMillis();
                if (Math.abs(currentTimestamp - stateProcessor.getHeartbeatTimestamp().getTimestamp()) > allowedInterval) {
                    sendTestRequest();
                }
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "TestRequest timer task interrupted. Message was : {0}", ex.toString());
            } catch (InvalidMsgException ex) {
                handleBuildMessageError(ex);
            }
        }
    }

    private void sendTestRequest() throws InvalidMsgException, InterruptedException {
        TestRequestMsg msg = MessageFiller.buildTestRequestMsg(stateProcessor.getProtocol());
        String testReqID = DateFormatter.getFixTSFormat().format(new Date());
        msg.setTestReqID(testReqID);
        HeartbeatReceiveStatus htbtRcvdStatus = (HeartbeatReceiveStatus) stateProcessor.getStatus(ProtocolState.HEARTBEAT_RECEIVE);
        htbtRcvdStatus.setExpectTestReqID(testReqID);
        stateProcessor.getTimers().startInputTimeoutTask();
        stateProcessor.sendProtocolMessage(msg);
    }

    private void handleBuildMessageError(Exception ex) {
        String msg = "Invalid message build : " + ex.getMessage();
        if (stateProcessor instanceof FIXClientStateProcessor) {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("TestRequestTimeoutTimer", ComponentType.FIXClient.toString(),
                    BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, ex.toString(), ex)));
        } else {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("TestRequestTimeoutTimer", ComponentType.FIXServer.toString(),
                    BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, ex.toString(), ex)));
        }
        LOGGER.severe(msg);
    }
}
