/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * ShortSaleReason.java
 *
 * $Id: ShortSaleReason.java,v 1.1 2010-12-20 10:58:54 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason for short sale.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/09/2009, 8:59:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ShortSaleReason {

    @XmlEnumValue("0") DealerSoldShort                          (0),
    @XmlEnumValue("1") DealerSoldShortExempt                    (1),
    @XmlEnumValue("2") SellingCustSoldShort                     (2),
    @XmlEnumValue("3") SellingCusSoldShortExempt                (3),
    @XmlEnumValue("4") ContraSideSoldShort                      (4),
    @XmlEnumValue("5") ContraSideSoldShortExempt                (5);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ShortSaleReason> stringToEnum = new HashMap<String, ShortSaleReason>();

    static {
        for (ShortSaleReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ShortSaleReason */
    ShortSaleReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ShortSaleReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
