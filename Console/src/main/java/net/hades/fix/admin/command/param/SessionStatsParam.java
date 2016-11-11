/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionStatsParam.java
 *
 * $Id: SessionStatsParam.java,v 1.1 2011-03-21 04:53:04 vrotaru Exp $
 */
package net.hades.fix.admin.command.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Parameters for the session statistics.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/03/2011
 */
public enum SessionStatsParam {

    CptyAddress                 ("cptyAddress"),
    SessAddress                 ("sessAddress"),
    StartTimestamp              ("startTimestamp"),
    TransportStats              ("transportStats"),
    ProtocolStats               ("protocolStats"),
    ConsumerStreamStats         ("consumerStreamStats"),
    ProducerStreamStats         ("producerStreamStats");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, SessionStatsParam> stringToEnum = new HashMap<String, SessionStatsParam>();

    static {
        for (SessionStatsParam tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of SessionStatsParam */
    SessionStatsParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SessionStatsParam valueFor(String value) {
        return stringToEnum.get(value);
    }
}
