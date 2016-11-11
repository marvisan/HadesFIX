/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * MultilegModel.java
 *
 * $Id: MultilegModel.java,v 1.1 2011-04-19 12:13:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of multi-leg order.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MultilegModel {

    @XmlEnumValue("0")      PredefinedMultilegSecurity          (0),
    @XmlEnumValue("1")      UserDefinedMultlegSecurit           (1),
    @XmlEnumValue("2")      UserDefinedNonSecuritized           (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MultilegModel> stringToEnum = new HashMap<String, MultilegModel>();

    static {
        for (MultilegModel tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MultilegModel */
    MultilegModel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MultilegModel valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
