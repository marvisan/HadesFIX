/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BasisPxType.java
 *
 * $Id: BasisPxType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to represent the basis price type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 05/09/2009, 4:38:12 PM
 */
@XmlType
@XmlEnum(String.class)
public enum BasisPxType {

    @XmlEnumValue("2") ClosingPriceMorningnSession          ('2'),
    @XmlEnumValue("3") ClosingPrice                         ('3'),
    @XmlEnumValue("4") CurrentPrice                         ('4'),
    @XmlEnumValue("5") SQ                                   ('5'),
    @XmlEnumValue("6") VWAPThroughDay                       ('6'),
    @XmlEnumValue("7") VWAPThroughMorningSession            ('7'),
    @XmlEnumValue("8") VWAPThroughAfternoonSession          ('8'),
    @XmlEnumValue("9") VWAPDayOpeningAuction                ('9'),
    @XmlEnumValue("A") VWAPMorningSessionOpeningAuction     ('A'),
    @XmlEnumValue("B") VWAPAfternoonSessionOpeningAuction   ('B'),
    @XmlEnumValue("C") Strike                               ('C'),
    @XmlEnumValue("D") Open                                 ('D'),
    @XmlEnumValue("Z") Others                               ('Z');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, BasisPxType> stringToEnum = new HashMap<String, BasisPxType>();

    static {
        for (BasisPxType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of BasisPxType */
    BasisPxType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static BasisPxType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
