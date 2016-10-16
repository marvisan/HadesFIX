/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetAckGroup.java
 *
 * $Id: QuoteSetAckGroup.java,v 1.8 2010-11-23 10:20:17 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PutOrCall;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * QuoteSetAck group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class QuoteSetAckGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteSetID.getValue(),
        TagNum.QuoteSetValidUntilTime.getValue(),
        TagNum.TotNoQuoteEntries.getValue(),
        TagNum.TotNoCxldQuotes.getValue(),
        TagNum.TotNoAccQuotes.getValue(),
        TagNum.TotNoRejQuotes.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoQuoteEntries.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 302. Starting with 4.2 version.
     */
    protected String quoteSetID;

    /**
     * Starting with 4.3 version.
     */
    protected UnderlyingInstrument underlyingInstrument;

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
     * TagNum = 314. Starting with 4.2 version.
     */
    protected Integer underlyingMaturityDay;

    /**
     * TagNum = 315. Starting with 4.2 version.
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
     * TagNum = 367. Starting with 5.0SP2 version.
     */
    protected Date quoteSetValidUntilTime;

    /**
     * TagNum = 304. Starting with 4.2 version.
     */
    protected Integer totNoQuoteEntries;

    /**
     * TagNum = 1168. Starting with 5.0SP1 version.
     */
    protected Integer totNoCxldQuotes;

    /**
     * TagNum = 1169. Starting with 5.0SP1 version.
     */
    protected Integer totNoAccQuotes;

    /**
     * TagNum = 1170. Starting with 5.0SP1 version.
     */
    protected Integer totNoRejQuotes;

    /**
     * TagNum = 893. Starting with 4.4 version.
     */
    protected Boolean lastFragment;

    /**
     * TagNum = 295 REQUIRED. Starting with 4.2 version.
     */
    protected Integer noQuoteEntries;

    /**
     * Starting with 4.2 version.
     */
    protected QuoteEntryAckGroup[] quoteEntryAckGroups;

    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteSetAckGroup() {
    }
    
    public QuoteSetAckGroup(FragmentContext context) {
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
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.QuoteSetID, required = true)
    public String getQuoteSetID() {
        return quoteSetID;
    }

    /**
     * Message field setter.
     * @param quoteSetID field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.QuoteSetID, required = true)
    public void setQuoteSetID(String quoteSetID) {
        this.quoteSetID = quoteSetID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public UnderlyingInstrument getUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced = "4.3")
    public void setUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null..
     */
    @FIXVersion(introduced = "4.3")
    public void clearUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbol, required=true, condRequired=true)
    public String getUnderlyingSymbol() {
        return getSafeUnderlyingInstrument().getUnderlyingSymbol();
    }

    /**
     * Message field setter.
     * @param underlyingSymbol field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbol, required=true, condRequired=true)
    public void setUnderlyingSymbol(String underlyingSymbol) {
        getSafeUnderlyingInstrument().setUnderlyingSymbol(underlyingSymbol);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbolSfx)
    public String getUnderlyingSymbolSfx() {
        return getSafeUnderlyingInstrument().getUnderlyingSymbolSfx();
    }

    /**
     * Message field setter.
     * @param underlyingSymbolSfx field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbolSfx)
    public void setUnderlyingSymbolSfx(String underlyingSymbolSfx) {
        getSafeUnderlyingInstrument().setUnderlyingSymbolSfx(underlyingSymbolSfx);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityID)
    public String getUnderlyingSecurityID() {
        return getSafeUnderlyingInstrument().getUnderlyingSecurityID();
    }

    /**
     * Message field setter.
     * @param underlyingSecurityID field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityID)
    public void setUnderlyingSecurityID(String underlyingSecurityID) {
        getSafeUnderlyingInstrument().setUnderlyingSecurityID(underlyingSecurityID);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityIDSource)
    public String getUnderlyingSecurityIDSource() {
        return getSafeUnderlyingInstrument().getUnderlyingSecurityIDSource();
    }

    /**
     * Message field setter.
     * @param underlyingSecurityIDSource field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityIDSource)
    public void setUnderlyingSecurityIDSource(String underlyingSecurityIDSource) {
        getSafeUnderlyingInstrument().setUnderlyingSecurityIDSource(underlyingSecurityIDSource);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityType)
    public String getUnderlyingSecurityType() {
        return getSafeUnderlyingInstrument().getUnderlyingSecurityType();
    }

    /**
     * Message field setter.
     * @param underlyingSecurityType field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityType)
    public void setUnderlyingSecurityType(String underlyingSecurityType) {
        getSafeUnderlyingInstrument().setUnderlyingSecurityType(underlyingSecurityType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityMonthYear)
    public String getUnderlyingMaturityMonthYear() {
        return getSafeUnderlyingInstrument().getUnderlyingMaturityMonthYear();
    }

    /**
     * Message field setter.
     * @param underlyingMaturityMonthYear field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityMonthYear)
    public void setUnderlyingMaturityMonthYear(String underlyingMaturityMonthYear) {
        getSafeUnderlyingInstrument().setUnderlyingMaturityMonthYear(underlyingMaturityMonthYear);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityDay)
    public Integer getUnderlyingMaturityDay() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param underlyingMaturityDay field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityDay)
    public void setUnderlyingMaturityDay(Integer underlyingMaturityDay) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingPutOrCall)
    public PutOrCall getUnderlyingPutOrCall() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param underlyingPutOrCall field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingPutOrCall)
    public void setUnderlyingPutOrCall(PutOrCall underlyingPutOrCall) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingStrikePrice)
    public Double getUnderlyingStrikePrice() {
        return getSafeUnderlyingInstrument().getUnderlyingStrikePrice();
    }

    /**
     * Message field setter.
     * @param underlyingStrikePrice field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingStrikePrice)
    public void setUnderlyingStrikePrice(Double underlyingStrikePrice) {
        getSafeUnderlyingInstrument().setUnderlyingStrikePrice(underlyingStrikePrice);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingOptAttribute)
    public Character getUnderlyingOptAttribute() {
        return getSafeUnderlyingInstrument().getUnderlyingOptAttribute();
    }

    /**
     * Message field setter.
     * @param underlyingOptAttribute field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingOptAttribute)
    public void setUnderlyingOptAttribute(Character underlyingOptAttribute) {
        getSafeUnderlyingInstrument().setUnderlyingOptAttribute(underlyingOptAttribute);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingContractMultiplier)
    public Double getUnderlyingContractMultiplier() {
        return getSafeUnderlyingInstrument().getUnderlyingContractMultiplier();
    }

    /**
     * Message field setter.
     * @param underlyingContractMultiplier field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingContractMultiplier)
    public void setUnderlyingContractMultiplier(Double underlyingContractMultiplier) {
        getSafeUnderlyingInstrument().setUnderlyingContractMultiplier(underlyingContractMultiplier);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingCouponRate)
    public Double getUnderlyingCouponRate() {
        return getSafeUnderlyingInstrument().getUnderlyingCouponRate();
    }

    /**
     * Message field setter.
     * @param underlyingCouponRate field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingCouponRate)
    public void setUnderlyingCouponRate(Double underlyingCouponRate) {
        getSafeUnderlyingInstrument().setUnderlyingCouponRate(underlyingCouponRate);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityExchange)
    public String getUnderlyingSecurityExchange() {
        return getSafeUnderlyingInstrument().getUnderlyingSecurityExchange();
    }

    /**
     * Message field setter.
     * @param underlyingSecurityExchange field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityExchange)
    public void setUnderlyingSecurityExchange(String underlyingSecurityExchange) {
        getSafeUnderlyingInstrument().setUnderlyingSecurityExchange(underlyingSecurityExchange);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingIssuer)
    public String getUnderlyingIssuer() {
        return getSafeUnderlyingInstrument().getUnderlyingIssuer();
    }

    /**
     * Message field setter.
     * @param underlyingIssuer field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingIssuer)
    public void setUnderlyingIssuer(String underlyingIssuer) {
        getSafeUnderlyingInstrument().setUnderlyingIssuer(underlyingIssuer);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuerLen)
    public Integer getEncodedUnderlyingIssuerLen() {
        return getSafeUnderlyingInstrument().getEncodedUnderlyingIssuerLen();
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingIssuerLen field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuerLen)
    public void setEncodedUnderlyingIssuerLen(Integer encodedUnderlyingIssuerLen) {
        getSafeUnderlyingInstrument().setEncodedUnderlyingIssuerLen(encodedUnderlyingIssuerLen);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuer)
    public byte[] getEncodedUnderlyingIssuer() {
        return getSafeUnderlyingInstrument().getEncodedUnderlyingIssuer();
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingIssuer field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuer)
    public void setEncodedUnderlyingIssuer(byte[] encodedUnderlyingIssuer) {
        getSafeUnderlyingInstrument().setEncodedUnderlyingIssuer(encodedUnderlyingIssuer);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityDesc)
    public String getUnderlyingSecurityDesc() {
        return getSafeUnderlyingInstrument().getUnderlyingSecurityDesc();
    }

    /**
     * Message field setter.
     * @param underlyingSecurityDesc field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityDesc)
    public void setUnderlyingSecurityDesc(String underlyingSecurityDesc) {
        getSafeUnderlyingInstrument().setUnderlyingSecurityDesc(underlyingSecurityDesc);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDescLen)
    public Integer getEncodedUnderlyingSecurityDescLen() {
        return getSafeUnderlyingInstrument().getEncodedUnderlyingSecurityDescLen();
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingSecurityDescLen field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDescLen)
    public void setEncodedUnderlyingSecurityDescLen(Integer encodedUnderlyingSecurityDescLen) {
        getSafeUnderlyingInstrument().setEncodedUnderlyingSecurityDescLen(encodedUnderlyingSecurityDescLen);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDesc)
    public byte[] getEncodedUnderlyingSecurityDesc() {
        return getSafeUnderlyingInstrument().getEncodedUnderlyingSecurityDesc();
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingSecurityDesc field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDesc)
    public void setEncodedUnderlyingSecurityDesc(byte[] encodedUnderlyingSecurityDesc) {
        getSafeUnderlyingInstrument().setEncodedUnderlyingSecurityDesc(encodedUnderlyingSecurityDesc);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.QuoteSetValidUntilTime)
    public Date getQuoteSetValidUntilTime() {
        return quoteSetValidUntilTime;
    }

    /**
     * Message field setter.
     * @param quoteSetValidUntilTime field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.QuoteSetValidUntilTime)
    public void setQuoteSetValidUntilTime(Date quoteSetValidUntilTime) {
        this.quoteSetValidUntilTime = quoteSetValidUntilTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotNoQuoteEntries, required=true)
    public Integer getTotNoQuoteEntries() {
        return totNoQuoteEntries;
    }

    /**
     * Message field setter.
     * @param totNoQuoteEntries field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotNoQuoteEntries, required=true)
    public void setTotNoQuoteEntries(Integer totNoQuoteEntries) {
        this.totNoQuoteEntries = totNoQuoteEntries;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TotNoCxldQuotes)
    public Integer getTotNoCxldQuotes() {
        return totNoCxldQuotes;
    }

    /**
     * Message field setter.
     * @param totNoCxldQuotes field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TotNoCxldQuotes)
    public void setTotNoCxldQuotes(Integer totNoCxldQuotes) {
        this.totNoCxldQuotes = totNoCxldQuotes;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TotNoAccQuotes)
    public Integer getTotNoAccQuotes() {
        return totNoAccQuotes;
    }

    /**
     * Message field setter.
     * @param totNoAccQuotes field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TotNoAccQuotes)
    public void setTotNoAccQuotes(Integer totNoAccQuotes) {
        this.totNoAccQuotes = totNoAccQuotes;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TotNoRejQuotes)
    public Integer getTotNoRejQuotes() {
        return totNoRejQuotes;
    }

    /**
     * Message field setter.
     * @param totNoRejQuotes field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TotNoRejQuotes)
    public void setTotNoRejQuotes(Integer totNoRejQuotes) {
        this.totNoRejQuotes = totNoRejQuotes;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public Boolean getLastFragment() {
        return lastFragment;
    }

    /**
     * Message field setter.
     * @param lastFragment field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoQuoteEntries, required=true)
    public Integer getNoQuoteEntries() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link QuoteEntryAckGroup} groups. It will also create an array
     * of {@link QuoteEntryAckGroup} objects and set the <code>quoteEntryAckGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>quoteEntryAckGroups</code> array they will be discarded.<br/>
     * @param noQuoteEntries number of groups
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoQuoteEntries, required=true)
    public void setNoQuoteEntries(Integer noQuoteEntries) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for <code>QuoteEntryAckGroup</code> array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
    public QuoteEntryAckGroup[] getQuoteEntryAckGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link QuoteEntryAckGroup} object to the existing array of
     * <code>quoteEntryAckGroups</code> and expands the static array with 1 place.<br/>
     * This method will also update <code>noQuoteEntries</code> field to the proper value.<br/>
     * Note: If the <code>setNoQuoteEntries</code> method has been called there will already be a
     * number of objects in the <code>quoteEntryAckGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public QuoteEntryAckGroup addQuoteEntryAckGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link QuoteEntryAckGroup} object from the existing array
     * of <code>quoteEntryAckGroups</code> and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noQuoteEntries</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.2")
    public QuoteEntryAckGroup deleteQuoteEntryAckGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link QuoteEntryAckGroup} objects from the <code>quoteEntryAckGroups</code>
     * array (sets the array to 0 length)<br/>
     * This method will also update <code>noQuoteEntries</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearQuoteEntryAckGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.QuoteSetID.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (quoteSetID == null || quoteSetID.trim().isEmpty()) {
            errorMsg.append(" [QuoteSetID]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }
    
    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {             validateRequiredTags();         }
        
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            TagEncoder.encode(bao, TagNum.QuoteSetID, quoteSetID);
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(MsgSecureType.ALL_FIELDS));
            }
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
            TagEncoder.encodeUtcTimestamp(bao, TagNum.QuoteSetValidUntilTime, quoteSetValidUntilTime);
            TagEncoder.encode(bao, TagNum.TotNoQuoteEntries, totNoQuoteEntries);
            TagEncoder.encode(bao, TagNum.TotNoCxldQuotes, totNoCxldQuotes);
            TagEncoder.encode(bao, TagNum.TotNoAccQuotes, totNoAccQuotes);
            TagEncoder.encode(bao, TagNum.TotNoRejQuotes, totNoRejQuotes);
            TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            if (noQuoteEntries != null) {
                TagEncoder.encode(bao, TagNum.NoQuoteEntries, noQuoteEntries);
                if (quoteEntryAckGroups != null && quoteEntryAckGroups.length == noQuoteEntries.intValue()) {
                    for (int i = 0; i < noQuoteEntries.intValue(); i++) {
                        if (quoteEntryAckGroups[i] != null) {
                            bao.write(quoteEntryAckGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "QuoteEntryAckGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoQuoteEntries.getValue(), error);
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
            case QuoteSetID:
                quoteSetID = new String(tag.value, sessionCharset);
                break;
                
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
                underlyingMaturityDay = new Integer(new String(tag.value, sessionCharset));
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

            case EncodedUnderlyingIssuerLen:
                encodedUnderlyingIssuerLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSecurityDesc:
                underlyingSecurityDesc = new String(tag.value, sessionCharset);
                break;

            case EncodedUnderlyingSecurityDescLen:
                encodedUnderlyingSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case QuoteSetValidUntilTime:
                quoteSetValidUntilTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TotNoQuoteEntries:
                totNoQuoteEntries = new Integer(new String(tag.value, sessionCharset));
                break;

            case TotNoCxldQuotes:
                totNoCxldQuotes = new Integer(new String(tag.value, sessionCharset));
                break;

            case TotNoAccQuotes:
                totNoAccQuotes = new Integer(new String(tag.value, sessionCharset));
                break;

            case TotNoRejQuotes:
                totNoRejQuotes = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastFragment:
                lastFragment = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoQuoteEntries:
                noQuoteEntries = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [QuoteSetAckGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    /// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private UnderlyingInstrument getSafeUnderlyingInstrument() {
        if (getUnderlyingInstrument() == null) {
            setUnderlyingInstrument();
        }

        return getUnderlyingInstrument();
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{QuoteSetAckGroup=");
        printTagValue(b, TagNum.QuoteSetID, quoteSetID);
        printTagValue(b, underlyingInstrument);
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
        printUTCDateTimeTagValue(b, TagNum.QuoteSetValidUntilTime, quoteSetValidUntilTime);
        printTagValue(b, TagNum.TotNoQuoteEntries, totNoQuoteEntries);
        printTagValue(b, TagNum.TotNoCxldQuotes, totNoCxldQuotes);
        printTagValue(b, TagNum.TotNoAccQuotes, totNoAccQuotes);
        printTagValue(b, TagNum.TotNoRejQuotes, totNoRejQuotes);
        printTagValue(b, TagNum.LastFragment, lastFragment);
        printTagValue(b, TagNum.NoQuoteEntries, noQuoteEntries);
        printTagValue(b, quoteEntryAckGroups);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
