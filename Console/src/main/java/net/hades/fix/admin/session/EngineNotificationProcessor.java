/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineNotificationProcessor.java
 *
 * $Id$
 */
package net.hades.fix.admin.session;

import net.hades.fix.admin.gui.model.AlertTableModel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Processor thread for engine notifications.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class EngineNotificationProcessor implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(EngineNotificationProcessor.class.getName());
    public static final String CMD_EXIT = "exit";
    
    private BlockingQueue<String> commandQueue;
    private AlertTableModel alertsTableModel;
    private volatile boolean exit;
    
    public EngineNotificationProcessor() {
        commandQueue = new ArrayBlockingQueue<String>(5);
        alertsTableModel = new AlertTableModel();
    }
    
    @Override
    public void run() {
        while (!exit) {
            try {
                String command = commandQueue.take();
                if (CMD_EXIT.equals(command)) {
                    LOGGER.info("Shutting down NotificationProcessor...");
                    
                    exit = true;
                }
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "NotificationProcessor thread has been interrupted. Shutting down NotificationProcessor...");
                exit = true;
            }
        }
        
        LOGGER.info("NotificationProcessor shut down successfully.");
    }
    
    public void executeCommand(String command) {
        commandQueue.add(command);
    }

    public AlertTableModel getAlertsTableModel() {
        return alertsTableModel;
    }
}
