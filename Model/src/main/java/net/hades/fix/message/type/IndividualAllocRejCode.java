/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IndividualAllocRejCode.java
 *
 * $Id: IndividualAllocRejCode.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent the type of allocation rejection.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum IndividualAllocRejCode {

    @XmlEnumValue("0")  UnknownAccount                      (0),
    @XmlEnumValue("1")  IncorrectQuantity                   (1),
    @XmlEnumValue("2")  IncorrectAveragegPrice              (2),
    @XmlEnumValue("3")  UnknownExecutingBroker              (3),
    @XmlEnumValue("4")  CommissionDifferenc                 (4),
    @XmlEnumValue("5")  UnknownOrderID                      (5),
    @XmlEnumValue("6")  UnknownListID                       (6),
    @XmlEnumValue("7")  OtherFurtherInText                  (7),
    @XmlEnumValue("8")  IncorrectAllocatedQuantity          (8),
    @XmlEnumValue("9")  CalculationDifference               (9),
    @XmlEnumValue("10") UnknownStaleExecID                  (10),
    @XmlEnumValue("11") MismatchedData                      (11),
    @XmlEnumValue("12") UnknownClOrdID                      (12),
    @XmlEnumValue("13") WarehouseRequestRejected            (13),
    @XmlEnumValue("99") Other                               (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, IndividualAllocRejCode> stringToEnum = new HashMap<String, IndividualAllocRejCode>();

    static {
        for (IndividualAllocRejCode tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of IndividualAllocRejCode */
    IndividualAllocRejCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static IndividualAllocRejCode valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
