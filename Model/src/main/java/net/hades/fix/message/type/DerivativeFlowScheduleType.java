/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeFlowScheduleType.java
 *
 * $Id: DerivativeFlowScheduleType.java,v 1.1 2010-12-20 10:58:54 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Flow schedule by which electricity or natural gas is traded.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:32:13 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum DerivativeFlowScheduleType {

    @XmlEnumValue("0") NERCEasternOffPeak                       (0),
    @XmlEnumValue("1") NERCWesternOffPeak                       (1),
    @XmlEnumValue("2") NERCCalendarAllDays                      (2),
    @XmlEnumValue("3") NERCEasternPeak                          (3),
    @XmlEnumValue("4") NERCWesternPeak                          (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, DerivativeFlowScheduleType> stringToEnum = new HashMap<String, DerivativeFlowScheduleType>();

    static {
        for (DerivativeFlowScheduleType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DerivativeFlowScheduleType */
    DerivativeFlowScheduleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DerivativeFlowScheduleType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
