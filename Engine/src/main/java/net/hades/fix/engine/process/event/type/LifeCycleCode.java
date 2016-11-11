/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LifeCycleCode.java
 *
 * $Id: LifeCycleCode.java,v 1.6 2011-03-29 11:04:28 vrotaru Exp $
 */
package net.hades.fix.engine.process.event.type;

/**
 * Life cycle event code.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 10/07/2009, 6:12:28 PM
 */
public enum LifeCycleCode {

    TRANSP_CONNECT,
    TRANSP_DISCONNECT,
    TRANSP_DISABLE,
    LISTENER_SHUTDOWN,
    FIX_LOGGED_ON,
    FIX_LOGGED_OUT,
    FIX_MSG_OUT_OF_SYNC,
    FIX_SESSION_RESTART,
    FIX_SESSION_SHUTDOWN,
    FIX_LOGON_TIMEOUT;

    private static final long serialVersionUID = 1L;
}
