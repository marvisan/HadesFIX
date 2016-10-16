/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDQuoteType.java
 *
 * $Id: MDQuoteType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of quotes.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/07/2009, 4:42:01 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MDQuoteType {

    @XmlEnumValue("0") Indicative               (0),
    @XmlEnumValue("1") Tradeable                (1),
    @XmlEnumValue("2") RestrictedTradeable      (2),
    @XmlEnumValue("3") Counter                  (3),
    @XmlEnumValue("4") IndicativeAndTradeable   (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MDQuoteType> stringToEnum = new HashMap<String, MDQuoteType>();

    static {
        for (MDQuoteType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MDQuoteType */
    MDQuoteType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MDQuoteType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
