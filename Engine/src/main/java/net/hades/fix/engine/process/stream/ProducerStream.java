/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProducerStream.java
 *
 * $Id: ProducerStream.java,v 1.34 2011-04-30 04:39:44 vrotaru Exp $
 */
package net.hades.fix.engine.process.stream;

import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.config.model.FlowInfo;
import net.hades.fix.engine.config.model.ProducerStreamInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProcessStatus;
import net.hades.fix.engine.mgmt.data.StreamStats;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.GenericEventProcessor;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.engine.util.UIDGen;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Message;
import net.hades.fix.message.exception.InvalidMsgException;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stream that produces (transmits) messages to a counterparty.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.34 $
 */
public class ProducerStream extends Stream {

    private static final Logger LOGGER = Logger.getLogger(ProducerStream.class.getName());

    public static final String PRODUCER_STREAM_PREFIX = "SP";
    private static final int DEFAULT_STREAM_QUEUE_SIZE = 10000;

    private ProducerStreamInfo configuration;

    private StreamMessageProducer streamMessageProducer;

    protected BlockingQueue<Message> messageQueue;

    public ProducerStream(SessionCoordinator sessionCoordinator, ProducerStreamInfo configuration) throws ConfigurationException {
        super(sessionCoordinator.getName() + "_" + configuration.getId());

        this.sessionCoordinator = sessionCoordinator;
        this.configuration = configuration;
        this.protocol = sessionCoordinator.getProtocol();

        messageQueue = new ArrayBlockingQueue<Message>(DEFAULT_STREAM_QUEUE_SIZE);

        setProcessStatus(ProcessStatus.INITIALISING);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Staring Producer stream thread [{0}].", configuration.getId());

        getStats().setStartTimestamp(new Date());
        getMgmtData().setId(String.valueOf(getId()));
        getMgmtData().setName(getName());
        getMgmtData().setStatus(ProcessStatus.INACTIVE);
        setProcessStatus(ProcessStatus.INACTIVE);

        while (!shutdown) {
            try {
                processCommand(commandQueue.take());
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Producer stream thread [{0}] has been interrupted.", getName());
            } catch (Exception ex) {
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.ProducerStream.toString(),
                                BaseSeverityType.RECOVERABLE, AlertCode.MGMT_COMMAND_ERROR, "Unexpected error.", ex)
                ));
            }
        }

        LOGGER.log(Level.INFO, "Producer stream thread [{0}] shutdown.", configuration.getId());
    }

    @Override
    public void initialise() throws ConfigurationException {
        LOGGER.log(Level.INFO, "Producer stream [{0}] initialising.", configuration.getId());

        streamMessageProducer = new StreamMessageProducer(getName());
        eventProcessor = new GenericEventProcessor(getName());

        flows = new ArrayList<Flow>();
        for (FlowInfo flowInfo : configuration.getFlows()) {
            if (flowInfo.getId() == null) {
                flowInfo.setId(UIDGen.getInstance().getNextUID("FP"));
            }
            Flow flow = new ProducerFlow(this, flowInfo);
            flow.initialise();
            flows.add(flow);
        }

        LOGGER.log(Level.INFO, "Producer stream [{0}] initialised.", configuration.getId());
    }

    /**
     * Sends a message to the Stream message queue
     *
     * @param message message
     * @return true if the message was delivered successfully, false otherwise
     */
    public boolean write(Message message) {
        return messageQueue.offer(message);
    }

    @Override
    protected void startup() {
        LOGGER.log(Level.INFO, "Starting stream [{0}].", getName());

        startEventProcessor();
        streamMessageProducer.block();
        startStreamMessageProducer();

        for (Flow flow : flows) {
            flow.start();
        }

        LOGGER.log(Level.INFO, "Stream [{0}] started.", getName());
    }

    @Override
    protected void block() {
        streamMessageProducer.block();
        streamMessageProducer.interrupt();
        while (streamMessageProducer.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "Stream [{0}] blocked.", getName());
    }

    @Override
    protected void unblock() {
        streamMessageProducer.unblock();
        streamMessageProducer.interrupt();
        while (!streamMessageProducer.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        setProcessStatus(ProcessStatus.ACTIVE);
        getMgmtData().setStatus(ProcessStatus.ACTIVE);

        LOGGER.log(Level.INFO, "Stream [{0}] unblocked.", getName());
    }

    @Override
    protected void shutdown() {
        LOGGER.log(Level.INFO, "Shutting down stream [{0}].", getName());

        for (Flow flow : flows) {
            flow.stop();
        }
        if (streamMessageProducer != null) {
            streamMessageProducer.shutdown();
            streamMessageProducer.interrupt();
            while (streamMessageProducer.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            streamMessageProducer = null;
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

        LOGGER.log(Level.INFO, "Stream [{0}] shutdown.", getName());

        shutdown = true;
    }

    @Override
    protected void shutdownNow() {
        shutdown();
    }

    @Override
    protected String getID() {
        return configuration.getId();
    }

    private void startStreamMessageProducer() {
        streamMessageProducer.start();
        ThreadUtil.blockUntilAlive(streamMessageProducer);
        while (streamMessageProducer.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    private void startEventProcessor() {
        eventProcessor.start();
        ThreadUtil.blockUntilAlive(eventProcessor);
        while (!eventProcessor.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }

        addAlertListener(sessionCoordinator.getEventProcessor());
        addLifeCycleListener(sessionCoordinator.getEventProcessor());
        addMessageListener(sessionCoordinator.getEventProcessor());
    }

    private class StreamMessageProducer extends Thread {

        private volatile boolean producerShutdown;
        private volatile boolean producerBlocked;
        private volatile boolean active;

        private StreamMessageProducer(String name) {
            super(name + "_MSG_PRODUCER");
        }

        @Override
        public void run() {
            LOGGER.log(Level.INFO, "Starting FIX Msg consumer [{0}].", getName());

            while (!producerShutdown) {
                try {
                    if (!producerBlocked) {
                        active = true;
                        if (!sessionCoordinator.getProtocol().getStateProcessor().getProcessingStage().equals(ProcessingStage.LOGGEDON)) {
                            // do npt send messages to protocol if the session is not logged in
                            ThreadUtil.sleep(1);
                            continue;
                        }
                        Message message = messageQueue.take();
                        if (message != null) {
                            try {
                                protocol.writeMessage((FIXMsg) message);
                                ((StreamStats) getStats()).incrMsgOutCount();
                            } catch (InvalidMsgException ex) {
                                LOGGER.log(Level.SEVERE, "Invalid message send to protocol. Error was : {0}", ex.getMessage());
                                eventProcessor.onAlertEvent(new AlertEvent(this, Alert.createAlert(getName(), ComponentType.ProducerStream.name(),
                                        BaseSeverityType.RECOVERABLE, AlertCode.PRODUCER_STREAM_ERROR, ex.toString(), ex)));
                            }
                        }
                    } else {
                        Thread.sleep(1);
                        active = false;
                    }
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "Fix Msg consumer thread (0) has been interrupted.", getName());
                }
            }
            active = false;

            LOGGER.log(Level.INFO, "FIX Msg consumer [{0}] shutdown.", getName());
        }

        public boolean isActive() {
            return active;
        }

        public void block() {
            producerBlocked = true;
        }

        public void unblock() {
            producerBlocked = false;
        }

        public void shutdown() {
            producerShutdown = true;
        }
    }
}
