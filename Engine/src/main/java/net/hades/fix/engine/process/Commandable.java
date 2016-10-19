/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Commandable.java
 *
 * $Id$
 */
package net.hades.fix.engine.process;

import net.hades.fix.engine.process.command.Command;

/**
 * Generic interface used to execute async management commands.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public interface Commandable {
    /**
     * Execute an async command
     * @param command command to execute
     */
    void execute(Command command) ;
}
