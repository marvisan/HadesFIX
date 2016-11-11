/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * SecurityTradingStatus.java
 *
 * $Id: SecurityTradingStatus.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Trading status applicable to the transaction.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/09/2009, 9:33:21 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SecurityTradingStatus {

    @XmlEnumValue("1")      OpeningDelay                    (1),
    @XmlEnumValue("2")      TradingHalt                     (2),
    @XmlEnumValue("3")      Resume                          (3),
    @XmlEnumValue("4")      NoOpen                          (4),
    @XmlEnumValue("5")      PriceIndication                 (5),
    @XmlEnumValue("6")      TradingRangeIndication          (6),
    @XmlEnumValue("7")      MarketImbalanceBuy              (7),
    @XmlEnumValue("8")      MarketImbalanceSell             (8),
    @XmlEnumValue("9")      MarketCloseImbalanceBuy         (9),
    @XmlEnumValue("10")     MarketCloseImbalanceSell        (10),
    @XmlEnumValue("12")     NoMarketImbalance               (12),
    @XmlEnumValue("13")     NoMarketCloseImbalance          (13),
    @XmlEnumValue("14")     ITSPreOpening                   (14),
    @XmlEnumValue("15")     NewPriceIndication              (15),
    @XmlEnumValue("16")     TradeDisseminationTime          (16),
    @XmlEnumValue("17")     ReadyToTrade                    (17),
    @XmlEnumValue("18")     NotAvailableForTrading          (18),
    @XmlEnumValue("19")     NotTradedOnThisMarket           (19),
    @XmlEnumValue("20")     UnknownOrInvalid                (20),
    @XmlEnumValue("21")     PreOpen                         (21),
    @XmlEnumValue("22")     OpeningRotation                 (22),
    @XmlEnumValue("23")     FastMarket                      (23),
    @XmlEnumValue("24")     PreCross                        (24),
    @XmlEnumValue("25")     Cross                           (25),
    @XmlEnumValue("26")     PostClose                       (26);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SecurityTradingStatus> stringToEnum = new HashMap<String, SecurityTradingStatus>();

    static {
        for (SecurityTradingStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SecurityTradingStatus */
    SecurityTradingStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecurityTradingStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
