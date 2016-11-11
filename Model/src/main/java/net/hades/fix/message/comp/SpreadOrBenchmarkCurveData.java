/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SpreadOrBenchmarkCurveData.java
 *
 * $Id: SpreadOrBenchmarkCurveData.java,v 1.10 2010-11-23 10:20:19 vrotaru Exp $
 */
package net.hades.fix.message.comp;

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
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BenchmarkCurveName;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SecurityIDSource;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The SpreadOrBenchmarkCurveData component block is primarily used for Fixed Income
 * to convey spread to a benchmark security or curve.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 16/02/2009, 7:23:39 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SpreadOrBenchmarkCurveData extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Spread.getValue(),
        TagNum.BenchmarkCurveCurrency.getValue(),
        TagNum.BenchmarkCurveName.getValue(),
        TagNum.BenchmarkCurvePoint.getValue(),
        TagNum.BenchmarkPrice.getValue(),
        TagNum.BenchmarkPriceType.getValue(),
        TagNum.BenchmarkSecurityID.getValue(),
        TagNum.BenchmarkSecurityIDSource.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Spread.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 218. Starting with 4.3 version.
     */
    protected Double spread;

    /**
     * TagNum = 220. Starting with 4.3 version.
     */
    protected Currency benchmarkCurveCurrency;

    /**
     * TagNum = 221. Starting with 4.3 version.
     */
    protected BenchmarkCurveName benchmarkCurveName;

    /**
     * TagNum = 222. Starting with 4.3 version.
     */
    protected String benchmarkCurvePoint;

    /**
     * TagNum = 662. Starting with 4.4 version.
     */
    protected Double benchmarkPrice;

    /**
     * TagNum = 663. Starting with 4.4 version.
     */
    protected PriceType benchmarkPriceType;

    /**
     * TagNum = 699. Starting with 4.4 version.
     */
    protected String benchmarkSecurityID;

    /**
     * TagNum = 761. Starting with 4.4 version.
     */
    protected SecurityIDSource benchmarkSecurityIDSource;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SpreadOrBenchmarkCurveData() {
        super();
    }

    public SpreadOrBenchmarkCurveData(FragmentContext context) {
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
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Spread)
    public Double getSpread() {
        return spread;
    }

    /**
     * Message field setter.
     * @param spread field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Spread)
    public void setSpread(Double spread) {
        this.spread = spread;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.BenchmarkCurveCurrency)
    public Currency getBenchmarkCurveCurrency() {
        return benchmarkCurveCurrency;
    }

    /**
     * Message field setter.
     * @param benchmarkCurveCurrency field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.BenchmarkCurveCurrency)
    public void setBenchmarkCurveCurrency(Currency benchmarkCurveCurrency) {
        this.benchmarkCurveCurrency = benchmarkCurveCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.BenchmarkCurveName)
    public BenchmarkCurveName getBenchmarkCurveName() {
        return benchmarkCurveName;
    }

    /**
     * Message field setter.
     * @param benchmarkCurveName field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.BenchmarkCurveName)
    public void setBenchmarkCurveName(BenchmarkCurveName benchmarkCurveName) {
        this.benchmarkCurveName = benchmarkCurveName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.BenchmarkCurvePoint)
    public String getBenchmarkCurvePoint() {
        return benchmarkCurvePoint;
    }

    /**
     * Message field setter.
     * @param benchmarkCurvePoint field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.BenchmarkCurvePoint)
    public void setBenchmarkCurvePoint(String benchmarkCurvePoint) {
        this.benchmarkCurvePoint = benchmarkCurvePoint;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.BenchmarkPrice)
    public Double getBenchmarkPrice() {
        return benchmarkPrice;
    }

    /**
     * Message field setter.
     * @param benchmarkPrice field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.BenchmarkPrice)
    public void setBenchmarkPrice(Double benchmarkPrice) {
        this.benchmarkPrice = benchmarkPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.BenchmarkPriceType)
    public PriceType getBenchmarkPriceType() {
        return benchmarkPriceType;
    }

    /**
     * Message field setter.
     * @param benchmarkPriceType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.BenchmarkPriceType)
    public void setBenchmarkPriceType(PriceType benchmarkPriceType) {
        this.benchmarkPriceType = benchmarkPriceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.BenchmarkSecurityID)
    public String getBenchmarkSecurityID() {
        return benchmarkSecurityID;
    }

    /**
     * Message field setter.
     * @param benchmarkSecurityID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.BenchmarkSecurityID)
    public void setBenchmarkSecurityID(String benchmarkSecurityID) {
        this.benchmarkSecurityID = benchmarkSecurityID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.BenchmarkSecurityIDSource)
    public SecurityIDSource getBenchmarkSecurityIDSource() {
        return benchmarkSecurityIDSource;
    }

    /**
     * Message field setter.
     * @param benchmarkSecurityIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.BenchmarkSecurityIDSource)
    public void setBenchmarkSecurityIDSource(SecurityIDSource benchmarkSecurityIDSource) {
        this.benchmarkSecurityIDSource = benchmarkSecurityIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.Spread.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (spread == null) {
            errorMsg.append(" [Spread]");
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
            TagEncoder.encode(bao, TagNum.Spread, spread);
            if (benchmarkCurveCurrency != null) {
                TagEncoder.encode(bao, TagNum.BenchmarkCurveCurrency, benchmarkCurveCurrency.getValue());
            }
            if (benchmarkCurveName != null) {
                TagEncoder.encode(bao, TagNum.BenchmarkCurveName, benchmarkCurveName.getValue());
            }
            TagEncoder.encode(bao, TagNum.BenchmarkCurvePoint, benchmarkCurvePoint);
            TagEncoder.encode(bao, TagNum.BenchmarkPrice, benchmarkPrice);
            if (benchmarkPriceType != null) {
                TagEncoder.encode(bao, TagNum.BenchmarkPriceType, benchmarkPriceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.BenchmarkSecurityID, benchmarkSecurityID);
            if (benchmarkSecurityIDSource != null) {
                TagEncoder.encode(bao, TagNum.BenchmarkSecurityIDSource, benchmarkSecurityIDSource.getValue());
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
            case Spread:
                spread = new Double(new String(tag.value, sessionCharset));
                break;

            case BenchmarkCurveCurrency:
                benchmarkCurveCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case BenchmarkCurveName:
                benchmarkCurveName = BenchmarkCurveName.valueFor(new String(tag.value, sessionCharset));
                break;

            case BenchmarkCurvePoint:
                benchmarkCurvePoint = new String(tag.value, sessionCharset);
                break;

            case BenchmarkPrice:
                benchmarkPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case BenchmarkPriceType:
                benchmarkPriceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case BenchmarkSecurityID:
                benchmarkSecurityID = new String(tag.value, sessionCharset);
                break;

            case BenchmarkSecurityIDSource:
                benchmarkSecurityIDSource = SecurityIDSource.valueFor(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SpreadOrBenchmarkCurveData44] fields.";
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
        b.append("{SpreadOrBenchmarkCurveData=");
        printTagValue(b, TagNum.Spread, spread);
        printTagValue(b, TagNum.BenchmarkCurveCurrency, benchmarkCurveCurrency);
        printTagValue(b, TagNum.BenchmarkCurveName, benchmarkCurveName);
        printTagValue(b, TagNum.BenchmarkCurvePoint, benchmarkCurvePoint);
        printTagValue(b, TagNum.BenchmarkPrice, benchmarkPrice);
        printTagValue(b, TagNum.BenchmarkPriceType, benchmarkPriceType);
        printTagValue(b, TagNum.BenchmarkSecurityID, benchmarkSecurityID);
        printTagValue(b, TagNum.BenchmarkSecurityIDSource, benchmarkSecurityIDSource);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
