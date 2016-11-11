/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AffirmStatus.java
 *
 * $Id: AffirmStatus.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Status of the ConfirmationAck..
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/08/2009, 9:17:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AffirmStatus {

    @XmlEnumValue("1") Received                 (1),
    @XmlEnumValue("2") ConfirmRejected          (2),
    @XmlEnumValue("3") Affirmed                 (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AffirmStatus> stringToEnum = new HashMap<String, AffirmStatus>();

    static {
        for (AffirmStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AffirmStatus */
    AffirmStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AffirmStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
