/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollInquiryStatus.java
 *
 * $Id: CollInquiryStatus.java,v 1.1 2010-12-20 10:58:54 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Status of Collateral Inquiry.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:32:13 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum CollInquiryStatus {

    @XmlEnumValue("0") Accepted                         (0),
    @XmlEnumValue("1") AcceptedWithWarnings             (1),
    @XmlEnumValue("2") Completed                        (2),
    @XmlEnumValue("3") CompletedWithWarnings            (3),
    @XmlEnumValue("4") Rejected                         (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CollInquiryStatus> stringToEnum = new HashMap<String, CollInquiryStatus>();

    static {
        for (CollInquiryStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CollInquiryStatus */
    CollInquiryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CollInquiryStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
