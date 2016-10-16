/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnsupportedCrypterException.java
 *
 * $Id: UnsupportedCrypterException.java,v 1.3 2010-02-25 08:37:46 vrotaru Exp $
 */
package net.hades.fix.message.exception;

/**
 * Exception thrown when a crypter implementation has not been found.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 25/08/2008, 20:12:39
 */
public class UnsupportedCrypterException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnsupportedCrypterException(String message) {
        super(message);
    }

    public UnsupportedCrypterException(String message, Throwable cause) {
        super(message, cause);
    }
}
