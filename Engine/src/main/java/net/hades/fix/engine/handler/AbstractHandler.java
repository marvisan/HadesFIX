/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.handler;

import java.time.Duration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.process.session.ContextParamKey;
import net.hades.fix.message.Message;
import net.hades.fix.message.config.SessionContext;

/**
 * Main class to inherit from when implementing a handler.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class AbstractHandler implements Handler  {

    private static final Logger Log = Logger.getLogger(AbstractHandler.class.getName());
	
    private static final int DEFAULT_SHUTDOWN_TIMEOUT_SECS = 30;
    private static final int DEFAULT_SLEEP_MILLIS = 5;

    private Duration timeout;
    protected long shutdownTimeoutSecs;
    protected SessionContext sessionContext;
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
	populateHandlerParams();
	timeout = Duration.ofSeconds(shutdownTimeoutSecs, 1);
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
		Message message = inQueue.poll(1, TimeUnit.SECONDS);
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
	    timeout = timeout.minusMillis(DEFAULT_SLEEP_MILLIS);
	    if (timeout.isNegative()) {
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
    public boolean tryWrite(Message message, int waitMillis) {
	try {
	    return inQueue.offer(message, waitMillis, TimeUnit.MILLISECONDS);
	} catch (InterruptedException ex) {
	    status = TaskStatus.Exiting;
	    Log.info(String.format("Handler [%s] Interrupted tryWrite() : [%s]", id, status));
	}
	return false;
    }

    @Override
    public void addNextHandler(String id, Handler next) {
	nextHandlers.putIfAbsent(id, next);
    }

    @Override
    public TaskStatus getStatus() {
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
    
    @Override
    public void shutdown() {
	
    }

    @Override
    public void shutdownImmediate() {
    }

    private void populateHandlerParams() {
	shutdownTimeoutSecs = DEFAULT_SHUTDOWN_TIMEOUT_SECS;
	if (parameters != null && !parameters.isEmpty()) {
	    if (parameters.containsKey(HandlerParamKey.HadesFIX_ShutdownTimeoutSecs.name())) {
		try {
		    shutdownTimeoutSecs = Long.valueOf(parameters.get(HandlerParamKey.HadesFIX_ShutdownTimeoutSecs.name()));
		} catch (NumberFormatException ex) {
		    Log.log(Level.WARNING, "Handler invalid parameter [{0}] value [{1}] - not a number", new Object[]{
			HandlerParamKey.HadesFIX_ShutdownTimeoutSecs.name(),
			parameters.get(HandlerParamKey.HadesFIX_ShutdownTimeoutSecs.name())});
		}
	    }
	}
	if (context != null && !context.isEmpty()) {
	    if (context.containsKey(ContextParamKey.HadesFIX_SessionContext.name())) {
		sessionContext = (SessionContext) context.get(ContextParamKey.HadesFIX_SessionContext.name());
	    }
	}
    }

}
