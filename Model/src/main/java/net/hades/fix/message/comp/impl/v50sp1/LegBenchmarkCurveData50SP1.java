/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegBenchmarkCurveData50SP1.java
 *
 * $Id: LegBenchmarkCurveData50SP1.java,v 1.4 2010-02-25 08:37:37 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.LegBenchmarkCurveData;
import net.hades.fix.message.type.BenchmarkCurveName;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIX version 5.0SP1 implementation of LegBenchmarkCurveData component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 07/04/2009, 10:58:26 AM
 */
@XmlRootElement(name="BnchmkCurve")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class LegBenchmarkCurveData50SP1 extends LegBenchmarkCurveData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegBenchmarkCurveData50SP1() {
        super();
    }

    public LegBenchmarkCurveData50SP1(FragmentContext context) {
        super(context);
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LegBenchmarkCurveData] component version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
