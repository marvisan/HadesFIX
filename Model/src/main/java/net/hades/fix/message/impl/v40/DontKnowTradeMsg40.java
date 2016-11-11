/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DontKnowTradeMsg40.java
 *
 * $Id: DontKnowTradeMsg40.java,v 1.2 2011-01-16 00:47:41 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;

import net.hades.fix.message.DontKnowTradeMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.0 DontKnowTradeMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class DontKnowTradeMsg40 extends DontKnowTradeMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V40 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.DKReason.getValue(),
        TagNum.Symbol.getValue(),
        TagNum.Issuer.getValue(),
        TagNum.SecurityDesc.getValue(),
        TagNum.Side.getValue(),
        TagNum.OrderQty.getValue(),
        TagNum.CashOrderQty.getValue(),
        TagNum.LastQty.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.Text.getValue(),
    }));

    protected static final Set<Integer> START_DATA_TAGS_V40 = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V40);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V40;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DontKnowTradeMsg40() {
        super();
    }

    public DontKnowTradeMsg40(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DontKnowTradeMsg40(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DontKnowTradeMsg40(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V40;
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

    @Override
    public Double getOrderQty() {
        return orderQty;
    }

    @Override
    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public Double getCashOrderQty() {
        return cashOrderQty;
    }

    @Override
    public void setCashOrderQty(Double cashOrderQty) {
        this.cashOrderQty = cashOrderQty;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (orderID == null || orderID.trim().isEmpty()) {
            errorMsg.append(" [OrderID]");
            hasMissingTag = true;
        }
        if (execID == null || execID.trim().isEmpty()) {
            errorMsg.append(" [ExecID]");
            hasMissingTag = true;
        }
        if (DKReason == null) {
            errorMsg.append(" [DKReason]");
            hasMissingTag = true;
        }
        if (symbol == null || symbol.trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        if (orderQty == null) {
            errorMsg.append(" [OrderQty]");
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
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryOrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecID, execID);
            }
            if (DKReason != null && MsgUtil.isTagInList(TagNum.DKReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DKReason, DKReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Symbol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Symbol, symbol);
            }
             if (MsgUtil.isTagInList(TagNum.Issuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Issuer, issuer);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OrderQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderQty, orderQty);
            }
            if (MsgUtil.isTagInList(TagNum.CashOrderQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashOrderQty, cashOrderQty);
            }
            if (MsgUtil.isTagInList(TagNum.LastQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastQty, lastQty);
            }
            if (MsgUtil.isTagInList(TagNum.LastPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
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
        return message;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DontKnowTradeMsg] message version [4.0].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS_V40;
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
