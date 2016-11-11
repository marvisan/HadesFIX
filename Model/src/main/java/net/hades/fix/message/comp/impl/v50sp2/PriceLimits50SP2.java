/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PriceLimits50SP2.java
 *
 * $Id: PriceLimits50SP2.java,v 1.2 2011-04-19 12:13:38 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.comp.PriceLimits;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.PriceLimitType;

/**
 * FIX 5.0SP2 implementation of PriceLimits component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/02/2009, 8:31:52 PM
 */
@XmlRootElement(name="PxLmts")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class PriceLimits50SP2 extends PriceLimits {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PriceLimits50SP2() {
    }

    public PriceLimits50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "PxLmtTyp")
    @Override
    public PriceLimitType getPriceLimitType() {
        return priceLimitType;
    }

    @Override
    public void setPriceLimitType(PriceLimitType priceLimitType) {
        this.priceLimitType = priceLimitType;
    }

    @XmlAttribute(name = "LowLmtPx")
    @Override
    public Double getLowLimitPrice() {
        return lowLimitPrice;
    }

    @Override
    public void setLowLimitPrice(Double lowLimitPrice) {
        this.lowLimitPrice = lowLimitPrice;
    }

    @XmlAttribute(name = "HiLmtPx")
    @Override
    public Double getHighLimitPrice() {
        return highLimitPrice;
    }

    @Override
    public void setHighLimitPrice(Double highLimitPrice) {
        this.highLimitPrice = highLimitPrice;
    }

    @XmlAttribute(name = "TrdgRefPx")
    @Override
    public Double getTradingReferencePrice() {
        return tradingReferencePrice;
    }

    @Override
    public void setTradingReferencePrice(Double tradingReferencePrice) {
        this.tradingReferencePrice = tradingReferencePrice;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PriceLimits] component version [5.0SP2].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
