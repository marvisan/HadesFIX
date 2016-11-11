/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PossResend.java
 *
 * $Id: PossResend.java,v 1.3 2010-01-14 09:06:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Possible resend.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 5/07/2008, 19:39:35
 */
public enum PossResend {

    PossibleResend              ("Y"),
    OriginalTransmission        ("N");

    private static final long serialVersionUID = -344420785743718816L;
    
    private String value;

    private static final Map<String, PossResend> stringToEnum = new HashMap<String, PossResend>();

    static {
        for (PossResend tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of PossResend */
    PossResend(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static PossResend valueFor(String value) {
        return stringToEnum.get(value);
    }
}
