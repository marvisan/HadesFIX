/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StreamAsgnAckType.java
 *
 * $Id: StreamAsgnAckType.java,v 1.1 2011-04-28 10:07:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of acknowledgment.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/04/2011, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum StreamAsgnAckType {

    @XmlEnumValue("0")  Accepted        (0),
    @XmlEnumValue("1")  Rejected        (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, StreamAsgnAckType> stringToEnum = new HashMap<String, StreamAsgnAckType>();

    static {
        for (StreamAsgnAckType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of StreamAsgnAckType */
    StreamAsgnAckType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StreamAsgnAckType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
