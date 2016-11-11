/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlySecurityGroup.java
 *
 * $Id: UndlySecurityGroup.java,v 1.1 2011-04-16 07:38:26 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The Underlying security used in security definition.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 16/12/2008, 7:24:31 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class UndlySecurityGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UnderlyingSymbol.getValue(),
        TagNum.UnderlyingSymbolSfx.getValue(),
        TagNum.UnderlyingSecurityID.getValue(),
        TagNum.UnderlyingSecurityIDSource.getValue(),
        TagNum.UnderlyingSecurityType.getValue(),
        TagNum.UnderlyingMaturityMonthYear.getValue(),
        TagNum.UnderlyingMaturityDay.getValue(),
        TagNum.UnderlyingPutOrCall.getValue(),
        TagNum.UnderlyingStrikePrice.getValue(),
        TagNum.UnderlyingOptAttribute.getValue(),
        TagNum.UnderlyingContractMultiplier.getValue(),
        TagNum.UnderlyingCouponRate.getValue(),
        TagNum.UnderlyingSecurityExchange.getValue(),
        TagNum.UnderlyingIssuer.getValue(),
        TagNum.UnderlyingSecurityDesc.getValue(),
        TagNum.RatioQty.getValue(),
        TagNum.Side.getValue(),
        TagNum.UnderlyingCurrency.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedUnderlyingIssuerLen.getValue(),
        TagNum.EncodedUnderlyingSecurityDescLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UnderlyingSymbol.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 311. Starting with 4.2 version.
     */
    protected String underlyingSymbol;

    /**
     * TagNum = 312. Starting with 4.2 version.
     */
    protected String underlyingSymbolSfx;

    /**
     * TagNum = 309. Starting with 4.2 version.
     */
    protected String underlyingSecurityID;

    /**
     * TagNum = 305. Starting with 4.2 version.
     */
    protected String underlyingSecurityIDSource;

    /**
     * TagNum = 310. Starting with 4.2 version.
     */
    protected String underlyingSecurityType;

    /**
     * TagNum = 313. Starting with 4.2 version.
     */
    protected String underlyingMaturityMonthYear;

    /**
     * TagNum = 542. Starting with 4.2 version.
     */
    protected Integer underlyingMaturityDay;

    /**
     * TagNum = 1213. Starting with 5.0SP1 version.
     */
    protected PutOrCall underlyingPutOrCall;

    /**
     * TagNum = 316. Starting with 4.2 version.
     */
    protected Double underlyingStrikePrice;

    /**
     * TagNum = 317. Starting with 4.2 version.
     */
    protected Character underlyingOptAttribute;

    /**
     * TagNum = 436. Starting with 4.2 version.
     */
    protected Double underlyingContractMultiplier;

    /**
     * TagNum = 435. Starting with 4.2 version.
     */
    protected Double underlyingCouponRate;

    /**
     * TagNum = 308. Starting with 4.2 version.
     */
    protected String underlyingSecurityExchange;

    /**
     * TagNum = 306. Starting with 4.2 version.
     */
    protected String underlyingIssuer;

    /**
     * TagNum = 362. Starting with 4.2 version.
     */
    protected Integer encodedUnderlyingIssuerLen;

    /**
     * TagNum = 363. Starting with 4.2 version.
     */
    protected byte[] encodedUnderlyingIssuer;

    /**
     * TagNum = 307. Starting with 4.2 version.
     */
    protected String underlyingSecurityDesc;

    /**
     * TagNum = 364. Starting with 4.2 version.
     */
    protected Integer encodedUnderlyingSecurityDescLen;

    /**
     * TagNum = 365. Starting with 4.2 version.
     */
    protected byte[] encodedUnderlyingSecurityDesc;

    /**
     * TagNum = 319. Starting with 4.2 version.
     */
    protected Double ratioQty;

    /**
     * TagNum = 54. Starting with 4.2 version.
     */
    protected Side side;

    /**
     * TagNum = 318. Starting with 4.2 version.
     */
    protected Currency underlyingCurrency;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UndlySecurityGroup() {
    }

    public UndlySecurityGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbol, required=true)
    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    /**
     * Message field setter.
     * @param underlyingSymbol field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbol, required=true)
    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbolSfx)
    public String getUnderlyingSymbolSfx() {
        return underlyingSymbolSfx;
    }

    /**
     * Message field setter.
     * @param underlyingSymbolSfx field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbolSfx)
    public void setUnderlyingSymbolSfx(String underlyingSymbolSfx) {
        this.underlyingSymbolSfx = underlyingSymbolSfx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityID)
    public String getUnderlyingSecurityID() {
        return underlyingSecurityID;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityID field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityID)
    public void setUnderlyingSecurityID(String underlyingSecurityID) {
        this.underlyingSecurityID = underlyingSecurityID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityIDSource)
    public String getUnderlyingSecurityIDSource() {
        return underlyingSecurityIDSource;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityIDSource field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityIDSource)
    public void setUnderlyingSecurityIDSource(String underlyingSecurityIDSource) {
        this.underlyingSecurityIDSource = underlyingSecurityIDSource;
    }
 
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityMonthYear)
    public String getUnderlyingMaturityMonthYear() {
        return underlyingMaturityMonthYear;
    }

    /**
     * Message field setter.
     * @param underlyingMaturityMonthYear field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityMonthYear)
    public void setUnderlyingMaturityMonthYear(String underlyingMaturityMonthYear) {
        this.underlyingMaturityMonthYear = underlyingMaturityMonthYear;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityDay)
    public Integer getUnderlyingMaturityDay() {
        return underlyingMaturityDay;
    }

    /**
     * Message field setter.
     * @param underlyingMaturityDay field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityDay)
    public void setUnderlyingMaturityDay(Integer underlyingMaturityDay) {
        this.underlyingMaturityDay = underlyingMaturityDay;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingPutOrCall)
    public PutOrCall getUnderlyingPutOrCall() {
        return underlyingPutOrCall;
    }

    /**
     * Message field setter.
     * @param underlyingPutOrCall field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingPutOrCall)
    public void setUnderlyingPutOrCall(PutOrCall underlyingPutOrCall) {
        this.underlyingPutOrCall = underlyingPutOrCall;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityType)
    public String getUnderlyingSecurityType() {
        return underlyingSecurityType;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityType field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityType)
    public void setUnderlyingSecurityType(String underlyingSecurityType) {
        this.underlyingSecurityType = underlyingSecurityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingStrikePrice)
    public Double getUnderlyingStrikePrice() {
        return underlyingStrikePrice;
    }

    /**
     * Message field setter.
     * @param underlyingStrikePrice field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingStrikePrice)
    public void setUnderlyingStrikePrice(Double underlyingStrikePrice) {
        this.underlyingStrikePrice = underlyingStrikePrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingOptAttribute)
    public Character getUnderlyingOptAttribute() {
        return underlyingOptAttribute;
    }

    /**
     * Message field setter.
     * @param underlyingOptAttribute field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingOptAttribute)
    public void setUnderlyingOptAttribute(Character underlyingOptAttribute) {
        this.underlyingOptAttribute = underlyingOptAttribute;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingContractMultiplier)
    public Double getUnderlyingContractMultiplier() {
        return underlyingContractMultiplier;
    }

    /**
     * Message field setter.
     * @param underlyingContractMultiplier field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingContractMultiplier)
    public void setUnderlyingContractMultiplier(Double underlyingContractMultiplier) {
        this.underlyingContractMultiplier = underlyingContractMultiplier;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingCouponRate)
    public Double getUnderlyingCouponRate() {
        return underlyingCouponRate;
    }

    /**
     * Message field setter.
     * @param underlyingCouponRate field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingCouponRate)
    public void setUnderlyingCouponRate(Double underlyingCouponRate) {
        this.underlyingCouponRate = underlyingCouponRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityExchange)
    public String getUnderlyingSecurityExchange() {
        return underlyingSecurityExchange;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityExchange field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityExchange)
    public void setUnderlyingSecurityExchange(String underlyingSecurityExchange) {
        this.underlyingSecurityExchange = underlyingSecurityExchange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingIssuer)
    public String getUnderlyingIssuer() {
        return underlyingIssuer;
    }

    /**
     * Message field setter.
     * @param underlyingIssuer field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingIssuer)
    public void setUnderlyingIssuer(String underlyingIssuer) {
        this.underlyingIssuer = underlyingIssuer;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuerLen)
    public Integer getEncodedUnderlyingIssuerLen() {
        return encodedUnderlyingIssuerLen;
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingIssuerLen field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuerLen)
    public void setEncodedUnderlyingIssuerLen(Integer encodedUnderlyingIssuerLen) {
        this.encodedUnderlyingIssuerLen = encodedUnderlyingIssuerLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuer)
    public byte[] getEncodedUnderlyingIssuer() {
        return encodedUnderlyingIssuer;
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingIssuer field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuer)
    public void setEncodedUnderlyingIssuer(byte[] encodedUnderlyingIssuer) {
        this.encodedUnderlyingIssuer = encodedUnderlyingIssuer;
        if (encodedUnderlyingIssuerLen == null) {
            encodedUnderlyingIssuerLen = new Integer(encodedUnderlyingIssuer.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityDesc)
    public String getUnderlyingSecurityDesc() {
        return underlyingSecurityDesc;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityDesc field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityDesc)
    public void setUnderlyingSecurityDesc(String underlyingSecurityDesc) {
        this.underlyingSecurityDesc = underlyingSecurityDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDescLen)
    public Integer getEncodedUnderlyingSecurityDescLen() {
        return encodedUnderlyingSecurityDescLen;
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingSecurityDescLen field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDescLen)
    public void setEncodedUnderlyingSecurityDescLen(Integer encodedUnderlyingSecurityDescLen) {
        this.encodedUnderlyingSecurityDescLen = encodedUnderlyingSecurityDescLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDesc)
    public byte[] getEncodedUnderlyingSecurityDesc() {
        return encodedUnderlyingSecurityDesc;
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingSecurityDesc field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDesc)
    public void setEncodedUnderlyingSecurityDesc(byte[] encodedUnderlyingSecurityDesc) {
        this.encodedUnderlyingSecurityDesc = encodedUnderlyingSecurityDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.RatioQty)
    public Double getRatioQty() {
        return ratioQty;
    }

    /**
     * Message field setter.
     * @param ratioQty field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.RatioQty)
    public void setRatioQty(Double ratioQty) {
        this.ratioQty = ratioQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.Side)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.Side)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingCurrency)
    public Currency getUnderlyingCurrency() {
        return underlyingCurrency;
    }

    /**
     * Message field setter.
     * @param underlyingCurrency field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingCurrency)
    public void setUnderlyingCurrency(Currency underlyingCurrency) {
        this.underlyingCurrency = underlyingCurrency;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.UnderlyingSymbol.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (underlyingSymbol == null) {
            errorMsg.append(" [UnderlyingSymbol]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            TagEncoder.encode(bao, TagNum.UnderlyingSymbol, underlyingSymbol);
            TagEncoder.encode(bao, TagNum.UnderlyingSymbolSfx, underlyingSymbolSfx);
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityID, underlyingSecurityID);
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityIDSource, underlyingSecurityIDSource);
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityType, underlyingSecurityType);
            TagEncoder.encode(bao, TagNum.UnderlyingMaturityMonthYear, underlyingMaturityMonthYear);
            TagEncoder.encode(bao, TagNum.UnderlyingMaturityDay, underlyingMaturityDay);
            if (underlyingPutOrCall != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingPutOrCall, underlyingPutOrCall.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingStrikePrice, underlyingStrikePrice);
            TagEncoder.encode(bao, TagNum.UnderlyingOptAttribute, underlyingOptAttribute);
            TagEncoder.encode(bao, TagNum.UnderlyingContractMultiplier, underlyingContractMultiplier);
            TagEncoder.encode(bao, TagNum.UnderlyingCouponRate, underlyingCouponRate);
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityExchange, underlyingSecurityExchange);
            TagEncoder.encode(bao, TagNum.UnderlyingIssuer, underlyingIssuer, sessionCharset);
            if (encodedUnderlyingIssuerLen != null && encodedUnderlyingIssuerLen.intValue() > 0) {
                if (encodedUnderlyingIssuer != null && encodedUnderlyingIssuer.length > 0) {
                    encodedUnderlyingIssuerLen = new Integer(encodedUnderlyingIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingIssuerLen, encodedUnderlyingIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingIssuer, encodedUnderlyingIssuer);
                }
            }
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityDesc, underlyingSecurityDesc, sessionCharset);
            if (encodedUnderlyingSecurityDescLen != null && encodedUnderlyingSecurityDescLen.intValue() > 0) {
                if (encodedUnderlyingSecurityDesc != null && encodedUnderlyingSecurityDesc.length > 0) {
                    encodedUnderlyingSecurityDescLen = new Integer(encodedUnderlyingSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingSecurityDescLen, encodedUnderlyingSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingSecurityDesc, encodedUnderlyingSecurityDesc);
                }
            }
            TagEncoder.encode(bao, TagNum.RatioQty, ratioQty);
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (underlyingCurrency != null) {
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case UnderlyingSymbol:
                underlyingSymbol = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSymbolSfx:
                underlyingSymbolSfx = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSecurityID:
                underlyingSecurityID = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSecurityIDSource:
                underlyingSecurityIDSource = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSecurityType:
                underlyingSecurityType = new String(tag.value, sessionCharset);
                break;

            case UnderlyingMaturityMonthYear:
                underlyingMaturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case UnderlyingMaturityDay:
                underlyingMaturityDay = Integer.valueOf(new String(tag.value, sessionCharset));
                break;

            case UnderlyingPutOrCall:
                underlyingPutOrCall = PutOrCall.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case UnderlyingStrikePrice:
                underlyingStrikePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingOptAttribute:
                underlyingOptAttribute = new Character(new String(tag.value, sessionCharset).charAt(0));
                break;

            case UnderlyingContractMultiplier:
                underlyingContractMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingCouponRate:
                underlyingCouponRate = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSecurityExchange:
                underlyingSecurityExchange = new String(tag.value, sessionCharset);
                break;

            case UnderlyingIssuer:
                underlyingIssuer = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSecurityDesc:
                underlyingSecurityDesc = new String(tag.value, sessionCharset);
                break;

            case RatioQty:
                ratioQty = new Double(new String(tag.value, sessionCharset));
                break;

            case Side:
                side = Side.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case UnderlyingCurrency:
                underlyingCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [UnderlyingInstrument] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedUnderlyingIssuerLen.getValue()) {
            try {
                encodedUnderlyingIssuerLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedUnderlyingIssuerLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedUnderlyingIssuerLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedUnderlyingIssuerLen.intValue());
            encodedUnderlyingIssuer = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncodedUnderlyingSecurityDescLen.getValue()) {
            try {
                encodedUnderlyingSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedUnderlyingSecurityDescLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedUnderlyingSecurityDescLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedUnderlyingSecurityDescLen.intValue());
            encodedUnderlyingSecurityDesc = dataTag.value;
        }

        return result;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{UndlySecurityGroup=");
        printTagValue(b, TagNum.UnderlyingSymbol, underlyingSymbol);
        printTagValue(b, TagNum.UnderlyingSymbolSfx, underlyingSymbolSfx);
        printTagValue(b, TagNum.UnderlyingSecurityID, underlyingSecurityID);
        printTagValue(b, TagNum.UnderlyingSecurityIDSource, underlyingSecurityIDSource);
        printTagValue(b, TagNum.UnderlyingSecurityType, underlyingSecurityType);
        printTagValue(b, TagNum.UnderlyingMaturityMonthYear, underlyingMaturityMonthYear);
        printTagValue(b, TagNum.UnderlyingMaturityDay, underlyingMaturityDay);
        printTagValue(b, TagNum.UnderlyingPutOrCall, underlyingPutOrCall);
        printTagValue(b, TagNum.UnderlyingStrikePrice, underlyingStrikePrice);
        printTagValue(b, TagNum.UnderlyingOptAttribute, underlyingOptAttribute);
        printTagValue(b, TagNum.UnderlyingContractMultiplier, underlyingContractMultiplier);
        printTagValue(b, TagNum.UnderlyingCouponRate, underlyingCouponRate);
        printTagValue(b, TagNum.UnderlyingSecurityExchange, underlyingSecurityExchange);
        printTagValue(b, TagNum.UnderlyingIssuer, underlyingIssuer);
        printTagValue(b, TagNum.EncodedUnderlyingIssuerLen, encodedUnderlyingIssuerLen);
        printTagValue(b, TagNum.EncodedUnderlyingIssuer, encodedUnderlyingIssuer);
        printTagValue(b, TagNum.UnderlyingSecurityDesc, underlyingSecurityDesc);
        printTagValue(b, TagNum.EncodedUnderlyingSecurityDescLen, encodedUnderlyingSecurityDescLen);
        printTagValue(b, TagNum.EncodedUnderlyingSecurityDesc, encodedUnderlyingSecurityDesc);
        printTagValue(b, TagNum.RatioQty, ratioQty);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.UnderlyingCurrency, underlyingCurrency);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
