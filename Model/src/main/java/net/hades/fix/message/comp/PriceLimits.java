/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PriceLimits.java
 *
 * $Id: PriceLimits.java,v 1.1 2011-04-17 09:30:50 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.type.PriceLimitType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Price limits data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/02/2009, 2:52:41 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class PriceLimits extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.PriceLimitType.getValue(),
        TagNum.LowLimitPrice.getValue(),
        TagNum.HighLimitPrice.getValue(),
        TagNum.TradingReferencePrice.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.PegOffsetValue.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1306. Starting with 5.0SP1 version.
     */
    protected PriceLimitType priceLimitType;

    /**
     * TagNum = 1148. Starting with 5.0SP1 version.
     */
    protected Double lowLimitPrice;

    /**
     * TagNum = 1149. Starting with 5.0SP1 version.
     */
    protected Double highLimitPrice;

    /**
     * TagNum = 1150. Starting with 5.0SP1 version.
     */
    protected Double tradingReferencePrice;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PriceLimits() {
    }

    public PriceLimits(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceLimitType)
    public PriceLimitType getPriceLimitType() {
        return priceLimitType;
    }

    /**
     * Message field setter.
     * @param priceLimitType field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceLimitType)
    public void setPriceLimitType(PriceLimitType priceLimitType) {
        this.priceLimitType = priceLimitType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LowLimitPrice)
    public Double getLowLimitPrice() {
        return lowLimitPrice;
    }

    /**
     * Message field setter.
     * @param lowLimitPrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LowLimitPrice)
    public void setLowLimitPrice(Double lowLimitPrice) {
        this.lowLimitPrice = lowLimitPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.HighLimitPrice)
    public Double getHighLimitPrice() {
        return highLimitPrice;
    }

    /**
     * Message field setter.
     * @param highLimitPrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.HighLimitPrice)
    public void setHighLimitPrice(Double highLimitPrice) {
        this.highLimitPrice = highLimitPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.TradingReferencePrice)
    public Double getTradingReferencePrice() {
        return tradingReferencePrice;
    }

    /**
     * Message field setter.
     * @param tradingReferencePrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.TradingReferencePrice)
    public void setTradingReferencePrice(Double tradingReferencePrice) {
        this.tradingReferencePrice = tradingReferencePrice;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.PriceLimitType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (priceLimitType != null) {
                TagEncoder.encode(bao, TagNum.PriceLimitType, priceLimitType.getValue());
            }
            TagEncoder.encode(bao, TagNum.LowLimitPrice, lowLimitPrice);
            TagEncoder.encode(bao, TagNum.HighLimitPrice, highLimitPrice);
            TagEncoder.encode(bao, TagNum.TradingReferencePrice, tradingReferencePrice);
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

            case PriceLimitType:
                priceLimitType = PriceLimitType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LowLimitPrice:
                lowLimitPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case HighLimitPrice:
                highLimitPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case TradingReferencePrice:
                tradingReferencePrice = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [PriceLimits] fields.";
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
        b.append("{PriceLimits=");
        printTagValue(b, TagNum.PriceLimitType, priceLimitType);
        printTagValue(b, TagNum.LowLimitPrice, lowLimitPrice);
        printTagValue(b, TagNum.HighLimitPrice, highLimitPrice);
        printTagValue(b, TagNum.TradingReferencePrice, tradingReferencePrice);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
