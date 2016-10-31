/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Status.java
 *
 * $Id: Status.java,v 1.29 2011-04-03 08:00:05 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.client.FIXClientStateProcessor;
import net.hades.fix.engine.process.protocol.client.LogoutSendClientStatus;
import net.hades.fix.engine.process.protocol.server.FIXServerStateProcessor;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.LogoutMsg;
import net.hades.fix.message.RejectMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.MsgUtil;

/**
 * State of the processing machine.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.29 $
 */
public abstract class Status {

    private static final Logger LOGGER = Logger.getLogger(Status.class .getName());

    // max clock deviance is 2 minutes
    public static final long MAX_CLOCK_DEVIANCE_MILLIS = 2 * 60 * 1000;

    protected FIXMsg message;

    protected StateProcessor stateProcessor;

    protected long maxMsgSize;

    protected SessionType sessionType;

    protected ProtocolState processingStage;

    /**
     * Ctor.
     *
     * @param stateProcessor state processor
     */
    public Status(StateProcessor stateProcessor) {
        this.stateProcessor = stateProcessor;
        if (stateProcessor instanceof FIXClientStateProcessor) {
            sessionType = SessionType.BUY;
        } else {
            sessionType = SessionType.SELL;
        }
    }

    /**
     * Contract to be implement ba every state in order to process a message.
     * @return next state the protocol will be set
     * @throws UnrecoverableException fatal error in processing
     * @throws InterruptedException
     */
    public abstract Status process() throws UnrecoverableException, InterruptedException;

    /**
     * Name of the state in which the state machine is currently.
     * @return state name
     */
    public abstract String getName();

    /**
     * Resets the state of the status.
     */
    public abstract void recycle();

    /**
     * Returns the status enumeration value for this process status.
     * @return protocol status
     */
    public abstract ProtocolState getProtocolState();

    /**
     * Resets the message stored in the state. Overridden methods must reset the state specific
     * values after calling this method.
     */
    public void reset() {
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Protocol state [{0}] reset.", getName());
        }
        message = null;
    }

    /**
     * Sets the input message to be processed in this state.
     * @param message FIX message received by th protocol
     */
    public void setMessage(FIXMsg message) {
        this.message = message;
    }

    public void setMaxMsgSize(long maxMsgSize) {
        this.maxMsgSize = maxMsgSize;
    }

    public String retrieveSessionAddress() {
        return stateProcessor.getProtocol().getSessionCoordinator().getSessionAddress().toString();
    }

    public void setProcessingStage(ProtocolState processingStage) {
        this.processingStage = processingStage;
    }

    protected boolean isMsgSendTimeValid() {
        boolean result = true;
        if (message != null) {
            Date sendTime = message.getHeader().getSendingTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(sendTime);
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            long now = cal.getTimeInMillis();
            if (Math.abs(now - sendTime.getTime()) > MAX_CLOCK_DEVIANCE_MILLIS) {
                result = false;
            }
        }

        return result;
    }

    /**
     * Mismatch in address always result in resetting the connection without logout.
     * @param ex error
     * @return IDLE state
     * @throws InterruptedException
     */
    protected Status handleFailedAddressValidation(InvalidMsgException ex) throws InterruptedException {
        if (SessionType.BUY.equals(sessionType)) {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.getMessage(), null)));
        } else {
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.getMessage(), null)));
        }

        if (SessionType.BUY.equals(sessionType)) {
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                    LifeCycleType.PROTOCOL_CLIENT.name(),
                    LifeCycleCode.FIX_SESSION_RESTART.name()));
        } else {
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                    LifeCycleType.PROTOCOL_SERVER.name(),
                    LifeCycleCode.FIX_SESSION_SHUTDOWN.name()));
        }

        return stateProcessor.getStatus(ProtocolState.IDLE);
    }

    protected void sendRejectMsg(Exception ex, SessionRejectReason reason) throws InterruptedException {
        if (message != null && message.getHeader() != null) {
            try {
                RejectMsg reject = MessageFiller.buildRejectMsg(stateProcessor.getProtocol(), message, reason, ex);
                stateProcessor.sendProtocolMessage(reject);
            } catch (InvalidMsgException ex1) {
                LOGGER.log(Level.SEVERE, "Could not build Reject message. Error was : {0}", ex1.toString());
            }
        }
    }

    protected void sendLogoutMsg(Exception ex) throws InterruptedException {
        if (message != null && message.getHeader() != null) {
            try {
                LogoutMsg logout = MessageFiller.buildLogoutMsg(stateProcessor.getProtocol());
                logout.setText(ex.getMessage());
                stateProcessor.sendProtocolMessage(logout);
            } catch (InvalidMsgException ex1) {
                LOGGER.log(Level.SEVERE, "Could not build Logout message. Error was : {0}", ex1.toString());
            }
        }
    }

    protected Status handleMissingSequenceNumber() throws InterruptedException {
        String logMsg = "Missing sequence number field [34] in received message. Resetting connection.";

        LOGGER.severe(logMsg);
        Exception ex = new Exception(logMsg);
        sendRejectMsg(ex, SessionRejectReason.RequiredTagMissing);

        Status status = stateProcessor.getStatus(ProtocolState.LOGOUT_SEND);
        ((LogoutSendClientStatus) status).setText(logMsg);

        return status;
    }

    protected String buildInboundLogMessage(FIXMsg fixMsg) {
        StringBuilder sb = new StringBuilder("<<< ");
        sb.append(stateProcessor.getProtocol().getCptyID()).append(" - ").append(stateProcessor.getProtocol().getLocalID()).append(" (").append(fixMsg.getHeader().getMsgSeqNum());
        sb.append(") ").append(MsgType.displayName(fixMsg.getHeader().getMsgType())).append(" ").append(MsgUtil.getPrintableRawFixMessage(fixMsg.getRawMessage()));

        return sb.toString();
    }

    protected void setNeedsRouting(FIXMsg message) {
        if (stateProcessor instanceof FIXServerStateProcessor) {
            if (!stateProcessor.getProtocol().isRoutingMode()) {
                if (message.getHeader().getDeliverToCompID() != null && !message.getHeader().getDeliverToCompID().isEmpty()) {
                    stateProcessor.getProtocol().setRoutingMode(true);
                }
            }
        }
    }
}
