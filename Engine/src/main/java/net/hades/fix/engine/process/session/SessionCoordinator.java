/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.session;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.*;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.handler.HandlerException;
import net.hades.fix.engine.exception.ProtocolException;
import net.hades.fix.engine.exception.ProtocolStatusException;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.*;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.Advisable;
import net.hades.fix.engine.process.ManagedTask;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.EventProcessor;
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
public abstract class SessionCoordinator implements ManagedTask, Advisable {

    private static final Logger Log = Logger.getLogger(SessionCoordinator.class.getName());

    private static final int MAX_NUM_COMMANDS = 5;

    protected HadesInstance hadesInstance;

    protected String id;
    protected CounterpartyInfo cptyConfiguration;
    protected SessionAddress sessionAddress;

    protected Protocol protocol;

    protected EventProcessor eventProcessor;

    protected ProducerStream producerStream;
    protected ConsumerStream consumerStream;
    protected List<Handler> singletonHandlers;

    protected volatile TaskStatus status;
    protected volatile boolean shutdown;

    public SessionCoordinator(HadesInstance hadesInstance, CounterpartyInfo cptyConfiguration, SessionAddress sessionAddress) throws ConfigurationException {
        this.hadesInstance = hadesInstance;
	this.cptyConfiguration = cptyConfiguration;
        this.sessionAddress = sessionAddress;

    }
    
    protected abstract SessionInfo getConfiguration();
    
    /**
     * Runs the stream handlers upon the TCP connection success.
     * @param clientSocket TCP connection
     */
    public abstract void startStreamHandlers(Socket clientSocket);

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
            String errMsg = "Invalid Reject message for session [" + id + "].";

            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});

            ComponentType componentType = ComponentType.ClientSessionCoordinator;
            if (this instanceof ServerSessionCoordinator) {
                componentType = ComponentType.ServerSessionCoordinator;
            }
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(id, componentType.toString(), BaseSeverityType.RECOVERABLE,
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
            String errMsg = "Invalid SequenceReset message for session [" + id + "].";

            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });

            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(id, this.getClass().getSimpleName(), BaseSeverityType.RECOVERABLE,
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
                String errMsg = "Invalid Reject message for session [" + id + "].";

                Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});

                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(id, this.getClass().getSimpleName(), BaseSeverityType.RECOVERABLE,
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
            String errMsg = "Singleton handler class [" + implClass + "] for session [" + getConfiguration().getID()
                    + "] was not found.";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (InstantiationException ex) {
            String errMsg = "Could not create an instance of handler class [" + implClass
                    + "] for flow [" + getConfiguration().getID() + "].";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (IllegalAccessException ex) {
            String errMsg = "Class accessor error for [" + implClass + "] for flow [" + getConfiguration().getID()
                    + "].";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        }

        return handler;
    }

    protected Map<String, String> buildParameterMap(HandlerParamInfo[] handlerParams) {
        Map<String, String> result = null;
        if (handlerParams != null && handlerParams.length > 0) {
            result = new HashMap<>(handlerParams.length);
            for (HandlerParamInfo handlerParam : handlerParams) {
                result.put(handlerParam.getName(), handlerParam.getValue());
            }
        }
        return result;
    }

    protected void aggregateSessionConfiguration(CounterpartyInfo counterpartyInfo) {
        if (Log.isLoggable(Level.FINEST)) {
            Log.finest("Aggregating handler/secured messages configuration.");
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
            if (getConfiguration().getSchedules() != null && getConfiguration().getSchedules().length > 0) {
                for (ScheduleTaskInfo task : getConfiguration().getSchedules()) {
                    hadesInstance.getScheduler().scheduleTask(this, task);
                }
            }
        }
    }

    private void aggregateHandlerDefInfo(CounterpartyInfo counterpartyInfo) {
        if (Log.isLoggable(Level.FINEST)) {
            Log.log(Level.FINEST, "Aggregating counterparty handler configuration for [{0}] handlers.", counterpartyInfo.getHandlerDefs().length);
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
        if (Log.isLoggable(Level.FINEST)) {
            Log.log(Level.FINEST, "Aggregating counterparty secured messages for [{0}] messages.", counterpartyInfo.getSecuredMessages().length);
        }

        for (SessionInfo sessionInfo : counterpartyInfo.getSessions()) {
            Map<String, SecuredMessageInfo> securedMessages = new HashMap<>();
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
