/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DKReason.java
 *
 * $Id: DKReason.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Execution rejection type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 23/08/2009, 10:51:30 AM
 */
@XmlType
@XmlEnum(String.class)
public enum DKReason {

    @XmlEnumValue("A") UnknownSymbol                ('A'),
    @XmlEnumValue("B") WrongSide                    ('B'),
    @XmlEnumValue("C") QuantityExceedsOrder         ('C'),
    @XmlEnumValue("D") NoMatchingOrder              ('D'),
    @XmlEnumValue("E") PriceExceedsLimit            ('E'),
    @XmlEnumValue("F") CalculationDifference        ('F'),
    @XmlEnumValue("Z") Other                        ('Z');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, DKReason> stringToEnum = new HashMap<String, DKReason>();

    static {
        for (DKReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DKReason */
    DKReason(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static DKReason valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
