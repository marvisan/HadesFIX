/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestRequestReceiveStatus.java
 *
 * $Id: TestRequestReceiveStatus.java,v 1.6 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.message.TestRequestMsg;

/**
 * Processing state in which the client receives a TestRequest message.<br/>
 * The outcome states are:
 * <ul>
 *      <li>HEARTBEAT_SEND - in case of success
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 */
public class TestRequestReceiveStatus extends Status {

    private static final ProtocolState STATE = ProtocolState.TEST_REQUEST_RECEIVE;

    public TestRequestReceiveStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        return respondeHeartbeat();
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.TEST_REQUEST_RECEIVE.name();
    }

    @Override
    public void recycle() {
        message = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }

    private Status respondeHeartbeat() throws InterruptedException {
        Status status = stateProcessor.getStatus(ProtocolState.HEARTBEAT_SEND);
        ((HeartbeatSendStatus) status).setTestReqID(((TestRequestMsg) message).getTestReqID());

        return status;
    }

}
