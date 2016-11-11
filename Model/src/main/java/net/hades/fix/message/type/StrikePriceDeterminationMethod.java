/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StrikePriceDeterminationMethod.java
 *
 * $Id: StrikePriceDeterminationMethod.java,v 1.4 2010-02-25 08:37:29 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies how the strike price is determined at the point of option exercise.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/06/2009, 9:25:47 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum StrikePriceDeterminationMethod {

    @XmlEnumValue("1") FixedStrike              (1),
    @XmlEnumValue("2") StrikeAtExpiration       (2),
    @XmlEnumValue("3") StrikeToAverage          (3),
    @XmlEnumValue("4") StrikeToOptimal          (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, StrikePriceDeterminationMethod> stringToEnum = new HashMap<String, StrikePriceDeterminationMethod>();

    static {
        for (StrikePriceDeterminationMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of StrikePriceDeterminationMethod */
    StrikePriceDeterminationMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StrikePriceDeterminationMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
