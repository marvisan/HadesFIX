/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCategory.java
 *
 * $Id: OrderCategory.java,v 1.2 2011-01-15 02:10:11 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines the type of interest behind a trade.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum OrderCategory {

    @XmlEnumValue("1") Order                           ('1'),
    @XmlEnumValue("2") Quote                           ('2'),
    @XmlEnumValue("3") PrivatelyNegotiatedTrade        ('3'),
    @XmlEnumValue("4") MultilegOrder                   ('4'),
    @XmlEnumValue("5") LinkedOrder                     ('5'),
    @XmlEnumValue("6") QuoteRequest                    ('6'),
    @XmlEnumValue("7") ImpliedOrder                    ('7'),
    @XmlEnumValue("8") CrossOrder                      ('8'),
    @XmlEnumValue("9") StreamingPrice                  ('9');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, OrderCategory> stringToEnum = new HashMap<String, OrderCategory>();

    static {
        for (OrderCategory tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of OrderCategory */
    OrderCategory(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static OrderCategory valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
