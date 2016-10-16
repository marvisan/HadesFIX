/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RejectSendStatus.java
 *
 * $Id: RejectSendStatus.java,v 1.9 2011-04-03 08:00:05 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProtocolStats;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.message.RejectMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.SessionRejectReason;

import java.util.logging.Logger;

/**
 * Processing state in which the client sends an Reject message. <br/>
 * The outcome states are:
 * <ul>
 *      <li>PROCESSING - in case of success
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 */
public class RejectSendStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(RejectSendStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.REJECT_SEND;

    private Exception error;

    private SessionRejectReason reason;

    public RejectSendStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
       Status status;
        try {
            status = sendRejectMsg();
        } catch (InvalidMsgException ex) {
            status = handleBuildMessageError(ex);
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.REJECT_SEND.name();
    }

    @Override
    public void recycle() {
        message = null;
        error = null;
        reason = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }
    
    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public void setReason(SessionRejectReason reason) {
        this.reason = reason;
    }

    private Status sendRejectMsg() throws InvalidMsgException, InterruptedException {
        RejectMsg msg;
        if (reason == null) {
            msg = MessageFiller.buildRejectMsg(stateProcessor.getProtocol(), message, SessionRejectReason.Other, error);
        } else {
            msg = MessageFiller.buildRejectMsg(stateProcessor.getProtocol(), message, reason, error);
        }
        ((ProtocolStats) stateProcessor.getProtocol().getStats()).incrRejMsgCount();
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
