/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PegPriceType.java
 *
 * $Id: PegPriceType.java,v 1.1 2010-12-05 08:13:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of peg.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/06/2009, 8:40:22 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PegPriceType {

    @XmlEnumValue("1")  LastPeg                     (1),
    @XmlEnumValue("2")  MidPricePeg                 (2),
    @XmlEnumValue("3")  OpeningPeg                  (3),
    @XmlEnumValue("4")  MarketPeg                   (4),
    @XmlEnumValue("5")  PrimaryPeg                  (5),
    @XmlEnumValue("7")  PegToVWAP                   (7),
    @XmlEnumValue("8")  TrailingStopPeg             (8),
    @XmlEnumValue("9")  PegToLimitPrice             (9);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PegPriceType> stringToEnum = new HashMap<String, PegPriceType>();

    static {
        for (PegPriceType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PegPriceType */
    PegPriceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PegPriceType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
