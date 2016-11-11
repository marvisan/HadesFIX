/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportMsg50SP2.java
 *
 * $Id: ExecutionReportMsg50SP2.java,v 1.3 2011-04-14 23:44:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.ExecutionReportMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.impl.v50sp2.FinancingDetails50SP2;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2;
import net.hades.fix.message.comp.impl.v50sp2.PegInstructions50SP2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.FillExecGroup;
import net.hades.fix.message.group.InstrmtLegExecGroup;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.group.StrategyParametersGroup;
import net.hades.fix.message.group.impl.v50sp2.FillExecGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.InstrmtLegExecGroup50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.DiscretionInstructions;
import net.hades.fix.message.comp.DisplayInstruction;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.PegInstructions;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.TriggeringInstruction;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v50sp2.ApplicationSequenceControl50SP2;
import net.hades.fix.message.comp.impl.v50sp2.CommissionData50SP2;
import net.hades.fix.message.comp.impl.v50sp2.DiscretionInstructions50SP2;
import net.hades.fix.message.comp.impl.v50sp2.DisplayInstruction50SP2;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2;
import net.hades.fix.message.comp.impl.v50sp2.OrderQtyData50SP2;
import net.hades.fix.message.comp.impl.v50sp2.SpreadOrBenchmarkCurveData50SP2;
import net.hades.fix.message.comp.impl.v50sp2.Stipulations50SP2;
import net.hades.fix.message.comp.impl.v50sp2.TriggeringInstruction50SP2;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2;
import net.hades.fix.message.comp.impl.v50sp2.YieldData50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ContAmtGroup;
import net.hades.fix.message.group.ContraBrokerGroup;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.PreTradeAllocGroup;
import net.hades.fix.message.group.RateSourceGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.group.impl.v50sp2.ContAmtGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.ContraBrokerGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.MiscFeeGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.PreTradeAllocGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.RateSourceGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.StrategyParametersGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.TrdRegTimestampsGroup50SP2;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.ExecPriceType;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.LastCapacity;
import net.hades.fix.message.type.LastLiquidityInd;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdRejReason;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrderCategory;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.PriorityIndicator;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0SP2 ExecutionReport implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="ExecRpt")
@XmlType(propOrder = {"header", "applicationSequenceControl", "partyIDGroups", "contraBrokers", "allocGroups", "instrument", "financingDetails",
    "underlyingInstruments", "stipulationsGroups", "orderQtyData", "triggeringInstruction", "pegInstructions", "discretionInstructions",
    "strategyParametersGroups", "fillExecGroups", "commissionData", "spreadOrBenchmarkCurveData", "yieldData", "rateSources", "displayInstruction",
    "contAmtGroups", "instrmtLegExecGroups", "miscFeeGroups", "trdRegTimestampsGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class ExecutionReportMsg50SP2 extends ExecutionReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> APPL_SEQ_CONTROL_COMP_TAGS = new ApplicationSequenceControl50SP2().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP2().getFragmentAllTags();
    protected static final Set<Integer> CONTRA_BROKER_GROUP_TAGS = new ContraBrokerGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> ALLOC_GROUP_TAGS = new PreTradeAllocGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> FINANCING_DETAILS_COMP_TAGS = new FinancingDetails50SP2().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRIGG_INSTR_COMP_TAGS = new TriggeringInstruction50SP2().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations50SP2().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData50SP2().getFragmentAllTags();
    protected static final Set<Integer> PEG_INSTRUCTIONS_COMP_TAGS = new PegInstructions50SP2().getFragmentAllTags();
    protected static final Set<Integer> DISCR_INSTR_COMP_TAGS = new DiscretionInstructions50SP2().getFragmentAllTags();
    protected static final Set<Integer> STRATEGY_PARAMS_GROUP_TAGS = new StrategyParametersGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> FILL_EXECUTION_GROUP_TAGS = new FillExecGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> COMMISSION_DATA_COMP_TAGS = new CommissionData50SP2().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData50SP2().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData50SP2().getFragmentAllTags();
    protected static final Set<Integer> RATE_SOURCE_GROUP_TAGS = new RateSourceGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> DISPLAY_INSTRUCTION_COMP_TAGS = new DisplayInstruction50SP2().getFragmentAllTags();
    protected static final Set<Integer> CONT_AMT_GROUP_TAGS = new ContAmtGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRMT_LEG_EXEC_GROUP_TAGS = new InstrmtLegExecGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> MISC_FEE_GROUP_TAGS = new MiscFeeGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRD_REG_TSTAMP_GROUP_TAGS = new TrdRegTimestampsGroup50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(APPL_SEQ_CONTROL_COMP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(CONTRA_BROKER_GROUP_TAGS);
        ALL_TAGS.addAll(ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(TRIGG_INSTR_COMP_TAGS);
        ALL_TAGS.addAll(PEG_INSTRUCTIONS_COMP_TAGS);
        ALL_TAGS.addAll(DISCR_INSTR_COMP_TAGS);
        ALL_TAGS.addAll(STRATEGY_PARAMS_GROUP_TAGS);
        ALL_TAGS.addAll(FILL_EXECUTION_GROUP_TAGS);
        ALL_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(RATE_SOURCE_GROUP_TAGS);
        ALL_TAGS.addAll(DISPLAY_INSTRUCTION_COMP_TAGS);
        ALL_TAGS.addAll(CONT_AMT_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRMT_LEG_EXEC_GROUP_TAGS);
        ALL_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        ALL_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(APPL_SEQ_CONTROL_COMP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(CONTRA_BROKER_GROUP_TAGS);
        START_COMP_TAGS.addAll(ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(TRIGG_INSTR_COMP_TAGS);
        START_COMP_TAGS.addAll(PEG_INSTRUCTIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(DISCR_INSTR_COMP_TAGS);
        START_COMP_TAGS.addAll(STRATEGY_PARAMS_GROUP_TAGS);
        START_COMP_TAGS.addAll(FILL_EXECUTION_GROUP_TAGS);
        START_COMP_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(RATE_SOURCE_GROUP_TAGS);
        START_COMP_TAGS.addAll(DISPLAY_INSTRUCTION_COMP_TAGS);
        START_COMP_TAGS.addAll(CONT_AMT_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRMT_LEG_EXEC_GROUP_TAGS);
        START_COMP_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        START_COMP_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ExecutionReportMsg50SP2() {
        super();
    }

    public ExecutionReportMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public ExecutionReportMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public ExecutionReportMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        ExecutionReportMsg50SP2 fixml = (ExecutionReportMsg50SP2) fragment;
        if (fixml.getApplicationSequenceControl() != null) {
            applicationSequenceControl = fixml.getApplicationSequenceControl();
        }
        if (fixml.getOrderID() != null) {
            orderID = fixml.getOrderID();
        }
        if (fixml.getSecondaryOrderID() != null) {
            secondaryOrderID = fixml.getSecondaryOrderID();
        }
        if (fixml.getSecondaryClOrdID() != null) {
            secondaryClOrdID = fixml.getSecondaryClOrdID();
        }
        if (fixml.getSecondaryExecID() != null) {
            secondaryExecID = fixml.getSecondaryExecID();
        }
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getOrigClOrdID() != null) {
            origClOrdID = fixml.getOrigClOrdID();
        }
        if (fixml.getClOrdLinkID() != null) {
            clOrdLinkID = fixml.getClOrdLinkID();
        }
        if (fixml.getQuoteRespID() != null) {
            quoteRespID = fixml.getQuoteRespID();
        }
        if (fixml.getOrdStatusReqID() != null) {
            ordStatusReqID = fixml.getOrdStatusReqID();
        }
        if (fixml.getMassStatusReqID() != null) {
            massStatusReqID = fixml.getMassStatusReqID();
        }
        if (fixml.getHostCrossID() != null) {
            hostCrossID = fixml.getHostCrossID();
        }
        if (fixml.getTotNumReports() != null) {
            totNumReports = fixml.getTotNumReports();
        }
        if (fixml.getLastRptRequested() != null) {
            lastRptRequested = fixml.getLastRptRequested();
        }
        if (fixml.getParties() != null) {
            setParties(fixml.getParties());
        }
        if (fixml.getTradeOriginationDate() != null) {
            tradeOriginationDate = fixml.getTradeOriginationDate();
        }
        if (fixml.getContraBrokers() != null && fixml.getContraBrokers().length > 0) {
            setContraBrokers(fixml.getContraBrokers());
        }
        if (fixml.getListID() != null) {
            listID = fixml.getListID();
        }
        if (fixml.getCrossID() != null) {
            crossID = fixml.getCrossID();
        }
        if (fixml.getOrigCrossID() != null) {
            origCrossID = fixml.getOrigCrossID();
        }
        if (fixml.getCrossType() != null) {
            crossType = fixml.getCrossType();
        }
        if (fixml.getTrdMatchID() != null) {
            trdMatchID = fixml.getTrdMatchID();
        }
        if (fixml.getExecID() != null) {
            execID = fixml.getExecID();
        }
        if (fixml.getExecRefID() != null) {
            execRefID = fixml.getExecRefID();
        }
        if (fixml.getExecType() != null) {
            execType = fixml.getExecType();
        }
        if (fixml.getOrdStatus() != null) {
            ordStatus = fixml.getOrdStatus();
        }
        if (fixml.getWorkingIndicator() != null) {
            workingIndicator = fixml.getWorkingIndicator();
        }
        if (fixml.getOrdRejReason() != null) {
            ordRejReason = fixml.getOrdRejReason();
        }
        if (fixml.getExecRestatementReason() != null) {
            execRestatementReason = fixml.getExecRestatementReason();
        }
        if (fixml.getAccount() != null) {
            account = fixml.getAccount();
        }
        if (fixml.getAcctIDSource() != null) {
            acctIDSource = fixml.getAcctIDSource();
        }
        if (fixml.getAccountType() != null) {
            accountType = fixml.getAccountType();
        }
        if (fixml.getDayBookingInst() != null) {
            dayBookingInst = fixml.getDayBookingInst();
        }
        if (fixml.getBookingUnit() != null) {
            bookingUnit = fixml.getBookingUnit();
        }
        if (fixml.getPreallocMethod() != null) {
            preallocMethod = fixml.getPreallocMethod();
        }
        if (fixml.getAllocID() != null) {
            allocID = fixml.getAllocID();
        }
        if (fixml.getAllocGroups() != null && fixml.getAllocGroups().length > 0) {
            setAllocGroups(fixml.getAllocGroups());
        }
        if (fixml.getSettlType() != null) {
            settlType = fixml.getSettlType();
        }
        if (fixml.getSettlDate() != null) {
            settlDate = fixml.getSettlDate();
        }
        if (fixml.getMatchType() != null) {
            matchType = fixml.getMatchType();
        }
        if (fixml.getOrderCategory() != null) {
            orderCategory = fixml.getOrderCategory();
        }
        if (fixml.getCashMargin() != null) {
            cashMargin = fixml.getCashMargin();
        }
        if (fixml.getClearingFeeIndicator() != null) {
            clearingFeeIndicator = fixml.getClearingFeeIndicator();
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getFinancingDetails() != null) {
            setFinancingDetails(fixml.getFinancingDetails());
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getSide() != null) {
            side = fixml.getSide();
        }
        if (fixml.getStipulations() != null) {
            setStipulations(fixml.getStipulations());
        }
        if (fixml.getQtyType() != null) {
            qtyType = fixml.getQtyType();
        }
        if (fixml.getOrderQtyData() != null) {
            setOrderQtyData(fixml.getOrderQtyData());
        }
        if (fixml.getLotType() != null) {
            lotType = fixml.getLotType();
        }
        if (fixml.getOrdType() != null) {
            ordType = fixml.getOrdType();
        }
        if (fixml.getPriceType() != null) {
            priceType = fixml.getPriceType();
        }
        if (fixml.getPrice() != null) {
            price = fixml.getPrice();
        }
        if (fixml.getPriceProtectionScope() != null) {
            priceProtectionScope = fixml.getPriceProtectionScope();
        }
        if (fixml.getStopPx() != null) {
            stopPx = fixml.getStopPx();
        }
        if (fixml.getTriggeringInstruction() != null) {
            setTriggeringInstruction(fixml.getTriggeringInstruction());
        }
        if (fixml.getPegInstructions() != null) {
            setPegInstructions(fixml.getPegInstructions());
        }
        if (fixml.getDiscretionInstructions() != null) {
            setDiscretionInstructions(fixml.getDiscretionInstructions());
        }
        if (fixml.getPeggedPrice() != null) {
            peggedPrice = fixml.getPeggedPrice();
        }
        if (fixml.getPeggedRefPrice() != null) {
            peggedRefPrice = fixml.getPeggedRefPrice();
        }
        if (fixml.getDiscretionPrice() != null) {
            discretionPrice = fixml.getDiscretionPrice();
        }
        if (fixml.getTargetStrategy() != null) {
            targetStrategy = fixml.getTargetStrategy();
        }
        if (fixml.getStrategyParametersGroups() != null && fixml.getStrategyParametersGroups().length > 0) {
            setStrategyParametersGroups(fixml.getStrategyParametersGroups());
        }
        if (fixml.getTargetStrategyParameters() != null) {
            targetStrategyParameters = fixml.getTargetStrategyParameters();
        }
        if (fixml.getCurrency() != null) {
            currency = fixml.getCurrency();
        }
        if (fixml.getComplianceID() != null) {
            complianceID = fixml.getComplianceID();
        }
        if (fixml.getSolicitedFlag() != null) {
            solicitedFlag = fixml.getSolicitedFlag();
        }
        if (fixml.getParticipationRate() != null) {
            participationRate = fixml.getParticipationRate();
        }
        if (fixml.getTargetStrategyPerformance() != null) {
            targetStrategyPerformance = fixml.getTargetStrategyPerformance();
        }
        if (fixml.getTimeInForce() != null) {
            timeInForce = fixml.getTimeInForce();
        }
        if (fixml.getEffectiveTime() != null) {
            effectiveTime = fixml.getEffectiveTime();
        }
        if (fixml.getExpireDate() != null) {
            expireDate = fixml.getExpireDate();
        }
        if (fixml.getExpireTime() != null) {
            expireTime = fixml.getExpireTime();
        }
        if (fixml.getExecInst() != null) {
            execInst = fixml.getExecInst();
        }
        if (fixml.getAggressorIndicator() != null) {
            aggressorIndicator = fixml.getAggressorIndicator();
        }
        if (fixml.getOrderCapacity() != null) {
            orderCapacity = fixml.getOrderCapacity();
        }
        if (fixml.getOrderRestrictions() != null) {
            orderRestrictions = fixml.getOrderRestrictions();
        }
        if (fixml.getPreTradeAnonymity() != null) {
            preTradeAnonymity = fixml.getPreTradeAnonymity();
        }
        if (fixml.getCustOrderCapacity() != null) {
            custOrderCapacity = fixml.getCustOrderCapacity();
        }
        if (fixml.getLastQty() != null) {
            lastQty = fixml.getLastQty();
        }
        if (fixml.getCalculatedCcyLastQty() != null) {
            calculatedCcyLastQty = fixml.getCalculatedCcyLastQty();
        }
        if (fixml.getLastSwapPoints() != null) {
            lastSwapPoints = fixml.getLastSwapPoints();
        }
        if (fixml.getUnderlyingLastQty() != null) {
            underlyingLastQty = fixml.getUnderlyingLastQty();
        }
        if (fixml.getLastPx() != null) {
            lastPx = fixml.getLastPx();
        }
        if (fixml.getUnderlyingLastPx() != null) {
            underlyingLastPx = fixml.getUnderlyingLastPx();
        }
        if (fixml.getLastParPx() != null) {
            lastParPx = fixml.getLastParPx();
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
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
        }
        if (fixml.getTimeBracket() != null) {
            timeBracket = fixml.getTimeBracket();
        }
        if (fixml.getLastCapacity() != null) {
            lastCapacity = fixml.getLastCapacity();
        }
        if (fixml.getLeavesQty() != null) {
            leavesQty = fixml.getLeavesQty();
        }
        if (fixml.getCumQty() != null) {
            cumQty = fixml.getCumQty();
        }
        if (fixml.getAvgPx() != null) {
            avgPx = fixml.getAvgPx();
        }
        if (fixml.getDayOrderQty() != null) {
            dayOrderQty = fixml.getDayOrderQty();
        }
        if (fixml.getDayCumQty() != null) {
            dayCumQty = fixml.getDayCumQty();
        }
        if (fixml.getDayAvgPx() != null) {
            dayAvgPx = fixml.getDayAvgPx();
        }
        if (fixml.getTotNoFills() != null) {
            totNoFills = fixml.getTotNoFills();
        }
        if (fixml.getLastFragment() != null) {
            lastFragment = fixml.getLastFragment();
        }
        if (fixml.getFillExecGroups() != null && fixml.getFillExecGroups().length > 0) {
            setFillExecGroups(fixml.getFillExecGroups());
        }
        if (fixml.getGTBookingInst() != null) {
            GTBookingInst = fixml.getGTBookingInst();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getReportToExch() != null) {
            reportToExch = fixml.getReportToExch();
        }
        if (fixml.getCommissionData() != null) {
            setCommissionData(fixml.getCommissionData());
        }
        if (fixml.getSpreadOrBenchmarkCurveData() != null) {
            setSpreadOrBenchmarkCurveData(fixml.getSpreadOrBenchmarkCurveData());
        }
        if (fixml.getYieldData() != null) {
            setYieldData(fixml.getYieldData());
        }
        if (fixml.getGrossTradeAmt() != null) {
            grossTradeAmt = fixml.getGrossTradeAmt();
        }
        if (fixml.getNumDaysInterest() != null) {
            numDaysInterest = fixml.getNumDaysInterest();
        }
        if (fixml.getExDate() != null) {
            exDate = fixml.getExDate();
        }
        if (fixml.getAccruedInterestRate() != null) {
            accruedInterestRate = fixml.getAccruedInterestRate();
        }
        if (fixml.getAccruedInterestAmt() != null) {
            accruedInterestAmt = fixml.getAccruedInterestAmt();
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
        if (fixml.getTradedFlatSwitch() != null) {
            tradedFlatSwitch = fixml.getTradedFlatSwitch();
        }
        if (fixml.getBasisFeatureDate() != null) {
            basisFeatureDate = fixml.getBasisFeatureDate();
        }
        if (fixml.getBasisFeaturePrice() != null) {
            basisFeaturePrice = fixml.getBasisFeaturePrice();
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
        if (fixml.getSettlCurrAmt() != null) {
            settlCurrAmt = fixml.getSettlCurrAmt();
        }
        if (fixml.getSettlCurrency() != null) {
            settlCurrency = fixml.getSettlCurrency();
        }
        if (fixml.getRateSources() != null) {
            setRateSources(fixml.getRateSources());
        }
        if (fixml.getSettlCurrFxRate() != null) {
            settlCurrFxRate = fixml.getSettlCurrFxRate();
        }
        if (fixml.getSettlCurrFxRateCalc() != null) {
            settlCurrFxRateCalc = fixml.getSettlCurrFxRateCalc();
        }
        if (fixml.getHandlInst() != null) {
            handlInst = fixml.getHandlInst();
        }
        if (fixml.getMinQty() != null) {
            minQty = fixml.getMinQty();
        }
        if (fixml.getMatchIncrement() != null) {
            matchIncrement = fixml.getMatchIncrement();
        }
        if (fixml.getMaxPriceLevels() != null) {
            maxPriceLevels = fixml.getMaxPriceLevels();
        }
        if (fixml.getDisplayInstruction() != null) {
            setDisplayInstruction(fixml.getDisplayInstruction());
        }
        if (fixml.getMaxFloor() != null) {
            maxFloor = fixml.getMaxFloor();
        }
        if (fixml.getPositionEffect() != null) {
            positionEffect = fixml.getPositionEffect();
        }
        if (fixml.getMaxShow() != null) {
            maxShow = fixml.getMaxShow();
        }
        if (fixml.getBookingType() != null) {
            bookingType = fixml.getBookingType();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
        if (fixml.getSettlDate2() != null) {
            settlDate2 = fixml.getSettlDate2();
        }
        if (fixml.getOrderQty2() != null) {
            orderQty2 = fixml.getOrderQty2();
        }
        if (fixml.getLastForwardPoints2() != null) {
            lastForwardPoints2 = fixml.getLastForwardPoints2();
        }
        if (fixml.getMultilegReportingType() != null) {
            multilegReportingType = fixml.getMultilegReportingType();
        }
        if (fixml.getCancellationRights() != null) {
            cancellationRights = fixml.getCancellationRights();
        }
        if (fixml.getMoneyLaunderingStatus() != null) {
            moneyLaunderingStatus = fixml.getMoneyLaunderingStatus();
        }
        if (fixml.getRegistID() != null) {
            registID = fixml.getRegistID();
        }
        if (fixml.getDesignation() != null) {
            designation = fixml.getDesignation();
        }
        if (fixml.getTransBkdTime() != null) {
            transBkdTime = fixml.getTransBkdTime();
        }
        if (fixml.getExecValuationPoint() != null) {
            execValuationPoint = fixml.getExecValuationPoint();
        }
        if (fixml.getExecPriceType() != null) {
            execPriceType = fixml.getExecPriceType();
        }
        if (fixml.getExecPriceAdjustment() != null) {
            execPriceAdjustment = fixml.getExecPriceAdjustment();
        }
        if (fixml.getPriorityIndicator() != null) {
            priorityIndicator = fixml.getPriorityIndicator();
        }
        if (fixml.getPriceImprovement() != null) {
            priceImprovement = fixml.getPriceImprovement();
        }
        if (fixml.getLastLiquidityInd() != null) {
            lastLiquidityInd = fixml.getLastLiquidityInd();
        }
        if (fixml.getContAmtGroups() != null) {
            setContAmtGroups(fixml.getContAmtGroups());
        }
        if (fixml.getInstrmtLegExecGroups() != null) {
            setInstrmtLegExecGroups(fixml.getInstrmtLegExecGroups());
        }
        if (fixml.getCopyMsgIndicator() != null) {
            copyMsgIndicator = fixml.getCopyMsgIndicator();
        }
        if (fixml.getMiscFeeGroups() != null) {
            setMiscFeeGroups(fixml.getMiscFeeGroups());
        }
        if (fixml.getDividendYield() != null) {
            dividendYield = fixml.getDividendYield();
        }
        if (fixml.getManualOrderIndicator() != null) {
            manualOrderIndicator = fixml.getManualOrderIndicator();
        }
        if (fixml.getCustDirectedOrder() != null) {
            custDirectedOrder = fixml.getCustDirectedOrder();
        }
        if (fixml.getReceivedDeptID() != null) {
            receivedDeptID = fixml.getReceivedDeptID();
        }
        if (fixml.getCustOrderHandlingInst() != null) {
            custOrderHandlingInst = fixml.getCustOrderHandlingInst();
        }
        if (fixml.getOrderHandlingInstSource() != null) {
            orderHandlingInstSource = fixml.getOrderHandlingInstSource();
        }
        if (fixml.getTrdRegTimestampsGroups() != null && fixml.getTrdRegTimestampsGroups().length > 0) {
            setTrdRegTimestampsGroups(fixml.getTrdRegTimestampsGroups());
        }
        if (fixml.getVolatility() != null) {
            volatility = fixml.getVolatility();
        }
        if (fixml.getTimeToExpiration() != null) {
            timeToExpiration = fixml.getTimeToExpiration();
        }
        if (fixml.getRiskFreeRate() != null) {
            riskFreeRate = fixml.getRiskFreeRate();
        }
        if (fixml.getPriceDelta() != null) {
            priceDelta = fixml.getPriceDelta();
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

    @XmlElementRef
    @Override
    public ApplicationSequenceControl getApplicationSequenceControl() {
        return applicationSequenceControl;
    }

    @Override
    public void setApplicationSequenceControl() {
        this.applicationSequenceControl = new ApplicationSequenceControl50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public void setApplicationSequenceControl(ApplicationSequenceControl applicationSequenceControl) {
        this.applicationSequenceControl = applicationSequenceControl;
    }

    @Override
    public void clearApplicationSequenceControl() {
        this.applicationSequenceControl = null;
    }

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "OrdID2")
    @Override
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    @Override
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    @XmlAttribute(name = "ID2")
    @Override
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    @Override
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
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

    @XmlAttribute(name = "ID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @XmlAttribute(name = "OrigID")
    @Override
    public String getOrigClOrdID() {
        return origClOrdID;
    }

    @Override
    public void setOrigClOrdID(String origClOrdID) {
        this.origClOrdID = origClOrdID;
    }

    @XmlAttribute(name = "LnkID")
    @Override
    public String getClOrdLinkID() {
        return clOrdLinkID;
    }

    @Override
    public void setClOrdLinkID(String clOrdLinkID) {
        this.clOrdLinkID = clOrdLinkID;
    }

    @XmlAttribute(name = "RspID")
    @Override
    public String getQuoteRespID() {
        return quoteRespID;
    }

    @Override
    public void setQuoteRespID(String quoteRespID) {
        this.quoteRespID = quoteRespID;
    }

    @XmlAttribute(name = "StatReqID")
    @Override
    public String getOrdStatusReqID() {
        return ordStatusReqID;
    }

    @Override
    public void setOrdStatusReqID(String ordStatusReqID) {
        this.ordStatusReqID = ordStatusReqID;
    }

    @XmlAttribute(name = "MassStatReqID")
    @Override
    public String getMassStatusReqID() {
        return massStatusReqID;
    }

    @Override
    public void setMassStatusReqID(String massStatusReqID) {
        this.massStatusReqID = massStatusReqID;
    }

    @XmlAttribute(name = "HstCxID")
    @Override
    public String getHostCrossID() {
        return hostCrossID;
    }

    @Override
    public void setHostCrossID(String hostCrossID) {
        this.hostCrossID = hostCrossID;
    }

    @XmlAttribute(name = "TotNumRpts")
    @Override
    public Integer getTotNumReports() {
        return totNumReports;
    }

    @Override
    public void setTotNumReports(Integer totNumReports) {
        this.totNumReports = totNumReports;
    }

    @XmlAttribute(name = "LastRptReqed")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getLastRptRequested() {
        return lastRptRequested;
    }

    @Override
    public void setLastRptRequested(Boolean lastRptRequested) {
        this.lastRptRequested = lastRptRequested;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.parties = new Parties50SP2(context);
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
            ((Parties50SP2) parties).setPartyIDGroups(partyIDGroups);
        }
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

    @Override
    public Integer getNoContraBrokers() {
        return noContraBrokers;
    }

    @Override
    public void setNoContraBrokers(Integer noContraBrokers) {
        this.noContraBrokers = noContraBrokers;
        if (noContraBrokers != null) {
            contraBrokers = new ContraBrokerGroup[noContraBrokers.intValue()];
            for (int i = 0; i < contraBrokers.length; i++) {
                contraBrokers[i] = new ContraBrokerGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public ContraBrokerGroup[] getContraBrokers() {
        return contraBrokers;
    }

    public void setContraBrokers(ContraBrokerGroup[] contraBrokers) {
        this.contraBrokers = contraBrokers;
        if (contraBrokers != null) {
            noContraBrokers = new Integer(contraBrokers.length);
        }
    }

    @Override
    public ContraBrokerGroup addContraBroker() {
        ContraBrokerGroup group = new ContraBrokerGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<ContraBrokerGroup> groups = new ArrayList<ContraBrokerGroup>();
        if (contraBrokers != null && contraBrokers.length > 0) {
            groups = new ArrayList<ContraBrokerGroup>(Arrays.asList(contraBrokers));
        }
        groups.add(group);
        contraBrokers = groups.toArray(new ContraBrokerGroup[groups.size()]);
        noContraBrokers = new Integer(contraBrokers.length);

        return group;
    }

    @Override
    public ContraBrokerGroup deleteContraBroker(int index) {
        ContraBrokerGroup result = null;
        if (contraBrokers != null && contraBrokers.length > 0 && contraBrokers.length > index) {
            List<ContraBrokerGroup> groups = new ArrayList<ContraBrokerGroup>(Arrays.asList(contraBrokers));
            result = groups.remove(index);
            contraBrokers = groups.toArray(new ContraBrokerGroup[groups.size()]);
            if (contraBrokers.length > 0) {
                noContraBrokers = new Integer(contraBrokers.length);
            } else {
                contraBrokers = null;
                noContraBrokers = null;
            }
        }

        return result;
    }

    @Override
    public int clearContraBrokers() {
        int result = 0;
        if (contraBrokers != null && contraBrokers.length > 0) {
            result = contraBrokers.length;
            contraBrokers = null;
            noContraBrokers = null;
        }

        return result;
    }

    @XmlAttribute(name = "ListID")
    @Override
    public String getListID() {
        return listID;
    }

    @Override
    public void setListID(String listID) {
        this.listID = listID;
    }

    @XmlAttribute(name = "CrssID")
    @Override
    public String getCrossID() {
        return crossID;
    }

    @Override
    public void setCrossID(String crossID) {
        this.crossID = crossID;
    }

    @XmlAttribute(name = "OrigCrssID")
    @Override
    public String getOrigCrossID() {
        return origCrossID;
    }

    @Override
    public void setOrigCrossID(String origCrossID) {
        this.origCrossID = origCrossID;
    }

    @XmlAttribute(name = "CrssTyp")
    @Override
    public CrossType getCrossType() {
        return crossType;
    }

    @Override
    public void setCrossType(CrossType crossType) {
        this.crossType = crossType;
    }

    @XmlAttribute(name = "MtchID")
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

    @XmlAttribute(name = "ExecRefID")
    @Override
    public String getExecRefID() {
        return execRefID;
    }

    @Override
    public void setExecRefID(String execRefID) {
        this.execRefID = execRefID;
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

    @XmlAttribute(name = "Stat")
    @Override
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    @Override
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    @XmlAttribute(name = "WorkingInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getWorkingIndicator() {
        return workingIndicator;
    }

    @Override
    public void setWorkingIndicator(Boolean workingIndicator) {
        this.workingIndicator = workingIndicator;
    }

    @XmlAttribute(name = "RejRsn")
    @Override
    public OrdRejReason getOrdRejReason() {
        return ordRejReason;
    }

    @Override
    public void setOrdRejReason(OrdRejReason ordRejReason) {
        this.ordRejReason = ordRejReason;
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

    @XmlAttribute(name = "Acct")
    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @XmlAttribute(name = "AcctIDSrc")
    @Override
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    @Override
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    @XmlAttribute(name = "AcctTyp")
    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @XmlAttribute(name = "DayBkngInst")
    @Override
    public DayBookingInst getDayBookingInst() {
        return dayBookingInst;
    }

    @Override
    public void setDayBookingInst(DayBookingInst dayBookingInst) {
        this.dayBookingInst = dayBookingInst;
    }

    @XmlAttribute(name = "BkngUnit")
    @Override
    public BookingUnit getBookingUnit() {
        return bookingUnit;
    }

    @Override
    public void setBookingUnit(BookingUnit bookingUnit) {
        this.bookingUnit = bookingUnit;
    }

    @XmlAttribute(name = "PreallocMeth")
    @Override
    public PreallocMethod getPreallocMethod() {
        return preallocMethod;
    }

    @Override
    public void setPreallocMethod(PreallocMethod preallocMethod) {
        this.preallocMethod = preallocMethod;
    }

    @XmlAttribute(name = "AllocID")
    @Override
    public String getAllocID() {
        return allocID;
    }

    @Override
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    @Override
    public Integer getNoAllocs() {
        return noAllocs;
    }

    @Override
    public void setNoAllocs(Integer noAllocs) {
        this.noAllocs = noAllocs;
        if (noAllocs != null) {
            allocGroups = new PreTradeAllocGroup[noAllocs.intValue()];
            for (int i = 0; i < allocGroups.length; i++) {
                allocGroups[i] = new PreTradeAllocGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public PreTradeAllocGroup[] getAllocGroups() {
        return allocGroups;
    }

    public void setAllocGroups(PreTradeAllocGroup[] allocGroups) {
        this.allocGroups = allocGroups;
        if (allocGroups != null) {
            noAllocs = new Integer(allocGroups.length);
        }
    }

    @Override
    public PreTradeAllocGroup addAllocGroup() {
        PreTradeAllocGroup group = new PreTradeAllocGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<PreTradeAllocGroup> groups = new ArrayList<PreTradeAllocGroup>();
        if (allocGroups != null && allocGroups.length > 0) {
            groups = new ArrayList<PreTradeAllocGroup>(Arrays.asList(allocGroups));
        }
        groups.add(group);
        allocGroups = groups.toArray(new PreTradeAllocGroup[groups.size()]);
        noAllocs = new Integer(allocGroups.length);

        return group;
    }

    @Override
    public PreTradeAllocGroup deleteAllocGroup(int index) {
        PreTradeAllocGroup result = null;
        if (allocGroups != null && allocGroups.length > 0 && allocGroups.length > index) {
            List<PreTradeAllocGroup> groups = new ArrayList<PreTradeAllocGroup>(Arrays.asList(allocGroups));
            result = groups.remove(index);
            allocGroups = groups.toArray(new PreTradeAllocGroup[groups.size()]);
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

    @XmlAttribute(name = "MtchTyp")
    @Override
    public MatchType getMatchType() {
        return matchType;
    }

    @Override
    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    @XmlAttribute(name = "OrdCat")
    @Override
    public OrderCategory getOrderCategory() {
        return orderCategory;
    }

    @Override
    public void setOrderCategory(OrderCategory orderCategory) {
        this.orderCategory = orderCategory;
    }

    @XmlAttribute(name = "CshMgn")
    @Override
    public CashMargin getCashMargin() {
        return cashMargin;
    }

    @Override
    public void setCashMargin(CashMargin cashMargin) {
        this.cashMargin = cashMargin;
    }

    @XmlAttribute(name = "ClrFeeInd")
    @Override
    public ClearingFeeIndicator getClearingFeeIndicator() {
        return clearingFeeIndicator;
    }

    @Override
    public void setClearingFeeIndicator(ClearingFeeIndicator clearingFeeIndicator) {
        this.clearingFeeIndicator = clearingFeeIndicator;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrument = new Instrument50SP2(context);
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.financingDetails = new FinancingDetails50SP2(context);
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
                underlyingInstruments[i] = new UnderlyingInstrument50SP2(context);
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
        UnderlyingInstrument group = new UnderlyingInstrument50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "Side")
    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public Stipulations getStipulations() {
        return stipulations;
    }

    @Override
    public void setStipulations() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.stipulations = new Stipulations50SP2(context);
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
            ((Stipulations50SP2) stipulations).setStipulationsGroups(stipulationsGroups);
        }
    }

    @Override
    public QuantityType getQuantityType() {
        return quantityType;
    }

    @Override
    public void setQuantityType(QuantityType quantityType) {
        this.quantityType = quantityType;
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
    public OrderQtyData getOrderQtyData() {
        return orderQtyData;
    }

    @Override
    public void setOrderQtyData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.orderQtyData = new OrderQtyData50SP2(context);
    }

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
    }

    @XmlAttribute(name = "LotTyp")
    @Override
    public LotType getLotType() {
        return lotType;
    }

    @Override
    public void setLotType(LotType lotType) {
        this.lotType = lotType;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public OrdType getOrdType() {
        return ordType;
    }

    @Override
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
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

    @XmlAttribute(name = "Px")
    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public PriceProtectionScope getPriceProtectionScope() {
        return priceProtectionScope;
    }

    @XmlAttribute(name = "PxPrtScp")
    @Override
    public void setPriceProtectionScope(PriceProtectionScope priceProtectionScope) {
        this.priceProtectionScope = priceProtectionScope;
    }

    @XmlAttribute(name = "StopPx")
    @Override
    public Double getStopPx() {
        return stopPx;
    }

    @Override
    public void setStopPx(Double stopPx) {
        this.stopPx = stopPx;
    }

    @XmlElementRef
    @Override
    public TriggeringInstruction getTriggeringInstruction() {
        return triggeringInstruction;
    }

    public void setTriggeringInstruction(TriggeringInstruction triggeringInstruction) {
        this.triggeringInstruction = triggeringInstruction;
    }

    @Override
    public void setTriggeringInstruction() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.triggeringInstruction = new TriggeringInstruction50SP2(context);
    }

    @Override
    public void clearTriggeringInstruction() {
        triggeringInstruction = null;
    }

    @XmlElementRef
    @Override
    public PegInstructions getPegInstructions() {
        return pegInstructions;
    }

    public void setPegInstructions(PegInstructions pegInstructions) {
        this.pegInstructions = pegInstructions;
    }

    @Override
    public void setPegInstructions() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.pegInstructions = new PegInstructions50SP2(context);
    }

    @Override
    public void clearPegInstructions() {
        this.pegInstructions = null;
    }

    @XmlElementRef
    @Override
    public DiscretionInstructions getDiscretionInstructions() {
        return discretionInstructions;
    }

    public void setDiscretionInstructions(DiscretionInstructions discretionInstructions) {
        this.discretionInstructions = discretionInstructions;
    }

    @Override
    public void setDiscretionInstructions() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.discretionInstructions = new DiscretionInstructions50SP2(context);
    }

    @Override
    public void clearDiscretionInstructions() {
        this.discretionInstructions = null;
    }

    @XmlAttribute(name = "PeggedPx")
    @Override
    public Double getPeggedPrice() {
        return peggedPrice;
    }

    @Override
    public void setPeggedPrice(Double peggedPrice) {
        this.peggedPrice = peggedPrice;
    }

    @XmlAttribute(name = "PggdRefPx")
    @Override
    public Double getPeggedRefPrice() {
        return peggedRefPrice;
    }

    @Override
    public void setPeggedRefPrice(Double peggedRefPrice) {
        this.peggedRefPrice = peggedRefPrice;
    }

    @XmlAttribute(name = "DsctnPx")
    @Override
    public Double getDiscretionPrice() {
        return discretionPrice;
    }

    @Override
    public void setDiscretionPrice(Double discretionPrice) {
        this.discretionPrice = discretionPrice;
    }

    @XmlAttribute(name = "TgtStrategy")
    @Override
    public Integer getTargetStrategy() {
        return targetStrategy;
    }

    @Override
    public void setTargetStrategy(Integer targetStrategy) {
        this.targetStrategy = targetStrategy;
    }

    @Override
    public Integer getNoStrategyParameters() {
        return noStrategyParameters;
    }

    @Override
    public void setNoStrategyParameters(Integer noStrategyParameters) {
        this.noStrategyParameters = noStrategyParameters;
        if (noStrategyParameters != null) {
            strategyParametersGroups = new StrategyParametersGroup[noStrategyParameters.intValue()];
            for (int i = 0; i < strategyParametersGroups.length; i++) {
                strategyParametersGroups[i] = new StrategyParametersGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public StrategyParametersGroup[] getStrategyParametersGroups() {
        return strategyParametersGroups;
    }

    public void setStrategyParametersGroups(StrategyParametersGroup[] strategyParametersGroups) {
        this.strategyParametersGroups = strategyParametersGroups;
        if (strategyParametersGroups != null) {
            noStrategyParameters = new Integer(strategyParametersGroups.length);
        }
    }

    @Override
    public StrategyParametersGroup addStrategyParametersGroup() {
        StrategyParametersGroup group = new StrategyParametersGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<StrategyParametersGroup> groups = new ArrayList<StrategyParametersGroup>();
        if (strategyParametersGroups != null && strategyParametersGroups.length > 0) {
            groups = new ArrayList<StrategyParametersGroup>(Arrays.asList(strategyParametersGroups));
        }
        groups.add(group);
        strategyParametersGroups = groups.toArray(new StrategyParametersGroup[groups.size()]);
        noStrategyParameters = new Integer(strategyParametersGroups.length);

        return group;
    }

    @Override
    public StrategyParametersGroup deleteStrategyParametersGroup(int index) {
        StrategyParametersGroup result = null;
        if (strategyParametersGroups != null && strategyParametersGroups.length > 0 && strategyParametersGroups.length > index) {
            List<StrategyParametersGroup> groups = new ArrayList<StrategyParametersGroup>(Arrays.asList(strategyParametersGroups));
            result = groups.remove(index);
            strategyParametersGroups = groups.toArray(new StrategyParametersGroup[groups.size()]);
            if (strategyParametersGroups.length > 0) {
                noStrategyParameters = new Integer(strategyParametersGroups.length);
            } else {
                strategyParametersGroups = null;
                noStrategyParameters = null;
            }
        }

        return result;
    }

    @Override
    public int clearStrategyParametersGroups() {
        int result = 0;
        if (strategyParametersGroups != null && strategyParametersGroups.length > 0) {
            result = strategyParametersGroups.length;
            strategyParametersGroups = null;
            noStrategyParameters = null;
        }

        return result;
    }

    @XmlAttribute(name = "TgtStrategyParameters")
    @Override
    public String getTargetStrategyParameters() {
        return targetStrategyParameters;
    }

    @Override
    public void setTargetStrategyParameters(String targetStrategyParameters) {
        this.targetStrategyParameters = targetStrategyParameters;
    }

    @XmlAttribute(name = "ParticipationRt")
    @Override
    public Double getParticipationRate() {
        return participationRate;
    }

    @Override
    public void setParticipationRate(Double participationRate) {
        this.participationRate = participationRate;
    }

    @XmlAttribute(name = "TgtStrategyPerformance")
    @Override
    public Double getTargetStrategyPerformance() {
        return targetStrategyPerformance;
    }

    @Override
    public void setTargetStrategyPerformance(Double targetStrategyPerformance) {
        this.targetStrategyPerformance = targetStrategyPerformance;
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

    @XmlAttribute(name = "ComplianceID")
    @Override
    public String getComplianceID() {
        return complianceID;
    }

    @Override
    public void setComplianceID(String complianceID) {
        this.complianceID = complianceID;
    }

    @XmlAttribute(name = "SolFlag")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getSolicitedFlag() {
        return solicitedFlag;
    }

    @Override
    public void setSolicitedFlag(Boolean solicitedFlag) {
        this.solicitedFlag = solicitedFlag;
    }

    @XmlAttribute(name = "TmInForce")
    @Override
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    @Override
    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    @XmlAttribute(name = "EfctvTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    @Override
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    @XmlAttribute(name = "ExpireDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getExpireDate() {
        return expireDate;
    }

    @Override
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @XmlAttribute(name = "ExpireTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getExpireTime() {
        return expireTime;
    }

    @Override
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @XmlAttribute(name = "ExecInst")
    @Override
    public String getExecInst() {
        return execInst;
    }

    @Override
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    @XmlAttribute(name = "AgrsrInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getAggressorIndicator() {
        return aggressorIndicator;
    }

    @Override
    public void setAggressorIndicator(Boolean aggressorIndicator) {
        this.aggressorIndicator = aggressorIndicator;
    }

    @XmlAttribute(name = "Cpcty")
    @Override
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    @Override
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    @XmlAttribute(name = "Rstctions")
    @Override
    public String getOrderRestrictions() {
        return orderRestrictions;
    }

    @Override
    public void setOrderRestrictions(String orderRestrictions) {
        this.orderRestrictions = orderRestrictions;
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

    @XmlAttribute(name = "LastQty")
    @Override
    public Double getLastQty() {
        return lastQty;
    }

    @Override
    public void setLastQty(Double lastQty) {
        this.lastQty = lastQty;
    }

    @XmlAttribute(name = "CalcCcyLastQty")
    @Override
    public Double getCalculatedCcyLastQty() {
        return calculatedCcyLastQty;
    }

    @Override
    public void setCalculatedCcyLastQty(Double calculatedCcyLastQty) {
        this.calculatedCcyLastQty = calculatedCcyLastQty;
    }

    @XmlAttribute(name = "LastSwapPnts")
    @Override
    public Double getLastSwapPoints() {
        return lastSwapPoints;
    }

    @Override
    public void setLastSwapPoints(Double lastSwapPoints) {
        this.lastSwapPoints = lastSwapPoints;
    }

    @XmlAttribute(name = "UndLastQty")
    @Override
    public Double getUnderlyingLastQty() {
        return underlyingLastQty;
    }

    @Override
    public void setUnderlyingLastQty(Double underlyingLastQty) {
        this.underlyingLastQty = underlyingLastQty;
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

    @XmlAttribute(name = "UndLastPx")
    @Override
    public Double getUnderlyingLastPx() {
        return underlyingLastPx;
    }

    @Override
    public void setUnderlyingLastPx(Double underlyingLastPx) {
        this.underlyingLastPx = underlyingLastPx;
    }

    @XmlAttribute(name = "LastParPx")
    @Override
    public Double getLastParPx() {
        return lastParPx;
    }

    @Override
    public void setLastParPx(Double lastParPx) {
        this.lastParPx = lastParPx;
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

    @XmlAttribute(name = "TmBkt")
    @Override
    public String getTimeBracket() {
        return timeBracket;
    }

    @Override
    public void setTimeBracket(String timeBracket) {
        this.timeBracket = timeBracket;
    }
    
    @XmlAttribute(name = "LastCpcty")
    @Override
    public LastCapacity getLastCapacity() {
        return lastCapacity;
    }

    @Override
    public void setLastCapacity(LastCapacity lastCapacity) {
        this.lastCapacity = lastCapacity;
    }

    @XmlAttribute(name = "LeavesQty")
    @Override
    public Double getLeavesQty() {
        return leavesQty;
    }

    @Override
    public void setLeavesQty(Double leavesQty) {
        this.leavesQty = leavesQty;
    }

    @XmlAttribute(name = "CumQty")
    @Override
    public Double getCumQty() {
        return cumQty;
    }

    @Override
    public void setCumQty(Double cumQty) {
        this.cumQty = cumQty;
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

    @XmlAttribute(name = "DayOrdQty")
    @Override
    public Double getDayOrderQty() {
        return dayOrderQty;
    }

    @Override
    public void setDayOrderQty(Double dayOrderQty) {
        this.dayOrderQty = dayOrderQty;
    }

    @XmlAttribute(name = "DayCumQty")
    @Override
    public Double getDayCumQty() {
        return dayCumQty;
    }

    @Override
    public void setDayCumQty(Double dayCumQty) {
        this.dayCumQty = dayCumQty;
    }

    @XmlAttribute(name = "DayAvgPx")
    @Override
    public Double getDayAvgPx() {
        return dayAvgPx;
    }

    @Override
    public void setDayAvgPx(Double dayAvgPx) {
        this.dayAvgPx = dayAvgPx;
    }

    @XmlAttribute(name = "TotNoFills")
    @Override
    public Integer getTotNoFills() {
        return totNoFills;
    }

    @Override
    public void setTotNoFills(Integer totNoFills) {
        this.totNoFills = totNoFills;
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
    public Integer getNoFills() {
        return noFills;
    }

    @Override
    public void setNoFills(Integer noFills) {
        this.noFills = noFills;
        if (noFills != null) {
            fillExecGroups = new FillExecGroup[noFills.intValue()];
            for (int i = 0; i < fillExecGroups.length; i++) {
                fillExecGroups[i] = new FillExecGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public FillExecGroup[] getFillExecGroups() {
        return fillExecGroups;
    }

    public void setFillExecGroups(FillExecGroup[] fillExecGroups) {
        this.fillExecGroups = fillExecGroups;
        if (fillExecGroups != null) {
            noFills = new Integer(fillExecGroups.length);
        }
    }

    @Override
    public FillExecGroup addFillExecGroup() {
        FillExecGroup group = new FillExecGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<FillExecGroup> groups = new ArrayList<FillExecGroup>();
        if (fillExecGroups != null && fillExecGroups.length > 0) {
            groups = new ArrayList<FillExecGroup>(Arrays.asList(fillExecGroups));
        }
        groups.add(group);
        fillExecGroups = groups.toArray(new FillExecGroup[groups.size()]);
        noFills = new Integer(fillExecGroups.length);

        return group;
    }

    @Override
    public FillExecGroup deleteFillExecGroup(int index) {
        FillExecGroup result = null;
        if (fillExecGroups != null && fillExecGroups.length > 0 && fillExecGroups.length > index) {
            List<FillExecGroup> groups = new ArrayList<FillExecGroup>(Arrays.asList(fillExecGroups));
            result = groups.remove(index);
            fillExecGroups = groups.toArray(new FillExecGroup[groups.size()]);
            if (fillExecGroups.length > 0) {
                noFills = new Integer(fillExecGroups.length);
            } else {
                fillExecGroups = null;
                noFills = null;
            }
        }

        return result;
    }

    @Override
    public int clearFillExecGroups() {
        int result = 0;
        if (fillExecGroups != null && fillExecGroups.length > 0) {
            result = fillExecGroups.length;
            fillExecGroups = null;
            noFills = null;
        }

        return result;
    }

    @XmlAttribute(name = "GTBkngInst")
    @Override
    public GTBookingInst getGTBookingInst() {
        return GTBookingInst;
    }

    @Override
    public void setGTBookingInst(GTBookingInst GTBookingInst) {
        this.GTBookingInst = GTBookingInst;
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

    @XmlAttribute(name = "RptToExch")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getReportToExch() {
        return reportToExch;
    }

    @Override
    public void setReportToExch(Boolean reportToExch) {
        this.reportToExch = reportToExch;
    }

    @XmlElementRef
    @Override
    public CommissionData getCommissionData() {
        return commissionData;
    }

    public void setCommissionData(CommissionData commissionData) {
        this.commissionData = commissionData;
    }

    @Override
    public void setCommissionData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.commissionData = new CommissionData50SP2(context);
    }

    @Override
    public void clearCommissionData() {
        commissionData = null;
    }

    @XmlElementRef
    @Override
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50SP2(context);
    }

    @Override
    public void clearSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = null;
    }

    public void setSpreadOrBenchmarkCurveData(SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData) {
        this.spreadOrBenchmarkCurveData = spreadOrBenchmarkCurveData;
    }

    @XmlElementRef
    @Override
    public YieldData getYieldData() {
        return yieldData;
    }

    @Override
    public void setYieldData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.yieldData = new YieldData50SP2(context);
    }

    @Override
    public void clearYieldData() {
        this.yieldData = null;
    }

    public void setYieldData(YieldData yieldData) {
        this.yieldData = yieldData;
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

    @XmlAttribute(name = "NumDaysInt")
    @Override
    public Integer getNumDaysInterest() {
        return numDaysInterest;
    }

    @Override
    public void setNumDaysInterest(Integer numDaysInterest) {
        this.numDaysInterest = numDaysInterest;
    }

    @XmlAttribute(name = "ExDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getExDate() {
        return exDate;
    }

    @Override
    public void setExDate(Date exDate) {
        this.exDate = exDate;
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

    @XmlAttribute(name = "TrddFlatSwitch")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getTradedFlatSwitch() {
        return tradedFlatSwitch;
    }

    @Override
    public void setTradedFlatSwitch(Boolean tradedFlatSwitch) {
        this.tradedFlatSwitch = tradedFlatSwitch;
    }

    @XmlAttribute(name = "BasisFeatureDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override

    public Date getBasisFeatureDate() {
        return basisFeatureDate;
    }

    @Override
    public void setBasisFeatureDate(Date basisFeatureDate) {
        this.basisFeatureDate = basisFeatureDate;
    }

    @XmlAttribute(name = "BasisFeaturePx")
    @Override
    public Double getBasisFeaturePrice() {
        return basisFeaturePrice;
    }

    @Override
    public void setBasisFeaturePrice(Double basisFeaturePrice) {
        this.basisFeaturePrice = basisFeaturePrice;
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

    @XmlAttribute(name = "SettlCurrAmt")
    @Override
    public Double getSettlCurrAmt() {
        return settlCurrAmt;
    }

    @Override
    public void setSettlCurrAmt(Double settlCurrAmt) {
        this.settlCurrAmt = settlCurrAmt;
    }

    @XmlAttribute(name = "SettlCcy")
    @Override
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    @Override
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    @Override
    public Integer getNoRateSources() {
        return noRateSources;
    }

    @Override
    public void setNoRateSources(Integer noRateSources) {
        this.noRateSources = noRateSources;
        if (noRateSources != null) {
            rateSources = new RateSourceGroup[noRateSources.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < rateSources.length; i++) {
                rateSources[i] = new RateSourceGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public RateSourceGroup[] getRateSources() {
        return rateSources;
    }

    public void setRateSources(RateSourceGroup[] rateSources) {
        this.rateSources = rateSources;
        if (rateSources != null) {
            noRateSources = new Integer(rateSources.length);
        }
    }

    @Override
    public RateSourceGroup addRateSource() {
        RateSourceGroup group = new RateSourceGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<RateSourceGroup> groups = new ArrayList<RateSourceGroup>();
        if (rateSources != null && rateSources.length > 0) {
            groups = new ArrayList<RateSourceGroup>(Arrays.asList(rateSources));
        }
        groups.add(group);
        rateSources = groups.toArray(new RateSourceGroup[groups.size()]);
        noRateSources = new Integer(rateSources.length);

        return group;
    }

    @Override
    public RateSourceGroup deleteRateSource(int index) {
        RateSourceGroup result = null;
        if (rateSources != null && rateSources.length > 0 && rateSources.length > index) {
            List<RateSourceGroup> groups = new ArrayList<RateSourceGroup>(Arrays.asList(rateSources));
            result = groups.remove(index);
            rateSources = groups.toArray(new RateSourceGroup[groups.size()]);
            if (rateSources.length > 0) {
                noRateSources = new Integer(rateSources.length);
            } else {
                rateSources = null;
                noRateSources = null;
            }
        }

        return result;
    }

    @Override
    public int clearRateSources() {
        int result = 0;
        if (rateSources != null && rateSources.length > 0) {
            result = rateSources.length;
            rateSources = null;
            noRateSources = null;
        }

        return result;
    }

    @XmlAttribute(name = "SettlCurrFxRt")
    @Override
    public Double getSettlCurrFxRate() {
        return settlCurrFxRate;
    }

    @Override
    public void setSettlCurrFxRate(Double settlCurrFxRate) {
        this.settlCurrFxRate = settlCurrFxRate;
    }

    @XmlAttribute(name = "SettlCurrFxRtCalc")
    @Override
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    @Override
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }

    @XmlAttribute(name = "HandlInst")
    @Override
    public HandlInst getHandlInst() {
        return handlInst;
    }

    @Override
    public void setHandlInst(HandlInst handlInst) {
        this.handlInst = handlInst;
    }

    @XmlAttribute(name = "MinQty")
    @Override
    public Double getMinQty() {
        return minQty;
    }

    @Override
    public void setMinQty(Double minQty) {
        this.minQty = minQty;
    }

    @XmlAttribute(name = "MtchInc")
    @Override
    public Double getMatchIncrement() {
        return matchIncrement;
    }

    @Override
    public void setMatchIncrement(Double matchIncrement) {
        this.matchIncrement = matchIncrement;
    }

    @XmlAttribute(name = "MxPxLvls")
    @Override
    public Integer getMaxPriceLevels() {
        return maxPriceLevels;
    }

    @Override
    public void setMaxPriceLevels(Integer maxPriceLevels) {
        this.maxPriceLevels = maxPriceLevels;
    }

    @XmlElementRef
    @Override
    public DisplayInstruction getDisplayInstruction() {
        return displayInstruction;
    }

    public void setDisplayInstruction(DisplayInstruction displayInstruction) {
        this.displayInstruction = displayInstruction;
    }

    @Override
    public void setDisplayInstruction() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.displayInstruction = new DisplayInstruction50SP2(context);
    }

    @Override
    public void clearDisplayInstruction() {
        this.displayInstruction = null;
    }

    @XmlAttribute(name = "MaxFloor")
    @Override
    public Double getMaxFloor() {
        return maxFloor;
    }

    @Override
    public void setMaxFloor(Double maxFloor) {
        this.maxFloor = maxFloor;
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

    @XmlAttribute(name = "MaxShow")
    @Override
    public Double getMaxShow() {
        return maxShow;
    }

    @Override
    public void setMaxShow(Double maxShow) {
        this.maxShow = maxShow;
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

    @XmlAttribute(name = "SettlDt2")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getSettlDate2() {
        return settlDate2;
    }

    @Override
    public void setSettlDate2(Date settlDate2) {
        this.settlDate2 = settlDate2;
    }

    @XmlAttribute(name = "Qty2")
    @Override
    public Double getOrderQty2() {
        return orderQty2;
    }

    @Override
    public void setOrderQty2(Double orderQty2) {
        this.orderQty2 = orderQty2;
    }

    @Override
    public String getClearingFirm() {
        return clearingFirm;
    }

    @Override
    public void setClearingFirm(String clearingFirm) {
        this.clearingFirm = clearingFirm;
    }

    @Override
    public String getClearingAccount() {
        return clearingAccount;
    }

    @Override
    public void setClearingAccount(String clearingAccount) {
        this.clearingAccount = clearingAccount;
    }

    @XmlAttribute(name = "LastFwdPnts2")
    @Override
    public Double getLastForwardPoints2() {
        return lastForwardPoints2;
    }

    @Override
    public void setLastForwardPoints2(Double lastForwardPoints2) {
        this.lastForwardPoints2 = lastForwardPoints2;
    }

    @XmlAttribute(name = "MLegRptTyp")
    @Override
    public MultiLegReportingType getMultilegReportingType() {
        return multilegReportingType;
    }

    @Override
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        this.multilegReportingType = multilegReportingType;
    }

    @XmlAttribute(name = "CxllationRights")
    @Override
    public CancellationRights getCancellationRights() {
        return cancellationRights;
    }

    @Override
    public void setCancellationRights(CancellationRights cancellationRights) {
        this.cancellationRights = cancellationRights;
    }

    @XmlAttribute(name = "MnyLaunderingStat")
    @Override
    public MoneyLaunderingStatus getMoneyLaunderingStatus() {
        return moneyLaunderingStatus;
    }

    @Override
    public void setMoneyLaunderingStatus(MoneyLaunderingStatus moneyLaunderingStatus) {
        this.moneyLaunderingStatus = moneyLaunderingStatus;
    }

    @XmlAttribute(name = "RegistID")
    @Override
    public String getRegistID() {
        return registID;
    }

    @Override
    public void setRegistID(String registID) {
        this.registID = registID;
    }

    @XmlAttribute(name = "Designation")
    @Override
    public String getDesignation() {
        return designation;
    }

    @Override
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @XmlAttribute(name = "TransBkdTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    @Override
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
    }

    @XmlAttribute(name = "ExecValuationPoint")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getExecValuationPoint() {
        return execValuationPoint;
    }

    @Override
    public void setExecValuationPoint(Date execValuationPoint) {
        this.execValuationPoint = execValuationPoint;
    }

    @XmlAttribute(name = "ExecPxTyp")
    @Override
    public ExecPriceType getExecPriceType() {
        return execPriceType;
    }

    @Override
    public void setExecPriceType(ExecPriceType execPriceType) {
        this.execPriceType = execPriceType;
    }

    @XmlAttribute(name = "ExecPxAdjment")
    @Override
    public Double getExecPriceAdjustment() {
        return execPriceAdjustment;
    }

    @Override
    public void setExecPriceAdjustment(Double execPriceAdjustment) {
        this.execPriceAdjustment = execPriceAdjustment;
    }

    @XmlAttribute(name = "PriInd")
    @Override
    public PriorityIndicator getPriorityIndicator() {
        return priorityIndicator;
    }

    @Override
    public void setPriorityIndicator(PriorityIndicator priorityIndicator) {
        this.priorityIndicator = priorityIndicator;
    }

    @XmlAttribute(name = "PxImprvmnt")
    @Override
    public Double getPriceImprovement() {
        return priceImprovement;
    }

    @Override
    public void setPriceImprovement(Double priceImprovement) {
        this.priceImprovement = priceImprovement;
    }

    @XmlAttribute(name = "LastLqdtyInd")
    @Override
    public LastLiquidityInd getLastLiquidityInd() {
        return lastLiquidityInd;
    }

    @Override
    public void setLastLiquidityInd(LastLiquidityInd lastLiquidityInd) {
        this.lastLiquidityInd = lastLiquidityInd;
    }

    @Override
    public Integer getNoContAmts() {
        return noContAmts;
    }

    @Override
    public void setNoContAmts(Integer noContAmts) {
        this.noContAmts = noContAmts;
        if (noContAmts != null) {
            contAmtGroups = new ContAmtGroup[noContAmts.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < contAmtGroups.length; i++) {
                contAmtGroups[i] = new ContAmtGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public ContAmtGroup[] getContAmtGroups() {
        return contAmtGroups;
    }

    public void setContAmtGroups(ContAmtGroup[] contAmtGroups) {
        this.contAmtGroups = contAmtGroups;
        if (contAmtGroups != null) {
            noContAmts = new Integer(contAmtGroups.length);
        }
    }

    @Override
    public ContAmtGroup addContAmtGroup() {
        ContAmtGroup group = new ContAmtGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<ContAmtGroup> groups = new ArrayList<ContAmtGroup>();
        if (contAmtGroups != null && contAmtGroups.length > 0) {
            groups = new ArrayList<ContAmtGroup>(Arrays.asList(contAmtGroups));
        }
        groups.add(group);
        contAmtGroups = groups.toArray(new ContAmtGroup[groups.size()]);
        noContAmts = new Integer(contAmtGroups.length);

        return group;
    }

    @Override
    public ContAmtGroup deleteContAmtGroup(int index) {
        ContAmtGroup result = null;
        if (contAmtGroups != null && contAmtGroups.length > 0 && contAmtGroups.length > index) {
            List<ContAmtGroup> groups = new ArrayList<ContAmtGroup>(Arrays.asList(contAmtGroups));
            result = groups.remove(index);
            contAmtGroups = groups.toArray(new ContAmtGroup[groups.size()]);
            if (contAmtGroups.length > 0) {
                noContAmts = new Integer(contAmtGroups.length);
            } else {
                contAmtGroups = null;
                noContAmts = null;
            }
        }

        return result;
    }

    @Override
    public int clearContAmtGroups() {
        int result = 0;
        if (contAmtGroups != null && contAmtGroups.length > 0) {
            result = contAmtGroups.length;
            contAmtGroups = null;
            noLegs = null;
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
            instrmtLegExecGroups = new InstrmtLegExecGroup[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < instrmtLegExecGroups.length; i++) {
                instrmtLegExecGroups[i] = new InstrmtLegExecGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public InstrmtLegExecGroup[] getInstrmtLegExecGroups() {
        return instrmtLegExecGroups;
    }

    public void setInstrmtLegExecGroups(InstrmtLegExecGroup[] instrmtLegExecGroups) {
        this.instrmtLegExecGroups = instrmtLegExecGroups;
        if (instrmtLegExecGroups != null) {
            noLegs = new Integer(instrmtLegExecGroups.length);
        }
    }

    @Override
    public InstrmtLegExecGroup addInstrmtLegExecGroup() {
        InstrmtLegExecGroup group = new InstrmtLegExecGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<InstrmtLegExecGroup> groups = new ArrayList<InstrmtLegExecGroup>();
        if (instrmtLegExecGroups != null && instrmtLegExecGroups.length > 0) {
            groups = new ArrayList<InstrmtLegExecGroup>(Arrays.asList(instrmtLegExecGroups));
        }
        groups.add(group);
        instrmtLegExecGroups = groups.toArray(new InstrmtLegExecGroup[groups.size()]);
        noLegs = new Integer(instrmtLegExecGroups.length);

        return group;
    }

    @Override
    public InstrmtLegExecGroup deleteInstrmtLegExecGroup(int index) {
        InstrmtLegExecGroup result = null;
        if (instrmtLegExecGroups != null && instrmtLegExecGroups.length > 0 && instrmtLegExecGroups.length > index) {
            List<InstrmtLegExecGroup> groups = new ArrayList<InstrmtLegExecGroup>(Arrays.asList(instrmtLegExecGroups));
            result = groups.remove(index);
            instrmtLegExecGroups = groups.toArray(new InstrmtLegExecGroup[groups.size()]);
            if (instrmtLegExecGroups.length > 0) {
                noLegs = new Integer(instrmtLegExecGroups.length);
            } else {
                instrmtLegExecGroups = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrmtLegExecGroups() {
        int result = 0;
        if (instrmtLegExecGroups != null && instrmtLegExecGroups.length > 0) {
            result = instrmtLegExecGroups.length;
            instrmtLegExecGroups = null;
            noLegs = null;
        }

        return result;
    }

    @XmlAttribute(name = "CopyMsgInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getCopyMsgIndicator() {
        return copyMsgIndicator;
    }

    @Override
    public void setCopyMsgIndicator(Boolean copyMsgIndicator) {
        this.copyMsgIndicator = copyMsgIndicator;
    }

    @Override
    public Integer getNoMiscFees() {
        return noMiscFees;
    }

    @Override
    public void setNoMiscFees(Integer noMiscFees) {
        this.noMiscFees = noMiscFees;
        if (noMiscFees != null) {
            miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < miscFeeGroups.length; i++) {
                miscFeeGroups[i] = new MiscFeeGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public MiscFeeGroup[] getMiscFeeGroups() {
        return miscFeeGroups;
    }

    public void setMiscFeeGroups(MiscFeeGroup[] miscFeeGroups) {
        this.miscFeeGroups = miscFeeGroups;
        if (miscFeeGroups != null) {
            noMiscFees = new Integer(miscFeeGroups.length);
        }
    }

    @Override
    public MiscFeeGroup addMiscFeeGroup() {
        MiscFeeGroup group = new MiscFeeGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MiscFeeGroup> groups = new ArrayList<MiscFeeGroup>();
        if (miscFeeGroups != null && miscFeeGroups.length > 0) {
            groups = new ArrayList<MiscFeeGroup>(Arrays.asList(miscFeeGroups));
        }
        groups.add(group);
        miscFeeGroups = groups.toArray(new MiscFeeGroup[groups.size()]);
        noMiscFees = new Integer(miscFeeGroups.length);

        return group;
    }

    @Override
    public MiscFeeGroup deleteMiscFeeGroup(int index) {
        MiscFeeGroup result = null;
        if (miscFeeGroups != null && miscFeeGroups.length > 0 && miscFeeGroups.length > index) {
            List<MiscFeeGroup> groups = new ArrayList<MiscFeeGroup>(Arrays.asList(miscFeeGroups));
            result = groups.remove(index);
            miscFeeGroups = groups.toArray(new MiscFeeGroup[groups.size()]);
            if (miscFeeGroups.length > 0) {
                noMiscFees = new Integer(miscFeeGroups.length);
            } else {
                miscFeeGroups = null;
                noMiscFees = null;
            }
        }

        return result;
    }

    @Override
    public int clearMiscFeeGroups() {
        int result = 0;
        if (miscFeeGroups != null && miscFeeGroups.length > 0) {
            result = miscFeeGroups.length;
            miscFeeGroups = null;
            noMiscFees = null;
        }

        return result;
    }

    @XmlAttribute(name = "DividendYield")
    @Override
    public Double getDividendYield() {
        return dividendYield;
    }

    @Override
    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    @XmlAttribute(name = "ManOrdInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getManualOrderIndicator() {
        return manualOrderIndicator;
    }

    @Override
    public void setManualOrderIndicator(Boolean manualOrderIndicator) {
        this.manualOrderIndicator = manualOrderIndicator;
    }

    @XmlAttribute(name = "CustDrctdOrd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getCustDirectedOrder() {
        return custDirectedOrder;
    }

    @Override
    public void setCustDirectedOrder(Boolean custDirectedOrder) {
        this.custDirectedOrder = custDirectedOrder;
    }

    @XmlAttribute(name = "RcvdDptID")
    @Override
    public String getReceivedDeptID() {
        return receivedDeptID;
    }

    @Override
    public void setReceivedDeptID(String receivedDeptID) {
        this.receivedDeptID = receivedDeptID;
    }

    @XmlAttribute(name = "CustOrdHdlInst")
    @Override
    public String getCustOrderHandlingInst() {
        return custOrderHandlingInst;
    }

    @Override
    public void setCustOrderHandlingInst(String custOrderHandlingInst) {
        this.custOrderHandlingInst = custOrderHandlingInst;
    }

    @XmlAttribute(name = "OrdHndlInstSrc")
    @Override
    public Integer getOrderHandlingInstSource() {
        return orderHandlingInstSource;
    }

    @Override
    public void setOrderHandlingInstSource(Integer orderHandlingInstSource) {
        this.orderHandlingInstSource = orderHandlingInstSource;
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
                trdRegTimestampsGroups[i] = new TrdRegTimestampsGroup50SP2(context);
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
        TrdRegTimestampsGroup group = new TrdRegTimestampsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "Vol")
    @Override
    public Double getVolatility() {
        return volatility;
    }

    @Override
    public void setVolatility(Double volatility) {
        this.volatility = volatility;
    }

    @XmlAttribute(name = "TmToExp")
    @Override
    public Double getTimeToExpiration() {
        return timeToExpiration;
    }

    @Override
    public void setTimeToExpiration(Double timeToExpiration) {
        this.timeToExpiration = timeToExpiration;
    }

    @XmlAttribute(name = "RFR")
    @Override
    public Double getRiskFreeRate() {
        return riskFreeRate;
    }

    @Override
    public void setRiskFreeRate(Double riskFreeRate) {
        this.riskFreeRate = riskFreeRate;
    }

    @XmlAttribute(name = "PxDelta")
    @Override
    public Double getPriceDelta() {
        return priceDelta;
    }

    @Override
    public void setPriceDelta(Double priceDelta) {
        this.priceDelta = priceDelta;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (APPL_SEQ_CONTROL_COMP_TAGS.contains(tag.tagNum)) {
            if (applicationSequenceControl == null) {
                applicationSequenceControl = new ApplicationSequenceControl50SP2(context);
            }
            applicationSequenceControl.decode(tag, message);
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP2(context);
            }
            parties.decode(tag, message);
        }
        if (CONTRA_BROKER_GROUP_TAGS.contains(tag.tagNum)) {
            if (noContraBrokers != null && noContraBrokers.intValue() > 0) {
                message.reset();
                contraBrokers = new ContraBrokerGroup[noContraBrokers.intValue()];
                for (int i = 0; i < noContraBrokers.intValue(); i++) {
                    ContraBrokerGroup group = new ContraBrokerGroup50SP2(context);
                    group.decode(message);
                    contraBrokers[i] = group;
                }
            }
        }
        if (ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAllocs != null && noAllocs.intValue() > 0) {
                message.reset();
                allocGroups = new PreTradeAllocGroup[noAllocs.intValue()];
                for (int i = 0; i < noAllocs.intValue(); i++) {
                    PreTradeAllocGroup group = new PreTradeAllocGroup50SP2(context);
                    group.decode(message);
                    allocGroups[i] = group;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50SP2(context);
            }
            instrument.decode(tag, message);
        }
        if (FINANCING_DETAILS_COMP_TAGS.contains(tag.tagNum)) {
            if (financingDetails == null) {
                financingDetails = new FinancingDetails50SP2(context);
            }
            financingDetails.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument component = new UnderlyingInstrument50SP2(context);
                    component.decode(message);
                    underlyingInstruments[i] = component;
                }
            }
        }
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations50SP2(context);
            }
            stipulations.decode(tag, message);
        }
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData50SP2(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (TRIGG_INSTR_COMP_TAGS.contains(tag.tagNum)) {
            if (triggeringInstruction == null) {
                triggeringInstruction = new TriggeringInstruction50SP2(context);
            }
            triggeringInstruction.decode(tag, message);
        }
        if (PEG_INSTRUCTIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (pegInstructions == null) {
                pegInstructions = new PegInstructions50SP2(context);
            }
            pegInstructions.decode(tag, message);
        }
        if (DISCR_INSTR_COMP_TAGS.contains(tag.tagNum)) {
            if (discretionInstructions == null) {
                discretionInstructions = new DiscretionInstructions50SP2(context);
            }
            discretionInstructions.decode(tag, message);
        }
        if (STRATEGY_PARAMS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noStrategyParameters != null && noStrategyParameters.intValue() > 0) {
                message.reset();
                strategyParametersGroups = new StrategyParametersGroup[noStrategyParameters.intValue()];
                for (int i = 0; i < noStrategyParameters.intValue(); i++) {
                    StrategyParametersGroup group = new StrategyParametersGroup50SP2(context);
                    group.decode(message);
                    strategyParametersGroups[i] = group;
                }
            }
        }
        if (FILL_EXECUTION_GROUP_TAGS.contains(tag.tagNum)) {
            if (noFills != null && noFills.intValue() > 0) {
                message.reset();
                fillExecGroups = new FillExecGroup[noFills.intValue()];
                for (int i = 0; i < noFills.intValue(); i++) {
                    FillExecGroup group = new FillExecGroup50SP2(context);
                    group.decode(message);
                    fillExecGroups[i] = group;
                }
            }
        }
        if (COMMISSION_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (commissionData == null) {
                commissionData = new CommissionData50SP2(context);
            }
            commissionData.decode(tag, message);
        }
        if (SPREAD_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50SP2(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
        if (YIELD_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (yieldData == null) {
                yieldData = new YieldData50SP2(context);
            }
            yieldData.decode(tag, message);
        }
        if (RATE_SOURCE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRateSources != null && noRateSources.intValue() > 0) {
                message.reset();
                rateSources = new RateSourceGroup[noRateSources.intValue()];
                for (int i = 0; i < noRateSources.intValue(); i++) {
                    RateSourceGroup component = new RateSourceGroup50SP2(context);
                    component.decode(message);
                    rateSources[i] = component;
                }
            }
        }
        if (DISPLAY_INSTRUCTION_COMP_TAGS.contains(tag.tagNum)) {
            if (displayInstruction == null) {
                displayInstruction = new DisplayInstruction50SP2(context);
            }
            displayInstruction.decode(tag, message);
        }
        if (CONT_AMT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noContAmts != null && noContAmts.intValue() > 0) {
                message.reset();
                contAmtGroups = new ContAmtGroup[noContAmts.intValue()];
                for (int i = 0; i < noContAmts.intValue(); i++) {
                    ContAmtGroup component = new ContAmtGroup50SP2(context);
                    component.decode(message);
                    contAmtGroups[i] = component;
                }
            }
        }
        if (INSTRMT_LEG_EXEC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrmtLegExecGroups = new InstrmtLegExecGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrmtLegExecGroup component = new InstrmtLegExecGroup50SP2(context);
                    component.decode(message);
                    instrmtLegExecGroups[i] = component;
                }
            }
        }
        if (MISC_FEE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMiscFees != null && noMiscFees.intValue() > 0) {
                message.reset();
                miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
                for (int i = 0; i < noMiscFees.intValue(); i++) {
                    MiscFeeGroup component = new MiscFeeGroup50SP2(context);
                    component.decode(message);
                    miscFeeGroups[i] = component;
                }
            }
        }
        if (TRD_REG_TSTAMP_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTrdRegTimestamps != null && noTrdRegTimestamps.intValue() > 0) {
                message.reset();
                trdRegTimestampsGroups = new TrdRegTimestampsGroup[noTrdRegTimestamps.intValue()];
                for (int i = 0; i < noTrdRegTimestamps.intValue(); i++) {
                    TrdRegTimestampsGroup group = new TrdRegTimestampsGroup50SP2(context);
                    group.decode(message);
                    trdRegTimestampsGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ExecutionReportMsg] message version [5.0SP2].";
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
