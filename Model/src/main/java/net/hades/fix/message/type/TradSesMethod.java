/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * TradSesMethod.java
 *
 * $Id: TradSesMethod.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *	Method of trading.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 05/09/2009, 2:59:18 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradSesMethod {

    @XmlEnumValue("1")      Electronic      (1),
    @XmlEnumValue("2")      OpenOutcry      (2),
    @XmlEnumValue("3")      TwoParty        (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TradSesMethod> stringToEnum = new HashMap<String, TradSesMethod>();

    static {
        for (TradSesMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradSesMethod */
    TradSesMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradSesMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
