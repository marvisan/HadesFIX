/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionException.java
 *
 * $Id: SessionException.java,v 1.1 2010-05-08 10:16:38 vrotaru Exp $
 */
package net.hades.fix.admin.session;

/**
 * Error thrown by the administration command line utility.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 08/05/2010
 */
public class SessionException extends Exception {

    private static final long serialVersionUID = 1L;

    public SessionException(String message) {
        super(message);
    }

    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
