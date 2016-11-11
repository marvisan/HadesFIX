/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * PriorityIndicator.java
 *
 * $Id: PriorityIndicator.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Cancel/Replace.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 27/09/2009, 10:58:16 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PriorityIndicator {

    @XmlEnumValue("0")      PriorityUnchanged       (0),
    @XmlEnumValue("1")      LostPriority            (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PriorityIndicator> stringToEnum = new HashMap<String, PriorityIndicator>();

    static {
        for (PriorityIndicator tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PriorityIndicator */
    PriorityIndicator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PriorityIndicator valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
