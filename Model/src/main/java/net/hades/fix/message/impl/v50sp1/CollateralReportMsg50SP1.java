/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollateralReportMsg50SP1.java
 *
 * $Id: AllocationInstructionMsg44.java,v 1.3 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import net.hades.fix.message.CollateralReportMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.group.TrdCollGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.group.impl.v50sp1.MiscFeeGroup50SP1;
import net.hades.fix.message.group.impl.v50sp1.TrdCollGroup50SP1;
import net.hades.fix.message.group.impl.v50sp1.TrdRegTimestampsGroup50SP1;
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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SettlInstructionsData;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50sp1.FinancingDetails50SP1;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1;
import net.hades.fix.message.comp.impl.v50sp1.Parties50SP1;
import net.hades.fix.message.comp.impl.v50sp1.SettlInstructionsData50SP1;
import net.hades.fix.message.comp.impl.v50sp1.SpreadOrBenchmarkCurveData50SP1;
import net.hades.fix.message.comp.impl.v50sp1.Stipulations50SP1;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.ExecCollGroup;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.impl.v50sp1.ExecCollGroup50SP1;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CollApplType;
import net.hades.fix.message.type.CollStatus;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0SP1 CollateralReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 19/12/2011, 9:32:41 AM
 */
@XmlRootElement(name="CollRpt")
@XmlType(propOrder = {"header", "partyIDGroups", "execCollGroups", "trdCollGroups", "instrument", "financingDetails", "instrumentLegs", "underlyingInstruments",
    "trdRegTimestampsGroups", "miscFeeGroups", "spreadOrBenchmarkCurveData", "stipulationsGroups", "settlInstructionsData"})
