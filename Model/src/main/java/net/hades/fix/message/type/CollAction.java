/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollAction.java
 *
 * $Id: CollAction.java,v 1.1 2010-12-20 10:58:54 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Action proposed for an Underlying Instrument instance.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:32:13 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CollAction {

    @XmlEnumValue("0") Retain                       (0),
    @XmlEnumValue("1") Add                          (1),
    @XmlEnumValue("2") Remove                       (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CollAction> stringToEnum = new HashMap<String, CollAction>();

    static {
        for (CollAction tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CollAction */
    CollAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CollAction valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
