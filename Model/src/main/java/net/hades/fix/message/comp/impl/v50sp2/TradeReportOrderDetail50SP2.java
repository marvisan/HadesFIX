/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeReportOrderDetail50SP2.java
 *
 * $Id: TradeReportOrderDetail50SP2.java,v 1.2 2011-10-25 08:29:21 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.DisplayInstruction;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.TradeReportOrderDetail;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.impl.v44.OrderQtyData44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrigCustOrderCapacity;
import net.hades.fix.message.type.RefOrdIDReason;
import net.hades.fix.message.type.RefOrderIDSource;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX 5.0SP2 implementation of PriceLimits component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/02/2009, 8:31:52 PM
 */
@XmlRootElement(name="TrdRptOrdDetl")
@XmlType(propOrder = {"orderQtyData", "displayInstruction"})
@XmlAccessorType(XmlAccessType.NONE)
public class TradeReportOrderDetail50SP2 extends TradeReportOrderDetail {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;
    
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData50SP2().getFragmentAllTags();
    protected static final Set<Integer> DISPLAY_INSTRUCTION_COMP_TAGS = new DisplayInstruction50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(DISPLAY_INSTRUCTION_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(DISPLAY_INSTRUCTION_COMP_TAGS);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeReportOrderDetail50SP2() {
    }

    public TradeReportOrderDetail50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "OrdID2")
    @Override
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    @Override
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
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

    @XmlAttribute(name = "ListID")
    @Override
    public String getListID() {
        return listID;
    }

    @Override
    public void setListID(String listID) {
        this.listID = listID;
    }

    @XmlAttribute(name = "RefOrdID")
    @Override
    public String getRefOrderID() {
        return refOrderID;
    }

    @Override
    public void setRefOrderID(String refOrderID) {
        this.refOrderID = refOrderID;
    }

    @XmlAttribute(name = "RefOrdIDSrc")
    @Override
    public RefOrderIDSource getRefOrderIDSource() {
        return refOrderIDSource;
    }

    @Override
    public void setRefOrderIDSource(RefOrderIDSource refOrderIDSource) {
        this.refOrderIDSource = refOrderIDSource;
    }

    @XmlAttribute(name = "RefOrdIDRsn")
    @Override
    public RefOrdIDReason getRefOrdIDReason() {
        return refOrdIDReason;
    }

    @Override
    public void setRefOrdIDReason(RefOrdIDReason refOrdIDReason) {
        this.refOrdIDReason = refOrdIDReason;
    }

    @XmlAttribute(name = "OrdTyp")
    @Override
    public OrdType getOrdType() {
        return ordType;
    }

    @Override
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @XmlAttribute(name = "StopPx")
    @Override
    public Double getStopPx() {
        return stopPx;
    }

    @Override
    public void setStopPx(Double stopPx) {
        this.stopPx = stopPx;
    }

    @XmlAttribute(name = "ExecInst")
    @Override
    public String getExecInst() {
        return execInst;
    }

    @Override
    public void setExecInst(String execInst) {
        this.execInst = execInst;
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

    @XmlElementRef
    @Override
    public OrderQtyData getOrderQtyData() {
        return orderQtyData;
    }

    @Override
    public void setOrderQtyData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.orderQtyData = new OrderQtyData50SP2(context);
    }

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
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

    @XmlAttribute(name = "CumQty")
    @Override
    public Double getCumQty() {
        return cumQty;
    }

    @Override
    public void setCumQty(Double cumQty) {
        this.cumQty = cumQty;
    }

    @XmlAttribute(name = "TmInForce")
    @Override
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    @Override
    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    @XmlAttribute(name = "ExpireTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getExpireTime() {
        return expireTime;
    }

    @Override
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @XmlElementRef
    @Override
    public DisplayInstruction getDisplayInstruction() {
        return displayInstruction;
    }

    public void setDisplayInstruction(DisplayInstruction displayInstruction) {
        this.displayInstruction = displayInstruction;
    }

    @Override
    public void setDisplayInstruction() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.displayInstruction = new DisplayInstruction50SP2(context);
    }

    @Override
    public void clearDisplayInstruction() {
        this.displayInstruction = null;
    }

    @XmlAttribute(name = "Cpcty")
    @Override
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    @Override
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    @XmlAttribute(name = "Rstctions")
    @Override
    public String getOrderRestrictions() {
        return orderRestrictions;
    }

    @Override
    public void setOrderRestrictions(String orderRestrictions) {
        this.orderRestrictions = orderRestrictions;
    }

    @XmlAttribute(name = "BkngTyp")
    @Override
    public BookingType getBookingType() {
        return bookingType;
    }

    @Override
    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    @XmlAttribute(name = "OrigCustOrdCpcty")
    @Override
    public OrigCustOrderCapacity getOrigCustOrderCapacity() {
        return origCustOrderCapacity;
    }

    @Override
    public void setOrigCustOrderCapacity(OrigCustOrderCapacity origCustOrderCapacity) {
        this.origCustOrderCapacity = origCustOrderCapacity;
    }

    @XmlAttribute(name = "OrdInptDev")
    @Override
    public String getOrderInputDevice() {
        return orderInputDevice;
    }

    @Override
    public void setOrderInputDevice(String orderInputDevice) {
        this.orderInputDevice = orderInputDevice;
    }

    @XmlAttribute(name = "LotTyp")
    @Override
    public LotType getLotType() {
        return lotType;
    }

    @Override
    public void setLotType(LotType lotType) {
        this.lotType = lotType;
    }

    @XmlAttribute(name = "TransBkdTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    @Override
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
    }

    @XmlAttribute(name = "OrigOrdModTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getOrigOrdModTime() {
        return origOrdModTime;
    }

    @Override
    public void setOrigOrdModTime(Date origOrdModTime) {
        this.origOrdModTime = origOrdModTime;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData44(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (DISPLAY_INSTRUCTION_COMP_TAGS.contains(tag.tagNum)) {
            if (displayInstruction == null) {
                displayInstruction = new DisplayInstruction50SP2(context);
            }
            displayInstruction.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradeReportOrderDetail] component version [5.0SP2].";
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
