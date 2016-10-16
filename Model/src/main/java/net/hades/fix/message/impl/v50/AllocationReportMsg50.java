/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationReportMsg50.java
 *
 * $Id: AllocationInstructionMsg44.java,v 1.3 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.AllocationReportMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v50.AllocGroup50;
import net.hades.fix.message.group.impl.v50.ExecAllocGroup50;
import net.hades.fix.message.group.impl.v50.OrderAllocGroup50;
import net.hades.fix.message.group.impl.v50.PosAmtGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50;
import net.hades.fix.message.comp.impl.v50.Instrument50;
import net.hades.fix.message.comp.impl.v50.InstrumentExtension50;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50;
import net.hades.fix.message.comp.impl.v50.Parties50;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50;
import net.hades.fix.message.comp.impl.v50.Stipulations50;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50;
import net.hades.fix.message.comp.impl.v50.YieldData50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.AllocGroup;
import net.hades.fix.message.group.ExecAllocGroup;
import net.hades.fix.message.group.OrderAllocGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.group.StipulationsGroup;
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
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0 AllocationReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="AllocRpt")
@XmlType(propOrder = {"header", "orderAllocGroups", "execAllocGroups", "instrument", "instrumentExtension", "financingDetails",
    "underlyingInstruments", "instrumentLegs", "spreadOrBenchmarkCurveData", "partyIDGroups", "stipulationsGroups", "yieldData",
    "posAmtGroups", "allocGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class AllocationReportMsg50 extends AllocationReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ORDER_ALLOC_GROUP_TAGS = new OrderAllocGroup50().getFragmentAllTags();
    protected static final Set<Integer> EXEC_ALLOC_GROUP_TAGS = new ExecAllocGroup50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_EXTENSION_COMP_TAGS = new InstrumentExtension50().getFragmentAllTags();
    protected static final Set<Integer> FINANCING_DETAILS_COMP_TAGS = new FinancingDetails50().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData50().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations50().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData50().getFragmentAllTags();
    protected static final Set<Integer> POS_AMT_GROUP_TAGS = new PosAmtGroup50().getFragmentAllTags();
    protected static final Set<Integer> ALLOC_GROUP_TAGS = new AllocGroup50().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(ORDER_ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(EXEC_ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_EXTENSION_COMP_TAGS);
        ALL_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(POS_AMT_GROUP_TAGS);
        ALL_TAGS.addAll(ALLOC_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ORDER_ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(EXEC_ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_EXTENSION_COMP_TAGS);
        START_COMP_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(POS_AMT_GROUP_TAGS);
        START_COMP_TAGS.addAll(ALLOC_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AllocationReportMsg50() {
        super();
    }

    public AllocationReportMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public AllocationReportMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public AllocationReportMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        AllocationReportMsg50 fixml = (AllocationReportMsg50) fragment;
        if (fixml.getAllocReportID() != null) {
            allocReportID = fixml.getAllocReportID();
        }
        if (fixml.getAllocID() != null) {
            allocID = fixml.getAllocID();
        }
        if (fixml.getAllocTransType() != null) {
            allocTransType = fixml.getAllocTransType();
        }
        if (fixml.getAllocReportRefID() != null) {
            allocReportRefID = fixml.getAllocReportRefID();
        }
        if (fixml.getAllocCancReplaceReason() != null) {
            allocCancReplaceReason = fixml.getAllocCancReplaceReason();
        }
        if (fixml.getSecondaryAllocID() != null) {
            secondaryAllocID = fixml.getSecondaryAllocID();
        }
        if (fixml.getAllocReportType() != null) {
            allocReportType = fixml.getAllocReportType();
        }
        if (fixml.getAllocStatus() != null) {
            allocStatus = fixml.getAllocStatus();
        }
        if (fixml.getAllocRejCode() != null) {
            allocRejCode = fixml.getAllocRejCode();
        }
        if (fixml.getRefAllocID() != null) {
            refAllocID = fixml.getRefAllocID();
        }
        if (fixml.getAllocIntermedReqType() != null) {
            allocIntermedReqType = fixml.getAllocIntermedReqType();
        }
        if (fixml.getAllocLinkID() != null) {
            allocLinkID = fixml.getAllocLinkID();
        }
        if (fixml.getAllocLinkType() != null) {
            allocLinkType = fixml.getAllocLinkType();
        }
        if (fixml.getBookingRefID() != null) {
            bookingRefID = fixml.getBookingRefID();
        }
        if (fixml.getClearingBusinessDate() != null) {
            clearingBusinessDate = fixml.getClearingBusinessDate();
        }
        if (fixml.getTrdType() != null) {
            trdType = fixml.getTrdType();
        }
        if (fixml.getTrdSubType() != null) {
            trdSubType = fixml.getTrdSubType();
        }
        if (fixml.getMultiLegReportingType() != null) {
            multiLegReportingType = fixml.getMultiLegReportingType();
        }
        if (fixml.getCustOrderCapacity() != null) {
            custOrderCapacity = fixml.getCustOrderCapacity();
        }
        if (fixml.getTradeInputSource() != null) {
            tradeInputSource = fixml.getTradeInputSource();
        }
        if (fixml.getRndPx() != null) {
            rndPx = fixml.getRndPx();
        }
        if (fixml.getMessageEventSource() != null) {
            messageEventSource = fixml.getMessageEventSource();
        }
        if (fixml.getTradeInputDevice() != null) {
            tradeInputDevice = fixml.getTradeInputDevice();
        }
        if (fixml.getAvgPxIndicator() != null) {
            avgPxIndicator = fixml.getAvgPxIndicator();
        }
        if (fixml.getAllocNoOrdersType() != null) {
            allocNoOrdersType = fixml.getAllocNoOrdersType();
        }
        if (fixml.getOrderAllocGroups() != null && fixml.getOrderAllocGroups().length > 0) {
            setOrderAllocGroups(fixml.getOrderAllocGroups());
        }
        if (fixml.getExecAllocGroups() != null && fixml.getExecAllocGroups().length > 0) {
            setExecAllocGroups(fixml.getExecAllocGroups());
        }
        if (fixml.getPreviouslyReported() != null) {
            previouslyReported = fixml.getPreviouslyReported();
        }
        if (fixml.getReversalIndicator() != null) {
            reversalIndicator = fixml.getReversalIndicator();
        }
        if (fixml.getMatchType() != null) {
            matchType = fixml.getMatchType();
        }
        if (fixml.getSide() != null) {
            side = fixml.getSide();
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getInstrumentExtension() != null) {
            setInstrumentExtension(fixml.getInstrumentExtension());
        }
        if (fixml.getFinancingDetails() != null) {
            setFinancingDetails(fixml.getFinancingDetails());
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getInstrumentLegs() != null && fixml.getInstrumentLegs().length > 0) {
            setInstrumentLegs(fixml.getInstrumentLegs());
        }
        if (fixml.getQuantity() != null) {
            quantity = fixml.getQuantity();
        }
        if (fixml.getQtyType() != null) {
            qtyType = fixml.getQtyType();
        }
        if (fixml.getLastMkt() != null) {
            lastMkt = fixml.getLastMkt();
        }
        if (fixml.getTradeOriginationDate() != null) {
            tradeOriginationDate = fixml.getTradeOriginationDate();
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
        }
        if (fixml.getPriceType() != null) {
            priceType = fixml.getPriceType();
        }
        if (fixml.getAvgPx() != null) {
            avgPx = fixml.getAvgPx();
        }
        if (fixml.getAvgParPx() != null) {
            avgParPx = fixml.getAvgParPx();
        }
        if (fixml.getSpreadOrBenchmarkCurveData() != null) {
            setSpreadOrBenchmarkCurveData(fixml.getSpreadOrBenchmarkCurveData());
        }
        if (fixml.getCurrency() != null) {
            currency = fixml.getCurrency();
        }
        if (fixml.getAvgPxPrecision() != null) {
            avgPxPrecision = fixml.getAvgPxPrecision();
        }
        if (fixml.getParties() != null) {
            setParties(fixml.getParties());
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getSettlType() != null) {
            settlType = fixml.getSettlType();
        }
        if (fixml.getSettlDate() != null) {
            settlDate = fixml.getSettlDate();
        }
        if (fixml.getBookingType() != null) {
            bookingType = fixml.getBookingType();
        }
        if (fixml.getGrossTradeAmt() != null) {
            grossTradeAmt = fixml.getGrossTradeAmt();
        }
        if (fixml.getConcession() != null) {
            concession = fixml.getConcession();
        }
        if (fixml.getTotalTakedown() != null) {
            totalTakedown = fixml.getTotalTakedown();
        }
        if (fixml.getNetMoney() != null) {
            netMoney = fixml.getNetMoney();
        }
        if (fixml.getPositionEffect() != null) {
            positionEffect = fixml.getPositionEffect();
        }
        if (fixml.getAutoAcceptIndicator() != null) {
            autoAcceptIndicator = fixml.getAutoAcceptIndicator();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
        if (fixml.getNumDaysInterest() != null) {
            numDaysInterest = fixml.getNumDaysInterest();
        }
        if (fixml.getAccruedInterestRate() != null) {
            accruedInterestRate = fixml.getAccruedInterestRate();
        }
        if (fixml.getAccruedInterestAmt() != null) {
            accruedInterestAmt = fixml.getAccruedInterestAmt();
        }
        if (fixml.getTotalAccruedInterestAmt() != null) {
            totalAccruedInterestAmt = fixml.getTotalAccruedInterestAmt();
        }
        if (fixml.getInterestAtMaturity() != null) {
            interestAtMaturity = fixml.getInterestAtMaturity();
        }
        if (fixml.getEndAccruedInterestAmt() != null) {
            endAccruedInterestAmt = fixml.getEndAccruedInterestAmt();
        }
        if (fixml.getStartCash() != null) {
            startCash = fixml.getStartCash();
        }
        if (fixml.getEndCash() != null) {
            endCash = fixml.getEndCash();
        }
        if (fixml.getLegalConfirm() != null) {
            legalConfirm = fixml.getLegalConfirm();
        }
        if (fixml.getStipulations() != null) {
            setStipulations(fixml.getStipulations());
        }
        if (fixml.getYieldData() != null) {
            setYieldData(fixml.getYieldData());
        }
        if (fixml.getPosAmtGroups() != null && fixml.getPosAmtGroups().length > 0) {
            setPosAmtGroups(fixml.getPosAmtGroups());
        }
        if (fixml.getTotNoAllocs() != null) {
            totNoAllocs = fixml.getTotNoAllocs();
        }
        if (fixml.getLastFragment() != null) {
            lastFragment = fixml.getLastFragment();
        }
        if (fixml.getAllocGroups() != null) {
            setAllocGroups(fixml.getAllocGroups());
        }
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "RptID")
    @Override
    public String getAllocReportID() {
        return allocReportID;
    }

    @Override
    public void setAllocReportID(String allocReportID) {
        this.allocReportID = allocReportID;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getAllocID() {
        return allocID;
    }

    @Override
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    @XmlAttribute(name = "TransTyp")
    @Override
    public AllocTransType getAllocTransType() {
        return allocTransType;
    }

    @Override
    public void setAllocTransType(AllocTransType allocTransType) {
        this.allocTransType = allocTransType;
    }

    @XmlAttribute(name = "RptRefID")
    @Override
    public String getAllocReportRefID() {
        return allocReportRefID;
    }

    @Override
    public void setAllocReportRefID(String allocReportRefID) {
        this.allocReportRefID = allocReportRefID;
    }

    @XmlAttribute(name = "CxlRplcRsn")
    @Override
    public AllocCancReplaceReason getAllocCancReplaceReason() {
        return allocCancReplaceReason;
    }

    @Override
    public void setAllocCancReplaceReason(AllocCancReplaceReason allocCancReplaceReason) {
        this.allocCancReplaceReason = allocCancReplaceReason;
    }

    @XmlAttribute(name = "ID2")
    @Override
    public String getSecondaryAllocID() {
        return secondaryAllocID;
    }

    @Override
    public void setSecondaryAllocID(String secondaryAllocID) {
        this.secondaryAllocID = secondaryAllocID;
    }

    @XmlAttribute(name = "RptTyp")
    @Override
    public AllocReportType getAllocReportType() {
        return allocReportType;
    }

    @Override
    public void setAllocReportType(AllocReportType allocReportType) {
        this.allocReportType = allocReportType;
    }

    @XmlAttribute(name = "Stat")
    @Override
    public AllocStatus getAllocStatus() {
        return allocStatus;
    }

    @Override
    public void setAllocStatus(AllocStatus allocStatus) {
        this.allocStatus = allocStatus;
    }

    @XmlAttribute(name = "RejCode")
    @Override
    public AllocRejCode getAllocRejCode() {
        return allocRejCode;
    }

    @Override
    public void setAllocRejCode(AllocRejCode allocRejCode) {
        this.allocRejCode = allocRejCode;
    }

    @XmlAttribute(name = "RefID")
    @Override
    public String getRefAllocID() {
        return refAllocID;
    }

    @Override
    public void setRefAllocID(String refAllocID) {
        this.refAllocID = refAllocID;
    }

    @XmlAttribute(name = "ImReqTyp")
    @Override
    public AllocIntermedReqType getAllocIntermedReqType() {
        return allocIntermedReqType;
    }

    @Override
    public void setAllocIntermedReqType(AllocIntermedReqType allocIntermedReqType) {
        this.allocIntermedReqType = allocIntermedReqType;
    }

    @XmlAttribute(name = "LinkID")
    @Override
    public String getAllocLinkID() {
        return allocLinkID;
    }

    @Override
    public void setAllocLinkID(String allocLinkID) {
        this.allocLinkID = allocLinkID;
    }

    @XmlAttribute(name = "LinkTyp")
    @Override
    public AllocLinkType getAllocLinkType() {
        return allocLinkType;
    }

    @Override
    public void setAllocLinkType(AllocLinkType allocLinkType) {
        this.allocLinkType = allocLinkType;
    }

    @XmlAttribute(name = "BkngRefID")
    @Override
    public String getBookingRefID() {
        return bookingRefID;
    }

    @Override
    public void setBookingRefID(String bookingRefID) {
        this.bookingRefID = bookingRefID;
    }

    @XmlAttribute(name = "BizDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    @Override
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    @XmlAttribute(name = "TrdTyp")
    @Override
    public TrdType getTrdType() {
        return trdType;
    }

    @Override
    public void setTrdType(TrdType trdType) {
        this.trdType = trdType;
    }

    @XmlAttribute(name = "TrdSubTyp")
    @Override
    public TrdSubType getTrdSubType() {
        return trdSubType;
    }

    @Override
    public void setTrdSubType(TrdSubType trdSubType) {
        this.trdSubType = trdSubType;
    }

    @XmlAttribute(name = "MLegRptTyp")
    @Override
    public MultiLegReportingType getMultiLegReportingType() {
        return multiLegReportingType;
    }

    @Override
    public void setMultiLegReportingType(MultiLegReportingType multiLegReportingType) {
        this.multiLegReportingType = multiLegReportingType;
    }

    @XmlAttribute(name = "CustCpcty")
    @Override
    public CustOrderCapacity getCustOrderCapacity() {
        return custOrderCapacity;
    }

    @Override
    public void setCustOrderCapacity(CustOrderCapacity custOrderCapacity) {
        this.custOrderCapacity = custOrderCapacity;
    }

    @XmlAttribute(name = "InptSrc")
    @Override
    public String getTradeInputSource() {
        return tradeInputSource;
    }

    @Override
    public void setTradeInputSource(String tradeInputSource) {
        this.tradeInputSource = tradeInputSource;
    }

    @XmlAttribute(name = "RndPx")
    @Override
    public Double getRndPx() {
        return rndPx;
    }

    @Override
    public void setRndPx(Double rndPx) {
        this.rndPx = rndPx;
    }

    @XmlAttribute(name = "MsgEvtSrc")
    @Override
    public String getMessageEventSource() {
        return messageEventSource;
    }

    @Override
    public void setMessageEventSource(String messageEventSource) {
        this.messageEventSource = messageEventSource;
    }

    @XmlAttribute(name = "InptDev")
    @Override
    public String getTradeInputDevice() {
        return tradeInputDevice;
    }

    @Override
    public void setTradeInputDevice(String tradeInputDevice) {
        this.tradeInputDevice = tradeInputDevice;
    }

    @XmlAttribute(name = "AvgPxInd")
    @Override
    public AvgPxIndicator getAvgPxIndicator() {
        return avgPxIndicator;
    }

    @Override
    public void setAvgPxIndicator(AvgPxIndicator avgPxIndicator) {
        this.avgPxIndicator = avgPxIndicator;
    }
    
    @XmlAttribute(name = "NoOrdsTyp")
    @Override
    public AllocNoOrdersType getAllocNoOrdersType() {
        return allocNoOrdersType;
    }

    @Override
    public void setAllocNoOrdersType(AllocNoOrdersType allocNoOrdersType) {
        this.allocNoOrdersType = allocNoOrdersType;
    }

    @Override
    public Integer getNoOrders() {
        return noOrders;
    }

    @Override
    public void setNoOrders(Integer noOrders) {
        this.noOrders = noOrders;
        if (noOrders != null) {
            orderAllocGroups = new OrderAllocGroup[noOrders.intValue()];
            for (int i = 0; i < orderAllocGroups.length; i++) {
                orderAllocGroups[i] = new OrderAllocGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public OrderAllocGroup[] getOrderAllocGroups() {
        return orderAllocGroups;
    }
    
    public void setOrderAllocGroups(OrderAllocGroup[] orderAllocGroups) {
        this.orderAllocGroups = orderAllocGroups;
        if (orderAllocGroups != null) {
            noOrders = new Integer(orderAllocGroups.length);
        }
    }

    @Override
    public OrderAllocGroup addOrderAllocGroup() {
        OrderAllocGroup group = new OrderAllocGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<OrderAllocGroup> groups = new ArrayList<OrderAllocGroup>();
        if (orderAllocGroups != null && orderAllocGroups.length > 0) {
            groups = new ArrayList<OrderAllocGroup>(Arrays.asList(orderAllocGroups));
        }
        groups.add(group);
        orderAllocGroups = groups.toArray(new OrderAllocGroup[groups.size()]);
        noOrders = new Integer(orderAllocGroups.length);

        return group;
    }

    @Override
    public OrderAllocGroup deleteOrderAllocGroup(int index) {
        OrderAllocGroup result = null;
        if (orderAllocGroups != null && orderAllocGroups.length > 0 && orderAllocGroups.length > index) {
            List<OrderAllocGroup> groups = new ArrayList<OrderAllocGroup>(Arrays.asList(orderAllocGroups));
            result = groups.remove(index);
            orderAllocGroups = groups.toArray(new OrderAllocGroup[groups.size()]);
            if (orderAllocGroups.length > 0) {
                noOrders = new Integer(orderAllocGroups.length);
            } else {
                orderAllocGroups = null;
                noOrders = null;
            }
        }

        return result;
    }

    @Override
    public int clearOrderAllocGroups() {
        int result = 0;
        if (orderAllocGroups != null && orderAllocGroups.length > 0) {
            result = orderAllocGroups.length;
            orderAllocGroups = null;
            noOrders = null;
        }

        return result;
    }

    @Override
    public Integer getNoExecs() {
        return noExecs;
    }

    @Override
    public void setNoExecs(Integer noExecs) {
        this.noExecs = noExecs;
        if (noExecs != null) {
            execAllocGroups = new ExecAllocGroup[noExecs.intValue()];
            for (int i = 0; i < execAllocGroups.length; i++) {
                execAllocGroups[i] = new ExecAllocGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public ExecAllocGroup[] getExecAllocGroups() {
        return execAllocGroups;
    }

    public void setExecAllocGroups(ExecAllocGroup[] execAllocGroups) {
        this.execAllocGroups = execAllocGroups;
        if (execAllocGroups != null) {
            noExecs = new Integer(execAllocGroups.length);
        }
    }

    @Override
    public ExecAllocGroup addExecAllocGroup() {
        ExecAllocGroup group = new ExecAllocGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<ExecAllocGroup> groups = new ArrayList<ExecAllocGroup>();
        if (execAllocGroups != null && execAllocGroups.length > 0) {
            groups = new ArrayList<ExecAllocGroup>(Arrays.asList(execAllocGroups));
        }
        groups.add(group);
        execAllocGroups = groups.toArray(new ExecAllocGroup[groups.size()]);
        noExecs = new Integer(execAllocGroups.length);

        return group;
    }

    @Override
    public ExecAllocGroup deleteExecAllocGroup(int index) {
        ExecAllocGroup result = null;
        if (execAllocGroups != null && execAllocGroups.length > 0 && execAllocGroups.length > index) {
            List<ExecAllocGroup> groups = new ArrayList<ExecAllocGroup>(Arrays.asList(execAllocGroups));
            result = groups.remove(index);
            execAllocGroups = groups.toArray(new ExecAllocGroup[groups.size()]);
            if (execAllocGroups.length > 0) {
                noExecs = new Integer(execAllocGroups.length);
            } else {
                execAllocGroups = null;
                noExecs = null;
            }
        }

        return result;
    }

    @Override
    public int clearExecAllocGroups() {
        int result = 0;
        if (execAllocGroups != null && execAllocGroups.length > 0) {
            result = execAllocGroups.length;
            execAllocGroups = null;
            noExecs = null;
        }

        return result;
    }

    @XmlAttribute(name = "PrevlyRpted")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPreviouslyReported() {
        return previouslyReported;
    }

    @Override
    public void setPreviouslyReported(Boolean previouslyReported) {
        this.previouslyReported = previouslyReported;
    }

    @XmlAttribute(name = "ReversalInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getReversalIndicator() {
        return reversalIndicator;
    }

    @Override
    public void setReversalIndicator(Boolean reversalIndicator) {
        this.reversalIndicator = reversalIndicator;
    }

    @XmlAttribute(name = "MtchTyp")
    @Override
    public MatchType getMatchType() {
        return matchType;
    }

    @Override
    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    @XmlAttribute(name = "Side")
    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrument = new Instrument50(context);
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    @XmlElementRef
    @Override
    public InstrumentExtension getInstrumentExtension() {
        return instrumentExtension;
    }

    @Override
    public void setInstrumentExtension() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrumentExtension = new InstrumentExtension50(context);
    }

    public void setInstrumentExtension(InstrumentExtension instrumentExtension) {
        this.instrumentExtension = instrumentExtension;
    }

    @Override
    public void clearInstrumentExtension() {
        instrumentExtension = null;
    }

    @XmlElementRef
    @Override
    public FinancingDetails getFinancingDetails() {
        return financingDetails;
    }

    @Override
    public void setFinancingDetails() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.financingDetails = new FinancingDetails50(context);
    }

    public void setFinancingDetails(FinancingDetails financingDetails) {
        this.financingDetails = financingDetails;
    }

    @Override
    public void clearFinancingDetails() {
        this.financingDetails = null;
    }

    @Override
    public Integer getNoUnderlyings() {
        return noUnderlyings;
    }

    @Override
    public void setNoUnderlyings(Integer noUnderlyings) {
        this.noUnderlyings = noUnderlyings;
        if (noUnderlyings != null) {
            underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < underlyingInstruments.length; i++) {
                underlyingInstruments[i] = new UnderlyingInstrument50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        return underlyingInstruments;
    }

    public void setUnderlyingInstruments(UnderlyingInstrument[] underlyingInstruments) {
        this.underlyingInstruments = underlyingInstruments;
        if (underlyingInstruments != null) {
            noUnderlyings = new Integer(underlyingInstruments.length);
        }
    }

    @Override
    public UnderlyingInstrument addUnderlyingInstrument() {
        UnderlyingInstrument group = new UnderlyingInstrument50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>();
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
        }
        groups.add(group);
        underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
        noUnderlyings = new Integer(underlyingInstruments.length);

        return group;
    }

    @Override
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        UnderlyingInstrument result = null;
        if (underlyingInstruments != null && underlyingInstruments.length > 0 && underlyingInstruments.length > index) {
            List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
            result = groups.remove(index);
            underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
            if (underlyingInstruments.length > 0) {
                noUnderlyings = new Integer(underlyingInstruments.length);
            } else {
                underlyingInstruments = null;
                noUnderlyings = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingInstruments() {
        int result = 0;
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            result = underlyingInstruments.length;
            underlyingInstruments = null;
            noUnderlyings = null;
        }

        return result;
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            instrumentLegs = new InstrumentLeg[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < instrumentLegs.length; i++) {
                instrumentLegs[i] = new InstrumentLeg50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public InstrumentLeg[] getInstrumentLegs() {
        return instrumentLegs;
    }

    public void setInstrumentLegs(InstrumentLeg[] instrumentLegs) {
        this.instrumentLegs = instrumentLegs;
        if (instrumentLegs != null) {
            noLegs = instrumentLegs.length;
        }
    }

    @Override
    public InstrumentLeg addInstrumentLeg() {
        InstrumentLeg group = new InstrumentLeg50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<InstrumentLeg> groups = new ArrayList<InstrumentLeg>();
        if (instrumentLegs != null && instrumentLegs.length > 0) {
            groups = new ArrayList<InstrumentLeg>(Arrays.asList(instrumentLegs));
        }
        groups.add(group);
        instrumentLegs = groups.toArray(new InstrumentLeg[groups.size()]);
        noLegs = new Integer(instrumentLegs.length);

        return group;
    }

    @Override
    public InstrumentLeg deleteInstrumentLeg(int index) {
        InstrumentLeg result = null;
        if (instrumentLegs != null && instrumentLegs.length > 0 && instrumentLegs.length > index) {
            List<InstrumentLeg> groups = new ArrayList<InstrumentLeg>(Arrays.asList(instrumentLegs));
            result = groups.remove(index);
            instrumentLegs = groups.toArray(new InstrumentLeg[groups.size()]);
            if (instrumentLegs.length > 0) {
                noLegs = new Integer(instrumentLegs.length);
            } else {
                instrumentLegs = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrumentLegs() {
        int result = 0;
        if (instrumentLegs != null && instrumentLegs.length > 0) {
            result = instrumentLegs.length;
            instrumentLegs = null;
            noLegs = null;
        }

        return result;
    }

    @XmlAttribute(name = "Qty")
    @Override
    public Double getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @XmlAttribute(name = "QtyTyp")
    @Override
    public QtyType getQtyType() {
        return qtyType;
    }

    @Override
    public void setQtyType(QtyType qtyType) {
        this.qtyType = qtyType;
    }

    @XmlAttribute(name = "LastMkt")
    @Override
    public String getLastMkt() {
        return lastMkt;
    }

    @Override
    public void setLastMkt(String lastMkt) {
        this.lastMkt = lastMkt;
    }

    @XmlAttribute(name = "OrignDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getTradeOriginationDate() {
        return tradeOriginationDate;
    }

    @Override
    public void setTradeOriginationDate(Date tradeOriginationDate) {
        this.tradeOriginationDate = tradeOriginationDate;
    }

    @XmlAttribute(name = "SesID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @XmlAttribute(name = "SesSub")
    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @XmlAttribute(name = "PxTyp")
    @Override
    public PriceType getPriceType() {
        return priceType;
    }

    @Override
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    @XmlAttribute(name = "AvgPx")
    @Override
    public Double getAvgPx() {
        return avgPx;
    }

    @Override
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
    }

    @XmlAttribute(name = "AvgParPx")
    @Override
    public Double getAvgParPx() {
        return avgParPx;
    }

    @Override
    public void setAvgParPx(Double avgParPx) {
        this.avgParPx = avgParPx;
    }

    @XmlElementRef
    @Override
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50(context);
    }

    @Override
    public void clearSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = null;
    }

    public void setSpreadOrBenchmarkCurveData(SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData) {
        this.spreadOrBenchmarkCurveData = spreadOrBenchmarkCurveData;
    }

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @XmlAttribute(name = "AvgPxPrcsn")
    @Override
    public Integer getAvgPxPrecision() {
        return avgPxPrecision;
    }

    @Override
    public void setAvgPxPrecision(Integer avgPxPrecision) {
        this.avgPxPrecision = avgPxPrecision;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.parties = new Parties50(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @XmlElementRef
    public PartyGroup[] getPartyIDGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        if (partyIDGroups != null) {
            if (parties == null) {
                setParties();
            }
            ((Parties50) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    @XmlAttribute(name = "TrdDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @XmlAttribute(name = "SettlTyp")
    @Override
    public String getSettlType() {
        return settlType;
    }

    @Override
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    @XmlAttribute(name = "SettlDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getSettlDate() {
        return settlDate;
    }

    @Override
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    @XmlAttribute(name = "BkngTyp")
    @Override
    public BookingType getBookingType() {
        return bookingType;
    }

    @Override
    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    @XmlAttribute(name = "GrossTrdAmt")
    @Override
    public Double getGrossTradeAmt() {
        return grossTradeAmt;
    }

    @Override
    public void setGrossTradeAmt(Double grossTradeAmt) {
        this.grossTradeAmt = grossTradeAmt;
    }

    @XmlAttribute(name = "Concession")
    @Override
    public Double getConcession() {
        return concession;
    }

    @Override
    public void setConcession(Double concession) {
        this.concession = concession;
    }

    @XmlAttribute(name = "TotTakedown")
    @Override
    public Double getTotalTakedown() {
        return totalTakedown;
    }

    @Override
    public void setTotalTakedown(Double totalTakedown) {
        this.totalTakedown = totalTakedown;
    }

    @XmlAttribute(name = "NetMny")
    @Override
    public Double getNetMoney() {
        return netMoney;
    }

    @Override
    public void setNetMoney(Double netMoney) {
        this.netMoney = netMoney;
    }

    @XmlAttribute(name = "PosEfct")
    @Override
    public PositionEffect getPositionEffect() {
        return positionEffect;
    }

    @Override
    public void setPositionEffect(PositionEffect positionEffect) {
        this.positionEffect = positionEffect;
    }

    @XmlAttribute(name = "AutoAcceptInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getAutoAcceptIndicator() {
        return autoAcceptIndicator;
    }

    @Override
    public void setAutoAcceptIndicator(Boolean autoAcceptIndicator) {
        this.autoAcceptIndicator = autoAcceptIndicator;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    @XmlAttribute(name = "NumDaysInt")
    @Override
    public Integer getNumDaysInterest() {
        return numDaysInterest;
    }

    @Override
    public void setNumDaysInterest(Integer numDaysInterest) {
        this.numDaysInterest = numDaysInterest;
    }

    @XmlAttribute(name = "AcrdIntRt")
    @Override
    public Double getAccruedInterestRate() {
        return accruedInterestRate;
    }

    @Override
    public void setAccruedInterestRate(Double accruedInterestRate) {
        this.accruedInterestRate = accruedInterestRate;
    }

    @XmlAttribute(name = "AcrdIntAmt")
    @Override
    public Double getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    @Override
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
    }

    @XmlAttribute(name = "TotAcrdIntAmt")
    @Override
    public Double getTotalAccruedInterestAmt() {
        return totalAccruedInterestAmt;
    }

    @Override
    public void setTotalAccruedInterestAmt(Double totalAccruedInterestAmt) {
        this.totalAccruedInterestAmt = totalAccruedInterestAmt;
    }

    @XmlAttribute(name = "IntAtMat")
    @Override
    public Double getInterestAtMaturity() {
        return interestAtMaturity;
    }

    @Override
    public void setInterestAtMaturity(Double interestAtMaturity) {
        this.interestAtMaturity = interestAtMaturity;
    }

    @XmlAttribute(name = "EndAcrdIntAmt")
    @Override
    public Double getEndAccruedInterestAmt() {
        return endAccruedInterestAmt;
    }

    @Override
    public void setEndAccruedInterestAmt(Double endAccruedInterestAmt) {
        this.endAccruedInterestAmt = endAccruedInterestAmt;
    }

    @XmlAttribute(name = "StartCsh")
    @Override
    public Double getStartCash() {
        return startCash;
    }

    @Override
    public void setStartCash(Double startCash) {
        this.startCash = startCash;
    }

    @XmlAttribute(name = "EndCsh")
    @Override
    public Double getEndCash() {
        return endCash;
    }

    @Override
    public void setEndCash(Double endCash) {
        this.endCash = endCash;
    }

    @XmlAttribute(name = "LegalCnfm")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getLegalConfirm() {
        return legalConfirm;
    }

    @Override
    public void setLegalConfirm(Boolean legalConfirm) {
        this.legalConfirm = legalConfirm;
    }

    @Override
    public Stipulations getStipulations() {
        return stipulations;
    }

    @Override
    public void setStipulations() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.stipulations = new Stipulations50(context);
    }

    @Override
    public void clearStipulations() {
         this.stipulations = null;
    }

    public void setStipulations(Stipulations stipulations) {
        this.stipulations = stipulations;
    }

    @XmlElementRef
    public StipulationsGroup[] getStipulationsGroups() {
        return stipulations == null ? null : stipulations.getStipulationsGroups();
    }

    public void setStipulationsGroups(StipulationsGroup[] stipulationsGroups) {
        if (stipulationsGroups != null) {
            if (stipulations == null) {
                setStipulations();
            }
            ((Stipulations50) stipulations).setStipulationsGroups(stipulationsGroups);
        }
    }

    @XmlElementRef
    @Override
    public YieldData getYieldData() {
        return yieldData;
    }

    @Override
    public void setYieldData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.yieldData = new YieldData50(context);
    }

    @Override
    public void clearYieldData() {
        this.yieldData = null;
    }

    public void setYieldData(YieldData yieldData) {
        this.yieldData = yieldData;
    }

    @Override
    public Integer getNoPosAmt() {
        return noPosAmt;
    }

    @Override
    public void setNoPosAmt(Integer noPosAmt) {
        this.noPosAmt = noPosAmt;
        if (noPosAmt != null) {
            posAmtGroups = new PosAmtGroup[noPosAmt.intValue()];
            for (int i = 0; i < posAmtGroups.length; i++) {
                posAmtGroups[i] = new PosAmtGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public PosAmtGroup[] getPosAmtGroups() {
        return posAmtGroups;
    }

    public void setPosAmtGroups(PosAmtGroup[] posAmtGroups) {
        this.posAmtGroups = posAmtGroups;
        if (posAmtGroups != null) {
            noPosAmt = new Integer(posAmtGroups.length);
        }
    }

    @Override
    public PosAmtGroup addPosAmtGroup() {
        PosAmtGroup group = new PosAmtGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<PosAmtGroup> groups = new ArrayList<PosAmtGroup>();
        if (posAmtGroups != null && posAmtGroups.length > 0) {
            groups = new ArrayList<PosAmtGroup>(Arrays.asList(posAmtGroups));
        }
        groups.add(group);
        posAmtGroups = groups.toArray(new PosAmtGroup[groups.size()]);
        noPosAmt = new Integer(posAmtGroups.length);

        return group;
    }

    @Override
    public PosAmtGroup deletePosAmtGroup(int index) {
        PosAmtGroup result = null;
        if (posAmtGroups != null && posAmtGroups.length > 0 && posAmtGroups.length > index) {
            List<PosAmtGroup> groups = new ArrayList<PosAmtGroup>(Arrays.asList(posAmtGroups));
            result = groups.remove(index);
            posAmtGroups = groups.toArray(new PosAmtGroup[groups.size()]);
            if (posAmtGroups.length > 0) {
                noPosAmt = new Integer(posAmtGroups.length);
            } else {
                posAmtGroups = null;
                noPosAmt = null;
            }
        }

        return result;
    }

    @Override
    public int clearPosAmtGroups() {
        int result = 0;
        if (posAmtGroups != null && posAmtGroups.length > 0) {
            result = posAmtGroups.length;
            posAmtGroups = null;
            noPosAmt = null;
        }

        return result;
    }

    @XmlAttribute(name = "TotNoAllocs")
    @Override
    public Integer getTotNoAllocs() {
        return totNoAllocs;
    }

    @Override
    public void setTotNoAllocs(Integer totNoAllocs) {
        this.totNoAllocs = totNoAllocs;
    }

    @XmlAttribute(name = "LastFragment")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getLastFragment() {
        return lastFragment;
    }

    @Override
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    @Override
    public Integer getNoAllocs() {
        return noAllocs;
    }

    @Override
    public void setNoAllocs(Integer noAllocs) {
        this.noAllocs = noAllocs;
        if (noAllocs != null) {
            allocGroups = new AllocGroup[noAllocs.intValue()];
            for (int i = 0; i < allocGroups.length; i++) {
                allocGroups[i] = new AllocGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public AllocGroup[] getAllocGroups() {
        return allocGroups;
    }

    public void setAllocGroups(AllocGroup[] allocGroups) {
        this.allocGroups = allocGroups;
        if (allocGroups != null) {
            noAllocs = new Integer(allocGroups.length);
        }
    }

    @Override
    public AllocGroup addAllocGroup() {
        AllocGroup group = new AllocGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<AllocGroup> groups = new ArrayList<AllocGroup>();
        if (allocGroups != null && allocGroups.length > 0) {
            groups = new ArrayList<AllocGroup>(Arrays.asList(allocGroups));
        }
        groups.add(group);
        allocGroups = groups.toArray(new AllocGroup[groups.size()]);
        noAllocs = new Integer(allocGroups.length);

        return group;
    }

    @Override
    public AllocGroup deleteAllocGroup(int index) {
        AllocGroup result = null;
        if (allocGroups != null && allocGroups.length > 0 && allocGroups.length > index) {
            List<AllocGroup> groups = new ArrayList<AllocGroup>(Arrays.asList(allocGroups));
            result = groups.remove(index);
            allocGroups = groups.toArray(new AllocGroup[groups.size()]);
            if (allocGroups.length > 0) {
                noAllocs = new Integer(allocGroups.length);
            } else {
                allocGroups = null;
                noAllocs = null;
            }
        }

        return result;
    }

    @Override
    public int clearAllocGroups() {
        int result = 0;
        if (allocGroups != null && allocGroups.length > 0) {
            result = allocGroups.length;
            allocGroups = null;
            noAllocs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (ORDER_ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noOrders != null && noOrders.intValue() > 0) {
                message.reset();
                orderAllocGroups = new OrderAllocGroup[noOrders.intValue()];
                for (int i = 0; i < noOrders.intValue(); i++) {
                    OrderAllocGroup group = new OrderAllocGroup50(context);
                    group.decode(message);
                    orderAllocGroups[i] = group;
                }
            }
        }
        if (EXEC_ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noExecs != null && noExecs.intValue() > 0) {
                message.reset();
                execAllocGroups = new ExecAllocGroup[noExecs.intValue()];
                for (int i = 0; i < noExecs.intValue(); i++) {
                    ExecAllocGroup group = new ExecAllocGroup50(context);
                    group.decode(message);
                    execAllocGroups[i] = group;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50(context);
            }
            instrument.decode(tag, message);
        }
        if (INSTRUMENT_EXTENSION_COMP_TAGS.contains(tag.tagNum)) {
            if (instrumentExtension == null) {
                instrumentExtension = new InstrumentExtension50(context);
            }
            instrumentExtension.decode(tag, message);
        }
        if (FINANCING_DETAILS_COMP_TAGS.contains(tag.tagNum)) {
            if (financingDetails == null) {
                financingDetails = new FinancingDetails50(context);
            }
            financingDetails.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument component = new UnderlyingInstrument50(context);
                    component.decode(message);
                    underlyingInstruments[i] = component;
                }
            }
        }
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrumentLegs = new InstrumentLeg[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrumentLeg component = new InstrumentLeg50(context);
                    component.decode(message);
                    instrumentLegs[i] = component;
                }
            }
        }
        if (SPREAD_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50(context);
            }
            parties.decode(tag, message);
        }
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations50(context);
            }
            stipulations.decode(tag, message);
        }
        if (YIELD_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (yieldData == null) {
                yieldData = new YieldData50(context);
            }
            yieldData.decode(tag, message);
        }
        if (POS_AMT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noPosAmt != null && noPosAmt.intValue() > 0) {
                message.reset();
                posAmtGroups = new PosAmtGroup[noPosAmt.intValue()];
                for (int i = 0; i < noPosAmt.intValue(); i++) {
                    PosAmtGroup group = new PosAmtGroup50(context);
                    group.decode(message);
                    posAmtGroups[i] = group;
                }
            }
        }
        if (ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAllocs != null && noAllocs.intValue() > 0) {
                message.reset();
                allocGroups = new AllocGroup[noAllocs.intValue()];
                for (int i = 0; i < noAllocs.intValue(); i++) {
                    AllocGroup group = new AllocGroup50(context);
                    group.decode(message);
                    allocGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [AllocationReportMsg] message version [5.0].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
