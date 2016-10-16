/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartySubIDType.java
 *
 * $Id: PartySubIDType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of PartySubID (523) value. 4000+ = Reserved and available for bi-laterally agreed
 * upon user defined values.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 07/12/2008, 3:10:07 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PartySubIDType {

    @XmlEnumValue("1")  Firm                                            (1),
    @XmlEnumValue("2")  Person                                          (2),
    @XmlEnumValue("3")  System                                          (3),
    @XmlEnumValue("4")  Application                                     (4),
    @XmlEnumValue("5")  FullLegalNameOfFirm                             (5),
    @XmlEnumValue("6")  PostalAddress                                   (6),
    @XmlEnumValue("7")  PhoneNumber                                     (7),
    @XmlEnumValue("8")  EmailAddress                                    (8),
    @XmlEnumValue("9")  ContactName                                     (9),
    @XmlEnumValue("10") SecuritiesAccountNumber                         (10),
    @XmlEnumValue("11") RegistrationNumber                              (11),
    @XmlEnumValue("12") RegisteredAddress                               (12),
    @XmlEnumValue("13") RegulatoryStatus                                (13),
    @XmlEnumValue("14") RegistrationName                                (14),
    @XmlEnumValue("15") CashAccountNumber                               (15),
    @XmlEnumValue("16") BIC                                             (16),
    @XmlEnumValue("17") CSDParticipantMemberCode                        (17),
    @XmlEnumValue("18") RegisteredAddressConfirmation                   (18),
    @XmlEnumValue("19") FundAccountName                                 (19),
    @XmlEnumValue("20") TelexNumber                                     (20),
    @XmlEnumValue("21") FaxNumber                                       (21),
    @XmlEnumValue("22") SecuritiesAccountName                           (22),
    @XmlEnumValue("23") CashAccountName                                 (23),
    @XmlEnumValue("24") Department                                      (24),
    @XmlEnumValue("25") LocationDesk                                    (25),
    @XmlEnumValue("26") PositionAccountType                             (26),
    @XmlEnumValue("27") SecurityLocateID                                (27),
    @XmlEnumValue("28") MarketMaker                                     (28),
    @XmlEnumValue("29") EligibleCounterparty                            (29),
    @XmlEnumValue("30") ProfessionalClient                              (30),
    @XmlEnumValue("31") Location                                        (31),
    @XmlEnumValue("32") ExecutionVenue                                  (32),
    @XmlEnumValue("33") CurrencyDeliveryIdentifier                      (33);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PartySubIDType> stringToEnum = new HashMap<String, PartySubIDType>();

    static {
        for (PartySubIDType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PartySubIDType */
    PartySubIDType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PartySubIDType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
