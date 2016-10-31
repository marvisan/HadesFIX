/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DeliveryType.java
 *
 * $Id: DeliveryType.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies type of settlement.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 10/02/2009, 11:37:26 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum DeliveryType {

    @XmlEnumValue("0") VersusPayment           (0),
    @XmlEnumValue("1") Free                    (1),
    @XmlEnumValue("2") TriParty                (2),
    @XmlEnumValue("3") HoldInCustody           (3);

    private static final long serialVersionUID = -9102771826588851133L;

    private int value;

    private static final Map<String, DeliveryType> stringToEnum = new HashMap<String, DeliveryType>();

    static {
        for (DeliveryType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DeliveryType */
    DeliveryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DeliveryType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
