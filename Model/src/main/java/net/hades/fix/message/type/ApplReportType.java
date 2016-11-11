/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ApplReportType.java
 *
 * $Id: ApplReportType.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type or application report.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ApplReportType {

    @XmlEnumValue("0")      ResetApplSeqNum                     (0),
    @XmlEnumValue("1")      LastMessageHasBeenSent              (1),
    @XmlEnumValue("2")      Heartbeat                           (2),
    @XmlEnumValue("3")      MessageResendCompleted              (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ApplReportType> stringToEnum = new HashMap<String, ApplReportType>();

    static {
        for (ApplReportType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ApplReportType */
    ApplReportType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ApplReportType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
