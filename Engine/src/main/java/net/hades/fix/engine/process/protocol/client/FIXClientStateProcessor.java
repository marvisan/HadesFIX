/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXClientStateProcessor.java
 *
 * $Id: FIXClientStateProcessor.java,v 1.4 2011-04-04 05:41:33 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.client;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.Protocol;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.state.*;
import net.hades.fix.engine.process.transport.Transport;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Incoming messages processing thread.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 */
public class FIXClientStateProcessor extends StateProcessor {

    private static final Logger LOGGER = Logger.getLogger(FIXClientStateProcessor.class.getName());

    public FIXClientStateProcessor(String name, Protocol protocol, Transport transport) {
        super(name, protocol, transport);
        processingStage.set(ProtocolState.INITIALISED);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Client state processor thread [{0}] started.", getName());

        initialiseThreadSessionContextData();
        startTimeoutTimers();
        while (!shutdown) {
            try {
                if (!blocked) {
                    active = true;
                    if (status != null) {
                        lock.lock();
                        try {
                            status.setProcessingStage(processingStage.get());
                            status = status.process();
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        Thread.sleep(1);
                    }
                } else {
                    Thread.sleep(1);
                    active = false;
                }
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Client state processor thread [{0}] interrupted.", getName());
            } catch (UnrecoverableException ex) {
                LOGGER.log(Level.SEVERE, "Unrecoverable error occurred : {0}", ExceptionUtil.getStackTrace(ex));
                protocol.getEventProcessor().onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                                BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
                protocol.getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(this,
                        LifeCycleType.PROTOCOL_CLIENT.name(),
                        LifeCycleCode.FIX_SESSION_SHUTDOWN.name()));
                protocol.getSessionCoordinator().execute(new Command(CommandType.ShutdownNow));
            } catch (Exception ex) {
                protocol.getEventProcessor().onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                                BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
                LOGGER.log(Level.SEVERE, "Unexpected error occurred : {0}", ExceptionUtil.getStackTrace(ex));
                protocol.getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(this,
                        LifeCycleType.PROTOCOL_CLIENT.name(),
                        LifeCycleCode.FIX_SESSION_SHUTDOWN.name()));
                protocol.getSessionCoordinator().execute(new Command(CommandType.ShutdownNow));
            }
        }
        timers.shutdown();
        active = false;

        LOGGER.log(Level.INFO, "Client state processor thread [{0}] stopped.", getName());
    }

    @Override
    public Status getStatus(ProtocolState protocolState) {
        if (protocolState == null) {
            throw new IllegalArgumentException("Protocol state parameter cannot be null");
        }
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Getting state : {0}", protocolState.name());
        }

        lock.lock();
        Status result = statePool.get(protocolState);
        try {
            if (result == null) {
                if (LOGGER.isLoggable(Level.FINEST)) {
                    LOGGER.log(Level.FINEST, "State : {0} not in pool. Build it.", protocolState.name());
                }

                switch (protocolState) {
                    case PROCESSING:
                        result = new ProcessingClientStatus(this);
                        result.setMaxMsgSize(this.getMaxMsgSize());
                        break;

                    case LOGON_SEND:
                        result = new LogonSendClientStatus(this);
                        break;

                    case LOGON_RECEIVE:
                        result = new LogonReceiveClientStatus(this);
                        break;

                    case LOGON_RESET_SEQ_NUM_SEND:
                        result = new LogonResetSeqNumSendStatus(this);
                        break;

                    case LOGOUT_SEND:
                        result = new LogoutSendClientStatus(this);
                        break;

                    case LOGOUT_RECEIVE:
                        result = new LogoutReceiveClientStatus(this);
                        break;

                    case HEARTBEAT_RECEIVE:
                        result = new HeartbeatReceiveStatus(this);
                        break;

                    case HEARTBEAT_SEND:
                        result = new HeartbeatSendStatus(this);
                        break;

                    case REJECT_RECEIVE:
                        result = new RejectReceiveStatus(this);
                        break;

                    case REJECT_SEND:
                        result = new RejectSendStatus(this);
                        break;

                    case RESEND_GAP_MESSAGES:
                        result = new ResendGapMessagesStatus(this);
                        break;

                    case RESEND_REQUEST_RECEIVE:
                        result = new ResendRequestReceiveStatus(this);
                        break;

                    case RESEND_REQUEST_SEND:
                        result = new ResendRequestSendStatus(this);
                        break;

                    case SEQUENCE_RESET_RECEIVE:
                        result = new SequenceResetReceiveStatus(this);
                        break;

                    case SEQUENCE_RESET_SEND:
                        result = new SequenceResetSendStatus(this);
                        break;

                    case TEST_REQUEST_RECEIVE:
                        result = new TestRequestReceiveStatus(this);
                        break;

                    case TEST_REQUEST_SEND:
                        result = new TestRequestSendStatus(this);
                        break;

                    case IDLE:
                        result = new IdleStatus(this);
                        break;
                }
                statePool.put(protocolState, result);
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

}
