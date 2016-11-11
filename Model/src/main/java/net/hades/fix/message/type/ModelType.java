/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ModelType.java
 *
 * $Id: ModelType.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of pricing model used.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 30/08/2009, 12:35:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ModelType {

    @XmlEnumValue("0") UtilityProvidedStandardModel     (0),
    @XmlEnumValue("1") ProprietaryModel                 (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ModelType> stringToEnum = new HashMap<String, ModelType>();

    static {
        for (ModelType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ModelType */
    ModelType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ModelType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
