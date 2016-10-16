/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CorporateAction.java
 *
 * $Id: CorporateAction.java,v 1.5 2011-04-20 00:32:35 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Corporate Action.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 21/08/2009, 9:43:03 PM
 */
@XmlType
@XmlEnum(String.class)
public enum CorporateAction {

    @XmlEnumValue("A") ExDividend                              ('A'),
    @XmlEnumValue("B") ExDistribution                          ('B'),
    @XmlEnumValue("C") ExRights                                ('C'),
    @XmlEnumValue("D") New                                     ('D'),
    @XmlEnumValue("E") ExInterest                              ('E'),
    @XmlEnumValue("F") CashDividend                            ('F'),
    @XmlEnumValue("G") StockDividend                           ('G'),
    @XmlEnumValue("H") NonIntegerStockSplit                    ('H'),
    @XmlEnumValue("I") ReverseStockSplit                       ('I'),
    @XmlEnumValue("J") StandardIntegerStockSplit               ('J'),
    @XmlEnumValue("K") PositionConsolidation                   ('K'),
    @XmlEnumValue("L") LiquidationReorganization               ('L'),
    @XmlEnumValue("M") MergerReorganization                    ('M'),
    @XmlEnumValue("N") RightsOffering                          ('N'),
    @XmlEnumValue("O") ShareholderMeeting                      ('O'),
    @XmlEnumValue("P") Spinoff                                 ('P'),
    @XmlEnumValue("Q") TenderOffer                             ('Q'),
    @XmlEnumValue("R") Warrant                                 ('R'),
    @XmlEnumValue("S") SpecialAction                           ('S'),
    @XmlEnumValue("T") SymbolConversion                        ('T'),
    @XmlEnumValue("U") CUSIPNameChange                         ('U'),
    @XmlEnumValue("V") LeapRollover                            ('V'),
    @XmlEnumValue("W") SuccessionEvent                         ('W');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, CorporateAction> stringToEnum = new HashMap<String, CorporateAction>();

    static {
        for (CorporateAction tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CorporateAction */
    CorporateAction(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static CorporateAction valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
