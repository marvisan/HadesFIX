/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocType.java
 *
 * $Id: AllocType.java,v 1.4 2011-04-19 12:13:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type or purpose of an Allocation message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocType {

    @XmlEnumValue("1")      Calculated                              (1),
    @XmlEnumValue("2")      Preliminary                             (2),
    @XmlEnumValue("3")      SellsideCalcuWithPreliminary            (3),
    @XmlEnumValue("4")      SellsideCalcuwithoutPreliminary         (4),
    @XmlEnumValue("5")      ReadyToBookSingleOrder                  (5),
    @XmlEnumValue("6")      ReadyToBookCombinedSet                  (6),
    @XmlEnumValue("7")      WarehouseInstruction                    (7),
    @XmlEnumValue("8")      RequestToIntermediary                   (8),
    @XmlEnumValue("9")      Accept                                  (9),
    @XmlEnumValue("10")     Reject                                  (10),
    @XmlEnumValue("11")     AcceptPending                           (11),
    @XmlEnumValue("12")     IncompleteGroup                         (12),
    @XmlEnumValue("13")     CompleteGroup                           (13),
    @XmlEnumValue("14")     ReversalPending                         (14);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocType> stringToEnum = new HashMap<String, AllocType>();

    static {
        for (AllocType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocType */
    AllocType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
