/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixmlDecodingException.java
 *
 * $Id: FixmlDecodingException.java,v 1.2 2009-07-12 08:01:31 vrotaru Exp $
 */
package net.hades.fix.message.exception;

/**
 * Error thrown when the FIXML decoding encounters an unexpected system error.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/05/2009, 2:45:13 PM
 */
public class FixmlDecodingException extends Exception {
    
    private static final long serialVersionUID = -406822033256005007L;

    public FixmlDecodingException(String message) {
        super(message);
    }

    public FixmlDecodingException(String message, Throwable cause) {
        super(message, cause);
    }
}
