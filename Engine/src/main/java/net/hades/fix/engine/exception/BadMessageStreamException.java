/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.exception;

/**
 * Exception throw in cases the persistent message stream is corrupted.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class BadMessageStreamException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadMessageStreamException(String message) {
        super(message);
    }
}
