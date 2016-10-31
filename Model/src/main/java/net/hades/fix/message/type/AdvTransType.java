/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvTransType.java
 *
 * $Id: AdvTransType.java,v 1.5 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies advertisement message transaction type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 18/10/2008, 15:55:07
 */
@XmlType
@XmlEnum(String.class)
public enum AdvTransType {

    @XmlEnumValue("N") New             ("N"),
    @XmlEnumValue("C") Cancel          ("C"),
    @XmlEnumValue("R") Replace         ("R");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, AdvTransType> stringToEnum = new HashMap<String, AdvTransType>();

    static {
        for (AdvTransType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of AdvTransType */
    AdvTransType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static AdvTransType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
