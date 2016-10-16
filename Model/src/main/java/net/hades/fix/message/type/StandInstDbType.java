/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StandInstDbType.java
 *
 * $Id: StandInstDbType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Standing Instruction database used.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 30/08/2009, 11:27:22 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum StandInstDbType {

    @XmlEnumValue("0") Other                    (0),
    @XmlEnumValue("1") DTC_SID                  (1),
    @XmlEnumValue("2") Thomson_ALERT            (2),
    @XmlEnumValue("3") GlobalCustodian          (3),
    @XmlEnumValue("4") AccountNet               (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, StandInstDbType> stringToEnum = new HashMap<String, StandInstDbType>();

    static {
        for (StandInstDbType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of StandInstDbType */
    StandInstDbType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StandInstDbType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
