/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineSessionWorker.java
 *
 * $Id$
 */
package net.hades.fix.admin.session;

import net.hades.fix.admin.command.Command;
import net.hades.fix.admin.console.data.OutcomeResult;
import net.hades.fix.admin.console.data.TableResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.management.NotificationFilterSupport;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectionNotification;

import net.hades.fix.admin.cmdline.CommandName;
import net.hades.fix.admin.console.data.MultiTableResult;
import net.hades.fix.admin.console.data.SessionAddress;
import net.hades.fix.admin.console.exception.InputValidationException;
import net.hades.fix.admin.gui.HadesAdminConsole;
import net.hades.fix.admin.gui.config.model.EngineConnectionInfo;
import net.hades.fix.admin.gui.util.ConnectionHelper;
import net.hades.fix.commons.exception.ExceptionUtil;

/**
 * Thread running the engine connection.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class EngineSessionWorker implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(EngineSessionWorker.class.getName());
    
    public static final String CMD_EXIT = "exit";
    
    private int connectionId;
    private String remoteConnDetails;
    private HadesEngineSession session;
    private EngineConnectionInfo connectionInfo;
    private volatile boolean exit;
    private BlockingQueue<String> commandQueue;
    private Map<Long, SessionAddress> addresses;
    private HadesAdminConsole console;
    private NotificationListener engineListener;
    private NotificationListener connectionListener;
    
    public EngineSessionWorker(EngineConnectionInfo connectionInfo, HadesAdminConsole console) {
        this.connectionInfo = connectionInfo;
        this.console = console;
        commandQueue = new ArrayBlockingQueue<String>(5);
        connectionId = connectionInfo.getId().intValue();
        remoteConnDetails = String.format("%s:%s", new Object[] {connectionInfo.getHost(), connectionInfo.getPort()});
    }
    
    public void executeCommand(String command) {
        commandQueue.add(command);
    }
    
    @Override
    public void run() {
        while (!exit) {
            try {
                String command = commandQueue.take();
                if (CMD_EXIT.equals(command)) {
                    session.disconnect();
                    exit();
                }
            } catch (SessionException ex) {
                LOGGER.log(Level.WARNING, "HadesFIX session with Id [{0}] could not be disconnected. Closing session for [{1}] now.", new Object[] {connectionId, remoteConnDetails});
                console.closeConnection(connectionInfo);
                exit = true;
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "HadesFIX session with Id [{0}] has been interrupted. Closing session for [{1}] now.", new Object[] {connectionId, remoteConnDetails});
                console.closeConnection(connectionInfo);
                exit = true;
            }
        }
        LOGGER.log(Level.INFO, "HadesFIX session: {0}-{1} has been terminated.", new Object[] {connectionId, remoteConnDetails});
        console.closeConnection(connectionInfo);
    }
    
    public void connect() throws InputValidationException, SessionException {
        
        try {
            session = ConnectionHelper.connectToEngine(connectionInfo);
            connectionInfo.setEngineName(session.getHadesInstanceName());
            connectionInfo.setEngineDescr(session.getHadesInstanceDescr());
            engineListener = new EngineEventListener(this, connectionInfo);
            NotificationFilterSupport engineFilter = new NotificationFilterSupport();
            engineFilter.enableType(EngineEventListener.ALERT_NOTIFICATION);
            engineFilter.enableType(EngineEventListener.LIFECYCLE_NOTIFICATION);
            engineFilter.enableType(EngineEventListener.MESSAGE_NOTIFICATION);
            engineFilter.enableType(JMXConnectionNotification.CLOSED);
            engineFilter.enableType(JMXConnectionNotification.OPENED);
            engineFilter.enableType(JMXConnectionNotification.FAILED);
            engineFilter.enableType(JMXConnectionNotification.NOTIFS_LOST);
            ObjectName server = new ObjectName("HadesDomain:engine=HadesFIXEngineMBean");
            session.getMBeanServerConnection().addNotificationListener(server, engineListener, engineFilter, server);
            connectionListener = new ConnectionEventListener(this);
            NotificationFilterSupport connectionFilter = new NotificationFilterSupport();
            connectionFilter.enableType(JMXConnectionNotification.CLOSED);
            connectionFilter.enableType(JMXConnectionNotification.OPENED);
            connectionFilter.enableType(JMXConnectionNotification.FAILED);
            connectionFilter.enableType(JMXConnectionNotification.NOTIFS_LOST);
            session.getConnector().addConnectionNotificationListener(connectionListener, connectionFilter, null);
        } catch (MalformedObjectNameException ex) {
            LOGGER.log(Level.SEVERE, "Invalid obejct name for instance: {0}. Error was: {1}", new Object[] {connectionId, ExceptionUtil.getStackTrace(ex)});
            throw new SessionException("Invalid obejct name.", ex);
        } catch (InstanceNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No MBean server instance found for instance: {0}. Error was: {1}", new Object[] {connectionId, ExceptionUtil.getStackTrace(ex)});
            throw new SessionException("No MBean server instance found.", ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error communicating with the MBean server instance: {0}. Error was: {1}", new Object[] {connectionId, ExceptionUtil.getStackTrace(ex)});
            throw new SessionException("Error communicating with the MBean server instance.", ex);
        }
    }
    
    public String getInstanceName() throws SessionException {
        return session.getHadesInstanceName();
    }
    
    public String getInstanceDescr() throws SessionException {
        return session.getHadesInstanceDescr();
    }

    public String getRemoteConnDetails() {
        return remoteConnDetails;
    }

    public EngineNotificationProcessor getEngineNotificationProcessor() {
        return console.getNotificationProcessor();
    }
    
    public void exit() {
        exit = true;
    }

    /**
     * Gets all the configured sessions on the server.
     * @return 
     */
    public TableResult getConfigSessions() {
        Command cmd = Command.getCommand(CommandName.LIST_SESS.getValue(), session.getMBeanServerConnection());
        TableResult result = (TableResult) cmd.execute();
        // refresh sessions always
        refreshSessions(result);
        
        return result;
    }
    
    /**
     * Gets the current running info for the given session.
     * @param sessionId
     * @return 
     */
    public TableResult getConfigSession(String sessionId) {
        StringBuilder sb = new StringBuilder(CommandName.LIST_SESS.getValue());
        sb.append(" ").append(sessionId);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        
        return (TableResult) cmd.execute();
    }

    /**
     * Shutdown the engine for this connection.
     * @return 
     */
    public OutcomeResult shutdown() {
        Command cmd = Command.getCommand(CommandName.SERV_SHUTDOWN.getValue(), session.getMBeanServerConnection());
        
        return (OutcomeResult) cmd.execute();
    }

    /**
     * Disconnects transport of a HadesFIX engine session.
     * @param sessionId
     * @return 
     */
    public OutcomeResult disconnectSessionTransport(String sessionId) {
        StringBuilder sb = new StringBuilder(CommandName.SESS_DISC.getValue());
        sb.append(" ").append(sessionId);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        cmd.setSessionAddress(addresses.get(Long.parseLong(sessionId)));
        
        return (OutcomeResult) cmd.execute();
    }

    /**
     * Connects a disconnected transport of a HadesFIX engine session.
     * @param sessionId
     * @return 
     */
    public OutcomeResult connectSessionTransport(String sessionId) {
        StringBuilder sb = new StringBuilder(CommandName.SESS_CONN.getValue());
        sb.append(" ").append(sessionId);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        cmd.setSessionAddress(addresses.get(Long.parseLong(sessionId)));
        
        return (OutcomeResult) cmd.execute();
    }

    /**
     * 
     * @param sessionId
     * @param newSeqNum
     * @return 
     */
    public OutcomeResult resetSequence(String sessionId, int newSeqNum) {
        StringBuilder sb = new StringBuilder(CommandName.SEQ_RESET.getValue());
        sb.append(" ").append(sessionId).append(" ").append(newSeqNum);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        cmd.setSessionAddress(addresses.get(Long.parseLong(sessionId)));
        
        return (OutcomeResult) cmd.execute();
    }

    /**
     * Sets the session Rx and Tx sequence number to 1 by sending a SequenceReset FIX message to the counter-party.
     * @param sessionId
     * @return 
     */
    public OutcomeResult resetSeession(String sessionId) {
        StringBuilder sb = new StringBuilder(CommandName.SESS_RESET.getValue());
        sb.append(" ").append(sessionId);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        cmd.setSessionAddress(addresses.get(Long.parseLong(sessionId)));
        
        return (OutcomeResult) cmd.execute();
    }

    /**
     * Freezes a session. Only heartbeats are accepted by the session.
     * @param sessionId
     * @return 
     */
    public OutcomeResult freezeSession(String sessionId) {
        StringBuilder sb = new StringBuilder(CommandName.SESS_FREEZE.getValue());
        sb.append(" ").append(sessionId);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        cmd.setSessionAddress(addresses.get(Long.parseLong(sessionId)));
        
        return (OutcomeResult) cmd.execute();
    }

    /**
     * Thaws an existing frozen session.
     * @param sessionId
     * @return 
     */
    public OutcomeResult thawSession(String sessionId) {
        StringBuilder sb = new StringBuilder(CommandName.SESS_THAW.getValue());
        sb.append(" ").append(sessionId);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        cmd.setSessionAddress(addresses.get(Long.parseLong(sessionId)));
        
        return (OutcomeResult) cmd.execute();
    }

    /**
     * Stops a running session. Does not respond to heartbeats.
     * @param sessionId
     * @return 
     */
    public OutcomeResult stopSession(String sessionId) {
        StringBuilder sb = new StringBuilder(CommandName.SESS_STOP.getValue());
        sb.append(" ").append(sessionId);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        cmd.setSessionAddress(addresses.get(Long.parseLong(sessionId)));
        
        return (OutcomeResult) cmd.execute();
    }

    /**
     * Starts a stopped session. Might take a while for sequences ti synchronize.
     * @param sessionId
     * @return 
     */
    public OutcomeResult startSession(String sessionId) {
        StringBuilder sb = new StringBuilder(CommandName.SESS_START.getValue());
        sb.append(" ").append(sessionId);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        cmd.setSessionAddress(addresses.get(Long.parseLong(sessionId)));
        
        return (OutcomeResult) cmd.execute();
    }

    /**
     * Retrieves the statistics for the given session.
     * @param sessionId
     * @return 
     */
    public MultiTableResult getSessionStats(String sessionId) {
        StringBuilder sb = new StringBuilder(CommandName.SESS_STATS.getValue());
        sb.append(" ").append(sessionId);
        Command cmd = Command.getCommand(sb.toString(), session.getMBeanServerConnection());
        cmd.setSessionAddress(addresses.get(Long.parseLong(sessionId)));
        
        return (MultiTableResult) cmd.execute();
    }

    private void refreshSessions(TableResult result) {
        if (result.getSize() == 0) {
            return;
        }
        addresses = new HashMap<Long, SessionAddress>();
        for (int i = 0; i < result.getSize(); i++) {
            List<String> sess = result.getDataRow(i);
            addresses.put(Long.valueOf(sess.get(0)), new SessionAddress(sess.get(1), sess.get(2)));
        }
    }
   
}
