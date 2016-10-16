/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdRptStatus.java
 *
 * $Id: TrdRptStatus.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Trade report status.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 22/08/2009, 9:17:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TrdRptStatus {

    @XmlEnumValue("0") Accepted                     (0),
    @XmlEnumValue("1") Rejected                     (1),
    @XmlEnumValue("3") AcceptedWithErrors           (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TrdRptStatus> stringToEnum = new HashMap<String, TrdRptStatus>();

    static {
        for (TrdRptStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TrdRptStatus */
    TrdRptStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TrdRptStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
