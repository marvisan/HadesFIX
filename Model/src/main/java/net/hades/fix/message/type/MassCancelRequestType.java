/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassCancelRequestType.java
 *
 * $Id: MassCancelRequestType.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Scope type of Order Mass Cancel Request.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/09/2009, 6:52:04 PM
 */
@XmlType
@XmlEnum(String.class)
public enum MassCancelRequestType {

    @XmlEnumValue("1") CancelOrdersForSecurity                  ('1'),
    @XmlEnumValue("2") CancelOrdersForUnderlying                ('2'),
    @XmlEnumValue("3") CancelOrdersForProduct                   ('3'),
    @XmlEnumValue("4") CancelOrdersForCFICode                   ('4'),
    @XmlEnumValue("5") CancelOrdersForSecurityType              ('5'),
    @XmlEnumValue("6") CancelOrdersForTradingSession            ('6'),
    @XmlEnumValue("7") CancelAllOrders                          ('7'),
    @XmlEnumValue("8") CancelOrdersForMarket                    ('8'),
    @XmlEnumValue("9") CancelOrdersForMarketSegment             ('9'),
    @XmlEnumValue("A") CancelOrdersForSecurityGroup             ('A'),
    @XmlEnumValue("B") CancelForSecurityIssuer                  ('B'),
    @XmlEnumValue("C") CancelForIssuerOfUnderlying              ('C');

    private static final long serialVersionUID = -8043310493625215998L;

    private char value;

    private static final Map<String, MassCancelRequestType> stringToEnum = new HashMap<String, MassCancelRequestType>();

    static {
        for (MassCancelRequestType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MassCancelRequestType */
    MassCancelRequestType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static MassCancelRequestType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
