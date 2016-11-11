/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MiscFeeType.java
 *
 * $Id: MiscFeeType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of miscellaneous fee.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 23/08/2009, 4:22:26 PM
 */
@XmlType
@XmlEnum(String.class)
public enum MiscFeeType {

    @XmlEnumValue("1") Regulatory                   ("1"),
    @XmlEnumValue("2") Tax                          ("2"),
    @XmlEnumValue("3") LocalCommission              ("3"),
    @XmlEnumValue("4") ExchangeFees                 ("4"),
    @XmlEnumValue("5") Stamp                        ("5"),
    @XmlEnumValue("6") Levy                         ("6"),
    @XmlEnumValue("7") Other                        ("7"),
    @XmlEnumValue("8") Markup                       ("8"),
    @XmlEnumValue("9") ConsumptionTax               ("9"),
    @XmlEnumValue("10") PerTransaction              ("10"),
    @XmlEnumValue("11") Conversion                  ("11"),
    @XmlEnumValue("12") Agent                       ("12"),
    @XmlEnumValue("13") TransferFee                 ("13"),
    @XmlEnumValue("14") SecurityLending             ("14");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, MiscFeeType> stringToEnum = new HashMap<String, MiscFeeType>();

    static {
        for (MiscFeeType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of MiscFeeType */
    MiscFeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MiscFeeType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
