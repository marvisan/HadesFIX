/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrigCustOrderCapacity.java
 *
 * $Id: OrigCustOrderCapacity.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The customer capacity for a trade at the time of the order/execution.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/08/2009, 9:17:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum OrigCustOrderCapacity {

    @XmlEnumValue("1") MemberTradingOwnAccount                      (1),
    @XmlEnumValue("2") ClearingFirmTradingPropAccount               (2),
    @XmlEnumValue("3") MemberTradingForAnotherMember                (3),
    @XmlEnumValue("4") AllOther                                     (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, OrigCustOrderCapacity> stringToEnum = new HashMap<String, OrigCustOrderCapacity>();

    static {
        for (OrigCustOrderCapacity tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of OrigCustOrderCapacity */
    OrigCustOrderCapacity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrigCustOrderCapacity valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
