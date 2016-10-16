/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderListGroup50SP2.java
 *
 * $Id: OrderStatusGroup50SP2.java,v 1.2 2011-02-04 09:58:20 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderStatusGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.OrdRejReason;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.type.OrdStatus;

/**
 * FIX 5.0SP2 implementation of OrderStatusGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="ListStat")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class OrderStatusGroup50SP2 extends OrderStatusGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public OrderStatusGroup50SP2() {
    }
    
    public OrderStatusGroup50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "ClOrdID2")
    @Override
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    @Override
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    @XmlAttribute(name = "CumQty")
    @Override
    public Double getCumQty() {
        return cumQty;
    }

    @Override
    public void setCumQty(Double cumQty) {
        this.cumQty = cumQty;
    }

    @XmlAttribute(name = "OrdStat")
    @Override
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    @Override
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    @XmlAttribute(name = "WorkingInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getWorkingIndicator() {
        return workingIndicator;
    }

    @Override
    public void setWorkingIndicator(Boolean workingIndicator) {
        this.workingIndicator = workingIndicator;
    }

    @XmlAttribute(name = "LeavesQty")
    @Override
    public Double getLeavesQty() {
        return leavesQty;
    }

    @Override
    public void setLeavesQty(Double leavesQty) {
        this.leavesQty = leavesQty;
    }

    @XmlAttribute(name = "CxlQty")
    @Override
    public Double getCxlQty() {
        return cxlQty;
    }

    @Override
    public void setCxlQty(Double cxlQty) {
        this.cxlQty = cxlQty;
    }

    @XmlAttribute(name = "AvgPx")
    @Override
    public Double getAvgPx() {
        return avgPx;
    }

    @Override
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
    }

    @XmlAttribute(name = "RejRsn")
    @Override
    public OrdRejReason getOrdRejReason() {
        return ordRejReason;
    }

    @Override
    public void setOrdRejReason(OrdRejReason ordRejReason) {
        this.ordRejReason = ordRejReason;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }
    
    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [OrderStatusGroup] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
