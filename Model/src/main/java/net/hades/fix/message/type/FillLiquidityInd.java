/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FillLiquidityInd.java
 *
 * $Id: FillLiquidityInd.java,v 1.1 2010-12-20 10:58:54 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicator to identify whether this fill was a result of a liquidity provider.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:32:13 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum FillLiquidityInd {

    @XmlEnumValue("1") AddedLiquidity                   (1),
    @XmlEnumValue("2") RemovedLiquidity                 (2),
    @XmlEnumValue("3") LiquidityRoutedOut               (3),
    @XmlEnumValue("4") Auction                          (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, FillLiquidityInd> stringToEnum = new HashMap<String, FillLiquidityInd>();

    static {
        for (FillLiquidityInd tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of FillLiquidityInd */
    FillLiquidityInd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FillLiquidityInd valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
