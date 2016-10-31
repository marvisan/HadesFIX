/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlMethod.java
 *
 * $Id: SettlMethod.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Settlement method for a contract.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/12/2008, 7:51:21 PM
 */
@XmlType
@XmlEnum(String.class)
public enum SettlMethod {

    @XmlEnumValue("C") Cash                    ('C'),
    @XmlEnumValue("P") Physical                ('P');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, SettlMethod> stringToEnum = new HashMap<String, SettlMethod>();

    static {
        for (SettlMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlMethod */
    SettlMethod(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static SettlMethod valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
