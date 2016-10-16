/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MaturityMonthYearIncrementUnits.java
 *
 * $Id: MaturityMonthYearIncrementUnits.java,v 1.1 2011-04-17 09:30:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Unit of measure for the Maturity Month Year Increment.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 30/08/2009, 12:35:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MaturityMonthYearIncrementUnits {

    @XmlEnumValue("0")  Months      (0),
    @XmlEnumValue("1")  Days        (1),
    @XmlEnumValue("2")  Weeks       (2),
    @XmlEnumValue("3")  Years       (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MaturityMonthYearIncrementUnits> stringToEnum = new HashMap<String, MaturityMonthYearIncrementUnits>();

    static {
        for (MaturityMonthYearIncrementUnits tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MaturityMonthYearIncrementUnits */
    MaturityMonthYearIncrementUnits(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MaturityMonthYearIncrementUnits valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
