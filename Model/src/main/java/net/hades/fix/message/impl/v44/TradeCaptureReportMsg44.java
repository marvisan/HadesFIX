/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportMsg44.java
 *
 * $Id: TradeCaptureReportMsg44.java,v 1.2 2011-10-25 08:29:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v44.SpreadOrBenchmarkCurveData44;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.impl.v44.PosAmtGroup44;
import net.hades.fix.message.group.impl.v44.TrdInstrmtLegGroup44;
import net.hades.fix.message.group.impl.v44.TrdRegTimestampsGroup44;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.Header;
import net.hades.fix.message.TradeCaptureReportMsg;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44;
import net.hades.fix.message.comp.impl.v44.Instrument44;
import net.hades.fix.message.comp.impl.v44.OrderQtyData44;
import net.hades.fix.message.comp.impl.v44.YieldData44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.group.TrdCapRptSideGroup;
import net.hades.fix.message.group.TrdInstrmtLegGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.group.impl.v44.TrdCapRptSideGroup44;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.AvgPxIndicator;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.ShortSaleReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TradeReportTransType;
import net.hades.fix.message.type.TradeReportType;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 4.4 TradeCaptureReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="TrdCaptRpt")
@XmlType(propOrder = {"header", "instrument", "financingDetails", "orderQtyData", "yieldData", "underlyingInstruments", 
    "spreadOrBenchmarkCurveData", "posAmtGroups", "trdInstrmtLegGroups", "trdRegTimestampsGroups", "trdCapRptSideGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class TradeCaptureReportMsg44 extends TradeCaptureReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V44 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TradeReportID.getValue(),
        TagNum.TradeReportTransType.getValue(),
        TagNum.TradeReportType.getValue(),
        TagNum.TradeRequestID.getValue(),
        TagNum.TrdType.getValue(),
        TagNum.TrdSubType.getValue(),
        TagNum.SecondaryTrdType.getValue(),
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
        TagNum.OrdStatus.getValue(),
        TagNum.SecondaryExecID.getValue(),
        TagNum.ExecRestatementReason.getValue(),
        TagNum.PreviouslyReported.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.UnderlyingTradingSessionID.getValue(),
        TagNum.UnderlyingTradingSessionSubID.getValue(),
        TagNum.LastQty.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.LastSpotRate.getValue(),
        TagNum.LastForwardPoints.getValue(),
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
        TagNum.MatchStatus.getValue(),
        TagNum.MatchType.getValue(),
        TagNum.NoSides.getValue(),
        TagNum.CopyMsgIndicator.getValue(),
        TagNum.PublishTrdIndicator.getValue(),
        TagNum.ShortSaleReason.getValue()
    }));

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument44().getFragmentAllTags();
    protected static final Set<Integer> FINANCING_DETAILS_COMP_TAGS = new FinancingDetails44().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData44().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData44().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument44().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData44().getFragmentAllTags();
    protected static final Set<Integer> POS_AMT_GROUP_TAGS = new PosAmtGroup44().getFragmentAllTags();
    protected static final Set<Integer> TRD_INSTRMT_LEG__GROUP_TAGS = new TrdInstrmtLegGroup44().getFragmentAllTags();
    protected static final Set<Integer> TRD_REG_TSTAMP_GROUP_TAGS = new TrdRegTimestampsGroup44().getFragmentAllTags();
    protected static final Set<Integer> TRD_CAP_RPT_SIDE_GROUP_TAGS = new TrdCapRptSideGroup44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V44);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        ALL_TAGS.addAll(POS_AMT_GROUP_TAGS);
        ALL_TAGS.addAll(TRD_INSTRMT_LEG__GROUP_TAGS);
        ALL_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        ALL_TAGS.addAll(TRD_CAP_RPT_SIDE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS.addAll(POS_AMT_GROUP_TAGS);
        START_COMP_TAGS.addAll(TRD_INSTRMT_LEG__GROUP_TAGS);
        START_COMP_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        START_COMP_TAGS.addAll(TRD_CAP_RPT_SIDE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V44;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeCaptureReportMsg44() {
        super();
    }

    public TradeCaptureReportMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradeCaptureReportMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradeCaptureReportMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V44;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        TradeCaptureReportMsg44 fixml = (TradeCaptureReportMsg44) fragment;
        if (fixml.getTradeReportID() != null) {
            tradeReportID = fixml.getTradeReportID();
        }
        if (fixml.getTradeReportTransType() != null) {
            tradeReportTransType = fixml.getTradeReportTransType();
        }
        if (fixml.getTradeReportType() != null) {
            tradeReportType = fixml.getTradeReportType();
        }
        if (fixml.getTradeRequestID() != null) {
            tradeRequestID = fixml.getTradeRequestID();
        }
        if (fixml.getTrdType() != null) {
            trdType = fixml.getTrdType();
        }
        if (fixml.getTrdSubType() != null) {
            trdSubType = fixml.getTrdSubType();
        }
        if (fixml.getSecondaryTrdType() != null) {
            secondaryTrdType = fixml.getSecondaryTrdType();
        }
        if (fixml.getTransferReason() != null) {
            transferReason = fixml.getTransferReason();
        }
        if (fixml.getExecType() != null) {
            execType = fixml.getExecType();
        }
        if (fixml.getTotNumTradeReports() != null) {
            totNumTradeReports = fixml.getTotNumTradeReports();
        }
        if (fixml.getLastRptRequested() != null) {
            lastRptRequested = fixml.getLastRptRequested();
        }
        if (fixml.getUnsolicitedIndicator() != null) {
            unsolicitedIndicator = fixml.getUnsolicitedIndicator();
        }
        if (fixml.getSubscriptionRequestType() != null) {
            subscriptionRequestType = fixml.getSubscriptionRequestType();
        }
        if (fixml.getTradeReportRefID() != null) {
            tradeReportRefID = fixml.getTradeReportRefID();
        }
        if (fixml.getSecondaryTradeReportRefID() != null) {
            secondaryTradeReportRefID = fixml.getSecondaryTradeReportRefID();
        }
        if (fixml.getSecondaryTradeReportID() != null) {
            secondaryTradeReportID = fixml.getSecondaryTradeReportID();
        }
        if (fixml.getTradeLinkID() != null) {
            tradeLinkID = fixml.getTradeLinkID();
        }
        if (fixml.getTrdMatchID() != null) {
            trdMatchID = fixml.getTrdMatchID();
        }
        if (fixml.getExecID() != null) {
            execID = fixml.getExecID();
        }
        if (fixml.getOrdStatus() != null) {
            ordStatus = fixml.getOrdStatus();
        }
        if (fixml.getSecondaryExecID() != null) {
            secondaryExecID = fixml.getSecondaryExecID();
        }
        if (fixml.getExecRestatementReason() != null) {
            execRestatementReason = fixml.getExecRestatementReason();
        }
        if (fixml.getPreviouslyReported() != null) {
            previouslyReported = fixml.getPreviouslyReported();
        }
        if (fixml.getPriceType() != null) {
            priceType = fixml.getPriceType();
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getFinancingDetails() != null) {
            setFinancingDetails(fixml.getFinancingDetails());
        }
        if (fixml.getOrderQtyData() != null) {
            setOrderQtyData(fixml.getOrderQtyData());
        }
        if (fixml.getQtyType() != null) {
            qtyType = fixml.getQtyType();
        }
        if (fixml.getYieldData() != null) {
            setYieldData(fixml.getYieldData());
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getUnderlyingTradingSessionID() != null) {
            underlyingTradingSessionID = fixml.getUnderlyingTradingSessionID();
        }
        if (fixml.getUnderlyingTradingSessionSubID() != null) {
            underlyingTradingSessionSubID = fixml.getUnderlyingTradingSessionSubID();
        }
        if (fixml.getLastQty() != null) {
            lastQty = fixml.getLastQty();
        }
        if (fixml.getLastPx() != null) {
            lastPx = fixml.getLastPx();
        }
        if (fixml.getLastSpotRate() != null) {
            lastSpotRate = fixml.getLastSpotRate();
        }
        if (fixml.getLastForwardPoints() != null) {
            lastForwardPoints = fixml.getLastForwardPoints();
        }
        if (fixml.getLastMkt() != null) {
            lastMkt = fixml.getLastMkt();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getClearingBusinessDate() != null) {
            clearingBusinessDate = fixml.getClearingBusinessDate();
        }
        if (fixml.getAvgPx() != null) {
            avgPx = fixml.getAvgPx();
        }
        if (fixml.getSpreadOrBenchmarkCurveData() != null) {
            setSpreadOrBenchmarkCurveData(fixml.getSpreadOrBenchmarkCurveData());
        }
        if (fixml.getAvgPxIndicator() != null) {
            avgPxIndicator = fixml.getAvgPxIndicator();
        }
        if (fixml.getPosAmtGroups() != null && fixml.getPosAmtGroups().length > 0) {
            setPosAmtGroups(fixml.getPosAmtGroups());
        }
        if (fixml.getMultilegReportingType() != null) {
            multilegReportingType = fixml.getMultilegReportingType();
        }
        if (fixml.getTradeLegRefID() != null) {
            tradeLegRefID = fixml.getTradeLegRefID();
        }
        if (fixml.getTrdInstrmtLegGroups() != null && fixml.getTrdInstrmtLegGroups().length > 0) {
            setTrdInstrmtLegGroups(fixml.getTrdInstrmtLegGroups());
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getTrdRegTimestampsGroups() != null && fixml.getTrdRegTimestampsGroups().length > 0) {
            setTrdRegTimestampsGroups(fixml.getTrdRegTimestampsGroups());
        }
        if (fixml.getSettlType() != null) {
            settlType = fixml.getSettlType();
        }
        if (fixml.getSettlDate() != null) {
            settlDate = fixml.getSettlDate();
        }
        if (fixml.getMatchStatus() != null) {
            matchStatus = fixml.getMatchStatus();
        }
        if (fixml.getMatchType() != null) {
            matchType = fixml.getMatchType();
        }
        if (fixml.getTrdCapRptSideGroups() != null && fixml.getTrdCapRptSideGroups().length > 0) {
            setTrdCapRptSideGroups(fixml.getTrdCapRptSideGroups());
        }
        if (fixml.getCopyMsgIndicator() != null) {
            copyMsgIndicator = fixml.getCopyMsgIndicator();
        }
        if (fixml.getPublishTrdIndicator() != null) {
            publishTrdIndicator = fixml.getPublishTrdIndicator();
        }
        if (fixml.getShortSaleReason() != null) {
            shortSaleReason = fixml.getShortSaleReason();
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
    public String getTradeReportID() {
        return tradeReportID;
    }

    @Override
    public void setTradeReportID(String tradeReportID) {
        this.tradeReportID = tradeReportID;
    }

    @XmlAttribute(name = "TransTyp")
    @Override
    public TradeReportTransType getTradeReportTransType() {
        return tradeReportTransType;
    }

    @Override
    public void setTradeReportTransType(TradeReportTransType tradeReportTransType) {
        this.tradeReportTransType = tradeReportTransType;
    }

    @XmlAttribute(name = "RptTyp")
    @Override
    public TradeReportType getTradeReportType() {
        return tradeReportType;
    }

    @Override
    public void setTradeReportType(TradeReportType tradeReportType) {
        this.tradeReportType = tradeReportType;
    }
           
    @XmlAttribute(name = "ReqID")
    @Override
    public String getTradeRequestID() {
        return tradeRequestID;
    }

    @Override
    public void setTradeRequestID(String tradeRequestID) {
        this.tradeRequestID = tradeRequestID;
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

    @XmlAttribute(name = "TrdTyp2")
    @Override
    public SecondaryTrdType getSecondaryTrdType() {
        return secondaryTrdType;
    }

    @Override
    public void setSecondaryTrdType(SecondaryTrdType secondaryTrdType) {
        this.secondaryTrdType = secondaryTrdType;
    }

    @XmlAttribute(name = "TrnsfrRsn")
    @Override
    public String getTransferReason() {
        return transferReason;
    }

    @Override
    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    @XmlAttribute(name = "ExecTyp")
    @Override
    public ExecType getExecType() {
        return execType;
    }

    @Override
    public void setExecType(ExecType execType) {
        this.execType = execType;
    }

    @XmlAttribute(name = "TotNumTrdRpts")
    @Override
    public Integer getTotNumTradeReports() {
        return totNumTradeReports;
    }

    @Override
    public void setTotNumTradeReports(Integer totNumTradeReports) {
        this.totNumTradeReports = totNumTradeReports;
    }

    @XmlAttribute(name = "LastRptReqed")
    @Override
    public Boolean getLastRptRequested() {
        return lastRptRequested;
    }

    @Override
    public void setLastRptRequested(Boolean lastRptRequested) {
        this.lastRptRequested = lastRptRequested;
    }

    @XmlAttribute(name = "Unsol")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getUnsolicitedIndicator() {
        return unsolicitedIndicator;
    }

    @Override
    public void setUnsolicitedIndicator(Boolean unsolicitedIndicator) {
        this.unsolicitedIndicator = unsolicitedIndicator;
    }

    @XmlAttribute(name = "SubReqTyp")
    @Override
    public SubscriptionRequestType getSubscriptionRequestType() {
        return subscriptionRequestType;
    }

    @Override
    public void setSubscriptionRequestType(SubscriptionRequestType subscriptionRequestType) {
        this.subscriptionRequestType = subscriptionRequestType;
    }

    @XmlAttribute(name = "RptRefID")
    @Override
    public String getTradeReportRefID() {
        return tradeReportRefID;
    }

    @Override
    public void setTradeReportRefID(String tradeReportRefID) {
        this.tradeReportRefID = tradeReportRefID;
    }

    @XmlAttribute(name = "RptRefID2")
    @Override
    public String getSecondaryTradeReportRefID() {
        return secondaryTradeReportRefID;
    }

    @Override
    public void setSecondaryTradeReportRefID(String secondaryTradeReportRefID) {
        this.secondaryTradeReportRefID = secondaryTradeReportRefID;
    }

    @XmlAttribute(name = "RptID2")
    @Override
    public String getSecondaryTradeReportID() {
        return secondaryTradeReportID;
    }

    @Override
    public void setSecondaryTradeReportID(String secondaryTradeReportID) {
        this.secondaryTradeReportID = secondaryTradeReportID;
    }

    @XmlAttribute(name = "LinkID")
    @Override
    public String getTradeLinkID() {
        return tradeLinkID;
    }

    @Override
    public void setTradeLinkID(String tradeLinkID) {
        this.tradeLinkID = tradeLinkID;
    }

    @XmlAttribute(name = "TrdMtchID")
    @Override
    public String getTrdMatchID() {
        return trdMatchID;
    }

    @Override
    public void setTrdMatchID(String trdMatchID) {
        this.trdMatchID = trdMatchID;
    }

    @XmlAttribute(name = "ExecID")
    @Override
    public String getExecID() {
        return execID;
    }

    @Override
    public void setExecID(String execID) {
        this.execID = execID;
    }

    @XmlAttribute(name = "OrdStat")
    @Override
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    @Override
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    @XmlAttribute(name = "ExecID2")
    @Override
    public String getSecondaryExecID() {
        return secondaryExecID;
    }

    @Override
    public void setSecondaryExecID(String secondaryExecID) {
        this.secondaryExecID = secondaryExecID;
    }

    @XmlAttribute(name = "ExecRstmtRsn")
    @Override
    public ExecRestatementReason getExecRestatementReason() {
        return execRestatementReason;
    }

    @Override
    public void setExecRestatementReason(ExecRestatementReason execRestatementReason) {
        this.execRestatementReason = execRestatementReason;
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

    @XmlAttribute(name = "PxTyp")
    @Override
    public PriceType getPriceType() {
        return priceType;
    }

    @Override
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument44(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @XmlElementRef
    @Override
    public FinancingDetails getFinancingDetails() {
        return financingDetails;
    }

    @Override
    public void setFinancingDetails() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.financingDetails = new FinancingDetails44(context);
    }

    public void setFinancingDetails(FinancingDetails financingDetails) {
        this.financingDetails = financingDetails;
    }

    @Override
    public void clearFinancingDetails() {
        this.financingDetails = null;
    }

    @XmlElementRef
    @Override
    public OrderQtyData getOrderQtyData() {
        return orderQtyData;
    }

    @Override
    public void setOrderQtyData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.orderQtyData = new OrderQtyData44(context);
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
    }

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
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

    @XmlElementRef
    @Override
    public YieldData getYieldData() {
        return yieldData;
    }

    @Override
    public void setYieldData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.yieldData = new YieldData44(context);
    }

    @Override
    public void clearYieldData() {
        this.yieldData = null;
    }

    public void setYieldData(YieldData yieldData) {
        this.yieldData = yieldData;
    }

    @Override
    public void setNoUnderlyings(Integer noUnderlyings) {
        this.noUnderlyings = noUnderlyings;
        if (noUnderlyings != null) {
            underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < underlyingInstruments.length; i++) {
                underlyingInstruments[i] = new UnderlyingInstrument44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        return underlyingInstruments;
    }

    @Override
    public Integer getNoUnderlyings() {
        return noUnderlyings;
    }
    
    public void setUnderlyingInstruments(UnderlyingInstrument[] underlyingInstruments) {
        this.underlyingInstruments = underlyingInstruments;
        if (underlyingInstruments != null) {
            noUnderlyings = new Integer(underlyingInstruments.length);
        }
    }

    @Override
    public UnderlyingInstrument addUnderlyingInstrument() {
        UnderlyingInstrument group = new UnderlyingInstrument44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    @XmlAttribute(name = "UndSesID")
    @Override
    public String getUnderlyingTradingSessionID() {
        return underlyingTradingSessionID;
    }

    @Override
    public void setUnderlyingTradingSessionID(String underlyingTradingSessionID) {
        this.underlyingTradingSessionID = underlyingTradingSessionID;
    }

    @XmlAttribute(name = "UndSesSub")
    @Override
    public String getUnderlyingTradingSessionSubID() {
        return underlyingTradingSessionSubID;
    }

    @Override
    public void setUnderlyingTradingSessionSubID(String underlyingTradingSessionSubID) {
        this.underlyingTradingSessionSubID = underlyingTradingSessionSubID;
    }

    @XmlAttribute(name = "LastQty")
    @Override
    public Double getLastQty() {
        return lastQty;
    }

    @Override
    public void setLastQty(Double lastQty) {
        this.lastQty = lastQty;
    }

    @XmlAttribute(name = "LastPx")
    @Override
    public Double getLastPx() {
        return lastPx;
    }

    @Override
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    @XmlAttribute(name = "LastSpotRt")
    @Override
    public Double getLastSpotRate() {
        return lastSpotRate;
    }

    @Override
    public void setLastSpotRate(Double lastSpotRate) {
        this.lastSpotRate = lastSpotRate;
    }

    @XmlAttribute(name = "LastFwdPnts")
    @Override
    public Double getLastForwardPoints() {
        return lastForwardPoints;
    }

    @Override
    public void setLastForwardPoints(Double lastForwardPoints) {
        this.lastForwardPoints = lastForwardPoints;
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

    @XmlAttribute(name = "AvgPx")
    @Override
    public Double getAvgPx() {
        return avgPx;
    }

    @Override
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
    }

    @XmlElementRef
    @Override
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData44(context);
    }

    @Override
    public void clearSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = null;
    }

    public void setSpreadOrBenchmarkCurveData(SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData) {
        this.spreadOrBenchmarkCurveData = spreadOrBenchmarkCurveData;
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
                posAmtGroups[i] = new PosAmtGroup44(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        PosAmtGroup group = new PosAmtGroup44(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "MLEGRptTyp")
    @Override
    public MultiLegReportingType getMultilegReportingType() {
        return multilegReportingType;
    }

    @Override
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        this.multilegReportingType = multilegReportingType;
    }

    @XmlAttribute(name = "TrdLegRefID")
    @Override
    public String getTradeLegRefID() {
        return tradeLegRefID;
    }

    @Override
    public void setTradeLegRefID(String tradeLegRefID) {
        this.tradeLegRefID = tradeLegRefID;
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            trdInstrmtLegGroups = new TrdInstrmtLegGroup[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < trdInstrmtLegGroups.length; i++) {
                trdInstrmtLegGroups[i] = new TrdInstrmtLegGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public TrdInstrmtLegGroup[] getTrdInstrmtLegGroups() {
        return trdInstrmtLegGroups;
    }

    public void setTrdInstrmtLegGroups(TrdInstrmtLegGroup[] instrumentLegs) {
        this.trdInstrmtLegGroups = instrumentLegs;
        if (instrumentLegs != null) {
            noLegs = new Integer(instrumentLegs.length);
        }
    }

    @Override
    public TrdInstrmtLegGroup addTrdInstrmtLegGroup() {
        TrdInstrmtLegGroup group = new TrdInstrmtLegGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<TrdInstrmtLegGroup> groups = new ArrayList<TrdInstrmtLegGroup>();
        if (trdInstrmtLegGroups != null && trdInstrmtLegGroups.length > 0) {
            groups = new ArrayList<TrdInstrmtLegGroup>(Arrays.asList(trdInstrmtLegGroups));
        }
        groups.add(group);
        trdInstrmtLegGroups = groups.toArray(new TrdInstrmtLegGroup[groups.size()]);
        noLegs = new Integer(trdInstrmtLegGroups.length);

        return group;
    }

    @Override
    public TrdInstrmtLegGroup deleteTrdInstrmtLegGroup(int index) {
        TrdInstrmtLegGroup result = null;
        if (trdInstrmtLegGroups != null && trdInstrmtLegGroups.length > 0 && trdInstrmtLegGroups.length > index) {
            List<TrdInstrmtLegGroup> groups = new ArrayList<TrdInstrmtLegGroup>(Arrays.asList(trdInstrmtLegGroups));
            result = groups.remove(index);
            trdInstrmtLegGroups = groups.toArray(new TrdInstrmtLegGroup[groups.size()]);
            if (trdInstrmtLegGroups.length > 0) {
                noLegs = new Integer(trdInstrmtLegGroups.length);
            } else {
                trdInstrmtLegGroups = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearTrdInstrmtLegGroups() {
        int result = 0;
        if (trdInstrmtLegGroups != null && trdInstrmtLegGroups.length > 0) {
            result = trdInstrmtLegGroups.length;
            trdInstrmtLegGroups = null;
            noLegs = null;
        }

        return result;
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

    @Override
    public Integer getNoTrdRegTimestamps() {
        return noTrdRegTimestamps;
    }

    @Override
    public void setNoTrdRegTimestamps(Integer noTrdRegTimestamps) {
        this.noTrdRegTimestamps = noTrdRegTimestamps;
        if (noTrdRegTimestamps != null) {
            trdRegTimestampsGroups = new TrdRegTimestampsGroup[noTrdRegTimestamps.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < trdRegTimestampsGroups.length; i++) {
                trdRegTimestampsGroups[i] = new TrdRegTimestampsGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public TrdRegTimestampsGroup[] getTrdRegTimestampsGroups() {
        return trdRegTimestampsGroups;
    }

    public void setTrdRegTimestampsGroups(TrdRegTimestampsGroup[] trdRegTimestampsGroups) {
        this.trdRegTimestampsGroups = trdRegTimestampsGroups;
        if (trdRegTimestampsGroups != null) {
            noTrdRegTimestamps = new Integer(trdRegTimestampsGroups.length);
        }
    }

    @Override
    public TrdRegTimestampsGroup addTrdRegTimestampsGroup() {
        TrdRegTimestampsGroup group = new TrdRegTimestampsGroup44(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TrdRegTimestampsGroup> groups = new ArrayList<TrdRegTimestampsGroup>();
        if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length > 0) {
            groups = new ArrayList<TrdRegTimestampsGroup>(Arrays.asList(trdRegTimestampsGroups));
        }
        groups.add(group);
        trdRegTimestampsGroups = groups.toArray(new TrdRegTimestampsGroup[groups.size()]);
        noTrdRegTimestamps = new Integer(trdRegTimestampsGroups.length);

        return group;
    }

    @Override
    public TrdRegTimestampsGroup deleteTrdRegTimestampsGroup(int index) {
        TrdRegTimestampsGroup result = null;
        if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length > 0 && trdRegTimestampsGroups.length > index) {
            List<TrdRegTimestampsGroup> groups = new ArrayList<TrdRegTimestampsGroup>(Arrays.asList(trdRegTimestampsGroups));
            result = groups.remove(index);
            trdRegTimestampsGroups = groups.toArray(new TrdRegTimestampsGroup[groups.size()]);
            if (trdRegTimestampsGroups.length > 0) {
                noTrdRegTimestamps = new Integer(trdRegTimestampsGroups.length);
            } else {
                trdRegTimestampsGroups = null;
                noTrdRegTimestamps = null;
            }
        }

        return result;
    }

    @Override
    public int clearTrdRegTimestampsGroups() {
        int result = 0;
        if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length > 0) {
            result = trdRegTimestampsGroups.length;
            trdRegTimestampsGroups = null;
            noTrdRegTimestamps = null;
        }

        return result;
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

    @XmlAttribute(name = "MtchStat")
    @Override
    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    @Override
    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
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

    @Override
    public Integer getNoSides() {
        return noSides;
    }

    @Override
    public void setNoSides(Integer noSides) {
        this.noSides = noSides;
        if (noSides != null) {
            trdCapRptSideGroups = new TrdCapRptSideGroup[noSides.intValue()];
            for (int i = 0; i < trdCapRptSideGroups.length; i++) {
                trdCapRptSideGroups[i] = new TrdCapRptSideGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public TrdCapRptSideGroup[] getTrdCapRptSideGroups() {
        return trdCapRptSideGroups;
    }

    public void setTrdCapRptSideGroups(TrdCapRptSideGroup[] sideCrossOrdModGroups) {
        this.trdCapRptSideGroups = sideCrossOrdModGroups;
        if (sideCrossOrdModGroups != null) {
            noSides = new Integer(sideCrossOrdModGroups.length);
        }
    }

    @Override
    public TrdCapRptSideGroup addTrdCapRptSideGroup() {
        TrdCapRptSideGroup group = new TrdCapRptSideGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<TrdCapRptSideGroup> groups = new ArrayList<TrdCapRptSideGroup>();
        if (trdCapRptSideGroups != null && trdCapRptSideGroups.length > 0) {
            groups = new ArrayList<TrdCapRptSideGroup>(Arrays.asList(trdCapRptSideGroups));
        }
        groups.add(group);
        trdCapRptSideGroups = groups.toArray(new TrdCapRptSideGroup[groups.size()]);
        noSides = new Integer(trdCapRptSideGroups.length);

        return group;
    }

    @Override
    public TrdCapRptSideGroup deleteTrdCapRptSideGroup(int index) {
        TrdCapRptSideGroup result = null;
        if (trdCapRptSideGroups != null && trdCapRptSideGroups.length > 0 && trdCapRptSideGroups.length > index) {
            List<TrdCapRptSideGroup> groups = new ArrayList<TrdCapRptSideGroup>(Arrays.asList(trdCapRptSideGroups));
            result = groups.remove(index);
            trdCapRptSideGroups = groups.toArray(new TrdCapRptSideGroup[groups.size()]);
            if (trdCapRptSideGroups.length > 0) {
                noSides = new Integer(trdCapRptSideGroups.length);
            } else {
                trdCapRptSideGroups = null;
                noSides = null;
            }
        }

        return result;
    }

    @Override
    public int clearTrdCapRptSideGroups() {
        int result = 0;
        if (trdCapRptSideGroups != null && trdCapRptSideGroups.length > 0) {
            result = trdCapRptSideGroups.length;
            trdCapRptSideGroups = null;
            noSides = null;
        }

        return result;
    }

    @XmlAttribute(name = "CopyMsgInd")
    @Override
    public Boolean getCopyMsgIndicator() {
        return copyMsgIndicator;
    }

    @Override
    public void setCopyMsgIndicator(Boolean copyMsgIndicator) {
        this.copyMsgIndicator = copyMsgIndicator;
    }

    @XmlAttribute(name = "PubTrdInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPublishTrdIndicator() {
        return publishTrdIndicator;
    }

    @Override
    public void setPublishTrdIndicator(Boolean publishTrdIndicator) {
        this.publishTrdIndicator = publishTrdIndicator;
    }

    @XmlAttribute(name = "ShrtSaleRsn")
    @Override
    public ShortSaleReason getShortSaleReason() {
        return shortSaleReason;
    }

    @Override
    public void setShortSaleReason(ShortSaleReason shortSaleReason) {
        this.shortSaleReason = shortSaleReason;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (tradeReportID == null || tradeReportID.trim().isEmpty()) {
            errorMsg.append(" [TradeReportID]");
            hasMissingTag = true;
        }
        if (previouslyReported == null) {
            errorMsg.append(" [PreviouslyReported]");
            hasMissingTag = true;
        }
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
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.TradeReportID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportID, tradeReportID);
            }
            if (tradeReportTransType != null && MsgUtil.isTagInList(TagNum.TradeReportTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportTransType, tradeReportTransType.getValue());
            }
            if (tradeReportType != null && MsgUtil.isTagInList(TagNum.TradeReportType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportType, tradeReportType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeRequestID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeRequestID, tradeRequestID);
            }
            if (trdType != null && MsgUtil.isTagInList(TagNum.TrdType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TrdType, trdType.getValue());
            }
            if (trdSubType != null && MsgUtil.isTagInList(TagNum.TrdSubType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TrdSubType, trdSubType.getValue());
            }
            if (secondaryTrdType != null && MsgUtil.isTagInList(TagNum.SecondaryTrdType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryTrdType, secondaryTrdType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TransferReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TransferReason, transferReason);
            }
            if (execType != null && MsgUtil.isTagInList(TagNum.ExecType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecType, execType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TotNumTradeReports, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNumTradeReports, totNumTradeReports);
            }
            if (MsgUtil.isTagInList(TagNum.LastRptRequested, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastRptRequested, lastRptRequested);
            }
            if (MsgUtil.isTagInList(TagNum.UnsolicitedIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
            }
            if (subscriptionRequestType != null && MsgUtil.isTagInList(TagNum.SubscriptionRequestType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeReportRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportRefID, tradeReportRefID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryTradeReportRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryTradeReportRefID, secondaryTradeReportRefID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryTradeReportID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryTradeReportID, secondaryTradeReportID);
            }
            if (MsgUtil.isTagInList(TagNum.TradeLinkID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeLinkID, tradeLinkID);
            }
            if (MsgUtil.isTagInList(TagNum.TrdMatchID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TrdMatchID, trdMatchID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecID, execID);
            }
            if (ordStatus != null && MsgUtil.isTagInList(TagNum.OrdStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryExecID, secondaryExecID);
            }
            if (execRestatementReason != null && MsgUtil.isTagInList(TagNum.ExecRestatementReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecRestatementReason, execRestatementReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PreviouslyReported, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PreviouslyReported, previouslyReported);
            }
            if (priceType != null && MsgUtil.isTagInList(TagNum.PriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (financingDetails != null) {
                bao.write(financingDetails.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (qtyType != null && MsgUtil.isTagInList(TagNum.QtyType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QtyType, qtyType.getValue());
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (noUnderlyings != null && MsgUtil.isTagInList(TagNum.NoUnderlyings, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noUnderlyings.intValue()) {
                    for (int i = 0; i < noUnderlyings.intValue(); i++) {
                        if (underlyingInstruments[i] != null) {
                            bao.write(underlyingInstruments[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "UnderlyingInstrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingTradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingTradingSessionID, underlyingTradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingTradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingTradingSessionSubID, underlyingTradingSessionSubID);
            }
            if (MsgUtil.isTagInList(TagNum.LastQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastQty, lastQty);
            }
            if (MsgUtil.isTagInList(TagNum.LastPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            }
            if (MsgUtil.isTagInList(TagNum.LastParPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastParPx, lastParPx);
            }
            if (MsgUtil.isTagInList(TagNum.LastSpotRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastSpotRate, lastSpotRate);
            }
            if (MsgUtil.isTagInList(TagNum.LastForwardPoints, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastForwardPoints, lastForwardPoints);
            }
            if (MsgUtil.isTagInList(TagNum.LastMkt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeDate, tradeDate);
            }
            if (MsgUtil.isTagInList(TagNum.ClearingBusinessDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            }
            if (MsgUtil.isTagInList(TagNum.AvgPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            }
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (avgPxIndicator != null && MsgUtil.isTagInList(TagNum.AvgPxIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AvgPxIndicator, avgPxIndicator.getValue());
            }
            if (noPosAmt != null && MsgUtil.isTagInList(TagNum.NoPosAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoPosAmt, noPosAmt);
                if (posAmtGroups != null && posAmtGroups.length == noPosAmt.intValue()) {
                    for (int i = 0; i < noPosAmt.intValue(); i++) {
                        if (posAmtGroups[i] != null) {
                            bao.write(posAmtGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "PosAmtGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoPosAmt.getValue(), error);
                }
            }
            if (multilegReportingType != null && MsgUtil.isTagInList(TagNum.MultiLegReportingType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MultiLegReportingType, multilegReportingType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeLegRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeLegRefID, tradeLegRefID);
            }
            if (noLegs != null && MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (trdInstrmtLegGroups != null && trdInstrmtLegGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (trdInstrmtLegGroups[i] != null) {
                            bao.write(trdInstrmtLegGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "TrdInstrmtLegGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (noTrdRegTimestamps != null && MsgUtil.isTagInList(TagNum.NoTrdRegTimestamps, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoTrdRegTimestamps, noTrdRegTimestamps);
                if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length == noTrdRegTimestamps.intValue()) {
                    for (int i = 0; i < noTrdRegTimestamps.intValue(); i++) {
                        if (trdRegTimestampsGroups[i] != null) {
                            bao.write(trdRegTimestampsGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "TrdRegTimestampsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTrdRegTimestamps.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.SettlType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlType, settlType);
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            }
            if (matchStatus != null && MsgUtil.isTagInList(TagNum.MatchStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            if (matchType != null && MsgUtil.isTagInList(TagNum.MatchType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MatchType, matchType.getValue());
            }
            if (noSides != null && MsgUtil.isTagInList(TagNum.NoSides, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoSides, noSides);
                if (trdCapRptSideGroups != null && trdCapRptSideGroups.length == noSides.intValue()) {
                    for (int i = 0; i < noSides.intValue(); i++) {
                        if (trdCapRptSideGroups[i] != null) {
                            bao.write(trdCapRptSideGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "TrdCapRptSideGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSides.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.CopyMsgIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CopyMsgIndicator, copyMsgIndicator);
            }
            if (MsgUtil.isTagInList(TagNum.PublishTrdIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PublishTrdIndicator, publishTrdIndicator);
            }
            if (shortSaleReason != null && MsgUtil.isTagInList(TagNum.ShortSaleReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ShortSaleReason, shortSaleReason.getValue());
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument44(context);
            }
            instrument.decode(tag, message);
        }
        if (FINANCING_DETAILS_COMP_TAGS.contains(tag.tagNum)) {
            if (financingDetails == null) {
                financingDetails = new FinancingDetails44(context);
            }
            financingDetails.decode(tag, message);
        }
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData44(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (YIELD_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (yieldData == null) {
                yieldData = new YieldData44(context);
            }
            yieldData.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument component = new UnderlyingInstrument44(context);
                    component.decode(message);
                    underlyingInstruments[i] = component;
                }
            }
        }
        if (SPREAD_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData44(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
        if (POS_AMT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noPosAmt != null && noPosAmt.intValue() > 0) {
                message.reset();
                posAmtGroups = new PosAmtGroup[noPosAmt.intValue()];
                for (int i = 0; i < noPosAmt.intValue(); i++) {
                    PosAmtGroup group = new PosAmtGroup44(context);
                    group.decode(message);
                    posAmtGroups[i] = group;
                }
            }
        }
        if (TRD_INSTRMT_LEG__GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                trdInstrmtLegGroups = new TrdInstrmtLegGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    TrdInstrmtLegGroup group = new TrdInstrmtLegGroup44(context);
                    group.decode(message);
                    trdInstrmtLegGroups[i] = group;
                }
            }
        }
        if (TRD_REG_TSTAMP_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTrdRegTimestamps != null && noTrdRegTimestamps.intValue() > 0) {
                message.reset();
                trdRegTimestampsGroups = new TrdRegTimestampsGroup[noTrdRegTimestamps.intValue()];
                for (int i = 0; i < noTrdRegTimestamps.intValue(); i++) {
                    TrdRegTimestampsGroup group = new TrdRegTimestampsGroup44(context);
                    group.decode(message);
                    trdRegTimestampsGroups[i] = group;
                }
            }
        }
        if (TRD_CAP_RPT_SIDE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSides != null && noSides.intValue() > 0) {
                message.reset();
                trdCapRptSideGroups = new TrdCapRptSideGroup[noSides.intValue()];
                for (int i = 0; i < noSides.intValue(); i++) {
                    TrdCapRptSideGroup component = new TrdCapRptSideGroup44(context);
                    component.decode(message);
                    trdCapRptSideGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradeCaptureReportMsg] message version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
