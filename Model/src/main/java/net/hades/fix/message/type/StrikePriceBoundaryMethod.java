/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StrikePriceBoundaryMethod.java
 *
 * $Id: StrikePriceBoundaryMethod.java,v 1.4 2010-02-25 08:37:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies the boundary condition to be used for the strike price relative to the underlying
 * price at the point of option exercise.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/06/2009, 9:32:12 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum StrikePriceBoundaryMethod {

    @XmlEnumValue("1") LessThanUnderlyingPriceITM               (1),
    @XmlEnumValue("2") LessThanOrEqualUnderlyingPriceITM        (2),
    @XmlEnumValue("3") EqualUnderlyingPriceITM                  (3),
    @XmlEnumValue("4") GreaterThanOrEqualUnderlyingPriceITM     (4),
    @XmlEnumValue("5") GreaterThanUnderlyingPriceITM            (5);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, StrikePriceBoundaryMethod> stringToEnum = new HashMap<String, StrikePriceBoundaryMethod>();

    static {
        for (StrikePriceBoundaryMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of StrikePriceBoundaryMethod */
    StrikePriceBoundaryMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StrikePriceBoundaryMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
