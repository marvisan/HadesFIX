/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossPrioritization.java
 *
 * $Id: CrossPrioritization.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates if one side or the other of a cross order should be prioritized.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/09/2009, 7:55:32 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CrossPrioritization {

    @XmlEnumValue("0")      None                        (0),
    @XmlEnumValue("1")      BuySidePrioritized          (1),
    @XmlEnumValue("2")      SellSidePrioritized         (2);

    private static final long serialVersionUID = -8986233818230104191L;

    private int value;

    private static final Map<String, CrossPrioritization> stringToEnum = new HashMap<String, CrossPrioritization>();

    static {
        for (CrossPrioritization tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CrossPrioritization */
    CrossPrioritization(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CrossPrioritization valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
