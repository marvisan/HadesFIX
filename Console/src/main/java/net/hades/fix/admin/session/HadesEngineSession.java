/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * HadesEngineSession.java
 *
 * $Id: HadesEngineSession.java,v 1.8 2011-04-01 05:04:23 vrotaru Exp $
 */
package net.hades.fix.admin.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.jmxmp.JMXMPConnector;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import net.hades.fix.admin.session.security.HadesClientProvider;
import net.hades.fix.admin.session.security.UsernamePasswordCallbackHandler;
import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.security.PasswordBank;

/**
 * Session connection to the Hades FIX engine MBean server.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 08/05/2010
 */
public final class HadesEngineSession {

    private static final Logger LOGGER = Logger.getLogger(HadesEngineSession.class.getName());

    public static final String HADES_ENGINE_MBEAN_NAME = "HadesDomain:engine=HadesFIXEngineMBean";

    private static final String JMX_USE_SSL_ENV                 = "hadesfix.remotejmx.usessl";
    private static final String JMX_SSL_KEYSTORE_ENV            = "hadesfix.remotejmx.ssl.keystore";
    private static final String JMX_SSL_KEYSTORE_PASSWD_ENV     = "hadesfix.remotejmx.ssl.keystore.passwd";
    private static final String JMX_USE_SSL_CLI_AUTH_ENV        = "hadesfix.remotejmx.ssl.cliauth";
    private static final String JMX_SSL_TRUSTSTORE_ENV          = "hadesfix.remotejmx.ssl.truststore";
    private static final String JMX_SSL_TRUSTSTORE_PASSWD_ENV   = "hadesfix.remotejmx.ssl.truststore.passwd";
    private static final String JMX_USE_AUTH_ENV                = "hadesfix.remotejmx.useauth";
    private static final String JMX_USERNAME_ENV                = "hadesfix.remotejmx.username";
    private static final String JMX_HOST_ENV                    = "hadesfix.remotejmx.host";
    private static final String JMX_PORT_ENV                    = "hadesfix.remotejmx.port";

    private SessionData sessionData;
    private Map<String, Object> env = new HashMap<String, Object>();
    private JMXMPConnector connector;
    private JMXServiceURL url;
    private MBeanServerConnection mbsc;

    public HadesEngineSession() throws SessionException {
        setupSessionData();
        connect();
    }

    public HadesEngineSession(SessionData sessionData) throws SessionException {
        validateSessionData(sessionData);
        this.sessionData = sessionData;
    }

