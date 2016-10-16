/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SpreadOrBenchmarkCurveData.java
 *
 * $Id: SecondaryPriceLimits.java,v 1.1 2011-09-27 08:57:26 vrotaru Exp $
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
 * The SecondaryPriceLimits component block.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 16/02/2009, 7:23:39 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SecondaryPriceLimits extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecondaryPriceLimitType.getValue(),
        TagNum.SecondaryLowLimitPrice.getValue(),
        TagNum.SecondaryHighLimitPrice.getValue(),
        TagNum.SecondaryTradingReferencePrice.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecondaryPriceLimitType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1305. Starting with 5.0SP1 version.
     */
    protected PriceLimitType secondaryPriceLimitType;

    /**
     * TagNum = 1221. Starting with 5.0SP1 version.
     */
    protected Double secondaryLowLimitPrice;

    /**
     * TagNum = 1230. Starting with 5.0SP1 version.
     */
    protected Double secondaryHighLimitPrice;

    /**
     * TagNum = 1240. Starting with 5.0SP1 version.
     */
    protected Double secondaryTradingReferencePrice;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecondaryPriceLimits() {
        super();
    }

    public SecondaryPriceLimits(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecondaryPriceLimitType)
    public PriceLimitType getSecondaryPriceLimitType() {
        return secondaryPriceLimitType;
    }

    /**
     * Message field setter.
     * @param secondaryPriceLimitType field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecondaryPriceLimitType)
    public void setSecondaryPriceLimitType(PriceLimitType secondaryPriceLimitType) {
        this.secondaryPriceLimitType = secondaryPriceLimitType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecondaryLowLimitPrice)
    public Double getSecondaryLowLimitPrice() {
        return secondaryLowLimitPrice;
    }

    /**
     * Message field setter.
     * @param secondaryLowLimitPrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecondaryLowLimitPrice)
    public void setSecondaryLowLimitPrice(Double secondaryLowLimitPrice) {
        this.secondaryLowLimitPrice = secondaryLowLimitPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecondaryHighLimitPrice)
    public Double getSecondaryHighLimitPrice() {
        return secondaryHighLimitPrice;
    }

    /**
     * Message field setter.
     * @param secondaryHighLimitPrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecondaryHighLimitPrice)
    public void setSecondaryHighLimitPrice(Double secondaryHighLimitPrice) {
        this.secondaryHighLimitPrice = secondaryHighLimitPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecondaryTradingReferencePrice)
    public Double getSecondaryTradingReferencePrice() {
        return secondaryTradingReferencePrice;
    }

    /**
     * Message field setter.
     * @param secondaryTradingReferencePrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecondaryTradingReferencePrice)
    public void setSecondaryTradingReferencePrice(Double secondaryTradingReferencePrice) {
        this.secondaryTradingReferencePrice = secondaryTradingReferencePrice;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.SecondaryPriceLimitType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (secondaryPriceLimitType == null) {
            errorMsg.append(" [SecondaryPriceLimitType]");
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
            if (secondaryPriceLimitType != null) {
                TagEncoder.encode(bao, TagNum.SecondaryPriceLimitType, secondaryPriceLimitType.getValue());
            }
            TagEncoder.encode(bao, TagNum.SecondaryLowLimitPrice, secondaryLowLimitPrice);
            TagEncoder.encode(bao, TagNum.SecondaryHighLimitPrice, secondaryHighLimitPrice);
            TagEncoder.encode(bao, TagNum.SecondaryTradingReferencePrice, secondaryTradingReferencePrice);
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
            case SecondaryPriceLimitType:
                secondaryPriceLimitType = PriceLimitType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SecondaryLowLimitPrice:
                secondaryLowLimitPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case SecondaryHighLimitPrice:
                secondaryHighLimitPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case SecondaryTradingReferencePrice:
                secondaryTradingReferencePrice = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SecondaryPriceLimits] fields.";
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
        b.append("{SecondaryPriceLimits=");
        printTagValue(b, TagNum.SecondaryPriceLimitType, secondaryPriceLimitType);
        printTagValue(b, TagNum.SecondaryLowLimitPrice, secondaryLowLimitPrice);
        printTagValue(b, TagNum.SecondaryHighLimitPrice, secondaryHighLimitPrice);
        printTagValue(b, TagNum.SecondaryTradingReferencePrice, secondaryTradingReferencePrice);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
