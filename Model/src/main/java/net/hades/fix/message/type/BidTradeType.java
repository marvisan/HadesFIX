/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidTradeType.java
 *
 * $Id: BidTradeType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent the type of trade.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 05/09/2009, 4:35:27 PM
 */
@XmlType
@XmlEnum(String.class)
public enum BidTradeType {

    @XmlEnumValue("A") Agency                   ('A'),
    @XmlEnumValue("G") VWAPGuarantee            ('G'),
    @XmlEnumValue("J") GuaranteedClose          ('J'),
    @XmlEnumValue("R") RiskTrade                ('R');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, BidTradeType> stringToEnum = new HashMap<String, BidTradeType>();

    static {
        for (BidTradeType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of BidTradeType */
    BidTradeType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static BidTradeType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
