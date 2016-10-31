/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ResendRequestStatus.java
 *
 * $Id: ResendGapMessagesStatus.java,v 1.6 2011-04-03 08:00:06 vrotaru Exp $
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
import net.hades.fix.engine.process.protocol.SeqGap;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.SequenceResetMsg;
import net.hades.fix.message.exception.InvalidMsgException;

import java.util.Date;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Resend gap messages requested by the counterparty. <br/>
 * The outcome states are:
 * <ul>
 *      <li>PROCESSING - in case of success/error
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 */
public class ResendGapMessagesStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(ResendGapMessagesStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.RESEND_GAP_MESSAGES;

    private SeqGap seqGap;

    public ResendGapMessagesStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status;
        try {
            status = sendMessagesInGap();
        } catch (InvalidMsgException ex) {
            status = handleBuildMessageError(ex);
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.RESEND_GAP_MESSAGES.name();
    }

    @Override
    public void recycle() {
        message = null;
        seqGap = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }

    public void setSeqGap(SeqGap seqGap) {
        this.seqGap = seqGap;
    }

    private Status sendMessagesInGap() throws InvalidMsgException, InterruptedException {
        Status result = stateProcessor.getStatus(ProtocolState.PROCESSING);
        if (seqGap != null) {
            NavigableMap<Integer, FIXMsg> messages = stateProcessor.getProtocol().getHistoryCache().getMessages(seqGap.getFirst(), seqGap.getLast());
            if (messages != null && messages.size() > 0) {
                // we have all/some messages in local cache
                AtomicInteger start = new AtomicInteger(seqGap.getFirst());
                while (start.intValue() <= seqGap.getLast()) {
                    stateProcessor.sendProtocolMessage(getNextMsgToSend(start, messages));
                }
            } else {
                // no messages in the cache. we'll set the sequence to the next TX seq
                SequenceResetMsg sequenceResetMsgequenceResetMsg = MessageFiller.buildSequenceResetMsg(stateProcessor.getProtocol(), seqGap.getFirst());
                sequenceResetMsgequenceResetMsg.setGapFillFlag(Boolean.TRUE);
                sequenceResetMsgequenceResetMsg.getHeader().setPossDupFlag(Boolean.TRUE);
                sequenceResetMsgequenceResetMsg.getHeader().setSendingTime(new Date());
                sequenceResetMsgequenceResetMsg.setNewSeqNo(seqGap.getLast() + 1);
                stateProcessor.getProtocol().setTxSeqNo(seqGap.getLast());
                stateProcessor.sendProtocolMessage(sequenceResetMsgequenceResetMsg);
            }
            seqGap = null;
        }
        stateProcessor.setProcessingStage(ProtocolState.LOGGEDON);

        return result;
    }

    private FIXMsg getNextMsgToSend(AtomicInteger index, NavigableMap<Integer, FIXMsg> messages) throws InvalidMsgException {
        FIXMsg result = null;
        boolean isGapFill = false;
        for (int i = index.intValue(); i <= seqGap.getLast(); i++) {
            FIXMsg msg = null;
            for (Entry<Integer, FIXMsg> entry : messages.entrySet()) {
                Integer seqNum = entry.getKey();
                if (i == seqNum) {
                    msg = messages.get(seqNum);
                    break;
                }
            }
            if (msg == null || msg instanceof SequenceResetMsg) {
                if (result == null) {
                    if (msg == null) {
                        result = MessageFiller.buildSequenceResetMsg(stateProcessor.getProtocol(), i);
                    } else {
                        result = msg;
                    }
                }
                ((SequenceResetMsg) result).setGapFillFlag(Boolean.TRUE);
                ((SequenceResetMsg) result).setNewSeqNo(i + 1);
                result.getHeader().setPossDupFlag(Boolean.TRUE);
                result.getHeader().setSendingTime(new Date());
                isGapFill = true;
                index.incrementAndGet();
            } else {
                if (!isGapFill) {
                    result = msg;
                    result.getHeader().setPossDupFlag(Boolean.TRUE);
                    result.getHeader().setSendingTime(new Date());
                    index.incrementAndGet();
                    break;
                } else {
                    break;
                }
            }
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
