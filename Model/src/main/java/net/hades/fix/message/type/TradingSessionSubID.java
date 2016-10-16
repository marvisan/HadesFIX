/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionSubID.java
 *
 * $Id: TradingSessionSubID.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Optional market assigned sub identifier for a trading phase within a trading session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 17/06/2009, 11:09:32 AM
 */
@XmlType
@XmlEnum(String.class)
public enum TradingSessionSubID {

    @XmlEnumValue("1") PreTrading               ("1"),
    @XmlEnumValue("2") Opening                  ("2"),
    @XmlEnumValue("3") ContinuousTrading        ("3"),
    @XmlEnumValue("4") Closing                  ("4"),
    @XmlEnumValue("5") PostTrading              ("5"),
    @XmlEnumValue("6") Intraday                 ("6"),
    @XmlEnumValue("7") Quiescent                ("7");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, TradingSessionSubID> stringToEnum = new HashMap<String, TradingSessionSubID>();

    static {
        for (TradingSessionSubID tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of TradingSessionSubID */
    TradingSessionSubID(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TradingSessionSubID valueFor(String value) {
        return stringToEnum.get(value);
    }
}
