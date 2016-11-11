/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PegScope.java
 *
 * $Id: PegScope.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The scope of the peg.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/06/2009, 8:40:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PegScope {

    @XmlEnumValue("1")  Local                       (1),
    @XmlEnumValue("2")  National                    (2),
    @XmlEnumValue("3")  Global                      (3),
    @XmlEnumValue("4")  NationalExclLocal           (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PegScope> stringToEnum = new HashMap<String, PegScope>();

    static {
        for (PegScope tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PegScope */
    PegScope(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PegScope valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
