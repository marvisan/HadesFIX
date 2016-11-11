/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * TrdType.java
 *
 * $Id: TrdType.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Trade.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TrdType {

    @XmlEnumValue("0")      RegularTrade                        (0),
    @XmlEnumValue("1")      BlockTrade                          (1),
    @XmlEnumValue("2")      EFP                                 (2),
    @XmlEnumValue("3")      Transfer                            (3),
    @XmlEnumValue("4")      LateTrade                           (4),
    @XmlEnumValue("5")      TTrade                              (5),
    @XmlEnumValue("6")      WeightedAvgPriceTrade               (6),
    @XmlEnumValue("7")      BunchedTrade                        (7),
    @XmlEnumValue("8")      LateBunchedTrade                    (8),
    @XmlEnumValue("9")      PriorRefPriceTrade                  (9),
    @XmlEnumValue("10")     AfterHoursTrade                     (10),
    @XmlEnumValue("11")     EFR                                 (11),
    @XmlEnumValue("12")     EFS                                 (12),
    @XmlEnumValue("13")     EFM                                 (13),
    @XmlEnumValue("14")     EOO                                 (14),
    @XmlEnumValue("15")     TradingAtSettlement                 (15),
    @XmlEnumValue("16")     AllOrNone                           (16),
    @XmlEnumValue("17")     FuturesLargeOrderExec               (18),
    @XmlEnumValue("18")     EFF                                 (18),
    @XmlEnumValue("19")     OptionInterimTrade                  (19),
    @XmlEnumValue("20")     OptionCabinetTrade                  (20),
    @XmlEnumValue("22")     PrivatelyNegotiatedTrades           (22),
    @XmlEnumValue("23")     SubstOfFuturesforForwards           (23),
    @XmlEnumValue("24")     Errortrade                          (24),
    @XmlEnumValue("25")     SpecialCumDividend                  (25),
    @XmlEnumValue("26")     SpecialExDividend                   (26),
    @XmlEnumValue("27")     SpecialCumCoupon                    (27),
    @XmlEnumValue("28")     SpecialExCoupon                     (28),
    @XmlEnumValue("29")     CashSettlement                      (29),
    @XmlEnumValue("30")     SpecialPrice                        (30),
    @XmlEnumValue("31")     GuaranteedDelivery                  (31),
    @XmlEnumValue("32")     SpecialCumRights                    (32),
    @XmlEnumValue("33")     SpecialExRights                     (33),
    @XmlEnumValue("34")     SpecialCumCapitalRepayments         (34),
    @XmlEnumValue("35")     SpecialExCapitalRepayments          (35),
    @XmlEnumValue("36")     SpecialCumBonus                     (36),
    @XmlEnumValue("37")     SpecialExBonus                      (37),
    @XmlEnumValue("38")     LargeTrade                          (38),
    @XmlEnumValue("39")     WorkedPrincipalTrade                (39),
    @XmlEnumValue("40")     BlockTradesAfterMarket              (40),
    @XmlEnumValue("41")     NameChange                          (41),
    @XmlEnumValue("42")     PortfolioTransfer                   (42),
    @XmlEnumValue("43")     ProrogationBuyEuronextParis         (43),
    @XmlEnumValue("44")     ProrogationSellEuronextParis        (44),
    @XmlEnumValue("45")     OptionExercise                      (45),
    @XmlEnumValue("46")     DeltaNeutralTransaction             (46),
    @XmlEnumValue("47")     FinancingTransactio                 (48),
    @XmlEnumValue("48")     NonStandardSettlement               (48),
    @XmlEnumValue("49")     DerivativeRelatedTransaction        (49),
    @XmlEnumValue("50")     PortfolioTrade                      (50),
    @XmlEnumValue("51")     VolumeWeightedAverageTrade          (51),
    @XmlEnumValue("52")     ExchangeGrantedTrade                (52),
    @XmlEnumValue("53")     RepurchaseAgreement                 (53),
    @XmlEnumValue("54")     OTC                                 (54),
    @XmlEnumValue("55")     EBF                                 (55);

    private static final long serialVersionUID = -1340547506889628741L;

    private int value;

    private static final Map<String, TrdType> stringToEnum = new HashMap<String, TrdType>();

    static {
        for (TrdType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TrdType */
    TrdType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TrdType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
