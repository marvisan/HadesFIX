/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DumpProcess.java
 *
 * $Id: DumpProcess.java,v 1.2 2010-10-10 09:32:27 vrotaru Exp $
 */
package net.hades.fix.engine.handler.dump;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ArrayBlockingQueue;

import net.hades.fix.engine.config.ConfigurationException;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.MessageEvent;
import net.hades.fix.engine.mgmt.alert.SystemAlerts;

/**
 * Test dump print process.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 13/07/2009, 10:43:12 AM
 */
public class DumpProcess {

    private static final Logger LOGGER = Logger.getLogger(DumpProcess.class.getName());

    private BlockingQueue<byte[]> txQueue;

    private BlockingQueue<byte[]> rxQueue;

    private boolean shutdown;

    public DumpProcess() {
        txQueue = new ArrayBlockingQueue<byte[]>(10);
        rxQueue = new ArrayBlockingQueue<byte[]>(10);
    }

    
    public void run() {
        while (!shutdown) {
            try {
                System.out.println("RX ---> " + new String(rxQueue.take()));
                System.out.println("TX ---> " + new String(txQueue.take()));
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.INFO, "Thread interrupted  : {0}", ex.toString());
            }
        }
        LOGGER.info("DumpProcess EXIT");
    }
    
    
    public void setConfigData(Map<String, String> configData) throws ConfigurationException {
    }

    
    public synchronized void shutdown() {
        shutdown = true;
    }

    
    public void onLifeCycleEvent(LifeCycleEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public SystemAlerts getAlerts() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void onRequestData(MessageEvent data) {
//        rxQueue.offer(data.getEncodedMessage());
    }

    
    public void onResponseData(MessageEvent data) {
//        if (data.getEncodedMessage() != null) {
//            try {
//                txQueue.put(data.getEncodedMessage());
//            } catch (InterruptedException ex) {
//                LOGGER.finest("TX queue interrupted : " + ex.toString());
//            }
//        }
    }

    
    public void startup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void restart() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void block() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void unblock() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void shutdownNow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
