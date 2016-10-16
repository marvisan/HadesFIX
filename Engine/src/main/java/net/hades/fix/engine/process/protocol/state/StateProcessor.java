/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StateProcessor.java
 *
 * $Id: StateProcessor.java,v 1.7 2011-04-30 04:39:45 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.*;
import net.hades.fix.engine.process.protocol.timer.TimeoutTimers;
import net.hades.fix.engine.process.protocol.timer.Timeouts;
import net.hades.fix.engine.process.transport.Transport;
import net.hades.fix.engine.util.ThreadLocalSessionDataUtil;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Message;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.exception.InvalidMsgException;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Superclass for the state processors.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 */
public abstract class StateProcessor extends Thread {

    private static final Logger LOGGER = Logger.getLogger(StateProcessor.class.getName());

    private static final String COMPONENT_SUFFIX = "_STATE_PROCESSOR";

    protected Protocol protocol;
    protected Transport transport;

    protected volatile boolean shutdown;
    protected volatile boolean blocked;
    protected volatile boolean active;

    protected Status status;

    protected SeqGap gap;
    protected ResendRequestMsg gapRequestMessage;

    protected TimeoutTimers timers;
    protected HeartbeatTimestamp heartbeatTimestamp;

    protected final Map<ProtocolState, Status> statePool;
    
    protected int maxMsgSize;

    protected AtomicReference<ProcessingStage> processingStage;

    protected MessageHistoryCache resequencingBuffer;

    protected final ReentrantLock lock =  new ReentrantLock();

    protected StateProcessor(String name, Protocol protocol, Transport transport) {
        super(name + COMPONENT_SUFFIX);
        this.protocol = protocol;
        this.transport = transport;
        heartbeatTimestamp = new HeartbeatTimestamp();
        statePool = Collections.synchronizedMap(new EnumMap<ProtocolState, Status>(ProtocolState.class));
        resequencingBuffer = new MessageHistoryCache();
        processingStage = new AtomicReference<ProcessingStage>();
        status = getStatus(ProtocolState.IDLE);
        blocked = true;
    }

    public abstract Status getStatus(ProtocolState protocolState);

    public void sendProtocolMessage(FIXMsg message) throws InterruptedException {
        if (message == null) {
            return;
        }
        try {
            message.setPriority(Message.PRIORITY_HIGH);
            protocol.writeMessage(message);
        } catch (InvalidMsgException ex) {
            String errMsg = "Invalid priority message : " + message.toString();
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            protocol.getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                            BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, errMsg, null)));
        }
    }

    public Status getStatus() {
        lock.lock();
        try {
            return status;
        } finally {
            lock.unlock();
        }
    }

    public void setStatus(ProtocolState protocolState) {
        lock.lock();
        try {
            status = getStatus(protocolState);

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Status: {0}", status.getName());
            }
        } finally {
            lock.unlock();
        }
    }

    public void resetAllTimerTasks() {
        timers.resetAllTasks();
    }

    public int getMaxMsgSize() {
        return maxMsgSize;
    }

    public void setMaxMsgSize(int maxMsgSize) {
        this.maxMsgSize = maxMsgSize;
    }

    public void shutdown() {
        shutdown = true;
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }

    public boolean isActive() {
        return active;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public HeartbeatTimestamp getHeartbeatTimestamp() {
        return heartbeatTimestamp;
    }

    public TimeoutTimers getTimers() {
        return timers;
    }

    public void clearResequencingBuffer() {
        resequencingBuffer.clear();
    }

    public MessageHistoryCache getResequencingBuffer() {
        return resequencingBuffer;
    }

    // ACCESSORS
    //////////////////////////////////////////////

    /**
     * Getter for status of the protocol (logged in or not).
     * @return current processing stage
     */
    public ProcessingStage getProcessingStage() {
        return processingStage.get();
    }

    /**
     * Setter for status of the protocol (logged in or not).
     * @param processingStage status
     */
    public void setProcessingStage(ProcessingStage processingStage) {
        this.processingStage.set(processingStage);
    }

    public synchronized SeqGap getGap() {
        return gap;
    }

    public synchronized void setGap(SeqGap gap) {
        this.gap = gap;
    }

    public ResendRequestMsg getGapRequestMessage() {
        return gapRequestMessage;
    }

    public void setGapRequestMessage(ResendRequestMsg gapRequestMessage) {
        this.gapRequestMessage = gapRequestMessage;
    }

    public void initialiseThreadSessionContextData() {
        ThreadLocalSessionDataUtil.setThreadLocalSessionData(getProtocol().getConfiguration(), getProtocol().getVersion());
    }

    protected void startTimeoutTimers() {
        Timeouts timeouts = new Timeouts();
        timeouts.setEnableResendTimeout(protocol.getConfiguration().getEnableResendTimeout());
        if (timeouts.isEnableResendTimeout()) {
            timeouts.setResendTimeout(protocol.getConfiguration().getResendTimeout());
        }
        timeouts.setHtbtOffset(protocol.getConfiguration().getHeartBtOffset());
        timeouts.setHtbtTimeout(protocol.getConfiguration().getHeartBtInt());
        timeouts.setLogonTimeout(protocol.getConfiguration().getLogonTimeout());
        timeouts.setLogoutTimeout(protocol.getConfiguration().getLogoutTimeout());
        timeouts.setTestRequestTimeout((int) ((double) protocol.getConfiguration().getHeartBtInt() * 1.5d));
        timers = new TimeoutTimers(this, timeouts);
    }
}
