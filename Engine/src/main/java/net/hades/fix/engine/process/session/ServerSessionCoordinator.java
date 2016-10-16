/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.session;

import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.CounterpartyInfo;
import net.hades.fix.engine.config.model.ServerSessionInfo;
import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.ProtocolStatusException;
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
import net.hades.fix.engine.process.protocol.server.FIXServer;
import net.hades.fix.engine.process.stream.ConsumerStream;
import net.hades.fix.engine.process.stream.ProducerStream;
import net.hades.fix.engine.process.transport.TCPServerWorker;
import net.hades.fix.engine.util.UIDGen;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main class controlling the lifecycle of a counterparty server session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.15 $
 */
public final class ServerSessionCoordinator extends SessionCoordinator {

    private static final Logger LOGGER = Logger.getLogger(ServerSessionCoordinator.class.getName());

    public ServerSessionCoordinator(HadesInstance hadesInstance, SessionInfo configuration, CounterpartyInfo cptyConfiguration,
                                    SessionAddress sessionAddress) throws ConfigurationException {
        super(sessionAddress.getRemoteAddress().getID() + ":" + sessionAddress.getLocalAddress().getID());
        this.hadesInstance = hadesInstance;
        this.configuration = configuration;
        this.cptyConfiguration = cptyConfiguration;
        this.sessionAddress = sessionAddress;
        setProcessStatus(ProcessStatus.INITIALISING);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Server session coordinator [{0}] running.", getName());

        getMgmtData().setId(String.valueOf(getId()));
        getMgmtData().setName(configuration.getID());
        getMgmtData().setConfig(configuration.toString());
        getStats().setStartTimestamp(new Date());

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        while (!isShutdown()) {
            try {
                processCommand(commandQueue.take());
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Server Session Coordinator thread [{0}] has been interrupted", getName());
            } catch (Exception ex) {
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.ServerSessionCoordinator.toString(),
                                BaseSeverityType.RECOVERABLE, AlertCode.MGMT_COMMAND_ERROR, "Unexpected error.", ex)));
            }
        }

        hadesInstance.removeSessionCoordinator(getCptyID(), getLocalID());

        LOGGER.log(Level.INFO, "Server session coordinator [{0}] destroyed.", getName());
    }

    @Override
    public void initialise() throws ConfigurationException {
        LOGGER.log(Level.INFO, "Initialising session coordinator for session [{0}].", configuration.getID());

        aggregateSessionConfiguration(cptyConfiguration);
        findSingletonHandlers();

        eventProcessor = new GenericEventProcessor(getName());

        protocol = new FIXServer(this, (ServerSessionInfo) configuration);
        protocol.initialise();

        LOGGER.log(Level.INFO, "Server FIX protocol [{0}] initialised.", protocol.getName());

        if (configuration.getConsumerStreamInfo().getId() == null) {
            configuration.getConsumerStreamInfo().setId(UIDGen.getInstance().getNextUID(ConsumerStream.CONSUMER_STREAM_PREFIX));
        }
        consumerStream = new ConsumerStream(this, configuration.getConsumerStreamInfo());
        consumerStream.initialise();

        LOGGER.log(Level.INFO, "Consumer stream [{0}] initialised.", consumerStream.getName());

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
        if (ProcessStatus.INACTIVE.equals(processStatus.get())) {
            LOGGER.log(Level.INFO, "Starting session coordinator [{0}].", getName());

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

            protocol.start();
            ThreadUtil.blockUntilAlive(protocol);
            while (!ProcessStatus.INITIALISING.equals(protocol.getProcessStatus())) {
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

            consumerStream.start();
            ThreadUtil.blockUntilAlive(consumerStream);
            consumerStream.execute(new Command(CommandType.Startup));
            while (!ProcessStatus.INACTIVE.equals(consumerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }

            startSessionScheduledTasks();

            LOGGER.log(Level.INFO, "Session coordinator [{0}] started.", getName());
        }
    }

    @Override
    public void freezeProtocol() throws ProtocolStatusException {
        LOGGER.log(Level.INFO, "Freezing session [{0}].", getName());

        if (ProcessStatus.ACTIVE.equals(processStatus.get())) {
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
            getMgmtData().setStatus(ProcessStatus.FROZEN);
        } else {
            throw new ProtocolStatusException("The session is not active. Nothing to be frozen.");
        }

        LOGGER.log(Level.INFO, "Session [{0}] frozen.", getName());
    }

    @Override
    public void thawProtocol() throws ProtocolStatusException {
        LOGGER.log(Level.INFO, "Thawing session [{0}].", getName());

        if (ProcessStatus.FROZEN.equals(processStatus.get())) {
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
            getMgmtData().setStatus(ProcessStatus.ACTIVE);
        } else {
            throw new ProtocolStatusException("The session is not frozen. Nothing to be thawed.");
        }

        LOGGER.log(Level.INFO, "Session [{0}] thawed.", getName());
    }

    @Override
    public void disconnectTransport() throws ProtocolStatusException {
        throw new ProtocolStatusException("Only BUY side (initiator) sessions can be disconnected.");
    }

    @Override
    public void connectTransport() throws ProtocolStatusException {
        throw new ProtocolStatusException("Only BUY side (initiator) sessions can be connected.");
    }

    @Override
    public void shutdown() {
        LOGGER.log(Level.INFO, "Shutting down session coordinator [{0}].", getName());

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        producerStream.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        producerStream = null;

        if (transport != null) {
            transport.execute(new Command(CommandType.StopTCPReader));
            while (!ProcessStatus.TCP_READER_STOPPED.equals(transport.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }

        protocol.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        protocol = null;

        consumerStream.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        consumerStream = null;

        if (transport != null) {
            transport.execute(new Command(CommandType.UnsetSessionCoordinator));
            transport.execute(new Command(CommandType.Shutdown));
            while (!ProcessStatus.SHUTDOWN.equals(transport.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            transport = null;
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

        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);
        shutdown = true;

        LOGGER.log(Level.INFO, "Session coordinator [{0}] shutdown.", getName());
    }

    @Override
    public void shutdownNow() {
        LOGGER.log(Level.INFO, "Shutting down immediate session coordinator [{0}].", getName());

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        producerStream.execute(new Command(CommandType.ShutdownNow));
        while (!ProcessStatus.SHUTDOWN.equals(producerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        producerStream = null;

        if (transport != null) {
            transport.execute(new Command(CommandType.UnsetSessionCoordinator));
            transport.execute(new Command(CommandType.ShutdownNow));
            while (!ProcessStatus.SHUTDOWN.equals(transport.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            transport = null;
        }

        protocol.execute(new Command(CommandType.ShutdownNow));
        while (!ProcessStatus.SHUTDOWN.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        protocol = null;

        consumerStream.execute(new Command(CommandType.ShutdownNow));
        while (!ProcessStatus.SHUTDOWN.equals(consumerStream.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        consumerStream = null;

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

        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);
        shutdown = true;

        LOGGER.log(Level.INFO, "Session coordinator [{0}] shutdown immediate.", getName());
    }

    // </editor-fold>

    public void reload() {
        //TODO implement it
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
                transportDisconnected();
                break;

            case TransportConnected:
                TCPServerWorker worker = (TCPServerWorker) command.getParameter(Command.PARAM_SERVER_TRANSPORT_WORKER);
                transportConnected(worker);
                break;

            case SessionRestarted:
                shutdownSession();
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

    private void transportConnected(TCPServerWorker worker) {
        if (ProcessStatus.INACTIVE.equals(processStatus.get())
                || ProcessStatus.FROZEN.equals(processStatus.get())
                || ProcessStatus.DISCONNECTED.equals(processStatus.get())) {
            LOGGER.log(Level.INFO, "Transport connected action on session coordinator [{0}]", getName());

            consumerStream.execute(new Command(CommandType.Unblock));
            while (!ProcessStatus.ACTIVE.equals(consumerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            transport = worker;
            protocol.execute(new Command(CommandType.Startup, Command.PARAM_SERVER_TRANSPORT_WORKER, worker));
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
            getMgmtData().setStatus(ProcessStatus.ACTIVE);
        }
    }

    private void transportDisconnected() {
        if (ProcessStatus.ACTIVE.equals(processStatus.get())) {
            LOGGER.log(Level.INFO, "Transport disconnected action on session coordinator [{0}]", getName());

            producerStream.execute(new Command(CommandType.Block));
            while (!ProcessStatus.INACTIVE.equals(producerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            protocol.execute(new Command(CommandType.TransportDisconnected));
            transport = null;
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
            if (configuration.getResetSeqAtDisconnect()) {
                protocol.execute(new Command(CommandType.ResetSequences));
            }
            transport = null;

            setProcessStatus(ProcessStatus.INACTIVE);
            getMgmtData().setStatus(ProcessStatus.INACTIVE);
        }
    }

    private void shutdownSession() {
        LOGGER.log(Level.INFO, "Shutting down session coordinator [{0}].", getName());

        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

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

        if (transport != null) {
            transport.execute(new Command(CommandType.StopTCPReader));
            while (!ProcessStatus.TCP_READER_STOPPED.equals(transport.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
        }

        protocol.execute(new Command(CommandType.Shutdown));
        while (!ProcessStatus.SHUTDOWN.equals(protocol.getProcessStatus())) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        if (transport != null) {
            transport.execute(new Command(CommandType.UnsetSessionCoordinator));
            transport.execute(new Command(CommandType.Shutdown));
            while (!ProcessStatus.SHUTDOWN.equals(transport.getProcessStatus())) {
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

        hadesInstance.removeSessionCoordinator(getCptyID(), getLocalID());

        setProcessStatus(ProcessStatus.SHUTDOWN);
        getMgmtData().setStatus(ProcessStatus.SHUTDOWN);
        shutdown = true;

        LOGGER.log(Level.INFO, "Session coordinator [{0}] shutdown.", getName());
    }

    private void block() {
        if (ProcessStatus.ACTIVE.equals(processStatus.get()) || ProcessStatus.FROZEN.equals(processStatus.get())) {
            LOGGER.log(Level.INFO, "Blocking session coordinator [{0}]", getName());

            producerStream.execute(new Command(CommandType.Block));
            while (!ProcessStatus.INACTIVE.equals(producerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            if (transport != null) {
                transport.execute(new Command(CommandType.Block));
                while (!ProcessStatus.INACTIVE.equals(transport.getProcessStatus())) {
                    if (!ThreadUtil.sleep(1)) {
                        break;
                    }
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
            getMgmtData().setStatus(ProcessStatus.INACTIVE);

            LOGGER.log(Level.INFO, "Session coordinator [{0}] blocked.", getName());
        }
    }

    private void unblock() {
        if (ProcessStatus.INACTIVE.equals(processStatus.get()) || ProcessStatus.FROZEN.equals(processStatus.get())) {
            LOGGER.log(Level.INFO, "Unblocking session coordinator [{0}]", getName());

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
            if (transport != null) {
                transport.execute(new Command(CommandType.Unblock));
                while (!ProcessStatus.ACTIVE.equals(transport.getProcessStatus())) {
                    if (!ThreadUtil.sleep(1)) {
                        break;
                    }
                }
            }
            producerStream.execute(new Command(CommandType.Unblock));
            while (!ProcessStatus.ACTIVE.equals(producerStream.getProcessStatus())) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }

            setProcessStatus(ProcessStatus.ACTIVE);
            getMgmtData().setStatus(ProcessStatus.ACTIVE);

            LOGGER.log(Level.INFO, "Session coordinator [{0}] unblocked.", getName());
        }
    }
}
