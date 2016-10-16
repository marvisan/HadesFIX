/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteMsg43.java
 *
 * $Id: QuoteMsg43.java,v 1.10 2011-04-14 23:44:34 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import net.hades.fix.message.Header;
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.3 QuoteMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class QuoteMsg43 extends QuoteMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public QuoteMsg43() {
        super();
    }

    public QuoteMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    public QuoteMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    public QuoteMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        parties = null;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument43(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.QuoteID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteReqID, quoteReqID);
            }
            if (quoteType != null && MsgUtil.isTagInList(TagNum.QuoteType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteType, quoteType.getValue());
            }
            if (quoteResponseLevel != null && MsgUtil.isTagInList(TagNum.QuoteResponseLevel, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteResponseLevel, quoteResponseLevel.getValue());
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
            if (MsgUtil.isTagInList(TagNum.SettlType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlType, settlType);
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
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties43(context);
            }
            parties.decode(tag, message);
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteMsg] message version [4.3].";
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
