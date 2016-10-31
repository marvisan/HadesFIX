/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * PosTransType.java
 *
 * $Id: PosTransType.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the type of position transaction.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/09/2009, 5:43:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PosTransType {

    @XmlEnumValue("1")      Exercise                    (1),
    @XmlEnumValue("2")      DoNotExercise               (2),
    @XmlEnumValue("3")      PositionAdjustment          (3),
    @XmlEnumValue("4")      PositionChange              (4),
    @XmlEnumValue("5")      Pledge                      (5),
    @XmlEnumValue("6")      LargeTraderSubmission       (6);

    private static final long serialVersionUID = -1684919440008218864L;

    private int value;

    private static final Map<String, PosTransType> stringToEnum = new HashMap<String, PosTransType>();

    static {
        for (PosTransType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PosTransType */
    PosTransType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PosTransType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
