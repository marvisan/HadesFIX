/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * StatsType.java
 *
 * $Id: StatsType.java,v 1.3 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of statistics.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 22/08/2009, 9:17:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum StatsType {

    @XmlEnumValue("1") ExchangeLast             (1),
    @XmlEnumValue("2") HighLowPrice             (2),
    @XmlEnumValue("3") AveragePrice             (3),
    @XmlEnumValue("4") Turnover                 (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, StatsType> stringToEnum = new HashMap<String, StatsType>();

    static {
        for (StatsType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of StatsType */
    StatsType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StatsType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
