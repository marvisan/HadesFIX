/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionID.java
 *
 * $Id: TradingSessionID.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Standard values for an identifier for Trading Session.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 17/06/2009, 11:04:42 AM
 */
@XmlType
@XmlEnum(String.class)
public enum TradingSessionID {

    @XmlEnumValue("1") Day              ("1"),
    @XmlEnumValue("2") HalfDay          ("2"),
    @XmlEnumValue("3") Morning          ("3"),
    @XmlEnumValue("4") Afternoon        ("4"),
    @XmlEnumValue("5") Evening          ("5"),
    @XmlEnumValue("6") AfterHours       ("6");

    private static final long serialVersionUID = -2777505174455766016L;

    private String value;

    private static final Map<String, TradingSessionID> stringToEnum = new HashMap<String, TradingSessionID>();

    static {
        for (TradingSessionID tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of TradingSessionID */
    TradingSessionID(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TradingSessionID valueFor(String value) {
        return stringToEnum.get(value);
    }
}
