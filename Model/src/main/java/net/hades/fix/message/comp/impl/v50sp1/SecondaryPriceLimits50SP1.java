/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecondaryPriceLimits50SP1.java
 *
 * $Id: SecondaryPriceLimits50SP1.java,v 1.1 2011-09-27 08:57:26 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.SecondaryPriceLimits;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.type.PriceLimitType;

/**
 * FIX version 5.0SP1 SecondaryPriceLimits component data implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 16/02/2009, 8:27:57 PM
 */
@XmlRootElement(name="PxLmts2")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SecondaryPriceLimits50SP1 extends SecondaryPriceLimits {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecondaryPriceLimits50SP1() {
        super();
    }

    public SecondaryPriceLimits50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "PxLmtTyp")
    @Override
    public PriceLimitType getSecondaryPriceLimitType() {
        return secondaryPriceLimitType;
    }

    @Override
    public void setSecondaryPriceLimitType(PriceLimitType secondaryPriceLimitType) {
        this.secondaryPriceLimitType = secondaryPriceLimitType;
    }

    @XmlAttribute(name = "LowLmtPx")
    @Override
    public Double getSecondaryLowLimitPrice() {
        return secondaryLowLimitPrice;
    }

    @Override
    public void setSecondaryLowLimitPrice(Double secondaryLowLimitPrice) {
        this.secondaryLowLimitPrice = secondaryLowLimitPrice;
    }

    @XmlAttribute(name = "HiLmtPx")
    @Override
    public Double getSecondaryHighLimitPrice() {
        return secondaryHighLimitPrice;
    }

    @Override
    public void setSecondaryHighLimitPrice(Double secondaryHighLimitPrice) {
        this.secondaryHighLimitPrice = secondaryHighLimitPrice;
    }

    @XmlAttribute(name = "TrdgRefPx")
    @Override
    public Double getSecondaryTradingReferencePrice() {
        return secondaryTradingReferencePrice;
    }

    @Override
    public void setSecondaryTradingReferencePrice(Double secondaryTradingReferencePrice) {
        this.secondaryTradingReferencePrice = secondaryTradingReferencePrice;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecondaryPriceLimits] component version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
