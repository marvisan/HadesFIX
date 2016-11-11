/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AccountType.java
 *
 * $Id: AccountType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of account associated with an order.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 03/04/2009, 10:32:13 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AccountType {

    /**
     * Account is carried on customer side of the books.
     */
    @XmlEnumValue("1") CustomerSide                            (1),
    /**
     * Account is carried on non-customer side of books.
     */
    @XmlEnumValue("2") NonCustomerSide                         (2),
    @XmlEnumValue("3") HouseTrader                             (3),
    @XmlEnumValue("4") FloorTrader                             (4),
    /**
     * Account is carried on non-customer side of books and is cross margined.
     */
    @XmlEnumValue("6") NonCustSideCrossMargined                (6),
    /**
     * Account is house trader and is cross margined.
     */
    @XmlEnumValue("7") HouseTraderCrossMargined                (7),
    @XmlEnumValue("8") JointBackOfficeAccount                  (8);

    private static final long serialVersionUID = -6882693612202816367L;

    private int value;

    private static final Map<String, AccountType> stringToEnum = new HashMap<String, AccountType>();

    static {
        for (AccountType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AccountType */
    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AccountType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
