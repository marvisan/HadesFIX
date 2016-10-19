/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXServer.java
 *
 * $Id: FIXServer.java,v 1.51 2011-04-30 04:39:45 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.server;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.config.model.ServerSessionInfo;
import net.hades.fix.engine.config.model.SessionInfo;
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
import net.hades.fix.engine.process.session.ServerSessionCoordinator;
import net.hades.fix.engine.process.session.SessionType;
import net.hades.fix.engine.process.transport.TCPServerWorkerOld;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.LogoutMsg;
import net.hades.fix.message.Message;
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
 * Implementation of a FIX server process.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.51 $
 */
public final class FIXServer extends Protocol {

    private static final Logger LOGGER = Logger.getLogger(FIXServer.class.getName());

    public static final String COMPONENT_NAME = "FIXSRV";

    public FIXServer(ServerSessionCoordinator sessionCoordinator, ServerSessionInfo configuration) throws ConfigurationException {
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
        LOGGER.log(Level.INFO, "Running FIX server thread [{0}].", getName());

        stats.get().setStartTimestamp(new Date());
        mgmtData.get().setId(String.valueOf(getId()));
        mgmtData.get().setName(getName());
        mgmtData.get().setStatus(ProcessStatus.INITIALISING);
        mgmtData.get().setSessionType(SessionType.SELL);

        while (!shutdown) {
            try {
                processCommand(commandQueue.take());
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "FIX Server thread [{0}] has been interrupted.", getName());
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Unexpected exception raised by FIX Server: {0}", ExceptionUtil.getStackTrace(ex));

                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                                BaseSeverityType.RECOVERABLE, AlertCode.MGMT_COMMAND_ERROR, null, ex)));
            }
        }


        LOGGER.log(Level.INFO, "FIX server thread [{0}] destroyed.", getName());
    }

    @Override
    public void initialise() throws ConfigurationException {
        setSessionProtocolVersion();
        setSupportedMsgTypes();
        createSessionConfigDir();
        createSeqNoPersister();
        sessStartSeqSet = new SeqSet(seqNoPersister.getRxSeqNo(), seqNoPersister.getTxSeqNo());
    }

    @Override
    public void relayMessage(FIXMsg message) {
        if (isRoutingMode()) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, ">R> {0} - {1} ({2}) {3} {4}", new Object[]{getLocalID(), getCptyID(), message.getHeader().getMsgSeqNum(), MsgType.displayName(message.getHeader().getMsgType()), MsgUtil.getPrintableRawFixMessage(message.getRawMessage())});
            }
            getMessageRouter().routeRequestMessage(message);
        } else {
            while (!rxQueue.offer(message)) {
                ThreadUtil.sleep(1);
            }
        }
    }

    @Override
    public int getNextRxSeqNo() {
        int rxSeqNo;
        try {
            rxSeqNo = seqNoPersister.getNextRxSeqNo();
        } catch (SeqNoPersistenceException ex) {
            rxSeqNo = seqNoPersister.getRxSeqNo();
            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.FIXServer.name(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
        }

        return rxSeqNo;
    }

    @Override
    public void setRxSeqNo(int rxSeqNo) {
        try {
            seqNoPersister.setRxSeqNo(rxSeqNo);
        } catch (SeqNoPersistenceException ex) {
            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.FIXServer.name(),
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
            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.FIXServer.name(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
        }

        return txSeqNo;
    }

    @Override
    public void setTxSeqNo(int txSeqNo) {
        try {
            seqNoPersister.setTxSeqNo(txSeqNo);
        } catch (SeqNoPersistenceException ex) {
            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.FIXServer.name(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
        }
    }

    @Override
    public boolean writeMessage(FIXMsg fixMsg) throws InvalidMsgException {
        boolean result = true;
        if (fixMsg != null) {
            while (!txQueue.offer(fixMsg)) {
                if (!ThreadUtil.sleep(1)) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    // ACCESSOR METHODS
    /////////////////////////////////////

    @Override
    public SessionInfo getConfiguration() {
        return configuration;
    }

    @Override
    public void processAdminMessage(FIXMsg fixMsg) throws TagNotPresentException, BadFormatMsgException {
        if (!ProcessingStage.LOGGEDON.equals(stateProcessor.getProcessingStage())) {
            if (MsgType.Logon.getValue().equals(fixMsg.getHeader().getMsgType())) {
                // logon send by the business layer - reset Logon timer
                stateProcessor.getTimers().resetLogonTimeoutTask();
            }
        }
        if (fixMsg.getHeader().getMsgType().equals(MsgType.Logon.getValue())) {
            // this is a response Logon message coming from the business ties
            stateProcessor.setProcessingStage(ProcessingStage.LOGGEDON);
        } else if (fixMsg.getHeader().getMsgType().equals(MsgType.Logout.getValue())) {
            if (fixMsg.getPriority() == Message.PRIORITY_NORMAL) {
                stateProcessor.setStatus(ProtocolState.IDLE);
                String logMsg = "Logout message received from the business layer.";
                if (((LogoutMsg) fixMsg).getText() != null) {
                    logMsg = ((LogoutMsg) fixMsg).getText();
                }

                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                                BaseSeverityType.INFO, AlertCode.LOGOUT_SEND, logMsg, null)));

                ((LogoutReceiveServerStatus) stateProcessor.getStatus(ProtocolState.LOGOUT_RECEIVE)).setExpected(true);
                stateProcessor.getTimers().startLogoutTimerTask();
                stateProcessor.setStatus(ProtocolState.PROCESSING);

                LOGGER.severe(logMsg);
            }
        }
    }

    @Override
    protected MessageRouter getMessageRouter() {
        if (messageRouter == null) {
            // creates and starts the message router
            messageRouter = new MessageRouter(this, getLocalID() + "_MSG_ROUTER");
            messageRouter.start();
            messageRouter.startup();
            while (!messageRouter.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            messageRouter.initialiseThreadSessionContextData();
        }

        return messageRouter;
    }

    @Override
    protected void setSessionConfigData() throws ConfigurationException {
        super.setSessionConfigData();
    }

    private void processCommand(Command command) {
        if (command == null) {
            return;
        }
        switch (command.getCommandType()) {
            case Startup:
                TCPServerWorkerOld worker = (TCPServerWorkerOld) command.getParameter(Command.PARAM_SERVER_TRANSPORT_WORKER);
                startup(worker);
                break;

            case Block:
                block();
                break;

            case Unblock:
                unblock();
                break;

            case TransportDisconnected:
                transportDisconnected();
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
                LOGGER.log(Level.SEVERE, "Command [{0}] not implemented for a FIX Server.", command.getCommandType().name());

        }
    }

    private void startup(TCPServerWorkerOld worker) {
        LOGGER.log(Level.INFO, "Starting up FIX server [{0}].", getName());

        transport = worker;

        eventProcessor = new GenericEventProcessor(getName());
        messageSender = new FixMessageSender(getName(), this, worker);
        stateProcessor = new FIXServerStateProcessor(getName(), this, worker);

        startEventProcessor();
        startMessageSender();
        startStateProcessor();

        stateProcessor.setStatus(ProtocolState.PROCESSING);
        stateProcessor.setProcessingStage(ProcessingStage.INITIALISED);

        unblockStateProcessor();

        setProcessStatus(ProcessStatus.ACTIVE);
        mgmtData.get().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "FIX server [{0}] started up.", getName());
    }

    private void block() {
        if (stateProcessor != null) {
            stateProcessor.block();
            stateProcessor.interrupt();
            while (stateProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (messageSender != null) {
            messageSender.block();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "FIX server [{0}] blocked.", getName());
    }

    private void transportDisconnected() {
        LOGGER.log(Level.INFO, "Stopping FIX server [{0}] as transport was lost.", getName());

        if (stateProcessor != null) {
            stateProcessor.shutdown();
            stateProcessor.interrupt();
            while (stateProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            stateProcessor = null;
        }
        if (messageSender != null) {
            messageSender.shutdown();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            messageSender = null;
        }
        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            eventProcessor = null;
        }
        if (messageRouter != null) {
            messageRouter.shutdown();
            messageRouter.interrupt();
            while (messageRouter.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            messageRouter = null;
        }

        if (configuration.getResetSeqAtDisconnect()) {
            resetSequences();
        }

        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "FIX server [{0}] stopped as transport was lost.", getName());
    }

    private void unblock() {
        unblockStateProcessor();
        if (messageSender != null) {
            messageSender.unblock();
            messageSender.interrupt();
            while (!messageSender.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        setProcessStatus(ProcessStatus.ACTIVE);
        mgmtData.get().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "FIX server [{0}] unblocked.", getName());
    }

    private void unblockStateProcessor() {
        if (stateProcessor != null) {
            stateProcessor.unblock();
            stateProcessor.interrupt();
            while (!stateProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
    }

    private void freeze() {
        stateProcessor.setProcessingStage(ProcessingStage.FROZEN);
        stateProcessor.interrupt();

        setProcessStatus(ProcessStatus.FROZEN);
        mgmtData.get().setStatus(ProcessStatus.FROZEN);

        LOGGER.log(Level.INFO, "FIX server [{0}] frozen.", getName());
    }

    private void thaw() {
        stateProcessor.setProcessingStage(ProcessingStage.LOGGEDON);
        stateProcessor.interrupt();

        setProcessStatus(ProcessStatus.ACTIVE);
        mgmtData.get().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "FIX server [{0}] thawed.", getName());
    }

    private void shutdown() {
        LOGGER.log(Level.INFO, "FIX server [{0}] shutting down.", getName());

        if (stateProcessor != null) {
            stateProcessor.block();
            stateProcessor.interrupt();
            stateProcessor.setProcessingStage(ProcessingStage.LOGGEDOUT);
        }

        while (transport != null && transport.isConnected() && (!rxQueue.isEmpty() || !txQueue.isEmpty()) && ProcessStatus.ACTIVE.equals(processStatus.get())) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Buffer clear task interrupted. Error was : {0}", ex.toString());
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
        if (messageRouter != null) {
            messageRouter.shutdown();
            messageRouter.interrupt();
            while (messageRouter.isActive()) {
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
        setProcessStatus(ProcessStatus.SHUTDOWN);
        mgmtData.get().setStatus(ProcessStatus.SHUTDOWN);

        LOGGER.log(Level.INFO, "FIX server [{0}] shutdown.", getName());
        shutdown = true;
    }

    private void shutdownNow() {
        LOGGER.log(Level.INFO, "FIX server [{0}] shutting down now.", getName());

        if (stateProcessor != null) {
            stateProcessor.block();
            stateProcessor.interrupt();
            stateProcessor.setProcessingStage(ProcessingStage.LOGGEDOUT);
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
        if (messageRouter != null) {
            messageRouter.shutdown();
            messageRouter.interrupt();
            while (messageRouter.isActive()) {
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
        setProcessStatus(ProcessStatus.SHUTDOWN);
        mgmtData.get().setStatus(ProcessStatus.SHUTDOWN);

        LOGGER.log(Level.INFO, "FIX server [{0}] shutdown.", getName());
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
        messageSender.start();
        ThreadUtil.blockUntilAlive(messageSender);
        while (!messageSender.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void startStateProcessor() {
        stateProcessor.start();
        stateProcessor.block();
        ThreadUtil.blockUntilAlive(stateProcessor);
        while (stateProcessor.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }
}
