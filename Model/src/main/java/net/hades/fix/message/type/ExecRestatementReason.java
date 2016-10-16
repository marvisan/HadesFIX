/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecRestatementReason.java
 *
 * $Id: ExecRestatementReason.java,v 1.5 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to identify reason for an ExecutionRpt message sent.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 05/09/2009, 3:36:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ExecRestatementReason {

    @XmlEnumValue("0")      GTCorporateAction               (0),
    @XmlEnumValue("1")      GTRenewal                       (1),
    @XmlEnumValue("2")      VerbalChange                    (2),
    @XmlEnumValue("3")      RepricingOfOrder                (3),
    @XmlEnumValue("4")      BrokerOption                    (4),
    @XmlEnumValue("5")      PartDeclineOfOrderQty           (5),
    @XmlEnumValue("6")      CancelOnTradingHalt             (6),
    @XmlEnumValue("7")      CancelOnSystemFailure           (7),
    @XmlEnumValue("8")      MarketOption                    (8),
    @XmlEnumValue("9")      Canceled                        (9),
    @XmlEnumValue("10")     WarehouseRecap                  (10),
    @XmlEnumValue("11")     PegRefresh                      (11),
    @XmlEnumValue("99")     Other                           (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ExecRestatementReason> stringToEnum = new HashMap<String, ExecRestatementReason>();

    static {
        for (ExecRestatementReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ExecRestatementReason */
    ExecRestatementReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ExecRestatementReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
