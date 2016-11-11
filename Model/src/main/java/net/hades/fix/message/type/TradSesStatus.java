/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * TradSesStatus.java
 *
 * $Id: TradSesStatus.java,v 1.3 2010-01-14 09:06:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * State of the trading session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 3:06:52 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradSesStatus {

    @XmlEnumValue("0")      Unknown             (0),
    @XmlEnumValue("1")      Halted              (1),
    @XmlEnumValue("2")      Open                (2),
    @XmlEnumValue("3")      Closed              (3),
    @XmlEnumValue("4")      PreOpen             (4),
    @XmlEnumValue("5")      PreClose            (5),
    @XmlEnumValue("6")      RequestRejected     (6);

    private static final long serialVersionUID = -5567149155448302912L;

    private int value;

    private static final Map<String, TradSesStatus> stringToEnum = new HashMap<String, TradSesStatus>();

    static {
        for (TradSesStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradSesStatus */
    TradSesStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradSesStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
