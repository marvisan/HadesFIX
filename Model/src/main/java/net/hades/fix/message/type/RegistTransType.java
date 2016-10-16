/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistTransType.java
 *
 * $Id: RegistTransType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Registration Instructions transaction type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 24/09/2009, 9:19:09 PM
 */
@XmlType
@XmlEnum(String.class)
public enum RegistTransType {

    @XmlEnumValue("0") New              ('0'),
    @XmlEnumValue("1") Replace          ('1'),
    @XmlEnumValue("2") Cancel           ('2');

    private static final long serialVersionUID = -796828362334427941L;

    private char value;

    private static final Map<String, RegistTransType> stringToEnum = new HashMap<String, RegistTransType>();

    static {
        for (RegistTransType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RegistTransType */
    RegistTransType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static RegistTransType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
