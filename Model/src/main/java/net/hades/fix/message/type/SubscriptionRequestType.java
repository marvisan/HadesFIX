/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SubscriptionRequestType.java
 *
 * $Id: SubscriptionRequestType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Subscription Request Type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/06/2009, 10:35:30 AM
 */
@XmlType
@XmlEnum(String.class)
public enum SubscriptionRequestType {

    @XmlEnumValue("0") Snapshot                         ('0'),
    @XmlEnumValue("1") Subscribe                        ('1'),
    @XmlEnumValue("2") Unsubscribe                      ('2');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, SubscriptionRequestType> stringToEnum = new HashMap<String, SubscriptionRequestType>();

    static {
        for (SubscriptionRequestType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SubscriptionRequestType */
    SubscriptionRequestType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static SubscriptionRequestType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
