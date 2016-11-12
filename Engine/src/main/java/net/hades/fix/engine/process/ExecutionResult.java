/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */

package net.hades.fix.engine.process;

/**
 * The result of a Callable task executed by engine.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ExecutionResult {
    private final TaskStatus status;
    private Exception exception;
    
    public ExecutionResult(TaskStatus status) {
	this.status = status;
    }

    public ExecutionResult(TaskStatus status, Exception exception) {
	this(status);
	this.exception = exception;
    }

    public TaskStatus getStatus() {
	return status;
    }

    public Exception getException() {
	return exception;
    }
    
    
}
