/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfirmType.java
 *
 * $Id: AvgPxIndicator.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the type of Confirmation message being sent.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ConfirmType {

    @XmlEnumValue("1")  Status                                  (1),
    @XmlEnumValue("2")  Confirmation                            (2),
    @XmlEnumValue("3")  ConfirmationRequestRejected             (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ConfirmType> stringToEnum = new HashMap<String, ConfirmType>();

    static {
        for (ConfirmType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ConfirmType */
    ConfirmType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ConfirmType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
