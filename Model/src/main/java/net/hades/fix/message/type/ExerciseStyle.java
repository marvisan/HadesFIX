/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExerciseStyle.java
 *
 * $Id: ExerciseStyle.java,v 1.5 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Derivative security type of exercise.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 28/11/2008, 8:36:30 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ExerciseStyle {

    @XmlEnumValue("0") European            (0),
    @XmlEnumValue("1") American            (1),
    @XmlEnumValue("2") Bermuda             (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ExerciseStyle> stringToEnum = new HashMap<String, ExerciseStyle>();

    static {
        for (ExerciseStyle tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ExerciseStyle */
    ExerciseStyle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ExerciseStyle valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
