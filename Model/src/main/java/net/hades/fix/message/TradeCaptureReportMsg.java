/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportMsg.java
 *
 * $Id: TradeCaptureReportMsg.java,v 1.2 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.TrdCapRptSideGroup;
import net.hades.fix.message.group.TrdRepIndicatorsGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;

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
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.group.TrdInstrmtLegGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.AsOfIndicator;
import net.hades.fix.message.type.AvgPxIndicator;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrderCategory;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.ShortSaleReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TradeHandlingInstr;
import net.hades.fix.message.type.TradePublishIndicator;
import net.hades.fix.message.type.TradeReportTransType;
import net.hades.fix.message.type.TradeReportType;
import net.hades.fix.message.type.TrdRptStatus;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.type.VenueType;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Trade Capture Report message can be:
 * • Used to report trades between counterparties.<br/>
 * • Used to report trades to a trade matching system<br/>
 * • Can be sent unsolicited between counterparties.<br/>
 * • Sent as a reply to a Trade Capture Report Request.<br/>
 * • Can be used to report unmatched and matched trades.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TradeCaptureReportMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TradeReportID.getValue(),
        TagNum.TradeID.getValue(),
        TagNum.SecondaryTradeID.getValue(),
        TagNum.FirmTradeID.getValue(),
        TagNum.SecondaryFirmTradeID.getValue(),
        TagNum.TradeReportTransType.getValue(),
        TagNum.TradeReportType.getValue(),
        TagNum.TrdRptStatus.getValue(),
        TagNum.TradeRequestID.getValue(),
        TagNum.TrdType.getValue(),
        TagNum.TrdSubType.getValue(),
        TagNum.SecondaryTrdType.getValue(),
        TagNum.TradeHandlingInstr.getValue(),
        TagNum.OrigTradeHandlingInstr.getValue(),
        TagNum.OrigTradeDate.getValue(),
        TagNum.OrigTradeID.getValue(),
        TagNum.OrigSecondaryTradeID.getValue(),
        TagNum.TransferReason.getValue(),
        TagNum.ExecType.getValue(),
        TagNum.TotNumTradeReports.getValue(),
        TagNum.LastRptRequested.getValue(),
        TagNum.UnsolicitedIndicator.getValue(),
        TagNum.SubscriptionRequestType.getValue(),
        TagNum.TradeReportRefID.getValue(),
        TagNum.SecondaryTradeReportRefID.getValue(),
        TagNum.SecondaryTradeReportID.getValue(),
        TagNum.TradeLinkID.getValue(),
        TagNum.TrdMatchID.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.SecondaryExecID.getValue(),
        TagNum.ExecRestatementReason.getValue(),
        TagNum.PreviouslyReported.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.AsOfIndicator.getValue(),
        TagNum.SettlSessID.getValue(),
        TagNum.SettlSessSubID.getValue(),
        TagNum.VenueType.getValue(),
        TagNum.MarketSegmentID.getValue(),
        TagNum.MarketID.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.UnderlyingTradingSessionID.getValue(),
        TagNum.UnderlyingTradingSessionSubID.getValue(),
        TagNum.LastQty.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.CalculatedCcyLastQty.getValue(),
        TagNum.Currency.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.LastSpotRate.getValue(),
        TagNum.LastForwardPoints.getValue(),
        TagNum.LastSwapPoints.getValue(),
        TagNum.LastMkt.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.AvgPx.getValue(),
        TagNum.AvgPxIndicator.getValue(),
        TagNum.NoPosAmt.getValue(),
        TagNum.MultiLegReportingType.getValue(),
        TagNum.TradeLegRefID.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.NoTrdRegTimestamps.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.UnderlyingSettlementDate.getValue(),
        TagNum.MatchStatus.getValue(),
        TagNum.MatchType.getValue(),
        TagNum.NoSides.getValue(),
        TagNum.Volatility.getValue(),
        TagNum.DividendYield.getValue(),
        TagNum.RiskFreeRate.getValue(),
        TagNum.CurrencyRatio.getValue(),
        TagNum.CopyMsgIndicator.getValue(),
        TagNum.NoTrdRepIndicators.getValue(),
        TagNum.TradePublishIndicator.getValue(),
        TagNum.ShortSaleReason.getValue(),
        TagNum.TierCode.getValue(),
        TagNum.MessageEventSource.getValue(),
        TagNum.LastUpdateTime.getValue(),
        TagNum.RndPx.getValue(),
        TagNum.TZTransactTime.getValue(),
        TagNum.ReportedPxDiff.getValue(),
        TagNum.GrossTradeAmt.getValue(),
        TagNum.RejectText.getValue(),
        TagNum.FeeMultiplier.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<>(Arrays.asList(new Integer[] {
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<>(Arrays.asList(new Integer[] {
        TagNum.TradeReportID.getValue(),
        TagNum.ExecType.getValue(),
        TagNum.PreviouslyReported.getValue(),
        TagNum.LastQty.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.NoSides.getValue()
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
     * TagNum = 571. Starting with 4.3 version.
     */
    protected String tradeReportID;
    
    /**
     * TagNum = 1003. Starting with 5.0 version.
     */
    protected String tradeID;
    
    /**
     * TagNum = 1040. Starting with 5.0 version.
     */
    protected String secondaryTradeID;
    
    /**
     * TagNum = 1041. Starting with 5.0 version.
     */
    protected String firmTradeID;
    
    /**
     * TagNum = 1042. Starting with 5.0 version.
     */
    protected String secondaryFirmTradeID;
    
    /**
     * TagNum = 487. Starting with 4.3 version.
     */
    protected TradeReportTransType tradeReportTransType;
    
    /**
     * TagNum = 856. Starting with 4.4 version.
     */
    protected TradeReportType tradeReportType;
    
    /**
     * TagNum = 939. Starting with 5.0SP1 version.
     */
    protected TrdRptStatus trdRptStatus;
           
    /**
     * TagNum = 568. Starting with 4.3 version.
     */
    protected String tradeRequestID;

    /**
     * TagNum = 828. Starting with 4.4 version.
     */
    protected TrdType trdType;

    /**
     * TagNum = 829. Starting with 4.4 version.
     */
    protected TrdSubType trdSubType;

    /**
     * TagNum = 855. Starting with 4.4 version.
     */
    protected SecondaryTrdType secondaryTrdType;

    /**
     * TagNum = 1123. Starting with 5.0 version.
     */
    protected TradeHandlingInstr tradeHandlingInstr;

    /**
     * TagNum = 1124. Starting with 5.0 version.
     */
    protected TradeHandlingInstr origTradeHandlingInstr;

    /**
     * TagNum = 1125. Starting with 5.0 version.
     */
    protected Date origTradeDate;
   
    /**
     * TagNum = 1126. Starting with 5.0 version.
     */
    protected String origTradeID;
   
    /**
     * TagNum = 1127. Starting with 5.0 version.
     */
    protected String origSecondaryTradeID;

    /**
     * TagNum = 830. Starting with 4.4 version.
     */
    protected String transferReason;

    /**
     * TagNum = 150. Starting with 4.3 version.
     */
    protected ExecType execType;

    /**
     * TagNum = 748. Starting with 4.4 version.
     */
    protected Integer totNumTradeReports;

    /**
     * TagNum = 912. Starting with 4.4 version.
     */
    protected Boolean lastRptRequested;

    /**
     * TagNum = 325. Starting with 4.4 version.
     */
    protected Boolean unsolicitedIndicator;

    /**
     * TagNum = 263. Starting with 4.4 version.
     */
    protected SubscriptionRequestType subscriptionRequestType;
    
    /**
     * TagNum = 572. Starting with 4.3 version.
     */
    protected String tradeReportRefID;
    
    /**
     * TagNum = 881. Starting with 4.4 version.
     */
    protected String secondaryTradeReportRefID;
     
    /**
     * TagNum = 818. Starting with 4.4 version.
     */
    protected String secondaryTradeReportID;

    /**
     * TagNum = 820. Starting with 4.4 version.
     */
    protected String tradeLinkID;

    /**
     * TagNum = 880. Starting with 4.4 version.
     */
    protected String trdMatchID;
    
    /**
     * TagNum = 17. Starting with 4.3 version.
     */
    protected String execID;

    /**
     * TagNum = 39 REQUIRED. Starting with 4.4 version.
     */
    protected OrdStatus ordStatus;

    /**
     * TagNum = 527. Starting with 4.3 version.
     */
    protected String secondaryExecID;
    
    /**
     * TagNum = 378. Starting with 4.3 version.
     */
    protected ExecRestatementReason execRestatementReason;

    /**
     * TagNum = 570. Starting with 4.3 version.
     */
    protected Boolean previouslyReported;

    /**
     * TagNum = 423. Starting with 4.4 version.
     */
    protected PriceType priceType;
    
    /**
     * Starting with 5.0 version.
     */
    protected RootParties rootParties;

    /**
     * TagNum = 1015. Starting with 5.0 version.
     */
    protected AsOfIndicator asOfIndicator;

    /**
     * TagNum = 716. Starting with 5.0 version.
     */
    protected String settlSessID;

    /**
     * TagNum = 716. Starting with 5.0 version.
     */
    protected String settlSessSubID;

    /**
     * TagNum = 1430. Starting with 5.0SP2 version.
     */
    protected VenueType venueType;

    /**
     * TagNum = 1300. Starting with 5.0SP2 version.
     */
    protected String marketSegmentID;

    /**
     * TagNum = 1301. Starting with 5.0SP2 version.
     */
    protected String marketID;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * Starting with 4.4 version.
     */
    protected FinancingDetails financingDetails;

    /**
     * Starting with 4.3 version.
     */
    protected OrderQtyData orderQtyData;

    /**
     * TagNum = 854. Starting with 4.4 version.
     */
    protected QtyType qtyType;

    /**
     * Starting with 4.4 version.
     */
    protected YieldData yieldData;

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;
    
     /**
     * TagNum = 822. Starting with 4.4 version.
     */
    protected String underlyingTradingSessionID;
    
     /**
     * TagNum = 823. Starting with 4.4 version.
     */
    protected String underlyingTradingSessionSubID;

    /**
     * TagNum = 32 REQUIRED. Starting with 4.3 version.
     */
    protected Double lastQty;

    /**
     * TagNum = 31 REQUIRED. Starting with 4.3 version.
     */
    protected Double lastPx;

    /**
     * TagNum = 1056. Starting with 5.0 version.
     */
    protected Double calculatedCcyLastQty;

    /**
     * TagNum = 15. Starting with 5.0SP1 version.
     */
    protected Currency currency;

    /**
     * TagNum = 120. Starting with 5.0SP1 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 669. Starting with 5.0 version.
     */
    protected Double lastParPx;

    /**
     * TagNum = 194. Starting with 4.3 version.
     */
    protected Double lastSpotRate;

    /**
     * TagNum = 195. Starting with 4.3 version.
     */
    protected Double lastForwardPoints;

    /**
     * TagNum = 1071. Starting with 5.0 version.
     */
    protected Double lastSwapPoints;

    /**
     * TagNum = 30. Starting with 4.3 version.
     */
    protected String lastMkt;

    /**
     * TagNum = 75 REQUIRED. Starting with 4.3 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 715. Starting with 4.4 version.
     */
    protected Date clearingBusinessDate;

    /**
     * TagNum = 6. Starting with 4.4 version.
     */
    protected Double avgPx;

    /**
     * Starting with 4.4 version.
     */
    protected SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData;

    /**
     * TagNum = 819. Starting with 4.4 version.
     */
    protected AvgPxIndicator avgPxIndicator;

    /**
     * TagNum = 753. Starting with 4.4 version.
     */
    protected Integer noPosAmt;

    /**
     * Starting with 4.4 version.
     */
    protected PosAmtGroup[] posAmtGroups;

    /**
     * TagNum = 442. Starting with 4.4 version.
     */
    protected MultiLegReportingType multilegReportingType;
    
     /**
     * TagNum = 824. Starting with 4.4 version.
     */
    protected String tradeLegRefID;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected TrdInstrmtLegGroup[] trdInstrmtLegGroups;

    /**
     * TagNum = 60. Starting with 4.3 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 768. Starting with 4.4 version.
     */
    protected Integer noTrdRegTimestamps;

    /**
     * Starting with 4.4 version.
     */
    protected TrdRegTimestampsGroup[] trdRegTimestampsGroups;

    /**
     * TagNum = 63. Starting with 4.3 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 4.3 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 64. Starting with 5.0SP1 version.
     */
    protected Date underlyingSettlementDate;

    /**
     * TagNum = 573. Starting with 4.3 version.
     */
    protected MatchStatus matchStatus;

    /**
     * TagNum = 574. Starting with 4.3 version.
     */
    protected MatchType matchType;

    /**
     * TagNum = 1115. Starting with 5.0 version.
     */
    protected OrderCategory orderCategory;

    /**
     * TagNum = 78 REQUIRED. Starting with 4.3 version.
     */
    protected Integer noSides;

    /**
     * Starting with 4.3 version.
     */
    protected TrdCapRptSideGroup[] trdCapRptSideGroups;

    /**
     * TagNum = 1188. Starting with 5.0SP1 version.
     */
    protected Double volatility;

    /**
     * TagNum = 1380. Starting with 5.0SP1 version.
     */
    protected Double dividendYield;

    /**
     * TagNum = 1190. Starting with 5.0SP1 version.
     */
    protected Double riskFreeRate;

    /**
     * TagNum = 1382. Starting with 5.0SP1 version.
     */
    protected Double currencyRatio;

    /**
     * TagNum = 797. Starting with 4.4 version.
     */
    protected Boolean copyMsgIndicator;

    /**
     * TagNum = 1387. Starting with 5.0SP1 version.
     */
    protected Integer noTrdRepIndicators;

    /**
     * Starting with 5.0SP1 version.
     */
    protected TrdRepIndicatorsGroup[] trdRepIndicatorsGroups;

    /**
     * TagNum = 852. Starting with 4.4 version.
     */
    protected Boolean publishTrdIndicator;

    /**
     * TagNum = 1390. Starting with 5.0SP1 version.
     */
    protected TradePublishIndicator tradePublishIndicator;

    /**
     * TagNum = 853. Starting with 4.4 version.
     */
    protected ShortSaleReason shortSaleReason;
    
     /**
     * TagNum = 994. Starting with 5.0 version.
     */
    protected String tierCode;

    /**
     * TagNum = 1011. Starting with 5.0 version.
     */
    protected String messageEventSource;

    /**
     * TagNum = 779. Starting with 5.0 version.
     */
    protected Date lastUpdateTime;

    /**
     * TagNum = 991. Starting with 5.0 version.
     */
    protected Double rndPx;

    /**
     * TagNum = 1132. Starting with 5.0 version.
     */
    protected Date TZTransactTime;

    /**
     * TagNum = 1134. Starting with 5.0 version.
     */
    protected Boolean reportedPxDiff;

    /**
     * TagNum = 381. Starting with 5.0 version.
     */
    protected Double grossTradeAmt;
   
    /**
     * TagNum = 1328. Starting with 5.0SP1 version.
     */
    protected String rejectText;

    /**
     * TagNum = 1329. Starting with 5.0SP1 version.
     */
    protected Double feeMultiplier;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeCaptureReportMsg() {
        super();
    }

    public TradeCaptureReportMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public TradeCaptureReportMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.TradeCaptureReport.getValue(), beginString);
    }

    public TradeCaptureReportMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.TradeCaptureReport.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeReportID)
    public String getTradeReportID() {
        return tradeReportID;
    }

    /**
     * Message field setter.
     * @param tradeReportID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeReportID)
    public void setTradeReportID(String tradeReportID) {
        this.tradeReportID = tradeReportID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeID)
    public String getTradeID() {
        return tradeID;
    }

    /**
     * Message field setter.
     * @param tradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeID)
    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryTradeID)
    public String getSecondaryTradeID() {
        return secondaryTradeID;
    }

    /**
     * Message field setter.
     * @param secondaryTradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryTradeID)
    public void setSecondaryTradeID(String secondaryTradeID) {
        this.secondaryTradeID = secondaryTradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FirmTradeID)
    public String getFirmTradeID() {
        return firmTradeID;
    }

    /**
     * Message field setter.
     * @param firmTradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FirmTradeID)
    public void setFirmTradeID(String firmTradeID) {
        this.firmTradeID = firmTradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryFirmTradeID)
    public String getSecondaryFirmTradeID() {
        return secondaryFirmTradeID;
    }

    /**
     * Message field setter.
     * @param secondaryFirmTradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryFirmTradeID)
    public void setSecondaryFirmTradeID(String secondaryFirmTradeID) {
        this.secondaryFirmTradeID = secondaryFirmTradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeReportTransType)
    public TradeReportTransType getTradeReportTransType() {
        return tradeReportTransType;
    }

    /**
     * Message field setter.
     * @param tradeReportTransType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeReportTransType)
    public void setTradeReportTransType(TradeReportTransType tradeReportTransType) {
        this.tradeReportTransType = tradeReportTransType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeReportType)
    public TradeReportType getTradeReportType() {
        return tradeReportType;
    }

    /**
     * Message field setter.
     * @param tradeReportType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeReportType)
    public void setTradeReportType(TradeReportType tradeReportType) {
        this.tradeReportType = tradeReportType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TrdRptStatus)
    public TrdRptStatus getTrdRptStatus() {
        return trdRptStatus;
    }

    /**
     * Message field setter.
     * @param trdRptStatus field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TrdRptStatus)
    public void setTrdRptStatus(TrdRptStatus trdRptStatus) {
        this.trdRptStatus = trdRptStatus;
    }
            
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeRequestID, required=true)
    public String getTradeRequestID() {
        return tradeRequestID;
    }

    /**
     * Message field setter.
     * @param tradeRequestID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeRequestID, required=true)
    public void setTradeRequestID(String tradeRequestID) {
        this.tradeRequestID = tradeRequestID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdType)
    public TrdType getTrdType() {
        return trdType;
    }

    /**
     * Message field setter.
     * @param trdType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdType)
    public void setTrdType(TrdType trdType) {
        this.trdType = trdType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdSubType)
    public TrdSubType getTrdSubType() {
        return trdSubType;
    }

    /**
     * Message field setter.
     * @param trdSubType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdSubType)
    public void setTrdSubType(TrdSubType trdSubType) {
        this.trdSubType = trdSubType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTrdType)
    public SecondaryTrdType getSecondaryTrdType() {
        return secondaryTrdType;
    }

    /**
     * Message field setter.
     * @param secondaryTrdType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTrdType)
    public void setSecondaryTrdType(SecondaryTrdType secondaryTrdType) {
        this.secondaryTrdType = secondaryTrdType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeHandlingInstr)
    public TradeHandlingInstr getTradeHandlingInstr() {
        return tradeHandlingInstr;
    }

    /**
     * Message field setter.
     * @param tradeHandlingInstr field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeHandlingInstr)
    public void setTradeHandlingInstr(TradeHandlingInstr tradeHandlingInstr) {
        this.tradeHandlingInstr = tradeHandlingInstr;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrigTradeHandlingInstr)
    public TradeHandlingInstr getOrigTradeHandlingInstr() {
        return origTradeHandlingInstr;
    }

    /**
     * Message field setter.
     * @param origTradeHandlingInstr field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrigTradeHandlingInstr)
    public void setOrigTradeHandlingInstr(TradeHandlingInstr origTradeHandlingInstr) {
        this.origTradeHandlingInstr = origTradeHandlingInstr;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrigTradeDate)
    public Date getOrigTradeDate() {
        return origTradeDate;
    }

    /**
     * Message field setter.
     * @param origTradeDate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrigTradeDate)
    public void setOrigTradeDate(Date origTradeDate) {
        this.origTradeDate = origTradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrigTradeID)
    public String getOrigTradeID() {
        return origTradeID;
    }

    /**
     * Message field setter.
     * @param origTradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrigTradeID)
    public void setOrigTradeID(String origTradeID) {
        this.origTradeID = origTradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrigSecondaryTradeID)
    public String getOrigSecondaryTradeID() {
        return origSecondaryTradeID;
    }

    /**
     * Message field setter.
     * @param origSecondaryTradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrigSecondaryTradeID)
    public void setOrigSecondaryTradeID(String origSecondaryTradeID) {
        this.origSecondaryTradeID = origSecondaryTradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransferReason)
    public String getTransferReason() {
        return transferReason;
    }

    /**
     * Message field setter.
     * @param transferReason field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransferReason)
    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecType)
    public ExecType getExecType() {
        return execType;
    }

    /**
     * Message field setter.
     * @param execType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecType)
    public void setExecType(ExecType execType) {
        this.execType = execType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNumTradeReports)
    public Integer getTotNumTradeReports() {
        return totNumTradeReports;
    }

    /**
     * Message field setter.
     * @param totNumTradeReports field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNumTradeReports)
    public void setTotNumTradeReports(Integer totNumTradeReports) {
        this.totNumTradeReports = totNumTradeReports;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastRptRequested)
    public Boolean getLastRptRequested() {
        return lastRptRequested;
    }

    /**
     * Message field setter.
     * @param lastRptRequested field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastRptRequested)
    public void setLastRptRequested(Boolean lastRptRequested) {
        this.lastRptRequested = lastRptRequested;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnsolicitedIndicator)
    public Boolean getUnsolicitedIndicator() {
        return unsolicitedIndicator;
    }

    /**
     * Message field setter.
     * @param unsolicitedIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnsolicitedIndicator)
    public void setUnsolicitedIndicator(Boolean unsolicitedIndicator) {
        this.unsolicitedIndicator = unsolicitedIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SubscriptionRequestType)
    public SubscriptionRequestType getSubscriptionRequestType() {
        return subscriptionRequestType;
    }

    /**
     * Message field setter.
     * @param subscriptionRequestType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SubscriptionRequestType)
    public void setSubscriptionRequestType(SubscriptionRequestType subscriptionRequestType) {
        this.subscriptionRequestType = subscriptionRequestType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeReportRefID)
    public String getTradeReportRefID() {
        return tradeReportRefID;
    }

    /**
     * Message field setter.
     * @param tradeReportRefID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeReportRefID)
    public void setTradeReportRefID(String tradeReportRefID) {
        this.tradeReportRefID = tradeReportRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTradeReportRefID)
    public String getSecondaryTradeReportRefID() {
        return secondaryTradeReportRefID;
    }

    /**
     * Message field setter.
     * @param secondaryTradeReportRefID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTradeReportRefID)
    public void setSecondaryTradeReportRefID(String secondaryTradeReportRefID) {
        this.secondaryTradeReportRefID = secondaryTradeReportRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTradeReportID)
    public String getSecondaryTradeReportID() {
        return secondaryTradeReportID;
    }

    /**
     * Message field setter.
     * @param secondaryTradeReportID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTradeReportID)
    public void setSecondaryTradeReportID(String secondaryTradeReportID) {
        this.secondaryTradeReportID = secondaryTradeReportID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeLinkID)
    public String getTradeLinkID() {
        return tradeLinkID;
    }

    /**
     * Message field setter.
     * @param tradeLinkID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeLinkID)
    public void setTradeLinkID(String tradeLinkID) {
        this.tradeLinkID = tradeLinkID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdMatchID)
    public String getTrdMatchID() {
        return trdMatchID;
    }

    /**
     * Message field setter.
     * @param trdMatchID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdMatchID)
    public void setTrdMatchID(String trdMatchID) {
        this.trdMatchID = trdMatchID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecID)
    public String getExecID() {
        return execID;
    }

    /**
     * Message field setter.
     * @param execID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecID)
    public void setExecID(String execID) {
        this.execID = execID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrdStatus)
    public OrdStatus getOrdStatus() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param ordStatus field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrdStatus)
    public void setOrdStatus(OrdStatus ordStatus) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryExecID)
    public String getSecondaryExecID() {
        return secondaryExecID;
    }

    /**
     * Message field setter.
     * @param secondaryExecID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryExecID)
    public void setSecondaryExecID(String secondaryExecID) {
        this.secondaryExecID = secondaryExecID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecRestatementReason)
    public ExecRestatementReason getExecRestatementReason() {
        return execRestatementReason;
    }

    /**
     * Message field setter.
     * @param execRestatementReason field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecRestatementReason)
    public void setExecRestatementReason(ExecRestatementReason execRestatementReason) {
        this.execRestatementReason = execRestatementReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PreviouslyReported)
    public Boolean getPreviouslyReported() {
        return previouslyReported;
    }

    /**
     * Message field setter.
     * @param previouslyReported field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PreviouslyReported)
    public void setPreviouslyReported(Boolean previouslyReported) {
        this.previouslyReported = previouslyReported;
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
    @FIXVersion(introduced="5.0")
    public RootParties getRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component class to the proper implementation.
     */
    @FIXVersion(introduced="5.0")
    public void setRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component class to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AsOfIndicator)
    public AsOfIndicator getAsOfIndicator() {
        return asOfIndicator;
    }

    /**
     * Message field setter.
     * @param asOfIndicator field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AsOfIndicator)
    public void setAsOfIndicator(AsOfIndicator asOfIndicator) {
        this.asOfIndicator = asOfIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlSessID)
    public String getSettlSessID() {
        return settlSessID;
    }

    /**
     * Message field setter.
     * @param settlSessID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlSessID)
    public void setSettlSessID(String settlSessID) {
        this.settlSessID = settlSessID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlSessSubID)
    public String getSettlSessSubID() {
        return settlSessSubID;
    }

    /**
     * Message field setter.
     * @param settlSessSubID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlSessSubID)
    public void setSettlSessSubID(String settlSessSubID) {
        this.settlSessSubID = settlSessSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.VenueType)
    public VenueType getVenueType() {
        return venueType;
    }

    /**
     * Message field setter.
     * @param venueType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.VenueType)
    public void setVenueType(VenueType venueType) {
        this.venueType = venueType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public String getMarketSegmentID() {
        return marketSegmentID;
    }

    /**
     * Message field setter.
     * @param marketSegmentID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public void setMarketSegmentID(String marketSegmentID) {
        this.marketSegmentID = marketSegmentID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.MarketID)
    public String getMarketID() {
        return marketID;
    }

    /**
     * Message field setter.
     * @param marketID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.MarketID)
    public void setMarketID(String marketID) {
        this.marketID = marketID;
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
     * Sets the Instrument component class to the proper implementation.
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
    @FIXVersion(introduced="4.3")
    public OrderQtyData getOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the OrderQtyData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the OrderQtyData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QtyType)
    public QtyType getQtyType() {
        return qtyType;
    }

    /**
     * Message field setter.
     * @param qtyType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QtyType)
    public void setQtyType(QtyType qtyType) {
        this.qtyType = qtyType;
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
     * Sets the YieldData component if used in this message to the proper implementation
     * class.
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingTradingSessionID)
    public String getUnderlyingTradingSessionID() {
        return underlyingTradingSessionID;
    }

    /**
     * Message field setter.
     * @param underlyingTradingSessionID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingTradingSessionID)
    public void setUnderlyingTradingSessionID(String underlyingTradingSessionID) {
        this.underlyingTradingSessionID = underlyingTradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingTradingSessionSubID)
    public String getUnderlyingTradingSessionSubID() {
        return underlyingTradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param underlyingTradingSessionSubID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingTradingSessionSubID)
    public void setUnderlyingTradingSessionSubID(String underlyingTradingSessionSubID) {
        this.underlyingTradingSessionSubID = underlyingTradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastQty, required=true)
    public Double getLastQty() {
        return lastQty;
    }

    /**
     * Message field setter.
     * @param lastQty field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastQty, required=true)
    public void setLastQty(Double lastQty) {
        this.lastQty = lastQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastPx, required=true)
    public Double getLastPx() {
        return lastPx;
    }

    /**
     * Message field setter.
     * @param lastPx field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastPx, required=true)
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CalculatedCcyLastQty)
    public Double getCalculatedCcyLastQty() {
        return calculatedCcyLastQty;
    }

    /**
     * Message field setter.
     * @param calculatedCcyLastQty field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CalculatedCcyLastQty)
    public void setCalculatedCcyLastQty(Double calculatedCcyLastQty) {
        this.calculatedCcyLastQty = calculatedCcyLastQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastParPx)
    public Double getLastParPx() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param lastParPx field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastParPx)
    public void setLastParPx(Double lastParPx) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastSpotRate)
    public Double getLastSpotRate() {
        return lastSpotRate;
    }

    /**
     * Message field setter.
     * @param lastSpotRate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastSpotRate)
    public void setLastSpotRate(Double lastSpotRate) {
        this.lastSpotRate = lastSpotRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastForwardPoints)
    public Double getLastForwardPoints() {
        return lastForwardPoints;
    }

    /**
     * Message field setter.
     * @param lastForwardPoints field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastForwardPoints)
    public void setLastForwardPoints(Double lastForwardPoints) {
        this.lastForwardPoints = lastForwardPoints;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastSwapPoints)
    public Double getLastSwapPoints() {
        return lastSwapPoints;
    }

    /**
     * Message field setter.
     * @param lastSwapPoints field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastSwapPoints)
    public void setLastSwapPoints(Double lastSwapPoints) {
        this.lastSwapPoints = lastSwapPoints;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public String getLastMkt() {
        return lastMkt;
    }

    /**
     * Message field setter.
     * @param lastMkt field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public void setLastMkt(String lastMkt) {
        this.lastMkt = lastMkt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeDate, required=true)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeDate, required=true)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    /**
     * Message field setter.
     * @param clearingBusinessDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AvgPx)
    public Double getAvgPx() {
        return avgPx;
    }

    /**
     * Message field setter.
     * @param avgPx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AvgPx)
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
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
    @TagNumRef(tagNum=TagNum.AvgPxIndicator)
    public AvgPxIndicator getAvgPxIndicator() {
        return avgPxIndicator;
    }

    /**
     * Message field setter.
     * @param avgPxIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AvgPxIndicator)
    public void setAvgPxIndicator(AvgPxIndicator avgPxIndicator) {
        this.avgPxIndicator = avgPxIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoPosAmt)
    public Integer getNoPosAmt() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link PosAmtGroup} groups. It will also create an array
     * of {@link PosAmtGroup} objects and set the <code>posAmtGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>posAmtGroups</code> array they will be discarded.<br/>
     * @param noPosAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoPosAmt)
    public void setNoPosAmt(Integer noPosAmt) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link PosAmtGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public PosAmtGroup[] getPosAmtGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link PosAmtGroup} object to the existing array of <code>posAmtGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noPosAmt</code> field to the proper value.<br/>
     * Note: If the <code>setNoPosAmt</code> method has been called there will already be a number of objects in the
     * <code>posAmtGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public PosAmtGroup addPosAmtGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link PosAmtGroup} object from the existing array of <code>posAmtGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noPosAmt</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public PosAmtGroup deletePosAmtGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link PosAmtGroup} objects from the <code>posAmtGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noPosAmt</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearPosAmtGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public MultiLegReportingType getMultilegReportingType() {
        return multilegReportingType;
    }

    /**
     * Message field setter.
     * @param multilegReportingType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        this.multilegReportingType = multilegReportingType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeLegRefID)
    public String getTradeLegRefID() {
        return tradeLegRefID;
    }

    /**
     * Message field setter.
     * @param tradeLegRefID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeLegRefID)
    public void setTradeLegRefID(String tradeLegRefID) {
        this.tradeLegRefID = tradeLegRefID;
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
     * This method sets the number of {@link TrdInstrmtLegGroup} components. It will also create an array
     * of {@link TrdInstrmtLegGroup} objects and set the <code>trdInstrmtLegGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>trdInstrmtLegGroups</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TrdInstrmtLegGroup} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public TrdInstrmtLegGroup[] getTrdInstrmtLegGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TrdInstrmtLegGroup} object to the existing array of <code>trdInstrmtLegGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>trdInstrmtLegGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public TrdInstrmtLegGroup addTrdInstrmtLegGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TrdInstrmtLegGroup} object from the existing array of <code>trdInstrmtLegGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public TrdInstrmtLegGroup deleteTrdInstrmtLegGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TrdInstrmtLegGroup} objects from the <code>trdInstrmtLegGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearTrdInstrmtLegGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @TagNumRef(tagNum=TagNum.NoTrdRegTimestamps)
    public Integer getNoTrdRegTimestamps() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TrdRegTimestampsGroup} components. It will also create an array
     * of {@link TrdRegTimestampsGroup} objects and set the <code>trdRegTimestampsGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>trdRegTimestampsGroups</code> array they will be discarded.<br/>
     * @param noTrdRegTimestamps number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoTrdRegTimestamps)
    public void setNoTrdRegTimestamps(Integer noTrdRegTimestamps) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TrdRegTimestampsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public TrdRegTimestampsGroup[] getTrdRegTimestampsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TrdRegTimestampsGroup} object to the existing array of <code>trdRegTimestampsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTrdRegTimestamps</code> field to the proper value.<br/>
     * Note: If the <code>setNoTrdRegTimestamps</code> method has been called there will already be a number of objects in the
     * <code>trdRegTimestampsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public TrdRegTimestampsGroup addTrdRegTimestampsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TrdRegTimestampsGroup} object from the existing array of <code>trdRegTimestampsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTrdRegTimestamps</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public TrdRegTimestampsGroup deleteTrdRegTimestampsGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TrdRegTimestampsGroup} objects from the <code>trdRegTimestampsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTrdRegTimestamps</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearTrdRegTimestampsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlType)
    public String getSettlType() {
        return settlType;
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced="4.3")
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
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingSettlementDate)
    public Date getUnderlyingSettlementDate() {
        return underlyingSettlementDate;
    }

    /**
     * Message field setter.
     * @param underlyingSettlementDate field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingSettlementDate)
    public void setUnderlyingSettlementDate(Date underlyingSettlementDate) {
        this.underlyingSettlementDate = underlyingSettlementDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    /**
     * Message field setter.
     * @param matchStatus field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MatchType)
    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * Message field setter.
     * @param matchType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MatchType)
    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCategory)
    public OrderCategory getOrderCategory() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param orderCategory field value
     */
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCategory)
    public void setOrderCategory(OrderCategory orderCategory) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoSides)
    public Integer getNoSides() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TrdCapRptSideGroup} groups. It will also create an array
     * of {@link TrdCapRptSideGroup} objects and set the <code>trdCapRptSideGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>trdCapRptSideGroups</code> array they will be discarded.<br/>
     * @param noSides field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoSides)
    public void setNoSides(Integer noSides) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TrdCapRptSideGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public TrdCapRptSideGroup[] getTrdCapRptSideGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TrdCapRptSideGroup} object to the existing array of <code>trdCapRptSideGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSides</code> field to the proper value.<br/>
     * Note: If the <code>setNoSides</code> method has been called there will already be a number of objects in the
     * <code>trdCapRptSideGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public TrdCapRptSideGroup addTrdCapRptSideGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TrdCapRptSideGroup} object from the existing array of <code>trdCapRptSideGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSides</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public TrdCapRptSideGroup deleteTrdCapRptSideGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TrdCapRptSideGroup} objects from the <code>trdCapRptSideGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSides</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearTrdCapRptSideGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.Volatility)
    public Double getVolatility() {
        return volatility;
    }

    /**
     * Message field setter.
     * @param volatility field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.Volatility)
    public void setVolatility(Double volatility) {
        this.volatility = volatility;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DividendYield)
    public Double getDividendYield() {
        return dividendYield;
    }

    /**
     * Message field setter.
     * @param dividendYield field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DividendYield)
    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RiskFreeRate)
    public Double getRiskFreeRate() {
        return riskFreeRate;
    }

    /**
     * Message field setter.
     * @param riskFreeRate field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RiskFreeRate)
    public void setRiskFreeRate(Double riskFreeRate) {
        this.riskFreeRate = riskFreeRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.CurrencyRatio)
    public Double getCurrencyRatio() {
        return currencyRatio;
    }

    /**
     * Message field setter.
     * @param currencyRatio field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.CurrencyRatio)
    public void setCurrencyRatio(Double currencyRatio) {
        this.currencyRatio = currencyRatio;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CopyMsgIndicator)
    public Boolean getCopyMsgIndicator() {
        return copyMsgIndicator;
    }

    /**
     * Message field setter.
     * @param copyMsgIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CopyMsgIndicator)
    public void setCopyMsgIndicator(Boolean copyMsgIndicator) {
        this.copyMsgIndicator = copyMsgIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoTrdRepIndicators)
    public Integer getNoTrdRepIndicators() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TrdRepIndicatorsGroup} groups. It will also create an array
     * of {@link TrdRepIndicatorsGroup} objects and set the <code>trdRepIndicatorsGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>trdRepIndicatorsGroups</code> array they will be discarded.<br/>
     * @param noTrdRepIndicators field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoTrdRepIndicators)
    public void setNoTrdRepIndicators(Integer noTrdRepIndicators) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TrdRepIndicatorsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public TrdRepIndicatorsGroup[] getTrdRepIndicatorsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TrdRepIndicatorsGroup} object to the existing array of <code>trdRepIndicatorsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTrdRepIndicators</code> field to the proper value.<br/>
     * Note: If the <code>setNoTrdRepIndicators</code> method has been called there will already be a number of objects in the
     * <code>TrdRepIndicatorsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public TrdRepIndicatorsGroup addTrdRepIndicatorsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TrdRepIndicatorsGroup} object from the existing array of <code>trdRepIndicatorsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTrdRepIndicators</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public TrdRepIndicatorsGroup deleteTrdRepIndicatorsGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TrdRepIndicatorsGroup} objects from the <code>trdRepIndicatorsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTrdRepIndicators</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearTrdRepIndicatorsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.PublishTrdIndicator)
    public Boolean getPublishTrdIndicator() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param publishTrdIndicator field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.PublishTrdIndicator)
    public void setPublishTrdIndicator(Boolean publishTrdIndicator) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradePublishIndicator)
    public TradePublishIndicator getTradePublishIndicator() {
        return tradePublishIndicator;
    }

    /**
     * Message field setter.
     * @param tradePublishIndicator field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradePublishIndicator)
    public void setTradePublishIndicator(TradePublishIndicator tradePublishIndicator) {
        this.tradePublishIndicator = tradePublishIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ShortSaleReason)
    public ShortSaleReason getShortSaleReason() {
        return shortSaleReason;
    }

    /**
     * Message field setter.
     * @param shortSaleReason field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ShortSaleReason)
    public void setShortSaleReason(ShortSaleReason shortSaleReason) {
        this.shortSaleReason = shortSaleReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TierCode)
    public String getTierCode() {
        return tierCode;
    }

    /**
     * Message field setter.
     * @param tierCode field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TierCode)
    public void setTierCode(String tierCode) {
        this.tierCode = tierCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MessageEventSource)
    public String getMessageEventSource() {
        return messageEventSource;
    }

    /**
     * Message field setter.
     * @param messageEventSource field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MessageEventSource)
    public void setMessageEventSource(String messageEventSource) {
        this.messageEventSource = messageEventSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastUpdateTime)
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Message field setter.
     * @param lastUpdateTime field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastUpdateTime)
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RndPx)
    public Double getRndPx() {
        return rndPx;
    }

    /**
     * Message field setter.
     * @param rndPx field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RndPx)
    public void setRndPx(Double rndPx) {
        this.rndPx = rndPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TZTransactTime)
    public Date getTZTransactTime() {
        return TZTransactTime;
    }

    /**
     * Message field setter.
     * @param TZTransactTime field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TZTransactTime)
    public void setTZTransactTime(Date TZTransactTime) {
        this.TZTransactTime = TZTransactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ReportedPxDiff)
    public Boolean getReportedPxDiff() {
        return reportedPxDiff;
    }

    /**
     * Message field setter.
     * @param reportedPxDiff field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ReportedPxDiff)
    public void setReportedPxDiff(Boolean reportedPxDiff) {
        this.reportedPxDiff = reportedPxDiff;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.GrossTradeAmt)
    public Double getGrossTradeAmt() {
        return grossTradeAmt;
    }

    /**
     * Message field setter.
     * @param grossTradeAmt field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.GrossTradeAmt)
    public void setGrossTradeAmt(Double grossTradeAmt) {
        this.grossTradeAmt = grossTradeAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RejectText)
    public String getRejectText() {
        return rejectText;
    }

    /**
     * Message field setter.
     * @param rejectText field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RejectText)
    public void setRejectText(String rejectText) {
        this.rejectText = rejectText;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.FeeMultiplier)
    public Double getFeeMultiplier() {
        return feeMultiplier;
    }

    /**
     * Message field setter.
     * @param feeMultiplier field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.FeeMultiplier)
    public void setFeeMultiplier(Double feeMultiplier) {
        this.feeMultiplier = feeMultiplier;
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
        if (lastQty == null) {
            errorMsg.append(" [LastQty]");
            hasMissingTag = true;
        }
        if (lastPx == null) {
            errorMsg.append(" [LastPx]");
            hasMissingTag = true;
        }
        if (tradeDate == null) {
            errorMsg.append(" [TradeDate]");
            hasMissingTag = true;
        }
        if (noSides == null) {
            errorMsg.append(" [NoSides]");
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
            TagEncoder.encode(bao, TagNum.TradeReportID, tradeReportID);
            TagEncoder.encode(bao, TagNum.TradeID, tradeID);
            TagEncoder.encode(bao, TagNum.SecondaryTradeID, secondaryTradeID);
            TagEncoder.encode(bao, TagNum.FirmTradeID, firmTradeID);
            TagEncoder.encode(bao, TagNum.SecondaryFirmTradeID, secondaryFirmTradeID);
            if (tradeReportTransType != null) {
                TagEncoder.encode(bao, TagNum.TradeReportTransType, tradeReportTransType.getValue());
            }
            if (tradeReportType != null) {
                TagEncoder.encode(bao, TagNum.TradeReportType, tradeReportType.getValue());
            }
            if (trdRptStatus != null) {
                TagEncoder.encode(bao, TagNum.TrdRptStatus, trdRptStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeRequestID, tradeRequestID);
            if (trdType != null) {
                TagEncoder.encode(bao, TagNum.TrdType, trdType.getValue());
            }
            if (trdSubType != null) {
                TagEncoder.encode(bao, TagNum.TrdSubType, trdSubType.getValue());
            }
            if (secondaryTrdType != null) {
                TagEncoder.encode(bao, TagNum.SecondaryTrdType, secondaryTrdType.getValue());
            }
            if (tradeHandlingInstr != null) {
                TagEncoder.encode(bao, TagNum.TradeHandlingInstr, tradeHandlingInstr.getValue());
            }
            if (origTradeHandlingInstr != null) {
                TagEncoder.encode(bao, TagNum.OrigTradeHandlingInstr, origTradeHandlingInstr.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.OrigTradeDate, origTradeDate);
            TagEncoder.encode(bao, TagNum.OrigTradeID, origTradeID);
            TagEncoder.encode(bao, TagNum.OrigSecondaryTradeID, origSecondaryTradeID);
            TagEncoder.encode(bao, TagNum.TransferReason, transferReason);
            if (execType != null) {
                TagEncoder.encode(bao, TagNum.ExecType, execType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TotNumTradeReports, totNumTradeReports);
            TagEncoder.encode(bao, TagNum.LastRptRequested, lastRptRequested);
            TagEncoder.encode(bao, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
            if (subscriptionRequestType != null) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeReportRefID, tradeReportRefID);
            TagEncoder.encode(bao, TagNum.SecondaryTradeReportRefID, secondaryTradeReportRefID);
            TagEncoder.encode(bao, TagNum.SecondaryTradeReportID, secondaryTradeReportID);
            TagEncoder.encode(bao, TagNum.TradeLinkID, tradeLinkID);
            TagEncoder.encode(bao, TagNum.TrdMatchID, trdMatchID);
            TagEncoder.encode(bao, TagNum.ExecID, execID);
            if (ordStatus != null) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.SecondaryExecID, secondaryExecID);
            if (execRestatementReason != null) {
                TagEncoder.encode(bao, TagNum.ExecRestatementReason, execRestatementReason.getValue());
            }
            TagEncoder.encode(bao, TagNum.PreviouslyReported, previouslyReported);
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (rootParties != null) {
                bao.write(rootParties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (asOfIndicator != null) {
                TagEncoder.encode(bao, TagNum.AsOfIndicator, asOfIndicator.getValue());
            }
            TagEncoder.encode(bao, TagNum.SettlSessID, settlSessID);
            TagEncoder.encode(bao, TagNum.SettlSessSubID, settlSessSubID);
            if (venueType != null) {
                TagEncoder.encode(bao, TagNum.VenueType, venueType.getValue());
            }
            TagEncoder.encode(bao, TagNum.MarketSegmentID, marketSegmentID);
            TagEncoder.encode(bao, TagNum.MarketID, marketID);
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (financingDetails != null) {
                bao.write(financingDetails.encode(MsgSecureType.ALL_FIELDS));
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (qtyType != null) {
                TagEncoder.encode(bao, TagNum.QtyType, qtyType.getValue());
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(MsgSecureType.ALL_FIELDS));
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
            TagEncoder.encode(bao, TagNum.UnderlyingTradingSessionID, underlyingTradingSessionID);
            TagEncoder.encode(bao, TagNum.UnderlyingTradingSessionSubID, underlyingTradingSessionSubID);
            TagEncoder.encode(bao, TagNum.LastQty, lastQty);
            TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            TagEncoder.encode(bao, TagNum.CalculatedCcyLastQty, calculatedCcyLastQty);
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.LastParPx, lastParPx);
            TagEncoder.encode(bao, TagNum.LastSpotRate, lastSpotRate);
            TagEncoder.encode(bao, TagNum.LastForwardPoints, lastForwardPoints);
            TagEncoder.encode(bao, TagNum.LastSwapPoints, lastSwapPoints);
            TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (avgPxIndicator != null) {
                TagEncoder.encode(bao, TagNum.AvgPxIndicator, avgPxIndicator.getValue());
            }
            if (noPosAmt != null) {
                TagEncoder.encode(bao, TagNum.NoPosAmt, noPosAmt);
                if (posAmtGroups != null && posAmtGroups.length == noPosAmt.intValue()) {
                    for (int i = 0; i < noPosAmt.intValue(); i++) {
                        if (posAmtGroups[i] != null) {
                            bao.write(posAmtGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "PosAmtGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoPosAmt.getValue(), error);
                }
            }
            if (multilegReportingType != null) {
                TagEncoder.encode(bao, TagNum.MultiLegReportingType, multilegReportingType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeLegRefID, tradeLegRefID);
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (trdInstrmtLegGroups != null && trdInstrmtLegGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (trdInstrmtLegGroups[i] != null) {
                            bao.write(trdInstrmtLegGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TrdInstrmtLegGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            if (noTrdRegTimestamps != null) {
                TagEncoder.encode(bao, TagNum.NoTrdRegTimestamps, noTrdRegTimestamps);
                if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length == noTrdRegTimestamps.intValue()) {
                    for (int i = 0; i < noTrdRegTimestamps.intValue(); i++) {
                        if (trdRegTimestampsGroups[i] != null) {
                            bao.write(trdRegTimestampsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TrdRegTimestampsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTrdRegTimestamps.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            TagEncoder.encodeDate(bao, TagNum.UnderlyingSettlementDate, underlyingSettlementDate);
            if (matchStatus != null) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            if (matchType != null) {
                TagEncoder.encode(bao, TagNum.MatchType, matchType.getValue());
            }
            if (orderCategory != null) {
                TagEncoder.encode(bao, TagNum.OrderCategory, orderCategory.getValue());
            }
            if (noSides != null) {
                TagEncoder.encode(bao, TagNum.NoSides, noSides);
                if (trdCapRptSideGroups != null && trdCapRptSideGroups.length == noSides.intValue()) {
                    for (int i = 0; i < noSides.intValue(); i++) {
                        if (trdCapRptSideGroups[i] != null) {
                            bao.write(trdCapRptSideGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TrdCapRptSideGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSides.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.Volatility, volatility);
            TagEncoder.encode(bao, TagNum.DividendYield, dividendYield);
            TagEncoder.encode(bao, TagNum.RiskFreeRate, riskFreeRate);
            TagEncoder.encode(bao, TagNum.CurrencyRatio, currencyRatio);
            TagEncoder.encode(bao, TagNum.CopyMsgIndicator, copyMsgIndicator);
            if (noTrdRepIndicators != null) {
                TagEncoder.encode(bao, TagNum.NoTrdRepIndicators, noTrdRepIndicators);
                if (trdRepIndicatorsGroups != null && trdRepIndicatorsGroups.length == noTrdRepIndicators.intValue()) {
                    for (int i = 0; i < noTrdRepIndicators.intValue(); i++) {
                        if (trdRepIndicatorsGroups[i] != null) {
                            bao.write(trdRepIndicatorsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TrdRepIndicatorsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoMiscFees.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.PublishTrdIndicator, publishTrdIndicator);
            if (tradePublishIndicator != null) {
                TagEncoder.encode(bao, TagNum.TradePublishIndicator, tradePublishIndicator.getValue());
            }
            if (shortSaleReason != null) {
                TagEncoder.encode(bao, TagNum.ShortSaleReason, shortSaleReason.getValue());
            }
            TagEncoder.encode(bao, TagNum.TierCode, tierCode);
            TagEncoder.encode(bao, TagNum.MessageEventSource, messageEventSource);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.LastUpdateTime, lastUpdateTime);
            TagEncoder.encode(bao, TagNum.RndPx, rndPx);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TZTransactTime, TZTransactTime);
            TagEncoder.encode(bao, TagNum.ReportedPxDiff, reportedPxDiff);
            TagEncoder.encode(bao, TagNum.GrossTradeAmt, grossTradeAmt);
            TagEncoder.encode(bao, TagNum.RejectText, rejectText);
            TagEncoder.encode(bao, TagNum.FeeMultiplier, feeMultiplier);
 
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
            case TradeReportID:
                tradeReportID = new String(tag.value, sessionCharset);
                break;

            case TradeID:
                tradeID = new String(tag.value, sessionCharset);
                break;
                
            case SecondaryTradeID:
                secondaryTradeID = new String(tag.value, sessionCharset);
                break;
                
            case FirmTradeID:
                firmTradeID = new String(tag.value, sessionCharset);
                break;
                
            case SecondaryFirmTradeID:
                secondaryFirmTradeID = new String(tag.value, sessionCharset);
                break;

            case TradeReportTransType:
                tradeReportTransType = TradeReportTransType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradeReportType:
                tradeReportType = TradeReportType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TrdRptStatus:
                trdRptStatus = TrdRptStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;
                        
            case TradeRequestID:
                tradeRequestID = new String(tag.value, sessionCharset);
                break;

            case TrdType:
                trdType = TrdType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TrdSubType:
                trdSubType = TrdSubType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SecondaryTrdType:
                secondaryTrdType = SecondaryTrdType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradeHandlingInstr:
                tradeHandlingInstr = TradeHandlingInstr.valueFor(new String(tag.value, sessionCharset));
                break;

            case OrigTradeHandlingInstr:
                origTradeHandlingInstr = TradeHandlingInstr.valueFor(new String(tag.value, sessionCharset));
                break;

            case OrigTradeDate:
                origTradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case OrigTradeID:
                origTradeID = new String(tag.value, sessionCharset);
                break;

            case OrigSecondaryTradeID:
                origSecondaryTradeID = new String(tag.value, sessionCharset);
                break;
                
            case TransferReason:
                transferReason = new String(tag.value, sessionCharset);
                break;

            case ExecType:
                execType = ExecType.valueFor(new String(tag.value, sessionCharset));
                break;

            case TotNumTradeReports:
                totNumTradeReports = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastRptRequested:
                lastRptRequested = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case UnsolicitedIndicator:
                unsolicitedIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case SubscriptionRequestType:
                subscriptionRequestType = SubscriptionRequestType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;
               
            case TradeReportRefID:
                tradeReportRefID = new String(tag.value, sessionCharset);
                break;
               
            case SecondaryTradeReportRefID:
                secondaryTradeReportRefID = new String(tag.value, sessionCharset);
                break;
               
            case SecondaryTradeReportID:
                secondaryTradeReportID = new String(tag.value, sessionCharset);
                break;
                
            case TradeLinkID:
                tradeLinkID = new String(tag.value, sessionCharset);
                break;
                
            case TrdMatchID:
                trdMatchID = new String(tag.value, sessionCharset);
                break;

            case ExecID:
                execID = new String(tag.value, sessionCharset);
                break;

            case OrdStatus:
                ordStatus = OrdStatus.valueFor(new String(tag.value, sessionCharset));
                break;

           case SecondaryExecID:
                secondaryExecID = new String(tag.value, sessionCharset);
                break;

            case ExecRestatementReason:
                execRestatementReason = ExecRestatementReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PreviouslyReported:
                previouslyReported = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case PriceType:
                priceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AsOfIndicator:
                asOfIndicator = AsOfIndicator.valueFor(new String(tag.value, sessionCharset));
                break;

           case SettlSessID:
                settlSessID = new String(tag.value, sessionCharset);
                break;

           case SettlSessSubID:
                settlSessSubID = new String(tag.value, sessionCharset);
                break;

            case VenueType:
                venueType = VenueType.valueFor(new String(tag.value, sessionCharset));
                break;

            case MarketSegmentID:
                marketSegmentID = new String(tag.value, sessionCharset);
                break;

            case MarketID:
                marketID = new String(tag.value, sessionCharset);
                break;

            case QtyType:
                qtyType = QtyType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case UnderlyingTradingSessionID:
                underlyingTradingSessionID = new String(tag.value, sessionCharset);
                break;

            case UnderlyingTradingSessionSubID:
                underlyingTradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case LastQty:
                lastQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LastPx:
                lastPx = new Double(new String(tag.value, sessionCharset));
                break;

            case CalculatedCcyLastQty:
                calculatedCcyLastQty = new Double(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case LastParPx:
                lastParPx = new Double(new String(tag.value, sessionCharset));
                break;

            case LastSpotRate:
                lastSpotRate = new Double(new String(tag.value, sessionCharset));
                break;

            case LastForwardPoints:
                lastForwardPoints = new Double(new String(tag.value, sessionCharset));
                break;

            case LastSwapPoints:
                lastSwapPoints = new Double(new String(tag.value, sessionCharset));
                break;

            case LastMkt:
                lastMkt = new String(tag.value, sessionCharset);
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ClearingBusinessDate:
                clearingBusinessDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case AvgPx:
                avgPx = new Double(new String(tag.value, sessionCharset));
                break;

            case AvgPxIndicator:
                avgPxIndicator = AvgPxIndicator.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoPosAmt:
                noPosAmt = new Integer(new String(tag.value, sessionCharset));
                break;

            case MultiLegReportingType:
                multilegReportingType = MultiLegReportingType.valueFor(new String(tag.value, sessionCharset));
                break;

            case TradeLegRefID:
                tradeLegRefID = new String(tag.value, sessionCharset);
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case NoTrdRegTimestamps:
                noTrdRegTimestamps = new Integer(new String(tag.value, sessionCharset));
                break;

            case SettlType:
                settlType = new String(tag.value, sessionCharset);
                break;

            case SettlDate:
                settlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSettlementDate:
                underlyingSettlementDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case MatchStatus:
                matchStatus = MatchStatus.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MatchType:
                matchType = MatchType.valueFor(new String(tag.value, sessionCharset));
                break;

            case OrderCategory:
                orderCategory = OrderCategory.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case NoSides:
                noSides = new Integer(new String(tag.value, sessionCharset));
                break;

            case Volatility:
                volatility = new Double(new String(tag.value, sessionCharset));
                break;

            case DividendYield:
                dividendYield = new Double(new String(tag.value, sessionCharset));
                break;

            case RiskFreeRate:
                riskFreeRate = new Double(new String(tag.value, sessionCharset));
                break;

            case CurrencyRatio:
                currencyRatio = new Double(new String(tag.value, sessionCharset));
                break;

            case CopyMsgIndicator:
                copyMsgIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoTrdRepIndicators:
                noTrdRepIndicators = new Integer(new String(tag.value, sessionCharset));
                break;

            case PublishTrdIndicator:
                publishTrdIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case TradePublishIndicator:
                tradePublishIndicator = TradePublishIndicator.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ShortSaleReason:
                shortSaleReason = ShortSaleReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TierCode:
                tierCode = new String(tag.value, sessionCharset);
                break;

            case MessageEventSource:
                messageEventSource = new String(tag.value, sessionCharset);
                break;

            case LastUpdateTime:
                lastUpdateTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case RndPx:
                rndPx = new Double(new String(tag.value, sessionCharset));
                break;

            case TZTransactTime:
                TZTransactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ReportedPxDiff:
                reportedPxDiff = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case GrossTradeAmt:
                grossTradeAmt = new Double(new String(tag.value, sessionCharset));
                break;
                
           case RejectText:
                rejectText = new String(tag.value, sessionCharset);
                break;

            case FeeMultiplier:
                feeMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TradeCaptureReportMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{TradeCaptureReportMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.TradeReportID, tradeReportID);
        printTagValue(b, TagNum.TradeID, tradeID);
        printTagValue(b, TagNum.SecondaryTradeID, secondaryTradeID);
        printTagValue(b, TagNum.FirmTradeID, firmTradeID);
        printTagValue(b, TagNum.SecondaryFirmTradeID, secondaryFirmTradeID);
        printTagValue(b, TagNum.TradeReportTransType, tradeReportTransType);
        printTagValue(b, TagNum.TradeReportType, tradeReportType);
        printTagValue(b, TagNum.TrdRptStatus, trdRptStatus);
        printTagValue(b, TagNum.TradeRequestID, tradeRequestID);
        printTagValue(b, TagNum.TrdType, trdType);
        printTagValue(b, TagNum.TrdSubType, trdSubType);
        printTagValue(b, TagNum.SecondaryTrdType, secondaryTrdType);
        printTagValue(b, TagNum.TradeHandlingInstr, tradeHandlingInstr);
        printTagValue(b, TagNum.OrigTradeHandlingInstr, origTradeHandlingInstr);
        printDateTagValue(b, TagNum.OrigTradeDate, origTradeDate);
        printTagValue(b, TagNum.OrigTradeID, origTradeID);
        printTagValue(b, TagNum.OrigSecondaryTradeID, origSecondaryTradeID);
        printTagValue(b, TagNum.TransferReason, transferReason);
        printTagValue(b, TagNum.ExecType, execType);
        printTagValue(b, TagNum.TotNumTradeReports, totNumTradeReports);
        printTagValue(b, TagNum.LastRptRequested, lastRptRequested);
        printTagValue(b, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
        printTagValue(b, TagNum.SubscriptionRequestType, subscriptionRequestType);
        printTagValue(b, TagNum.TradeReportRefID, tradeReportRefID);
        printTagValue(b, TagNum.SecondaryTradeReportRefID, secondaryTradeReportRefID);
        printTagValue(b, TagNum.SecondaryTradeReportID, secondaryTradeReportID);
        printTagValue(b, TagNum.TradeLinkID, tradeLinkID);
        printTagValue(b, TagNum.TrdMatchID, trdMatchID);
        printTagValue(b, TagNum.ExecID, execID);
        printTagValue(b, TagNum.OrdStatus, ordStatus);
        printTagValue(b, TagNum.SecondaryExecID, secondaryExecID);
        printTagValue(b, TagNum.ExecRestatementReason, execRestatementReason);
        printTagValue(b, TagNum.PreviouslyReported, previouslyReported);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, rootParties);
        printTagValue(b, TagNum.AsOfIndicator, asOfIndicator);
        printTagValue(b, TagNum.SettlSessID, settlSessID);
        printTagValue(b, TagNum.SettlSessSubID, settlSessSubID);
        printTagValue(b, TagNum.VenueType, venueType);
        printTagValue(b, TagNum.MarketSegmentID, marketSegmentID);
        printTagValue(b, TagNum.MarketID, marketID);
        printTagValue(b, instrument);
        printTagValue(b, financingDetails);
        printTagValue(b, orderQtyData);
        printTagValue(b, TagNum.QtyType, qtyType);
        printTagValue(b, yieldData);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.UnderlyingTradingSessionID, underlyingTradingSessionID);
        printTagValue(b, TagNum.UnderlyingTradingSessionSubID, underlyingTradingSessionSubID);
        printTagValue(b, TagNum.LastQty, lastQty);
        printTagValue(b, TagNum.LastPx, lastPx);
        printTagValue(b, TagNum.CalculatedCcyLastQty, calculatedCcyLastQty);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printTagValue(b, TagNum.LastParPx, lastParPx);
        printTagValue(b, TagNum.LastSpotRate, lastSpotRate);
        printTagValue(b, TagNum.LastForwardPoints, lastForwardPoints);
        printTagValue(b, TagNum.LastSwapPoints, lastSwapPoints);
        printTagValue(b, TagNum.LastMkt, lastMkt);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, TagNum.AvgPx, avgPx);
        printTagValue(b, spreadOrBenchmarkCurveData);
        printTagValue(b, TagNum.AvgPxIndicator, avgPxIndicator);
        printTagValue(b, TagNum.NoPosAmt, noPosAmt);
        printTagValue(b, posAmtGroups);
        printTagValue(b, TagNum.MultiLegReportingType, multilegReportingType);
        printTagValue(b, TagNum.TradeLegRefID, tradeLegRefID);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, trdInstrmtLegGroups);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.NoTrdRegTimestamps, noTrdRegTimestamps);
        printTagValue(b, trdRegTimestampsGroups);
        printTagValue(b, TagNum.SettlType, settlType);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printDateTagValue(b, TagNum.UnderlyingSettlementDate, underlyingSettlementDate);
        printTagValue(b, TagNum.MatchStatus, matchStatus);
        printTagValue(b, TagNum.MatchType, matchType);
        printTagValue(b, TagNum.OrderCategory, orderCategory);
        printTagValue(b, TagNum.NoSides, noSides);
        printTagValue(b, trdCapRptSideGroups);
        printTagValue(b, TagNum.Volatility, volatility);
        printTagValue(b, TagNum.DividendYield, dividendYield);
        printTagValue(b, TagNum.RiskFreeRate, riskFreeRate);
        printTagValue(b, TagNum.CurrencyRatio, currencyRatio);
        printTagValue(b, TagNum.CopyMsgIndicator, copyMsgIndicator);
        printTagValue(b, TagNum.NoTrdRepIndicators, noTrdRepIndicators);
        printTagValue(b, trdRepIndicatorsGroups);
        printTagValue(b, TagNum.PublishTrdIndicator, publishTrdIndicator);
        printTagValue(b, TagNum.TradePublishIndicator, tradePublishIndicator);
        printTagValue(b, TagNum.ShortSaleReason, shortSaleReason);
        printTagValue(b, TagNum.TierCode, tierCode);
        printTagValue(b, TagNum.MessageEventSource, messageEventSource);
        printUTCDateTimeTagValue(b, TagNum.LastUpdateTime, lastUpdateTime);
        printTagValue(b, TagNum.RndPx, rndPx);
        printUTCDateTimeTagValue(b, TagNum.TZTransactTime, TZTransactTime);
        printTagValue(b, TagNum.ReportedPxDiff, reportedPxDiff);
        printTagValue(b, TagNum.GrossTradeAmt, grossTradeAmt);
        printTagValue(b, TagNum.RejectText, rejectText);
        printTagValue(b, TagNum.FeeMultiplier, feeMultiplier);   
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
