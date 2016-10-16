/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionStatus.java
 *
 * $Id: SessionStatus.java,v 1.1 2010-03-31 11:05:16 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Status of a FIX session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:32:13 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SessionStatus {

    @XmlEnumValue("0") SessionActive                        (0),
    @XmlEnumValue("1") PasswordChanged                      (1),
    @XmlEnumValue("2") PasswordDueToExpire                  (2),
    @XmlEnumValue("3") NewPasswordNotComplying              (3),
    @XmlEnumValue("4") LogoutComplete                       (4),
    @XmlEnumValue("5") InvalidUsernameOrPassword            (5),
    @XmlEnumValue("6") AccountLocked                        (6),
    @XmlEnumValue("7") LogonsAreNotAllowed                  (7),
    @XmlEnumValue("8") PasswordExpired                      (8);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SessionStatus> stringToEnum = new HashMap<String, SessionStatus>();

    static {
        for (SessionStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AccountType */
    SessionStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SessionStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
