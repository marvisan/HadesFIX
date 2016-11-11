/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * GTBookingInst.java
 *
 * $Id: GTBookingInst.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to identify whether to book out executions on a part-filled GT order.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 06/09/2009, 9:54:54 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum GTBookingInst {

    @XmlEnumValue("0")      BookOutAllTrades                        (0),
    @XmlEnumValue("1")      AccumExectUntilOrderFilled              (1),
    @XmlEnumValue("2")      AccumUntilVerballlyNotified             (2);

    private static final long serialVersionUID = -1873897148072474928L;

    private int value;

    private static final Map<String, GTBookingInst> stringToEnum = new HashMap<String, GTBookingInst>();

    static {
        for (GTBookingInst tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of GTBookingInst */
    GTBookingInst(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GTBookingInst valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
