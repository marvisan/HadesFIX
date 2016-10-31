/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportMsg43.java
 *
 * $Id: ExecutionReportMsg43.java,v 1.4 2011-10-21 10:31:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.ExecutionReportMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v43.SpreadOrBenchmarkCurveData43;
import net.hades.fix.message.comp.impl.v43.Stipulations43;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.group.impl.v43.MiscFeeGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.Header;
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.impl.v43.CommissionData43;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.comp.impl.v43.OrderQtyData43;
import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.comp.impl.v43.YieldData43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ContAmtGroup;
import net.hades.fix.message.group.ContraBrokerGroup;
import net.hades.fix.message.group.InstrmtLegExecGroup;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.impl.v43.ContAmtGroup43;
import net.hades.fix.message.group.impl.v43.ContraBrokerGroup43;
import net.hades.fix.message.group.impl.v43.InstrmtLegExecGroup43;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.ExecPriceType;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.LastCapacity;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdRejReason;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.PriorityIndicator;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

/**
 * FIX version 4.3 ExecutionReport implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class ExecutionReportMsg43 extends ExecutionReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.SecondaryExecID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.OrigClOrdID.getValue(),
        TagNum.ClOrdLinkID.getValue(),
        TagNum.TradeOriginationDate.getValue(),
        TagNum.NoContraBrokers.getValue(),
        TagNum.ListID.getValue(),
        TagNum.CrossID.getValue(),
        TagNum.OrigCrossID.getValue(),
        TagNum.CrossType.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.ExecRefID.getValue(),
        TagNum.ExecType.getValue(),
        TagNum.OrdStatus.getValue(),
        TagNum.WorkingIndicator.getValue(),
        TagNum.OrdRejReason.getValue(),
        TagNum.ExecRestatementReason.getValue(),
        TagNum.Account.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.DayBookingInst.getValue(),
        TagNum.BookingUnit.getValue(),
        TagNum.PreallocMethod.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.CashMargin.getValue(),
        TagNum.ClearingFeeIndicator.getValue(),
        TagNum.Side.getValue(),
        TagNum.QuantityType.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.Price.getValue(),
        TagNum.StopPx.getValue(),
        TagNum.TargetStrategyPerformance.getValue(),
        TagNum.PegOffsetValue.getValue(),
        TagNum.DiscretionInst.getValue(),
        TagNum.DiscretionOffsetValue.getValue(),
        TagNum.Currency.getValue(),
        TagNum.ComplianceID.getValue(),
        TagNum.SolicitedFlag.getValue(),
        TagNum.TimeInForce.getValue(),
        TagNum.EffectiveTime.getValue(),
        TagNum.ExpireDate.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.CustOrderCapacity.getValue(),
        TagNum.Rule80A.getValue(),
        TagNum.LastQty.getValue(),
        TagNum.UnderlyingLastQty.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.LastSpotRate.getValue(),
        TagNum.LastForwardPoints.getValue(),
        TagNum.LastMkt.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.LastCapacity.getValue(),
        TagNum.LeavesQty.getValue(),
        TagNum.CumQty.getValue(),
        TagNum.AvgPx.getValue(),
        TagNum.DayOrderQty.getValue(),
        TagNum.DayCumQty.getValue(),
        TagNum.DayAvgPx.getValue(),
        TagNum.GTBookingInst.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.ReportToExch.getValue(),
        TagNum.GrossTradeAmt.getValue(),
        TagNum.NumDaysInterest.getValue(),
        TagNum.ExDate.getValue(),
        TagNum.AccruedInterestRate.getValue(),
        TagNum.AccruedInterestAmt.getValue(),
        TagNum.TradedFlatSwitch.getValue(),
        TagNum.BasisFeatureDate.getValue(),
        TagNum.BasisFeaturePrice.getValue(),
        TagNum.Concession.getValue(),
        TagNum.TotalTakedown.getValue(),
        TagNum.NetMoney.getValue(),
        TagNum.SettlCurrAmt.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.SettlCurrFxRate.getValue(),
        TagNum.SettlCurrFxRateCalc.getValue(),
        TagNum.HandlInst.getValue(),
        TagNum.MinQty.getValue(),
        TagNum.MaxFloor.getValue(),
        TagNum.PositionEffect.getValue(),
        TagNum.MaxShow.getValue(),
        TagNum.Text.getValue(),
        TagNum.SettlDate2.getValue(),
        TagNum.OrderQty2.getValue(),
        TagNum.ClearingFirm.getValue(),
        TagNum.MultiLegReportingType.getValue(),
        TagNum.CancellationRights.getValue(),
        TagNum.MoneyLaunderingStatus.getValue(),
        TagNum.RegistID.getValue(),
        TagNum.Designation.getValue(),
        TagNum.TransBkdTime.getValue(),
        TagNum.ExecValuationPoint.getValue(),
        TagNum.ExecPriceType.getValue(),
        TagNum.ExecPriceAdjustment.getValue(),
        TagNum.PriorityIndicator.getValue(),
        TagNum.PriceImprovement.getValue(),
        TagNum.NoContAmts.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.NoMiscFees.getValue()
    }));

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    protected static final Set<Integer> CONTRA_BROKER_GROUP_TAGS = new ContraBrokerGroup43().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations43().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData43().getFragmentAllTags();
    protected static final Set<Integer> COMMISSION_DATA_COMP_TAGS = new CommissionData43().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData43().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData43().getFragmentAllTags();
    protected static final Set<Integer> CONT_AMT_GROUP_TAGS = new ContAmtGroup43().getFragmentAllTags();
    protected static final Set<Integer> INSTRMT_LEG_EXEC_GROUP_TAGS = new InstrmtLegExecGroup43().getFragmentAllTags();
    protected static final Set<Integer> MISC_FEE_GROUP_TAGS = new MiscFeeGroup43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(CONTRA_BROKER_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(CONT_AMT_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRMT_LEG_EXEC_GROUP_TAGS);
        ALL_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(CONTRA_BROKER_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(CONT_AMT_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRMT_LEG_EXEC_GROUP_TAGS);
        START_COMP_TAGS.addAll(MISC_FEE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V43;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ExecutionReportMsg43() {
        super();
    }

    public ExecutionReportMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ExecutionReportMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ExecutionReportMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V43;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    @Override
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    @Override
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    @Override
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    @Override
    public String getSecondaryExecID() {
        return secondaryExecID;
    }

    @Override
    public void setSecondaryExecID(String secondaryExecID) {
        this.secondaryExecID = secondaryExecID;
    }

    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @Override
    public String getOrigClOrdID() {
        return origClOrdID;
    }

    @Override
    public void setOrigClOrdID(String origClOrdID) {
        this.origClOrdID = origClOrdID;
    }

    @Override
    public String getClOrdLinkID() {
        return clOrdLinkID;
    }

    @Override
    public void setClOrdLinkID(String clOrdLinkID) {
        this.clOrdLinkID = clOrdLinkID;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.parties = new Parties43(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    public PartyGroup[] getPartyIDGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

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
                contraBrokers[i] = new ContraBrokerGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

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
        ContraBrokerGroup group = new ContraBrokerGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    @Override
    public String getListID() {
        return listID;
    }

    @Override
    public void setListID(String listID) {
        this.listID = listID;
    }

    @Override
    public String getCrossID() {
        return crossID;
    }

    @Override
    public void setCrossID(String crossID) {
        this.crossID = crossID;
    }

    @Override
    public String getOrigCrossID() {
        return origCrossID;
    }

    @Override
    public void setOrigCrossID(String origCrossID) {
        this.origCrossID = origCrossID;
    }

    @Override
    public CrossType getCrossType() {
        return crossType;
    }

    @Override
    public void setCrossType(CrossType crossType) {
        this.crossType = crossType;
    }

    @Override
    public String getExecID() {
        return execID;
    }

    @Override
    public void setExecID(String execID) {
        this.execID = execID;
    }

    @Override
    public String getExecRefID() {
        return execRefID;
    }

    @Override
    public void setExecRefID(String execRefID) {
        this.execRefID = execRefID;
    }

    @Override
    public ExecType getExecType() {
        return execType;
    }

    @Override
    public void setExecType(ExecType execType) {
        this.execType = execType;
    }

    @Override
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    @Override
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    @Override
    public Boolean getWorkingIndicator() {
        return workingIndicator;
    }

    @Override
    public void setWorkingIndicator(Boolean workingIndicator) {
        this.workingIndicator = workingIndicator;
    }

    @Override
    public OrdRejReason getOrdRejReason() {
        return ordRejReason;
    }

    @Override
    public void setOrdRejReason(OrdRejReason ordRejReason) {
        this.ordRejReason = ordRejReason;
    }

    @Override
    public ExecRestatementReason getExecRestatementReason() {
        return execRestatementReason;
    }

    @Override
    public void setExecRestatementReason(ExecRestatementReason execRestatementReason) {
        this.execRestatementReason = execRestatementReason;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public DayBookingInst getDayBookingInst() {
        return dayBookingInst;
    }

    @Override
    public void setDayBookingInst(DayBookingInst dayBookingInst) {
        this.dayBookingInst = dayBookingInst;
    }

    @Override
    public BookingUnit getBookingUnit() {
        return bookingUnit;
    }

    @Override
    public void setBookingUnit(BookingUnit bookingUnit) {
        this.bookingUnit = bookingUnit;
    }

    @Override
    public PreallocMethod getPreallocMethod() {
        return preallocMethod;
    }

    @Override
    public void setPreallocMethod(PreallocMethod preallocMethod) {
        this.preallocMethod = preallocMethod;
    }

    @Override
    public String getSettlType() {
        return settlType;
    }

    @Override
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    @Override
    public Date getSettlDate() {
        return settlDate;
    }

    @Override
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    @Override
    public CashMargin getCashMargin() {
        return cashMargin;
    }

    @Override
    public void setCashMargin(CashMargin cashMargin) {
        this.cashMargin = cashMargin;
    }

    @Override
    public ClearingFeeIndicator getClearingFeeIndicator() {
        return clearingFeeIndicator;
    }

    @Override
    public void setClearingFeeIndicator(ClearingFeeIndicator clearingFeeIndicator) {
        this.clearingFeeIndicator = clearingFeeIndicator;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument43(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.stipulations = new Stipulations43(context);
    }

    @Override
    public void clearStipulations() {
         this.stipulations = null;
    }

    public void setStipulations(Stipulations stipulations) {
        this.stipulations = stipulations;
    }

    public StipulationsGroup[] getStipulationsGroups() {
        return stipulations == null ? null : stipulations.getStipulationsGroups();
    }

    public void setStipulationsGroups(StipulationsGroup[] stipulationsGroups) {
        if (stipulationsGroups != null) {
            if (stipulations == null) {
                setStipulations();
            }
            ((Stipulations43) stipulations).setStipulationsGroups(stipulationsGroups);
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

    @Override
    public OrderQtyData getOrderQtyData() {
        return orderQtyData;
    }

    @Override
    public void setOrderQtyData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.orderQtyData = new OrderQtyData43(context);
    }

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
    }

    @Override
    public OrdType getOrdType() {
        return ordType;
    }

    @Override
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
    }

    @Override
    public PriceType getPriceType() {
        return priceType;
    }

    @Override
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public Double getStopPx() {
        return stopPx;
    }

    @Override
    public void setStopPx(Double stopPx) {
        this.stopPx = stopPx;
    }

    @Override
    public Double getPegOffsetValue() {
        return pegOffsetValue;
    }

    @Override
    public void setPegOffsetValue(Double pegOffsetValue) {
        this.pegOffsetValue = pegOffsetValue;
    }

    @Override
    public DiscretionInst getDiscretionInst() {
        return discretionInst;
    }

    @Override
    public void setDiscretionInst(DiscretionInst discretionInst) {
        this.discretionInst = discretionInst;
    }

    @Override
    public Double getDiscretionOffsetValue() {
        return discretionOffsetValue;
    }

    @Override
    public void setDiscretionOffsetValue(Double discretionOffsetValue) {
        this.discretionOffsetValue = discretionOffsetValue;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String getComplianceID() {
        return complianceID;
    }

    @Override
    public void setComplianceID(String complianceID) {
        this.complianceID = complianceID;
    }

    @Override
    public Boolean getSolicitedFlag() {
        return solicitedFlag;
    }

    @Override
    public void setSolicitedFlag(Boolean solicitedFlag) {
        this.solicitedFlag = solicitedFlag;
    }

    @Override
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    @Override
    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    @Override
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    @Override
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    @Override
    public Date getExpireDate() {
        return expireDate;
    }

    @Override
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public Date getExpireTime() {
        return expireTime;
    }

    @Override
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String getExecInst() {
        return execInst;
    }

    @Override
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    @Override
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    @Override
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    @Override
    public String getOrderRestrictions() {
        return orderRestrictions;
    }

    @Override
    public void setOrderRestrictions(String orderRestrictions) {
        this.orderRestrictions = orderRestrictions;
    }

    @Override
    public CustOrderCapacity getCustOrderCapacity() {
        return custOrderCapacity;
    }

    @Override
    public void setCustOrderCapacity(CustOrderCapacity custOrderCapacity) {
        this.custOrderCapacity = custOrderCapacity;
    }

    @Override
    public Rule80A getRule80A() {
        return rule80A;
    }

    @Override
    public void setRule80A(Rule80A rule80A) {
        this.rule80A = rule80A;
    }

    @Override
    public Double getLastQty() {
        return lastQty;
    }

    @Override
    public void setLastQty(Double lastQty) {
        this.lastQty = lastQty;
    }

    @Override
    public Double getUnderlyingLastQty() {
        return underlyingLastQty;
    }

    @Override
    public void setUnderlyingLastQty(Double underlyingLastQty) {
        this.underlyingLastQty = underlyingLastQty;
    }

    @Override
    public Double getLastPx() {
        return lastPx;
    }

    @Override
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    @Override
    public Double getLastSpotRate() {
        return lastSpotRate;
    }

    @Override
    public void setLastSpotRate(Double lastSpotRate) {
        this.lastSpotRate = lastSpotRate;
    }

    @Override
    public Double getLastForwardPoints() {
        return lastForwardPoints;
    }

    @Override
    public void setLastForwardPoints(Double lastForwardPoints) {
        this.lastForwardPoints = lastForwardPoints;
    }

    @Override
    public String getLastMkt() {
        return lastMkt;
    }

    @Override
    public void setLastMkt(String lastMkt) {
        this.lastMkt = lastMkt;
    }

    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @Override
    public LastCapacity getLastCapacity() {
        return lastCapacity;
    }

    @Override
    public void setLastCapacity(LastCapacity lastCapacity) {
        this.lastCapacity = lastCapacity;
    }

    @Override
    public Double getLeavesQty() {
        return leavesQty;
    }

    @Override
    public void setLeavesQty(Double leavesQty) {
        this.leavesQty = leavesQty;
    }

    @Override
    public Double getCumQty() {
        return cumQty;
    }

    @Override
    public void setCumQty(Double cumQty) {
        this.cumQty = cumQty;
    }

    @Override
    public Double getAvgPx() {
        return avgPx;
    }

    @Override
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
    }

    @Override
    public Double getDayOrderQty() {
        return dayOrderQty;
    }

    @Override
    public void setDayOrderQty(Double dayOrderQty) {
        this.dayOrderQty = dayOrderQty;
    }

    @Override
    public Double getDayCumQty() {
        return dayCumQty;
    }

    @Override
    public void setDayCumQty(Double dayCumQty) {
        this.dayCumQty = dayCumQty;
    }

    @Override
    public Double getDayAvgPx() {
        return dayAvgPx;
    }

    @Override
    public void setDayAvgPx(Double dayAvgPx) {
        this.dayAvgPx = dayAvgPx;
    }

    @Override
    public GTBookingInst getGTBookingInst() {
        return GTBookingInst;
    }

    @Override
    public void setGTBookingInst(GTBookingInst GTBookingInst) {
        this.GTBookingInst = GTBookingInst;
    }

    @Override
    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @Override
    public Boolean getReportToExch() {
        return reportToExch;
    }

    @Override
    public void setReportToExch(Boolean reportToExch) {
        this.reportToExch = reportToExch;
    }

    @Override
    public CommissionData getCommissionData() {
        return commissionData;
    }

    public void setCommissionData(CommissionData commissionData) {
        this.commissionData = commissionData;
    }

    @Override
    public void setCommissionData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.commissionData = new CommissionData43(context);
    }

    @Override
    public void clearCommissionData() {
        commissionData = null;
    }

    @Override
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData43(context);
    }

    @Override
    public void clearSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = null;
    }

    public void setSpreadOrBenchmarkCurveData(SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData) {
        this.spreadOrBenchmarkCurveData = spreadOrBenchmarkCurveData;
    }

    @Override
    public YieldData getYieldData() {
        return yieldData;
    }

    @Override
    public void setYieldData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.yieldData = new YieldData43(context);
    }

    @Override
    public void clearYieldData() {
        this.yieldData = null;
    }

    public void setYieldData(YieldData yieldData) {
        this.yieldData = yieldData;
    }

    @Override
    public Double getGrossTradeAmt() {
        return grossTradeAmt;
    }

    @Override
    public void setGrossTradeAmt(Double grossTradeAmt) {
        this.grossTradeAmt = grossTradeAmt;
    }

    @Override
    public Integer getNumDaysInterest() {
        return numDaysInterest;
    }

    @Override
    public void setNumDaysInterest(Integer numDaysInterest) {
        this.numDaysInterest = numDaysInterest;
    }

    @Override
    public Date getExDate() {
        return exDate;
    }

    @Override
    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    @Override
    public Double getAccruedInterestRate() {
        return accruedInterestRate;
    }

    @Override
    public void setAccruedInterestRate(Double accruedInterestRate) {
        this.accruedInterestRate = accruedInterestRate;
    }

    @Override
    public Double getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    @Override
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
    }

    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getTradedFlatSwitch() {
        return tradedFlatSwitch;
    }

    @Override
    public void setTradedFlatSwitch(Boolean tradedFlatSwitch) {
        this.tradedFlatSwitch = tradedFlatSwitch;
    }

    @Override

    public Date getBasisFeatureDate() {
        return basisFeatureDate;
    }

    @Override
    public void setBasisFeatureDate(Date basisFeatureDate) {
        this.basisFeatureDate = basisFeatureDate;
    }

    @Override
    public Double getBasisFeaturePrice() {
        return basisFeaturePrice;
    }

    @Override
    public void setBasisFeaturePrice(Double basisFeaturePrice) {
        this.basisFeaturePrice = basisFeaturePrice;
    }

    @Override
    public Double getConcession() {
        return concession;
    }

    @Override
    public void setConcession(Double concession) {
        this.concession = concession;
    }

    @Override
    public Double getTotalTakedown() {
        return totalTakedown;
    }

    @Override
    public void setTotalTakedown(Double totalTakedown) {
        this.totalTakedown = totalTakedown;
    }

    @Override
    public Double getNetMoney() {
        return netMoney;
    }

    @Override
    public void setNetMoney(Double netMoney) {
        this.netMoney = netMoney;
    }

    @Override
    public Double getSettlCurrAmt() {
        return settlCurrAmt;
    }

    @Override
    public void setSettlCurrAmt(Double settlCurrAmt) {
        this.settlCurrAmt = settlCurrAmt;
    }

    @Override
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    @Override
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    @Override
    public Double getSettlCurrFxRate() {
        return settlCurrFxRate;
    }

    @Override
    public void setSettlCurrFxRate(Double settlCurrFxRate) {
        this.settlCurrFxRate = settlCurrFxRate;
    }

    @Override
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    @Override
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }

    @Override
    public HandlInst getHandlInst() {
        return handlInst;
    }

    @Override
    public void setHandlInst(HandlInst handlInst) {
        this.handlInst = handlInst;
    }

    @Override
    public Double getMinQty() {
        return minQty;
    }

    @Override
    public void setMinQty(Double minQty) {
        this.minQty = minQty;
    }

    @Override
    public Double getMaxFloor() {
        return maxFloor;
    }

    @Override
    public void setMaxFloor(Double maxFloor) {
        this.maxFloor = maxFloor;
    }

    @Override
    public PositionEffect getPositionEffect() {
        return positionEffect;
    }

    @Override
    public void setPositionEffect(PositionEffect positionEffect) {
        this.positionEffect = positionEffect;
    }

    @Override
    public Double getMaxShow() {
        return maxShow;
    }

    @Override
    public void setMaxShow(Double maxShow) {
        this.maxShow = maxShow;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

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

    @Override
    public Date getSettlDate2() {
        return settlDate2;
    }

    @Override
    public void setSettlDate2(Date settlDate2) {
        this.settlDate2 = settlDate2;
    }

    @Override
    public Double getOrderQty2() {
        return orderQty2;
    }

    @Override
    public void setOrderQty2(Double orderQty2) {
        this.orderQty2 = orderQty2;
    }

    @Override
    public MultiLegReportingType getMultilegReportingType() {
        return multilegReportingType;
    }

    @Override
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        this.multilegReportingType = multilegReportingType;
    }

    @Override
    public CancellationRights getCancellationRights() {
        return cancellationRights;
    }

    @Override
    public void setCancellationRights(CancellationRights cancellationRights) {
        this.cancellationRights = cancellationRights;
    }

    @Override
    public MoneyLaunderingStatus getMoneyLaunderingStatus() {
        return moneyLaunderingStatus;
    }

    @Override
    public void setMoneyLaunderingStatus(MoneyLaunderingStatus moneyLaunderingStatus) {
        this.moneyLaunderingStatus = moneyLaunderingStatus;
    }

    @Override
    public String getRegistID() {
        return registID;
    }

    @Override
    public void setRegistID(String registID) {
        this.registID = registID;
    }

    @Override
    public String getDesignation() {
        return designation;
    }

    @Override
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    @Override
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
    }

    @Override
    public Date getExecValuationPoint() {
        return execValuationPoint;
    }

    @Override
    public void setExecValuationPoint(Date execValuationPoint) {
        this.execValuationPoint = execValuationPoint;
    }

    @Override
    public ExecPriceType getExecPriceType() {
        return execPriceType;
    }

    @Override
    public void setExecPriceType(ExecPriceType execPriceType) {
        this.execPriceType = execPriceType;
    }

    @Override
    public Double getExecPriceAdjustment() {
        return execPriceAdjustment;
    }

    @Override
    public void setExecPriceAdjustment(Double execPriceAdjustment) {
        this.execPriceAdjustment = execPriceAdjustment;
    }

    @Override
    public PriorityIndicator getPriorityIndicator() {
        return priorityIndicator;
    }

    @Override
    public void setPriorityIndicator(PriorityIndicator priorityIndicator) {
        this.priorityIndicator = priorityIndicator;
    }

    @Override
    public Double getPriceImprovement() {
        return priceImprovement;
    }

    @Override
    public void setPriceImprovement(Double priceImprovement) {
        this.priceImprovement = priceImprovement;
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < contAmtGroups.length; i++) {
                contAmtGroups[i] = new ContAmtGroup43(context);
            }
        }
    }

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
        ContAmtGroup group = new ContAmtGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < instrmtLegExecGroups.length; i++) {
                instrmtLegExecGroups[i] = new InstrmtLegExecGroup43(context);
            }
        }
    }

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
        InstrmtLegExecGroup group = new InstrmtLegExecGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    @Override
    public Integer getNoMiscFees() {
        return noMiscFees;
    }

    @Override
    public void setNoMiscFees(Integer noMiscFees) {
        this.noMiscFees = noMiscFees;
        if (noMiscFees != null) {
            miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < miscFeeGroups.length; i++) {
                miscFeeGroups[i] = new MiscFeeGroup43(context);
            }
        }
    }

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
        MiscFeeGroup group = new MiscFeeGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryOrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryExecID, secondaryExecID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.OrigClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrigClOrdID, origClOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdLinkID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdLinkID, clOrdLinkID);
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.TradeOriginationDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            }
            if (noContraBrokers != null && MsgUtil.isTagInList(TagNum.NoContraBrokers, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoContraBrokers, noContraBrokers);
                if (contraBrokers != null && contraBrokers.length == noContraBrokers.intValue()) {
                    for (int i = 0; i < noContraBrokers.intValue(); i++) {
                        if (contraBrokers[i] != null) {
                            bao.write(contraBrokers[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "ContraBrokerGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoContraBrokers.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.ListID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListID, listID);
            }
            if (MsgUtil.isTagInList(TagNum.CrossID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CrossID, crossID);
            }
            if (MsgUtil.isTagInList(TagNum.OrigCrossID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrigCrossID, origCrossID);
            }
            if (crossType != null && MsgUtil.isTagInList(TagNum.CrossType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CrossType, crossType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecID, execID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecRefID, execRefID);
            }
            if (execType != null && MsgUtil.isTagInList(TagNum.ExecType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecType, execType.getValue());
            }
            if (ordStatus != null && MsgUtil.isTagInList(TagNum.OrdStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.WorkingIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.WorkingIndicator, workingIndicator);
            }
            if (ordRejReason != null && MsgUtil.isTagInList(TagNum.OrdRejReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdRejReason, ordRejReason.getValue());
            }
            if (execRestatementReason != null && MsgUtil.isTagInList(TagNum.ExecRestatementReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecRestatementReason, execRestatementReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Account, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Account, account);
            }
            if (accountType != null && MsgUtil.isTagInList(TagNum.AccountType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (dayBookingInst != null && MsgUtil.isTagInList(TagNum.DayBookingInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DayBookingInst, dayBookingInst.getValue());
            }
            if (bookingUnit != null && MsgUtil.isTagInList(TagNum.BookingUnit, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BookingUnit, bookingUnit.getValue());
            }
            if (preallocMethod != null && MsgUtil.isTagInList(TagNum.PreallocMethod, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PreallocMethod, preallocMethod.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SettlType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlType, settlType);
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            }
            if (cashMargin != null && MsgUtil.isTagInList(TagNum.CashMargin, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashMargin, cashMargin.getValue());
            }
            if (clearingFeeIndicator != null && MsgUtil.isTagInList(TagNum.ClearingFeeIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (stipulations != null) {
                bao.write(stipulations.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (quantityType != null && MsgUtil.isTagInList(TagNum.QuantityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuantityType, quantityType.getValue());
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (ordType != null && MsgUtil.isTagInList(TagNum.OrdType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            if (priceType != null && MsgUtil.isTagInList(TagNum.PriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Price, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Price, price);
            }
            if (MsgUtil.isTagInList(TagNum.StopPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StopPx, stopPx);
            }
            if (MsgUtil.isTagInList(TagNum.PegOffsetValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            }
            if (discretionInst != null && MsgUtil.isTagInList(TagNum.DiscretionInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionInst, discretionInst.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.DiscretionOffsetValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionOffsetValue, discretionOffsetValue);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ComplianceID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ComplianceID, complianceID);
            }
            if (MsgUtil.isTagInList(TagNum.SolicitedFlag, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SolicitedFlag, solicitedFlag);
            }
            if (timeInForce != null && MsgUtil.isTagInList(TagNum.TimeInForce, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.EffectiveTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            }
            if (MsgUtil.isTagInList(TagNum.ExpireDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.ExpireDate, expireDate);
            }
            if (MsgUtil.isTagInList(TagNum.ExpireTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            }
            if (MsgUtil.isTagInList(TagNum.ExecInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            }
            if (orderCapacity != null && MsgUtil.isTagInList(TagNum.OrderCapacity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OrderRestrictions, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderRestrictions, orderRestrictions);
            }
            if (custOrderCapacity != null && MsgUtil.isTagInList(TagNum.CustOrderCapacity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CustOrderCapacity, custOrderCapacity.getValue());
            }
            if (rule80A != null && MsgUtil.isTagInList(TagNum.Rule80A, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Rule80A, rule80A.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.LastQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastQty, lastQty);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingLastQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingLastQty, underlyingLastQty);
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
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (lastCapacity != null && MsgUtil.isTagInList(TagNum.LastCapacity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastCapacity, lastCapacity.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.LeavesQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LeavesQty, leavesQty);
            }
            if (MsgUtil.isTagInList(TagNum.CumQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CumQty, cumQty);
            }
            if (MsgUtil.isTagInList(TagNum.AvgPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            }
            if (MsgUtil.isTagInList(TagNum.DayOrderQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DayOrderQty, dayOrderQty);
            }
            if (MsgUtil.isTagInList(TagNum.DayCumQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DayCumQty, dayCumQty);
            }
            if (MsgUtil.isTagInList(TagNum.DayAvgPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DayAvgPx, dayAvgPx);
            }
            if (GTBookingInst != null && MsgUtil.isTagInList(TagNum.GTBookingInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.GTBookingInst, GTBookingInst.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeDate, tradeDate);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.ReportToExch, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ReportToExch, reportToExch);
            }
            if (commissionData != null) {
                bao.write(commissionData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.GrossTradeAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.GrossTradeAmt, grossTradeAmt);
            }
            if (MsgUtil.isTagInList(TagNum.NumDaysInterest, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NumDaysInterest, numDaysInterest);
            }
            if (MsgUtil.isTagInList(TagNum.ExDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.ExDate, exDate);
            }
            if (MsgUtil.isTagInList(TagNum.AccruedInterestRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccruedInterestRate, accruedInterestRate);
            }
            if (MsgUtil.isTagInList(TagNum.AccruedInterestAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            }
            if (MsgUtil.isTagInList(TagNum.TradedFlatSwitch, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradedFlatSwitch, tradedFlatSwitch);
            }
            if (MsgUtil.isTagInList(TagNum.BasisFeatureDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.BasisFeatureDate, basisFeatureDate);
            }
            if (MsgUtil.isTagInList(TagNum.BasisFeaturePrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BasisFeaturePrice, basisFeaturePrice);
            }
            if (MsgUtil.isTagInList(TagNum.Concession, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Concession, concession);
            }
            if (MsgUtil.isTagInList(TagNum.TotalTakedown, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotalTakedown, totalTakedown);
            }
            if (MsgUtil.isTagInList(TagNum.NetMoney, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NetMoney, netMoney);
            }
            if (MsgUtil.isTagInList(TagNum.SettlCurrAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrAmt, settlCurrAmt);
            }
            if (settlCurrency != null && MsgUtil.isTagInList(TagNum.SettlCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SettlCurrFxRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRate, settlCurrFxRate);
            }
            if (settlCurrFxRateCalc != null && MsgUtil.isTagInList(TagNum.SettlCurrFxRateCalc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc.getValue());
            }
            if (handlInst != null && MsgUtil.isTagInList(TagNum.HandlInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.HandlInst, handlInst.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MinQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MinQty, minQty);
            }
            if (MsgUtil.isTagInList(TagNum.MaxFloor, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaxFloor, maxFloor);
            }
            if (positionEffect != null && MsgUtil.isTagInList(TagNum.PositionEffect, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PositionEffect, positionEffect.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MaxShow, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaxShow, maxShow);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate2, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate2, settlDate2);
            }
            if (MsgUtil.isTagInList(TagNum.OrderQty2, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderQty2, orderQty2);
            }
            if (multilegReportingType != null && MsgUtil.isTagInList(TagNum.MultiLegReportingType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MultiLegReportingType, multilegReportingType.getValue());
            }
            if (cancellationRights != null && MsgUtil.isTagInList(TagNum.CancellationRights, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CancellationRights, cancellationRights.getValue());
            }
            if (moneyLaunderingStatus != null && MsgUtil.isTagInList(TagNum.MoneyLaunderingStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.RegistID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistID, registID);
            }
            if (MsgUtil.isTagInList(TagNum.Designation, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Designation, designation);
            }
            if (MsgUtil.isTagInList(TagNum.TransBkdTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransBkdTime, transBkdTime);
            }
            if (MsgUtil.isTagInList(TagNum.ExecValuationPoint, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ExecValuationPoint, execValuationPoint);
            }
            if (execPriceType != null && MsgUtil.isTagInList(TagNum.ExecPriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecPriceType, execPriceType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExecPriceAdjustment, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecPriceAdjustment, execPriceAdjustment);
            }
            if (priorityIndicator != null && MsgUtil.isTagInList(TagNum.PriorityIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PriorityIndicator, priorityIndicator.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PriceImprovement, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PriceImprovement, priceImprovement);
            }
            if (noContAmts != null && MsgUtil.isTagInList(TagNum.NoContAmts, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoContAmts, noContAmts);
                if (contAmtGroups != null && contAmtGroups.length == noContAmts.intValue()) {
                    for (int i = 0; i < noContAmts.intValue(); i++) {
                        if (contAmtGroups[i] != null) {
                            bao.write(contAmtGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "ContAmtGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoContAmts.getValue(), error);
                }
            }
            if (noLegs != null && MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrmtLegExecGroups != null && instrmtLegExecGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrmtLegExecGroups[i] != null) {
                            bao.write(instrmtLegExecGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "InstrmtLegExecGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            if (noMiscFees != null && MsgUtil.isTagInList(TagNum.NoMiscFees, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoMiscFees, noMiscFees);
                if (miscFeeGroups != null && miscFeeGroups.length == noMiscFees.intValue()) {
                    for (int i = 0; i < noMiscFees.intValue(); i++) {
                        if (miscFeeGroups[i] != null) {
                            bao.write(miscFeeGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "MiscFeeGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoMiscFees.getValue(), error);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties43(context);
            }
            parties.decode(tag, message);
        }
        if (CONTRA_BROKER_GROUP_TAGS.contains(tag.tagNum)) {
            if (noContraBrokers != null && noContraBrokers.intValue() > 0) {
                message.reset();
                contraBrokers = new ContraBrokerGroup[noContraBrokers.intValue()];
                for (int i = 0; i < noContraBrokers.intValue(); i++) {
                    ContraBrokerGroup group = new ContraBrokerGroup43(context);
                    group.decode(message);
                    contraBrokers[i] = group;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations43(context);
            }
            stipulations.decode(tag, message);
        }
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData43(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (COMMISSION_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (commissionData == null) {
                commissionData = new CommissionData43(context);
            }
            commissionData.decode(tag, message);
        }
        if (SPREAD_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData43(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
        if (YIELD_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (yieldData == null) {
                yieldData = new YieldData43(context);
            }
            yieldData.decode(tag, message);
        }
        if (CONT_AMT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noContAmts != null && noContAmts.intValue() > 0) {
                message.reset();
                contAmtGroups = new ContAmtGroup[noContAmts.intValue()];
                for (int i = 0; i < noContAmts.intValue(); i++) {
                    ContAmtGroup component = new ContAmtGroup43(context);
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
                    InstrmtLegExecGroup component = new InstrmtLegExecGroup43(context);
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
                    MiscFeeGroup component = new MiscFeeGroup43(context);
                    component.decode(message);
                    miscFeeGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ExecutionReportMsg] message version [4.3].";
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
