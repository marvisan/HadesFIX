/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtAssignmentMethod.java
 *
 * $Id: InstrmtAssignmentMethod.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Method under which assignment was conducted.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 30/11/2008, 11:27:54 AM
 */
@XmlType
@XmlEnum(String.class)
public enum InstrmtAssignmentMethod {

    @XmlEnumValue("R") Random                 ('R'),
    @XmlEnumValue("P") ProRata                ('P');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, InstrmtAssignmentMethod> stringToEnum = new HashMap<String, InstrmtAssignmentMethod>();

    static {
        for (InstrmtAssignmentMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of InstrmtAssignmentMethod */
    InstrmtAssignmentMethod(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static InstrmtAssignmentMethod valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
