/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.router;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.process.protocol.client.FixClient;
import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.model.CounterpartyAddress;
import net.hades.fix.engine.process.protocol.server.FixServer;
import net.hades.fix.engine.util.MessageUtil;
import net.hades.fix.engine.util.ThreadLocalSessionDataUtil;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.HopsGroup;

/**
 * Router class that sends messages to be delivered to a counterparty destination.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class MessageRouter extends Thread {

    private static final Logger LOGGER = Logger.getLogger(MessageRouter.class.getName());

    private FixServer server;

    private Map<CounterpartyAddress, FixClient> clients;
    private LinkedBlockingQueue<FIXMsg> inboundQueue;
    private LinkedBlockingQueue<FIXMsg> outboundQueue;
    private volatile boolean active;
    private RouterRequestWorker requestWorker;
    private RoutingReplyWorker replyWorker;
    private CountDownLatch exitSignal = new CountDownLatch(1);

    public MessageRouter(FixServer server, String routerName) {
        super(routerName);
        this.server = server;
        clients = new ConcurrentHashMap<CounterpartyAddress, FixClient>();
        inboundQueue = new LinkedBlockingQueue<FIXMsg>();
        outboundQueue = new LinkedBlockingQueue<FIXMsg>();

        LOGGER.log(Level.INFO, "Message Router [{0}] created.", routerName);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Message Router [{0}] started.", getName());

        active = true;

        try {
            exitSignal.await();
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Message Router interrupted [{0}].", ExceptionUtil.getStackTrace(ex));
        }

        active = false;

        LOGGER.log(Level.INFO, "Message Router [{0}] stopped.", getName());
    }

    public void startup() {
        requestWorker = new RouterRequestWorker(getName() + "_REQ_WRKR");
        requestWorker.start();
        while (!requestWorker.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        replyWorker = new RoutingReplyWorker(getName() + "_REPL_WRKR");
        replyWorker.start();
        while (!replyWorker.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    public boolean routeRequestMessage(FIXMsg message) {
        return inboundQueue.offer(message);
    }

    public boolean routeResponseMessage(FIXMsg message) {
        return outboundQueue.offer(message);
    }

    public void shutdown() {
        requestWorker.shutdown();
        requestWorker.interrupt();
        while (requestWorker.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        replyWorker.shutdown();
        replyWorker.interrupt();
        while (replyWorker.isActive()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        exitSignal.countDown();
    }

    public boolean isActive() {
        return active;
    }


    public void initialiseThreadSessionContextData() {
        ThreadLocalSessionDataUtil.setThreadLocalSessionData(server.getConfiguration(), server.getVersion());
    }


    private MessageRouter getMessageRouter() {
        return this;
    }

    private void prepareMessageForRouting(FIXMsg message) {
        message.getHeader().addHopsGroup();
        HopsGroup hop = message.getHeader().getHopsGroups()[message.getHeader().getHopsGroups().length - 1];
        hop.setHopCompID(message.getHeader().getTargetCompID());
        hop.setHopSendingTime(message.getHeader().getSendingTime());
        hop.setHopRefID(message.getHeader().getMsgSeqNum());
        message.getHeader().setDeliverToCompID(null);
        message.getHeader().setDeliverToSubID(null);
        message.getHeader().setDeliverToLocationID(null);
        message.getHeader().setOnBehalfOfCompID(message.getHeader().getSenderCompID());
        message.getHeader().setOnBehalfOfSubID(message.getHeader().getSenderSubID());
        message.getHeader().setOnBehalfOfLocationID(message.getHeader().getSenderLocationID());
        message.getHeader().setSenderCompID(null);
        message.getHeader().setSenderSubID(null);
        message.getHeader().setSenderLocationID(null);
        message.getHeader().setTargetCompID(null);
        message.getHeader().setTargetSubID(null);
        message.getHeader().setTargetLocationID(null);
    }

    private void rejectMessage(String text, FIXMsg message) throws InterruptedException {
//        try {
//            RejectMsg reject = MessageFiller.buildRejectMsg(server, message, SessionRejectReason.Other, new Exception(text));
//            server.sendProtocolMessage(reject);
//        } catch (InvalidMsgException ex) {
//            server.onAlertEvent(new AlertEvent(this,
//                    new Alert(new Date(),
//                            getName(),
//                            BaseSeverityType.FATAL,
//                            FixServer.COMPONENT_NAME,
//                            "Could not build Reject for message" + (message != null ? new String(message.getRawMessage()) : "null"),
//                            ex)));
//        }
    }

    private class RouterRequestWorker extends Thread {

        private volatile boolean active;
        private volatile boolean shutdown;

        public RouterRequestWorker(String name) {
            super(name);
        }

        @Override
        public void run() {
            LOGGER.log(Level.INFO, "Router Request Worker [{0}] started.", getName());

            initialiseThreadSessionContextData();
            active = true;
            FIXMsg message = null;
            while (!shutdown) {
                try {
                    message = inboundQueue.take();
                    if (message != null) {
                        sendMessageToDestination(message);
                    }
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "Event processor thread {0} interrupted.", getName());
                } catch (Exception ex) {
                    try {
                        rejectMessage("Could not route message. Error was : " + ex.toString(), message);
                    } catch (InterruptedException ex1) {
                        LOGGER.log(Level.WARNING, "Router request worker timer task interrupted. Message was : {0}", ex1.toString());
                        break;
                    }
                }
            }

            active = false;

            LOGGER.log(Level.INFO, "Router Request Worker [{0}] stopped.", getName());
        }

        public void shutdown() {
            shutdown = true;
        }

        public boolean isActive() {
            return active;
        }

        private void sendMessageToDestination(FIXMsg message) throws InvalidMsgException, BadFormatMsgException, InterruptedException {
            CounterpartyAddress destination = MessageUtil.getDeliverToAddress(message);
            FixClient clientSession;
            if (clients.containsKey(destination)) {
                clientSession = clients.get(destination);
                if (clientSession != null) {
                    // check if the session is connected
//                    if (ProcessStatus.ACTIVE.equals(clientSession.getProcessStatus()) &&
//                            ProtocolState.LOGGEDON.equals(clientSession.getStateProcessor().getProcessingStage())) {
//                        if (!message.isDecoded()) {
//                            message.decode();
//                        }
//                        prepareMessageForRouting(message);
//                        clientSession.writeMessage(message);
//                    } else {
//                        rejectMessage("Route to " + destination.getID() + " destination not connected.", message);
//                    }
                } else {
                    rejectMessage("Route to " + destination.getID() + " destination not configured.", message);
                }
            } else {
                clientSession = searchForClientSession(destination);
                if (clientSession != null) {
                    // check if the session is connected
//                    if (ProcessStatus.ACTIVE.equals(clientSession.getProcessStatus()) &&
//                            ProtocolState.LOGGEDON.equals(clientSession.getStateProcessor().getProcessingStage())) {
//                        if (!message.isDecoded()) {
//                            message.decode();
//                        }
//                        prepareMessageForRouting(message);
//                        clientSession.writeMessage(message);
//                    } else {
//                        rejectMessage("Route to " + destination.getID() + " destination not connected.", message);
//                    }
                } else {
                    rejectMessage("Route to " + destination.getID() + " destination not configured.", message);
                }
                clients.put(destination, clientSession);
            }
        }

        private FixClient searchForClientSession(CounterpartyAddress dest) {
            FixClient client = null;
//            List<Coordinable> coordinators = server.getSessionCoordinator().getSessionCoordinators();
//            if (coordinators != null && !coordinators.isEmpty()) {
//                for (Coordinable coordinator : coordinators) {
//                    if (coordinator instanceof ClientSessionCoordinator) {
//                        if (dest.equals(coordinator.getSessionAddress().getRemoteAddress())) {
//                            client = (FIXClientOld) coordinator.getProtocol();
//                            client.setMessageRouter(getMessageRouter());
//                            break;
//                        }
//                    }
//                }
//            }
            return client;
        }
    }

    private class RoutingReplyWorker extends Thread {

        private volatile boolean active;
        private volatile boolean shutdown;

        public RoutingReplyWorker(String name) {
            super(name);
        }

        @Override
        public void run() {
            LOGGER.log(Level.INFO, "Routing Reply Worker [{0}] started.", getName());

            initialiseThreadSessionContextData();
            active = true;
            while (!shutdown) {
                FIXMsg message = null;
                try {
                    message = outboundQueue.take();
                    if (message != null) {
                        if (!message.isDecoded()) {
                            message.decode();
                        }
                        sendMessageToDestination(message);
                    }
                } catch (Exception ex) {
                    try {
                        rejectMessage("Could not decode the message. Error was : " + ex.toString(), message);
                    } catch (InterruptedException ex1) {
                        LOGGER.log(Level.WARNING, "Router reply worker timer task interrupted. Message was : {0}", ex1.toString());
                        break;
                    }
                }
            }

            active = false;

            LOGGER.log(Level.INFO, "Routing Reply Worker [{0}] stopped.", getName());
        }

        public void shutdown() {
            shutdown = true;
        }

        public boolean isActive() {
            return active;
        }

        private void sendMessageToDestination(FIXMsg message) throws InvalidMsgException {
            prepareMessageForRouting(message);
//            server.writeMessage(message);
        }
    }
}
