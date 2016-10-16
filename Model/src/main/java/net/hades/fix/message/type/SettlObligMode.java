/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlObligMode.java
 *
 * $Id: SettlObligMode.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the reporting mode of the settlement obligation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SettlObligMode {

    @XmlEnumValue("1")      Preliminary         (1),
    @XmlEnumValue("2")      Final               (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SettlObligMode> stringToEnum = new HashMap<String, SettlObligMode>();

    static {
        for (SettlObligMode tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlObligMode */
    SettlObligMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SettlObligMode valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
