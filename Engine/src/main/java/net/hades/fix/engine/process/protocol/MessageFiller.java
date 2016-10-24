/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.hades.fix.commons.security.PasswordBank;
import net.hades.fix.message.*;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.group.impl.v42.MsgTypeGroup42;
import net.hades.fix.message.group.impl.v50.MsgTypeGroup50;
import net.hades.fix.message.group.impl.v50sp1.MsgTypeGroup50SP1;
import net.hades.fix.message.group.impl.v50sp2.MsgTypeGroup50SP2;
import net.hades.fix.message.type.*;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.codec.HexCodec;

/**
 * Fills a FIX protocol message with data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class MessageFiller {

    private MessageFiller() {
    }

    public static LogonMsg buildLogonMsg(Protocol protocol) throws InvalidMsgException {
        LogonMsg message = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.Logon, MsgDirection.Send));
        fillHeader(protocol, message);
        if (protocol.getConfiguration().getEncryption() != null && protocol.getConfiguration().getEncryption().getEncryptionType() != null) {
            message.setEncryptMethod(EncryptMethod.valueFor(Integer.valueOf(protocol.getConfiguration().getEncryption().getEncryptionType())));
        } else {
            message.setEncryptMethod(EncryptMethod.None);
        }
        message.setHeartBtInt(protocol.getConfiguration().getHeartBtInt());
        if (protocol.getConfiguration().getEncryption() != null && protocol.getConfiguration().getEncryption().getEncryptionSymKey() != null) {
            byte[] rawData = HexCodec.decode(protocol.getConfiguration().getEncryption().getEncryptionSymKey());
            message.setRawDataLength(rawData.length);
            message.setRawData(rawData);
        }
        ApplVerID ver = getApplVerID(protocol, message);
        
        if (ver.equals(ApplVerID.FIX40) || ver.equals(ApplVerID.FIX41) || ver.equals(ApplVerID.FIX42)) {
            if (protocol.getConfiguration().getAuthenticationInfo() != null && protocol.getConfiguration().getAuthenticationInfo().getLoginUsername() != null) {
                char[] password = PasswordBank.getInstance().getEntryValue(protocol.getConfiguration().getAuthenticationInfo().getLoginPassword());
                if (password == null) {
                    throw new InvalidMsgException("Authentication has been enabled but there is no entry in the PasswordBank for ["
                            + protocol.getConfiguration().getAuthenticationInfo().getLoginUsername() + "].");
                }
                message.setRawData(new String(password).getBytes());
            }
        }
        
        if (MsgUtil.compare(ver, ApplVerID.FIX41) >= 0) {
            if (protocol.getConfiguration().getResetSeqAtLogon()) {
                message.setResetSeqNumFlag(Boolean.TRUE);
            }
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX42) >= 0) {
            if (protocol.getConfiguration().getMsgTypes() != null && protocol.getConfiguration().getMsgTypes().length > 0) {
                message.setMaxMessageSize(protocol.getConfiguration().getMaxMsgLen());
                List<MsgTypeGroup> mtgs = Arrays.asList(message.getMsgTypeGroups());
                if (mtgs.size() > 0) {
                    message.setNoMsgTypes(mtgs.size());
                    for (int i = 0; i < message.getMsgTypeGroups().length; i++) {
                        MsgTypeGroup grp = mtgs.get(i);
                        message.getMsgTypeGroups()[i].setRefMsgType(grp.getRefMsgType());
                        message.getMsgTypeGroups()[i].setMsgDirection(grp.getMsgDirection());
                        if (message.getHeader().getBeginString().equals(BeginString.FIXT_1_1)) {
                            message.getMsgTypeGroups()[i].setRefApplVerID(grp.getRefApplVerID());
                            message.getMsgTypeGroups()[i].setRefCstmApplVerID(grp.getRefCstmApplVerID());
                        }
                    }
                }
            }
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX43) >= 0) {
            if (protocol.getConfiguration().getAuthenticationInfo() != null &&
                protocol.getConfiguration().getAuthenticationInfo().getLoginUsername() != null) {
                message.setUsername(protocol.getConfiguration().getAuthenticationInfo().getLoginUsername());
                char[] password = PasswordBank.getInstance().getEntryValue(protocol.getConfiguration().getAuthenticationInfo().getLoginPassword());
                if (password == null) {
                    throw new InvalidMsgException("Authentication has been enabled but there is no entry in the PasswordBank for [" +
                            protocol.getConfiguration().getAuthenticationInfo().getLoginUsername() + "].");
                }
                message.setPassword(String.valueOf(password));
                message.setTestMessageIndicator(protocol.isTestSession());
            }
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX44) >= 0) {
            if (protocol.getConfiguration().getEnableNextExpMsgSeqNum() != null && protocol.getConfiguration().getEnableNextExpMsgSeqNum()) {
                message.setNextExpectedMsgSeqNum(protocol.getRxSeqNo() + 1);
            }
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX50) >= 0) {
            message.setDefaultApplVerID(ver);
            message.getHeader().setApplVerID(ver);
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX50SP1) >= 0) {
            if (protocol.getConfiguration().getAuthenticationInfo() != null) {
                message.setNewPassword(protocol.getConfiguration().getAuthenticationInfo().getNewPassword());
            }
            if (protocol.getConfiguration().getEncryptedAuthenticationInfo() != null) {
                message.setEncryptedPasswordMethod(protocol.getConfiguration().getEncryptedAuthenticationInfo().getEncryptedPasswordMethod());
                if (protocol.getConfiguration().getEncryptedAuthenticationInfo().getEncryptedPassword() != null
                        && protocol.getConfiguration().getEncryptedAuthenticationInfo().getEncryptedPassword().length > 0) {
                    message.setEncryptedPassword(protocol.getConfiguration().getEncryptedAuthenticationInfo().getEncryptedPassword());
                    message.setEncryptedPasswordLen(protocol.getConfiguration().getEncryptedAuthenticationInfo().getEncryptedPassword().length);
                }
                if (protocol.getConfiguration().getEncryptedAuthenticationInfo().getEncryptedNewPassword() != null
                        && protocol.getConfiguration().getEncryptedAuthenticationInfo().getEncryptedNewPassword().length > 0) {
                    message.setEncryptedPassword(protocol.getConfiguration().getEncryptedAuthenticationInfo().getEncryptedNewPassword());
                    message.setEncryptedPasswordLen(protocol.getConfiguration().getEncryptedAuthenticationInfo().getEncryptedNewPassword().length);
                }
                message.setDefaultApplExtID(protocol.getConfiguration().getDefaultApplExtID());
                message.setDefaultCstmApplVerID(protocol.getConfiguration().getDefaultCstmApplVerID());
            }
        }
        MsgTypeGroup[] suppMsgTypes = protocol.getMsgTypes();
        if (suppMsgTypes != null && suppMsgTypes.length > 0) {
            for (MsgTypeGroup suppMsgType : suppMsgTypes) {
                MsgTypeGroup sm = message.addMsgTypeGroup();
                sm.setRefMsgType(suppMsgType.getRefMsgType());
                sm.setMsgDirection(suppMsgType.getMsgDirection());
                if (MsgUtil.compare(ver, ApplVerID.FIX50) >= 0) {
                    sm.setRefApplVerID(suppMsgType.getRefApplVerID());
                    sm.setRefCstmApplVerID(suppMsgType.getRefCstmApplVerID());
                }
                if (MsgUtil.compare(ver, ApplVerID.FIX50SP1) >= 0) {
                    sm.setRefApplExtID(suppMsgType.getRefApplExtID());
                    sm.setDefaultVerIndicator(suppMsgType.getDefaultVerIndicator());
                }
            }
        }

        return message;
    }

    public static LogonMsg buildResetSeqNumLogonMsg(Protocol protocol) throws InvalidMsgException {
        LogonMsg message = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.Logon, MsgDirection.Send));
        fillHeader(protocol, message);
        if (protocol.getConfiguration().getEncryption() != null && protocol.getConfiguration().getEncryption().getEncryptionType() != null) {
            message.setEncryptMethod(EncryptMethod.valueFor(Integer.valueOf(protocol.getConfiguration().getEncryption().getEncryptionType())));
        } else {
            message.setEncryptMethod(EncryptMethod.None);
        }
        message.setHeartBtInt(protocol.getConfiguration().getHeartBtInt());
        message.setResetSeqNumFlag(Boolean.TRUE);

        return message;
    }

    public static LogoutMsg buildLogoutMsg(Protocol protocol) throws InvalidMsgException {
        LogoutMsg logoutMsg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.Logout, MsgDirection.Send));
        fillHeader(protocol, logoutMsg);

        return logoutMsg;
    }

    public static ResendRequestMsg buildResendRequestMsg(Protocol protocol) throws InvalidMsgException {
        ResendRequestMsg resendRequestMsg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.ResendRequest, MsgDirection.Send));
        fillHeader(protocol, resendRequestMsg);

        return resendRequestMsg;
    }

    public static ResendRequestMsg buildResendRequestMsgNoSeq(Protocol protocol) throws InvalidMsgException {
        ResendRequestMsg resendRequestMsg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.ResendRequest, MsgDirection.Send));
        fillHeader(protocol, resendRequestMsg, 0);

        return resendRequestMsg;
    }

    public static TestRequestMsg buildTestRequestMsg(Protocol protocol) throws InvalidMsgException {
        TestRequestMsg message = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.TestRequest, MsgDirection.Send));
        fillHeader(protocol, message);
        message.setTestReqID(String.valueOf(System.currentTimeMillis()));

        return message;
    }

    public static HeartbeatMsg buildHeartbeatMsg(Protocol protocol) throws InvalidMsgException {
        HeartbeatMsg message = (HeartbeatMsg) FIXMsgBuilder.build(MsgType.Heartbeat.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.Heartbeat, MsgDirection.Send));
        fillHeader(protocol, message);

        return message;
    }

    public static SequenceResetMsg buildSequenceResetMsg(Protocol protocol) throws InvalidMsgException {
        SequenceResetMsg message = (SequenceResetMsg) FIXMsgBuilder.build(MsgType.SequenceReset.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.SequenceReset, MsgDirection.Send));
        fillHeader(protocol, message);

        return message;
    }

    public static SequenceResetMsg buildSequenceResetMsg(Protocol protocol, int seqNum) throws InvalidMsgException {
        SequenceResetMsg message = (SequenceResetMsg) FIXMsgBuilder.build(MsgType.SequenceReset.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.SequenceReset, MsgDirection.Send));
        fillHeader(protocol, message, seqNum);

        return message;
    }

    public static RejectMsg buildRejectMsg(Protocol protocol, FIXMsg fixMsg, SessionRejectReason sessionRejectReason, Exception ex) throws InvalidMsgException {
        RejectMsg message = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(),
                protocol.getVersion().getBeginString(),
                getDefaultApplVerID(protocol, MsgType.Logon, MsgDirection.Send));
        fillHeader(protocol, message);
        message.setText(ex.getMessage());
        message.setRefSeqNo(fixMsg.getHeader().getMsgSeqNum());
        ApplVerID ver = getApplVerID(protocol, message);
        if (MsgUtil.compare(ver, ApplVerID.FIX42) >= 0) {
            if (ex instanceof BadFormatMsgException) {
                message.setRefTagID(((BadFormatMsgException) ex).getTagNum());
                message.setSessionRejectReason(((BadFormatMsgException) ex).getRejectReason());
            } else {
                message.setSessionRejectReason(sessionRejectReason);
            }
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX50SP1) >= 0) {
            if (fixMsg.getHeader().getApplVerID() == null) {
                //TODO get this values from the logon message if specified
                message.setRefApplVerID(ver);
                message.setRefApplExtID(0);
            }
        }

        return message;
    }

    public static MsgTypeGroup createMsgTypeGroupForVersion(Protocol protocol) throws InvalidMsgException {
        ApplVerID applVerID = MsgUtil.getMsgFixVersion(protocol.getVersion().getBeginString(), protocol.getVersion().getApplVerID());
        switch (applVerID) {
            case FIX40:
            case FIX41:
            case FIX42:
                return new MsgTypeGroup42();

            case FIX43:
            case FIX44:
            case FIX50:
                return new MsgTypeGroup50();

            case FIX50SP1:
                return new MsgTypeGroup50SP1();

            case FIX50SP2:
                return new MsgTypeGroup50SP2();

            default:
                return new MsgTypeGroup42();
        }
    }

    private static void fillHeader(Protocol protocol, FIXMsg message) {
        message.setHeader(HeaderFiller.fillHeader(protocol, message.getHeader()));
        Date now = new Date();
        message.getHeader().setOrigSendingTime(now);
        message.getHeader().setSendingTime(now);
    }

    private static void fillHeader(Protocol protocol, FIXMsg message, int seqNum) {
        message.setHeader(HeaderFiller.fillHeader(protocol, message.getHeader()));
        Date now = new Date();
        message.getHeader().setOrigSendingTime(now);
        message.getHeader().setSendingTime(now);
    }

    private static ApplVerID getApplVerID(Protocol protocol, FIXMsg message) {
        ApplVerID ver = null;
        if (BeginString.FIXT_1_1.equals(message.getHeader().getBeginString())) {
            // check first in message group type
            ver = ApplVerID.valueFor(protocol.getMessageSessionApplVerID(MsgType.valueFor(message.getHeader().getMsgType()), null));
            if (ver == null) {
                ver = message.getHeader().getApplVerID();
            }
        } else {
            try {
                ver = MsgUtil.getApplVerFromBeginString(message.getHeader().getBeginString());
            } catch (InvalidMsgException ex) {
                // will not happen here
            }
        }

        return ver;
    }

    private static ApplVerID getDefaultApplVerID(Protocol protocol, MsgType msgType, MsgDirection direction) throws InvalidMsgException {
        ApplVerID ver;
        if (BeginString.FIXT_1_1.equals(protocol.getVersion().getBeginString())) {
            // check first in message group type
            ver = ApplVerID.valueFor(protocol.getMessageSessionApplVerID(msgType, direction));
        } else {
            ver = protocol.getVersion().getApplVerID();
        }

        return ver;
    }
}
