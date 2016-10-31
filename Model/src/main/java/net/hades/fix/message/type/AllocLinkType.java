/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocLinkType.java
 *
 * $Id: AllocLinkType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Allocation linkage.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 30/08/2009, 12:35:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocLinkType {

    @XmlEnumValue("0") FXNetting    (0),
    @XmlEnumValue("1") FXSwap       (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocLinkType> stringToEnum = new HashMap<String, AllocLinkType>();

    static {
        for (AllocLinkType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocLinkType */
    AllocLinkType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocLinkType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
