/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CancellationRights.java
 *
 * $Id: CancellationRights.java,v 1.3 2010-01-14 09:06:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code identifying whether Cancellation rights/Cooling off period applies.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 22/09/2009, 8:38:30 PM
 */
@XmlType
@XmlEnum(String.class)
public enum CancellationRights {

    @XmlEnumValue("Y") Yes                              ('Y'),
    @XmlEnumValue("N") No_ExecutionOnly                 ('N'),
    @XmlEnumValue("M") No_WaiverAgreement               ('M'),
    @XmlEnumValue("O") No_Institutional                 ('O');

    private static final long serialVersionUID = -3457718745616106531L;

    private char value;

    private static final Map<String, CancellationRights> stringToEnum = new HashMap<String, CancellationRights>();

    static {
        for (CancellationRights tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CancellationRights */
    CancellationRights(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static CancellationRights valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
