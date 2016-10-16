/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContractMultiplierUnit.java
 *
 * $Id: ContractMultiplierUnit.java,v 1.3 2010-01-14 09:06:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates the type of multiplier being applied to the contract.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 03/06/2009, 12:07:33 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ContractMultiplierUnit {

    @XmlEnumValue("0") Shares       (0),
    @XmlEnumValue("1") Hours        (1),
    @XmlEnumValue("2") Days         (2);

    private static final long serialVersionUID = -6865926102265443304L;

    private int value;

    private static final Map<String, ContractMultiplierUnit> stringToEnum = new HashMap<String, ContractMultiplierUnit>();

    static {
        for (ContractMultiplierUnit tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ContractMultiplierUnit */
    ContractMultiplierUnit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ContractMultiplierUnit valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
