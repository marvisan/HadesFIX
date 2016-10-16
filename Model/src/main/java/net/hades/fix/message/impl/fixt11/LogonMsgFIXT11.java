/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogonMsgFIXT11.java
 *
 * $Id: LogonMsgFIXT11.java,v 1.11 2011-04-14 23:45:00 vrotaru Exp $
 */
package net.hades.fix.message.impl.fixt11;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.EncryptMethod;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.Header;
import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.group.impl.fixt11.MsgTypeGroupFIXT11;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionStatus;

/**
 * The logon message is utilized to authenticate a user attempting to establish a connection to a remote
 * system. The logon message must be the first message sent by the application requesting to initiate a
 * FIXT1.1 session.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 20/09/2008, 20:51:13
 */
public abstract class LogonMsgFIXT11 extends LogonMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public LogonMsgFIXT11(Header header, ByteBuffer rawMsg) 
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }
    
    public LogonMsgFIXT11(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public LogonMsgFIXT11(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////
        
    @Override
    public EncryptMethod getEncryptMethod() {
        return encryptMethod;
    }

    @Override
    public void setEncryptMethod(EncryptMethod encryptMethod) {
        this.encryptMethod = encryptMethod;
    }

    @Override
    public Integer getHeartBtInt() {
        return heartBtInt;
    }

    @Override
    public void setHeartBtInt(Integer heartBtInt) {
        this.heartBtInt = heartBtInt;
    }

    @Override
    public byte[] getRawData() {
        return rawData;
    }

    @Override
    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
        if (rawDataLength == null) {
            rawDataLength = new Integer(rawData.length);
        }
    }

    @Override
    public Integer getRawDataLength() {
        return rawDataLength;
    }

    @Override
    public void setRawDataLength(Integer rawDataLength) {
        this.rawDataLength = rawDataLength;
    }
    
    @Override
    public Boolean getResetSeqNumFlag() {
        return resetSeqNumFlag;
    }

    @Override
    public void setResetSeqNumFlag(Boolean resetSeqNumFlag) {
        this.resetSeqNumFlag = resetSeqNumFlag;
    }

    @Override
    public Integer getNoMsgTypes() {
        return noMsgTypes;
    }

    @Override
    public void setNoMsgTypes(Integer noMsgTypes) {
        this.noMsgTypes = noMsgTypes;
        if (noMsgTypes != null) {
            msgTypeGroups = new MsgTypeGroup[noMsgTypes.intValue()];
            for (int i = 0; i < msgTypeGroups.length; i++) {
                msgTypeGroups[i] = new MsgTypeGroupFIXT11(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }
    
    @Override
    public MsgTypeGroup[] getMsgTypeGroups() {
        return msgTypeGroups;
    }

    @Override
    public MsgTypeGroup addMsgTypeGroup() {
        MsgTypeGroup group = new MsgTypeGroupFIXT11(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MsgTypeGroup> groups = new ArrayList<MsgTypeGroup>();
        if (msgTypeGroups != null && msgTypeGroups.length > 0) {
            groups = new ArrayList<MsgTypeGroup>(Arrays.asList(msgTypeGroups));
        }
        groups.add(group);
        msgTypeGroups = groups.toArray(new MsgTypeGroup[groups.size()]);
        noMsgTypes = new Integer(msgTypeGroups.length);
        
        return group;
    }
    
    @Override
    public MsgTypeGroup deleteMsgTypeGroup(int index) {
        MsgTypeGroup result = null;
        if (msgTypeGroups != null && msgTypeGroups.length > 0 && msgTypeGroups.length > index) {
            List<MsgTypeGroup> groups = new ArrayList<MsgTypeGroup>(Arrays.asList(msgTypeGroups));
            result = groups.remove(index);
            msgTypeGroups = groups.toArray(new MsgTypeGroup[groups.size()]);
            if (msgTypeGroups.length > 0) {
                noMsgTypes = new Integer(msgTypeGroups.length);
            } else {
                msgTypeGroups = null;
                noMsgTypes = null;
            }
        }
        
        return result;
    }

    @Override
    public int clearMsgTypeGroups() {
        int result = 0;
        if (msgTypeGroups != null && msgTypeGroups.length > 0) {
            result = msgTypeGroups.length;
            msgTypeGroups = null;
            noMsgTypes = null;
        }
        
        return result;
    }
    
    @Override
    public Integer getMaxMessageSize() {
        return maxMessageSize;
    }

    @Override
    public void setMaxMessageSize(Integer maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    @Override
    public Boolean getTestMessageIndicator() {
        return testMessageIndicator;
    }

    @Override
    public void setTestMessageIndicator(Boolean testMessageIndicator) {
        this.testMessageIndicator = testMessageIndicator;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getNewPassword() {
        return newPassword;
    }

    @Override
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public Integer getEncryptedPasswordMethod() {
        return encryptedPasswordMethod;
    }

    @Override
    public void setEncryptedPasswordMethod(Integer encryptedPasswordMethod) {
        this.encryptedPasswordMethod = encryptedPasswordMethod;
    }

    @Override
    public Integer getEncryptedPasswordLen() {
        return encryptedPasswordLen;
    }

    @Override
    public void setEncryptedPasswordLen(Integer encryptedPasswordLen) {
        this.encryptedPasswordLen = encryptedPasswordLen;
    }

    @Override
    public byte[] getEncryptedPassword() {
        return encryptedPassword;
    }

    @Override
    public void setEncryptedPassword(byte[] encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        if (encryptedPasswordLen == null) {
            encryptedPasswordLen = new Integer(encryptedPassword.length);
        }
    }

    @Override
    public Integer getEncryptedNewPasswordLen() {
        return encryptedNewPasswordLen;
    }

    @Override
    public void setEncryptedNewPasswordLen(Integer encryptedNewPasswordLen) {
        this.encryptedNewPasswordLen = encryptedNewPasswordLen;
    }

    @Override
    public byte[] getEncryptedNewPassword() {
        return encryptedNewPassword;
    }

    @Override
    public void setEncryptedNewPassword(byte[] encryptedNewPassword) {
        this.encryptedNewPassword = encryptedNewPassword;
        if (encryptedNewPasswordLen == null) {
            encryptedNewPasswordLen = new Integer(encryptedNewPassword.length);
        }
    }

    @Override
    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    @Override
    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    @Override
    public Integer getNextExpectedMsgSeqNum() {
        return nextExpectedMsgSeqNum;
    }

    @Override
    public void setNextExpectedMsgSeqNum(Integer nextExpectedMsgSeqNum) {
        this.nextExpectedMsgSeqNum = nextExpectedMsgSeqNum;
    }
    
    @Override
    public ApplVerID getDefaultApplVerID() {
        return defaultApplVerID;
    }

    @Override
    public void setDefaultApplVerID(ApplVerID defaultApplVerID) {
        this.defaultApplVerID = defaultApplVerID;
    }
    
    @Override
    public Integer getDefaultApplExtID() {
        return defaultApplExtID;
    }

    @Override
    public void setDefaultApplExtID(Integer defaultApplExtID) {
        this.defaultApplExtID = defaultApplExtID;
    }

    @Override
    public String getDefaultCstmApplVerID() {
        return defaultCstmApplVerID;
    }

    @Override
    public void setDefaultCstmApplVerID(String defaultCstmApplVerID) {
        this.defaultCstmApplVerID = defaultCstmApplVerID;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (encryptMethod == null) {
            errorMsg.append(" [EncryptMethod]");
            hasMissingTag = true;
        }
        if (heartBtInt == null) {
            errorMsg.append(" [HeartBtInt]");
            hasMissingTag = true;
        }
        if (defaultApplVerID == null) {
            errorMsg.append(" [DefaultApplVerID]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LogonMsg] message version [T1.1].";
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
