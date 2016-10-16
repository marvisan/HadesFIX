/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidDescriptorType.java
 *
 * $Id: BidDescriptorType.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to identify the type of BidDescriptor.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 4:09:17 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum BidDescriptorType {

    @XmlEnumValue("1")      Sector              (1),
    @XmlEnumValue("2")      Country             (2),
    @XmlEnumValue("3")      Index               (3);

    private static final long serialVersionUID = -7043082219304005631L;

    private int value;

    private static final Map<String, BidDescriptorType> stringToEnum = new HashMap<String, BidDescriptorType>();

    static {
        for (BidDescriptorType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of BidDescriptorType */
    BidDescriptorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BidDescriptorType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
