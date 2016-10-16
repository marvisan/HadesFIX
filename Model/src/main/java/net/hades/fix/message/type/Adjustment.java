/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Adjustment.java
 *
 * $Id: Adjustment.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of adjustment.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 2:39:48 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum Adjustment {

    @XmlEnumValue("1")      Cancel              (1),
    @XmlEnumValue("2")      Error               (2),
    @XmlEnumValue("3")      Correction          (3);

    private static final long serialVersionUID = -5256125441884754259L;

    private int value;

    private static final Map<String, Adjustment> stringToEnum = new HashMap<String, Adjustment>();

    static {
        for (Adjustment tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of Adjustment */
    Adjustment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Adjustment valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
