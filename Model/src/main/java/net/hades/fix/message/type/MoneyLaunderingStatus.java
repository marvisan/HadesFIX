/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MoneyLaunderingStatus.java
 *
 * $Id: MoneyLaunderingStatus.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code identifying Money laundering status.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 22/09/2009, 8:46:02 PM
 */
@XmlType
@XmlEnum(String.class)
public enum MoneyLaunderingStatus {

    @XmlEnumValue("Y") Passed                           ('Y'),
    @XmlEnumValue("N") NotChecked                       ('N'),
    @XmlEnumValue("1") Exempt_BelowLimit                ('1'),
    @XmlEnumValue("2") Exempt_ClientMoney               ('2'),
    @XmlEnumValue("3") Exempt_AuthorisedCredit          ('3');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, MoneyLaunderingStatus> stringToEnum = new HashMap<String, MoneyLaunderingStatus>();

    static {
        for (MoneyLaunderingStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MoneyLaunderingStatus */
    MoneyLaunderingStatus(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static MoneyLaunderingStatus valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
