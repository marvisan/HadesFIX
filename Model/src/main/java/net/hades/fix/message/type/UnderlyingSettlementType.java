/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingSettlementType.java
 *
 * $Id: UnderlyingSettlementType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates order settlement period for the underlying instrument.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 15/12/2008, 9:17:16 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum UnderlyingSettlementType {

    @XmlEnumValue("2") Tplus1          (2),
    @XmlEnumValue("4") Tplus3          (4),
    @XmlEnumValue("5") Tplus4          (5);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, UnderlyingSettlementType> stringToEnum = new HashMap<String, UnderlyingSettlementType>();

    static {
        for (UnderlyingSettlementType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of UnderlyingSettlementType */
    UnderlyingSettlementType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UnderlyingSettlementType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
