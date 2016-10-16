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
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradeHandlingInstr;

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
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.group.TradeAllocGroup;
import net.hades.fix.message.group.TrdCapRptAckSideGroup;
import net.hades.fix.message.group.TrdInstrmtLegGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.group.TrdRepIndicatorsGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.AsOfIndicator;
import net.hades.fix.message.type.AvgPxIndicator;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.ShortSaleReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TradePublishIndicator;
import net.hades.fix.message.type.TradeReportTransType;
import net.hades.fix.message.type.TradeReportType;
import net.hades.fix.message.type.TrdRptStatus;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.type.VenueType;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Trade Capture Report Ack message can be:
 * <ul>
 *      <li>Used to acknowledge trade capture reports received from a counterparty</li>
 *      <li>Used to reject a trade capture report received from a counterparty</li>
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TradeCaptureReportAckMsg extends FIXMsg {

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
        TagNum.TradeReportRefID.getValue(),
        TagNum.SecondaryTradeReportRefID.getValue(),
        TagNum.TrdRptStatus.getValue(),
        TagNum.TradeReportRejectReason.getValue(),
        TagNum.SecondaryTradeReportID.getValue(),
        TagNum.SubscriptionRequestType.getValue(),
        TagNum.TradeLinkID.getValue(),
        TagNum.TrdMatchID.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.SecondaryExecID.getValue(),
        TagNum.ExecRestatementReason.getValue(),
        TagNum.PreviouslyReported.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.UnderlyingTradingSessionID.getValue(),
        TagNum.UnderlyingTradingSessionSubID.getValue(),
        TagNum.SettlSessID.getValue(),
        TagNum.SettlSessSubID.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.LastQty.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.VenueType.getValue(),
        TagNum.MarketSegmentID.getValue(),
        TagNum.MarketID.getValue(),
        TagNum.LastParPx.getValue(),
        TagNum.CalculatedCcyLastQty.getValue(),
        TagNum.LastSwapPoints.getValue(),
        TagNum.Currency.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.LastSpotRate.getValue(),
        TagNum.LastForwardPoints.getValue(),
        TagNum.LastMkt.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.AvgPx.getValue(),
        TagNum.AvgPxIndicator.getValue(),
        TagNum.MultiLegReportingType.getValue(),
        TagNum.TradeLegRefID.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.MatchStatus.getValue(),
        TagNum.MatchType.getValue(),
        TagNum.CopyMsgIndicator.getValue(),
        TagNum.NoTrdRepIndicators.getValue(),
        TagNum.TradePublishIndicator.getValue(),
        TagNum.ShortSaleReason.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.NoTrdRegTimestamps.getValue(),
        TagNum.ResponseTransportType.getValue(),
        TagNum.ResponseDestination.getValue(),
        TagNum.Text.getValue(),
        TagNum.AsOfIndicator.getValue(),
        TagNum.ClearingFeeIndicator.getValue(),
        TagNum.NoPosAmt.getValue(),
        TagNum.TierCode.getValue(),
        TagNum.MessageEventSource.getValue(),
        TagNum.LastUpdateTime.getValue(),
        TagNum.RndPx.getValue(),
        TagNum.NoSides.getValue(),
        TagNum.RptSys.getValue(),
        TagNum.GrossTradeAmt.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.FeeMultiplier.getValue()
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
     * TagNum = 571. Starting with 4.4 version.
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
     * TagNum = 487. Starting with 4.4 version.
     */
    protected TradeReportTransType tradeReportTransType;
    
    /**
     * TagNum = 856. Starting with 4.4 version.
     */
    protected TradeReportType tradeReportType;
    
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
     * Starting with 5.0 version.
     */
    protected RootParties rootParties;

    /**
     * TagNum = 150. Starting with 4.4 version.
     */
    protected ExecType execType;
    
    /**
     * TagNum = 572. Starting with 4.4 version.
     */
    protected String tradeReportRefID;
    
    /**
     * TagNum = 881. Starting with 4.4 version.
     */
    protected String secondaryTradeReportRefID;
   
    /**
     * TagNum = 939. Starting with 4.4 version.
     */
    protected TrdRptStatus trdRptStatus;
    
    /**
     * TagNum = 939. Starting with 4.4 version.
     */
    protected Integer tradeReportRejectReason;
   
    /**
     * TagNum = 818. Starting with 4.4 version.
     */
    protected String secondaryTradeReportID;

    /**
     * TagNum = 263. Starting with 4.4 version.
     */
    protected SubscriptionRequestType subscriptionRequestType;

    /**
     * TagNum = 820. Starting with 4.4 version.
     */
    protected String tradeLinkID;

    /**
     * TagNum = 880. Starting with 4.4 version.
     */
    protected String trdMatchID;
    
    /**
     * TagNum = 17. Starting with 4.4 version.
     */
    protected String execID;
 
    /**
     * TagNum = 527. Starting with 4.3 version.
     */
    protected String secondaryExecID;

    /**
     * TagNum = 39. Starting with 4.4 version.
     */
    protected OrdStatus ordStatus;
    
    /**
     * TagNum = 378. Starting with 5.0 version.
     */
    protected ExecRestatementReason execRestatementReason;

    /**
     * TagNum = 570. Starting with 5.0 version.
     */
    protected Boolean previouslyReported;

    /**
     * TagNum = 423. Starting with 5.0 version.
     */
    protected PriceType priceType;
        
     /**
     * TagNum = 822. Starting with 5.0 version.
     */
    protected String underlyingTradingSessionID;
    
     /**
     * TagNum = 823. Starting with 5.0 version.
     */
    protected String underlyingTradingSessionSubID;

    /**
     * TagNum = 716. Starting with 5.0 version.
     */
    protected String settlSessID;

    /**
     * TagNum = 716. Starting with 5.0 version.
     */
    protected String settlSessSubID;

    /**
     * TagNum = 854. Starting with 5.0 version.
     */
    protected QtyType qtyType;

    /**
     * TagNum = 32 REQUIRED. Starting with 5.0 version.
     */
    protected Double lastQty;

    /**
     * TagNum = 31 REQUIRED. Starting with 5.0 version.
     */
    protected Double lastPx;

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
     * Starting with 4.4 version.
     */
    protected Instrument instrument;

    /**
     * TagNum = 669. Starting with 5.0 version.
     */
    protected Double lastParPx;

    /**
     * TagNum = 1056. Starting with 5.0 version.
     */
    protected Double calculatedCcyLastQty;

    /**
     * TagNum = 1071. Starting with 5.0 version.
     */
    protected Double lastSwapPoints;

    /**
     * TagNum = 15. Starting with 5.0SP1 version.
     */
    protected Currency currency;

    /**
     * TagNum = 120. Starting with 5.0SP1 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 194. Starting with 5.0 version.
     */
    protected Double lastSpotRate;

    /**
     * TagNum = 195. Starting with 5.0 version.
     */
    protected Double lastForwardPoints;

    /**
     * TagNum = 30. Starting with 5.0 version.
     */
    protected String lastMkt;

    /**
     * TagNum = 75 REQUIRED. Starting with 5.0 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 715. Starting with 5.0 version.
     */
    protected Date clearingBusinessDate;

    /**
     * TagNum = 6. Starting with 5.0 version.
     */
    protected Double avgPx;

    /**
     * TagNum = 819. Starting with 5.0 version.
     */
    protected AvgPxIndicator avgPxIndicator;

    /**
     * TagNum = 442. Starting with 5.0 version.
     */
    protected MultiLegReportingType multilegReportingType;
    
     /**
     * TagNum = 824. Starting with 5.0 version.
     */
    protected String tradeLegRefID;

    /**
     * TagNum = 60. Starting with 5.0 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 63. Starting with 5.0 version.
     */
    protected String settlType;

    /**
     * TagNum = 711. Starting with 5.0 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 5.0 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;

    /**
     * TagNum = 573. Starting with 5.0 version.
     */
    protected MatchStatus matchStatus;

    /**
     * TagNum = 574. Starting with 5.0 version.
     */
    protected MatchType matchType;

    /**
     * TagNum = 797. Starting with 5.0 version.
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
     * TagNum = 853. Starting with 5.0 version.
     */
    protected ShortSaleReason shortSaleReason;
    
    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected TrdInstrmtLegGroup[] trdInstrmtLegGroups;

    /**
     * TagNum = 768. Starting with 4.4 version.
     */
    protected Integer noTrdRegTimestamps;

    /**
     * Starting with 4.4 version.
     */
    protected TrdRegTimestampsGroup[] trdRegTimestampsGroups;
            
    /**
     * TagNum = 725. Starting with 4.4 version.
     */
    protected ResponseTransportType responseTransportType;
           
    /**
     * TagNum = 726. Starting with 4.4 version.
     */
    protected String responseDestination;

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
 
    /**
     * TagNum = 1015. Starting with 5.0 version.
     */
    protected AsOfIndicator asOfIndicator;

    /**
     * TagNum = 635. Starting with 4.4 version.
     */
    protected ClearingFeeIndicator clearingFeeIndicator;

    /**
     * TagNum = 753. Starting with 5.0 version.
     */
    protected Integer noPosAmt;

    /**
     * Starting with 5.0 version.
     */
    protected PosAmtGroup[] posAmtGroups;
   
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
     * TagNum = 78. Starting with 5.0 version.
     */
    protected Integer noSides;

    /**
     * Starting with 5.0 version.
     */
    protected TrdCapRptAckSideGroup[] trdCapRptAckSideGroups;

    /**
     * TagNum = 1135. Starting with 5.0 version.
     */
    protected String rptSys;

    /**
     * TagNum = 381. Starting with 5.0 version.
     */
    protected Double grossTradeAmt;

    /**
     * TagNum = 64. Starting with 5.0 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 1329. Starting with 5.0SP1 version.
     */
    protected Double feeMultiplier;
    
    /**
     * TagNum = 78. Starting with 4.4 version.
     */
    protected Integer noAllocs;

    /**
     * Starting with 4.4 version.
     */
    protected TradeAllocGroup[] allocGroups;


    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeCaptureReportAckMsg() {
        super();
    }

    public TradeCaptureReportAckMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public TradeCaptureReportAckMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.TradeCaptureReportAck.getValue(), beginString);
    }

    public TradeCaptureReportAckMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.TradeCaptureReportAck.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeReportID)
    public String getTradeReportID() {
        return tradeReportID;
    }

    /**
     * Message field setter.
     * @param tradeReportID field value
     */
    @FIXVersion(introduced="4.4")
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeReportTransType)
    public TradeReportTransType getTradeReportTransType() {
        return tradeReportTransType;
    }

    /**
     * Message field setter.
     * @param tradeReportTransType field value
     */
    @FIXVersion(introduced="4.4")
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExecType)
    public ExecType getExecType() {
        return execType;
    }

    /**
     * Message field setter.
     * @param execType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExecType)
    public void setExecType(ExecType execType) {
        this.execType = execType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeReportRefID)
    public String getTradeReportRefID() {
        return tradeReportRefID;
    }

    /**
     * Message field setter.
     * @param tradeReportRefID field value
     */
    @FIXVersion(introduced="4.4")
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
    @TagNumRef(tagNum=TagNum.TrdRptStatus)
    public TrdRptStatus getTrdRptStatus() {
        return trdRptStatus;
    }

    /**
     * Message field setter.
     * @param trdRptStatus field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdRptStatus)
    public void setTrdRptStatus(TrdRptStatus trdRptStatus) {
        this.trdRptStatus = trdRptStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeReportRejectReason)
    public Integer getTradeReportRejectReason() {
        return tradeReportRejectReason;
    }

    /**
     * Message field setter.
     * @param tradeReportRejectReason field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeReportRejectReason)
    public void setTradeReportRejectReason(Integer tradeReportRejectReason) {
        this.tradeReportRejectReason = tradeReportRejectReason;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExecID)
    public String getExecID() {
        return execID;
    }

    /**
     * Message field setter.
     * @param execID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExecID)
    public void setExecID(String execID) {
        this.execID = execID;
    }
   
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryExecID)
    public String getSecondaryExecID() {
        return secondaryExecID;
    }

    /**
     * Message field setter.
     * @param secondaryExecID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryExecID)
    public void setSecondaryExecID(String secondaryExecID) {
        this.secondaryExecID = secondaryExecID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrdStatus)
    public OrdStatus getOrdStatus() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param ordStatus field value
     */
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrdStatus)
    public void setOrdStatus(OrdStatus ordStatus) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ExecRestatementReason)
    public ExecRestatementReason getExecRestatementReason() {
        return execRestatementReason;
    }

    /**
     * Message field setter.
     * @param execRestatementReason field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ExecRestatementReason)
    public void setExecRestatementReason(ExecRestatementReason execRestatementReason) {
        this.execRestatementReason = execRestatementReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PreviouslyReported)
    public Boolean getPreviouslyReported() {
        return previouslyReported;
    }

    /**
     * Message field setter.
     * @param previouslyReported field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PreviouslyReported)
    public void setPreviouslyReported(Boolean previouslyReported) {
        this.previouslyReported = previouslyReported;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PriceType)
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * Message field setter.
     * @param priceType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PriceType)
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingTradingSessionID)
    public String getUnderlyingTradingSessionID() {
        return underlyingTradingSessionID;
    }

    /**
     * Message field setter.
     * @param underlyingTradingSessionID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingTradingSessionID)
    public void setUnderlyingTradingSessionID(String underlyingTradingSessionID) {
        this.underlyingTradingSessionID = underlyingTradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingTradingSessionSubID)
    public String getUnderlyingTradingSessionSubID() {
        return underlyingTradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param underlyingTradingSessionSubID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingTradingSessionSubID)
    public void setUnderlyingTradingSessionSubID(String underlyingTradingSessionSubID) {
        this.underlyingTradingSessionSubID = underlyingTradingSessionSubID;
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.QtyType)
    public QtyType getQtyType() {
        return qtyType;
    }

    /**
     * Message field setter.
     * @param qtyType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.QtyType)
    public void setQtyType(QtyType qtyType) {
        this.qtyType = qtyType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastQty)
    public Double getLastQty() {
        return lastQty;
    }

    /**
     * Message field setter.
     * @param lastQty field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastQty)
    public void setLastQty(Double lastQty) {
        this.lastQty = lastQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastPx, required=true)
    public Double getLastPx() {
        return lastPx;
    }

    /**
     * Message field setter.
     * @param lastPx field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastPx, required=true)
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
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
    @FIXVersion(introduced = "4.4")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Instrument component class to the proper implementation.
     */
    @FIXVersion(introduced="4.4")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Instrument component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearInstrument() {
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
    @TagNumRef(tagNum=TagNum.LastSpotRate)
    public Double getLastSpotRate() {
        return lastSpotRate;
    }

    /**
     * Message field setter.
     * @param lastSpotRate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastSpotRate)
    public void setLastSpotRate(Double lastSpotRate) {
        this.lastSpotRate = lastSpotRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastForwardPoints)
    public Double getLastForwardPoints() {
        return lastForwardPoints;
    }

    /**
     * Message field setter.
     * @param lastForwardPoints field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastForwardPoints)
    public void setLastForwardPoints(Double lastForwardPoints) {
        this.lastForwardPoints = lastForwardPoints;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public String getLastMkt() {
        return lastMkt;
    }

    /**
     * Message field setter.
     * @param lastMkt field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public void setLastMkt(String lastMkt) {
        this.lastMkt = lastMkt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeDate, required=true)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeDate, required=true)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    /**
     * Message field setter.
     * @param clearingBusinessDate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AvgPx)
    public Double getAvgPx() {
        return avgPx;
    }

    /**
     * Message field setter.
     * @param avgPx field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AvgPx)
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AvgPxIndicator)
    public AvgPxIndicator getAvgPxIndicator() {
        return avgPxIndicator;
    }

    /**
     * Message field setter.
     * @param avgPxIndicator field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AvgPxIndicator)
    public void setAvgPxIndicator(AvgPxIndicator avgPxIndicator) {
        this.avgPxIndicator = avgPxIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public MultiLegReportingType getMultilegReportingType() {
        return multilegReportingType;
    }

    /**
     * Message field setter.
     * @param multilegReportingType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        this.multilegReportingType = multilegReportingType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeLegRefID)
    public String getTradeLegRefID() {
        return tradeLegRefID;
    }

    /**
     * Message field setter.
     * @param tradeLegRefID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeLegRefID)
    public void setTradeLegRefID(String tradeLegRefID) {
        this.tradeLegRefID = tradeLegRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public void setNoUnderlyings(Integer noUnderlyings) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UnderlyingInstrument} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0")
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
    @FIXVersion(introduced = "5.0")
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
    @FIXVersion(introduced = "5.0")
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingInstrument} objects from the <code>underlyingInstruments</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyings</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0")
    public int clearUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    /**
     * Message field setter.
     * @param matchStatus field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchType)
    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * Message field setter.
     * @param matchType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchType)
    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CopyMsgIndicator)
    public Boolean getCopyMsgIndicator() {
        return copyMsgIndicator;
    }

    /**
     * Message field setter.
     * @param copyMsgIndicator field value
     */
    @FIXVersion(introduced="5.0")
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ShortSaleReason)
    public ShortSaleReason getShortSaleReason() {
        return shortSaleReason;
    }

    /**
     * Message field setter.
     * @param shortSaleReason field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ShortSaleReason)
    public void setShortSaleReason(ShortSaleReason shortSaleReason) {
        this.shortSaleReason = shortSaleReason;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseTransportType)
    public ResponseTransportType getResponseTransportType() {
        return responseTransportType;
    }

    /**
     * Message field setter.
     * @param responseTransportType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseTransportType)
    public void setResponseTransportType(ResponseTransportType responseTransportType) {
        this.responseTransportType = responseTransportType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseDestination)
    public String getResponseDestination() {
        return responseDestination;
    }

    /**
     * Message field setter.
     * @param responseDestination field value
     */ 
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseDestination)
    public void setResponseDestination(String responseDestination) {
        this.responseDestination = responseDestination;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingFeeIndicator)
    public ClearingFeeIndicator getClearingFeeIndicator() {
        return clearingFeeIndicator;
    }

    /**
     * Message field setter.
     * @param clearingFeeIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingFeeIndicator)
    public void setClearingFeeIndicator(ClearingFeeIndicator clearingFeeIndicator) {
        this.clearingFeeIndicator = clearingFeeIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoPosAmt)
    public void setNoPosAmt(Integer noPosAmt) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link PosAmtGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0")
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
    @FIXVersion(introduced="5.0")
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
    @FIXVersion(introduced="5.0")
    public PosAmtGroup deletePosAmtGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link PosAmtGroup} objects from the <code>posAmtGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noPosAmt</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearPosAmtGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @TagNumRef(tagNum=TagNum.NoSides)
    public Integer getNoSides() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TrdCapRptAckSideGroup} groups. It will also create an array
     * of {@link TrdCapRptAckSideGroup} objects and set the <code>trdCapRptAckSideGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>trdCapRptAckSideGroups</code> array they will be discarded.<br/>
     * @param noSides field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoSides)
    public void setNoSides(Integer noSides) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TrdCapRptAckSideGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0")
    public TrdCapRptAckSideGroup[] getTrdCapRptAckSideGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TrdCapRptAckSideGroup} object to the existing array of <code>trdCapRptAckSideGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSides</code> field to the proper value.<br/>
     * Note: If the <code>setNoSides</code> method has been called there will already be a number of objects in the
     * <code>trdCapRptAckSideGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public TrdCapRptAckSideGroup addTrdCapRptAckSideGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TrdCapRptAckSideGroup} object from the existing array of <code>trdCapRptAckSideGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSides</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public TrdCapRptAckSideGroup deleteTrdCapRptAckSideGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TrdCapRptAckSideGroup} objects from the <code>trdCapRptAckSideGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSides</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearTrdCapRptAckSideGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RptSys)
    public String getRptSys() {
        return rptSys;
    }

    /**
     * Message field setter.
     * @param rptSys field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RptSys)
    public void setRptSys(String rptSys) {
        this.rptSys = rptSys;
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

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public Integer getNoAllocs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TradeAllocGroup} groups. It will also create an array
     * of {@link TradeAllocGroup} objects and set the <code>allocGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>allocGroups</code> array they will be discarded.<br/>
     * @param noAllocs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public void setNoAllocs(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TradeAllocGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public TradeAllocGroup[] getAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TradeAllocGroup} object to the existing array of <code>allocGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * Note: If the <code>setNoAllocs</code> method has been called there will already be a number of objects in the
     * <code>allocGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public TradeAllocGroup addAllocGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TradeAllocGroup} object from the existing array of <code>allocGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public TradeAllocGroup deleteAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TradeAllocGroup} objects from the <code>allocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noAllocs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
            if (rootParties != null) {
                bao.write(rootParties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (execType != null) {
                TagEncoder.encode(bao, TagNum.ExecType, execType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeReportRefID, tradeReportRefID);
            TagEncoder.encode(bao, TagNum.SecondaryTradeReportRefID, secondaryTradeReportRefID);
            if (trdRptStatus != null) {
                TagEncoder.encode(bao, TagNum.TrdRptStatus, trdRptStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeReportRejectReason, tradeReportRejectReason);
            TagEncoder.encode(bao, TagNum.SecondaryTradeReportID, secondaryTradeReportID);
            if (subscriptionRequestType != null) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeLinkID, tradeLinkID);
            TagEncoder.encode(bao, TagNum.TrdMatchID, trdMatchID);
            TagEncoder.encode(bao, TagNum.ExecID, execID);
            TagEncoder.encode(bao, TagNum.SecondaryExecID, secondaryExecID);
            if (ordStatus != null) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            if (execRestatementReason != null) {
                TagEncoder.encode(bao, TagNum.ExecRestatementReason, execRestatementReason.getValue());
            }
            TagEncoder.encode(bao, TagNum.PreviouslyReported, previouslyReported);
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingTradingSessionID, underlyingTradingSessionID);
            TagEncoder.encode(bao, TagNum.UnderlyingTradingSessionSubID, underlyingTradingSessionSubID);
            TagEncoder.encode(bao, TagNum.SettlSessID, settlSessID);
            TagEncoder.encode(bao, TagNum.SettlSessSubID, settlSessSubID);
            if (qtyType != null) {
                TagEncoder.encode(bao, TagNum.QtyType, qtyType.getValue());
            }
            TagEncoder.encode(bao, TagNum.LastQty, lastQty);
            TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            if (venueType != null) {
                TagEncoder.encode(bao, TagNum.VenueType, venueType.getValue());
            }
            TagEncoder.encode(bao, TagNum.MarketSegmentID, marketSegmentID);
            TagEncoder.encode(bao, TagNum.MarketID, marketID);
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.LastParPx, lastParPx);
            TagEncoder.encode(bao, TagNum.CalculatedCcyLastQty, calculatedCcyLastQty);
            TagEncoder.encode(bao, TagNum.LastSwapPoints, lastSwapPoints);
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.LastSpotRate, lastSpotRate);
            TagEncoder.encode(bao, TagNum.LastForwardPoints, lastForwardPoints);
            TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            if (avgPxIndicator != null) {
                TagEncoder.encode(bao, TagNum.AvgPxIndicator, avgPxIndicator.getValue());
            }
            if (multilegReportingType != null) {
                TagEncoder.encode(bao, TagNum.MultiLegReportingType, multilegReportingType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeLegRefID, tradeLegRefID);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
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
            if (matchStatus != null) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            if (matchType != null) {
                TagEncoder.encode(bao, TagNum.MatchType, matchType.getValue());
            }
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
            if (responseTransportType != null) {
                TagEncoder.encode(bao, TagNum.ResponseTransportType, responseTransportType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ResponseDestination, responseDestination);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (asOfIndicator != null) {
                TagEncoder.encode(bao, TagNum.AsOfIndicator, asOfIndicator.getValue());
            }
            if (clearingFeeIndicator != null) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
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
            TagEncoder.encode(bao, TagNum.TierCode, tierCode);
            TagEncoder.encode(bao, TagNum.MessageEventSource, messageEventSource);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.LastUpdateTime, lastUpdateTime);
            TagEncoder.encode(bao, TagNum.RndPx, rndPx);
            if (noSides != null) {
                TagEncoder.encode(bao, TagNum.NoSides, noSides);
                if (trdCapRptAckSideGroups != null && trdCapRptAckSideGroups.length == noSides.intValue()) {
                    for (int i = 0; i < noSides.intValue(); i++) {
                        if (trdCapRptAckSideGroups[i] != null) {
                            bao.write(trdCapRptAckSideGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TrdCapRptAckSideGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSides.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.RptSys, rptSys);
            TagEncoder.encode(bao, TagNum.GrossTradeAmt, grossTradeAmt);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            TagEncoder.encode(bao, TagNum.FeeMultiplier, feeMultiplier);
            if (noAllocs != null && noAllocs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoAllocs, noAllocs);
                if (allocGroups != null && allocGroups.length == noAllocs.intValue()) {
                    for (TradeAllocGroup allocGroup : allocGroups) {
                        bao.write(allocGroup.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "TradeAllocGroup field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoAllocs.getValue(), error);
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
               
            case TradeReportRefID:
                tradeReportRefID = new String(tag.value, sessionCharset);
                break;
               
            case SecondaryTradeReportRefID:
                secondaryTradeReportRefID = new String(tag.value, sessionCharset);
                break;
                       
            case TrdRptStatus:
                trdRptStatus = TrdRptStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;
                      
            case TradeReportRejectReason:
                tradeReportRejectReason = Integer.valueOf(new String(tag.value, sessionCharset));
                break;
              
            case SecondaryTradeReportID:
                secondaryTradeReportID = new String(tag.value, sessionCharset);
                break;

            case SubscriptionRequestType:
                subscriptionRequestType = SubscriptionRequestType.valueFor(new String(tag.value, sessionCharset).charAt(0));
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

           case SecondaryExecID:
                secondaryExecID = new String(tag.value, sessionCharset);
                break;
 
            case OrdStatus:
                ordStatus = OrdStatus.valueFor(new String(tag.value, sessionCharset));
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

            case UnderlyingTradingSessionID:
                underlyingTradingSessionID = new String(tag.value, sessionCharset);
                break;

            case UnderlyingTradingSessionSubID:
                underlyingTradingSessionSubID = new String(tag.value, sessionCharset);
                break;

           case SettlSessID:
                settlSessID = new String(tag.value, sessionCharset);
                break;

           case SettlSessSubID:
                settlSessSubID = new String(tag.value, sessionCharset);
                break;

            case QtyType:
                qtyType = QtyType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LastQty:
                lastQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LastPx:
                lastPx = new Double(new String(tag.value, sessionCharset));
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

            case LastParPx:
                lastParPx = new Double(new String(tag.value, sessionCharset));
                break;

            case CalculatedCcyLastQty:
                calculatedCcyLastQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LastSwapPoints:
                lastSwapPoints = new Double(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case LastSpotRate:
                lastSpotRate = new Double(new String(tag.value, sessionCharset));
                break;

            case LastForwardPoints:
                lastForwardPoints = new Double(new String(tag.value, sessionCharset));
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

            case MultiLegReportingType:
                multilegReportingType = MultiLegReportingType.valueFor(new String(tag.value, sessionCharset));
                break;

            case TradeLegRefID:
                tradeLegRefID = new String(tag.value, sessionCharset);
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case SettlType:
                settlType = new String(tag.value, sessionCharset);
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case MatchStatus:
                matchStatus = MatchStatus.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MatchType:
                matchType = MatchType.valueFor(new String(tag.value, sessionCharset));
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

            case NoLegs:
                noLegs = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case NoTrdRegTimestamps:
                noTrdRegTimestamps = new Integer(new String(tag.value, sessionCharset));
                break;

            case ResponseTransportType:
                responseTransportType = ResponseTransportType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;
                        
            case ResponseDestination:
                responseDestination = new String(tag.value, sessionCharset);
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case AsOfIndicator:
                asOfIndicator = AsOfIndicator.valueFor(new String(tag.value, sessionCharset));
                break;

            case ClearingFeeIndicator:
                clearingFeeIndicator = ClearingFeeIndicator.valueFor(new String(tag.value, sessionCharset));
                break;

            case NoPosAmt:
                noPosAmt = new Integer(new String(tag.value, sessionCharset));
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

            case NoSides:
                noSides = new Integer(new String(tag.value, sessionCharset));
                break;

            case RptSys:
                rptSys = new String(tag.value, sessionCharset);
                break;

            case GrossTradeAmt:
                grossTradeAmt = new Double(new String(tag.value, sessionCharset));
                break;
 
            case SettlDate:
                settlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case FeeMultiplier:
                feeMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case NoAllocs:
                noAllocs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TradeCaptureReportAckMsg] fields.";
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedTextLen.getValue(), error);
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
        StringBuilder b = new StringBuilder("{TradeCaptureReportAckMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.TradeReportID, tradeReportID);
        printTagValue(b, TagNum.TradeID, tradeID);
        printTagValue(b, TagNum.SecondaryTradeID, secondaryTradeID);
        printTagValue(b, TagNum.FirmTradeID, firmTradeID);
        printTagValue(b, TagNum.SecondaryFirmTradeID, secondaryFirmTradeID);
        printTagValue(b, TagNum.TradeReportTransType, tradeReportTransType);
        printTagValue(b, TagNum.TradeReportType, tradeReportType);
        printTagValue(b, TagNum.TrdType, trdType);
        printTagValue(b, TagNum.TrdSubType, trdSubType);
        printTagValue(b, TagNum.SecondaryTrdType, secondaryTrdType);
        printTagValue(b, TagNum.TradeHandlingInstr, tradeHandlingInstr);
        printTagValue(b, TagNum.OrigTradeHandlingInstr, origTradeHandlingInstr);
        printDateTagValue(b, TagNum.OrigTradeDate, origTradeDate);
        printTagValue(b, TagNum.OrigTradeID, origTradeID);
        printTagValue(b, TagNum.OrigSecondaryTradeID, origSecondaryTradeID);
        printTagValue(b, TagNum.TransferReason, transferReason);
        printTagValue(b, rootParties);
        printTagValue(b, TagNum.ExecType, execType);
        printTagValue(b, TagNum.TradeReportRefID, tradeReportRefID);
        printTagValue(b, TagNum.SecondaryTradeReportRefID, secondaryTradeReportRefID);
        printTagValue(b, TagNum.TrdRptStatus, trdRptStatus);
        printTagValue(b, TagNum.TradeReportRejectReason, tradeReportRejectReason);
        printTagValue(b, TagNum.SecondaryTradeReportID, secondaryTradeReportID);
        printTagValue(b, TagNum.SubscriptionRequestType, subscriptionRequestType);
        printTagValue(b, TagNum.TradeLinkID, tradeLinkID);
        printTagValue(b, TagNum.TrdMatchID, trdMatchID);
        printTagValue(b, TagNum.ExecID, execID);
        printTagValue(b, TagNum.SecondaryExecID, secondaryExecID);
        printTagValue(b, TagNum.OrdStatus, ordStatus);
        printTagValue(b, TagNum.ExecRestatementReason, execRestatementReason);
        printTagValue(b, TagNum.PreviouslyReported, previouslyReported);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, TagNum.UnderlyingTradingSessionID, underlyingTradingSessionID);
        printTagValue(b, TagNum.UnderlyingTradingSessionSubID, underlyingTradingSessionSubID);
        printTagValue(b, TagNum.SettlSessID, settlSessID);
        printTagValue(b, TagNum.SettlSessSubID, settlSessSubID);
        printTagValue(b, TagNum.QtyType, qtyType);
        printTagValue(b, TagNum.LastQty, lastQty);
        printTagValue(b, TagNum.LastPx, lastPx);
        printTagValue(b, TagNum.VenueType, venueType);
        printTagValue(b, TagNum.MarketSegmentID, marketSegmentID);
        printTagValue(b, TagNum.MarketID, marketID);
        printTagValue(b, instrument);
        printTagValue(b, TagNum.LastParPx, lastParPx);
        printTagValue(b, TagNum.CalculatedCcyLastQty, calculatedCcyLastQty);
        printTagValue(b, TagNum.LastSwapPoints, lastSwapPoints);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printTagValue(b, TagNum.LastSpotRate, lastSpotRate);
        printTagValue(b, TagNum.LastForwardPoints, lastForwardPoints);
        printTagValue(b, TagNum.LastMkt, lastMkt);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, TagNum.AvgPx, avgPx);
        printTagValue(b, TagNum.AvgPxIndicator, avgPxIndicator);
        printTagValue(b, TagNum.MultiLegReportingType, multilegReportingType);
        printTagValue(b, TagNum.TradeLegRefID, tradeLegRefID);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.SettlType, settlType);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.MatchStatus, matchStatus);
        printTagValue(b, TagNum.MatchType, matchType);
        printTagValue(b, TagNum.CopyMsgIndicator, copyMsgIndicator);
        printTagValue(b, TagNum.NoTrdRepIndicators, noTrdRepIndicators);
        printTagValue(b, trdRepIndicatorsGroups);
        printTagValue(b, TagNum.PublishTrdIndicator, publishTrdIndicator);
        printTagValue(b, TagNum.TradePublishIndicator, tradePublishIndicator);
        printTagValue(b, TagNum.ShortSaleReason, shortSaleReason);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, trdInstrmtLegGroups);
        printTagValue(b, TagNum.NoTrdRegTimestamps, noTrdRegTimestamps);
        printTagValue(b, trdRegTimestampsGroups);
        printTagValue(b, TagNum.ResponseTransportType, responseTransportType);
        printTagValue(b, TagNum.ResponseDestination, responseDestination);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.AsOfIndicator, asOfIndicator);
        printTagValue(b, TagNum.ClearingFeeIndicator, clearingFeeIndicator);
        printTagValue(b, TagNum.NoPosAmt, noPosAmt);
        printTagValue(b, posAmtGroups);
        printTagValue(b, TagNum.TierCode, tierCode);
        printTagValue(b, TagNum.MessageEventSource, messageEventSource);
        printUTCDateTimeTagValue(b, TagNum.LastUpdateTime, lastUpdateTime);
        printTagValue(b, TagNum.RndPx, rndPx);
        printTagValue(b, TagNum.NoSides, noSides);
        printTagValue(b, trdCapRptAckSideGroups);
        printTagValue(b, TagNum.RptSys, rptSys);
        printTagValue(b, TagNum.GrossTradeAmt, grossTradeAmt);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.FeeMultiplier, feeMultiplier);
        printTagValue(b, TagNum.NoAllocs, noAllocs);
        printTagValue(b, allocGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
