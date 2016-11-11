/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * PosQtyStatus.java
 *
 * $Id: PosQtyStatus.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Status of this position.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/09/2009, 4:22:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PosQtyStatus {

    @XmlEnumValue("0")      Submitted       (0),
    @XmlEnumValue("1")      Accepted        (1),
    @XmlEnumValue("2")      Rejected        (2);

    private static final long serialVersionUID = -8090045742562693078L;

    private int value;

    private static final Map<String, PosQtyStatus> stringToEnum = new HashMap<String, PosQtyStatus>();

    static {
        for (PosQtyStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PosQtyStatus */
    PosQtyStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PosQtyStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
