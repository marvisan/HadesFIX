/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDIncGroup.java
 *
 * $Id: MDIncGroup.java,v 1.11 2011-04-21 09:45:45 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.DealingCapacity;

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
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DeleteReason;
import net.hades.fix.message.type.HaltReason;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MDOriginType;
import net.hades.fix.message.type.MDQuoteType;
import net.hades.fix.message.type.MDUpdateAction;
import net.hades.fix.message.type.MarketDepth;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.QuoteCondition;
import net.hades.fix.message.type.RateSource;
import net.hades.fix.message.type.SecurityTradingStatus;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TickDirection;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradeCondition;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * Market data incremental refresh group data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 09/10/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MDIncGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MDUpdateAction.getValue(),
        TagNum.DeleteReason.getValue(),
        TagNum.MDSubBookType.getValue(),
        TagNum.MarketDepth.getValue(),
        TagNum.MDEntryType.getValue(),
        TagNum.MDEntryID.getValue(),
        TagNum.MDEntryRefID.getValue(),
        TagNum.MDStreamID.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.FinancialStatus.getValue(),
        TagNum.CorporateAction.getValue(),
        TagNum.MDEntryPx.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.Currency.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.MDEntrySize.getValue(),
        TagNum.NoRateSources.getValue(),
        TagNum.NoOfSecSizes.getValue(),
        TagNum.LotType.getValue(),
        TagNum.MDEntryDate.getValue(),
        TagNum.MDEntryTime.getValue(),
        TagNum.TickDirection.getValue(),
        TagNum.MDMkt.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.SecurityTradingStatus.getValue(),
        TagNum.HaltReason.getValue(),
        TagNum.QuoteCondition.getValue(),
        TagNum.TradeCondition.getValue(),
        TagNum.TrdType.getValue(),
        TagNum.MatchType.getValue(),
        TagNum.MDEntryOriginator.getValue(),
        TagNum.LocationID.getValue(),
        TagNum.DeskID.getValue(),
        TagNum.OpenCloseSettlFlag.getValue(),
        TagNum.TimeInForce.getValue(),
        TagNum.ExpireDate.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.MinQty.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.SellerDays.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.QuoteEntryID.getValue(),
        TagNum.TradeID.getValue(),
        TagNum.MDEntryBuyer.getValue(),
        TagNum.MDEntrySeller.getValue(),
        TagNum.NumberOfOrders.getValue(),
        TagNum.MDEntryPositionNo.getValue(),
        TagNum.Scope.getValue(),
        TagNum.TotalVolumeTraded.getValue(),
        TagNum.TotalVolumeTradedDate.getValue(),
        TagNum.TotalVolumeTradedTime.getValue(),
        TagNum.PriceDelta.getValue(),
        TagNum.NetChgPrevDay.getValue(),
        TagNum.Text.getValue(),
        TagNum.MDPriceLevel.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.MDOriginType.getValue(),
        TagNum.HighPx.getValue(),
        TagNum.LowPx.getValue(),
        TagNum.FirstPx.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.TradeVolume.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.TransBkdTime.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.MDQuoteType.getValue(),
        TagNum.RptSeq.getValue(),
        TagNum.DealingCapacity.getValue(),
        TagNum.MDEntrySpotRate.getValue(),
        TagNum.MDEntryForwardPoints.getValue(),
        TagNum.NoStatsIndicators.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * TagNum = 279. Starting with 4.2 version.
     */
    protected MDUpdateAction mdUpdateAction;

    /**
     * TagNum = 285. Starting with 4.2 version.
     */
    protected DeleteReason deleteReason;

    /**
     * TagNum = 1173. Starting with 5.0SP1 version.
     */
    protected Integer mdSubBookType;

    /**
     * TagNum = 264. Starting with 5.0SP1 version.
     */
    protected MarketDepth marketDepth;

    /**
     * TagNum = 269. Starting with 4.2 version.
     */
    protected MDEntryType mdEntryType;

    /**
     * TagNum = 280. Starting with 4.2 version.
     */
    protected String mdEntryID;

    /**
     * TagNum = 278. Starting with 4.2 version.
     */
    protected String mdEntryRefID;

    /**
     * TagNum = 1500. Starting with 5.0SP2 version.
     */
    protected String mdStreamID;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg[] instrumentLegs;

    /**
     * TagNum = 55 REQUIRED. Starting with 4.2 version.
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
     * TagNum = 291. Starting with 4.2 version.
     */
    protected String financialStatus;

    /**
     * TagNum = 292. Starting with 4.2 version.
     */
    protected String corporateAction;

    /**
     * TagNum = 270. Starting with 4.2 version.
     */
    protected Double mdEntryPx;

    /**
     * TagNum = 423. Starting with 5.0SP1 version.
     */
    protected PriceType priceType;

    /**
     * Starting with 5.0SP1 version.
     */
    protected YieldData yieldData;

    /**
     * Starting with 5.0SP1 version.
     */
    protected SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData;

    /**
     * TagNum = 40. Starting with 5.0 version.
     */
    protected OrdType ordType;

    /**
     * TagNum = 15. Starting with 4.2 version.
     */
    protected Currency currency;

    /**
     * TagNum = 120. Starting with 5.0SP2 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 555. Starting with 5.0SP2 version.
     */
    protected Integer noRateSources;

    /**
     * Starting with 5.0SP2 version.
     */
    protected RateSourceGroup[] rateSources;

    /**
     * TagNum = 271. Starting with 4.2 version.
     */
    protected Double mdEntrySize;

    /**
     * TagNum = 1177. Starting with 5.0SP1 version.
     */
    protected Integer noOfSecSizes;

    /**
     * Starting with 5.0SP1 version.
     */
    protected MDSecSizeGroup[] mdSecSizeGroups;

    /**
     * TagNum = 1093. Starting with 5.0SP1 version.
     */
    protected LotType lotType;

    /**
     * TagNum = 272. Starting with 4.2 version.
     */
    protected Date mdEntryDate;

    /**
     * TagNum = 273. Starting with 4.2 version.
     */
    protected Date mdEntryTime;

    /**
     * TagNum = 274. Starting with 4.2 version.
     */
    protected TickDirection tickDirection;

    /**
     * TagNum = 275. Starting with 4.2 version.
     */
    protected String mdMkt;

    /**
     * TagNum = 336. Starting with 4.2 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 326. Starting with 5.0SP2 version.
     */
    protected SecurityTradingStatus securityTradingStatus;

    /**
     * TagNum = 327. Starting with 5.0SP1 version.
     */
    protected HaltReason haltReason;

    /**
     * TagNum = 276. Starting with 4.2 version.
     */
    protected QuoteCondition quoteCondition;

    /**
     * TagNum = 277. Starting with 4.2 version.
     */
    protected TradeCondition tradeCondition;

    /**
     * TagNum = 828. Starting with 5.0SP1 version.
     */
    protected TrdType trdType;

    /**
     * TagNum = 574. Starting with 5.0SP1 version.
     */
    protected MatchType matchType;

    /**
     * TagNum = 282. Starting with 4.2 version.
     */
    protected String mdEntryOriginator;

    /**
     * TagNum = 283. Starting with 4.2 version.
     */
    protected String locationID;

    /**
     * TagNum = 284. Starting with 4.2 version.
     */
    protected String deskID;

    /**
     * TagNum = 286. Starting with 4.2 version.
     */
    protected String openCloseSettlFlag;

    /**
     * TagNum = 59. Starting with 4.2 version.
     */
    protected TimeInForce timeInForce;

    /**
     * TagNum = 432. Starting with 4.2 version.
     */
    protected Date expireDate;

    /**
     * TagNum = 126. Starting with 4.2 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 110. Starting with 4.2 version.
     */
    protected Double minQty;

    /**
     * TagNum = 18. Starting with 4.2 version.
     */
    protected String execInst;

    /**
     * TagNum = 287. Starting with 4.2 version.
     */
    protected Integer sellerDays;

    /**
     * TagNum = 37. Starting with 4.2 version.
     */
    protected String orderID;

    /**
     * TagNum = 198. Starting with 5.0 version.
     */
    protected String secondaryOrderID;

    /**
     * TagNum = 299. Starting with 4.2 version.
     */
    protected String quoteEntryID;

    /**
     * TagNum = 1003. Starting with 5.0SP1 version.
     */
    protected String tradeID;

    /**
     * TagNum = 288. Starting with 4.2 version.
     */
    protected String mdEntryBuyer;

    /**
     * TagNum = 289. Starting with 4.2 version.
     */
    protected String mdEntrySeller;

    /**
     * TagNum = 346. Starting with 4.2 version.
     */
    protected Integer numberOfOrders;

    /**
     * TagNum = 290. Starting with 4.2 version.
     */
    protected Integer mdEntryPositionNo;

    /**
     * TagNum = 546. Starting with 4.3 version.
     */
    protected String scope;

    /**
     * TagNum = 387. Starting with 4.2 version.
     */
    protected Double totalVolumeTraded;

    /**
     * TagNum = 449. Starting with 4.3 version.
     */
    protected Date totalVolumeTradedDate;

    /**
     * TagNum = 450. Starting with 4.3 version.
     */
    protected Date totalVolumeTradedTime;

    /**
     * TagNum = 811. Starting with 4.4 version.
     */
    protected Double priceDelta;

    /**
     * TagNum = 451. Starting with 4.3 version.
     */
    protected Double netChgPrevDay;

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

    /**
     * TagNum = 1023. Starting with 5.0 version.
     */
    protected Integer mdPriceLevel;

    /**
     * TagNum = 528. Starting with 5.0 version.
     */
    protected OrderCapacity orderCapacity;

    /**
     * TagNum = 1024. Starting with 5.0 version.
     */
    protected MDOriginType mdOriginType;

    /**
     * TagNum = 332. Starting with 5.0 version.
     */
    protected Double highPx;

    /**
     * TagNum = 333. Starting with 5.0 version.
     */
    protected Double lowPx;

    /**
     * TagNum = 1025. Starting with 5.0SP2 version.
     */
    protected Double firstPx;

    /**
     * TagNum = 31. Starting with 5.0SP2 version.
     */
    protected Double lastPx;

    /**
     * TagNum = 1020. Starting with 5.0 version.
     */
    protected Double tradeVolume;

    /**
     * TagNum = 63. Starting with 5.0 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 5.0 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 483. Starting with 5.0SP1 version.
     */
    protected Date transBkdTime;

    /**
     * TagNum = 60. Starting with 5.0SP1 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 1070. Starting with 5.0 version.
     */
    protected MDQuoteType mdQuoteType;

    /**
     * TagNum = 83. Starting with 5.0 version.
     */
    protected Integer rptSeq;

    /**
     * TagNum = 1048. Starting with 5.0 version.
     */
    protected DealingCapacity dealingCapacity;

    /**
     * TagNum = 1026. Starting with 5.0 version.
     */
    protected Double mdEntrySpotRate;

    /**
     * TagNum = 1027. Starting with 5.0 version.
     */
    protected Double mdEntryForwardPoints;

    /**
     * TagNum = 1175. Starting with 5.0SP1 version.
     */
    protected Integer noStatsIndicators;

    /**
     * Starting with 5.0SP1 version.
     */
    protected StatsIndGroup[] statsIndGroups;

    /**
     * Starting with 5.0 version.
     */
    protected Parties parties;


    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDIncGroup() {
    }

    public MDIncGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDUpdateAction, required=true)
    public MDUpdateAction getMdUpdateAction() {
        return mdUpdateAction;
    }

    /**
     * Message field setter.
     * @param mdUpdateAction field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDUpdateAction, required=true)
    public void setMdUpdateAction(MDUpdateAction mdUpdateAction) {
        this.mdUpdateAction = mdUpdateAction;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DeleteReason)
    public DeleteReason getDeleteReason() {
        return deleteReason;
    }

    /**
     * Message field setter.
     * @param deleteReason field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DeleteReason)
    public void setDeleteReason(DeleteReason deleteReason) {
        this.deleteReason = deleteReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MDSubBookType)
    public Integer getMdSubBookType() {
        return mdSubBookType;
    }

    /**
     * Message field setter.
     * @param mdSubBookType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MDSubBookType)
    public void setMdSubBookType(Integer mdSubBookType) {
        this.mdSubBookType = mdSubBookType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketDepth)
    public MarketDepth getMarketDepth() {
        return marketDepth;
    }

    /**
     * Message field setter.
     * @param marketDepth field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketDepth)
    public void setMarketDepth(MarketDepth marketDepth) {
        this.marketDepth = marketDepth;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryType)
    public MDEntryType getMdEntryType() {
        return mdEntryType;
    }

    /**
     * Message field setter.
     * @param mdEntryType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryType)
    public void setMdEntryType(MDEntryType mdEntryType) {
        this.mdEntryType = mdEntryType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryID)
    public String getMdEntryID() {
        return mdEntryID;
    }

    /**
     * Message field setter.
     * @param mdEntryID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryID)
    public void setMdEntryID(String mdEntryID) {
        this.mdEntryID = mdEntryID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryRefID)
    public String getMdEntryRefID() {
        return mdEntryRefID;
    }

    /**
     * Message field setter.
     * @param mdEntryRefID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryRefID)
    public void setMdEntryRefID(String mdEntryRefID) {
        this.mdEntryRefID = mdEntryRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.MDStreamID)
    public String getMdStreamID() {
        return mdStreamID;
    }

    /**
     * Message field setter.
     * @param mdStreamID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.MDStreamID)
    public void setMdStreamID(String mdStreamID) {
        this.mdStreamID = mdStreamID;
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
     * Sets the field to the proper implementation instance.
     */
    @FIXVersion(introduced = "4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "4.3")
    public void clearInstrument() {
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
     * This method sets the number of {@link UnderlyingInstrument} components. It will also create an array
     * of {@link UnderlyingInstrument} objects and set the <code>underlyingInstruments</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingInstruments</code> array they will be discarded.<br/>
     * @param noUnderlyings number of MsgTypeGroup objects
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
    @TagNumRef(tagNum = TagNum.NoLegs)
    public Integer getNoLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link InstrumentLeg} groups. It will also create an array
     * of {@link InstrumentLeg} objects and set the <code>instrumentLegs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrumentLegs</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrumentLeg} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
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
    @FIXVersion(introduced = "4.4")
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
    @FIXVersion(introduced = "4.4")
    public InstrumentLeg deleteInstrumentLeg(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrumentLeg} objects from the <code>instrumentLegs</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required = true)
    public String getSymbol() {
        return getSafeInstrument().getSymbol();
    }

    /**
     * Message field setter.
     * @param symbol field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required = true)
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
    @TagNumRef(tagNum = TagNum.FinancialStatus)
    public String getFinancialStatus() {
        return financialStatus;
    }

    /**
     * Message field setter.
     * @param financialStatus field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.FinancialStatus)
    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.CorporateAction)
    public String getCorporateAction() {
        return corporateAction;
    }

    /**
     * Message field setter.
     * @param corporateAction field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.CorporateAction)
    public void setCorporateAction(String corporateAction) {
        this.corporateAction = corporateAction;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryPx)
    public Double getMdEntryPx() {
        return mdEntryPx;
    }

    /**
     * Message field setter.
     * @param mdEntryPx field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryPx)
    public void setMdEntryPx(Double mdEntryPx) {
        this.mdEntryPx = mdEntryPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.PriceType)
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * Message field setter.
     * @param priceType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.PriceType)
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    public YieldData getYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the YieldData component class to the proper implementation.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the YieldData component to null.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component class to the proper implementation.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component TO NULL.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.OrdType)
    public OrdType getOrdType() {
        return ordType;
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.OrdType)
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
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
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntrySize)
    public Double getMdEntrySize() {
        return mdEntrySize;
    }

    /**
     * Message field setter.
     * @param mdEntrySize field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntrySize)
    public void setMdEntrySize(Double mdEntrySize) {
        this.mdEntrySize = mdEntrySize;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoOfSecSizes)
    public Integer getNoOfSecSizes() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param noOfSecSizes field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoOfSecSizes)
    public void setNoOfSecSizes(Integer noOfSecSizes) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MDSecSizeGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0SP1")
    public MDSecSizeGroup[] getMDSecSizeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MDSecSizeGroup} object to the existing array of <code>noOfSecSizes</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noOfSecSizes</code> field to the proper value.<br/>
     * Note: If the <code>setNoOfSecSizes</code> method has been called there will already be a number of objects in the
     * <code>mdSecSizeGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public MDSecSizeGroup addMDSecSizeGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MDSecSizeGroup} object from the existing array of <code>mdSecSizeGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noOfSecSizes</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public MDSecSizeGroup deleteMDSecSizeGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MDSecSizeGroup} objects from the <code>mdSecSizeGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noOfSecSizes</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearMDSecSizeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LotType)
    public LotType getLotType() {
        return lotType;
    }

    /**
     * Message field setter.
     * @param lotType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LotType)
    public void setLotType(LotType lotType) {
        this.lotType = lotType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryDate)
    public Date getMdEntryDate() {
        return mdEntryDate;
    }

    /**
     * Message field setter.
     * @param mdEntryDate field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryDate)
    public void setMdEntryDate(Date mdEntryDate) {
        this.mdEntryDate = mdEntryDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryTime)
    public Date getMdEntryTime() {
        return mdEntryTime;
    }

    /**
     * Message field setter.
     * @param mdEntryTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryTime)
    public void setMdEntryTime(Date mdEntryTime) {
        this.mdEntryTime = mdEntryTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TickDirection)
    public TickDirection getTickDirection() {
        return tickDirection;
    }

    /**
     * Message field setter.
     * @param tickDirection field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TickDirection)
    public void setTickDirection(TickDirection tickDirection) {
        this.tickDirection = tickDirection;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDMkt)
    public String getMdMkt() {
        return mdMkt;
    }

    /**
     * Message field setter.
     * @param mdMkt field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDMkt)
    public void setMdMkt(String mdMkt) {
        this.mdMkt = mdMkt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityTradingStatus)
    public SecurityTradingStatus getSecurityTradingStatus() {
        return securityTradingStatus;
    }

    /**
     * Message field setter.
     * @param securityTradingStatus field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecurityTradingStatus)
    public void setSecurityTradingStatus(SecurityTradingStatus securityTradingStatus) {
        this.securityTradingStatus = securityTradingStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.HaltReason)
    public HaltReason getHaltReason() {
        return haltReason;
    }

    /**
     * Message field setter.
     * @param haltReason field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.HaltReason)
    public void setHaltReason(HaltReason haltReason) {
        this.haltReason = haltReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteCondition)
    public QuoteCondition getQuoteCondition() {
        return quoteCondition;
    }

    /**
     * Message field setter.
     * @param quoteCondition field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteCondition)
    public void setQuoteCondition(QuoteCondition quoteCondition) {
        this.quoteCondition = quoteCondition;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradeCondition)
    public TradeCondition getTradeCondition() {
        return tradeCondition;
    }

    /**
     * Message field setter.
     * @param tradeCondition field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradeCondition)
    public void setTradeCondition(TradeCondition tradeCondition) {
        this.tradeCondition = tradeCondition;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TrdType)
    public TrdType getTrdType() {
        return trdType;
    }

    /**
     * Message field setter.
     * @param trdType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TrdType)
    public void setTrdType(TrdType trdType) {
        this.trdType = trdType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MatchType)
    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * Message field setter.
     * @param matchType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MatchType)
    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryOriginator)
    public String getMdEntryOriginator() {
        return mdEntryOriginator;
    }

    /**
     * Message field setter.
     * @param mdEntryOriginator field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryOriginator)
    public void setMdEntryOriginator(String mdEntryOriginator) {
        this.mdEntryOriginator = mdEntryOriginator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LocationID)
    public String getLocationID() {
        return locationID;
    }

    /**
     * Message field setter.
     * @param locationID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LocationID)
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DeskID)
    public String getDeskID() {
        return deskID;
    }

    /**
     * Message field setter.
     * @param deskID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DeskID)
    public void setDeskID(String deskID) {
        this.deskID = deskID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OpenCloseSettlFlag)
    public String getOpenCloseSettlFlag() {
        return openCloseSettlFlag;
    }

    /**
     * Message field setter.
     * @param openCloseSettlFlag field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OpenCloseSettlFlag)
    public void setOpenCloseSettlFlag(String openCloseSettlFlag) {
        this.openCloseSettlFlag = openCloseSettlFlag;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    /**
     * Message field setter.
     * @param timeInForce field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * Message field setter.
     * @param expireDate field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MinQty)
    public Double getMinQty() {
        return minQty;
    }

    /**
     * Message field setter.
     * @param minQty field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MinQty)
    public void setMinQty(Double minQty) {
        this.minQty = minQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public String getExecInst() {
        return execInst;
    }

    /**
     * Message field setter.
     * @param execInst field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SellerDays)
    public Integer getSellerDays() {
        return sellerDays;
    }

    /**
     * Message field setter.
     * @param sellerDays field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SellerDays)
    public void setSellerDays(Integer sellerDays) {
        this.sellerDays = sellerDays;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    /**
     * Message field setter.
     * @param secondaryOrderID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteEntryID)
    public String getQuoteEntryID() {
        return quoteEntryID;
    }

    /**
     * Message field setter.
     * @param quoteEntryID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteEntryID)
    public void setQuoteEntryID(String quoteEntryID) {
        this.quoteEntryID = quoteEntryID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradeID)
    public String getTradeID() {
        return tradeID;
    }

    /**
     * Message field setter.
     * @param tradeID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradeID)
    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryBuyer)
    public String getMdEntryBuyer() {
        return mdEntryBuyer;
    }

    /**
     * Message field setter.
     * @param mdEntryBuyer field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryBuyer)
    public void setMdEntryBuyer(String mdEntryBuyer) {
        this.mdEntryBuyer = mdEntryBuyer;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntrySeller)
    public String getMdEntrySeller() {
        return mdEntrySeller;
    }

    /**
     * Message field setter.
     * @param mdEntrySeller field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntrySeller)
    public void setMdEntrySeller(String mdEntrySeller) {
        this.mdEntrySeller = mdEntrySeller;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NumberOfOrders)
    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    /**
     * Message field setter.
     * @param numberOfOrders field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NumberOfOrders)
    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryPositionNo)
    public Integer getMdEntryPositionNo() {
        return mdEntryPositionNo;
    }

    /**
     * Message field setter.
     * @param mdEntryPositionNo field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDEntryPositionNo)
    public void setMdEntryPositionNo(Integer mdEntryPositionNo) {
        this.mdEntryPositionNo = mdEntryPositionNo;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Scope)
    public String getScope() {
        return scope;
    }

    /**
     * Message field setter.
     * @param scope field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Scope)
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.TotalVolumeTraded)
    public Double getTotalVolumeTraded() {
        return totalVolumeTraded;
    }

    /**
     * Message field setter.
     * @param totalVolumeTraded field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.TotalVolumeTraded)
    public void setTotalVolumeTraded(Double totalVolumeTraded) {
        this.totalVolumeTraded = totalVolumeTraded;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.TotalVolumeTradedDate)
    public Date getTotalVolumeTradedDate() {
        return totalVolumeTradedDate;
    }

    /**
     * Message field setter.
     * @param totalVolumeTradedDate field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.TotalVolumeTradedDate)
    public void setTotalVolumeTradedDate(Date totalVolumeTradedDate) {
        this.totalVolumeTradedDate = totalVolumeTradedDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.TotalVolumeTradedTime)
    public Date getTotalVolumeTradedTime() {
        return totalVolumeTradedTime;
    }

    /**
     * Message field setter.
     * @param totalVolumeTradedTime field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.TotalVolumeTradedTime)
    public void setTotalVolumeTradedTime(Date totalVolumeTradedTime) {
        this.totalVolumeTradedTime = totalVolumeTradedTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PriceDelta)
    public Double getPriceDelta() {
        return priceDelta;
    }

    /**
     * Message field setter.
     * @param priceDelta field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PriceDelta)
    public void setPriceDelta(Double priceDelta) {
        this.priceDelta = priceDelta;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NetChgPrevDay)
    public Double getNetChgPrevDay() {
        return netChgPrevDay;
    }

    /**
     * Message field setter.
     * @param netChgPrevDay field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NetChgPrevDay)
    public void setNetChgPrevDay(Double netChgPrevDay) {
        this.netChgPrevDay = netChgPrevDay;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MDPriceLevel)
    public Integer getMdPriceLevel() {
        return mdPriceLevel;
    }

    /**
     * Message field setter.
     * @param mdPriceLevel field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MDPriceLevel)
    public void setMdPriceLevel(Integer mdPriceLevel) {
        this.mdPriceLevel = mdPriceLevel;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    /**
     * Message field setter.
     * @param orderCapacity field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDOriginType)
    public MDOriginType getMdOriginType() {
        return mdOriginType;
    }

    /**
     * Message field setter.
     * @param mdOriginType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDOriginType)
    public void setMdOriginType(MDOriginType mdOriginType) {
        this.mdOriginType = mdOriginType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.HighPx)
    public Double getHighPx() {
        return highPx;
    }

    /**
     * Message field setter.
     * @param highPx field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.HighPx)
    public void setHighPx(Double highPx) {
        this.highPx = highPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LowPx)
    public Double getLowPx() {
        return lowPx;
    }

    /**
     * Message field setter.
     * @param lowPx field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LowPx)
    public void setLowPx(Double lowPx) {
        this.lowPx = lowPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.FirstPx)
    public Double getFirstPx() {
        return firstPx;
    }

    /**
     * Message field setter.
     * @param firstPx field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.FirstPx)
    public void setFirstPx(Double firstPx) {
        this.firstPx = firstPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LastPx)
    public Double getLastPx() {
        return lastPx;
    }

    /**
     * Message field setter.
     * @param lastPx field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LastPx)
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeVolume)
    public Double getTradeVolume() {
        return tradeVolume;
    }

    /**
     * Message field setter.
     * @param tradeVolume field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeVolume)
    public void setTradeVolume(Double tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlType)
    public String getSettlType() {
        return settlType;
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlType)
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        return settlDate;
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    /**
     * Message field setter.
     * @param transBkdTime field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDQuoteType)
    public MDQuoteType getMdQuoteType() {
        return mdQuoteType;
    }

    /**
     * Message field setter.
     * @param mdQuoteType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDQuoteType)
    public void setMdQuoteType(MDQuoteType mdQuoteType) {
        this.mdQuoteType = mdQuoteType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RptSeq)
    public Integer getRptSeq() {
        return rptSeq;
    }

    /**
     * Message field setter.
     * @param rptSeq field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RptSeq)
    public void setRptSeq(Integer rptSeq) {
        this.rptSeq = rptSeq;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.DealingCapacity)
    public DealingCapacity getDealingCapacity() {
        return dealingCapacity;
    }

    /**
     * Message field setter.
     * @param dealingCapacity field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.DealingCapacity)
    public void setDealingCapacity(DealingCapacity dealingCapacity) {
        this.dealingCapacity = dealingCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDEntrySpotRate)
    public Double getMdEntrySpotRate() {
        return mdEntrySpotRate;
    }

    /**
     * Message field setter.
     * @param mdEntrySpotRate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDEntrySpotRate)
    public void setMdEntrySpotRate(Double mdEntrySpotRate) {
        this.mdEntrySpotRate = mdEntrySpotRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDEntryForwardPoints)
    public Double getMdEntryForwardPoints() {
        return mdEntryForwardPoints;
    }

    /**
     * Message field setter.
     * @param mdEntryForwardPoints field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDEntryForwardPoints)
    public void setMdEntryForwardPoints(Double mdEntryForwardPoints) {
        this.mdEntryForwardPoints = mdEntryForwardPoints;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoStatsIndicators)
    public Integer getNoStatsIndicators() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link StatsIndGroup} groups. It will also create an array
     * of {@link StatsIndGroup} objects and set the <code>statsIndGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>statsIndGroups</code> array they will be discarded.<br/>
     * @param noStatsIndicators field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoStatsIndicators)
    public void setNoStatsIndicators(Integer noStatsIndicators) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link StatsIndGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0SP1")
    public StatsIndGroup[] getStatsIndGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link StatsIndGroup} object to the existing array of <code>statsIndGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noStatsIndicators</code> field to the proper value.<br/>
     * Note: If the <code>setNoStatsIndicators</code> method has been called there will already be a number of objects in the
     * <code>statsIndGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "5.0SP1")
    public StatsIndGroup addStatsIndGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link StatsIndGroup} object from the existing array of <code>statsIndGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noStatsIndicators</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "5.0SP1")
    public StatsIndGroup deleteStatsIndGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link StatsIndGroup} objects from the <code>statsIndGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noStatsIndicators</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0SP1")
    public int clearStatsIndGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper component implementtaion.
     */
    @FIXVersion(introduced="5.0")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.MDUpdateAction.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (mdUpdateAction == null) {
            errorMsg.append(" [MDUpdateAction]");
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
            if (mdUpdateAction != null) {
                TagEncoder.encode(bao, TagNum.MDUpdateAction, mdUpdateAction.getValue());
            }
            if (deleteReason != null) {
                TagEncoder.encode(bao, TagNum.DeleteReason, deleteReason.getValue());
            }
            TagEncoder.encode(bao, TagNum.MDSubBookType, mdSubBookType);
            if (marketDepth != null) {
                TagEncoder.encode(bao, TagNum.MarketDepth, marketDepth.getValue());
            }
            if (mdEntryType != null) {
                TagEncoder.encode(bao, TagNum.MDEntryType, mdEntryType.getValue());
            }
            TagEncoder.encode(bao, TagNum.MDEntryID, mdEntryID);
            TagEncoder.encode(bao, TagNum.MDEntryRefID, mdEntryRefID);
            TagEncoder.encode(bao, TagNum.MDStreamID, mdStreamID);
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noUnderlyings.intValue()) {
                    for (UnderlyingInstrument underlyingInstrument : underlyingInstruments) {
                        bao.write(underlyingInstrument.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error =
                            "UnderlyingInstruments field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (noLegs != null && noLegs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (InstrumentLeg instrumentLeg : instrumentLegs) {
                        bao.write(instrumentLeg.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error =
                            "InstrumentLegs field has been set but there is no data or the number of components does not match.";
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
            TagEncoder.encode(bao, TagNum.FinancialStatus, financialStatus);
            TagEncoder.encode(bao, TagNum.CorporateAction, corporateAction);
            TagEncoder.encode(bao, TagNum.MDEntryPx, mdEntryPx);
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (ordType != null) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
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
            TagEncoder.encode(bao, TagNum.MDEntrySize, mdEntrySize);
            if (noOfSecSizes != null) {
                TagEncoder.encode(bao, TagNum.NoOfSecSizes, noOfSecSizes);
                if (mdSecSizeGroups != null && mdSecSizeGroups.length == noOfSecSizes.intValue()) {
                    for (int i = 0; i < noOfSecSizes.intValue(); i++) {
                        if (mdSecSizeGroups[i] != null) {
                            bao.write(mdSecSizeGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "MDSecSizeGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoOfSecSizes.getValue(), error);
                }
            }
            if (lotType != null) {
                TagEncoder.encode(bao, TagNum.LotType, lotType.getValue());
            }
            TagEncoder.encodeUtcDate(bao, TagNum.MDEntryDate, mdEntryDate);
            TagEncoder.encodeUTCTime(bao, TagNum.MDEntryTime, mdEntryTime);
            if (tickDirection != null) {
                 TagEncoder.encode(bao, TagNum.TickDirection, tickDirection.getValue());
            }
            TagEncoder.encode(bao, TagNum.MDMkt, mdMkt);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            if (securityTradingStatus != null) {
                TagEncoder.encode(bao, TagNum.SecurityTradingStatus, securityTradingStatus.getValue());
            }
            if (haltReason != null) {
                 TagEncoder.encode(bao, TagNum.HaltReason, haltReason.getValue());
            }
            if (quoteCondition != null) {
                 TagEncoder.encode(bao, TagNum.QuoteCondition, quoteCondition.getValue());
            }
            if (tradeCondition != null) {
                 TagEncoder.encode(bao, TagNum.TradeCondition, tradeCondition.getValue());
            }
            if (trdType != null) {
                 TagEncoder.encode(bao, TagNum.TrdType, trdType.getValue());
            }
            if (matchType != null) {
                 TagEncoder.encode(bao, TagNum.MatchType, matchType.getValue());
            }
            TagEncoder.encode(bao, TagNum.MDEntryOriginator, mdEntryOriginator);
            TagEncoder.encode(bao, TagNum.LocationID, locationID);
            TagEncoder.encode(bao, TagNum.DeskID, deskID);
            TagEncoder.encode(bao, TagNum.OpenCloseSettlFlag, openCloseSettlFlag);
            if (timeInForce != null) {
                 TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.ExpireDate, expireDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            TagEncoder.encode(bao, TagNum.MinQty, minQty);
            TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            TagEncoder.encode(bao, TagNum.SellerDays, sellerDays);
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            TagEncoder.encode(bao, TagNum.QuoteEntryID, quoteEntryID);
            TagEncoder.encode(bao, TagNum.TradeID, tradeID);
            TagEncoder.encode(bao, TagNum.MDEntryBuyer, mdEntryBuyer);
            TagEncoder.encode(bao, TagNum.MDEntrySeller, mdEntrySeller);
            TagEncoder.encode(bao, TagNum.NumberOfOrders, numberOfOrders);
            TagEncoder.encode(bao, TagNum.MDEntryPositionNo, mdEntryPositionNo);
            TagEncoder.encode(bao, TagNum.Scope, scope);
            TagEncoder.encode(bao, TagNum.TotalVolumeTraded, totalVolumeTraded);
            TagEncoder.encodeUtcDate(bao, TagNum.TotalVolumeTradedDate, totalVolumeTradedDate);
            TagEncoder.encodeUTCTime(bao, TagNum.TotalVolumeTradedTime, totalVolumeTradedTime);
            TagEncoder.encode(bao, TagNum.PriceDelta, priceDelta);
            TagEncoder.encode(bao, TagNum.NetChgPrevDay, netChgPrevDay);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            TagEncoder.encode(bao, TagNum.MDPriceLevel, mdPriceLevel);
            if (orderCapacity != null) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            if (mdOriginType != null) {
                TagEncoder.encode(bao, TagNum.MDOriginType, mdOriginType.getValue());
            }
            TagEncoder.encode(bao, TagNum.HighPx, highPx);
            TagEncoder.encode(bao, TagNum.LowPx, lowPx);
            TagEncoder.encode(bao, TagNum.FirstPx, firstPx);
            TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            TagEncoder.encode(bao, TagNum.TradeVolume, tradeVolume);
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransBkdTime, transBkdTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            if (mdQuoteType != null) {
                TagEncoder.encode(bao, TagNum.MDQuoteType, mdQuoteType.getValue());
            }
            TagEncoder.encode(bao, TagNum.RptSeq, rptSeq);
            if (dealingCapacity != null) {
                TagEncoder.encode(bao, TagNum.DealingCapacity, dealingCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.MDEntrySpotRate, mdEntrySpotRate);
            TagEncoder.encode(bao, TagNum.MDEntryForwardPoints, mdEntryForwardPoints);
            if (noStatsIndicators != null) {
                TagEncoder.encode(bao, TagNum.NoStatsIndicators, noStatsIndicators);
                if (statsIndGroups != null && statsIndGroups.length == noStatsIndicators.intValue()) {
                    for (int i = 0; i < noStatsIndicators.intValue(); i++) {
                        if (statsIndGroups[i] != null) {
                            bao.write(statsIndGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "StatsIndGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoStatsIndicators.getValue(), error);
                }
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
            case MDUpdateAction:
                mdUpdateAction = MDUpdateAction.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case DeleteReason:
                deleteReason = DeleteReason.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MDSubBookType:
                mdSubBookType = new Integer(new String(tag.value, sessionCharset));
                break;

            case MarketDepth:
                marketDepth = MarketDepth.valueFor(Integer.parseInt(new String(tag.value, sessionCharset)));
                break;

            case MDEntryType:
                mdEntryType = MDEntryType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MDEntryID:
                mdEntryID = new String(tag.value, sessionCharset);
                break;

            case MDEntryRefID:
                mdEntryRefID = new String(tag.value, sessionCharset);
                break;

            case MDStreamID:
                mdStreamID = new String(tag.value, sessionCharset);
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, sessionCharset));
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

            case FinancialStatus:
                financialStatus = new String(tag.value, sessionCharset);
                break;

            case CorporateAction:
                corporateAction = new String(tag.value, sessionCharset);
                break;

            case MDEntryPx:
                mdEntryPx = new Double(new String(tag.value, sessionCharset));
                break;

            case PriceType:
                priceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OrdType:
                ordType = OrdType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case MDEntrySize:
                mdEntrySize = new Double(new String(tag.value, sessionCharset));
                break;

            case NoRateSources:
                noRateSources = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoOfSecSizes:
                noOfSecSizes = new Integer(new String(tag.value, sessionCharset));
                break;

            case LotType:
                lotType = LotType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MDEntryDate:
                mdEntryDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case MDEntryTime:
                mdEntryTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TickDirection:
                tickDirection = TickDirection.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MDMkt:
                mdMkt = new String(tag.value, sessionCharset);
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case SecurityTradingStatus:
                securityTradingStatus = SecurityTradingStatus.valueFor(new Integer(new String(tag.value, sessionCharset)));
                break;

            case HaltReason:
                haltReason = HaltReason.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case QuoteCondition:
                quoteCondition = QuoteCondition.valueFor(new String(tag.value, sessionCharset));
                break;

            case TradeCondition:
                tradeCondition = TradeCondition.valueFor(new String(tag.value, sessionCharset));
                break;

            case TrdType:
                trdType = TrdType.valueFor(Integer.parseInt(new String(tag.value, sessionCharset)));
                break;

            case MatchType:
                matchType = MatchType.valueFor(new String(tag.value, sessionCharset));
                break;

            case MDEntryOriginator:
                mdEntryOriginator = new String(tag.value, sessionCharset);
                break;

            case LocationID:
                locationID = new String(tag.value, sessionCharset);
                break;

            case DeskID:
                deskID = new String(tag.value, sessionCharset);
                break;

            case OpenCloseSettlFlag:
                openCloseSettlFlag = new String(tag.value, sessionCharset);
                break;

            case TimeInForce:
                timeInForce = TimeInForce.valueFor(new String(tag.value, sessionCharset));
                break;

            case ExpireDate:
                expireDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ExpireTime:
                expireTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case MinQty:
                minQty = new Double(new String(tag.value, sessionCharset));
                break;

            case ExecInst:
                execInst = new String(tag.value, sessionCharset);
                break;

            case SellerDays:
                sellerDays = new Integer(new String(tag.value, sessionCharset));
                break;

            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;

            case SecondaryOrderID:
                secondaryOrderID = new String(tag.value, sessionCharset);
                break;

            case QuoteEntryID:
                quoteEntryID = new String(tag.value, sessionCharset);
                break;

            case TradeID:
                tradeID = new String(tag.value, sessionCharset);
                break;

            case MDEntryBuyer:
                mdEntryBuyer = new String(tag.value, sessionCharset);
                break;

            case MDEntrySeller:
                mdEntrySeller = new String(tag.value, sessionCharset);
                break;

            case NumberOfOrders:
                numberOfOrders = new Integer(new String(tag.value, sessionCharset));
                break;

            case MDEntryPositionNo:
                mdEntryPositionNo = new Integer(new String(tag.value, sessionCharset));
                break;

            case Scope:
                scope = new String(tag.value, sessionCharset);
                break;

            case TotalVolumeTraded:
                totalVolumeTraded = new Double(new String(tag.value, sessionCharset));
                break;

            case TotalVolumeTradedDate:
                totalVolumeTradedDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TotalVolumeTradedTime:
                totalVolumeTradedTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case PriceDelta:
                priceDelta = new Double(new String(tag.value, sessionCharset));
                break;

            case NetChgPrevDay:
                netChgPrevDay = new Double(new String(tag.value, sessionCharset));
                break;
                
            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case MDPriceLevel:
                mdPriceLevel = new Integer(new String(tag.value, sessionCharset));
                break;

            case OrderCapacity:
                orderCapacity = OrderCapacity.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MDOriginType:
                mdOriginType = MDOriginType.valueFor(Integer.parseInt(new String(tag.value, sessionCharset)));
                break;

            case HighPx:
                highPx = new Double(new String(tag.value, sessionCharset));
                break;

            case LowPx:
                lowPx = new Double(new String(tag.value, sessionCharset));
                break;

            case FirstPx:
                firstPx = new Double(new String(tag.value, sessionCharset));
                break;

            case LastPx:
                lastPx = new Double(new String(tag.value, sessionCharset));
                break;

            case TradeVolume:
                tradeVolume = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlType:
                settlType = new String(tag.value, sessionCharset);
                break;

            case SettlDate:
                settlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TransBkdTime:
                transBkdTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case MDQuoteType:
                mdQuoteType = MDQuoteType.valueFor(Integer.parseInt(new String(tag.value, sessionCharset)));
                break;

            case RptSeq:
                rptSeq = new Integer(new String(tag.value, sessionCharset));
                break;

            case DealingCapacity:
                dealingCapacity = DealingCapacity.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MDEntrySpotRate:
                mdEntrySpotRate = new Double(new String(tag.value, sessionCharset));
                break;

            case MDEntryForwardPoints:
                mdEntryForwardPoints = new Double(new String(tag.value, sessionCharset));
                break;

            case NoStatsIndicators:
                noStatsIndicators = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [MDIncGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }

        return result;
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
        b.append("{MDIncGroup=");
        printTagValue(b, TagNum.MDUpdateAction, mdUpdateAction);
        printTagValue(b, TagNum.DeleteReason, deleteReason);
        printTagValue(b, TagNum.MDSubBookType, mdSubBookType);
        printTagValue(b, TagNum.MarketDepth, marketDepth);
        printTagValue(b, TagNum.MDEntryType, mdEntryType);
        printTagValue(b, TagNum.MDEntryID, mdEntryID);
        printTagValue(b, TagNum.MDEntryRefID, mdEntryRefID);
        printTagValue(b, TagNum.MDStreamID, mdStreamID);
        printTagValue(b, instrument);
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
        printTagValue(b, TagNum.OptAttribute, optAttribute);
        printTagValue(b, TagNum.ContractMultiplier, contractMultiplier);
        printTagValue(b, TagNum.CouponRate, couponRate);
        printTagValue(b, TagNum.SecurityExchange, securityExchange);
        printTagValue(b, TagNum.Issuer, issuer);
        printTagValue(b, TagNum.EncodedIssuerLen, encodedIssuerLen);
        printTagValue(b, TagNum.EncodedIssuer, encodedIssuer);
        printTagValue(b, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
        printTagValue(b, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
        printTagValue(b, TagNum.FinancialStatus, financialStatus);
        printTagValue(b, TagNum.CorporateAction, corporateAction);
        printTagValue(b, TagNum.MDEntryPx, mdEntryPx);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, yieldData);
        printTagValue(b, spreadOrBenchmarkCurveData);
        printTagValue(b, TagNum.OrdType, ordType);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printTagValue(b, TagNum.NoRateSources, noRateSources);
        printTagValue(b, rateSources);
        printTagValue(b, TagNum.MDEntrySize, mdEntrySize);
        printTagValue(b, TagNum.NoOfSecSizes, noOfSecSizes);
        printTagValue(b, mdSecSizeGroups);
        printTagValue(b, TagNum.LotType, lotType);
        printDateTagValue(b, TagNum.MDEntryDate, mdEntryDate);
        printTimeTagValue(b, TagNum.MDEntryTime, mdEntryTime);
        printTagValue(b, TagNum.TickDirection, tickDirection);
        printTagValue(b, TagNum.MDMkt, mdMkt);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.SecurityTradingStatus, securityTradingStatus);
        printTagValue(b, TagNum.HaltReason, haltReason);
        printTagValue(b, TagNum.QuoteCondition, quoteCondition);
        printTagValue(b, TagNum.TradeCondition, tradeCondition);
        printTagValue(b, TagNum.TrdType, trdType);
        printTagValue(b, TagNum.MatchType, matchType);
        printTagValue(b, TagNum.MDEntryOriginator, mdEntryOriginator);
        printTagValue(b, TagNum.LocationID, locationID);
        printTagValue(b, TagNum.DeskID, deskID);
        printTagValue(b, TagNum.OpenCloseSettlFlag, openCloseSettlFlag);
        printTagValue(b, TagNum.TimeInForce, timeInForce);
        printDateTagValue(b, TagNum.ExpireDate, expireDate);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printTagValue(b, TagNum.MinQty, minQty);
        printTagValue(b, TagNum.ExecInst, execInst);
        printTagValue(b, TagNum.SellerDays, sellerDays);
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.SecondaryOrderID, secondaryOrderID);
        printTagValue(b, TagNum.QuoteEntryID, quoteEntryID);
        printTagValue(b, TagNum.TradeID, tradeID);
        printTagValue(b, TagNum.MDEntryBuyer, mdEntryBuyer);
        printTagValue(b, TagNum.MDEntrySeller, mdEntrySeller);
        printTagValue(b, TagNum.NumberOfOrders, numberOfOrders);
        printTagValue(b, TagNum.MDEntryPositionNo, mdEntryPositionNo);
        printTagValue(b, TagNum.Scope, scope);
        printTagValue(b, TagNum.TotalVolumeTraded, totalVolumeTraded);
        printUTCDateTimeTagValue(b, TagNum.TotalVolumeTradedDate, totalVolumeTradedDate);
        printUTCTimeTagValue(b, TagNum.TotalVolumeTradedTime, totalVolumeTradedTime);
        printTagValue(b, TagNum.PriceDelta, priceDelta);
        printTagValue(b, TagNum.NetChgPrevDay, netChgPrevDay);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.MDPriceLevel, mdPriceLevel);
        printTagValue(b, TagNum.OrderCapacity, orderCapacity);
        printTagValue(b, TagNum.MDOriginType, mdOriginType);
        printTagValue(b, TagNum.HighPx, highPx);
        printTagValue(b, TagNum.LowPx, lowPx);
        printTagValue(b, TagNum.FirstPx, firstPx);
        printTagValue(b, TagNum.LastPx, lastPx);
        printTagValue(b, TagNum.TradeVolume, tradeVolume);
        printTagValue(b, TagNum.SettlType, settlType);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printUTCTimeTagValue(b, TagNum.TransBkdTime, transBkdTime);
        printUTCTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.MDQuoteType, mdQuoteType);
        printTagValue(b, TagNum.RptSeq, rptSeq);
        printTagValue(b, TagNum.DealingCapacity, dealingCapacity);
        printTagValue(b, TagNum.MDEntrySpotRate, mdEntrySpotRate);
        printTagValue(b, TagNum.MDEntryForwardPoints, mdEntryForwardPoints);
        printTagValue(b, TagNum.NoStatsIndicators, noStatsIndicators);
        printTagValue(b, statsIndGroups);
        printTagValue(b, parties);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
