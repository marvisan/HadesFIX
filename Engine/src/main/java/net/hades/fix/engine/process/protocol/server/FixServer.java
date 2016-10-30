/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.server;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.config.model.ServerSessionInfo;
import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.SeqNoPersistenceException;
import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.*;
import net.hades.fix.engine.process.protocol.router.MessageRouter;
import net.hades.fix.engine.process.session.ServerSessionCoordinator;
import net.hades.fix.engine.util.PartyUtil;
import net.hades.fix.engine.util.ThreadLocalSessionDataUtil;
import net.hades.fix.message.BinaryMessage;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.LogoutMsg;
import net.hades.fix.message.Message;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.util.MsgUtil;

/**
 * Implementation of a FIX server process.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public final class FixServer extends Protocol {

    private static final Logger Log = Logger.getLogger(FixServer.class.getName());

    public static final String COMPONENT_NAME = "FIXSRV";

    public FixServer(ServerSessionCoordinator coordinator, ServerSessionInfo configuration) throws ConfigurationException {
	super(coordinator, configuration);

	setSessionConfigData();
	initialise();
	rxQueue = new ArrayBlockingQueue<>(configuration.getRxBufferSize());
	id = COMPONENT_NAME + "_" + PartyUtil.getID(targetCompID, targetLocationID, targetSubID);
	status = TaskStatus.New;
    }

    @Override
    public ExecutionResult call() throws Exception {
	Log.log(Level.INFO, "Running Fix Server thread [{0}].", id);

	status = TaskStatus.Running;
	protocolState = ProtocolState.IDLE;
	while (!shutdown) {
	    FIXMsg reqMsg = null;
	    try {
		BinaryMessage message = (BinaryMessage) rxQueue.poll(1, TimeUnit.SECONDS);
		if (message == null) {
		    continue;
		}
		reqMsg = decodeAndValidate(message.getRawPayload());
	    } catch (InterruptedException ex) {
		Log.log(Level.WARNING, "Fix Server thread [{0}] has been interrupted.", id);
		shutdown = true;
	    } catch (UnrecoverableException ex) {
		coordinator.shutdownImmediate();
	    } catch (RejectMessageException ex) {
		if (ProcessingStage.INITIALISED.equals(processingStage) || ProcessingStage.LOGGEDOUT.equals(processingStage)) {
		    // discard silently
		    continue;
		}
		FIXMsg rejMsg = null;
	    }
	    if (reqMsg == null) {
		continue;
	    }

	    try {
		switch (protocolState) {
		    case IDLE:

		}
	    } catch (Exception ex) {
		Log.log(Level.SEVERE, "Unexpected exception raised by Fix Server", ex);
		coordinator.onAlertEvent(new AlertEvent(this,
			Alert.createAlert(id, FixServer.class.getSimpleName(),
				BaseSeverityType.FATAL, AlertCode.SESSION_DESTROYED, ex.getMessage(), ex)));
	    }
	}
	status = TaskStatus.Running;
	return new ExecutionResult(status);
    }

    @Override
    public void setTimeoutSecs(int timeoutSecs) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map getStatistics() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void shutdownImmediate() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setSessionConfigData() throws ConfigurationException {
	super.setSessionConfigData();
    }

    @Override
    protected void initialise() throws ConfigurationException {
	setSessionProtocolVersion();
	setSupportedMsgTypes();
	createSessionConfigDir();
	createSeqNoPersister();
	ThreadLocalSessionDataUtil.setThreadLocalSessionData(configuration, protocolVersion);
	createTimeoutTimers();
    }

    @Override
    public void relayMessage(FIXMsg message) {
	if (isRoutingMode()) {
	    if (Log.isLoggable(Level.FINE)) {
		Log.log(Level.FINE, ">R> {0} - {1} ({2}) {3} {4}", new Object[]{getLocalID(), getCptyID(), message.getHeader().getMsgSeqNum(),
		    MsgType.displayName(message.getHeader().getMsgType()), MsgUtil.getPrintableRawFixMessage(message.getRawMessage())});
	    }
	    getMessageRouter().routeRequestMessage(message);
	} else {
	    while (!rxQueue.offer(message)) {
		ThreadUtil.sleep(1);
	    }
	}
    }

    @Override
    public int getNextRxSeqNo() {
	int rxSeqNo;
	try {
	    rxSeqNo = seqNoPersister.getNextRxSeqNo();
	} catch (SeqNoPersistenceException ex) {
	    rxSeqNo = seqNoPersister.getRxSeqNo();
	    coordinator.onAlertEvent(new AlertEvent(this, Alert.createAlert(id, FixServer.class.getSimpleName(),
		    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
	}
	return rxSeqNo;
    }

    @Override
    public void setRxSeqNo(int rxSeqNo) {
	try {
	    seqNoPersister.setRxSeqNo(rxSeqNo);
	} catch (SeqNoPersistenceException ex) {
	    coordinator.onAlertEvent(new AlertEvent(this, Alert.createAlert(id, FixServer.class.getSimpleName(),
		    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
	}
    }

    @Override
    public int getNextTxSeqNo() {
	int txSeqNo;
	try {
	    txSeqNo = seqNoPersister.getNextTxSeqNo();
	} catch (SeqNoPersistenceException ex) {
	    txSeqNo = seqNoPersister.getTxSeqNo();
	    coordinator.onAlertEvent(new AlertEvent(this, Alert.createAlert(id, FixServer.class.getSimpleName(),
		    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
	}

	return txSeqNo;
    }

    @Override
    public void setTxSeqNo(int txSeqNo) {
	try {
	    seqNoPersister.setTxSeqNo(txSeqNo);
	} catch (SeqNoPersistenceException ex) {
	    coordinator.onAlertEvent(new AlertEvent(this, Alert.createAlert(id, FixServer.class.getSimpleName(),
		    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.toString(), ex)));
	}
    }

    @Override
    public SessionInfo getConfiguration() {
	return configuration;
    }

    @Override
    public void processAdminMessage(FIXMsg fixMsg) throws TagNotPresentException, BadFormatMsgException {
	if (!ProcessingStage.LOGGEDON.equals(stateProcessor.getProcessingStage())) {
	    if (MsgType.Logon.getValue().equals(fixMsg.getHeader().getMsgType())) {
		// logon send by the business layer - reset Logon timer
		stateProcessor.getTimers().resetLogonTimeoutTask();
	    }
	}
	if (fixMsg.getHeader().getMsgType().equals(MsgType.Logon.getValue())) {
	    // this is a response Logon message coming from the business ties
	    stateProcessor.setProcessingStage(ProcessingStage.LOGGEDON);
	} else if (fixMsg.getHeader().getMsgType().equals(MsgType.Logout.getValue())) {
	    if (fixMsg.getPriority() == Message.PRIORITY_NORMAL) {
		stateProcessor.setStatus(ProtocolState.IDLE);
		String logMsg = "Logout message received from the business layer.";
		if (((LogoutMsg) fixMsg).getText() != null) {
		    logMsg = ((LogoutMsg) fixMsg).getText();
		}

		coordinator.onAlertEvent(new AlertEvent(this,
			Alert.createAlert(id, ComponentType.FIXServer.toString(),
				BaseSeverityType.INFO, AlertCode.LOGOUT_SEND, logMsg, null)));

		((LogoutReceiveServerStatus) stateProcessor.getStatus(ProtocolState.LOGOUT_RECEIVE)).setExpected(true);
		stateProcessor.getTimers().startLogoutTimerTask();
		stateProcessor.setStatus(ProtocolState.PROCESSING);
		Log.severe(logMsg);
	    }
	}
    }

    @Override
    protected MessageRouter getMessageRouter() {
	if (messageRouter == null) {
	    // creates and starts the message router
	    messageRouter = new MessageRouter(this, getLocalID() + "_MSG_ROUTER");
	    messageRouter.start();
	    messageRouter.startup();
	    while (!messageRouter.isActive()) {
		if (!ThreadUtil.sleep(1)) {
		    break;
		}
	    }
	    messageRouter.initialiseThreadSessionContextData();
	}

	return messageRouter;
    }

    @Override
    protected void setNeedsRouting(FIXMsg fixMsg) {
	if (!isRoutingMode()) {
	    if (fixMsg.getHeader().getDeliverToCompID() != null && !fixMsg.getHeader().getDeliverToCompID().isEmpty()) {
		setRoutingMode(true);
	    }
	}
    }

    @Override
    public boolean writeMessage(FIXMsg fixMsg) throws InvalidMsgException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void shutdown() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