@XmlAccessorType(XmlAccessType.NONE)
public class CollateralReportMsg50SP1 extends CollateralReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP1().getFragmentAllTags();
    protected static final Set<Integer> EXEC_COLL_GROUP_TAGS = new ExecCollGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> TRD_COLL_GROUP_TAGS = new TrdCollGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP1().getFragmentAllTags();
    protected static final Set<Integer> FINANCING_DETAILS_COMP_TAGS = new FinancingDetails50SP1().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50SP1().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50SP1().getFragmentAllTags();
    protected static final Set<Integer> TRD_REG_TSTAMP_GROUP_TAGS = new TrdRegTimestampsGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> MISC_FEE_GROUP_TAGS = new MiscFeeGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData50SP1().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations50SP1().getFragmentAllTags();
    protected static final Set<Integer> SETTL_INST_COMP_TAGS = new SettlInstructionsData50SP1().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(EXEC_COLL_GROUP_TAGS);
        ALL_TAGS.addAll(TRD_COLL_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        ALL_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(SETTL_INST_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(EXEC_COLL_GROUP_TAGS);
        START_COMP_TAGS.addAll(TRD_COLL_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        START_COMP_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(SETTL_INST_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public CollateralReportMsg50SP1() {
        super();
    }

    public CollateralReportMsg50SP1(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public CollateralReportMsg50SP1(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public CollateralReportMsg50SP1(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        CollateralReportMsg50SP1 fixml = (CollateralReportMsg50SP1) fragment;
        if (fixml.getCollRptID() != null) {
            collRptID = fixml.getCollRptID();
        }
        if (fixml.getCollInquiryID() != null) {
            collInquiryID = fixml.getCollInquiryID();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getCollApplType() != null) {
            collApplType = fixml.getCollApplType();
        }
        if (fixml.getFinancialStatus() != null) {
            financialStatus = fixml.getFinancialStatus();
        }
        if (fixml.getCollStatus() != null) {
            collStatus = fixml.getCollStatus();
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
        if (fixml.getAccount() != null) {
            account = fixml.getAccount();
        }
        if (fixml.getAccountType() != null) {
            accountType = fixml.getAccountType();
        }
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
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
        if (fixml.getExecCollGroups() != null) {
            setExecCollGroups(fixml.getExecCollGroups());
        }
        if (fixml.getTrdCollGroups() != null) {
            setTrdCollGroups(fixml.getTrdCollGroups());
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getFinancingDetails() != null) {
            setFinancingDetails(fixml.getFinancingDetails());
        }
        if (fixml.getSettlDate() != null) {
            settlDate = fixml.getSettlDate();
        }
        if (fixml.getQuantity() != null) {
            quantity = fixml.getQuantity();
        }
        if (fixml.getQtyType() != null) {
            qtyType = fixml.getQtyType();
        }
        if (fixml.getCurrency() != null) {
            currency = fixml.getCurrency();
        }
        if (fixml.getInstrumentLegs() != null && fixml.getInstrumentLegs().length > 0) {
            setInstrumentLegs(fixml.getInstrumentLegs());
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getMarginExcess() != null) {
            marginExcess = fixml.getMarginExcess();
        }
        if (fixml.getTotalNetValue() != null) {
            totalNetValue = fixml.getTotalNetValue();
        }
        if (fixml.getCashOutstanding() != null) {
            cashOutstanding = fixml.getCashOutstanding();
        }
        if (fixml.getTrdRegTimestampsGroups() != null && fixml.getTrdRegTimestampsGroups().length > 0) {
            setTrdRegTimestampsGroups(fixml.getTrdRegTimestampsGroups());
        }
        if (fixml.getSide() != null) {
            side = fixml.getSide();
        }
        if (fixml.getMiscFeeGroups() != null) {
            setMiscFeeGroups(fixml.getMiscFeeGroups());
        }
        if (fixml.getPrice() != null) {
            price = fixml.getPrice();
        }
        if (fixml.getPriceType() != null) {
            priceType = fixml.getPriceType();
        }
        if (fixml.getAccruedInterestAmt() != null) {
            accruedInterestAmt = fixml.getAccruedInterestAmt();
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
        if (fixml.getSpreadOrBenchmarkCurveData() != null) {
            setSpreadOrBenchmarkCurveData(fixml.getSpreadOrBenchmarkCurveData());
        }
        if (fixml.getStipulations() != null) {
            setStipulations(fixml.getStipulations());
        }
        if (fixml.getSettlInstructionsData() != null) {
            setSettlInstructionsData(fixml.getSettlInstructionsData());
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
        }
        if (fixml.getSettlSessID() != null) {
            settlSessID = fixml.getSettlSessID();
        }
        if (fixml.getSettlSessSubID() != null) {
            settlSessSubID = fixml.getSettlSessSubID();
        }
        if (fixml.getClearingBusinessDate() != null) {
            clearingBusinessDate = fixml.getClearingBusinessDate();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
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
    public String getCollRptID() {
        return collRptID;
    }

    @Override
    public void setCollRptID(String collRptID) {
        this.collRptID = collRptID;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getCollInquiryID() {
        return collInquiryID;
    }

    @Override
    public void setCollInquiryID(String collInquiryID) {
        this.collInquiryID = collInquiryID;
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

    @XmlAttribute(name = "ApplTyp")
    @Override
    public CollApplType getCollApplType() {
        return collApplType;
    }

    @Override
    public void setCollApplType(CollApplType collApplType) {
        this.collApplType = collApplType;
    }

    @XmlAttribute(name = "FinclStat")
    @Override
    public String getFinancialStatus() {
        return financialStatus;
    }

    @Override
    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    @XmlAttribute(name = "Stat")
    @Override
    public CollStatus getCollStatus() {
        return collStatus;
    }

    @Override
    public void setCollStatus(CollStatus collStatus) {
        this.collStatus = collStatus;
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
        this.parties = new Parties50SP1(context);
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
            ((Parties50SP1) parties).setPartyIDGroups(partyIDGroups);
        }
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

    @XmlAttribute(name = "AcctTyp")
    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
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

    @XmlAttribute(name = "ClOrdID2")
    @Override
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    @Override
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    @Override
    public Integer getNoExecs() {
        return noExecs;
    }

    @Override
    public void setNoExecs(Integer noExecs) {
        this.noExecs = noExecs;
        if (noExecs != null) {
            execCollGroups = new ExecCollGroup[noExecs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < execCollGroups.length; i++) {
                execCollGroups[i] = new ExecCollGroup50SP1(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public ExecCollGroup[] getExecCollGroups() {
        return execCollGroups;
    }

    public void setExecCollGroups(ExecCollGroup[] rateSources) {
        this.execCollGroups = rateSources;
        if (rateSources != null) {
            noExecs = new Integer(rateSources.length);
        }
    }

    @Override
    public ExecCollGroup addExecCollGroup() {
        ExecCollGroup group = new ExecCollGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<ExecCollGroup> groups = new ArrayList<ExecCollGroup>();
        if (execCollGroups != null && execCollGroups.length > 0) {
            groups = new ArrayList<ExecCollGroup>(Arrays.asList(execCollGroups));
        }
        groups.add(group);
        execCollGroups = groups.toArray(new ExecCollGroup[groups.size()]);
        noExecs = new Integer(execCollGroups.length);

        return group;
    }

    @Override
    public ExecCollGroup deleteExecCollGroup(int index) {
        ExecCollGroup result = null;
        if (execCollGroups != null && execCollGroups.length > 0 && execCollGroups.length > index) {
            List<ExecCollGroup> groups = new ArrayList<ExecCollGroup>(Arrays.asList(execCollGroups));
            result = groups.remove(index);
            execCollGroups = groups.toArray(new ExecCollGroup[groups.size()]);
            if (execCollGroups.length > 0) {
                noExecs = new Integer(execCollGroups.length);
            } else {
                execCollGroups = null;
                noExecs = null;
            }
        }

        return result;
    }

    @Override
    public int clearExecCollGroups() {
        int result = 0;
        if (execCollGroups != null && execCollGroups.length > 0) {
            result = execCollGroups.length;
            execCollGroups = null;
            noExecs = null;
        }

        return result;
    }

    @Override
    public Integer getNoTrades() {
        return noTrades;
    }

    @Override
    public void setNoTrades(Integer noContAmts) {
        this.noTrades = noContAmts;
        if (noContAmts != null) {
            trdCollGroups = new TrdCollGroup[noContAmts.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < trdCollGroups.length; i++) {
                trdCollGroups[i] = new TrdCollGroup50SP1(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public TrdCollGroup[] getTrdCollGroups() {
        return trdCollGroups;
    }

    public void setTrdCollGroups(TrdCollGroup[] contAmtGroups) {
        this.trdCollGroups = contAmtGroups;
        if (contAmtGroups != null) {
            noTrades = new Integer(contAmtGroups.length);
        }
    }

    @Override
    public TrdCollGroup addTrdCollGroup() {
        TrdCollGroup group = new TrdCollGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TrdCollGroup> groups = new ArrayList<TrdCollGroup>();
        if (trdCollGroups != null && trdCollGroups.length > 0) {
            groups = new ArrayList<TrdCollGroup>(Arrays.asList(trdCollGroups));
        }
        groups.add(group);
        trdCollGroups = groups.toArray(new TrdCollGroup[groups.size()]);
        noTrades = new Integer(trdCollGroups.length);

        return group;
    }

    @Override
    public TrdCollGroup deleteTrdCollGroup(int index) {
        TrdCollGroup result = null;
        if (trdCollGroups != null && trdCollGroups.length > 0 && trdCollGroups.length > index) {
            List<TrdCollGroup> groups = new ArrayList<TrdCollGroup>(Arrays.asList(trdCollGroups));
            result = groups.remove(index);
            trdCollGroups = groups.toArray(new TrdCollGroup[groups.size()]);
            if (trdCollGroups.length > 0) {
                noTrades = new Integer(trdCollGroups.length);
            } else {
                trdCollGroups = null;
                noTrades = null;
            }
        }

        return result;
    }

    @Override
    public int clearTrdCollGroups() {
        int result = 0;
        if (trdCollGroups != null && trdCollGroups.length > 0) {
            result = trdCollGroups.length;
            trdCollGroups = null;
            noTrades = null;
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
        this.instrument = new Instrument50SP1(context);
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
    public FinancingDetails getFinancingDetails() {
        return financingDetails;
    }

    @Override
    public void setFinancingDetails() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.financingDetails = new FinancingDetails50SP1(context);
    }

    public void setFinancingDetails(FinancingDetails financingDetails) {
        this.financingDetails = financingDetails;
    }

    @Override
    public void clearFinancingDetails() {
        this.financingDetails = null;
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

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
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
                instrumentLegs[i] = new InstrumentLeg50SP1(context);
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
        InstrumentLeg group = new InstrumentLeg50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
                underlyingInstruments[i] = new UnderlyingInstrument50SP1(context);
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
        UnderlyingInstrument group = new UnderlyingInstrument50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "MgnExcess")
    @Override
    public Double getMarginExcess() {
        return marginExcess;
    }

    @Override
    public void setMarginExcess(Double marginExcess) {
        this.marginExcess = marginExcess;
    }

    @XmlAttribute(name = "TotNetValu")
    @Override
    public Double getTotalNetValue() {
        return totalNetValue;
    }

    @Override
    public void setTotalNetValue(Double totalNetValue) {
        this.totalNetValue = totalNetValue;
    }

    @XmlAttribute(name = "CshOutstanding")
    @Override
    public Double getCashOutstanding() {
        return cashOutstanding;
    }

    @Override
    public void setCashOutstanding(Double cashOutstanding) {
        this.cashOutstanding = cashOutstanding;
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
                trdRegTimestampsGroups[i] = new TrdRegTimestampsGroup50SP1(context);
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
        TrdRegTimestampsGroup group = new TrdRegTimestampsGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
                miscFeeGroups[i] = new MiscFeeGroup50SP1(context);
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
        MiscFeeGroup group = new MiscFeeGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "Px")
    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
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

    @XmlAttribute(name = "AcrdIntAmt")
    @Override
    public Double getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    @Override
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
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

    @XmlElementRef
    @Override
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50SP1(context);
    }

    @Override
    public void clearSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = null;
    }

    public void setSpreadOrBenchmarkCurveData(SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData) {
        this.spreadOrBenchmarkCurveData = spreadOrBenchmarkCurveData;
    }

    @Override
    public Stipulations getStipulations() {
        return stipulations;
    }

    @Override
    public void setStipulations() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.stipulations = new Stipulations50SP1(context);
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
            ((Stipulations50SP1) stipulations).setStipulationsGroups(stipulationsGroups);
        }
    }

    @XmlElementRef
    @Override
    public SettlInstructionsData getSettlInstructionsData() {
        return settlInstructionsData;
    }

    @Override
    public void setSettlInstructionsData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.settlInstructionsData = new SettlInstructionsData50SP1(context);
    }

    public void setSettlInstructionsData(SettlInstructionsData settlInstructionsData) {
        this.settlInstructionsData = settlInstructionsData;
    }

    @Override
    public void clearSettlInstructionsData() {
        this.settlInstructionsData = null;
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

    @XmlAttribute(name = "SetSesID")
    @Override
    public String getSettlSessID() {
        return settlSessID;
    }

    @Override
    public void setSettlSessID(String settlSessID) {
        this.settlSessID = settlSessID;
    }

    @XmlAttribute(name = "SetSesSub")
    @Override
    public String getSettlSessSubID() {
        return settlSessSubID;
    }

    @Override
    public void setSettlSessSubID(String settlSessSubID) {
        this.settlSessSubID = settlSessSubID;
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP1(context);
            }
            parties.decode(tag, message);
        }
        if (EXEC_COLL_GROUP_TAGS.contains(tag.tagNum)) {
            if (noExecs != null && noExecs.intValue() > 0) {
                message.reset();
                execCollGroups = new ExecCollGroup[noExecs.intValue()];
                for (int i = 0; i < noExecs.intValue(); i++) {
                    ExecCollGroup component = new ExecCollGroup50SP1(context);
                    component.decode(message);
                    execCollGroups[i] = component;
                }
            }
        }
        if (TRD_COLL_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTrades != null && noTrades.intValue() > 0) {
                message.reset();
                trdCollGroups = new TrdCollGroup[noTrades.intValue()];
                for (int i = 0; i < noTrades.intValue(); i++) {
                    TrdCollGroup component = new TrdCollGroup50SP1(context);
                    component.decode(message);
                    trdCollGroups[i] = component;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50SP1(context);
            }
            instrument.decode(tag, message);
        }
        if (FINANCING_DETAILS_COMP_TAGS.contains(tag.tagNum)) {
            if (financingDetails == null) {
                financingDetails = new FinancingDetails50SP1(context);
            }
            financingDetails.decode(tag, message);
        }
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrumentLegs = new InstrumentLeg[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrumentLeg component = new InstrumentLeg50SP1(context);
                    component.decode(message);
                    instrumentLegs[i] = component;
                }
            }
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument component = new UnderlyingInstrument50SP1(context);
                    component.decode(message);
                    underlyingInstruments[i] = component;
                }
            }
        }
        if (TRD_REG_TSTAMP_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTrdRegTimestamps != null && noTrdRegTimestamps.intValue() > 0) {
                message.reset();
                trdRegTimestampsGroups = new TrdRegTimestampsGroup[noTrdRegTimestamps.intValue()];
                for (int i = 0; i < noTrdRegTimestamps.intValue(); i++) {
                    TrdRegTimestampsGroup group = new TrdRegTimestampsGroup50SP1(context);
                    group.decode(message);
                    trdRegTimestampsGroups[i] = group;
                }
            }
        }
        if (MISC_FEE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMiscFees != null && noMiscFees.intValue() > 0) {
                message.reset();
                miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
                for (int i = 0; i < noMiscFees.intValue(); i++) {
                    MiscFeeGroup component = new MiscFeeGroup50SP1(context);
                    component.decode(message);
                    miscFeeGroups[i] = component;
                }
            }
        }
        if (SPREAD_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50SP1(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations50SP1(context);
            }
            stipulations.decode(tag, message);
        }
        if (SETTL_INST_COMP_TAGS.contains(tag.tagNum)) {
            if (settlInstructionsData == null) {
                settlInstructionsData = new SettlInstructionsData50SP1(context);
            }
            settlInstructionsData.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [CollateralReportMsg] message version [5.0SP1].";
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
