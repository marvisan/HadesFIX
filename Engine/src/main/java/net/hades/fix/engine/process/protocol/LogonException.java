/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

/**
 * Log-on error.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class LogonException extends Exception {

    private static final long serialVersionUID = 1L;

    public LogonException(String message) {
        super(message);
    }
}
