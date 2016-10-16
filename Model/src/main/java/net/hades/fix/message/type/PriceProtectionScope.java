/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PriceProtectionScope.java
 *
 * $Id: PriceProtectionScope.java,v 1.2 2011-01-15 02:10:11 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines the type of price protection the customer requires on their order.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum PriceProtectionScope {

    @XmlEnumValue("0") None                ('0'),
    @XmlEnumValue("1") LocalExchange       ('1'),
    @XmlEnumValue("2") National            ('2'),
    @XmlEnumValue("3") Global              ('3');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, PriceProtectionScope> stringToEnum = new HashMap<String, PriceProtectionScope>();

    static {
        for (PriceProtectionScope tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of Benchmark */
    PriceProtectionScope(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static PriceProtectionScope valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
