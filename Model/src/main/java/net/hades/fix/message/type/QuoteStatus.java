/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatus.java
 *
 * $Id: QuoteStatus.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Types of quote status.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 03/07/2009, 4:35:56 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum QuoteStatus {

    @XmlEnumValue("0") Accepted                             (0),
    @XmlEnumValue("1") CancelForSymbol                      (1),
    @XmlEnumValue("2") CanceledForSecurityType              (2),
    @XmlEnumValue("3") CanceledForUnderlying                (3),
    @XmlEnumValue("4") CanceledAll                          (4),
    @XmlEnumValue("5") Rejected                             (5),
    @XmlEnumValue("6") RemovedFromMarket                    (6),
    @XmlEnumValue("7") Expired                              (7),
    @XmlEnumValue("8") Query                                (8),
    @XmlEnumValue("9") QuoteNotFound                        (9),
    @XmlEnumValue("10") Pending                             (10),
    @XmlEnumValue("11") Pass                                (11),
    @XmlEnumValue("12") LockedMarketWarning                 (12),
    @XmlEnumValue("13") CrossMarketWarning                  (13),
    @XmlEnumValue("14") CanceledDueToLockMarket             (14),
    @XmlEnumValue("15") CanceledDueToCrossMarket            (15),
    @XmlEnumValue("16") Active                              (16),
    @XmlEnumValue("17") Canceled                            (17),
    @XmlEnumValue("18") UnsolicitedQuoteReplenishment       (18),
    @XmlEnumValue("19") PendingEndTrade                     (19),
    @XmlEnumValue("20") TooLateToEnd                        (20);

    private static final long serialVersionUID = -5253940118500882873L;

    private int value;

    private static final Map<String, QuoteStatus> stringToEnum = new HashMap<String, QuoteStatus>();

    static {
        for (QuoteStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteStatus */
    QuoteStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
