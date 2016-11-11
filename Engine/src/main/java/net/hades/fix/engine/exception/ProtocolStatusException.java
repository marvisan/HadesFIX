/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.exception;

/**
 * Protocol exception thrown by the components when the protocol status is not in the
 * expected state.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ProtocolStatusException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public ProtocolStatusException(String message) {
        super(message);
    }
}
