/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstReqRejCode.java
 *
 * $Id: SettlInstReqRejCode.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Settlement reject reason code.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SettlInstReqRejCode {

    @XmlEnumValue("0")  UnableToProcessRequest                          (0),
    @XmlEnumValue("1")  UnknownAccount                                  (1),
    @XmlEnumValue("2")  NoMatchingSettlement                            (2),
    @XmlEnumValue("99") Other                                           (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, SettlInstReqRejCode> stringToEnum = new HashMap<String, SettlInstReqRejCode>();

    static {
        for (SettlInstReqRejCode tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlInstReqRejCode */
    SettlInstReqRejCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SettlInstReqRejCode valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
