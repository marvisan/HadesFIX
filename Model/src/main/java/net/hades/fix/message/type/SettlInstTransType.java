/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstTransType.java
 *
 * $Id: SettlInstTransType.java,v 1.2 2011-03-26 03:24:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Types of resulting position.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum SettlInstTransType {

    @XmlEnumValue("N") New                 ('N'),
    @XmlEnumValue("C") Cancel              ('C'),
    @XmlEnumValue("R") Replace             ('R'),
    @XmlEnumValue("T") Restate             ('T');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, SettlInstTransType> stringToEnum = new HashMap<String, SettlInstTransType>();

    static {
        for (SettlInstTransType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlInstTransType */
    SettlInstTransType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static SettlInstTransType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
