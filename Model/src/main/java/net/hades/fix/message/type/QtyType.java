/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QtyType.java
 *
 * $Id: QtyType.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Product quantity type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 23/11/2008, 16:50:19
 */
@XmlType
@XmlEnum(Integer.class)
public enum QtyType {

    @XmlEnumValue("0") Units               (0),
    @XmlEnumValue("1") Contracts           (1),
    @XmlEnumValue("2") UnitsPerTimeUnit    (2);

    private static final long serialVersionUID = -6574773737207372641L;

    private int value;

    private static final Map<String, QtyType> stringToEnum = new HashMap<String, QtyType>();

    static {
        for (QtyType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QtyType */
    QtyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QtyType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
