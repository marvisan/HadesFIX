/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListType.java
 *
 * $Id: SecurityListType.java,v 1.1 2011-04-27 03:47:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of security list.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 30/08/2009, 12:35:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SecurityListType {

    @XmlEnumValue("1") IndustryClassification       (1),
    @XmlEnumValue("2") TradingList                  (2),
    @XmlEnumValue("3") Market                       (3),
    @XmlEnumValue("4") NewspaperList                (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SecurityListType> stringToEnum = new HashMap<String, SecurityListType>();

    static {
        for (SecurityListType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SecurityListType */
    SecurityListType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecurityListType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
