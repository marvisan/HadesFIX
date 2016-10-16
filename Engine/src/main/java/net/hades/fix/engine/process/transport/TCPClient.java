/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TCPClientProcess.java
 *
 * $Id: TCPClient.java,v 1.52 2011-04-30 04:39:45 vrotaru Exp $
 */
package net.hades.fix.engine.process.transport;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.security.PasswordBank;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.config.Configurator;
import net.hades.fix.engine.config.model.BackupConnectionInfo;
import net.hades.fix.engine.config.model.ClientTcpConnectionInfo;
import net.hades.fix.engine.config.model.SSLInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.ConnectionException;
import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProcessData;
import net.hades.fix.engine.mgmt.data.ProcessStatus;
import net.hades.fix.engine.mgmt.data.TransportProcessData;
import net.hades.fix.engine.mgmt.data.TransportStats;
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
import net.hades.fix.engine.process.session.ClientSessionCoordinator;
import net.hades.fix.message.util.MsgUtil;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The HadesFIX client TCP process that connects to a remote FIX engine host.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.52 $
 */
public final class TCPClient extends Thread implements Transport {

    private static final Logger LOGGER = Logger.getLogger(TCPClient.class.getName());

    private static final String COMPONENT_NAME = "TCPCLI";

    private static final int DEFAULT_SO_LINGER = -1;
    private static final int DEFAULT_TOTAL_NUM_OF_RETRIES = 3;
    private static final int DEFAULT_RETRY_SECONDS_TO_WAIT = 3;
    private static final int DEFAULT_TIMEOUT_MILLIS = 10000;
    private static final int DEFAULT_RX_BUFFER_SIZE = 1000;
    private static final int DEFAULT_TX_BUFFER_SIZE = 1;

    private BlockingDeque<byte[]> rxQueue;
    private BlockingDeque<byte[]> txQueue;
    private BlockingQueue<Command> commandQueue;

    private ClientTcpConnectionInfo configuration;

    private volatile boolean shutdown;
    private volatile boolean blocked;
    private volatile boolean connected;

    private boolean hasSSL;
    private SSLContext sslCtx;
    private Socket socket;

    private BufferedInputStream in;
    private FIXMsgInputStream fixMsgIn;
    private BufferedOutputStream out;

    private TCPConnections connections;

    private ConnectionData currentConnData;

    private EventProcessor eventProcessor;

    private ClientSessionCoordinator sessionCoordinator;

    private AtomicReference<ProcessStatus> processStatus;
    private AtomicReference<TransportProcessData> mgmtData;
    private AtomicReference<TransportStats> stats;

    private FIXMessageStreamReader messageStreamReader;
    private MessageSender messageSender;

