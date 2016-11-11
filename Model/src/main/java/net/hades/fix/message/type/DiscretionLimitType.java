/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DiscretionLimitType.java
 *
 * $Id: DiscretionLimitType.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Discretion Limit.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/06/2009, 8:40:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum DiscretionLimitType {

    @XmlEnumValue("0")  OrBetter                (0),
    @XmlEnumValue("1")  Strict                  (1),
    @XmlEnumValue("2")  OrWorse                 (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, DiscretionLimitType> stringToEnum = new HashMap<String, DiscretionLimitType>();

    static {
        for (DiscretionLimitType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DiscretionLimitType */
    DiscretionLimitType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DiscretionLimitType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
