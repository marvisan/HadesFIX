/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PegOffsetType.java
 *
 * $Id: PegOffsetType.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Peg Offset value.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/06/2009, 8:40:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PegOffsetType {

    @XmlEnumValue("0")  Price                   (0),
    @XmlEnumValue("1")  BasisPoints             (1),
    @XmlEnumValue("2")  Ticks                   (2),
    @XmlEnumValue("3")  PriceTier               (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PegOffsetType> stringToEnum = new HashMap<String, PegOffsetType>();

    static {
        for (PegOffsetType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PegOffsetType */
    PegOffsetType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PegOffsetType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
