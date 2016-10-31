/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RoundingDirection.java
 *
 * $Id: RoundingDirection.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies which direction to round.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 10/02/2009, 12:15:30 PM
 */
@XmlType
@XmlEnum(String.class)
public enum RoundingDirection {

    @XmlEnumValue("0") RoundToNearest                  ('0'),
    @XmlEnumValue("1") RoundDown                       ('1'),
    @XmlEnumValue("2") RoundUp                         ('2');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, RoundingDirection> stringToEnum = new HashMap<String, RoundingDirection>();

    static {
        for (RoundingDirection tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RoundingDirection */
    RoundingDirection(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static RoundingDirection valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
