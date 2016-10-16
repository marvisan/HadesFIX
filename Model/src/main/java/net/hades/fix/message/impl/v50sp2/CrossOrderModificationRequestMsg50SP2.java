/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderModificationRequestMsg50SP2.java
 *
 * $Id: CrossOrderModificationRequestMsg50SP2.java,v 1.1 2011-05-17 09:28:21 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.CrossOrderModificationRequestMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SideCrossOrdModGroup;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.group.StrategyParametersGroup;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.group.impl.v50sp2.SideCrossOrdModGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.StrategyParametersGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.TradingSessionGroup50SP2;
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

import net.hades.fix.message.comp.DiscretionInstructions;
import net.hades.fix.message.comp.DisplayInstruction;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.PegInstructions;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.TriggeringInstruction;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v50sp2.DiscretionInstructions50SP2;
import net.hades.fix.message.comp.impl.v50sp2.DisplayInstruction50SP2;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2;
import net.hades.fix.message.comp.impl.v50sp2.PegInstructions50SP2;
import net.hades.fix.message.comp.impl.v50sp2.RootParties50SP2;
import net.hades.fix.message.comp.impl.v50sp2.SpreadOrBenchmarkCurveData50SP2;
import net.hades.fix.message.comp.impl.v50sp2.Stipulations50SP2;
import net.hades.fix.message.comp.impl.v50sp2.TriggeringInstruction50SP2;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2;
import net.hades.fix.message.comp.impl.v50sp2.YieldData50SP2;
import net.hades.fix.message.group.RootPartyGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0SP2 CrossOrderModificationRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 10/05/2011, 9:32:41 AM
 */
