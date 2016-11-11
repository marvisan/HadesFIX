/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * SecurityRequestResult.java
 *
 * $Id: SecurityRequestResult.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The results returned to a Security Request message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/09/2009, 8:09:51 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SecurityRequestResult {

    @XmlEnumValue("0")      ValidRequest                (0),
    @XmlEnumValue("1")      UnsupportedRequest          (1),
    @XmlEnumValue("2")      NoInstrumentsFound          (2),
    @XmlEnumValue("3")      NotAuthorized               (3),
    @XmlEnumValue("4")      TemporarilyUnavailable      (4),
    @XmlEnumValue("5")      RequestNotSupported         (5);

    private static final long serialVersionUID = -7178978997515309952L;

    private int value;

    private static final Map<String, SecurityRequestResult> stringToEnum = new HashMap<String, SecurityRequestResult>();

    static {
        for (SecurityRequestResult tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SecurityRequestResult */
    SecurityRequestResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecurityRequestResult valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
