/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RejectReceiveStatus.java
 *
 * $Id: RejectReceiveStatus.java,v 1.6 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.MessageEvent;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.message.RejectMsg;

/**
 * Processing state in which the client receives a Reject message. <br/>
 * The outcome states are:
 * <ul>
 *      <li>PROCESSING - in case of success
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 */
public class RejectReceiveStatus extends Status {

    private static final ProtocolState STATE = ProtocolState.REJECT_RECEIVE;

    public RejectReceiveStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        RejectMsg msg = (RejectMsg) message;
        String errMsg = "Reject message received with reason: " + msg.getText();
        if (SessionType.BUY.equals(sessionType)) {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                    BaseSeverityType.RECOVERABLE, AlertCode.MSG_REJECTED, errMsg, null)));
        } else {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                    BaseSeverityType.RECOVERABLE, AlertCode.MSG_REJECTED, errMsg, null)));
        }
        stateProcessor.getProtocol().getEventProcessor().onMessageEvent(new MessageEvent(this, msg));

        return stateProcessor.getStatus(ProtocolState.PROCESSING);
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.REJECT_RECEIVE.name();
    }

    @Override
    public void recycle() {
        message = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }
}
