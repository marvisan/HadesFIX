/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RestructuringType.java
 *
 * $Id: RestructuringType.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * A category of CDS credit even in which the underlying bond experiences a restructuring.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 03/06/2009, 8:55:02 AM
 */
@XmlType
@XmlEnum(String.class)
public enum RestructuringType {

    @XmlEnumValue("FR") Full            ("FR"),
    @XmlEnumValue("MR") Modified        ("MR"),
    @XmlEnumValue("MM") ModifiedMod     ("MM"),
    @XmlEnumValue("XR") None            ("XR");

    private static final long serialVersionUID = -612737152430699995L;

    private String value;

    private static final Map<String, RestructuringType> stringToEnum = new HashMap<String, RestructuringType>();

    static {
        for (RestructuringType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of RestructuringType */
    RestructuringType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RestructuringType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
