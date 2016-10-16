/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NotificationSequenceGenerator.java
 *
 * $Id$
 */
package net.hades.fix.engine.process.event;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Singleton class generating unique sequence numbers for jmx notifications.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class NotificationSequenceGenerator {

    private static final NotificationSequenceGenerator INSTANCE;
    
    private static AtomicLong sequence = new AtomicLong(new Random(System.currentTimeMillis()).nextLong());
    
    static {
        INSTANCE = new NotificationSequenceGenerator();
    }
    
    public static NotificationSequenceGenerator getInstance() {
        return INSTANCE;
    }
    
    public long getNextSequence() {
        return sequence.getAndIncrement();
    }
}
