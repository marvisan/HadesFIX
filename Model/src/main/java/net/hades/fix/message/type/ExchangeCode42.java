/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExchangeCode42.java
 *
 * $Id: ExchangeCode42.java,v 1.5 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Exchange codes for FIX 4.2.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 1/07/2008, 19:29:35
 */
public enum ExchangeCode42 {

    AmericanStockExchange                           ("A"),
    AmericanStockExchangeOptions                    ("1"),
    AustralianStockExchange                         ("AX"),
    BerlinStockExchange                             ("BE"),
    BostonStockExchange                             ("B"),
    ChicagoBoardOptionsExchange                     ("W"),
    ChicagoStockExchange                            ("MW"),
    HongKongStockExchange                           ("HK"),
    KoreaStockExchange                              ("KS"),
    LIFFE                                           ("3"),
    LondonStockExchange                             ("L"),
    LondonTradedOptionsMarket                       ("5"),
    MilanStockExchange                              ("MI"),
    NASDAQ                                          ("O"),
    NYMEX                                           ("12"),
    NewYorkStockExchange                            ("N"),
    NewZealandStockExchange                         ("NZ"),
    ParisStockExchange                              ("PA"),
    TokyoStockExchange                              ("T");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, ExchangeCode42> stringToEnum = new HashMap<String, ExchangeCode42>();

    static {
        for (ExchangeCode42 tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of ExchangeCode42 */
    ExchangeCode42(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static ExchangeCode42 valueFor(String value) {
        return stringToEnum.get(value);
    }
}
