/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TriggerPriceDirection.java
 *
 * $Id: TriggerPriceDirection.java,v 1.2 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines the side from which the trigger price is reached.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum TriggerPriceDirection {

    @XmlEnumValue("U") UP                         ('U'),
    @XmlEnumValue("D") DOWN                       ('D');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, TriggerPriceDirection> stringToEnum = new HashMap<String, TriggerPriceDirection>();

    static {
        for (TriggerPriceDirection tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TriggerPriceDirection */
    TriggerPriceDirection(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static TriggerPriceDirection valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
