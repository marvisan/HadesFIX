/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingPriceDeterminationMethod.java
 *
 * $Id: UnderlyingPriceDeterminationMethod.java,v 1.4 2010-02-25 08:37:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies how the underlying price is determined at the point of option exercise.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/06/2009, 9:52:07 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum UnderlyingPriceDeterminationMethod {

    @XmlEnumValue("1") Regular                  (1),
    @XmlEnumValue("2") SpecialReference         (2),
    @XmlEnumValue("3") OptimalValue             (3),
    @XmlEnumValue("4") AverageValue             (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, UnderlyingPriceDeterminationMethod> stringToEnum = new HashMap<String, UnderlyingPriceDeterminationMethod>();

    static {
        for (UnderlyingPriceDeterminationMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of UnderlyingPriceDeterminationMethod */
    UnderlyingPriceDeterminationMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UnderlyingPriceDeterminationMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
