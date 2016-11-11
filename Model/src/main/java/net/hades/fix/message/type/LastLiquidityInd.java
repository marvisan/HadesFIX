/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LastLiquidityInd.java
 *
 * $Id: LastLiquidityInd.java,v 1.1 2010-12-20 10:58:54 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicator to identify whether this fill was a result of a liquidity provider providing
 * or liquidity taker taking the liquidity.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/09/2009, 8:59:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum LastLiquidityInd {

    @XmlEnumValue("1") AddedLiquidity                   (1),
    @XmlEnumValue("2") RemovedLiquidity                 (2),
    @XmlEnumValue("3") LiquidityRoutedOut               (3),
    @XmlEnumValue("4") Auction                          (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, LastLiquidityInd> stringToEnum = new HashMap<String, LastLiquidityInd>();

    static {
        for (LastLiquidityInd tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of LastLiquidityInd */
    LastLiquidityInd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LastLiquidityInd valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
