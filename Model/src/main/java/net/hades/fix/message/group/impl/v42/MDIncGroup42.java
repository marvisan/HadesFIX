/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDIncGroup42.java
 *
 * $Id: MDIncGroup42.java,v 1.7 2010-11-23 10:20:18 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDIncGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCTimeAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DeleteReason;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MDUpdateAction;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.QuoteCondition;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TickDirection;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradeCondition;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 5.0 implementation of MDReqGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 05/04/2009, 11:39:24 AM
 */
public class MDIncGroup42 extends MDIncGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MDUpdateAction.getValue(),
        TagNum.DeleteReason.getValue(),
        TagNum.MDEntryType.getValue(),
        TagNum.MDEntryID.getValue(),
        TagNum.MDEntryRefID.getValue(),
        TagNum.Symbol.getValue(),
        TagNum.SymbolSfx.getValue(),
        TagNum.SecurityID.getValue(),
        TagNum.SecurityIDSource.getValue(),
        TagNum.SecurityType.getValue(),
        TagNum.MaturityMonthYear.getValue(),
        TagNum.MaturityDay.getValue(),
        TagNum.PutOrCall.getValue(),
        TagNum.StrikePrice.getValue(),
        TagNum.OptAttribute.getValue(),
        TagNum.ContractMultiplier.getValue(),
        TagNum.CouponRate.getValue(),
        TagNum.SecurityExchange.getValue(),
        TagNum.Issuer.getValue(),
        TagNum.SecurityDesc.getValue(),
        TagNum.FinancialStatus.getValue(),
        TagNum.CorporateAction.getValue(),
        TagNum.MDEntryPx.getValue(),
        TagNum.Currency.getValue(),
        TagNum.MDEntrySize.getValue(),
        TagNum.MDEntryDate.getValue(),
        TagNum.MDEntryTime.getValue(),
        TagNum.TickDirection.getValue(),
        TagNum.MDMkt.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.QuoteCondition.getValue(),
        TagNum.TradeCondition.getValue(),
        TagNum.MDEntryOriginator.getValue(),
        TagNum.LocationID.getValue(),
        TagNum.DeskID.getValue(),
        TagNum.OpenCloseSettlFlag.getValue(),
        TagNum.TimeInForce.getValue(),
        TagNum.ExpireDate.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.MinQty.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.SellerDays.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.QuoteEntryID.getValue(),
        TagNum.MDEntryBuyer.getValue(),
        TagNum.MDEntrySeller.getValue(),
        TagNum.NumberOfOrders.getValue(),
        TagNum.MDEntryPositionNo.getValue(),
        TagNum.TotalVolumeTraded.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS_V42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedIssuerLen.getValue(),
        TagNum.EncodedSecurityDescLen.getValue(),
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V42);
        ALL_TAGS.addAll(START_DATA_TAGS_V42);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDIncGroup42() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MDIncGroup42(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V42;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @XmlAttribute(name = "UpdtAct")
    @Override
    public MDUpdateAction getMdUpdateAction() {
        return mdUpdateAction;
    }

    @Override
    public void setMdUpdateAction(MDUpdateAction mdUpdateAction) {
        this.mdUpdateAction = mdUpdateAction;
    }

    @XmlAttribute(name = "DelRsn")
    @Override
    public DeleteReason getDeleteReason() {
        return deleteReason;
    }

    @Override
    public void setDeleteReason(DeleteReason deleteReason) {
        this.deleteReason = deleteReason;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public MDEntryType getMdEntryType() {
        return mdEntryType;
    }

    @Override
    public void setMdEntryType(MDEntryType mdEntryType) {
        this.mdEntryType = mdEntryType;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getMdEntryID() {
        return mdEntryID;
    }

    @Override
    public void setMdEntryID(String mdEntryID) {
        this.mdEntryID = mdEntryID;
    }

    @XmlAttribute(name = "RefID")
    @Override
    public String getMdEntryRefID() {
        return mdEntryRefID;
    }

    @Override
    public void setMdEntryRefID(String mdEntryRefID) {
        this.mdEntryRefID = mdEntryRefID;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbolSfx() {
        return symbolSfx;
    }

    @Override
    public void setSymbolSfx(String symbolSfx) {
        this.symbolSfx = symbolSfx;
    }

    @Override
    public String getSecurityID() {
        return securityID;
    }

    @Override
    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    @Override
    public String getSecurityIDSource() {
        return securityIDSource;
    }

    @Override
    public void setSecurityIDSource(String securityIDSource) {
        this.securityIDSource = securityIDSource;
    }

    @Override
    public String getSecurityType() {
        return securityType;
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    @Override
    public String getMaturityMonthYear() {
        return maturityMonthYear;
    }

    @Override
    public void setMaturityMonthYear(String maturityMonthYear) {
        this.maturityMonthYear = maturityMonthYear;
    }

    @Override
    public Integer getMaturityDay() {
        return maturityDay;
    }

    @Override
    public void setMaturityDay(Integer maturityDay) {
        this.maturityDay = maturityDay;
    }

    @Override
    public PutOrCall getPutOrCall() {
        return putOrCall;
    }

    @Override
    public void setPutOrCall(PutOrCall putOrCall) {
        this.putOrCall = putOrCall;
    }

    @Override
    public Double getStrikePrice() {
        return strikePrice;
    }

    @Override
    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    @Override
    public Character getOptAttribute() {
        return optAttribute;
    }

    @Override
    public void setOptAttribute(Character optAttribute) {
        this.optAttribute = optAttribute;
    }

    @Override
    public Double getContractMultiplier() {
        return contractMultiplier;
    }

    @Override
    public void setContractMultiplier(Double contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
    }

    @Override
    public Double getCouponRate() {
        return couponRate;
    }

    @Override
    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }

    @Override
    public String getSecurityExchange() {
        return securityExchange;
    }

    @Override
    public void setSecurityExchange(String securityExchange) {
        this.securityExchange = securityExchange;
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public Integer getEncodedIssuerLen() {
        return encodedIssuerLen;
    }

    @Override
    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        this.encodedIssuerLen = encodedIssuerLen;
    }

    @Override
    public byte[] getEncodedIssuer() {
        return encodedIssuer;
    }

    @Override
    public void setEncodedIssuer(byte[] encodedIssuer) {
        this.encodedIssuer = encodedIssuer;
        if (encodedIssuerLen == null) {
            encodedIssuerLen = new Integer(encodedIssuer.length);
        }
    }

    @Override
    public String getSecurityDesc() {
        return securityDesc;
    }

    @Override
    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
    }

    @Override
    public Integer getEncodedSecurityDescLen() {
        return encodedSecurityDescLen;
    }

    @Override
    public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
        this.encodedSecurityDescLen = encodedSecurityDescLen;
    }

    @Override
    public byte[] getEncodedSecurityDesc() {
        return encodedSecurityDesc;
    }

    @Override
    public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
        this.encodedSecurityDesc = encodedSecurityDesc;
        if (encodedSecurityDescLen == null) {
            encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
        }
    }

    @XmlAttribute(name = "FinclStat")
    @Override
    public String getFinancialStatus() {
        return financialStatus;
    }

    @Override
    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    @XmlAttribute(name = "CorpActn")
    @Override
    public String getCorporateAction() {
        return corporateAction;
    }

    @Override
    public void setCorporateAction(String corporateAction) {
        this.corporateAction = corporateAction;
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

    @Override
    public Double getTotalVolumeTraded() {
        return totalVolumeTraded;
    }

    @Override
    public void setTotalVolumeTraded(Double totalVolumeTraded) {
        this.totalVolumeTraded = totalVolumeTraded;
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
            if (mdUpdateAction != null && MsgUtil.isTagInList(TagNum.MDUpdateAction, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDUpdateAction, mdUpdateAction.getValue());
            }
            if (deleteReason != null && MsgUtil.isTagInList(TagNum.DeleteReason, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.DeleteReason, deleteReason.getValue());
            }
            if (mdEntryType != null && MsgUtil.isTagInList(TagNum.MDEntryType, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryType, mdEntryType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryID, mdEntryID);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryRefID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryRefID, mdEntryRefID);
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (noUnderlyings != null && MsgUtil.isTagInList(TagNum.NoUnderlyings, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noUnderlyings.intValue()) {
                    for (int i = 0; i < noUnderlyings.intValue(); i++) {
                        if (underlyingInstruments[i] != null) {
                            bao.write(underlyingInstruments[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "UnderlyingInstrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (noLegs != null && MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrumentLegs[i] != null) {
                            bao.write(instrumentLegs[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "InstrumentLeg field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegs.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.Symbol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Symbol, symbol);
            }
            if (MsgUtil.isTagInList(TagNum.SymbolSfx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityID, securityID);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityMonthYear, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityDay, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaturityDay, maturityDay);
            }
            if (putOrCall != null && MsgUtil.isTagInList(TagNum.PutOrCall, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PutOrCall, putOrCall.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.StrikePrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            }
            if (MsgUtil.isTagInList(TagNum.OptAttribute, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            }
            if (MsgUtil.isTagInList(TagNum.ContractMultiplier, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContractMultiplier, contractMultiplier);
            }
            if (MsgUtil.isTagInList(TagNum.CouponRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CouponRate, couponRate);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityExchange, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange);
            }
            if (MsgUtil.isTagInList(TagNum.Issuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Issuer, issuer);
            }
            if (encodedIssuerLen != null && encodedIssuerLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedIssuerLen, SECURED_TAGS, secured)) {
                if (encodedIssuer != null && encodedIssuer.length > 0) {
                    encodedIssuerLen = new Integer(encodedIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedIssuerLen, encodedIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedIssuer, encodedIssuer);
                }
            }
            if (MsgUtil.isTagInList(TagNum.SecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            }
            if (encodedSecurityDescLen != null && encodedSecurityDescLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedSecurityDescLen, SECURED_TAGS, secured)) {
                if (encodedSecurityDesc != null && encodedSecurityDesc.length > 0) {
                    encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
                }
            }
            if (MsgUtil.isTagInList(TagNum.FinancialStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.FinancialStatus, financialStatus);
            }
            if (MsgUtil.isTagInList(TagNum.CorporateAction, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CorporateAction, corporateAction);
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
            if (MsgUtil.isTagInList(TagNum.TotalVolumeTraded, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.TotalVolumeTraded, totalVolumeTraded);
            }
            if (MsgUtil.isTagInList(TagNum.TotalVolumeTradedDate, SECURED_TAGS, secured)) {
                 TagEncoder.encodeUtcDate(bao, TagNum.TotalVolumeTradedDate, totalVolumeTradedDate);
            }
            if (MsgUtil.isTagInList(TagNum.TotalVolumeTradedTime, SECURED_TAGS, secured)) {
                 TagEncoder.encodeUtcDate(bao, TagNum.TotalVolumeTradedTime, totalVolumeTradedTime);
            }
            if (MsgUtil.isTagInList(TagNum.NetChgPrevDay, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.NetChgPrevDay, netChgPrevDay);
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
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedIssuerLen.getValue()) {
            try {
                encodedIssuerLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedIssuerLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedIssuerLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedIssuerLen.intValue());
            encodedIssuer = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncodedSecurityDescLen.getValue()) {
            try {
                encodedSecurityDescLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedSecurityDescLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedIssuerLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedSecurityDescLen.intValue());
            encodedSecurityDesc = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncodedTextLen.getValue()) {
            try {
                encodedTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }

        return result;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MDIncGroup] group version [4.2].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS_V42;
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
