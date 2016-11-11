/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollAsgnRejectReason.java
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
 * Collateral Assignment Reject Reason.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 16/12/2011, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CollAsgnRejectReason {

    @XmlEnumValue("0")  UnknownDeal                                 (0),
    @XmlEnumValue("1")  UnknownInstrument                           (1),
    @XmlEnumValue("2")  UnauthorizedTransaction                     (2),
    @XmlEnumValue("3")  InsufficientCollateral                      (3),
    @XmlEnumValue("4")  InvalidTypeOfCollateral                     (4),
    @XmlEnumValue("5")  ExcessiveSubstitution                       (5),
    @XmlEnumValue("99") Other                                       (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CollAsgnRejectReason> stringToEnum = new HashMap<String, CollAsgnRejectReason>();

    static {
        for (CollAsgnRejectReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CollAsgnRejectReason */
    CollAsgnRejectReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CollAsgnRejectReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
