/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStatusType.java
 *
 * $Id: ListStatusType.java,v 1.4 2010-02-25 08:37:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent the status type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 06/09/2009, 10:05:12 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ListStatusType {

    @XmlEnumValue("1")      Ack                 (1),
    @XmlEnumValue("2")      Response            (2),
    @XmlEnumValue("3")      Timed               (3),
    @XmlEnumValue("4")      ExecStarted         (4),
    @XmlEnumValue("5")      AllDone             (5),
    @XmlEnumValue("6")      Alert               (6);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ListStatusType> stringToEnum = new HashMap<String, ListStatusType>();

    static {
        for (ListStatusType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ListStatusType */
    ListStatusType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ListStatusType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
