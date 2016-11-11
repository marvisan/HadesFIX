/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsRefType.java
 *
 * $Id: NewsRefType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Reference to another News message type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 18/06/2009, 7:45:07 PM
 */
public enum NewsRefType {

    Replacement             (0),
    OtherLanguage           (1),
    Complimentary           (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, NewsRefType> stringToEnum = new HashMap<String, NewsRefType>();

    static {
        for (NewsRefType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of NewsRefType */
    NewsRefType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NewsRefType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
