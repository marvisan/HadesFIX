/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionStatsParam.java
 *
 * $Id: SessionStatsResultParam.java,v 1.1 2011-03-21 04:53:04 vrotaru Exp $
 */
package net.hades.fix.admin.command.param;

/**
 * Parameters for the session statistics.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/03/2011
 */
public enum SessionStatsResultParam {

    SessionStats,
    TransportStats,
    ProtocolStats,
    ConsumerStreamStats,
    ProducerStreamStats;

    private static final long serialVersionUID = 1L;
}
