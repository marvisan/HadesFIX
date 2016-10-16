/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Coordinable.java
 *
 * $Id: Coordinable.java,v 1.3 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.session;

import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.process.Reportable;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.event.EventProcessor;
import net.hades.fix.engine.process.protocol.Protocol;

import java.util.List;

import net.hades.fix.engine.Engine;
import net.hades.fix.engine.config.model.HandlerDefInfo;
import net.hades.fix.engine.model.SessionAddress;

/**
 * Restricted interface available to Handler developers.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 */
public interface Coordinable extends Reportable {

    /**
     * Returns a list of singleton handlers.
     * @return handlers
     */
    List<Handler> getSingletonHandlers();

    /**
     * Returns a list of handlers definitions.
     * @return handler defs
     */
    HandlerDefInfo[] getHandlerDefs();

    /**
     * Local/Remote session address.
     * @return session address
     */
    SessionAddress getSessionAddress();

    /**
     * Retrieve a reference to the engine running this session coordinator.
     * @return current engine
     */
    Engine getEngine();

    /**
     * Returns the session running protocol server.
     * @return protocol attached to the session
     */
    Protocol getProtocol();
    
    /**
     * Gets the session coordinator event processor.
     * @return  event processor
     */
    EventProcessor getEventProcessor();
    
    /**
     * Executes asynchronously a command.
     * @param command executes a command
     */
    void execute(Command command);
}
