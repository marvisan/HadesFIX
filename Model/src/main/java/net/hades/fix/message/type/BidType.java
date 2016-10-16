/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidType.java
 *
 * $Id: BidType.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to identify the type of Bid Request.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/09/2009, 4:02:03 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum BidType {

    @XmlEnumValue("1")      NonDisclosed            (1),
    @XmlEnumValue("2")      Disclosed               (2),
    @XmlEnumValue("3")      NoBiddingProcess        (3);

    private static final long serialVersionUID = -8862424972555457458L;

    private int value;

    private static final Map<String, BidType> stringToEnum = new HashMap<String, BidType>();

    static {
        for (BidType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of BidType */
    BidType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BidType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
