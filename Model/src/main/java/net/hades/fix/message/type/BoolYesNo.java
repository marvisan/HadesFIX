/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BoolYesNo.java
 *
 * $Id: BoolYesNo.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Boolean values as Y/N strings.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 6/07/2008, 15:46:36
 */
public enum BoolYesNo {

    Yes         ("Y"),
    No          ("N");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, BoolYesNo> stringToEnum = new HashMap<String, BoolYesNo>();

    static {
        for (BoolYesNo tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of BoolYesNo */
    BoolYesNo(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static BoolYesNo valueFor(String value) {
        return stringToEnum.get(value);
    }
}
