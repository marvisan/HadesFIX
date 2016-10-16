/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Streamable.java
 *
 * $Id: Streamable.java,v 1.5 2011-03-21 04:54:25 vrotaru Exp $
 */
package net.hades.fix.engine.process.stream;

import net.hades.fix.engine.mgmt.data.Stats;
import net.hades.fix.engine.process.session.Coordinable;

/**
 * Restricted interface available to Handler developers.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 */
public interface Streamable {
    /**
     * Return the session coordinator instance in which this stream runs.
     * @return session coordinator
     */
    Coordinable getSessionCoordinator();

    /**
     * Return the statistics container for this stream.
     * @return statistics for the stream
     */
    Stats getStats();
}
