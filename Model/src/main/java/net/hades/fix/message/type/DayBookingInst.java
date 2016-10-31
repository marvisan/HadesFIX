/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DayBookingInst.java
 *
 * $Id: DayBookingInst.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates whether or not automatic booking can occur.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/09/2009, 9:40:42 PM
 */
@XmlType
@XmlEnum(String.class)
public enum DayBookingInst {

    @XmlEnumValue("0") CanTriggerBookingWithoutReference            ('0'),
    @XmlEnumValue("1") SpeakWithOrderInitiatorBeforeBooking         ('1'),
    @XmlEnumValue("2") Accumulate                                   ('2');

    private static final long serialVersionUID = -7693885603507946438L;

    private char value;

    private static final Map<String, DayBookingInst> stringToEnum = new HashMap<String, DayBookingInst>();

    static {
        for (DayBookingInst tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DayBookingInst */
    DayBookingInst(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static DayBookingInst valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
