/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BenchmarkCurveName.java
 *
 * $Id: DerivativeExerciseStyle.java,v 1.1 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of exercise of a derivatives security.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 10/02/2009, 7:01:39 PM
 */
@XmlType
@XmlEnum(String.class)
public enum DerivativeExerciseStyle {

    @XmlEnumValue("0")          European        ("0"),
    @XmlEnumValue("1")          American        ("1"),
    @XmlEnumValue("2")          Bermuda         ("2");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, DerivativeExerciseStyle> stringToEnum = new HashMap<String, DerivativeExerciseStyle>();

    static {
        for (DerivativeExerciseStyle tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DerivativeExerciseStyle. */
    DerivativeExerciseStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DerivativeExerciseStyle valueFor(String value) {
        return stringToEnum.get(value);
    }
}
