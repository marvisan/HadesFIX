/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RoutingType.java
 *
 * $Id: RoutingType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates the type of RoutingID.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 10/02/2009, 6:51:15 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum RoutingType {

    @XmlEnumValue("1") TargetFirm                  (1),
    @XmlEnumValue("2") TargetList                  (2),
    @XmlEnumValue("3") BlockFirm                   (3),
    @XmlEnumValue("4") BlockList                   (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, RoutingType> stringToEnum = new HashMap<String, RoutingType>();

    static {
        for (RoutingType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RoutingType */
    RoutingType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RoutingType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
