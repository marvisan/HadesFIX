/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.exception;

/**
 * The persistence is turn on but the sequences cannot be written to the sequence file.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class SeqNoPersistenceException extends Exception {

    private static final long serialVersionUID = 1L;

    public SeqNoPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
