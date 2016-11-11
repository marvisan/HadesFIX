/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Scope.java
 *
 * $Id: Scope.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Scope of a market data entry.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/07/2009, 1:03:06 PM
 */
public enum Scope {

    LocalMarket                 ('1'),
    National                    ('2'),
    Global                      ('3');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, Scope> stringToEnum = new HashMap<String, Scope>();

    static {
        for (Scope tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of Scope */
    Scope(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static Scope valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
