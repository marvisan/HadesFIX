/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TCPServer.java
 *
 * $Id: TCPServer.java,v 1.24 2011-04-30 04:39:44 vrotaru Exp $
 */
package net.hades.fix.engine.process.transport;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.security.PasswordBank;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.Configurator;
import net.hades.fix.engine.config.model.SSLInfo;
import net.hades.fix.engine.config.model.ServerTcpConnectionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.ConnectionException;
import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.*;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.ManagedProcess;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.EventProcessor;
import net.hades.fix.engine.process.event.GenericEventProcessor;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;
import net.hades.fix.engine.process.session.ServerSessionCoordinator;
import net.hades.fix.engine.process.session.SessionCoordinator;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TCP/IP server implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.24 $
 */
public final class TCPServer extends Thread implements ManagedProcess {

    private static final Logger LOGGER = Logger.getLogger(TCPServer.class.getName());

    private static final String COMPONENT_NAME = "TCPSRV";
    private static final int DEFAULT_BACKLOG_VALUE = 0;

    private final HadesInstance hadesInstance;
    private final ServerTcpConnectionInfo configuration;

    private volatile boolean shutdown;

    private final BlockingQueue<Command> commandQueue;

    private boolean hasSSL;
    private SSLContext sslCtx;

    private List<String> restrictedHosts;
    private final Map<SessionAddress, TCPServerWorker> sessionWorkers;
    private final Map<SessionAddress, ServerSessionCoordinator> sessionCoordinators;

    private final AtomicReference<ProcessStatus> processStatus;
    private final AtomicReference<TransportProcessData> mgmtData;
    private final AtomicReference<TransportStats> stats;

    private ServerSocket server;
    private SocketAddress serverAddress;
    private int serverPort;
    private final int totalNumConnections;
    private int rcvBuffSize;

    private EventProcessor eventProcessor;
    private TCPListener tcpListener;

    public TCPServer(HadesInstance hadesInstance, ServerTcpConnectionInfo configuration) throws ConfigurationException {
        super(configuration.getName() + "_" + COMPONENT_NAME);
        this.hadesInstance = hadesInstance;
        this.configuration = configuration;
        totalNumConnections = DEFAULT_BACKLOG_VALUE;

        processStatus = new AtomicReference<>(ProcessStatus.INITIALISING);
        mgmtData = new AtomicReference<>(new TransportProcessData());
        getMgmtData().setStatus(ProcessStatus.INITIALISING);
        stats = new AtomicReference<>(new TransportStats());

        commandQueue = new LinkedBlockingQueue<>();
        sessionWorkers = Collections.synchronizedMap(new HashMap<SessionAddress, TCPServerWorker>());
        sessionCoordinators = Collections.synchronizedMap(new HashMap<SessionAddress, ServerSessionCoordinator>());
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "TCPServer thread [{0}] running.", getName());

        getMgmtData().setId(String.valueOf(getId()));
        getMgmtData().setName(getName());
        getMgmtData().setStatus(getProcessStatus());
        getStats().setStartTimestamp(new Date());

