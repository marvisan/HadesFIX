/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConsumerStream.java
 *
 * $Id: ConsumerStream.java,v 1.32 2011-04-30 04:39:44 vrotaru Exp $
 */
package net.hades.fix.engine.process.stream;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.config.model.ConsumerStreamInfo;
import net.hades.fix.engine.config.model.FlowInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.HandlerException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProcessStatus;
import net.hades.fix.engine.mgmt.data.StreamStats;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.GenericEventProcessor;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.engine.util.UIDGen;
import net.hades.fix.message.FIXMsg;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stream that consumes (receives) messages from a counterparty.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.32 $
 */
public class ConsumerStream extends Stream {

    private static final Logger LOGGER = Logger.getLogger(ConsumerStream.class.getName());

    public static final String CONSUMER_STREAM_PREFIX = "SC";

    private ConsumerStreamInfo configuration;

    private StreamMessageConsumer streamMessageConsumer;

    public ConsumerStream(SessionCoordinator sessionCoordinator, ConsumerStreamInfo configuration) throws ConfigurationException {
        super(sessionCoordinator.getName() + "_" + configuration.getId());

        this.sessionCoordinator = sessionCoordinator;
        this.configuration = configuration;
        this.protocol = sessionCoordinator.getProtocol();

        setProcessStatus(ProcessStatus.INITIALISING);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Starting Consumer stream thread [{0}].", configuration.getId());

        getStats().setStartTimestamp(new Date());
        getMgmtData().setId(String.valueOf(getId()));
        getMgmtData().setName(getName());
        getMgmtData().setStatus(ProcessStatus.INACTIVE);
        setProcessStatus(ProcessStatus.INACTIVE);

        while (!shutdown) {
            try {
                processCommand(commandQueue.take());
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Consumer stream thread [{0}] has been interrupted.", getName());
            } catch (Exception ex) {
                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.ConsumerStream.toString(),
                                BaseSeverityType.RECOVERABLE, AlertCode.MGMT_COMMAND_ERROR, "Unexpected error.", ex)));
            }
        }

        LOGGER.log(Level.INFO, "Consumer stream thread [{0}] shutdown.", configuration.getId());
    }

    @Override
    public void initialise() throws ConfigurationException {
        LOGGER.log(Level.INFO, "Consumer stream [{0}] initialising.", configuration.getId());

        streamMessageConsumer = new StreamMessageConsumer(getName());
        eventProcessor = new GenericEventProcessor(getName());

        flows = new ArrayList<Flow>();
        for (FlowInfo flowInfo : configuration.getFlows()) {
            if (flowInfo.getId() == null) {
                flowInfo.setId(UIDGen.getInstance().getNextUID("FC"));
            }
            Flow flow = new ConsumerFlow(this, flowInfo);
            flow.initialise();

            flows.add(flow);
        }
        setProcessStatus(ProcessStatus.INACTIVE);
        getMgmtData().setStatus(ProcessStatus.INACTIVE);

        LOGGER.log(Level.INFO, "Consumer stream [{0}] initialised.", configuration.getId());
    }

    @Override
    protected void startup() {
        LOGGER.log(Level.INFO, "Starting stream [{0}].", getName());

        startEventProcessor();
        streamMessageConsumer.block();
        startStreamMessageConsumer();

        for (Flow flow : flows) {
            flow.start();
        }

        LOGGER.log(Level.INFO, "Stream [{0}] started.", getName());
    }

    @Override
    protected void block() {
        streamMessageConsumer.block();
        streamMessageConsumer.interrupt();
        while (streamMessageConsumer.isActive()) {
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
        streamMessageConsumer.unblock();
        streamMessageConsumer.interrupt();
        while (!streamMessageConsumer.isActive()) {
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

        block();
        for (Flow flow : flows) {
            flow.stop();
        }
        if (streamMessageConsumer != null) {
            streamMessageConsumer.shutdown();
            streamMessageConsumer.interrupt();
            while (streamMessageConsumer.isActive()) {
                if (!ThreadUtil.sleep(1)) {
                    break;
                }
            }
            streamMessageConsumer = null;
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

    private void startStreamMessageConsumer() {
        streamMessageConsumer.start();
        ThreadUtil.blockUntilAlive(streamMessageConsumer);
        while (streamMessageConsumer.isActive()) {
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

    private class StreamMessageConsumer extends Thread {

        private volatile boolean consumerShutdown;
        private volatile boolean consumerBlocked;
        private volatile boolean active;

        private StreamMessageConsumer(String name) {
            super(name + "_MSG_CONSUMER");
        }

        @Override
        public void run() {
            LOGGER.log(Level.INFO, "Starting FIX Msg consumer [{0}].", getName());

            while (!consumerShutdown) {
                try {
                    if (!consumerBlocked) {
                        active = true;
                        FIXMsg message = protocol.readMessage();
                        if (message != null) {
                            ((StreamStats) getStats()).incrMsgInCount();
                            boolean processed = false;
                            for (Flow flow : flows) {
                                if (flow.canProcess(message)) {
                                    try {
                                        ((ConsumerFlow) flow).process(message);
                                        processed = true;
                                    } catch (HandlerException ex) {
                                        LOGGER.log(Level.SEVERE, "Handler processing error was : {0}", ExceptionUtil.getStackTrace(ex));
                                        sessionCoordinator.sendRejectMessage(message, ex);
                                    } catch (Exception ex) {
                                        String errMsg = "Unexpected error received from the flow [" + flow.getFlowConfig().getId() + "].";
                                        LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
                                        eventProcessor.onAlertEvent(new AlertEvent(this,
                                                Alert.createAlert(getName(), ComponentType.ConsumerStream.toString(), BaseSeverityType.FATAL, null,
                                                        errMsg, ex)));
                                    }
                                    break;
                                }
                            }
                            if (!processed) {
                                ((StreamStats) getStats()).incrMsgDiscardCount();
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
            consumerBlocked = true;
        }

        public void unblock() {
            consumerBlocked = false;
        }

        public void shutdown() {
            consumerShutdown = true;
        }
    }
}
