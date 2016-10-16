/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOITransType.java
 *
 * $Id: IOITransType.java,v 1.3 2010-01-14 09:06:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  Identifies IOI message transaction type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 09/02/2009, 8:53:40 PM
 */
@XmlType
@XmlEnum(String.class)
public enum IOITransType {

    @XmlEnumValue("N") New                     ("N"),
    @XmlEnumValue("C") Cancel                  ("C"),
    @XmlEnumValue("R") Replace                 ("R");

    private static final long serialVersionUID = -3492824404485022354L;

    private String value;

    private static final Map<String, IOITransType> stringToEnum = new HashMap<String, IOITransType>();

    static {
        for (IOITransType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of IOITransType */
    IOITransType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static IOITransType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
