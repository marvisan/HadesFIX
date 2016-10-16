/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CxlRejReason.java
 *
 * $Id: CxlRejReason.java,v 1.4 2011-01-23 10:02:05 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason for cancelling or rejection.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 6/07/2008, 15:17:40
 */
@XmlType
@XmlEnum(Integer.class)
public enum CxlRejReason {

    @XmlEnumValue("0") TooLateToCancel         (0),
    @XmlEnumValue("1") UnknownOrder            (1),
    @XmlEnumValue("2") BrokerOption            (2),
    @XmlEnumValue("3") OrderPendingCancel      (3);

    private static final long serialVersionUID = -791219298272621119L;
    
    private int value;

    private static final Map<String, CxlRejReason> stringToEnum = new HashMap<String, CxlRejReason>();

    static {
        for (CxlRejReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    
    /** Creates a new instance of CxlRejReason */
    CxlRejReason(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static CxlRejReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
