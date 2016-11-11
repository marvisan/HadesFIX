/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDReqGroup44.java
 *
 * $Id: MDFullGroup44.java,v 1.10 2011-04-15 04:37:42 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDFullGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.QuoteCondition;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TickDirection;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradeCondition;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCTimeAdapter;

/**
 * FIX 4.4 implementation of MDReqGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="Full")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class MDFullGroup44 extends MDFullGroup {

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

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDFullGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MDFullGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @XmlAttribute(name = "Typ")
    @Override
    public MDEntryType getMdEntryType() {
        return mdEntryType;
    }

    @Override
    public void setMdEntryType(MDEntryType mdEntryType) {
        this.mdEntryType = mdEntryType;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getMdEntryPx() {
        return mdEntryPx;
    }

    @Override
    public void setMdEntryPx(Double mdEntryPx) {
        this.mdEntryPx = mdEntryPx;
    }

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @XmlAttribute(name = "Sz")
    @Override
    public Double getMdEntrySize() {
        return mdEntrySize;
    }

    @Override
    public void setMdEntrySize(Double mdEntrySize) {
        this.mdEntrySize = mdEntrySize;
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

    @XmlAttribute(name = "Dt")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getMdEntryDate() {
        return mdEntryDate;
    }

    @Override
    public void setMdEntryDate(Date mdEntryDate) {
        this.mdEntryDate = mdEntryDate;
    }

    @XmlAttribute(name = "Tm")
    @XmlJavaTypeAdapter(FixUTCTimeAdapter.class)
    @Override
    public Date getMdEntryTime() {
        return mdEntryTime;
    }

    @Override
    public void setMdEntryTime(Date mdEntryTime) {
        this.mdEntryTime = mdEntryTime;
    }

    @XmlAttribute(name = "TickDirctn")
    @Override
    public TickDirection getTickDirection() {
        return tickDirection;
    }

    @Override
    public void setTickDirection(TickDirection tickDirection) {
        this.tickDirection = tickDirection;
    }

    @XmlAttribute(name = "Mkt")
    @Override
    public String getMdMkt() {
        return mdMkt;
    }

    @Override
    public void setMdMkt(String mdMkt) {
        this.mdMkt = mdMkt;
    }

    @XmlAttribute(name = "SesID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @XmlAttribute(name = "SesSub")
    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @XmlAttribute(name = "QCond")
    @Override
    public QuoteCondition getQuoteCondition() {
        return quoteCondition;
    }

    @Override
    public void setQuoteCondition(QuoteCondition quoteCondition) {
        this.quoteCondition = quoteCondition;
    }

    @XmlAttribute(name = "TrdCond")
    @Override
    public TradeCondition getTradeCondition() {
        return tradeCondition;
    }

    @Override
    public void setTradeCondition(TradeCondition tradeCondition) {
        this.tradeCondition = tradeCondition;
    }

    @XmlAttribute(name = "Orig")
    @Override
    public String getMdEntryOriginator() {
        return mdEntryOriginator;
    }

    @Override
    public void setMdEntryOriginator(String mdEntryOriginator) {
        this.mdEntryOriginator = mdEntryOriginator;
    }

    @XmlAttribute(name = "LctnID")
    @Override
    public String getLocationID() {
        return locationID;
    }

    @Override
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    @XmlAttribute(name = "DeskID")
    @Override
    public String getDeskID() {
        return deskID;
    }

    @Override
    public void setDeskID(String deskID) {
        this.deskID = deskID;
    }

    @XmlAttribute(name = "OpenClsSettlFlag")
    @Override
    public String getOpenCloseSettlFlag() {
        return openCloseSettlFlag;
    }

    @Override
    public void setOpenCloseSettlFlag(String openCloseSettlFlag) {
        this.openCloseSettlFlag = openCloseSettlFlag;
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

    @XmlAttribute(name = "ExpireDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getExpireDate() {
        return expireDate;
    }

    @Override
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @XmlAttribute(name = "ExpireTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getExpireTime() {
        return expireTime;
    }

    @Override
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @XmlAttribute(name = "MinQty")
    @Override
    public Double getMinQty() {
        return minQty;
    }

    @Override
    public void setMinQty(Double minQty) {
        this.minQty = minQty;
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

    @XmlAttribute(name = "SellerDays")
    @Override
    public Integer getSellerDays() {
        return sellerDays;
    }

    @Override
    public void setSellerDays(Integer sellerDays) {
        this.sellerDays = sellerDays;
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

    @XmlAttribute(name = "EntryID")
    @Override
    public String getQuoteEntryID() {
        return quoteEntryID;
    }

    @Override
    public void setQuoteEntryID(String quoteEntryID) {
        this.quoteEntryID = quoteEntryID;
    }

    @XmlAttribute(name = "Buyer")
    @Override
    public String getMdEntryBuyer() {
        return mdEntryBuyer;
    }

    @Override
    public void setMdEntryBuyer(String mdEntryBuyer) {
        this.mdEntryBuyer = mdEntryBuyer;
    }

    @XmlAttribute(name = "Seller")
    @Override
    public String getMdEntrySeller() {
        return mdEntrySeller;
    }

    @Override
    public void setMdEntrySeller(String mdEntrySeller) {
        this.mdEntrySeller = mdEntrySeller;
    }

    @XmlAttribute(name = "NumOfOrds")
    @Override
    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    @Override
    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    @XmlAttribute(name = "PosNo")
    @Override
    public Integer getMdEntryPositionNo() {
        return mdEntryPositionNo;
    }

    @Override
    public void setMdEntryPositionNo(Integer mdEntryPositionNo) {
        this.mdEntryPositionNo = mdEntryPositionNo;
    }

    @XmlAttribute(name = "Scope")
    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @XmlAttribute(name = "PxDelta")
    @Override
    public Double getPriceDelta() {
        return priceDelta;
    }

    @Override
    public void setPriceDelta(Double priceDelta) {
        this.priceDelta = priceDelta;
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (mdEntryType != null && MsgUtil.isTagInList(TagNum.MDEntryType, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryType, mdEntryType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryPx, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryPx, mdEntryPx);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MDEntrySize, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntrySize, mdEntrySize);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryDate, SECURED_TAGS, secured)) {
                 TagEncoder.encodeUtcDate(bao, TagNum.MDEntryDate, mdEntryDate);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryTime, SECURED_TAGS, secured)) {
                 TagEncoder.encodeUTCTime(bao, TagNum.MDEntryTime, mdEntryTime);
            }
            if (tickDirection != null && MsgUtil.isTagInList(TagNum.TickDirection, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.TickDirection, tickDirection.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MDMkt, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDMkt, mdMkt);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (quoteCondition != null && MsgUtil.isTagInList(TagNum.QuoteCondition, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.QuoteCondition, quoteCondition.getValue());
            }
            if (tradeCondition != null && MsgUtil.isTagInList(TagNum.TradeCondition, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.TradeCondition, tradeCondition.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryOriginator, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryOriginator, mdEntryOriginator);
            }
            if (MsgUtil.isTagInList(TagNum.LocationID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.LocationID, locationID);
            }
            if (MsgUtil.isTagInList(TagNum.DeskID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.DeskID, deskID);
            }
            if (MsgUtil.isTagInList(TagNum.OpenCloseSettlFlag, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.OpenCloseSettlFlag, openCloseSettlFlag);
            }
            if (timeInForce != null && MsgUtil.isTagInList(TagNum.TimeInForce, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExpireDate, SECURED_TAGS, secured)) {
                 TagEncoder.encodeDate(bao, TagNum.ExpireDate, expireDate);
            }
            if (MsgUtil.isTagInList(TagNum.ExpireTime, SECURED_TAGS, secured)) {
                 TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            }
            if (MsgUtil.isTagInList(TagNum.MinQty, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MinQty, minQty);
            }
            if (MsgUtil.isTagInList(TagNum.ExecInst, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            }
            if (MsgUtil.isTagInList(TagNum.SellerDays, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.SellerDays, sellerDays);
            }
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteEntryID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.QuoteEntryID, quoteEntryID);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryBuyer, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryBuyer, mdEntryBuyer);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntrySeller, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntrySeller, mdEntrySeller);
            }
            if (MsgUtil.isTagInList(TagNum.NumberOfOrders, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.NumberOfOrders, numberOfOrders);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryPositionNo, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryPositionNo, mdEntryPositionNo);
            }
            if (MsgUtil.isTagInList(TagNum.Scope, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.Scope, scope);
            }
            if (MsgUtil.isTagInList(TagNum.PriceDelta, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.PriceDelta, priceDelta);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (MsgUtil.isTagInList(TagNum.MDPriceLevel, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MDPriceLevel, mdPriceLevel);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MDFullGroup] group version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
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
}