    public TCPClient(ClientSessionCoordinator sessionCoordinator, ClientTcpConnectionInfo configuration) throws ConfigurationException {
        super(sessionCoordinator.getName() + "_" + COMPONENT_NAME);
        this.sessionCoordinator = sessionCoordinator;
        this.configuration = configuration;
        commandQueue = new LinkedBlockingQueue<Command>();
        processStatus = new AtomicReference<ProcessStatus>(ProcessStatus.INITIALISING);
        mgmtData = new AtomicReference<TransportProcessData>(new TransportProcessData());
        getMgmtData().setStatus(ProcessStatus.INITIALISING);
        stats = new AtomicReference<TransportStats>(new TransportStats());

        rxQueue = new LinkedBlockingDeque<byte[]>(configuration.getRxBufferSize() != null
                ? configuration.getRxBufferSize()
                : DEFAULT_RX_BUFFER_SIZE);
        txQueue = new LinkedBlockingDeque<byte[]>(DEFAULT_TX_BUFFER_SIZE);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Running TCPClient thread [{0}].", getName());

        getMgmtData().setId(String.valueOf(getId()));
        getMgmtData().setName(getName());
        getMgmtData().setStatus(getProcessStatus());
        getStats().setStartTimestamp(new Date());

        currentConnData = connections.getCurrentConnection();
        blocked = true;

        while (!shutdown) {
            try {
                if (!blocked) {
                    if (!connected) {
                        try {
                            connect(currentConnData);
                            eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                                    LifeCycleType.CLIENT_TCP_CONN.name(),
                                    LifeCycleCode.TRANSP_CONNECT.name()));
                            sessionCoordinator.execute(new Command(CommandType.TransportConnected));
                            messageSender.unblock();
                        } catch (ConnectionException ex) {
                            // we will retry if configured
                            LOGGER.log(Level.SEVERE, "Connection error : {0}", ExceptionUtil.getStackTrace(ex));
                            if (retryCycleOrQuit()) {
                                block();
                                connected = false;
                                eventProcessor.onAlertEvent(new AlertEvent(this,
                                        Alert.createAlert(getName(), ComponentType.TCPClient.toString(),
                                                BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, null, ex)
                                ));
                                sessionCoordinator.execute(new Command(CommandType.ReconnectTransport));
                            }
                        }
                    }
                }
                try {
                    processCommand(commandQueue.take());
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "TCP Client worker thread [{)}] has been interrupted.", getName());
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Unexpected error : {0}", ExceptionUtil.getStackTrace(ex));
                block();
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPClient.toString(),
                                BaseSeverityType.FATAL, AlertCode.TRANSP_ABORT, "Unexpected error.", ex)
                ));
                eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                        LifeCycleType.CLIENT_TCP_CONN.name(),
                        LifeCycleCode.TRANSP_DISABLE.name()));
                sessionCoordinator.execute(new Command(CommandType.ShutdownNow));
            }
        }

        LOGGER.log(Level.INFO, "TCPClient thread [{0}] shutdown.", getName());
    }

    public void initialise() throws ConfigurationException {
        hasSSL = checkSSLEnabled(configuration.getSslData());
        connections = extractConnections();

        // starting event processor
        eventProcessor = new GenericEventProcessor(getName());

        messageSender = new MessageSender(getName());
        messageStreamReader = new FIXMessageStreamReader(getName());

        processStatus.set(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);
    }

    @Override
    public void execute(Command command) {
        try {
            commandQueue.put(command);
        } catch (InterruptedException ex) {
            String errMsg = "TCP Client thread [" + getName() + "] has been unexpectedly interrupted.";
            LOGGER.log(Level.WARNING, "{0} Error was : {1}", new Object[]{errMsg, ex.getMessage()});
        }
    }

    @Override
    public byte[] readMessage() throws InterruptedException {
        byte[] message = rxQueue.take();
        if (message != null && LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Reading from RX queue : {0}", MsgUtil.getPrintableRawFixMessage(message));
        }

        return message;
    }

    @Override
    public byte[] readMessage(int timeout) throws InterruptedException {
        byte[] message = rxQueue.poll(timeout, TimeUnit.MILLISECONDS);
        if (message != null && LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Reading from RX queue : {0}", MsgUtil.getPrintableRawFixMessage(message));
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
    public ProcessStatus getProcessStatus() {
        return processStatus.get();
    }

    @Override
    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus.set(processStatus);
    }

    @Override
    public String retrieveSessionAddress() {
        return sessionCoordinator.getSessionAddress().toString();
    }

    @Override
    public ProcessData getMgmtData() {
        return mgmtData.get();
    }

    @Override
    public TransportStats getStats() {
        return stats.get();
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

    public String getCptyID() {
        return sessionCoordinator.getCptyID();
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

            case ReconnectTransport:
                reconnect((Integer) command.getParameter(Command.PARAM_RECONNECT_DELAY));
                break;

            case StopTCPReader:
                stopTCPReader();
                break;

            case StopIncoming:
                stopIncoming();
                break;

            case DisconnectTransport:
                disconnect();
                break;

            case Shutdown:
                shutdown();
                break;

            case ShutdownNow:
                shutdownNow();
                break;

            default:
                LOGGER.log(Level.SEVERE, "Command [{0}] not implemented for a TCP Client.", command.getCommandType().name());

        }
    }

    private void startup() {
        startEventProcessor();

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "TCP Client [{0}] started up.", getName());
    }

    private void block() {
        LOGGER.log(Level.INFO, "Blocking TCP Client [{0}].", getName());

        blocked = true;

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "TCP Client [{0}] blocked.", getName());
    }

    private void blockReader() {
        LOGGER.log(Level.INFO, "Blocking TCP Client [{0}] Reader.", getName());

        if (messageStreamReader != null) {
            messageStreamReader.block();
            messageStreamReader.interrupt();
            while (messageStreamReader.isActive()) {
                ThreadUtil.sleep(1);
            }
        }

        LOGGER.log(Level.INFO, "TCP Client [{0}] Reader blocked.", getName());
    }

    private void blockSender() {
        LOGGER.log(Level.INFO, "Blocking TCP Client [{0}] Sender.", getName());

        if (messageSender != null) {
            messageSender.block();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                ThreadUtil.sleep(1);
            }
        }

        LOGGER.log(Level.INFO, "TCP Client [{0}] Sender blocked.", getName());
    }

    private void unblock() {
        LOGGER.log(Level.INFO, "Unblocking TCP Client [{0}].", getName());

        blocked = false;

        setProcessStatus(ProcessStatus.ACTIVE);
        getMgmtData().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "TCP Client [{0}] unblocked.", getName());
    }

    private void shutdown() {
        LOGGER.log(Level.INFO, "Stopping TCP client [{0}].", getName());

        blocked = true;
        do {
            // wait for the RX and TX to get flushed
            ThreadUtil.sleep(1);
        } while ((rxQueue.size() > 0 || txQueue.size() > 0) && ProcessStatus.ACTIVE.equals(getProcessStatus()));

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        if (messageStreamReader != null) {
            messageStreamReader.shutdown();
            messageStreamReader.interrupt();
            while (messageStreamReader.isActive()) {
                ThreadUtil.sleep(1);
            }
        }
        if (messageSender != null) {
            messageSender.shutdown();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                ThreadUtil.sleep(1);
            }
        }
        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                ThreadUtil.sleep(1);
            }
        }
        closeSocketStreams();
        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);
        this.shutdown = true;

        LOGGER.log(Level.INFO, "TCP client [{0}] shutdown.", getName());
    }

    private void shutdownNow() {
        LOGGER.log(Level.INFO, "Shutting down immediately TCP client [{0}].", getName());

        blocked = true;
        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);
        if (messageStreamReader != null) {
            messageStreamReader.shutdown();
            messageStreamReader.interrupt();
            while (messageStreamReader.isActive()) {
                ThreadUtil.sleep(1);
            }
        }
        if (messageSender != null) {
            messageSender.shutdown();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                ThreadUtil.sleep(1);
            }
        }
        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                ThreadUtil.sleep(1);
            }
        }
        closeSocketStreams();
        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);
        this.shutdown = true;

        LOGGER.log(Level.INFO, "TCP client [{0}] shutdown immediately.", getName());
    }

    private void reconnect(int interval) {
        LOGGER.log(Level.INFO, "Reconnecting TCP Client [{0}].", getName());

        shutdownReader();
        shutdownSender();
        closeSocketStreams();
        ThreadUtil.sleep(interval);
        blocked = false;

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);
    }

    private void stopIncoming() {
        LOGGER.log(Level.INFO, "Stopping incoming messages for TCP Client [{0}].", getName());

        blocked = true;

        blockReader();
        do {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        } while (!rxQueue.isEmpty());

        setProcessStatus(ProcessStatus.INCOMING_STOPPED);
        getMgmtData().setStatus(ProcessStatus.INCOMING_STOPPED);

        LOGGER.log(Level.INFO, "TCP Client [{0}] incoming messages stopped.", getName());
    }

    private void disconnect() {
        LOGGER.log(Level.INFO, "Disconnecting TCP Client [{0}].", getName());

        blocked = true;

        do {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        } while (!txQueue.isEmpty());

        block();
        while (!ProcessStatus.INACTIVE.equals(getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        shutdownReader();
        shutdownSender();
        closeSocketStreams();

        LOGGER.log(Level.INFO, "TCP Client [{0}] disconnected.", getName());
    }

    private void shutdownReader() {
        if (messageStreamReader != null) {
            messageStreamReader.shutdown();
            messageStreamReader.interrupt();
            while (messageStreamReader.isActive()) {
                ThreadUtil.sleep(1);
            }
            messageStreamReader = null;
        }
    }

    private void shutdownSender() {
        if (messageSender != null) {
            messageSender.shutdown();
            messageSender.interrupt();
            while (messageSender.isActive()) {
                ThreadUtil.sleep(1);
            }
            messageSender = null;
        }
    }

    private void stopTCPReader() {
        blockReader();

        setProcessStatus(ProcessStatus.TCP_READER_STOPPED);
        getMgmtData().setStatus(ProcessStatus.TCP_READER_STOPPED);
    }

    private void startEventProcessor() {
        eventProcessor.start();
        ThreadUtil.blockUntilAlive(eventProcessor);
        while (!eventProcessor.isActive()) {
            ThreadUtil.sleep(1);
        }

        addAlertListener(sessionCoordinator.getEventProcessor());
        addLifeCycleListener(sessionCoordinator.getEventProcessor());
        addMessageListener(sessionCoordinator.getEventProcessor());
    }

    private void startMessageSender() {
        messageSender.start();
        messageSender.block();
        ThreadUtil.blockUntilAlive(messageSender);
        while (messageSender.isActive()) {
            ThreadUtil.sleep(1);
        }
    }

    private void startMessageReader() {
        messageStreamReader.start();
        ThreadUtil.blockUntilAlive(messageStreamReader);
        while (!messageStreamReader.isActive()) {
            ThreadUtil.sleep(1);
        }
    }

    private void connect(ConnectionData connData) throws UnrecoverableException, ConnectionException {
        if (connData == null) {
            return;
        }

        if (socket != null && socket.isConnected()) {
            return;
        }

        LOGGER.log(Level.INFO, "Attempting to connect to host [{0}] port [{1}].", new Object[]{connData.host, connData.port});

        int timeout = (configuration.getSocketTimeout() != null ? configuration.getSocketTimeout() : DEFAULT_TIMEOUT_MILLIS);
        try {
            socket = getSocket(connData.host, connData.port);
            setTcpNodelay();
            setSoLinger();
            setSoRcvbuf();
            setSoSndbuf();
            setSendKeepAlive();
            setSocketTimeout(timeout);

            LOGGER.log(Level.INFO, "Connected to host [{0}] port [{1}].", new Object[]{connData.host, connData.port});

            in = new BufferedInputStream(socket.getInputStream());
            fixMsgIn = new FIXMsgInputStream(in);
            out = new BufferedOutputStream(socket.getOutputStream());
            connected = true;
            messageStreamReader = new FIXMessageStreamReader(getName());
            startMessageReader();
            messageSender = new MessageSender(getName());
            startMessageSender();
        } catch (UnknownHostException ex) {
            String error = "The give host name address is not valid [" + configuration.getHost() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
            throw new UnrecoverableException(error, ex);
        } catch (SocketTimeoutException ex) {
            String error = "The socket connection has timeout after [" + timeout + "] seconds.";
            throw new ConnectionException(error, ex);
        } catch (IOException ex) {
            String error = "I/O socket connection.";
            throw new ConnectionException(error, ex);
        }
    }

    private void setTcpNodelay() {
        if (configuration.getTcpNodelay() != null && configuration.getTcpNodelay()) {
            try {
                socket.setTcpNoDelay(true);
            } catch (SocketException ex) {
                String logMsg = "TCP_NODELAY cannot be set on this socket implementation.";
                LOGGER.warning(logMsg);

                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                        ComponentType.TCPClient.toString(),
                        BaseSeverityType.WARNING,
                        null,
                        logMsg,
                        ex)));
            }
        }
    }

    private void setSendKeepAlive() {
        boolean sendKeepAlive = true;
        if (configuration.getSendKeepAlive() != null) {
            sendKeepAlive = configuration.getSendKeepAlive();
        }
        try {
            socket.setKeepAlive(sendKeepAlive);
        } catch (SocketException ex) {
            String logMsg = "TCP_NODELAY cannot be set on this socket implementation.";
            LOGGER.warning(logMsg);

            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                    ComponentType.TCPClient.toString(),
                    BaseSeverityType.WARNING,
                    null,
                    logMsg,
                    ex)));
        }
    }

    private void setSoLinger() {
        int socketLinger = DEFAULT_SO_LINGER;
        if (configuration.getSocketLingerTimeout() != null) {
            socketLinger = configuration.getSocketLingerTimeout();
        }
        if (socketLinger > 0) {
            try {
                socket.setSoLinger(true, socketLinger);
            } catch (SocketException ex) {
                String logMsg = "SO_LINGER cannot be set on this socket implementation.";
                LOGGER.warning(logMsg);

                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                        ComponentType.TCPClient.toString(),
                        BaseSeverityType.WARNING,
                        null,
                        logMsg,
                        ex)));
            }
        }
    }

    private void setSoRcvbuf() {
        if (configuration.getSocketRcvbuf() != null && configuration.getSocketRcvbuf() > 0) {
            try {
                socket.setReceiveBufferSize(configuration.getSocketRcvbuf());
            } catch (SocketException ex) {
                String logMsg = "SO_RCVBUF cannot be set on this socket implementation.";
                LOGGER.warning(logMsg);

                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                        ComponentType.TCPClient.toString(),
                        BaseSeverityType.WARNING,
                        null,
                        logMsg,
                        ex)));
            }
        }
    }

    private void setSoSndbuf() {
        if (configuration.getSocketSndbuf() != null && configuration.getSocketSndbuf() > 0) {
            try {
                socket.setReceiveBufferSize(configuration.getSocketSndbuf());
            } catch (SocketException ex) {
                String logMsg = "SO_SNDBUF cannot be set on this socket implementation.";
                LOGGER.warning(logMsg);

                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                        ComponentType.TCPClient.toString(),
                        BaseSeverityType.WARNING,
                        null,
                        logMsg,
                        ex)));
            }
        }
    }

    private void setSocketTimeout(int timeout) {
        try {
            socket.setSoTimeout(timeout);
        } catch (SocketException ex) {
            String logMsg = "Initial socket timeout cannot be set on this socket implementation.";
            LOGGER.warning(logMsg);

            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(),
                            ComponentType.TCPClient.toString(),
                            BaseSeverityType.WARNING,
                            AlertCode.TRANSP_CONNECT,
                            logMsg,
                            ex)
            ));
        }
    }

    private void resetSocketTimeout() {
        try {
            socket.setSoTimeout(0);
        } catch (SocketException ex) {
            String logMsg = "Socket timeout cannot be reset on this socket implementation.";
            LOGGER.warning(logMsg);

            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(),
                    ComponentType.TCPClient.toString(),
                    BaseSeverityType.WARNING,
                    AlertCode.TRANSP_CONNECT,
                    logMsg,
                    ex)));
        }
    }

