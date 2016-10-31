/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContingencyType.java
 *
 * $Id: ContingencyType.java,v 1.1 2011-01-30 10:23:25 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines the type of contingency.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:32:13 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ContingencyType {

    @XmlEnumValue("1") OneCancelsOther                      (1),
    @XmlEnumValue("2") OneTriggersOther                     (2),
    @XmlEnumValue("3") OneUpdatesOther_AbsQuantRed          (3),
    @XmlEnumValue("4") OneUpdatesOther_PropQuantRed         (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ContingencyType> stringToEnum = new HashMap<String, ContingencyType>();

    static {
        for (ContingencyType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ContingencyType */
    ContingencyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ContingencyType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
