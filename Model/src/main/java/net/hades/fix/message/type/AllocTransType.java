/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocTransType.java
 *
 * $Id: AllocTransType.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Allocation transaction type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 22/08/2009, 8:41:51 PM
 */
@XmlType
@XmlEnum(String.class)
public enum AllocTransType {

    @XmlEnumValue("0") New                          ('0'),
    @XmlEnumValue("1") Replace                      ('1'),
    @XmlEnumValue("2") Cancel                       ('2'),
    @XmlEnumValue("3") Preliminary                  ('3'),
    @XmlEnumValue("4") Calculated                   ('4'),
    @XmlEnumValue("5") CalculatedWOPreliminary      ('5'),
    @XmlEnumValue("6") Reversal                     ('6');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, AllocTransType> stringToEnum = new HashMap<String, AllocTransType>();

    static {
        for (AllocTransType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocTransType */
    AllocTransType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static AllocTransType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
