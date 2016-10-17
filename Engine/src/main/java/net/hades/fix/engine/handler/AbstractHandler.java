/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.handler;

import java.time.Duration;
import java.util.EventListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.message.Message;

/**
 * Main class to inherit from when implementing a handler.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class AbstractHandler implements Handler  {

    private final Logger Log = Logger.getLogger(this.getClass().getSimpleName());
	
    private static final int DEFAULT_TIMEOUT_SECS = 30;
    private static final int DEFAULT_SLEEP_MILLIS = 1;

    private Duration timeout;
    
    protected String id;
    protected volatile TaskStatus status;
    protected final SynchronousQueue<Message> inQueue;
    protected final ConcurrentMap<String, Handler> nextHandlers;
    protected Map<String, String> parameters;
    protected ConcurrentMap<String, Object> context;
    protected boolean disabled;

    public AbstractHandler(String id) {
	this.id = id;
	inQueue = new SynchronousQueue<>();
	nextHandlers = new ConcurrentHashMap<>();
	status = TaskStatus.New;
	timeout = Duration.ofSeconds(DEFAULT_TIMEOUT_SECS, 1);
    }

    public AbstractHandler(String id, Map<String, String> parameters) {
	this(id);
	this.parameters = parameters;
    }

    public AbstractHandler(String id, ConcurrentMap<String, Object> context) {
	this(id);
	this.context = context;
    }

    public AbstractHandler(String id, Map<String, String> parameters, ConcurrentMap<String, Object> context) {
	this(id);
	this.parameters = parameters;
	this.context = context;
    }

    protected abstract void process(Message message);

    @Override
    public ExecutionResult call() throws Exception {
	status = TaskStatus.Running;
	Log.info(String.format("Handler [%s] : [%s]", id, status));
	while (status.equals(TaskStatus.Running)) {
	    try {
		Message message = inQueue.take();
		if (message != null) {
		    process(message);
		}
	    } catch (InterruptedException ex) {
		status = TaskStatus.Exiting;
		Log.info(String.format("Handler [%s] Interrupted call() : [%s]", id, status));
	    }
	}
	while (!inQueue.isEmpty()) {
	    // wait for the queue to get drained
	    Thread.sleep(DEFAULT_SLEEP_MILLIS);
	    if (timeout.minusMillis(DEFAULT_SLEEP_MILLIS).isNegative()) {
		status = TaskStatus.TimedOut;
		Log.info(String.format("Handler [%s] timedout call() : [%s]", id, status));
		return new ExecutionResult(TaskStatus.TimedOut);
	    }
	}
	status = TaskStatus.Completed;
	Log.info(String.format("Handler [%s] : [%s]", id, status));
	return new ExecutionResult(TaskStatus.Completed);
    }

    @Override
    public void write(Message message) {
	try {
	    inQueue.put(message);
	} catch (InterruptedException ex) {
	    status = TaskStatus.Exiting;
	    Log.info(String.format("Handler [%s] Interrupted write() : [%s]", id, status));
	}
    }

    @Override
    public void tryWrite(Message message, int waitMillis) {
	try {
	    inQueue.offer(message, waitMillis, TimeUnit.MILLISECONDS);
	} catch (InterruptedException ex) {
	    status = TaskStatus.Exiting;
	    Log.info(String.format("Handler [%s] Interrupted tryWrite() : [%s]", id, status));
	}
    }

    @Override
    public void addNextHandler(String id, Handler next) {
	nextHandlers.putIfAbsent(id, next);
    }

    @Override
    public void setTimeoutSecs(int timeoutSecs) {
	timeout = Duration.ofSeconds(timeoutSecs, 1);
    }

    @Override
    public TaskStatus getStaus() {
	return status;
    }

    @Override
    public String getId() {
	return id;
    }

    @Override
    public void setDisabled(boolean disabled) {
	this.disabled = disabled;
    }
}
