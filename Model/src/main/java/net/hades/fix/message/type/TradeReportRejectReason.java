/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeReportRejectReason.java
 *
 * $Id: AllocHandlInst.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason Trade Capture Request was rejected.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 29/11/2011, 8:59:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradeReportRejectReason {

    @XmlEnumValue("0")  Successful                              (0),
    @XmlEnumValue("1")  InvalidPartyInformation                 (1),
    @XmlEnumValue("2")  UnknownInstrument                       (2),
    @XmlEnumValue("3")  UnauthorizedToReportTrades              (3),
    @XmlEnumValue("4")  InvalidTradeType                        (4),
    @XmlEnumValue("99") Other                                   (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TradeReportRejectReason> stringToEnum = new HashMap<String, TradeReportRejectReason>();

    static {
        for (TradeReportRejectReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradeReportRejectReason */
    TradeReportRejectReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradeReportRejectReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
