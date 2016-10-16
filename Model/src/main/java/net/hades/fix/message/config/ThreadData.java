/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ThreadData.java
 *
 * $Id: ThreadData.java,v 1.3 2010-11-20 06:37:18 vrotaru Exp $
 */
package net.hades.fix.message.config;

/**
 * Utility class used to store thread local data used by the message encoding/decoding.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 */
public class ThreadData {
    
    private static SessionContextContainer contextContainer = new SessionContextContainer();

    private static class SessionContextContainer extends ThreadLocal<SessionContext> {

        @Override
        public SessionContext initialValue() {
            return new SessionContext();
        }
    }

    public static SessionContext getSessionContext() {
        return contextContainer.get();
    }
}
