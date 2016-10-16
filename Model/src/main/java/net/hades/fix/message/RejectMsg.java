/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RejectMsg.java
 *
 * $Id: RejectMsg.java,v 1.13 2011-04-28 10:07:48 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The reject message should be issued when a message is received but cannot be properly processed due
 * to a session-level rule violation. An example of when a reject may be appropriate would be the receipt
 * of a message with invalid basic data (e.g. MsgType=&) which successfully passes de-encryption,
 * CheckSum and BodyLength checks. As a rule, messages should be forwarded to the trading
 * application for business level rejections whenever possible.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.13 $
 * @created 11/08/2008, 19:45:49
 */
public abstract class RejectMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /** 
     * TagNum = 45 REQUIRED. Starting with 4.0 version.
     */
    protected Integer refSeqNo;
    
    /**
     * TagNum = 58. Starting with 4.0 version.
     */
    protected String text;
    
    /** 
     * TagNum = 371. Starting with 4.2 version.
     */
    protected Integer refTagID;
    
    /** 
     * TagNum = 372. Starting with 4.2 version.
     */
    protected String refMsgType;

    /**
     * TagNum = 1130. Starting with 5.0SP1 version.
     */
    protected ApplVerID refApplVerID;

    /**
     * TagNum = 1406. Starting with 5.0SP1 version.
     */
    protected Integer refApplExtID;

    /**
     * TagNum = 1131. Starting with 5.0SP1 version.
     */
    protected String refCstmApplVerID;
    
    /**
     * TagNum = 373. Starting with 4.2 version.
     */
    protected SessionRejectReason sessionRejectReason;
    
    /** 
     * TagNum = 354. Starting with 4.2 version.
     */
    protected Integer encodedTextLen;
    
    /** 
     * TagNum = 355. Starting with 4.2 version.
     */
    protected byte[] encodedText;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RejectMsg(Header header, ByteBuffer rawMsg) 
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }
    
    public RejectMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.Reject.getValue(), beginString);
    }

    public RejectMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.Reject.getValue(), beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum= TagNum.EncodedText)
    public byte[] getEncodedText() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RefSeqNo, required=true)
    public Integer getRefSeqNo() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param refSeqNo field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RefSeqNo, required=true)
    public void setRefSeqNo(Integer refSeqNo) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.RefTagID)
    public Integer getRefTagID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param refTagID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.RefTagID)
    public void setRefTagID(Integer refTagID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RefMsgType)
    public String getRefMsgType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param refMsgType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.RefMsgType)
    public void setRefMsgType(String refMsgType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RefApplVerID)
    public ApplVerID getRefApplVerID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param refApplVerID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RefApplVerID)
    public void setRefApplVerID(ApplVerID refApplVerID) {
       throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RefApplExtID)
    public Integer getRefApplExtID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param refApplExtID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RefApplExtID)
    public void setRefApplExtID(Integer refApplExtID) {
       throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RefCstmApplVerID)
    public String getRefCstmApplVerID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param refCstmApplVerID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RefCstmApplVerID)
    public void setRefCstmApplVerID(String refCstmApplVerID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SessionRejectReason)
    public SessionRejectReason getSessionRejectReason() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param sessionRejectReason field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SessionRejectReason)
    public void setSessionRejectReason(SessionRejectReason sessionRejectReason) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (refSeqNo == null) {
            errorMsg.append(" [RefSeqNo]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            TagEncoder.encode(bao, TagNum.RefSeqNo, refSeqNo);
            TagEncoder.encode(bao, TagNum.RefTagID, refTagID);
            TagEncoder.encode(bao, TagNum.RefMsgType, refMsgType);
            if (refApplVerID != null) {
                TagEncoder.encode(bao, TagNum.RefApplVerID, refApplVerID.getValue());
            }
            TagEncoder.encode(bao, TagNum.RefApplExtID, refApplExtID);
            TagEncoder.encode(bao, TagNum.RefCstmApplVerID, refCstmApplVerID);
            if (sessionRejectReason != null) {
                TagEncoder.encode(bao, TagNum.SessionRejectReason, sessionRejectReason.getValue());
            }
            TagEncoder.encode(bao, TagNum.Text, text, sessionCharset);
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
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.RefSeqNo, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RefSeqNo, refSeqNo);
            }
            if (MsgUtil.isTagInList(TagNum.RefTagID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RefTagID, refTagID);
            }
            if (MsgUtil.isTagInList(TagNum.RefMsgType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RefMsgType, refMsgType);
            }
            if (sessionRejectReason != null && MsgUtil.isTagInList(TagNum.SessionRejectReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SessionRejectReason, sessionRejectReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text, sessionCharset);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
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
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case RefSeqNo:
                refSeqNo = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case RefTagID:
                refTagID = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case RefMsgType:
                refMsgType = new String(tag.value, getSessionCharset());
                break;

            case RefApplVerID:
                refApplVerID = ApplVerID.valueFor(new String(tag.value));
                break;

            case RefApplExtID:
                refApplExtID = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case RefCstmApplVerID:
                refCstmApplVerID = new String(tag.value, getSessionCharset());
                break;

            case SessionRejectReason:
                sessionRejectReason = SessionRejectReason.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())).intValue());
                break;

            case Text:
                text = new String(tag.value, getSessionCharset());
                break;

            default:
                String error = getNotPresentTagMessage(tag);
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException, InvalidMsgException {
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedTextLen.getValue()) {
            try {
                encodedTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.EncodedTextLen.getValue(), error);
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
        StringBuilder b = new StringBuilder("{RejectMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.RefSeqNo, refSeqNo);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.RefTagID, refTagID);
        printTagValue(b, TagNum.RefMsgType, refMsgType);
        printTagValue(b, TagNum.RefApplVerID, refApplVerID);
        printTagValue(b, TagNum.RefApplExtID, refApplExtID);
        printTagValue(b, TagNum.RefCstmApplVerID, refCstmApplVerID);
        printTagValue(b, TagNum.SessionRejectReason, sessionRejectReason);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
