/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.session;

import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.ClientSessionInfo;
import net.hades.fix.engine.config.model.ClientTcpConnectionInfo;
import net.hades.fix.engine.config.model.CounterpartyInfo;
import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.stream.ConsumerStream;
import net.hades.fix.engine.process.stream.ProducerStream;
import net.hades.fix.engine.util.UIDGen;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.process.transport.TcpClient;

/**
 * The main class controlling the lifecycle of a counterparty client session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public final class ClientSessionCoordinator extends SessionCoordinator {

    private static final Logger LOGGER = Logger.getLogger(ClientSessionCoordinator.class.getName());

    private int numOfLogonRetries;
    
    protected TcpClient transport;
    
    private ClientSessionInfo configuration;

    public ClientSessionCoordinator(HadesInstance hadesInstance, SessionInfo configuration, CounterpartyInfo cptyConfiguration, SessionAddress sessionAddress)
	    throws ConfigurationException {
	super(hadesInstance, cptyConfiguration, sessionAddress);
	this.hadesInstance = hadesInstance;
	this.configuration = (ClientSessionInfo) configuration;
	id = configuration.getID();
	initialise();
	status = TaskStatus.New;
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Client session coordinator [{0}] running.", id);

        status = TaskStatus.Running;

        while (!shutdown) {
            try {
                processCommand(commandQueue.take());
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Client Session Coordinator thread [{0}] has been interrupted.", id);
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

        // initialise transport
        transport = new TcpClient(this, (ClientTcpConnectionInfo) configuration.getConnection());
        // initialise Fix Client
        protocol = new FixClient(this, configuration);

        LOGGER.log(Level.INFO, "Client FIX protocol [{0}] initialised.", protocol.getName());
        // generate consumer stream ID if not set
        if (configuration.getConsumerStreamInfo().getId() == null) {
            configuration.getConsumerStreamInfo().setId(UIDGen.getInstance().getNextUID(ConsumerStream.CONSUMER_STREAM_PREFIX));
        }
        consumerStream = new ConsumerStream(this, configuration.getConsumerStreamInfo());

        if (configuration.getProducerStreamInfo().getId() == null) {
            configuration.getProducerStreamInfo().setId(UIDGen.getInstance().getNextUID(ProducerStream.PRODUCER_STREAM_PREFIX));
        }
        producerStream = new ProducerStream(this, configuration.getProducerStreamInfo());

        LOGGER.log(Level.INFO, "Producer stream [{0}] initialised.", producerStream.getName());

        LOGGER.log(Level.INFO, "Session coordinator for session [{0}] initialised.", configuration.getID());
    }


}
