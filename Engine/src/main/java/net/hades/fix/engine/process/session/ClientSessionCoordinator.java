/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.session;

import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.ClientSessionInfo;
import net.hades.fix.engine.config.model.ClientTcpConnectionInfo;
import net.hades.fix.engine.config.model.CounterpartyInfo;
import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProcessStatus;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.GenericEventProcessor;
import net.hades.fix.engine.process.protocol.client.FIXClient;
import net.hades.fix.engine.process.stream.ConsumerStream;
import net.hades.fix.engine.process.stream.ProducerStream;
import net.hades.fix.engine.process.transport.TCPClient;
import net.hades.fix.engine.util.UIDGen;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main class controlling the lifecycle of a counterparty client session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.14 $
 */
public final class ClientSessionCoordinator extends SessionCoordinator {

    private static final Logger LOGGER = Logger.getLogger(ClientSessionCoordinator.class.getName());

    private int numOfLogonRetries;

    public ClientSessionCoordinator(HadesInstance hadesInstance, SessionInfo configuration, CounterpartyInfo cptyConfiguration,
                                    SessionAddress sessionAddress) throws ConfigurationException {
        super(sessionAddress.toString());
        this.hadesInstance = hadesInstance;
        this.configuration = configuration;
        this.cptyConfiguration = cptyConfiguration;
        this.sessionAddress = sessionAddress;
        setProcessStatus(ProcessStatus.INITIALISING);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Client session coordinator [{0}] running.", getName());

        mgmtData.get().setId(String.valueOf(getId()));
        mgmtData.get().setName(configuration.getID());
        mgmtData.get().setConfig(configuration.toString());
        stats.get().setStartTimestamp(new Date());
        setProcessStatus(ProcessStatus.INACTIVE);

        while (!shutdown) {
            try {
                processCommand(commandQueue.take());
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Client Session Coordinator thread [{0}] has been interrupted.", getName());
            } catch (Exception ex) {
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.ClientSessionCoordinator.toString(),
                                BaseSeverityType.RECOVERABLE, AlertCode.MGMT_COMMAND_ERROR, "Unexpected error.", ex)));
            }
        }

        hadesInstance.removeSessionCoordinator(getCptyID(), getLocalID());

        LOGGER.log(Level.INFO, "Client session coordinator [{0}] destroyed.", getName());
    }

    @Override
    public void initialise() throws ConfigurationException {
        LOGGER.log(Level.INFO, "Initialising session coordinator for session [{0}].", configuration.getID());

        // read configuration for this session
        aggregateSessionConfiguration(cptyConfiguration);
        findSingletonHandlers();

        // event processor
        eventProcessor = new GenericEventProcessor(getName());
        // initialise transport
        transport = new TCPClient(this, (ClientTcpConnectionInfo) configuration.getConnection());
        transport.initialise();

        LOGGER.log(Level.INFO, "Client transport [{0}] initialised.", ((TCPClient) transport).getName());
        // initialise FIX client
        protocol = new FIXClient(this, (ClientSessionInfo) configuration);
        protocol.initialise();
        protocol.setTransport(transport);

        LOGGER.log(Level.INFO, "Client FIX protocol [{0}] initialised.", protocol.getName());
        // generate consumer stream ID if not set
        if (configuration.getConsumerStreamInfo().getId() == null) {
            configuration.getConsumerStreamInfo().setId(UIDGen.getInstance().getNextUID(ConsumerStream.CONSUMER_STREAM_PREFIX));
        }
        consumerStream = new ConsumerStream(this, configuration.getConsumerStreamInfo());
        consumerStream.initialise();

        LOGGER.log(Level.INFO, "Consumer stream [{0}] initialised.", consumerStream.getName());
        // generate producer stream ID if not set
        if (configuration.getProducerStreamInfo().getId() == null) {
            configuration.getProducerStreamInfo().setId(UIDGen.getInstance().getNextUID(ProducerStream.PRODUCER_STREAM_PREFIX));
        }
        producerStream = new ProducerStream(this, configuration.getProducerStreamInfo());
        producerStream.initialise();

        LOGGER.log(Level.INFO, "Producer stream [{0}] initialised.", producerStream.getName());

        LOGGER.log(Level.INFO, "Session coordinator for session [{0}] initialised.", configuration.getID());
    }

    @Override
    public void startup() {
        if (ProcessStatus.INACTIVE.equals(getProcessStatus())) {
            LOGGER.log(Level.INFO, "Starting session coordinator [{0}].", getName());

            // start event processor here
            eventProcessor.start();
            ThreadUtil.blockUntilAlive(eventProcessor);
            while (!eventProcessor.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }

            addAlertListener(hadesInstance.getEventProcessor());
            addLifeCycleListener(hadesInstance.getEventProcessor());
            addMessageListener(hadesInstance.getEventProcessor());

            ((TCPClient) transport).start();
            ThreadUtil.blockUntilAlive((TCPClient) transport);
            transport.execute(new Command(CommandType.Startup));
            while (!ProcessStatus.INACTIVE.equals(transport.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }

            producerStream.start();
            ThreadUtil.blockUntilAlive(producerStream);
            producerStream.execute(new Command(CommandType.Startup));
            while (!ProcessStatus.INACTIVE.equals(producerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }

            protocol.start();
            ThreadUtil.blockUntilAlive(protocol);
            protocol.execute(new Command(CommandType.Startup));
            while (!ProcessStatus.INACTIVE.equals(protocol.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }

            consumerStream.start();
            ThreadUtil.blockUntilAlive(consumerStream);
            consumerStream.execute(new Command(CommandType.Startup));
            while (!ProcessStatus.INACTIVE.equals(consumerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }

            if (((ClientSessionInfo) configuration).getConnectOnStartup()) {
                transport.execute(new Command(CommandType.Unblock));
                while (!ProcessStatus.ACTIVE.equals(transport.getProcessStatus())) {
                    if (!ThreadUtil.sleep(1)) {
                        break;
                    }
                }
            }
            startSessionScheduledTasks();

            processStatus.set(ProcessStatus.INACTIVE);
            mgmtData.get().setStatus(ProcessStatus.INACTIVE);

            LOGGER.log(Level.INFO, "Session coordinator [{0}] started.", getName());
        }
    }

    @Override
    public void connectTransport() {
        transport.execute(new Command(CommandType.Unblock, Command.PARAM_RECONNECT_DELAY, 1));
    }

    @Override
    public void disconnectTransport() {
        producerStream.execute(new Command(CommandType.Block));
        while (!ProcessStatus.INACTIVE.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        transport.execute(new Command(CommandType.StopIncoming));
        while (!ProcessStatus.INCOMING_STOPPED.equals(transport.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        protocol.execute(new Command(CommandType.DisconnectTransport));
        while (!ProcessStatus.INACTIVE.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        if (configuration.getResetSeqAtDisconnect()) {
            protocol.execute(new Command(CommandType.ResetSequences));
        }
        transport.execute(new Command(CommandType.DisconnectTransport));
        while (!ProcessStatus.INACTIVE.equals(transport.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        consumerStream.execute(new Command(CommandType.Block));
        while (!ProcessStatus.INACTIVE.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        setProcessStatus(ProcessStatus.DISCONNECTED);
        mgmtData.get().setStatus(ProcessStatus.DISCONNECTED);
    }

    @Override
    public void shutdown() {
        LOGGER.log(Level.INFO, "Shutting down session coordinator [{0}].", getName());

        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);

        producerStream.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        transport.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(transport.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        protocol.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        consumerStream.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                ThreadUtil.sleep(1);
            }
            eventProcessor = null;
        }

        setProcessStatus(ProcessStatus.SHUTDOWN);
        mgmtData.get().setStatus(ProcessStatus.SHUTDOWN);

        LOGGER.log(Level.INFO, "Session coordinator [{0}] shut down.", getName());

        shutdown = true;
    }

    @Override
    public void shutdownNow() {
        LOGGER.log(Level.INFO, "Shutting down immediate session coordinator [{0}].", getName());

        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);

        producerStream.execute(new Command(CommandType.ShutdownNow));
        while (!ProcessStatus.SHUTDOWN.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        transport.execute(new Command(CommandType.ShutdownNow));
        while (!ProcessStatus.SHUTDOWN.equals(transport.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        protocol.execute(new Command(CommandType.ShutdownNow));
        while (!ProcessStatus.SHUTDOWN.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        consumerStream.execute(new Command(CommandType.ShutdownNow));
        while (!ProcessStatus.SHUTDOWN.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                ThreadUtil.sleep(1);
            }
        }

        setProcessStatus(ProcessStatus.SHUTDOWN);
        mgmtData.get().setStatus(ProcessStatus.SHUTDOWN);

        LOGGER.log(Level.INFO, "Session coordinator [{0}] shutdown immediate.", getName());

        shutdown = true;
    }

    @Override
    public void freezeProtocol() {
        LOGGER.log(Level.INFO, "Freezing session [{0}].", getName());

        producerStream.execute(new Command(CommandType.Block));
        while (!ProcessStatus.INACTIVE.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        protocol.execute(new Command(CommandType.Freeze));
        while (!ProcessStatus.FROZEN.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        setProcessStatus(ProcessStatus.FROZEN);
        mgmtData.get().setStatus(ProcessStatus.FROZEN);

        LOGGER.log(Level.INFO, "Session [{0}] frozen.", getName());
    }

    @Override
    public void thawProtocol() {
        LOGGER.log(Level.INFO, "Thawing session [{0}].", getName());

        protocol.execute(new Command(CommandType.Thaw));
        while (!ProcessStatus.ACTIVE.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        producerStream.execute(new Command(CommandType.Unblock));
        while (!ProcessStatus.ACTIVE.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        setProcessStatus(ProcessStatus.ACTIVE);
        mgmtData.get().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "Session [{0}] thawed.", getName());
    }

    public synchronized void reload() {
        //TODO implement it
    }

    public int getNumOfLogonRetries() {
        return numOfLogonRetries;
    }

    public void incrNumOfLogonRetries() {
        numOfLogonRetries++;
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

            case Freeze:
                freezeProtocol();
                break;

            case Thaw:
                thawProtocol();
                break;

            case TransportConnected:
                transportConnected();
                break;

            case DisconnectTransport:
                disconnectTransport();
                break;

            case ConnectTransport:
                connectTransport();
                break;

            case ReconnectTransport:
                reconnectTransport();
                break;

            case StartProtocol:
                startProtocol();
                break;

            case SessionOpened:
                openSession();
                break;

            case SessionRestarted:
                shutdownSession();
                break;

            case StopSession:
                stopSession();
                break;

            case Shutdown:
                shutdown();
                break;

            case ShutdownNow:
                shutdownNow();
                break;

            default:
                LOGGER.log(Level.SEVERE, "Command [{0}] not implemented for a TCP Server.", command.getCommandType().name());
        }
    }

    private void block() {
        if (ProcessStatus.ACTIVE.equals(getProcessStatus()) || ProcessStatus.FROZEN.equals(getProcessStatus())) {
            LOGGER.log(Level.INFO, "Blocking session coordinator [{0}]", getName());

            producerStream.execute(new Command(CommandType.Block));
            while (!ProcessStatus.INACTIVE.equals(producerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            transport.execute(new Command(CommandType.Block));
            while (!ProcessStatus.INACTIVE.equals(transport.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            protocol.execute(new Command(CommandType.Block));
            while (!ProcessStatus.INACTIVE.equals(protocol.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            consumerStream.execute(new Command(CommandType.Block));
            while (!ProcessStatus.INACTIVE.equals(consumerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            setProcessStatus(ProcessStatus.INACTIVE);
            mgmtData.get().setStatus(ProcessStatus.INACTIVE);
            blocked = true;

            LOGGER.log(Level.INFO, "Session coordinator [{0}] blocked.", getName());
        }
    }

    private void unblock() {
        if (ProcessStatus.INACTIVE.equals(getProcessStatus())) {
            LOGGER.log(Level.INFO, "Unblocking session coordinator [{0}].", getName());

            consumerStream.execute(new Command(CommandType.Unblock));
            while (!ProcessStatus.ACTIVE.equals(consumerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            protocol.execute(new Command(CommandType.Unblock));
            while (!ProcessStatus.ACTIVE.equals(protocol.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            transport.execute(new Command(CommandType.Unblock));
            while (!ProcessStatus.ACTIVE.equals(transport.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            producerStream.execute(new Command(CommandType.Unblock));
            while (!ProcessStatus.ACTIVE.equals(producerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            setProcessStatus(ProcessStatus.ACTIVE);
            mgmtData.get().setStatus(ProcessStatus.ACTIVE);
            blocked = false;

            LOGGER.log(Level.INFO, "Session coordinator [{0}] unblocked.", getName());
        }
    }

    public void transportConnected() {
        LOGGER.log(Level.INFO, "Session coordinator [{0}] connecting transport.", getName());

        consumerStream.execute(new Command(CommandType.Unblock));
        while (!ProcessStatus.ACTIVE.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        protocol.execute(new Command(CommandType.TransportConnected));
        while (!ProcessStatus.ACTIVE.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        producerStream.execute(new Command(CommandType.Unblock));
        while (!ProcessStatus.ACTIVE.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        numOfLogonRetries = 0;

        setProcessStatus(ProcessStatus.ACTIVE);
        mgmtData.get().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "Session coordinator [{0}] transport connected.", getName());
    }

    private void reconnectTransport() {
        LOGGER.log(Level.INFO, "Session coordinator [{0}] attempting to restart transport.", getName());

        ClientSessionInfo config = (ClientSessionInfo) configuration;
        if (!config.getDoNotReconnWhenSeqNumTooLow()) {
            if (config.getMaxNumLogonRetries() > 0) {
                if (getNumOfLogonRetries() >= config.getMaxNumLogonRetries() - 1) {
                    setProcessStatus(ProcessStatus.SHUTDOWN);
                    mgmtData.get().setStatus(ProcessStatus.SHUTDOWN);

                    String errMsg = String.format("Number of maximum connection retries [{%s}] has been exceeded. Session will be shutdown.",
                            config.getMaxNumLogonRetries());
                    LOGGER.info(errMsg);
                    eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.ClientSessionCoordinator.toString(),
                            BaseSeverityType.INFO, AlertCode.NUM_MAX_RETRIES_EXCEEDED, errMsg, null)));

                    shutdownNow();
                } else {
                    restartTransport(config.getReconnectDelay());
                }
            } else {
                restartTransport(config.getReconnectDelay());
            }
        } else {
            String errMsg = "Reconnect session flag when received sequence number is too low is disabled. Session is closed.";
            LOGGER.info(errMsg);
            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.ClientSessionCoordinator.toString(),
                    BaseSeverityType.INFO, AlertCode.SESSION_RECONNECT_DISABLED, errMsg, null)));
            shutdownNow();
        }
    }

    private void restartTransport(int reconnectDelay) {
        producerStream.execute(new Command(CommandType.Block));
        while (!ProcessStatus.INACTIVE.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        protocol.execute(new Command(CommandType.Block));
        while (!ProcessStatus.INACTIVE.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        if (configuration.getResetSeqAtDisconnect()) {
            protocol.execute(new Command(CommandType.ResetSequences));
        }

        transport.execute(new Command(CommandType.ReconnectTransport, Command.PARAM_RECONNECT_DELAY, reconnectDelay));
        incrNumOfLogonRetries();

        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(ProcessStatus.INACTIVE);
    }

//    private void reconnectTransport1() {
//        ClientSessionInfo config = (ClientSessionInfo) configuration;
//        if (config.getMaxNumLogonRetries() > 0 && getNumOfLogonRetries() >= config.getMaxNumLogonRetries() - 1) {
//            setProcessStatus(ProcessStatus.SHUTDOWN);
//            mgmtData.get().setStatus(processStatus);
//
//            String errMsg = String.format("Number of maximum connection retries [{%s}] has been exceeded. Session will be shutdown.",
//                    config.getMaxNumLogonRetries());
//            LOGGER.info(errMsg);
//            eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.ClientSessionCoordinator.toString(),
//                    BaseSeverityType.INFO, AlertCode.NUM_MAX_RETRIES_EXCEEDED, errMsg, null)));
//
//            shutdownNow();
//        } else {
//            producerStream.execute(new Command(CommandType.Block));
//            while (!ProcessStatus.INACTIVE.equals(producerStream.getProcessStatus())) {
//                if (!ThreadUtil.sleep(1)) {
//                    break;
//                }
//            }
//            protocol.execute(new Command(CommandType.Block));
//            while (!ProcessStatus.INACTIVE.equals(protocol.getProcessStatus())) {
//                if (!ThreadUtil.sleep(1)) {
//                    break;
//                }
//            }
//            protocol.execute(new Command(CommandType.Reset));
//            if (configuration.getResetSeqAtDisconnect()) {
//                protocol.execute(new Command(CommandType.ResetSequences));
//            }
//            transport.execute(new Command(CommandType.ReconnectTransport, config.getReconnectDelay()));
//            while (!ProcessStatus.INACTIVE.equals(consumerStream.getProcessStatus())) {
//                if (!ThreadUtil.sleep(1)) {
//                    break;
//                }
//            }
//            incrNumOfLogonRetries();
//
//            setProcessStatus(ProcessStatus.INACTIVE);
//            mgmtData.setStatus(ProcessStatus.INACTIVE);
//        }
//
//    }

    public void stopSession() {
        LOGGER.log(Level.INFO, "Stopping session coordinator [{0}].", getName());

        blocked = true;

        producerStream.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        transport.execute(new Command(CommandType.StopTCPReader));
        while (!ProcessStatus.TCP_READER_STOPPED.equals(transport.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        protocol.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        consumerStream.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        consumerStream.interrupt();

        transport.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(transport.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                ThreadUtil.sleep(1);
            }
        }

        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(getProcessStatus());

        LOGGER.log(Level.INFO, "Session coordinator [{0}] stopped.", getName());

        shutdown = true;
    }

    public void shutdownSession() {
        LOGGER.log(Level.INFO, "Shutting down session coordinator [{0}].", getName());

        setProcessStatus(ProcessStatus.INACTIVE);
        mgmtData.get().setStatus(getProcessStatus());

        blocked = true;

        producerStream.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        consumerStream.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        transport.execute(new Command(CommandType.StopTCPReader));
        while (!ProcessStatus.TCP_READER_STOPPED.equals(transport.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        protocol.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        transport.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(transport.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        if (eventProcessor != null) {
            eventProcessor.shutdown();
            eventProcessor.interrupt();
            while (eventProcessor.isActive()) {
                ThreadUtil.sleep(1);
            }
        }

        setProcessStatus(ProcessStatus.SHUTDOWN);
        mgmtData.get().setStatus(ProcessStatus.SHUTDOWN);

        LOGGER.log(Level.INFO, "Session coordinator [{0}] shutdown.", getName());

        shutdown = true;
    }

    private void openSession() {
        consumerStream.execute(new Command(CommandType.Unblock));
        while (!ProcessStatus.ACTIVE.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        producerStream.execute(new Command(CommandType.Unblock));
        while (!ProcessStatus.ACTIVE.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void startProtocol() {
        protocol.execute(new Command(CommandType.Startup));
        while (!ProcessStatus.INACTIVE.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        consumerStream.execute(new Command(CommandType.Unblock));
        while (!ProcessStatus.ACTIVE.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        producerStream.execute(new Command(CommandType.Unblock));
        while (!ProcessStatus.ACTIVE.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        setProcessStatus(ProcessStatus.ACTIVE);
        mgmtData.get().setStatus(ProcessStatus.ACTIVE);
    }

}
