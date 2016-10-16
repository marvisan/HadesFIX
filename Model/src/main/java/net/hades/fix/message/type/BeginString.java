/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BeginString.java
 *
 * $Id: BeginString.java,v 1.7 2010-11-17 10:55:05 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * FIX version.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 6/07/2008, 10:18:18
 */
public enum BeginString {

    FIX_4_0                 ("FIX.4.0"),
    FIX_4_1                 ("FIX.4.1"),
    FIX_4_2                 ("FIX.4.2"),
    FIX_4_3                 ("FIX.4.3"),
    FIX_4_4                 ("FIX.4.4"),
    FIX_5_0                 ("FIX.5.0"),            // Non standard value used by some engines (e.g. CameronFIX)
    FIX_5_0SP1              ("FIX.5.0SP1"),         // Non standard value used by some engines (e.g. CameronFIX)
    FIX_5_0SP2              ("FIX.5.0SP2"),         // Non standard value used by some engines (e.g. CameronFIX)
    FIXT_1_1                ("FIXT.1.1");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, BeginString> stringToEnum = new HashMap<String, BeginString>();

    static {
        for (BeginString tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of BeginString */
    BeginString(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static BeginString valueFor(String value) {
        return stringToEnum.get(value);
    }
}
