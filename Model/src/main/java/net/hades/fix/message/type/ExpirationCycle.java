/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExpirationCycle.java
 *
 * $Id: ExpirationCycle.java,v 1.1 2011-04-16 07:38:24 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Trading expiration cycle.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/08/2009, 9:17:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ExpirationCycle {

    @XmlEnumValue("0") ExpireOnTradingSessionClose                  (0),
    @XmlEnumValue("1") ExpireOnTradingSessionOpen                   (1),
    @XmlEnumValue("2") TradingEligibilitySpecified                  (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ExpirationCycle> stringToEnum = new HashMap<String, ExpirationCycle>();

    static {
        for (ExpirationCycle tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ExpirationCycle */
    ExpirationCycle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ExpirationCycle valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
