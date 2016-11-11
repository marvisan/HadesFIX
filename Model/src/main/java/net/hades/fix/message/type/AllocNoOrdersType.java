/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocNoOrdersType.java
 *
 * $Id: AllocNoOrdersType.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of no of orders.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocNoOrdersType {

    @XmlEnumValue("0")  NotSpecified                    (0),
    @XmlEnumValue("1")  ExplicitListProvided            (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocNoOrdersType> stringToEnum = new HashMap<String, AllocNoOrdersType>();

    static {
        for (AllocNoOrdersType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocNoOrdersType */
    AllocNoOrdersType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocNoOrdersType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
