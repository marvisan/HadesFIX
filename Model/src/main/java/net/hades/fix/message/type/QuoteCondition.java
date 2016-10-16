/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCondition.java
 *
 * $Id: QuoteCondition.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Conditions describing a quote.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 01/09/2009, 9:30:10 PM
 */
@XmlType
@XmlEnum(String.class)
public enum QuoteCondition {

    @XmlEnumValue("A") Open                                    ("A"),
    @XmlEnumValue("B") Closed                                  ("B"),
    @XmlEnumValue("C") ExchangeBest                            ("C"),
    @XmlEnumValue("D") ConsolidatedBest                        ("D"),
    @XmlEnumValue("E") Locked                                  ("E"),
    @XmlEnumValue("F") Crossed                                 ("F"),
    @XmlEnumValue("G") Depth                                   ("G"),
    @XmlEnumValue("H") FastTrading                             ("H"),
    @XmlEnumValue("I") NonFirm                                 ("I"),
    @XmlEnumValue("L") SlowQuote                               ("L"),
    @XmlEnumValue("J") OutrightPrice                           ("J"),
    @XmlEnumValue("K") ImpliedPrice                            ("K"),
    @XmlEnumValue("M") DepthOnOffer                            ("M"),
    @XmlEnumValue("N") DepthOnBid                              ("N"),
    @XmlEnumValue("O") Closing                                 ("O"),
    @XmlEnumValue("P") NewsDissemination                       ("P"),
    @XmlEnumValue("Q") TradingRange                            ("Q"),
    @XmlEnumValue("P") OrderInflux                             ("P"),
    @XmlEnumValue("S") DueToRelated                            ("S"),
    @XmlEnumValue("T") NewsPending                             ("T"),
    @XmlEnumValue("U") AdditionalInfo                          ("U"),
    @XmlEnumValue("V") AdditionalInfoDueRelated                ("V"),
    @XmlEnumValue("W") Resume                                  ("W"),
    @XmlEnumValue("X") ViewOfCommon                            ("X"),
    @XmlEnumValue("Y") VolumeAlert                             ("Y"),
    @XmlEnumValue("Z") OrderImbalance                          ("Z"),
    @XmlEnumValue("a") EquipmentChangeover                     ("a"),
    @XmlEnumValue("b") NoOpen                                  ("b"),
    @XmlEnumValue("c") RegularETH                              ("c"),
    @XmlEnumValue("d") AutomaticExecution                      ("d"),
    @XmlEnumValue("e") AutomaticExecutionETH                   ("e"),
    @XmlEnumValue("f") FastMarketETH                           ("f"),
    @XmlEnumValue("g") InactiveETH                             ("g"),
    @XmlEnumValue("h") Rotation                                ("h"),
    @XmlEnumValue("i") RotationETH                             ("i"),
    @XmlEnumValue("j") Halt                                    ("j"),
    @XmlEnumValue("k") HaltETH                                 ("k"),
    @XmlEnumValue("l") DueToNewsDissemination                  ("l"),
    @XmlEnumValue("m") DueToNewsPending                        ("m"),
    @XmlEnumValue("n") TradingResume                           ("n"),
    @XmlEnumValue("o") OutOfSequence                           ("o"),
    @XmlEnumValue("p") BidSpecialist                           ("p"),
    @XmlEnumValue("q") OfferSpecialist                         ("q"),
    @XmlEnumValue("r") BidOfferSpecialist                      ("r"),
    @XmlEnumValue("s") EndOfDaySAM                             ("s"),
    @XmlEnumValue("t") ForbiddenSAM                            ("t"),
    @XmlEnumValue("u") FrozenSAM                               ("u"),
    @XmlEnumValue("v") PreOpeningSAM                           ("v"),
    @XmlEnumValue("w") OpeningSAM                              ("w"),
    @XmlEnumValue("x") OpenSAM                                 ("x"),
    @XmlEnumValue("y") SurveillanceSAM                         ("y"),
    @XmlEnumValue("s") SuspendedSAM                            ("z"),
    @XmlEnumValue("0") ReservedSAM                             ("0"),
    @XmlEnumValue("1") NoActiveSAM                             ("1"),
    @XmlEnumValue("2") Restricted                              ("2"),
    @XmlEnumValue("3") RestOfBookVWAP                          ("3"),
    @XmlEnumValue("4") BetterPrices                            ("4"),
    @XmlEnumValue("5") MedianPrice                             ("5"),
    @XmlEnumValue("6") FullCurve                               ("6"),
    @XmlEnumValue("7") FlatCurve                               ("7");

    private static final long serialVersionUID = -7020881645002391759L;

    private String value;

    private static final Map<String, QuoteCondition> stringToEnum = new HashMap<String, QuoteCondition>();

    static {
        for (QuoteCondition tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of QuoteCondition. */
    QuoteCondition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static QuoteCondition valueFor(String value) {
        return stringToEnum.get(value);
    }
}
