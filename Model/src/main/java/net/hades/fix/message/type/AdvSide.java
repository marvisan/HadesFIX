/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvSide.java
 *
 * $Id: AdvSide.java,v 1.5 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Broker's side of advertised trade.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 18/10/2008, 15:55:07
 */
@XmlType
@XmlEnum(String.class)
public enum AdvSide {

    @XmlEnumValue("B") Buy             ("B"),
    @XmlEnumValue("S") Sell            ("S"),
    @XmlEnumValue("T") Trade           ("T"),
    @XmlEnumValue("X") Cross           ("X");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, AdvSide> stringToEnum = new HashMap<String, AdvSide>();

    static {
        for (AdvSide tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of AdvSide. */
    AdvSide(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static AdvSide valueFor(String value) {
        return stringToEnum.get(value);
    }
}
