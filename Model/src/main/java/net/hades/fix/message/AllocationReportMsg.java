/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionMsg.java
 *
 * $Id: AllocationInstructionMsg.java,v 1.5 2011-10-21 10:31:03 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.AllocGroup;
import net.hades.fix.message.group.ExecAllocGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.*;
import net.hades.fix.message.util.BooleanConverter;
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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderAllocGroup;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.group.RateSourceGroup;
import net.hades.fix.message.type.AllocCancReplaceReason;
import net.hades.fix.message.type.AllocIntermedReqType;
import net.hades.fix.message.type.AllocLinkType;
import net.hades.fix.message.type.AllocNoOrdersType;
import net.hades.fix.message.type.AllocRejCode;
import net.hades.fix.message.type.AllocReportType;
import net.hades.fix.message.type.AllocStatus;
import net.hades.fix.message.type.AllocTransType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.AvgPxIndicator;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * Sent from sell-side to buy-side, sell-side to 3rd-party or 3rd-party to buy-side, the Allocation Report (Claim)
 * provides account breakdown of an order or set of orders plus any additional follow-up front-office information
 * developed post-trade during the trade allocation, matching and calculation phase. In versions of FIX prior to
 * version 4.4, this functionality was provided through the Allocation message. Depending on the needs of the
 * market and the timing of “confirmed” status, the role of Allocation Report can be taken over in whole or in part
 * by the Confirmation message.<br/>
 * Note the response to the Allocation Report message is the Allocation Report Ack message. In versions of FIX
 * prior to version 4.4, the Allocation ACK served this purpose.
 * An Allocation Report message can be submitted with AllocReportType of
 * <ul>
 *      <li>Sellside Calculated Using Preliminary (includes Misc Fees, Accrued Interest and Net Money)
 *      <li>Sellside Calculated Without Preliminary (includes Misc Fees, Accrued Interest and Net Money). (AllocType=" Sellside Initiated"), 
 * e.g. where the allocations have been provided via some other mechanism or agreed earlier in the order process.
 *      <li>Warehouse recap – sent unsolicited by sellside, used to communicate confirmation and current statusof any warehoused 
 * position in a particular stock (see Volume 7 – PRODUCT: EQUITIES for specific usage guidance on this topic)
 * </ul>
 * Settlement instructions are supported on the Allocation Report message to allow the Respondent (sell-side party or
 * carry firm) to send an override of its own instructions to the Initiator.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 29/01/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AllocationReportMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AllocReportID.getValue(),
        TagNum.AllocID.getValue(),
        TagNum.AllocTransType.getValue(),
        TagNum.AllocReportRefID.getValue(),
        TagNum.AllocCancReplaceReason.getValue(),
        TagNum.SecondaryAllocID.getValue(),
        TagNum.AllocReportType.getValue(),
        TagNum.AllocStatus.getValue(),
        TagNum.AllocRejCode.getValue(),
        TagNum.RefAllocID.getValue(),
        TagNum.AllocIntermedReqType.getValue(),
        TagNum.AllocLinkID.getValue(),
        TagNum.AllocLinkType.getValue(),
        TagNum.BookingRefID.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.TrdType.getValue(),
        TagNum.TrdSubType.getValue(),
        TagNum.MultiLegReportingType.getValue(),
        TagNum.CustOrderCapacity.getValue(),
        TagNum.TradeInputSource.getValue(),
        TagNum.RndPx.getValue(),
        TagNum.MessageEventSource.getValue(),
        TagNum.TradeInputDevice.getValue(),
        TagNum.AvgPxIndicator.getValue(),
        TagNum.AllocNoOrdersType.getValue(),
        TagNum.NoOrders.getValue(),
        TagNum.NoExecs.getValue(),
        TagNum.PreviouslyReported.getValue(),
        TagNum.ReversalIndicator.getValue(),
        TagNum.MatchType.getValue(),
        TagNum.Side.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.Quantity.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.LastMkt.getValue(),
        TagNum.TradeOriginationDate.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.AvgPx.getValue(),
        TagNum.AvgParPx.getValue(),
        TagNum.Currency.getValue(),
        TagNum.AvgPxPrecision.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.BookingType.getValue(),
        TagNum.GrossTradeAmt.getValue(),
        TagNum.Concession.getValue(),
        TagNum.TotalTakedown.getValue(),
        TagNum.NetMoney.getValue(),
        TagNum.PositionEffect.getValue(),
        TagNum.AutoAcceptIndicator.getValue(),
        TagNum.Text.getValue(),
        TagNum.NumDaysInterest.getValue(),
        TagNum.AccruedInterestRate.getValue(),
        TagNum.AccruedInterestAmt.getValue(),
        TagNum.TotalAccruedInterestAmt.getValue(),
        TagNum.InterestAtMaturity.getValue(),
        TagNum.EndAccruedInterestAmt.getValue(),
        TagNum.StartCash.getValue(),
        TagNum.EndCash.getValue(),
        TagNum.LegalConfirm.getValue(),
        TagNum.TotNoAllocs.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoAllocs.getValue(),
        TagNum.NoRateSources.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AllocReportID.getValue(),
        TagNum.AllocTransType.getValue(),
        TagNum.AllocReportType.getValue(),
        TagNum.AllocStatus.getValue(),
        TagNum.AllocNoOrdersType.getValue(),
        TagNum.Side.getValue(),
        TagNum.Symbol.getValue(),
        TagNum.Quantity.getValue(),
        TagNum.TradeDate.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 735 REQUIRED. Starting with 4.4 version.
     */
    protected String allocReportID;

    /**
     * TagNum = 70. Starting with 4.4 version.
     */
    protected String allocID;

    /**
     * TagNum = 71 REQUIRED. Starting with 4.4 version.
     */
    protected AllocTransType allocTransType;

    /**
     * TagNum = 795. Starting with 4.4 version.
     */
    protected String allocReportRefID;

    /**
     * TagNum = 796. Starting with 4.4 version.
     */
    protected AllocCancReplaceReason allocCancReplaceReason;

    /**
     * TagNum = 793. Starting with 4.4 version.
     */
    protected String secondaryAllocID;

    /**
     * TagNum = 71 REQUIRED. Starting with 4.4 version.
     */
    protected AllocReportType allocReportType;

    /**
     * TagNum = 87 REQUIRED. Starting with 4.4 version.
     */
    protected AllocStatus allocStatus;

    /**
     * TagNum = 88. Starting with 4.4 version.
     */
    protected AllocRejCode allocRejCode;

    /**
     * TagNum = 71. Starting with 4.4 version.
     */
    protected String refAllocID;

    /**
     * TagNum = 808. Starting with 4.4 version.
     */
    protected AllocIntermedReqType allocIntermedReqType;

    /**
     * TagNum = 196. Starting with 4.4 version.
     */
    protected String allocLinkID;

    /**
     * TagNum = 197. Starting with 4.1 version.
     */
    protected AllocLinkType allocLinkType;

    /**
     * TagNum = 466. Starting with 4.4 version.
     */
    protected String bookingRefID;

    /**
     * TagNum = 715. Starting with 5.0 version.
     */
    protected Date clearingBusinessDate;

    /**
     * TagNum = 828. Starting with 5.0 version.
     */
    protected TrdType trdType;

    /**
     * TagNum = 829. Starting with 5.0 version.
     */
    protected TrdSubType trdSubType;

    /**
     * TagNum = 442. Starting with 5.0 version.
     */
    protected MultiLegReportingType multiLegReportingType;

    /**
     * TagNum = 582. Starting with 5.0 version.
     */
    protected CustOrderCapacity custOrderCapacity;

    /**
     * TagNum = 578. Starting with 5.0 version.
     */
    protected String tradeInputSource;

    /**
     * TagNum = 991. Starting with 5.0 version.
     */
    protected Double rndPx;

    /**
     * TagNum = 1011. Starting with 5.0 version.
     */
    protected String messageEventSource;

    /**
     * TagNum = 579. Starting with 5.0 version.
     */
    protected String tradeInputDevice;

    /**
     * TagNum = 819. Starting with 5.0 version.
     */
    protected AvgPxIndicator avgPxIndicator;

    /**
     * TagNum = 857. Starting with 4.4 version.
     */
    protected AllocNoOrdersType allocNoOrdersType;

    /**
     * TagNum = 73. Starting with 4.4 version.
     */
    protected Integer noOrders;

    /**
     * Starting with 4.4 version.
     */
    protected OrderAllocGroup[] orderAllocGroups;

    /**
     * TagNum = 124. Starting with 4.4 version.
     */
    protected Integer noExecs;

    /**
     * Starting with 4.4 version.
     */
    protected ExecAllocGroup[] execAllocGroups;

    /**
     * TagNum = 570. Starting with 4.4 version.
     */
    protected Boolean previouslyReported;

    /**
     * TagNum = 700. Starting with 4.4 version.
     */
    protected Boolean reversalIndicator;

    /**
     * TagNum = 574. Starting with 4.4 version.
     */
    protected MatchType matchType;

    /**
     * TagNum = 54 REQUIRED. Starting with 4.4 version.
     */
    protected Side side;

    /**
     * Starting with 4.4 version.
     */
    protected Instrument instrument;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentExtension instrumentExtension;

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
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg[] instrumentLegs;

    /**
     * TagNum = 53 REQUIRED. Starting with 4.4 version.
     */
    protected Double quantity;

    /**
     * TagNum = 854. Starting with 4.4 version.
     */
    protected QtyType qtyType;

    /**
     * TagNum = 30. Starting with 4.4 version.
     */
    protected String lastMkt;

    /**
     * Starting with 4.4 version.
     */
    protected Date tradeOriginationDate;

    /**
     * TagNum = 336. Starting with 4.4 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.4 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 423. Starting with 4.4 version.
     */
    protected PriceType priceType;

    /**
     * TagNum = 6 REQUIRED. Starting with 4.4 version.
     */
    protected Double avgPx;

    /**
     * TagNum = 860. Starting with 4.4 version.
     */
    protected Double avgParPx;

    /**
     * Starting with 4.4 version.
     */
    protected SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData;

    /**
     * TagNum = 15. Starting with 4.4 version.
     */
    protected Currency currency;

    /**
     * TagNum = 74. Starting with 4.4 version.
     */
    protected Integer avgPxPrecision;

    /**
     * Starting with 4.4 version.
     */
    protected Parties parties;

    /**
     * TagNum = 75 REQUIRED. Starting with 4.4 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 60. Starting with 4.4 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 63. Starting with 4.4 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 4.4 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 775. Starting with 4.4 version.
     */
    protected BookingType bookingType;

    /**
     * TagNum = 381. Starting with 4.4 version.
     */
    protected Double grossTradeAmt;

    /**
     * TagNum = 238. Starting with 4.4 version.
     */
    protected Double concession;

    /**
     * TagNum = 237. Starting with 4.4 version.
     */
    protected Double totalTakedown;

    /**
     * TagNum = 118. Starting with 4.4 version.
     */
    protected Double netMoney;
        
    /**
     * TagNum = 77. Starting with 4.0 version.
     */
    protected PositionEffect positionEffect;

    /**
     * TagNum = 754. Starting with 4.4 version.
     */
    protected Boolean autoAcceptIndicator;

    /**
     * TagNum = 58. Starting with 4.0 version.
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
     * TagNum = 157. Starting with 4.4 version.
     */
    protected Integer numDaysInterest;

    /**
     * TagNum = 158. Starting with 4.4 version.
     */
    protected Double accruedInterestRate;

    /**
     * TagNum = 158. Starting with 4.4 version.
     */
    protected Double accruedInterestAmt;

    /**
     * TagNum = 540. Starting with 4.4 version.
     */
    protected Double totalAccruedInterestAmt;

    /**
     * TagNum = 738. Starting with 4.4 version.
     */
    protected Double interestAtMaturity;

    /**
     * TagNum = 920. Starting with 4.4 version.
     */
    protected Double endAccruedInterestAmt;

    /**
     * TagNum = 921. Starting with 4.4 version.
     */
    protected Double startCash;

    /**
     * TagNum = 922. Starting with 4.4 version.
     */
    protected Double endCash;

    /**
     * TagNum = 754. Starting with 4.4 version.
     */
    protected Boolean legalConfirm;

    /**
     * Starting with 4.4 version.
     */
    protected Stipulations stipulations;

    /**
     * Starting with 4.4 version.
     */
    protected YieldData yieldData;

    /**
     * TagNum = 753. Starting with 5.0 version.
     */
    protected Integer noPosAmt;

    /**
     * Starting with 5.0 version.
     */
    protected PosAmtGroup[] posAmtGroups;

    /**
     * TagNum = 892. Starting with 4.4 version.
     */
    protected Integer totNoAllocs;

    /**
     * TagNum = 893. Starting with 4.4 version.
     */
    protected Boolean lastFragment;

    /**
     * TagNum = 78. Starting with 4.4 version.
     */
    protected Integer noAllocs;

    /**
     * Starting with 4.4 version.
     */
    protected AllocGroup[] allocGroups;

    /**
     * TagNum = 555. Starting with 5.0SP2 version.
     */
    protected Integer noRateSources;

    /**
     * Starting with 5.0SP2 version.
     */
    protected RateSourceGroup[] rateSources;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AllocationReportMsg() {
        super();
    }

    public AllocationReportMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public AllocationReportMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.AllocationReport.getValue(), beginString);
    }

    public AllocationReportMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.AllocationReport.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.AllocReportID, required=true)
    public String getAllocReportID() {
        return allocReportID;
    }

    /**
     * Message field setter.
     * @param allocReportID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocReportID, required=true)
    public void setAllocReportID(String allocReportID) {
        this.allocReportID = allocReportID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocID)
    public String getAllocID() {
        return allocID;
    }

    /**
     * Message field setter.
     * @param allocID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocID)
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocTransType, required=true)
    public AllocTransType getAllocTransType() {
        return allocTransType;
    }

    /**
     * Message field setter.
     * @param allocTransType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocTransType, required=true)
    public void setAllocTransType(AllocTransType allocTransType) {
        this.allocTransType = allocTransType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocReportRefID)
    public String getAllocReportRefID() {
        return allocReportRefID;
    }

    /**
     * Message field setter.
     * @param allocReportRefID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocReportRefID)
    public void setAllocReportRefID(String allocReportRefID) {
        this.allocReportRefID = allocReportRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocCancReplaceReason)
    public AllocCancReplaceReason getAllocCancReplaceReason() {
        return allocCancReplaceReason;
    }

    /**
     * Message field setter.
     * @param allocCancReplaceReason field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocCancReplaceReason)
    public void setAllocCancReplaceReason(AllocCancReplaceReason allocCancReplaceReason) {
        this.allocCancReplaceReason = allocCancReplaceReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryAllocID)
    public String getSecondaryAllocID() {
        return secondaryAllocID;
    }

    /**
     * Message field setter.
     * @param secondaryAllocID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryAllocID)
    public void setSecondaryAllocID(String secondaryAllocID) {
        this.secondaryAllocID = secondaryAllocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocReportType, required=true)
    public AllocReportType getAllocReportType() {
        return allocReportType;
    }

    /**
     * Message field setter.
     * @param allocReportType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocReportType, required=true)
    public void setAllocReportType(AllocReportType allocReportType) {
        this.allocReportType = allocReportType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocStatus, required=true)
    public AllocStatus getAllocStatus() {
        return allocStatus;
    }

    /**
     * Message field setter.
     * @param allocStatus field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocStatus, required=true)
    public void setAllocStatus(AllocStatus allocStatus) {
        this.allocStatus = allocStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocRejCode)
    public AllocRejCode getAllocRejCode() {
        return allocRejCode;
    }

    /**
     * Message field setter.
     * @param allocRejCode field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocRejCode)
    public void setAllocRejCode(AllocRejCode allocRejCode) {
        this.allocRejCode = allocRejCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.RefAllocID)
    public String getRefAllocID() {
        return refAllocID;
    }

    /**
     * Message field setter.
     * @param refAllocID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.RefAllocID)
    public void setRefAllocID(String refAllocID) {
        this.refAllocID = refAllocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocIntermedReqType)
    public AllocIntermedReqType getAllocIntermedReqType() {
        return allocIntermedReqType;
    }

    /**
     * Message field setter.
     * @param allocIntermedReqType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocIntermedReqType)
    public void setAllocIntermedReqType(AllocIntermedReqType allocIntermedReqType) {
        this.allocIntermedReqType = allocIntermedReqType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocLinkID)
    public String getAllocLinkID() {
        return allocLinkID;
    }

    /**
     * Message field setter.
     * @param allocLinkID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocLinkID)
    public void setAllocLinkID(String allocLinkID) {
        this.allocLinkID = allocLinkID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocLinkType)
    public AllocLinkType getAllocLinkType() {
        return allocLinkType;
    }

    /**
     * Message field setter.
     * @param allocLinkType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocLinkType)
    public void setAllocLinkType(AllocLinkType allocLinkType) {
        this.allocLinkType = allocLinkType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.BookingRefID)
    public String getBookingRefID() {
        return bookingRefID;
    }

    /**
     * Message field setter.
     * @param bookingRefID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.BookingRefID)
    public void setBookingRefID(String bookingRefID) {
        this.bookingRefID = bookingRefID;
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
    @TagNumRef(tagNum=TagNum.TrdType)
    public TrdType getTrdType() {
        return trdType;
    }

    /**
     * Message field setter.
     * @param trdType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TrdType)
    public void setTrdType(TrdType trdType) {
        this.trdType = trdType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TrdSubType)
    public TrdSubType getTrdSubType() {
        return trdSubType;
    }

    /**
     * Message field setter.
     * @param trdSubType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TrdSubType)
    public void setTrdSubType(TrdSubType trdSubType) {
        this.trdSubType = trdSubType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public MultiLegReportingType getMultiLegReportingType() {
        return multiLegReportingType;
    }

    /**
     * Message field setter.
     * @param multiLegReportingType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public void setMultiLegReportingType(MultiLegReportingType multiLegReportingType) {
        this.multiLegReportingType = multiLegReportingType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CustOrderCapacity)
    public CustOrderCapacity getCustOrderCapacity() {
        return custOrderCapacity;
    }

    /**
     * Message field setter.
     * @param custOrderCapacity field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CustOrderCapacity)
    public void setCustOrderCapacity(CustOrderCapacity custOrderCapacity) {
        this.custOrderCapacity = custOrderCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeInputSource)
    public String getTradeInputSource() {
        return tradeInputSource;
    }

    /**
     * Message field setter.
     * @param tradeInputSource field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeInputSource)
    public void setTradeInputSource(String tradeInputSource) {
        this.tradeInputSource = tradeInputSource;
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
    @TagNumRef(tagNum=TagNum.TradeInputDevice)
    public String getTradeInputDevice() {
        return tradeInputDevice;
    }

    /**
     * Message field setter.
     * @param tradeInputDevice field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeInputDevice)
    public void setTradeInputDevice(String tradeInputDevice) {
        this.tradeInputDevice = tradeInputDevice;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocNoOrdersType)
    public AllocNoOrdersType getAllocNoOrdersType() {
        return allocNoOrdersType;
    }

    /**
     * Message field setter.
     * @param allocNoOrdersType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocNoOrdersType)
    public void setAllocNoOrdersType(AllocNoOrdersType allocNoOrdersType) {
        this.allocNoOrdersType = allocNoOrdersType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoOrders)
    public Integer getNoOrders() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link OrderAllocGroup} groups. It will also create an array
     * of {@link OrderAllocGroup} objects and set the <code>orderAllocGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>orderAllocGroups</code> array they will be discarded.<br/>
     * @param noOrders field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoOrders)
    public void setNoOrders(Integer noOrders) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link OrderAllocGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public OrderAllocGroup[] getOrderAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link OrderAllocGroup} object to the existing array of <code>orderAllocGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noOrders</code> field to the proper value.<br/>
     * Note: If the <code>setNoOrders</code> method has been called there will already be a number of objects in the
     * <code>OrderAllocGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public OrderAllocGroup addOrderAllocGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link OrderAllocGroup} object from the existing array of <code>orderAllocGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noOrders</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public OrderAllocGroup deleteOrderAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link OrderAllocGroup} objects from the <code>orderAllocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noOrders</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearOrderAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoExecs)
    public Integer getNoExecs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link ExecAllocGroup} groups. It will also create an array
     * of {@link ExecAllocGroup} objects and set the <code>execAllocGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>execAllocGroups</code> array they will be discarded.<br/>
     * @param noExecs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoExecs)
    public void setNoExecs(Integer noExecs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link ExecAllocGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public ExecAllocGroup[] getExecAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link ExecAllocGroup} object to the existing array of <code>execAllocGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noExecs</code> field to the proper value.<br/>
     * Note: If the <code>setNoExecs</code> method has been called there will already be a number of objects in the
     * <code>execAllocGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public ExecAllocGroup addExecAllocGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link ExecAllocGroup} object from the existing array of <code>ExecAllocGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noExecs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public ExecAllocGroup deleteExecAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link ExecAllocGroup} objects from the <code>execAllocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noExecs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearExecAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PreviouslyReported)
    public Boolean getPreviouslyReported() {
        return previouslyReported;
    }

    /**
     * Message field setter.
     * @param previouslyReported field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PreviouslyReported)
    public void setPreviouslyReported(Boolean previouslyReported) {
        this.previouslyReported = previouslyReported;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ReversalIndicator)
    public Boolean getReversalIndicator() {
        return reversalIndicator;
    }

    /**
     * Message field setter.
     * @param reversalIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ReversalIndicator)
    public void setReversalIndicator(Boolean reversalIndicator) {
        this.reversalIndicator = reversalIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MatchType)
    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * Message field setter.
     * @param matchType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MatchType)
    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Side, required=true)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Side, required=true)
    public void setSide(Side side) {
        this.side = side;
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
     * Message field setter.
     */
    @FIXVersion(introduced="4.4")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the instrument component to null.
     */
    @FIXVersion(introduced="4.4")
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
     * Sets the InstrumentExtension component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearInstrumentExtension() {
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
    @TagNumRef(tagNum=TagNum.NoLegs)
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Quantity)
    public Double getQuantity() {
        return quantity;
    }

    /**
     * Message field setter.
     * @param quantity field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Quantity)
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public String getLastMkt() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param lastMkt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public void setLastMkt(String lastMkt) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeOriginationDate)
    public Date getTradeOriginationDate() {
        return tradeOriginationDate;
    }

    /**
     * Message field setter.
     * @param tradeOriginationDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeOriginationDate)
    public void setTradeOriginationDate(Date tradeOriginationDate) {
        this.tradeOriginationDate = tradeOriginationDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
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
    @TagNumRef(tagNum=TagNum.AvgPx, required=true)
    public Double getAvgPx() {
        return avgPx;
    }

    /**
     * Message field setter.
     * @param avgPx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AvgPx, required=true)
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AvgParPx)
    public Double getAvgParPx() {
        return avgParPx;
    }

    /**
     * Message field setter.
     * @param avgParPx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AvgParPx)
    public void setAvgParPx(Double avgParPx) {
        this.avgParPx = avgParPx;
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
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AvgPxPrecision)
    public Integer getAvgPxPrecision() {
        return avgPxPrecision;
    }

    /**
     * Message field setter.
     * @param avgPxPrecision field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AvgPxPrecision)
    public void setAvgPxPrecision(Integer avgPxPrecision) {
        this.avgPxPrecision = avgPxPrecision;
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
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeDate, required=true)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeDate, required=true)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.4")
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        return settlDate;
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.BookingType)
    public BookingType getBookingType() {
        return bookingType;
    }

    /**
     * Message field setter.
     * @param bookingType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.BookingType)
    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.GrossTradeAmt)
    public Double getGrossTradeAmt() {
        return grossTradeAmt;
    }

    /**
     * Message field setter.
     * @param grossTradeAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.GrossTradeAmt)
    public void setGrossTradeAmt(Double grossTradeAmt) {
        this.grossTradeAmt = grossTradeAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Concession)
    public Double getConcession() {
        return concession;
    }

    /**
     * Message field setter.
     * @param concession field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Concession)
    public void setConcession(Double concession) {
        this.concession = concession;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotalTakedown)
    public Double getTotalTakedown() {
        return totalTakedown;
    }

    /**
     * Message field setter.
     * @param totalTakedown field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotalTakedown)
    public void setTotalTakedown(Double totalTakedown) {
        this.totalTakedown = totalTakedown;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public Double getNetMoney() {
        return netMoney;
    }

    /**
     * Message field setter.
     * @param netMoney field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public void setNetMoney(Double netMoney) {
        this.netMoney = netMoney;
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PositionEffect)
    public PositionEffect getPositionEffect() {
        return positionEffect;
    }

    /**
     * Message field setter.
     * @param positionEffect field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PositionEffect)
    public void setPositionEffect(PositionEffect positionEffect) {
        this.positionEffect = positionEffect;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AutoAcceptIndicator)
    public Boolean getAutoAcceptIndicator() {
        return autoAcceptIndicator;
    }

    /**
     * Message field setter.
     * @param autoAcceptIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AutoAcceptIndicator)
    public void setAutoAcceptIndicator(Boolean autoAcceptIndicator) {
        this.autoAcceptIndicator = autoAcceptIndicator;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NumDaysInterest)
    public Integer getNumDaysInterest() {
        return numDaysInterest;
    }

    /**
     * Message field setter.
     * @param numDaysInterest field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NumDaysInterest)
    public void setNumDaysInterest(Integer numDaysInterest) {
        this.numDaysInterest = numDaysInterest;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestRate)
    public Double getAccruedInterestRate() {
        return accruedInterestRate;
    }

    /**
     * Message field setter.
     * @param accruedInterestRate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestRate)
    public void setAccruedInterestRate(Double accruedInterestRate) {
        this.accruedInterestRate = accruedInterestRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public Double getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param accruedInterestAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotalAccruedInterestAmt)
    public Double getTotalAccruedInterestAmt() {
        return totalAccruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param totalAccruedInterestAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotalAccruedInterestAmt)
    public void setTotalAccruedInterestAmt(Double totalAccruedInterestAmt) {
        this.totalAccruedInterestAmt = totalAccruedInterestAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.InterestAtMaturity)
    public Double getInterestAtMaturity() {
        return interestAtMaturity;
    }

    /**
     * Message field setter.
     * @param interestAtMaturity field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.InterestAtMaturity)
    public void setInterestAtMaturity(Double interestAtMaturity) {
        this.interestAtMaturity = interestAtMaturity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndAccruedInterestAmt)
    public Double getEndAccruedInterestAmt() {
        return endAccruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param endAccruedInterestAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndAccruedInterestAmt)
    public void setEndAccruedInterestAmt(Double endAccruedInterestAmt) {
        this.endAccruedInterestAmt = endAccruedInterestAmt;
    }
  /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StartCash)
    public Double getStartCash() {
        return startCash;
    }

    /**
     * Message field setter.
     * @param startCash field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StartCash)
    public void setStartCash(Double startCash) {
        this.startCash = startCash;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndCash)
    public Double getEndCash() {
        return endCash;
    }

    /**
     * Message field setter.
     * @param endCash field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndCash)
    public void setEndCash(Double endCash) {
        this.endCash = endCash;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegalConfirm)
    public Boolean getLegalConfirm() {
        return legalConfirm;
    }

    /**
     * Message field setter.
     * @param legalConfirm field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegalConfirm)
    public void setLegalConfirm(Boolean legalConfirm) {
        this.legalConfirm = legalConfirm;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNoAllocs)
    public Integer getTotNoAllocs() {
        return totNoAllocs;
    }

    /**
     * Message field setter.
     * @param totNoAllocs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNoAllocs)
    public void setTotNoAllocs(Integer totNoAllocs) {
        this.totNoAllocs = totNoAllocs;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public Integer getNoAllocs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link AllocGroup} groups. It will also create an array
     * of {@link AllocGroup} objects and set the <code>allocGroups</code> field with this array.
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
     * Message field getter for {@link AllocGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public AllocGroup[] getAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link AllocGroup} object to the existing array of <code>allocGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * Note: If the <code>setNoAllocs</code> method has been called there will already be a number of objects in the
     * <code>allocGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public AllocGroup addAllocGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link AllocGroup} object from the existing array of <code>allocGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public AllocGroup deleteAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link AllocGroup} objects from the <code>allocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noAllocs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearAllocGroups() {
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
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (allocReportID == null || allocReportID.trim().isEmpty()) {
            errorMsg.append(" [AllocReportID]");
            hasMissingTag = true;
        }
        if (allocTransType == null) {
            errorMsg.append(" [AllocTransType]");
            hasMissingTag = true;
        }
        if (allocReportType == null) {
            errorMsg.append(" [AllocReportType]");
            hasMissingTag = true;
        }
        if (allocStatus == null) {
            errorMsg.append(" [AllocStatus]");
            hasMissingTag = true;
        }
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol() == null || instrument.getSymbol().trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (quantity == null) {
            errorMsg.append(" [Quantity]");
            hasMissingTag = true;
        }
        if (avgPx == null) {
            errorMsg.append(" [AvgPx]");
            hasMissingTag = true;
        }
        if (tradeDate == null) {
            errorMsg.append(" [TradeDate]");
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
            TagEncoder.encode(bao, TagNum.AllocReportID, allocReportID);
            TagEncoder.encode(bao, TagNum.AllocID, allocID);
            if (allocTransType != null) {
                TagEncoder.encode(bao, TagNum.AllocTransType, allocTransType.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocReportRefID, allocReportRefID);
            if (allocCancReplaceReason != null) {
                TagEncoder.encode(bao, TagNum.AllocCancReplaceReason, allocCancReplaceReason.getValue());
            }
            TagEncoder.encode(bao, TagNum.SecondaryAllocID, secondaryAllocID);
            if (allocReportType != null) {
                TagEncoder.encode(bao, TagNum.AllocReportType, allocReportType.getValue());
            }
            if (allocStatus != null) {
                TagEncoder.encode(bao, TagNum.AllocStatus, allocStatus.getValue());
            }
            if (allocRejCode != null) {
                TagEncoder.encode(bao, TagNum.AllocRejCode, allocRejCode.getValue());
            }
            TagEncoder.encode(bao, TagNum.RefAllocID, refAllocID);
            if (allocIntermedReqType != null) {
                TagEncoder.encode(bao, TagNum.AllocIntermedReqType, allocIntermedReqType.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocLinkID, allocLinkID);
            if (allocLinkType != null) {
                TagEncoder.encode(bao, TagNum.AllocLinkType, allocLinkType.getValue());
            }
            TagEncoder.encode(bao, TagNum.BookingRefID, bookingRefID);
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            if (trdType != null) {
                TagEncoder.encode(bao, TagNum.TrdType, trdType.getValue());
            }
            if (trdSubType != null) {
                TagEncoder.encode(bao, TagNum.TrdSubType, trdSubType.getValue());
            }
            if (multiLegReportingType != null) {
                TagEncoder.encode(bao, TagNum.MultiLegReportingType, multiLegReportingType.getValue());
            }
            if (custOrderCapacity != null) {
                TagEncoder.encode(bao, TagNum.CustOrderCapacity, custOrderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeInputSource, tradeInputSource);
            TagEncoder.encode(bao, TagNum.RndPx, rndPx);
            TagEncoder.encode(bao, TagNum.MessageEventSource, messageEventSource);
            TagEncoder.encode(bao, TagNum.TradeInputDevice, tradeInputDevice);
            if (avgPxIndicator != null) {
                TagEncoder.encode(bao, TagNum.AvgPxIndicator, avgPxIndicator.getValue());
            }
            if (allocNoOrdersType != null) {
                TagEncoder.encode(bao, TagNum.AllocNoOrdersType, allocNoOrdersType.getValue());
            }
            if (noOrders != null) {
                TagEncoder.encode(bao, TagNum.NoOrders, noOrders);
                if (orderAllocGroups != null && orderAllocGroups.length == noOrders.intValue()) {
                    for (int i = 0; i < noOrders.intValue(); i++) {
                        if (orderAllocGroups[i] != null) {
                            bao.write(orderAllocGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "OrderAllocGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoOrders.getValue(), error);
                }
            }
            if (noExecs != null) {
                TagEncoder.encode(bao, TagNum.NoExecs, noExecs);
                if (execAllocGroups != null && execAllocGroups.length == noExecs.intValue()) {
                    for (int i = 0; i < noExecs.intValue(); i++) {
                        if (execAllocGroups[i] != null) {
                            bao.write(execAllocGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "ExecAllocGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoExecs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.PreviouslyReported, previouslyReported);
            TagEncoder.encode(bao, TagNum.ReversalIndicator, reversalIndicator);
            if (matchType != null) {
                TagEncoder.encode(bao, TagNum.MatchType, matchType.getValue());
            }
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (instrumentExtension != null) {
                bao.write(instrumentExtension.encode(MsgSecureType.ALL_FIELDS));
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
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (InstrumentLeg instrumentLeg : instrumentLegs) {
                        bao.write(instrumentLeg.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "InstrumentLegs field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                        TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.Quantity, quantity);
            if (qtyType != null) {
                TagEncoder.encode(bao, TagNum.QtyType, qtyType.getValue());
            }
            TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
            TagEncoder.encodeDate(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            TagEncoder.encode(bao, TagNum.AvgParPx, avgParPx);
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.AvgPxPrecision, avgPxPrecision);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            if (bookingType != null) {
                TagEncoder.encode(bao, TagNum.BookingType, bookingType.getValue());
            }
            TagEncoder.encode(bao, TagNum.GrossTradeAmt, grossTradeAmt);
            TagEncoder.encode(bao, TagNum.Concession, concession);
            TagEncoder.encode(bao, TagNum.TotalTakedown, totalTakedown);
            TagEncoder.encode(bao, TagNum.NetMoney, netMoney);
            if (positionEffect != null) {
                TagEncoder.encode(bao, TagNum.PositionEffect, positionEffect.getValue());
            }
            TagEncoder.encode(bao, TagNum.AutoAcceptIndicator, autoAcceptIndicator);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            TagEncoder.encode(bao, TagNum.NumDaysInterest, numDaysInterest);
            TagEncoder.encode(bao, TagNum.AccruedInterestRate, accruedInterestRate);
            TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            TagEncoder.encode(bao, TagNum.TotalAccruedInterestAmt, totalAccruedInterestAmt);
            TagEncoder.encode(bao, TagNum.InterestAtMaturity, interestAtMaturity);
            TagEncoder.encode(bao, TagNum.EndAccruedInterestAmt, endAccruedInterestAmt);
            TagEncoder.encode(bao, TagNum.StartCash, startCash);
            TagEncoder.encode(bao, TagNum.EndCash, endCash);
            TagEncoder.encode(bao, TagNum.LegalConfirm, legalConfirm);
            if (stipulations != null) {
                bao.write(stipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(MsgSecureType.ALL_FIELDS));
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
            TagEncoder.encode(bao, TagNum.TotNoAllocs, totNoAllocs);
            TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            if (noAllocs != null) {
                TagEncoder.encode(bao, TagNum.NoAllocs, noAllocs);
                if (allocGroups != null && allocGroups.length == noAllocs.intValue()) {
                    for (int i = 0; i < noAllocs.intValue(); i++) {
                        if (allocGroups[i] != null) {
                            bao.write(allocGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "AllocGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoAllocs.getValue(), error);
                }
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
            case AllocReportID:
                allocReportID = new String(tag.value, sessionCharset);
                break;
                
            case AllocID:
                allocID = new String(tag.value, sessionCharset);
                break;

            case AllocTransType:
                allocTransType = AllocTransType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;
                
            case AllocReportRefID:
                allocReportRefID = new String(tag.value, sessionCharset);
                break;

            case AllocCancReplaceReason:
                allocCancReplaceReason = AllocCancReplaceReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SecondaryAllocID:
                secondaryAllocID = new String(tag.value, sessionCharset);
                break;

            case AllocReportType:
                allocReportType = AllocReportType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocStatus:
                allocStatus = AllocStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocRejCode:
                allocRejCode = AllocRejCode.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case RefAllocID:
                refAllocID = new String(tag.value, sessionCharset);
                break;

            case AllocIntermedReqType:
                allocIntermedReqType = AllocIntermedReqType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocLinkID:
                allocLinkID = new String(tag.value, sessionCharset);
                break;

            case AllocLinkType:
                allocLinkType = AllocLinkType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case BookingRefID:
                bookingRefID = new String(tag.value, sessionCharset);
                break;

            case ClearingBusinessDate:
                clearingBusinessDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TrdType:
                trdType = TrdType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TrdSubType:
                trdSubType = TrdSubType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MultiLegReportingType:
                multiLegReportingType = MultiLegReportingType.valueFor(new String(tag.value, sessionCharset));
                break;

            case CustOrderCapacity:
                custOrderCapacity = CustOrderCapacity.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradeInputSource:
                tradeInputSource = new String(tag.value, sessionCharset);
                break;

            case RndPx:
                rndPx = new Double(new String(tag.value, sessionCharset));
                break;

            case MessageEventSource:
                messageEventSource = new String(tag.value, sessionCharset);
                break;

            case TradeInputDevice:
                tradeInputDevice = new String(tag.value, sessionCharset);
                break;

            case AvgPxIndicator:
                avgPxIndicator = AvgPxIndicator.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocNoOrdersType:
                allocNoOrdersType = AllocNoOrdersType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoOrders:
                noOrders = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoExecs:
                noExecs = new Integer(new String(tag.value, sessionCharset));
                break;

            case PreviouslyReported:
                previouslyReported = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case ReversalIndicator:
                reversalIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case MatchType:
                matchType = MatchType.valueFor(new String(tag.value, sessionCharset));
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, sessionCharset));
                break;

            case Quantity:
                quantity = new Double(new String(tag.value, sessionCharset));
                break;

            case QtyType:
                qtyType = QtyType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LastMkt:
                lastMkt = new String(tag.value, sessionCharset);
                break;

            case TradeOriginationDate:
                tradeOriginationDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case PriceType:
                priceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AvgPx:
                avgPx = new Double(new String(tag.value, sessionCharset));
                break;

            case AvgParPx:
                avgParPx = new Double(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case AvgPxPrecision:
                avgPxPrecision = Integer.valueOf(new String(tag.value, sessionCharset));
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
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

            case BookingType:
                bookingType = BookingType.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;

            case GrossTradeAmt:
                grossTradeAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case Concession:
                concession = new Double(new String(tag.value, sessionCharset));
                break;

            case TotalTakedown:
                totalTakedown = new Double(new String(tag.value, sessionCharset));
                break;

            case NetMoney:
                netMoney = new Double(new String(tag.value, sessionCharset));
                break;

            case PositionEffect:
                positionEffect = PositionEffect.valueFor(new String(tag.value, getSessionCharset()));
                break;

            case AutoAcceptIndicator:
                autoAcceptIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case NumDaysInterest:
                numDaysInterest = new Integer(new String(tag.value, sessionCharset));
                break;

            case AccruedInterestRate:
                accruedInterestRate = new Double(new String(tag.value, sessionCharset));
                break;

            case AccruedInterestAmt:
                accruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case TotalAccruedInterestAmt:
                totalAccruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case InterestAtMaturity:
                interestAtMaturity = new Double(new String(tag.value, sessionCharset));
                break;

            case EndAccruedInterestAmt:
                endAccruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case StartCash:
                startCash = new Double(new String(tag.value, sessionCharset));
                break;

            case EndCash:
                endCash = new Double(new String(tag.value, sessionCharset));
                break;

            case LegalConfirm:
                legalConfirm = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoPosAmt:
                noPosAmt = new Integer(new String(tag.value, sessionCharset));
                break;

            case TotNoAllocs:
                totNoAllocs = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastFragment:
                lastFragment = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoAllocs:
                noAllocs = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoRateSources:
                noRateSources = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [AllocationReportMsg] fields.";
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{AllocationReportMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.AllocReportID, allocReportID);
        printTagValue(b, TagNum.AllocID, allocID);
        printTagValue(b, TagNum.AllocTransType, allocTransType);
        printTagValue(b, TagNum.AllocReportRefID, allocReportRefID);
        printTagValue(b, TagNum.AllocCancReplaceReason, allocCancReplaceReason);
        printTagValue(b, TagNum.SecondaryAllocID, secondaryAllocID);
        printTagValue(b, TagNum.AllocReportType, allocReportType);
        printTagValue(b, TagNum.AllocStatus, allocStatus);
        printTagValue(b, TagNum.AllocRejCode, allocRejCode);
        printTagValue(b, TagNum.RefAllocID, refAllocID);
        printTagValue(b, TagNum.AllocIntermedReqType, allocIntermedReqType);
        printTagValue(b, TagNum.AllocLinkID, allocLinkID);
        printTagValue(b, TagNum.AllocLinkType, allocLinkType);
        printTagValue(b, TagNum.BookingRefID, bookingRefID);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, TagNum.TrdType, trdType);
        printTagValue(b, TagNum.TrdSubType, trdSubType);
        printTagValue(b, TagNum.MultiLegReportingType, multiLegReportingType);
        printTagValue(b, TagNum.CustOrderCapacity, custOrderCapacity);
        printTagValue(b, TagNum.TradeInputSource, tradeInputSource);
        printTagValue(b, TagNum.RndPx, rndPx);
        printTagValue(b, TagNum.MessageEventSource, messageEventSource);
        printTagValue(b, TagNum.TradeInputDevice, tradeInputDevice);
        printTagValue(b, TagNum.AvgPxIndicator, avgPxIndicator);
        printTagValue(b, TagNum.AllocNoOrdersType, allocNoOrdersType);
        printTagValue(b, TagNum.NoOrders, noOrders);
        printTagValue(b, orderAllocGroups);
        printTagValue(b, TagNum.NoExecs, noExecs);
        printTagValue(b, execAllocGroups);
        printTagValue(b, TagNum.PreviouslyReported, previouslyReported);
        printTagValue(b, TagNum.ReversalIndicator, reversalIndicator);
        printTagValue(b, TagNum.MatchType, matchType);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, instrument);
        printTagValue(b, instrumentExtension);
        printTagValue(b, financingDetails);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrumentLegs);
        printTagValue(b, TagNum.Quantity, quantity);
        printTagValue(b, TagNum.QtyType, qtyType);
        printTagValue(b, TagNum.LastMkt, lastMkt);
        printDateTagValue(b, TagNum.TradeOriginationDate, tradeOriginationDate);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, TagNum.AvgPx, avgPx);
        printTagValue(b, TagNum.AvgParPx, avgParPx);
        printTagValue(b, spreadOrBenchmarkCurveData);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.AvgPxPrecision, avgPxPrecision);
        printTagValue(b, parties);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.SettlType, settlType);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.BookingType, bookingType);
        printTagValue(b, TagNum.GrossTradeAmt, grossTradeAmt);
        printTagValue(b, TagNum.Concession, concession);
        printTagValue(b, TagNum.TotalTakedown, totalTakedown);
        printTagValue(b, TagNum.NetMoney, netMoney);
        printTagValue(b, TagNum.PositionEffect, positionEffect);
        printTagValue(b, TagNum.AutoAcceptIndicator, autoAcceptIndicator);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.NumDaysInterest, numDaysInterest);
        printTagValue(b, TagNum.AccruedInterestRate, accruedInterestRate);
        printTagValue(b, TagNum.AccruedInterestAmt, accruedInterestAmt);
        printTagValue(b, TagNum.TotalAccruedInterestAmt, totalAccruedInterestAmt);
        printTagValue(b, TagNum.InterestAtMaturity, interestAtMaturity);
        printTagValue(b, TagNum.EndAccruedInterestAmt, endAccruedInterestAmt);
        printTagValue(b, TagNum.StartCash, startCash);
        printTagValue(b, TagNum.EndCash, endCash);
        printTagValue(b, TagNum.LegalConfirm, legalConfirm);
        printTagValue(b, stipulations);
        printTagValue(b, yieldData);
        printTagValue(b, TagNum.NoPosAmt, noPosAmt);
        printTagValue(b, posAmtGroups);
        printTagValue(b, TagNum.TotNoAllocs, totNoAllocs);
        printTagValue(b, TagNum.LastFragment, lastFragment);
        printTagValue(b, TagNum.NoAllocs, noAllocs);
        printTagValue(b, allocGroups);
        printTagValue(b, TagNum.NoRateSources, noRateSources);
        printTagValue(b, rateSources);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
