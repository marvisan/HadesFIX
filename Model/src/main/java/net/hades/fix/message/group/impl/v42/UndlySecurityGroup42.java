/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlySecurityGroup42.java
 *
 * $Id: UndlySecurityGroup42.java,v 1.1 2011-04-16 07:38:27 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.UndlySecurityGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.util.TagEncoder;

/**
 *  The UndlySecurityGroup group for FIX version 4.2.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 16/12/2008, 7:28:06 PM
 */
public class UndlySecurityGroup42 extends UndlySecurityGroup {

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

    public UndlySecurityGroup42() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public UndlySecurityGroup42(FragmentContext context) {
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
    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    @Override
    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    @Override
    public String getUnderlyingSymbolSfx() {
        return underlyingSymbolSfx;
    }

    @Override
    public void setUnderlyingSymbolSfx(String underlyingSymbolSfx) {
        this.underlyingSymbolSfx = underlyingSymbolSfx;
    }

    @Override
    public String getUnderlyingSecurityID() {
        return underlyingSecurityID;
    }

    @Override
    public void setUnderlyingSecurityID(String underlyingSecurityID) {
        this.underlyingSecurityID = underlyingSecurityID;
    }

    @Override
    public String getUnderlyingSecurityIDSource() {
        return underlyingSecurityIDSource;
    }

    @Override
    public void setUnderlyingSecurityIDSource(String underlyingSecurityIDSource) {
        this.underlyingSecurityIDSource = underlyingSecurityIDSource;
    }

    @Override
    public String getUnderlyingMaturityMonthYear() {
        return underlyingMaturityMonthYear;
    }

    @Override
    public void setUnderlyingMaturityMonthYear(String underlyingMaturityMonthYear) {
        this.underlyingMaturityMonthYear = underlyingMaturityMonthYear;
    }

    @Override
    public Integer getUnderlyingMaturityDay() {
        return underlyingMaturityDay;
    }

    @Override
    public void setUnderlyingMaturityDay(Integer underlyingMaturityDay) {
        this.underlyingMaturityDay = underlyingMaturityDay;
    }

    @Override
    public PutOrCall getUnderlyingPutOrCall() {
        return underlyingPutOrCall;
    }

    @Override
    public void setUnderlyingPutOrCall(PutOrCall underlyingPutOrCall) {
        this.underlyingPutOrCall = underlyingPutOrCall;
    }

    @Override
    public String getUnderlyingSecurityType() {
        return underlyingSecurityType;
    }

    @Override
    public void setUnderlyingSecurityType(String underlyingSecurityType) {
        this.underlyingSecurityType = underlyingSecurityType;
    }

    @Override
    public Double getUnderlyingStrikePrice() {
        return underlyingStrikePrice;
    }

    @Override
    public void setUnderlyingStrikePrice(Double underlyingStrikePrice) {
        this.underlyingStrikePrice = underlyingStrikePrice;
    }

    @Override
    public Character getUnderlyingOptAttribute() {
        return underlyingOptAttribute;
    }

    @Override
    public void setUnderlyingOptAttribute(Character underlyingOptAttribute) {
        this.underlyingOptAttribute = underlyingOptAttribute;
    }

    @Override
    public Double getUnderlyingContractMultiplier() {
        return underlyingContractMultiplier;
    }

    @Override
    public void setUnderlyingContractMultiplier(Double underlyingContractMultiplier) {
        this.underlyingContractMultiplier = underlyingContractMultiplier;
    }

    @Override
    public Double getUnderlyingCouponRate() {
        return underlyingCouponRate;
    }

    @Override
    public void setUnderlyingCouponRate(Double underlyingCouponRate) {
        this.underlyingCouponRate = underlyingCouponRate;
    }

    @Override
    public String getUnderlyingSecurityExchange() {
        return underlyingSecurityExchange;
    }

    @Override
    public void setUnderlyingSecurityExchange(String underlyingSecurityExchange) {
        this.underlyingSecurityExchange = underlyingSecurityExchange;
    }

    @Override
    public String getUnderlyingIssuer() {
        return underlyingIssuer;
    }

    @Override
    public void setUnderlyingIssuer(String underlyingIssuer) {
        this.underlyingIssuer = underlyingIssuer;
    }

    @Override
    public Integer getEncodedUnderlyingIssuerLen() {
        return encodedUnderlyingIssuerLen;
    }

    @Override
    public void setEncodedUnderlyingIssuerLen(Integer encodedUnderlyingIssuerLen) {
        this.encodedUnderlyingIssuerLen = encodedUnderlyingIssuerLen;
    }

    @Override
    public byte[] getEncodedUnderlyingIssuer() {
        return encodedUnderlyingIssuer;
    }

    @Override
    public void setEncodedUnderlyingIssuer(byte[] encodedUnderlyingIssuer) {
        this.encodedUnderlyingIssuer = encodedUnderlyingIssuer;
        if (encodedUnderlyingIssuerLen == null) {
            encodedUnderlyingIssuerLen = new Integer(encodedUnderlyingIssuer.length);
        }
    }

    @Override
    public String getUnderlyingSecurityDesc() {
        return underlyingSecurityDesc;
    }

    @Override
    public void setUnderlyingSecurityDesc(String underlyingSecurityDesc) {
        this.underlyingSecurityDesc = underlyingSecurityDesc;
    }

    @Override
    public Integer getEncodedUnderlyingSecurityDescLen() {
        return encodedUnderlyingSecurityDescLen;
    }

    @Override
    public void setEncodedUnderlyingSecurityDescLen(Integer encodedUnderlyingSecurityDescLen) {
        this.encodedUnderlyingSecurityDescLen = encodedUnderlyingSecurityDescLen;
    }

    @Override
    public Double getRatioQty() {
        return ratioQty;
    }

    @Override
    public void setRatioQty(Double ratioQty) {
        this.ratioQty = ratioQty;
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
    public byte[] getEncodedUnderlyingSecurityDesc() {
        return encodedUnderlyingSecurityDesc;
    }

    @Override
    public void setEncodedUnderlyingSecurityDesc(byte[] encodedUnderlyingSecurityDesc) {
        this.encodedUnderlyingSecurityDesc = encodedUnderlyingSecurityDesc;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.UnderlyingSymbol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSymbol, underlyingSymbol);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSymbolSfx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSymbolSfx, underlyingSymbolSfx);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityID, underlyingSecurityID);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityIDSource, underlyingSecurityIDSource);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingMaturityMonthYear, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingMaturityMonthYear, underlyingMaturityMonthYear);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingMaturityDay, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingMaturityDay, underlyingMaturityDay);
            }
            if (underlyingPutOrCall != null && MsgUtil.isTagInList(TagNum.UnderlyingPutOrCall, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingPutOrCall, underlyingPutOrCall.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityType, underlyingSecurityType);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingStrikePrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingStrikePrice, underlyingStrikePrice);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingOptAttribute, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingOptAttribute, underlyingOptAttribute);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingContractMultiplier, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingContractMultiplier, underlyingContractMultiplier);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingCouponRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingCouponRate, underlyingCouponRate);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityExchange, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityExchange, underlyingSecurityExchange);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingIssuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingIssuer, underlyingIssuer, sessionCharset);
            }
            if (encodedUnderlyingIssuerLen != null && encodedUnderlyingIssuerLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedUnderlyingIssuerLen, SECURED_TAGS, secured)) {
                if (encodedUnderlyingIssuer != null && encodedUnderlyingIssuer.length > 0) {
                    encodedUnderlyingIssuerLen = new Integer(encodedUnderlyingIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingIssuerLen, encodedUnderlyingIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingIssuer, encodedUnderlyingIssuer);
                }
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityDesc, underlyingSecurityDesc, sessionCharset);
            }
            if (encodedUnderlyingSecurityDescLen != null && encodedUnderlyingSecurityDescLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedUnderlyingSecurityDescLen, SECURED_TAGS, secured)) {
                if (encodedUnderlyingSecurityDesc != null && encodedUnderlyingSecurityDesc.length > 0) {
                    encodedUnderlyingSecurityDescLen = new Integer(encodedUnderlyingSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingSecurityDescLen, encodedUnderlyingSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingSecurityDesc, encodedUnderlyingSecurityDesc);
                }
            }
            if (MsgUtil.isTagInList(TagNum.RatioQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RatioQty, ratioQty);
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (underlyingCurrency != null && MsgUtil.isTagInList(TagNum.UnderlyingCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingCurrency, underlyingCurrency.getValue());
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
        return "This tag is not supported in [UndlySecurityGroup] component version [4.2].";
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
