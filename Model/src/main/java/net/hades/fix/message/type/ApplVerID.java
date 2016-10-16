/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ApplVerID.java
 *
 * $Id: ApplVerID.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Specifies the service pack release being applied at message level..
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 19/10/2008, 15:55:07
 */
public enum ApplVerID {

    FIX27           ("0"),
    FIX30           ("1"),
    FIX40           ("2"),
    FIX41           ("3"),
    FIX42           ("4"),
    FIX43           ("5"),
    FIX44           ("6"),
    FIX50           ("7"),
    FIX50SP1        ("8"),
    FIX50SP2        ("9");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, ApplVerID> stringToEnum = new HashMap<String, ApplVerID>();

    static {
        for (ApplVerID tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of AdvSide. */
    ApplVerID(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static ApplVerID valueFor(String value) {
        return stringToEnum.get(value);
    }
}
