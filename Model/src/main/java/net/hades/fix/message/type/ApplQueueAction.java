/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ApplQueueAction.java
 *
 * $Id: ApplQueueAction.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Action type for appl queue.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/07/2009, 1:44:44 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ApplQueueAction {

    @XmlEnumValue("0") NoActionTaken        (0),
    @XmlEnumValue("1") QueueFlushed         (1),
    @XmlEnumValue("2") OverlayLast          (2),
    @XmlEnumValue("3") EndSession           (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ApplQueueAction> stringToEnum = new HashMap<String, ApplQueueAction>();

    static {
        for (ApplQueueAction tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ApplQueueAction */
    ApplQueueAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ApplQueueAction valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
