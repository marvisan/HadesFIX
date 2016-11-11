/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDOriginType.java
 *
 * $Id: MDOriginType.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of origin of an entry in the book.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 31/10/2009, 3:47:19 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MDOriginType {

    @XmlEnumValue("0")      Book            (0),
    @XmlEnumValue("1")      OffBook         (1),
    @XmlEnumValue("2")      Cross           (2);

    private static final long serialVersionUID = -1340547506889628741L;

    private int value;

    private static final Map<String, MDOriginType> stringToEnum = new HashMap<String, MDOriginType>();

    static {
        for (MDOriginType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MDOriginType */
    MDOriginType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MDOriginType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
