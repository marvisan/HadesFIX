/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationReportAckMsg.java
 *
 * $Id: AllocationInstructionAckMsg.java,v 1.2 2011-04-28 10:07:44 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.AllocRejCode;
import net.hades.fix.message.type.MsgType;

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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AllocAckGroup;
import net.hades.fix.message.type.AllocIntermedReqType;
import net.hades.fix.message.type.AllocReportType;
import net.hades.fix.message.type.AllocStatus;
import net.hades.fix.message.type.AllocTransType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.AvgPxIndicator;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.util.MsgUtil;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Allocation Report Ack message is used to acknowledge the receipt of and provide status for an Allocation
 * Report message.
 * It is possible that multiple Allocation Report Ack messages can be generated for a single Allocation Report
 * message to acknowledge the receipt and then to detail the acceptance or rejection of the Allocation Report
 * message.
 * It is recommended, when appropriate, that the MatchStatus field be used in the Allocation Report Ack to denote
 * whether any financial details provided in the Allocation Report with AllocStatus of ‘Accepted’ were matched
 * by the Initiator. If a match takes place and succeeds, then the match status will be '0-Compared and affirmed'.
 * If the match takes place and fails, or no match takes place, then the match status will be '1-Uncompared or
 * unaffirmed'.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/01/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AllocationReportAckMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AllocReportID.getValue(),
        TagNum.AllocID.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.AvgPxIndicator.getValue(),
        TagNum.Quantity.getValue(),
        TagNum.AllocTransType.getValue(),
        TagNum.SecondaryAllocID.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.AllocStatus.getValue(),
        TagNum.AllocRejCode.getValue(),
        TagNum.AllocReportType.getValue(),
        TagNum.AllocIntermedReqType.getValue(),
        TagNum.MatchStatus.getValue(),
        TagNum.Product.getValue(),
        TagNum.SecurityType.getValue(),
        TagNum.Text.getValue(),
        TagNum.NoAllocs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AllocReportID.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 735 REQUIRED. Starting with 4.4 version.
     */
    protected String allocReportID;

    /**
     * TagNum = 70. Starting with 4.4 version.
     */
    protected String allocID;

    /**
     * TagNum = 715. Starting with 5.0 version.
     */
    protected Date clearingBusinessDate;

    /**
     * TagNum = 819. Starting with 5.0 version.
     */
    protected AvgPxIndicator avgPxIndicator;

    /**
     * TagNum = 53. Starting with 5.0 version.
     */
    protected Double quantity;

    /**
     * TagNum = 71. Starting with 5.0 version.
     */
    protected AllocTransType allocTransType;

    /**
     * Starting with 4.4 version.
     */
    protected Parties parties;

    /**
     * TagNum = 793. Starting with 4.4 version.
     */
    protected String secondaryAllocID;

    /**
     * TagNum = 75. Starting with 4.4 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 60. Starting with 4.4 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 87 REQUIRED. Starting with 4.4 version.
     */
    protected AllocStatus allocStatus;

    /**
     * TagNum = 88. Starting with 4.4 version.
     */
    protected AllocRejCode allocRejCode;

    /**
     * TagNum = 71. Starting with 4.4 version.
     */
    protected AllocReportType allocReportType;

    /**
     * TagNum = 808. Starting with 4.4 version.
     */
    protected AllocIntermedReqType allocIntermedReqType;

    /**
     * TagNum = 573. Starting with 4.4 version.
     */
    protected MatchStatus matchStatus;

    /**
     * TagNum = 460. Starting with 4.4 version.
     */
    protected Product product;

    /**
     * TagNum = 167. Starting with 4.4 version.
     */
    protected String securityType;

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

    /**
     * TagNum = 78. Starting with 4.4 version.
     */
    protected Integer noAllocs;

    /**
     * Starting with 4.4 version.
     */
    protected AllocAckGroup[] allocAckGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AllocationReportAckMsg() {
        super();
    }

    public AllocationReportAckMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public AllocationReportAckMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.AllocationReportAck.getValue(), beginString);
    }

    public AllocationReportAckMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.AllocationReportAck.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.AllocReportID, required=true)
    public String getAllocReportID() {
        return allocReportID;
    }

    /**
     * Message field setter.
     * @param allocReportID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocReportID, required=true)
    public void setAllocReportID(String allocReportID) {
        this.allocReportID = allocReportID;
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    /**
     * Message field setter.
     * @param clearingBusinessDate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AvgPxIndicator)
    public AvgPxIndicator getAvgPxIndicator() {
        return avgPxIndicator;
    }

    /**
     * Message field setter.
     * @param avgPxIndicator field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AvgPxIndicator)
    public void setAvgPxIndicator(AvgPxIndicator avgPxIndicator) {
        this.avgPxIndicator = avgPxIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.Quantity)
    public Double getQuantity() {
        return quantity;
    }

    /**
     * Message field setter.
     * @param quantity field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.Quantity)
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocTransType, required=true)
    public AllocTransType getAllocTransType() {
        return allocTransType;
    }

    /**
     * Message field setter.
     * @param allocTransType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocTransType, required=true)
    public void setAllocTransType(AllocTransType allocTransType) {
        this.allocTransType = allocTransType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @TagNumRef(tagNum=TagNum.TradeDate)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocStatus)
    public AllocStatus getAllocStatus() {
        return allocStatus;
    }

    /**
     * Message field setter.
     * @param allocStatus field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocStatus)
    public void setAllocStatus(AllocStatus allocStatus) {
        this.allocStatus = allocStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocRejCode)
    public AllocRejCode getAllocRejCode() {
        return allocRejCode;
    }

    /**
     * Message field setter.
     * @param allocRejCode field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocRejCode)
    public void setAllocRejCode(AllocRejCode allocRejCode) {
        this.allocRejCode = allocRejCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocReportType)
    public AllocReportType getAllocReportType() {
        return allocReportType;
    }

    /**
     * Message field setter.
     * @param allocReportType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocReportType)
    public void setAllocReportType(AllocReportType allocReportType) {
        this.allocReportType = allocReportType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocIntermedReqType)
    public AllocIntermedReqType getAllocIntermedReqType() {
        return allocIntermedReqType;
    }

    /**
     * Message field setter.
     * @param allocIntermedReqType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocIntermedReqType)
    public void setAllocIntermedReqType(AllocIntermedReqType allocIntermedReqType) {
        this.allocIntermedReqType = allocIntermedReqType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    /**
     * Message field setter.
     * @param matchStatus field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Product)
    public Product getProduct() {
        return product;
    }

    /**
     * Message field setter.
     * @param product field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Product)
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        return securityType;
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
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

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public Integer getNoAllocs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link AllocAckGroup} groups. It will also create an array
     * of {@link AllocAckGroup} objects and set the <code>allocAckGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>allocAckGroups</code> array they will be discarded.<br/>
     * @param noAllocs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public void setNoAllocs(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link AllocAckGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public AllocAckGroup[] getAllocAckGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link AllocAckGroup} object to the existing array of <code>allocAckGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * Note: If the <code>setNoAllocs</code> method has been called there will already be a number of objects in the
     * <code>allocAckGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public AllocAckGroup addAllocAckGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link AllocAckGroup} object from the existing array of <code>allocAckGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public AllocAckGroup deleteAllocAckGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link AllocAckGroup} objects from the <code>allocAckGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noAllocs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearAllocAckGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (allocReportID == null || allocReportID.trim().isEmpty()) {
            errorMsg.append(" [AllocReportID]");
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
            TagEncoder.encode(bao, TagNum.AllocReportID, allocReportID);
            TagEncoder.encode(bao, TagNum.AllocID, allocID);
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            if (avgPxIndicator != null) {
                TagEncoder.encode(bao, TagNum.AvgPxIndicator, avgPxIndicator.getValue());
            }
            TagEncoder.encode(bao, TagNum.Quantity, quantity);
            if (allocTransType != null) {
                TagEncoder.encode(bao, TagNum.AllocTransType, allocTransType.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.SecondaryAllocID, secondaryAllocID);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            if (allocStatus != null) {
                TagEncoder.encode(bao, TagNum.AllocStatus, allocStatus.getValue());
            }
            if (allocRejCode != null) {
                TagEncoder.encode(bao, TagNum.AllocRejCode, allocRejCode.getValue());
            }
            if (allocReportType != null) {
                TagEncoder.encode(bao, TagNum.AllocReportType, allocReportType.getValue());
            }
            if (allocIntermedReqType != null) {
                TagEncoder.encode(bao, TagNum.AllocIntermedReqType, allocIntermedReqType.getValue());
            }
            if (matchStatus != null) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            if (product != null) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (noAllocs != null) {
                TagEncoder.encode(bao, TagNum.NoAllocs, noAllocs);
                if (allocAckGroups != null && allocAckGroups.length == noAllocs.intValue()) {
                    for (int i = 0; i < noAllocs.intValue(); i++) {
                        if (allocAckGroups[i] != null) {
                            bao.write(allocAckGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "AllocAckGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoAllocs.getValue(), error);
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
            case AllocReportID:
                allocReportID = new String(tag.value, sessionCharset);
                break;

            case AllocID:
                allocID = new String(tag.value, sessionCharset);
                break;

            case ClearingBusinessDate:
                clearingBusinessDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case AvgPxIndicator:
                avgPxIndicator = AvgPxIndicator.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Quantity:
                quantity = new Double(new String(tag.value, sessionCharset));
                break;

            case AllocTransType:
                allocTransType = AllocTransType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case SecondaryAllocID:
                secondaryAllocID = new String(tag.value, sessionCharset);
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case AllocStatus:
                allocStatus = AllocStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocRejCode:
                allocRejCode = AllocRejCode.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocReportType:
                allocReportType = AllocReportType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocIntermedReqType:
                allocIntermedReqType = AllocIntermedReqType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MatchStatus:
                matchStatus = MatchStatus.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Product:
                product = Product.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SecurityType:
                securityType = new String(tag.value, sessionCharset);
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case NoAllocs:
                noAllocs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [AllocationReportAckMsg] fields.";
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
        StringBuilder b = new StringBuilder("{AllocationReportAckMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.AllocReportID, allocReportID);
        printTagValue(b, TagNum.AllocID, allocID);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, TagNum.AvgPxIndicator, avgPxIndicator);
        printTagValue(b, TagNum.Quantity, quantity);
        printTagValue(b, TagNum.AllocTransType, allocTransType);
        printTagValue(b, parties);
        printTagValue(b, TagNum.SecondaryAllocID, secondaryAllocID);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.AllocStatus, allocStatus);
        printTagValue(b, TagNum.AllocRejCode, allocRejCode);
        printTagValue(b, TagNum.AllocReportType, allocReportType);
        printTagValue(b, TagNum.AllocIntermedReqType, allocIntermedReqType);
        printTagValue(b, TagNum.MatchStatus, matchStatus);
        printTagValue(b, TagNum.Product, product);
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.NoAllocs, noAllocs);
        printTagValue(b, allocAckGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
