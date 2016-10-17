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

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.config.Configurator;
import net.hades.fix.engine.config.model.MsgTypeInfo;
import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.mgmt.data.*;
import net.hades.fix.engine.process.Commandable;
import net.hades.fix.engine.process.Manageable;
import net.hades.fix.engine.process.Reportable;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.event.EventProcessor;
import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;
import net.hades.fix.engine.process.protocol.router.MessageRouter;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.engine.process.session.Coordinable;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.engine.process.session.persist.FileSessSeqPersister;
import net.hades.fix.engine.process.session.persist.MemSessSeqPersister;
import net.hades.fix.engine.process.session.persist.SessSeqPersister;
import net.hades.fix.engine.process.transport.Transport;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgDirection;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.util.MsgUtil;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Defines the interface to be implemented by a FIX protocol.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.50 $
 */
public abstract class Protocol extends Thread implements Reportable, Manageable, Commandable {

    private static final Logger LOGGER = Logger.getLogger(Protocol.class.getName());

    private static final String SESSIONS_DATA_DIR = "data";

    private static final int DEFAULT_HEART_BT_INT               = 60;
    private static final int DEFAULT_LOGON_TIMEOUT              = 30;
    private static final int DEFAULT_LOGOUT_TIMEOUT             = 10;
    private static final String DEFAULT_ENCRYPTION_TYPE         = "0";
    private static final boolean DEFAULT_LATENCY_CHECK          = false;
    private static final int DEFAULT_MAX_LATENCY_THRES          = 30;
    private static final boolean DEFAULT_RST_SEQ_AT_LOGON       = false;
    private static final boolean DEFAULT_RST_SEQ_AT_LOGOUT      = false;
    private static final boolean DEFAULT_RST_SEQ_AT_DISCONN     = false;
    private static final boolean DEFAULT_PRINT_FIXML            = false;
    private static final boolean DEFAULT_VALID_IN_FIXML         = false;
    private static final boolean DEFAULT_VALID_OUT_FIXML        = false;
    private static final boolean DEFAULT_ABORT_VALID_ON_ERR     = false;
    private static final boolean DEFAULT_DISABLE_GAP_DETECTION  = false;
    private static final boolean ENABLE_RESEND_TIMEOUT          = false;
    private static final boolean DEFAULT_TEST_MSG_IND           = false;
    private static final boolean DEFAULT_PERSISTENCE            = false;
    private static final int DEFAULT_RX_BUFFER_SIZE             = 1000;
    private static final int DEFAULT_TX_BUFFER_SIZE             = 1000;
    private static final int DEFAULT_RESEND_TIMEOUT             = 3000;
    private static final double DEFAULT_HEARTBT_OFFSET_FRACTION = 0.1;

    protected SeqSet sessStartSeqSet;

    protected String sessConfigDir;

    protected ProtocolVersion protocolVersion;

    protected Transport transport;
    protected SessionCoordinator sessionCoordinator;
    protected SessionInfo configuration;

    protected BlockingQueue<FIXMsg> rxQueue;
    protected PriorityBlockingQueue<FIXMsg> txQueue;
    protected BlockingQueue<Command> commandQueue;

    protected EventProcessor eventProcessor;

    protected MessageHistoryCache historyCache;

    protected volatile boolean shutdown;

    protected String targetCompID;
    protected String targetSubID;
    protected String targetLocationID;

    protected MsgTypeGroup[] msgTypes;

    protected AtomicReference<ProcessStatus> processStatus;
    protected AtomicReference<ProtocolProcessData> mgmtData;
    protected AtomicReference<ProtocolStats> stats;

    protected boolean testSession;
    protected volatile boolean routingMode;

    protected SessSeqPersister seqNoPersister;

    protected MessageRouter messageRouter;

    protected StateProcessor stateProcessor;
    protected FixMessageSender messageSender;

    public Protocol(String name) throws ConfigurationException {
        super(name);
        historyCache = new MessageHistoryCache();
        processStatus = new AtomicReference<ProcessStatus>(ProcessStatus.INITIALISING);
        mgmtData = new AtomicReference<ProtocolProcessData>(new ProtocolProcessData());
        mgmtData.get().setStatus(ProcessStatus.INITIALISING);
        stats = new AtomicReference<ProtocolStats>(new ProtocolStats());
        commandQueue = new LinkedBlockingQueue<Command>();
    }

