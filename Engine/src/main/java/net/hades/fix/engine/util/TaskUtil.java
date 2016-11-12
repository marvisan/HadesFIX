/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */

package net.hades.fix.engine.util;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.process.EngineTask;
import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.ManagedTask;
import net.hades.fix.engine.process.TaskStartException;
import net.hades.fix.engine.process.TaskStatus;

/**
 * Utilities used for engine tasks.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class TaskUtil {

    private static final int DEFAULT_SLEEP_MILLIS = 5;
    private static final int DEFAULT_TIMEOUT_SECS = 10;
    
    public static void waitToStart(EngineTask<ExecutionResult> task) throws TaskStartException {
	Duration timeout = Duration.ofSeconds(DEFAULT_TIMEOUT_SECS, 1);
	Callable<ExecutionResult> proc = task.getEngineTask();
	if (proc instanceof Handler) {
	    Handler t = (Handler) proc;
	    while (!TaskStatus.Running.equals(t.getStatus())) {
		try {
		    Thread.sleep(DEFAULT_SLEEP_MILLIS);
		} catch (InterruptedException ex) {
		    throw new TaskStartException(String.format("Task [%s] was interrupted, shutdown session", t.getId()), ex);
		}
		timeout = timeout.minusMillis(DEFAULT_SLEEP_MILLIS);
		if (timeout.isNegative()) {
		    throw new TaskStartException(String.format("Task [%s] did not start in [%s} seconds, shutdown session", t.getId(), DEFAULT_TIMEOUT_SECS));
		}
	    }
	} else if (proc instanceof ManagedTask) {
	    ManagedTask t = (ManagedTask) proc;
	    while (!TaskStatus.Running.equals(t.getStatus())) {
		try {
		    Thread.sleep(DEFAULT_SLEEP_MILLIS);
		} catch (InterruptedException ex) {
		    throw new TaskStartException(String.format("Task [%s] was interrupted, shutdown session", t.getId()), ex);
		}
		timeout = timeout.minusMillis(DEFAULT_SLEEP_MILLIS);
		if (timeout.isNegative()) {
		    throw new TaskStartException(String.format("Task [%s] did not start in [%s} seconds, shutdown session", t.getId(), DEFAULT_TIMEOUT_SECS));
		}
	    }
	}
    }

}
