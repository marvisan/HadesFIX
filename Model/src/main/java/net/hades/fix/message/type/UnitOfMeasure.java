/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnitOfMeasure.java
 *
 * $Id: UnitOfMeasure.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Unit of measure valid values.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 27/11/2008, 8:10:11 PM
 */
@XmlType
@XmlEnum(String.class)
public enum UnitOfMeasure {

    @XmlEnumValue("Bcf") BillionCubicFeet           ("Bcf"),
    @XmlEnumValue("MMbbl") MillionBarrels           ("MMbbl"),
    @XmlEnumValue("MMBtu") OneMillionBTU            ("MMBtu"),
    @XmlEnumValue("MWh") MegawattHours              ("MWh"),
    @XmlEnumValue("Bbl") Barrels                    ("Bbl"),
    @XmlEnumValue("Bu") Bushels                     ("Bu"),
    @XmlEnumValue("lbs") Pounds                     ("lbs"),
    @XmlEnumValue("Gal") Gallons                    ("Gal"),
    @XmlEnumValue("oz_tr") TroyOunces               ("oz_tr"),
    @XmlEnumValue("t") MetricTons                   ("t"),
    @XmlEnumValue("tn") USTons                      ("tn");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, UnitOfMeasure> stringToEnum = new HashMap<String, UnitOfMeasure>();

    static {
        for (UnitOfMeasure tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of AdvSide. */
    UnitOfMeasure(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UnitOfMeasure valueFor(String value) {
        return stringToEnum.get(value);
    }
}
