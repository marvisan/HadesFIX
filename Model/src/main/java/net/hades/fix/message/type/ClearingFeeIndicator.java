/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ClearingFeeIndicator.java
 *
 * $Id: ClearingFeeIndicator.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of fee being assessed of the customer for trade executions at an exchange.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/09/2009, 10:47:06 AM
 */
@XmlType
@XmlEnum(String.class)
public enum ClearingFeeIndicator {

    @XmlEnumValue("1") TradingOwnAcct1stYear                ("1"),
    @XmlEnumValue("2") TradingOwnAcct2ndYear                ("2"),
    @XmlEnumValue("3") TradingOwnAcct3rdYear                ("3"),
    @XmlEnumValue("4") TradingOwnAcct4thYear                ("4"),
    @XmlEnumValue("5") TradingOwnAcct5thYear                ("5"),
    @XmlEnumValue("9") TradingOwnAcct6thYear                ("9"),
    @XmlEnumValue("B") CBOEMember                           ("B"),
    @XmlEnumValue("C") NonMemberAndCustomer                 ("C"),
    @XmlEnumValue("E") EquityMemberAndClearingMember        ("E"),
    @XmlEnumValue("F") FullAndAssociateMember               ("F"),
    @XmlEnumValue("H") Firms106HAnd106J                     ("H"),
    @XmlEnumValue("I") GIM_IDEM_COM_Membership              ("I"),
    @XmlEnumValue("L") Lessee106F_Employees                 ("L"),
    @XmlEnumValue("M") AllOtherOwnershipTypes               ("M");

    private static final long serialVersionUID = -8646069837517212453L;

    private String value;

    private static final Map<String, ClearingFeeIndicator> stringToEnum = new HashMap<String, ClearingFeeIndicator>();

    static {
        for (ClearingFeeIndicator tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of ClearingFeeIndicator. */
    ClearingFeeIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ClearingFeeIndicator valueFor(String value) {
        return stringToEnum.get(value);
    }
}
