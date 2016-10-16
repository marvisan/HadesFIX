/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDUpdateType.java
 *
 * $Id: MDUpdateType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of market data update.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/07/2009, 12:49:48 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MDUpdateType {

    @XmlEnumValue("0") FullRefresh              (0),
    @XmlEnumValue("1") IncrementalRefresh       (1);

    private static final long serialVersionUID = -4011470797301029558L;

    private int value;

    private static final Map<String, MDUpdateType> stringToEnum = new HashMap<String, MDUpdateType>();

    static {
        for (MDUpdateType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MDUpdateType */
    MDUpdateType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MDUpdateType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
