/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HaltReason.java
 *
 * $Id: HaltReason.java,v 1.5 2011-04-21 09:45:43 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason for the Opening Delay or Trading Halt.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 05/09/2009, 2:23:19 PM
 */
@XmlType
@XmlEnum(String.class)
public enum HaltReason {

    @XmlEnumValue("0")      NewsDissemination               ('0'),
    @XmlEnumValue("1")      OrderInflux                     ('1'),
    @XmlEnumValue("2")      OrderImbalance                  ('2'),
    @XmlEnumValue("3")      AdditionalInformation           ('3'),
    @XmlEnumValue("4")      NewsPending                     ('4'),
    @XmlEnumValue("5")      EquipmentChangeover             ('5'),
    @XmlEnumValue("D")      NewsDissOld                     ('D'),
    @XmlEnumValue("E")      OrderInfluxOld                  ('E'),
    @XmlEnumValue("I")      OrderImbalanceOld               ('I'),
    @XmlEnumValue("M")      AdditionalInfoOld               ('M'),
    @XmlEnumValue("P")      NewsPendingOld                  ('P'),
    @XmlEnumValue("X")      EquipmentChangeoverOld          ('X');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, HaltReason> stringToEnum = new HashMap<String, HaltReason>();

    static {
        for (HaltReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of HaltReason */
    HaltReason(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static HaltReason valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
