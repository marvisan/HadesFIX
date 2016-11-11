/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.handler;

/**
 * Handler error. The engine will return a reject message back to the counter-party
 * with the message text set to the exception message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class HandlerException extends Exception {

    private static final long serialVersionUID = 1L;

    public HandlerException(String message) {
        super(message);
    }

    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
