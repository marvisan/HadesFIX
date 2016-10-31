/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDReqRejReason.java
 *
 * $Id: MDReqRejReason.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of reason for the rejection.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/09/2009, 8:43:02 PM
 */
@XmlType
@XmlEnum(String.class)
public enum MDReqRejReason {

    @XmlEnumValue("0") UnknownSymbol                            ('0'),
    @XmlEnumValue("1") DuplicateMDReqID                         ('1'),
    @XmlEnumValue("2") InsufficientBandwidth                    ('2'),
    @XmlEnumValue("3") InsufficientPermissions                  ('3'),
    @XmlEnumValue("4") UnsupportedSubscriptionRequestType       ('4'),
    @XmlEnumValue("5") UnsupportedMarketDepth                   ('5'),
    @XmlEnumValue("6") UnsupportedMDUpdateType                  ('6'),
    @XmlEnumValue("7") UnsupportedAggregatedBook                ('7'),
    @XmlEnumValue("8") UnsupportedMDEntryType                   ('8'),
    @XmlEnumValue("9") UnsupportedTradingSessionID              ('9'),
    @XmlEnumValue("A") UnsupportedScope                         ('A'),
    @XmlEnumValue("B") UnsupportedOpenCloseSettleFlag           ('B'),
    @XmlEnumValue("C") UnsupportedMDImplicitDelete              ('C'),
    @XmlEnumValue("D") InsufficientCredit                       ('D');

    private static final long serialVersionUID = -8253272193185236912L;

    private char value;

    private static final Map<String, MDReqRejReason> stringToEnum = new HashMap<String, MDReqRejReason>();

    static {
        for (MDReqRejReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MDReqRejReason */
    MDReqRejReason(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static MDReqRejReason valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
