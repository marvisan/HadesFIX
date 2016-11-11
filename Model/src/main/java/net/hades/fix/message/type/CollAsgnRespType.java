/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollAsgnRespType.java
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
 * Collateral Assignment Response Type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 16/12/2011, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CollAsgnRespType {

    @XmlEnumValue("0")  Received                        (0),
    @XmlEnumValue("1")  Accepted                        (1),
    @XmlEnumValue("2")  Declined                        (2),
    @XmlEnumValue("3")  Rejected                        (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CollAsgnRespType> stringToEnum = new HashMap<String, CollAsgnRespType>();

    static {
        for (CollAsgnRespType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CollAsgnRespType */
    CollAsgnRespType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CollAsgnRespType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
