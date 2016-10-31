/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * YieldData.java
 *
 * $Id: YieldData.java,v 1.10 2010-11-23 10:20:19 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.YieldType;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * The YieldData component block conveys yield information for a given Fixed Income security.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 16/02/2009, 8:32:37 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class YieldData extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.YieldType.getValue(),
        TagNum.Yield.getValue(),
        TagNum.YieldCalcDate.getValue(),
        TagNum.YieldRedemptionDate.getValue(),
        TagNum.YieldRedemptionPrice.getValue(),
        TagNum.YieldRedemptionPriceType.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.YieldType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 235. Starting with 4.4 version.
     */
    protected YieldType yieldType;

    /**
     * TagNum = 236. Starting with 4.4 version.
     */
    protected Double yield;

    /**
     * TagNum = 701. Starting with 4.4 version.
     */
    protected Date yieldCalcDate;

    /**
     * TagNum = 696. Starting with 4.4 version.
     */
    protected Date yieldRedemptionDate;

    /**
     * TagNum = 697. Starting with 4.4 version.
     */
    protected Double yieldRedemptionPrice;

    /**
     * TagNum = 698. Starting with 4.4 version.
     */
    protected PriceType yieldRedemptionPriceType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public YieldData() {
        super();
    }

    public YieldData(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldType)
    public YieldType getYieldType() {
        return yieldType;
    }

    /**
     * Message field setter.
     * @param yieldType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldType)
    public void setYieldType(YieldType yieldType) {
        this.yieldType = yieldType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Yield)
    public Double getYield() {
        return yield;
    }

    /**
     * Message field setter.
     * @param yield field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Yield)
    public void setYield(Double yield) {
        this.yield = yield;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldCalcDate)
    public Date getYieldCalcDate() {
        return yieldCalcDate;
    }

    /**
     * Message field setter.
     * @param yieldCalcDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldCalcDate)
    public void setYieldCalcDate(Date yieldCalcDate) {
        this.yieldCalcDate = yieldCalcDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldRedemptionDate)
    public Date getYieldRedemptionDate() {
        return yieldRedemptionDate;
    }

    /**
     * Message field setter.
     * @param yieldRedemptionDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldRedemptionDate)
    public void setYieldRedemptionDate(Date yieldRedemptionDate) {
        this.yieldRedemptionDate = yieldRedemptionDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldRedemptionPrice)
    public Double getYieldRedemptionPrice() {
        return yieldRedemptionPrice;
    }

    /**
     * Message field setter.
     * @param yieldRedemptionPrice field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldRedemptionPrice)
    public void setYieldRedemptionPrice(Double yieldRedemptionPrice) {
        this.yieldRedemptionPrice = yieldRedemptionPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldRedemptionPriceType)
    public PriceType getYieldRedemptionPriceType() {
        return yieldRedemptionPriceType;
    }

    /**
     * Message field setter.
     * @param yieldRedemptionPriceType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.YieldRedemptionPriceType)
    public void setYieldRedemptionPriceType(PriceType yieldRedemptionPriceType) {
        this.yieldRedemptionPriceType = yieldRedemptionPriceType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.YieldType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (yieldType == null) {
            errorMsg.append(" [YieldType]");
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
            if (yieldType != null) {
                TagEncoder.encode(bao, TagNum.YieldType, yieldType.getValue());
            }
            TagEncoder.encode(bao, TagNum.Yield, yield);
            TagEncoder.encodeDate(bao, TagNum.YieldCalcDate, yieldCalcDate);
            TagEncoder.encodeDate(bao, TagNum.YieldRedemptionDate, yieldRedemptionDate);
            TagEncoder.encode(bao, TagNum.YieldRedemptionPrice, yieldRedemptionPrice);
            if (yieldRedemptionPriceType != null) {
                TagEncoder.encode(bao, TagNum.YieldRedemptionPriceType, yieldRedemptionPriceType.getValue());
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
            case YieldType:
                yieldType = YieldType.valueFor(new String(tag.value, sessionCharset));
                break;

            case Yield:
                yield = new Double(new String(tag.value, sessionCharset));
                break;

            case YieldCalcDate:
                yieldCalcDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case YieldRedemptionDate:
                yieldRedemptionDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case YieldRedemptionPrice:
                yieldRedemptionPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case YieldRedemptionPriceType:
                yieldRedemptionPriceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [YieldData] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
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

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{YieldData=");
        printTagValue(b, TagNum.YieldType, yieldType);
        printTagValue(b, TagNum.Yield, yield);
        printDateTagValue(b, TagNum.YieldCalcDate, yieldCalcDate);
        printDateTagValue(b, TagNum.YieldRedemptionDate, yieldRedemptionDate);
        printTagValue(b, TagNum.YieldRedemptionPrice, yieldRedemptionPrice);
        printTagValue(b, TagNum.YieldRedemptionPriceType, yieldRedemptionPriceType);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
