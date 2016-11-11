/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollApplType.java
 *
 * $Id: CollApplType.java,v 1.1 2011-02-06 04:44:15 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Collateral apply type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CollApplType {

    @XmlEnumValue("0")  SpecificDeposit         (0),
    @XmlEnumValue("1")  General                 (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CollApplType> stringToEnum = new HashMap<String, CollApplType>();

    static {
        for (CollApplType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CollApplType */
    CollApplType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CollApplType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
