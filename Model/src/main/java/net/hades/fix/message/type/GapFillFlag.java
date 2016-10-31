/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * GapFillFlag.java
 *
 * $Id: GapFillFlag.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Gap filling message flag.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 6/07/2008, 15:55:07
 */
public enum GapFillFlag {

    GapFillMessage          ("Y"),
    SequenceReset           ("N");

    private static final long serialVersionUID = -7629027156983855843L;
    
    private String value;

    private static final Map<String, GapFillFlag> stringToEnum = new HashMap<String, GapFillFlag>();

    static {
        for (GapFillFlag tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of GapFillFlag */
    GapFillFlag(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static GapFillFlag valueFor(String value) {
        return stringToEnum.get(value);
    }
}
