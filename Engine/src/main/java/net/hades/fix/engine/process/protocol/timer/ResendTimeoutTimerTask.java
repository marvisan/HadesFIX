/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ResendTimeoutTimerTask.java
 *
 * $Id: ResendTimeoutTimerTask.java,v 1.1 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.timer;

import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.client.FIXClientStateProcessor;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.message.FIXMsg;

import java.util.Deque;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Timer task that resets the sequence number to last received message in the gap if the <code>enableResenTimeout</code>
 * option is enabled.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class ResendTimeoutTimerTask extends EngineTimerTask {

    private static final Logger LOGGER = Logger.getLogger(ResendTimeoutTimerTask.class.getName());

    private SessionType sessionType;

    public ResendTimeoutTimerTask(StateProcessor stateProcessor) {
        super(stateProcessor);
        if (stateProcessor instanceof FIXClientStateProcessor) {
            sessionType = SessionType.BUY;
        } else {
            sessionType = SessionType.SELL;
        }
    }

    @Override
    public void run() {
        // messages are in descending order - relay all the messages to the consumer stream handlers
        Deque<FIXMsg> msgs = stateProcessor.getResequencingBuffer().getLastConsequentMessages();
        if (msgs.size() > 0) {
            int lastSeq = 0;
            for (Iterator<FIXMsg> it = msgs.descendingIterator(); it.hasNext();) {
                FIXMsg msg = it.next();
                lastSeq = msg.getHeader().getMsgSeqNum();
                stateProcessor.getProtocol().relayMessage(msg);
            }
            if (lastSeq > 0) {
                stateProcessor.getProtocol().setRxSeqNo(lastSeq);
            }
        } else {
            stateProcessor.getProtocol().setRxSeqNo(stateProcessor.getGap().getLast());
        }
        String logMsg = "No response received for the ResendRequest message in ["
                + stateProcessor.getProtocol().getConfiguration().getResendTimeout()
                + "] milliseconds. Setting the incoming sequence number to ["
                + stateProcessor.getProtocol().getRxSeqNo() + "].";
        LOGGER.warning(logMsg);
        if (SessionType.BUY.equals(sessionType)) {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("ResendTimeoutTimerTask", ComponentType.FIXClient.toString(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg, null)));
        } else {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert("ResendTimeoutTimerTask", ComponentType.FIXServer.toString(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg, null)));
        }

        stateProcessor.setGap(null);
        // clear all the gaps
        stateProcessor.clearResequencingBuffer();
    }
}
