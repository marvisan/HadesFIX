/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocIntermedReqType.java
 *
 * $Id: AllocIntermedReqType.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type or request type for an allocation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocIntermedReqType {

    @XmlEnumValue("1")  PendingAccept               (1),
    @XmlEnumValue("2")  PendingRelease              (2),
    @XmlEnumValue("3")  PendingReversal             (3),
    @XmlEnumValue("4")  Accept                      (4),
    @XmlEnumValue("5")  BlockLevelReject            (5),
    @XmlEnumValue("6")  AccountLevelReject          (6);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocIntermedReqType> stringToEnum = new HashMap<String, AllocIntermedReqType>();

    static {
        for (AllocIntermedReqType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocIntermedReqType */
    AllocIntermedReqType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocIntermedReqType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
