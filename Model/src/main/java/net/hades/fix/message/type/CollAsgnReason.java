/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollAsgnReason.java
 *
 * $Id: AllocType.java,v 1.4 2011-04-19 12:13:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason for Collateral Assignment.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CollAsgnReason {

    @XmlEnumValue("0")      Initial                                 (0),
    @XmlEnumValue("1")      Scheduled                               (1),
    @XmlEnumValue("2")      TimeWarning                             (2),
    @XmlEnumValue("3")      MarginDeficiency                        (3),
    @XmlEnumValue("4")      MarginExcess                            (4),
    @XmlEnumValue("5")      ForwardCollateralDemand                 (5),
    @XmlEnumValue("6")      EventOfDefault                          (6),
    @XmlEnumValue("7")      AdverseTaxEvent                         (7);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CollAsgnReason> stringToEnum = new HashMap<String, CollAsgnReason>();

    static {
        for (CollAsgnReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CollAsgnReason */
    CollAsgnReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CollAsgnReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
