/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineEventListener.java
 *
 * $Id$
 */
package net.hades.fix.admin.session;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.remote.JMXConnectionNotification;

/**
 * Handle jmx connection alerts.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class ConnectionEventListener implements NotificationListener {
    
    private static final Logger LOGGER = Logger.getLogger(ConnectionEventListener.class.getName());
    
    private EngineSessionWorker engineSessionWorker;

    public ConnectionEventListener(EngineSessionWorker engineSessionWorker) {
        this.engineSessionWorker = engineSessionWorker;
    }

    
    @Override
    public void handleNotification(Notification notification, Object handback) {
        if (notification.getType().equals(JMXConnectionNotification.OPENED)) {
            handleJmxConnOpen(notification);
        } else if (notification.getType().equals(JMXConnectionNotification.CLOSED)) {
            handleJmxConnClosed(notification);
        } else if (notification.getType().equals(JMXConnectionNotification.FAILED)) {
            handleJmxConnFailed(notification);
        } else if (notification.getType().equals(JMXConnectionNotification.NOTIFS_LOST)) {
            handleJmxConnNotifLost(notification);
        }
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
