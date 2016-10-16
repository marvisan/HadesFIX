/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCondition.java
 *
 * $Id: TradeCondition.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of conditions describing a trade.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 12:48:05 PM
 */
@XmlType
@XmlEnum(String.class)
public enum TradeCondition {

    @XmlEnumValue("A") CashMarket                                      ("A"),
    @XmlEnumValue("B") AveragePriceTrade                               ("B"),
    @XmlEnumValue("C") CashTrade                                       ("C"),
    @XmlEnumValue("D") NextDayMarket                                   ("D"),
    @XmlEnumValue("E") OpeningTradeDetail                              ("E"),
    @XmlEnumValue("F") IntradayTradeDetail                             ("F"),
    @XmlEnumValue("G") Rule127Trade                                    ("G"),
    @XmlEnumValue("H") Rule155Trade                                    ("H"),
    @XmlEnumValue("I") SoldLast                                        ("I"),
    @XmlEnumValue("J") NextDayTrade                                    ("J"),
    @XmlEnumValue("K") Opened                                          ("K"),
    @XmlEnumValue("L") Seller                                          ("L"),
    @XmlEnumValue("M") Sold                                            ("M"),
    @XmlEnumValue("N") StoppedStock                                    ("N"),
    @XmlEnumValue("P") ImbalanceMoreBuyers                             ("P"),
    @XmlEnumValue("Q") ImbalanceMoreSellers                            ("Q"),
    @XmlEnumValue("R") OpeningPrice                                     ("R"),
    @XmlEnumValue("S") BargainCondition                                ("S"),
    @XmlEnumValue("T") ConvertedPriceIndicator                         ("T"),
    @XmlEnumValue("U") ExchangeLast                                    ("U"),
    @XmlEnumValue("V") FinalPriceOfSession                             ("V"),
    @XmlEnumValue("W") ExPit                                           ("W"),
    @XmlEnumValue("X") Crossed                                         ("X"),
    @XmlEnumValue("Y") TradesResultFromManualQuote                     ("Y"),
    @XmlEnumValue("X") TradesResultFromIntermarketSweep                ("Z"),
    @XmlEnumValue("a") VolumeOnly                                      ("a"),
    @XmlEnumValue("b") DirectPlus                                      ("b"),
    @XmlEnumValue("c") Acquisition                                     ("c"),
    @XmlEnumValue("d") Bunched                                         ("d"),
    @XmlEnumValue("e") Distribution                                    ("e"),
    @XmlEnumValue("f") BunchedSale                                     ("f"),
    @XmlEnumValue("g") SplitTrade                                      ("g"),
    @XmlEnumValue("h") CancelStopped                                   ("h"),
    @XmlEnumValue("i") CancelETH                                       ("i"),
    @XmlEnumValue("j") CancelStoppedETH                                ("j"),
    @XmlEnumValue("k") OutOfSequenceETH                                ("k"),
    @XmlEnumValue("l") CancelLastETH                                   ("l"),
    @XmlEnumValue("m") SoldLastSaleETH                                 ("m"),
    @XmlEnumValue("n") CancelLast                                      ("n"),
    @XmlEnumValue("o") SoldLastSale                                    ("o"),
    @XmlEnumValue("p") CancelOpen                                      ("p"),
    @XmlEnumValue("q") CancelOpenETH                                   ("q"),
    @XmlEnumValue("r") OpenedSaleETH                                   ("r"),
    @XmlEnumValue("s") CancelOnly                                      ("s"),
    @XmlEnumValue("t") CancelOnlyETH                                   ("t"),
    @XmlEnumValue("u") LateOpenETH                                     ("u"),
    @XmlEnumValue("v") AutoExecutionETH                                ("v"),
    @XmlEnumValue("w") Reopen                                          ("w"),
    @XmlEnumValue("x") ReopenETH                                       ("x"),
    @XmlEnumValue("y") Adjusted                                        ("y"),
    @XmlEnumValue("z") AdjustedETH                                     ("z"),
    @XmlEnumValue("AA") Spread                                         ("AA"),
    @XmlEnumValue("AB") SpreadETH                                      ("AB"),
    @XmlEnumValue("AC") Straddle                                       ("AC"),
    @XmlEnumValue("AD") StraddleETH                                    ("AD"),
    @XmlEnumValue("AE") Stopped                                        ("AE"),
    @XmlEnumValue("AF") StoppedETH                                     ("AF"),
    @XmlEnumValue("AG") RegularETH                                     ("AG"),
    @XmlEnumValue("AH") Combo                                          ("AH"),
    @XmlEnumValue("AI") ComboETH                                       ("AI"),
    @XmlEnumValue("AJ") OfficialClosingPrice                           ("AJ"),
    @XmlEnumValue("AK") PriorReferencePrice                            ("AK"),
    @XmlEnumValue("0") Cancel                                          ("0"),
    @XmlEnumValue("AL") StoppedSoldLast                                ("AL"),
    @XmlEnumValue("AM") StoppedOutOfSequence                           ("AM"),
    @XmlEnumValue("AN") OfficalClosingPriceDuplicate                   ("AN"),
    @XmlEnumValue("AO") CrossedDuplicate                               ("AO"),
    @XmlEnumValue("AP") FastMarket                                     ("AP"),
    @XmlEnumValue("AQ") AutomaticExecution                             ("AQ"),
    @XmlEnumValue("AR") FormT                                          ("AR"),
    @XmlEnumValue("AS") BasketIndex                                    ("AS"),
    @XmlEnumValue("AT") BurstBasket                                    ("AT"),
    @XmlEnumValue("AV") OutsideSpread                                  ("AV"),
    @XmlEnumValue("1") ImpliedTrade                                    ("1"),
    @XmlEnumValue("2") MarketplaceEnteredTrade                         ("2"),
    @XmlEnumValue("3") MultAssetClassMultilegTrade                     ("3"),
    @XmlEnumValue("4") MultilegToMultilegTrade                         ("4");

    private static final long serialVersionUID = -8855725918416924010L;

    private String value;

    private static final Map<String, TradeCondition> stringToEnum = new HashMap<String, TradeCondition>();

    static {
        for (TradeCondition tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of TradeCondition. */
    TradeCondition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TradeCondition valueFor(String value) {
        return stringToEnum.get(value);
    }

}
