/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventPriceBoundaryMethod.java
 *
 * $Id: ComplexEventPriceBoundaryMethod.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies the boundary condition to be used for the event price relative to the underlying price.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/06/2009, 3:44:16 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ComplexEventPriceBoundaryMethod {

    @XmlEnumValue("1") LessThan                 (1),
    @XmlEnumValue("2") LessThanOrEqual          (2),
    @XmlEnumValue("3") EqualTo                  (3),
    @XmlEnumValue("4") GreaterThanOrEqual       (4),
    @XmlEnumValue("5") GreaterThan              (5);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ComplexEventPriceBoundaryMethod> stringToEnum = new HashMap<String, ComplexEventPriceBoundaryMethod>();

    static {
        for (ComplexEventPriceBoundaryMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ComplexEventPriceBoundaryMethod */
    ComplexEventPriceBoundaryMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ComplexEventPriceBoundaryMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
