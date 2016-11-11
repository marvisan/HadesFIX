/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventPriceTimeType.java
 *
 * $Id: ComplexEventPriceTimeType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies when the complex event outcome takes effect.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 03/06/2009, 3:49:07 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ComplexEventPriceTimeType {

    @XmlEnumValue("1") Expiration           (1),
    @XmlEnumValue("2") Immediate            (2),
    @XmlEnumValue("3") SpecifiedDateTime    (3);

    private static final long serialVersionUID = -5601353295842943522L;

    private int value;

    private static final Map<String, ComplexEventPriceTimeType> stringToEnum = new HashMap<String, ComplexEventPriceTimeType>();

    static {
        for (ComplexEventPriceTimeType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ComplexEventPriceTimeType */
    ComplexEventPriceTimeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ComplexEventPriceTimeType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