        try {
            createServerSocket();
        } catch (IOException ex) {
            setProcessStatus(ProcessStatus.INACTIVE);
            getMgmtData().setStatus(ProcessStatus.INACTIVE);
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPServer.toString(),
                    BaseSeverityType.FATAL, AlertCode.TRANSP_ABORT, "I/O error starting TCP listener.", ex)));
            shutdown = true;
        } catch (UnrecoverableException ex) {
            setProcessStatus(ProcessStatus.INACTIVE);
            getMgmtData().setStatus(ProcessStatus.INACTIVE);
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPServer.toString(),
                    BaseSeverityType.FATAL, AlertCode.TRANSP_ABORT, ex.getMessage(), ex)));
            shutdown = true;
        }
        while (!shutdown) {
            try {
                processCommand(commandQueue.take());
            } catch (InterruptedException ex) {
                String errMsg = "TCPServer thread [" + getName() + "] has been unexpectedly interrupted.";
                LOGGER.log(Level.WARNING, "{0} Error was : {1}", new Object[] { errMsg, ex.toString() });
            }
        }

        LOGGER.log(Level.INFO, "TCPServer thread [{0}] destroyed.", getName());
    }

    @Override
    public void execute(Command command) {
        try {
            commandQueue.put(command);
        } catch (InterruptedException ex) {
            String errMsg = "TCP Server thread [" + getName() + "] has been unexpectedly interrupted.";
            LOGGER.log(Level.WARNING, "{0} Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });
        }
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

    @Override
    public String retrieveSessionAddress() {
        return "Undefined";
    }

    public EventProcessor getEventProcessor() {
        return eventProcessor;
    }

    public int getServerPort() {
        return serverPort;
    }

    public ServerTcpConnectionInfo getConfiguration() {
        return configuration;
    }

    public void addServerSessionCoordinator(SessionAddress sessionAddress, ServerSessionCoordinator coordinator) {
        sessionCoordinators.put(sessionAddress, coordinator);
    }

    public boolean hasServerSessionCoordinator(SessionAddress sessionAddress) {
        return sessionCoordinators.containsKey(sessionAddress);
    }

    public void setServerSessionCoordinator(SessionAddress sessionAddress, ServerSessionCoordinator coordinator) {
        sessionCoordinators.put(sessionAddress, coordinator);
    }

    public void unsetSessionCoordinator(SessionAddress address) {
        sessionWorkers.put(address, null);
        sessionCoordinators.put(address, null);
    }

    public void unsetSessionWorker(SessionAddress address) {
        sessionWorkers.put(address, null);
    }

    @Override
    public String toString() {
        return "TCP Server : " + serverAddress.toString() + "].";
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
           
            case DisconnectTransport:
                transportDisconnected((SessionAddress)command.getParameter(Command.PARAM_SESSION_ADDRESS));
                break;

            case TransportConnected:
                transportConnected((SessionAddress) command.getParameter(Command.PARAM_SESSION_ADDRESS),
                        (TCPServerWorker) command.getParameter(Command.PARAM_SERVER_TRANSPORT_WORKER));
                break;

            case Shutdown:
                shutdown();
                break;

            case ShutdownNow:
                shutdownNow();
                break;

            case SetServerSessionTransport:
                setSessionWorker((SessionAddress) command.getParameter(Command.PARAM_SESSION_ADDRESS),
                        (TCPServerWorker) command.getParameter(Command.PARAM_SERVER_TRANSPORT_WORKER));
                break;

            default:
                LOGGER.log(Level.SEVERE, "Command [{0}] not implemented for a TCP Server.", command.getCommandType().name());

        }
    }

    public void initialise() throws ConfigurationException {
        eventProcessor = new GenericEventProcessor(getName());

        hasSSL = checkSSLEnabled(configuration.getSslData());

        setServerAddress();
        setServerParameters();

        getMgmtData().setStatus(ProcessStatus.INITIALISING);
    }

    private void startup() {
        startEventProcessor();
        startTCPListener();

        setProcessStatus(ProcessStatus.ACTIVE);
        getMgmtData().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "TCP Server [{0}] started.", getName());
    }

    private void block() {
        if (tcpListener != null) {
            tcpListener.block();
            tcpListener.interrupt();
            while (tcpListener.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "TCP Server [{0}] blocked.", getName());
    }

    private  void unblock() {
        if (tcpListener != null) {
            tcpListener.unblock();
            tcpListener.interrupt();
            while (!tcpListener.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        setProcessStatus(ProcessStatus.ACTIVE);
        getMgmtData().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "TCP Server [{0}] unblocked.", getName());
    }

    private void shutdown() {
        LOGGER.log(Level.INFO, "Shutting down TCP Server [{0}].", getName());

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);
        
        shutdownSessionCoordinators();
        if (tcpListener != null) {
            tcpListener.unblock();
            tcpListener.interrupt();
            while (!tcpListener.isActive()) {
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
        }
        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);

        LOGGER.log(Level.INFO, "TCP Server [{0}] shutdown.", getName());

        shutdown = true;
    }

    private void shutdownNow() {
        LOGGER.log(Level.INFO, "Shutting down immediate TCP Server [{0}].", getName());

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);
        
        shutdownNowSessionCoordinators();
        if (tcpListener != null) {
            tcpListener.unblock();
            tcpListener.interrupt();
            while (!tcpListener.isActive()) {
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
        }
        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);

        LOGGER.log(Level.INFO, "TCP Server [{0}] shutdown immediate.", getName());

        shutdown = true;
    }
     
    private void transportConnected(SessionAddress sessionAddress, TCPServerWorker worker) {
        LOGGER.log(Level.INFO, "Transport connected [{0}]", sessionAddress.toString());
            
        SessionCoordinator sc = sessionCoordinators.get(sessionAddress);
        if (setSessionWorker(sessionAddress, worker)) {
            sc.execute(new Command(CommandType.TransportConnected, Command.PARAM_SERVER_TRANSPORT_WORKER, worker));
            while (!ProcessStatus.ACTIVE.equals(sc.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }

            setProcessStatus(ProcessStatus.ACTIVE);
            getMgmtData().setStatus(ProcessStatus.ACTIVE);
        } else {
            // session was blocked here - only shutdown the transport worker
            worker.execute(new Command(CommandType.ShutdownNow));
        }
    }

    private void transportDisconnected(SessionAddress sessionAddress) {
        LOGGER.log(Level.INFO, "Transport disconnected [{0}]", sessionAddress.toString());
        
        TCPServerWorker worker = sessionWorkers.get(sessionAddress);
        
        SessionCoordinator sc = sessionCoordinators.get(sessionAddress);
        sc.execute(new Command(CommandType.DisconnectTransport));
        while (!ProcessStatus.INACTIVE.equals(sc.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        if (worker != null) {
            worker.execute(new Command(CommandType.ShutdownNow));
        }
        unsetSessionWorker(sessionAddress);

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);
    }

    private void shutdownSessionCoordinators() {
        for (SessionAddress sessionAddress : sessionWorkers.keySet()) {
            TCPServerWorker sw = sessionWorkers.get(sessionAddress);
            if (sw != null) {
                sw.execute(new Command(CommandType.Shutdown));
            }
            SessionCoordinator sc = sessionCoordinators.get(sessionAddress);
            if (sc != null) {
                sc.execute(new Command(CommandType.Shutdown));
            }
        }
    }
    
    private void shutdownNowSessionCoordinators() {
        for (SessionAddress sa : sessionWorkers.keySet()) {
            TCPServerWorker sw = sessionWorkers.get(sa);
            if (sw != null) {
                sw.execute(new Command(CommandType.ShutdownNow));
            }
            SessionCoordinator sc = sessionCoordinators.get(sa);
            if (sc != null) {
                sc.execute(new Command(CommandType.ShutdownNow));
            }
        }
    }

    private void setServerAddress() throws ConfigurationException {
        String configHost = "127.0.0.1";
        if (configuration.getHost() != null && !configuration.getHost().isEmpty()) {
            configHost = configuration.getHost();
        }
        try {
            serverAddress = getSocketAddress(configHost);
        } catch (UnknownHostException ex) {
            String logMsg = "Error configuring the FIXServer process: " + getName();
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { logMsg, ExceptionUtil.getStackTrace(ex) });
            throw new ConfigurationException(logMsg, ex);
        }
        serverPort = configuration.getPort();
    }

    private void setServerParameters() throws ConfigurationException {
        if (configuration.getRxBufferSize() != null) {
            rcvBuffSize = configuration.getRxBufferSize();
        }
        if (configuration.getRestrHostsIPAddresses() != null && !configuration.getRestrHostsIPAddresses().isEmpty()) {
            String restrAddr = configuration.getRestrHostsIPAddresses();
            String[] restrAddrArr = restrAddr.split(",");
            // check if address is dotted
            for (String addr : restrAddrArr) {
                if (addr.indexOf(".") <= 0) {
                    String logMsg = String.format("Restricted address list for TCPServer: %s must contain IP addresses not host names: %s", getName(), restrAddr);
                    LOGGER.severe(logMsg);
                    throw new ConfigurationException(logMsg);
                }
            }
            restrictedHosts = Arrays.asList(restrAddrArr);
        }
    }

    private SocketAddress getSocketAddress(String hostName) throws UnknownHostException {
        InetAddress address;
        if (isDottedAddress(hostName)) {
            address = InetAddress.getByAddress(getDottedAddress(hostName));
        } else {
            address = InetAddress.getByName(hostName);
        }
        return new InetSocketAddress(address, configuration.getPort());
    }

    private byte[] getDottedAddress(String host) {
        byte[] result = new byte[4];
        String[] tokens = host.split("\\.");
        if (tokens.length != 4) {
            String error = "Received IP address is bad [" + host + "].";
            LOGGER.severe(error);
            throw new RuntimeException(error);
        }
        for (int i = 0; i < tokens.length; i++) {
            result[i] = (byte) Integer.parseInt(tokens[i]);
        }

        return result;
    }

    private boolean isDottedAddress(String host) {
        String[] tokens = host.split("\\.");
        boolean allNumbers = true;
        for (String token : tokens) {
            if (!token.matches("[0-9]*")) {
                allNumbers = false;
                break;
            }
        }

        return allNumbers;
    }

    private void validateConnection(Socket clientSocket) throws ConnectionException {
        InetSocketAddress remote = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
        if (remote != null) {
            if (restrictedHosts != null && restrictedHosts.size() > 0) {
                boolean found = false;
                for (String hostIPAddr : restrictedHosts) {
                    if (remote.getAddress().getHostAddress().equals(hostIPAddr)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    String logErr = "The connecting socket address [" + remote.getAddress().getHostAddress() +
                            "] is not in restricted list addresses.";
                    LOGGER.severe(logErr);
                    throw new ConnectionException(logErr);
                }
            }
        } else {
            String logErr = "The connecting client dropped the connection.";
            LOGGER.severe(logErr);
            throw new ConnectionException(logErr);
        }
    }

    private void createConnectionWorker(Socket clientSocket) throws ConfigurationException {
        TCPServerWorker worker = new TCPServerWorker(this, clientSocket);
        worker.initialise();
        worker.start();
        worker.execute(new Command(CommandType.Startup));
        worker.execute(new Command(CommandType.Unblock));
    }

    private void createServerSocket() throws UnrecoverableException, IOException {
        server = getSocket();
        if (rcvBuffSize > 0) {
            server.setReceiveBufferSize(rcvBuffSize);
        }
        server.bind(serverAddress, totalNumConnections);
    }

    private boolean setSessionWorker(SessionAddress address, TCPServerWorker worker) {
        boolean found = false;
        try {
            checkWorkerExistsForAddress(address);
        } catch (ConnectionException ex) {
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(), BaseSeverityType.FATAL, AlertCode.SESSION_DESTROYED, ex.getMessage(), ex)));
            return false;
        }
        if (sessionCoordinators != null && sessionCoordinators.size() > 0) {
            for (SessionAddress confAddress : sessionCoordinators.keySet()) {
                if (address.equals(confAddress)) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            ServerSessionCoordinator coordinator = sessionCoordinators.get(address);
            if (coordinator != null) {
                sessionWorkers.put(address, worker);
            } else {
                String logMsg = "Session coordinator not started for the connection [" + address.getRemoteAddress()
                        + ":" + address.getLocalAddress() + "]. Shutting down connection.";
                LOGGER.severe(logMsg);
                eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(), BaseSeverityType.FATAL, AlertCode.SESSION_DESTROYED, logMsg, null)));
                return false;
            }
        } else {
            String logMsg = "Could not find a session configured for the connection [" + address.getRemoteAddress() +
                    ":" + address.getLocalAddress() + "]. Shutting down connection.";
            LOGGER.severe(logMsg);
            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.TCPServerWorker.toString(), BaseSeverityType.FATAL, AlertCode.SESSION_DESTROYED, logMsg, null)));
            return false;
        }
        
        return true;
    }

    private void checkWorkerExistsForAddress(SessionAddress address) throws ConnectionException {
        if (sessionWorkers.size() > 0) {
            if (sessionWorkers.get(address) != null) {
                String logErr = "Only one connection is allowed for the address [" + address.toString()
                        + "]. This connection will be shutdown.";
                LOGGER.severe(logErr);
                throw new ConnectionException(logErr);
            }
        }
    }

    private boolean checkSSLEnabled(SSLInfo sslData) {
        boolean result = false;
        if (sslData != null && sslData.getKeyStoreLoc() != null && !sslData.getKeyStoreLoc().isEmpty()) {
            result = true;
        }

        return result;
    }

    private ServerSocket getSocket() throws UnrecoverableException {
        ServerSocket tcpSocket;
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
                SSLServerSocketFactory factory = sslCtx.getServerSocketFactory();
                tcpSocket = factory.createServerSocket();
                if (configuration.getSslData().isUseCliAuth() != null && configuration.getSslData().isUseCliAuth()) {
                    ((SSLServerSocket) tcpSocket).setWantClientAuth(true);
                } else {
                    ((SSLServerSocket) tcpSocket).setWantClientAuth(false);
                }
            } catch (UnrecoverableKeyException ex) {
                String logMsg = "Unable to retrieve the key from keystore.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] {logMsg, ExceptionUtil.getStackTrace(ex)});
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.TCPServer.toString(), BaseSeverityType.FATAL, null, logMsg, ex)));
                throw new UnrecoverableException(logMsg, ex);
            } catch (IOException ex) {
                String logMsg = "Could not create an unbound SSL socket.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] {logMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(logMsg, ex);
            } catch (KeyManagementException ex) {
                String logMsg = "Could not use the key from given keystore.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] {logMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(logMsg, ex);
            } catch (KeyStoreException ex) {
                String logMsg = "Could not read the key from given keystore.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] {logMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(logMsg, ex);
            } catch (NoSuchAlgorithmException ex) {
                String logMsg = "The algorith used is not supported.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] {logMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(logMsg, ex);
            }

        } else {
            try {
                tcpSocket = new ServerSocket();
            } catch (ConnectException ex) {
                String logMsg = "Could not create an unbound SSL socket.";
                throw new UnrecoverableException(logMsg, ex);
            } catch (UnknownHostException ex) {
                String logMsg = "Could not create an unbound SSL socket.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] {logMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(logMsg, ex);
            } catch (IOException ex) {
                String logMsg = "Could not create an unbound SSL socket.";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] {logMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(logMsg, ex);
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

    private void startTCPListener() {
        // starting event processor
        tcpListener = new TCPListener(getName());
        tcpListener.start();
        ThreadUtil.blockUntilAlive(tcpListener);
        while (!tcpListener.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void startEventProcessor() {
        eventProcessor.start();
        ThreadUtil.blockUntilAlive(eventProcessor);
        while (eventProcessor.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        addAlertListener(hadesInstance.getEventProcessor());
        addLifeCycleListener(hadesInstance.getEventProcessor());
        addMessageListener(hadesInstance.getEventProcessor());
    }

    private class TCPListener extends Thread {

        private volatile boolean listenerShutdown;
        private volatile boolean listenerBlocked;
        private volatile boolean active;

        private TCPListener(String name) {
            super(name + "_LISTENER");
        }

        @Override
        public void run() {
            LOGGER.log(Level.INFO, "Starting Server listener thread [{0}].", getName());

            while (!listenerShutdown) {
                try {
                    if (!listenerBlocked) {
                        active = true;
                        Socket clientSocket = server.accept();
                        // received a connection
                        validateConnection(clientSocket);
                        createConnectionWorker(clientSocket);
                    } else {
                        active = false;
                        Thread.sleep(1);
                    }
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.INFO, "Transport Server listener {0} interrupted.", getName());
                } catch (ConnectionException ex) {
                    eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.TCPServer.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, "Client connection error.", ex)));
                    execute(new Command(CommandType.DisconnectTransport));
                } catch (IOException ex) {
                    LOGGER.severe("Transport server listener I/O error... shutting down server.");
                    listenerBlocked = true;
                    eventProcessor.onAlertEvent(new AlertEvent(this,
                            Alert.createAlert(getName(), ComponentType.TCPServer.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_ABORT, "I/O error.", ex)));
                    eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                            LifeCycleType.SERVER_TCP_CONN.name(),
                            LifeCycleCode.LISTENER_SHUTDOWN.name()));
                    execute(new Command(CommandType.ShutdownNow));
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Unrecoverable unexpected error : {0}", ExceptionUtil.getStackTrace(ex));
                    listenerBlocked = true;
                    eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.TCPServer.toString(),
                            BaseSeverityType.FATAL, AlertCode.TRANSP_ABORT, "Unexpected error.", ex)));
                    eventProcessor.onLifeCycleEvent(new LifeCycleEvent(this,
                            LifeCycleType.SERVER_TCP_CONN.name(),
                            LifeCycleCode.LISTENER_SHUTDOWN.name()));
                    execute(new Command(CommandType.ShutdownNow));
                }
            }
            active = false;

            LOGGER.log(Level.INFO, "Server listener thread [{0}] shutdown.", getName());
        }

        public void shutdown() {
            listenerShutdown = true;
        }

        public void block() {
            listenerBlocked = true;
        }

        public void unblock() {
            listenerBlocked = false;
        }

        public boolean isActive() {
            return active;
        }
    }
}
