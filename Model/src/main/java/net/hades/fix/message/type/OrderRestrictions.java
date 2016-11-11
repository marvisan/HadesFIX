/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderRestrictions.java
 *
 * $Id: OrderRestrictions.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Restrictions associated with an order.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 7/07/2008, 20:46:39
 */
public enum OrderRestrictions {

    ProgramTrade                        ("1"),
    IndexArbitrage                      ("2"),
    NonIndexArbitrage                   ("3"),
    CompetingMarketMaker                ("4"),
    ActingAsMarketMakerInSecurity       ("5"),
    ActingAsMarketMakerInUnderlying     ("6"),
    ForeignEntity                       ("7"),
    ExternalMarketParticipant           ("8"),
    ExternalInterConnectedMarket        ("9"),
    RisklessArbitrage                   ("A"),
    IssuerHolding                       ("B"),
    IssuePriceStabilization             ("C"),
    NonAlgorithmic                      ("D"),
    Algorithmic                         ("E"),
    Cross                               ("F");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, OrderRestrictions> stringToEnum = new HashMap<String, OrderRestrictions>();

    static {
        for (OrderRestrictions tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of OrderRestrictions */
    OrderRestrictions(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static OrderRestrictions valueFor(String value) {
        return stringToEnum.get(value);
    }
}
