/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TerminationType.java
 *
 * $Id: TerminationType.java,v 1.4 2010-02-25 08:37:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of financing termination.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 10/02/2009, 11:30:45 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TerminationType {

    @XmlEnumValue("1") Overnight           (1),
    @XmlEnumValue("2") Term                (2),
    @XmlEnumValue("3") Flexible            (3),
    @XmlEnumValue("4") Open                (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TerminationType> stringToEnum = new HashMap<String, TerminationType>();

    static {
        for (TerminationType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }


    /** Creates a new instance of TerminationType */
    TerminationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TerminationType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
