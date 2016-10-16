/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IDSource.java
 *
 * $Id: SecurityIDSource.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Source of alternate security.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 29/06/2008, 18:39:38
 */
@XmlType
@XmlEnum(String.class)
public enum SecurityIDSource {

    @XmlEnumValue("1") CUSIP                   ("1"),
    @XmlEnumValue("2") SEDOL                   ("2"),
    @XmlEnumValue("3") QUIK                    ("3"),
    @XmlEnumValue("4") ISIN                    ("4"),
    @XmlEnumValue("5") RIC                     ("5"),
    @XmlEnumValue("6") ISOCurrency             ("6"),
    @XmlEnumValue("7") ISOCountry              ("7"),
    @XmlEnumValue("8") Exchange                ("8"),
    @XmlEnumValue("9") CTA                     ("9"),  // Consolidated Tape Association (CTA) Symbol (SIAC CTS/CQS line format)
    @XmlEnumValue("A") BloombergSymbol         ("A"),
    @XmlEnumValue("B") Wertpapier              ("B"),
    @XmlEnumValue("C") Dutch                   ("C"),
    @XmlEnumValue("D") Valoren                 ("D"),
    @XmlEnumValue("E") Sicovam                 ("E"),
    @XmlEnumValue("F") Belgian                 ("F"),
    @XmlEnumValue("G") ClearstreamEuroclear    ("G"),
    @XmlEnumValue("H") ClearingHouse           ("H"),
    @XmlEnumValue("I") ISDA_XML                ("I"),  // ISDA/FpML Product Specification (XML in EncodedSecurityDesc)
    @XmlEnumValue("J") OPRA                    ("J"),  // Option Price Reporting Authority
    @XmlEnumValue("K") ISDA_URL                ("K"),  // ISDA/FpML Product URL (URL in SecurityID)
    @XmlEnumValue("L") LetterOfCredit          ("L"),
    @XmlEnumValue("M") Marketplace             ("M");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, SecurityIDSource> stringToEnum = new HashMap<String, SecurityIDSource>();

    static {
        for (SecurityIDSource tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of IDSource */
    SecurityIDSource(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static SecurityIDSource valueFor(String value) {
        return stringToEnum.get(value);
    }
}
