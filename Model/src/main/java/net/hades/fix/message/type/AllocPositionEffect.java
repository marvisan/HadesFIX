/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocPositionEffect.java
 *
 * $Id: AllocPositionEffect.java,v 1.1 2011-02-10 10:02:15 vrotaru Exp $
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
 * @version $Revision: 1.1 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum AllocPositionEffect {

    @XmlEnumValue("O") Open            ('O'),
    @XmlEnumValue("C") Close           ('C'),
    @XmlEnumValue("R") Rolled          ('R'),
    @XmlEnumValue("F") FIFO            ('F');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, AllocPositionEffect> stringToEnum = new HashMap<String, AllocPositionEffect>();

    static {
        for (AllocPositionEffect tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocPositionEffect */
    AllocPositionEffect(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static AllocPositionEffect valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
