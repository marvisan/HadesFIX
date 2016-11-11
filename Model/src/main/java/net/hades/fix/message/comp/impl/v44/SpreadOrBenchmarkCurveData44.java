/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SpreadOrBenchmarkCurveData44.java
 *
 * $Id: SpreadOrBenchmarkCurveData44.java,v 1.8 2010-08-25 05:30:29 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.type.BenchmarkCurveName;
import net.hades.fix.message.type.PriceType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SecurityIDSource;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 SpreadOrBenchmarkCurveData component data implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 16/02/2009, 8:23:48 PM
 */
@XmlRootElement(name="SprdBnchmkCurve")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SpreadOrBenchmarkCurveData44 extends SpreadOrBenchmarkCurveData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SpreadOrBenchmarkCurveData44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SpreadOrBenchmarkCurveData44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Spread")
    @Override
    public Double getSpread() {
        return spread;
    }

    @Override
    public void setSpread(Double spread) {
        this.spread = spread;
    }

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getBenchmarkCurveCurrency() {
        return benchmarkCurveCurrency;
    }

    @Override
    public void setBenchmarkCurveCurrency(Currency benchmarkCurveCurrency) {
        this.benchmarkCurveCurrency = benchmarkCurveCurrency;
    }

    @XmlAttribute(name = "Name")
    @Override
    public BenchmarkCurveName getBenchmarkCurveName() {
        return benchmarkCurveName;
    }

    @Override
    public void setBenchmarkCurveName(BenchmarkCurveName benchmarkCurveName) {
        this.benchmarkCurveName = benchmarkCurveName;
    }

    @XmlAttribute(name = "Point")
    @Override
    public String getBenchmarkCurvePoint() {
        return benchmarkCurvePoint;
    }

    @Override
    public void setBenchmarkCurvePoint(String benchmarkCurvePoint) {
        this.benchmarkCurvePoint = benchmarkCurvePoint;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getBenchmarkPrice() {
        return benchmarkPrice;
    }

    @Override
    public void setBenchmarkPrice(Double benchmarkPrice) {
        this.benchmarkPrice = benchmarkPrice;
    }

    @XmlAttribute(name = "PxTyp")
    @Override
    public PriceType getBenchmarkPriceType() {
        return benchmarkPriceType;
    }

    @Override
    public void setBenchmarkPriceType(PriceType benchmarkPriceType) {
        this.benchmarkPriceType = benchmarkPriceType;
    }

    @XmlAttribute(name = "SecID")
    @Override
    public String getBenchmarkSecurityID() {
        return benchmarkSecurityID;
    }

    @Override
    public void setBenchmarkSecurityID(String benchmarkSecurityID) {
        this.benchmarkSecurityID = benchmarkSecurityID;
    }

    @XmlAttribute(name = "SecIDSrc")
    @Override
    public SecurityIDSource getBenchmarkSecurityIDSource() {
        return benchmarkSecurityIDSource;
    }

    @Override
    public void setBenchmarkSecurityIDSource(SecurityIDSource benchmarkSecurityIDSource) {
        this.benchmarkSecurityIDSource = benchmarkSecurityIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.Spread, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Spread, spread);
            }
            if (benchmarkCurveCurrency != null && MsgUtil.isTagInList(TagNum.BenchmarkCurveCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BenchmarkCurveCurrency, benchmarkCurveCurrency.getValue());
            }
            if (benchmarkCurveName != null && MsgUtil.isTagInList(TagNum.BenchmarkCurveName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BenchmarkCurveName, benchmarkCurveName.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.BenchmarkCurvePoint, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BenchmarkCurvePoint, benchmarkCurvePoint);
            }
            if (MsgUtil.isTagInList(TagNum.BenchmarkPrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BenchmarkPrice, benchmarkPrice);
            }
            if (benchmarkPriceType != null && MsgUtil.isTagInList(TagNum.BenchmarkPriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BenchmarkPriceType, benchmarkPriceType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.BenchmarkSecurityID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BenchmarkSecurityID, benchmarkSecurityID);
            }
            if (benchmarkSecurityIDSource != null && MsgUtil.isTagInList(TagNum.BenchmarkSecurityIDSource, SECURED_TAGS, secured)) {
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SpreadOrBenchmarkCurveData] component version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
