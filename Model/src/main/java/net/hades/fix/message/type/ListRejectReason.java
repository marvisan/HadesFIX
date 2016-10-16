/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListRejectReason.java
 *
 * $Id: ListRejectReason.java,v 1.1 2011-02-03 10:36:53 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to identify reason for rejection of a New Order List message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/09/2009, 4:02:03 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ListRejectReason {

    @XmlEnumValue("1")      BrokerExchangeOption            (0),
    @XmlEnumValue("2")      ExchangeClosed                  (2),
    @XmlEnumValue("4")      TooLateToEnter                  (4),
    @XmlEnumValue("5")      UnknownOrder                    (5),
    @XmlEnumValue("6")      DuplicateOrder                  (6),
    @XmlEnumValue("11")     UnsupportedOrder                (11),
    @XmlEnumValue("99")     Other                           (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ListRejectReason> stringToEnum = new HashMap<String, ListRejectReason>();

    static {
        for (ListRejectReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ListRejectReason */
    ListRejectReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ListRejectReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