//    private InetSocketAddress createRemoteAddress(String hostName, Integer port) throws UnknownHostException {
//        InetAddress address;
//        if (isDottedAddress(hostName)) {
//            address = InetAddress.getByAddress(getDottedAddress(hostName));
//        } else {
//            address = InetAddress.getByName(hostName);
//        }
//
//        return new InetSocketAddress(address, port);
//    }

//    private byte[] getDottedAddress(String host) {
//        byte[] result = new byte[4];
//        String[] tokens = host.split("\\.");
//        if (tokens.length != 4) {
//            String error = "Received IP address is bad [" + host + "].";
//            LOGGER.severe(error);
//            throw new RuntimeException(error);
//        }
//        for (int i = 0; i < tokens.length; i++) {
//            result[i] = (byte) Integer.parseInt(tokens[i]);
//        }
//
//        return result;
//    }

//    private boolean isDottedAddress(String host) {
//        String[] tokens = host.split("\\.");
//        boolean allNumbers = true;
//        for (String token : tokens) {
//            if (!token.matches("[0-9]*")) {
//                allNumbers = false;
//                break;
//            }
//        }
//
//        return allNumbers;
//    }

    private void closeSocketStreams() {
        LOGGER.log(Level.INFO, "Closing socket streams for TCP Client [{0}].", getName());

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
                LOGGER.log(Level.SEVERE, "Could not close socket ouput stream. Error was : {0}", ExceptionUtil.getStackTrace(ex));
            }
        }
        connected = false;

        LOGGER.log(Level.INFO, "TCP Client [{0}] socket streams closed.", getName());
    }

    private boolean retryCycleOrQuit() {
        currentConnData = connections.getCurrentConnection();
        if (currentConnData == null) {
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPClient.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_ABORT, "No available connection data found.", null)
            ));
            eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                    LifeCycleType.CLIENT_TCP_CONN.name(),
                    LifeCycleCode.TRANSP_DISABLE.name()));
            sessionCoordinator.execute(new Command(CommandType.DisconnectTransport));
            return false;
        }

        return true;
    }

    private TCPConnections extractConnections() {
        TCPConnections result = new TCPConnections();
        ConnectionData defConn = new ConnectionData(configuration.getHost(), configuration.getPort());
        result.addConnectionData(defConn);
        if (configuration.getBackupConnections() != null && configuration.getBackupConnections().length > 0) {
            for (BackupConnectionInfo connInfo : configuration.getBackupConnections()) {
                ConnectionData conn = new ConnectionData(connInfo.getHost(), connInfo.getPort());
                result.addConnectionData(conn);
            }
        }

        return result;
    }

    private boolean checkSSLEnabled(SSLInfo sslData) {
        boolean result = false;
        if (sslData != null && sslData.getKeyStoreLoc() != null && !sslData.getKeyStoreLoc().isEmpty()) {
            result = true;
        }

        return result;
    }

    private Socket getSocket(String host, Integer port) throws UnrecoverableException, ConnectionException {
        Socket tcpSocket;
        if (hasSSL) {
            try {
                if (sslCtx == null) {
                    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    if (configuration.getSslData().getKeyPasswd() == null || configuration.getSslData().getKeyPasswd().isEmpty()) {
                        configuration.getSslData().setKeyPasswd(new String(PasswordBank.getInstance().getEntryValue(
                                configuration.getSslData().getKeyStorePasswd())));
                    }
                    KeyStore ks = readKeystore(Configurator.getConfigDir() + "/" + configuration.getSslData().getKeyStoreLoc());
                    kmf.init(ks, configuration.getSslData().getKeyPasswd().toCharArray());
                    TrustManagerFactory tmf = null;
                    KeyStore ts = null;
                    if (configuration.getSslData().getTrustStoreLoc() != null && !configuration.getSslData().getTrustStoreLoc().isEmpty()) {
                        tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                        ts = readKeystore(Configurator.getConfigDir() + "/" + configuration.getSslData().getTrustStoreLoc());
                    }
                    if (ts != null) {
                        tmf.init(ts);
                    }
                    sslCtx = SSLContext.getInstance("TLS");
                    sslCtx.init(kmf.getKeyManagers(), tmf != null ? tmf.getTrustManagers() : null, null);
                }
                SSLSocketFactory factory = sslCtx.getSocketFactory();
                tcpSocket = factory.createSocket(host, port);
            } catch (UnrecoverableKeyException ex) {
                String errMsg = "Unable to retrieve the key from keystore.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPClient.toString(), BaseSeverityType.FATAL, null, errMsg, ex)));
                throw new UnrecoverableException(errMsg, ex);
            } catch (IOException ex) {
                String errMsg = "Could not create a SSL socket for [" + host + ":" + port + "].";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPClient.toString(), BaseSeverityType.FATAL, null, errMsg, ex)));
                throw new UnrecoverableException(errMsg, ex);
            } catch (KeyManagementException ex) {
                String errMsg = "Could not use the key from given keystore.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPClient.toString(), BaseSeverityType.FATAL, null, errMsg, ex)));
                throw new UnrecoverableException(errMsg, ex);
            } catch (KeyStoreException ex) {
                String errMsg = "Could not read the key from given keystore.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPClient.toString(), BaseSeverityType.FATAL, null, errMsg, ex)));
                throw new UnrecoverableException(errMsg, ex);
            } catch (NoSuchAlgorithmException ex) {
                String errMsg = "The algorithm used is not supported.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPClient.toString(), BaseSeverityType.FATAL, null, errMsg, ex)));
                throw new UnrecoverableException(errMsg, ex);
            }

        } else {
            try {
                tcpSocket = new Socket(host, port);
            } catch (ConnectException ex) {
                String errMsg = "Could not connect to [" + host + ":" + port + "].";
                throw new ConnectionException(errMsg, ex);
            } catch (UnknownHostException ex) {
                String errMsg = "Could not create a socket for [" + host + ":" + port + "].";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(errMsg, ex);
            } catch (IOException ex) {
                String errMsg = "Could not create a socket for [" + host + ":" + port + "].";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(errMsg, ex);
            }
        }

        return tcpSocket;
    }

    private KeyStore readKeystore(String keyStoreFileName) throws UnrecoverableException {
        KeyStore ks;
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException ex) {
            String errMsg = "Could not create a KeySTore of default type.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPClient.toString(), BaseSeverityType.FATAL, null, errMsg, ex)));
            throw new UnrecoverableException(errMsg, ex);
        }

        // get user password and file input stream
        if (configuration.getSslData().getKeyStorePasswd() == null || configuration.getSslData().getKeyStorePasswd().isEmpty()) {
            configuration.getSslData().setKeyStorePasswd("hadesfix");
        }
        char[] password = PasswordBank.getInstance().getEntryValue(configuration.getSslData().getKeyStorePasswd());

        FileInputStream fis = null;
        try {
            fis = new java.io.FileInputStream(keyStoreFileName);
            ks.load(fis, password);
        } catch (IOException ex) {
            String errMsg = "Could not load the keystore file [" + keyStoreFileName + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPClient.toString(), BaseSeverityType.FATAL, null, errMsg, ex)));
            throw new UnrecoverableException(errMsg, ex);
        } catch (NoSuchAlgorithmException ex) {
            String errMsg = "Could not create a keystore for the given algorithm.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPClient.toString(), BaseSeverityType.FATAL, null, errMsg, ex)));
            throw new UnrecoverableException(errMsg, ex);
        } catch (CertificateException ex) {
            String errMsg = "Could not load the certificate form keystore [" + keyStoreFileName + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPClient.toString(), BaseSeverityType.FATAL, null, errMsg, ex)));
            throw new UnrecoverableException(errMsg, ex);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Could not close the file handler [{0}].", keyStoreFileName);
                }
            }
        }

        return ks;
    }

    // INNER CLASSES

    private class FIXMessageStreamReader extends Thread {

        private volatile boolean readerShutdown;
        private volatile boolean readerBlocked;
        private volatile boolean firstMessage;
        private volatile boolean active;

        private FIXMessageStreamReader(String name) {
            super(name + "_MSG_STREAM_READER");
            firstMessage = true;
            LOGGER.info("FIX byte message stream reader initialised.");
        }

        @Override
        public void run() {
            LOGGER.log(Level.INFO, "Starting FIX byte message stream reader [{0}].", getName());

            while (!readerShutdown) {
                try {
                    if (fixMsgIn != null && !readerBlocked) {
                        active = true;
                        writeSocketData();
                    } else {
                        Thread.sleep(1);
                        if (readerBlocked) {
                            active = false;
                        }
                    }
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "Receive bytes stream thread [{0}] interrupted.", getName());
                } catch (SocketTimeoutException ex) {
                    LOGGER.log(Level.SEVERE, "Socket timed out waiting for the first message : {0}", ExceptionUtil.getStackTrace(ex));

                    TCPClient.this.block();
                    while (!ProcessStatus.INACTIVE.equals(getProcessStatus())) {
                        if (!ThreadUtil.sleep(1)) {
                            break;
                        }
                    }
                    blockSender();
                    readerBlocked = true;
                    closeSocketStreams();
                    connected = false;
                    if (retryCycleOrQuit()) {
                        eventProcessor.onAlertEvent(new AlertEvent(this,
                                Alert.createAlert(getName(), ComponentType.TCPClient.toString(),
                                        BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, null, ex)
                        ));
                        eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                                LifeCycleType.CLIENT_TCP_CONN.name(),
                                LifeCycleCode.TRANSP_DISCONNECT.name()));

                        LOGGER.severe("Communication error... retrying to reconnect client.");

                        sessionCoordinator.execute(new Command(CommandType.ReconnectTransport));
                    }
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Communication error: {0}", ExceptionUtil.getStackTrace(ex));

                    TCPClient.this.block();
                    while (!ProcessStatus.INACTIVE.equals(getProcessStatus())) {
                        if (!ThreadUtil.sleep(1)) {
                            break;
                        }
                    }
                    blockSender();
                    readerBlocked = true;
                    closeSocketStreams();
                    connected = false;
                    if (retryCycleOrQuit()) {
                        eventProcessor.onAlertEvent(new AlertEvent(this,
                                Alert.createAlert(getName(), ComponentType.TCPClient.toString(),
                                        BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, null, ex)
                        ));
                        eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                                LifeCycleType.CLIENT_TCP_CONN.name(),
                                LifeCycleCode.TRANSP_DISCONNECT.name()));

                        LOGGER.severe("Communication error... retrying to reconnect client.");

                        sessionCoordinator.execute(new Command(CommandType.ReconnectTransport));
                    }
                }
            }
            active = false;

            LOGGER.log(Level.INFO, "FIX byte message stream reader [{0}] stopped.", getName());
        }

        public void block() {
            readerBlocked = true;
        }

        public void unblock() {
            readerBlocked = false;
        }

        public void shutdown() {
            readerShutdown = true;
        }

        public boolean isActive() {
            return active;
        }

        private void writeSocketData() throws IOException, ConnectionException, InterruptedException {
            byte[] fixMsg = fixMsgIn.readMessage();
            int fixMsgLen;
            if (fixMsg != null) {
                fixMsgLen = fixMsg.length;
                if (firstMessage) {
                    // after getting the first message reset socket timeout
                    resetSocketTimeout();
                    firstMessage = false;
                }

                rxQueue.put(fixMsg);
                getStats().addBytesIn(fixMsgLen);
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
            LOGGER.log(Level.INFO, "Msg sender queue [{0}] starting.", getName());

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
                    LOGGER.log(Level.WARNING, "Send data thread [{0}] interrupted.", getName());
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Communication error: {0}", ExceptionUtil.getStackTrace(ex));

                    TCPClient.this.block();
                    while (!ProcessStatus.INACTIVE.equals(getProcessStatus())) {
                        if (!ThreadUtil.sleep(1)) {
                            break;
                        }
                    }
                    blockReader();
                    senderBlocked = true;
                    closeSocketStreams();
                    connected = false;
                    if (retryCycleOrQuit()) {
                        eventProcessor.onAlertEvent(new AlertEvent(this,
                                Alert.createAlert(getName(), ComponentType.TCPClient.toString(),
                                        BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, null, ex)
                        ));
                        eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                                LifeCycleType.CLIENT_TCP_CONN.name(),
                                LifeCycleCode.TRANSP_DISCONNECT.name()));

                        LOGGER.severe("Communication error... retrying to reconnect client.");

                        sessionCoordinator.execute(new Command(CommandType.ReconnectTransport));
                    }
                }
            }
            active = false;

            LOGGER.log(Level.INFO, "Msg sender queue [{0}] stopped.", getName());
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
            byte[] message = txQueue.take();
            if (out != null) {
                if (message != null) {
                    out.write(message);
                    out.flush();
                    getStats().addBytesOut(message.length);
                }
            }
        }
    }

    private class ConnectionData {

        private String host;
        private Integer port;

        public ConnectionData(String host, Integer port) {
            this.host = host;
            this.port = port;
        }
    }

    private class TCPConnections {

        private int numOfRetries;
        private int retrySecondsToWait;

        private int currNumOfRetries;

        private List<ConnectionData> connections;

        private int currentIdx;

        public TCPConnections() {
            connections = new ArrayList<ConnectionData>();
            numOfRetries = configuration.getNumOfRetries() != null
                    ? configuration.getNumOfRetries()
                    : DEFAULT_TOTAL_NUM_OF_RETRIES;
            retrySecondsToWait = configuration.getRetrySecondsToWait() != null
                    ? configuration.getRetrySecondsToWait()
                    : DEFAULT_RETRY_SECONDS_TO_WAIT;
        }

        public void addConnectionData(ConnectionData connectionData) {
            connections.add(connectionData);
        }

        public ConnectionData getCurrentConnection() {
            ConnectionData result = null;
            while (numOfRetries == 0 || currNumOfRetries < numOfRetries) {
                try {
                    Thread.sleep(retrySecondsToWait * 1000);
                } catch (InterruptedException ex1) {
                    LOGGER.log(Level.SEVERE, "Should not have been interrupted : {0}", ex1.toString());
                }
                if (currentIdx < connections.size()) {
                    result = connections.get(currentIdx);
                    currentIdx++;
                    break;
                } else {
                    currentIdx = 0;
                    currNumOfRetries++;
                }
            }

            return result;
        }
    }
}
