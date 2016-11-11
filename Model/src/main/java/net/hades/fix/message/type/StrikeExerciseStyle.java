/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StrikeExerciseStyle.java
 *
 * $Id: StrikeExerciseStyle.java,v 1.1 2011-04-17 09:30:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Expiration Style for an option class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 30/08/2009, 12:35:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum StrikeExerciseStyle {

    @XmlEnumValue("0")  European      (0),
    @XmlEnumValue("1")  American      (1),
    @XmlEnumValue("2")  Bermuda       (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, StrikeExerciseStyle> stringToEnum = new HashMap<String, StrikeExerciseStyle>();

    static {
        for (StrikeExerciseStyle tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of StrikeExerciseStyle */
    StrikeExerciseStyle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StrikeExerciseStyle valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
