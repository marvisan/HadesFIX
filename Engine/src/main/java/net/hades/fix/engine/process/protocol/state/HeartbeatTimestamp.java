/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HeartbeatTimestamp.java
 *
 * $Id: HeartbeatTimestamp.java,v 1.3 2011-04-03 08:00:05 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

/**
 * Holds last heartbeat timestamp.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 */
public class HeartbeatTimestamp {

    private long lastHtbtTimestamp ;

    public HeartbeatTimestamp() {
        lastHtbtTimestamp = System.currentTimeMillis();
    }

    public synchronized void setTimestamp(long htbtTimestamp) {
        this.lastHtbtTimestamp = htbtTimestamp;
    }
    
    public synchronized long getTimestamp() {
        return lastHtbtTimestamp;
    }
}
