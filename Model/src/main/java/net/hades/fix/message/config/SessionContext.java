/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionContext.java
 *
 * $Id: SessionContext.java,v 1.4 2010-11-20 06:37:18 vrotaru Exp $
 */
package net.hades.fix.message.config;

import java.util.EnumMap;

/**
 * Holder of configuration data stored in the thread local. It is set by the FIX
 * session and used by the message encoders/decoders.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 25/08/2008, 19:23:57
 */
public class SessionContext {

    private EnumMap<SessionContextKey, Object> values;

    public SessionContext() {
        values = new EnumMap<SessionContextKey, Object>(SessionContextKey.class);
    }

    public Object getValue(SessionContextKey key) {
        return values.get(key);
    }
    
    public void setValue(SessionContextKey key, Object value) {
        values.put(key, value);
    }
    
    public void removeValue(SessionContextKey key) {
        values.remove(key);
    }

    public void clear() {
        values.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SessionContext {");
        sb.append("\n");
        for (SessionContextKey key : values.keySet()) {
            sb.append(key.getValue()).append("=").append(values.get(key)).append("\n");
        }
        sb.append("}");

        return sb.toString();
    }

}
