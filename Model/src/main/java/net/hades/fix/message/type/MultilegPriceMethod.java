/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * MultilegPriceMethod.java
 *
 * $Id: MultilegPriceMethod.java,v 1.1 2011-04-19 12:13:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines how the multi-leg price is to be interpreted when applied to the legs.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MultilegPriceMethod {

    @XmlEnumValue("0")      NetPrice                                (0),
    @XmlEnumValue("1")      ReversedNetPrice                        (1),
    @XmlEnumValue("2")      YieldDifference                         (2),
    @XmlEnumValue("3")      Individual                              (3),
    @XmlEnumValue("4")      ContractWeightedAveragePrice            (4),
    @XmlEnumValue("5")      MultipliedPrice                         (5);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MultilegPriceMethod> stringToEnum = new HashMap<String, MultilegPriceMethod>();

    static {
        for (MultilegPriceMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MultilegPriceMethod */
    MultilegPriceMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MultilegPriceMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
