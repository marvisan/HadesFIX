/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RateSourceType.java
 *
 * $Id: RateSourceType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates whether the rate source specified is a primary or secondary source.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/06/2009, 8:33:41 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum RateSourceType {

    @XmlEnumValue("0")  Primary                 (0),
    @XmlEnumValue("1")  Secondary               (1);

    private static final long serialVersionUID = -4342010799073576126L;

    private int value;

    private static final Map<String, RateSourceType> stringToEnum = new HashMap<String, RateSourceType>();

    static {
        for (RateSourceType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RateSourceType */
    RateSourceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RateSourceType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
