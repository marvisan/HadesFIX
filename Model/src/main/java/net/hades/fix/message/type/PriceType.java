/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PriceType.java
 *
 * $Id: PriceType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent the price type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 10/02/2009, 5:17:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PriceType {

    @XmlEnumValue("1")  Percentage                          (1),
    @XmlEnumValue("2")  PerUnit                             (2),
    @XmlEnumValue("3")  FixedAmount                         (3),
    @XmlEnumValue("4")  Discount                            (4),
    @XmlEnumValue("5")  Premium                             (5),
    @XmlEnumValue("6")  Spread                              (6),
    @XmlEnumValue("7")  TEDPrice                            (7),
    @XmlEnumValue("8")  TEDYield                            (8),
    @XmlEnumValue("9")  Yield                               (9),
    @XmlEnumValue("10") FixedCabinetTradePrice              (10),
    @XmlEnumValue("11") VariableCabinetTradePrice           (11),
    @XmlEnumValue("13") ProductTicksInHalfs                 (13),
    @XmlEnumValue("14") ProductTicksInFourths               (14),
    @XmlEnumValue("15") ProductTicksInEights                (15),
    @XmlEnumValue("16") ProductTicksInSixteenths            (16),
    @XmlEnumValue("17") ProductTicksInThirtySeconds         (17),
    @XmlEnumValue("18") ProductTicksInSixtyForths           (18),
    @XmlEnumValue("19") ProductTicksInOneTwentyEights       (19);

    private static final long serialVersionUID = -97534709767289633L;

    private int value;

    private static final Map<String, PriceType> stringToEnum = new HashMap<String, PriceType>();

    static {
        for (PriceType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PriceType */
    PriceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PriceType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
