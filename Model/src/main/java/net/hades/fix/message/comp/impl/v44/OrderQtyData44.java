/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderQtyData44.java
 *
 * $Id: OrderQtyData44.java,v 1.9 2011-02-06 04:44:15 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.RoundingDirection;
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
 * FIX version 4.4 implementation of OrderQtyData component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 14/02/2009, 7:19:18 PM
 */
@XmlRootElement(name="OrdQty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class OrderQtyData44 extends OrderQtyData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderQtyData44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public OrderQtyData44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Qty")
    @Override
    public Double getOrderQty() {
        return orderQty;
    }

    @Override
    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    @XmlAttribute(name = "Cash")
    @Override
    public Double getCashOrderQty() {
        return cashOrderQty;
    }

    @Override
    public void setCashOrderQty(Double cashOrderQty) {
        this.cashOrderQty = cashOrderQty;
    }

    @XmlAttribute(name = "Pct")
    @Override
    public Double getOrderPercent() {
        return orderPercent;
    }

    @Override
    public void setOrderPercent(Double orderPercent) {
        this.orderPercent = orderPercent;
    }

    @XmlAttribute(name = "RndDir")
    @Override
    public RoundingDirection getRoundingDirection() {
        return roundingDirection;
    }

    @Override
    public void setRoundingDirection(RoundingDirection roundingDirection) {
        this.roundingDirection = roundingDirection;
    }

    @XmlAttribute(name = "RndMod")
    @Override
    public Double getRoundingModulus() {
        return roundingModulus;
    }

    @Override
    public void setRoundingModulus(Double roundingModulus) {
        this.roundingModulus = roundingModulus;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.OrderQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderQty, orderQty);
            }
            if (MsgUtil.isTagInList(TagNum.CashOrderQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashOrderQty, cashOrderQty);
            }
            if (MsgUtil.isTagInList(TagNum.OrderPercent, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderPercent, orderPercent);
            }
            if (roundingDirection != null && MsgUtil.isTagInList(TagNum.RoundingDirection, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RoundingDirection, roundingDirection.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.RoundingModulus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RoundingModulus, roundingModulus);
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
        return "This tag is not supported in [OrderQtyData] component version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
