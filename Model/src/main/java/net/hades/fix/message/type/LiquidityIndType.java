/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LiquidityIndType.java
 *
 * $Id: LiquidityIndType.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to identify the type of liquidity.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 05/09/2009, 4:22:27 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum LiquidityIndType {

    @XmlEnumValue("1")      MovingAverage5Day           (1),
    @XmlEnumValue("2")      MovingAverage20Day          (2),
    @XmlEnumValue("3")      NormalMarketSize            (3),
    @XmlEnumValue("4")      Other                       (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, LiquidityIndType> stringToEnum = new HashMap<String, LiquidityIndType>();

    static {
        for (LiquidityIndType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of LiquidityIndType */
    LiquidityIndType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LiquidityIndType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
