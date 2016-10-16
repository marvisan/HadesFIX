/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Filter.java
 *
 * $Id: Filter.java,v 1.1 2010-11-13 08:23:05 vrotaru Exp $
 */
package net.hades.fix.engine.process.stream;

import net.hades.fix.message.Message;

/**
 * Contract to be fulfilled by a message filter.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public interface Filter {

    /**
     * Determine if a message is allowed to enter a flow or not.
     * 
     * @param message message to be processed
     * @return true if the message is allowed to enter the flow, false otherwise
     */
    boolean allow(Message message);
}
