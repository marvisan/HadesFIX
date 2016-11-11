/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdSubType.java
 *
 * $Id: TrdSubType.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of trade further classification.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TrdSubType {

    @XmlEnumValue("0")  CMTA                                    (0),
    @XmlEnumValue("1")  Adjustment                              (1),
    @XmlEnumValue("2")  ExternalTransfer                        (2),
    @XmlEnumValue("3")  RejectForSubmittingSide                 (3),
    @XmlEnumValue("4")  AdvisoryForContraSide                   (4),
    @XmlEnumValue("5")  OffsetDueToAllocation                   (5),
    @XmlEnumValue("6")  OnsetDueToAllocation                    (6),
    @XmlEnumValue("7")  DifferentialSpread                      (7),
    @XmlEnumValue("8")  ImpliedSpreadLegExecuted                (8),
    @XmlEnumValue("9")  TransactionFromExercise                 (9),
    @XmlEnumValue("10") TransactionFromAssignment               (10),
    @XmlEnumValue("11") ACATS                                   (11),
    @XmlEnumValue("14") AI                                      (14),
    @XmlEnumValue("15") B                                       (15),
    @XmlEnumValue("16") K                                       (16),
    @XmlEnumValue("17") LC                                      (17),
    @XmlEnumValue("18") M                                       (18),
    @XmlEnumValue("19") N                                       (19),
    @XmlEnumValue("20") NM                                      (20),
    @XmlEnumValue("21") NR                                      (21),
    @XmlEnumValue("22") P                                       (22),
    @XmlEnumValue("23") PA                                      (23),
    @XmlEnumValue("24") PC                                      (24),
    @XmlEnumValue("25") PN                                      (25),
    @XmlEnumValue("26") R                                       (26),
    @XmlEnumValue("27") RO                                      (27),
    @XmlEnumValue("28") RT                                      (28),
    @XmlEnumValue("29") SW                                      (29),
    @XmlEnumValue("30") T                                       (30),
    @XmlEnumValue("31") WN                                      (31),
    @XmlEnumValue("32") WT                                      (32),
    @XmlEnumValue("33") OffHoursTrade                           (33),
    @XmlEnumValue("34") OnHoursTrade                            (34),
    @XmlEnumValue("35") OTCQuote                                (35),
    @XmlEnumValue("36") ConvertedSWAP                           (36),
    @XmlEnumValue("37") X                                       (37),
    @XmlEnumValue("38") I                                       (38),
    @XmlEnumValue("39") L                                       (39);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TrdSubType> stringToEnum = new HashMap<String, TrdSubType>();

    static {
        for (TrdSubType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TrdSubType */
    TrdSubType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TrdSubType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
