/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdjustmentType.java
 *
 * $Id: AdjustmentType.java,v 1.1 2011-01-03 09:19:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of adjustment.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AdjustmentType {

    @XmlEnumValue("0")      MarginDisposition       (0),
    @XmlEnumValue("1")      DeltaPlus               (1),
    @XmlEnumValue("2")      DeltaMinus              (2),
    @XmlEnumValue("3")      Final                   (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AdjustmentType> stringToEnum = new HashMap<String, AdjustmentType>();

    static {
        for (AdjustmentType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AdjustmentType */
    AdjustmentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AdjustmentType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
