/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecPriceType.java
 *
 * $Id: ExecPriceType.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code that identifies how the execution price LastPx (31) was calculated.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 22/09/2009, 8:56:30 PM
 */
@XmlType
@XmlEnum(String.class)
public enum ExecPriceType {

    @XmlEnumValue("B") BidPrice                                     ('B'),
    @XmlEnumValue("C") CreationPrice                                ('C'),
    @XmlEnumValue("D") CreationPricePlusAdjustPercent               ('D'),
    @XmlEnumValue("E") CreationPricePlusAdjustAmount                ('E'),
    @XmlEnumValue("O") OfferPrice                                   ('O'),
    @XmlEnumValue("P") OfferPriceMinusAdjustPercent                 ('P'),
    @XmlEnumValue("Q") OfferPriceMinusAdjustAmount                  ('Q'),
    @XmlEnumValue("S") SinglePrice                                  ('S');

    private static final long serialVersionUID = -1513550321332632447L;

    private char value;

    private static final Map<String, ExecPriceType> stringToEnum = new HashMap<String, ExecPriceType>();

    static {
        for (ExecPriceType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ExecPriceType */
    ExecPriceType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static ExecPriceType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
