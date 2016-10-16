/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeRequestResult.java
 *
 * $Id: AllocHandlInst.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Result of Trade Request.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 29/11/2011, 8:59:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradeRequestResult {

    @XmlEnumValue("0")  Successful                          (0),
    @XmlEnumValue("1")  UnknownInstrument                   (1),
    @XmlEnumValue("2")  InvalidTradeType                    (2),
    @XmlEnumValue("3")  InvalidParties                      (3),
    @XmlEnumValue("4")  InvalidTransportType                (4),
    @XmlEnumValue("5")  InvalidDestination                  (5),
    @XmlEnumValue("8")  TradeRequestTypeNotSupported        (8),
    @XmlEnumValue("9")  NotAuthorized                       (9),
    @XmlEnumValue("99") Other                               (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TradeRequestResult> stringToEnum = new HashMap<String, TradeRequestResult>();

    static {
        for (TradeRequestResult tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradeRequestResult */
    TradeRequestResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradeRequestResult valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
