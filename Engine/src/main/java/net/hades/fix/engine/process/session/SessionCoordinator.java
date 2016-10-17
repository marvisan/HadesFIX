/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ServerSessionCoordinator.java
 */
package net.hades.fix.engine.process.session;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.Engine;
import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.*;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.HandlerException;
import net.hades.fix.engine.exception.ProtocolException;
import net.hades.fix.engine.exception.ProtocolStatusException;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.*;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.Commandable;
import net.hades.fix.engine.process.Reportable;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.EventProcessor;
import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.Protocol;
import net.hades.fix.engine.process.protocol.ProtocolVersion;
import net.hades.fix.engine.process.stream.ConsumerStream;
import net.hades.fix.engine.process.stream.ProducerStream;
import net.hades.fix.engine.process.transport.Transport;
import net.hades.fix.message.*;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.MsgUtil;

/**
 * Abstract class to be extended by a session coordinator.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class SessionCoordinator implements Handler {

    private static final Logger LOGGER = Logger.getLogger(SessionCoordinator.class.getName());

    private static final int MAX_NUM_COMMANDS = 5;

    protected HadesInstance hadesInstance;

    protected SessionInfo configuration;
    protected CounterpartyInfo cptyConfiguration;

    protected BlockingQueue<Command> commandQueue;

    protected SessionAddress sessionAddress;

    protected Transport transport;

    protected Protocol protocol;

    protected EventProcessor eventProcessor;

    protected ProducerStream producerStream;
    protected ConsumerStream consumerStream;
    protected List<Handler> singletonHandlers;

    protected volatile boolean shutdown;
    protected volatile boolean blocked;

    protected AtomicReference<ProcessStatus> processStatus;
    protected AtomicReference<SessionProcessData> mgmtData;
    protected AtomicReference<SessionStats> stats;

    public SessionCoordinator(String name) throws ConfigurationException {
        super(name);

        processStatus = new AtomicReference<>();
        mgmtData = new AtomicReference<>(new SessionProcessData());
        stats = new AtomicReference<>(new SessionStats());
        commandQueue = new LinkedBlockingQueue<>(MAX_NUM_COMMANDS);
    }
    
    /**
     * Runs the stream handlers upon the TCP connection success.
     * @param clientSocket TCP connection
     */
    public abstract void startStreamHandlers(Socket clientSocket);


    /**
     * Initialise the structures of the session coordinator.
     * @throws ConfigurationException configuration error
     */
    public abstract void initialise() throws ConfigurationException;

    /**
     * Starts all the threads managed by this session coordinator.
     */
    public abstract void startup();

    /**
     * Shuts down gracefully thus session coordinator and its processes.
     */
    public abstract void shutdown();

    /**
     * Shuts immediately thus session coordinator and its processes.
     */
    public abstract void shutdownNow();

    /**
     * Freezes the session managed by this coordinator.
     * @throws ProtocolStatusException
     */
    public abstract void freezeProtocol() throws ProtocolStatusException;

    /**
     * Unfreeze the session managed by this coordinator.
     * @throws ProtocolStatusException
     */
    public abstract void thawProtocol() throws ProtocolStatusException;

    /**
     * Disconnects the transport of an active session, block the protocol and streams.
     * @throws ProtocolStatusException
     */
    public abstract void disconnectTransport() throws ProtocolStatusException;

    /**
     * Connects a disconnected blocked client session. Does nothing for server side.
     * @throws ProtocolStatusException
     */
    public abstract void connectTransport() throws ProtocolStatusException;

    @Override
    public void addLifeCycleListener(LifeCycleListener listener) {
        eventProcessor.addLifeCycleListener(listener);
    }

    @Override
    public void addAlertListener(AlertListener listener) {
        eventProcessor.addAlertListener(listener);
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        eventProcessor.addMessageListener(listener);
    }

    @Override
    public void execute(Command command) {
        try {
            commandQueue.put(command);
        } catch (InterruptedException ex) {
            String errMsg = "Session coordinator thread [" + getName() + "] has been unexpectedly interrupted.";
            LOGGER.log(Level.WARNING, "{0} Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });
        }
    }

    @Override
    public EventProcessor getEventProcessor() {
        return eventProcessor;
    }
    
    @Override
    public List<Handler> getSingletonHandlers() {
        return singletonHandlers;
    }

    @Override
    public HandlerDefInfo[] getHandlerDefs() {
        return configuration.getHandlerDefs();
    }

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public Engine getEngine() {
        return hadesInstance;
    }

    @Override
    public SessionAddress getSessionAddress() {
        return sessionAddress;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus.get();
    }

    public ProcessData getMgmtData() {
        mgmtData.get().setTransportProcessData(transport != null ? (TransportProcessData) transport.getMgmtData() : new TransportProcessData());
        mgmtData.get().setProtocolProcessData(protocol != null ? (ProtocolProcessData) protocol.getMgmtData() : new ProtocolProcessData());
        mgmtData.get().setConsumerProcessData(consumerStream != null ? (StreamProcessData) consumerStream.getMgmtData() : new StreamProcessData());
        mgmtData.get().setProducerProcessData(producerStream != null ? (StreamProcessData) producerStream.getMgmtData() : new StreamProcessData());

        return mgmtData.get();
    }

    public Stats getStats() {
        stats.get().setTransportStats(transport != null ? (TransportStats) transport.getStats() : new TransportStats());
        stats.get().setProtocolStats(protocol != null ? (ProtocolStats) protocol.getStats() : new ProtocolStats());
        stats.get().setConsumerStreamStats(consumerStream != null ? (StreamStats) consumerStream.getStats() : new StreamStats());
        stats.get().setProducerStreamStats(producerStream != null ? (StreamStats) producerStream.getStats() : new StreamStats());

        return stats.get();
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public SessionInfo getConfiguration() {
        return configuration;
    }

    public void sendRejectMessage(FIXMsg message, HandlerException exception) throws InterruptedException {
        RejectMsg msg;
        ProtocolVersion protocolVersion = protocol.getVersion();
        try {
            msg = MessageFiller.buildRejectMsg(protocol, message, SessionRejectReason.Other, exception);
            if (MsgUtil.compare(protocolVersion.getBeginString(), BeginString.FIX_4_2) >= 0) {
                msg.setRefMsgType(message.getHeader().getMsgType());
                msg.setRefSeqNo(message.getHeader().getMsgSeqNum());
            }
            msg.setPriority(Message.PRIORITY_HIGH);
            protocol.writeMessage(msg);
        } catch (InvalidMsgException ex) {
            String errMsg = "Invalid Reject message for session [" + getName() + "].";

            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});

            ComponentType componentType = ComponentType.ClientSessionCoordinator;
            if (this instanceof ServerSessionCoordinator) {
                componentType = ComponentType.ServerSessionCoordinator;
            }
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), componentType.toString(), BaseSeverityType.RECOVERABLE,
                        AlertCode.MSG_FORMAT_ERROR, errMsg, ex)));
        }
    }

    public void sendResetSequenceMessage(int newSeqNum) throws ProtocolException, InterruptedException {
        SequenceResetMsg msg;
        try {
            msg = MessageFiller.buildSequenceResetMsg(protocol);
            msg.setGapFillFlag(Boolean.FALSE);
            msg.setNewSeqNo(newSeqNum);
            protocol.setRxSeqNo(newSeqNum - 1);
            msg.setPriority(Message.PRIORITY_HIGH);
            protocol.writeMessage(msg);
        } catch (InvalidMsgException ex) {
            String errMsg = "Invalid SequenceReset message for session [" + getName() + "].";

            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });

            ComponentType componentType = ComponentType.ClientSessionCoordinator;
            if (this instanceof ServerSessionCoordinator) {
                componentType = ComponentType.ServerSessionCoordinator;
            }
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), componentType.toString(), BaseSeverityType.RECOVERABLE,
                    AlertCode.MSG_FORMAT_ERROR, errMsg, ex)));
            throw new ProtocolException(errMsg, ex);
        }
    }

    /**
     * Sends a Logon message with ResetSeqNumFlag set to true that will trigger a sequence
     * number reset on both sides.
     * @throws net.hades.fix.engine.exception.ProtocolException
     */
    public void sessionReset() throws ProtocolException {
        if (ProcessStatus.ACTIVE.equals(processStatus.get())) {
            try {
                protocol.getStateProcessor().setProcessingStage(ProcessingStage.RESET);
                protocol.setTxSeqNo(0);
                LogonMsg logonMessage = MessageFiller.buildResetSeqNumLogonMsg(getProtocol());
                logonMessage.setPriority(Message.PRIORITY_HIGH);
                protocol.writeMessage(logonMessage);
                protocol.getStateProcessor().getTimers().startLogonTimerTask();
            } catch (InvalidMsgException ex) {
                String errMsg = "Invalid Reject message for session [" + getName() + "].";

                LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});

                ComponentType componentType = ComponentType.ClientSessionCoordinator;
                if (this instanceof ServerSessionCoordinator) {
                    componentType = ComponentType.ServerSessionCoordinator;
                }
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), componentType.toString(), BaseSeverityType.RECOVERABLE,
                                AlertCode.MSG_FORMAT_ERROR, errMsg, ex)));
                throw new ProtocolException(errMsg, ex);
            }
        } else {
            throw new ProtocolException("Could not reset session sequence number. The session is not active.");
        }
    }

    public String getCptyID() {
        return sessionAddress.getRemoteAddress().getID();
    }

    public String getLocalID() {
        return sessionAddress.getLocalAddress().getID();
    }

