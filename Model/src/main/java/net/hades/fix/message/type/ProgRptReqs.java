/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * ProgRptReqs.java
 *
 * $Id: ProgRptReqs.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to identify the desired frequency of progress reports.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 4:28:27 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ProgRptReqs {

    @XmlEnumValue("1")      BuySide             (1),
    @XmlEnumValue("2")      SellSide            (2),
    @XmlEnumValue("3")      RealTime            (3);

    private static final long serialVersionUID = -4583772398792295697L;

    private int value;

    private static final Map<String, ProgRptReqs> stringToEnum = new HashMap<String, ProgRptReqs>();

    static {
        for (ProgRptReqs tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ProgRptReqs */
    ProgRptReqs(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ProgRptReqs valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
