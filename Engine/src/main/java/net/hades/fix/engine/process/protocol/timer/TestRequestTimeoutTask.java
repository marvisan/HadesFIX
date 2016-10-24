/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.timer;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.engine.process.protocol.Protocol;

import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.message.TestRequestMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.util.format.DateFormatter;

/**
 * Timer task that sends a TestRequest message to the counterparty if there is no response in the configured interval.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class TestRequestTimeoutTask extends EngineTimerTask {

    private static final Logger Log = Logger.getLogger(TestRequestTimeoutTask.class.getName());

    public TestRequestTimeoutTask(Protocol protocol) {
	super(protocol);
    }

    @Override
    public void run() {
	if (Log.isLoggable(Level.FINEST)) {
	    Log.finest("Test request timer initiated.");
	}

	if (ProcessingStage.LOGGEDON.equals(protocol.getProcessingStage())) {
	    try {
		sendTestRequest();
	    } catch (InterruptedException ex) {
		Log.log(Level.WARNING, "TestRequest timer task interrupted", ex);
	    }
	}
    }

    private void sendTestRequest() throws InterruptedException {
	try {
	    TestRequestMsg msg = MessageFiller.buildTestRequestMsg(protocol);
	    String testReqID = DateFormatter.getFixTSFormat().format(new Date());
	    msg.setTestReqID(testReqID);
	    protocol.writeToTransport(msg);
	    protocol.getTimers().startInputTimeoutTask();
	    protocol.getLastSeqNo(MsgType.TestRequest.name());
	} catch (InvalidMsgException | TagNotPresentException | BadFormatMsgException ex) {
	    Log.log(Level.SEVERE, "TestRequest message could not be built", ex);
	}
    }

}