    /**
     * Initialise the protocol class.
     *
     * @throws ConfigurationException
     */
    public abstract void initialise() throws ConfigurationException;

    /**
     * Sends the message to the consumer stream for delivery.
     *
     * @param message FIX business message
     */
    public abstract void relayMessage(FIXMsg message);

    /**
     * Getter for the next RX sequence number. The engine RX sequence number is incremented and
     * persisted locally.
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
     * Getter for the next TX sequence number. The engine TX sequence number is incremented and
     * persisted locally.
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

    /**
     * Writes a message to the transport coming from the producer stream.
     * The message will be delivered only if there are non priority protocol messages outstanding.<p>
     * If the message parameter is null this method returns true.
     *
     * @param fixMsg fix message
     * @return false if it was delivered to the transport
     * @throws InvalidMsgException
     */
    public abstract boolean writeMessage(FIXMsg fixMsg) throws InvalidMsgException;

    @Override
    public void addLifeCycleListener(LifeCycleListener listener) {
        eventProcessor.addLifeCycleListener(listener);
    }

    @Override
    public void addAlertListener(AlertListener listener) {
        eventProcessor.addAlertListener(listener);
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        eventProcessor.addMessageListener(listener);
    }

    @Override
    public void execute(Command command) {
        try {
            commandQueue.put(command);
        } catch (InterruptedException ex) {
            String errMsg = "Protocol thread [" + getName() + "] has been unexpectedly interrupted.";
            LOGGER.log(Level.WARNING, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
        }
    }

    @Override
    public String retrieveSessionAddress() {
        return sessionCoordinator.getSessionAddress().toString();
    }

    @Override
    public ProcessStatus getProcessStatus() {
        return processStatus.get();
    }

    @Override
    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus.set(processStatus);
    }

    @Override
    public ProcessData getMgmtData() {
        mgmtData.get().setRxSeqNo(seqNoPersister.getRxSeqNo());
        mgmtData.get().setTxSeqNo(seqNoPersister.getTxSeqNo());

        return mgmtData.get();
    }

    @Override
    public Stats getStats() {
        return stats.get();
    }

    public void resetSequences() {
        setRxSeqNo(0);
        setTxSeqNo(0);
    }

    public EventProcessor getEventProcessor() {
        return eventProcessor;
    }

    public StateProcessor getStateProcessor() {
        return stateProcessor;
    }

    /**
     * Validates the incoming FIX message address.
     * @param message FIX message
     * @throws InvalidMsgException in case the address is invalid
     */
    public void validateIncomingMessageAddress(FIXMsg message) throws InvalidMsgException {
        if (message != null) {
            if (!targetCompID.equals(message.getHeader().getSenderCompID())) {
                String errMsg = "SenderCompID [" + message.getHeader().getSenderCompID() + "] does not matches the session configured [" +
                        getTargetCompID() + "]";
                LOGGER.severe(errMsg);
                throw new InvalidMsgException(errMsg);
            }
            if (targetSubID != null && (message.getHeader().getSenderSubID() == null ||
                    !targetSubID.equals(message.getHeader().getSenderSubID()))) {
                String errMsg = "SenderSubID [" + message.getHeader().getSenderSubID() + "] does not matches the session configured [" +
                        getTargetSubID() + "]";
                LOGGER.severe(errMsg);
                throw new InvalidMsgException(errMsg);
            }
            if (targetLocationID != null && (message.getHeader().getSenderLocationID() == null ||
                    !targetLocationID.equals(message.getHeader().getSenderLocationID()))) {
                String errMsg = "SenderLocationID [" + message.getHeader().getSenderLocationID() + "] does not matches the session configured [" +
                        getTargetLocationID() + "]";
                LOGGER.severe(errMsg);
                throw new InvalidMsgException(errMsg);
            }
        }
    }

