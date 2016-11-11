/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Seniority.java
 *
 * $Id: Seniority.java,v 1.5 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies which issue (underlying bond) will receive payment priority in the event of a default.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 03/06/2009, 9:17:06 AM
 */
@XmlType
@XmlEnum(String.class)
public enum Seniority {

    @XmlEnumValue("SD") SeniorSecured       ("SD"),
    @XmlEnumValue("SR") Senior              ("SR"),
    @XmlEnumValue("SB") Subordinated        ("SB");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, Seniority> stringToEnum = new HashMap<String, Seniority>();

    static {
        for (Seniority tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of Seniority */
    Seniority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Seniority valueFor(String value) {
        return stringToEnum.get(value);
    }
}
