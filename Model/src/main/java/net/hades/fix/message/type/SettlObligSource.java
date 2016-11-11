/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * SettlObligSource.java
 *
 * $Id: SettlObligSource.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies whether delivery instructions are for the buyside or the sellside..
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/09/2009, 9:44:48 PM
 */
@XmlType
@XmlEnum(String.class)
public enum SettlObligSource {

    @XmlEnumValue("1") InstructionsOfBroker                 ('1'),
    @XmlEnumValue("2") InstructionsForInstitution           ('2'),
    @XmlEnumValue("3") Investor                             ('3');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, SettlObligSource> stringToEnum = new HashMap<String, SettlObligSource>();

    static {
        for (SettlObligSource tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlObligSource */
    SettlObligSource(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static SettlObligSource valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
