/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BusinessRejectReason.java
 *
 * $Id: BusinessRejectReason.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to identify reason for a Business Message Reject.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 3:46:35 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum BusinessRejectReason {

    @XmlEnumValue("0")      Other                               (0),
    @XmlEnumValue("1")      UnknownID                           (1),
    @XmlEnumValue("2")      UnknownSecurity                     (2),
    @XmlEnumValue("3")      UnsupportedMessageType              (3),
    @XmlEnumValue("4")      ApplicationNotAvailable             (4),
    @XmlEnumValue("5")      CondRequiredFieldMissing            (5),
    @XmlEnumValue("6")      NotAuthorized                       (6),
    @XmlEnumValue("7")      DeliverToNotAvailable               (7),
    @XmlEnumValue("18")     InvalidPriceIncrement               (18);

    private static final long serialVersionUID = -7358014066417438723L;

    private int value;

    private static final Map<String, BusinessRejectReason> stringToEnum = new HashMap<String, BusinessRejectReason>();

    static {
        for (BusinessRejectReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of BusinessRejectReason */
    BusinessRejectReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BusinessRejectReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
