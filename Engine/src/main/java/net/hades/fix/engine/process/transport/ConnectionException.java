/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.transport;

/**
 * Exception thrown in case a connection fails.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ConnectionException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
