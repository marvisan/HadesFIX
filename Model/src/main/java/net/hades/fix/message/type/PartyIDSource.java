/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartyIDSource.java
 *
 * $Id: PartyIDSource.java,v 1.3 2010-01-14 09:06:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * PartyIDSource value within an instrument.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 07/12/2008, 12:57:58 PM
 */
@XmlType
@XmlEnum(String.class)
public enum PartyIDSource {

    @XmlEnumValue("B") BIC                                 ('B'),
    @XmlEnumValue("C") NASD                                ('C'),
    @XmlEnumValue("D") Proprietary                         ('D'),
    @XmlEnumValue("E") ISOCountryCode                      ('E'),
    @XmlEnumValue("F") SettlementEntityLocation            ('F'),
    @XmlEnumValue("G") MarketIdentificerCode               ('G'),
    @XmlEnumValue("H") CSD                                 ('H'),
    @XmlEnumValue("6") UKNumber                            ('6'),
    @XmlEnumValue("7") USSSN                               ('7'),
    @XmlEnumValue("8") USTaxID                             ('8'),
    @XmlEnumValue("9") AustralianBusinessNumber            ('9'),
    @XmlEnumValue("A") AustralianTaxFileNumber             ('A'),
    @XmlEnumValue("1") KoreanInvestorID                    ('1'),
    @XmlEnumValue("2") TaiwaneseInvestorID                 ('2'),
    @XmlEnumValue("3") TaiwaneseTradingAcct                ('3'),
    @XmlEnumValue("4") MalaysianCentralDepository          ('4'),
    @XmlEnumValue("5") ChineseInvestorID                   ('5'),
    @XmlEnumValue("I") DirectedBroker                      ('I');

    private static final long serialVersionUID = -857314204616734390L;

    private char value;

    private static final Map<String, PartyIDSource> stringToEnum = new HashMap<String, PartyIDSource>();

    static {
        for (PartyIDSource tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PartyIDSource */
    PartyIDSource(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static PartyIDSource valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
