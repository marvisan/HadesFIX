/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FXRateCalc.java
 *
 * $Id: FXRateCalc.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates if FxRate should be multiplied or divided.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 16/12/2008, 7:13:58 PM
 */
@XmlType
@XmlEnum(String.class)
public enum FXRateCalc {

    @XmlEnumValue("D") Divide              ("D"),
    @XmlEnumValue("M") Multiply            ("M");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, FXRateCalc> stringToEnum = new HashMap<String, FXRateCalc>();

    static {
        for (FXRateCalc tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of FXRateCalc */
    FXRateCalc(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FXRateCalc valueFor(String value) {
        return stringToEnum.get(value);
    }
}
