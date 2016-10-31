/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIQualifier.java
 *
 * $Id: IOIQualifier.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code to qualify IOI use.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 10/02/2009, 5:43:02 PM
 */
@XmlType
@XmlEnum(String.class)
public enum IOIQualifier {

    @XmlEnumValue("A") AllOrNone                           ('A'),
    @XmlEnumValue("B") MarketOnClose                       ('B'),
    @XmlEnumValue("C") AtTheClose                          ('C'),
    @XmlEnumValue("D") VolumeWeightedAveragePrice          ('D'),
    @XmlEnumValue("I") InTouchWith                         ('I'),
    @XmlEnumValue("L") Limit                               ('L'),
    @XmlEnumValue("M") MoreBehind                          ('M'),
    @XmlEnumValue("O") AtTheOpen                           ('O'),
    @XmlEnumValue("P") TakingPosition                      ('P'),
    @XmlEnumValue("Q") AtTheMarket                         ('Q'),
    @XmlEnumValue("R") ReadyToTrade                        ('R'),
    @XmlEnumValue("S") PortfolioShown                      ('S'),
    @XmlEnumValue("T") ThroughTheDay                       ('T'),
    @XmlEnumValue("V") Versus                              ('V'),
    @XmlEnumValue("W") WorkingAway                         ('W'),
    @XmlEnumValue("X") CrossingOpportunity                 ('X'),
    @XmlEnumValue("Y") AtTheMidpoint                       ('Y'),
    @XmlEnumValue("Z") PreOpen                             ('Z');

    private static final long serialVersionUID = -1180907820200189049L;

    private char value;

    private static final Map<String, IOIQualifier> stringToEnum = new HashMap<String, IOIQualifier>();

    static {
        for (IOIQualifier tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of IOIQualifier */
    IOIQualifier(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static IOIQualifier valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
