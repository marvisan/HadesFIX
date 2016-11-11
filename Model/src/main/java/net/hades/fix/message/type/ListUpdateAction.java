/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListUpdateAction.java
 *
 * $Id: ListUpdateAction.java,v 1.1 2011-10-13 07:18:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of change.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/09/2009, 9:44:48 PM
 */
@XmlType
@XmlEnum(String.class)
public enum ListUpdateAction {

    @XmlEnumValue("A") Add          ('A'),
    @XmlEnumValue("D") Delete       ('D'),
    @XmlEnumValue("M") Modify       ('M');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, ListUpdateAction> stringToEnum = new HashMap<String, ListUpdateAction>();

    static {
        for (ListUpdateAction tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ListUpdateAction */
    ListUpdateAction(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static ListUpdateAction valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
