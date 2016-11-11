/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecondaryTrdType.java
 *
 * $Id: SecondaryTrdType.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Secondary trading type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SecondaryTrdType {

    @XmlEnumValue("0")  RegularTrade                                (0),
    @XmlEnumValue("1")  BlockTrade                                  (1),
    @XmlEnumValue("2")  EFP                                         (2),
    @XmlEnumValue("3")  Transfer                                    (3),
    @XmlEnumValue("4")  LateTrade                                   (4),
    @XmlEnumValue("5")  T_Trade                                     (5),
    @XmlEnumValue("6")  WeightedAveragePriceTrade                   (6),
    @XmlEnumValue("7")  BunchedTrade                                (7),
    @XmlEnumValue("8")  LateBunchedTrade                            (8),
    @XmlEnumValue("9")  PriorRefPriceTrade                          (9),
    @XmlEnumValue("10") AfterHoursTrade                             (10),
    @XmlEnumValue("11") ExchangeForRisk                             (11),
    @XmlEnumValue("12") ExchangeForSwap                             (12),
    @XmlEnumValue("13") ExchangeOfFutures                           (13),
    @XmlEnumValue("14") ExchangeOfOptions                           (14),
    @XmlEnumValue("15") TradingAtSettlement                         (15),
    @XmlEnumValue("16") AllOrNone                                   (16),
    @XmlEnumValue("17") FuturesLargeOrderExecution                  (17),
    @XmlEnumValue("18") ExchangeFuturesForFutures                   (18),
    @XmlEnumValue("19") OptionInterimTrade                          (19),
    @XmlEnumValue("20") OptionCabinetTrade                          (20),
    @XmlEnumValue("22") PrivatelyNegotiatedTrades                   (22),
    @XmlEnumValue("23") SubstitutionFuturesForForwards              (23),
    @XmlEnumValue("24") ErrorTrade                                  (24),
    @XmlEnumValue("25") SpecialCumDividend                          (25),
    @XmlEnumValue("26") SpecialExDividend                           (26),
    @XmlEnumValue("27") SpecialCumCoupon                            (27),
    @XmlEnumValue("28") SpecialExCoupon                             (28),
    @XmlEnumValue("29") CashSettlement                              (29),
    @XmlEnumValue("30") SpecialPrice                                (30),
    @XmlEnumValue("31") GuaranteedDelivery                          (31),
    @XmlEnumValue("32") SpecialCumRights                            (32),
    @XmlEnumValue("33") SpecialExRights                             (33),
    @XmlEnumValue("34") SpecialCumCapitalRepayments                 (34),
    @XmlEnumValue("35") SpecialExCapitalRepayments                  (35),
    @XmlEnumValue("36") SpecialCumBonus                             (36),
    @XmlEnumValue("37") SpecialExBonus                              (37),
    @XmlEnumValue("38") BlockTradeAsLarge                           (38),
    @XmlEnumValue("39") WorkedPrincipalTrade                        (39),
    @XmlEnumValue("40") BlockTradesAfterMarket                      (40),
    @XmlEnumValue("41") NameChange                                  (41),
    @XmlEnumValue("42") PortfolioTransfer                           (42),
    @XmlEnumValue("43") ProrogationBuyEuronextParis                 (43),
    @XmlEnumValue("44") ProrogationSellEuronextParis                (44),
    @XmlEnumValue("45") OptionExercise                              (45),
    @XmlEnumValue("46") DeltaNeutralTransaction                     (46),
    @XmlEnumValue("47") FinancingTransaction                        (47),
    @XmlEnumValue("48") NonStandardSettlement                       (48),
    @XmlEnumValue("49") DerivativeRelatedTransaction                (49),
    @XmlEnumValue("50") PortfolioTrade                              (50),
    @XmlEnumValue("51") VolumeWeightedAverageTrade                  (51),
    @XmlEnumValue("52") ExchangeGrantedTrade                        (52),
    @XmlEnumValue("53") RepurchaseAgreement                         (53),
    @XmlEnumValue("54") OTC                                         (54),
    @XmlEnumValue("55") ExchangeBasisFacility                       (55);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SecondaryTrdType> stringToEnum = new HashMap<String, SecondaryTrdType>();

    static {
        for (SecondaryTrdType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SecondaryTrdType */
    SecondaryTrdType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecondaryTrdType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
