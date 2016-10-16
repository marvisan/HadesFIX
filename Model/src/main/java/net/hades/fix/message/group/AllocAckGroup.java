/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocAckGroup.java
 *
 * $Id: AllocAckGroup.java,v 1.1 2011-02-13 04:40:44 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AllocPositionEffect;
import net.hades.fix.message.type.IndividualAllocRejCode;
import net.hades.fix.message.type.IndividualAllocType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Allocation ACK group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AllocAckGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AllocAccount.getValue(),
        TagNum.AllocAcctIDSource.getValue(),
        TagNum.AllocPrice.getValue(),
        TagNum.AllocPositionEffect.getValue(),
        TagNum.IndividualAllocID.getValue(),
        TagNum.IndividualAllocRejCode.getValue(),
        TagNum.AllocText.getValue(),
        TagNum.SecondaryIndividualAllocID.getValue(),
        TagNum.AllocCustomerCapacity.getValue(),
        TagNum.IndividualAllocType.getValue(),
        TagNum.AllocQty.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedAllocTextLen.getValue()
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 79 REQUIRED. Starting with 4.4 version.
     */
    protected String allocAccount;

    /**
     * TagNum = 661. Starting with 4.4 version.
     */
    protected AcctIDSource allocAcctIDSource;

    /**
     * TagNum = 366. Starting with 4.4 version.
     */
    protected Double allocPrice;

    /**
     * TagNum = 1047. Starting with 5.0 version.
     */
    protected AllocPositionEffect allocPositionEffect;

    /**
     * TagNum = 467. Starting with 4.4 version.
     */
    protected String individualAllocID;

    /**
     * TagNum = 776. Starting with 4.4 version.
     */
    protected IndividualAllocRejCode individualAllocRejCode;

    /**
     * Starting with 5.0 version.
     */
    protected NestedParties nestedParties;

    /**
     * TagNum = 161. Starting with 4.4 version.
     */
    protected String allocText;

    /**
     * TagNum = 360. Starting with 4.4 version.
     */
    protected Integer encodedAllocTextLen;

    /**
     * TagNum = 361. Starting with 4.4 version.
     */
    protected byte[] encodedAllocText;

    /**
     * TagNum = 999. Starting with 5.0 version.
     */
    protected String secondaryIndividualAllocID;

    /**
     * TagNum = 993. Starting with 5.0 version.
     */
    protected String allocCustomerCapacity;

    /**
     * TagNum = 992. Starting with 5.0 version.
     */
    protected IndividualAllocType individualAllocType;

    /**
     * TagNum = 80. Starting with 5.0 version.
     */
    protected Double allocQty;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public AllocAckGroup() {
    }
    
    public AllocAckGroup(FragmentContext context) {
        super(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAccount, required=true)
    public String getAllocAccount() {
        return allocAccount;
    }

    /**
     * Message field setter.
     * @param allocAccount field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAccount, required=true)
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAcctIDSource)
    public AcctIDSource getAllocAcctIDSource() {
        return allocAcctIDSource;
    }

    /**
     * Message field setter.
     * @param allocAcctIDSource field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAcctIDSource)
    public void setAllocAcctIDSource(AcctIDSource allocAcctIDSource) {
        this.allocAcctIDSource = allocAcctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocPrice)
    public Double getAllocPrice() {
        return allocPrice;
    }

    /**
     * Message field setter.
     * @param allocPrice field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocPrice)
    public void setAllocPrice(Double allocPrice) {
        this.allocPrice = allocPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocPositionEffect)
    public AllocPositionEffect getAllocPositionEffect() {
        return allocPositionEffect;
    }

    /**
     * Message field setter.
     * @param allocPositionEffect field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocPositionEffect)
    public void setAllocPositionEffect(AllocPositionEffect allocPositionEffect) {
        this.allocPositionEffect = allocPositionEffect;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.IndividualAllocID)
    public String getIndividualAllocID() {
        return individualAllocID;
    }

    /**
     * Message field setter.
     * @param individualAllocID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.IndividualAllocID)
    public void setIndividualAllocID(String individualAllocID) {
        this.individualAllocID = individualAllocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.IndividualAllocRejCode)
    public IndividualAllocRejCode getIndividualAllocRejCode() {
        return individualAllocRejCode;
    }

    /**
     * Message field setter.
     * @param individualAllocRejCode field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.IndividualAllocRejCode)
    public void setIndividualAllocRejCode(IndividualAllocRejCode individualAllocRejCode) {
        this.individualAllocRejCode = individualAllocRejCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    public NestedParties getNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "5.0")
    public void setNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component to null.<br/>
     */
    @FIXVersion(introduced = "5.0")
    public void clearNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocText)
    public String getAllocText() {
        return allocText;
    }

    /**
     * Message field setter.
     * @param allocText field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocText)
    public void setAllocText(String allocText) {
        this.allocText = allocText;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedAllocTextLen)
    public Integer getEncodedAllocTextLen() {
        return encodedAllocTextLen;
    }

    /**
     * Message field setter.
     * @param encodedAllocTextLen field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedAllocTextLen)
    public void setEncodedAllocTextLen(Integer encodedAllocTextLen) {
        this.encodedAllocTextLen = encodedAllocTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedAllocText)
    public byte[] getEncodedAllocText() {
        return encodedAllocText;
    }

    /**
     * Message field setter.
     * @param encodedAllocText field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedAllocText)
    public void setEncodedAllocText(byte[] encodedAllocText) {
        this.encodedAllocText = encodedAllocText;
        if (encodedAllocTextLen == null) {
            encodedAllocTextLen = new Integer(encodedAllocText.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryIndividualAllocID)
    public String getSecondaryIndividualAllocID() {
        return secondaryIndividualAllocID;
    }

    /**
     * Message field setter.
     * @param secondaryIndividualAllocID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryIndividualAllocID)
    public void setSecondaryIndividualAllocID(String secondaryIndividualAllocID) {
        this.secondaryIndividualAllocID = secondaryIndividualAllocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocCustomerCapacity)
    public String getAllocCustomerCapacity() {
        return allocCustomerCapacity;
    }

    /**
     * Message field setter.
     * @param allocCustomerCapacity field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocCustomerCapacity)
    public void setAllocCustomerCapacity(String allocCustomerCapacity) {
        this.allocCustomerCapacity = allocCustomerCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.IndividualAllocType)
    public IndividualAllocType getIndividualAllocType() {
        return individualAllocType;
    }

    /**
     * Message field setter.
     * @param individualAllocType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.IndividualAllocType)
    public void setIndividualAllocType(IndividualAllocType individualAllocType) {
        this.individualAllocType = individualAllocType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocQty)
    public Double getAllocQty() {
        return allocQty;
    }

    /**
     * Message field setter.
     * @param allocQty field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocQty)
    public void setAllocQty(Double allocQty) {
        this.allocQty = allocQty;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.AllocAccount.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (allocAccount == null || allocAccount.trim().isEmpty()) {
            errorMsg.append(" [AllocAccount]");
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
            TagEncoder.encode(bao, TagNum.AllocAccount, allocAccount);
            if (allocAcctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AllocAcctIDSource, allocAcctIDSource.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocPrice, allocPrice);
            if (allocPositionEffect != null) {
                TagEncoder.encode(bao, TagNum.AllocPositionEffect, allocPositionEffect.getValue());
            }
            TagEncoder.encode(bao, TagNum.IndividualAllocID, individualAllocID);
            if (individualAllocRejCode != null) {
                TagEncoder.encode(bao, TagNum.IndividualAllocRejCode, individualAllocRejCode.getValue());
            }
            if (nestedParties != null) {
                bao.write(nestedParties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.AllocText, allocText);
            if (encodedAllocTextLen != null && encodedAllocTextLen.intValue() > 0) {
                if (encodedAllocText != null && encodedAllocText.length > 0) {
                    encodedAllocTextLen = new Integer(encodedAllocText.length);
                    TagEncoder.encode(bao, TagNum.EncodedAllocTextLen, encodedAllocTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedAllocText);
                }
            }
            TagEncoder.encode(bao, TagNum.SecondaryIndividualAllocID, secondaryIndividualAllocID);
            TagEncoder.encode(bao, TagNum.AllocCustomerCapacity, allocCustomerCapacity);
            if (individualAllocType != null) {
                TagEncoder.encode(bao, TagNum.IndividualAllocType, individualAllocType.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocQty, allocQty);
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
            case AllocAccount:
                allocAccount = new String(tag.value, sessionCharset);
                break;

            case AllocAcctIDSource:
                allocAcctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocPrice:
                allocPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case AllocPositionEffect:
                allocPositionEffect = AllocPositionEffect.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case IndividualAllocID:
                individualAllocID = new String(tag.value, sessionCharset);
                break;

            case IndividualAllocRejCode:
                individualAllocRejCode = IndividualAllocRejCode.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocText:
                allocText = new String(tag.value, sessionCharset);
                break;

            case SecondaryIndividualAllocID:
                secondaryIndividualAllocID = new String(tag.value, sessionCharset);
                break;

            case AllocCustomerCapacity:
                allocCustomerCapacity = new String(tag.value, sessionCharset);
                break;

            case IndividualAllocType:
                individualAllocType = IndividualAllocType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocQty:
                allocQty = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [AllocAckGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedAllocTextLen.getValue()) {
            try {
                encodedAllocTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedAllocTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedAllocTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedAllocTextLen.intValue());
            encodedAllocText = dataTag.value;
        }

        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    /// </editor-fold>
    
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
        b.append("{AllocAckGroup=");
        printTagValue(b, TagNum.AllocAccount, allocAccount);
        printTagValue(b, TagNum.AllocAcctIDSource, allocAcctIDSource);
        printTagValue(b, TagNum.AllocPrice, allocPrice);
        printTagValue(b, TagNum.AllocPositionEffect, allocPositionEffect);
        printTagValue(b, TagNum.IndividualAllocID, individualAllocID);
        printTagValue(b, TagNum.IndividualAllocRejCode, individualAllocRejCode);
        printTagValue(b, nestedParties);
        printTagValue(b, TagNum.AllocText, allocText);
        printTagValue(b, TagNum.EncodedAllocTextLen, encodedAllocTextLen);
        printTagValue(b, TagNum.EncodedAllocText, encodedAllocText);
        printTagValue(b, TagNum.SecondaryIndividualAllocID, secondaryIndividualAllocID);
        printTagValue(b, TagNum.AllocCustomerCapacity, allocCustomerCapacity);
        printTagValue(b, TagNum.IndividualAllocType, individualAllocType);
        printTagValue(b, TagNum.AllocQty, allocQty);
         b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
