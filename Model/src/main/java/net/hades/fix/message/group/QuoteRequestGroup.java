/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestGroup.java
 *
 * $Id: QuoteRequestGroup.java,v 1.11 2010-11-23 10:20:16 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.QuotePriceType;
import net.hades.fix.message.type.QuoteRequestType;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.RateSource;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * Rlated symbols for a quote request message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class QuoteRequestGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -3747662759100468286L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoUnderlyings.getValue(),
        TagNum.PrevClosePx.getValue(),
        TagNum.QuoteRequestType.getValue(),
        TagNum.QuoteType.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.TradeOriginationDate.getValue(),
        TagNum.Side.getValue(),
        TagNum.QuantityType.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.MinQty.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.SettlDate2.getValue(),
        TagNum.NoRateSources.getValue(),
        TagNum.OrderQty2.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.Currency.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.NoQuoteQualifiers.getValue(),
        TagNum.QuotePriceType.getValue(),
        TagNum.ValidUntilTime.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.Price.getValue(),
        TagNum.Price2.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;
    
    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;
    
    /**
     * Starting with 4.4 version.
     */
    protected FinancingDetails financingDetails;

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
     * TagNum = 206. Starting with 4.2 version.
     */
    protected Character optAttribute;

    /**
     * TagNum = 231. Starting with 4.2 version.
     */
    protected Double contractMultiplier;

    /**
     * TagNum = 223. Starting with 4.2 version.
     */
    protected Double couponRate;

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
     * TagNum = 140. Starting with 4.2 version.
     */
    protected Double prevClosePx;

    /**
     * TagNum = 303. Starting with 4.2 version.
     */
    protected QuoteRequestType quoteRequestType;

    /**
     * TagNum = 537. Starting with 4.3 version.
     */
    protected QuoteType quoteType;

    /**
     * TagNum = 336. Starting with 4.2 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 229. Starting with 4.3 version.
     */
    protected Date tradeOriginationDate;

    /**
     * TagNum = 555. Starting with 5.0SP2 version.
     */
    protected Integer noRateSources;

    /**
     * Starting with 5.0SP2 version.
     */
    protected RateSourceGroup[] rateSources;

    /**
     * Starting with 4.3 version.
     */
    protected Stipulations stipulations;

    /**
     * TagNum = 54. Starting with 4.2 version.
     */
    protected Side side;

    /**
     * TagNum = 465. Starting with 4.3 version.
     */
    protected QuantityType quantityType;

    /**
     * TagNum = 854. Starting with 4.4 version.
     */
    protected QtyType qtyType;

    /**
     * Starting with 4.4 version.
     */
    protected OrderQtyData orderQtyData;

    /**
     * TagNum = 38. Starting with 4.2 version.
     */
    protected Double orderQty;

    /**
     * TagNum = 152. Starting with 4.3 version.
     */
    protected Double cashOrderQty;

    /**
     * TagNum = 110. Starting with 5.0SP1 version.
     */
    protected Double minQty;

    /**
     * TagNum = 63. Starting with 4.3 version.
     */
    protected String settlType;

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
     * TagNum = 126. Starting with 4.2 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 60. Starting with 4.2 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 15. Starting with 4.2 version.
     */
    protected Currency currency;

    /**
     * TagNum = 120. Starting with 5.0SP2 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 1. Starting with 4.4 version.
     */
    protected String account;

    /**
     * TagNum = 660. Starting with 4.4 version.
     */
    protected Integer acctIDSource;

    /**
     * TagNum = 581. Starting with 4.4 version.
     */
    protected AccountType accountType;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected QuoteRequestLegGroup[] legQuoteSymbolGroups;

    /**
     * TagNum = 735. Starting with 4.4 version.
     */
    protected Integer noQuoteQualifiers;

    /**
     * Starting with 4.4 version.
     */
    protected QuoteQualifierGroup[] quoteQualifierGroups;
    
    /**
     * TagNum = 692. Starting with 4.4 version.
     */
    protected QuotePriceType quotePriceType;

    /**
     * TagNum = 62. Starting with 4.4 version.
     */
    protected Date validUntilTime;

    /**
     * Starting with 4.4 version.
     */
    protected SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData;

    /**
     * TagNum = 423. Starting with 4.3 version.
     */
    protected PriceType priceType;

    /**
     * TagNum = 44. Starting with 4.3 version.
     */
    protected Double price;

    /**
     * TagNum = 640. Starting with 4.3 version.
     */
    protected Double price2;

    /**
     * Starting with 4.3 version.
     */
    protected YieldData yieldData;

    /**
     * Starting with 4.4 version.
     */
    protected Parties parties;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public QuoteRequestGroup() {
    }

    public QuoteRequestGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public FinancingDetails getFinancingDetails() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the FinancingDetails component class to the proper implementation.
     */
    @FIXVersion(introduced = "4.4")
    public void setFinancingDetails() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoUnderlyings)
    public Integer getNoUnderlyings() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UnderlyingInstrument} groups. It will also create an array
     * of {@link UnderlyingInstrument} objects and set the <code>underlyingInstruments</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingInstruments</code> array they will be discarded.<br/>
     * @param noUnderlyings field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoUnderlyings)
    public void setNoUnderlyings(Integer noUnderlyings) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UnderlyingInstrument} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link UnderlyingInstrument} object to the existing array of <code>underlyingInstruments</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * Note: If the <code>setNoUnderlyings</code> method has been called there will already be a number of objects in the
     * <code>underlyingInstruments</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public UnderlyingInstrument addUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UnderlyingInstrument} object from the existing array of <code>underlyingInstruments</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingInstrument} objects from the <code>underlyingInstruments</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyings</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol)
    public String getSymbol() {
        return getSafeInstrument().getSymbol();
    }

    /**
     * Message field setter.
     * @param symbol field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol)
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
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public Double getPrevClosePx() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param prevClosePx field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public void setPrevClosePx(Double prevClosePx) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.QuoteRequestType)
    public QuoteRequestType getQuoteRequestType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param quoteRequestType field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.QuoteRequestType)
    public void setQuoteRequestType(QuoteRequestType quoteRequestType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.QuoteType)
    public QuoteType getQuoteType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param quoteType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.QuoteType)
    public void setQuoteType(QuoteType quoteType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.TradingSessionID)
    public String getTradingSessionID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.TradeOriginationDate)
    public Date getTradeOriginationDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param tradeOriginationDate field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.TradeOriginationDate)
    public void setTradeOriginationDate(Date tradeOriginationDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.NoRateSources)
    public Integer getNoRateSources() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link RateSource} groups. It will also create an array
     * of {@link RateSource} objects and set the <code>rateSources</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>rateSources</code> array they will be discarded.<br/>
     * @param noRateSources field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.NoRateSources)
    public void setNoRateSources(Integer noRateSources) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link RateSource} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0SP2")
    public RateSourceGroup[] getRateSources() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link RateSource} object to the existing array of <code>rateSources</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRateSources</code> field to the proper value.<br/>
     * Note: If the <code>setNoRateSources</code> method has been called there will already be a number of objects in the
     * <code>rateSources</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "5.0SP2")
    public RateSourceGroup addRateSource() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link RateSource} object from the existing array of <code>rateSources</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRateSources</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "5.0SP2")
    public RateSourceGroup deleteRateSource(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link RateSource} objects from the <code>rateSources</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRateSources</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0SP2")
    public int clearRateSources() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public Stipulations getStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper implementation class.
     */
    @FIXVersion(introduced = "4.3")
    public void setStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "4.3")
    public void clearStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.Side)
    public Side getSide() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.Side)
    public void setSide(Side side) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.QuantityType)
    public QuantityType getQuantityType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param quantityType field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.QuantityType)
    public void setQuantityType(QuantityType quantityType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.QtyType)
    public QtyType getQtyType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param qtyType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.QtyType)
    public void setQtyType(QtyType qtyType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public OrderQtyData getOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the OrderQtyData component class to the proper implementation.
     */
    @FIXVersion(introduced = "4.4")
    public void setOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.4")
    @TagNumRef(tagNum = TagNum.OrderQty)
    public Double getOrderQty() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param orderQty field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.4")
    @TagNumRef(tagNum = TagNum.OrderQty)
    public void setOrderQty(Double orderQty) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.CashOrderQty)
    public Double getCashOrderQty() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param cashOrderQty field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.CashOrderQty)
    public void setCashOrderQty(Double cashOrderQty) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MinQty)
    public Double getMinQty() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param minQty field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MinQty)
    public void setMinQty(Double minQty) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SettlType)
    public String getSettlType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SettlType)
    public void setSettlType(String settlType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.SettlDate)
    public Date getSettlDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.OrdType)
    public OrdType getOrdType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.OrdType)
    public void setOrdType(OrdType ordType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.SettlDate2)
    public Date getSettlDate2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlDate2 field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.SettlDate2)
    public void setSettlDate2(Date settlDate2) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.OrderQty2)
    public Double getOrderQty2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param orderQty2 field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.OrderQty2)
    public void setOrderQty2(Double orderQty2) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.ExpireTime)
    public Date getExpireTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.TransactTime)
    public Date getTransactTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Account)
    public String getAccount() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Account)
    public void setAccount(String account) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AcctIDSource)
    public Integer getAcctIDSource() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param acctIDSource field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AcctIDSource)
    public void setAcctIDSource(Integer acctIDSource) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AccountType)
    public AccountType getAccountType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param accountType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AccountType)
    public void setAccountType(AccountType accountType) {
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
     * This method sets the number of {@link QuoteRequestLegGroup} groups. It will also create an array
     * of {@link QuoteRequestLegGroup} objects and set the <code>legQuoteSymbolGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>legQuoteSymbolGroups</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link QuoteRequestLegGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public QuoteRequestLegGroup[] getLegQuoteSymbolGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link QuoteRequestLegGroup} object to the existing array of <code>legQuoteSymbolGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>legQuoteSymbolGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public QuoteRequestLegGroup addLegQuoteSymbolGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link QuoteRequestLegGroup} object from the existing array of <code>legQuoteSymbolGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public QuoteRequestLegGroup deleteLegQuoteSymbolGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link QuoteRequestLegGroup} objects from the <code>legQuoteSymbolGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public int clearLegQuoteSymbolGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoQuoteQualifiers)
    public Integer getNoQuoteQualifiers() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link QuoteQualifierGroup} groups. It will also create an array
     * of {@link QuoteQualifierGroup} objects and set the <code>quoteQualifierGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>quoteQualifierGroups</code> array they will be discarded.<br/>
     * @param noQuoteQualifiers field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoQuoteQualifiers)
    public void setNoQuoteQualifiers(Integer noQuoteQualifiers) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link QuoteQualifierGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public QuoteQualifierGroup[] getQuoteQualifierGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link QuoteQualifierGroup} object to the existing array of <code>quoteQualifierGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noQuoteQualifiers</code> field to the proper value.<br/>
     * Note: If the <code>setNoQuoteQualifiers</code> method has been called there will already be a number of objects in the
     * <code>quoteQualifierGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public QuoteQualifierGroup addQuoteQualifierGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link QuoteQualifierGroup} object from the existing array of <code>quoteQualifierGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noQuoteQualifiers</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public QuoteQualifierGroup deleteQuoteQualifierGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link QuoteQualifierGroup} objects from the <code>quoteQualifierGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noQuoteQualifiers</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public int clearQuoteQualifierGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QuotePriceType)
    public QuotePriceType getQuotePriceType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param quotePriceType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QuotePriceType)
    public void setQuotePriceType(QuotePriceType quotePriceType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ValidUntilTime)
    public Date getValidUntilTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param validUntilTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ValidUntilTime)
    public void setValidUntilTime(Date validUntilTime) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component class to the proper implementation.
     */
    @FIXVersion(introduced="4.3")
    public void setSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component TO NULL.
     */
    @FIXVersion(introduced="4.3")
    public void clearSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceType)
    public PriceType getPriceType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param priceType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceType)
    public void setPriceType(PriceType priceType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price)
    public Double getPrice() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price)
    public void setPrice(Double price) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price2)
    public Double getPrice2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param price2 field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price2)
    public void setPrice2(Double price2) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public YieldData getYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the YieldData component class to the proper implementation.
     */
    @FIXVersion(introduced="4.3")
    public void setYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the YieldData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the YieldData Parties class to the proper implementation.
     */
    @FIXVersion(introduced="4.4")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the YieldData Parties class to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.Symbol.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (instrument == null || instrument.getSymbol().trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
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
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (financingDetails != null) {
                bao.write(financingDetails.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noUnderlyings != null) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noUnderlyings.intValue()) {
                    for (int i = 0; i < noUnderlyings.intValue(); i++) {
                        if (underlyingInstruments[i] != null) {
                            bao.write(underlyingInstruments[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UnderlyingInstrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoUnderlyings.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.PrevClosePx, prevClosePx);
            if (quoteRequestType != null) {
                TagEncoder.encode(bao, TagNum.QuoteRequestType, quoteRequestType.getValue());
            }
            if (quoteType != null) {
                TagEncoder.encode(bao, TagNum.QuoteType, quoteType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            TagEncoder.encodeDate(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (qtyType != null) {
                TagEncoder.encode(bao, TagNum.QtyType, qtyType.getValue());
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.MinQty, minQty);
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            TagEncoder.encodeDate(bao, TagNum.SettlDate2, settlDate2);
            TagEncoder.encode(bao, TagNum.OrderQty2, orderQty2);
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            if (noRateSources != null && noRateSources.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoRateSources, noRateSources);
                if (rateSources != null && rateSources.length == noRateSources.intValue()) {
                    for (RateSourceGroup rateSource : rateSources) {
                        bao.write(rateSource.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "RateSourceGroup field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoRateSources.getValue(), error);
                }
            }
            if (stipulations != null) {
                bao.write(stipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Account, account);
            TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource);
            if (accountType != null) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (legQuoteSymbolGroups != null && legQuoteSymbolGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (legQuoteSymbolGroups[i] != null) {
                            bao.write(legQuoteSymbolGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "LegQuoteSymbolGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegs.getValue(), error);
                }
            }
            if (noQuoteQualifiers != null) {
                TagEncoder.encode(bao, TagNum.NoQuoteQualifiers, noQuoteQualifiers);
                if (quoteQualifierGroups != null && quoteQualifierGroups.length == noQuoteQualifiers.intValue()) {
                    for (int i = 0; i < noQuoteQualifiers.intValue(); i++) {
                        if (quoteQualifierGroups[i] != null) {
                            bao.write(quoteQualifierGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "QuoteQualifierGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoQuoteQualifiers.getValue(), error);
                }
            }
            if (quotePriceType != null) {
                TagEncoder.encode(bao, TagNum.QuotePriceType, quotePriceType.getValue());
            }
            if (ordType != null) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ValidUntilTime, validUntilTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.Price, price);
            TagEncoder.encode(bao, TagNum.Price2, price2);
            if (yieldData != null) {
                bao.write(yieldData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
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
            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
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

            case SecurityDesc:
                securityDesc = new String(tag.value, sessionCharset);
                break;

            case PrevClosePx:
                prevClosePx = new Double(new String(tag.value, sessionCharset));
                break;

            case NoRateSources:
                noRateSources = new Integer(new String(tag.value, sessionCharset));
                break;

            case QuoteRequestType:
                quoteRequestType = QuoteRequestType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QuoteType:
                quoteType = QuoteType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case TradeOriginationDate:
                tradeOriginationDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Side:
                side = Side.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case QuantityType:
                quantityType = QuantityType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QtyType:
                qtyType = QtyType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OrderQty:
                orderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case CashOrderQty:
                cashOrderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case MinQty:
                minQty = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlType:
                settlType = new String(tag.value, sessionCharset);
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

            case ExpireTime:
                expireTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case Account:
                account = new String(tag.value, sessionCharset);
                break;

            case AcctIDSource:
                acctIDSource = new Integer(new String(tag.value, sessionCharset));
                break;

            case AccountType:
                accountType = AccountType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoQuoteQualifiers:
                noQuoteQualifiers = new Integer(new String(tag.value, sessionCharset));
                break;

            case QuotePriceType:
                quotePriceType = QuotePriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ValidUntilTime:
                validUntilTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case PriceType:
                priceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Price:
                price = new Double(new String(tag.value, sessionCharset));
                break;

            case Price2:
                price2 = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [QuoteRelatedSymbolGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    // </editor-fold>

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
        b.append("{QuoteRequestGroup=");
        printTagValue(b, instrument);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, financingDetails);
        printTagValue(b, TagNum.Symbol, symbol);
        printTagValue(b, TagNum.SymbolSfx, symbolSfx);
        printTagValue(b, TagNum.SecurityID, securityID);
        printTagValue(b, TagNum.SecurityIDSource, securityIDSource);
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.MaturityMonthYear, maturityMonthYear);
        printTagValue(b, TagNum.MaturityDay, maturityDay);
        printTagValue(b, TagNum.PutOrCall, putOrCall);
        printTagValue(b, TagNum.StrikePrice, strikePrice);
        printTagValue(b, TagNum.OptAttribute, optAttribute);
        printTagValue(b, TagNum.ContractMultiplier, contractMultiplier);
        printTagValue(b, TagNum.CouponRate, couponRate);
        printTagValue(b, TagNum.SecurityExchange, securityExchange);
        printTagValue(b, TagNum.Issuer, issuer);
        printTagValue(b, TagNum.EncodedIssuerLen, encodedIssuerLen);
        printTagValue(b, TagNum.EncodedIssuer, encodedIssuer);
        printTagValue(b, TagNum.SecurityDesc, securityDesc);
        printTagValue(b, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
        printTagValue(b, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
        printTagValue(b, TagNum.PrevClosePx, prevClosePx);
        printTagValue(b, TagNum.QuoteRequestType, quoteRequestType);
        printTagValue(b, TagNum.QuoteType, quoteType);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printDateTagValue(b, TagNum.TradeOriginationDate, tradeOriginationDate);
        printTagValue(b, TagNum.NoRateSources, noRateSources);
        printTagValue(b, rateSources);
        printTagValue(b, stipulations);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.QuantityType, quantityType);
        printTagValue(b, TagNum.QtyType, qtyType);
        printTagValue(b, orderQtyData);
        printTagValue(b, TagNum.OrderQty, orderQty);
        printTagValue(b, TagNum.CashOrderQty, cashOrderQty);
        printTagValue(b, TagNum.MinQty, minQty);
        printTagValue(b, TagNum.SettlType, settlType);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.OrdType, ordType);
        printDateTagValue(b, TagNum.SettlDate2, settlDate2);
        printTagValue(b, TagNum.OrderQty2, orderQty2);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.AccountType, accountType);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, legQuoteSymbolGroups);
        printTagValue(b, TagNum.NoQuoteQualifiers, noQuoteQualifiers);
        printTagValue(b, quoteQualifierGroups);
        printTagValue(b, TagNum.QuotePriceType, quotePriceType);
        printUTCDateTimeTagValue(b, TagNum.ValidUntilTime, validUntilTime);
        printTagValue(b, spreadOrBenchmarkCurveData);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.Price2, price2);
        printTagValue(b, yieldData);
        printTagValue(b, parties);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
