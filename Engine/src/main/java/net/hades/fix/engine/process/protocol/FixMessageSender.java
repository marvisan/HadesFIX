/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixMessageSender.java
 *
 * $Id: FixMessageSender.java,v 1.5 2011-04-30 04:39:45 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProtocolStats;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.transport.Transport;
import net.hades.fix.engine.util.MessageUtil;
import net.hades.fix.engine.util.ThreadLocalSessionDataUtil;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Message;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread that reads the messages from the TX queue which is filled by the producer stream.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 */
public class FixMessageSender extends Thread {

    private static final Logger LOGGER = Logger.getLogger(FixMessageSender.class.getName());

    private static final String COMPONENT_SUFFIX = "_MSG_SENDER";

    private volatile boolean shutdown;
    private volatile boolean blocked;
    private volatile boolean active;

    private Protocol protocol;

    private Transport transport;

    public FixMessageSender(String name, Protocol protocol, Transport transport) {
        super(name + COMPONENT_SUFFIX);
        this.protocol = protocol;
        this.transport = transport;
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Msg sender thread [{0}] started.", getName());

        initialiseThreadSessionContextData();
        FIXMsg fixMsg = null;
        while (!shutdown) {
            if (!blocked) {
                active = true;
                if (getTransport() != null) {
                    try {
                        fixMsg = protocol.getTxQueue().take();
                        if (fixMsg != null) {
                            if (fixMsg.getHeader() != null) {
                                if (fixMsg.getPriority() == Message.PRIORITY_NORMAL) {
                                    fixMsg.setHeader(HeaderFiller.fillHeaderUnfilled(protocol, fixMsg.getHeader()));
                                    protocol.getHistoryCache().addMessage(fixMsg);
                                    protocol.processAdminMessage(fixMsg);
                                }
                                byte[] rawMessage = fixMsg.encode();
                                if (MessageUtil.isAdminMessage(fixMsg)) {
                                    if (LOGGER.isLoggable(Level.INFO)) {
                                        LOGGER.info(protocol.buildOutboundLogMessage(fixMsg, rawMessage));
                                    }
                                } else {
                                    if (LOGGER.isLoggable(Level.FINE)) {
                                        LOGGER.log(Level.FINE, "Sequences : RX ({0}) , TX ({1})", new Object[]{protocol.getRxSeqNo() + 1, protocol.getTxSeqNo()});
                                        LOGGER.fine(protocol.buildOutboundLogMessage(fixMsg, rawMessage));
                                    }
                                }
                                transport.writeMessage(rawMessage);
                                protocol.getStateProcessor().getTimers().startHeartbeatTask();
                                ((ProtocolStats) protocol.getStats()).incrTotMsgOutCount();
                            } else {
                                String logMsg = "Received message has a null header : " + fixMsg.toString();
                                LOGGER.log(Level.SEVERE, "{0}. Msg was : {1}", new Object[]{logMsg, fixMsg.toString()});
                                protocol.getEventProcessor().onAlertEvent(new AlertEvent(this,
                                        Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                                                BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, logMsg, null)));
                            }
                        } else {
                            ThreadUtil.sleep(1);
                        }
                    } catch (TagNotPresentException ex) {
                        String logMsg = "Received message is missing a required tag. Message : " + fixMsg.toString();
                        LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{logMsg, ExceptionUtil.getStackTrace(ex)});
                        protocol.getEventProcessor().onAlertEvent(new AlertEvent(this,
                                Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                                        BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, logMsg, null)));
                    } catch (BadFormatMsgException ex) {
                        String logMsg = "Received message is in bad FIX format. Message : " + fixMsg.toString();
                        LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{logMsg, ExceptionUtil.getStackTrace(ex)});
                        protocol.getEventProcessor().onAlertEvent(new AlertEvent(this,
                                Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                                        BaseSeverityType.RECOVERABLE, AlertCode.MSG_FORMAT_ERROR, logMsg, null)));
                    } catch (InterruptedException ex) {
                        LOGGER.log(Level.WARNING, "Protocol sending message thread [{0}] interrupted.", getName());
                    }
                } else {
                    ThreadUtil.sleep(1);
                }
            } else {
                active = false;
                ThreadUtil.sleep(1);
            }
        }
        active = false;

        LOGGER.log(Level.INFO, "Msg sender thread [{0}] destroyed.", getName());
    }

    public void shutdown() {
        shutdown = true;
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized Transport getTransport() {
        return transport;
    }

    public synchronized void setTransport(Transport transport) {
        this.transport = transport;
    }

    public void initialiseThreadSessionContextData() {
        ThreadLocalSessionDataUtil.setThreadLocalSessionData(protocol.getConfiguration(), protocol.getVersion());
    }

}
