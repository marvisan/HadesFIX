/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocStatus.java
 *
 * $Id: AllocStatus.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies status of allocation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 22/08/2009, 9:17:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocStatus {

    @XmlEnumValue("0") Accepted                     (0),
    @XmlEnumValue("1") BlockLevelReject             (1),
    @XmlEnumValue("2") AccountLevelReject           (2),
    @XmlEnumValue("3") Received                     (3),
    @XmlEnumValue("4") Incomplete                   (4),
    @XmlEnumValue("5") RejectedByIntermediary       (5),
    @XmlEnumValue("6") AllocationPending            (6),
    @XmlEnumValue("7") Reversed                     (7);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocStatus> stringToEnum = new HashMap<String, AllocStatus>();

    static {
        for (AllocStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocStatus */
    AllocStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
