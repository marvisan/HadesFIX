/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusReportMsg43.java
 *
 * $Id: QuoteStatusReportMsg43.java,v 1.10 2011-04-14 23:44:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.QuoteStatusReportMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.3 QuoteStatusReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:41:14 AM
 */
public class QuoteStatusReportMsg43 extends QuoteStatusReportMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5997926434654756096L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteStatusReportMsg43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }
    
    public QuoteStatusReportMsg43(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
 
    public QuoteStatusReportMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }
    
    public QuoteStatusReportMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getQuoteStatusReqID() {
        return quoteStatusReqID;
    }

    @Override
    public void setQuoteStatusReqID(String quoteStatusReqID) {
        this.quoteStatusReqID = quoteStatusReqID;
    }

    @Override
    public String getQuoteReqID() {
        return quoteReqID;
    }

    @Override
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    @Override
    public String getQuoteID() {
        return quoteID;
    }

    @Override
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    @Override
    public QuoteType getQuoteType() {
        return quoteType;
    }

    @Override
    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.parties = new Parties43(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        instrument = new Instrument43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
    }

    @Override
    public void clearInstrument() {
        instrument = null;
    }

    @Override
    public Double getBidPx() {
        return bidPx;
    }

    @Override
    public void setBidPx(Double bidPx) {
        this.bidPx = bidPx;
    }

    @Override
    public Double getOfferPx() {
        return offerPx;
    }

    @Override
    public void setOfferPx(Double offerPx) {
        this.offerPx = offerPx;
    }

    @Override
    public Double getMktBidPx() {
        return mktBidPx;
    }

    @Override
    public void setMktBidPx(Double mktBidPx) {
        this.mktBidPx = mktBidPx;
    }

    @Override
    public Double getMktOfferPx() {
        return mktOfferPx;
    }

    @Override
    public void setMktOfferPx(Double mktOfferPx) {
        this.mktOfferPx = mktOfferPx;
    }

    @Override
    public Double getMinBidSize() {
        return minBidSize;
    }

    @Override
    public void setMinBidSize(Double minBidSize) {
        this.minBidSize = minBidSize;
    }

    @Override
    public Double getBidSize() {
        return bidSize;
    }

    @Override
    public void setBidSize(Double bidSize) {
        this.bidSize = bidSize;
    }

    @Override
    public Double getMinOfferSize() {
        return minOfferSize;
    }

    @Override
    public void setMinOfferSize(Double minOfferSize) {
        this.minOfferSize = minOfferSize;
    }

    @Override
    public Double getOfferSize() {
        return offerSize;
    }

    @Override
    public void setOfferSize(Double offerSize) {
        this.offerSize = offerSize;
    }

    @Override
    public Date getValidUntilTime() {
        return validUntilTime;
    }

    @Override
    public void setValidUntilTime(Date validUntilTime) {
        this.validUntilTime = validUntilTime;
    }

    @Override
    public Double getBidSpotRate() {
        return bidSpotRate;
    }

    @Override
    public void setBidSpotRate(Double bidSpotRate) {
        this.bidSpotRate = bidSpotRate;
    }

    @Override
    public Double getOfferSpotRate() {
        return offerSpotRate;
    }

    @Override
    public void setOfferSpotRate(Double offerSpotRate) {
        this.offerSpotRate = offerSpotRate;
    }

    @Override
    public Double getBidForwardPoints() {
        return bidForwardPoints;
    }

    @Override
    public void setBidForwardPoints(Double bidForwardPoints) {
        this.bidForwardPoints = bidForwardPoints;
    }

    @Override
    public Double getOfferForwardPoints() {
        return offerForwardPoints;
    }

    @Override
    public void setOfferForwardPoints(Double offerForwardPoints) {
        this.offerForwardPoints = offerForwardPoints;
    }

    @Override
    public Double getMidPx() {
        return midPx;
    }

    @Override
    public void setMidPx(Double midPx) {
        this.midPx = midPx;
    }

    @Override
    public Double getBidYield() {
        return bidYield;
    }

    @Override
    public void setBidYield(Double bidYield) {
        this.bidYield = bidYield;
    }

    @Override
    public Double getMidYield() {
        return midYield;
    }

    @Override
    public void setMidYield(Double midYield) {
        this.midYield = midYield;
    }

    @Override
    public Double getOfferYield() {
        return offerYield;
    }

    @Override
    public void setOfferYield(Double offerYield) {
        this.offerYield = offerYield;
    }

    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @Override
    public Date getSettlDate() {
        return settlDate;
    }

    @Override
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    @Override
    public OrdType getOrdType() {
        return ordType;
    }

    @Override
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
    }

    @Override
    public Date getSettlDate2() {
        return settlDate2;
    }

    @Override
    public void setSettlDate2(Date settlDate2) {
        this.settlDate2 = settlDate2;
    }

    @Override
    public Double getOrderQty2() {
        return orderQty2;
    }

    @Override
    public void setOrderQty2(Double orderQty2) {
        this.orderQty2 = orderQty2;
    }

    @Override
    public Double getBidForwardPoints2() {
        return bidForwardPoints2;
    }

    @Override
    public void setBidForwardPoints2(Double bidForwardPoints2) {
        this.bidForwardPoints2 = bidForwardPoints2;
    }

    @Override
    public Double getOfferForwardPoints2() {
        return offerForwardPoints2;
    }

    @Override
    public void setOfferForwardPoints2(Double offerForwardPoints2) {
        this.offerForwardPoints2 = offerForwardPoints2;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public Double getSettlCurrBidFxRate() {
        return settlCurrBidFxRate;
    }

    @Override
    public void setSettlCurrBidFxRate(Double settlCurrBidFxRate) {
        this.settlCurrBidFxRate = settlCurrBidFxRate;
    }

    @Override
    public Double getSettlCurrOfferFxRate() {
        return settlCurrOfferFxRate;
    }

    @Override
    public void setSettlCurrOfferFxRate(Double settlCurrOfferFxRate) {
        this.settlCurrOfferFxRate = settlCurrOfferFxRate;
    }

    @Override
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    @Override
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }

    @Override
    public CommType getCommType() {
        return commType;
    }

    @Override
    public void setCommType(CommType commType) {
        this.commType = commType;
    }

    @Override
    public Double getCommission() {
        return commission;
    }

    @Override
    public void setCommission(Double commission) {
        this.commission = commission;
    }

    @Override
    public CustOrderCapacity getCustOrderCapacity() {
        return custOrderCapacity;
    }

    @Override
    public void setCustOrderCapacity(CustOrderCapacity custOrderCapacity) {
        this.custOrderCapacity = custOrderCapacity;
    }

    @Override
    public String getExDestination() {
        return exDestination;
    }

    @Override
    public void setExDestination(String exDestination) {
        this.exDestination = exDestination;
    }

    @Override
    public QuoteStatus getQuoteStatus() {
        return quoteStatus;
    }

    @Override
    public void setQuoteStatus(QuoteStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (quoteID == null || quoteID.trim().isEmpty()) {
            errorMsg.append(" [QuoteID]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol() == null) {
            errorMsg.append(" [Instrument]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.QuoteStatusReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteStatusReqID, quoteStatusReqID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteReqID, quoteReqID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            }
            if (quoteType != null && MsgUtil.isTagInList(TagNum.QuoteType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteType, quoteType.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.Account, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Account, account);
            }
            if (accountType != null && MsgUtil.isTagInList(TagNum.AccountType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.BidPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidPx, bidPx);
            }
            if (MsgUtil.isTagInList(TagNum.OfferPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OfferPx, offerPx);
            }
            if (MsgUtil.isTagInList(TagNum.MktBidPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MktBidPx, mktBidPx);
            }
            if (MsgUtil.isTagInList(TagNum.MktOfferPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MktOfferPx, mktOfferPx);
            }
            if (MsgUtil.isTagInList(TagNum.MinBidSize, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MinBidSize, minBidSize);
            }
            if (MsgUtil.isTagInList(TagNum.BidSize, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidSize, bidSize);
            }
            if (MsgUtil.isTagInList(TagNum.MinOfferSize, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MinOfferSize, minOfferSize);
            }
            if (MsgUtil.isTagInList(TagNum.OfferSize, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OfferSize, offerSize);
            }
            if (MsgUtil.isTagInList(TagNum.ValidUntilTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ValidUntilTime, validUntilTime);
            }
            if (MsgUtil.isTagInList(TagNum.BidSpotRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidSpotRate, bidSpotRate);
            }
            if (MsgUtil.isTagInList(TagNum.OfferSpotRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OfferSpotRate, offerSpotRate);
            }
            if (MsgUtil.isTagInList(TagNum.BidForwardPoints, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidForwardPoints, bidForwardPoints);
            }
            if (MsgUtil.isTagInList(TagNum.OfferForwardPoints, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OfferForwardPoints, offerForwardPoints);
            }
            if (MsgUtil.isTagInList(TagNum.MidPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MidPx, midPx);
            }
            if (MsgUtil.isTagInList(TagNum.BidYield, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidYield, bidYield);
            }
            if (MsgUtil.isTagInList(TagNum.MidYield, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MidYield, midYield);
            }
            if (MsgUtil.isTagInList(TagNum.OfferYield, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OfferYield, offerYield);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            }
            if (ordType != null && MsgUtil.isTagInList(TagNum.OrdType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate2, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate2, settlDate2);
            }
            if (MsgUtil.isTagInList(TagNum.OrderQty2, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderQty2, orderQty2);
            }
            if (MsgUtil.isTagInList(TagNum.BidForwardPoints2, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidForwardPoints2, bidForwardPoints2);
            }
            if (MsgUtil.isTagInList(TagNum.OfferForwardPoints2, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OfferForwardPoints2, offerForwardPoints2);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SettlCurrBidFxRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrBidFxRate, settlCurrBidFxRate);
            }
            if (MsgUtil.isTagInList(TagNum.SettlCurrOfferFxRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrOfferFxRate, settlCurrOfferFxRate);
            }
            if (settlCurrFxRateCalc != null && MsgUtil.isTagInList(TagNum.SettlCurrFxRateCalc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc.getValue());
            }
            if (commType != null && MsgUtil.isTagInList(TagNum.CommType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Commission, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Commission, commission);
            }
            if (custOrderCapacity != null && MsgUtil.isTagInList(TagNum.CustOrderCapacity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CustOrderCapacity, custOrderCapacity.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExDestination, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExDestination, exDestination);
            }
            if (quoteStatus != null && MsgUtil.isTagInList(TagNum.QuoteStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteStatus, quoteStatus.getValue());
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties43(context);
            }
            parties.decode(tag, message);
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteStatusReportMsg] message version [4.3].";
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
