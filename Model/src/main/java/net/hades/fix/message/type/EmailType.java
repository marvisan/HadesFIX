/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailType.java
 *
 * $Id: EmailType.java,v 1.5 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Email type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/04/2009, 8:21:49 AM
 */
@XmlType
@XmlEnum(String.class)
public enum EmailType {

    @XmlEnumValue("0") New                         ('0'),
    @XmlEnumValue("1") Reply                       ('1'),
    @XmlEnumValue("2") AdminReply                  ('2');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, EmailType> stringToEnum = new HashMap<String, EmailType>();

    static {
        for (EmailType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of Benchmark */
    EmailType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static EmailType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
