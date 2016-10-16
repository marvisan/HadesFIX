/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDEntryType.java
 *
 * $Id: MDEntryType.java,v 1.5 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of market data entry.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 26/07/2009, 1:12:08 PM
 */
@XmlType
@XmlEnum(String.class)
public enum MDEntryType {

    @XmlEnumValue("0") Bid                                      ('0'),
    @XmlEnumValue("1") Offer                                    ('1'),
    @XmlEnumValue("2") Trade                                    ('2'),
    @XmlEnumValue("3") IndexValue                               ('3'),
    @XmlEnumValue("4") OpeningPrice                             ('4'),
    @XmlEnumValue("5") ClosingPrice                             ('5'),
    @XmlEnumValue("6") SettlementPrice                          ('6'),
    @XmlEnumValue("7") TradingSessionHighPrice                  ('7'),
    @XmlEnumValue("8") TradingSessionLowPrice                   ('8'),
    @XmlEnumValue("9") TradingSessionVWAPPrice                  ('9'),
    @XmlEnumValue("A") Imbalance                                ('A'),
    @XmlEnumValue("B") TradeVolume                              ('B'),
    @XmlEnumValue("C") OpenInterest                             ('C'),
    @XmlEnumValue("D") CompositeUnderlyingPrice                 ('D'),
    @XmlEnumValue("E") SimulatedSellPrice                       ('E'),
    @XmlEnumValue("F") SimulatedBuyPrice                        ('F'),
    @XmlEnumValue("G") MarginRate                               ('G'),
    @XmlEnumValue("H") MidPrice                                 ('H'),
    @XmlEnumValue("J") EmptyBook                                ('J'),
    @XmlEnumValue("K") SettleHighPrice                          ('K'),
    @XmlEnumValue("L") SettleLowPrice                           ('L'),
    @XmlEnumValue("M") PriorSettlePrice                         ('M'),
    @XmlEnumValue("N") SessionHighBid                           ('N'),
    @XmlEnumValue("O") SessionLowOffer                          ('O'),
    @XmlEnumValue("P") EarlyPrices                              ('P'),
    @XmlEnumValue("Q") AuctionClearingPrice                     ('Q'),
    @XmlEnumValue("S") SwapValueFactor                          ('S'),
    @XmlEnumValue("R") DailyValueAdjustmentForLong              ('R'),
    @XmlEnumValue("T") CumulativeValueAdjustmentForLong         ('T'),
    @XmlEnumValue("U") DailyValueAdjustmentForShort             ('U'),
    @XmlEnumValue("V") CumulativeValueAdjustmentForShort        ('V'),
    @XmlEnumValue("W") FixingPrice                              ('W'),
    @XmlEnumValue("X") CashRate                                 ('X'),
    @XmlEnumValue("Y") RecoveryRate                             ('Y'),
    @XmlEnumValue("Z") RecoveryRateForLong                      ('Z'),
    @XmlEnumValue("a") RecoveryRateForShort                     ('a');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, MDEntryType> stringToEnum = new HashMap<String, MDEntryType>();

    static {
        for (MDEntryType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MDEntryType */
    MDEntryType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static MDEntryType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
