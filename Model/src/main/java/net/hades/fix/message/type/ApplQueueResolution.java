/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ApplQueueResolution.java
 *
 * $Id: ApplQueueResolution.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Resolution taken when appl queue exceeds specified maximum queue size.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 07/11/2009, 4:34:48 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ApplQueueResolution {

    @XmlEnumValue("0")  NoActionTaken           (0),
    @XmlEnumValue("1")  QueueFlushed            (1),
    @XmlEnumValue("2")  OverlayLast             (2),
    @XmlEnumValue("3")  EndSession              (3);

    private static final long serialVersionUID = -2433251106675743407L;

    private int value;

    private static final Map<String, ApplQueueResolution> stringToEnum = new HashMap<String, ApplQueueResolution>();

    static {
        for (ApplQueueResolution tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ApplQueueResolution */
    ApplQueueResolution(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ApplQueueResolution valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
