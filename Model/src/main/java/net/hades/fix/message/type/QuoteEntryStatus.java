/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteEntryStatus.java
 *
 * $Id: QuoteEntryStatus.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Types of status of a mass quote.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 22/07/2009, 9:21:59 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum QuoteEntryStatus {

    @XmlEnumValue("0") Accepted                             (0),
    @XmlEnumValue("5") Rejected                             (5),
    @XmlEnumValue("6") RemovedFromMarket                    (6),
    @XmlEnumValue("7") Expired                              (7),
    @XmlEnumValue("12") LockedMarketWarning                 (12),
    @XmlEnumValue("13") CrossMarketWarning                  (13),
    @XmlEnumValue("14") CanceledDueToLockMarket             (14),
    @XmlEnumValue("15") CanceledDueToCrossMarket            (15),
    @XmlEnumValue("16") Active                              (16);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, QuoteEntryStatus> stringToEnum = new HashMap<String, QuoteEntryStatus>();

    static {
        for (QuoteEntryStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteEntryStatus */
    QuoteEntryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteEntryStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
