/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * NetGrossInd.java
 *
 * $Id: NetGrossInd.java,v 1.4 2010-02-25 08:37:29 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent whether value is net (inclusive of tax) or gross.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 06/09/2009, 10:09:24 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum NetGrossInd {

    @XmlEnumValue("1")      Net                 (1),
    @XmlEnumValue("2")      Gross               (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, NetGrossInd> stringToEnum = new HashMap<String, NetGrossInd>();

    static {
        for (NetGrossInd tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of NetGrossInd */
    NetGrossInd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NetGrossInd valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
