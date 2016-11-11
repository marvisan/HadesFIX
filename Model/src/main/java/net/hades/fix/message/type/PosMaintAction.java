/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * PosMaintAction.java
 *
 * $Id: PosMaintAction.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Maintenance Action to be performed.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/09/2009, 4:17:21 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PosMaintAction {

    @XmlEnumValue("1")      New             (1),
    @XmlEnumValue("2")      Replace         (2),
    @XmlEnumValue("3")      Cancel          (3),
    @XmlEnumValue("4")      Reverse         (4);

    private static final long serialVersionUID = -306402694318283090L;

    private int value;

    private static final Map<String, PosMaintAction> stringToEnum = new HashMap<String, PosMaintAction>();

    static {
        for (PosMaintAction tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PosMaintAction */
    PosMaintAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PosMaintAction valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
