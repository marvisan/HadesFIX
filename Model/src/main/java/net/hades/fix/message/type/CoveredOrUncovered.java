/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CoveredOrUncovered.java
 *
 * $Id: CoveredOrUncovered.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Products covering type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/09/2009, 8:52:42 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CoveredOrUncovered {

    @XmlEnumValue("0") Covered          (0),
    @XmlEnumValue("1") Uncovered        (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CoveredOrUncovered> stringToEnum = new HashMap<String, CoveredOrUncovered>();

    static {
        for (CoveredOrUncovered tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CoveredOrUncovered */
    CoveredOrUncovered(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CoveredOrUncovered valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
