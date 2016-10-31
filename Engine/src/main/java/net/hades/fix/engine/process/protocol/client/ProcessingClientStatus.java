/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProcessingClientStatus.java
 *
 * $Id: ProcessingClientStatus.java,v 1.32 2011-04-04 05:41:34 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.client;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProtocolStats;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.SeqGap;
import net.hades.fix.engine.process.protocol.state.RejectSendStatus;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.engine.process.protocol.state.Status;
import net.hades.fix.engine.util.MessageUtil;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main state responsible with processing on RX and TX channels.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.32 $
 */
public class ProcessingClientStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(ProcessingClientStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.PROCESSING;

    public ProcessingClientStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status = this;
        byte[] byteFixMsg = stateProcessor.getTransport().readMessage();
        FIXMsg msg;
        try {
            if (byteFixMsg != null && byteFixMsg.length > 0) {
                ((ProtocolStats) stateProcessor.getProtocol().getStats()).incrTotMsgInCount();
                if (maxMsgSize > 0 && byteFixMsg.length > maxMsgSize) {
                    // discard messages larger than maxMsgSize
                    stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                                Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR,
                                    "Discarded message with size : " + byteFixMsg.length, null)));
                    return status;
                }
                msg = FIXMsgBuilder.build(byteFixMsg);

                if (MessageUtil.isAdminMessage(msg)) {
                    if (LOGGER.isLoggable(Level.INFO)) {
                        LOGGER.info(buildInboundLogMessage(msg));
                    }
                } else {
                    if (LOGGER.isLoggable(Level.FINE)) {
                        LOGGER.log(Level.FINE, "Sequences : RX ({0}) , TX ({1})", new Object[]{stateProcessor.getProtocol().getRxSeqNo() + 1, stateProcessor.getProtocol().getTxSeqNo() + 1});
                        LOGGER.fine(buildInboundLogMessage(msg));
                    }
                }

                stateProcessor.getTimers().resetInputTimeoutTask();
                if (ProtocolState.INITIALISED.equals(stateProcessor.getProcessingStage())) {
                    // only accept login and logout messages in INITIALISED stage
                    if (!(MsgType.Logout.getValue().equals(msg.getHeader().getMsgType()) ||
                          MsgType.Logon.getValue().equals(msg.getHeader().getMsgType()))) {
                        StringBuilder logMsg = new StringBuilder("Unexpected message received and discarded in INITIALISED stage : ")
                                .append(msg.toString());
                        stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                                Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                                BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg.toString(), null)));
                        LOGGER.severe(logMsg.toString());

                        return status;
                    }
                    stateProcessor.getTimers().resetLogonTimeoutTask();
                } else if (ProtocolState.FROZEN.equals(stateProcessor.getProcessingStage())) {
                    if (!(MsgType.Heartbeat.getValue().equals(msg.getHeader().getMsgType())
                        || MsgType.TestRequest.getValue().equals(msg.getHeader().getMsgType()))) {
                        if (LOGGER.isLoggable(Level.FINER)) {
                            LOGGER.log(Level.FINER, "Protocol is frozen. The message has been rejected : {0}", msg.getRawMessage());
                        }
                        status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                        status.setMessage(msg);
                        ((RejectSendStatus) status).setReason(SessionRejectReason.Other);
                        ((RejectSendStatus) status).setError(new Exception("Client protocol blocked to process messages."));
                        // increment RX sequence number
                        stateProcessor.getProtocol().getNextRxSeqNo();
                        
                        return status;
                    }
                }
                if (stateProcessor.getProtocol().getConfiguration().getHeartBtInt() > 0) {
                    if (MsgType.Heartbeat.getValue().equals(msg.getHeader().getMsgType())
                            || MsgType.TestRequest.getValue().equals(msg.getHeader().getMsgType())) {
                        stateProcessor.getTimers().startHeartbeatTask();
                    }
                    stateProcessor.getTimers().startTestRequestTimeoutTask();
                }
                if (msg.getHeader().getMsgSeqNum() == null) {
                    return handleMissingSequenceNumber();
                }
                try {
                    stateProcessor.getProtocol().validateIncomingMessageAddress(msg);
                } catch (InvalidMsgException ex) {
                    // at this stage we reject
                    return handleFailedAddressValidation(ex);
                }
                if (!isMsgSendTimeValid()) {
                    String logMsg = "SendingTime accuracy problem.";
                    LOGGER.severe(logMsg);

                    Exception ex = new Exception(logMsg);
                    sendRejectMsg(ex, SessionRejectReason.SendingTimeAccuracyProblem);
                    sendLogoutMsg(ex);

                    return status;
                }
                String msgType = msg.getHeader().getMsgType();
                if (!stateProcessor.getProtocol().getConfiguration().getDisableGapDetection()) {
                    SeqGap seqGap = stateProcessor.getGap();
                    if (!(MsgType.SequenceReset.getValue().equals(msgType) || MsgType.ResendRequest.getValue().equals(msgType) || 
                          MsgType.Logout.getValue().equals(msgType) || MsgType.Logon.getValue().equals(msgType))) {
                        if (seqGap == null) {
                            status = processNonGapMessage(msg);
                        } else {
                            if (stateProcessor.getProtocol().getConfiguration().getEnableResendTimeout()) {
                                stateProcessor.getTimers().resetResendTimeoutTask();
                            }
                            status = processGapMessage(msg, seqGap);
                        }
                    } else {
                        if (MsgType.ResendRequest.getValue().equals(msgType)) {
                            if (seqGap != null) {
                                status = processGapMessage(msg, seqGap);
                            } else {
                                status = processNonGapMessage(msg);
                            }
                        } else {
                            status = processNonGapMessage(msg);
                        }
                    }
                } else {
                    status = processAnyMessage(msg);
                }
            }
        } catch (InvalidMsgException ex) {
            String errMsg = "Invalid message : " + ExceptionUtil.getStackTrace(ex) + ". Rcvd message : " + new String(byteFixMsg);
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                    BaseSeverityType.RECOVERABLE, AlertCode.MSG_REJECTED, errMsg, null)));
            LOGGER.severe(errMsg);
            status = this;
        } catch (TagNotPresentException ex) {
            String errMsg = "Tag not found in message : " + ExceptionUtil.getStackTrace(ex) + ". Rcvd message : " + new String(byteFixMsg);
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                    BaseSeverityType.RECOVERABLE, AlertCode.MSG_REJECTED, errMsg, null)));
            LOGGER.severe(errMsg);
            status = this;
        } catch (BadFormatMsgException ex) {
            String errMsg = "Message is in bad format : " + ExceptionUtil.getStackTrace(ex) + ". Rcvd message : " + new String(byteFixMsg);
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                    BaseSeverityType.RECOVERABLE, AlertCode.MSG_REJECTED, errMsg, null)));
            LOGGER.severe(errMsg);
            status = this;
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.PROCESSING.name();
    }

    @Override
    public void recycle() {
        message = null;
        maxMsgSize = -1;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }

    private Status processNonGapMessage(FIXMsg msg) throws UnrecoverableException, InvalidMsgException, BadFormatMsgException, InterruptedException {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest("Non gap message processing.");
        }

        Status status;
        if (!(MsgType.SequenceReset.getValue().equals(msg.getHeader().getMsgType()) || MsgType.Logon.getValue().equals(msg.getHeader().getMsgType()))) {
            int msgSeqNo = msg.getHeader().getMsgSeqNum();
            int expSeqNo = stateProcessor.getProtocol().getRxSeqNo() + 1;
            boolean possDuplicate = false;
            if (msg.getHeader().getPossDupFlag() != null) {
                possDuplicate = msg.getHeader().getPossDupFlag();
            }
            if (msgSeqNo == expSeqNo) {
                // sequence number in sync, deliver the message
                stateProcessor.getProtocol().getNextRxSeqNo();
                status = processSequencedMessage(msg);
            } else if (msgSeqNo > expSeqNo) {
                if(MsgType.Logout.getValue().equals(msg.getHeader().getMsgType())) {
                    // if we get a Logout out of sync we dont care about sequence, just respond back and restart the session
                    status = stateProcessor.getStatus(ProtocolState.LOGOUT_SEND);
                    status.recycle();
                    status.setMessage(msg);
                } else {
                    // request resend of missed messages
                    String logMsg = "Gap sequence number detected for message type [" + msg.getHeader().getMsgType() + "], expected ["
                            + expSeqNo + "] message seq [" + msgSeqNo + "].";
                    LOGGER.info(logMsg);
                    stateProcessor.setGap(new SeqGap(expSeqNo, msgSeqNo));
                    stateProcessor.getResequencingBuffer().addMessage(msg);
                    status = stateProcessor.getStatus(ProtocolState.RESEND_REQUEST_SEND);
                }
                stateProcessor.setProcessingStage(ProtocolState.ADMIN);
            } else if (msgSeqNo < expSeqNo && possDuplicate) {
                // duplicate, discard message
                LOGGER.warning("Duplicate Message received. Will discard it.");
                status = this;
            } else if (msgSeqNo < expSeqNo && !possDuplicate) {
                stateProcessor.getProtocol().setRxSeqNo(expSeqNo);
                String errMsg = "MsgSeqNum too low, expecting " + expSeqNo + " but received " + msgSeqNo;
                LOGGER.severe(errMsg);
                status = stateProcessor.getStatus(ProtocolState.LOGOUT_SEND);
                status.recycle();
                status.setMessage(msg);
                ((LogoutSendClientStatus) status).setText(errMsg);
                if(!MsgType.Logout.getValue().equals(msg.getHeader().getMsgType())) {
                    ((LogoutSendClientStatus) status).setExpectLogout(true);
                }
                stateProcessor.setProcessingStage(ProtocolState.ADMIN);
            } else {
                String errMsg = "This message has wrong sequence number message received." + " MsgSeqNum="
                        + msgSeqNo + " ExpMsgNum=" + expSeqNo;
                LOGGER.severe(errMsg);
                stateProcessor.getProtocol().setRxSeqNo(expSeqNo);
                status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                ((RejectSendStatus) status).setError(new Exception(errMsg));
                status.setMessage(msg);
            }
        } else {
            if (MsgType.Logon.getValue().equals(msg.getHeader().getMsgType())) {
                // might be out of sync so get the next rx seq number
                stateProcessor.getProtocol().getNextRxSeqNo();
            }
            status = processSequencedMessage(msg);
        }

        return status;
    }

    private Status processGapMessage(FIXMsg msg, SeqGap seqGap) throws UnrecoverableException, InvalidMsgException, BadFormatMsgException, InterruptedException {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest("Gap message processing.");
        }

        Status status = this;
        int msgSeqNo = msg.getHeader().getMsgSeqNum();
        int expSeqNo = stateProcessor.getProtocol().getRxSeqNo() + 1;
        Date origSendingTime = msg.getHeader().getOrigSendingTime();
        Date sendingTime = msg.getHeader().getSendingTime();
        boolean possDuplicate = false;
        if (msg.getHeader().getPossDupFlag() != null) {
            possDuplicate = msg.getHeader().getPossDupFlag();
        }
        if (msgSeqNo > expSeqNo) {
            if (possDuplicate) {
                if (isMsgSeqIsInGap(msgSeqNo, seqGap)) {
                    stateProcessor.getResequencingBuffer().addMessageSafe(msg);
                } else {
                    stateProcessor.getResequencingBuffer().addMessage(msg);

                    LOGGER.log(Level.FINE, "Added message out of gap [{0},{1}] with sequence [{2}] to resequencing buffer",
                            new Object[] {seqGap.getFirst(), seqGap.getLast(), msgSeqNo});

                    extendGap(msgSeqNo, seqGap);
                }
            } else {
                stateProcessor.getResequencingBuffer().addMessage(msg);

                LOGGER.log(Level.FINE, "Added message out of gap [{0},{1}] with sequence [{2}] to resequencing buffer",
                            new Object[] {seqGap.getFirst(), seqGap.getLast(), msgSeqNo});

                extendGap(msgSeqNo, seqGap);
                if (MsgType.ResendRequest.getValue().equals(msg.getHeader().getMsgType())) {
                    // out of sync resend request, deliver it as the resend has priority
                    status = processSequencedMessage(msg);
                } else {
                    // try a delivery if the received message is next to the last in gap
                    deliverInSequenceBusinessMessages(seqGap);
                }
            }
        } else if (msgSeqNo < expSeqNo) {
            if (possDuplicate) {
                if (origSendingTime != null && sendingTime != null) {
                    if (origSendingTime.compareTo(sendingTime) <= 0) {
                        // ignore message
                        LOGGER.info("Duplicate message received. Will discard it.");
                        status = this;
                    }
                } else {
                    String errMsg = "Missing SendingTime or OrigSendingTime received.";
                    LOGGER.severe(errMsg);
                    status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                    ((RejectSendStatus) status).setReason(SessionRejectReason.RequiredTagMissing);
                    ((RejectSendStatus) status).setError(new Exception(errMsg));
                    status.setMessage(msg);
                    stateProcessor.getProtocol().setRxSeqNo(expSeqNo);
                }
            }
        } else if (msgSeqNo == expSeqNo) {
            if (possDuplicate) {
                if (origSendingTime != null && sendingTime != null) {
                    if (origSendingTime.compareTo(sendingTime) <= 0) {
                        stateProcessor.getResequencingBuffer().addMessageSafe(msg);
                        deliverInSequenceBusinessMessages(seqGap);
                    } else {
                        String errMsg = "SendingTime accuracy problem.";
                        LOGGER.severe(errMsg);
                        status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                        ((RejectSendStatus) status).setReason(SessionRejectReason.RequiredTagMissing);
                        ((RejectSendStatus) status).setError(new Exception(errMsg));
                        status.setMessage(msg);
                        stateProcessor.getProtocol().setRxSeqNo(expSeqNo);
                    }
                } else {
                    String errMsg = "Missing SendingTime or OrigSendingTime received.";
                    LOGGER.severe(errMsg);
                    status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                    ((RejectSendStatus) status).setReason(SessionRejectReason.RequiredTagMissing);
                    ((RejectSendStatus) status).setError(new Exception(errMsg));
                    status.setMessage(msg);
                    stateProcessor.getProtocol().setRxSeqNo(expSeqNo);
                }
            }
        }

        return status;
    }

    private Status processAnyMessage(FIXMsg msg) throws InvalidMsgException, BadFormatMsgException, InterruptedException {
        Status status;
        int msgSeqNum = msg.getHeader().getMsgSeqNum();
        if (msgSeqNum >= stateProcessor.getProtocol().getRxSeqNo() + 1) {
            status = processSequencedMessage(msg);
            stateProcessor.getProtocol().setRxSeqNo(msgSeqNum);
        } else {
            status = this;
            LOGGER.log(Level.INFO, "Discard message : {0}", new String(msg.getRawMessage()));
        }

        return status;
    }

    private Status processSequencedMessage(FIXMsg msg) throws InvalidMsgException, BadFormatMsgException, InterruptedException {
        Status status = null;

        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "{0} --> {1} [{2}] : {3}",
                    new Object[]{ stateProcessor.getProtocol().getCptyID(),
                        stateProcessor.getProtocol().getLocalID(),
                        MsgType.displayName(msg.getHeader().getMsgType()),
                        msg.toString()});
        }

        if (MessageUtil.isAdminMessage(msg)) {
            msg.decode();

            if (MsgType.TestRequest.getValue().equals(msg.getHeader().getMsgType())) {
                stateProcessor.setProcessingStage(ProtocolState.LOGGEDON);
                status = stateProcessor.getStatus(ProtocolState.TEST_REQUEST_RECEIVE);
                status.setMessage(msg);
            } else if (MsgType.Heartbeat.getValue().equals(msg.getHeader().getMsgType())) {
                stateProcessor.setProcessingStage(ProtocolState.LOGGEDON);
                status = stateProcessor.getStatus(ProtocolState.HEARTBEAT_RECEIVE);
                status.setMessage(msg);
            } else if (MsgType.Reject.getValue().equals(msg.getHeader().getMsgType())) {
                stateProcessor.setProcessingStage(ProtocolState.LOGGEDON);
                status = stateProcessor.getStatus(ProtocolState.REJECT_RECEIVE);
                status.setMessage(msg);
            } else if (MsgType.SequenceReset.getValue().equals(msg.getHeader().getMsgType())) {
                stateProcessor.setProcessingStage(ProtocolState.ADMIN);
                status = stateProcessor.getStatus(ProtocolState.SEQUENCE_RESET_RECEIVE);
                status.setMessage(msg);
            } else if (MsgType.ResendRequest.getValue().equals(msg.getHeader().getMsgType())) {
                stateProcessor.setProcessingStage(ProtocolState.ADMIN);
                status = stateProcessor.getStatus(ProtocolState.RESEND_REQUEST_RECEIVE);
                status.setMessage(msg);
            } else if (MsgType.Logout.getValue().equals(msg.getHeader().getMsgType())) {
                stateProcessor.setProcessingStage(ProtocolState.ADMIN);
                status = stateProcessor.getStatus(ProtocolState.LOGOUT_RECEIVE);
                status.setMessage(msg);
            } else if (MsgType.Logon.getValue().equals(msg.getHeader().getMsgType())) {
                status = stateProcessor.getStatus(ProtocolState.LOGON_RECEIVE);
                status.setMessage(msg);
            }
        } else {
            stateProcessor.setProcessingStage(ProtocolState.LOGGEDON);
            status = this;
            stateProcessor.getProtocol().relayMessage(msg);
        }

        return status;
    }

    private void deliverInSequenceBusinessMessages(SeqGap gap) {
        List<FIXMsg> conseqMsgs = stateProcessor.getResequencingBuffer().getConsequentMessages(gap.getFirst(), gap.getLast());
        if (conseqMsgs != null && conseqMsgs.size() > 0) {
            int first = conseqMsgs.get(0).getHeader().getMsgSeqNum();
            int last = conseqMsgs.get(conseqMsgs.size() - 1).getHeader().getMsgSeqNum();
            for (FIXMsg msg : conseqMsgs) {
                int msgSeqNo = msg.getHeader().getMsgSeqNum();
                if (!MessageUtil.isAdminMessage(msg)) {
                    stateProcessor.getProtocol().relayMessage(msg);
                    stateProcessor.getProtocol().setRxSeqNo(msgSeqNo);
                }
                if (msgSeqNo >= gap.getLast()) {
                    // reset gap
                    stateProcessor.setGap(null);
                } else {
                    gap.setFirst(msgSeqNo + 1);
                }
            }
            stateProcessor.getResequencingBuffer().clearMessages(first, last);
        }
    }

    private boolean isMsgSeqIsInGap(int msgSeqNo, SeqGap seqGap) {
        boolean result = false;
        if (msgSeqNo > seqGap.getFirst() && msgSeqNo <= seqGap.getLast()) {
            result = true;
        }

        return result;
    }

    private void extendGap(int msgSeqNum, SeqGap gap) {
        gap.setLast(msgSeqNum);
    }

}
