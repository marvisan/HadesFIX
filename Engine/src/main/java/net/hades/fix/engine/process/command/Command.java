/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Command.java
 *
 * $Id: Command.java,v 1.1 2011-04-07 09:57:51 vrotaru Exp $
 */
package net.hades.fix.engine.process.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Process command send by another thread.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public final class Command {

    public static final String PARAM_SERVER_TRANSPORT_WORKER = "ServerTransportWorker";
    public static final String PARAM_SESSION_ADDRESS = "SessionAddress";
    public static final String PARAM_RECONNECT_DELAY = "ReconnectDelay";

    private CommandType commandType;
    
    private Map<String, Object> parameters;

    public Command(CommandType commandType) {
        this.commandType = commandType;
        parameters = new HashMap<String, Object>();
    }

    public Command(CommandType commandType, String name, Object value) {
        this(commandType);
        addParameter(name, value);
    }

    public CommandType getCommandType() {
        return commandType;
    }
    
    public void addParameter(String name, Object value) {
        parameters.put(name, value);
    }

    public Object getParameter(String name) {
        return parameters.get(name);
    }
}
