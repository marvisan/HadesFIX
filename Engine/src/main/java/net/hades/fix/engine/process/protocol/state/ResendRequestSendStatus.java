/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ResendRequestSendStatus.java
 *
 * $Id: ResendRequestSendStatus.java,v 1.9 2011-04-03 08:00:05 vrotaru Exp $
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
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.exception.InvalidMsgException;

import java.util.logging.Logger;

/**
 * Processing state in which the the server send a ResendRequest message.<br/>
 * The outcome states are:
 * <ul>
 *      <li>PROCESSING - in case of success/error
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 */
public class ResendRequestSendStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(ResendRequestSendStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.RESEND_REQUEST_SEND;

    public ResendRequestSendStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status;
        try {
            status = sendResendRequestStatus();
        } catch (InvalidMsgException ex) {
            status = handleBuildMessageError(ex);
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.RESEND_REQUEST_SEND.name();
    }

    @Override
    public void recycle() {
        message = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }

    private Status sendResendRequestStatus() throws InvalidMsgException, InterruptedException {
        Status result = stateProcessor.getStatus(ProtocolState.PROCESSING);
        if (stateProcessor.getGap() != null) {
            ResendRequestMsg resendRequestMsg = MessageFiller.buildResendRequestMsg(stateProcessor.getProtocol());
            resendRequestMsg.setBeginSeqNo(stateProcessor.getGap().getFirst());
            if (stateProcessor.getProtocol().getConfiguration().getResendEndSeqNum() != null && !stateProcessor.getProtocol().getConfiguration().getResendEndSeqNum().isEmpty()) {
                resendRequestMsg.setEndSeqNo(new Integer(stateProcessor.getProtocol().getConfiguration().getResendEndSeqNum()));
            } else {
                resendRequestMsg.setEndSeqNo(stateProcessor.getGap().getLast());
            }
            stateProcessor.sendProtocolMessage(resendRequestMsg);
            if (stateProcessor.getProtocol().getConfiguration().getEnableResendTimeout()) {
                // start the resend timeout timer
                stateProcessor.getTimers().startResendTimerTask();
            }
            stateProcessor.setProcessingStage(ProtocolState.LOGGEDON);
        } else {
            throw new InvalidMsgException("Sequence gap numbers have not been set.");
        }

        return result;
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
