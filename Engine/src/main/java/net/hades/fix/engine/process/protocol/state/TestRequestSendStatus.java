/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestRequestSendStatus.java
 *
 * $Id: TestRequestSendStatus.java,v 1.11 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.message.TestRequestMsg;
import net.hades.fix.message.exception.InvalidMsgException;

import java.util.logging.Logger;

/**
 * Processing state in which the client sends a TestRequest message. <br/>
 * The outcome states are:
 * <ul>
 *      <li>PROCESSING - in case of success/error
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 */
public class TestRequestSendStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(TestRequestSendStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.TEST_REQUEST_SEND;

    private String testReqID;

    public TestRequestSendStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status;
        try {
            status = sendTestRequest();
        } catch (InvalidMsgException ex) {
            status = handleBuildMessageError(ex);
        }
        testReqID = null;

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.TEST_REQUEST_SEND.name();
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }
    
    @Override
    public void recycle() {
        message = null;
        testReqID = null;
    }

    public String getTestReqID() {
        return testReqID;
    }

    public void setTestReqID(String testReqID) {
        this.testReqID = testReqID;
    }

    private Status sendTestRequest() throws InvalidMsgException, InterruptedException {
        TestRequestMsg msg = MessageFiller.buildTestRequestMsg(stateProcessor.getProtocol());
        msg.setTestReqID(testReqID);
        stateProcessor.sendProtocolMessage(msg);

        return stateProcessor.getStatus(ProtocolState.PROCESSING);
    }

    private Status handleBuildMessageError(Exception ex) throws InterruptedException {
        String msg = "Invalid message build : " + ex.getMessage();
        if (SessionType.BUY.equals(sessionType)) {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                            BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, ex.toString(), ex)));
        } else {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                    BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, ex.toString(), ex)));
        }
        LOGGER.severe(msg);
        
        return stateProcessor.getStatus(ProtocolState.PROCESSING);
    }
}
