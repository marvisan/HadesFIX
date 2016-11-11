/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.exception;

/**
 * Nothing to do if this is thrown.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class UnrecoverableException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public UnrecoverableException(String message) {
        super(message);
    }

    public UnrecoverableException(String message, Throwable cause) {
        super(message, cause);
    }
}
