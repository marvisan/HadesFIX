/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DiscretionMoveType.java
 *
 * $Id: DiscretionMoveType.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Describes whether discretionary price is static or floats.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/06/2009, 8:40:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum DiscretionMoveType {

    @XmlEnumValue("0")  Floating                (0),
    @XmlEnumValue("1")  Fixed                   (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, DiscretionMoveType> stringToEnum = new HashMap<String, DiscretionMoveType>();

    static {
        for (DiscretionMoveType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DiscretionMoveType */
    DiscretionMoveType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DiscretionMoveType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
