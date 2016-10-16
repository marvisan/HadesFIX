/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DiscretionRoundDirection.java
 *
 * $Id: DiscretionRoundDirection.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies whether to round the price to be more or less aggressive.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/06/2009, 8:40:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum DiscretionRoundDirection {

    @XmlEnumValue("1")  MoreAggressive                  (1),
    @XmlEnumValue("2")  MorePassive                     (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, DiscretionRoundDirection> stringToEnum = new HashMap<String, DiscretionRoundDirection>();

    static {
        for (DiscretionRoundDirection tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DiscretionRoundDirection */
    DiscretionRoundDirection(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DiscretionRoundDirection valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
