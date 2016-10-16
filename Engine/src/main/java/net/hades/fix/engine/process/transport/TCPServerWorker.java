/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TCPServerWorker.java
 *
 * $Id: TCPServerWorker.java,v 1.30 2011-04-30 04:39:45 vrotaru Exp $
 */
package net.hades.fix.engine.process.transport;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.ConnectionException;
import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.*;
import net.hades.fix.engine.model.CounterpartyAddress;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.EventProcessor;
import net.hades.fix.engine.process.event.GenericEventProcessor;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.io.FIXMsgInputStream;
import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Worker thread that processes sockets messages.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.30 $
 */
public class TCPServerWorker extends Thread implements Transport {

    private static final Logger LOGGER = Logger.getLogger(TCPServerWorker.class.getName());

    private static final String COMP_NAME = "WRKR";

    private static final int DEFAULT_RX_BUFFER_SIZE                     = 1000;
    private static final int DEFAULT_TX_BUFFER_SIZE                     = 1;
    private static final int DEFAULT_SO_LINGER                          = -1;
    private static final int DEFAULT_SO_TIMEOUT                         = 10000; // 10 seconds

    private TCPServer tcpServer;

    private Socket socket;

    private SessionAddress sessionAddress;

    private volatile boolean shutdown;
    private volatile boolean blocked;
    private volatile boolean connected;

    private EventProcessor eventProcessor;

    private BlockingDeque<byte[]> rxQueue;
    private BlockingDeque<byte[]> txQueue;
    private BlockingQueue<Command> commandQueue;

    private AtomicReference<ProcessStatus> processStatus;
    private AtomicReference<TransportProcessData> mgmtData;
    private AtomicReference<TransportStats> stats;

    private BufferedInputStream in;
    private FIXMsgInputStream fixMsgIn;
    private BufferedOutputStream out;

    private FIXMessageStreamReader messageStreamReader;

    private MessageSender messageSender;

