/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeReportType.java
 *
 * $Id: TradeReportType.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type or trade report.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradeReportType {

    @XmlEnumValue("0")  Submit                                  (0),
    @XmlEnumValue("1")  Alleged                                 (1),
    @XmlEnumValue("2")  Accept                                  (2),
    @XmlEnumValue("3")  Decline                                 (3),
    @XmlEnumValue("4")  Addendum                                (4),
    @XmlEnumValue("5")  No_Was                                  (5),
    @XmlEnumValue("6")  TradeReportCancel                       (6),
    @XmlEnumValue("7")  LockedInTradeBreak                      (7),
    @XmlEnumValue("8")  Defaulted                               (8),
    @XmlEnumValue("9")  InvalidCMTA                             (9),
    @XmlEnumValue("10") Pended                                  (10),
    @XmlEnumValue("11") AllegedNew                             (11),
    @XmlEnumValue("12") AllegedAddendum                        (12),
    @XmlEnumValue("13") AllegedNo_Was                          (13),
    @XmlEnumValue("14") AllegedTradeReportCancel               (14),
    @XmlEnumValue("15") AllegedLockedInTradeBreak              (15);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TradeReportType> stringToEnum = new HashMap<String, TradeReportType>();

    static {
        for (TradeReportType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradeReportType */
    TradeReportType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradeReportType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
