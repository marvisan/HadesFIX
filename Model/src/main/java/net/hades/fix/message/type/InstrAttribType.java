/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrAttribType.java
 *
 * $Id: InstrAttribType.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent the type of instrument attribute.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum InstrAttribType {

    @XmlEnumValue("1")  Flat                                    (1),
    @XmlEnumValue("2")  ZeroCoupon                              (2),
    @XmlEnumValue("3")  InterestBearing                         (3),
    @XmlEnumValue("4")  NoPeriodicPayments                      (4),
    @XmlEnumValue("5")  VariableRate                            (5),
    @XmlEnumValue("6")  LessFeeForPut                           (6),
    @XmlEnumValue("7")  SteppedCoupon                           (7),
    @XmlEnumValue("8")  CouponPeriod                            (8),
    @XmlEnumValue("9")  WhenIssued                              (9),
    @XmlEnumValue("10") OriginalIssueDiscount                   (10),
    @XmlEnumValue("11") Callable                                (11),
    @XmlEnumValue("12") EscrowedToMaturity                      (12),
    @XmlEnumValue("13") EscrowedToRedemptionDate                (13),
    @XmlEnumValue("14") PreRefunded                             (14),
    @XmlEnumValue("15") InDefault                               (15),
    @XmlEnumValue("16") Unrated                                 (16),
    @XmlEnumValue("17") Taxable                                 (17),
    @XmlEnumValue("18") Indexed                                 (18),
    @XmlEnumValue("19") SubjectToAltMinTax                      (19),
    @XmlEnumValue("20") OriginalIssueDiscountPrice              (20),
    @XmlEnumValue("21") CallableBelowMaturityValue              (21),
    @XmlEnumValue("22") CallableWithoutNotice                   (22),
    @XmlEnumValue("23") PriceTickRulesForSecurity               (23),
    @XmlEnumValue("24") TradeTypeEligibility                    (24),
    @XmlEnumValue("25") InstrumentDenominato                    (25),
    @XmlEnumValue("26") InstrumentNumerato                      (26),
    @XmlEnumValue("27") InstrumentPricePrecision                (27),
    @XmlEnumValue("28") InstrumentStrikePrice                   (28),
    @XmlEnumValue("29") TradeableIndicator                      (29),
    @XmlEnumValue("99") Text                                    (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, InstrAttribType> stringToEnum = new HashMap<String, InstrAttribType>();

    static {
        for (InstrAttribType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of InstrAttribType */
    InstrAttribType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static InstrAttribType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
