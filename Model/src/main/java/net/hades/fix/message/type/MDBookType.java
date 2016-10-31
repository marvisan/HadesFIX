/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDBookType.java
 *
 * $Id: MDBookType.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of book for which the feed is intended.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 21/08/2009, 9:28:05 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MDBookType {

    @XmlEnumValue("1") TopOfBook            (1),
    @XmlEnumValue("2") PriceDepth           (2),
    @XmlEnumValue("3") OrderDepth           (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MDBookType> stringToEnum = new HashMap<String, MDBookType>();

    static {
        for (MDBookType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MDBookType */
    MDBookType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MDBookType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
