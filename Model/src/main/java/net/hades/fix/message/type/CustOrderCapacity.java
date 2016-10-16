/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CustOrderCapacity.java
 *
 * $Id: CustOrderCapacity.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Capacity of customer placing the order.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 11:02:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CustOrderCapacity {

    @XmlEnumValue("1") MemberTradingOwnAccount                 (1),
    @XmlEnumValue("2") ClearingFirmTrading                     (2),
    @XmlEnumValue("3") MemberTradingForAnotherMember           (3),
    @XmlEnumValue("4") AllOther                                (4);

    private static final long serialVersionUID = -5789145659683615106L;

    private int value;

    private static final Map<String, CustOrderCapacity> stringToEnum = new HashMap<String, CustOrderCapacity>();

    static {
        for (CustOrderCapacity tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CustOrderCapacity */
    CustOrderCapacity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CustOrderCapacity valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
