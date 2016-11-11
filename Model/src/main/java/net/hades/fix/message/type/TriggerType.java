/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TriggerType.java
 *
 * $Id: TriggerType.java,v 1.2 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines the trigger instructions will come into effect.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum TriggerType {

    @XmlEnumValue("1") PartialExecution                    ('1'),
    @XmlEnumValue("2") SpecifiedTradingSession             ('2'),
    @XmlEnumValue("3") NextAuction                         ('3'),
    @XmlEnumValue("4") PriceMovement                       ('4');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, TriggerType> stringToEnum = new HashMap<String, TriggerType>();

    static {
        for (TriggerType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TriggerType */
    TriggerType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static TriggerType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
