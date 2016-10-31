/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Urgency.java
 *
 * $Id: Urgency.java,v 1.4 2010-02-25 08:37:31 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Urgency of message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 5/07/2008, 19:27:01
 */
@XmlType
@XmlEnum(String.class)
public enum Urgency {

    @XmlEnumValue("0") Normal          ("0"),
    @XmlEnumValue("1") Flash           ("1"),
    @XmlEnumValue("2") Background      ("2");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, Urgency> stringToEnum = new HashMap<String, Urgency>();

    static {
        for (Urgency tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of Urgency */
    Urgency(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static Urgency valueFor(String value) {
        return stringToEnum.get(value);
    }
}
