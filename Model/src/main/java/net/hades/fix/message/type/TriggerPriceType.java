/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TriggerPriceType.java
 *
 * $Id: TriggerPriceType.java,v 1.2 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines the type of price that the trigger is compared to.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum TriggerPriceType {

    @XmlEnumValue("1") BestOffer                       ('1'),
    @XmlEnumValue("2") LastTrade                       ('2'),
    @XmlEnumValue("3") BestBid                         ('3'),
    @XmlEnumValue("4") BestBidOrLastTrade              ('4'),
    @XmlEnumValue("5") BestOfferOrLastTrade            ('5'),
    @XmlEnumValue("6") BestMid                         ('6');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, TriggerPriceType> stringToEnum = new HashMap<String, TriggerPriceType>();

    static {
        for (TriggerPriceType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TriggerPriceType */
    TriggerPriceType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static TriggerPriceType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
