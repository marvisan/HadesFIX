/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistStatus.java
 *
 * $Id: RegistStatus.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Registration status.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 24/09/2009, 8:50:50 PM
 */
@XmlType
@XmlEnum(String.class)
public enum RegistStatus {

    @XmlEnumValue("A") Accepted         ('A'),
    @XmlEnumValue("R") Rejected         ('R'),
    @XmlEnumValue("H") Held             ('H'),
    @XmlEnumValue("N") Reminder         ('N');

    private static final long serialVersionUID = -4483379135217081499L;

    private char value;

    private static final Map<String, RegistStatus> stringToEnum = new HashMap<String, RegistStatus>();

    static {
        for (RegistStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RegistStatus */
    RegistStatus(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static RegistStatus valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
