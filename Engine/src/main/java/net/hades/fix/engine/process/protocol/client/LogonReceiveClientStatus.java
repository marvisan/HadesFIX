/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LoginStatus.java
 *
 * $Id: LogonReceiveClientStatus.java,v 1.22 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.client;

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
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.process.protocol.SeqGap;
import net.hades.fix.engine.process.protocol.state.ResendRequestSendStatus;
import net.hades.fix.engine.process.protocol.state.StateProcessor;
import net.hades.fix.engine.process.protocol.state.Status;
import net.hades.fix.message.LogonMsg;
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
 * @version $Revision: 1.22 $
 */
public class LogonReceiveClientStatus extends Status {

    private static final Logger LOGGER = Logger.getLogger(LogonReceiveClientStatus.class.getName());

    private static final ProtocolState STATE = ProtocolState.LOGON_RECEIVE;

    public LogonReceiveClientStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Status status = stateProcessor.getStatus(ProtocolState.PROCESSING);
        try {
            try {
                if (ProtocolState.RESET.equals(processingStage)) {
                    if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getBeginString(), BeginString.FIX_4_1) >= 0) {
                        if (((LogonMsg) message).getResetSeqNumFlag() != null && ((LogonMsg) message).getResetSeqNumFlag()) {
                            stateProcessor.getProtocol().setRxSeqNo(message.getHeader().getMsgSeqNum());
                            stateProcessor.getProtocol().getHistoryCache().clear();
                            stateProcessor.setProcessingStage(ProtocolState.LOGGEDON);
                            stateProcessor.getTimers().resetLogonTimeoutTask();
                            return status;
                        }
                    }
                } else if (ProtocolState.LOGGEDON.equals(stateProcessor.getProcessingStage())) {
                    if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getBeginString(), BeginString.FIX_4_1) >= 0) {
                        if (((LogonMsg) message).getResetSeqNumFlag() != null && ((LogonMsg) message).getResetSeqNumFlag()) {
                            stateProcessor.setProcessingStage(ProtocolState.RESET);
                            stateProcessor.getProtocol().setRxSeqNo(message.getHeader().getMsgSeqNum());
                            stateProcessor.getProtocol().setTxSeqNo(message.getHeader().getMsgSeqNum() - 1);
                            status = stateProcessor.getStatus(ProtocolState.LOGON_SEND);
                            status.setMessage(message);
                            return status;
                        }
                    }
                } else {
                    status = processLogonMsg((LogonMsg) message);
                    stateProcessor.setProcessingStage(ProtocolState.LOGGEDON);
                    stateProcessor.getProtocol().getEventProcessor().onMessageEvent(new MessageEvent(stateProcessor.getProtocol(), message));
                    stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                            LifeCycleType.PROTOCOL_CLIENT.name(),
                            LifeCycleCode.FIX_LOGGED_ON.name()));
                    stateProcessor.getTimers().resetLogonTimeoutTask();
                    stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionOpened));
                }
            } catch (LogonException ex) {
                status = stateProcessor.getStatus(ProtocolState.LOGOUT_SEND);
                status.recycle();
                ((LogoutSendClientStatus) status).setText(ex.getMessage());
                ((LogoutSendClientStatus) status).setExpectLogout(false);

                return status;
            }
        } catch (InvalidMsgException ex) {
            // at this stage we reject, logout and disconnect transport
            String logMsg = "Invalid message : " + ex.getMessage() + ". Reconnecting transport.";
            LOGGER.severe(logMsg);

            sendRejectMsg(ex, SessionRejectReason.InvalidMessageType);
            sendLogoutMsg(ex);
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg, ex)));
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                    LifeCycleType.PROTOCOL_CLIENT.name(),
                    LifeCycleCode.FIX_SESSION_RESTART.name()));
            stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
        } catch (BadFormatMsgException ex) {
            // at this stage we do not reject, just disconnect transport
            String logMsg = "Logon message is in bad format : " + ex.getMessage() + ". Reconnecting transport.";
            LOGGER.severe(logMsg);

            sendRejectMsg(ex, SessionRejectReason.InvalidMessageType);
            sendLogoutMsg(ex);
            stateProcessor.getProtocol().getEventProcessor().onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(getName(), ComponentType.FIXClient.toString(),
                    BaseSeverityType.FATAL, AlertCode.PROTOCOL_ERROR, logMsg, ex)));
            stateProcessor.getProtocol().getEventProcessor().onLifeCycleEvent(new LifeCycleEvent(stateProcessor.getProtocol(),
                    LifeCycleType.PROTOCOL_CLIENT.name(),
                    LifeCycleCode.FIX_SESSION_RESTART.name()));
            stateProcessor.getProtocol().getSessionCoordinator().execute(new Command(CommandType.SessionRestarted));
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

    private Status processLogonMsg(LogonMsg message) throws InvalidMsgException, BadFormatMsgException, LogonException, InterruptedException {
        Status status = stateProcessor.getStatus(ProtocolState.PROCESSING);
        // handle seq nums
        boolean gapExists = false;
        int expSeqNum = stateProcessor.getProtocol().getRxSeqNo();
        
        if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getBeginString(), BeginString.FIX_4_4) >= 0
                && stateProcessor.getProtocol().getConfiguration().getEnableNextExpMsgSeqNum() != null
                && stateProcessor.getProtocol().getConfiguration().getEnableNextExpMsgSeqNum()
                && message.getNextExpectedMsgSeqNum() != null) {
            int nextTxSeqNum = stateProcessor.getProtocol().getTxSeqNo() + 1;
            if (message.getNextExpectedMsgSeqNum() != expSeqNum) {
                if (message.getNextExpectedMsgSeqNum() < expSeqNum) {
                    status = stateProcessor.getStatus(ProtocolState.RESEND_REQUEST_SEND);
                    SeqGap gap = new SeqGap(message.getNextExpectedMsgSeqNum(), expSeqNum);
                    stateProcessor.getResequencingBuffer().addMessage(message);
                    stateProcessor.setGap(gap);
                    // revert the rx seqnum as it has been already incremented
                    stateProcessor.getProtocol().setRxSeqNo(expSeqNum - 1);
                    gapExists = true;
                } else {
                    String logMsg = "MsgSeqNum too low, expecting [" + nextTxSeqNum + "] but received ["
                            + message.getNextExpectedMsgSeqNum() + "].";
                    // TODO send logout with reason
                    LOGGER.severe(logMsg);
                    throw new LogonException(logMsg);
                }
            }
        } else if (expSeqNum != message.getHeader().getMsgSeqNum()) {
            if (message.getHeader().getMsgSeqNum() > expSeqNum) {
                status = stateProcessor.getStatus(ProtocolState.RESEND_REQUEST_SEND);
                SeqGap gap = new SeqGap(expSeqNum, message.getHeader().getMsgSeqNum());
                stateProcessor.getResequencingBuffer().addMessage(message);
                stateProcessor.setGap(gap);
                // revert the rx seqnum as it has been already incremented
                stateProcessor.getProtocol().setRxSeqNo(expSeqNum - 1);
                gapExists = true;
            } else {
                String logMsg = "MsgSeqNum too low, expecting " + expSeqNum + " but received " + message.getHeader().getMsgSeqNum();
                LOGGER.severe(logMsg);
                throw new LogonException(logMsg);
            }
        }
        // override configured protocol version if set
        if (MsgUtil.compare(message.getHeader().getBeginString(), BeginString.FIXT_1_1) >= 0 && message.getDefaultApplVerID() != null) {
            if (MsgUtil.compare(message.getDefaultApplVerID(), ApplVerID.FIX50) >= 0) {
                stateProcessor.getProtocol().getVersion().setApplVerID(message.getDefaultApplVerID());
            }
            if (MsgUtil.compare(message.getDefaultApplVerID(), ApplVerID.FIX50SP1) >= 0) {
                if (message.getDefaultApplExtID() != null) {
                    stateProcessor.getProtocol().getVersion().setApplExtID(message.getDefaultApplExtID());
                }
                if (message.getDefaultCstmApplVerID() != null) {
                    stateProcessor.getProtocol().getVersion().setCstmApplVerID(message.getDefaultCstmApplVerID());
                }
            }
        }
        if (message.getHeartBtInt() != null) {
            stateProcessor.getProtocol().getConfiguration().setHeartBtInt(message.getHeartBtInt());
        }
        if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getBeginString(), BeginString.FIX_4_2) >= 0) {
            if (message.getMaxMessageSize() != null) {
                stateProcessor.getProtocol().getConfiguration().setMaxMsgLen(message.getMaxMessageSize());
            }
            if (message.getNoMsgTypes() != null && message.getNoMsgTypes() > 0) {
                List<MsgTypeGroup> sessMsgTypes;
                if (stateProcessor.getProtocol().getMsgTypes() != null && stateProcessor.getProtocol().getMsgTypes().length > 0) {
                    sessMsgTypes = Arrays.asList(stateProcessor.getProtocol().getMsgTypes());
                } else {
                    sessMsgTypes = new ArrayList<MsgTypeGroup>();
                }
                for (MsgTypeGroup msgType : message.getMsgTypeGroups()) {
                    boolean found = false;
                    if (stateProcessor.getProtocol().getMsgTypes() != null && stateProcessor.getProtocol().getMsgTypes().length > 0) {
                        for (MsgTypeGroup sessMsgType : sessMsgTypes) {
                            if (sessMsgType.getRefMsgType().equals(msgType.getRefMsgType())) {
                                // override the session level config msg types
                                sessMsgType.setMsgDirection(msgType.getMsgDirection());
                                if (BeginString.FIXT_1_1.equals(message.getHeader().getBeginString())) {
                                    if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getApplVerID(), ApplVerID.FIX50) >= 0) {
                                        if (msgType.getRefApplVerID() != null) {
                                            sessMsgType.setRefApplVerID(msgType.getRefApplVerID());
                                        }
                                        if (msgType.getRefCstmApplVerID() != null) {
                                            sessMsgType.setRefCstmApplVerID(msgType.getRefCstmApplVerID());
                                        }
                                    }
                                    if (MsgUtil.compare(message.getDefaultApplVerID(), ApplVerID.FIX50SP1) >= 0) {
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
                        if (BeginString.FIXT_1_1.equals(message.getHeader().getBeginString())) {
                            if (MsgUtil.compare(stateProcessor.getProtocol().getVersion().getApplVerID(), ApplVerID.FIX50) >= 0) {
                                if (msgType.getRefApplVerID() != null) {
                                    group.setRefApplVerID(msgType.getRefApplVerID());
                                }
                                if (msgType.getRefCstmApplVerID() != null) {
                                    group.setRefCstmApplVerID(msgType.getRefCstmApplVerID());
                                }
                            }
                            if (MsgUtil.compare(message.getDefaultApplVerID(), ApplVerID.FIX50SP1) >= 0) {
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
        if (BeginString.FIX_4_3.compareTo(message.getHeader().getBeginString()) <= 0) {
            if (message.getMaxMessageSize() != null) {
                stateProcessor.getProtocol().getConfiguration().setMaxMsgLen(message.getMaxMessageSize());
            }
            if (message.getTestMessageIndicator() != null) {
                stateProcessor.getProtocol().setTestSession(message.getTestMessageIndicator());
            }
        }
        if (BeginString.FIXT_1_1.compareTo(message.getHeader().getBeginString()) <= 0) {
            if (message.getSessionStatus() != null) {
                SessionStatus sessStat = message.getSessionStatus();
                if (SessionStatus.InvalidUsernameOrPassword.equals(sessStat) || SessionStatus.AccountLocked.equals(sessStat)
                        || SessionStatus.LogonsAreNotAllowed.equals(sessStat) || SessionStatus.NewPasswordNotComplying.equals(sessStat)
                        || SessionStatus.PasswordExpired.equals(sessStat)) {
                    throw new LogonException(sessStat.name());
                }
            }
            if (message.getDefaultApplVerID() != null) {
                stateProcessor.getProtocol().getConfiguration().setDefaultApplVerID(message.getDefaultApplVerID().getValue());
            }
            if (message.getDefaultApplExtID() != null) {
                stateProcessor.getProtocol().getConfiguration().setDefaultApplExtID(message.getDefaultApplExtID());
            }
            if (message.getDefaultCstmApplVerID() != null) {
                stateProcessor.getProtocol().getConfiguration().setDefaultCstmApplVerID(message.getDefaultCstmApplVerID());
            }
        }
        if (!(status instanceof ResendRequestSendStatus)) {
            stateProcessor.getProtocol().setRxSeqNo(expSeqNum);
        }
        if (gapExists) {
            // do not enable yet sending of business messages as we might get logouts
            stateProcessor.setProcessingStage(ProtocolState.ADMIN);
        } else {
            stateProcessor.setProcessingStage(ProtocolState.LOGGEDON);
        }

        return status;
    }

}
