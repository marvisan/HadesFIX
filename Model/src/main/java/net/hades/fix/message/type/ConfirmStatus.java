/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfirmStatus.java
 *
 * $Id: AvgPxIndicator.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the status of the Confirmation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ConfirmStatus {

    @XmlEnumValue("1")  Received                                (1),
    @XmlEnumValue("2")  MismatchedAccount                       (2),
    @XmlEnumValue("3")  MissingSettlementInstructions           (3),
    @XmlEnumValue("4")  Confirmed                               (4),
    @XmlEnumValue("5")  RequestRejected                         (5);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ConfirmStatus> stringToEnum = new HashMap<String, ConfirmStatus>();

    static {
        for (ConfirmStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ConfirmStatus */
    ConfirmStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ConfirmStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
