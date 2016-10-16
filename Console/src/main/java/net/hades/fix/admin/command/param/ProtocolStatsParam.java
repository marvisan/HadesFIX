/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProtocolStatsParam.java
 *
 * $Id: ProtocolStatsParam.java,v 1.1 2011-03-21 04:53:04 vrotaru Exp $
 */
package net.hades.fix.admin.command.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Parameters for the protocol statistics.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/03/2011
 */
public enum ProtocolStatsParam {

    StartTimestamp              ("startTimestamp"),
    TotMsgInCount               ("totMsgInCount"),
    ThroughputIn                ("throughputIn"),
    TotMsgOutCount              ("totMsgOutCount"),
    ThroughputOut               ("throughputOut"),
    RejMsgCount                 ("rejMsgCount");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, ProtocolStatsParam> stringToEnum = new HashMap<String, ProtocolStatsParam>();

    static {
        for (ProtocolStatsParam tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of ProtocolStatsParam */
    ProtocolStatsParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProtocolStatsParam valueFor(String value) {
        return stringToEnum.get(value);
    }
}
