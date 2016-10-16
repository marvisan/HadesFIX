/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OptPayoutType.java
 *
 * $Id: OptPayoutType.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates the type of payout that will result from an in-the-money option.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 03/06/2009, 3:15:55 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum OptPayoutType {

    @XmlEnumValue("1") Vanilla          (1),
    @XmlEnumValue("2") Capped           (2),
    @XmlEnumValue("3") Binary           (3);

    private static final long serialVersionUID = -4527546135918040933L;

    private int value;

    private static final Map<String, OptPayoutType> stringToEnum = new HashMap<String, OptPayoutType>();

    static {
        for (OptPayoutType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of OptPayoutType */
    OptPayoutType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OptPayoutType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
