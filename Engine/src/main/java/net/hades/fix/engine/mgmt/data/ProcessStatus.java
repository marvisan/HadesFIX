/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProcessStatus.java
 *
 * $Id: ProcessStatus.java,v 1.7 2011-04-30 04:39:45 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.data;

/**
 * State a process/component is in.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 */
public enum ProcessStatus {
    INITIALISING,
    INACTIVE,
    ACTIVE,
    FROZEN,
    STOPPED,
    INCOMING_STOPPED,
    DISCONNECTED,
    TCP_READER_STOPPED,
    SHUTDOWN;

    private static final long serialVersionUID = 1L;
}
