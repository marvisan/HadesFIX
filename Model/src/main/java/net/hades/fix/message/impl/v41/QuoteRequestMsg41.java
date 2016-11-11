/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg41.java
 *
 * $Id: QuoteRequestMsg41.java,v 1.8 2010-08-25 05:30:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41;

import net.hades.fix.message.Header;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.1 QuoteRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 05/04/2009, 10:17:46 AM
 */
public class QuoteRequestMsg41 extends QuoteRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V41 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteReqID.getValue(),
        TagNum.RFQReqID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.BookingType.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.PrivateQuote.getValue(),
        TagNum.RespondentType.getValue(),
        TagNum.PreTradeAnonymity.getValue(),
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
        TagNum.SecurityExchange.getValue(),
        TagNum.Issuer.getValue(),
        TagNum.SecurityDesc.getValue(),
        TagNum.PrevClosePx.getValue(),
        TagNum.Side.getValue(),
        TagNum.OrderQty.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.SettlDate2.getValue(),
        TagNum.OrderQty2.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V41);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public QuoteRequestMsg41() {
        super();
    }

    public QuoteRequestMsg41(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public QuoteRequestMsg41(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public QuoteRequestMsg41(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V41;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getQuoteReqID() {
        return quoteReqID;
    }

    @Override
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
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
    public String getSecurityDesc() {
        return securityDesc;
    }

    @Override
    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
    }

    @Override
    public Double getPrevClosePx() {
        return prevClosePx;
    }

    @Override
    public void setPrevClosePx(Double prevClosePx) {
        this.prevClosePx = prevClosePx;
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public Double getOrderQty() {
        return orderQty;
    }

    @Override
    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;

        if (quoteReqID == null || quoteReqID.trim().isEmpty()) {
            errorMsg.append(" [QuoteReqID]");
            hasMissingTag = true;
        }
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
            if (MsgUtil.isTagInList(TagNum.QuoteReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteReqID, quoteReqID);
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
            if (MsgUtil.isTagInList(TagNum.SecurityExchange, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange);
            }
            if (MsgUtil.isTagInList(TagNum.Issuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Issuer, issuer);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc);
            }
            if (MsgUtil.isTagInList(TagNum.PrevClosePx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PrevClosePx, prevClosePx);
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OrderQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderQty, orderQty);
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
        return "This tag is not supported in [QuoteRequestMsg] message version [4.1].";
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
