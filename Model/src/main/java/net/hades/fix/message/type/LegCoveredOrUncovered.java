/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegCoveredOrUncovered.java
 *
 * $Id: LegCoveredOrUncovered.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * CoveredOrUncovered for leg of a multileg.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 8:16:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum LegCoveredOrUncovered {

    @XmlEnumValue("0")      Covered             (0),
    @XmlEnumValue("1")      Uncovered           (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, LegCoveredOrUncovered> stringToEnum = new HashMap<String, LegCoveredOrUncovered>();

    static {
        for (LegCoveredOrUncovered tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of LegCoveredOrUncovered */
    LegCoveredOrUncovered(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LegCoveredOrUncovered valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
