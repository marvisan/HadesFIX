/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogonMsg42.java
 *
 * $Id: LogonMsg42.java,v 1.9 2011-04-14 23:44:39 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.EncryptMethod;
import net.hades.fix.message.type.TagNum;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v42.MsgTypeGroup42;
import net.hades.fix.message.type.BeginString;

/**
 * The logon message is utilized to authenticate a user attempting to establish a connection to a remote
 * system. The logon message must be the first message sent by the application requesting to initiate a
 * FIX 4.2 session.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 18/09/2008, 20:51:13
 */
public class LogonMsg42 extends LogonMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = -2723082855687256098L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncryptMethod.getValue(),
        TagNum.HeartBtInt.getValue(),
        TagNum.ResetSeqNumFlag.getValue(),
        TagNum.MaxMessageSize.getValue(),
        TagNum.NoMsgTypes.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RawDataLength.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncryptMethod.getValue(),
        TagNum.HeartBtInt.getValue()
    }));

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> MSG_TYPE_GROUP_TAGS = new MsgTypeGroup42().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(MSG_TYPE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MSG_TYPE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public LogonMsg42(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public LogonMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LogonMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

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
        msgTypeGroups = new MsgTypeGroup[noMsgTypes.intValue()];
        for (int i = 0; i < msgTypeGroups.length; i++) {
            msgTypeGroups[i] = new MsgTypeGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        }
    }
    
    @Override
    public MsgTypeGroup[] getMsgTypeGroups() {
        return msgTypeGroups;
    }

    @Override
    public MsgTypeGroup addMsgTypeGroup() {
        
        MsgTypeGroup group = new MsgTypeGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            noMsgTypes = new Integer(msgTypeGroups.length);
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
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (MSG_TYPE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMsgTypes != null && noMsgTypes.intValue() > 0) {
                message.reset();
                if (msgTypeGroups == null) {
                    msgTypeGroups = new MsgTypeGroup[noMsgTypes.intValue()];
                }
                for (int i = 0; i < msgTypeGroups.length; i++) {
                    MsgTypeGroup group = new MsgTypeGroup42(context);
                    group.decode(message);
                    msgTypeGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LogonMsg] message version [4.2].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
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
