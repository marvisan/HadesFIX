/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossType.java
 *
 * $Id: CrossType.java,v 1.3 2010-01-14 09:06:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of cross being submitted to a market.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/09/2009, 7:47:36 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CrossType {

    @XmlEnumValue("1")      CrossAON            (1),
    @XmlEnumValue("2")      CrossIOC            (2),
    @XmlEnumValue("3")      CrossOneSide        (3),
    @XmlEnumValue("4")      CrossSamePrice      (4);

    private static final long serialVersionUID = -6299742389053194920L;

    private int value;

    private static final Map<String, CrossType> stringToEnum = new HashMap<String, CrossType>();

    static {
        for (CrossType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CrossType */
    CrossType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CrossType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
