/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

 /*
 * Protocol.java
 *
 * $Id: Protocol.java,v 1.50 2011-04-07 09:57:51 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.MessageEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.protocol.server.FixServer;
import net.hades.fix.engine.process.protocol.timer.Timeouts;
import net.hades.fix.engine.util.MessageUtil;
import net.hades.fix.message.Message;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.config.Configurator;
import net.hades.fix.engine.config.model.MsgTypeInfo;
import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.process.protocol.router.MessageRouter;
import net.hades.fix.engine.process.protocol.timer.TimersHolder;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.engine.process.session.persist.FileSessSeqPersister;
import net.hades.fix.engine.process.session.persist.MemSessSeqPersister;
import net.hades.fix.engine.process.session.persist.SessSeqPersister;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.HeartbeatMsg;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.TestRequestMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgDirection;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.util.MsgUtil;

/**
 * Defines the interface to be implemented by a FIX protocol.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class Protocol implements Handler {

    private static final Logger Log = Logger.getLogger(Protocol.class.getName());

    private static final String SESSIONS_DATA_DIR = "data";
    // max clock deviance is 2 minutes
    public static final long MAX_CLOCK_DEVIANCE_MILLIS = 2 * 60 * 1000;

    private static final int DEFAULT_HEART_BT_INT = 60;
    private static final int DEFAULT_LOGON_TIMEOUT = 30;
    private static final int DEFAULT_LOGOUT_TIMEOUT = 10;
    private static final String DEFAULT_ENCRYPTION_TYPE = "0";
    private static final boolean DEFAULT_LATENCY_CHECK = false;
    private static final int DEFAULT_MAX_LATENCY_THRES = 30;
    private static final boolean DEFAULT_RST_SEQ_AT_LOGON = false;
    private static final boolean DEFAULT_RST_SEQ_AT_LOGOUT = false;
    private static final boolean DEFAULT_RST_SEQ_AT_DISCONN = false;
    private static final boolean DEFAULT_PRINT_FIXML = false;
    private static final boolean DEFAULT_VALID_IN_FIXML = false;
    private static final boolean DEFAULT_VALID_OUT_FIXML = false;
    private static final boolean DEFAULT_ABORT_VALID_ON_ERR = false;
    private static final boolean DEFAULT_DISABLE_GAP_DETECTION = false;
    private static final boolean ENABLE_RESEND_TIMEOUT = false;
    private static final boolean DEFAULT_TEST_MSG_IND = false;
    private static final boolean DEFAULT_PERSISTENCE = false;
    private static final int DEFAULT_RX_BUFFER_SIZE = 1000;
    private static final int DEFAULT_TX_BUFFER_SIZE = 1000;
    private static final int DEFAULT_RESEND_TIMEOUT = 3000;
    private static final double DEFAULT_HEARTBT_OFFSET_FRACTION = 0.1;

    protected String id;
    protected volatile TaskStatus status;
    protected volatile ProtocolState protocolState;
    protected volatile ProcessingStage processingStage;
    protected SeqSet sessStartSeqSet;
    protected String sessConfigDir;

    protected ProtocolVersion protocolVersion;
    protected long maxMsgSize;

    protected SessionCoordinator coordinator;
    protected SessionInfo configuration;
    protected final ConcurrentMap<String, String> statistics;
    protected final ConcurrentMap<String, Handler> nextHandlers;
    protected final ConcurrentMap<String, Integer> lastSentSeqNo;

    protected BlockingQueue<Message> rxQueue;
    protected MessageHistoryCache historyCache;
    protected SeqGap gap;
    protected ResendRequestMsg gapRequestMessage;

    protected volatile boolean shutdown;

    protected String targetCompID;
    protected String targetSubID;
    protected String targetLocationID;

    protected TimersHolder timers;

    protected MsgTypeGroup[] msgTypes;

    protected boolean testSession;
    protected volatile boolean routingMode;

    protected SessSeqPersister seqNoPersister;
    protected MessageRouter messageRouter;

    protected Handler transportOut;
    protected MessageHistoryCache resequencingBuffer;

    private final BiFunction<String, String, String> sumStats = (String old, String cur) -> {
	return String.valueOf(Integer.valueOf(old) + Integer.valueOf(cur));
    };

    public Protocol(SessionCoordinator coordinator, SessionInfo configuration) throws ConfigurationException {
	this.coordinator = coordinator;
	this.configuration = configuration;
	nextHandlers = new ConcurrentHashMap<>();
	historyCache = new MessageHistoryCache();
	statistics = new ConcurrentHashMap<>();
	lastSentSeqNo = new ConcurrentHashMap<>();
	resequencingBuffer = new MessageHistoryCache();
	processingStage = ProcessingStage.INITIALISED;
	status = TaskStatus.New;
    }

    /**
     * Writes a message (Binary or FIX) to the transport. If FIX then writes the message header Seq and Date.
     *
     * @param msg
     * @throws net.hades.fix.message.exception.TagNotPresentException
     * @throws net.hades.fix.message.exception.BadFormatMsgException
     * @throws java.lang.InterruptedException
     */
    public abstract void writeToTransport(Message msg) throws TagNotPresentException, BadFormatMsgException, InterruptedException;

    /**
     * Sends the message to the consumer stream for delivery.
     *
     * @param message FIX business message
     */
    public abstract void relayMessage(FIXMsg message);

    /**
     * Getter for the next RX sequence number. The engine RX sequence number is incremented and persisted locally.
     *
     * @return next seq number
     */
    public abstract int getNextRxSeqNo();

    /**
     * Sets the current RX sequence number and writes it to the sequence number file.
     *
     * @param rxSeqNo new seq number
     */
    public abstract void setRxSeqNo(int rxSeqNo);

    /**
     * Getter for the next TX sequence number. The engine TX sequence number is incremented and persisted locally.
     *
     * @return next seq number
     */
    public abstract int getNextTxSeqNo();

    /**
     * Sets the TX sequence number and writes it to the sequence number file.
     *
     * @param txSeqNo new tx sequence number
     */
    public abstract void setTxSeqNo(int txSeqNo);

    protected abstract void setNeedsRouting(FIXMsg fixMsg);

    @Override
    public void addNextHandler(String id, Handler next) {
	nextHandlers.putIfAbsent(id, next);
    }

    @Override
    public TaskStatus getStatus() {
	return status;
    }

    @Override
    public String getId() {
	return id;
    }

    @Override
    public void write(Message message) {
	try {
	    rxQueue.put(message);
	} catch (InterruptedException ex) {
	    Log.log(Level.SEVERE, "Protocol [{0}] write() interrupted", id);
	}
    }

    @Override
    public boolean tryWrite(Message message, int waitMillis) {
	try {
	    return rxQueue.offer(message, 1, TimeUnit.SECONDS);
	} catch (InterruptedException ex) {
	    Log.log(Level.SEVERE, "Protocol [{0}] tryWrite() interrupted", id);
	}
	return true;
    }

    public SessionCoordinator getSessionCoordinator() {
	return coordinator;
    }

    public ProcessingStage getProcessingStage() {
	return processingStage;
    }

    public TimersHolder getTimers() {
	return timers;
    }

    public MessageHistoryCache getResequencingBuffer() {
	return resequencingBuffer;
    }

    public SeqGap getGap() {
	return gap;
    }

    public void setGap(SeqGap gap) {
	this.gap = gap;
    }
    
    public void addLastSeqNo(String msgType, Integer seqValue) {
	lastSentSeqNo.put(msgType, seqValue);
    }
    
    public int getLastSeqNo(String msgType) {
	return lastSentSeqNo.getOrDefault(msgType, 0);
    }

    public void resetSequences() {
	setRxSeqNo(0);
	setTxSeqNo(0);
    }

    /**
     * Validates the incoming FIX message address.
     *
     * @param message FIX message
     * @throws InvalidMsgException in case the address is invalid
     */
    public void validateIncomingMessageAddress(FIXMsg message) throws InvalidMsgException {
	if (message != null) {
	    if (!targetCompID.equals(message.getHeader().getSenderCompID())) {
		String errMsg = "SenderCompID [" + message.getHeader().getSenderCompID() + "] does not matches the session configured ["
			+ getTargetCompID() + "]";
		Log.severe(errMsg);
		throw new InvalidMsgException(errMsg);
	    }
	    if (targetSubID != null && (message.getHeader().getSenderSubID() == null
		    || !targetSubID.equals(message.getHeader().getSenderSubID()))) {
		String errMsg = "SenderSubID [" + message.getHeader().getSenderSubID() + "] does not matches the session configured ["
			+ getTargetSubID() + "]";
		Log.severe(errMsg);
		throw new InvalidMsgException(errMsg);
	    }
	    if (targetLocationID != null && (message.getHeader().getSenderLocationID() == null
		    || !targetLocationID.equals(message.getHeader().getSenderLocationID()))) {
		String errMsg = "SenderLocationID [" + message.getHeader().getSenderLocationID() + "] does not matches the session configured ["
			+ getTargetLocationID() + "]";
		Log.severe(errMsg);
		throw new InvalidMsgException(errMsg);
	    }
	}
    }

    /**
     * Getter for the current TX sequence number. The engine TX sequence number is not incremented and persisted locally.
     *
     * @return next seq number
     */
    public int getTxSeqNo() {
	return seqNoPersister.getTxSeqNo();
    }

    /**
     * Returns the current RX sequence number.
     *
     * @return RX sequence number
     */
    public int getRxSeqNo() {
	return seqNoPersister.getRxSeqNo();
    }

    /**
     * Gets the ApplVerID for a message type based on the configuration and session settings.
     *
     * @param messageType message type
     * @param direction direction (send or receive). null means any direction
     * @return application version ID
     */
    public String getMessageSessionApplVerID(MsgType messageType, MsgDirection direction) {
	String result = protocolVersion.getApplVerID().getValue();
	if (msgTypes != null && msgTypes.length > 0) {
	    for (MsgTypeGroup msgType : msgTypes) {
		if (msgType.getRefMsgType().equals(messageType.getValue())) {
		    if (direction != null && msgType.getMsgDirection() != null) {
			if (!msgType.getMsgDirection().equals(direction)) {
			    continue;
			}
		    }
		    if (msgType.getRefApplVerID() != null) {
			result = msgType.getRefApplVerID().getValue();
		    }
		    if (msgType.getRefCstmApplVerID() != null) {
			result = msgType.getRefCstmApplVerID();
		    }
		    break;
		}
	    }
	}
	return result;
    }

    /**
     * Gets the current session FIX version.
     *
     * @return version data
     */
    public ProtocolVersion getVersion() {
	return protocolVersion;
    }

    public boolean isRoutingMode() {
	return routingMode;
    }

    public synchronized void setMessageRouter(MessageRouter messageRouter) {
	this.messageRouter = messageRouter;
	this.routingMode = true;
    }

    /**
     * Return the RX/TX sequences set at the beginning of the session.
     *
     * @return RX/TX seq
     */
    public SeqSet getSessStartSeqSet() {
	return sessStartSeqSet;
    }

    public void setSessStartSeqSet(SeqSet sessStartSeqSet) {
	this.sessStartSeqSet = sessStartSeqSet;
    }

    /**
     * Returns true if this session is setup as a test session.
     *
     * @return true if it is a test session
     */
    public boolean isTestSession() {
	return testSession;
    }

    /**
     * Sets the <code>TestSession</code> flag.
     *
     * @param testSession test session flag
     */
    public void setTestSession(boolean testSession) {
	this.testSession = testSession;
    }

    /**
     * Gets the history of sent messages cache.
     *
     * @return cache with messages sent
     */
    public MessageHistoryCache getHistoryCache() {
	return historyCache;
    }

    /**
     * Getter for the counterparty COMP Id.
     *
     * @return counterparty COMP Id
     */
    public String getTargetCompID() {
	return targetCompID;
    }

    /**
     * Getter for the counterparty Location Id.
     *
     * @return counterparty Location Id
     */
    public String getTargetLocationID() {
	return targetLocationID;
    }

    /**
     * Getter for the counterparty Sub Id.
     *
     * @return counterparty Sub Id
     */
    public String getTargetSubID() {
	return targetSubID;
    }

    /**
     * Counterparty identifier string.
     *
     * @return counterparty string as COMPID.SUBID.LOCATIONID
     */
    public String getCptyID() {
	return coordinator.getCptyID();
    }

    /**
     * Local party identifier string.
     *
     * @return counterparty string as COMPID.SUBID.LOCATIONID
     */
    public String getLocalID() {
	return coordinator.getLocalID();
    }

    /**
     * Getter for the configuration protocol.
     *
     * @return configuration data
     */
    public SessionInfo getConfiguration() {
	return configuration;
    }

    public MsgTypeGroup[] getMsgTypes() {
	return msgTypes;
    }

    public void setMsgTypes(MsgTypeGroup[] msgTypes) {
	this.msgTypes = msgTypes;
    }

    public void setRoutingMode(boolean routingMode) {
	this.routingMode = routingMode;
    }

    /**
     * Gets the message router.
     *
     * @return message router
     */
    protected abstract MessageRouter getMessageRouter();

    protected abstract void processAdminMessage(FIXMsg fixMsg) throws TagNotPresentException, BadFormatMsgException;

    /**
     * Resets the data structures and prepares the protocol layer for a new Logon exchange.
     */
    protected void reset() {
	getStateProcessor().setGap(null);
	getStateProcessor().setGapRequestMessage(null);
	getStateProcessor().setProcessingStage(ProcessingStage.INITIALISED);
	getStateProcessor().resetAllTimerTasks();
    }

    protected void updateLastSentSeqNo(FIXMsg fixMsg) {
	if (fixMsg instanceof HeartbeatMsg) {
	    lastSentSeqNo.put(MsgType.Heartbeat.getValue(), fixMsg.getHeader().getMsgSeqNum());
	}
    }

    protected FIXMsg decodeAndValidate(byte[] byteFixMsg) throws UnrecoverableException, RejectMessageException {
	FIXMsg msg = null;
	try {
	    if (byteFixMsg != null && byteFixMsg.length > 0) {
		statistics.merge("MSG_IN", "1", sumStats);
		if (maxMsgSize > 0 && byteFixMsg.length > maxMsgSize) {
		    // discard messages larger than maxMsgSize
		    Log.log(Level.WARNING, "Discarded message with size [{0}].", byteFixMsg.length);
		    statistics.merge("MSG_REJ", "1", sumStats);
		    return null;
		}
		msg = FIXMsgBuilder.build(byteFixMsg);

		if (MessageUtil.isAdminMessage(msg)) {
		    if (Log.isLoggable(Level.INFO)) {
			Log.info(buildInboundLogMessage(msg));
		    }
		} else if (Log.isLoggable(Level.FINE)) {
		    Log.log(Level.FINE, "Sequences : RX ({0}) , TX ({1})", new Object[]{getRxSeqNo() + 1, getTxSeqNo() + 1});
		    Log.fine(buildInboundLogMessage(msg));
		}
		try {
		    validateIncomingMessageAddress(msg);
		} catch (InvalidMsgException ex) {
		    // at this stage we reject
		    coordinator.onAlertEvent(new AlertEvent(this,
			    Alert.createAlert(id, FixServer.class.getSimpleName(),
				    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, ex.getMessage(), null)));

		    coordinator.onLifeCycleEvent(new LifeCycleEvent(this, LifeCycleType.PROTOCOL_SERVER.name(),
			    LifeCycleCode.FIX_SESSION_SHUTDOWN.name()));
		    throw new UnrecoverableException("Bad message address", ex);
		}
		if (!isMsgSendTimeValid(msg)) {
		    String errMsg = "SendingTime accuracy problem.";
		    Log.severe(errMsg);

		    statistics.merge("MSG_REJ", "1", sumStats);
		    coordinator.onMessageEvent(new MessageEvent(this, msg));
		    throw new RejectMessageException(msg, SessionRejectReason.SendingTimeAccuracyProblem);
		}
		setNeedsRouting(msg);
	    }
	    return msg;
	} catch (InvalidMsgException ex) {
	    String errMsg = "Invalid message : " + ExceptionUtil.getStackTrace(ex) + ". Received message : " + new String(byteFixMsg);
	    Log.severe(errMsg);
	    statistics.merge("MSG_REJ", "1", sumStats);
	    coordinator.onMessageEvent(new MessageEvent(this, msg));
	    throw new RejectMessageException(msg, SessionRejectReason.Other);
	} catch (TagNotPresentException ex) {
	    String errMsg = "Tag not found in message : " + ExceptionUtil.getStackTrace(ex) + ". Received message : " + new String(byteFixMsg);
	    Log.severe(errMsg);
	    statistics.merge("MSG_REJ", "1", sumStats);
	    coordinator.onMessageEvent(new MessageEvent(this, msg));
	    throw new RejectMessageException(msg, SessionRejectReason.UndefinedTag);
	} catch (BadFormatMsgException ex) {
	    String errMsg = "Message is in bad format : " + ExceptionUtil.getStackTrace(ex) + ". Received message : " + new String(byteFixMsg);
	    Log.severe(errMsg);
	    statistics.merge("MSG_REJ", "1", sumStats);
	    coordinator.onMessageEvent(new MessageEvent(this, msg));
	    throw new RejectMessageException(msg, ex.getRejectReason() != null ? ex.getRejectReason() : SessionRejectReason.Other);
	}
    }

    protected void setSessionConfigData() throws ConfigurationException {
	try {
	    if (configuration.getHeartBtInt() == null) {
		configuration.setHeartBtInt(DEFAULT_HEART_BT_INT);
	    }
	    if (configuration.getLogonTimeout() == null) {
		configuration.setLogonTimeout(DEFAULT_LOGON_TIMEOUT);
	    }
	    if (configuration.getLogoutTimeout() == null) {
		configuration.setLogoutTimeout(DEFAULT_LOGOUT_TIMEOUT);
	    }
	    if (configuration.getLatencyCheck() == null) {
		configuration.setLatencyCheck(DEFAULT_LATENCY_CHECK);
	    }
	    if (configuration.getLatencyCheck()) {
		if (configuration.getMaxLatencyTreshold() == null) {
		    configuration.setMaxLatencyTreshold(DEFAULT_MAX_LATENCY_THRES);
		}
	    }
	    if (configuration.getResetSeqAtStartup() == null) {
		configuration.setResetSeqAtStartup(Boolean.TRUE);
	    }
	    if (configuration.getResetSeqAtLogon() == null) {
		configuration.setResetSeqAtLogon(DEFAULT_RST_SEQ_AT_LOGON);
	    }
	    if (configuration.getResetSeqAtLogout() == null) {
		configuration.setResetSeqAtLogout(DEFAULT_RST_SEQ_AT_LOGOUT);
	    }
	    if (configuration.getResetSeqAtDisconnect() == null) {
		configuration.setResetSeqAtDisconnect(DEFAULT_RST_SEQ_AT_DISCONN);
	    }
	    if (configuration.getEncryption() != null) {
		if (configuration.getEncryption().getEncryptionType() == null) {
		    configuration.getEncryption().setEncryptionType(DEFAULT_ENCRYPTION_TYPE);
		}
	    }
	    if (configuration.getResetSeqAtDisconnect() == null) {
		configuration.setResetSeqAtDisconnect(DEFAULT_PRINT_FIXML);
	    }
	    if (configuration.getValidateIncomingFIXML() == null) {
		configuration.setValidateIncomingFIXML(DEFAULT_VALID_IN_FIXML);
	    }
	    if (configuration.getValidateOutgoingFIXML() == null) {
		configuration.setValidateOutgoingFIXML(DEFAULT_VALID_OUT_FIXML);
	    }
	    if (configuration.getAbortFIXMLValidationOnError() == null) {
		configuration.setAbortFIXMLValidationOnError(DEFAULT_ABORT_VALID_ON_ERR);
	    }
	    if (configuration.getRxBufferSize() == null) {
		configuration.setRxBufferSize(DEFAULT_RX_BUFFER_SIZE);
	    }
	    if (configuration.getTxBufferSize() == null) {
		configuration.setTxBufferSize(DEFAULT_TX_BUFFER_SIZE);
	    }
	    if (configuration.getDisableGapDetection() == null) {
		configuration.setDisableGapDetection(DEFAULT_DISABLE_GAP_DETECTION);
	    }
	    if (configuration.getEnableResendTimeout() == null) {
		configuration.setEnableResendTimeout(ENABLE_RESEND_TIMEOUT);
	    }
	    if (configuration.getPersistence() == null) {
		configuration.setPersistence(DEFAULT_PERSISTENCE);
	    }
	    if (configuration.getEnableResendTimeout()) {
		if (configuration.getResendTimeout() == null) {
		    configuration.setResendTimeout(DEFAULT_RESEND_TIMEOUT);
		}
	    }
	    if (configuration.getTestMessageIndicator() == null) {
		configuration.setTestMessageIndicator(DEFAULT_TEST_MSG_IND);
	    }
	    if (configuration.getHeartBtOffset() == null) {
		configuration.setHeartBtOffset((int) (DEFAULT_HEARTBT_OFFSET_FRACTION * (double) configuration.getHeartBtInt()));
	    }
	    if (configuration.getTestMessageIndicator() == null) {
		configuration.setTestMessageIndicator(Boolean.FALSE);
		setTestSession(false);
	    } else if (configuration.getTestMessageIndicator()) {
		setTestSession(true);
	    }
	    if (configuration.getMaxMsgLen() != null) {
		maxMsgSize = configuration.getMaxMsgLen();
	    }

	    targetCompID = coordinator.getSessionAddress().getRemoteAddress().getCompID();
	    targetLocationID = coordinator.getSessionAddress().getRemoteAddress().getLocationID();
	    targetSubID = coordinator.getSessionAddress().getRemoteAddress().getSubID();
	} catch (Exception ex) {
	    String error = "Error configuring the FIXClient process.";
	    Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
	    throw new ConfigurationException(error, ex);
	}
    }

    protected void setSessionProtocolVersion() throws ConfigurationException {
	BeginString beginString = MsgUtil.getBeginStringFromString(configuration.getFixVersion());
	if (MsgUtil.compare(beginString, BeginString.FIXT_1_1) >= 0) {
	    if (configuration.getDefaultApplVerID() == null) {
		String error = "For FIXT.1.1 version defaultApplVerID config value is required.";
		Log.severe(error);
		throw new ConfigurationException(error);
	    }
	    protocolVersion = new ProtocolVersion(beginString, ApplVerID.valueFor(configuration.getDefaultApplVerID()));
	} else {
	    try {
		protocolVersion = new ProtocolVersion(beginString, MsgUtil.getApplVerFromBeginString(beginString));
	    } catch (InvalidMsgException ex) {
		throw new ConfigurationException("Could not retrieve the AppVerId for BeginString [" + beginString + "].", ex);
	    }
	}

	protocolVersion.setApplExtID(configuration.getDefaultApplExtID());
	protocolVersion.setCstmApplVerID(configuration.getDefaultCstmApplVerID());
    }

    protected void setSupportedMsgTypes() throws ConfigurationException {
	if (configuration.getMsgTypes() != null && configuration.getMsgTypes().length > 0) {
	    try {
		ApplVerID applVerID = MsgUtil.getMsgFixVersion(protocolVersion.getBeginString(), protocolVersion.getApplVerID());
		msgTypes = new MsgTypeGroup[configuration.getMsgTypes().length];
		if (MsgUtil.compare(applVerID, ApplVerID.FIX42) >= 0) {
		    for (int i = 0; i < configuration.getMsgTypes().length; i++) {
			MsgTypeGroup group = MessageFiller.createMsgTypeGroupForVersion(this);
			MsgTypeInfo msgTypeInfo = configuration.getMsgTypes()[i];
			group.setRefMsgType(msgTypeInfo.getRefMsgType());
			if (msgTypeInfo.getMsgDirection() != null) {
			    group.setMsgDirection(MsgDirection.valueFor(msgTypeInfo.getMsgDirection().toUpperCase()));
			}
			if (MsgUtil.compare(applVerID, ApplVerID.FIX50) >= 0) {
			    group.setRefApplVerID(ApplVerID.valueFor(msgTypeInfo.getRefApplVerID()));
			    group.setRefCstmApplVerID(msgTypeInfo.getRefCstmApplVerID());
			}
			if (MsgUtil.compare(applVerID, ApplVerID.FIX50SP1) >= 0) {
			    group.setRefApplExtID(msgTypeInfo.getRefApplExtID());
			    group.setDefaultVerIndicator(msgTypeInfo.getDefaultVerIndicator());
			}
			msgTypes[i] = group;
		    }
		}
	    } catch (InvalidMsgException ex) {
		String error = "Could not create MsgTypeGroup for session configuration";
		Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
		throw new ConfigurationException(error, ex);
	    }
	}
    }

    protected void createSessionConfigDir() throws ConfigurationException {
	String configDir = Configurator.getConfigDir();
	sessConfigDir = configDir + File.separator + SESSIONS_DATA_DIR;
	File sessConfigDirFile = new File(sessConfigDir);
	if (!sessConfigDirFile.exists()) {
	    if (!sessConfigDirFile.mkdir()) {
		String errMsg = "Could not create session config dir [" + sessConfigDirFile.getPath() + "] for session ["
			+ coordinator.getSenderId() + "].";
		Log.severe(errMsg);
		throw new ConfigurationException(errMsg);
	    }
	}
	sessConfigDir = configDir + File.separator + SESSIONS_DATA_DIR + File.separator
		+ coordinator.getSessionAddress().getRemoteAddress().getID() + "_"
		+ coordinator.getSessionAddress().getLocalAddress().getID();
	sessConfigDirFile = new File(sessConfigDir);
	if (!sessConfigDirFile.exists()) {
	    if (!sessConfigDirFile.mkdir()) {
		String errMsg = "Could not create session config dir [" + sessConfigDirFile.getPath() + "] for session ["
			+ coordinator.getSenderId() + "].";
		Log.severe(errMsg);
		throw new ConfigurationException(errMsg);
	    }
	}
    }

    protected void createSeqNoPersister() throws ConfigurationException {
	if (configuration.getPersistence()) {
	    seqNoPersister = new FileSessSeqPersister(sessConfigDir, configuration.getResetSeqAtStartup());
	} else {
	    seqNoPersister = new MemSessSeqPersister(sessConfigDir, configuration.getResetSeqAtStartup());
	}
	sessStartSeqSet = new SeqSet(seqNoPersister.getRxSeqNo(), seqNoPersister.getTxSeqNo());
    }

    protected String buildInboundLogMessage(FIXMsg fixMsg) {
	StringBuilder sb = new StringBuilder("<<< ");
	sb.append(getCptyID()).append(" - ").append(getLocalID()).append(" (").append(fixMsg.getHeader().getMsgSeqNum());
	sb.append(") ").append(MsgType.displayName(fixMsg.getHeader().getMsgType())).append(" ").append(MsgUtil.getPrintableRawFixMessage(fixMsg.getRawMessage()));
	return sb.toString();
    }

    protected String buildOutboundLogMessage(FIXMsg fixMsg, byte[] rawMsg) {
	return ">>> " + getLocalID() + " - " + getCptyID() + " (" + fixMsg.getHeader().getMsgSeqNum() + ") "
		+ MsgType.displayName(fixMsg.getHeader().getMsgType()) + " " + MsgUtil.getPrintableRawFixMessage(rawMsg);
    }

    protected boolean isMsgSendTimeValid(FIXMsg fixMsg) {
	if (fixMsg != null) {
	    Date sendTime = fixMsg.getHeader().getSendingTime();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(sendTime);
	    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
	    long now = cal.getTimeInMillis();
	    if (Math.abs(now - sendTime.getTime()) > MAX_CLOCK_DEVIANCE_MILLIS) {
		return false;
	    }
	}
	return true;
    }

    protected void createTimeoutTimers() {
	Timeouts timeouts = new Timeouts();
	timeouts.setEnableResendTimeout(getConfiguration().getEnableResendTimeout());
	if (timeouts.isEnableResendTimeout()) {
	    timeouts.setResendTimeout(getConfiguration().getResendTimeout());
	}
	timeouts.setHtbtOffset(getConfiguration().getHeartBtOffset());
	timeouts.setHtbtTimeout(getConfiguration().getHeartBtInt());
	timeouts.setLogonTimeout(getConfiguration().getLogonTimeout());
	timeouts.setLogoutTimeout(getConfiguration().getLogoutTimeout());
	timeouts.setTestRequestTimeout((int) ((double) getConfiguration().getHeartBtInt() * 1.5d));
	timers = new TimersHolder(this, timeouts);
    }

}
