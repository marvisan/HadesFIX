/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PriceLimitType.java
 *
 * $Id: PriceLimitType.java,v 1.1 2011-04-17 09:30:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Price limits.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 30/08/2009, 12:35:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PriceLimitType {

    @XmlEnumValue("0") Price            (0),
    @XmlEnumValue("1") Ticks            (1),
    @XmlEnumValue("2") Percentage       (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PriceLimitType> stringToEnum = new HashMap<String, PriceLimitType>();

    static {
        for (PriceLimitType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PriceLimitType */
    PriceLimitType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PriceLimitType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
