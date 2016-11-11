/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineEventListener.java
 *
 * $Id$
 */
package net.hades.fix.admin.session;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Notification;
import javax.management.NotificationListener;

import net.hades.fix.admin.gui.config.model.EngineConnectionInfo;
import net.hades.fix.admin.gui.model.AlertEvent;
import javax.management.remote.JMXConnectionNotification;

/**
 * Handle alerts, life-cycle events and message events.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class EngineEventListener implements NotificationListener {
    
    private static final Logger LOGGER = Logger.getLogger(EngineEventListener.class.getName());
    
    public static final String ALERT_NOTIFICATION = "hadesfix.alert";
    public static final String LIFECYCLE_NOTIFICATION = "hadesfix.lifecycle";
    public static final String MESSAGE_NOTIFICATION = "hadesfix.message";
    
    private EngineSessionWorker engineSessionWorker;
    private EngineConnectionInfo connectionInfo;

    public EngineEventListener(EngineSessionWorker engineSessionWorker, EngineConnectionInfo connectionInfo) {
        this.engineSessionWorker = engineSessionWorker;
        this.connectionInfo = connectionInfo;
    }

    
    @Override
    public void handleNotification(Notification notification, Object handback) {
        if (notification.getType().equals(ALERT_NOTIFICATION)) {
            handleAlertEvent(notification);
        } else if (notification.getType().equals(LIFECYCLE_NOTIFICATION)) {
            handleLifeCycle(notification);
        } else if (notification.getType().equals(MESSAGE_NOTIFICATION)) {
            handleMessage(notification);
        } else if (notification.getType().equals(JMXConnectionNotification.OPENED)) {
            handleJmxConnOpen(notification);
        } else if (notification.getType().equals(JMXConnectionNotification.CLOSED)) {
            handleJmxConnClosed(notification);
        } else if (notification.getType().equals(JMXConnectionNotification.FAILED)) {
            handleJmxConnFailed(notification);
        } else if (notification.getType().equals(JMXConnectionNotification.NOTIFS_LOST)) {
            handleJmxConnNotifLost(notification);
        }
    }

    private void handleAlertEvent(Notification notification) {
        AlertEvent alert = new AlertEvent();
        alert.setId(String.valueOf(notification.getSequenceNumber()));
        alert.setTime((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date(notification.getTimeStamp())));
        @SuppressWarnings("unchecked")
        Map<String, String> userData = (Map<String, String>) notification.getUserData();
        alert.setSession(userData.get("session"));
        alert.setComponent(userData.get("component"));
        alert.setCode(userData.get("code"));
        alert.setMessage(userData.get("message"));
        alert.setType(userData.get("severity"));
        alert.setErrorMessage(userData.get("errorMessage"));
        alert.setErrorStack(userData.get("errorStack"));
        alert.setDisplayColor(connectionInfo.getDisplayColor());
        
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "ALERT : {0}", alert.toString());
        }
        
        engineSessionWorker.getEngineNotificationProcessor().getAlertsTableModel().addAlert(alert);
    }

    private void handleLifeCycle(Notification notification) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleMessage(Notification notification) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleJmxConnOpen(Notification notification) {
        LOGGER.log(Level.INFO, "JMX connection open: {0}", notification.getMessage());
    }

    private void handleJmxConnClosed(Notification notification) {
        LOGGER.log(Level.INFO, "JMX connection closed: {0}", notification.getMessage());
        engineSessionWorker.executeCommand(EngineSessionWorker.CMD_EXIT);
    }

    private void handleJmxConnFailed(Notification notification) {
        LOGGER.log(Level.SEVERE, "JMX connection failed: {0}", notification.getMessage());
        engineSessionWorker.executeCommand(EngineSessionWorker.CMD_EXIT);
    }

    private void handleJmxConnNotifLost(Notification notification) {
        LOGGER.log(Level.INFO, "JMX connection notification lost: {0}", notification.getMessage());
        engineSessionWorker.executeCommand(EngineSessionWorker.CMD_EXIT);
    }

}
