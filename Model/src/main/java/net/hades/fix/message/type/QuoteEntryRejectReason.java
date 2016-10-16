/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteEntryRejectReason.java
 *
 * $Id: QuoteEntryRejectReason.java,v 1.4 2010-02-25 08:37:29 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason quote was rejected.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 22/07/2009, 9:11:10 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum QuoteEntryRejectReason {

    @XmlEnumValue("1")  UnknownSymbol                           (1),
    @XmlEnumValue("2")  ExchangeClosed                          (2),
    @XmlEnumValue("3")  QuoteRequestExceedsLimit                (3),
    @XmlEnumValue("4")  TooLateToEnter                          (4),
    @XmlEnumValue("5")  UnknownQuote                            (5),
    @XmlEnumValue("6")  DuplicateQuote                          (6),
    @XmlEnumValue("7")  InvalidBidAskSpread                     (7),
    @XmlEnumValue("8")  InvalidPrice                            (8),
    @XmlEnumValue("9")  NotAuthorizedToQuoteSecurity            (9),
    @XmlEnumValue("10") PriceExceedsCurrentPriceBand            (10),
    @XmlEnumValue("11") QuoteLocked                             (11),
    @XmlEnumValue("12") InvalidOrUnknownSecurityIssuer          (12),
    @XmlEnumValue("13") InvalidOrUnknownIssuerOfUnderlying      (13),
    @XmlEnumValue("99") Other                                   (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, QuoteEntryRejectReason> stringToEnum = new HashMap<String, QuoteEntryRejectReason>();

    static {
        for (QuoteEntryRejectReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteEntryRejectReason */
    QuoteEntryRejectReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteEntryRejectReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
