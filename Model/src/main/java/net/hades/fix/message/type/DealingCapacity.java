/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DealingCapacity.java
 *
 * $Id: DealingCapacity.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Role of the dealer.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 31/10/2009, 4:36:25 PM
 */
@XmlType
@XmlEnum(String.class)
public enum DealingCapacity {

    @XmlEnumValue("A") Agent                    ('A'),
    @XmlEnumValue("P") Principal                ('P'),
    @XmlEnumValue("R") RisklessPrincipal        ('R');

    private static final long serialVersionUID = -4331377559609971128L;

    private char value;

    private static final Map<String, DealingCapacity> stringToEnum = new HashMap<String, DealingCapacity>();

    static {
        for (DealingCapacity tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DealingCapacity */
    DealingCapacity(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static DealingCapacity valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
