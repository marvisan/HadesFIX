/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * SettlPriceType.java
 *
 * $Id: SettlPriceType.java,v 1.1 2011-01-03 09:19:35 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of settlement price.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SettlPriceType {

    @XmlEnumValue("1")      Final               (1),
    @XmlEnumValue("2")      Theoretical         (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SettlPriceType> stringToEnum = new HashMap<String, SettlPriceType>();

    static {
        for (SettlPriceType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlPriceType */
    SettlPriceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SettlPriceType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
