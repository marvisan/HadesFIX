/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread factory that can have a priority and a thread name.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class PriorityNamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public PriorityNamedThreadFactory() {
	SecurityManager s = System.getSecurityManager();
	group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
	namePrefix = "pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
	EngineTask task = (EngineTask) r;
	String threadName = task.getName() != null ? task.getName() : namePrefix + threadNumber.getAndIncrement();
	Thread t = new Thread(group, task, threadName, 0);
	if (t.isDaemon()) {
	    t.setDaemon(false);
	}
	if (t.getPriority() != Thread.NORM_PRIORITY) {
	    t.setPriority(Thread.NORM_PRIORITY);
	}
	if (task.getPriority() > 0) {
	    t.setPriority(task.getPriority());
	}
	return t;
    }

}
