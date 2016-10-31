/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BenchmarkCurveName.java
 *
 * $Id: BenchmarkCurveName.java,v 1.4 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Name of benchmark curve.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 10/02/2009, 7:01:39 PM
 */
@XmlType
@XmlEnum(String.class)
public enum BenchmarkCurveName {

    @XmlEnumValue("EONIA")          EONIA                       ("EONIA"),
    @XmlEnumValue("EUREPO")         EUREPO                      ("EUREPO"),
    @XmlEnumValue("Euribor")        EURIBOR                     ("Euribor"),
    @XmlEnumValue("FutureSWAP")     FUTURESWAP                  ("FutureSWAP"),
    @XmlEnumValue("LIBID")          LIBID                       ("LIBID"),
    @XmlEnumValue("LIBOR")          LIBOR                       ("LIBOR"),
    @XmlEnumValue("MuniAAA")        MUNIAAA                     ("MuniAAA"),
    @XmlEnumValue("OTHER")          OTHER                       ("OTHER"),
    @XmlEnumValue("Pfandbriefe")    PFANDBRIEFE                 ("Pfandbriefe"),
    @XmlEnumValue("SONIA")          SONIA                       ("SONIA"),
    @XmlEnumValue("SWAP")           SWAP                        ("SWAP"),
    @XmlEnumValue("Treasury")       TREASURY                    ("Treasury");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, BenchmarkCurveName> stringToEnum = new HashMap<String, BenchmarkCurveName>();

    static {
        for (BenchmarkCurveName tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of BenchmarkCurveName. */
    BenchmarkCurveName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BenchmarkCurveName valueFor(String value) {
        return stringToEnum.get(value);
    }
}
