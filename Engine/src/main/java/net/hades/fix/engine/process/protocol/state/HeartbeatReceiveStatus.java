/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HeartbeatReceiveStatus.java
 *
 * $Id: HeartbeatReceiveStatus.java,v 1.13 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import java.util.logging.Logger;

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
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.message.HeartbeatMsg;

/**
 * Processing state in which the client receives an Heartbeat message. <br/>
 * The outcome states are:
 * <ul>
 *      <li>HEARTBEAT_SEND - in case of success
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.13 $
 */
public class HeartbeatReceiveStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(HeartbeatReceiveStatus.class .getName());

    private static final ProtocolState STATE = ProtocolState.HEARTBEAT_RECEIVE;

    private String expectTestReqID;

    private long allowedInterval;

    public HeartbeatReceiveStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
        this.allowedInterval = (long) (0.05d * (double) stateProcessor.getProtocol().getConfiguration().getHeartBtInt()) * 1000;
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status = stateProcessor.getStatus(ProtocolState.PROCESSING);
        String testReqID = ((HeartbeatMsg) message).getTestReqID();
        if (testReqID != null && !testReqID.isEmpty()) {
            // looks like a response to a TestRequest
            status = checkTestReqID(testReqID);
            expectTestReqID = null;
        } else {
            long currentTimestamp = System.currentTimeMillis();
            if (Math.abs(currentTimestamp - stateProcessor.getHeartbeatTimestamp().getTimestamp()) > allowedInterval) {
                status = respondeHeartbeat();
                stateProcessor.getHeartbeatTimestamp().setTimestamp(currentTimestamp);
            }
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.HEARTBEAT_RECEIVE.name();
    }

    @Override
    public void recycle() {
        message = null;
        expectTestReqID = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }

    public void setExpectTestReqID(String expectTestReqID) {
        this.expectTestReqID = expectTestReqID;
    }

    private Status respondeHeartbeat() throws InterruptedException {
        Status status = stateProcessor.getStatus(ProtocolState.HEARTBEAT_SEND);
        ((HeartbeatSendStatus) status).setTestReqID(((HeartbeatMsg) message).getTestReqID());

        return status;
    }

    private Status checkTestReqID(String testReqID) throws InterruptedException {
        Status status = stateProcessor.getStatus(ProtocolState.PROCESSING);
        String logMsg;

        if (expectTestReqID == null || !expectTestReqID.trim().equals(testReqID)) {
            status = stateProcessor.getStatus(ProtocolState.IDLE);
            logMsg = "Received TestReqID [" + testReqID + "] does not match the sent one [" + expectTestReqID + "].";
            LOGGER.severe(logMsg);
            if (SessionType.BUY.equals(sessionType)) {
                stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                        Alert.createAlert("HeartbeatReceiveStatus", ComponentType.FIXClient.name(),
                        BaseSeverityType.WARNING, AlertCode.TEST_REQUEST_TIMEOUT_ERROR, logMsg, null)));
                stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                        LifeCycleType.PROTOCOL_CLIENT.name(),
                        LifeCycleCode.FIX_SESSION_RESTART.name()));
                
            } else {
                stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                        Alert.createAlert("HeartbeatReceiveStatus", ComponentType.FIXServer.name(),
                        BaseSeverityType.WARNING, AlertCode.TEST_REQUEST_TIMEOUT_ERROR, logMsg, null)));
                stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                        LifeCycleType.PROTOCOL_SERVER.name(),
                        LifeCycleCode.FIX_SESSION_RESTART.name()));
            }
            stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
        }
        
        return status;
    }
}
