/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionEffect.java
 *
 * $Id: PositionEffect.java,v 1.5 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Position effect.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 5/07/2008, 19:35:42
 */
@XmlType
@XmlEnum(String.class)
public enum PositionEffect {

    @XmlEnumValue("O") Open            ("O"),
    @XmlEnumValue("C") Close           ("C"),
    @XmlEnumValue("R") Rolled          ("R"),      // (FIX 4.3+)
    @XmlEnumValue("F") FIFO            ("F");      // (FIX 4.3+)

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, PositionEffect> stringToEnum = new HashMap<String, PositionEffect>();

    static {
        for (PositionEffect tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of PositionEffect */
    PositionEffect(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static PositionEffect valueFor(String value) {
        return stringToEnum.get(value);
    }
}
