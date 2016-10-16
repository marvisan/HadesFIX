/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PriceLimits.java
 *
 * $Id: TradeReportOrderDetail.java,v 1.2 2011-10-25 08:29:20 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.DateConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
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
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.util.TagEncoder;

/**
 * Trade report order data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/02/2009, 2:52:41 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TradeReportOrderDetail extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.ListID.getValue(),
        TagNum.RefOrderID.getValue(),
        TagNum.RefOrderIDSource.getValue(),
        TagNum.RefOrdIDReason.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.Price.getValue(),
        TagNum.StopPx.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.OrdStatus.getValue(),
        TagNum.LeavesQty.getValue(),
        TagNum.CumQty.getValue(),
        TagNum.TimeInForce.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.BookingType.getValue(),
        TagNum.OrigCustOrderCapacity.getValue(),
        TagNum.OrderInputDevice.getValue(),
        TagNum.LotType.getValue(),
        TagNum.TransBkdTime.getValue(),
        TagNum.OrigOrdModTime.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 37 REQUIRED. Starting with 5.0SP2 version.
     */
    protected String orderID;

    /**
     * TagNum = 198. Starting with 5.0SP2 version.
     */
    protected String secondaryOrderID;

    /**
     * TagNum = 11. Starting with 5.0SP2 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 526. Starting with 5.0SP2 version.
     */
    protected String secondaryClOrdID;

    /**
     * TagNum = 66. Starting with 5.0SP2 version.
     */
    protected String listID;
   
    /**
     * TagNum = 1080. Starting with 5.0SP2 version.
     */
    protected String refOrderID;
    
    /**
     * TagNum = 1081. Starting with 5.0SP2 version.
     */
    protected RefOrderIDSource refOrderIDSource;
    
    /**
     * TagNum = 1431. Starting with 5.0SP2 version.
     */
    protected RefOrdIDReason refOrdIDReason;

    /**
     * TagNum = 40. Starting with 5.0SP2 version.
     */
    protected OrdType ordType;

    /**
     * TagNum = 44. Starting with 5.0SP2 version.
     */
    protected Double price;

    /**
     * TagNum = 99. Starting with 5.0SP2 version.
     */
    protected Double stopPx;

    /**
     * TagNum = 18. Starting with 5.0SP2 version.
     */
    protected String execInst;

    /**
     * TagNum = 39. Starting with 5.0SP2 version.
     */
    protected OrdStatus ordStatus;

    /**
     * Starting with 5.0SP2 version.
     */
    protected OrderQtyData orderQtyData;

    /**
     * TagNum = 151. Starting with 5.0SP2 version.
     */
    protected Double leavesQty;

    /**
     * TagNum = 14. Starting with 5.0SP2 version.
     */
    protected Double cumQty;

    /**
     * TagNum = 59. Starting with 5.0SP2 version.
     */
    protected TimeInForce timeInForce;

    /**
     * TagNum = 126. Starting with 5.0SP2 version.
     */
    protected Date expireTime;

    /**
     * Starting with 5.0SP2 version.
     */
    protected DisplayInstruction displayInstruction;

    /**
     * TagNum = 528. Starting with 5.0SP2 version.
     */
    protected OrderCapacity orderCapacity;

    /**
     * TagNum = 529. Starting with 5.0SP2 version.
     */
    protected String orderRestrictions;

    /**
     * TagNum = 775. Starting with 5.0SP2 version.
     */
    protected BookingType bookingType;

    /**
     * TagNum = 1432. Starting with 5.0SP2 version.
     */
    protected OrigCustOrderCapacity origCustOrderCapacity;

    /**
     * TagNum = 821. Starting with 5.0SP2 version.
     */
    protected String orderInputDevice;

    /**
     * TagNum = 1093. Starting with 5.0SP2 version.
     */
    protected LotType lotType;

    /**
     * TagNum = 483. Starting with 5.0SP2 version.
     */
    protected Date transBkdTime;

    /**
     * TagNum = 586. Starting with 5.0SP2 version.
     */
    protected Date origOrdModTime;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeReportOrderDetail() {
    }

    public TradeReportOrderDetail(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    /**
     * Message field setter.
     * @param secondaryOrderID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    /**
     * Message field setter.
     * @param secondaryClOrdID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.ListID)
    public String getListID() {
        return listID;
    }

    /**
     * Message field setter.
     * @param listID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.ListID)
    public void setListID(String listID) {
        this.listID = listID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.RefOrderID)
    public String getRefOrderID() {
        return refOrderID;
    }

    /**
     * Message field setter.
     * @param refOrderID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.RefOrderID)
    public void setRefOrderID(String refOrderID) {
        this.refOrderID = refOrderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.RefOrderIDSource)
    public RefOrderIDSource getRefOrderIDSource() {
        return refOrderIDSource;
    }

    /**
     * Message field setter.
     * @param refOrderIDSource field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.RefOrderIDSource)
    public void setRefOrderIDSource(RefOrderIDSource refOrderIDSource) {
        this.refOrderIDSource = refOrderIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.RefOrdIDReason)
    public RefOrdIDReason getRefOrdIDReason() {
        return refOrdIDReason;
    }

    /**
     * Message field setter.
     * @param refOrdIDReason field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.RefOrdIDReason)
    public void setRefOrdIDReason(RefOrdIDReason refOrdIDReason) {
        this.refOrdIDReason = refOrdIDReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrdType)
    public OrdType getOrdType() {
        return ordType;
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrdType)
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.Price)
    public Double getPrice() {
        return price;
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.Price)
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.StopPx)
    public Double getStopPx() {
        return stopPx;
    }

    /**
     * Message field setter.
     * @param stopPx field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.StopPx)
    public void setStopPx(Double stopPx) {
        this.stopPx = stopPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public String getExecInst() {
        return execInst;
    }

    /**
     * Message field setter.
     * @param execInst field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrdStatus)
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    /**
     * Message field setter.
     * @param ordStatus field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrdStatus)
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    public OrderQtyData getOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the OrderQtyData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0SP2")
    public void setOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the OrderQtyData component to null.
     */
    @FIXVersion(introduced="5.0SP2")
    public void clearOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LeavesQty)
    public Double getLeavesQty() {
        return leavesQty;
    }

    /**
     * Message field setter.
     * @param leavesQty field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LeavesQty)
    public void setLeavesQty(Double leavesQty) {
        this.leavesQty = leavesQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.CumQty)
    public Double getCumQty() {
        return cumQty;
    }

    /**
     * Message field setter.
     * @param cumQty field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.CumQty)
    public void setCumQty(Double cumQty) {
        this.cumQty = cumQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    /**
     * Message field setter.
     * @param timeInForce field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    public DisplayInstruction getDisplayInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the DisplayInstruction component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0SP2")
    public void setDisplayInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the DisplayInstruction component to null.
     */
    @FIXVersion(introduced="5.0SP2")
    public void clearDisplayInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    /**
     * Message field setter.
     * @param orderCapacity field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderRestrictions)
    public String getOrderRestrictions() {
        return orderRestrictions;
    }

    /**
     * Message field setter.
     * @param orderRestrictions field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderRestrictions)
    public void setOrderRestrictions(String orderRestrictions) {
        this.orderRestrictions = orderRestrictions;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.BookingType)
    public BookingType getBookingType() {
        return bookingType;
    }

    /**
     * Message field setter.
     * @param bookingType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.BookingType)
    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrigCustOrderCapacity)
    public OrigCustOrderCapacity getOrigCustOrderCapacity() {
        return origCustOrderCapacity;
    }

    /**
     * Message field setter.
     * @param origCustOrderCapacity field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrigCustOrderCapacity)
    public void setOrigCustOrderCapacity(OrigCustOrderCapacity origCustOrderCapacity) {
        this.origCustOrderCapacity = origCustOrderCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderInputDevice)
    public String getOrderInputDevice() {
        return orderInputDevice;
    }

    /**
     * Message field setter.
     * @param orderInputDevice field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderInputDevice)
    public void setOrderInputDevice(String orderInputDevice) {
        this.orderInputDevice = orderInputDevice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LotType)
    public LotType getLotType() {
        return lotType;
    }

    /**
     * Message field setter.
     * @param lotType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LotType)
    public void setLotType(LotType lotType) {
        this.lotType = lotType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    /**
     * Message field setter.
     * @param transBkdTime field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrigOrdModTime)
    public Date getOrigOrdModTime() {
        return origOrdModTime;
    }

    /**
     * Message field setter.
     * @param origOrdModTime field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrigOrdModTime)
    public void setOrigOrdModTime(Date origOrdModTime) {
        this.origOrdModTime = origOrdModTime;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.OrderID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (orderID == null || orderID.trim().isEmpty()) {
            errorMsg.append(" [OrderID]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            TagEncoder.encode(bao, TagNum.ListID, listID);
            TagEncoder.encode(bao, TagNum.RefOrderID, refOrderID);
            if (refOrderIDSource != null) {
                TagEncoder.encode(bao, TagNum.RefOrderIDSource, refOrderIDSource.getValue());
            }
            if (refOrdIDReason != null) {
                TagEncoder.encode(bao, TagNum.RefOrdIDReason, refOrdIDReason.getValue());
            }
            if (ordType != null) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            TagEncoder.encode(bao, TagNum.Price, price);
            TagEncoder.encode(bao, TagNum.StopPx, stopPx);
            TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            if (ordStatus != null) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.LeavesQty, leavesQty);
            TagEncoder.encode(bao, TagNum.CumQty, cumQty);
            if (timeInForce != null) {
                TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            if (displayInstruction != null) {
                bao.write(displayInstruction.encode(MsgSecureType.ALL_FIELDS));
            }
            if (orderCapacity != null) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.OrderRestrictions, orderRestrictions);
            if (bookingType != null) {
                TagEncoder.encode(bao, TagNum.BookingType, bookingType.getValue());
            }
            if (origCustOrderCapacity != null) {
                TagEncoder.encode(bao, TagNum.OrigCustOrderCapacity, origCustOrderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.OrderInputDevice, orderInputDevice);
            if (lotType != null) {
                TagEncoder.encode(bao, TagNum.LotType, lotType.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransBkdTime, transBkdTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.OrigOrdModTime, origOrdModTime);
            
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;

           case SecondaryOrderID:
                secondaryOrderID = new String(tag.value, sessionCharset);
                break;

            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

           case SecondaryClOrdID:
                secondaryClOrdID = new String(tag.value, sessionCharset);
                break;

            case ListID:
                listID = new String(tag.value, sessionCharset);
                break;
                
            case RefOrderID:
                refOrderID = new String(tag.value, sessionCharset);
                break;

            case RefOrderIDSource:
                refOrderIDSource = RefOrderIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case RefOrdIDReason:
                refOrdIDReason = RefOrdIDReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OrdType:
                ordType = OrdType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Price:
                price = new Double(new String(tag.value, sessionCharset));
                break;

            case StopPx:
                stopPx = new Double(new String(tag.value, sessionCharset));
                break;

            case ExecInst:
                execInst = new String(tag.value, sessionCharset);
                break;

            case OrdStatus:
                ordStatus = OrdStatus.valueFor(new String(tag.value, sessionCharset));
                break;

            case LeavesQty:
                leavesQty = new Double(new String(tag.value, sessionCharset));
                break;

            case CumQty:
                cumQty = new Double(new String(tag.value, sessionCharset));
                break;

            case TimeInForce:
                timeInForce = TimeInForce.valueFor(new String(tag.value, sessionCharset));
                break;

            case ExpireTime:
                expireTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case OrderCapacity:
                orderCapacity = OrderCapacity.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case OrderRestrictions:
                orderRestrictions = new String(tag.value, getSessionCharset());
                break;

            case BookingType:
                bookingType = BookingType.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;

            case OrigCustOrderCapacity:
                origCustOrderCapacity = OrigCustOrderCapacity.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;

            case OrderInputDevice:
                orderInputDevice = new String(tag.value, sessionCharset);
                break;

            case LotType:
                lotType = LotType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case TransBkdTime:
                transBkdTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;
                
            case OrigOrdModTime:
                origOrdModTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TradeReportOrderDetail] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{TradeReportOrderDetail=");
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.SecondaryOrderID, secondaryOrderID);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.SecondaryClOrdID, secondaryClOrdID);
        printTagValue(b, TagNum.ListID, listID);
        printTagValue(b, TagNum.RefOrderID, refOrderID);
        printTagValue(b, TagNum.RefOrderIDSource, refOrderIDSource);
        printTagValue(b, TagNum.RefOrdIDReason, refOrdIDReason);
        printTagValue(b, TagNum.OrdType, ordType);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.StopPx, stopPx);
        printTagValue(b, TagNum.ExecInst, execInst);
        printTagValue(b, TagNum.OrdStatus, ordStatus);
        printTagValue(b, orderQtyData);
        printTagValue(b, TagNum.LeavesQty, leavesQty);
        printTagValue(b, TagNum.CumQty, cumQty);
        printTagValue(b, TagNum.TimeInForce, timeInForce);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printTagValue(b, displayInstruction);
        printTagValue(b, TagNum.OrderCapacity, orderCapacity);
        printTagValue(b, TagNum.OrderRestrictions, orderRestrictions);
        printTagValue(b, TagNum.BookingType, bookingType);
        printTagValue(b, TagNum.OrigCustOrderCapacity, origCustOrderCapacity);
        printTagValue(b, TagNum.OrderInputDevice, orderInputDevice);
        printTagValue(b, TagNum.LotType, lotType);
        printUTCDateTimeTagValue(b, TagNum.TransBkdTime, transBkdTime);
        printUTCDateTimeTagValue(b, TagNum.OrigOrdModTime, origOrdModTime);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
