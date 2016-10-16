/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NoSides.java
 *
 * $Id: NoSides.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Number of Side repeating group instances.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 8:00:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum NoSides {

    @XmlEnumValue("1")      OneSide             (1),
    @XmlEnumValue("2")      BothSides           (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, NoSides> stringToEnum = new HashMap<String, NoSides>();

    static {
        for (NoSides tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of NoSides */
    NoSides(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NoSides valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
