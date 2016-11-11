/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * TradSesMode.java
 *
 * $Id: TradSesMode.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Trading Session Mode.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 3:02:57 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradSesMode {

    @XmlEnumValue("1")      Testing             (1),
    @XmlEnumValue("2")      Simulated           (2),
    @XmlEnumValue("3")      Production          (3);

    private static final long serialVersionUID = -1808170031683763886L;

    private int value;

    private static final Map<String, TradSesMode> stringToEnum = new HashMap<String, TradSesMode>();

    static {
        for (TradSesMode tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradSesMode */
    TradSesMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradSesMode valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
