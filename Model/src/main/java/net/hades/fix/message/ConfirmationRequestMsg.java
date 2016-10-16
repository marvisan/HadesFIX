/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfirmationRequestMsg.java
 *
 * $Id: AllocationInstructionMsg.java,v 1.5 2011-10-21 10:31:03 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderAllocGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AllocAccountType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ConfirmType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Confirmation Request message is used to request a Confirmation message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 29/11/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ConfirmationRequestMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ConfirmReqID.getValue(),
        TagNum.ConfirmType.getValue(),
        TagNum.NoOrders.getValue(),
        TagNum.AllocID.getValue(),
        TagNum.SecondaryAllocID.getValue(),
        TagNum.IndividualAllocID.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.AllocAccount.getValue(),
        TagNum.AllocAcctIDSource.getValue(),
        TagNum.AllocAccountType.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ConfirmReqID.getValue(),
        TagNum.ConfirmType.getValue(),
        TagNum.TransactTime.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 859 REQUIRED. Starting with 4.4 version.
     */
    protected String confirmReqID;

    /**
     * TagNum = 773 REQUIRED. Starting with 4.4 version.
     */
    protected ConfirmType confirmType;

    /**
     * TagNum = 73. Starting with 4.4 version.
     */
    protected Integer noOrders;

    /**
     * Starting with 4.4 version.
     */
    protected OrderAllocGroup[] orderAllocGroups;

    /**
     * TagNum = 70. Starting with 4.4 version.
     */
    protected String allocID;

    /**
     * TagNum = 793. Starting with 4.4 version.
     */
    protected String secondaryAllocID;

    /**
     * TagNum = 467. Starting with 4.4 version.
     */
    protected String individualAllocID;

    /**
     * TagNum = 60 REQUIRED. Starting with 4.4 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 79 REQUIRED. Starting with 4.4 version.
     */
    protected String allocAccount;

    /**
     * TagNum = 661. Starting with 4.4 version.
     */
    protected AcctIDSource allocAcctIDSource;

    /**
     * TagNum = 798. Starting with 4.4 version.
     */
    protected AllocAccountType allocAccountType;

    /**
     * TagNum = 58. Starting with 4.4 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.4 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.4 version.
     */
    protected byte[] encodedText;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ConfirmationRequestMsg() {
        super();
    }

    public ConfirmationRequestMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public ConfirmationRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.ConfirmationRequest.getValue(), beginString);
    }

    public ConfirmationRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.ConfirmationRequest.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.ConfirmReqID, required=true)
    public String getConfirmReqID() {
        return confirmReqID;
    }

    /**
     * Message field setter.
     * @param confirmReqID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ConfirmReqID, required=true)
    public void setConfirmReqID(String confirmReqID) {
        this.confirmReqID = confirmReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ConfirmType, required=true)
    public ConfirmType getConfirmType() {
        return confirmType;
    }

    /**
     * Message field setter.
     * @param confirmType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ConfirmType, required=true)
    public void setConfirmType(ConfirmType confirmType) {
        this.confirmType = confirmType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoOrders)
    public Integer getNoOrders() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link OrderAllocGroup} groups. It will also create an array
     * of {@link OrderAllocGroup} objects and set the <code>orderAllocGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>orderAllocGroups</code> array they will be discarded.<br/>
     * @param noOrders field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoOrders)
    public void setNoOrders(Integer noOrders) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link OrderAllocGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public OrderAllocGroup[] getOrderAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link OrderAllocGroup} object to the existing array of <code>orderAllocGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noOrders</code> field to the proper value.<br/>
     * Note: If the <code>setNoOrders</code> method has been called there will already be a number of objects in the
     * <code>OrderAllocGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public OrderAllocGroup addOrderAllocGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link OrderAllocGroup} object from the existing array of <code>orderAllocGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noOrders</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public OrderAllocGroup deleteOrderAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link OrderAllocGroup} objects from the <code>orderAllocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noOrders</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearOrderAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocID)
    public String getAllocID() {
        return allocID;
    }

    /**
     * Message field setter.
     * @param allocID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocID)
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryAllocID)
    public String getSecondaryAllocID() {
        return secondaryAllocID;
    }

    /**
     * Message field setter.
     * @param secondaryAllocID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryAllocID)
    public void setSecondaryAllocID(String secondaryAllocID) {
        this.secondaryAllocID = secondaryAllocID;
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
    @TagNumRef(tagNum=TagNum.TransactTime, required=true)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransactTime, required=true)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

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
    @TagNumRef(tagNum=TagNum.AllocAccountType)
    public AllocAccountType getAllocAccountType() {
        return allocAccountType;
    }

    /**
     * Message field setter.
     * @param allocAccountType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAccountType)
    public void setAllocAccountType(AllocAccountType allocAccountType) {
        this.allocAccountType = allocAccountType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedText)
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
        if (confirmReqID == null || confirmReqID.trim().isEmpty()) {
            errorMsg.append(" [ConfirmReqID]");
            hasMissingTag = true;
        }
        if (confirmType == null) {
            errorMsg.append(" [ConfirmType]");
            hasMissingTag = true;
        }
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
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
            TagEncoder.encode(bao, TagNum.ConfirmReqID, confirmReqID);
            if (confirmType != null) {
                TagEncoder.encode(bao, TagNum.ConfirmType, confirmType.getValue());
            }
            if (noOrders != null) {
                TagEncoder.encode(bao, TagNum.NoOrders, noOrders);
                if (orderAllocGroups != null && orderAllocGroups.length == noOrders.intValue()) {
                    for (int i = 0; i < noOrders.intValue(); i++) {
                        if (orderAllocGroups[i] != null) {
                            bao.write(orderAllocGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "OrderAllocGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoOrders.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.AllocID, allocID);
            TagEncoder.encode(bao, TagNum.SecondaryAllocID, secondaryAllocID);
            TagEncoder.encode(bao, TagNum.IndividualAllocID, individualAllocID);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encode(bao, TagNum.AllocAccount, allocAccount);
            if (allocAcctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AllocAcctIDSource, allocAcctIDSource.getValue());
            }
            if (allocAccountType != null) {
                TagEncoder.encode(bao, TagNum.AllocAccountType, allocAccountType.getValue());
            }
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
            case ConfirmReqID:
                confirmReqID = new String(tag.value, sessionCharset);
                break;

            case ConfirmType:
                confirmType = ConfirmType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoOrders:
                noOrders = new Integer(new String(tag.value, sessionCharset));
                break;
                
            case AllocID:
                allocID = new String(tag.value, sessionCharset);
                break;

            case SecondaryAllocID:
                secondaryAllocID = new String(tag.value, sessionCharset);
                break;

            case IndividualAllocID:
                individualAllocID = new String(tag.value, sessionCharset);
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case AllocAccount:
                allocAccount = new String(tag.value, sessionCharset);
                break;

            case AllocAcctIDSource:
                allocAcctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocAccountType:
                allocAccountType = AllocAccountType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ConfirmationRequestMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }

        return result;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
        StringBuilder b = new StringBuilder("{ConfirmationRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.ConfirmReqID, confirmReqID);
        printTagValue(b, TagNum.ConfirmType, confirmType);
        printTagValue(b, TagNum.NoOrders, noOrders);
        printTagValue(b, orderAllocGroups);
        printTagValue(b, TagNum.AllocID, allocID);
        printTagValue(b, TagNum.SecondaryAllocID, secondaryAllocID);
        printTagValue(b, TagNum.IndividualAllocID, individualAllocID);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.AllocAccount, allocAccount);
        printTagValue(b, TagNum.AllocAcctIDSource, allocAcctIDSource);
        printTagValue(b, TagNum.AllocAccountType, allocAccountType);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
