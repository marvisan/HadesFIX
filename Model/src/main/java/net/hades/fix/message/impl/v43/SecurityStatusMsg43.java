/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusMsg43.java
 *
 * $Id: SecurityStatusMsg43.java,v 1.1 2011-04-21 09:45:44 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;


import net.hades.fix.message.SecurityStatusMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 SecurityStatusMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class SecurityStatusMsg43 extends SecurityStatusMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityStatusReqID.getValue(),
        TagNum.Currency.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.UnsolicitedIndicator.getValue(),
        TagNum.SecurityTradingStatus.getValue(),
        TagNum.SecurityTradingEvent.getValue(),
        TagNum.FinancialStatus.getValue(),
        TagNum.CorporateAction.getValue(),
        TagNum.HaltReason.getValue(),
        TagNum.InViewOfCommon.getValue(),
        TagNum.DueToRelated.getValue(),
        TagNum.BuyVolume.getValue(),
        TagNum.SellVolume.getValue(),
        TagNum.HighPx.getValue(),
        TagNum.LowPx.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.Adjustment.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V43;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityStatusMsg43() {
        super();
    }

    public SecurityStatusMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityStatusMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityStatusMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V43;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument43(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.SecurityStatusReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityStatusReqID, securityStatusReqID);
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (MsgUtil.isTagInList(TagNum.UnsolicitedIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
            }
            if (securityTradingStatus != null && MsgUtil.isTagInList(TagNum.SecurityTradingStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityTradingStatus, securityTradingStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.FinancialStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.FinancialStatus, financialStatus);
            }
            if (corporateAction != null && MsgUtil.isTagInList(TagNum.CorporateAction, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CorporateAction, corporateAction.getValue());
            }
            if (haltReason != null && MsgUtil.isTagInList(TagNum.HaltReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.HaltReason, haltReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.InViewOfCommon, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.InViewOfCommon, inViewOfCommon);
            }
            if (MsgUtil.isTagInList(TagNum.DueToRelated, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DueToRelated, dueToRelated);
            }
            if (MsgUtil.isTagInList(TagNum.BuyVolume, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BuyVolume, buyVolume);
            }
            if (MsgUtil.isTagInList(TagNum.SellVolume, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SellVolume, sellVolume);
            }
            if (MsgUtil.isTagInList(TagNum.HighPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.HighPx, highPx);
            }
            if (MsgUtil.isTagInList(TagNum.LowPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LowPx, lowPx);
            }
            if (MsgUtil.isTagInList(TagNum.LastPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (adjustment != null && MsgUtil.isTagInList(TagNum.Adjustment, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Adjustment, adjustment.getValue());
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
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityStatusMsg] message version [4.3].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