@XmlRootElement(name="CrssOrdCxlRplcReq")
@XmlType(propOrder = {"header", "rootPartyIDGroups", "sideCrossOrdModGroups", "instrument", "underlyingInstruments", "instrumentLegs",
    "displayInstruction", "tradingSessionGroups", "stipulationsGroups", "triggeringInstruction", "spreadOrBenchmarkCurveData", 
    "yieldData", "pegInstructions", "discretionInstructions", "strategyParametersGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class CrossOrderModificationRequestMsg50SP2 extends CrossOrderModificationRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ROOT_PARTIES_COMP_TAGS = new RootParties50SP2().getFragmentAllTags();
    protected static final Set<Integer> SIDE_CROSS_ORD_MOD_GROUP_TAGS = new SideCrossOrdModGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50SP2().getFragmentAllTags();
    protected static final Set<Integer> DISPLAY_INSTRUCTION_COMP_TAGS = new DisplayInstruction50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRAD_SESSION_GROUP_TAGS = new TradingSessionGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRIGG_INSTR_COMP_TAGS = new TriggeringInstruction50SP2().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData50SP2().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData50SP2().getFragmentAllTags();
    protected static final Set<Integer> PEG_INSTRUCTIONS_COMP_TAGS = new PegInstructions50SP2().getFragmentAllTags();
    protected static final Set<Integer> DISCR_INSTR_COMP_TAGS = new DiscretionInstructions50SP2().getFragmentAllTags();
    protected static final Set<Integer> STRATEGY_PARAMS_GROUP_TAGS = new StrategyParametersGroup50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(ROOT_PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(SIDE_CROSS_ORD_MOD_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(DISPLAY_INSTRUCTION_COMP_TAGS);
        ALL_TAGS.addAll(TRAD_SESSION_GROUP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(TRIGG_INSTR_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(PEG_INSTRUCTIONS_COMP_TAGS);
        ALL_TAGS.addAll(DISCR_INSTR_COMP_TAGS);
        ALL_TAGS.addAll(STRATEGY_PARAMS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SIDE_CROSS_ORD_MOD_GROUP_TAGS);
        START_COMP_TAGS.addAll(ROOT_PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(DISPLAY_INSTRUCTION_COMP_TAGS);
        START_COMP_TAGS.addAll(TRAD_SESSION_GROUP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(TRIGG_INSTR_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(PEG_INSTRUCTIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(DISCR_INSTR_COMP_TAGS);
        START_COMP_TAGS.addAll(STRATEGY_PARAMS_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public CrossOrderModificationRequestMsg50SP2() {
        super();
    }

    public CrossOrderModificationRequestMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public CrossOrderModificationRequestMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public CrossOrderModificationRequestMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        CrossOrderModificationRequestMsg50SP2 fixml = (CrossOrderModificationRequestMsg50SP2) fragment;
        if (fixml.getOrderID() != null) {
            orderID = fixml.getOrderID();
        }
        if (fixml.getCrossID() != null) {
            crossID = fixml.getCrossID();
        }
        if (fixml.getOrigCrossID() != null) {
            origCrossID = fixml.getOrigCrossID();
        }
        if (fixml.getHostCrossID() != null) {
            hostCrossID = fixml.getHostCrossID();
        }
        if (fixml.getCrossType() != null) {
            crossType = fixml.getCrossType();
        }
        if (fixml.getCrossPrioritization() != null) {
            crossPrioritization = fixml.getCrossPrioritization();
        }
        if (fixml.getRootParties() != null) {
            rootParties = fixml.getRootParties();
        }
        if (fixml.getSideCrossOrdModGroups() != null && fixml.getSideCrossOrdModGroups().length > 0) {
            setSideCrossOrdModGroups(fixml.getSideCrossOrdModGroups());
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getInstrumentLegs() != null && fixml.getInstrumentLegs().length > 0) {
            setInstrumentLegs(fixml.getInstrumentLegs());
        }
        if (fixml.getSettlType() != null) {
            settlType = fixml.getSettlType();
        }
        if (fixml.getSettlDate() != null) {
            settlDate = fixml.getSettlDate();
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
        if (fixml.getPrevClosePx() != null) {
            prevClosePx = fixml.getPrevClosePx();
        }
        if (fixml.getLocateReqd() != null) {
            locateReqd = fixml.getLocateReqd();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getTransBkdTime() != null) {
            transBkdTime = fixml.getTransBkdTime();
        }
        if (fixml.getStipulations() != null) {
            setStipulations(fixml.getStipulations());
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

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getCrossID() {
        return crossID;
    }

    @Override
    public void setCrossID(String crossID) {
        this.crossID = crossID;
    }

    @XmlAttribute(name = "OrigID")
    @Override
    public String getOrigCrossID() {
        return origCrossID;
    }

    @Override
    public void setOrigCrossID(String origCrossID) {
        this.origCrossID = origCrossID;
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

    @XmlAttribute(name = "Typ")
    @Override
    public CrossType getCrossType() {
        return crossType;
    }

    @Override
    public void setCrossType(CrossType crossType) {
        this.crossType = crossType;
    }

    @XmlAttribute(name = "Priorty")
    @Override
    public CrossPrioritization getCrossPrioritization() {
        return crossPrioritization;
    }

    @Override
    public void setCrossPrioritization(CrossPrioritization crossPrioritization) {
        this.crossPrioritization = crossPrioritization;
    }

    @Override
    public RootParties getRootParties() {
        return rootParties;
    }

    @Override
    public void setRootParties() {
        this.rootParties = new RootParties50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearRootParties() {
        this.rootParties = null;
    }

    @XmlElementRef
    public RootPartyGroup[] getRootPartyIDGroups() {
        return rootParties == null ? null : rootParties.getRootPartyIDGroups();
    }

    public void setRootPartyIDGroups(RootPartyGroup[] rootPartyIDGroups) {
        if (rootPartyIDGroups != null) {
            if (rootParties == null) {
                setRootParties();
            }
            ((RootParties50SP2) rootParties).setRootPartyIDGroups(rootPartyIDGroups);
        }
    }

    @Override
    public Integer getNoSides() {
        return noSides;
    }

    @Override
    public void setNoSides(Integer noSides) {
        this.noSides = noSides;
        if (noSides != null) {
            sideCrossOrdModGroups = new SideCrossOrdModGroup[noSides.intValue()];
            for (int i = 0; i < sideCrossOrdModGroups.length; i++) {
                sideCrossOrdModGroups[i] = new SideCrossOrdModGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public SideCrossOrdModGroup[] getSideCrossOrdModGroups() {
        return sideCrossOrdModGroups;
    }

    public void setSideCrossOrdModGroups(SideCrossOrdModGroup[] sideCrossOrdModGroups) {
        this.sideCrossOrdModGroups = sideCrossOrdModGroups;
        if (sideCrossOrdModGroups != null) {
            noSides = new Integer(sideCrossOrdModGroups.length);
        }
    }

    @Override
    public SideCrossOrdModGroup addSideCrossOrdModGroup() {
        SideCrossOrdModGroup group = new SideCrossOrdModGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<SideCrossOrdModGroup> groups = new ArrayList<SideCrossOrdModGroup>();
        if (sideCrossOrdModGroups != null && sideCrossOrdModGroups.length > 0) {
            groups = new ArrayList<SideCrossOrdModGroup>(Arrays.asList(sideCrossOrdModGroups));
        }
        groups.add(group);
        sideCrossOrdModGroups = groups.toArray(new SideCrossOrdModGroup[groups.size()]);
        noSides = new Integer(sideCrossOrdModGroups.length);

        return group;
    }

    @Override
    public SideCrossOrdModGroup deleteSideCrossOrdModGroup(int index) {
        SideCrossOrdModGroup result = null;
        if (sideCrossOrdModGroups != null && sideCrossOrdModGroups.length > 0 && sideCrossOrdModGroups.length > index) {
            List<SideCrossOrdModGroup> groups = new ArrayList<SideCrossOrdModGroup>(Arrays.asList(sideCrossOrdModGroups));
            result = groups.remove(index);
            sideCrossOrdModGroups = groups.toArray(new SideCrossOrdModGroup[groups.size()]);
            if (sideCrossOrdModGroups.length > 0) {
                noSides = new Integer(sideCrossOrdModGroups.length);
            } else {
                sideCrossOrdModGroups = null;
                noSides = null;
            }
        }

        return result;
    }

    @Override
    public int clearSideCrossOrdModGroups() {
        int result = 0;
        if (sideCrossOrdModGroups != null && sideCrossOrdModGroups.length > 0) {
            result = sideCrossOrdModGroups.length;
            sideCrossOrdModGroups = null;
            noSides = null;
        }

        return result;
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
    
    @Override
    public void clearInstrument() {
        this.instrument = null;
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
                instrumentLegs[i] = new InstrumentLeg50SP2(context);
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
            noLegs = new Integer(instrumentLegs.length);
        }
    }

    @Override
    public InstrumentLeg addInstrumentLeg() {
        InstrumentLeg group = new InstrumentLeg50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "PrevClsPx")
    @Override
    public Double getPrevClosePx() {
        return prevClosePx;
    }

    @Override
    public void setPrevClosePx(Double prevClosePx) {
        this.prevClosePx = prevClosePx;
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

    @XmlAttribute(name = "TransBkdTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    @Override
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
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

    @XmlAttribute(name = "OrdTyp")
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (ROOT_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (rootParties == null) {
                rootParties = new RootParties50SP2(context);
            }
            rootParties.decode(tag, message);
        }
        if (SIDE_CROSS_ORD_MOD_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSides != null && noSides.intValue() > 0) {
                message.reset();
                sideCrossOrdModGroups = new SideCrossOrdModGroup[noSides.intValue()];
                for (int i = 0; i < noSides.intValue(); i++) {
                    SideCrossOrdModGroup component = new SideCrossOrdModGroup50SP2(context);
                    component.decode(message);
                    sideCrossOrdModGroups[i] = component;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50SP2(context);
            }
            instrument.decode(tag, message);
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
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrumentLegs = new InstrumentLeg[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrumentLeg group = new InstrumentLeg50SP2(context);
                    group.decode(message);
                    instrumentLegs[i] = group;
                }
            }
        }
        if (DISPLAY_INSTRUCTION_COMP_TAGS.contains(tag.tagNum)) {
            if (displayInstruction == null) {
                displayInstruction = new DisplayInstruction50SP2(context);
            }
            displayInstruction.decode(tag, message);
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
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations50SP2(context);
            }
            stipulations.decode(tag, message);
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
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [CrossOrderModificationRequestMsg] message version [5.0SP2].";
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
