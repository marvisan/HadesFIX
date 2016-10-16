/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DeliveryForm.java
 *
 * $Id: DeliveryForm.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the form of delivery.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 27/09/2009, 11:44:15 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum DeliveryForm {

    @XmlEnumValue("1")      BookEntry       (1),
    @XmlEnumValue("2")      Bearer          (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, DeliveryForm> stringToEnum = new HashMap<String, DeliveryForm>();

    static {
        for (DeliveryForm tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DeliveryForm */
    DeliveryForm(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DeliveryForm valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
