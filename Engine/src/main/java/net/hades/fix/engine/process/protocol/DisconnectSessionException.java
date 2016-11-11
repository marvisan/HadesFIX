/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;


/**
 * Immediately disconnects the FIX session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class DisconnectSessionException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public DisconnectSessionException(String message) {
        super(message);
    }

    public DisconnectSessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
