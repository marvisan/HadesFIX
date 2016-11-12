/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process;

/**
 * Exception when starting an engine task thread.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class TaskStartException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public TaskStartException(String message) {
        super(message);
    }

    public TaskStartException(String message, Throwable cause) {
        super(message, cause);
    }
}
