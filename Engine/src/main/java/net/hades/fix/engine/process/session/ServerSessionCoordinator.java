/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.session;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.CounterpartyInfo;
import net.hades.fix.engine.config.model.ServerSessionInfo;
import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.data.ProcessStatus;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.server.FixServer;
import net.hades.fix.engine.process.stream.ConsumerStream;
import net.hades.fix.engine.process.stream.ProducerStream;
import net.hades.fix.engine.util.UIDGen;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.process.transport.TcpServer;

/**
 * The main class controlling the life cycle of a counter-party server session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public final class ServerSessionCoordinator extends SessionCoordinator {

    private static final Logger LOGGER = Logger.getLogger(ServerSessionCoordinator.class.getName());
    
    private final ServerSessionInfo configuration;
    protected TcpServer transport;

    public ServerSessionCoordinator(HadesInstance hadesInstance, SessionInfo configuration, CounterpartyInfo cptyConfiguration, SessionAddress sessionAddress) 
	    throws ConfigurationException {
        super(hadesInstance, cptyConfiguration, sessionAddress);
        this.configuration = (ServerSessionInfo) configuration;
	id = configuration.getID();
	initialise();
        status = TaskStatus.New;
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
        LOGGER.log(Level.INFO, "Initialising session coordinator for session [{0}].", id);

        aggregateSessionConfiguration(cptyConfiguration);

        protocol = new FixServer(this, (ServerSessionInfo) configuration);

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

 
}
