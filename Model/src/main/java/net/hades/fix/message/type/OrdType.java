/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrdType.java
 *
 * $Id: OrdType.java,v 1.4 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of order.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 1/07/2008, 20:24:37
 */
@XmlType
@XmlEnum(String.class)
public enum OrdType {

    @XmlEnumValue("1") Market                          ('1'),
    @XmlEnumValue("2") Limit                           ('2'),
    @XmlEnumValue("3") Stop                            ('3'),
    @XmlEnumValue("4") StopLimit                       ('4'),
    @XmlEnumValue("5") MarketOnClose                   ('5'),
    @XmlEnumValue("6") WithOrWithout                   ('6'),
    @XmlEnumValue("7") LimitOrBetter                   ('7'),
    @XmlEnumValue("8") LimitWithOrWithout              ('8'),
    @XmlEnumValue("9") OnBasis                         ('9'),
    @XmlEnumValue("A") OnClose                         ('A'),
    @XmlEnumValue("B") LimitOnClose                    ('B'),
    @XmlEnumValue("C") ForexMarket                     ('C'),
    @XmlEnumValue("D") PreviouslyQuoted                ('D'),
    @XmlEnumValue("E") PreviouslyIndicated             ('E'),
    @XmlEnumValue("F") ForexLimit                      ('F'),
    @XmlEnumValue("G") ForexSwap                       ('G'),
    @XmlEnumValue("H") ForexPreviouslyQuoted           ('H'),
    @XmlEnumValue("I") Funari                          ('I'),
    @XmlEnumValue("P") Pegged                          ('P');

    private static final long serialVersionUID = 1L;
    
    private char value;

    private static final Map<String, OrdType> stringToEnum = new HashMap<String, OrdType>();

    static {
        for (OrdType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of OrdType */
    OrdType(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }
    
    public static OrdType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
