/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatus.java
 *
 * $Id: SecurityStatus.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Used for derivatives. Denotes the current state of the Instrument.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 30/11/2008, 11:57:40 AM
 */
@XmlType
@XmlEnum(String.class)
public enum SecurityStatus {

    @XmlEnumValue("1") Active              ("1"),
    @XmlEnumValue("2") Inactive            ("2");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, SecurityStatus> stringToEnum = new HashMap<String, SecurityStatus>();

    static {
        for (SecurityStatus tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of SecurityStatus. */
    SecurityStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SecurityStatus valueFor(String value) {
        return stringToEnum.get(value);
    }
}
