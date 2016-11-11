/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * InputValidationException.java
 *
 * $Id: InputValidationException.java,v 1.1 2010-05-16 09:22:50 vrotaru Exp $
 */
package net.hades.fix.admin.console.exception;

/**
 * Error thrown by the administration command line utility.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 08/05/2010
 */
public class InputValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    public InputValidationException(String message) {
        super(message);
    }

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
