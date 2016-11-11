/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * TradSesStatusRejReason.java
 *
 * $Id: TradSesStatusRejReason.java,v 1.4 2011-04-23 00:19:04 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason a Trading Session Status Request was rejected.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 8:29:02 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradSesStatusRejReason {

    @XmlEnumValue("1") InvalidTradingSessionID             (1),
    @XmlEnumValue("9") Other                               (9);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TradSesStatusRejReason> stringToEnum = new HashMap<String, TradSesStatusRejReason>();

    static {
        for (TradSesStatusRejReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradSesStatusRejReason */
    TradSesStatusRejReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradSesStatusRejReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
