/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * YieldData44.java
 *
 * $Id: YieldData44.java,v 1.7 2010-08-25 05:30:29 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.YieldType;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 4.4 YieldData component data implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 16/02/2009, 8:36:13 PM
 */
@XmlRootElement(name="Yield")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class YieldData44 extends YieldData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -6393925427507142264L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public YieldData44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public YieldData44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (yieldType != null && MsgUtil.isTagInList(TagNum.YieldType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.YieldType, yieldType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Yield, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Yield, yield);
            }
            if (MsgUtil.isTagInList(TagNum.YieldCalcDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.YieldCalcDate, yieldCalcDate);
            }
            if (MsgUtil.isTagInList(TagNum.YieldRedemptionDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.YieldRedemptionDate, yieldRedemptionDate);
            }
            if (MsgUtil.isTagInList(TagNum.YieldRedemptionPrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.YieldRedemptionPrice, yieldRedemptionPrice);
            }
            if (MsgUtil.isTagInList(TagNum.YieldRedemptionPriceType, SECURED_TAGS, secured)) {
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [YieldData] component version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}