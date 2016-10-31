/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollAsgnTransType.java
 *
 * $Id: AllocType.java,v 1.4 2011-04-19 12:13:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Collateral Assignment Transaction Type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CollAsgnTransType {

    @XmlEnumValue("0")  New                         (0),
    @XmlEnumValue("1")  Replace                     (1),
    @XmlEnumValue("2")  Cancel                      (2),
    @XmlEnumValue("3")  Release                     (3),
    @XmlEnumValue("4")  Reverse                     (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CollAsgnTransType> stringToEnum = new HashMap<String, CollAsgnTransType>();

    static {
        for (CollAsgnTransType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CollAsgnTransType */
    CollAsgnTransType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CollAsgnTransType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
