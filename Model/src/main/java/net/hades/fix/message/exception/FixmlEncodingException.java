/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixmlEncodingException.java
 *
 * $Id: FixmlEncodingException.java,v 1.2 2009-07-12 08:01:31 vrotaru Exp $
 */
package net.hades.fix.message.exception;

/**
 * Error thrown when the FIXML encoding encounters an unexpected system error.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/05/2009, 2:45:13 PM
 */
public class FixmlEncodingException extends Exception {

    private static final long serialVersionUID = -1780547288966059828L;

    public FixmlEncodingException(String message) {
        super(message);
    }

    public FixmlEncodingException(String message, Throwable cause) {
        super(message, cause);
    }
}
