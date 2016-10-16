/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTradingEvent.java
 *
 * $Id: SecurityTradingEvent.java,v 1.1 2011-04-21 09:45:43 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Trading event for a security.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 30/08/2011, 12:35:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SecurityTradingEvent {

    @XmlEnumValue("1") OrderImbalance           (1),
    @XmlEnumValue("2") TradingResumes           (2),
    @XmlEnumValue("3") PriceVolatInter          (3),
    @XmlEnumValue("4") ChangeOfTradingSess      (4),
    @XmlEnumValue("5") ChangeOfTradingSubsess   (5),
    @XmlEnumValue("6") ChangeOfSecTradStatus    (6),
    @XmlEnumValue("7") ChangOfBookType          (7),
    @XmlEnumValue("8") ChangeOfMarketDepth      (8);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SecurityTradingEvent> stringToEnum = new HashMap<String, SecurityTradingEvent>();

    static {
        for (SecurityTradingEvent tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SecurityTradingEvent */
    SecurityTradingEvent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecurityTradingEvent valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
