/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * YieldType.java
 *
 * $Id: YieldType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of yield.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 10/02/2009, 7:20:51 PM
 */
@XmlType
@XmlEnum(String.class)
public enum YieldType {

    @XmlEnumValue("AFTERTAX")       AfterTaxYield                           ("AFTERTAX"),
    @XmlEnumValue("ANNUAL")         AnnualYield                             ("ANNUAL"),
    @XmlEnumValue("ATISSUE")        YieldAtIssue                            ("ATISSUE"),
    @XmlEnumValue("AVGMATURITY")    YieldToAvgMaturity                      ("AVGMATURITY"),
    @XmlEnumValue("BOOK")           BookYield                               ("BOOK"),
    @XmlEnumValue("CALL")           YieldToNextCall                         ("CALL"),
    @XmlEnumValue("CHANGE")         YieldChangeSinceClose                   ("CHANGE"),
    @XmlEnumValue("CLOSE")          ClosingYield                            ("CLOSE"),
    @XmlEnumValue("COMPOUND")       CompoundYield                           ("COMPOUND"),
    @XmlEnumValue("CURRENT")        CurrentYield                            ("CURRENT"),
    @XmlEnumValue("GOVTEQUIV")      GvntEquivalentYield                     ("GOVTEQUIV"),
    @XmlEnumValue("GROSS")          TrueGrossYield                          ("GROSS"),
    @XmlEnumValue("INFLATION")      YieldWithInflationAssumption            ("INFLATION"),
    @XmlEnumValue("INVERSEFLOATER") InverseFloaterBondYield                 ("INVERSEFLOATER"),
    @XmlEnumValue("LASTCLOSE")      MostRecentClosingYield                  ("LASTCLOSE"),
    @XmlEnumValue("LASTMONTH")      ClosingYieldMostRecentMonth             ("LASTMONTH"),
    @XmlEnumValue("LASTQUARTER")    ClosingYieldMostRecentQuarter           ("LASTQUARTER"),
    @XmlEnumValue("LASTYEAR")       ClosingYieldMostRecentYear              ("LASTYEAR"),
    @XmlEnumValue("LONGAVGLIFE")    YieldToLongestAverageLife               ("LONGAVGLIFE"),
    @XmlEnumValue("MARK")           MarkToMarketYield                       ("MARK"),
    @XmlEnumValue("MATURITY")       YieldToMaturity                         ("MATURITY"),
    @XmlEnumValue("NEXTREFUND")     YieldToNextRefund                       ("NEXTREFUND"),
    @XmlEnumValue("OPENAVG")        OpenAverageYield                        ("OPENAVG"),
    @XmlEnumValue("PREVCLOSE")      PreviousCloseYield                      ("PREVCLOSE"),
    @XmlEnumValue("PROCEEDS")       ProceedsYield                           ("PROCEEDS"),
    @XmlEnumValue("PUT")            YieldToNextPut                          ("PUT"),
    @XmlEnumValue("SEMIANNUAL")     SemiAnnualYield                         ("SEMIANNUAL"),
    @XmlEnumValue("SHORTAVGLIFE")   YieldToShortestAverageLife              ("SHORTAVGLIFE"),
    @XmlEnumValue("SIMPLE")         SimpleYield                             ("SIMPLE"),
    @XmlEnumValue("TAXEQUIV")       TaxEquivalentYield                      ("TAXEQUIV"),
    @XmlEnumValue("TENDER")         YieldToTenderDate                       ("TENDER"),
    @XmlEnumValue("TRUE")           TrueYield                               ("TRUE"),
    @XmlEnumValue("VALUE1_32")      YieldValueOf1Per32                      ("VALUE1_32"),
    @XmlEnumValue("WORST")          YieldToWorst                            ("WORST");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, YieldType> stringToEnum = new HashMap<String, YieldType>();

    static {
        for (YieldType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of YieldType */
    YieldType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static YieldType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
