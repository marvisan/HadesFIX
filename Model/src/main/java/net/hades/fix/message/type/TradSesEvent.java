/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * TradSesEvent.java
 *
 * $Id: TradSesEvent.java,v 1.1 2011-04-23 00:19:04 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies an event related to a trade session.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradSesEvent {

    @XmlEnumValue("0") TradingResumes                       (0),
    @XmlEnumValue("1") ChangeOfTradingSession               (1),
    @XmlEnumValue("2") ChangeOfTradingSubsession            (2),
    @XmlEnumValue("3") ChangeOfTradingStatus                (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TradSesEvent> stringToEnum = new HashMap<String, TradSesEvent>();

    static {
        for (TradSesEvent tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradSesEvent */
    TradSesEvent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradSesEvent valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
