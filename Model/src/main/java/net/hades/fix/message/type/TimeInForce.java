/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TimeInForce.java
 *
 * $Id: TimeInForce.java,v 1.3 2010-01-14 09:06:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Order time in effect.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 5/07/2008, 19:13:08
 */
@XmlType
@XmlEnum(String.class)
public enum TimeInForce {

    @XmlEnumValue("0") Day                         ("0"),
    @XmlEnumValue("1") GoodTillCancel              ("1"),
    @XmlEnumValue("2") Opening                     ("2"),
    @XmlEnumValue("3") ImmediateOrCancel           ("3"),
    @XmlEnumValue("4") FillOrKill                  ("4"),
    @XmlEnumValue("5") GoodTillCrossing            ("5"),
    @XmlEnumValue("6") GoodTillDate                ("6"),
    @XmlEnumValue("7") AtTheClosing                ("7");

    private static final long serialVersionUID = -7132088098336381202L;
    
    private String value;

    private static final Map<String, TimeInForce> stringToEnum = new HashMap<String, TimeInForce>();

    static {
        for (TimeInForce tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of TimeInForce */
    TimeInForce(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static TimeInForce valueFor(String value) {
        return stringToEnum.get(value);
    }
}
