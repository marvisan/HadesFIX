/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TimeUnit.java
 *
 * $Id: TimeUnit.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Measure units symbols for time.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 28/11/2008, 8:21:20 PM
 */
@XmlType
@XmlEnum(String.class)
public enum TimeUnit {

    @XmlEnumValue("H")   Hour                ("H"),
    @XmlEnumValue("Min") Minute              ("Min"),
    @XmlEnumValue("S")   Second              ("S"),
    @XmlEnumValue("D")   Day                 ("D"),
    @XmlEnumValue("Wk")  Week                ("Wk"),
    @XmlEnumValue("Mo")  Month               ("Mo"),
    @XmlEnumValue("Yr")  Year                ("Yr");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, TimeUnit> stringToEnum = new HashMap<String, TimeUnit>();

    static {
        for (TimeUnit tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of TimeUnit. */
    TimeUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TimeUnit valueFor(String value) {
        return stringToEnum.get(value);
    }
}
