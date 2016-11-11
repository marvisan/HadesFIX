/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionMaintenanceReportMsg50.java
 *
 * $Id: AllocationInstructionMsg44.java,v 1.3 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.PositionMaintenanceReportMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50.Instrument50;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50;
import net.hades.fix.message.comp.impl.v50.Parties50;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.group.PositionQtyGroup;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.group.impl.v50.PosAmtGroup50;
import net.hades.fix.message.group.impl.v50.PositionQtyGroup50;
import net.hades.fix.message.group.impl.v50.TradingSessionGroup50;
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

import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AdjustmentType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PosMaintAction;
import net.hades.fix.message.type.PosMaintStatus;
import net.hades.fix.message.type.PosTransType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0 PositionMaintenanceReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 07/12/2011, 9:32:41 AM
 */
@XmlRootElement(name="PosMntRpt")
@XmlType(propOrder = {"header", "partyIDGroups", "instrument", "instrumentLegs", "underlyingInstruments", "tradingSessionGroups", 
    "positionQtyGroups", "posAmtGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class PositionMaintenanceReportMsg50 extends PositionMaintenanceReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50().getFragmentAllTags();
    protected static final Set<Integer> TRAD_SESSION_GROUP_TAGS = new TradingSessionGroup50().getFragmentAllTags();
    protected static final Set<Integer> POSITION_QTY_GROUP_TAGS = new PositionQtyGroup50().getFragmentAllTags();
    protected static final Set<Integer> POS_AMT_GROUP_TAGS = new PosAmtGroup50().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(TRAD_SESSION_GROUP_TAGS);
        ALL_TAGS.addAll(POSITION_QTY_GROUP_TAGS);
        ALL_TAGS.addAll(POS_AMT_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(TRAD_SESSION_GROUP_TAGS);
        START_COMP_TAGS.addAll(POSITION_QTY_GROUP_TAGS);
        START_COMP_TAGS.addAll(POS_AMT_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PositionMaintenanceReportMsg50() {
        super();
    }

    public PositionMaintenanceReportMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public PositionMaintenanceReportMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public PositionMaintenanceReportMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        PositionMaintenanceReportMsg50 fixml = (PositionMaintenanceReportMsg50) fragment;
        if (fixml.getPosMaintRptID() != null) {
            posMaintRptID = fixml.getPosMaintRptID();
        }
        if (fixml.getPosTransType() != null) {
            posTransType = fixml.getPosTransType();
        }
        if (fixml.getPosReqID() != null) {
            posReqID = fixml.getPosReqID();
        }
        if (fixml.getPosMaintAction() != null) {
            posMaintAction = fixml.getPosMaintAction();
        }
        if (fixml.getOrigPosReqRefID() != null) {
            origPosReqRefID = fixml.getOrigPosReqRefID();
        }
        if (fixml.getPosMaintStatus() != null) {
            posMaintStatus = fixml.getPosMaintStatus();
        }
        if (fixml.getPosMaintResult() != null) {
            posMaintResult = fixml.getPosMaintResult();
        }
        if (fixml.getClearingBusinessDate() != null) {
            clearingBusinessDate = fixml.getClearingBusinessDate();
        }
        if (fixml.getSettlSessID() != null) {
            settlSessID = fixml.getSettlSessID();
        }
        if (fixml.getSettlSessSubID() != null) {
            settlSessSubID = fixml.getSettlSessSubID();
        }
        if (fixml.getParties() != null) {
            setParties(fixml.getParties());
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
        if (fixml.getPosMaintRptRefID() != null) {
            posMaintRptRefID = fixml.getPosMaintRptRefID();
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getCurrency() != null) {
            currency = fixml.getCurrency();
        }
        if (fixml.getSettlCurrency() != null) {
            settlCurrency = fixml.getSettlCurrency();
        }
        if (fixml.getContraryInstructionIndicator() != null) {
            contraryInstructionIndicator = fixml.getContraryInstructionIndicator();
        }
        if (fixml.getPriorSpreadIndicator() != null) {
            priorSpreadIndicator = fixml.getPriorSpreadIndicator();
        }
        if (fixml.getInstrumentLegs() != null && fixml.getInstrumentLegs().length > 0) {
            setInstrumentLegs(fixml.getInstrumentLegs());
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getTradingSessionGroups() != null && fixml.getTradingSessionGroups().length > 0) {
            setTradingSessionGroups(fixml.getTradingSessionGroups());
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getPositionQtyGroups() != null && fixml.getPositionQtyGroups().length > 0) {
            setPositionQtyGroups(fixml.getPositionQtyGroups());
        }
        if (fixml.getPosAmtGroups() != null && fixml.getPosAmtGroups().length > 0) {
            setPosAmtGroups(fixml.getPosAmtGroups());
        }
        if (fixml.getAdjustmentType() != null) {
            adjustmentType = fixml.getAdjustmentType();
        }
        if (fixml.getThresholdAmount() != null) {
            thresholdAmount = fixml.getThresholdAmount();
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
    public String getPosMaintRptID() {
        return posMaintRptID;
    }

    @Override
    public void setPosMaintRptID(String posMaintRptID) {
        this.posMaintRptID = posMaintRptID;
    }

    @XmlAttribute(name = "TxnTyp")
    @Override
    public PosTransType getPosTransType() {
        return posTransType;
    }

    @Override
    public void setPosTransType(PosTransType posTransType) {
        this.posTransType = posTransType;
    }

    @XmlAttribute(name = "ReqID")
    @Override
    public String getPosReqID() {
        return posReqID;
    }

    @Override
    public void setPosReqID(String posReqID) {
        this.posReqID = posReqID;
    }

    @XmlAttribute(name = "Actn")
    @Override
    public PosMaintAction getPosMaintAction() {
        return posMaintAction;
    }

    @Override
    public void setPosMaintAction(PosMaintAction posMaintAction) {
        this.posMaintAction = posMaintAction;
    }

    @XmlAttribute(name = "OrigReqRefID")
    @Override
    public String getOrigPosReqRefID() {
        return origPosReqRefID;
    }

    @Override
    public void setOrigPosReqRefID(String origPosReqRefID) {
        this.origPosReqRefID = origPosReqRefID;
    }

    @XmlAttribute(name = "Stat")
    @Override
    public PosMaintStatus getPosMaintStatus() {
        return posMaintStatus;
    }

    @Override
    public void setPosMaintStatus(PosMaintStatus posMaintStatus) {
        this.posMaintStatus = posMaintStatus;
    }

    @XmlAttribute(name = "Rslt")
    @Override
    public Integer getPosMaintResult() {
        return posMaintResult;
    }

    @Override
    public void setPosMaintResult(Integer posMaintResult) {
        this.posMaintResult = posMaintResult;
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

    @XmlAttribute(name = "SetSesID")
    @Override
    public String getSettlSessID() {
        return settlSessID;
    }

    @Override
    public void setSettlSessID(String settlSessID) {
        this.settlSessID = settlSessID;
    }

    @XmlAttribute(name = "SetSubID")
    @Override
    public String getSettlSessSubID() {
        return settlSessSubID;
    }

    @Override
    public void setSettlSessSubID(String settlSessSubID) {
        this.settlSessSubID = settlSessSubID;
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

    @XmlAttribute(name = "RptRefID")
    @Override
    public String getPosMaintRptRefID() {
        return posMaintRptRefID;
    }

    @Override
    public void setPosMaintRptRefID(String posMaintRptRefID) {
        this.posMaintRptRefID = posMaintRptRefID;
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

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
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

    @XmlAttribute(name = "CntraryInstrctnInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getContraryInstructionIndicator() {
        return contraryInstructionIndicator;
    }

    @Override
    public void setContraryInstructionIndicator(Boolean contraryInstructionIndicator) {
        this.contraryInstructionIndicator = contraryInstructionIndicator;
    }

    @XmlAttribute(name = "PriorSpreadInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPriorSpreadIndicator() {
        return priorSpreadIndicator;
    }

    @Override
    public void setPriorSpreadIndicator(Boolean priorSpreadIndicator) {
        this.priorSpreadIndicator = priorSpreadIndicator;
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
    public Integer getNoTradingSessions() {
        return noTradingSessions;
    }

    @Override
    public void setNoTradingSessions(Integer noTradingSessions) {
        this.noTradingSessions = noTradingSessions;
        if (noTradingSessions != null) {
            tradingSessionGroups = new TradingSessionGroup[noTradingSessions.intValue()];
            for (int i = 0; i < tradingSessionGroups.length; i++) {
                tradingSessionGroups[i] = new TradingSessionGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        TradingSessionGroup group = new TradingSessionGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
    public Integer getNoPositions() {
        return noPositions;
    }

    @Override
    public void setNoPositions(Integer noPositions) {
        this.noPositions = noPositions;
        if (noPositions != null) {
            positionQtyGroups = new PositionQtyGroup[noPositions.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < positionQtyGroups.length; i++) {
                positionQtyGroups[i] = new PositionQtyGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public PositionQtyGroup[] getPositionQtyGroups() {
        return positionQtyGroups;
    }

    public void setPositionQtyGroups(PositionQtyGroup[] PositionQtyGroups) {
        this.positionQtyGroups = PositionQtyGroups;
        if (PositionQtyGroups != null) {
            noPositions = new Integer(PositionQtyGroups.length);
        }
    }

    @Override
    public PositionQtyGroup addPositionQtyGroup() {
        PositionQtyGroup group = new PositionQtyGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<PositionQtyGroup> groups = new ArrayList<PositionQtyGroup>();
        if (positionQtyGroups != null && positionQtyGroups.length > 0) {
            groups = new ArrayList<PositionQtyGroup>(Arrays.asList(positionQtyGroups));
        }
        groups.add(group);
        positionQtyGroups = groups.toArray(new PositionQtyGroup[groups.size()]);
        noPositions = new Integer(positionQtyGroups.length);

        return group;
    }

    @Override
    public PositionQtyGroup deletePositionQtyGroup(int index) {
        PositionQtyGroup result = null;
        if (positionQtyGroups != null && positionQtyGroups.length > 0 && positionQtyGroups.length > index) {
            List<PositionQtyGroup> groups = new ArrayList<PositionQtyGroup>(Arrays.asList(positionQtyGroups));
            result = groups.remove(index);
            positionQtyGroups = groups.toArray(new PositionQtyGroup[groups.size()]);
            if (positionQtyGroups.length > 0) {
                noPositions = new Integer(positionQtyGroups.length);
            } else {
                positionQtyGroups = null;
                noPositions = null;
            }
        }

        return result;
    }

    @Override
    public int clearPositionQtyGroups() {
        int result = 0;
        if (positionQtyGroups != null && positionQtyGroups.length > 0) {
            result = positionQtyGroups.length;
            positionQtyGroups = null;
            noPositions = null;
        }

        return result;
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

    @XmlAttribute(name = "AdjTyp")
    @Override
    public AdjustmentType getAdjustmentType() {
        return adjustmentType;
    }

    @Override
    public void setAdjustmentType(AdjustmentType adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    @XmlAttribute(name = "ThresholdAmt")
    @Override
    public Double getThresholdAmount() {
        return thresholdAmount;
    }

    @Override
    public void setThresholdAmount(Double thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
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
                parties = new Parties50(context);
            }
            parties.decode(tag, message);
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50(context);
            }
            instrument.decode(tag, message);
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
        if (TRAD_SESSION_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTradingSessions != null && noTradingSessions.intValue() > 0) {
                message.reset();
                tradingSessionGroups = new TradingSessionGroup[noTradingSessions.intValue()];
                for (int i = 0; i < noTradingSessions.intValue(); i++) {
                    TradingSessionGroup group = new TradingSessionGroup50(context);
                    group.decode(message);
                    tradingSessionGroups[i] = group;
                }
            }
        }
        if (POSITION_QTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noPositions != null && noPositions.intValue() > 0) {
                message.reset();
                positionQtyGroups = new PositionQtyGroup[noPositions.intValue()];
                for (int i = 0; i < noPositions.intValue(); i++) {
                    PositionQtyGroup group = new PositionQtyGroup50(context);
                    group.decode(message);
                    positionQtyGroups[i] = group;
                }
            }
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
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PositionMaintenanceReportMsg] message version [5.0].";
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
