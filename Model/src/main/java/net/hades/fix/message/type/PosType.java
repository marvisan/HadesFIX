/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosType.java
 *
 * $Id: PosType.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of quantity that is being returned.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/09/2009, 3:59:47 PM
 */
@XmlType
@XmlEnum(String.class)
public enum PosType {

    @XmlEnumValue("ALC")    AllocationTradeQty                  ("ALC"),
    @XmlEnumValue("AS")     OptionAssignment                    ("AS"),
    @XmlEnumValue("ASF")    AsOfTradeQty                        ("ASF"),
    @XmlEnumValue("DLV")    DeliveryQty                         ("DLV"),
    @XmlEnumValue("ETR")    ElectronicTradeQty                  ("ETR"),
    @XmlEnumValue("EX")     OptionExerciseQty                   ("EX"),
    @XmlEnumValue("FIN")    EndOfDayQty                         ("FIN"),
    @XmlEnumValue("IAS")    IntraSpreadQty                      ("IAS"),
    @XmlEnumValue("IES")    InterSpreadQty                      ("IES"),
    @XmlEnumValue("PA")     AdjustmentQty                       ("PA"),
    @XmlEnumValue("PIT")    PitTradeQty                         ("PIT"),
    @XmlEnumValue("SOD")    StartOfDayQty                       ("SOD"),
    @XmlEnumValue("SPL")    IntegralSplit                       ("SPL"),
    @XmlEnumValue("TA")     TransactionFromAssignment           ("TA"),
    @XmlEnumValue("TOT")    TotalTransactionQty                 ("TOT"),
    @XmlEnumValue("TQ")     TransactionQuantity                 ("TQ"),
    @XmlEnumValue("TRF")    TransferTradeQty                    ("TRF"),
    @XmlEnumValue("TX")     TransactionFromExercise             ("TX"),
    @XmlEnumValue("XM")     CrossMarginQty                      ("XM"),
    @XmlEnumValue("RCV")    ReceiveQuantity                     ("RCV"),
    @XmlEnumValue("CAA")    CorporateActionAdjustment           ("CAA"),
    @XmlEnumValue("DN")     DeliveryNoticeQty                   ("DN"),
    @XmlEnumValue("EP")     ExchangeForPhysicalQty              ("EP"),
    @XmlEnumValue("PNTN")   PrivatelyNegotiatedTradeQty         ("PNTN"),
    @XmlEnumValue("DLT")    NetDeltaQty                         ("DLT"),
    @XmlEnumValue("CEA")    CreditEventAdjustment               ("CEA"),
    @XmlEnumValue("SEA")    SuccessionEventAdjustment           ("SEA");

    private static final long serialVersionUID = -3262613406457286359L;

    private String value;

    private static final Map<String, PosType> stringToEnum = new HashMap<String, PosType>();

    static {
        for (PosType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of PosType. */
    PosType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PosType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
