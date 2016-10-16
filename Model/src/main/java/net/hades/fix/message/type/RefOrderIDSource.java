/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PriceProtectionScope.java
 *
 * $Id: RefOrderIDSource.java,v 1.2 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines the type of price protection the customer requires on their order.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum RefOrderIDSource {

    @XmlEnumValue("0") SecondaryOrderID            ('0'),
    @XmlEnumValue("1") OrderID                     ('1'),
    @XmlEnumValue("2") MDEntryID                   ('2'),
    @XmlEnumValue("3") QuoteEntryID                ('3'),
    @XmlEnumValue("4") OriginalOrderID             ('4');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, RefOrderIDSource> stringToEnum = new HashMap<String, RefOrderIDSource>();

    static {
        for (RefOrderIDSource tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RefOrderIDSource */
    RefOrderIDSource(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static RefOrderIDSource valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
