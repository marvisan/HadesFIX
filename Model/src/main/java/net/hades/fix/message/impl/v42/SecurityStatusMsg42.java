/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusMsg42.java
 *
 * $Id: SecurityStatusMsg42.java,v 1.1 2011-04-21 09:45:46 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.SecurityStatusMsg;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.2 SecurityStatusMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class SecurityStatusMsg42 extends SecurityStatusMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityStatusReqID.getValue(),
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
        TagNum.Currency.getValue(),
        TagNum.TradingSessionID.getValue(),
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
    }));

    protected static final Set<Integer> START_DATA_TAGS_V42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedIssuerLen.getValue(),
        TagNum.EncodedSecurityDescLen.getValue()
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

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V42;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityStatusMsg42() {
        super();
    }

    public SecurityStatusMsg42(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityStatusMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityStatusMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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

    // ACCESSOR METHODS
    //////////////////////////////////////////

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
    public Character getOptAttribute() {
        return optAttribute;
    }

    @Override
    public void setOptAttribute(Character optAttribute) {
        this.optAttribute = optAttribute;
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (symbol == null || symbol.trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
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
            if (MsgUtil.isTagInList(TagNum.SecurityStatusReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityStatusReqID, securityStatusReqID);
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
            if (securityType != null && MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
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
            if (securityDesc != null && !securityDesc.trim().isEmpty() && MsgUtil.isTagInList(TagNum.SecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            }
            if (encodedSecurityDescLen != null && encodedSecurityDescLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedSecurityDescLen, SECURED_TAGS, secured)) {
                if (encodedSecurityDesc != null && encodedSecurityDesc.length > 0) {
                    encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
                }
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
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

            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedIssuerLen.getValue(), error);
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedSecurityDescLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedSecurityDescLen.intValue());
            encodedSecurityDesc = dataTag.value;
        }

        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityStatusMsg] message version [4.2].";
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
