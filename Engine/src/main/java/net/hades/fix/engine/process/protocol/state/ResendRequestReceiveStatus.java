/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ResendRequestReceiveStatus.java
 *
 * $Id: ResendRequestReceiveStatus.java,v 1.8 2011-04-03 08:00:05 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.SeqGap;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.exception.InvalidMsgException;

import java.util.logging.Logger;

/**
 * Processing state in which the the server receives a ResendRequest message.<br/>
 * The outcome states are:
 * <ul>
 *      <li>PROCESSING - in case of error
 *      <li>RESEND_GAP_MESSAGES - in case of success
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 */
public class ResendRequestReceiveStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(ResendRequestReceiveStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.RESEND_REQUEST_RECEIVE;

    public ResendRequestReceiveStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status;
        try {
            status = stateProcessor.getStatus(ProtocolState.RESEND_GAP_MESSAGES);
            if (message != null) {
                int endSeqNum = ((ResendRequestMsg) message).getEndSeqNo();
                if (endSeqNum == 0 || endSeqNum >= 9999999) {
                    endSeqNum = stateProcessor.getProtocol().getTxSeqNo();
                }
                SeqGap seqGap = new SeqGap(((ResendRequestMsg) message).getBeginSeqNo(), endSeqNum);
                ((ResendGapMessagesStatus) status).setSeqGap(seqGap);
            } else {
                throw new InvalidMsgException("ResendRequestMsg was not set in the status");
            }
        } catch (InvalidMsgException ex) {
            status = handleBuildMessageError(ex);
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.RESEND_REQUEST_RECEIVE.name();
    }

    @Override
    public void recycle() {
        message = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
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
