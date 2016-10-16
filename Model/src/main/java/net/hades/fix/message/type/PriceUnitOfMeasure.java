/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnitOfMeasure.java
 *
 * $Id: PriceUnitOfMeasure.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Price unit of measure valid values.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/11/2008, 8:10:11 PM
 */
@XmlType
@XmlEnum(String.class)
public enum PriceUnitOfMeasure {

    @XmlEnumValue("Bcf")    BillionCubicFeet                ("Bcf"),
    @XmlEnumValue("MMbbl")  MillionBarrels                  ("MMbbl"),
    @XmlEnumValue("MMBtu")  OneMillionBTU                   ("MMBtu"),
    @XmlEnumValue("MWh")    MegawattHours                   ("MWh"),
    @XmlEnumValue("Bbl")    Barrels                         ("Bbl"),
    @XmlEnumValue("Bu")     Bushels                         ("Bu"),
    @XmlEnumValue("lbs")    Pounds                          ("lbs"),
    @XmlEnumValue("Gal")    Gallons                         ("Gal"),
    @XmlEnumValue("oz_tr")  TroyOunces                      ("oz_tr"),
    @XmlEnumValue("t")      MetricTons                      ("t"),
    @XmlEnumValue("tn")     USTons                          ("tn"),
    @XmlEnumValue("USD")    USDollars                       ("USD");

    private static final long serialVersionUID = -503905673519345758L;

    private String value;

    private static final Map<String, PriceUnitOfMeasure> stringToEnum = new HashMap<String, PriceUnitOfMeasure>();

    static {
        for (PriceUnitOfMeasure tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of AdvSide. */
    PriceUnitOfMeasure(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PriceUnitOfMeasure valueFor(String value) {
        return stringToEnum.get(value);
    }
}
