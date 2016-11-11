/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MaturityMonthYearFormat.java
 *
 * $Id: MaturityMonthYearFormat.java,v 1.1 2011-04-17 09:30:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Format used to generate the MaturityMonthYear.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 30/08/2009, 12:35:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MaturityMonthYearFormat {

    @XmlEnumValue("0")  YearMonth           (0),
    @XmlEnumValue("1")  YearMonthDay        (1),
    @XmlEnumValue("2")  YearMonthWeek       (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MaturityMonthYearFormat> stringToEnum = new HashMap<String, MaturityMonthYearFormat>();

    static {
        for (MaturityMonthYearFormat tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MaturityMonthYearFormat */
    MaturityMonthYearFormat(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MaturityMonthYearFormat valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
