/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * VenueType.java
 *
 * $Id: VenueType.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of venue where a trade was executed.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 18/10/2008, 15:55:07
 */
@XmlType
@XmlEnum(String.class)
public enum VenueType {

    @XmlEnumValue("E") Electronic           ("E"),
    @XmlEnumValue("P") Pit                  ("P"),
    @XmlEnumValue("X") ExPit                ("X");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, VenueType> stringToEnum = new HashMap<String, VenueType>();

    static {
        for (VenueType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of VenueType. */
    VenueType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static VenueType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
