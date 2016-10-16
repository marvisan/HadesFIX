/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Flowable.java
 *
 * $Id: Flowable.java,v 1.4 2010-11-13 08:23:05 vrotaru Exp $
 */
package net.hades.fix.engine.process.stream;

/**
 * Restricted interface available to the Handler developers.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 */
public interface Flowable {
    /**
     * Gets the Flow identifier.
     * @return flow identifier
     */
    String getId();

    /**
     * Gets the stream in which the flow is running.
     * @return flow stream
     */
    Streamable getStream();
}
