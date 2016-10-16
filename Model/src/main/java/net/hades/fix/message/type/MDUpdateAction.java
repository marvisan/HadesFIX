/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDUpdateAction.java
 *
 * $Id: MDUpdateAction.java,v 1.5 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Market Data update action.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 02/09/2009, 8:36:12 PM
 */
@XmlType
@XmlEnum(String.class)
public enum MDUpdateAction {

    @XmlEnumValue("0") New              ('0'),
    @XmlEnumValue("1") Change           ('1'),
    @XmlEnumValue("2") Delete           ('2'),
    @XmlEnumValue("3") DeleteThru       ('3'),
    @XmlEnumValue("4") DeleteFrom       ('4'),
    @XmlEnumValue("5") Overlay          ('5');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, MDUpdateAction> stringToEnum = new HashMap<String, MDUpdateAction>();

    static {
        for (MDUpdateAction tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MDUpdateAction */
    MDUpdateAction(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static MDUpdateAction valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
