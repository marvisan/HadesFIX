/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIQty.java
 *
 * $Id: IOIQty.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Quantity (e.g. number of shares) in numeric form or relative size.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 10/02/2009, 4:36:09 PM
 */
@XmlType
@XmlEnum(String.class)
public enum IOIQty {

    @XmlEnumValue("O") Billion                     ("O"),
    @XmlEnumValue("S") Small                       ("S"),
    @XmlEnumValue("M") Medium                      ("M"),
    @XmlEnumValue("L") Large                       ("L"),
    @XmlEnumValue("U") UndisclosedQuantity         ("U");

    private static final long serialVersionUID = -5680104147474432812L;

    private String value;

    private static final Map<String, IOIQty> stringToEnum = new HashMap<String, IOIQty>();

    static {
        for (IOIQty tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of IOIQty */
    IOIQty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static IOIQty valueFor(String value) {
        return stringToEnum.get(value);
    }
}
