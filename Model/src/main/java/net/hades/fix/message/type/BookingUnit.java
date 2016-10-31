/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BookingUnit.java
 *
 * $Id: BookingUnit.java,v 1.4 2010-02-25 08:37:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates what constitutes a bookable unit.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 9:44:48 PM
 */
@XmlType
@XmlEnum(String.class)
public enum BookingUnit {

    @XmlEnumValue("0") EachExecutionBookableUnit            ('0'),
    @XmlEnumValue("1") AggregatePartialExecutions           ('1'),
    @XmlEnumValue("2") AggregateExecutionsForSymbol         ('2');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, BookingUnit> stringToEnum = new HashMap<String, BookingUnit>();

    static {
        for (BookingUnit tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of BookingUnit */
    BookingUnit(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static BookingUnit valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
