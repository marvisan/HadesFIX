/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocMethod.java
 *
 * $Id: AllocMethod.java,v 1.1 2011-02-10 10:02:15 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Allocation method.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocMethod {

    @XmlEnumValue("1")      Automatic               (1),
    @XmlEnumValue("2")      Guarantor               (2),
    @XmlEnumValue("3")      Manual                  (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocMethod> stringToEnum = new HashMap<String, AllocMethod>();

    static {
        for (AllocMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocMethod */
    AllocMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
