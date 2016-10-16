/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LoginStatus.java
 *
 * $Id: LogonReceiveServerStatus.java,v 1.29 2011-04-03 08:00:05 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.server;

import net.hades.fix.commons.security.PasswordBank;
import net.hades.fix.engine.config.model.ServerSessionInfo;
import net.hades.fix.engine.exception.LogonException;
import net.hades.fix.engine.exception.UnrecoverableException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.MessageEvent;
import net.hades.fix.engine.process.event.type.LifeCycleCode;
import net.hades.fix.engine.process.event.type.LifeCycleType;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.ProcessingStage;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.SeqGap;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.engine.process.protocol.state.Status;
import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SessionStatus;
import net.hades.fix.message.util.MsgUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implements the FIX client login receive state in client side.<br/>
 * The outcome states are:
 * <ul>
 *      <li>PROCESSING - in case of success
 *      <li>IDLE - in case of error
 * </ul>
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.29 $
 */
public class LogonReceiveServerStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(LogonReceiveServerStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.LOGON_RECEIVE;

    public LogonReceiveServerStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {

        Status status = stateProcessor.getStatus(ProtocolState.PROCESSING);
        try {
            if (ProcessingStage.RESET.equals(processingStage)) {
                if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getBeginString(), BeginString.FIX_4_1) >= 0) {
                    if (((LogonMsg) message).getResetSeqNumFlag() != null && ((LogonMsg) message).getResetSeqNumFlag()) {
                        stateProcessor.getProtocol().setRxSeqNo(message.getHeader().getMsgSeqNum());
                        stateProcessor.getProtocol().getHistoryCache().clear();
                        stateProcessor.setProcessingStage(ProcessingStage.LOGGEDON);
                        stateProcessor.getTimers().resetLogonTimeoutTask();
                        return status;
                    }
                }
            } else if (ProcessingStage.LOGGEDON.equals(processingStage)) {
                if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getBeginString(), BeginString.FIX_4_1) >= 0) {
                    if (((LogonMsg) message).getResetSeqNumFlag() != null && ((LogonMsg) message).getResetSeqNumFlag()) {
                        stateProcessor.setProcessingStage(ProcessingStage.RESET);
                        stateProcessor.getProtocol().setTxSeqNo(message.getHeader().getMsgSeqNum() - 1);
                        stateProcessor.getProtocol().setRxSeqNo(message.getHeader().getMsgSeqNum());
                        status = stateProcessor.getStatus(ProtocolState.LOGON_SEND);
                        status.setMessage(message);
                        return status;
                    }
                }
            } else {
                // check if authentication is required first
                status = processLogonMsg((LogonMsg) message);
                if (((ServerSessionInfo) stateProcessor.getProtocol().getConfiguration()).getEnableLogonPassThrough() != null
                        && ((ServerSessionInfo) stateProcessor.getProtocol().getConfiguration()).getEnableLogonPassThrough()) {
                    status = stateProcessor.getStatus(ProtocolState.PROCESSING);
                    stateProcessor.getProtocol().relayMessage(message);
                    // start logon timer
                    stateProcessor.getProtocol().getEventProcessor().onMessageEvent(new MessageEvent(stateProcessor.getProtocol(), message));
                    stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                            LifeCycleType.PROTOCOL_SERVER.name(),
                            LifeCycleCode.FIX_LOGGED_ON.name()));
                    stateProcessor.getTimers().startLogonTimerTask();

                    return status;
                } else {
                    if (!authenticated((LogonMsg) message)) {
                        closeSession("Invalid username/password received.");
                        status = stateProcessor.getStatus(ProtocolState.IDLE);
                        stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                                LifeCycleType.PROTOCOL_SERVER.name(),
                                LifeCycleCode.FIX_SESSION_RESTART.name()));

                        return status;
                    }
                    stateProcessor.getProtocol().getEventProcessor().onMessageEvent(new MessageEvent(stateProcessor.getProtocol(), message));
                    stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                            LifeCycleType.PROTOCOL_SERVER.name(),
                            LifeCycleCode.FIX_LOGGED_ON.name()));
                }
            }
        } catch (LogonException ex) {
            status = stateProcessor.getStatus(ProtocolState.LOGOUT_SEND);
            status.recycle();
            ((LogoutSendServerStatus) status).setText(ex.getMessage());
            ((LogoutSendServerStatus) status).setExpectLogout(false);
        } catch (InvalidMsgException ex) {
            // at this stage we reject, logout and disconnect transport
            String logMsg = "Invalid message : " + ex.getMessage() + ". Reconnecting transport.";
            LOGGER.severe(logMsg);

            stateProcessor.setProcessingStage(ProcessingStage.LOGGEDOUT);
            sendRejectMsg(ex, SessionRejectReason.InvalidMessageType);
            sendLogoutMsg(ex);
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg, ex)));
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                    LifeCycleType.PROTOCOL_SERVER.name(),
                    LifeCycleCode.FIX_SESSION_SHUTDOWN.name()));
        } catch (BadFormatMsgException ex) {
            // at this stage we do not reject, just disconnect transport
            String logMsg = "Logon message is in bad format : " + ex.getMessage() + ". Reconnecting transport.";
            LOGGER.severe(logMsg);

            stateProcessor.setProcessingStage(ProcessingStage.LOGGEDOUT);
            sendRejectMsg(ex, SessionRejectReason.InvalidMessageType);
            sendLogoutMsg(ex);
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg, ex)));
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                    LifeCycleType.PROTOCOL_SERVER.name(),
                    LifeCycleCode.FIX_SESSION_SHUTDOWN.name()));
        }

        return status;
    }

    @Override
    public String getName() {
        return stateProcessor.getProtocol().getName() + "_" + ProtocolState.LOGON_RECEIVE.name();
    }

    @Override
    public void recycle() {
        message = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }

    private boolean authenticated(LogonMsg message) {
        boolean result = true;
        if (BeginString.FIX_4_3.compareTo(message.getHeader().getBeginString()) <= 0) {
            if (stateProcessor.getProtocol().getConfiguration().getAuthenticationInfo() != null && stateProcessor.getProtocol().getConfiguration().getAuthenticationInfo().getLoginUsername() != null) {
                if (message.getUsername() != null && message.getPassword() != null) {
                    if (!message.getUsername().equals(stateProcessor.getProtocol().getConfiguration().getAuthenticationInfo().getLoginUsername())) {
                        result = false;
                    } else {
                        char[] password = PasswordBank.getInstance().getEntryValue(stateProcessor.getProtocol().getConfiguration().getAuthenticationInfo().getLoginPassword());
                        if (!message.getPassword().equals(String.valueOf(password))) {
                            result = false;
                        }
                    }
                } else {
                    result = false;
                }
            }
        } else {
            if (stateProcessor.getProtocol().getConfiguration().getAuthenticationInfo() != null && stateProcessor.getProtocol().getConfiguration().getAuthenticationInfo().getLoginUsername() != null) {
                if (message.getRawData() != null && message.getRawData().length > 0) {
                    char[] password = PasswordBank.getInstance().getEntryValue(stateProcessor.getProtocol().getConfiguration().getAuthenticationInfo().getLoginPassword());
                    if (password == null) {
                         String logMsg = "Authentication has been enabled but there is no entry in the PasswordBank for [" + 
                                 stateProcessor.getProtocol().getConfiguration().getAuthenticationInfo().getLoginUsername() + "].";
                        LOGGER.severe(logMsg);
                        result = false;
                    } else {
                        String clientPassword = new String(message.getRawData());
                        if (!clientPassword.equals(new String(password))) {
                            result = false;
                        }
                    }
                }
            }
        }
            

        return result;
    }

    private Status processLogonMsg(LogonMsg msg) throws InvalidMsgException, BadFormatMsgException, LogonException, InterruptedException {
        Status status = stateProcessor.getStatus(ProtocolState.LOGON_SEND);
        // handle seq nums
        int expSeqNum = stateProcessor.getProtocol().getRxSeqNo();
        if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getBeginString(), BeginString.FIX_4_4) >= 0
                && stateProcessor.getProtocol().getConfiguration().getEnableNextExpMsgSeqNum() != null
                && stateProcessor.getProtocol().getConfiguration().getEnableNextExpMsgSeqNum()
                && msg.getNextExpectedMsgSeqNum() != null) {
            int nextTxSeqNum = stateProcessor.getProtocol().getTxSeqNo() + 1;
            if (msg.getNextExpectedMsgSeqNum() != expSeqNum) {
                if (msg.getNextExpectedMsgSeqNum() < expSeqNum) {
                    // msg seq gap detected - request for the messages after the login has been sent
                    ResendRequestMsg resendRequestMsg = MessageFiller.buildResendRequestMsg(stateProcessor.getProtocol());
                    resendRequestMsg.setBeginSeqNo(msg.getNextExpectedMsgSeqNum());
                    stateProcessor.setGap(new SeqGap(msg.getNextExpectedMsgSeqNum(), expSeqNum));
                    stateProcessor.getResequencingBuffer().addMessage(msg);
                    if (stateProcessor.getProtocol().getConfiguration().getResendEndSeqNum() != null && !stateProcessor.getProtocol().getConfiguration().getResendEndSeqNum().isEmpty()) {
                        resendRequestMsg.setEndSeqNo(new Integer(stateProcessor.getProtocol().getConfiguration().getResendEndSeqNum()));
                    } else {
                        resendRequestMsg.setEndSeqNo(expSeqNum);
                    }
                    stateProcessor.setGapRequestMessage(resendRequestMsg);
                    // revert the rx seqnum as it has been already incremented
                    stateProcessor.getProtocol().setRxSeqNo(expSeqNum - 1);
                } else {
                    String errMsg = "Session outgoing sequence number [" + nextTxSeqNum
                            + "] is greater than the Login next expected message seq num [" + msg.getNextExpectedMsgSeqNum()
                            + "]. Session terminated.";
                    // TODO send logout with reason
                    LOGGER.severe(errMsg);
                    throw new LogonException(errMsg);
                }
            }
        } else if (expSeqNum != msg.getHeader().getMsgSeqNum()) {
            if (msg.getHeader().getMsgSeqNum() > expSeqNum) {
                // msg seq gap detected - request for the messages after the login has been sent
                ResendRequestMsg resendRequestMsg = MessageFiller.buildResendRequestMsgNoSeq(stateProcessor.getProtocol());
                resendRequestMsg.setBeginSeqNo(expSeqNum);
                stateProcessor.setGap(new SeqGap(expSeqNum, msg.getHeader().getMsgSeqNum()));
                stateProcessor.getResequencingBuffer().addMessage(msg);
                if (stateProcessor.getProtocol().getConfiguration().getResendEndSeqNum() != null && !stateProcessor.getProtocol().getConfiguration().getResendEndSeqNum().isEmpty()) {
                    resendRequestMsg.setEndSeqNo(new Integer(stateProcessor.getProtocol().getConfiguration().getResendEndSeqNum()));
                } else {
                    resendRequestMsg.setEndSeqNo(msg.getHeader().getMsgSeqNum());
                }
                stateProcessor.setGapRequestMessage(resendRequestMsg);
                // revert the rx seqnum as it has been already incremented
                stateProcessor.getProtocol().setRxSeqNo(expSeqNum - 1);
            } else {
                String logMsg = "MsgSeqNum too low, expecting [" + expSeqNum + "] but received [" + msg.getHeader().getMsgSeqNum() + "].";
                // TODO send logout with reason
                LOGGER.severe(logMsg);
                throw new LogonException(logMsg);
            }
        }
        // override configured protocol version if set
        if (MsgUtil.compare(msg.getHeader().getBeginString(), BeginString.FIXT_1_1) >= 0 && msg.getDefaultApplVerID() != null) {
            if (MsgUtil.compare(msg.getDefaultApplVerID(), ApplVerID.FIX50) >= 0) {
                stateProcessor.getProtocol().getVersion().setApplVerID(msg.getDefaultApplVerID());
            }
            if (MsgUtil.compare(msg.getDefaultApplVerID(), ApplVerID.FIX50SP1) >= 0) {
                if (msg.getDefaultApplExtID() != null) {
                    stateProcessor.getProtocol().getVersion().setApplExtID(msg.getDefaultApplExtID());
                }
                if (msg.getDefaultCstmApplVerID() != null) {
                    stateProcessor.getProtocol().getVersion().setCstmApplVerID(msg.getDefaultCstmApplVerID());
                }
            }
        }
        if (msg.getHeartBtInt() != null) {
            stateProcessor.getProtocol().getConfiguration().setHeartBtInt(msg.getHeartBtInt());
        }
        if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getBeginString(), BeginString.FIX_4_2) >= 0) {
            if (msg.getMaxMessageSize() != null) {
                stateProcessor.getProtocol().getConfiguration().setMaxMsgLen(msg.getMaxMessageSize());
            }
            if (msg.getNoMsgTypes() != null && msg.getNoMsgTypes() > 0) {
                List<MsgTypeGroup> sessMsgTypes;
                if (stateProcessor.getProtocol().getMsgTypes() != null && stateProcessor.getProtocol().getMsgTypes().length > 0) {
                    sessMsgTypes = Arrays.asList(stateProcessor.getProtocol().getMsgTypes());
                } else {
                    sessMsgTypes = new ArrayList<MsgTypeGroup>();
                }
                for (MsgTypeGroup msgType : msg.getMsgTypeGroups()) {
                    boolean found = false;
                    if (stateProcessor.getProtocol().getMsgTypes() != null && stateProcessor.getProtocol().getMsgTypes().length > 0) {
                        for (MsgTypeGroup sessMsgType : sessMsgTypes) {
                            if (sessMsgType.getRefMsgType().equals(msgType.getRefMsgType())) {
                                // override the session level config msg types
                                sessMsgType.setMsgDirection(msgType.getMsgDirection());
                                if (BeginString.FIXT_1_1.equals(msg.getHeader().getBeginString())) {
                                    if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getApplVerID(), ApplVerID.FIX50) >= 0) {
                                        if (msgType.getRefApplVerID() != null) {
                                            sessMsgType.setRefApplVerID(msgType.getRefApplVerID());
                                        }
                                        if (msgType.getRefCstmApplVerID() != null) {
                                            sessMsgType.setRefCstmApplVerID(msgType.getRefCstmApplVerID());
                                        }
                                    }
                                    if (MsgUtil.compare(msg.getDefaultApplVerID(), ApplVerID.FIX50SP1) >= 0) {
                                        if (msgType.getRefApplExtID() != null) {
                                            sessMsgType.setRefApplExtID(msgType.getRefApplExtID());
                                        }
                                        if (msgType.getDefaultVerIndicator() != null) {
                                            sessMsgType.setDefaultVerIndicator(msgType.getDefaultVerIndicator());
                                        }
                                    }
                                }
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        MsgTypeGroup group = MessageFiller.createMsgTypeGroupForVersion(stateProcessor.getProtocol());
                        group.setRefMsgType(msgType.getRefMsgType());
                        group.setMsgDirection(msgType.getMsgDirection());
                        if (BeginString.FIXT_1_1.equals(msg.getHeader().getBeginString())) {
                            if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getApplVerID(), ApplVerID.FIX50) >= 0) {
                                if (msgType.getRefApplVerID() != null) {
                                    group.setRefApplVerID(msgType.getRefApplVerID());
                                }
                                if (msgType.getRefCstmApplVerID() != null) {
                                    group.setRefCstmApplVerID(msgType.getRefCstmApplVerID());
                                }
                            }
                            if (MsgUtil.compare(msg.getDefaultApplVerID(), ApplVerID.FIX50SP1) >= 0) {
                                if (msgType.getRefApplExtID() != null) {
                                    group.setRefApplExtID(msgType.getRefApplExtID());
                                }
                                if (msgType.getDefaultVerIndicator() != null) {
                                    group.setDefaultVerIndicator(msgType.getDefaultVerIndicator());
                                }
                            }
                        }
                        sessMsgTypes.add(group);
                    }
                }
                stateProcessor.getProtocol().setMsgTypes(sessMsgTypes.toArray(new MsgTypeGroup[sessMsgTypes.size()]));
            }
        }
        if (BeginString.FIX_4_3.compareTo(msg.getHeader().getBeginString()) <= 0) {
            if (msg.getMaxMessageSize() != null) {
                stateProcessor.getProtocol().getConfiguration().setMaxMsgLen(msg.getMaxMessageSize());
            }
            if (msg.getTestMessageIndicator() != null) {
                stateProcessor.getProtocol().setTestSession(msg.getTestMessageIndicator());
            }
        }
        if (BeginString.FIXT_1_1.compareTo(msg.getHeader().getBeginString()) <= 0) {
            if (msg.getSessionStatus() != null) {
                SessionStatus sessStat = msg.getSessionStatus();
                if (SessionStatus.InvalidUsernameOrPassword.equals(sessStat) || SessionStatus.AccountLocked.equals(sessStat)
                        || SessionStatus.LogonsAreNotAllowed.equals(sessStat) || SessionStatus.NewPasswordNotComplying.equals(sessStat)
                        || SessionStatus.PasswordExpired.equals(sessStat)) {
                    throw new LogonException(sessStat.name());
                }
            }
            if (msg.getDefaultApplVerID() != null) {
                stateProcessor.getProtocol().getConfiguration().setDefaultApplVerID(msg.getDefaultApplVerID().getValue());
            }
            if (msg.getDefaultApplExtID() != null) {
                stateProcessor.getProtocol().getConfiguration().setDefaultApplExtID(msg.getDefaultApplExtID());
            }
            if (msg.getDefaultCstmApplVerID() != null) {
                stateProcessor.getProtocol().getConfiguration().setDefaultCstmApplVerID(msg.getDefaultCstmApplVerID());
            }
        }

        return status;
    }

    private void closeSession(String logMsg) {
        LOGGER.severe(logMsg);
        stateProcessor.setProcessingStage(ProcessingStage.LOGGEDOUT);
        stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                Alert.createAlert(getName(), ComponentType.FIXServer.toString(),
                BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg, null)));
        stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                LifeCycleType.PROTOCOL_SERVER.name(),
                LifeCycleCode.FIX_SESSION_RESTART.name()));
        stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
    }
}
