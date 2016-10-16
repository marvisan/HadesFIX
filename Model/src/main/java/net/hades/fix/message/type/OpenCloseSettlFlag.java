/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OpenCloseSettlFlag.java
 *
 * $Id: OpenCloseSettlFlag.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Type of open/close settlement flag.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/07/2009, 12:55:35 PM
 */
public enum OpenCloseSettlFlag {

    DailyOpen                   ('0'),
    SessionOpen                 ('1'),
    DeliverySettlement          ('2'),
    ExpectedEntry               ('3'),
    PrevBusinessDay             ('4'),
    TheoreticalPrice            ('5');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, OpenCloseSettlFlag> stringToEnum = new HashMap<String, OpenCloseSettlFlag>();

    static {
        for (OpenCloseSettlFlag tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of OpenCloseSettlFlag */
    OpenCloseSettlFlag(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static OpenCloseSettlFlag valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
