/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * SideValueInd.java
 *
 * $Id: SideValueInd.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to identify "SideValue" type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 4:14:06 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SideValueInd {

    @XmlEnumValue("1")      SideValue_1             (1),
    @XmlEnumValue("2")      SideValue_2             (2);

    private static final long serialVersionUID = -1459964899273683019L;

    private int value;

    private static final Map<String, SideValueInd> stringToEnum = new HashMap<String, SideValueInd>();

    static {
        for (SideValueInd tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SideValueInd */
    SideValueInd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SideValueInd valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