    /**
     * Getter for the current TX sequence number. The engine TX sequence number is not incremented and
     * persisted locally.
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
     * Reads a FIX message from the buffer. This method blocks until
     * a new message is available.
     *
     * @return FIX message as a byte array
     */
    public FIXMsg readMessage() {
        FIXMsg message = null;
        try {
            message = rxQueue.take();
        } catch (InterruptedException ex) {
            LOGGER.log(Level.WARNING, "Reading thread interrupted.");
        }
        return message;
    }

//    /**
//     * Reads a FIX message from the buffer. This method blocks until
//     * a new message is available.
//     * @param timeout timeout interval in seconds
//     * @return FIX message as a byte array
//     */
//    public FIXMsg readMessage(int timeout) {
//        FIXMsg fixMessage = null;
//        try {
//            fixMessage = rxQueue.poll(timeout, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException ex) {
//            LOGGER.log(Level.SEVERE, "Reading thread interrupted. Error was : {1}", new Object[]{ex.getMessage()});
//        }
//
//        return fixMessage;
//    }

    public BlockingQueue<FIXMsg> getTxQueue() {
        return txQueue;
    }

    /**
     * Gets the ApplVerID for a message type based on the configuration and session settings.
     *
     * @param messageType message type
     * @param direction   direction (send or receive). null means any direction
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

    public Coordinable getSessionCoordinator() {
        return (Coordinable) sessionCoordinator;
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
        return sessionCoordinator.getCptyID();
    }

    /**
     * Local party identifier string.
     *
     * @return counterparty string as COMPID.SUBID.LOCATIONID
     */
    public String getLocalID() {
        return sessionCoordinator.getLocalID();
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

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public String buildOutboundLogMessage(FIXMsg fixMsg, byte[] rawMsg) {
        return ">>> " + getLocalID() + " - " + getCptyID() + " (" + fixMsg.getHeader().getMsgSeqNum() + ") "
                + MsgType.displayName(fixMsg.getHeader().getMsgType()) + " " + MsgUtil.getPrintableRawFixMessage(rawMsg);
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
            } else {
                if (configuration.getTestMessageIndicator()) {
                    setTestSession(true);
                }
            }
            if (configuration.getMaxMsgLen() != null) {
                getStateProcessor().setMaxMsgSize(configuration.getMaxMsgLen());
            }

            targetCompID = sessionCoordinator.getSessionAddress().getRemoteAddress().getCompID();
            targetLocationID = sessionCoordinator.getSessionAddress().getRemoteAddress().getLocationID();
            targetSubID = sessionCoordinator.getSessionAddress().getRemoteAddress().getSubID();
        } catch (Exception ex) {
            String error = "Error configuring the FIXClient process.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(error, ex);
        }
    }

    protected void setSessionProtocolVersion() throws ConfigurationException {
        BeginString beginString = MsgUtil.getBeginStringFromString(configuration.getFixVersion());
        if (MsgUtil.compare(beginString, BeginString.FIXT_1_1) >= 0) {
            if (configuration.getDefaultApplVerID() == null) {
                String error = "For FIXT.1.1 version defaultApplVerID config value is required.";
                LOGGER.severe(error);
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
                LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
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
//                String errMsg = "Could not create session config dir [" + sessConfigDirFile.getPath() + "] for session ["
//                        + sessionCoordinator.getName() + "].";
//                LOGGER.severe(errMsg);
//                throw new ConfigurationException(errMsg);
            }
        }
        sessConfigDir = configDir + File.separator + SESSIONS_DATA_DIR + File.separator +
                sessionCoordinator.getSessionAddress().getRemoteAddress().getID() + "_" +
                sessionCoordinator.getSessionAddress().getLocalAddress().getID();
        sessConfigDirFile = new File(sessConfigDir);
        if (!sessConfigDirFile.exists()) {
            if (!sessConfigDirFile.mkdir()) {
//                String errMsg = "Could not create session config dir [" + sessConfigDirFile.getPath() + "] for session ["
//                        + sessionCoordinator.getName() + "].";
//                LOGGER.severe(errMsg);
//                throw new ConfigurationException(errMsg);
            }
        }
    }

    protected void createSeqNoPersister() throws ConfigurationException {
        if (configuration.getPersistence()) {
            seqNoPersister = new FileSessSeqPersister(sessConfigDir, configuration.getResetSeqAtStartup());
        } else {
            seqNoPersister = new MemSessSeqPersister(sessConfigDir, configuration.getResetSeqAtStartup());
        }
    }

    protected Protocol getProtocol() {
        return this;
    }

}
