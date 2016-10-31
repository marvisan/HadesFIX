/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIQltyInd.java
 *
 * $Id: IOIQltyInd.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Relative quality of indication.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 10/02/2009, 5:31:02 PM
 */
@XmlType
@XmlEnum(String.class)
public enum IOIQltyInd {

    @XmlEnumValue("H") High                    ('H'),
    @XmlEnumValue("L") Low                     ('L'),
    @XmlEnumValue("M") Medium                  ('M');

    private static final long serialVersionUID = -1563293474444273967L;

    private char value;

    private static final Map<String, IOIQltyInd> stringToEnum = new HashMap<String, IOIQltyInd>();

    static {
        for (IOIQltyInd tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of IOIQltyInd */
    IOIQltyInd(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static IOIQltyInd valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
