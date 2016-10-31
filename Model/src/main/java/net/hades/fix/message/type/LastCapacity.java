/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LastCapacity.java
 *
 * $Id: LastCapacity.java,v 1.1 2010-12-20 10:58:54 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Broker capacity in order execution.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum LastCapacity {

    @XmlEnumValue("1") Agent                    ('1'),
    @XmlEnumValue("2") CrossAsAgent             ('2'),
    @XmlEnumValue("3") CrossAsPrincipal         ('3'),
    @XmlEnumValue("4") Principal                ('4');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, LastCapacity> stringToEnum = new HashMap<String, LastCapacity>();

    static {
        for (LastCapacity tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of LastCapacity */
    LastCapacity(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static LastCapacity valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
