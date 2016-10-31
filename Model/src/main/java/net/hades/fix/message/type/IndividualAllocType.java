/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IndividualAllocType.java
 *
 * $Id: IndividualAllocType.java,v 1.1 2011-02-10 10:02:15 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifiers used for allocation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum IndividualAllocType {

    @XmlEnumValue("1")      SubAllocate                         (1),
    @XmlEnumValue("2")      ThirdPartyAllocation                (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, IndividualAllocType> stringToEnum = new HashMap<String, IndividualAllocType>();

    static {
        for (IndividualAllocType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of IndividualAllocType */
    IndividualAllocType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static IndividualAllocType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
