/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogonMsg.java
 *
 * $Id: LogonMsg.java,v 1.13 2011-04-28 10:07:45 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.EncryptMethod;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionStatus;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The logon message is utilized to authenticate a user attempting to establish a connection to a remote
 * system. The logon message must be the first message sent by the application requesting to initiate a
 * FIX session.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.13 $
 * @created 8/07/2008, 20:51:13
 */
public abstract class LogonMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /** 
     * TagNum = 98 REQUIRED. Starting with 4.0 version.
     */
    protected EncryptMethod encryptMethod;
    
    /** 
     * TagNum = 108 REQUIRED. Starting with 4.0 version.
     */
    protected Integer heartBtInt;
    
    /** 
     * TagNum = 95. Starting with 4.0 version.
     */
    protected Integer rawDataLength;
    
    /** 
     * TagNum = 96. Starting with 4.0 version.
     */
    protected byte[] rawData;
    
    /** 
     * TagNum = 141. Starting with 4.1 version.
     */
    protected Boolean resetSeqNumFlag;
    
    /** 
     * TagNum = 789. Starting with 4.4 version.
     */
    protected Integer nextExpectedMsgSeqNum;
    
    /** 
     * TagNum = 383. Starting with 4.2 version.
     */
    protected Integer maxMessageSize;
    
    /** 
     * TagNum = 384. Starting with 4.2 version.
     */
    protected Integer noMsgTypes;
    
    /** 
     * MsgTpe groups. Starting with 4.2 version.
     */
    protected MsgTypeGroup[] msgTypeGroups;
    
    /** 
     * TagNum = 464. Starting with 4.3 version.
     */
    protected Boolean testMessageIndicator;
    
    /** 
     * TagNum = 553. Starting with 4.3 version.
     */
    protected String username;
    
    /** 
     * TagNum = 554. Starting with 4.3 version.
     */
    protected String password;

    /**
     * TagNum = 925. Starting with 5.0SP1 version.
     */
    protected String newPassword;

    /**
     * TagNum = 1400. Starting with 5.0SP1 version.
     */
    protected Integer encryptedPasswordMethod;

    /**
     * TagNum = 1401. Starting with 5.0SP1 version.
     */
    protected Integer encryptedPasswordLen;

    /**
     * TagNum = 1402. Starting with 5.0SP1 version.
     */
    protected byte[] encryptedPassword;

    /**
     * TagNum = 1403. Starting with 5.0SP1 version.
     */
    protected Integer encryptedNewPasswordLen;

    /**
     * TagNum = 1404. Starting with 5.0SP1 version.
     */
    protected byte[] encryptedNewPassword;

    /**
     * TagNum = 1409. Starting with 5.0SP1 version.
     */
    protected SessionStatus sessionStatus;
    
    /** 
     * TagNum = 1137 REQUIRED. Starting with FIXT 1.1 version.
     */
    protected ApplVerID defaultApplVerID;

    /**
     * TagNum = 1407. Starting with 5.0SP1 version.
     */
    protected Integer defaultApplExtID;

    /**
     * TagNum = 1408. Starting with 5.0SP1 version.
     */
    protected String defaultCstmApplVerID;

    /**
     * TagNum = 58. Starting with 5.0SP1 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 5.0SP1 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 5.0SP1 version.
     */
    protected byte[] encodedText;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public LogonMsg(Header header, ByteBuffer rawMsg) 
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }
    
    public LogonMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.Logon.getValue(), beginString);
    }

    public LogonMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.Logon.getValue(), beginString, applVerID);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.EncryptMethod, required=true)
    public EncryptMethod getEncryptMethod() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encryptMethod field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.EncryptMethod, required=true)
    public void setEncryptMethod(EncryptMethod encryptMethod) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.HeartBtInt, required=true)
    public Integer getHeartBtInt() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param heartBtInt field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.HeartBtInt, required=true)
    public void setHeartBtInt(Integer heartBtInt) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RawData)
    public byte[] getRawData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RawDataLength)
    public Integer getRawDataLength() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param rawDataLength field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RawDataLength)
    public void setRawDataLength(Integer rawDataLength) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param rawData field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RawData)
    public void setRawData(byte[] rawData) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.ResetSeqNumFlag)
    public Boolean getResetSeqNumFlag() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param resetSeqNumFlag field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.ResetSeqNumFlag)
    public void setResetSeqNumFlag(Boolean resetSeqNumFlag) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoMsgTypes)
    public Integer getNoMsgTypes() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of MsgTypeGroup groups. It will also create an array
     * of MsgTypeGroup objects and set the <code>msgTypeGroups</code> field with this array.
     * The created objects inside the array need to be populated with data.<br/>
     * If there where already objects in <code>msgTypeGroups</code> array they will be discarded.
     * @param noMsgTypes number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoMsgTypes)
    public void setNoMsgTypes(Integer noMsgTypes) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for the <code>MsgTypeGroup</code> groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
    public MsgTypeGroup[] getMsgTypeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a  <code>MsgTypeGroup</code> object to the existing array of <code>msgTypeGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noMsgTypes</code> field to the proper value.<br/>
     * Note: If the <code>setNoMsgTypes</code> method has been called there will already be a number of objects in the
     * <code>msgTypeGroups</code> array created.
     * @return newly created and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public MsgTypeGroup addMsgTypeGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method deletes a  <code>MsgTypeGroup</code> object from the existing array of <code>msgTypeGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMsgTypes</code> field to the proper value.
     * @param index position in array to be deleted starting at 0
     * @return deleted group object
     */
    @FIXVersion(introduced="4.2")
    public MsgTypeGroup deleteMsgTypeGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Deletes all the <code>MsgTypeGroup</code> objects from the <code>msgTypeGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMsgTypes</code> field and set it to null.
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearMsgTypeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NextExpectedMsgSeqNum)
    public Integer getNextExpectedMsgSeqNum() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param nextExpectedMsgSeqNum field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NextExpectedMsgSeqNum)
    public void setNextExpectedMsgSeqNum(Integer nextExpectedMsgSeqNum) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MaxMessageSize)
    public Integer getMaxMessageSize() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maxMessageSize field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MaxMessageSize)
    public void setMaxMessageSize(Integer maxMessageSize) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TestMessageIndicator)
    public Boolean getTestMessageIndicator() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param testMessageIndicator field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TestMessageIndicator)
    public void setTestMessageIndicator(Boolean testMessageIndicator) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Username)
    public String getUsername() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param username field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Username)
    public void setUsername(String username) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Password)
    public String getPassword() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param password field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Password)
    public void setPassword(String password) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NewPassword)
    public String getNewPassword() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param newPassword field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NewPassword)
    public void setNewPassword(String newPassword) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedPasswordMethod)
    public Integer getEncryptedPasswordMethod() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encryptedPasswordMethod field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedPasswordMethod)
    public void setEncryptedPasswordMethod(Integer encryptedPasswordMethod) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedPasswordLen)
    public Integer getEncryptedPasswordLen() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encryptedPasswordLen field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedPasswordLen)
    public void setEncryptedPasswordLen(Integer encryptedPasswordLen) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedPassword)
    public byte[] getEncryptedPassword() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encryptedPassword field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedPassword)
    public void setEncryptedPassword(byte[] encryptedPassword) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedNewPasswordLen)
    public Integer getEncryptedNewPasswordLen() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encryptedNewPasswordLen field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedNewPasswordLen)
    public void setEncryptedNewPasswordLen(Integer encryptedNewPasswordLen) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedNewPassword)
    public byte[] getEncryptedNewPassword() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encryptedNewPassword field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncryptedNewPassword)
    public void setEncryptedNewPassword(byte[] encryptedNewPassword) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.SessionStatus)
    public SessionStatus getSessionStatus() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param sessionStatus field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.SessionStatus)
    public void setSessionStatus(SessionStatus sessionStatus) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.DefaultApplVerID, required=true)
    public ApplVerID getDefaultApplVerID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param defaultApplVerID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.DefaultApplVerID, required=true)
    public void setDefaultApplVerID(ApplVerID defaultApplVerID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DefaultApplExtID)
    public Integer getDefaultApplExtID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param defaultApplExtID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DefaultApplExtID)
    public void setDefaultApplExtID(Integer defaultApplExtID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DefaultCstmApplVerID)
    public String getDefaultCstmApplVerID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param defaultCstmApplVerID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DefaultCstmApplVerID)
    public void setDefaultCstmApplVerID(String defaultCstmApplVerID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (encryptMethod != null) {
                TagEncoder.encode(bao, TagNum.EncryptMethod, encryptMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.HeartBtInt, heartBtInt);
            if (rawDataLength != null && rawDataLength.intValue() > 0) {
                if (rawData != null && rawData.length > 0) {
                    rawDataLength = new Integer(rawData.length);
                    TagEncoder.encode(bao, TagNum.RawDataLength, rawDataLength);
                    TagEncoder.encode(bao, TagNum.RawData, rawData);
                }
            }
            TagEncoder.encode(bao, TagNum.ResetSeqNumFlag, resetSeqNumFlag);
            TagEncoder.encode(bao, TagNum.NextExpectedMsgSeqNum, nextExpectedMsgSeqNum);
            TagEncoder.encode(bao, TagNum.MaxMessageSize, maxMessageSize);
            if (noMsgTypes != null && noMsgTypes.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoMsgTypes, noMsgTypes);
                if (msgTypeGroups != null && msgTypeGroups.length == noMsgTypes.intValue()) {
                    for (int i = 0; i < msgTypeGroups.length; i++) {
                        if (msgTypeGroups[i] != null) {
                            bao.write(msgTypeGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "NoMsgTypes field has been set but there is no MsgType data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, header.getMsgType(), TagNum.NoHops.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.TestMessageIndicator, testMessageIndicator);
            TagEncoder.encode(bao, TagNum.Username, username);
            TagEncoder.encode(bao, TagNum.Password, password);
            TagEncoder.encode(bao, TagNum.NewPassword, newPassword);
            TagEncoder.encode(bao, TagNum.EncryptedPasswordMethod, encryptedPasswordMethod);
            if (encryptedPasswordLen != null && encryptedPasswordLen.intValue() > 0) {
                if (encryptedPassword != null && encryptedPassword.length > 0) {
                    encryptedPasswordLen = new Integer(encryptedPassword.length);
                    TagEncoder.encode(bao, TagNum.EncryptedPasswordLen, encryptedPasswordLen);
                    TagEncoder.encode(bao, TagNum.EncryptedPassword, encryptedPassword);
                }
            }
            if (encryptedNewPasswordLen != null && encryptedNewPasswordLen.intValue() > 0) {
                if (encryptedNewPassword != null && encryptedNewPassword.length > 0) {
                    encryptedNewPasswordLen = new Integer(encryptedNewPassword.length);
                    TagEncoder.encode(bao, TagNum.EncryptedNewPasswordLen, encryptedNewPasswordLen);
                    TagEncoder.encode(bao, TagNum.EncryptedNewPassword, encryptedNewPassword);
                }
            }
            if (sessionStatus != null) {
                TagEncoder.encode(bao, TagNum.SessionStatus, sessionStatus.getValue());
            }
            if (defaultApplVerID != null) {
                TagEncoder.encode(bao, TagNum.DefaultApplVerID, defaultApplVerID.getValue());
            }
            TagEncoder.encode(bao, TagNum.DefaultApplExtID, defaultApplExtID);
            TagEncoder.encode(bao, TagNum.DefaultCstmApplVerID, defaultCstmApplVerID);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
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
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case EncryptMethod:
                encryptMethod = EncryptMethod.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())).intValue());
                break;

            case HeartBtInt:
                heartBtInt = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case RawDataLength:
                rawDataLength = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case ResetSeqNumFlag:
                resetSeqNumFlag = BooleanConverter.parse(new String(tag.value, getSessionCharset()));
                break;

            case MaxMessageSize:
                maxMessageSize = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case NextExpectedMsgSeqNum:
                nextExpectedMsgSeqNum = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case TestMessageIndicator:
                testMessageIndicator = BooleanConverter.parse(new String(tag.value, getSessionCharset()));
                break;

            case Username:
                username = new String(tag.value, getSessionCharset());
                break;

            case Password:
                password = new String(tag.value, getSessionCharset());
                break;

            case NewPassword:
                newPassword = new String(tag.value, getSessionCharset());
                break;

            case EncryptedPasswordMethod:
                encryptedPasswordMethod = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case EncryptedPasswordLen:
                encryptedPasswordLen = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case EncryptedNewPasswordLen:
                encryptedNewPasswordLen = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case DefaultApplVerID:
                defaultApplVerID = ApplVerID.valueFor(new String(tag.value, getSessionCharset()));
                break;

            case NoMsgTypes:
                noMsgTypes = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case SessionStatus:
                sessionStatus = SessionStatus.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())).intValue());
                break;

            case DefaultApplExtID:
                defaultApplExtID = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case DefaultCstmApplVerID:
                defaultCstmApplVerID = new String(tag.value, getSessionCharset());
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case EncodedTextLen:
                encodedTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = getNotPresentTagMessage(tag);
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.RawDataLength.getValue()) {
            try {
                rawDataLength = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [RawDataLength] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.RawDataLength.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, rawDataLength.intValue());
            rawData = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncryptedPasswordLen.getValue()) {
            try {
                encryptedPasswordLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncryptedPasswordLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.EncryptedPasswordLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encryptedPasswordLen.intValue());
            encryptedPassword = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncryptedNewPasswordLen.getValue()) {
            try {
                encryptedNewPasswordLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncryptedNewPasswordLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.EncryptedNewPasswordLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encryptedNewPasswordLen.intValue());
            encryptedNewPassword = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncodedTextLen.getValue()) {
            try {
                encodedTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }
        
        return result;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{LogonMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.EncryptMethod, encryptMethod);
        printTagValue(b, TagNum.HeartBtInt, heartBtInt);
        printTagValue(b, TagNum.RawDataLength, rawDataLength);
        printBase64TagValue(b, TagNum.RawData, rawData);
        printTagValue(b, TagNum.ResetSeqNumFlag, resetSeqNumFlag);
        printTagValue(b, TagNum.NextExpectedMsgSeqNum, nextExpectedMsgSeqNum);
        printTagValue(b, TagNum.MaxMessageSize, maxMessageSize);
        printTagValue(b, TagNum.NoMsgTypes, noMsgTypes);
        printTagValue(b, msgTypeGroups);
        printTagValue(b, TagNum.TestMessageIndicator, testMessageIndicator);
        printTagValue(b, TagNum.Username, username);
        printTagValue(b, TagNum.Password, password);
        printTagValue(b, TagNum.NewPassword, newPassword);
        printTagValue(b, TagNum.EncryptedPasswordMethod, encryptedPasswordMethod);
        printTagValue(b, TagNum.EncryptedPasswordLen, encryptedPasswordLen);
        printBase64TagValue(b, TagNum.EncryptedPassword, encryptedPassword);
        printTagValue(b, TagNum.EncryptedNewPasswordLen, encryptedNewPasswordLen);
        printBase64TagValue(b, TagNum.EncryptedNewPassword, encryptedNewPassword);
        printTagValue(b, TagNum.SessionStatus, sessionStatus);
        printTagValue(b, TagNum.DefaultApplVerID, defaultApplVerID);
        printTagValue(b, TagNum.DefaultApplExtID, defaultApplExtID);
        printTagValue(b, TagNum.DefaultCstmApplVerID, defaultCstmApplVerID);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
