/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * SecurityRequestType.java
 *
 * $Id: SecurityRequestType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Security Definition Request.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/09/2009, 9:11:12 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SecurityRequestType {

    @XmlEnumValue("0")      ReqSecIdAndSpec             (0),
    @XmlEnumValue("1")      ReqSecIdForSpec             (1),
    @XmlEnumValue("2")      ReqListSecTypes             (2),
    @XmlEnumValue("3")      ReqListSec                  (3),
    @XmlEnumValue("4")      Symbol                      (4),
    @XmlEnumValue("5")      SecTypeCFICode              (5),
    @XmlEnumValue("6")      Product                     (6),
    @XmlEnumValue("7")      TradingSessionID            (7),
    @XmlEnumValue("8")      AllSecurities               (8),
    @XmlEnumValue("9")      MarketID                    (9);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SecurityRequestType> stringToEnum = new HashMap<String, SecurityRequestType>();

    static {
        for (SecurityRequestType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SecurityRequestType */
    SecurityRequestType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecurityRequestType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
