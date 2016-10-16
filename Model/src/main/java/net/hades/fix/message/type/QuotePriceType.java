/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuotePriceType.java
 *
 * $Id: QuotePriceType.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent price type requested in Quote.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/04/2009, 11:02:05 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum QuotePriceType {

    @XmlEnumValue("1")  Percentage                          (1),
    @XmlEnumValue("2")  PerShare                            (2),
    @XmlEnumValue("3")  FixedAmount                         (3),
    @XmlEnumValue("4")  Discount                            (4),
    @XmlEnumValue("5")  Premium                             (5),
    @XmlEnumValue("6")  Spread                              (6),
    @XmlEnumValue("7")  TEDPrice                            (7),
    @XmlEnumValue("8")  TEDYield                            (8),
    @XmlEnumValue("9")  YieldSpead                          (9),
    @XmlEnumValue("10") Yield                               (10);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, QuotePriceType> stringToEnum = new HashMap<String, QuotePriceType>();

    static {
        for (QuotePriceType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuotePriceType */
    QuotePriceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuotePriceType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
