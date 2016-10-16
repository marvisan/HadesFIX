/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SleepWell.java
 *
 * $Id: SleepWell.java,v 1.3 2010-10-13 11:07:01 vrotaru Exp $
 */
package net.hades.fix.commons.thread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread related utilities.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 18/05/2010
 */
public class SleepWell {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final Logger LOGGER = Logger.getLogger(SleepWell.class.getName());

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    private SleepWell() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    /**
     * This methods block until the given thread goes in alive state or interrupted.
     *
     * @param thread thread object
     */
    public static void blockUntilAlive(Thread thread) {
        while (!thread.isAlive()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, "Thread [{0}] has been unexpectedly interrupted.", thread.getName());
                break;
            }
        }
    }

    /**
     * This methods block until the given thread goes in dead state or interrupted.
     *
     * @param thread thread object
     */
    public static void blockUntilDead(Thread thread) {
        while (thread.isAlive()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, "Thread [{0}] has been unexpectedly interrupted.", thread.getName());
                break;
            }
        }
    }

    /**
     * Sleeps the given interval of time given in milliseconds.
     * 
     * @param interval time to sleep in milliseconds
     * @return true if was not interrupted
     */
    public static boolean sleep(long interval) {
        boolean result = true;
        try {
            Thread.sleep(interval);
        } catch (InterruptedException ex) {
            result = false;
        }

        return result;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
