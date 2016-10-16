/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BookingType.java
 *
 * $Id: BookingType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type descrbing the method for booking out this order.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 11/06/2009, 8:40:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum BookingType {

    @XmlEnumValue("0")  RegularBooking                  (0),
    @XmlEnumValue("1")  ContractForDifference           (1),
    @XmlEnumValue("2")  TotalReturnSwap                 (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, BookingType> stringToEnum = new HashMap<String, BookingType>();

    static {
        for (BookingType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of BookingType */
    BookingType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BookingType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
