/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectReason.java
 *
 * $Id: QuoteRequestRejectReason.java,v 1.3 2010-01-14 09:06:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Types of reject reasons.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 24/06/2009, 1:39:57 PM
 */
public enum QuoteRequestRejectReason {

    UnknownSymbol                       (1),
    ExchangeClosed                      (2),
    QuoteRequestExceedsLimit            (3),
    TooLateToEnter                      (4),
    InvalidPrice                        (5),
    NotAuthorizedToRequestQuote         (6),
    NoMatchForInquiry                   (7),
    NoMarketForInstrument               (8),
    NoInventory                         (9),
    Pass                                (10),
    InsufficientCredit                  (11),
    Other                               (99);

    private static final long serialVersionUID = -1066970344005500457L;

    private int value;

    private static final Map<String, QuoteRequestRejectReason> stringToEnum = new HashMap<String, QuoteRequestRejectReason>();

    static {
        for (QuoteRequestRejectReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteRequestRejectReason */
    QuoteRequestRejectReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteRequestRejectReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
