/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ApplResponseType.java
 *
 * $Id: ApplResponseType.java,v 1.1 2011-10-13 07:18:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of of acknowledgment being sent.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/09/2009, 8:59:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ApplResponseType {

    @XmlEnumValue("0") RequestProcessed                     (0),
    @XmlEnumValue("1") AppDoesNotExist                      (1),
    @XmlEnumValue("2") MessagesNotAvailable                 (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ApplResponseType> stringToEnum = new HashMap<String, ApplResponseType>();

    static {
        for (ApplResponseType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ApplResponseType */
    ApplResponseType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ApplResponseType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
