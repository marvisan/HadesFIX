/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MatchType.java
 *
 * $Id: MatchType.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The point in the matching process at which this trade was matched.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 8:51:08 PM
 */
@XmlType
@XmlEnum(String.class)
public enum MatchType {

    @XmlEnumValue("1") OnePartyTradeReport                      ("1"),
    @XmlEnumValue("2") TwoPartyTradeReport                      ("2"),
    @XmlEnumValue("3") ConfirmedTradeReport                     ("3"),
    @XmlEnumValue("4") AutoMatch                                ("4"),
    @XmlEnumValue("5") CrossAuction                             ("5"),
    @XmlEnumValue("6") CounterOrderSelection                    ("6"),
    @XmlEnumValue("7") CallAuction                              ("7"),
    @XmlEnumValue("8") BuyBackAuction                           ("8"),
    @XmlEnumValue("M3") ACTAcceptedTrade                        ("M3"),
    @XmlEnumValue("M4") ACTDefaultTrade                         ("M4"),
    @XmlEnumValue("M5") ACTDefaultAfterM2                       ("M5"),
    @XmlEnumValue("M6") ACTM6Match                              ("M6"),
    @XmlEnumValue("A1") ExactMatchOnTradeDate2MinWindow         ("A1"),
    @XmlEnumValue("A2") ExactMatchOnTradeDate4Badges            ("A2"),
    @XmlEnumValue("A3") ExactMatchOnTradeDate2BadgesExecTime    ("A3"),
    @XmlEnumValue("A4") ExactMatchOnTradeDate2Badges            ("A4"),
    @XmlEnumValue("A5") ExactMatchOnTradeDateExecTime           ("A5"),
    @XmlEnumValue("AQ") ComparedRecords                         ("AQ"),
    @XmlEnumValue("S1") SummarizedMatchUsingA1                  ("S1"),
    @XmlEnumValue("S2") SummarizedMatchUsingA2                  ("S2"),
    @XmlEnumValue("S3") SummarizedMatchUsingA3                  ("S3"),
    @XmlEnumValue("S4") SummarizedMatchUsingA4                  ("S4"),
    @XmlEnumValue("S5") SummarizedMatchUsingA5                  ("S5"),
    @XmlEnumValue("M1") SummarizedMatchUsingACTM1               ("M1"),
    @XmlEnumValue("M2") SummarizedMatchUsingACTM2               ("M2"),
    @XmlEnumValue("MT") OCSLockedIn                             ("MT");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, MatchType> stringToEnum = new HashMap<String, MatchType>();

    static {
        for (MatchType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of MatchType */
    MatchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MatchType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