//    public CounterpartyInfo getCptyConfiguration() {
//        return cptyConfiguration;
//    }
//
//    public void setCptyConfiguration(CounterpartyInfo cptyConfiguration) {
//        this.cptyConfiguration = cptyConfiguration;
//    }
//
//    public ConsumerStream getConsumerStream() {
//        return consumerStream;
//    }
//
//    public ProducerStream getProducerStream() {
//        return producerStream;
//    }
//
//    public void aggregateHandlers() {
//        aggregateHandlerDefInfo(cptyConfiguration);
//    }
//
//    public Map<String, String> getHandlerParameters(String handlerName) {
//        Map<String, String> result = new HashMap<String, String>();
//        for (HandlerDefInfo handlerDefInfo : configuration.getHandlerDefs()) {
//            if (handlerDefInfo.getName().equals(handlerName)) {
//                HandlerParamInfo[] parameters = handlerDefInfo.getParameters();
//                if (parameters != null && parameters.length > 0) {
//                    for (HandlerParamInfo parameter : parameters) {
//                        result.put(parameter.getName(), parameter.getValue());
//                    }
//                }
//                break;
//            }
//        }
//
//        return result;
//    }

    protected void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus.set(processStatus);
    }

    protected void findSingletonHandlers() throws ConfigurationException {
        LOGGER.log(Level.INFO, "Searching for singleton handlers for session [{0}].", configuration.getID());

        singletonHandlers = new ArrayList<Handler>();
        List<HandlerInfo> prodHandlersInfo = getHandlersInfo(configuration.getProducerStreamInfo());
        List<HandlerInfo> consHandlersInfo = getHandlersInfo(configuration.getConsumerStreamInfo());
        for (HandlerInfo ph : prodHandlersInfo) {
            for (HandlerInfo ch : consHandlersInfo) {
                boolean prodSingleton = ph.getSingleton() != null ? ph.getSingleton() : false;
                boolean consSingleton = ch.getSingleton() != null ? ch.getSingleton() : false;
                if (ph.getName().equals(ch.getName()) && prodSingleton && consSingleton) {
                    LOGGER.log(Level.INFO, "Found singleton handler [{0}].", ch.getName());

                    Handler handler = getConfiguredHandler(ph.getName(), configuration.getHandlerDefs());
                    if (!(handler instanceof ConsumerHandler && handler instanceof ProducerHandler)) {
                        String errMsg = "Singleton handler class [" + ph.getName() + "] must implement both ConsumerHandler and ProducerHandler.";
                        LOGGER.severe(errMsg);
                        throw new ConfigurationException(errMsg);
                    }
                    singletonHandlers.add(handler);
                }
            }
        }
    }

    protected List<HandlerInfo> getHandlersInfo(StreamInfo streamInfo) {
        List<HandlerInfo> handlers = new ArrayList<HandlerInfo>();
        for (FlowInfo cf : streamInfo.getFlows()) {
            HandlerInfo ch = cf.getHandler();
            handlers.add(ch);
            while (ch.getNextHandler() != null) {
                ch = ch.getNextHandler();
                handlers.add(ch);
            }
        }

        return handlers;
    }

    protected Handler getConfiguredHandler(String name, HandlerDefInfo[] handlerDefs) throws ConfigurationException {
        Handler result = null;
        for (HandlerDefInfo handlerDef : handlerDefs) {
            if (name.equals(handlerDef.getName())) {
                result = createHandlerInstance(handlerDef.getImplClass());
                result.setName(handlerDef.getName());
                result.setParameters(buildParameterMap(handlerDef.getParameters()));
            }
        }

        return result;
    }

    protected Handler createHandlerInstance(String implClass) throws ConfigurationException {
        Handler handler;
        try {
            Class<? extends Handler> filterClass = Class.forName(implClass.trim()).asSubclass(Handler.class);
            handler = filterClass.newInstance();
        } catch (ClassNotFoundException ex) {
            String errMsg = "Singleton handler class [" + implClass + "] for session [" + configuration.getID()
                    + "] was not found.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (InstantiationException ex) {
            String errMsg = "Could not create an instance of handler class [" + implClass
                    + "] for flow [" + configuration.getID() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (IllegalAccessException ex) {
            String errMsg = "Class accessor error for [" + implClass + "] for flow [" + configuration.getID()
                    + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        }

        return handler;
    }

    protected Map<String, String> buildParameterMap(HandlerParamInfo[] handlerParams) {
        Map<String, String> result = null;
        if (handlerParams != null && handlerParams.length > 0) {
            result = new HashMap<String, String>(handlerParams.length);
            for (HandlerParamInfo handlerParam : handlerParams) {
                result.put(handlerParam.getName(), handlerParam.getValue());
            }
        }

        return result;
    }

    protected void aggregateSessionConfiguration(CounterpartyInfo counterpartyInfo) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest("Aggregating handler/secured messages configuration.");
        }

        HandlerDefInfo[] handlerDefs = counterpartyInfo.getHandlerDefs();
        SecuredMessageInfo[] securedMessages = counterpartyInfo.getSecuredMessages();
        if (handlerDefs != null && handlerDefs.length > 0) {
            aggregateHandlerDefInfo(counterpartyInfo);
        }
        if (securedMessages != null && securedMessages.length > 0) {
            aggregateSecuredMessageInfo(counterpartyInfo);
        }
    }

    protected void startSessionScheduledTasks() {
        if (hadesInstance.getScheduler() != null && hadesInstance.getScheduler().isAlive()) {
            if (configuration.getSchedules() != null && configuration.getSchedules().length > 0) {
                for (ScheduleTaskInfo task : configuration.getSchedules()) {
                    hadesInstance.getScheduler().scheduleTask(this, task);
                }
            }
        }
    }

    private void aggregateHandlerDefInfo(CounterpartyInfo counterpartyInfo) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Aggregating counterparty handler configuration for [{0}] handlers.", counterpartyInfo.getHandlerDefs().length);
        }

        for (SessionInfo sessionInfo : counterpartyInfo.getSessions()) {
            Map<String, HandlerDefInfo> handlers = new HashMap<String, HandlerDefInfo>();
            for (HandlerDefInfo handler : counterpartyInfo.getHandlerDefs()) {
                handlers.put(handler.getName(), handler);
            }
            if (sessionInfo.getHandlerDefs() != null && sessionInfo.getHandlerDefs().length > 0) {
                for (HandlerDefInfo handler : sessionInfo.getHandlerDefs()) {
                    handlers.put(handler.getName(), handler);
                }
            }
            sessionInfo.setHandlerDefs(handlers.values().toArray(new HandlerDefInfo[handlers.size()]));
        }
    }


    private void aggregateSecuredMessageInfo(CounterpartyInfo counterpartyInfo) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Aggregating counterparty secured messages for [{0}] messages.", counterpartyInfo.getSecuredMessages().length);
        }

        for (SessionInfo sessionInfo : counterpartyInfo.getSessions()) {
            Map<String, SecuredMessageInfo> securedMessages = new HashMap<String, SecuredMessageInfo>();
            for (SecuredMessageInfo securedMessage : counterpartyInfo.getSecuredMessages()) {
                securedMessages.put(securedMessage.getType(), securedMessage);
            }
            if (sessionInfo.getSecuredMessages() != null && sessionInfo.getSecuredMessages().length > 0) {
                for (SecuredMessageInfo securedMessage : sessionInfo.getSecuredMessages()) {
                    securedMessages.put(securedMessage.getType(), securedMessage);
                }
            }
            sessionInfo.setSecuredMessages(securedMessages.values().toArray(new SecuredMessageInfo[securedMessages.size()]));
        }
    }

}
