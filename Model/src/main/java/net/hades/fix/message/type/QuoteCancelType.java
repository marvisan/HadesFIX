/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelType.java
 *
 * $Id: QuoteCancelType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Type of quote cancellations.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 29/06/2009, 9:38:08 AM
 */
public enum QuoteCancelType {

    CancelForOneOrMoreSecurities            (1),
    CancelForSecurityType                   (2),
    CancelForUnderlyingSecurity             (3),
    CancelAllQuotes                         (4),
    CancelSpecifiedQuote                    (5),
    CancelByQuoteType                       (6),
    CancelForSecurityIssuer                 (7),
    CancelForIssuerOfUnderlying             (8);

    private static final long serialVersionUID = -2345134661856340876L;

    private int value;

    private static final Map<String, QuoteCancelType> stringToEnum = new HashMap<String, QuoteCancelType>();

    static {
        for (QuoteCancelType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteCancelType */
    QuoteCancelType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteCancelType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
