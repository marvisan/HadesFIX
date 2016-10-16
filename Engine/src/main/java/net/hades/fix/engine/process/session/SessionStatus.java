/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionType.java
 *
 * $Id: SessionType.java,v 1.1 2010-10-08 08:43:13 vrotaru Exp $
 */
package net.hades.fix.engine.process.session;

/**
 * Type of the session status.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public enum SessionStatus {
    CONNECTED, DISCONNECTED, FROZEN;

    private static final long serialVersionUID = 1L;
}
