/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import net.hades.fix.engine.handler.Handler;

/**
 * Fix engine task.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class EngineTask<T extends ExecutionResult> extends FutureTask<ExecutionResult> implements PriorityNamedTask {

    private int threadPiority;
    private String threadName;

    public EngineTask(Callable<ExecutionResult> callable) {
	super(callable);
	if (callable instanceof Handler) {
	    threadName = ((Handler) callable).getId();
	}
    }

    public EngineTask(int threadPiority, Callable<ExecutionResult> callable) {
	this(callable);
	this.threadPiority = threadPiority;
    }

    @Override
    public int getPriority() {
	return threadPiority;
    }

    @Override
    public String getName() {
	return threadName;
    }

}
