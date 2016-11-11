/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * SettlDeliveryType.java
 *
 * $Id: SettlDeliveryType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of settlement.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 30/08/2009, 12:03:19 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SettlDeliveryType {

    @XmlEnumValue("0") Versus                       (0),
    @XmlEnumValue("1") Free                         (1),
    @XmlEnumValue("2") TriParty                     (2),
    @XmlEnumValue("3") HoldInCustody                (3);

    private static final long serialVersionUID = -91834995669042086L;

    private int value;

    private static final Map<String, SettlDeliveryType> stringToEnum = new HashMap<String, SettlDeliveryType>();

    static {
        for (SettlDeliveryType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlDeliveryType */
    SettlDeliveryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SettlDeliveryType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
