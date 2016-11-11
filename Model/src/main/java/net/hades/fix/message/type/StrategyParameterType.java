/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StrategyParameterType.java
 *
 * $Id: StrategyParameterType.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of strategy parameter value.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/06/2009, 8:40:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum StrategyParameterType {

    @XmlEnumValue("1")  Int                     (1),
    @XmlEnumValue("2")  Length                  (2),
    @XmlEnumValue("3")  NumInGroup              (3),
    @XmlEnumValue("4")  SeqNum                  (4),
    @XmlEnumValue("5")  TagNum                  (5),
    @XmlEnumValue("6")  Float                   (6),
    @XmlEnumValue("7")  Qty                     (7),
    @XmlEnumValue("8")  Price                   (8),
    @XmlEnumValue("9")  PriceOffset             (9),
    @XmlEnumValue("10") Amt                     (10),
    @XmlEnumValue("11") Percentage              (11),
    @XmlEnumValue("12") Char                    (12),
    @XmlEnumValue("13") Bool                    (13),
    @XmlEnumValue("14") StringType              (14),
    @XmlEnumValue("15") MultipleCharValue       (15),
    @XmlEnumValue("16") Currency                (16),
    @XmlEnumValue("17") Exchange                (17),
    @XmlEnumValue("18") MonthYear               (18),
    @XmlEnumValue("19") UTCTimestamp            (19),
    @XmlEnumValue("20") UTCTimeOnly             (20),
    @XmlEnumValue("21") LocalMktDate            (21),
    @XmlEnumValue("22") UTCDateOnly             (22),
    @XmlEnumValue("23") Data                    (23),
    @XmlEnumValue("24") MultipleStringValue     (24),
    @XmlEnumValue("25") Country                 (25),
    @XmlEnumValue("26") Language                (26),
    @XmlEnumValue("27") TZTimeOnly              (27),
    @XmlEnumValue("28") TZTimestamp             (28),
    @XmlEnumValue("29") Tenor                   (29);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, StrategyParameterType> stringToEnum = new HashMap<String, StrategyParameterType>();

    static {
        for (StrategyParameterType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of StrategyParameterType */
    StrategyParameterType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StrategyParameterType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
