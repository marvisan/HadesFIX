/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Product.java
 *
 * $Id: Product.java,v 1.5 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of product.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 28/10/2008, 7:35:50 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum Product {

    @XmlEnumValue("1")      AGENCY              (1),
    @XmlEnumValue("2")      COMMODITY           (2),
    @XmlEnumValue("3")      CORPORATE           (3),
    @XmlEnumValue("4")      CURRENCY            (4),
    @XmlEnumValue("5")      EQUITY              (5),
    @XmlEnumValue("6")      GOVERNMENT          (6),
    @XmlEnumValue("7")      INDEX               (7),
    @XmlEnumValue("8")      LOAN                (8),
    @XmlEnumValue("9")      MONEYMARKET         (9),
    @XmlEnumValue("10")     MORTGAGE            (10),
    @XmlEnumValue("11")     MUNICIPAL           (11),
    @XmlEnumValue("12")     OTHER               (12),
    @XmlEnumValue("13")     FINANCING           (13);

    private static final long serialVersionUID = 1L;
    
    private int value;

    private static final Map<String, Product> stringToEnum = new HashMap<String, Product>();

    static {
        for (Product tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of Product */
    Product(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static Product valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
