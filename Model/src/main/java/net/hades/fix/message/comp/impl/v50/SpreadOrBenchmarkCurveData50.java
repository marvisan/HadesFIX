/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SpreadOrBenchmarkCurveData50.java
 *
 * $Id: SpreadOrBenchmarkCurveData50.java,v 1.6 2010-02-04 10:11:06 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.type.BenchmarkCurveName;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SecurityIDSource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIX version 5.0 SpreadOrBenchmarkCurveData component data implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 16/02/2009, 8:27:57 PM
 */
@XmlRootElement(name="SprdBnchmkCurve")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SpreadOrBenchmarkCurveData50 extends SpreadOrBenchmarkCurveData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -6619969735535027888L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SpreadOrBenchmarkCurveData50() {
        super();
    }

    public SpreadOrBenchmarkCurveData50(FragmentContext context) {
        super(context);
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SpreadOrBenchmarkCurveData] component version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
