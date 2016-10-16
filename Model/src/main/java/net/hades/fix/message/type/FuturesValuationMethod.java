/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FuturesValuationMethod.java
 *
 * $Id: FuturesValuationMethod.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * For futures, indicates type of valuation method applied.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/12/2008, 7:41:35 PM
 */
@XmlType
@XmlEnum(String.class)
public enum FuturesValuationMethod {

    @XmlEnumValue("EQTY")   Equity                  ("EQTY"),
    @XmlEnumValue("FUT")    Future                  ("FUT"),
    @XmlEnumValue("FUTDA")  FutureAdjustment        ("FUTDA");

    private static final long serialVersionUID = -1117252862627490628L;

    private String value;

    private static final Map<String, FuturesValuationMethod> stringToEnum = new HashMap<String, FuturesValuationMethod>();

    static {
        for (FuturesValuationMethod tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of FuturesValuationMethod. */
    FuturesValuationMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FuturesValuationMethod valueFor(String value) {
        return stringToEnum.get(value);
    }
}
