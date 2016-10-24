/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.engine.exception.ConnectionException;
import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.handler.HandlerException;
import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.ManagedTask;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.process.io.FIXMsgInputStream;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.message.BinaryMessage;
import net.hades.fix.message.Message;

/**
 * The HadesFIX TCP process read and write to the TCP socket binary messages.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public final class TcpWorker implements Handler {

    private static final Logger Log = Logger.getLogger(TcpWorker.class.getName());

    private static final String COMPONENT_NAME = "TCP_WRKR";
    private static final int DEFAULT_TX_QUEUE_CAPACITY = 3000;

    private final SessionCoordinator coordinator;
    private final Socket socket;
    private final ConcurrentMap<String, String> statistics;
    private final BlockingQueue<Message> txQueue;
    private final MessageSender sender;

    private final String id;
    protected final ConcurrentMap<String, Handler> nextHandlers;

    private volatile boolean shutdown;
    private volatile TaskStatus status;
    private volatile long numMsgIn;

    private BufferedInputStream in;
    private BufferedOutputStream out;
    private FIXMsgInputStream fixMsgIn;
    private Future<ExecutionResult> senderResult;

    private final BiFunction<String, String, String> sumStats = (String old, String cur) -> {
	return String.valueOf(Long.valueOf(old) + Long.valueOf(cur));
    };

    public TcpWorker(SessionCoordinator coordinator, Socket socket) {
	this.coordinator = coordinator;
	this.socket = socket;
	statistics = new ConcurrentHashMap<>();
	nextHandlers = new ConcurrentHashMap<>();
	txQueue = new ArrayBlockingQueue<>(DEFAULT_TX_QUEUE_CAPACITY);
	id = COMPONENT_NAME + "_" + socket.getLocalAddress().getHostAddress() + ":" + String.valueOf(socket.getPort());
	sender = new MessageSender(id);
	status = TaskStatus.New;
    }

    @Override
    public ExecutionResult call() throws Exception {
	Log.log(Level.INFO, "Running Tcp Client Worker thread [{0}].", id);

	status = TaskStatus.Running;
	try {
	    initialiseConnection();
	} catch (UnrecoverableException ex) {
	    status = TaskStatus.Error;
	    return new ExecutionResult(TaskStatus.Error, ex);
	}
	senderResult = coordinator.getExecutorService().submit(sender);
	if (senderResult.isCancelled() || senderResult.isDone()) {
	    return new ExecutionResult(TaskStatus.Error, new HandlerException("Tcp worker message sender could not start"));
	}
	while (!shutdown) {
	    try {
		byte[] msg = fixMsgIn.readMessage();
		if (msg != null) {
		    statistics.merge("BYTES_IN", String.valueOf(msg.length), sumStats);
		    statistics.merge("MSG_IN", String.valueOf(numMsgIn++), sumStats);
		}
		if (nextHandlers.isEmpty()) {
		    status = TaskStatus.Error;
		    return new ExecutionResult(TaskStatus.Error, new HandlerException("No next handlers set by session coordinator"));
		}
		nextHandlers.values().iterator().next().write(new BinaryMessage(msg));
	    } catch (ConnectionException | IOException ex) {
		status = TaskStatus.Error;
		return new ExecutionResult(TaskStatus.Error, ex);
	    }
	}
	status = TaskStatus.Completed;
	return new ExecutionResult(status);
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
    public void write(Message message) {
	try {
	    txQueue.put(message);
	} catch (InterruptedException ex) {
	    Log.log(Level.SEVERE, "Protocol [{0}] write() interrupted", id);
	}
    }

    @Override
    public boolean tryWrite(Message message, int waitMillis) {
	try {
	    return txQueue.offer(message, 1, TimeUnit.SECONDS);
	} catch (InterruptedException ex) {
	    Log.log(Level.SEVERE, "Protocol [{0}] tryWrite() interrupted", id);
	}
	return true;
    }

    @Override
    public void addNextHandler(String id, Handler next) {
	nextHandlers.putIfAbsent(id, next);
    }

    @Override
    public Map<String, Long> getStatistics() {
	return new HashMap(statistics);
    }

    @Override
    public void shutdown() {
	sender.shutdown();
	closeStreams();
    }

    @Override
    public void shutdownImmediate() {
	sender.shutdownImmediate();
	closeStreams();
	shutdown = true;
    }

    private void initialiseConnection() throws UnrecoverableException, ConnectionException, IOException {
	try {
	    in = new BufferedInputStream(socket.getInputStream());
	    out = new BufferedOutputStream(socket.getOutputStream());
	    fixMsgIn = new FIXMsgInputStream(in);
	} catch (UnknownHostException ex) {
	    String error = "The given host name address is not valid.";
	    Log.log(Level.SEVERE, error, ex);
	    throw new UnrecoverableException(error, ex);
	} catch (SocketTimeoutException ex) {
	    String error = "The socket connection has timed out.";
	    Log.log(Level.SEVERE, error, ex);
	    throw new UnrecoverableException(error, ex);
	} catch (IOException ex) {
	    String error = "I/O Error while setting up the socket.";
	    Log.log(Level.SEVERE, error, ex);
	    throw new UnrecoverableException(error, ex);
	}
    }

    private void closeStreams() {
	if (in != null) {
	    try {
		in.close();
	    } catch (IOException ex) {
		Log.log(Level.WARNING, "Could not close input stream", ex);
	    }
	}
	if (out != null) {
	    try {
		out.close();
	    } catch (IOException ex) {
		Log.log(Level.WARNING, "Could not close output stream", ex);
	    }
	}
    }

    @Override
    public void setDisabled(boolean disabled) {
	throw new UnsupportedOperationException("Tcp Worker cannot be disabled"); 
    }

    private class MessageSender implements ManagedTask {

	private final String senderId;
	private volatile boolean senderShutdown;
	private volatile TaskStatus senderStatus;
	private int numMsgOut;

	private MessageSender(String name) {
	    senderId = name + "_MSG_SENDER";
	    senderStatus = TaskStatus.New;
	}

	@Override
	public ExecutionResult call() throws Exception {
	    Log.log(Level.INFO, "Transport message sender [{0}] starting.", senderId);

	    senderStatus = TaskStatus.Running;
	    while (!senderShutdown) {
		try {
		    sendData();
		} catch (InterruptedException ex) {
		    Log.log(Level.WARNING, "Transport message sender thread [{0}] interrupted.", senderId);
		    senderStatus = TaskStatus.Error;
		    return new ExecutionResult(senderStatus, ex);
		} catch (IOException ex) {
		    Log.log(Level.WARNING, "I/O error in message sender thread [{0}].", senderId);
		    senderStatus = TaskStatus.Error;
		    return new ExecutionResult(senderStatus, ex);
		} catch (Exception ex) {
		    Log.log(Level.WARNING, "unexpected error in message sender thread [{0}].", senderId);
		    senderStatus = TaskStatus.Error;
		    return new ExecutionResult(senderStatus, ex);
		}
	    }
	    senderStatus = TaskStatus.Completed;
	    return new ExecutionResult(senderStatus);

	}

	private void sendData() throws IOException, InterruptedException {
	    Message message = txQueue.poll(1, TimeUnit.SECONDS);
	    if (message == null || message.getRawMessage() == null) {
		return;
	    }
	    if (out != null) {
		byte[] byteMsg = message.getRawMessage();
		out.write(byteMsg);
		out.flush();
		statistics.merge("BYTES_OUT", String.valueOf(byteMsg.length), sumStats);
		statistics.merge("MSG_OUT", String.valueOf(numMsgOut++), sumStats);
	    }
	}

	@Override
	public void shutdown() {
	    if (!txQueue.isEmpty()) {
		//TODO
	    }
	}

	@Override
	public void shutdownImmediate() {
	    senderShutdown = true;
	}

	@Override
	public TaskStatus getStatus() {
	    return senderStatus;
	}

	@Override
	public String getId() {
	    return senderId;
	}
    }

}
