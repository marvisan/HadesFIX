/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionMaintenanceReportMsg44.java
 *
 * $Id: AllocationInstructionMsg44.java,v 1.3 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.PositionMaintenanceReportMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v44.Instrument44;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44;
import net.hades.fix.message.comp.impl.v44.Parties44;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.PositionQtyGroup;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.group.impl.v44.PosAmtGroup44;
import net.hades.fix.message.group.impl.v44.PositionQtyGroup44;
import net.hades.fix.message.group.impl.v44.TradingSessionGroup44;
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

import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AdjustmentType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PosMaintAction;
import net.hades.fix.message.type.PosMaintStatus;
import net.hades.fix.message.type.PosTransType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 4.4 PositionMaintenanceReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 07/12/2011, 9:32:41 AM
 */
@XmlRootElement(name="PosMntRpt")
@XmlType(propOrder = {"header", "partyIDGroups", "instrument", "instrumentLegs", "underlyingInstruments", "tradingSessionGroups", 
    "positionQtyGroups", "posAmtGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class PositionMaintenanceReportMsg44 extends PositionMaintenanceReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties44().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument44().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg44().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument44().getFragmentAllTags();
    protected static final Set<Integer> TRAD_SESSION_GROUP_TAGS = new TradingSessionGroup44().getFragmentAllTags();
    protected static final Set<Integer> POSITION_QTY_GROUP_TAGS = new PositionQtyGroup44().getFragmentAllTags();
    protected static final Set<Integer> POS_AMT_GROUP_TAGS = new PosAmtGroup44().getFragmentAllTags();

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

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PositionMaintenanceReportMsg44() {
        super();
    }

    public PositionMaintenanceReportMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public PositionMaintenanceReportMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public PositionMaintenanceReportMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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
        PositionMaintenanceReportMsg44 fixml = (PositionMaintenanceReportMsg44) fragment;
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
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
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

    @XmlAttribute(name = "SetSesSub")
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.parties = new Parties44(context);
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
            ((Parties44) parties).setPartyIDGroups(partyIDGroups);
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

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            instrumentLegs = new InstrumentLeg[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < instrumentLegs.length; i++) {
                instrumentLegs[i] = new InstrumentLeg44(context);
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
        InstrumentLeg group = new InstrumentLeg44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
                tradingSessionGroups[i] = new TradingSessionGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
        TradingSessionGroup group = new TradingSessionGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < positionQtyGroups.length; i++) {
                positionQtyGroups[i] = new PositionQtyGroup44(context);
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
        PositionQtyGroup group = new PositionQtyGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
                posAmtGroups[i] = new PosAmtGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
        PosAmtGroup group = new PosAmtGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (posMaintRptID == null || posMaintRptID.trim().isEmpty()) {
            errorMsg.append(" [PosMaintRptID]");
            hasMissingTag = true;
        }
        if (posTransType == null) {
            errorMsg.append(" [PosTransType]");
            hasMissingTag = true;
        }
        if (posMaintAction == null) {
            errorMsg.append(" [PosMaintAction]");
            hasMissingTag = true;
        }
        if (origPosReqRefID == null || origPosReqRefID.trim().isEmpty()) {
            errorMsg.append(" [OrigPosReqRefID]");
            hasMissingTag = true;
        }
        if (posMaintStatus == null) {
            errorMsg.append(" [PosMaintStatus]");
            hasMissingTag = true;
        }
        if (clearingBusinessDate == null) {
            errorMsg.append(" [ClearingBusinessDate]");
            hasMissingTag = true;
        }
        if (parties == null || parties.getNoPartyIDs() == null) {
            errorMsg.append(" [Parties]");
            hasMissingTag = true;
        }
        if (account == null || account.trim().isEmpty()) {
            errorMsg.append(" [Account]");
            hasMissingTag = true;
        }
        if (accountType == null) {
            errorMsg.append(" [AccountType]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol() == null || instrument.getSymbol().trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
            hasMissingTag = true;
        }
        if (noPositions == null || positionQtyGroups == null || positionQtyGroups.length == 0) {
            errorMsg.append(" [NoPositions]");
            hasMissingTag = true;
        }
        if (noPosAmt == null || posAmtGroups == null || posAmtGroups.length == 0) {
            errorMsg.append(" [NoPosAmt]");
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
            if (MsgUtil.isTagInList(TagNum.PosMaintRptID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PosMaintRptID, posMaintRptID);
            }
            if (posTransType != null && MsgUtil.isTagInList(TagNum.PosTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PosTransType, posTransType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PosReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PosReqID, posReqID);
            }
            if (posMaintAction != null && MsgUtil.isTagInList(TagNum.PosMaintAction, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PosMaintAction, posMaintAction.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OrigPosReqRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrigPosReqRefID, origPosReqRefID);
            }
            if (posMaintStatus != null && MsgUtil.isTagInList(TagNum.PosMaintStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PosMaintStatus, posMaintStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PosMaintResult, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PosMaintResult, posMaintResult);
            }
            if (MsgUtil.isTagInList(TagNum.ClearingBusinessDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            }
            if (MsgUtil.isTagInList(TagNum.SettlSessID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlSessID, settlSessID);
            }
            if (MsgUtil.isTagInList(TagNum.SettlSessSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlSessSubID, settlSessSubID);
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.Account, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Account, account);
            }
            if (acctIDSource != null && MsgUtil.isTagInList(TagNum.AcctIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (accountType != null && MsgUtil.isTagInList(TagNum.AccountType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (noLegs != null && MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrumentLegs[i] != null) {
                            bao.write(instrumentLegs[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "InstrumentLeg field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
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
            if (noTradingSessions != null && MsgUtil.isTagInList(TagNum.NoTradingSessions, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoTradingSessions, noTradingSessions);
                if (tradingSessionGroups != null && tradingSessionGroups.length == noTradingSessions.intValue()) {
                    for (int i = 0; i < noTradingSessions.intValue(); i++) {
                        if (tradingSessionGroups[i] != null) {
                            bao.write(tradingSessionGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "TradingSessionsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTradingSessions.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (noPositions != null && MsgUtil.isTagInList(TagNum.NoPositions, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoPositions, noPositions);
                if (positionQtyGroups != null && positionQtyGroups.length == noPositions.intValue()) {
                    for (int i = 0; i < noPositions.intValue(); i++) {
                        if (positionQtyGroups[i] != null) {
                            bao.write(positionQtyGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "PositionQtyGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoPositions.getValue(), error);
                }
            }
            if (noPosAmt != null&& MsgUtil.isTagInList(TagNum.NoPosAmt, SECURED_TAGS, secured)) {
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
            if (adjustmentType != null && MsgUtil.isTagInList(TagNum.AdjustmentType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AdjustmentType, adjustmentType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ThresholdAmount, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ThresholdAmount, thresholdAmount);
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
                parties = new Parties44(context);
            }
            parties.decode(tag, message);
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument44(context);
            }
            instrument.decode(tag, message);
        }
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrumentLegs = new InstrumentLeg[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrumentLeg component = new InstrumentLeg44(context);
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
                    UnderlyingInstrument component = new UnderlyingInstrument44(context);
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
                    TradingSessionGroup group = new TradingSessionGroup44(context);
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
                    PositionQtyGroup group = new PositionQtyGroup44(context);
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
                    PosAmtGroup group = new PosAmtGroup44(context);
                    group.decode(message);
                    posAmtGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PositionMaintenanceReportMsg] message version [4.4].";
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
