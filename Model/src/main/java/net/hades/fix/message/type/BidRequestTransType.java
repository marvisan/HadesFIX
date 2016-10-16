/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRequestTransType.java
 *
 * $Id: BidRequestTransType.java,v 1.3 2010-01-14 09:06:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Bid Request message type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 3:22:43 PM
 */
@XmlType
@XmlEnum(String.class)
public enum BidRequestTransType {

    @XmlEnumValue("C") Cancel           ('C'),
    @XmlEnumValue("N") New              ('N');

    private static final long serialVersionUID = -8649475049333352985L;

    private char value;

    private static final Map<String, BidRequestTransType> stringToEnum = new HashMap<String, BidRequestTransType>();

    static {
        for (BidRequestTransType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of BidRequestTransType */
    BidRequestTransType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static BidRequestTransType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
