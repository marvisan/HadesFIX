/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteEntryGroup43.java
 *
 * $Id: QuoteEntryGroup43.java,v 1.9 2011-04-14 23:44:47 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.QuoteEntryGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX 4.3 implementation of QuoteEntryGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 12/02/2009, 7:22:35 PM
 */
public class QuoteEntryGroup43 extends QuoteEntryGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5516218214328467733L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteEntryGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteEntryGroup43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;  
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
    public String getQuoteEntryID() {
        return quoteEntryID;
    }

    @Override
    public void setQuoteEntryID(String quoteEntryID) {
        this.quoteEntryID = quoteEntryID;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    @Override
    public void clearInstrument() {
        instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
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
    public Double getBidSize() {
        return bidSize;
    }

    @Override
    public void setBidSize(Double bidSize) {
        this.bidSize = bidSize;
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
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.QuoteEntryID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteEntryID, quoteEntryID);
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
            if (MsgUtil.isTagInList(TagNum.BidSize, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidSize, bidSize);
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
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
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
        return "This tag is not supported in [QuoteEntryGroup] group version [4.3].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
