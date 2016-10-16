/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderDelayUnit.java
 *
 * $Id: OrderDelayUnit.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Order delay time unit.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum OrderDelayUnit {

    @XmlEnumValue("0")      Seconds                             (0),
    @XmlEnumValue("1")      TenthsOfASecond                     (1),
    @XmlEnumValue("2")      HundredthsOfASecond                 (3),
    @XmlEnumValue("3")      Milliseconds                        (2),
    @XmlEnumValue("4")      Microseconds                        (4),
    @XmlEnumValue("5")      Nanoseconds                         (5),
    @XmlEnumValue("10")     Minutes                             (10),
    @XmlEnumValue("11")     Hours                               (11),
    @XmlEnumValue("12")     Days                                (12),
    @XmlEnumValue("13")     Weeks                               (13),
    @XmlEnumValue("14")     Months                              (14),
    @XmlEnumValue("15")     Years                               (15);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, OrderDelayUnit> stringToEnum = new HashMap<String, OrderDelayUnit>();

    static {
        for (OrderDelayUnit tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of OrderDelayUnit */
    OrderDelayUnit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderDelayUnit valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
