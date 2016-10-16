/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListRequestType.java
 *
 * $Id: SecurityListRequestType.java,v 1.4 2011-04-27 03:47:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the type/criteria of Security List Request.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 8:06:02 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SecurityListRequestType {

    @XmlEnumValue("0")      Symbol                  (0),
    @XmlEnumValue("1")      SecurityType            (1),
    @XmlEnumValue("2")      Product                 (2),
    @XmlEnumValue("3")      TradingSessionID        (3),
    @XmlEnumValue("4")      AllSecurities           (4),
    @XmlEnumValue("5")      MarketID                (5),;

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SecurityListRequestType> stringToEnum = new HashMap<String, SecurityListRequestType>();

    static {
        for (SecurityListRequestType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SecurityListRequestType */
    SecurityListRequestType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecurityListRequestType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
