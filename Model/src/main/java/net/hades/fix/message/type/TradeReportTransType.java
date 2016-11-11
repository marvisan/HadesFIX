/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * TradeReportTransType.java
 *
 * $Id: TradeReportTransType.java,v 1.3 2010-01-14 09:06:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code for message transaction type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 22/09/2009, 9:05:23 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradeReportTransType {

    @XmlEnumValue("0") New                          (0),
    @XmlEnumValue("1") Cancel                       (1),
    @XmlEnumValue("2") Replace                      (2),
    @XmlEnumValue("3") Release                      (3),
    @XmlEnumValue("4") Reverse                      (4),
    @XmlEnumValue("5") CancelDueToBackOutTrade      (5);

    private static final long serialVersionUID = -8664988761038193755L;

    private int value;

    private static final Map<String, TradeReportTransType> stringToEnum = new HashMap<String, TradeReportTransType>();

    static {
        for (TradeReportTransType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradeReportTransType */
    TradeReportTransType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradeReportTransType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
