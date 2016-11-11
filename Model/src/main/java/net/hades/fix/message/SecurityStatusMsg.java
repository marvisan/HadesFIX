/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusMsg.java
 *
 * $Id: SecurityStatusMsg.java,v 1.2 2011-04-28 10:07:47 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;

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

import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.type.Adjustment;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CorporateAction;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.HaltReason;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityTradingEvent;
import net.hades.fix.message.type.SecurityTradingStatus;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Security Status message provides for the ability to report changes in status to a security. The Security
 * Status message contains fields to indicate trading status, corporate actions, financial status of the company. The
 * Security Status message is used by one trading entity (for instance an exchange) to report changes in the state of
 * a security.<br/>
 * It is expected that the Security Status message that is sent as a response should indicate what type of request is
 * being provided. If the message is being generated as a result of a RequestType =1, then the response should
 * have a RequestType=1 to permit the requestor to determine why the message was sent.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SecurityStatusMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityStatusReqID.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.Currency.getValue(),
        TagNum.MarketID.getValue(),
        TagNum.MarketSegmentID.getValue(),
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
        TagNum.MDBookType.getValue(),
        TagNum.MarketDepth.getValue(),
        TagNum.BuyVolume.getValue(),
        TagNum.SellVolume.getValue(),
        TagNum.HighPx.getValue(),
        TagNum.LowPx.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.Adjustment.getValue(),
        TagNum.FirstPx.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Symbol.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * Starting with 5.0SP1 version.
     */
    protected ApplicationSequenceControl applicationSequenceControl;

    /**
     * TagNum = 324. Starting with 4.2 version.
     */
    protected String securityStatusReqID;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentExtension instrumentExtension;

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;

    /**
     * TagNum = 555. Starting with 4.3 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.3 version.
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
     * TagNum = 15. Starting with 4.2 version.
     */
    protected Currency currency;

    /**
     * TagNum = 1301. Starting with 5.0SP1 version.
     */
    protected String marketID;

    /**
     * TagNum = 1300. Starting with 5.0SP1 version.
     */
    protected String marketSegmentID;

    /**
     * TagNum = 336. Starting with 4.2 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 325. Starting with 4.2 version.
     */
    protected Boolean unsolicitedIndicator;

    /**
     * TagNum = 326. Starting with 4.2 version.
     */
    protected SecurityTradingStatus securityTradingStatus;

    /**
     * TagNum = 326. Starting with 5.0SP1 version.
     */
    protected SecurityTradingEvent securityTradingEvent;

    /**
     * TagNum = 625. Starting with 4.2 version.
     */
    protected String financialStatus;

    /**
     * TagNum = 292. Starting with 5.0SP1 version.
     */
    protected CorporateAction corporateAction;

    /**
     * TagNum = 327. Starting with 4.2 version.
     */
    protected HaltReason haltReason;

    /**
     * TagNum = 328. Starting with 4.2 version.
     */
    protected Boolean inViewOfCommon;

    /**
     * TagNum = 329. Starting with 4.2 version.
     */
    protected Boolean dueToRelated;

    /**
     * TagNum = 1021. Starting with 5.0SP1 version.
     */
    protected MDBookType MDBookType;

    /**
     * TagNum = 264. Starting with 5.0SP1 version.
     */
    protected Integer marketDepth;

    /**
     * TagNum = 330. Starting with 4.2 version.
     */
    protected Double buyVolume;

    /**
     * TagNum = 331. Starting with 4.2 version.
     */
    protected Double sellVolume;

    /**
     * TagNum = 332. Starting with 4.2 version.
     */
    protected Double highPx;

    /**
     * TagNum = 333. Starting with 4.2 version.
     */
    protected Double lowPx;

    /**
     * TagNum = 31. Starting with 4.2 version.
     */
    protected Double lastPx;

    /**
     * TagNum = 60. Starting with 5.0SP2 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 334. Starting with 4.2 version.
     */
    protected Adjustment adjustment;

    /**
     * TagNum = 1025. Starting with 5.0 version.
     */
    protected Double firstPx;

    /**
     * TagNum = 58. Starting with 4.2 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.2 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.2 version.
     */
    protected byte[] encodedText;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityStatusMsg() {
        super();
    }

    public SecurityStatusMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SecurityStatusMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.SecurityStatus.getValue(), beginString);
    }

    public SecurityStatusMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.SecurityStatus.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="5.0SP1")
    public ApplicationSequenceControl getApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the ApplicationSequenceControl component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Clear the ApplicationSequenceControl component.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SecurityStatusReqID)
    public String getSecurityStatusReqID() {
        return securityStatusReqID;
    }

    /**
     * Message field setter.
     * @param securityStatusReqID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SecurityStatusReqID)
    public void setSecurityStatusReqID(String securityStatusReqID) {
        this.securityStatusReqID = securityStatusReqID;
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
     * Message field setter.
     */
    @FIXVersion(introduced="4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.3")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentExtension getInstrumentExtension() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.4")
    public void setInstrumentExtension() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.4")
    public void clearInstrumentExtension() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public Integer getNoUnderlyings() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UnderlyingInstrument} components. It will also create an array
     * of {@link UnderlyingInstrument} objects and set the <code>underlyingInstruments</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingInstruments</code> array they will be discarded.<br/>
     * @param noUnderlyings number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
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
    @FIXVersion(introduced = "4.4")
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
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingInstrument} objects from the <code>underlyingInstruments</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyings</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrumentLeg} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
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
    @FIXVersion(introduced = "4.3")
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
    @FIXVersion(introduced = "4.3")
    public InstrumentLeg deleteInstrumentLeg(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrumentLeg} objects from the <code>instrumentLegs</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.3")
    public int clearInstrumentLegs() {
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
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        return getSafeInstrument().getSecurityType();
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        getSafeInstrument().setSecurityType(securityType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public String getMaturityMonthYear() {
        return getSafeInstrument().getMaturityMonthYear();
    }

    /**
     * Message field setter.
     * @param maturityMonthYear field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public void setMaturityMonthYear(String maturityMonthYear) {
        getSafeInstrument().setMaturityMonthYear(maturityMonthYear);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public Integer getMaturityDay() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maturityDay field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public void setMaturityDay(Integer maturityDay) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public PutOrCall getPutOrCall() {
        return getSafeInstrument().getPutOrCall();
    }

    /**
     * Message field setter.
     * @param putOrCall field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public void setPutOrCall(PutOrCall putOrCall) {
        getSafeInstrument().setPutOrCall(putOrCall);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public Double getStrikePrice() {
        return getSafeInstrument().getStrikePrice();
    }

    /**
     * Message field setter.
     * @param strikePrice field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
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
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public Character getOptAttribute() {
        return getSafeInstrument().getOptAttribute();
    }

    /**
     * Message field setter.
     * @param optAttribute field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public void setOptAttribute(Character optAttribute) {
        getSafeInstrument().setOptAttribute(optAttribute);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public String getSecurityExchange() {
        return getSafeInstrument().getSecurityExchange();
    }

    /**
     * Message field setter.
     * @param securityExchange field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
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
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketID)
    public String getMarketID() {
        return marketID;
    }

    /**
     * Message field setter.
     * @param marketID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketID)
    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public String getMarketSegmentID() {
        return marketSegmentID;
    }

    /**
     * Message field setter.
     * @param marketSegmentID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public void setMarketSegmentID(String marketSegmentID) {
        this.marketSegmentID = marketSegmentID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.UnsolicitedIndicator)
    public Boolean getUnsolicitedIndicator() {
        return unsolicitedIndicator;
    }

    /**
     * Message field setter.
     * @param unsolicitedIndicator field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.UnsolicitedIndicator)
    public void setUnsolicitedIndicator(Boolean unsolicitedIndicator) {
        this.unsolicitedIndicator = unsolicitedIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SecurityTradingStatus)
    public SecurityTradingStatus getSecurityTradingStatus() {
        return securityTradingStatus;
    }

    /**
     * Message field setter.
     * @param securityTradingStatus field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SecurityTradingStatus)
    public void setSecurityTradingStatus(SecurityTradingStatus securityTradingStatus) {
        this.securityTradingStatus = securityTradingStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.SecurityTradingEvent)
    public SecurityTradingEvent getSecurityTradingEvent() {
        return securityTradingEvent;
    }

    /**
     * Message field setter.
     * @param securityTradingEvent field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.SecurityTradingEvent)
    public void setSecurityTradingEvent(SecurityTradingEvent securityTradingEvent) {
        this.securityTradingEvent = securityTradingEvent;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.FinancialStatus)
    public String getFinancialStatus() {
        return financialStatus;
    }

    /**
     * Message field setter.
     * @param financialStatus field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.FinancialStatus)
    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.CorporateAction)
    public CorporateAction getCorporateAction() {
        return corporateAction;
    }

    /**
     * Message field setter.
     * @param corporateAction field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.CorporateAction)
    public void setCorporateAction(CorporateAction corporateAction) {
        this.corporateAction = corporateAction;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.HaltReason)
    public HaltReason getHaltReason() {
        return haltReason;
    }

    /**
     * Message field setter.
     * @param haltReason field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.HaltReason)
    public void setHaltReason(HaltReason haltReason) {
        this.haltReason = haltReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.InViewOfCommon)
    public Boolean getInViewOfCommon() {
        return inViewOfCommon;
    }

    /**
     * Message field setter.
     * @param inViewOfCommon field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.InViewOfCommon)
    public void setInViewOfCommon(Boolean inViewOfCommon) {
        this.inViewOfCommon = inViewOfCommon;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DueToRelated)
    public Boolean getDueToRelated() {
        return dueToRelated;
    }

    /**
     * Message field setter.
     * @param dueToRelated field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DueToRelated)
    public void setDueToRelated(Boolean dueToRelated) {
        this.dueToRelated = dueToRelated;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MDBookType)
    public MDBookType getMDBookType() {
        return MDBookType;
    }

    /**
     * Message field setter.
     * @param MDBookType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MDBookType)
    public void setMDBookType(MDBookType MDBookType) {
        this.MDBookType = MDBookType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketDepth)
    public Integer getMarketDepth() {
        return marketDepth;
    }

    /**
     * Message field setter.
     * @param marketDepth field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketDepth)
    public void setMarketDepth(Integer marketDepth) {
        this.marketDepth = marketDepth;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BuyVolume)
    public Double getBuyVolume() {
        return buyVolume;
    }

    /**
     * Message field setter.
     * @param buyVolume field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BuyVolume)
    public void setBuyVolume(Double buyVolume) {
        this.buyVolume = buyVolume;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SellVolume)
    public Double getSellVolume() {
        return sellVolume;
    }

    /**
     * Message field setter.
     * @param sellVolume field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SellVolume)
    public void setSellVolume(Double sellVolume) {
        this.sellVolume = sellVolume;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.HighPx)
    public Double getHighPx() {
        return highPx;
    }

    /**
     * Message field setter.
     * @param highPx field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.HighPx)
    public void setHighPx(Double highPx) {
        this.highPx = highPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LowPx)
    public Double getLowPx() {
        return lowPx;
    }

    /**
     * Message field setter.
     * @param lowPx field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LowPx)
    public void setLowPx(Double lowPx) {
        this.lowPx = lowPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LastPx)
    public Double getLastPx() {
        return lastPx;
    }

    /**
     * Message field setter.
     * @param lastPx field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LastPx)
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Adjustment)
    public Adjustment getAdjustment() {
        return adjustment;
    }

    /**
     * Message field setter.
     * @param adjustment field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Adjustment)
    public void setAdjustment(Adjustment adjustment) {
        this.adjustment = adjustment;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FirstPx)
    public Double getFirstPx() {
        return firstPx;
    }

    /**
     * Message field setter.
     * @param firstPx field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FirstPx)
    public void setFirstPx(Double firstPx) {
        this.firstPx = firstPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (instrument == null || instrument.getSymbol() == null || instrument.getSymbol().trim().isEmpty()) {
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
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (applicationSequenceControl != null) {
                bao.write(applicationSequenceControl.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.SecurityStatusReqID, securityStatusReqID);
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (instrumentExtension != null) {
                bao.write(instrumentExtension.encode(MsgSecureType.ALL_FIELDS));
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoUnderlyings.getValue(), error);
                }
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
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
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.MarketID, marketID);
            TagEncoder.encode(bao, TagNum.MarketSegmentID, marketSegmentID);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            TagEncoder.encode(bao, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
            if (securityTradingStatus != null) {
                TagEncoder.encode(bao, TagNum.SecurityTradingStatus, securityTradingStatus.getValue());
            }
            if (securityTradingEvent != null) {
                TagEncoder.encode(bao, TagNum.SecurityTradingEvent, securityTradingEvent.getValue());
            }
            TagEncoder.encode(bao, TagNum.FinancialStatus, financialStatus);
            if (corporateAction != null) {
                TagEncoder.encode(bao, TagNum.CorporateAction, corporateAction.getValue());
            }
            if (haltReason != null) {
                TagEncoder.encode(bao, TagNum.HaltReason, haltReason.getValue());
            }
            TagEncoder.encode(bao, TagNum.InViewOfCommon, inViewOfCommon);
            TagEncoder.encode(bao, TagNum.DueToRelated, dueToRelated);
            if (MDBookType != null) {
                TagEncoder.encode(bao, TagNum.MDBookType, MDBookType.getValue());
            }
            TagEncoder.encode(bao, TagNum.MarketDepth, marketDepth);
            TagEncoder.encode(bao, TagNum.BuyVolume, buyVolume);
            TagEncoder.encode(bao, TagNum.SellVolume, sellVolume);
            TagEncoder.encode(bao, TagNum.HighPx, highPx);
            TagEncoder.encode(bao, TagNum.LowPx, lowPx);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            if (adjustment != null) {
                TagEncoder.encode(bao, TagNum.Adjustment, adjustment.getValue());
            }
            TagEncoder.encode(bao, TagNum.FirstPx, firstPx);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
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
            case SecurityStatusReqID:
                securityStatusReqID = new String(tag.value, sessionCharset);
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
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

            case SecurityDesc:
                securityDesc = new String(tag.value, sessionCharset);
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case MarketID:
                marketID = new String(tag.value, sessionCharset);
                break;

            case MarketSegmentID:
                marketSegmentID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case UnsolicitedIndicator:
                unsolicitedIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case SecurityTradingStatus:
                securityTradingStatus = SecurityTradingStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SecurityTradingEvent:
                securityTradingEvent = SecurityTradingEvent.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case FinancialStatus:
                financialStatus = new String(tag.value, sessionCharset);
                break;

            case CorporateAction:
                corporateAction = CorporateAction.valueFor((new String(tag.value, sessionCharset)).charAt(0));
                break;

            case HaltReason:
                haltReason = HaltReason.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case InViewOfCommon:
                inViewOfCommon = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case DueToRelated:
                dueToRelated = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case MDBookType:
                MDBookType = MDBookType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MarketDepth:
                marketDepth = new Integer(new String(tag.value, sessionCharset));
                break;

            case BuyVolume:
                buyVolume = new Double(new String(tag.value, getSessionCharset()));
                break;

            case SellVolume:
                sellVolume = new Double(new String(tag.value, getSessionCharset()));
                break;

            case HighPx:
                highPx = new Double(new String(tag.value, getSessionCharset()));
                break;

            case LowPx:
                lowPx = new Double(new String(tag.value, getSessionCharset()));
                break;

            case LastPx:
                lastPx = new Double(new String(tag.value, getSessionCharset()));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Adjustment:
                adjustment = Adjustment.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case FirstPx:
                firstPx = new Double(new String(tag.value, getSessionCharset()));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SecurityStatusMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedTextLen.getValue()) {
            try {
                encodedTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
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
        StringBuilder b = new StringBuilder("{SecurityStatusMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.SecurityStatusReqID, securityStatusReqID);
        printTagValue(b, instrument);
        printTagValue(b, instrumentExtension);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
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
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.MarketID, marketID);
        printTagValue(b, TagNum.MarketSegmentID, marketSegmentID);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
        printTagValue(b, TagNum.SecurityTradingStatus, securityTradingStatus);
        printTagValue(b, TagNum.SecurityTradingEvent, securityTradingEvent);
        printTagValue(b, TagNum.FinancialStatus, financialStatus);
        printTagValue(b, TagNum.CorporateAction, corporateAction);
        printTagValue(b, TagNum.HaltReason, haltReason);
        printTagValue(b, TagNum.InViewOfCommon, inViewOfCommon);
        printTagValue(b, TagNum.DueToRelated, dueToRelated);
        printTagValue(b, TagNum.MDBookType, MDBookType);
        printTagValue(b, TagNum.MarketDepth, marketDepth);
        printTagValue(b, TagNum.BuyVolume, buyVolume);
        printTagValue(b, TagNum.SellVolume, sellVolume);
        printTagValue(b, TagNum.HighPx, highPx);
        printTagValue(b, TagNum.LowPx, lowPx);
        printTagValue(b, TagNum.LastPx, lastPx);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.Adjustment, adjustment);
        printTagValue(b, TagNum.FirstPx, firstPx);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
