/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

/**
 * Generic protocol exception thrown by the protocol layer.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ProtocolException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public ProtocolException(String message) {
        super(message);
    }

    public ProtocolException(String message, Throwable cause) {
        super(message, cause);
    }
}
