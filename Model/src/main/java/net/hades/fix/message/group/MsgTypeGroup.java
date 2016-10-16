/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MsgTypeGroup.java
 *
 * $Id: MsgTypeGroup.java,v 1.9 2010-11-23 10:20:17 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.BooleanConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.MsgDirection;
import net.hades.fix.message.util.TagEncoder;

/**
 * Specifies a group, supported MsgType. Required if NoMsgTypes is > 0.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 14/08/2008, 19:13:01
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MsgTypeGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /** 
     * TagNum = 372. Starting with 4.2 version.
     */
    protected String refMsgType;
    
    /** 
     * TagNum = 385. Starting with 4.2 version.
     */
    protected MsgDirection msgDirection;
    
    /** 
     * TagNum = 1130. Starting with T1.1 version.
     */
    protected ApplVerID refApplVerID;

    /**
     * TagNum = 1406. Starting with 5.0SP1 version.
     */
    protected Integer refApplExtID;

    /** 
     * TagNum = 1131. Starting with T1.1 version.
     */
    protected String refCstmApplVerID;

    /**
     * TagNum = 1410. Starting with T1.1 version.
     */
    protected Boolean defaultVerIndicator;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MsgTypeGroup() {
    }
    
    public MsgTypeGroup(FragmentContext context) {
        super(context);
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
    @TagNumRef(tagNum= TagNum.RefMsgType, required=true)
    public String getRefMsgType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param msgType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.RefMsgType, required=true)
    public void setRefMsgType(String msgType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MsgDirection)
    public MsgDirection getMsgDirection() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param msgDirection field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MsgDirection)
    public void setMsgDirection(MsgDirection msgDirection) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RefApplVerID)
    public ApplVerID getRefApplVerID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param refApplVerID field value
     */
    @FIXVersion(introduced="5.0")
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RefCstmApplVerID)
    public String getRefCstmApplVerID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param refCstmApplVerID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RefCstmApplVerID)
    public void setRefCstmApplVerID(String refCstmApplVerID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DefaultVerIndicator)
    public Boolean getDefaultVerIndicator() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param defaultVerIndicator field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DefaultVerIndicator)
    public void setDefaultVerIndicator(Boolean defaultVerIndicator) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.RefMsgType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (refMsgType == null) {
            errorMsg.append(" [RefMsgType]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {             validateRequiredTags();         }

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            TagEncoder.encode(bao, TagNum.RefMsgType, refMsgType);
            if (msgDirection != null) {
                TagEncoder.encode(bao, TagNum.MsgDirection, msgDirection.getValue());
            }
            if (refApplVerID != null) {
                TagEncoder.encode(bao, TagNum.RefApplVerID, refApplVerID.getValue());
            }
            if (refApplVerID != null) {
                TagEncoder.encode(bao, TagNum.RefApplVerID, refApplVerID.getValue());
            }
            TagEncoder.encode(bao, TagNum.RefCstmApplVerID, refCstmApplVerID);
            TagEncoder.encode(bao, TagNum.DefaultVerIndicator, defaultVerIndicator);
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
            case RefMsgType:
                refMsgType = new String(tag.value, sessionCharset);;
                break;

            case MsgDirection:
                msgDirection = MsgDirection.valueFor(new String(tag.value, sessionCharset));
                break;

            case RefApplVerID:
                refApplVerID = ApplVerID.valueFor(new String(tag.value, sessionCharset));
                break;

            case RefApplExtID:
                refApplExtID = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case RefCstmApplVerID:
                refCstmApplVerID = new String(tag.value, sessionCharset);
                break;

            case DefaultVerIndicator:
                defaultVerIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [MsgTypeGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{MsgTypeGroup=");
        printTagValue(b, TagNum.RefMsgType, refMsgType);
        printTagValue(b, TagNum.MsgDirection, msgDirection);
        printTagValue(b, TagNum.RefApplVerID, refApplVerID);
        printTagValue(b, TagNum.RefApplExtID, refApplExtID);
        printTagValue(b, TagNum.RefCstmApplVerID, refCstmApplVerID);
        printTagValue(b, TagNum.DefaultVerIndicator, defaultVerIndicator);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
