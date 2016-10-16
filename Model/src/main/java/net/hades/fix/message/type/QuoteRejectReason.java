/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRejectReason.java
 *
 * $Id: QuoteRejectReason.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Reason for quote rejection types.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/07/2009, 5:55:22 PM
 */
public enum QuoteRejectReason {

    UnknownSymbol                           (1),
    ExchangeClosed                          (2),
    QuoteRequestExceedsLimit                (3),
    TooLateToEnter                          (4),
    UnknownQuote                            (5),
    DuplicateQuote                          (6),
    InvalidBidAskSpread                     (7),
    InvalidPrice                            (8),
    NotAuthorizedToQuoteSecurity            (9),
    PriceExceedsCurrentPriceBand            (10),
    QuoteLocked                             (11),
    InvalidOrUnknownSecurityIssuer          (12),
    InvalidOrUnknownIssuerOfUnderlying      (13),
    Other                                   (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, QuoteRejectReason> stringToEnum = new HashMap<String, QuoteRejectReason>();

    static {
        for (QuoteRejectReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteRejectReason */
    QuoteRejectReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteRejectReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
