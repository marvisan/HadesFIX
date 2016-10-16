/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeRequestType.java
 *
 * $Id: TradeRequestType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Trade Capture Report.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/09/2009, 8:33:20 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradeRequestType {

    @XmlEnumValue("0")      AllTrades               (0),
    @XmlEnumValue("1")      MatchedTrades           (1),
    @XmlEnumValue("2")      UnmatchedTrades         (2),
    @XmlEnumValue("3")      UnreportedTrades        (3),
    @XmlEnumValue("4")      Advisories              (4);

    private static final long serialVersionUID = -3489342349775861936L;

    private int value;

    private static final Map<String, TradeRequestType> stringToEnum = new HashMap<String, TradeRequestType>();

    static {
        for (TradeRequestType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradeRequestType */
    TradeRequestType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradeRequestType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
