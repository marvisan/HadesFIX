/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FlowScheduleType.java
 *
 * $Id: FlowScheduleType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The industry standard flow schedule by which electricity or natural gas is traded.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/06/2009, 12:12:31 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum FlowScheduleType {

    @XmlEnumValue("0") NERCEasternOffPeak       (0),
    @XmlEnumValue("1") NERCWesternOffPeak       (1),
    @XmlEnumValue("2") NERCCalendarAllDays      (2),
    @XmlEnumValue("3") NERCEasternPeak          (3),
    @XmlEnumValue("4") NERCWesternPeak          (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, FlowScheduleType> stringToEnum = new HashMap<String, FlowScheduleType>();

    static {
        for (FlowScheduleType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of FlowScheduleType */
    FlowScheduleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FlowScheduleType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
