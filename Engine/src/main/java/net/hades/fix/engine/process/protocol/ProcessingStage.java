/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProcessingStage.java
 *
 * $Id: ProcessingStage.java,v 1.6 2011-03-30 10:59:14 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

/**
 * State of the protocol client/server.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 */
public enum ProcessingStage {

    INITIALISED,
    LOGGEDON,
    RESET,
    FROZEN,
    ADMIN,
    LOGGEDOUT;

    private static final long serialVersionUID = 1L;

}
