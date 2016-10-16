/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExerciseMethod.java
 *
 * $Id: ExerciseMethod.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Exercise Method used to in performing assignment.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/09/2009, 7:25:57 PM
 */
@XmlType
@XmlEnum(String.class)
public enum ExerciseMethod {

    @XmlEnumValue("A") Automatic                    ('A'),
    @XmlEnumValue("M") Manual                       ('M');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, ExerciseMethod> stringToEnum = new HashMap<String, ExerciseMethod>();

    static {
        for (ExerciseMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ExerciseMethod */
    ExerciseMethod(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static ExerciseMethod valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
