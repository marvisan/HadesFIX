/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteEntryAckGroup.java
 *
 * $Id: QuoteEntryAckGroup.java,v 1.8 2010-11-23 10:20:17 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.DateConverter;

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

import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.QuoteEntryStatus;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * QuoteEntryAck group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 20/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class QuoteEntryAckGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteEntryID.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.BidPx.getValue(),
        TagNum.OfferPx.getValue(),
        TagNum.BidSize.getValue(),
        TagNum.OfferSize.getValue(),
        TagNum.ValidUntilTime.getValue(),
        TagNum.BidSpotRate.getValue(),
        TagNum.OfferSpotRate.getValue(),
        TagNum.BidForwardPoints.getValue(),
        TagNum.OfferForwardPoints.getValue(),
        TagNum.MidPx.getValue(),
        TagNum.BidYield.getValue(),
        TagNum.MidYield.getValue(),
        TagNum.OfferYield.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.SettlDate2.getValue(),
        TagNum.OrderQty2.getValue(),
        TagNum.BidForwardPoints2.getValue(),
        TagNum.OfferForwardPoints2.getValue(),
        TagNum.Currency.getValue(),
        TagNum.BookingType.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.QuoteEntryStatus.getValue(),
        TagNum.QuoteEntryRejectReason.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 299. Starting with 4.2 version.
     */
    protected String quoteEntryID;
    
    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;
    
    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg[] instrumentLegs;

    /**
     * TagNum = 55. Starting with 4.2 version.
     */
    protected String symbol;

    /**
     * TagNum = 65. Starting with 4.2 version.
     */
    protected String symbolSfx;

    /**
     * TagNum = 48. Starting with 4.2 version.
     */
    protected String securityID;

    /**
     * TagNum = 22. Starting with 4.2 version.
     */
    protected String securityIDSource;

    /**
     * TagNum = 167. Starting with 4.2 version.
     */
    protected String securityType;

    /**
     * TagNum = 200. Starting with 4.2 version.
     */
    protected String maturityMonthYear;

    /**
     * TagNum = 205. Starting with 4.2 version.
     */
    protected Integer maturityDay;

    /**
     * TagNum = 201. Starting with 4.2 version.
     */
    protected PutOrCall putOrCall;

    /**
     * TagNum = 202. Starting with 4.2 version.
     */
    protected Double strikePrice;

    /**
     * TagNum = 231. Starting with 4.2 version.
     */
    protected Double contractMultiplier;

    /**
     * TagNum = 223. Starting with 4.2 version.
     */
    protected Double couponRate;

    /**
     * TagNum = 206. Starting with 4.2 version.
     */
    protected Character optAttribute;

    /**
     * TagNum = 207. Starting with 4.2 version.
     */
    protected String securityExchange;

    /**
     * TagNum = 106. Starting with 4.2 version.
     */
    protected String issuer;

    /**
     * TagNum = 348. Starting with 4.2 version.
     */
    protected Integer encodedIssuerLen;

    /**
     * TagNum = 349. Starting with 4.2 version.
     */
    protected byte[] encodedIssuer;

    /**
     * TagNum = 107. Starting with 4.2 version.
     */
    protected String securityDesc;

    /**
     * TagNum = 350. Starting with 4.2 version.
     */
    protected Integer encodedSecurityDescLen;

    /**
     * TagNum = 351. Starting with 4.2 version.
     */
    protected byte[] encodedSecurityDesc;

    /**
     * TagNum = 132 CONDITIONAL REQUIRED. Starting with 4.2 version.
     */
    protected Double bidPx;

    /**
     * TagNum = 133 CONDITIONAL REQUIRED. Starting with 4.2 version.
     */
    protected Double offerPx;

    /**
     * TagNum = 134. Starting with 4.2 version.
     */
    protected Double bidSize;

    /**
     * TagNum = 135. Starting with 4.2 version.
     */
    protected Double offerSize;

    /**
     * TagNum = 62. Starting with 4.2 version.
     */
    protected Date validUntilTime;

    /**
     * TagNum = 188. Starting with 4.2 version.
     */
    protected Double bidSpotRate;

    /**
     * TagNum = 190. Starting with 4.2 version.
     */
    protected Double offerSpotRate;

    /**
     * TagNum = 189. Starting with 4.2 version.
     */
    protected Double bidForwardPoints;

    /**
     * TagNum = 191. Starting with 4.2 version.
     */
    protected Double offerForwardPoints;

    /**
     * TagNum = 631. Starting with 4.3 version.
     */
    protected Double midPx;

    /**
     * TagNum = 632. Starting with 4.3 version.
     */
    protected Double bidYield;

    /**
     * TagNum = 633. Starting with 4.3 version.
     */
    protected Double midYield;

    /**
     * TagNum = 634. Starting with 4.3 version.
     */
    protected Double offerYield;

    /**
     * TagNum = 60. Starting with 4.2 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 336. Starting with 4.2 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.4 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 64. Starting with 4.2 version.
     */
    protected Date settlDate;
    
    /**
     * TagNum = 40. Starting with 4.2 version.
     */
    protected OrdType ordType;

    /**
     * TagNum = 193. Starting with 4.2 version.
     */
    protected Date settlDate2;

    /**
     * TagNum = 192. Starting with 4.2 version.
     */
    protected Double orderQty2;

    /**
     * TagNum = 642. Starting with 4.3 version.
     */
    protected Double bidForwardPoints2;

    /**
     * TagNum = 643. Starting with 4.3 version.
     */
    protected Double offerForwardPoints2;

    /**
     * TagNum = 15. Starting with 4.2 version.
     */
    protected Currency currency;

    /**
     * TagNum = 775. Starting with 5.0SP2 version.
     */
    protected BookingType bookingType;

    /**
     * TagNum = 528. Starting with 5.0SP2 version.
     */
    protected OrderCapacity orderCapacity;

    /**
     * TagNum = 529. Starting with 5.0SP2 version.
     */
    protected String orderRestrictions;

    /**
     * TagNum = 1167. Starting with 5.0SP1 version.
     */
    protected QuoteEntryStatus quoteEntryStatus;

    /**
     * TagNum = 368. Starting with 4.2 version.
     */
    protected Integer quoteEntryRejectReason;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteEntryAckGroup() {
    }
    
    public QuoteEntryAckGroup(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.QuoteEntryID)
    public String getQuoteEntryID() {
        return quoteEntryID;
    }

    /**
     * Message field setter.
     * @param quoteEntryID field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.QuoteEntryID)
    public void setQuoteEntryID(String quoteEntryID) {
        this.quoteEntryID = quoteEntryID;
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field set to the proper {@link Instrument} implementation.
     */
    @FIXVersion(introduced = "4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public Integer getNoLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link InstrumentLeg} components. It will also create an array
     * of {@link InstrumentLeg} objects and set the <code>instrumentLegs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrumentLegs</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrumentLeg} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public InstrumentLeg[] getInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link InstrumentLeg} object to the existing array of <code>instrumentLegs</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>instrumentLegs</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public InstrumentLeg addInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrumentLeg} object from the existing array of <code>instrumentLegs</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public InstrumentLeg deleteInstrumentLeg(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrumentLeg} objects from the <code>instrumentLegs</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public int clearInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required=true)
    public String getSymbol() {
        return getSafeInstrument().getSymbol();
    }

    /**
     * Message field setter.
     * @param symbol field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required=true)
    public void setSymbol(String symbol) {
        getSafeInstrument().setSymbol(symbol);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public String getSymbolSfx() {
        return getSafeInstrument().getSymbolSfx();
    }

    /**
     * Message field setter.
     * @param symbolSfx field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public void setSymbolSfx(String symbolSfx) {
        getSafeInstrument().setSymbolSfx(symbolSfx);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public String getSecurityID() {
        return getSafeInstrument().getSecurityID();
    }

    /**
     * Message field setter.
     * @param securityID field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public void setSecurityID(String securityID) {
        getSafeInstrument().setSecurityID(securityID);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public String getSecurityIDSource() {
        return getSafeInstrument().getSecurityIDSource();
    }

    /**
     * Message field setter.
     * @param securityIDSource field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public void setSecurityIDSource(String securityIDSource) {
        getSafeInstrument().setSecurityIDSource(securityIDSource);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        return getSafeInstrument().getSecurityType();
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        getSafeInstrument().setSecurityType(securityType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public String getMaturityMonthYear() {
        return getSafeInstrument().getMaturityMonthYear();
    }

    /**
     * Message field setter.
     * @param maturityMonthYear field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public void setMaturityMonthYear(String maturityMonthYear) {
        getSafeInstrument().setMaturityMonthYear(maturityMonthYear);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public Integer getMaturityDay() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maturityDay field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public void setMaturityDay(Integer maturityDay) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public PutOrCall getPutOrCall() {
        return getSafeInstrument().getPutOrCall();
    }

    /**
     * Message field setter.
     * @param putOrCall field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public void setPutOrCall(PutOrCall putOrCall) {
        getSafeInstrument().setPutOrCall(putOrCall);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public Double getStrikePrice() {
        return getSafeInstrument().getStrikePrice();
    }

    /**
     * Message field setter.
     * @param strikePrice field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public void setStrikePrice(Double strikePrice) {
        getSafeInstrument().setStrikePrice(strikePrice);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.ContractMultiplier)
    public Double getContractMultiplier() {
        return getSafeInstrument().getContractMultiplier();
    }

    /**
     * Message field setter.
     * @param contractMultiplier field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.ContractMultiplier)
    public void setContractMultiplier(Double contractMultiplier) {
        getSafeInstrument().setContractMultiplier(contractMultiplier);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.CouponRate)
    public Double getCouponRate() {
        return getSafeInstrument().getCouponRate();
    }

    /**
     * Message field setter.
     * @param couponRate field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.CouponRate)
    public void setCouponRate(Double couponRate) {
        getSafeInstrument().setCouponRate(couponRate);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public Character getOptAttribute() {
        return getSafeInstrument().getOptAttribute();
    }

    /**
     * Message field setter.
     * @param optAttribute field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public void setOptAttribute(Character optAttribute) {
        getSafeInstrument().setOptAttribute(optAttribute);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public String getSecurityExchange() {
        return getSafeInstrument().getSecurityExchange();
    }

    /**
     * Message field setter.
     * @param securityExchange field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public void setSecurityExchange(String securityExchange) {
        getSafeInstrument().setSecurityExchange(securityExchange);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Issuer)
    public String getIssuer() {
        return getSafeInstrument().getIssuer();
    }

    /**
     * Message field setter.
     * @param issuer field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Issuer)
    public void setIssuer(String issuer) {
        getSafeInstrument().setIssuer(issuer);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuerLen)
    public Integer getEncodedIssuerLen() {
        return getSafeInstrument().getEncodedIssuerLen();
    }

    /**
     * Message field setter.
     * @param encodedIssuerLen field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuerLen)
    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        getSafeInstrument().setEncodedIssuerLen(encodedIssuerLen);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuer)
    public byte[] getEncodedIssuer() {
        return getSafeInstrument().getEncodedIssuer();
    }

    /**
     * Message field setter.
     * @param encodedIssuer field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuer)
    public void setEncodedIssuer(byte[] encodedIssuer) {
        getSafeInstrument().setEncodedIssuer(encodedIssuer);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public String getSecurityDesc() {
        return getSafeInstrument().getSecurityDesc();
    }

    /**
     * Message field setter.
     * @param securityDesc field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public void setSecurityDesc(String securityDesc) {
        getSafeInstrument().setSecurityDesc(securityDesc);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDescLen)
    public Integer getEncodedSecurityDescLen() {
        return getSafeInstrument().getEncodedSecurityDescLen();
    }

    /**
     * Message field setter.
     * @param encodedSecurityDescLen field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDescLen)
    public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
        getSafeInstrument().setEncodedSecurityDescLen(encodedSecurityDescLen);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDesc)
    public byte[] getEncodedSecurityDesc() {
        return getSafeInstrument().getEncodedSecurityDesc();
    }

    /**
     * Message field setter.
     * @param encodedSecurityDesc field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDesc)
    public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
        getSafeInstrument().setEncodedSecurityDesc(encodedSecurityDesc);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidPx)
    public Double getBidPx() {
        return bidPx;
    }

    /**
     * Message field setter.
     * @param bidPx field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidPx)
    public void setBidPx(Double bidPx) {
        this.bidPx = bidPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferPx)
    public Double getOfferPx() {
        return offerPx;
    }

    /**
     * Message field setter.
     * @param offerPx field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferPx)
    public void setOfferPx(Double offerPx) {
        this.offerPx = offerPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidSize)
    public Double getBidSize() {
        return bidSize;
    }

    /**
     * Message field setter.
     * @param bidSize field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidSize)
    public void setBidSize(Double bidSize) {
        this.bidSize = bidSize;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferSize)
    public Double getOfferSize() {
        return offerSize;
    }

    /**
     * Message field setter.
     * @param offerSize field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferSize)
    public void setOfferSize(Double offerSize) {
        this.offerSize = offerSize;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ValidUntilTime)
    public Date getValidUntilTime() {
        return validUntilTime;
    }

    /**
     * Message field setter.
     * @param validUntilTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ValidUntilTime)
    public void setValidUntilTime(Date validUntilTime) {
        this.validUntilTime = validUntilTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidSpotRate)
    public Double getBidSpotRate() {
        return bidSpotRate;
    }

    /**
     * Message field setter.
     * @param bidSpotRate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidSpotRate)
    public void setBidSpotRate(Double bidSpotRate) {
        this.bidSpotRate = bidSpotRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferSpotRate)
    public Double getOfferSpotRate() {
        return offerSpotRate;
    }

    /**
     * Message field setter.
     * @param offerSpotRate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferSpotRate)
    public void setOfferSpotRate(Double offerSpotRate) {
        this.offerSpotRate = offerSpotRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidForwardPoints)
    public Double getBidForwardPoints() {
        return bidForwardPoints;
    }

    /**
     * Message field setter.
     * @param bidForwardPoints field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidForwardPoints)
    public void setBidForwardPoints(Double bidForwardPoints) {
        this.bidForwardPoints = bidForwardPoints;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferForwardPoints)
    public Double getOfferForwardPoints() {
        return offerForwardPoints;
    }

    /**
     * Message field setter.
     * @param offerForwardPoints field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferForwardPoints)
    public void setOfferForwardPoints(Double offerForwardPoints) {
        this.offerForwardPoints = offerForwardPoints;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MidPx)
    public Double getMidPx() {
        return midPx;
    }

    /**
     * Message field setter.
     * @param midPx field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MidPx)
    public void setMidPx(Double midPx) {
        this.midPx = midPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidYield)
    public Double getBidYield() {
        return bidYield;
    }

    /**
     * Message field setter.
     * @param bidYield field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidYield)
    public void setBidYield(Double bidYield) {
        this.bidYield = bidYield;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MidYield)
    public Double getMidYield() {
        return midYield;
    }

    /**
     * Message field setter.
     * @param midYield field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MidYield)
    public void setMidYield(Double midYield) {
        this.midYield = midYield;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferYield)
    public Double getOfferYield() {
        return offerYield;
    }

    /**
     * Message field setter.
     * @param offerYield field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferYield)
    public void setOfferYield(Double offerYield) {
        this.offerYield = offerYield;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        return settlDate;
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrdType)
    public OrdType getOrdType() {
        return ordType;
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrdType)
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlDate2)
    public Date getSettlDate2() {
        return settlDate2;
    }

    /**
     * Message field setter.
     * @param settlDate2 field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlDate2)
    public void setSettlDate2(Date settlDate2) {
        this.settlDate2 = settlDate2;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderQty2)
    public Double getOrderQty2() {
        return orderQty2;
    }

    /**
     * Message field setter.
     * @param orderQty2 field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderQty2)
    public void setOrderQty2(Double orderQty2) {
        this.orderQty2 = orderQty2;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidForwardPoints2)
    public Double getBidForwardPoints2() {
        return bidForwardPoints2;
    }

    /**
     * Message field setter.
     * @param bidForwardPoints2 field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidForwardPoints2)
    public void setBidForwardPoints2(Double bidForwardPoints2) {
        this.bidForwardPoints2 = bidForwardPoints2;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferForwardPoints2)
    public Double getOfferForwardPoints2() {
        return offerForwardPoints2;
    }

    /**
     * Message field setter.
     * @param offerForwardPoints2 field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferForwardPoints2)
    public void setOfferForwardPoints2(Double offerForwardPoints2) {
        this.offerForwardPoints2 = offerForwardPoints2;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.BookingType)
    public BookingType getBookingType() {
        return bookingType;
    }

    /**
     * Message field setter.
     * @param bookingType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.BookingType)
    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    /**
     * Message field setter.
     * @param orderCapacity field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderRestrictions)
    public String getOrderRestrictions() {
        return orderRestrictions;
    }

    /**
     * Message field setter.
     * @param orderRestrictions field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderRestrictions)
    public void setOrderRestrictions(String orderRestrictions) {
        this.orderRestrictions = orderRestrictions;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteEntryStatus)
    public QuoteEntryStatus getQuoteEntryStatus() {
        return quoteEntryStatus;
    }

    /**
     * Message field setter.
     * @param quoteEntryStatus field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteEntryStatus)
    public void setQuoteEntryStatus(QuoteEntryStatus quoteEntryStatus) {
        this.quoteEntryStatus = quoteEntryStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteEntryRejectReason)
    public Integer getQuoteEntryRejectReason() {
        return quoteEntryRejectReason;
    }

    /**
     * Message field setter.
     * @param quoteEntryRejectReason field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteEntryRejectReason)
    public void setQuoteEntryRejectReason(Integer quoteEntryRejectReason) {
        this.quoteEntryRejectReason = quoteEntryRejectReason;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.QuoteEntryID.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (quoteEntryID == null || quoteEntryID.trim().isEmpty()) {
            errorMsg.append(" [QuoteEntryID]");
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
            TagEncoder.encode(bao, TagNum.QuoteEntryID, quoteEntryID);
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrumentLegs[i] != null) {
                            bao.write(instrumentLegs[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "InstrumentLeg field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.Symbol, symbol);
            TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx);
            TagEncoder.encode(bao, TagNum.SecurityID, securityID);
            TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource);
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear);
            TagEncoder.encode(bao, TagNum.MaturityDay, maturityDay);
            if (putOrCall != null) {
                TagEncoder.encode(bao, TagNum.PutOrCall, putOrCall.getValue());
            }
            TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            TagEncoder.encode(bao, TagNum.ContractMultiplier, contractMultiplier);
            TagEncoder.encode(bao, TagNum.CouponRate, couponRate);
            TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange);
            TagEncoder.encode(bao, TagNum.Issuer, issuer);
            if (encodedIssuerLen != null && encodedIssuerLen.intValue() > 0) {
                if (encodedIssuer != null && encodedIssuer.length > 0) {
                    encodedIssuerLen = new Integer(encodedIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedIssuerLen, encodedIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedIssuer, encodedIssuer);
                }
            }
            TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            if (encodedSecurityDescLen != null && encodedSecurityDescLen.intValue() > 0) {
                if (encodedSecurityDesc != null && encodedSecurityDesc.length > 0) {
                    encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
                }
            }
            TagEncoder.encode(bao, TagNum.BidPx, bidPx);
            TagEncoder.encode(bao, TagNum.OfferPx, offerPx);
            TagEncoder.encode(bao, TagNum.BidSize, bidSize);
            TagEncoder.encode(bao, TagNum.OfferSize, offerSize);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ValidUntilTime, validUntilTime);
            TagEncoder.encode(bao, TagNum.BidSpotRate, bidSpotRate);
            TagEncoder.encode(bao, TagNum.OfferSpotRate, offerSpotRate);
            TagEncoder.encode(bao, TagNum.BidForwardPoints, bidForwardPoints);
            TagEncoder.encode(bao, TagNum.OfferForwardPoints, offerForwardPoints);
            TagEncoder.encode(bao, TagNum.MidPx, midPx);
            TagEncoder.encode(bao, TagNum.BidYield, bidYield);
            TagEncoder.encode(bao, TagNum.MidYield, midYield);
            TagEncoder.encode(bao, TagNum.OfferYield, offerYield);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            if (ordType != null) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.SettlDate2, settlDate2);
            TagEncoder.encode(bao, TagNum.OrderQty2, orderQty2);
            TagEncoder.encode(bao, TagNum.BidForwardPoints2, bidForwardPoints2);
            TagEncoder.encode(bao, TagNum.OfferForwardPoints2, offerForwardPoints2);
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (bookingType != null) {
                TagEncoder.encode(bao, TagNum.BookingType, bookingType.getValue());
            }
            if (orderCapacity != null) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.OrderRestrictions, orderRestrictions);
            if (quoteEntryStatus != null) {
                TagEncoder.encode(bao, TagNum.QuoteEntryStatus, quoteEntryStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.QuoteEntryRejectReason, quoteEntryRejectReason);
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
            case QuoteEntryID:
                quoteEntryID = new String(tag.value, sessionCharset);
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, getSessionCharset()));
                break;
                
            case Symbol:
                symbol = new String(tag.value, sessionCharset);
                break;

            case SymbolSfx:
                symbolSfx = new String(tag.value, sessionCharset);
                break;

            case SecurityID:
                securityID = new String(tag.value, sessionCharset);
                break;

            case SecurityIDSource:
                securityIDSource = new String(tag.value, sessionCharset);
                break;

            case SecurityType:
                securityType = new String(tag.value, sessionCharset);
                break;

            case MaturityMonthYear:
                maturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case MaturityDay:
                maturityDay = new Integer(new String(tag.value, sessionCharset));
                break;

            case PutOrCall:
                putOrCall = PutOrCall.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case StrikePrice:
                strikePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case OptAttribute:
                optAttribute = new Character(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ContractMultiplier:
                contractMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case CouponRate:
                couponRate = new Double(new String(tag.value, sessionCharset));
                break;

            case SecurityExchange:
                securityExchange = new String(tag.value, sessionCharset);
                break;

            case Issuer:
                issuer = new String(tag.value, sessionCharset);
                break;

            case EncodedIssuerLen:
                encodedIssuerLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case SecurityDesc:
                securityDesc = new String(tag.value, sessionCharset);
                break;

            case EncodedSecurityDescLen:
                encodedSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case BidPx:
                bidPx = new Double(new String(tag.value, sessionCharset));
                break;

            case OfferPx:
                offerPx = new Double(new String(tag.value, sessionCharset));
                break;

            case BidSize:
                bidSize = new Double(new String(tag.value, sessionCharset));
                break;

            case OfferSize:
                offerSize = new Double(new String(tag.value, sessionCharset));
                break;

            case ValidUntilTime:
                validUntilTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case BidSpotRate:
                bidSpotRate = new Double(new String(tag.value, sessionCharset));
                break;

            case OfferSpotRate:
                offerSpotRate = new Double(new String(tag.value, sessionCharset));
                break;

            case BidForwardPoints:
                bidForwardPoints = new Double(new String(tag.value, sessionCharset));
                break;

            case OfferForwardPoints:
                offerForwardPoints = new Double(new String(tag.value, sessionCharset));
                break;

            case MidPx:
                midPx = new Double(new String(tag.value, sessionCharset));
                break;

            case BidYield:
                bidYield = new Double(new String(tag.value, sessionCharset));
                break;

            case MidYield:
                midYield = new Double(new String(tag.value, sessionCharset));
                break;

            case OfferYield:
                offerYield = new Double(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case SettlDate:
                settlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case OrdType:
                ordType = OrdType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case SettlDate2:
                settlDate2 = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case OrderQty2:
                orderQty2 = new Double(new String(tag.value, sessionCharset));
                break;

            case BidForwardPoints2:
                bidForwardPoints2 = new Double(new String(tag.value, sessionCharset));
                break;

            case OfferForwardPoints2:
                offerForwardPoints2 = new Double(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case BookingType:
                bookingType = BookingType.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;

            case OrderCapacity:
                orderCapacity = OrderCapacity.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case OrderRestrictions:
                orderRestrictions = new String(tag.value, getSessionCharset());
                break;

            case QuoteEntryStatus:
                quoteEntryStatus = QuoteEntryStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QuoteEntryRejectReason:
                quoteEntryRejectReason = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [QuoteEntryAckGroup] fields.";
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

    private Instrument getSafeInstrument() {
        if (getInstrument() == null) {
            setInstrument();
        }

        return getInstrument();
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{QuoteEntryAckGroup=");
        printTagValue(b, TagNum.QuoteEntryID, quoteEntryID);
        printTagValue(b, instrument);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrumentLegs);
        printTagValue(b, TagNum.Symbol, symbol);
        printTagValue(b, TagNum.SymbolSfx, symbolSfx);
        printTagValue(b, TagNum.SecurityID, securityID);
        printTagValue(b, TagNum.SecurityIDSource, securityIDSource);
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.MaturityMonthYear, maturityMonthYear);
        printTagValue(b, TagNum.MaturityDay, maturityDay);
        printTagValue(b, TagNum.PutOrCall, putOrCall);
        printTagValue(b, TagNum.StrikePrice, strikePrice);
        printTagValue(b, TagNum.ContractMultiplier, contractMultiplier);
        printTagValue(b, TagNum.CouponRate, couponRate);
        printTagValue(b, TagNum.OptAttribute, optAttribute);
        printTagValue(b, TagNum.SecurityExchange, securityExchange);
        printTagValue(b, TagNum.Issuer, issuer);
        printTagValue(b, TagNum.EncodedIssuerLen, encodedIssuerLen);
        printTagValue(b, TagNum.EncodedIssuer, encodedIssuer);
        printTagValue(b, TagNum.SecurityDesc, securityDesc);
        printTagValue(b, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
        printTagValue(b, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
        printTagValue(b, TagNum.BidPx, bidPx);
        printTagValue(b, TagNum.OfferPx, offerPx);
        printTagValue(b, TagNum.BidSize, bidSize);
        printTagValue(b, TagNum.OfferSize, offerSize);
        printUTCDateTimeTagValue(b, TagNum.ValidUntilTime, validUntilTime);
        printTagValue(b, TagNum.BidSpotRate, bidSpotRate);
        printTagValue(b, TagNum.OfferSpotRate, offerSpotRate);
        printTagValue(b, TagNum.BidForwardPoints, bidForwardPoints);
        printTagValue(b, TagNum.OfferForwardPoints, offerForwardPoints);
        printTagValue(b, TagNum.MidPx, midPx);
        printTagValue(b, TagNum.BidYield, bidYield);
        printTagValue(b, TagNum.MidYield, midYield);
        printTagValue(b, TagNum.OfferYield, offerYield);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.OrdType, ordType);
        printDateTagValue(b, TagNum.SettlDate2, settlDate2);
        printTagValue(b, TagNum.OrderQty2, orderQty2);
        printTagValue(b, TagNum.BidForwardPoints2, bidForwardPoints2);
        printTagValue(b, TagNum.OfferForwardPoints2, offerForwardPoints2);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.BookingType, bookingType);
        printTagValue(b, TagNum.OrderCapacity, orderCapacity);
        printTagValue(b, TagNum.OrderRestrictions, orderRestrictions);
        printTagValue(b, TagNum.QuoteEntryStatus, quoteEntryStatus);
        printTagValue(b, TagNum.QuoteEntryRejectReason, quoteEntryRejectReason);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
