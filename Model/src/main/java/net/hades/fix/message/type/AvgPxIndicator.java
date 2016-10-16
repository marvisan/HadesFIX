/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AvgPxIndicator.java
 *
 * $Id: AvgPxIndicator.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Average price indicator.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AvgPxIndicator {

    @XmlEnumValue("0")  NoAveragePricing                    (0),
    @XmlEnumValue("1")  TradePartOfAveragePrice             (1),
    @XmlEnumValue("2")  LastTradeAveragePriceGroup          (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AvgPxIndicator> stringToEnum = new HashMap<String, AvgPxIndicator>();

    static {
        for (AvgPxIndicator tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AvgPxIndicator */
    AvgPxIndicator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AvgPxIndicator valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
