/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderStatusGroup.java
 *
 * $Id: OrderStatusGroup.java,v 1.1 2011-02-03 10:36:53 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.OrdRejReason;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.BooleanConverter;
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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Order status group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class OrderStatusGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ClOrdID.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.CumQty.getValue(),
        TagNum.OrdStatus.getValue(),
        TagNum.WorkingIndicator.getValue(),
        TagNum.LeavesQty.getValue(),
        TagNum.CxlQty.getValue(),
        TagNum.AvgPx.getValue(),
        TagNum.OrdRejReason.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 11 REQUIRED. Starting with 4.0 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 37. Starting with 5.0SP1 version.
     */
    protected String orderID;

    /**
     * TagNum = 526. Starting with 4.3 version.
     */
    protected String secondaryClOrdID;

    /**
     * TagNum = 14 REQUIRED. Starting with 4.0 version.
     */
    protected Double cumQty;

    /**
     * TagNum = 39 REQUIRED. Starting with 4.2 version.
     */
    protected OrdStatus ordStatus;

    /**
     * TagNum = 636. Starting with 4.3 version.
     */
    protected Boolean workingIndicator;

    /**
     * TagNum = 151 REQUIRED. Starting with 4.1 version.
     */
    protected Double leavesQty;

    /**
     * TagNum = 84 REQUIRED. Starting with 4.0 version.
     */
    protected Double cxlQty;

    /**
     * TagNum = 6 REQUIRED. Starting with 4.0 version.
     */
    protected Double avgPx;

    /**
     * TagNum = 103. Starting with 4.2 version.
     */
    protected OrdRejReason ordRejReason;

    /**
     * TagNum = 58. Starting with 4.2 version.
     */
    protected String text;

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
    
    public OrderStatusGroup() {
    }
    
    public OrderStatusGroup(FragmentContext context) {
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
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    /**
     * Message field setter.
     * @param secondaryClOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.CumQty, required = true)
    public Double getCumQty() {
        return cumQty;
    }

    /**
     * Message field setter.
     * @param cumQty field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.CumQty, required = true)
    public void setCumQty(Double cumQty) {
        this.cumQty = cumQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OrdStatus, required = true)
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    /**
     * Message field setter.
     * @param ordStatus field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OrdStatus, required = true)
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.WorkingIndicator)
    public Boolean getWorkingIndicator() {
        return workingIndicator;
    }

    /**
     * Message field setter.
     * @param workingIndicator field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.WorkingIndicator)
    public void setWorkingIndicator(Boolean workingIndicator) {
        this.workingIndicator = workingIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LeavesQty)
    public Double getLeavesQty() {
        return leavesQty;
    }

    /**
     * Message field setter.
     * @param leavesQty field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LeavesQty)
    public void setLeavesQty(Double leavesQty) {
        this.leavesQty = leavesQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.CxlQty)
    public Double getCxlQty() {
        return cxlQty;
    }

    /**
     * Message field setter.
     * @param cxlQty field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.CxlQty)
    public void setCxlQty(Double cxlQty) {
        this.cxlQty = cxlQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.AvgPx, required = true)
    public Double getAvgPx() {
        return avgPx;
    }

    /**
     * Message field setter.
     * @param avgPx field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.AvgPx, required = true)
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OrdRejReason)
    public OrdRejReason getOrdRejReason() {
        return ordRejReason;
    }

    /**
     * Message field setter.
     * @param ordRejReason field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OrdRejReason)
    public void setOrdRejReason(OrdRejReason ordRejReason) {
        this.ordRejReason = ordRejReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.2")
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
    protected int getFirstTag() {
        return TagNum.ClOrdID.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (clOrdID == null || clOrdID.trim().isEmpty()) {
            errorMsg.append(" [ClOrdID]");
            hasMissingTag = true;
        }
        if (cumQty == null) {
            errorMsg.append(" [CumQty]");
            hasMissingTag = true;
        }
        if (ordStatus == null) {
            errorMsg.append(" [OrdStatus]");
            hasMissingTag = true;
        }
        if (leavesQty == null) {
            errorMsg.append(" [LeavesQty]");
            hasMissingTag = true;
        }
        if (cxlQty == null) {
            errorMsg.append(" [CxlQty]");
            hasMissingTag = true;
        }
        if (avgPx == null) {
            errorMsg.append(" [AvgPx]");
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
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            TagEncoder.encode(bao, TagNum.CumQty, cumQty);
            if (ordStatus != null) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.WorkingIndicator, workingIndicator);
            TagEncoder.encode(bao, TagNum.LeavesQty, leavesQty);
            TagEncoder.encode(bao, TagNum.CxlQty, cxlQty);
            TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            if (ordRejReason != null) {
                TagEncoder.encode(bao, TagNum.OrdRejReason, ordRejReason.getValue());
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
            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;

            case SecondaryClOrdID:
                secondaryClOrdID = new String(tag.value, sessionCharset);
                break;

            case CumQty:
                cumQty = new Double(new String(tag.value, sessionCharset));
                break;

            case OrdStatus:
                ordStatus = OrdStatus.valueFor(new String(tag.value, sessionCharset));
                break;

            case WorkingIndicator:
                workingIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case LeavesQty:
                leavesQty = new Double(new String(tag.value, sessionCharset));
                break;

            case CxlQty:
                cxlQty = new Double(new String(tag.value, sessionCharset));
                break;

            case AvgPx:
                avgPx = new Double(new String(tag.value, sessionCharset));
                break;

            case OrdRejReason:
                ordRejReason = OrdRejReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case EncodedTextLen:
                encodedTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [OrderStatusGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
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
        b.append("{OrderStatusGroup=");
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.SecondaryClOrdID, secondaryClOrdID);
        printTagValue(b, TagNum.CumQty, cumQty);
        printTagValue(b, TagNum.OrdStatus, ordStatus);
        printTagValue(b, TagNum.WorkingIndicator, workingIndicator);
        printTagValue(b, TagNum.LeavesQty, leavesQty);
        printTagValue(b, TagNum.CxlQty, cxlQty);
        printTagValue(b, TagNum.AvgPx, avgPx);
        printTagValue(b, TagNum.OrdRejReason, ordRejReason);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
