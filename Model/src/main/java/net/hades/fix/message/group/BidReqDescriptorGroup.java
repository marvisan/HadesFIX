/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidReqDescriptorGroup.java
 *
 * $Id: BidReqDescriptorGroup.java,v 1.1 2011-04-14 11:44:45 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.BidDescriptorType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

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
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.SideValueInd;
import net.hades.fix.message.util.TagEncoder;

/**
 * Bid request descriptor group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/04/2009, 6:41:55 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BidReqDescriptorGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.BidDescriptorType.getValue(),
        TagNum.BidDescriptor.getValue(),
        TagNum.SideValueInd.getValue(),
        TagNum.LiquidityValue.getValue(),
        TagNum.LiquidityNumSecurities.getValue(),
        TagNum.LiquidityPctLow.getValue(),
        TagNum.LiquidityPctHigh.getValue(),
        TagNum.EFPTrackingError.getValue(),
        TagNum.FairValue.getValue(),
        TagNum.OutsideIndexPct.getValue(),
        TagNum.ValueOfFutures.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 399. Starting with 4.2 version.
     */
    protected BidDescriptorType bidDescriptorType;

    /**
     * TagNum = 400. Starting with 4.2 version.
     */
    protected String bidDescriptor;

    /**
     * TagNum = 401. Starting with 4.2 version.
     */
    protected SideValueInd sideValueInd;

    /**
     * TagNum = 404. Starting with 4.2 version.
     */
    protected Double liquidityValue;

    /**
     * TagNum = 441. Starting with 4.2 version.
     */
    protected Integer liquidityNumSecurities;

    /**
     * TagNum = 402. Starting with 4.2 version.
     */
    protected Double liquidityPctLow;

    /**
     * TagNum = 403. Starting with 4.2 version.
     */
    protected Double liquidityPctHigh;

    /**
     * TagNum = 405. Starting with 4.2 version.
     */
    protected Double EFPTrackingError;

    /**
     * TagNum = 406. Starting with 4.2 version.
     */
    protected Double fairValue;

    /**
     * TagNum = 407. Starting with 4.2 version.
     */
    protected Double outsideIndexPct;

    /**
     * TagNum = 408. Starting with 4.2 version.
     */
    protected Double valueOfFutures;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BidReqDescriptorGroup() {
    }

    public BidReqDescriptorGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.BidDescriptorType.getValue();
    }

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
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.BidDescriptorType)
    public BidDescriptorType getBidDescriptorType() {
        return bidDescriptorType;
    }

    /**
     * Message field setter.
     * @param bidDescriptorType field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.BidDescriptorType)
    public void setBidDescriptorType(BidDescriptorType bidDescriptorType) {
        this.bidDescriptorType = bidDescriptorType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.BidDescriptor)
    public String getBidDescriptor() {
        return bidDescriptor;
    }

    /**
     * Message field setter.
     * @param bidDescriptor field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.BidDescriptor)
    public void setBidDescriptor(String bidDescriptor) {
        this.bidDescriptor = bidDescriptor;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.SideValueInd)
    public SideValueInd getSideValueInd() {
        return sideValueInd;
    }

    /**
     * Message field setter.
     * @param sideValueInd field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.SideValueInd)
    public void setSideValueInd(SideValueInd sideValueInd) {
        this.sideValueInd = sideValueInd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.LiquidityValue)
    public Double getLiquidityValue() {
        return liquidityValue;
    }

    /**
     * Message field setter.
     * @param liquidityValue field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.LiquidityValue)
    public void setLiquidityValue(Double liquidityValue) {
        this.liquidityValue = liquidityValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.LiquidityNumSecurities)
    public Integer getLiquidityNumSecurities() {
        return liquidityNumSecurities;
    }

    /**
     * Message field setter.
     * @param liquidityNumSecurities field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.LiquidityNumSecurities)
    public void setLiquidityNumSecurities(Integer liquidityNumSecurities) {
        this.liquidityNumSecurities = liquidityNumSecurities;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.LiquidityPctLow)
    public Double getLiquidityPctLow() {
        return liquidityPctLow;
    }

    /**
     * Message field setter.
     * @param liquidityPctLow field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.LiquidityPctLow)
    public void setLiquidityPctLow(Double liquidityPctLow) {
        this.liquidityPctLow = liquidityPctLow;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.LiquidityPctHigh)
    public Double getLiquidityPctHigh() {
        return liquidityPctHigh;
    }

    /**
     * Message field setter.
     * @param liquidityPctHigh field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.LiquidityPctHigh)
    public void setLiquidityPctHigh(Double liquidityPctHigh) {
        this.liquidityPctHigh = liquidityPctHigh;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.EFPTrackingError)
    public Double getEFPTrackingError() {
        return EFPTrackingError;
    }

    /**
     * Message field setter.
     * @param EFPTrackingError field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.EFPTrackingError)
    public void setEFPTrackingError(Double EFPTrackingError) {
        this.EFPTrackingError = EFPTrackingError;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.FairValue)
    public Double getFairValue() {
        return fairValue;
    }

    /**
     * Message field setter.
     * @param fairValue field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.FairValue)
    public void setFairValue(Double fairValue) {
        this.fairValue = fairValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.OutsideIndexPct)
    public Double getOutsideIndexPct() {
        return outsideIndexPct;
    }

    /**
     * Message field setter.
     * @param outsideIndexPct field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.OutsideIndexPct)
    public void setOutsideIndexPct(Double outsideIndexPct) {
        this.outsideIndexPct = outsideIndexPct;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.ValueOfFutures)
    public Double getValueOfFutures() {
        return valueOfFutures;
    }

    /**
     * Message field setter.
     * @param valueOfFutures field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.ValueOfFutures)
    public void setValueOfFutures(Double valueOfFutures) {
        this.valueOfFutures = valueOfFutures;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (bidDescriptorType == null) {
            errorMsg.append(" [BidDescriptorType]");
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
            if (bidDescriptorType != null) {
                TagEncoder.encode(bao, TagNum.BidDescriptorType, bidDescriptorType.getValue());
            }
            TagEncoder.encode(bao, TagNum.BidDescriptor, bidDescriptor);
            if (sideValueInd != null) {
                 TagEncoder.encode(bao, TagNum.SideValueInd, sideValueInd.getValue());
            }
            TagEncoder.encode(bao, TagNum.LiquidityValue, liquidityValue);
            TagEncoder.encode(bao, TagNum.LiquidityNumSecurities, liquidityNumSecurities);
            TagEncoder.encode(bao, TagNum.LiquidityPctLow, liquidityPctLow);
            TagEncoder.encode(bao, TagNum.LiquidityPctHigh, liquidityPctHigh);
            TagEncoder.encode(bao, TagNum.EFPTrackingError, EFPTrackingError);
            TagEncoder.encode(bao, TagNum.FairValue, fairValue);
            TagEncoder.encode(bao, TagNum.OutsideIndexPct, outsideIndexPct);
            TagEncoder.encode(bao, TagNum.ValueOfFutures, valueOfFutures);
            
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
            case BidDescriptorType:
                bidDescriptorType = BidDescriptorType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case BidDescriptor:
                bidDescriptor = new String(tag.value, sessionCharset);
                break;

            case SideValueInd:
                sideValueInd = SideValueInd.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LiquidityValue:
                liquidityValue = new Double(new String(tag.value, sessionCharset));
                break;

            case LiquidityNumSecurities:
                liquidityNumSecurities = Integer.valueOf(new String(tag.value, sessionCharset));
                break;

            case LiquidityPctLow:
                liquidityPctLow = new Double(new String(tag.value, sessionCharset));
                break;

            case LiquidityPctHigh:
                liquidityPctHigh = new Double(new String(tag.value, sessionCharset));
                break;

            case EFPTrackingError:
                EFPTrackingError = new Double(new String(tag.value, sessionCharset));
                break;

            case FairValue:
                fairValue = new Double(new String(tag.value, sessionCharset));
                break;

            case OutsideIndexPct:
                outsideIndexPct = new Double(new String(tag.value, sessionCharset));
                break;

            case ValueOfFutures:
                valueOfFutures = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [BidReqDescriptorGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
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
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{BidReqDescriptorGroup=");
        printTagValue(b, TagNum.BidDescriptorType, bidDescriptorType);
        printTagValue(b, TagNum.BidDescriptor, bidDescriptor);
        printTagValue(b, TagNum.SideValueInd, sideValueInd);
        printTagValue(b, TagNum.LiquidityValue, liquidityValue);
        printTagValue(b, TagNum.LiquidityNumSecurities, liquidityNumSecurities);
        printTagValue(b, TagNum.LiquidityPctLow, liquidityPctLow);
        printTagValue(b, TagNum.LiquidityPctHigh, liquidityPctHigh);
        printTagValue(b, TagNum.EFPTrackingError, EFPTrackingError);
        printTagValue(b, TagNum.FairValue, fairValue);
        printTagValue(b, TagNum.OutsideIndexPct, outsideIndexPct);
        printTagValue(b, TagNum.ValueOfFutures, valueOfFutures);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
