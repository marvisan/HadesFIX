/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Side.java
 *
 * $Id: Side.java,v 1.3 2010-01-14 09:06:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Trade side of order.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 5/07/2008, 18:41:50
 */
@XmlType
@XmlEnum(String.class)
public enum Side {

    @XmlEnumValue("1") Buy                 ('1'),
    @XmlEnumValue("2") Sell                ('2'),
    @XmlEnumValue("3") BuyMinus            ('3'),
    @XmlEnumValue("4") SellPlus            ('4'),
    @XmlEnumValue("5") SellShort           ('5'),
    @XmlEnumValue("6") SellShortExempt     ('6'),
    @XmlEnumValue("7") Undisclosed         ('7'),
    @XmlEnumValue("8") Cross               ('8'),
    @XmlEnumValue("9") CrossShort          ('9'),
    @XmlEnumValue("A") CrossShorExempt     ('A'),
    @XmlEnumValue("B") AsDefined           ('B'),
    @XmlEnumValue("C") Opposite            ('C'),
    @XmlEnumValue("D") Subscribe           ('D'),
    @XmlEnumValue("E") Redeem              ('E'),
    @XmlEnumValue("F") Lend                ('F'),
    @XmlEnumValue("G") Borrow              ('G');

    private static final long serialVersionUID = -3008209280494594827L;
    
    private char value;

    private static final Map<String, Side> stringToEnum = new HashMap<String, Side>();

    static {
        for (Side tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }
    
    /** Creates a new instance of Side */
    Side(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }
    
    public static Side valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
