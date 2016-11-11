/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * CommandName.java
 *
 * $Id: CommandName.java,v 1.4 2011-03-30 10:59:25 vrotaru Exp $
 */
package net.hades.fix.admin.cmdline;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandName names used by the command line utility.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 08/05/2010
 */
public enum CommandName {

    LIST_SESS           ("seslst", "[<session_id>] List all/specified configured sessions summary."),
    SET_TX_SEQ          ("sesstx", "<session_id> <new_seq_num> : Sets the outgoing messages expected sequence."),
    SET_RX_SEQ          ("sessrx", "<session_id> <new_seq_num> : Sets the incoming messages expected sequence."),
    SESS_FREEZE         ("sesfrz", "<session_id> : Freeze the session."),
    SESS_THAW           ("sesthw", "<session_id> : Thaw the session."),
    SESS_STOP           ("seskil", "<session_id> : Stops the session and destroys the existing session data."),
    SESS_START          ("sesrun", "<session_id> : Starts the session again recreating session data."),
    SESS_CONN           ("sescon", "<session_id> : Attemts to connect transport of a stopped session."),
    SESS_DISC           ("sesdis", "<session_id> : Attemts to disconnect transport of an running session."),
    SESS_RESET          ("sesrst", "<session_id> : Attemts to reset session sequence numbers by sending a Logon message with ResetSeqNumFlag set to true."),
    SESS_STATS          ("sessta", "<session_id> : Gets the statistics for a given session."),
    SEQ_RESET           ("seqrst", "<session_id> <new_seq_num> : Send a sequence reset message to the counterparty."),
    SERV_SHUTDOWN       ("srvdwn", "Shutdown the server."),
    SERV_SHUTDOWN_NOW   ("srvkil", "Shutdown immediate the server (may result in message loss)."),
    HELP                ("help", "This help text describing the available commands."),
    BYE                 ("bye", "Exits the fix engine administration command utility."),
    QUIT                ("quit", "Exits the fix engine administration command utility."),
    EXIT                ("exit", "Exits the fix engine administration command utility.");

    private static final long serialVersionUID = 1L;
    
    private static final Map<String, CommandName> stringToEnum = new HashMap<String, CommandName>();

    static {
        for (CommandName tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    private String value;
    private String help;

    CommandName(String name, String help) {
        this.value = name;
        this.help = help;
    }

    public String getHelp() {
        return help;
    }

    public String getValue() {
        return value;
    }

    public static CommandName valueFor(String value) {
        return stringToEnum.get(value);
    }
}
