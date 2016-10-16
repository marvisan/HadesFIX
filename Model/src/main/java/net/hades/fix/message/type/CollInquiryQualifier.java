/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollInquiryQualifier.java
 *
 * $Id: AllocType.java,v 1.4 2011-04-19 12:13:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Collateral inquiry qualifiers.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CollInquiryQualifier {

    @XmlEnumValue("0")  TradeDate                           (0),
    @XmlEnumValue("1")  GCInstrument                        (1),
    @XmlEnumValue("2")  CollateralInstrument                (2),
    @XmlEnumValue("3")  SubstitutionEligible                (3),
    @XmlEnumValue("4")  NotAssigned                         (4),
    @XmlEnumValue("5")  PartiallyAssigned                   (5),
    @XmlEnumValue("6")  FullyAssigned                       (6),
    @XmlEnumValue("7")  OutstandingTrades                   (7);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CollInquiryQualifier> stringToEnum = new HashMap<String, CollInquiryQualifier>();

    static {
        for (CollInquiryQualifier tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CollInquiryQualifier */
    CollInquiryQualifier(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CollInquiryQualifier valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
