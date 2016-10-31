/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocRejCode.java
 *
 * $Id: AllocRejCode.java,v 1.5 2011-02-17 09:21:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies reason for rejection.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 22/08/2009, 9:23:29 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocRejCode {

    @XmlEnumValue("0")  UnknownAccount                      (0),
    @XmlEnumValue("1")  IncorrectQuantity                   (1),
    @XmlEnumValue("2")  IncorrectAveragePrice               (2),
    @XmlEnumValue("3")  UnknownExecutingBrokerMnemonic      (3),
    @XmlEnumValue("4")  CommissionDifference                (4),
    @XmlEnumValue("5")  UnknownOrderID                      (5),
    @XmlEnumValue("6")  UnknownListID                       (6),
    @XmlEnumValue("7")  OtherFfurtherInText                 (7),
    @XmlEnumValue("8")  IncorrectAllocatedQuantity          (8),
    @XmlEnumValue("9")  CalculationDifference               (9),
    @XmlEnumValue("10") UnknownOrStaleExecID                (10),
    @XmlEnumValue("11") MismatchedData                      (11),
    @XmlEnumValue("12") UnknownClOrdID                      (12),
    @XmlEnumValue("13") WarehouseRequestRejected            (13),
    @XmlEnumValue("99") Other                               (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocRejCode> stringToEnum = new HashMap<String, AllocRejCode>();

    static {
        for (AllocRejCode tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocRejCode */
    AllocRejCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocRejCode valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
