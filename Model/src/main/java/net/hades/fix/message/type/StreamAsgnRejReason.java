/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StreamAsgnRejReason.java
 *
 * $Id: StreamAsgnRejReason.java,v 1.1 2011-04-28 10:07:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type or reason for a assigned reject.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/04/2011, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum StreamAsgnRejReason {

    @XmlEnumValue("0")  UnknownClient                   (0),
    @XmlEnumValue("1")  ExceedMaxSize                   (1),
    @XmlEnumValue("2")  UnknownCurrencyPair             (2),
    @XmlEnumValue("3")  NoAvailableStream               (3),
    @XmlEnumValue("99") Other                           (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, StreamAsgnRejReason> stringToEnum = new HashMap<String, StreamAsgnRejReason>();

    static {
        for (StreamAsgnRejReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of StreamAsgnRejReason */
    StreamAsgnRejReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StreamAsgnRejReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
