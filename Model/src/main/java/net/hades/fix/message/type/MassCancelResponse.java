/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassCancelResponse.java
 *
 * $Id: MassCancelResponse.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Action type taken by counterparty order handling system.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 7:00:34 PM
 */
@XmlType
@XmlEnum(String.class)
public enum MassCancelResponse {

    @XmlEnumValue("0") CancelRequestRejected                    ('0'),
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

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, MassCancelResponse> stringToEnum = new HashMap<String, MassCancelResponse>();

    static {
        for (MassCancelResponse tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MassCancelResponse */
    MassCancelResponse(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static MassCancelResponse valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
