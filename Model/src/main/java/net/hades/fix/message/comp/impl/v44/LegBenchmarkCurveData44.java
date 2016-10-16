/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegBenchmarkCurveData44.java
 *
 * $Id: LegBenchmarkCurveData44.java,v 1.6 2010-08-25 05:30:29 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.LegBenchmarkCurveData;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BenchmarkCurveName;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 implementation of LegBenchmarkCurveData component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 07/04/2009, 10:58:26 AM
 */
@XmlRootElement(name="BnchmkCurve")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class LegBenchmarkCurveData44 extends LegBenchmarkCurveData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegBenchmarkCurveData44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LegBenchmarkCurveData44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getLegBenchmarkCurveCurrency() {
        return legBenchmarkCurveCurrency;
    }

    @Override
    public void setLegBenchmarkCurveCurrency(Currency legBenchmarkCurveCurrency) {
        this.legBenchmarkCurveCurrency = legBenchmarkCurveCurrency;
    }

    @XmlAttribute(name = "Name")
    @Override
    public BenchmarkCurveName getLegBenchmarkCurveName() {
        return legBenchmarkCurveName;
    }

    @Override
    public void setLegBenchmarkCurveName(BenchmarkCurveName legBenchmarkCurveName) {
        this.legBenchmarkCurveName = legBenchmarkCurveName;
    }

    @XmlAttribute(name = "Point")
    @Override
    public String getLegBenchmarkCurvePoint() {
        return legBenchmarkCurvePoint;
    }

    @Override
    public void setLegBenchmarkCurvePoint(String legBenchmarkCurvePoint) {
        this.legBenchmarkCurvePoint = legBenchmarkCurvePoint;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getLegBenchmarkPrice() {
        return legBenchmarkPrice;
    }

    @Override
    public void setLegBenchmarkPrice(Double legBenchmarkPrice) {
        this.legBenchmarkPrice = legBenchmarkPrice;
    }

    @XmlAttribute(name = "PxTyp")
    @Override
    public PriceType getLegBenchmarkPriceType() {
        return legBenchmarkPriceType;
    }

    @Override
    public void setLegBenchmarkPriceType(PriceType legBenchmarkPriceType) {
        this.legBenchmarkPriceType = legBenchmarkPriceType;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.LegBenchmarkCurveCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegBenchmarkCurveCurrency, legBenchmarkCurveCurrency.getValue());
            }
            if (legBenchmarkCurveName != null && MsgUtil.isTagInList(TagNum.LegBenchmarkCurveName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegBenchmarkCurveName, legBenchmarkCurveName.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.LegBenchmarkCurvePoint, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegBenchmarkCurvePoint, legBenchmarkCurvePoint);
            }
            if (MsgUtil.isTagInList(TagNum.LegBenchmarkPrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegBenchmarkPrice, legBenchmarkPrice);
            }
            if (legBenchmarkPriceType != null && MsgUtil.isTagInList(TagNum.LegBenchmarkPriceType, SECURED_TAGS, secured)) {
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LegBenchmarkCurveData] component version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
