/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContAmtType.java
 *
 * $Id: ContAmtType.java,v 1.4 2011-01-03 07:28:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of {@link TagNum#ContAmtValue} (520).
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 24/09/2009, 9:30:38 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ContAmtType {

    @XmlEnumValue("1")  CommissionAmount                        (1),
    @XmlEnumValue("2")  CommissionPercent                       (2),
    @XmlEnumValue("3")  InitialChargeAmount                     (3),
    @XmlEnumValue("4")  InitialChargePercent                    (4),
    @XmlEnumValue("5")  DiscountAmount                          (5),
    @XmlEnumValue("6")  DiscountPercent                         (6),
    @XmlEnumValue("7")  DilutionLevyAmount                      (7),
    @XmlEnumValue("8")  DilutionLevyPercent                     (8),
    @XmlEnumValue("9")  ExitChargeAmount                        (9),
    @XmlEnumValue("10") ExitChargePercent                       (10),
    @XmlEnumValue("11") TrailCommission                         (11),
    @XmlEnumValue("12") ProjectedFundValue                      (12),
    @XmlEnumValue("13") FundBasedRenewCommOrderValue            (13),
    @XmlEnumValue("14") FundBasedRenewCommProjFund              (14),
    @XmlEnumValue("15") NetSettlementAmount                     (15);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ContAmtType> stringToEnum = new HashMap<String, ContAmtType>();

    static {
        for (ContAmtType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ContAmtType */
    ContAmtType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ContAmtType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
