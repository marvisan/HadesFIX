/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * YieldData50SP2.java
 *
 * $Id: YieldData50SP2.java,v 1.6 2010-02-25 08:37:42 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.YieldType;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 5.0SP2 YieldData component data implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 16/02/2009, 9:15:50 PM
 */
@XmlRootElement(name="Yield")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class YieldData50SP2 extends YieldData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public YieldData50SP2() {
        super();
    }

    public YieldData50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Typ")
    @Override
    public YieldType getYieldType() {
        return yieldType;
    }

    @Override
    public void setYieldType(YieldType yieldType) {
        this.yieldType = yieldType;
    }


    @XmlAttribute(name = "Yld")
    @Override
    public Double getYield() {
        return yield;
    }

    @Override
    public void setYield(Double yield) {
        this.yield = yield;
    }

    @XmlAttribute(name = "CalcDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getYieldCalcDate() {
        return yieldCalcDate;
    }

    @Override
    public void setYieldCalcDate(Date yieldCalcDate) {
        this.yieldCalcDate = yieldCalcDate;
    }


    @XmlAttribute(name = "RedDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getYieldRedemptionDate() {
        return yieldRedemptionDate;
    }

    @Override
    public void setYieldRedemptionDate(Date yieldRedemptionDate) {
        this.yieldRedemptionDate = yieldRedemptionDate;
    }

    @XmlAttribute(name = "RedPx")
    @Override
    public Double getYieldRedemptionPrice() {
        return yieldRedemptionPrice;
    }

    @Override
    public void setYieldRedemptionPrice(Double yieldRedemptionPrice) {
        this.yieldRedemptionPrice = yieldRedemptionPrice;
    }

    @XmlAttribute(name = "RedPxTyp")
    @Override
    public PriceType getYieldRedemptionPriceType() {
        return yieldRedemptionPriceType;
    }

    @Override
    public void setYieldRedemptionPriceType(PriceType yieldRedemptionPriceType) {
        this.yieldRedemptionPriceType = yieldRedemptionPriceType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [YieldData] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
