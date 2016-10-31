/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListMethod.java
 *
 * $Id: ListMethod.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates whether instruments are pre-listed only or can also be defined via user request.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/12/2008, 7:52:02 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ListMethod {

    @XmlEnumValue("0") PreListedOnly                           (0),
    @XmlEnumValue("1") UserRequested                           (1);

    private static final long serialVersionUID = -8045920872873656503L;

    private int value;

    private static final Map<String, ListMethod> stringToEnum = new HashMap<String, ListMethod>();

    static {
        for (ListMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ListMethod */
    ListMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ListMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
