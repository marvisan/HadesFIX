/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegBenchmarkCurveData.java
 *
 * $Id: LegBenchmarkCurveData.java,v 1.10 2011-04-28 10:07:49 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
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
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BenchmarkCurveName;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The LegBenchmarkCurveData is used to convey the benchmark information used
 * for pricing in a multi-legged Fixed Income security.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 07/04/2009, 10:53:43 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class LegBenchmarkCurveData extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegBenchmarkCurveCurrency.getValue(),
        TagNum.LegBenchmarkCurveName.getValue(),
        TagNum.LegBenchmarkCurvePoint.getValue(),
        TagNum.LegBenchmarkPrice.getValue(),
        TagNum.LegBenchmarkPriceType.getValue()
   }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 676. Starting with 4.4 version.
     */
    protected Currency legBenchmarkCurveCurrency;

    /**
     * TagNum = 677. Starting with 4.4 version.
     */
    protected BenchmarkCurveName legBenchmarkCurveName;

    /**
     * TagNum = 678. Starting with 4.4 version.
     */
    protected String legBenchmarkCurvePoint;

    /**
     * TagNum = 679. Starting with 4.4 version.
     */
    protected Double legBenchmarkPrice;

    /**
     * TagNum = 680. Starting with 4.4 version.
     */
    protected PriceType legBenchmarkPriceType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegBenchmarkCurveData() {
    }

    public LegBenchmarkCurveData(FragmentContext context) {
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
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkCurveCurrency)
    public Currency getLegBenchmarkCurveCurrency() {
        return legBenchmarkCurveCurrency;
    }

    /**
     * Message field setter.
     * @param legBenchmarkCurveCurrency field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkCurveCurrency)
    public void setLegBenchmarkCurveCurrency(Currency legBenchmarkCurveCurrency) {
        this.legBenchmarkCurveCurrency = legBenchmarkCurveCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkCurveName)
    public BenchmarkCurveName getLegBenchmarkCurveName() {
        return legBenchmarkCurveName;
    }

    /**
     * Message field setter.
     * @param legBenchmarkCurveName field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkCurveName)
    public void setLegBenchmarkCurveName(BenchmarkCurveName legBenchmarkCurveName) {
        this.legBenchmarkCurveName = legBenchmarkCurveName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkCurvePoint)
    public String getLegBenchmarkCurvePoint() {
        return legBenchmarkCurvePoint;
    }

    /**
     * Message field setter.
     * @param legBenchmarkCurvePoint field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkCurvePoint)
    public void setLegBenchmarkCurvePoint(String legBenchmarkCurvePoint) {
        this.legBenchmarkCurvePoint = legBenchmarkCurvePoint;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkPrice)
    public Double getLegBenchmarkPrice() {
        return legBenchmarkPrice;
    }

    /**
     * Message field setter.
     * @param legBenchmarkPrice field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkPrice)
    public void setLegBenchmarkPrice(Double legBenchmarkPrice) {
        this.legBenchmarkPrice = legBenchmarkPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkPriceType)
    public PriceType getLegBenchmarkPriceType() {
        return legBenchmarkPriceType;
    }

    /**
     * Message field setter.
     * @param legBenchmarkPriceType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegBenchmarkPriceType)
    public void setLegBenchmarkPriceType(PriceType legBenchmarkPriceType) {
        this.legBenchmarkPriceType = legBenchmarkPriceType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.LegBenchmarkCurveCurrency.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (legBenchmarkCurveCurrency == null) {
            errorMsg.append(" [LegBenchmarkCurveCurrency]");
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
            TagEncoder.encode(bao, TagNum.LegBenchmarkCurveCurrency, legBenchmarkCurveCurrency.getValue());
            if (legBenchmarkCurveName != null) {
                 TagEncoder.encode(bao, TagNum.LegBenchmarkCurveName, legBenchmarkCurveName.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegBenchmarkCurvePoint, legBenchmarkCurvePoint);
            TagEncoder.encode(bao, TagNum.LegBenchmarkPrice, legBenchmarkPrice);
            if (legBenchmarkPriceType != null) {
                TagEncoder.encode(bao, TagNum.LegBenchmarkPriceType, legBenchmarkPriceType.getValue());
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
            case LegBenchmarkCurveCurrency:
                legBenchmarkCurveCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegBenchmarkCurveName:
                legBenchmarkCurveName = BenchmarkCurveName.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegBenchmarkCurvePoint:
                legBenchmarkCurvePoint = new String(tag.value, sessionCharset);
                break;

            case LegBenchmarkPrice:
                legBenchmarkPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case LegBenchmarkPriceType:
                legBenchmarkPriceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [LegBenchmarkCurveData] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
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
        b.append("{LegBenchmarkCurveData=");
        printTagValue(b, TagNum.LegBenchmarkCurveCurrency, legBenchmarkCurveCurrency);
        printTagValue(b, TagNum.LegBenchmarkCurveName, legBenchmarkCurveName);
        printTagValue(b, TagNum.LegBenchmarkCurvePoint, legBenchmarkCurvePoint);
        printTagValue(b, TagNum.LegBenchmarkPrice, legBenchmarkPrice);
        printTagValue(b, TagNum.LegBenchmarkPriceType, legBenchmarkPriceType);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
