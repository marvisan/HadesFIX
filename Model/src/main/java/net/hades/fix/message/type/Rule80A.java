/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Rule80A.java
 *
 * $Id: Rule80A.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Order capacity type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 5/07/2008, 16:34:37
 */
public enum Rule80A {

    AgencySingleOrder                           ("A"),
    ShortExemptTX                               ("B"),
    ProgramOrderNonIndexArbFirm                 ("C"),
    ProgramOrderIndexArbFirm                    ("D"),
    RegisteredEquityMarket                      ("E"),
    ShortExemptTXTypeW                          ("F"),
    ShortExemptTXTypeI                          ("H"),
    IndividualInvestor                          ("I"),
    ProgramOrderIndexArbInd                     ("J"),
    ProgramOrderNonIndexArbInd                  ("K"),
    ShortExemptTXTypePO                         ("L"),
    ProgramOrderIndexArbOther                   ("M"),
    rogramOrderNonIndexArbOther                 ("N"),
    CompetingDealerTrades                       ("O"),
    Principal                                   ("P"),
    CompetingDealerTrades2                      ("R"),
    SpecialistTrades                            ("S"),
    CompetingDealerTrades3                      ("T"),
    ProgramOrderIndexArbAgency                  ("U"),
    AllOtherOrdersAsAgent                       ("W"),
    ShortExemptTXTypeWT                         ("X"),
    ProgramOrderNonIndexArbAgency               ("Y"),
    ShortExemptTXTypeAR                         ("Z");

    private static final long serialVersionUID = -810399940856294779L;
    
    private String value;

    private static final Map<String, Rule80A> stringToEnum = new HashMap<String, Rule80A>();

    static {
        for (Rule80A tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of Rule80A */
    Rule80A(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static Rule80A valueFor(String value) {
        return stringToEnum.get(value);
    }
}
