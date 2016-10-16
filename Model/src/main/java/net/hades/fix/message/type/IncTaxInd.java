/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IncTaxInd.java
 *
 * $Id: IncTaxInd.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent whether value is net (inclusive of tax) or gross.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 05/09/2009, 4:32:03 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum IncTaxInd {

    @XmlEnumValue("1")      Net         (1),
    @XmlEnumValue("2")      Gross       (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, IncTaxInd> stringToEnum = new HashMap<String, IncTaxInd>();

    static {
        for (IncTaxInd tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of IncTaxInd */
    IncTaxInd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static IncTaxInd valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
