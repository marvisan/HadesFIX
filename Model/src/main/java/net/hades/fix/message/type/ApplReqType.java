/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ApplReqType.java
 *
 * $Id: ApplReqType.java,v 1.1 2011-10-13 07:18:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of application message request.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/09/2009, 8:59:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ApplReqType {

    @XmlEnumValue("0") Retransmission                       (0),
    @XmlEnumValue("1") Subscription                         (1),
    @XmlEnumValue("2") RequestForLastSeqNum                 (2),
    @XmlEnumValue("3") RequestValidApps                     (3),
    @XmlEnumValue("4") Unsubscribe                          (4),
    @XmlEnumValue("5") CancelRetransmission                 (5),
    @XmlEnumValue("6") CancelRetransAndUnsubscribe          (6);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ApplReqType> stringToEnum = new HashMap<String, ApplReqType>();

    static {
        for (ApplReqType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ApplReqType */
    ApplReqType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ApplReqType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
