/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollInquiryResult.java
 *
 * $Id: CollInquiryResult.java,v 1.1 2010-12-20 10:58:54 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Result returned in response to Collateral Inquiry.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:32:13 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CollInquiryResult {

    @XmlEnumValue("0") Successful                               (0),
    @XmlEnumValue("1") InvalidOrUnknownInstrument               (1),
    @XmlEnumValue("2") InvalidOrUnknownCollateralType           (2),
    @XmlEnumValue("3") InvalidParties                           (3),
    @XmlEnumValue("4") InvalidTransportType                     (4),
    @XmlEnumValue("5") InvalidDestination                       (5),
    @XmlEnumValue("6") NoCollateralFoundForTrade                (6),
    @XmlEnumValue("7") NoCollateralFoundForOrder                (7),
    @XmlEnumValue("8") CollInquiryTypeNotSupported              (8),
    @XmlEnumValue("9") UnauthorizedForCollInquiry               (9),
    @XmlEnumValue("99") Other                                   (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CollInquiryResult> stringToEnum = new HashMap<String, CollInquiryResult>();

    static {
        for (CollInquiryResult tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CollInquiryResult */
    CollInquiryResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CollInquiryResult valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
