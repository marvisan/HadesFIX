/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StreamStatsParam.java
 *
 * $Id: StreamStatsParam.java,v 1.1 2011-03-21 04:53:04 vrotaru Exp $
 */
package net.hades.fix.admin.command.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Parameters for the stream statistics result.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/03/2011
 */
public enum StreamStatsParam {

    StartTimestamp                  ("startTimestamp"),
    MsgInCount                      ("msgInCount"),
    MsgOutCount                     ("msgOutCount"),
    MsgRejectCount                  ("msgRejectCount"),
    MsgDiscardCount                 ("msgDiscardCount");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, StreamStatsParam> stringToEnum = new HashMap<String, StreamStatsParam>();

    static {
        for (StreamStatsParam tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of StreamStatsParam */
    StreamStatsParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StreamStatsParam valueFor(String value) {
        return stringToEnum.get(value);
    }
}
