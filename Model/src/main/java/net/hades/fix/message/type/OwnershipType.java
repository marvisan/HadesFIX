/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * OwnershipType.java
 *
 * $Id: OwnershipType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code for relationship between Registration parties.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 24/09/2009, 9:24:04 PM
 */
@XmlType
@XmlEnum(String.class)
public enum OwnershipType {

    @XmlEnumValue("J") JointInvestors           ('J'),
    @XmlEnumValue("T") TenantsInCommon          ('T'),
    @XmlEnumValue("2") JointTrustees            ('2');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, OwnershipType> stringToEnum = new HashMap<String, OwnershipType>();

    static {
        for (OwnershipType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of OwnershipType */
    OwnershipType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static OwnershipType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
