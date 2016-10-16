/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BadParameterException.java
 *
 * $Id: BadParameterException.java,v 1.2 2010-02-25 08:37:46 vrotaru Exp $
 */
package net.hades.fix.message.exception;

/**
 * Runtime error thrown where the parameter passe to a method is wrong format.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 02/06/2009, 3:53:20 PM
 */
public class BadParameterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>BadParameterException</code> without detail message.
     */
    public BadParameterException() {
    }

    /**
     * Constructs an instance of <code>BadParameterException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public BadParameterException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>BadParameterException</code> with the specified detail message.
     * @param msg the detail message.
     * @param cause cause
     */
    public BadParameterException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
