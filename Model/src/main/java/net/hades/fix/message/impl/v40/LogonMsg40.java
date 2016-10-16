/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogonMsg40.java
 *
 * $Id: LogonMsg40.java,v 1.8 2010-03-31 11:05:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;

import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.hades.fix.message.Header;
import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.EncryptMethod;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.BeginString;

/**
 * The logon message is utilized to authenticate a user attempting to establish a connection to a remote
 * system. The logon message must be the first message sent by the application requesting to initiate a
 * FIX 4.0 session.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 8/07/2008, 20:51:13
 */
public class LogonMsg40 extends LogonMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncryptMethod.getValue(),
        TagNum.HeartBtInt.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RawDataLength.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncryptMethod.getValue(),
        TagNum.HeartBtInt.getValue()
    }));

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public LogonMsg40(Header header, ByteBuffer rawMsg) 
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public LogonMsg40(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LogonMsg40(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException, InvalidMsgException {
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LogonMsg] message version [4.0].";
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
