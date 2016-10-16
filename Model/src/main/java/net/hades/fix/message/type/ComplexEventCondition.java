/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventCondition.java
 *
 * $Id: ComplexEventCondition.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies the condition between complex events when more than one event is specified.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/06/2009, 3:53:03 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ComplexEventCondition {

    @XmlEnumValue("1") And           (1),
    @XmlEnumValue("2") Or            (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ComplexEventCondition> stringToEnum = new HashMap<String, ComplexEventCondition>();

    static {
        for (ComplexEventCondition tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ComplexEventCondition */
    ComplexEventCondition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ComplexEventCondition valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
