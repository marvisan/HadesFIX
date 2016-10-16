/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionAddressHolder.java
 *
 * $Id: SessionAddressHolder.java,v 1.7 2011-04-01 05:04:23 vrotaru Exp $
 */
package net.hades.fix.admin.console.data;

import net.hades.fix.admin.cmdline.CommandName;
import net.hades.fix.admin.command.Command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


/**
 * Holder of the current sessions address.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 14/05/2010
 */
public class SessionAddressHolder {

    private static final Logger LOGGER = Logger.getLogger(SessionAddressHolder.class.getName());

    private static final SessionAddressHolder INSTANCE;

    static {
        INSTANCE = new SessionAddressHolder();
    }

    private Map<Long, SessionAddress> addresses;

    private SessionAddressHolder() {
        addresses = new ConcurrentHashMap<Long, SessionAddress>();
    }

    public static SessionAddressHolder getInstance() {
        return INSTANCE;
    }

    public void addSessionAddress(Long id, SessionAddress addr) {
        addresses.put(id, addr);
    }

    public void addSessionAddress(Long id, String cptyAddr, String sessAddr) {
        SessionAddress addr = new SessionAddress(cptyAddr, sessAddr);
        addresses.put(id, addr);
    }

    public SessionAddress getSessionAddress(Long id) {
        if (addresses.isEmpty()) {
            Command cmd = Command.getCommand(CommandName.LIST_SESS.getValue());
            cmd.execute();
        }
        
        return addresses.get(id);
    }

    public boolean checkAddressExists(SessionAddress addr) {
        boolean result = false;
        for (SessionAddress address : addresses.values()) {
            if (addr.getCptyAddress().equals(address.getCptyAddress()) && addr.getSessAdrress().equals(address.getSessAdrress())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
