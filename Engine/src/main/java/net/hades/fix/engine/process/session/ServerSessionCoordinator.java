/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.session;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.CounterpartyInfo;
import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.config.ConfigurationException;
import net.hades.fix.engine.process.protocol.ProtocolStatusException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.process.transport.TcpServer;

/**
 * The main class controlling the life cycle of a counter-party server session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public final class ServerSessionCoordinator extends SessionCoordinator {

    private static final Logger LOGGER = Logger.getLogger(ServerSessionCoordinator.class.getName());

    protected TcpServer transport;

    public ServerSessionCoordinator(HadesInstance hadesInstance, SessionInfo configuration, CounterpartyInfo cptyConfiguration, SessionAddress sessionAddress) 
	    throws ConfigurationException {
        super(hadesInstance, cptyConfiguration, sessionAddress);
	this.hadesInstance = hadesInstance;
	id = configuration.getID();
	status = TaskStatus.New;
    }

    @Override
    public ExecutionResult call() throws Exception {
	LOGGER.log(Level.INFO, "Server session coordinator [{0}] running.", id);
	status = TaskStatus.Running;
	ExecutionResult result;

	try {
	    String cmd = commandQueue.take();
	    LOGGER.log(Level.INFO, "Client session coordinator [{0}].", cmd);
	    status = TaskStatus.Completed;
	    result = new ExecutionResult(status);
	} catch (Exception ex) {
	    onAlertEvent(new AlertEvent(this,
		    Alert.createAlert(id, ServerSessionCoordinator.class.getSimpleName(),
			    BaseSeverityType.FATAL, AlertCode.SESSION_DESTROYED, "Unexpected error.", ex)));
	    status = TaskStatus.Error;
	    result = new ExecutionResult(status, ex);
	}
	return result;
    }

    @Override
    public String getId() {
	return id;
    }

    @Override
    public void shutdown() {
	shutdownImmediate();
    }

    @Override
    public void shutdownImmediate() {
	try {
	    commandQueue.put("SHUTDOWN");
	} catch (InterruptedException ex) {
	    onAlertEvent(new AlertEvent(this,
		    Alert.createAlert(id, ClientSessionCoordinator.class.getSimpleName(),
			    BaseSeverityType.FATAL, AlertCode.SESSION_DESTROYED, "Unexpected error.", ex)));
	    status = TaskStatus.Error;
	}
    }

    @Override
    public TaskStatus getStatus() {
	return status;
    }

    @Override
    public void startStreamHandlers(Socket clientSocket) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopStreamHandlers() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disconnectTransport() throws ProtocolStatusException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void connectTransport() throws ProtocolStatusException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
