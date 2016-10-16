/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MiscFeeBasis.java
 *
 * $Id: MiscFeeBasis.java,v 1.1 2011-01-09 07:27:41 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines the unit for a miscellaneous fee.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/06/2009, 8:29:03 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MiscFeeBasis {

    @XmlEnumValue("0")  Absolute                (0),
    @XmlEnumValue("1")  PerUnit                 (1),
    @XmlEnumValue("2")  Percentage              (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MiscFeeBasis> stringToEnum = new HashMap<String, MiscFeeBasis>();

    static {
        for (MiscFeeBasis tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MiscFeeBasis */
    MiscFeeBasis(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MiscFeeBasis valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
