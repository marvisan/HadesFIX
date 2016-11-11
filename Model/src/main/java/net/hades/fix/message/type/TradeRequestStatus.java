/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeRequestStatus.java
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
 * Status of Trade Request..
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 29/11/2011, 8:59:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradeRequestStatus {

    @XmlEnumValue("0")  Accepted                            (0),
    @XmlEnumValue("1")  Completed                           (1),
    @XmlEnumValue("2")  Rejected                            (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TradeRequestStatus> stringToEnum = new HashMap<String, TradeRequestStatus>();

    static {
        for (TradeRequestStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradeRequestStatus */
    TradeRequestStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradeRequestStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
