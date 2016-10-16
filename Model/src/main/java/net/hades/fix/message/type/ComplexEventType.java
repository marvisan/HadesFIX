/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventType.java
 *
 * $Id: ComplexEventType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the type of complex event.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/06/2009, 3:37:12 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ComplexEventType {

    @XmlEnumValue("1") Capped           (1),
    @XmlEnumValue("2") Trigger          (2),
    @XmlEnumValue("3") KnockInUp        (3),
    @XmlEnumValue("4") KockInDown       (4),
    @XmlEnumValue("5") KnockOutUp       (5),
    @XmlEnumValue("6") KnockOutDown     (6),
    @XmlEnumValue("7") Underlying       (7),
    @XmlEnumValue("8") ResetBarrier     (8),
    @XmlEnumValue("9") RollingBarrier   (9);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ComplexEventType> stringToEnum = new HashMap<String, ComplexEventType>();

    static {
        for (ComplexEventType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ComplexEventType */
    ComplexEventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ComplexEventType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
