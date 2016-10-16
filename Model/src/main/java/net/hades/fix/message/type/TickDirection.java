/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TickDirection.java
 *
 * $Id: TickDirection.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Tick direction.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/09/2009, 9:22:54 PM
 */
@XmlType
@XmlEnum(String.class)
public enum TickDirection {

    @XmlEnumValue("0") PlusTick                 ('0'),
    @XmlEnumValue("1") ZeroPlusTick             ('1'),
    @XmlEnumValue("2") MinusTick                ('2'),
    @XmlEnumValue("3") ZeroMinusTick            ('3');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, TickDirection> stringToEnum = new HashMap<String, TickDirection>();

    static {
        for (TickDirection tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TickDirection */
    TickDirection(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static TickDirection valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
