/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine;


import javax.management.openmbean.OpenDataException;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.jmxmp.JMXMPConnectorServer;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.security.PasswordBank;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.message.util.format.DateFormatter;
import net.hades.fix.engine.config.ConfigurationValidator;
import net.hades.fix.engine.config.Configurator;
import net.hades.fix.engine.config.model.*;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.ConnectionException;
import net.hades.fix.engine.exception.ProtocolException;
import net.hades.fix.engine.mgmt.HadesFIXEngineMBean;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProcessStatus;
import net.hades.fix.engine.mgmt.security.HadesServerProvider;
import net.hades.fix.engine.model.CounterpartyAddress;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.Reportable;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.EventProcessor;
import net.hades.fix.engine.process.event.GenericEventProcessor;
import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;
import net.hades.fix.engine.process.session.ClientSessionCoordinator;
import net.hades.fix.engine.process.session.ServerSessionCoordinator;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.engine.process.transport.TCPServerOld;
import net.hades.fix.engine.scheduler.Scheduler;

/**
 * Main starting class of the Hades FIX engine.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class HadesInstance implements Reportable {

    private static final Logger LOGGER = Logger.getLogger(HadesInstance.class.getName());

    private static final int DEFAULT_MANAGEMENT_PORT = 33333;
    private static final String HADES_INSTANCE_EVENT_PROCESSOR = "HADES_INSTANCE";

    private static MBeanServer server;

    private final HadesInstanceInfo configuration;
    private ConcurrentMap<SessionAddress, SessionCoordinator> sessions;
    private ConcurrentMap<String, TCPServerOld> tcpServers;

    private Scheduler scheduler;

    private static volatile boolean shutdown;

    private EventProcessor eventProcessor;

    private static HadesFIXEngineMBean openMBean = null;
    private static ObjectName openMBeanObjectName = null;

    public HadesInstance() throws ConfigurationException {
        LOGGER.log(Level.INFO, "HadesFIX engine : {0}. Welcome !", DateFormatter.getFixTSFormat().format(new Date()));

        configuration = Configurator.readConfiguration();
        ConfigurationValidator.validateConfiguration(configuration);
    }

    // MAIN
    ///////////////////////

    public static void main(String[] args) {
        try {
            HadesInstance engine = new HadesInstance();
            engine.initialise();
            engine.startEngine();
            server = MBeanServerFactory.createMBeanServer("HadesFIXDomain");
            openMBean = new HadesFIXEngineMBean(engine);
            openMBeanObjectName = new ObjectName("HadesDomain", "engine", "HadesFIXEngineMBean");
            server.registerMBean(openMBean, openMBeanObjectName);
            engine.setupRemoteJMXServer(server);

            engine.eventProcessor.onAlertEvent(new AlertEvent(engine,
                    Alert.createAlert(engine.getConfiguration().getName(),
                            ComponentType.HadesFIXEngine.toString(),
                            BaseSeverityType.INFO,
                            AlertCode.COMPONENT_STARTED,
                            "HadesFIX engine successfully started", null)));

            engine.waitToExit();
        } catch (ConnectionException ex) {
            LOGGER.log(Level.SEVERE, "FATAL : Could not start JMXMP server. Error was : {0}", ExceptionUtil.getStackTrace(ex));
        } catch (InstanceAlreadyExistsException ex) {
            LOGGER.log(Level.SEVERE, "FATAL : An instance of the Management server exists already. Error was : {0}", ExceptionUtil.getStackTrace(ex));
        } catch (MBeanRegistrationException ex) {
            LOGGER.log(Level.SEVERE, "FATAL : Could not register the Mgmt object. Error was : {0}", ExceptionUtil.getStackTrace(ex));
        } catch (NotCompliantMBeanException ex) {
            LOGGER.log(Level.SEVERE, "FATAL : Non compliant Mgmt object. Error was : {0}", ExceptionUtil.getStackTrace(ex));
        } catch (MalformedObjectNameException ex) {
            LOGGER.log(Level.SEVERE, "FATAL : Mgmt object name is malformed. Error was : {0}", ExceptionUtil.getStackTrace(ex));
        } catch (OpenDataException ex) {
            LOGGER.log(Level.SEVERE, "FATAL : Please see the engine log for details. Error was : {0}", ExceptionUtil.getStackTrace(ex));
        } catch (ConfigurationException ex) {
            if (ex.getErrors() != null && ex.getErrors().size() > 0) {
                printConfigurationErrors(ex.getErrors());
            } else {
                LOGGER.log(Level.SEVERE, "FATAL : Could not start HadesFIX engine because of configuration errors. Error was : {0}",
                        ExceptionUtil.getStackTrace(ex));
            }
        } finally {
            try {
                if (server != null) {
                    server.unregisterMBean(openMBeanObjectName);
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "FATAL : MBean not found error. Error was : {0}", ExceptionUtil.getStackTrace(ex));
            }
        }

        LOGGER.log(Level.INFO, "HadesFIX engine stopped : {0}", DateFormatter.getFixTSFormat().format(new Date()));
        System.exit(0);
    }

    // ACCESSOR METHODS
    ///////////////////////

    public HadesInstanceInfo getConfiguration() {
        return configuration;
    }

    public static HadesFIXEngineMBean getOpenMBean() {
        return openMBean;
    }

    public EventProcessor getEventProcessor() {
        return eventProcessor;
    }

    public void startEngine() {
        startConfiguredSessions();
        startInstanceScheduledTasks();
    }

    public void initialise() throws ConfigurationException {
        LOGGER.info("HadesFIX engine initialising...");

        sessions = new ConcurrentHashMap<>();
        aggregateEngineConfiguration(getConfiguration());
        startEventProcessor();
        setConfiguredListeners();
        createSessionCoordinators();
        createScheduler();

        LOGGER.info("HadesFIX engine initialised successfully.");
    }

    public void waitToExit() {
        LOGGER.log(Level.INFO, "HadesFIX engine started : {0}", DateFormatter.getFixTSFormat().format(new Date()));

        while (!shutdown) {
            try {
		Thread.sleep(5);
            } catch (InterruptedException ex) {
                String error = "Thread interrupted unexpectedly.";
                LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});

                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.HadesFIXEngine.toString(),
                        BaseSeverityType.WARNING, AlertCode.THREAD_INTERRUPTED, "HadesFIX engine process interrupted", ex)));

                ThreadUtil.sleep(1000);
                shutdown = true;
            }
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

    /**
     * Returns a session coordinator.
     * @param cptyAddr remote counterparty ID
     * @param sessAddr local counterparty ID
     * @return might return null
     */
    public SessionCoordinator getSessionCoordinator(String cptyAddr, String sessAddr) {
        SessionCoordinator sessionCoordinator = null;
        for (Entry<SessionAddress, SessionCoordinator> sessionEntry : sessions.entrySet()) {
            SessionAddress address = sessionEntry.getKey();
            if (cptyAddr.equals(address.getRemoteAddress().getID()) && sessAddr.equals(address.getLocalAddress().getID())) {
                sessionCoordinator = sessionEntry.getValue();
                break;
            }
        }
        return sessionCoordinator;
    }

    public void removeSessionCoordinator(String cptyAddr, String sessAddr) {
        for (Entry<SessionAddress, SessionCoordinator> sessionEntry : sessions.entrySet()) {
            SessionAddress address = sessionEntry.getKey();
            if (cptyAddr.equals(address.getRemoteAddress().getID()) && sessAddr.equals(address.getLocalAddress().getID())) {
                sessions.remove(sessionEntry.getKey());
                break;
            }
        }
    }

    public void startSession(String cptyAddr, String sessAddr) throws ConfigurationException {
        if (!isSessionStopped(cptyAddr, sessAddr)) {
            throw new ConfigurationException("Session [" + cptyAddr + ":" + sessAddr + "] is already running.");
        }

        for (CounterpartyInfo cptyInfo : configuration.getCounterparties()) {
            CounterpartyAddress remoteAddr = new CounterpartyAddress(cptyInfo.getCompID(), cptyInfo.getSubID(), cptyInfo.getLocationID());
            if (cptyAddr.equals(remoteAddr.getID())) {
                for (SessionInfo sessionInfo : cptyInfo.getSessions()) {
                    CounterpartyAddress localAddr = new CounterpartyAddress(sessionInfo.getCompID(), sessionInfo.getSubID(), sessionInfo.getLocationID());
                    if (sessAddr.equals(localAddr.getID())) {
                        SessionAddress address = new SessionAddress(remoteAddr, localAddr);
                        SessionCoordinator coordinator;
                        if (sessionInfo.getDisabled() != null && sessionInfo.getDisabled()) {
                            throw new ConfigurationException("Could not start the session. Session configuration disabled flag is set to true.");
                        }
                        if (sessionInfo instanceof ClientSessionInfo) {
                            coordinator = new ClientSessionCoordinator(this, sessionInfo, cptyInfo, address);
                            coordinator.initialise();
                        } else {
                            coordinator = new ServerSessionCoordinator(this, sessionInfo, cptyInfo, address);
                            coordinator.initialise();
                        }
                        startSessionCoordinator(coordinator);
                        sessions.put(address, coordinator);
                    }
                }
            }
        }
    }

    public void stopSession(String cptyAddr, String sessAddr) throws ProtocolException {
        SessionCoordinator sessionCoordinator = getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            sessionCoordinator.shutdown();
            while (!ProcessStatus.SHUTDOWN.equals(sessionCoordinator.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        } else {
            throw new ProtocolException("Could not found a session with address [" + cptyAddr + ":" + sessAddr + "].");
        }
    }

    public Map<SessionInfo, SessionCoordinator> getConfiguredSessions() {
        Map<SessionInfo, SessionCoordinator> configuredSessions = new HashMap<SessionInfo, SessionCoordinator>();
        for (CounterpartyInfo counterparty : configuration.getCounterparties()) {
            for (SessionInfo configSession : counterparty.getSessions()) {
                configSession.setRemoteID(counterparty.getID());
                SessionCoordinator sessionCoordinator = getSessionCoordinator(counterparty.getID(), configSession.getID());
                configuredSessions.put(configSession, sessionCoordinator);
            }
        }

        return configuredSessions;
    }

    public void shutdownEngine() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
        for (Entry<SessionAddress, SessionCoordinator> sessionEntry : sessions.entrySet()) {
            SessionAddress address = sessionEntry.getKey();
            if (!ProcessStatus.SHUTDOWN.equals(sessionEntry.getValue().getProcessStatus())) {
                sessionEntry.getValue().execute(new Command(CommandType.Shutdown));
                while (!ProcessStatus.SHUTDOWN.equals(sessionEntry.getValue().getProcessStatus())) {
                    if (!ThreadUtil.sleep(1)) {
                        break;
                    }
                }
            }

            LOGGER.log(Level.INFO, "Session [{0}] shut down gracefully.", address.toString());
        }
        sessions.clear();
        for (TCPServerOld tcpServer : tcpServers.values()) {
            tcpServer.execute(new Command(CommandType.Shutdown));
            while (!ProcessStatus.SHUTDOWN.equals(tcpServer.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }
        tcpServers.clear();

        LOGGER.log(Level.INFO, "HadesFIX engine [{0}] shutdown successfully.", configuration.getName());
	shutdown = true;
    }

    public void shutdownImmediateEngine() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
        for (Entry<SessionAddress, SessionCoordinator> sessionEntry : sessions.entrySet()) {
            SessionAddress address = sessionEntry.getKey();
            if (!ProcessStatus.SHUTDOWN.equals(sessionEntry.getValue().getProcessStatus())) {
                sessionEntry.getValue().execute(new Command(CommandType.ShutdownNow));
                while (!ProcessStatus.SHUTDOWN.equals(sessionEntry.getValue().getProcessStatus())) {
                    if (!ThreadUtil.sleep(1)) {
                        break;
                    }
                }
            }

            LOGGER.log(Level.INFO, "Session [{0}] shut down immediate.", address.toString());
        }
        sessions.clear();
        for (TCPServerOld tcpServer : tcpServers.values()) {
            tcpServer.execute(new Command(CommandType.ShutdownNow));
        }
        tcpServers.clear();

        LOGGER.log(Level.INFO, "HadesFIX engine [{0}] shutdown successfully.", configuration.getName());

        shutdown = true;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    private void createSessionCoordinators() throws ConfigurationException {
        if (LOGGER.isLoggable(Level.FINER)) {
            LOGGER.log(Level.FINER, "Creating session coordinators for HadesFIX instance [{0}].", configuration.getName());
        }

        for (CounterpartyInfo cptyInfo : configuration.getCounterparties()) {
            for (SessionInfo sessionInfo : cptyInfo.getSessions()) {
                if (sessionInfo.getDisabled() == null || !sessionInfo.getDisabled()) {
                    createSessionCoordinator(cptyInfo, sessionInfo);
                }
            }
        }
    }

    private void createSessionCoordinator(CounterpartyInfo cptyInfo, SessionInfo sessionInfo) throws ConfigurationException {
        CounterpartyAddress remoteAddr = new CounterpartyAddress(cptyInfo.getCompID(), cptyInfo.getSubID(), cptyInfo.getLocationID());
        CounterpartyAddress localAddr = new CounterpartyAddress(sessionInfo.getCompID(), sessionInfo.getSubID(), sessionInfo.getLocationID());
        SessionAddress address = new SessionAddress(remoteAddr, localAddr);
        SessionCoordinator coordinator;
        if (sessionInfo instanceof ClientSessionInfo) {
            coordinator = new ClientSessionCoordinator(this, sessionInfo, cptyInfo, address);
            coordinator.initialise();
        } else {
            coordinator = new ServerSessionCoordinator(this, sessionInfo, cptyInfo, address);
            coordinator.initialise();
        }
        sessions.put(address, coordinator);
    }

    private boolean isSessionStopped(String cptyAddress, String localAddress) {
        boolean result = true;
        if (sessions != null && !sessions.isEmpty()) {
            for (SessionCoordinator coordinator : sessions.values()) {
                if (cptyAddress.equals(coordinator.getSessionAddress().getRemoteAddress().getID()) &&
                        localAddress.equals(coordinator.getSessionAddress().getLocalAddress().getID())) {
                    if (!ProcessStatus.SHUTDOWN.equals(coordinator.getProcessStatus())) {
                        result = false;
                    }
                    break;
                }
            }
        }

        return result;
    }

    private void startSessionCoordinator(SessionCoordinator session) {
        session.execute(new Command(CommandType.Startup));
    }

    private void setConfiguredListeners() throws ConfigurationException {
        if (configuration.getAlertListeners() != null && configuration.getAlertListeners().length > 0) {
            for (ListenerInfo listener : configuration.getAlertListeners()) {
                if (listener.getClassName() != null) {
                    addAlertListener(createAlertListener(listener.getClassName()));
                }
            }
        }
        if (configuration.getMessageListeners() != null && configuration.getMessageListeners().length > 0) {
            for (ListenerInfo listener : configuration.getMessageListeners()) {
                if (listener.getClassName() != null) {
                    addMessageListener(createMessageListener(listener.getClassName()));
                }
            }
        }
        if (configuration.getLifeCycleListeners() != null && configuration.getLifeCycleListeners().length > 0) {
            for (ListenerInfo listener : configuration.getLifeCycleListeners()) {
                if (listener.getClassName() != null) {
                    addLifeCycleListener(createLifeCycleListener(listener.getClassName()));
                }
            }
        }
    }

    private AlertListener createAlertListener(String implClass) throws ConfigurationException {
        AlertListener alertListener;
        try {
            Class<? extends AlertListener> clazz = Class.forName(implClass.trim()).asSubclass(AlertListener.class);
            alertListener = clazz.newInstance();
        } catch (ClassNotFoundException ex) {
            String errMsg = "AlertListener class [" + implClass + "] was not found in the classpath.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        } catch (InstantiationException ex) {
            String errMsg = "Could not create an instance of AlertListener class [" + implClass + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        } catch (IllegalAccessException ex) {
            String errMsg = "Class accessor error for [" + implClass + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        }
        return alertListener;
    }

    private MessageListener createMessageListener(String implClass) throws ConfigurationException {
        MessageListener messageListener;
        try {
            Class<? extends MessageListener> clazz = Class.forName(implClass.trim()).asSubclass(MessageListener.class);
            messageListener = clazz.newInstance();
        } catch (ClassNotFoundException ex) {
            String errMsg = "MessageListener class [" + implClass + "] was not found in the classpath.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        } catch (InstantiationException ex) {
            String errMsg = "Could not create an instance of MessageListener class [" + implClass + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        } catch (IllegalAccessException ex) {
            String errMsg = "Class accessor error for [" + implClass + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        }
        return messageListener;
    }

    private LifeCycleListener createLifeCycleListener(String implClass) throws ConfigurationException {
        LifeCycleListener lifeCycleListener;
        try {
            Class<? extends LifeCycleListener> clazz = Class.forName(implClass.trim()).asSubclass(LifeCycleListener.class);
            lifeCycleListener = clazz.newInstance();
        } catch (ClassNotFoundException ex) {
            String errMsg = "LifeCycleListener class [" + implClass + "] was not found in the classpath.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        } catch (InstantiationException ex) {
            String errMsg = "Could not create an instance of LifeCycleListener class [" + implClass + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        } catch (IllegalAccessException ex) {
            String errMsg = "Class accessor error for [" + implClass + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        }
        return lifeCycleListener;
    }

    private void aggregateEngineConfiguration(HadesInstanceInfo hadesInstanceInfo) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest("Aggregating handler/secured messages configuration.");
        }

        HandlerDefInfo[] handlerDefs = hadesInstanceInfo.getHandlerDefs();
        SecuredMessageInfo[] securedMessages = hadesInstanceInfo.getSecuredMessages();
        if (handlerDefs != null && handlerDefs.length > 0) {
            aggregateHandlerDefInfo(hadesInstanceInfo);
        }
        if (securedMessages != null && securedMessages.length > 0) {
            aggregateSecuredMessageInfo(hadesInstanceInfo);
        }
    }

    private void aggregateHandlerDefInfo(HadesInstanceInfo hadesInstanceInfo) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Aggregating instance handler configuration for [{0}] handlers.",
                    hadesInstanceInfo.getHandlerDefs().length);
        }

        for (CounterpartyInfo partyInfo : hadesInstanceInfo.getCounterparties()) {
            Map<String, HandlerDefInfo> handlers = new HashMap<>();
            for (HandlerDefInfo handler : hadesInstanceInfo.getHandlerDefs()) {
                handlers.put(handler.getName(), handler);
            }
            if (partyInfo.getHandlerDefs() != null && partyInfo.getHandlerDefs().length > 0) {
                for (HandlerDefInfo handler : partyInfo.getHandlerDefs()) {
                    handlers.put(handler.getName(), handler);
                }
            }
            partyInfo.setHandlerDefs(handlers.values().toArray(new HandlerDefInfo[handlers.size()]));
        }
    }


    private void aggregateSecuredMessageInfo(HadesInstanceInfo hadesInstanceInfo) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Aggregating instance secured messages for [{0}] messages.",
                    hadesInstanceInfo.getSecuredMessages().length);
        }

        for (CounterpartyInfo partyInfo : hadesInstanceInfo.getCounterparties()) {
            Map<String, SecuredMessageInfo> securedMessages = new HashMap<>();
            for (SecuredMessageInfo securedMessage : hadesInstanceInfo.getSecuredMessages()) {
                securedMessages.put(securedMessage.getType(), securedMessage);
            }
            if (partyInfo.getSecuredMessages() != null && partyInfo.getSecuredMessages().length > 0) {
                for (SecuredMessageInfo securedMessage : hadesInstanceInfo.getSecuredMessages()) {
                    securedMessages.put(securedMessage.getType(), securedMessage);
                }
            }
            partyInfo.setSecuredMessages(securedMessages.values().toArray(new SecuredMessageInfo[securedMessages.size()]));
        }
    }

    private void setupRemoteJMXServer(MBeanServer server) throws ConnectionException, ConfigurationException {
        Map<String, Object> env = new HashMap<>();
        JMXServiceURL url = null;
        try {
            if (configuration.getMgmtHost() == null || configuration.getMgmtHost().trim().isEmpty()) {
                configuration.setMgmtHost("127.0.0.1");
            }
            if (configuration.getMgmtPort() == null) {
                configuration.setMgmtPort(DEFAULT_MANAGEMENT_PORT);
            }
            if (configuration.getMgmtUseAuth() != null && configuration.getMgmtUseAuth()) {
                Security.addProvider(new HadesServerProvider());
                // this will be override if SSL client authentication is enabled
                env.put("jmx.remote.profiles", HadesServerProvider.REMOTE_PROFILE_SASL);
            }
            if (configuration.getMgmtUseSSL() != null && configuration.getMgmtUseSSL()) {
                setupTLS(env);
            }
            url = new JMXServiceURL("jmxmp", configuration.getMgmtHost(), configuration.getMgmtPort());

            JMXMPConnectorServer jmxmpServer = new JMXMPConnectorServer(url, env, server);
            jmxmpServer.start();
        } catch (MalformedURLException ex) {
            String logMsg = "JMX url string [" + (url != null ? url.toString() : "unknown") + "] is in bad format.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{logMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConnectionException(logMsg, ex);
        } catch (IOException ex) {
            String logMsg = "Error connecting to url [" + url.toString() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{logMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConnectionException(logMsg, ex);
        }
    }

    private void setupTLS(Map<String, Object> env) throws ConfigurationException {
        try {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            String keystoreName;
            if (configuration.getMgmtKeystoreFile() == null || configuration.getMgmtKeystoreFile().isEmpty()) {
                keystoreName = "hadesfix-mgmt-key.ks";
            } else {
                keystoreName = configuration.getMgmtKeystoreFile();
            }
            String keystorePasswdEntry;
            if (configuration.getMgmtKeystorePasswd() == null || configuration.getMgmtKeystorePasswd().isEmpty()) {
                keystorePasswdEntry = "hadesfix";
            } else {
                keystorePasswdEntry = configuration.getMgmtKeystorePasswd();
            }
            char[] keystorePasswd = PasswordBank.getInstance().getEntryValue(keystorePasswdEntry);
            KeyStore ks = readKeystore(Configurator.getConfigDir() + "/" + keystoreName, keystorePasswd);

            kmf.init(ks, keystorePasswd);

            TrustManagerFactory tmf = null;
            KeyStore ts = null;
	    if (configuration.getMgmtTruststoreFile() != null && !configuration.getMgmtTruststoreFile().trim().isEmpty()) {
		tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		String truststoreName = configuration.getMgmtTruststoreFile();
		String trustPasswdEntry = configuration.getMgmtTruststorePasswd();
		char[] trustPasswd = PasswordBank.getInstance().getEntryValue(trustPasswdEntry);
		ts = readKeystore(Configurator.getConfigDir() + "/" + truststoreName, trustPasswd);
		if (ts != null) {
		    tmf.init(ts);
		}
            }
            if (configuration.getMgmtUseAuth() != null && configuration.getMgmtUseAuth()) {
                env.put("jmx.remote.profiles", HadesServerProvider.REMOTE_PROFILE_SASL_WITH_SSL);
            } else {
                env.put("jmx.remote.profiles", HadesServerProvider.REMOTE_PROFILE_SSL);
            }
            if (configuration.getMgmtUseSSLCliAuth() != null && configuration.getMgmtUseSSLCliAuth()) {
                env.put("jmx.remote.tls.need.client.authentication", Boolean.TRUE.toString());
            } else {
                env.put("jmx.remote.tls.need.client.authentication", Boolean.FALSE.toString());
            }
            
            SSLContext sslCtx = SSLContext.getInstance("TLS");
            sslCtx.init(kmf.getKeyManagers(), tmf != null ? tmf.getTrustManagers() : null, null);
            SSLSocketFactory ssf = sslCtx.getSocketFactory();

            env.put("jmx.remote.tls.socket.factory", ssf);
            env.put("jmx.remote.tls.enabled.protocols", "TLSv1");
        } catch (UnrecoverableKeyException ex) {
            String errMsg = "Unable to retrieve the key from keystore.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (KeyManagementException ex) {
            String errMsg = "Could not use the key from given keystore.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (KeyStoreException ex) {
            String errMsg = "Could not read the key from given keystore.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (NoSuchAlgorithmException ex) {
            String errMsg = "The algorithm used is not supported.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        }
    }

    private KeyStore readKeystore(String keyStoreFileName, char[] keyStorePasswd) throws ConfigurationException {
        KeyStore ks;
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException ex) {
            String errMsg = "Could not create a KeySTore of default type.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        }

        FileInputStream fis = null;
        try {
            File keystoreFile = new File(keyStoreFileName);
            if (!keystoreFile.exists()) {
                throw new ConfigurationException("Could not find the keystore file [" + keyStoreFileName + "].");
            }
            fis = new FileInputStream(keystoreFile);
            ks.load(fis, keyStorePasswd);
        } catch (IOException ex) {
            String errMsg = "Could not load the keystore file [" + keyStoreFileName + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (NoSuchAlgorithmException ex) {
            String errMsg = "Could not create a keystore for the given algorithm.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (CertificateException ex) {
            String errMsg = "Could not load the certificate form keystore [" + keyStoreFileName + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
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

    private static void printConfigurationErrors(Map<String, String> errors) {
        LOGGER.log(Level.SEVERE, "FATAL : Could not start HadesFIX engine because of the following configuration errors:");
	errors.keySet().stream().forEach((code) -> {
	    LOGGER.log(Level.SEVERE, "{0} - {1}", new Object[]{code, errors.get(code)});
	});
    }

    private void createScheduler() {
        if (configuration.getScheduler() != null) {
            scheduler = new Scheduler(this, configuration.getScheduler());
            scheduler.start();
            LOGGER.log(Level.INFO, "Scheduler thread [{0}] started.", scheduler.getName());
        }
    }

    private void startConfiguredSessions() {
	sessions.values().stream().forEach((session) -> {
	    startSessionCoordinator(session);
	});
    }

    private void startInstanceScheduledTasks() {
        if (configuration.getScheduler() != null) {
            if (configuration.getScheduler().getTasks() != null && configuration.getScheduler().getTasks().length > 0) {
                ThreadUtil.blockUntilAlive(scheduler);
                for (ScheduleTaskInfo task : configuration.getScheduler().getTasks()) {
                    scheduler.scheduleTask(task);
                }
            }
        }
    }

    private void startEventProcessor() {
        eventProcessor = new GenericEventProcessor(HADES_INSTANCE_EVENT_PROCESSOR);
        eventProcessor.start();
        ThreadUtil.blockUntilAlive(eventProcessor);
        while (!eventProcessor.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

}