    public void connect() throws SessionException {
        try {
            if (sessionData.isUseAuth()) {
                env.put("jmx.remote.profiles", HadesClientProvider.REMOTE_PROFILE_SASL);
                env.put("jmx.remote.sasl.callback.handler", new UsernamePasswordCallbackHandler(sessionData.getUsername(), sessionData.getPassword()));
                Security.addProvider(new HadesClientProvider());
            }
            if (sessionData.isUseSSL()) {
                setupTLS(env);
            }
            url = new JMXServiceURL("jmxmp", sessionData.getRemoteHost(), Integer.parseInt(sessionData.getRemotePort()));
            connector = new JMXMPConnector(url);
            connector.connect(env);
            mbsc = connector.getMBeanServerConnection();
        } catch (MalformedURLException ex) {
            String logMsg = "JMX url string [" + url.toString() + "] is in bad format.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { logMsg, ExceptionUtil.getStackTrace(ex) });
            throw new SessionException(logMsg, ex);
        } catch (IOException ex) {
            String logMsg = "Error connecting to url [" + url.toString() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { logMsg, ExceptionUtil.getStackTrace(ex) });
            throw new SessionException(logMsg, ex);
        }
    }
    
    public void disconnect() throws SessionException {
        try {
            connector.close();
        } catch (IOException ex) {
            String logMsg = "Error closing session for url [" + url.toString() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { logMsg, ExceptionUtil.getStackTrace(ex) });
            throw new SessionException(logMsg, ex);
        }
    }

    public String getHadesInstanceName() throws SessionException {
        String name = null;
        ObjectName openMBeanObjectName;
        try {
            openMBeanObjectName = new ObjectName(HadesEngineSession.HADES_ENGINE_MBEAN_NAME);
            name = (String) mbsc.getAttribute(openMBeanObjectName, "name");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error connecting to MBean server. Error was : {0}", ExceptionUtil.getStackTrace(ex));
            throw new SessionException("Could not retrieve Hades instance name. Error was : " + ex.getMessage(), ex);
        }

        return name;
    }

    public String getHadesInstanceDescr() throws SessionException {
        String description = null;
        ObjectName openMBeanObjectName;
        try {
            openMBeanObjectName = new ObjectName(HadesEngineSession.HADES_ENGINE_MBEAN_NAME);
            description = (String) mbsc.getAttribute(openMBeanObjectName, "description");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error connecting to MBean server. Error was : {0}", ExceptionUtil.getStackTrace(ex));
            throw new SessionException("Could not retrieve Hades instance name. Error was : " + ex.getMessage(), ex);
        }

        return description;
    }

    public MBeanServerConnection getMBeanServerConnection() {
        return mbsc;
    }

    public JMXMPConnector getConnector() {
        return connector;
    }

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private void setupSessionData() throws SessionException {
        sessionData = new SessionData();
        String useSSL = System.getProperty(JMX_USE_SSL_ENV, null);
        if (useSSL != null && !useSSL.trim().isEmpty()) {
            if (useSSL.equalsIgnoreCase("true") || useSSL.equalsIgnoreCase("yes")) {
                sessionData.setUseSSL(true);
            }
            if (sessionData.isUseSSL()) {
                String keystoreFile = System.getProperty(JMX_SSL_KEYSTORE_ENV, null);
                String keystorePasswd = System.getProperty(JMX_SSL_KEYSTORE_PASSWD_ENV, null);
                if (keystoreFile == null || keystoreFile.isEmpty() || keystorePasswd == null || keystorePasswd.isEmpty()) {
                    throw new SessionException("Management conector is configured to use SSL but no "
                            + "keystore file or keystore password have been provided. Please set '" + JMX_SSL_KEYSTORE_ENV + "'"
                            + "and '" + JMX_SSL_KEYSTORE_PASSWD_ENV + "' environment system parameters.");
                }
                sessionData.setSslKeystore(keystoreFile);
                char[] passwd = PasswordBank.getInstance().getEntryValue(keystorePasswd);
                if (passwd == null || passwd.length == 0) {
                    throw new SessionException("There is no password set in the PasswordBank for entry [" + keystorePasswd + "].");
                }
                sessionData.setSslKeystorePasswd(passwd);
            }
        }
        String useSSLCliAuth = System.getProperty(JMX_USE_SSL_CLI_AUTH_ENV, null);
        if (useSSLCliAuth != null && !useSSLCliAuth.trim().isEmpty()) {
            if (useSSLCliAuth.equalsIgnoreCase("true") || useSSLCliAuth.equalsIgnoreCase("yes")) {
                sessionData.setUseSSLCliAuth(true);
            }
        }
        String truststoreFile = System.getProperty(JMX_SSL_TRUSTSTORE_ENV, null);
        String truststorePasswd = System.getProperty(JMX_SSL_TRUSTSTORE_PASSWD_ENV, null);
        if (sessionData.isUseSSLCliAuth()) {
            if (truststoreFile == null || truststoreFile.isEmpty() || truststorePasswd == null || truststorePasswd.isEmpty()) {
                throw new SessionException("Management conector is configured to use SSL but no "
                        + "keystore file or keystore password have been provided. Please set '" + JMX_SSL_TRUSTSTORE_ENV + "'"
                        + "and '" + JMX_SSL_TRUSTSTORE_PASSWD_ENV + "' environment system parameters.");
            }
        }
        if (truststoreFile != null && !truststoreFile.trim().isEmpty() && truststorePasswd != null && !truststorePasswd.trim().isEmpty()) {   
            sessionData.setSslTruststore(truststoreFile);
            char[] passwd = PasswordBank.getInstance().getEntryValue(truststorePasswd);
            if (passwd == null || passwd.length == 0) {
                throw new SessionException("There is no password set in the PasswordBank for entry [" + truststorePasswd + "].");
            }
            sessionData.setSslTruststorePasswd(passwd);
        }
        String useAuth = System.getProperty(JMX_USE_AUTH_ENV, null);
        if (useAuth != null && !useAuth.trim().isEmpty()) {
            if (useAuth.equalsIgnoreCase("true") || useAuth.equalsIgnoreCase("yes")) {
                sessionData.setUseAuth(true);
            }
        }
        if (sessionData.isUseAuth()) {
            String username = System.getProperty(JMX_USERNAME_ENV, null);
            if (username == null || username.isEmpty()) {
                throw new SessionException("Management conector is configured to use authentication but no "
                        + "user name has been provided. Please set '" + JMX_USERNAME_ENV + "' environment system parameter.");
            }
            sessionData.setUsername(username);
            char[] passwd = PasswordBank.getInstance().getEntryValue(username);
                if (passwd == null || passwd.length == 0) {
                    throw new SessionException("There is no password set in the PasswordBank for entry [" + username + "].");
                }
            sessionData.setPassword(passwd);
        }
        String host = System.getProperty(JMX_HOST_ENV, null);
        if (host != null && !host.trim().isEmpty()) {
            sessionData.setRemoteHost(host);
        } else {
            throw new SessionException("JMX host name cannot be empty. Please set '" + JMX_HOST_ENV + "' environment system parameter.");
        }
        String port = System.getProperty(JMX_PORT_ENV, null);
        if (port != null && !port.trim().isEmpty()) {
            sessionData.setRemotePort(port);
        } else {
            throw new SessionException("JMX host port cannot be empty. Please set '" + JMX_PORT_ENV + "' environment system parameter.");
        }
    }

    private void validateSessionData(SessionData sessionData) throws SessionException {
        if (sessionData == null) {
            throw new IllegalArgumentException("Session data object cannot be null.");
        }
        if (sessionData.getRemoteHost() == null || sessionData.getRemoteHost().trim().isEmpty()) {
            throw new SessionException("JMX host name cannot be empty.");
        }
        if (sessionData.getRemotePort() == null || sessionData.getRemotePort().trim().isEmpty()) {
            throw new SessionException("JMX host port cannot be empty.");
        }
        if (sessionData.isUseAuth()) {
            if (sessionData.getUsername() == null || sessionData.getUsername().isEmpty()) {
                throw new SessionException("Management conector is configured to use authentication but no "
                        + "user name or password have been provided.");
            }
        }
        if (sessionData.isUseSSL()) {
            if (sessionData.getSslTruststore() == null || sessionData.getSslTruststore().isEmpty() ||
                sessionData.getSslTruststorePasswd() == null || sessionData.getSslTruststorePasswd().length == 0) {
                throw new SessionException("Management connector is configured to use SSL but no "
                        + "truststore file or truststore password have been provided.");
            }
        }
        if (sessionData.isUseSSLCliAuth()) {
            if (sessionData.getSslKeystore() == null || sessionData.getSslKeystore().isEmpty() ||
                sessionData.getSslKeystorePasswd() == null || sessionData.getSslKeystorePasswd().length == 0) {
                throw new SessionException("Management connector is configured to use SSL client authentication but no "
                        + "keystore file or keystore password have been provided.");
            }
        }
    }

    private void setupTLS(Map<String, Object> env) throws SessionException {
        try {
            KeyManagerFactory kmf = null;
            if (sessionData.isUseSSLCliAuth()) {
                // overrides the HADESFIX authentication
                kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                KeyStore ks = readKeystore(sessionData.getSslKeystore(), sessionData.getSslKeystorePasswd());
                kmf.init(ks, sessionData.getSslKeystorePasswd());
                env.put("jmx.remote.tls.need.client.authentication", Boolean.TRUE.toString());
            } else {
                env.put("jmx.remote.tls.need.client.authentication", Boolean.FALSE.toString());
            }
            if (sessionData.isUseAuth()) {
                env.put("jmx.remote.profiles", HadesClientProvider.REMOTE_PROFILE_SASL_WITH_SSL);
            } else {
                if (sessionData.isUseSSLCliAuth()) {
                    env.put("jmx.remote.profiles", HadesClientProvider.REMOTE_PROFILE_SSL);
                }
            }
            TrustManagerFactory tmf = null;
            KeyStore ts = null;
            if (sessionData.getSslTruststore() != null && !sessionData.getSslTruststore().trim().isEmpty() &&
                sessionData.getSslTruststorePasswd() != null && sessionData.getSslTruststorePasswd().length > 0) {
                tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                ts = readKeystore(sessionData.getSslTruststore(), sessionData.getSslTruststorePasswd());
            }
            if (ts != null) {
                tmf.init(ts);
            }
            SSLContext sslCtx = SSLContext.getInstance("TLS");
            sslCtx.init(kmf != null ? kmf.getKeyManagers() : null, tmf != null ? tmf.getTrustManagers() : null, null);
            SSLSocketFactory ssf = sslCtx.getSocketFactory();

            env.put("jmx.remote.tls.socket.factory", ssf);
            env.put("jmx.remote.tls.enabled.protocols", "TLSv1");
        } catch (UnrecoverableKeyException ex) {
            String errMsg = "Unable to retrieve the key from keystore.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });
            throw new SessionException(errMsg, ex);
        } catch (KeyManagementException ex) {
            String errMsg = "Could not use the key from given keystore.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });
            throw new SessionException(errMsg, ex);
        } catch (KeyStoreException ex) {
            String errMsg = "Could not read the key from given keystore.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });
            throw new SessionException(errMsg, ex);
        } catch (NoSuchAlgorithmException ex) {
            String errMsg = "The algorith used is not supported.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });
            throw new SessionException(errMsg, ex);
        }
    }

    private KeyStore readKeystore(String keyStoreFileName, char[] keyStorePasswd) throws SessionException {
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException ex) {
            String errMsg = "Could not create a KeyStore of default type.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new SessionException(errMsg, ex);
        }

        FileInputStream fis = null;
        try {
            File keystoreFile = new File(keyStoreFileName);
            if (!keystoreFile.exists()) {
                throw new SessionException("Could not find the keystore file [" + keyStoreFileName + "].");
            }
            fis = new FileInputStream(keystoreFile);
            ks.load(fis, keyStorePasswd);
        } catch (IOException ex) {
            String errMsg = "Could not load the keystore file [" + keyStoreFileName + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new SessionException(errMsg, ex);
        } catch (NoSuchAlgorithmException ex) {
            String errMsg = "Could not create a keystore for the given algorithm.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new SessionException(errMsg, ex);
        } catch (CertificateException ex) {
            String errMsg = "Could not load the certificate form keystore [" + keyStoreFileName + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new SessionException(errMsg, ex);
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

    // </editor-fold>
}
