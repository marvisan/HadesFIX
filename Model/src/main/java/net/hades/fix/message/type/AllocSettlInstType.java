/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocSettlInstType.java
 *
 * $Id: AllocSettlInstType.java,v 1.1 2011-02-10 10:02:15 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Allocation settlement type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocSettlInstType {

    @XmlEnumValue("0")      UseDefaultInstructions              (0),
    @XmlEnumValue("1")      DeriveFromParameters                (1),
    @XmlEnumValue("2")      FullDetailsProvided                 (2),
    @XmlEnumValue("3")      SSI_DB_ID_provided                  (3),
    @XmlEnumValue("4")      PhoneForInstructions                (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocSettlInstType> stringToEnum = new HashMap<String, AllocSettlInstType>();

    static {
        for (AllocSettlInstType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocSettlInstType */
    AllocSettlInstType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocSettlInstType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
