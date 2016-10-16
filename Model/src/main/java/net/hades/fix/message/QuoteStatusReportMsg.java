/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusReportMsg.java
 *
 * $Id: QuoteStatusReportMsg.java,v 1.12 2011-04-28 10:07:45 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.LegQuoteStatusGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.TargetParties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteQualifierGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The quote status report message is used:
 * <ul>
 *      <li>as the response to a Quote Status Request message</li>
 *      <li>as a response to a Quote Cancel message</li>
 *      <li>as a response to a Quote Response message in a negotiation dialog</li>
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class QuoteStatusReportMsg extends FIXMsg  {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -832459965761121559L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteStatusReqID.getValue(),
        TagNum.QuoteReqID.getValue(),
        TagNum.QuoteID.getValue(),
        TagNum.QuoteMsgID.getValue(),
        TagNum.QuoteRespID.getValue(),
        TagNum.QuoteType.getValue(),
        TagNum.QuoteCancelType.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.Side.getValue(),
        TagNum.BidPx.getValue(),
        TagNum.OfferPx.getValue(),
        TagNum.MktBidPx.getValue(),
        TagNum.MktOfferPx.getValue(),
        TagNum.MinBidSize.getValue(),
        TagNum.BidSize.getValue(),
        TagNum.MinOfferSize.getValue(),
        TagNum.OfferSize.getValue(),
        TagNum.MinQty.getValue(),
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
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.SettlDate2.getValue(),
        TagNum.OrderQty2.getValue(),
        TagNum.BidForwardPoints2.getValue(),
        TagNum.OfferForwardPoints2.getValue(),
        TagNum.Currency.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.NoQuoteQualifiers.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.Price.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.SettlCurrBidFxRate.getValue(),
        TagNum.SettlCurrOfferFxRate.getValue(),
        TagNum.SettlCurrFxRateCalc.getValue(),
        TagNum.CommType.getValue(),
        TagNum.Commission.getValue(),
        TagNum.CustOrderCapacity.getValue(),
        TagNum.ExDestination.getValue(),
        TagNum.ExDestinationIDSource.getValue(),
        TagNum.BookingType.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.QuoteStatus.getValue(),
        TagNum.QuoteRejectReason.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteID.getValue()
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 649. Starting with 4.3 version.
     */
    protected String quoteStatusReqID;

    /**
     * TagNum = 131. Starting with 4.3 version.
     */
    protected String quoteReqID;

    /**
     * TagNum = 117 REQUIRED. Starting with 4.3 version.
     */
    protected String quoteID;

    /**
     * TagNum = 1166. Starting with 5.0SP1 version.
     */
    protected String quoteMsgID;

    /**
     * TagNum = 693. Starting with 4.4 version.
     */
    protected String quoteRespID;

    /**
     * TagNum = 537. Starting with 4.3 version.
     */
    protected QuoteType quoteType;

    /**
     * TagNum = 298. Starting with 5.0SP1 version.
     */
    protected Integer quoteCancelType;

    /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

    /**
     * Starting with 5.0SP2 version.
     */
    protected TargetParties targetParties;

    /**
     * TagNum = 1. Starting with 4.3 version.
     */
    protected String account;

    /**
     * TagNum = 660. Starting with 4.4 version.
     */
    protected AcctIDSource acctIDSource;

    /**
     * TagNum = 581. Starting with 4.3 version.
     */
    protected AccountType accountType;

    /**
     * TagNum = 336. Starting with 4.3 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * Starting with 4.4 version.
     */
    protected FinancingDetails financingDetails;
    
    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;
    
    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;

    /**
     * TagNum = 54. Starting with 4.3 version.
     */
    protected Side side;

    /**
     * Starting with 4.4 version.
     */
    protected OrderQtyData orderQtyData;

    /**
     * TagNum = 132. Starting with 4.3 version.
     */
    protected Double bidPx;

    /**
     * TagNum = 133. Starting with 4.3 version.
     */
    protected Double offerPx;

    /**
     * TagNum = 645. Starting with 4.3 version.
     */
    protected Double mktBidPx;

    /**
     * TagNum = 646. Starting with 4.3 version.
     */
    protected Double mktOfferPx;

    /**
     * TagNum = 647. Starting with 4.3 version.
     */
    protected Double minBidSize;

    /**
     * TagNum = 134. Starting with 4.3 version.
     */
    protected Double bidSize;

    /**
     * TagNum = 648. Starting with 4.3 version.
     */
    protected Double minOfferSize;

    /**
     * TagNum = 135. Starting with 4.3 version.
     */
    protected Double offerSize;

    /**
     * TagNum = 110. Starting with 5.0SP1 version.
     */
    protected Double minQty;

    /**
     * TagNum = 62. Starting with 4.3 version.
     */
    protected Date validUntilTime;

    /**
     * TagNum = 188. Starting with 4.3 version.
     */
    protected Double bidSpotRate;

    /**
     * TagNum = 190. Starting with 4.3 version.
     */
    protected Double offerSpotRate;

    /**
     * TagNum = 189. Starting with 4.3 version.
     */
    protected Double bidForwardPoints;

    /**
     * TagNum = 191. Starting with 4.3 version.
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
     * TagNum = 60. Starting with 4.3 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 63. Starting with 4.4 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 4.3 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 40. Starting with 4.3 version.
     */
    protected OrdType ordType;

    /**
     * TagNum = 193. Starting with 4.3 version.
     */
    protected Date settlDate2;

    /**
     * TagNum = 192. Starting with 4.3 version.
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
     * TagNum = 15. Starting with 4.3 version.
     */
    protected Currency currency;

    /**
     * Starting with 4.4 version.
     */
    protected Stipulations stipulations;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected LegQuoteStatusGroup[] legQuoteStatusGroups;

    /**
     * TagNum = 735. Starting with 4.4 version.
     */
    protected Integer noQuoteQualifiers;

    /**
     * Starting with 4.4 version.
     */
    protected QuoteQualifierGroup[] quoteQualifierGroups;

    /**
     * TagNum = 126. Starting with 4.4 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 44. Starting with 4.4 version.
     */
    protected Double price;

    /**
     * TagNum = 423. Starting with 4.4 version.
     */
    protected PriceType priceType;

    /**
     * Starting with 4.4 version.
     */
    protected SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData;

    /**
     * Starting with 4.4 version.
     */
    protected YieldData yieldData;

    /**
     * TagNum = 656. Starting with 4.3 version.
     */
    protected Double settlCurrBidFxRate;

    /**
     * TagNum = 657. Starting with 4.3 version.
     */
    protected Double settlCurrOfferFxRate;

    /**
     * TagNum = 156. Starting with 4.3 version.
     */
    protected SettlCurrFxRateCalc settlCurrFxRateCalc;

    /**
     * TagNum = 13. Starting with 4.3 version.
     */
    protected CommType commType;

    /**
     * TagNum = 12. Starting with 4.3 version.
     */
    protected Double commission;

    /**
     * TagNum = 582. Starting with 4.3 version.
     */
    protected CustOrderCapacity custOrderCapacity;

    /**
     * TagNum = 100. Starting with 4.3 version.
     */
    protected String exDestination;

    /**
     * TagNum = 1133. Starting with 5.0 version.
     */
    protected ExDestinationIDSource exDestinationIDSource;

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
     * TagNum = 297. Starting with 4.3 version.
     */
    protected QuoteStatus quoteStatus;

    /**
     * TagNum = 300. Starting with 5.0SP2 version.
     */
    protected Integer quoteRejectReason;

    /**
     * TagNum = 58. Starting with 4.4 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.4 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.4 version.
     */
    protected byte[] encodedText;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteStatusReportMsg() {
        super();
    }
    
    public QuoteStatusReportMsg(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public QuoteStatusReportMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.QuoteStatusReport.getValue(), beginString);
    }
    
    public QuoteStatusReportMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.QuoteStatusReport.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteStatusReqID)
    public String getQuoteStatusReqID() {
        return quoteStatusReqID;
    }

    /**
     * Message field setter.
     * @param quoteStatusReqID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteStatusReqID)
    public void setQuoteStatusReqID(String quoteStatusReqID) {
        this.quoteStatusReqID = quoteStatusReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteReqID)
    public String getQuoteReqID() {
        return quoteReqID;
    }

    /**
     * Message field setter.
     * @param quoteReqID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteReqID)
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteID, required=true)
    public String getQuoteID() {
        return quoteID;
    }

    /**
     * Message field setter.
     * @param quoteID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteID, required=true)
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteMsgID)
    public String getQuoteMsgID() {
        return quoteMsgID;
    }

    /**
     * Message field setter.
     * @param quoteMsgID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteMsgID)
    public void setQuoteMsgID(String quoteMsgID) {
        this.quoteMsgID = quoteMsgID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QuoteRespID, required=true)
    public String getQuoteRespID() {
        return quoteRespID;
    }

    /**
     * Message field setter.
     * @param quoteRespID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QuoteRespID, required=true)
    public void setQuoteRespID(String quoteRespID) {
        this.quoteRespID = quoteRespID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteType)
    public QuoteType getQuoteType() {
        return quoteType;
    }

    /**
     * Message field setter.
     * @param quoteType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteType)
    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteCancelType)
    public Integer getQuoteCancelType() {
        return quoteCancelType;
    }

    /**
     * Message field setter.
     * @param quoteCancelType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteCancelType)
    public void setQuoteCancelType(Integer quoteCancelType) {
        this.quoteCancelType = quoteCancelType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    public TargetParties getTargetParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TargetParties component if used in this message to the proper implementation class.
     */
    @FIXVersion(introduced="5.0SP2")
    public void setTargetParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TargetParties component to null.
     */
    @FIXVersion(introduced="5.0SP2")
    public void clearTargetParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Account)
    public String getAccount() {
        return account;
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Account)
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    /**
     * Message field setter.
     * @param acctIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.AccountType)
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Message field setter.
     * @param accountType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.AccountType)
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
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
    @FIXVersion(introduced = "4.3")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Instrument component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Instrument component to null.
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
     * Sets the FinancingDetails component to null.
     */
    @FIXVersion(introduced = "4.4")
    public void clearFinancingDetails() {
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
     * of {@link UnderlyingInstrument} objects and set the <code>underlyingInstruments</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingInstruments</code> array they will be discarded.<br/>
     * @param noUnderlyings field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public void setNoUnderlyings(Integer noUnderlyings) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter for {@link UnderlyingInstrument} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
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
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Side)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Side)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public OrderQtyData getOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the OrderQtyData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the OrderQtyData component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidPx, condRequired=true)
    public Double getBidPx() {
        return bidPx;
    }

    /**
     * Message field setter.
     * @param bidPx field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BidPx, condRequired=true)
    public void setBidPx(Double bidPx) {
        this.bidPx = bidPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferPx, condRequired=true)
    public Double getOfferPx() {
        return offerPx;
    }

    /**
     * Message field setter.
     * @param offerPx field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OfferPx, condRequired=true)
    public void setOfferPx(Double offerPx) {
        this.offerPx = offerPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MktBidPx)
    public Double getMktBidPx() {
        return mktBidPx;
    }

    /**
     * Message field setter.
     * @param mktBidPx field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MktBidPx)
    public void setMktBidPx(Double mktBidPx) {
        this.mktBidPx = mktBidPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MktOfferPx)
    public Double getMktOfferPx() {
        return mktOfferPx;
    }

    /**
     * Message field setter.
     * @param mktOfferPx field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MktOfferPx)
    public void setMktOfferPx(Double mktOfferPx) {
        this.mktOfferPx = mktOfferPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MinBidSize)
    public Double getMinBidSize() {
        return minBidSize;
    }

    /**
     * Message field setter.
     * @param minBidSize field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MinBidSize)
    public void setMinBidSize(Double minBidSize) {
        this.minBidSize = minBidSize;
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
    @TagNumRef(tagNum=TagNum.MinOfferSize)
    public Double getMinOfferSize() {
        return minOfferSize;
    }

    /**
     * Message field setter.
     * @param minOfferSize field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MinOfferSize)
    public void setMinOfferSize(Double minOfferSize) {
        this.minOfferSize = minOfferSize;
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
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MinQty)
    public Double getMinQty() {
        return minQty;
    }

    /**
     * Message field setter.
     * @param minQty field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MinQty)
    public void setMinQty(Double minQty) {
        this.minQty = minQty;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlType)
    public String getSettlType() {
        return settlType;
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlType)
    public void setSettlType(String settlType) {
        this.settlType = settlType;
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
    @FIXVersion(introduced="4.4")
    public Stipulations getStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public Integer getNoLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link LegQuoteStatusGroup} groups. It will also create an array
     * of {@link LegQuoteStatusGroup} objects and set the <code>legQuoteStatusGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>legQuoteStatusGroups</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public LegQuoteStatusGroup[] getLegQuoteStatusGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link LegQuoteStatusGroup} object to the existing array of <code>legQuoteStatusGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>legQuoteStatusGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public LegQuoteStatusGroup addLegQuoteStatusGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link LegQuoteStatusGroup} object from the existing array of <code>legQuoteStatusGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public LegQuoteStatusGroup deleteLegQuoteStatusGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link LegQuoteStatusGroup} objects from the <code>legQuoteStatusGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearLegQuoteStatusGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoQuoteQualifiers)
    public void setNoQuoteQualifiers(Integer noQuoteQualifiers) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link QuoteQualifierGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
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
    @FIXVersion(introduced="4.4")
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
    @FIXVersion(introduced="4.4")
    public QuoteQualifierGroup deleteQuoteQualifierGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link QuoteQualifierGroup} objects from the <code>quoteQualifierGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noQuoteQualifiers</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearQuoteQualifierGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.ExpireTime)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Price)
    public Double getPrice() {
        return price;
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Price)
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PriceType)
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * Message field setter.
     * @param priceType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PriceType)
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public YieldData getYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the YieldData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the YieldData component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrBidFxRate)
    public Double getSettlCurrBidFxRate() {
        return settlCurrBidFxRate;
    }

    /**
     * Message field setter.
     * @param settlCurrBidFxRate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrBidFxRate)
    public void setSettlCurrBidFxRate(Double settlCurrBidFxRate) {
        this.settlCurrBidFxRate = settlCurrBidFxRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrOfferFxRate)
    public Double getSettlCurrOfferFxRate() {
        return settlCurrOfferFxRate;
    }

    /**
     * Message field setter.
     * @param settlCurrOfferFxRate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrOfferFxRate)
    public void setSettlCurrOfferFxRate(Double settlCurrOfferFxRate) {
        this.settlCurrOfferFxRate = settlCurrOfferFxRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRateCalc)
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    /**
     * Message field setter.
     * @param settlCurrFxRateCalc field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRateCalc)
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public CommType getCommType() {
        return commType;
    }

    /**
     * Message field setter.
     * @param commType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public void setCommType(CommType commType) {
        this.commType = commType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public Double getCommission() {
        return commission;
    }

    /**
     * Message field setter.
     * @param commission field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public void setCommission(Double commission) {
        this.commission = commission;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CustOrderCapacity)
    public CustOrderCapacity getCustOrderCapacity() {
        return custOrderCapacity;
    }

    /**
     * Message field setter.
     * @param custOrderCapacity field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CustOrderCapacity)
    public void setCustOrderCapacity(CustOrderCapacity custOrderCapacity) {
        this.custOrderCapacity = custOrderCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExDestination)
    public String getExDestination() {
        return exDestination;
    }

    /**
     * Message field setter.
     * @param exDestination field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExDestination)
    public void setExDestination(String exDestination) {
        this.exDestination = exDestination;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ExDestinationIDSource)
    public ExDestinationIDSource getExDestinationIDSource() {
        return exDestinationIDSource;
    }

    /**
     * Message field setter.
     * @param exDestinationIDSource field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ExDestinationIDSource)
    public void setExDestinationIDSource(ExDestinationIDSource exDestinationIDSource) {
        this.exDestinationIDSource = exDestinationIDSource;
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteStatus)
    public QuoteStatus getQuoteStatus() {
        return quoteStatus;
    }

    /**
     * Message field setter.
     * @param quoteStatus field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteStatus)
    public void setQuoteStatus(QuoteStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.QuoteRejectReason)
    public Integer getQuoteRejectReason() {
        return quoteRejectReason;
    }

    /**
     * Message field setter.
     * @param quoteRejectReason field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.QuoteRejectReason)
    public void setQuoteRejectReason(Integer quoteRejectReason) {
        this.quoteRejectReason = quoteRejectReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.4")
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
        // no default validation
    }
    
    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, TagNum.QuoteStatusReqID, quoteStatusReqID);
            TagEncoder.encode(bao, TagNum.QuoteReqID, quoteReqID);
            TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            TagEncoder.encode(bao, TagNum.QuoteMsgID, quoteMsgID);
            TagEncoder.encode(bao, TagNum.QuoteRespID, quoteRespID);
            if (quoteType != null) {
                TagEncoder.encode(bao, TagNum.QuoteType, quoteType.getValue());
            }
            TagEncoder.encode(bao, TagNum.QuoteCancelType, quoteCancelType);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (targetParties != null) {
                bao.write(targetParties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Account, account);
            if (acctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (accountType != null) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                        TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.BidPx, bidPx);
            TagEncoder.encode(bao, TagNum.OfferPx, offerPx);
            TagEncoder.encode(bao, TagNum.MktBidPx, mktBidPx);
            TagEncoder.encode(bao, TagNum.MktOfferPx, mktOfferPx);
            TagEncoder.encode(bao, TagNum.MinBidSize, minBidSize);
            TagEncoder.encode(bao, TagNum.BidSize, bidSize);
            TagEncoder.encode(bao, TagNum.MinOfferSize, minOfferSize);
            TagEncoder.encode(bao, TagNum.OfferSize, offerSize);
            TagEncoder.encode(bao, TagNum.MinQty, minQty);
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
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
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
            if (stipulations != null) {
                bao.write(stipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (legQuoteStatusGroups != null && legQuoteStatusGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (legQuoteStatusGroups[i] != null) {
                            bao.write(legQuoteStatusGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "LegQuoteStatusGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                        TagNum.NoLegs.getValue(), error);
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
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            TagEncoder.encode(bao, TagNum.Price, price);
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.SettlCurrBidFxRate, settlCurrBidFxRate);
            TagEncoder.encode(bao, TagNum.SettlCurrOfferFxRate, settlCurrOfferFxRate);
            if (settlCurrFxRateCalc != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc.getValue());
            }
            if (commType != null) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
            }
            TagEncoder.encode(bao, TagNum.Commission, commission);
            if (custOrderCapacity != null) {
                TagEncoder.encode(bao, TagNum.CustOrderCapacity, custOrderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.ExDestination, exDestination);
            if (exDestinationIDSource != null) {
                TagEncoder.encode(bao, TagNum.ExDestinationIDSource, exDestinationIDSource.getValue());
            }
            if (bookingType != null) {
                TagEncoder.encode(bao, TagNum.BookingType, bookingType.getValue());
            }
            if (orderCapacity != null) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.OrderRestrictions, orderRestrictions);
            if (quoteStatus != null) {
                TagEncoder.encode(bao, TagNum.QuoteStatus, quoteStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.QuoteRejectReason, quoteRejectReason);
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
            case QuoteStatusReqID:
                quoteStatusReqID = new String(tag.value, sessionCharset);
                break;

            case QuoteReqID:
                quoteReqID = new String(tag.value, sessionCharset);
                break;

            case QuoteID:
                quoteID = new String(tag.value, sessionCharset);
                break;

            case QuoteMsgID:
                quoteMsgID = new String(tag.value, sessionCharset);
                break;

            case QuoteRespID:
                quoteRespID = new String(tag.value, sessionCharset);
                break;

            case QuoteType:
                quoteType = QuoteType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QuoteCancelType:
                quoteCancelType = new Integer(new String(tag.value, sessionCharset));
                break;

            case Account:
                account = new String(tag.value, sessionCharset);
                break;

            case AcctIDSource:
                acctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AccountType:
                accountType = AccountType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case Side:
                side = Side.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case BidPx:
                bidPx = new Double(new String(tag.value, sessionCharset));
                break;

            case OfferPx:
                offerPx = new Double(new String(tag.value, sessionCharset));
                break;

            case MktBidPx:
                mktBidPx = new Double(new String(tag.value, sessionCharset));
                break;

            case MktOfferPx:
                mktOfferPx = new Double(new String(tag.value, sessionCharset));
                break;

            case MinBidSize:
                minBidSize = new Double(new String(tag.value, sessionCharset));
                break;

            case BidSize:
                bidSize = new Double(new String(tag.value, sessionCharset));
                break;

            case MinOfferSize:
                minOfferSize = new Double(new String(tag.value, sessionCharset));
                break;

            case OfferSize:
                offerSize = new Double(new String(tag.value, sessionCharset));
                break;

            case MinQty:
                minQty = new Double(new String(tag.value, sessionCharset));
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

            case BidForwardPoints2:
                bidForwardPoints2 = new Double(new String(tag.value, sessionCharset));
                break;

            case OfferForwardPoints2:
                offerForwardPoints2 = new Double(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoQuoteQualifiers:
                noQuoteQualifiers = new Integer(new String(tag.value, sessionCharset));
                break;

            case ExpireTime:
                expireTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Price:
                price = new Double(new String(tag.value, sessionCharset));
                break;

            case PriceType:
                priceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SettlCurrBidFxRate:
                settlCurrBidFxRate = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlCurrOfferFxRate:
                settlCurrOfferFxRate = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlCurrFxRateCalc:
                settlCurrFxRateCalc = SettlCurrFxRateCalc.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case CommType:
                commType = CommType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Commission:
                commission = new Double(new String(tag.value, sessionCharset));
                break;

            case CustOrderCapacity:
                custOrderCapacity = CustOrderCapacity.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ExDestination:
                exDestination = new String(tag.value, sessionCharset);
                break;

            case ExDestinationIDSource:
                exDestinationIDSource = ExDestinationIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case BookingType:
                bookingType = BookingType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OrderCapacity:
                orderCapacity = OrderCapacity.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case OrderRestrictions:
                orderRestrictions = new String(tag.value, sessionCharset);
                break;

            case QuoteStatus:
                quoteStatus = QuoteStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QuoteRejectReason:
                quoteRejectReason = new Integer(new String(tag.value, sessionCharset));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case EncodedTextLen:
                encodedTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in QuoteStatusReportMsg.";
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.EncodedTextLen.getValue(), error);
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{QuoteStatusReportMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.QuoteStatusReqID, quoteStatusReqID);
        printTagValue(b, TagNum.QuoteReqID, quoteReqID);
        printTagValue(b, TagNum.QuoteID, quoteID);
        printTagValue(b, TagNum.QuoteMsgID, quoteMsgID);
        printTagValue(b, TagNum.QuoteRespID, quoteRespID);
        printTagValue(b, TagNum.QuoteType, quoteType);
        printTagValue(b, TagNum.QuoteCancelType, quoteCancelType);
        printTagValue(b, parties);
        printTagValue(b, targetParties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.AccountType, accountType);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, instrument);
        printTagValue(b, financingDetails);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, orderQtyData);
        printTagValue(b, TagNum.BidPx, bidPx);
        printTagValue(b, TagNum.OfferPx, offerPx);
        printTagValue(b, TagNum.MktBidPx, bidPx);
        printTagValue(b, TagNum.MktOfferPx, bidPx);
        printTagValue(b, TagNum.MinBidSize, minBidSize);
        printTagValue(b, TagNum.BidSize, bidSize);
        printTagValue(b, TagNum.MinOfferSize, minOfferSize);
        printTagValue(b, TagNum.OfferSize, offerSize);
        printTagValue(b, TagNum.MinQty, minQty);
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
        printTagValue(b, TagNum.SettlType, settlType);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.OrdType, ordType);
        printDateTagValue(b, TagNum.SettlDate2, settlDate2);
        printTagValue(b, TagNum.OrderQty2, orderQty2);
        printTagValue(b, TagNum.BidForwardPoints2, bidForwardPoints2);
        printTagValue(b, TagNum.OfferForwardPoints2, offerForwardPoints2);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, stipulations);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, legQuoteStatusGroups);
        printTagValue(b, TagNum.NoQuoteQualifiers, noQuoteQualifiers);
        printTagValue(b, quoteQualifierGroups);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, spreadOrBenchmarkCurveData);
        printTagValue(b, yieldData);
        printTagValue(b, TagNum.SettlCurrBidFxRate, settlCurrBidFxRate);
        printTagValue(b, TagNum.SettlCurrOfferFxRate, settlCurrOfferFxRate);
        printTagValue(b, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc);
        printTagValue(b, TagNum.CommType, commType);
        printTagValue(b, TagNum.Commission, commission);
        printTagValue(b, TagNum.CustOrderCapacity, custOrderCapacity);
        printTagValue(b, TagNum.ExDestination, exDestination);
        printTagValue(b, TagNum.ExDestinationIDSource, exDestinationIDSource);
        printTagValue(b, TagNum.OrderCapacity, orderCapacity);
        printTagValue(b, TagNum.QuoteStatus, quoteStatus);
        printTagValue(b, TagNum.QuoteRejectReason, quoteRejectReason);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
}
