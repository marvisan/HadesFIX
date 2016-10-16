/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CmdLineException.java
 *
 * $Id: CmdLineException.java,v 1.1 2010-05-08 10:16:39 vrotaru Exp $
 */
package net.hades.fix.admin.cmdline;

/**
 * Error thrown by the administration command line utility.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 08/05/2010
 */
public class CmdLineException extends Exception {

    private static final long serialVersionUID = 1L;

    public CmdLineException(String message) {
        super(message);
    }

    public CmdLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