    public TCPServerWorker(TCPServer tcpServer, Socket socket) {
        super(((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress() + ":" +
                ((InetSocketAddress) socket.getRemoteSocketAddress()).getPort() + "_" + COMP_NAME);
        this.tcpServer = tcpServer;
        this.socket = socket;
        processStatus = new AtomicReference<ProcessStatus>(ProcessStatus.INITIALISING);
        mgmtData = new AtomicReference<TransportProcessData>(new TransportProcessData());
        stats = new AtomicReference<TransportStats>(new TransportStats());
        commandQueue = new LinkedBlockingQueue<Command>();

        rxQueue = new LinkedBlockingDeque<byte[]>(tcpServer.getConfiguration().getRxBufferSize() != null
                ? tcpServer.getConfiguration().getRxBufferSize()
                : DEFAULT_RX_BUFFER_SIZE);
        txQueue = new LinkedBlockingDeque<byte[]>(DEFAULT_TX_BUFFER_SIZE);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Running TCPServerWorker thread [{0}].", getName());

        getMgmtData().setId(String.valueOf(getId()));
        getMgmtData().setName(getName());
        getMgmtData().setStatus(getProcessStatus());
        getStats().setStartTimestamp(new Date());

        blocked = true;
        while (!shutdown) {
            try {
                if (!blocked) {
                    if (!connected) {
                        initialiseConnection();
                    }
                }
                try {
                    processCommand(commandQueue.take());
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "TCP Server Worker thread [{0}] has been interrupted.", getName());
                }
            } catch (UnrecoverableException ex) {
                LOGGER.log(Level.SEVERE, "Unrecoverable error : {0}", ExceptionUtil.getStackTrace(ex));
                blocked = true;
                eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                        LifeCycleType.SERVER_TCP_CONN.name(),
                        LifeCycleCode.TRANSP_DISCONNECT.name()));
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                        BaseSeverityType.FATAL, AlertCode.TRANSP_ABORT, "Unexpected error.", ex)));
                tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                shutdownNow();
            } catch (ConnectionException ex) {
                LOGGER.log(Level.SEVERE, "Connection error : {0}", ex.toString());
                blocked = true;
                eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                        LifeCycleType.SERVER_TCP_CONN.name(),
                        LifeCycleCode.TRANSP_DISCONNECT.name()));
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                        BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "Connection error.", ex)));
                tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                shutdownNow();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "IO error : {0}", ExceptionUtil.getStackTrace(ex));
                blocked = true;
                eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                        LifeCycleType.SERVER_TCP_CONN.name(),
                        LifeCycleCode.TRANSP_DISCONNECT.name()));
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                        BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "I/O socket error.", ex)));
                tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                shutdownNow();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Unexpected error : {0}", ExceptionUtil.getStackTrace(ex));
                blocked = true;
                eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                        LifeCycleType.SERVER_TCP_CONN.name(),
                        LifeCycleCode.TRANSP_DISCONNECT.name()));
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                        BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "Unexpected Error.", ex)));
                tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                shutdownNow();
            }
        }

        LOGGER.log(Level.INFO, "TCPServerWorker thread [{0}] destroyed.", getName());
    }

    @Override
    public void initialise() throws ConfigurationException {
        eventProcessor = new GenericEventProcessor(getName());
        messageSender = new MessageSender(getName());
        messageStreamReader = new FIXMessageStreamReader(getName());
    }

    @Override
    public void execute(Command command) {
        try {
            commandQueue.put(command);
        } catch (InterruptedException ex) {
            String errMsg = "TCP Server Worker thread [" + getName() + "] has been unexpectedly interrupted.";
            LOGGER.log(Level.WARNING, "{0} Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });
        }
    }

    @Override
    public byte[] readMessage() throws InterruptedException {
        byte[] message = rxQueue.take();
        if (message != null && LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Reading from RX queue : {0}", new String(message));
        }

        return message;
    }

    @Override
    public byte[] readMessage(int timeout) throws InterruptedException {
        byte[] message = rxQueue.poll(timeout, TimeUnit.MILLISECONDS);
        if (message != null && LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Reading from RX queue : {0}", new String(message));
        }

        return message;
    }

    @Override
    public void writeMessage(byte[] message) throws InterruptedException {
        if (message == null) {
            return;
        }
        txQueue.put(message);

        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Writing to TX queue : {0}", new String(message));
        }
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void addLifeCycleListener(LifeCycleListener listener) {
        eventProcessor.addLifeCycleListener(listener);
    }

    @Override
    public void addAlertListener(AlertListener listener) {
        eventProcessor.addAlertListener(listener);
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        eventProcessor.addMessageListener(listener);
    }

    @Override
    public synchronized ProcessStatus getProcessStatus() {
        return processStatus.get();
    }

    @Override
    public synchronized void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus.set(processStatus);
    }

    @Override
    public synchronized ProcessData getMgmtData() {
        return mgmtData.get();
    }

    @Override
    public synchronized Stats getStats() {
        return stats.get();
    }

    public SessionAddress getSessionAddress() {
        return sessionAddress;
    }

    @Override
    public String retrieveSessionAddress() {
        return sessionAddress != null ?sessionAddress.toString() : "Undefined";
    }

    private void processCommand(Command command) {
        if (command == null) {
            return;
        }
        switch (command.getCommandType()) {
            case Startup:
                startup();
                break;

            case Block:
                block();
                break;

            case Unblock:
                unblock();
                break;

            case StopTCPReader:
                stopTCPReader();
                break;

            case StopIncoming:
                stopIncoming();
                break;

            case DisconnectTransport:
                disconnectTransport();
                break;

            case UnsetSessionCoordinator:
                tcpServer.unsetSessionCoordinator(sessionAddress);
                break;

            case Shutdown:
                shutdown();
                break;

            case ShutdownNow:
                shutdownNow();
                break;

            default:
                LOGGER.log(Level.SEVERE, "Command [{0}] not implemented for a TCP Server Worker.", command.getCommandType().name());

        }
    }

    private void startup() {
        LOGGER.log(Level.INFO, "Starting up TCP Server worker [{0}].", getName());

        startEventProcessor();
        startMessageSender();

        LOGGER.log(Level.INFO, "TCP Server worker [{0}] started up.", getName());
    }

    private void block() {
        blocked = true;

        blockMessageSender();
        blockFIXMessageStreamReader();

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "TCP Server worker [{0}] blocked.", getName());
    }

    private void unblock() {
        blocked = false;

        setProcessStatus(ProcessStatus.ACTIVE);
        getMgmtData().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "TCP Server worker [{0}] unblocked.", getName());
    }

    private void blockMessageSender() {
        messageSender.block();
        messageSender.interrupt();
        while (messageSender.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void blockFIXMessageStreamReader() {
        messageStreamReader.block();
        messageStreamReader.interrupt();
        while (messageStreamReader.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void unblockMessageSender() {
        messageSender.unblock();
        messageSender.interrupt();
        while (!messageSender.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void stopTCPReader() {
        if (messageStreamReader != null) {
            messageStreamReader.shutdown();
            messageStreamReader.interrupt();
            while (messageStreamReader.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            messageStreamReader = null;
        }

        setProcessStatus(ProcessStatus.TCP_READER_STOPPED);
        getMgmtData().setStatus(ProcessStatus.TCP_READER_STOPPED);
    }

    private void shutdown() {
        LOGGER.log(Level.INFO, "Shutting down TCP Server Worker [{0}].", getName());

        blocked = true;

        do {
            // wait for the RX and TX to get flushed
            ThreadUtil.sleep(1);
        } while ((rxQueue.size() > 0 || txQueue.size() > 0) && (ProcessStatus.ACTIVE.equals(processStatus.get())
                || ProcessStatus.TCP_READER_STOPPED.equals(processStatus.get())));

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        if (messageStreamReader != null) {
            messageStreamReader.shutdown();
            messageStreamReader.interrupt();
            while (messageStreamReader.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            messageStreamReader = null;
        }
        if (messageSender != null) {
            messageSender.shutdown();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            eventProcessor = null;
        }
        closeSocketStreams();

        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);
        this.shutdown = true;

        LOGGER.log(Level.INFO, "TCP Server Worker [{0}] shutdown.", getName());
    }

    private void shutdownNow() {
        LOGGER.log(Level.INFO, "Shutting down TCP Server Worker immediate [{0}].", getName());

        blocked = true;

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        if (messageStreamReader != null) {
            messageStreamReader.shutdown();
            messageStreamReader.interrupt();
            while (messageStreamReader.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            messageStreamReader = null;
        }
        if (messageSender != null) {
            messageSender.shutdown();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            eventProcessor = null;
        }

        closeSocketStreams();

        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);
        this.shutdown = true;

        LOGGER.log(Level.INFO, "TCP Server Worker [{0}] shutdown immediate.", getName());
    }

    public void stopIncoming() {
        LOGGER.log(Level.INFO, "Stopping incoming messages for TCP Server Worker [{0}].", getName());

        blocked = true;

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        do {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        } while (!rxQueue.isEmpty());

        if (messageStreamReader != null) {
            messageStreamReader.shutdown();
            messageStreamReader.interrupt();
            while (messageStreamReader.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            messageStreamReader = null;
        }

        setProcessStatus(ProcessStatus.INCOMING_STOPPED);
        getMgmtData().setStatus(ProcessStatus.INCOMING_STOPPED);
        this.shutdown = true;

        LOGGER.log(Level.INFO, "TCP Server Worker [{0}] disconnected.", getName());
    }

    public void disconnectTransport() {
        LOGGER.log(Level.INFO, "Disconnecting TCP Server Worker [{0}].", getName());

        blocked = true;

        if (messageSender != null) {
            messageSender.shutdown();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        if (eventProcessor != null) {
            eventProcessor.shutdown();
            while (eventProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            eventProcessor = null;
        }
        closeSocketStreams();
        tcpServer.unsetSessionWorker(getSessionAddress());
        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);
        this.shutdown = true;

        LOGGER.log(Level.INFO, "TCP Server Worker [{0}] disconnected.", getName());
    }

    private void initialiseConnection() throws UnrecoverableException, ConnectionException, IOException {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest("Initialise socket streams.");
        }

        try {
            setTcpNodelay();
            setSoLinger();
            setSoRcvbuf();
            setSoSndbuf();
            setSendKeepAlive();
            // set this to infinity after the first message arrives
            setSocketTimeout();

            in = new BufferedInputStream(socket.getInputStream());
            fixMsgIn = new FIXMsgInputStream(in);
            out = new BufferedOutputStream(socket.getOutputStream());
            connected = true;
            startFIXMessageStreamReader();
        } catch (UnknownHostException ex) {
            String error = "The given host name address is not valid.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
            throw new UnrecoverableException(error, ex);
        } catch (SocketTimeoutException ex) {
            String error = "The socket connection has timed out.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
            throw new UnrecoverableException(error, ex);
        } catch (IOException ex) {
            String error = "I/O Error while setting up the socket.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
            throw new UnrecoverableException(error, ex);
        }
    }

    private void startEventProcessor() {
        eventProcessor.start();
        ThreadUtil.blockUntilAlive(eventProcessor);
        while (!eventProcessor.isActive()) {
            if (!ThreadUtil.sleep(2)) {
                break;
            }
        }

        addAlertListener(tcpServer.getEventProcessor());
        addLifeCycleListener(tcpServer.getEventProcessor());
        addMessageListener(tcpServer.getEventProcessor());
    }

    private void startMessageSender() {
        messageSender.block();
        messageSender.start();
        ThreadUtil.blockUntilAlive(messageSender);
        while (messageSender.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void startFIXMessageStreamReader() {
        messageStreamReader.start();
        ThreadUtil.blockUntilAlive(messageStreamReader);
        while (!messageStreamReader.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void setTcpNodelay() {
        if (tcpServer.getConfiguration().getTcpNodelay() != null && tcpServer.getConfiguration().getTcpNodelay()) {
            try {
                socket.setTcpNoDelay(true);
            } catch (SocketException ex) {
                String logMsg = "TCP_NODELAY cannot be set on this socket implementation.";
                LOGGER.warning(logMsg);

                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                        ComponentType.TCPServerWorker.toString(),
                        BaseSeverityType.WARNING,
                        null,
                        logMsg,
                        ex)));
            }
        }
    }

    private void setSendKeepAlive() {
        boolean sendKeepAlive = true;
        if (tcpServer.getConfiguration().getSendKeepAlive() != null) {
            sendKeepAlive = tcpServer.getConfiguration().getSendKeepAlive();
        }
        try {
            socket.setKeepAlive(sendKeepAlive);
        } catch (SocketException ex) {
            String logMsg = "TCP_NODELAY cannot be set on this socket implementation.";
            LOGGER.warning(logMsg);

            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                    ComponentType.TCPServerWorker.toString(),
                    BaseSeverityType.WARNING,
                    null,
                    logMsg,
                    ex)));
        }
    }

    private void setSoLinger() {
        int soLinger = DEFAULT_SO_LINGER;
        if (tcpServer.getConfiguration().getSocketLingerTimeout() != null) {
            soLinger = tcpServer.getConfiguration().getSocketLingerTimeout();
        }
        if (soLinger > 0) {
            try {
                socket.setSoLinger(true, soLinger);
            } catch (SocketException ex) {
                String logMsg = "SO_LINGER cannot be set on this socket implementation.";
                LOGGER.warning(logMsg);

                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                        ComponentType.TCPServerWorker.toString(),
                        BaseSeverityType.WARNING,
                        null,
                        logMsg,
                        ex)));
            }
        }
    }

    private void setSoRcvbuf() {
        if (tcpServer.getConfiguration().getSocketRcvbuf() != null && tcpServer.getConfiguration().getSocketRcvbuf() > 0) {
            try {
                socket.setReceiveBufferSize(tcpServer.getConfiguration().getSocketRcvbuf());
            } catch (SocketException ex) {
                String logMsg = "SO_RCVBUF cannot be set on this socket implementation.";
                LOGGER.warning(logMsg);

                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                        ComponentType.TCPServerWorker.toString(),
                        BaseSeverityType.WARNING,
                        null,
                        logMsg,
                        ex)));
            }
        }
    }

    private void setSoSndbuf() {
        if (tcpServer.getConfiguration().getSocketSndbuf() != null && tcpServer.getConfiguration().getSocketSndbuf() > 0) {
            try {
                socket.setReceiveBufferSize(tcpServer.getConfiguration().getSocketSndbuf());
            } catch (SocketException ex) {
                String logMsg = "SO_SNDBUF cannot be set on this socket implementation.";
                LOGGER.warning(logMsg);

                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                        ComponentType.TCPServerWorker.toString(),
                        BaseSeverityType.WARNING,
                        null,
                        logMsg,
                        ex)));
            }
        }
    }

    private void setSocketTimeout() {
        int socketTimeout = DEFAULT_SO_TIMEOUT;
        if (tcpServer.getConfiguration().getSocketTimeout() != null) {
            socketTimeout = tcpServer.getConfiguration().getSocketTimeout();
        }
        try {
            socket.setSoTimeout(socketTimeout);
        } catch (SocketException ex) {
            String logMsg = "Initial socket timeout cannot be set on this socket implementation.";
            LOGGER.warning(logMsg);

            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                    ComponentType.TCPServerWorker.toString(),
                    BaseSeverityType.WARNING,
                    AlertCode.TRANSP_CONNECT,
                    logMsg,
                    ex)));
        }
    }

    private void resetSocketTimeout() {
        try {
            socket.setSoTimeout(0);
        } catch (SocketException ex) {
            String logMsg = "Socket timeout cannot be reset on this socket implementation.";
            LOGGER.warning(logMsg);

            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                    ComponentType.TCPServerWorker.toString(),
                    BaseSeverityType.WARNING,
                    AlertCode.TRANSP_CONNECT,
                    logMsg,
                    ex)));
        }
    }

    private void closeSocketStreams() {
        if (socket != null) {
            try {
                socket.close();
                socket = null;
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Could not close socket. Error was : {0}", ExceptionUtil.getStackTrace(ex));
            }
        }
        if (in != null) {
            try {
                in.close();
                in = null;
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Could not close socket input stream. Error was : {0}", ExceptionUtil.getStackTrace(ex));
            }
        }
        if (out != null) {
            try {
                out.close();
                out = null;
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Could not close socket output stream. Error was : {0}", ExceptionUtil.getStackTrace(ex));
            }
        }
        connected = false;
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest("Closed socket and streams.");
        }
    }

    private class FIXMessageStreamReader extends Thread {

        private volatile boolean readerShutdown;
        private volatile boolean readerBlocked;
        private volatile boolean active;

        private FIXMessageStreamReader(String name) {
            super(name + "_MSG_READER");
            LOGGER.info("Transport FIX byte message stream reader initialised.");
        }

        @Override
        public void run() {
            LOGGER.log(Level.INFO, "Running FIX byte message stream reader [{0}].", getName());

            while (!readerShutdown) {
                try {
                    if (fixMsgIn != null && !readerBlocked) {
                        active = true;
                        writeMessageFromSocket();
                    } else {
                        if (readerBlocked) {
                            active = false;
                        }
                        Thread.sleep(1);
                    }
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "FIX byte reader thread [{0}] interrupted.", getName());
                } catch (SocketTimeoutException ex) {
                    LOGGER.log(Level.SEVERE, "Socket timeout waiting for the first message : {0}", ExceptionUtil.getStackTrace(ex));

                    block();
                    eventProcessor.onAlertEvent(new AlertEvent(TCPServerWorker.this,
                            Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "Socket timeout waiting for the first message.", ex)));

                    if (sessionAddress != null) {
                        tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                    } else {
                        execute(new Command(CommandType.ShutdownNow));
                    }
                 } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "IO error : {0}", ExceptionUtil.getStackTrace(ex));

                    block();
                    eventProcessor.onAlertEvent(new AlertEvent(TCPServerWorker.this,
                            Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "I/O error in socket stream writer.", ex)));
                    if (sessionAddress != null) {
                        tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                    } else {
                        execute(new Command(CommandType.ShutdownNow));
                    }
                } catch (UnrecoverableException ex) {
                    LOGGER.log(Level.SEVERE, "Unrecoverable error : {0}", ExceptionUtil.getStackTrace(ex));

                    block();
                    eventProcessor.onAlertEvent(new AlertEvent(TCPServerWorker.this,
                            Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "Unrecoverable error.", ex)));
                    if (sessionAddress != null) {
                        tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                    } else {
                        execute(new Command(CommandType.ShutdownNow));
                    }
                } catch (ConnectionException ex) {
                    LOGGER.log(Level.SEVERE, "Connection error : {0}", ExceptionUtil.getStackTrace(ex));

                    block();
                    eventProcessor.onAlertEvent(new AlertEvent(TCPServerWorker.this,
                            Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "Connection error.", ex)));
                    if (sessionAddress != null) {
                        tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS,sessionAddress));
                    } else {
                        execute(new Command(CommandType.ShutdownNow));
                    }
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Unexpected error : {0}", ExceptionUtil.getStackTrace(ex));

                    block();
                    eventProcessor.onAlertEvent(new AlertEvent(TCPServerWorker.this,
                            Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "Unexpected error.", ex)));
                    if (sessionAddress != null) {
                        tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                    } else {
                        execute(new Command(CommandType.ShutdownNow));
                    }
                }
            }
            active = false;

            LOGGER.log(Level.INFO, "Transport message stream reader [{0}] stopped.", getName());
        }

        public boolean isActive() {
            return active;
        }

        public void shutdown() {
            readerShutdown = true;
        }

        public void block() {
            readerBlocked = true;
        }

        public void unblock() {
            readerBlocked = false;
        }

        private void writeMessageFromSocket() throws IOException, ConnectionException, UnrecoverableException {
            byte[] fixMsg = fixMsgIn.readMessage();
            int fixMsgLen;
            if (fixMsg != null) {
                fixMsgLen = fixMsg.length;
                if (sessionAddress == null) {
                    // this is the first message received. need to decode it as we need the FIX address details
                    sessionAddress = checkMessageAddress(fixMsg);

                    Command command = new Command(CommandType.TransportConnected, Command.PARAM_SESSION_ADDRESS, sessionAddress);
                    command.addParameter(Command.PARAM_SERVER_TRANSPORT_WORKER, TCPServerWorker.this);
                    tcpServer.execute(command);
                    while (!ProcessStatus.ACTIVE.equals(tcpServer.getProcessStatus())) {
                        if (!ThreadUtil.sleep(1)) {
                            break;
                        }
                    }
                    resetSocketTimeout();
                    unblockMessageSender();

                    eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                            LifeCycleType.SERVER_TCP_CONN.name(),
                            LifeCycleCode.TRANSP_CONNECT.name()));
                }

                while (!rxQueue.offer(fixMsg)) {
                    if (!ThreadUtil.sleep(1)) {
                        break;
                    }
                }
                ((TransportStats) getStats()).addBytesIn(fixMsgLen);
            }
        }

        private SessionAddress checkMessageAddress(byte[] fixMsg) throws ConnectionException, UnrecoverableException {
            try {
                SessionContext context = ThreadData.getSessionContext();
                context.setValue(SessionContextKey.VALIDATE_REQUIRED, Boolean.FALSE);
                FIXMsg message = FIXMsgBuilder.build(fixMsg);
                SessionAddress address = extractAddress(message.getHeader());

                LOGGER.log(Level.INFO, "Incoming session address : {0}.", address.toString());

                return address;
            } catch (TagNotPresentException ex) {
                String logMsg = "Could not find required tag in order to decode FIX message : " + new String(fixMsg);
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{logMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(logMsg, ex);
            } catch (InvalidMsgException ex) {
                String logMsg = "Invalid FIX message : " + new String(fixMsg);
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{logMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(logMsg, ex);
            } catch (BadFormatMsgException ex) {
                String logMsg = "Bad format for FIX message : " + new String(fixMsg);
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{logMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(logMsg, ex);
            }
        }

        private SessionAddress extractAddress(Header header) {
            if (BeginString.FIX_4_0.equals(header.getBeginString())) {
                CounterpartyAddress remote = new CounterpartyAddress(header.getSenderCompID(),
                    header.getSenderSubID(),
                    null);
                CounterpartyAddress local = new CounterpartyAddress(header.getTargetCompID(),
                    header.getTargetSubID(),
                    null);

                return new SessionAddress(remote, local);
            } else {
                CounterpartyAddress remote = new CounterpartyAddress(header.getSenderCompID(),
                    header.getSenderSubID(),
                    header.getSenderLocationID());
                CounterpartyAddress local = new CounterpartyAddress(header.getTargetCompID(),
                    header.getTargetSubID(),
                    header.getTargetLocationID());

                return new SessionAddress(remote, local);
            }
        }
    }

    private class MessageSender extends Thread {

        private volatile boolean senderShutdown;
        private volatile boolean senderBlocked;
        private volatile boolean active;

        private MessageSender(String name) {
            super(name + "_MSG_SENDER");
        }

        @Override
        public void run() {
            LOGGER.log(Level.INFO, "Transport message sender queue [{0}] starting.", getName());

            while (!senderShutdown) {
                try {
                    if (!senderBlocked) {
                        active = true;
                        sendData();
                    } else {
                        Thread.sleep(1);
                        active = false;
                    }
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "Sender FIX bytes thread [{0}] interrupted.", getName());
                } catch (IOException ex) {
                    blocked = true;
                    block();

                    LOGGER.log(Level.SEVERE, "IO error : {0}", ExceptionUtil.getStackTrace(ex));

                    eventProcessor.onAlertEvent(new AlertEvent(TCPServerWorker.this,
                            Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "I/O error in socket stream writer.", ex)));
                    tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                } catch (Exception ex) {
                    blocked = true;
                    block();

                    LOGGER.log(Level.SEVERE, "Unexpected error : {0}", ExceptionUtil.getStackTrace(ex));

                    eventProcessor.onAlertEvent(new AlertEvent(TCPServerWorker.this,
                            Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "Unexpected error.", ex)));
                    tcpServer.execute(new Command(CommandType.DisconnectTransport, Command.PARAM_SESSION_ADDRESS, sessionAddress));
                }
            }
            active = false;

            LOGGER.log(Level.INFO, "Transport message sender queue [{0}] stopped.", getName());
        }

        public void block() {
            senderBlocked = true;
        }

        public void unblock() {
            senderBlocked = false;
        }

        public void shutdown() {
            senderShutdown = true;
        }

        public boolean isActive() {
            return active;
        }

        private void sendData() throws IOException, InterruptedException {
            byte[] message;
            message = txQueue.take();
            if (out != null) {
                if (message != null) {
                    out.write(message);
                    out.flush();
                    ((TransportStats) getStats()).addBytesOut(message.length);
                } else {
                    Thread.sleep(1);
                }
            }
        }
    }
}
