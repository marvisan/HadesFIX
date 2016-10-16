/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProcessCode.java
 *
 * $Id: ProcessCode.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Processing code for sub-account.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 22/08/2009, 9:06:04 PM
 */
@XmlType
@XmlEnum(String.class)
public enum ProcessCode {

    @XmlEnumValue("0") Regular                  ('0'),
    @XmlEnumValue("1") SoftDollar               ('1'),
    @XmlEnumValue("2") StepIn                   ('2'),
    @XmlEnumValue("3") StepOut                  ('3'),
    @XmlEnumValue("4") SoftDollarStepIn         ('4'),
    @XmlEnumValue("5") SoftDollarStepOut        ('5'),
    @XmlEnumValue("6") PlanSponsor              ('6');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, ProcessCode> stringToEnum = new HashMap<String, ProcessCode>();

    static {
        for (ProcessCode tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ProcessCode */
    ProcessCode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static ProcessCode valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
