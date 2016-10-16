/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RefOrdIDReason.java
 *
 * $Id: RefOrdIDReason.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The reason for updating the RefOrdID.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/08/2009, 9:17:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum RefOrdIDReason {

    @XmlEnumValue("0") GTCPreviousDay                   (0),
    @XmlEnumValue("1") PartialFillRemaining             (1),
    @XmlEnumValue("2") OrderChanged                     (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, RefOrdIDReason> stringToEnum = new HashMap<String, RefOrdIDReason>();

    static {
        for (RefOrdIDReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RefOrdIDReason */
    RefOrdIDReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RefOrdIDReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
