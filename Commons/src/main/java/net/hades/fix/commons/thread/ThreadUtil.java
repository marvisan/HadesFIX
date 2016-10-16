/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ThreadUtil.java
 *
 * $Id: ThreadUtil.java,v 1.1 2011-04-12 22:25:47 vrotaru Exp $
 */
package net.hades.fix.commons.thread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread related utilities.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class ThreadUtil {

    private static final Logger LOGGER = Logger.getLogger(ThreadUtil.class.getName());

    private ThreadUtil() {
    }

    /**
     * This methods block until the given thread goes in alive state or interrupted.
     * @param thread thread object
     */
    public static void blockUntilAlive(Thread thread) {
        if (thread == null) {
            return;
        }
        while (!thread.isAlive()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, "Thread [{0}] has been unexpectedly interrupted.", Thread.currentThread().getName());
                break;
            }
        }
    }

    public static void blockUntilDead(Thread thread) {
        if (thread == null) {
            return;
        }
        while (thread.isAlive()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, "Thread [{0}] has been unexpectedly interrupted.", Thread.currentThread().getName());
                break;
            }
        }
    }

    /**
     * Sleeps the given interval of time given in milliseconds.
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
}
