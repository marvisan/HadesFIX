/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.session;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.*;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.ProtocolStatusException;
import net.hades.fix.engine.mgmt.data.SessionProcessData;
import net.hades.fix.engine.mgmt.data.SessionStats;
import net.hades.fix.engine.model.CounterpartyAddress;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.Advisable;
import net.hades.fix.engine.process.ManagedTask;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.MessageEvent;
import net.hades.fix.engine.process.protocol.Protocol;
import net.hades.fix.engine.process.stream.ConsumerStream;
import net.hades.fix.engine.process.stream.ProducerStream;

/**
 * Abstract class to be extended by a session coordinator.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class SessionCoordinator implements ManagedTask, Advisable {

    private static final Logger Log = Logger.getLogger(SessionCoordinator.class.getName());
    
    public static final String SESSION_CONTEXT_KEY = "SESSION_CONTEXT_KEY";

    protected HadesInstance hadesInstance;

    protected String id;
    protected CounterpartyInfo cptyConfiguration;
    protected SessionInfo sessionConfiguration;
    protected SessionAddress sessionAddress;
    protected final BlockingQueue<String> commandQueue;

    protected Protocol protocol;
    protected ProducerStream producerStream;
    protected ConsumerStream consumerStream;
    protected volatile TaskStatus status;
    protected volatile boolean shutdown;
    protected ConcurrentMap<String, Object> sessionContext;

    public SessionCoordinator(HadesInstance hadesInstance, CounterpartyInfo cptyConfiguration, SessionAddress sessionAddress) throws ConfigurationException {
        this.hadesInstance = hadesInstance;
	this.cptyConfiguration = cptyConfiguration;
        this.sessionAddress = sessionAddress;
	commandQueue = new ArrayBlockingQueue<>(1);
	sessionContext = new ConcurrentHashMap<>();
	sessionConfiguration = getSessionConfiguration(cptyConfiguration, sessionAddress);
    }

    public SessionInfo getConfiguration() {
	return sessionConfiguration;
    }

    public ConcurrentMap<String, Object> getSessionContext() {
	return sessionContext;
    }
    
    /**
     * Runs the stream handlers upon the TCP connection success.
     * @param clientSocket TCP connection
     */
    public abstract void startStreamHandlers(Socket clientSocket);
    
    public abstract void disconnectTransport() throws ProtocolStatusException;
    
    public abstract void connectTransport() throws ProtocolStatusException;
    
    /**
     * Stopped the stream handlers upon the TCP connection close.
     */
    public abstract void stopStreamHandlers();

    public SessionAddress getSessionAddress() {
	return sessionAddress;
    }

    public String getCptyID() {
        return sessionAddress.getRemoteAddress().getID();
    }

    public String getLocalID() {
        return sessionAddress.getLocalAddress().getID();
    }
    
    public ExecutorService getExecutorService() {
	return hadesInstance.getExecutorService();
    }

    @Override
    public void onAlertEvent(AlertEvent message) {
	hadesInstance.getEventProcessor().onAlertEvent(message);
    }

    @Override
    public void onLifeCycleEvent(LifeCycleEvent message) {
	hadesInstance.getEventProcessor().onLifeCycleEvent(message);
    }

    @Override
    public void onMessageEvent(MessageEvent message) {
	hadesInstance.getEventProcessor().onMessageEvent(message);
    }

    public Protocol getProtocol() {
	return protocol;
    }
    
    public SessionProcessData getMgmtData() {
	return null;
    }
    
    public SessionStats getStats() {
	return null;
    }

    private SessionInfo getSessionConfiguration(CounterpartyInfo cptyConfiguration, SessionAddress sessionAddress) {
	for (SessionInfo config : cptyConfiguration.getSessions()) {
	    CounterpartyAddress localAddr = new CounterpartyAddress(config.getCompID(), config.getSubID(), config.getLocationID());
	    if (localAddr.equals(sessionAddress.getLocalAddress().getCompID())) {
		return config;
	    }
	}
	return null;
    }

}
