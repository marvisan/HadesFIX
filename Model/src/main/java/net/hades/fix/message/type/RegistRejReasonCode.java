/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistRejReasonCode.java
 *
 * $Id: RegistRejReasonCode.java,v 1.4 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason code why Registration Instructions has been rejected.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 24/09/2009, 8:54:59 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum RegistRejReasonCode {

    @XmlEnumValue("1")  InvalidAccountType                      (1),
    @XmlEnumValue("2")  InvalidTaxExemptType                    (2),
    @XmlEnumValue("3")  InvalidOwnershipType                    (3),
    @XmlEnumValue("4")  InvalidNoRegDetails                     (4),
    @XmlEnumValue("5")  InvalidRegSeqNo                         (5),
    @XmlEnumValue("6")  InvalidRegDetails                       (6),
    @XmlEnumValue("7")  InvalidMailingDetails                   (7),
    @XmlEnumValue("8")  InvalidMailingInstructions              (8),
    @XmlEnumValue("0")  InvalidInvestorID                       (9),
    @XmlEnumValue("10") InvalidInvestorIDSource                 (10),
    @XmlEnumValue("11") InvalidDateOfBirth                      (11),
    @XmlEnumValue("12") InvalidInvestorCountryOfResidence       (12),
    @XmlEnumValue("13") InvalidNoDistribInstns                  (13),
    @XmlEnumValue("14") InvalidDistribPercentage                (14),
    @XmlEnumValue("15") InvalidDistribPaymentMethod             (15),
    @XmlEnumValue("16") InvalidCashDistribAgentAcctName         (16),
    @XmlEnumValue("17") InvalidCashDistribAgentCode             (17),
    @XmlEnumValue("18") InvalidCashDistribAgentAcctNum          (18),
    @XmlEnumValue("99") Other                                   (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, RegistRejReasonCode> stringToEnum = new HashMap<String, RegistRejReasonCode>();

    static {
        for (RegistRejReasonCode tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RegistRejReasonCode */
    RegistRejReasonCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RegistRejReasonCode valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
