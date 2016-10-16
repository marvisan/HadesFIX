/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstMode.java
 *
 * $Id: SettlInstMode.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Mode used for Settlement Instructions.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 30/08/2009, 10:35:42 AM
 */
@XmlType
@XmlEnum(String.class)
public enum SettlInstMode {

    @XmlEnumValue("0") Default                                          ('0'),
    @XmlEnumValue("1") StandingInstructionsProvided                     ('1'),
    @XmlEnumValue("2") SpecificAllocationAccountOverriding              ('2'),
    @XmlEnumValue("3") SpecificAllocationAccountStanding                ('3'),
    @XmlEnumValue("4") SpecificOrder                                    ('4'),
    @XmlEnumValue("5") RequestReject                                    ('5');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, SettlInstMode> stringToEnum = new HashMap<String, SettlInstMode>();

    static {
        for (SettlInstMode tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlInstMode */
    SettlInstMode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static SettlInstMode valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
