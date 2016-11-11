/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AssignmentMethod.java
 *
 * $Id: AssignmentMethod.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Method by which short positions are assigned to an exercise notice during exercise and assignment processing.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/09/2009, 7:25:57 PM
 */
@XmlType
@XmlEnum(String.class)
public enum AssignmentMethod {

    @XmlEnumValue("P") ProRata                  ('P'),
    @XmlEnumValue("R") Random                   ('R');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, AssignmentMethod> stringToEnum = new HashMap<String, AssignmentMethod>();

    static {
        for (AssignmentMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AssignmentMethod */
    AssignmentMethod(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static AssignmentMethod valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
