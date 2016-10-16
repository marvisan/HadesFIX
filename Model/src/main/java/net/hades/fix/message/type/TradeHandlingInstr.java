/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeHandlingInstr.java
 *
 * $Id: TradeHandlingInstr.java,v 1.1 2011-10-08 08:42:54 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specified how the Trade Capture Report should be handled by the Respondent.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 10/02/2009, 7:01:39 PM
 */
@XmlType
@XmlEnum(String.class)
public enum TradeHandlingInstr {

    @XmlEnumValue("0")    TradeConfirmation                         ("0"),
    @XmlEnumValue("1")    TwoPartyReport                            ("1"),
    @XmlEnumValue("2")    OnePartyReportMatching                    ("2"),
    @XmlEnumValue("3")    OnePartyReportPassThrough                 ("3"),
    @XmlEnumValue("4")    AutomatedFloorOrderRouting                ("4"),
    @XmlEnumValue("5")    TwoPartyReportClaim                       ("5");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, TradeHandlingInstr> stringToEnum = new HashMap<String, TradeHandlingInstr>();

    static {
        for (TradeHandlingInstr tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradeHandlingInstr. */
    TradeHandlingInstr(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TradeHandlingInstr valueFor(String value) {
        return stringToEnum.get(value);
    }
}
