/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListOrderStatus.java
 *
 * $Id: ListOrderStatus.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent the status of a list order.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 06/09/2009, 10:11:55 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ListOrderStatus {

    @XmlEnumValue("1")      InBiddingProcess        (1),
    @XmlEnumValue("2")      ReceivedForExecution    (2),
    @XmlEnumValue("3")      Executing               (3),
    @XmlEnumValue("4")      Cancelling              (4),
    @XmlEnumValue("5")      Alert                   (5),
    @XmlEnumValue("6")      AllDone                 (6),
    @XmlEnumValue("7")      Reject                  (7);

    private static final long serialVersionUID = -8708605912787547692L;

    private int value;

    private static final Map<String, ListOrderStatus> stringToEnum = new HashMap<String, ListOrderStatus>();

    static {
        for (ListOrderStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ListOrderStatus */
    ListOrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ListOrderStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
