/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FinancialStatus.java
 *
 * $Id: FinancialStatus.java,v 1.4 2010-01-14 09:06:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Security's financial status.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 21/08/2009, 9:39:41 PM
 */
@XmlType
@XmlEnum(String.class)
public enum FinancialStatus {

    @XmlEnumValue("1") Bankrupt                            ('1'),
    @XmlEnumValue("2") PendingDelisting                    ('2'),
    @XmlEnumValue("3") Restricted                          ('3');

    private static final long serialVersionUID = -230593956534879331L;

    private char value;

    private static final Map<String, FinancialStatus> stringToEnum = new HashMap<String, FinancialStatus>();

    static {
        for (FinancialStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of FinancialStatus */
    FinancialStatus(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static FinancialStatus valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
