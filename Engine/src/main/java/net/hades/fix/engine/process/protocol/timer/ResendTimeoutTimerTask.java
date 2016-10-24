/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.timer;

import java.util.Deque;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.Protocol;
import net.hades.fix.engine.process.protocol.client.FixClient;
import net.hades.fix.engine.process.protocol.server.FixServer;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;

/**
 * Timer task that resets the sequence number to last received message in the gap if the <code>enableResenTimeout</code>
 * option is enabled.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ResendTimeoutTimerTask extends EngineTimerTask {

    private static final Logger Log = Logger.getLogger(ResendTimeoutTimerTask.class.getName());

    public ResendTimeoutTimerTask(Protocol protocol) {
        super(protocol);
    }

    @Override
    public void run() {
        // messages are in descending order - relay all the messages to the consumer stream handlers
        Deque<FIXMsg> msgs = protocol.getResequencingBuffer().getLastConsequentMessages();
        if (msgs.size() > 0) {
            int lastSeq = 0;
            for (Iterator<FIXMsg> it = msgs.descendingIterator(); it.hasNext();) {
                FIXMsg msg = it.next();
                lastSeq = msg.getHeader().getMsgSeqNum();
		
		try {
		    protocol.writeToTransport(msg);
		} catch (TagNotPresentException | BadFormatMsgException | InterruptedException ex) {
		    Log.log(Level.SEVERE, "Could not encoe FIX message", ex);
		}
            }
            if (lastSeq > 0) {
                protocol.setRxSeqNo(lastSeq);
            }
        } else {
            protocol.setRxSeqNo(protocol.getGap().getLast());
        }
        String logMsg = "No response received for the ResendRequest message in ["
                + protocol.getConfiguration().getResendTimeout()
                + "] milliseconds. Setting the incoming sequence number to ["
                + protocol.getRxSeqNo() + "].";
        Log.warning(logMsg);
        if (protocol instanceof FixClient) {
            protocol.getSessionCoordinator().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("ResendTimeoutTimerTask", FixClient.class.getSimpleName(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg, null)));
        } else {
            protocol.getSessionCoordinator().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("ResendTimeoutTimerTask", FixServer.class.getSimpleName(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg, null)));
        }

        protocol.setGap(null);
        // clear all the gaps
        protocol.getResequencingBuffer().clear();
    }
}
