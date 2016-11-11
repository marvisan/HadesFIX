/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * PreallocMethod.java
 *
 * $Id: PreallocMethod.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates the method of preallocation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 9:48:30 PM
 */
@XmlType
@XmlEnum(String.class)
public enum PreallocMethod {

    @XmlEnumValue("0") ProRata                  ('0'),
    @XmlEnumValue("1") DiscussFirst             ('1');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, PreallocMethod> stringToEnum = new HashMap<String, PreallocMethod>();

    static {
        for (PreallocMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PreallocMethod */
    PreallocMethod(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static PreallocMethod valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
