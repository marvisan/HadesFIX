/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderSingleMsg50SP2.java
 *
 * $Id: NewOrderSingleMsg50SP2.java,v 1.7 2011-05-12 09:26:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.impl.v50sp2.FinancingDetails50SP2;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2;
import net.hades.fix.message.comp.impl.v50sp2.PegInstructions50SP2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.group.StrategyParametersGroup;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.NewOrderSingleMsg;
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
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.PreTradeAllocGroup;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.group.impl.v50sp2.PreTradeAllocGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.StrategyParametersGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.TradingSessionGroup50SP2;
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
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.RefOrderIDSource;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0 NewOrderSingleMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="Order")
@XmlType(propOrder = {"header", "partyIDGroups", "allocGroups", "displayInstruction", "tradingSessionGroups", "instrument", "financingDetails",
    "underlyingInstruments", "stipulationsGroups", "orderQtyData", "triggeringInstruction", "spreadOrBenchmarkCurveData", "yieldData",
    "commissionData", "pegInstructions", "discretionInstructions", "strategyParametersGroups", "trdRegTimestampsGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class NewOrderSingleMsg50SP2 extends NewOrderSingleMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP2().getFragmentAllTags();
    protected static final Set<Integer> ALLOC_GROUP_TAGS = new PreTradeAllocGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> DISPLAY_INSTRUCTION_COMP_TAGS = new DisplayInstruction50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRAD_SESSION_GROUP_TAGS = new TradingSessionGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> FINANCING_DETAILS_COMP_TAGS = new FinancingDetails50SP2().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations50SP2().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRIGG_INSTR_COMP_TAGS = new TriggeringInstruction50SP2().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData50SP2().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData50SP2().getFragmentAllTags();
    protected static final Set<Integer> COMMISSION_DATA_COMP_TAGS = new CommissionData50SP2().getFragmentAllTags();
    protected static final Set<Integer> PEG_INSTRUCTIONS_COMP_TAGS = new PegInstructions50SP2().getFragmentAllTags();
    protected static final Set<Integer> DISCR_INSTR_COMP_TAGS = new DiscretionInstructions50SP2().getFragmentAllTags();
    protected static final Set<Integer> STRATEGY_PARAMS_GROUP_TAGS = new StrategyParametersGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRD_REG_TSTAMP_GROUP_TAGS = new TrdRegTimestampsGroup50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(DISPLAY_INSTRUCTION_COMP_TAGS);
        ALL_TAGS.addAll(TRAD_SESSION_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(TRIGG_INSTR_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        ALL_TAGS.addAll(PEG_INSTRUCTIONS_COMP_TAGS);
        ALL_TAGS.addAll(DISCR_INSTR_COMP_TAGS);
        ALL_TAGS.addAll(STRATEGY_PARAMS_GROUP_TAGS);
        ALL_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(DISPLAY_INSTRUCTION_COMP_TAGS);
        START_COMP_TAGS.addAll(TRAD_SESSION_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(TRIGG_INSTR_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(PEG_INSTRUCTIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(DISCR_INSTR_COMP_TAGS);
        START_COMP_TAGS.addAll(STRATEGY_PARAMS_GROUP_TAGS);
        START_COMP_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NewOrderSingleMsg50SP2() {
        super();
    }

    public NewOrderSingleMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public NewOrderSingleMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public NewOrderSingleMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        NewOrderSingleMsg fixml = (NewOrderSingleMsg) fragment;
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getSecondaryClOrdID() != null) {
            secondaryClOrdID = fixml.getSecondaryClOrdID();
        }
        if (fixml.getClOrdLinkID() != null) {
            clOrdLinkID = fixml.getClOrdLinkID();
        }
        if (fixml.getParties() != null) {
            setParties(fixml.getParties());
        }
        if (fixml.getTradeOriginationDate() != null) {
            tradeOriginationDate = fixml.getTradeOriginationDate();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
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
        if (fixml.getCashMargin() != null) {
            cashMargin = fixml.getCashMargin();
        }
        if (fixml.getClearingFeeIndicator() != null) {
            clearingFeeIndicator = fixml.getClearingFeeIndicator();
        }
        if (fixml.getHandlInst() != null) {
            handlInst = fixml.getHandlInst();
        }
        if (fixml.getExecInst() != null) {
            execInst = fixml.getExecInst();
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
        if (fixml.getExDestination() != null) {
            exDestination = fixml.getExDestination();
        }
        if (fixml.getExDestinationIDSource() != null) {
            exDestinationIDSource = fixml.getExDestinationIDSource();
        }
        if (fixml.getTradingSessionGroups() != null && fixml.getTradingSessionGroups().length > 0) {
            setTradingSessionGroups(fixml.getTradingSessionGroups());
        }
        if (fixml.getProcessCode() != null) {
            processCode = fixml.getProcessCode();
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
        if (fixml.getPrevClosePx() != null) {
            prevClosePx = fixml.getPrevClosePx();
        }
        if (fixml.getSide() != null) {
            side = fixml.getSide();
        }
        if (fixml.getLocateReqd() != null) {
            locateReqd = fixml.getLocateReqd();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
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
        if (fixml.getSpreadOrBenchmarkCurveData() != null) {
            setSpreadOrBenchmarkCurveData(fixml.getSpreadOrBenchmarkCurveData());
        }
        if (fixml.getYieldData() != null) {
            setYieldData(fixml.getYieldData());
        }
        if (fixml.getTriggeringInstruction() != null) {
            setTriggeringInstruction(fixml.getTriggeringInstruction());
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
        if (fixml.getIOIID() != null) {
            IOIID = fixml.getIOIID();
        }
        if (fixml.getQuoteID() != null) {
            quoteID = fixml.getQuoteID();
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
        if (fixml.getGTBookingInst() != null) {
            GTBookingInst = fixml.getGTBookingInst();
        }
        if (fixml.getCommissionData() != null) {
            setCommissionData(fixml.getCommissionData());
        }
        if (fixml.getOrderRestrictions() != null) {
            orderRestrictions = fixml.getOrderRestrictions();
        }
        if (fixml.getOrderCapacity() != null) {
            orderCapacity = fixml.getOrderCapacity();
        }
        if (fixml.getPreTradeAnonymity() != null) {
            preTradeAnonymity = fixml.getPreTradeAnonymity();
        }
        if (fixml.getCustOrderCapacity() != null) {
            custOrderCapacity = fixml.getCustOrderCapacity();
        }
        if (fixml.getForexReq() != null) {
            forexReq = fixml.getForexReq();
        }
        if (fixml.getSettlCurrency() != null) {
            settlCurrency = fixml.getSettlCurrency();
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
        if (fixml.getPrice2() != null) {
            price2 = fixml.getPrice2();
        }
        if (fixml.getPositionEffect() != null) {
            positionEffect = fixml.getPositionEffect();
        }
        if (fixml.getCoveredOrUncovered() != null) {
            coveredOrUncovered = fixml.getCoveredOrUncovered();
        }
        if (fixml.getMaxShow() != null) {
            maxShow = fixml.getMaxShow();
        }
        if (fixml.getPegInstructions() != null) {
            setPegInstructions(fixml.getPegInstructions());
        }
        if (fixml.getDiscretionInstructions() != null) {
            setDiscretionInstructions(fixml.getDiscretionInstructions());
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
        if (fixml.getParticipationRate() != null) {
            participationRate = fixml.getParticipationRate();
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
        if (fixml.getRefOrderID() != null) {
            refOrderID = fixml.getRefOrderID();
        }
        if (fixml.getRefOrderIDSource() != null) {
            refOrderIDSource = fixml.getRefOrderIDSource();
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

    @XmlAttribute(name = "ID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
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

    @XmlAttribute(name = "LnkID")
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

    @XmlAttribute(name = "HandlInst")
    @Override
    public HandlInst getHandlInst() {
        return handlInst;
    }

    @Override
    public void setHandlInst(HandlInst handlInst) {
        this.handlInst = handlInst;
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

    @XmlAttribute(name = "ExDest")
    @Override
    public String getExDestination() {
        return exDestination;
    }

    @Override
    public void setExDestination(String exDestination) {
        this.exDestination = exDestination;
    }

    @XmlAttribute(name = "ExDestIDSrc")
    @Override
    public ExDestinationIDSource getExDestinationIDSource() {
        return exDestinationIDSource;
    }

    @Override
    public void setExDestinationIDSource(ExDestinationIDSource exDestinationIDSource) {
        this.exDestinationIDSource = exDestinationIDSource;
    }

    @Override
    public Integer getNoTradingSessions() {
        return noTradingSessions;
    }

    @Override
    public void setNoTradingSessions(Integer noTradingSessions) {
        this.noTradingSessions = noTradingSessions;
        if (noTradingSessions != null) {
            tradingSessionGroups = new TradingSessionGroup[noTradingSessions.intValue()];
            for (int i = 0; i < tradingSessionGroups.length; i++) {
                tradingSessionGroups[i] = new TradingSessionGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public TradingSessionGroup[] getTradingSessionGroups() {
        return tradingSessionGroups;
    }

    public void setTradingSessionGroups(TradingSessionGroup[] tradingSessionGroups) {
        this.tradingSessionGroups = tradingSessionGroups;
        if (tradingSessionGroups != null) {
            noTradingSessions = new Integer(tradingSessionGroups.length);
        }
    }
    @Override
    public TradingSessionGroup addTradingSessionGroup() {
        TradingSessionGroup group = new TradingSessionGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TradingSessionGroup> groups = new ArrayList<TradingSessionGroup>();
        if (tradingSessionGroups != null && tradingSessionGroups.length > 0) {
            groups = new ArrayList<TradingSessionGroup>(Arrays.asList(tradingSessionGroups));
        }
        groups.add(group);
        tradingSessionGroups = groups.toArray(new TradingSessionGroup[groups.size()]);
        noTradingSessions = new Integer(tradingSessionGroups.length);

        return group;
    }

    @Override
    public TradingSessionGroup deleteTradingSessionGroup(int index) {
        TradingSessionGroup result = null;
        if (tradingSessionGroups != null && tradingSessionGroups.length > 0 && tradingSessionGroups.length > index) {
            List<TradingSessionGroup> groups = new ArrayList<TradingSessionGroup>(Arrays.asList(tradingSessionGroups));
            result = groups.remove(index);
            tradingSessionGroups = groups.toArray(new TradingSessionGroup[groups.size()]);
            if (tradingSessionGroups.length > 0) {
                noTradingSessions = new Integer(tradingSessionGroups.length);
            } else {
                tradingSessionGroups = null;
                noTradingSessions = null;
            }
        }

        return result;
    }

    @Override
    public int clearTradingSessionGroups() {
        int result = 0;
        if (tradingSessionGroups != null && tradingSessionGroups.length > 0) {
            result = tradingSessionGroups.length;
            tradingSessionGroups = null;
            noTradingSessions = null;
        }

        return result;
    }
    
    @XmlAttribute(name = "ProcCode")
    @Override
    public ProcessCode getProcessCode() {
        return processCode;
    }

    @Override
    public void setProcessCode(ProcessCode processCode) {
        this.processCode = processCode;
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

    @XmlAttribute(name = "PrevClsPx")
    @Override
    public Double getPrevClosePx() {
        return prevClosePx;
    }

    @Override
    public void setPrevClosePx(Double prevClosePx) {
        this.prevClosePx = prevClosePx;
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

    @XmlAttribute(name = "LocReqd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getLocateReqd() {
        return locateReqd;
    }

    @Override
    public void setLocateReqd(Boolean locateReqd) {
        this.locateReqd = locateReqd;
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

    @XmlAttribute(name = "PxPrtScp")
    @Override
    public PriceProtectionScope getPriceProtectionScope() {
        return priceProtectionScope;
    }

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

    @XmlAttribute(name = "IOIID")
    @Override
    public String getIOIID() {
        return IOIID;
    }

    @Override
    public void setIOIID(String IOIID) {
        this.IOIID = IOIID;
    }

    @XmlAttribute(name = "QID")
    @Override
    public String getQuoteID() {
        return quoteID;
    }

    @Override
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
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

    @XmlAttribute(name = "GTBkngInst")
    @Override
    public GTBookingInst getGTBookingInst() {
        return GTBookingInst;
    }

    @Override
    public void setGTBookingInst(GTBookingInst GTBookingInst) {
        this.GTBookingInst = GTBookingInst;
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

    @XmlAttribute(name = "PrTrdAnon")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPreTradeAnonymity() {
        return preTradeAnonymity;
    }

    @Override
    public void setPreTradeAnonymity(Boolean preTradeAnonymity) {
        this.preTradeAnonymity = preTradeAnonymity;
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

    @XmlAttribute(name = "ForexReq")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getForexReq() {
        return forexReq;
    }

    @Override
    public void setForexReq(Boolean forexReq) {
        this.forexReq = forexReq;
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

    @XmlAttribute(name = "Px2")
    @Override
    public Double getPrice2() {
        return price2;
    }

    @Override
    public void setPrice2(Double price2) {
        this.price2 = price2;
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

    @XmlAttribute(name = "Covered")
    @Override
    public CoveredOrUncovered getCoveredOrUncovered() {
        return coveredOrUncovered;
    }

    @Override
    public void setCoveredOrUncovered(CoveredOrUncovered coveredOrUncovered) {
        this.coveredOrUncovered = coveredOrUncovered;
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

    @XmlAttribute(name = "RefOrdID")
    @Override
    public String getRefOrderID() {
        return refOrderID;
    }

    @Override
    public void setRefOrderID(String refOrderID) {
        this.refOrderID = refOrderID;
    }

    @XmlAttribute(name = "RefOrdIDSrc")
    @Override
    public RefOrderIDSource getRefOrderIDSource() {
        return refOrderIDSource;
    }

    @Override
    public void setRefOrderIDSource(RefOrderIDSource refOrderIDSource) {
        this.refOrderIDSource = refOrderIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;

        if (clOrdID == null || clOrdID.trim().isEmpty()) {
            errorMsg.append(" [ClOrdID]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol().trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
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
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP2(context);
            }
            parties.decode(tag, message);
        }
        if (TRAD_SESSION_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTradingSessions != null && noTradingSessions.intValue() > 0) {
                message.reset();
                tradingSessionGroups = new TradingSessionGroup[noTradingSessions.intValue()];
                for (int i = 0; i < noTradingSessions.intValue(); i++) {
                    TradingSessionGroup group = new TradingSessionGroup50SP2(context);
                    group.decode(message);
                    tradingSessionGroups[i] = group;
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
        if (COMMISSION_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (commissionData == null) {
                commissionData = new CommissionData50SP2(context);
            }
            commissionData.decode(tag, message);
        }
        if (PEG_INSTRUCTIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (pegInstructions == null) {
                pegInstructions = new PegInstructions50SP2(context);
            }
            pegInstructions.decode(tag, message);
        }
        if (DISPLAY_INSTRUCTION_COMP_TAGS.contains(tag.tagNum)) {
            if (displayInstruction == null) {
                displayInstruction = new DisplayInstruction50SP2(context);
            }
            displayInstruction.decode(tag, message);
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
        return "This tag is not supported in [NewOrderSingleMsg] message version [5.0SP2].";
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
