/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TransportStatsParam.java
 *
 * $Id: TransportStatsParam.java,v 1.1 2011-03-21 04:53:04 vrotaru Exp $
 */
package net.hades.fix.admin.command.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Parameters for the transport statistics.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/03/2011
 */
public enum TransportStatsParam {

    StartTimestamp              ("startTimestamp"),
    BytesIn                     ("bytesIn"),
    ThroughputIn                ("throughputIn"),
    BytesOut                    ("bytesOut"),
    ThroughputOut               ("throughputOut");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, TransportStatsParam> stringToEnum = new HashMap<String, TransportStatsParam>();

    static {
        for (TransportStatsParam tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of TransportStatsParam */
    TransportStatsParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransportStatsParam valueFor(String value) {
        return stringToEnum.get(value);
    }
}
