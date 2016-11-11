/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegSwapType.java
 *
 * $Id: LegSwapType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Leg swap type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 03/04/2009, 10:44:15 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum LegSwapType {

    @XmlEnumValue("1") ParForPar                (1),
    @XmlEnumValue("2") ModifiedDuration         (2),
    @XmlEnumValue("4") Risk                     (4),
    @XmlEnumValue("5") Proceeds                 (5);

    private static final long serialVersionUID = -6898148718368149560L;

    private int value;

    private static final Map<String, LegSwapType> stringToEnum = new HashMap<String, LegSwapType>();

    static {
        for (LegSwapType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }


    /** Creates a new instance of LegSwapType */
    LegSwapType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LegSwapType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
