/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosReqType.java
 *
 * $Id: PosReqType.java,v 1.1 2011-01-03 09:19:35 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of position request being made.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PosReqType {

    @XmlEnumValue("0")  Positions                   (0),
    @XmlEnumValue("1")  Trades                      (1),
    @XmlEnumValue("2")  Exercises                   (2),
    @XmlEnumValue("3")  Assignments                 (3),
    @XmlEnumValue("4")  SettlementActivity          (4),
    @XmlEnumValue("5")  BackoutMessage              (5),
    @XmlEnumValue("6")  DeltaPositions              (6);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PosReqType> stringToEnum = new HashMap<String, PosReqType>();

    static {
        for (PosReqType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PosReqType */
    PosReqType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PosReqType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
