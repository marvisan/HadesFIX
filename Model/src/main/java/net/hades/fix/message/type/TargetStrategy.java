/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NoSides.java
 *
 * $Id: TargetStrategy.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Number of Side repeating group instances.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/09/2009, 8:00:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TargetStrategy {

    @XmlEnumValue("1")      VWAP                                (1),
    @XmlEnumValue("2")      Participate                         (2),
    @XmlEnumValue("1")      MininizeMarketImpact                (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TargetStrategy> stringToEnum = new HashMap<String, TargetStrategy>();

    static {
        for (TargetStrategy tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of NoSides */
    TargetStrategy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TargetStrategy valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
