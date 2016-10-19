/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.transport;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import net.hades.fix.engine.process.ManagedTask;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.security.PasswordBank;
import net.hades.fix.engine.config.Configurator;
import net.hades.fix.engine.config.model.SSLInfo;
import net.hades.fix.engine.config.model.ServerTcpConnectionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.ConnectionException;
import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.session.ServerSessionCoordinator;
import net.hades.fix.engine.process.session.SessionCoordinator;

/**
 * TCP/IP server implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class TcpServer implements ManagedTask {

    private static final Logger LOGGER = Logger.getLogger(TcpServer.class.getName());

    private static final String COMPONENT_NAME = "TCPSRV";
    private static final int DEFAULT_SO_LINGER = -1;
    private static final int DEFAULT_SO_TIMEOUT = 0; // infinity
    private static final int DEFAULT_TIMEOUT_SECS = 30;

    private final ServerTcpConnectionInfo configuration;
    private final SessionCoordinator coordinator;

    private volatile boolean shutdown;
    private volatile TaskStatus status;
    private boolean hasSSL;
    private SSLContext sslCtx;
    private final String id;
    private final Duration timeout;

    private List<String> restrictedHosts;

    private ServerSocket server;
    private SocketAddress serverAddress;
    private int totalNumConnections;
    private int rcvBuffSize;

    public TcpServer(ServerSessionCoordinator coordinator, ServerTcpConnectionInfo configuration) throws ConfigurationException {
	this.configuration = configuration;
	this.coordinator = coordinator;
	totalNumConnections = 1;
	id = COMPONENT_NAME + "_" + configuration.getHost() + ":" + String.valueOf(configuration.getPort());
	timeout = Duration.ofSeconds(DEFAULT_TIMEOUT_SECS, 1);
	setServerAddress();
	setServerParameters();
	status = TaskStatus.New;
    }

    @Override
    public ExecutionResult call() throws Exception {
	LOGGER.log(Level.INFO, "Tcp Server thread [{0}] running.", id);
	hasSSL = checkSSLEnabled(configuration.getSslData());
	status = TaskStatus.Running;
	try {
	    createServerSocket();
	} catch (IOException | UnrecoverableException ex) {
	    status = TaskStatus.Error;
	    coordinator.onAlertEvent(new AlertEvent(this, 
		    Alert.createAlert(id, TcpServer.class.getSimpleName(), BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, ex.getMessage(), ex)));
	    return new ExecutionResult(TaskStatus.Error, ex);
	}
	Socket clientSocket;
	while (!shutdown) {
	    try {
		clientSocket = server.accept();
	    } catch (SocketException ex) {
		status = TaskStatus.Completed;
		coordinator.onAlertEvent(new AlertEvent(this, 
			Alert.createAlert(id, TcpServer.class.getSimpleName(), BaseSeverityType.INFO, AlertCode.TRANSP_DISCONNECT, ex.getMessage(), ex)));
		return new ExecutionResult(TaskStatus.Completed, ex);
	    }
	    if (clientSocket == null) {
		continue;
	    }

	    totalNumConnections++;
	    if (totalNumConnections > 1) {
		String msg = String.format("Only one connection allowed for a FIX server. Attempt connect from : %s", clientSocket.getRemoteSocketAddress().toString());
		coordinator.onAlertEvent(new AlertEvent(this, 
			Alert.createAlert(id, TcpServer.class.getSimpleName(), BaseSeverityType.WARNING, AlertCode.TRANSP_DISCONNECT, null, null)));
		continue;
	    }
	    // received a connection
	    try {
		validateConnection(clientSocket);
	    } catch (ConnectionException ex) {
		status = TaskStatus.Error;
		coordinator.onAlertEvent(new AlertEvent(this, 
			Alert.createAlert(id, TcpServer.class.getSimpleName(), BaseSeverityType.FATAL, AlertCode.TRANSP_DISCONNECT, ex.getMessage(), ex)));
		return new ExecutionResult(TaskStatus.Error, ex);
	    }
	    configureSocket(clientSocket);
	    coordinator.startStreamHandlers(clientSocket);
	}
	status = TaskStatus.Completed;
	return new ExecutionResult(status);
    }

    @Override
    public String toString() {
	return "Tcp Server : " + serverAddress.toString() + "].";
    }

    @Override
    public void shutdown() {
	shutdownImmediate();
    }

    @Override
    public void shutdownImmediate() {
	if (server != null) {
	    while (!server.isClosed()) {
		try {
		    server.close();
		} catch (IOException ex) {
		    LOGGER.log(Level.WARNING, "Could not close server socket", ex);
		    break;
		}
	    }
	}
	shutdown = true;
    }

    @Override
    public TaskStatus getStatus() {
	return status;
    }

    @Override
    public String getId() {
	return id;
    }

    //------------------------------------------------------------------------------------------------------------------------
    
    private void setServerAddress() throws ConfigurationException {
	String configHost = "127.0.0.1";
	if (configuration.getHost() != null && !configuration.getHost().isEmpty()) {
	    configHost = configuration.getHost();
	}
	try {
	    serverAddress = getSocketAddress(configHost);
	} catch (UnknownHostException ex) {
	    String logMsg = "Error configuring the FIXServer process: " + id;
	    throw new ConfigurationException(logMsg, ex);
	}
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
		    String logMsg = String.format("Restricted address list for TCPServer: %s must contain IP addresses not host names: %s", id, restrAddr);
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
		    String logErr = "The connecting socket address [" + remote.getAddress().getHostAddress()
			    + "] is not in restricted list addresses.";
		    throw new ConnectionException(logErr);
		}
	    }
	} else {
	    String logErr = "The connecting client dropped the connection.";
	    throw new ConnectionException(logErr);
	}
    }

    private void createServerSocket() throws UnrecoverableException, IOException {
	server = getSocket();
	if (rcvBuffSize > 0) {
	    server.setReceiveBufferSize(rcvBuffSize);
	}
	server.bind(serverAddress, totalNumConnections);
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
		    KeyStore ts;
		    if (configuration.getSslData().getTrustStoreLoc() != null && !configuration.getSslData().getTrustStoreLoc().isEmpty()) {
			tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			ts = readKeystore(Configurator.getConfigDir() + "/" + configuration.getSslData().getTrustStoreLoc());
			if (ts != null) {
			    tmf.init(ts);
			}
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
		throw new UnrecoverableException(logMsg, ex);
	    } catch (IOException ex) {
		String logMsg = "Could not create an unbound SSL socket.";
		throw new UnrecoverableException(logMsg, ex);
	    } catch (KeyManagementException ex) {
		String logMsg = "Could not use the key from given keystore.";
		throw new UnrecoverableException(logMsg, ex);
	    } catch (KeyStoreException ex) {
		String logMsg = "Could not read the key from given keystore.";
		throw new UnrecoverableException(logMsg, ex);
	    } catch (NoSuchAlgorithmException ex) {
		String logMsg = "The algorithm used is not supported.";
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
		LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{logMsg, ExceptionUtil.getStackTrace(ex)});
		throw new UnrecoverableException(logMsg, ex);
	    } catch (IOException ex) {
		String logMsg = "Could not create an unbound SSL socket.";
		LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{logMsg, ExceptionUtil.getStackTrace(ex)});
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
	    throw new UnrecoverableException(errMsg, ex);
	} catch (NoSuchAlgorithmException ex) {
	    String errMsg = "Could not create a keystore for the given algorithm.";
	    throw new UnrecoverableException(errMsg, ex);
	} catch (CertificateException ex) {
	    String errMsg = "Could not load the certificate form keystore [" + keyStoreFileName + "].";
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

    private void configureSocket(Socket clientSocket) {
	setTcpNodelay(clientSocket);
	setSendKeepAlive(clientSocket);
	setSoLinger(clientSocket);
	setSoRcvbuf(clientSocket);
	setSoSndbuf(clientSocket);
	setSocketTimeout(clientSocket);
    }

    private void setTcpNodelay(Socket clientSocket) {
	boolean tcpNoDelay = false;
	if (configuration.getTcpNodelay() != null) {
	    tcpNoDelay = configuration.getTcpNodelay();
	}
	try {
	    clientSocket.setTcpNoDelay(tcpNoDelay);
	} catch (SocketException ex) {
	    String logMsg = "TCP_NODELAY cannot be set on this socket implementation.";
	    LOGGER.warning(logMsg);
	}
    }

    private void setSendKeepAlive(Socket clientSocket) {
	boolean sendKeepAlive = true;
	if (configuration.getSendKeepAlive() != null) {
	    sendKeepAlive = configuration.getSendKeepAlive();
	}
	try {
	    clientSocket.setKeepAlive(sendKeepAlive);
	} catch (SocketException ex) {
	    String logMsg = "TCP_NODELAY cannot be set on this socket implementation.";
	    LOGGER.warning(logMsg);
	}
    }

    private void setSoLinger(Socket clientSocket) {
	int soLinger = DEFAULT_SO_LINGER;
	if (configuration.getSocketLingerTimeout() != null) {
	    soLinger = configuration.getSocketLingerTimeout();
	}
	if (soLinger > 0) {
	    try {
		clientSocket.setSoLinger(true, soLinger);
	    } catch (SocketException ex) {
		String logMsg = "SO_LINGER cannot be set on this socket implementation.";
		LOGGER.warning(logMsg);
	    }
	}
    }

    private void setSoRcvbuf(Socket clientSocket) {
	if (configuration.getSocketRcvbuf() != null && configuration.getSocketRcvbuf() > 0) {
	    try {
		clientSocket.setReceiveBufferSize(configuration.getSocketRcvbuf());
	    } catch (SocketException ex) {
		String logMsg = "SO_RCVBUF cannot be set on this socket implementation.";
		LOGGER.warning(logMsg);
	    }
	}
    }

    private void setSoSndbuf(Socket clientSocket) {
	if (configuration.getSocketSndbuf() != null && configuration.getSocketSndbuf() > 0) {
	    try {
		clientSocket.setReceiveBufferSize(configuration.getSocketSndbuf());
	    } catch (SocketException ex) {
		String logMsg = "SO_SNDBUF cannot be set on this socket implementation.";
		LOGGER.warning(logMsg);
	    }
	}
    }

    private void setSocketTimeout(Socket clientSocket) {
	int socketTimeout = DEFAULT_SO_TIMEOUT;
	if (configuration.getSocketTimeout() != null) {
	    socketTimeout = configuration.getSocketTimeout();
	}
	try {
	    clientSocket.setSoTimeout(socketTimeout);
	} catch (SocketException ex) {
	    String logMsg = "Initial socket timeout cannot be set on this socket implementation.";
	    LOGGER.warning(logMsg);
	}
    }
}
