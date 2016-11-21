/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.transport;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.ManagedTask;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.security.PasswordBank;
import net.hades.fix.engine.config.Configurator;
import net.hades.fix.engine.config.model.BackupConnectionInfo;
import net.hades.fix.engine.config.model.ClientTcpConnectionInfo;
import net.hades.fix.engine.config.model.SSLInfo;
import net.hades.fix.engine.process.protocol.UnrecoverableException;
import net.hades.fix.engine.process.TaskStartException;
import net.hades.fix.engine.process.session.ClientSessionCoordinator;

/**
 * The HadesFIX client TCP process that connects to a remote FIX engine host.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public final class TcpClient implements ManagedTask {

    private static final Logger Log = Logger.getLogger(TcpClient.class.getName());

    private static final String COMPONENT_NAME = "TCPCLI";

    private static final int DEFAULT_SO_LINGER = -1;
    private static final int DEFAULT_TOTAL_NUM_OF_RETRIES = 3;
    private static final int DEFAULT_RETRY_SECONDS_TO_WAIT = 3;
    private static final int DEFAULT_TIMEOUT_MILLIS = 5000; // 5 seconds wait
    private static final int DEFAULT_TIMEOUT_SECS = 30;
    private static final int DEFAULT_SLEEP_MILLIS = 5;

    private final ClientSessionCoordinator coordinator;
    private final ClientTcpConnectionInfo configuration;

    private volatile boolean shutdown;
    private volatile TaskStatus status;
    private volatile boolean connected;

    private boolean hasSSL;
    private SSLContext sslCtx;
    private Socket socket;
    private final String id;
    private Duration timeout;

    private TCPConnections connections;
    private ConnectionData currentConnData;

    public TcpClient(ClientSessionCoordinator coordinator, ClientTcpConnectionInfo configuration) {
        this.coordinator = coordinator;
        this.configuration = configuration;
	id = COMPONENT_NAME + "_" + configuration.getHost() + ":" + String.valueOf(configuration.getPort());
	timeout = Duration.ofSeconds(DEFAULT_TIMEOUT_SECS, 1);
        status = TaskStatus.New;
    }

    @Override
    public ExecutionResult call() throws Exception {
	Log.log(Level.INFO, "Running Tcp Client thread [{0}].", id);
	hasSSL = checkSSLEnabled(configuration.getSslData());
	connections = extractConnections();
	currentConnData = connections.getCurrentConnection();
	status = TaskStatus.Running;
	while (!shutdown) {
	    try {
		if (!connected) {
		    try {
			connect(currentConnData);
		    } catch (ConnectionException ex) {
			// we will retry if configured
			Log.log(Level.SEVERE, "Connection error : {0}", ex);
			if (retryCycleOrQuit()) {
			    connected = false;
			}
		    } catch (TaskStartException ex) {
			Log.log(Level.SEVERE, "Error starting engine : {0}", ex);
			status = TaskStatus.Error;
			return new ExecutionResult(status, ex);
		    }
		}
	    } catch (Exception ex) {
		Log.log(Level.SEVERE, "Unexpected error : {0}", ex);
		status = TaskStatus.Error;
		return new ExecutionResult(status, ex);
	    }
	}
	status = TaskStatus.Completed;
	Log.log(Level.INFO, "Tcp Client thread [{0}] terminated.", id);
	return new ExecutionResult(status);
    }

    @Override
    public String getId() {
	return id;
    }

    @Override
    public void shutdown() {
	shutdownImmediate();
    }

    @Override
    public void shutdownImmediate() {
	Log.log(Level.INFO, "Closing socket streams for Tcp Client [{0}].", id);
	if (socket != null) {
	    while (!socket.isClosed()) {
		try {
		    socket.close();
		    socket = null;
		} catch (IOException ex) {
		    Log.log(Level.SEVERE, "Could not close Tcp Client socket", ex);
		    break;
		}
		try {
		    Thread.sleep(DEFAULT_SLEEP_MILLIS);
		} catch (InterruptedException ex) {
		    Log.log(Level.WARNING, "Thread [{0}] interrupted", id);
		    break;
		}
		timeout = timeout.minusMillis(DEFAULT_SLEEP_MILLIS);
		if (timeout.isNegative()) {
		    Log.info(String.format("Tcp Client [%s] timedout shutdownImmediate() : [%s]", id, status));
		    break;
		}
	    }
	}
        shutdown = true;
        Log.log(Level.INFO, "Tcp Client [{0}] socket streams closed.", id);
    }

    @Override
    public TaskStatus getStatus() {
	return status;
    }

    //-------------------------------------------------------------------------------------------------------
    
    private void connect(ConnectionData connData) throws UnrecoverableException, ConnectionException, TaskStartException {
        if (connData == null) {
            return;
        }

        if (socket != null && socket.isConnected()) {
            return;
        }

        Log.log(Level.INFO, "Attempting to connect to host [{0}] port [{1}].", new Object[]{connData.host, connData.port});

	int socketTimeout = (configuration.getSocketTimeout() != null ? configuration.getSocketTimeout() : DEFAULT_TIMEOUT_MILLIS);
	socket = getSocket(connData.host, connData.port);
	setTcpNodelay();
	setSoLinger();
	setSoRcvbuf();
	setSoSndbuf();
	setSendKeepAlive();
	setSocketTimeout(socketTimeout);
	Log.log(Level.INFO, "Connected to host [{0}] port [{1}].", new Object[]{connData.host, connData.port});
	coordinator.startStreamHandlers(socket);
    }

    private void setTcpNodelay() {
        if (configuration.getTcpNodelay() != null && configuration.getTcpNodelay()) {
            try {
                socket.setTcpNoDelay(true);
            } catch (SocketException ex) {
                String logMsg = "TCP_NODELAY cannot be set on this socket implementation.";
                Log.warning(logMsg);
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
            Log.warning(logMsg);
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
                Log.warning(logMsg);
            }
        }
    }

    private void setSoRcvbuf() {
        if (configuration.getSocketRcvbuf() != null && configuration.getSocketRcvbuf() > 0) {
            try {
                socket.setReceiveBufferSize(configuration.getSocketRcvbuf());
            } catch (SocketException ex) {
                String logMsg = "SO_RCVBUF cannot be set on this socket implementation.";
                Log.warning(logMsg);
            }
        }
    }

    private void setSoSndbuf() {
        if (configuration.getSocketSndbuf() != null && configuration.getSocketSndbuf() > 0) {
            try {
                socket.setReceiveBufferSize(configuration.getSocketSndbuf());
            } catch (SocketException ex) {
                String logMsg = "SO_SNDBUF cannot be set on this socket implementation.";
                Log.warning(logMsg);
            }
        }
    }

    private void setSocketTimeout(int timeout) {
        try {
            socket.setSoTimeout(timeout);
        } catch (SocketException ex) {
            String logMsg = "Initial socket timeout cannot be set on this socket implementation.";
            Log.warning(logMsg);
        }
    }

    private boolean retryCycleOrQuit() {
        currentConnData = connections.getCurrentConnection();
        return currentConnData != null;
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
                SSLSocketFactory factory = sslCtx.getSocketFactory();
                tcpSocket = factory.createSocket(host, port);
            } catch (UnrecoverableKeyException ex) {
                String errMsg = "Unable to retrieve the key from keystore.";
                throw new UnrecoverableException(errMsg, ex);
            } catch (IOException ex) {
                String errMsg = "Could not create a SSL socket for [" + host + ":" + port + "].";
                throw new UnrecoverableException(errMsg, ex);
            } catch (KeyManagementException ex) {
                String errMsg = "Could not use the key from given keystore.";
                Log.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(errMsg, ex);
            } catch (KeyStoreException ex) {
                String errMsg = "Could not read the key from given keystore.";
                Log.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(errMsg, ex);
            } catch (NoSuchAlgorithmException ex) {
                String errMsg = "The algorithm used is not supported.";
                Log.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
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
                Log.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                throw new UnrecoverableException(errMsg, ex);
            } catch (IOException ex) {
                String errMsg = "Could not create a socket for [" + host + ":" + port + "].";
                Log.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
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
            Log.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
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
            Log.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new UnrecoverableException(errMsg, ex);
        } catch (NoSuchAlgorithmException ex) {
            String errMsg = "Could not create a keystore for the given algorithm.";
            Log.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new UnrecoverableException(errMsg, ex);
        } catch (CertificateException ex) {
            String errMsg = "Could not load the certificate form keystore [" + keyStoreFileName + "].";
            Log.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new UnrecoverableException(errMsg, ex);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Log.log(Level.SEVERE, "Could not close the file handler [{0}].", keyStoreFileName);
                }
            }
        }

        return ks;
    }

    // INNER CLASSES

    private class ConnectionData {

        private String host;
        private Integer port;

        public ConnectionData(String host, Integer port) {
            this.host = host;
            this.port = port;
        }
    }

    private class TCPConnections {

        private final int numOfRetries;
        private final int retrySecondsToWait;
        private int currNumOfRetries;
        private final List<ConnectionData> connections;
        private int currentIdx;

        public TCPConnections() {
            connections = new ArrayList<>();
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
                    Log.log(Level.SEVERE, "Thread [{0}]interrupted interrupted : ",id);
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
