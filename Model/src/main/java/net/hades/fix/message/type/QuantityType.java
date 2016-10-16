/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuantityType.java
 *
 * $Id: QuantityType.java,v 1.3 2010-01-14 09:06:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Used for MBS and TIPS Fixed Income security types.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 10/02/2009, 12:00:52 PM
 */
public enum QuantityType {

    SHARES                  (1),
    BONDS                   (2),
    CURRENTFACE             (3),
    ORIGINALFACE            (4),
    CURRENCY                (5),
    CONTRACTS               (6),
    OTHER                   (7),
    PAR                     (8);

    private static final long serialVersionUID = -8169007131681758748L;

    private int value;

    private static final Map<String, QuantityType> stringToEnum = new HashMap<String, QuantityType>();

    static {
        for (QuantityType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuantityType */
    QuantityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuantityType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
