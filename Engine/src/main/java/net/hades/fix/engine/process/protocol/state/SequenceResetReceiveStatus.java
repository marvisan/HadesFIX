/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SequenceResetReceiveStatus.java
 *
 * $Id: SequenceResetReceiveStatus.java,v 1.8 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.SeqGap;
import net.hades.fix.engine.process.protocol.client.LogoutSendClientStatus;
import net.hades.fix.message.SequenceResetMsg;
import net.hades.fix.message.type.SessionRejectReason;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Processing state in which the client receives an SequenceReset message. <br/>
 * The outcome states are:
 * <ul>
 *      <li>PROCESSING - in case of success/error
 *      <li>RESEND_REQUEST_SEND - in case MsgSeqNum > ExpSeqNum
 *      <li>LOGOUT_SEND - inconsistent sequences
 *      <li>REJECT_SEND - invalid message sequences received
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 */
public class SequenceResetReceiveStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(SequenceResetReceiveStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.SEQUENCE_RESET_RECEIVE;

    public SequenceResetReceiveStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status = stateProcessor.getStatus(ProtocolState.PROCESSING);
        SequenceResetMsg msg = (SequenceResetMsg) message;
        // the RX sequence number has been already incremented when message has arrived
        SeqGap gap = stateProcessor.getGap();
        if (msg.getGapFillFlag() != null && msg.getGapFillFlag()) {
            // this is a gap fill message
            int expSeqNo = stateProcessor.getProtocol().getRxSeqNo() + 1;
            if (msg.getNewSeqNo() != null) {
                int newSeqNo = msg.getNewSeqNo();
                int msgSeqNo = msg.getHeader().getMsgSeqNum();
                boolean possDuplicate = msg.getHeader().getPossDupFlag() != null ? msg.getHeader().getPossDupFlag() : false;
                if (newSeqNo > msgSeqNo && msgSeqNo == expSeqNo) {
                    // reset RX sequence number
                    stateProcessor.getProtocol().setRxSeqNo(newSeqNo - 1);
                    if (gap != null) {
                        if (newSeqNo >= gap.getLast()) {
                            stateProcessor.setGap(null);
                        } else {
                            gap.setFirst(newSeqNo);
                        }
                    }
                } else if (newSeqNo > msgSeqNo && msgSeqNo > expSeqNo) {
                    // request resend of missed messages
                    stateProcessor.getProtocol().setRxSeqNo(expSeqNo - 1);
                    status = stateProcessor.getStatus(ProtocolState.RESEND_REQUEST_SEND);
                    if (gap != null) {
                        if (expSeqNo < gap.getFirst()) {
                            gap.setFirst(expSeqNo);
                        }
                        if (msgSeqNo > gap.getLast()) {
                            gap.setLast(msgSeqNo);
                        }
                    } else {
                        stateProcessor.setGap(new SeqGap(expSeqNo, msgSeqNo));
                    }
                } else if (newSeqNo > msgSeqNo && msgSeqNo < expSeqNo && possDuplicate) {
                    // duplicate, discard message
                    LOGGER.log(Level.INFO, "Duplicate SequenceReset message received. Will discard it : {0}", new String(message.getRawMessage()));
                } else if (newSeqNo > msgSeqNo && msgSeqNo < expSeqNo && !possDuplicate) {
                    stateProcessor.getProtocol().setRxSeqNo(expSeqNo - 1);
                    String errMsg = "MsgSeqNum too low, expecting " + expSeqNo + " received " + msgSeqNo;
                    LOGGER.severe(errMsg);
                    status = stateProcessor.getStatus(ProtocolState.LOGOUT_SEND);
                    ((LogoutSendClientStatus) status).setText(errMsg);
                } else if (newSeqNo <= msgSeqNo && msgSeqNo == expSeqNo) {
                    String errMsg = "Attempt to lower sequence number, invalid value NewSeqNum " + newSeqNo;
                    LOGGER.severe(errMsg);
                    status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                    status.setMessage(msg);
                    ((RejectSendStatus) status).setError(new Exception(errMsg));
                } else {
                    String errMsg = "Unexpected state for gap fill SequenceReset message received. NewSeqNum=" + newSeqNo
                            + " MsgSeqNum=" + msgSeqNo + " ExpMsgNum=" + expSeqNo;
                    LOGGER.severe(errMsg);
                    status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                    status.setMessage(msg);
                    ((RejectSendStatus) status).setError(new Exception(errMsg));
                }
            } else {
                String errMsg = "Invalid gap fill SequenceReset message received with no NewSeqNo field set.";
                LOGGER.severe(errMsg);
                status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                status.setMessage(msg);
                ((RejectSendStatus) status).setError(new Exception(errMsg));
            }
        } else {
            if (msg.getNewSeqNo() != null) {
                int newSeqNo = msg.getNewSeqNo();
                int expSeqNo = stateProcessor.getProtocol().getTxSeqNo() + 1;
                if (newSeqNo > expSeqNo) {
                    // reset RX sequence number
                    stateProcessor.getProtocol().setTxSeqNo(newSeqNo - 1);
                } else if (newSeqNo == expSeqNo) {
                    LOGGER.warning("Ignored SequenceReset message with the value of NewSeqNo equal to expected outgoing sequence.");
                } else {
                    String errMsg = "Received tag value for [NewSeqNo] is less than the expected sequence number.";
                    LOGGER.severe(errMsg);
                    status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                    status.setMessage(msg);
                    ((RejectSendStatus) status).setReason(SessionRejectReason.ValueOuOfRange);
                    ((RejectSendStatus) status).setError(new Exception(errMsg));
                }
            } else {
                String errMsg = "Invalid reset SequenceReset message received with no NewSeqNo field set.";
                LOGGER.severe(errMsg);
                status = stateProcessor.getStatus(ProtocolState.REJECT_SEND);
                status.setMessage(msg);
                ((RejectSendStatus) status).setError(new Exception(errMsg));
            }
        }
        stateProcessor.setProcessingStage(ProcessingStage.LOGGEDON);
       
        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.SEQUENCE_RESET_RECEIVE.name();
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
