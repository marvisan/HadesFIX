/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlCurrFxRateCalc.java
 *
 * $Id: SettlCurrFxRateCalc.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * SettlCurrFxRate type of calculation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 21/04/2009, 10:48:27 AM
 */
@XmlType
@XmlEnum(String.class)
public enum SettlCurrFxRateCalc {

    @XmlEnumValue("M") Multiply                ('M'),
    @XmlEnumValue("D") Divide                  ('D');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, SettlCurrFxRateCalc> stringToEnum = new HashMap<String, SettlCurrFxRateCalc>();

    static {
        for (SettlCurrFxRateCalc tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlCurrFxRateCalc */
    SettlCurrFxRateCalc(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static SettlCurrFxRateCalc valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
