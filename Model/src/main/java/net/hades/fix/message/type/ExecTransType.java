/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecTransType.java
 *
 * $Id: ExecTransType.java,v 1.4 2010-02-25 08:37:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Execution transaction type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 29/06/2008, 18:29:57
 */
public enum ExecTransType {

    New         ("0"),
    Cancel      ("1"),
    Correct     ("2"),
    Status      ("3");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, ExecTransType> stringToEnum = new HashMap<String, ExecTransType>();

    static {
        for (ExecTransType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of ExecTransType */
    ExecTransType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static ExecTransType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
