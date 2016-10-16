/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXClientProcess.java
 *
 * $Id: FIXClient.java,v 1.68 2011-04-30 04:39:45 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.client;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.config.model.ClientSessionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.SeqNoPersistenceException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProcessStatus;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.GenericEventProcessor;
import net.hades.fix.engine.process.protocol.*;
import net.hades.fix.engine.process.protocol.router.MessageRouter;
import net.hades.fix.engine.process.session.ClientSessionCoordinator;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.engine.process.transport.Transport;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.util.MsgUtil;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client side of a FIX engine process.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.68 $
 */
public final class FIXClient extends Protocol {

    private static final Logger LOGGER = Logger.getLogger(FIXClient.class.getName());

    private static final String COMPONENT_NAME = "FIXCLI";
    
    private static final int DEFAULT_LOGON_TIMEOUT              = 60;
    private static final boolean DO_NOT_RECON_WHEN_SEQ_TOO_LOW  = false;
    private static final boolean DEFAULT_CONN_ON_STARTUP        = true;
    private static final int DEFAULT_RECONNECT_DELAY            = 10000;
    private static final int DEFAULT_MAX_NUM_LOGON_RETRIES      = 0;

    private boolean routerInitialised;

    public FIXClient(ClientSessionCoordinator sessionCoordinator, ClientSessionInfo configuration) throws ConfigurationException {
        super(sessionCoordinator.getName() + "_" + COMPONENT_NAME);
        this.sessionCoordinator = sessionCoordinator;
        this.configuration = configuration;
        setProcessStatus(ProcessStatus.INITIALISING);
        setSessionConfigData();

        rxQueue = new ArrayBlockingQueue<FIXMsg>(configuration.getRxBufferSize());
        txQueue = new PriorityBlockingQueue<FIXMsg>(configuration.getTxBufferSize(), new FIXMsgPriorityComparator());
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Running FIX client thread [{0}].", getName());

        stats.get().setStartTimestamp(new Date());
        mgmtData.get().setId(String.valueOf(getId()));
        mgmtData.get().setName(getName());
        mgmtData.get().setStatus(ProcessStatus.INITIALISING);
        mgmtData.get().setSessionType(SessionType.BUY);

        while (!shutdown) {
            try {
                processCommand(commandQueue.take());
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "FIX Client thread [{0}] has been interrupted.", getName());
            } catch (Exception ex) {
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                                BaseSeverityType.RECOVERABLE, AlertCode.MGMT_COMMAND_ERROR, "Unexpected error.", ex)));
            }
        }
        
        LOGGER.log(Level.INFO, "FIX client thread [{0}] destroyed.", getName());
    }

    @Override
    public void initialise() throws ConfigurationException {
        setSessionProtocolVersion();
        setSupportedMsgTypes();
        createSessionConfigDir();
        createSeqNoPersister();
        eventProcessor = new GenericEventProcessor(getName());
        messageSender = new FixMessageSender(getName(), this, transport);
        stateProcessor = new FIXClientStateProcessor(getName(), this, transport);

        setProcessStatus(ProcessStatus.INITIALISING);
    }

    @Override
    public void relayMessage(FIXMsg message) {
        if (isRoutingMode()) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine(">R> " + getLocalID() + " - " + getCptyID() + " (" + message.getHeader().getMsgSeqNum()
                        + ") " + MsgType.displayName(message.getHeader().getMsgType()) + " "
                        + MsgUtil.getPrintableRawFixMessage(message.getRawMessage()));
            }
            getMessageRouter().routeResponseMessage(message);
        } else {
            while (!rxQueue.offer(message)) {
                ThreadUtil.sleep(1);
            }
        }
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
        if (stateProcessor != null) {
            getStateProcessor().setTransport(transport);
        }
        if (messageSender != null) {
            messageSender.setTransport(transport);
        }
    }

    @Override
    public int getNextRxSeqNo() {
        int rxSeqNo;
        try {
            rxSeqNo = seqNoPersister.getNextRxSeqNo();
        } catch (SeqNoPersistenceException ex) {
            rxSeqNo = seqNoPersister.getRxSeqNo();
            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.FIXClient.name(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
        }

        return rxSeqNo;
    }

    @Override
    public void setRxSeqNo(int rxSeqNo) {
        try {
            seqNoPersister.setRxSeqNo(rxSeqNo);
        } catch (SeqNoPersistenceException ex) {
            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.FIXClient.name(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
        }
    }

    @Override
    public int getNextTxSeqNo() {
        int txSeqNo;
        try {
            txSeqNo = seqNoPersister.getNextTxSeqNo();
        } catch (SeqNoPersistenceException ex) {
            txSeqNo = seqNoPersister.getTxSeqNo();
            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.FIXClient.name(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
        }

        return txSeqNo;
    }

    @Override
    public void setTxSeqNo(int txSeqNo) {
        try {
            seqNoPersister.setTxSeqNo(txSeqNo);
        } catch (SeqNoPersistenceException ex) {
            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.FIXClient.name(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
        }
    }

    @Override
    public boolean writeMessage(FIXMsg fixMsg) throws InvalidMsgException {
        boolean result = true;
        if (fixMsg != null) {
            txQueue.put(fixMsg);
        } else {
            result = false;
        }

        return result;
    }

    @Override
    protected void processAdminMessage(FIXMsg fixMsg) throws TagNotPresentException, BadFormatMsgException {
        // does nothing for client
    }

    @Override
    protected synchronized MessageRouter getMessageRouter() {
        if (!routerInitialised) {
            routerInitialised = true;
        }

        return messageRouter;
    }

    @Override
    protected void setSessionConfigData() throws ConfigurationException {
        super.setSessionConfigData();
        try {
            if (configuration.getLogonTimeout() == null) {
                configuration.setLogonTimeout(DEFAULT_LOGON_TIMEOUT);
            }
            if (((ClientSessionInfo) configuration).getConnectOnStartup() == null) {
                ((ClientSessionInfo) configuration).setConnectOnStartup(DEFAULT_CONN_ON_STARTUP);
            }
            if (((ClientSessionInfo) configuration).getReconnectDelay() == null) {
                ((ClientSessionInfo) configuration).setReconnectDelay(DEFAULT_RECONNECT_DELAY);
            }
            if (((ClientSessionInfo) configuration).getMaxNumLogonRetries() == null) {
                ((ClientSessionInfo) configuration).setMaxNumLogonRetries(DEFAULT_MAX_NUM_LOGON_RETRIES);
            }
            if (((ClientSessionInfo) configuration).getDoNotReconnWhenSeqNumTooLow() == null) {
                ((ClientSessionInfo) configuration).setConnectOnStartup(DO_NOT_RECON_WHEN_SEQ_TOO_LOW);
            }
        } catch (Exception ex) {
            String error = "Error configuring the FIXClient process.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(error, ex);
        }
    }

    private void processCommand(Command command) {
        if (command == null) {
            return;
        }
        switch (command.getCommandType()) {
            case Startup:
                startup();
                break;

            case Block:
                block();
                break;

            case DisconnectTransport:
                disconnectTransport();
                break;

            case TransportConnected:
                transportConnected();
                break;

            case Unblock:
                unblock();
                break;

            case Freeze:
                freeze();
                break;

            case Thaw:
                thaw();
                break;

            case Reset:
                reset();
                break;

            case ResetSequences:
                resetSequences();
                break;

            case Shutdown:
                shutdown();
                break;

            case ShutdownNow:
                shutdownNow();
                break;

            default:
                LOGGER.log(Level.SEVERE, "Command [{0}] not implemented for a FIX Client.", command.getCommandType().name());

        }
    }

    private void startup() {
        stateProcessor.setStatus(ProtocolState.LOGON_SEND);
        stateProcessor.setProcessingStage(ProcessingStage.INITIALISED);

        if (configuration.getResetSeqAtStartup()) {
            resetSequences();
        }

        startEventProcessor();
        startStateProcessor();
        startMessageSender();
        
        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "FIXClient [{0}] ready to send LOGON.", getName());
    }

    private void block() {
        LOGGER.log(Level.INFO, "Blocking FIX client [{0}].", getName());

        stateProcessor.block();
        stateProcessor.interrupt();
        while (stateProcessor.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        messageSender.block();
        messageSender.interrupt();
        while (messageSender.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "FIX client [{0}] blocked.", getName());
    }

    private void disconnectTransport() {
        LOGGER.log(Level.INFO, "Disconnecting FIX client transport [{0}].", getName());

        do {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        } while (!txQueue.isEmpty() || !rxQueue.isEmpty());

        block();

        if (configuration.getResetSeqAtDisconnect()) {
            resetSequences();
        }

        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "FIX client transport [{0}] disconnected.", getName());
    }

    private void unblock() {
        LOGGER.log(Level.INFO, "Unblocking FIX client [{0}].", getName());

        stateProcessor.unblock();
        stateProcessor.interrupt();
        while (!stateProcessor.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        messageSender.unblock();
        messageSender.interrupt();
        while (!messageSender.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        setProcessStatus(ProcessStatus.ACTIVE);
        mgmtData.get().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "FIX client [{0}] unblocked.", getName());
    }

    private void transportConnected() {
        LOGGER.log(Level.INFO, "FIX client [{0}] transport connected.", getName());

        stateProcessor.setStatus(ProtocolState.LOGON_SEND);
        stateProcessor.setProcessingStage(ProcessingStage.INITIALISED);

        unblock();

        LOGGER.log(Level.INFO, "FIX client [{0}] processing resumed.", getName());
    }

    private void freeze() {
        LOGGER.log(Level.INFO, "Freezing FIX client [{0}].", getName());

        getStateProcessor().setProcessingStage(ProcessingStage.FROZEN);
        stateProcessor.interrupt();

        setProcessStatus(ProcessStatus.FROZEN);
        mgmtData.get().setStatus(ProcessStatus.FROZEN);

        LOGGER.log(Level.INFO, "FIX client [{0}] frozen.", getName());
    }

    private void thaw() {
        LOGGER.log(Level.INFO, "Thawing FIXClient [{0}].", getName());

        getStateProcessor().setProcessingStage(ProcessingStage.LOGGEDON);
        stateProcessor.interrupt();

        setProcessStatus(ProcessStatus.ACTIVE);
        mgmtData.get().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "FIXClient [{0}] thawed.", getName());
    }

    private void shutdown() {
        LOGGER.log(Level.INFO, "Shutting down FIX client [{0}].", getName());

        stateProcessor.block();
        stateProcessor.interrupt();
        stateProcessor.setProcessingStage(ProcessingStage.LOGGEDOUT);
        while (transport != null && transport.isConnected() && (!rxQueue.isEmpty() || !txQueue.isEmpty()) && ProcessStatus.ACTIVE.equals(processStatus.get())) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Buffer clear wait task interrupted. Error was : {0}", ex.toString());
                break;
            }
        }
        
        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);
        
        if (stateProcessor != null) {
            stateProcessor.shutdown();
            stateProcessor.interrupt();
            while (stateProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (messageSender != null) {
            messageSender.shutdown();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (messageRouter != null) {
            messageRouter.shutdown();
            messageRouter.interrupt();
            while (messageRouter.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        setProcessStatus(ProcessStatus.SHUTDOWN);
        mgmtData.get().setStatus(ProcessStatus.SHUTDOWN);

        LOGGER.log(Level.INFO, "FIX client [{0}] shutdown.", getName());

        shutdown = true;
    }

    private void shutdownNow() {
        LOGGER.log(Level.INFO, "Shutting down now FIX client [{0}] .", getName());

        stateProcessor.block();
        stateProcessor.setProcessingStage(ProcessingStage.LOGGEDOUT);
        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);

        if (stateProcessor != null) {
            stateProcessor.shutdown();
            stateProcessor.interrupt();
            while (stateProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (messageSender != null) {
            messageSender.shutdown();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (messageRouter != null) {
            messageRouter.shutdown();
            messageRouter.interrupt();
            while (messageRouter.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }

        setProcessStatus(ProcessStatus.SHUTDOWN);
        mgmtData.get().setStatus(ProcessStatus.SHUTDOWN);
        
        LOGGER.log(Level.INFO, "FIX client [{0}] shutdown.", getName());
        shutdown = true;
    }

//    private void reload() {
//        //TODO implement it
//    }

    private void startEventProcessor() {
        eventProcessor.start();
        ThreadUtil.blockUntilAlive(eventProcessor);
        while (!eventProcessor.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        addAlertListener(sessionCoordinator.getEventProcessor());
        addLifeCycleListener(sessionCoordinator.getEventProcessor());
        addMessageListener(sessionCoordinator.getEventProcessor());
    }

    private void startMessageSender() {
        messageSender.block();
        messageSender.start();
        ThreadUtil.blockUntilAlive(messageSender);
        while (messageSender.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void startStateProcessor() {
        stateProcessor.block();
        stateProcessor.start();
        ThreadUtil.blockUntilAlive(stateProcessor);
        while (stateProcessor.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }
}
