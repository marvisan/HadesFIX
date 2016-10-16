/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegSymbolSfx.java
 *
 * $Id: LegSymbolSfx.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * SymbolSfx leg type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 30/10/2008, 7:58:30 PM
 */
public enum LegSymbolSfx {

    CD              ("CD"),
    WI              ("WI");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, LegSymbolSfx> stringToEnum = new HashMap<String, LegSymbolSfx>();

    static {
        for (LegSymbolSfx tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of LegSymbolSfx. */
    LegSymbolSfx(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static LegSymbolSfx valueFor(String value) {
        return stringToEnum.get(value);
    }
}