/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CustomerOrFirm.java
 *
 * $Id: CustomerOrFirm.java,v 1.3 2010-01-14 09:06:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Customer or Frm flag.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 6/07/2008, 16:50:19
 */
public enum CustomerOrFirm {

    Customer            (0),
    Firm                (1);

    private static final long serialVersionUID = -6375266041075637585L;
    
    private int value;

    private static final Map<String, CustomerOrFirm> stringToEnum = new HashMap<String, CustomerOrFirm>();

    static {
        for (CustomerOrFirm tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }
    
    /** Creates a new instance of CustomerOrFirm */
    CustomerOrFirm(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static CustomerOrFirm valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
