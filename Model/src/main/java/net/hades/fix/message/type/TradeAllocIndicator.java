/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeAllocIndicator.java
 *
 * $Id: TradeAllocIndicator.java,v 1.1 2011-04-16 07:38:24 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies how the trade is to be allocated.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/08/2009, 9:17:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradeAllocIndicator {

    @XmlEnumValue("0") AllocNotRequired                     (0),
    @XmlEnumValue("1") AllocRequired                        (1),
    @XmlEnumValue("2") UseAllocProvided                     (2),
    @XmlEnumValue("3") AllocGiveupExecutor                  (3),
    @XmlEnumValue("4") AllocFromExecutor                    (4),
    @XmlEnumValue("5") AllocToClaimAccount                  (5);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TradeAllocIndicator> stringToEnum = new HashMap<String, TradeAllocIndicator>();

    static {
        for (TradeAllocIndicator tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradeAllocIndicator */
    TradeAllocIndicator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradeAllocIndicator valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
