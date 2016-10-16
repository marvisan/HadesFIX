/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassCancelRejectReason.java
 *
 * $Id: MassCancelRejectReason.java,v 1.5 2011-05-07 06:58:56 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason type Order Mass Cancel Request was rejected.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 26/09/2009, 7:06:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MassCancelRejectReason {

    @XmlEnumValue("0")  MassCancelNotSupported                          (0),
    @XmlEnumValue("1")  UnknownSecurity                                 (1),
    @XmlEnumValue("2")  UnkownUnderlying                                (2),
    @XmlEnumValue("3")  UnknownProduct                                  (3),
    @XmlEnumValue("4")  UnknownCFICode                                  (4),
    @XmlEnumValue("5")  UnknownSecurityType                             (5),
    @XmlEnumValue("6")  UnknownTradingSession                           (6),
    @XmlEnumValue("7")  UnknownMarket                                   (7),
    @XmlEnumValue("8")  UnkownMarketSegment                             (8),
    @XmlEnumValue("9")  UnknownSecurityGroup                            (9),
    @XmlEnumValue("10") UnknownSecurityIssuer                           (10),
    @XmlEnumValue("11") UnknownIssuerOfUnderlyingSecurity               (11),
    @XmlEnumValue("99") Other                                           (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MassCancelRejectReason> stringToEnum = new HashMap<String, MassCancelRejectReason>();

    static {
        for (MassCancelRejectReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MassCancelRejectReason */
    MassCancelRejectReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MassCancelRejectReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
