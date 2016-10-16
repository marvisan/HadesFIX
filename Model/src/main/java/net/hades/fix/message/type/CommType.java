/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CommType.java
 *
 * $Id: CommType.java,v 1.5 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of commision.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 21/04/2009, 10:52:51 AM
 */
@XmlType
@XmlEnum(String.class)
public enum CommType {

    @XmlEnumValue("1") PerUnit                     ('1'),
    @XmlEnumValue("2") Percent                     ('2'),
    @XmlEnumValue("3") Absolute                    ('3'),
    @XmlEnumValue("4") CashDiscount                ('4'),
    @XmlEnumValue("5") EnhancedUnits               ('5'),
    @XmlEnumValue("6") PointsPerContract           ('6');

    private static final long serialVersionUID = -4331377559609971128L;

    private char value;

    private static final Map<String, CommType> stringToEnum = new HashMap<String, CommType>();

    static {
        for (CommType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CommType */
    CommType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static CommType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
