/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionReportMsg50SP2.java
 *
 * $Id: AllocationInstructionMsg44.java,v 1.3 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.type.ModelType;

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
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.PositionReportMsg;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v50sp2.ApplicationSequenceControl50SP2;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.group.PosUndInstrmtGroup;
import net.hades.fix.message.group.PositionQtyGroup;
import net.hades.fix.message.group.impl.v50sp2.PosAmtGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.PosUndInstrmtGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.PositionQtyGroup50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.PosReqType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.RegistStatus;
import net.hades.fix.message.type.SettlPriceType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 5.0SP2 PositionReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 12/12/2011, 9:32:41 AM
 */
@XmlRootElement(name="PosRpt")
@XmlType(propOrder = {"header", "applicationSequenceControl", "partyIDGroups", "instrument", "instrumentLegs", "posUndInstrmtGroups",
    "positionQtyGroups", "posAmtGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class PositionReportMsg50SP2 extends PositionReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> APPL_SEQ_CONTROL_COMP_TAGS = new ApplicationSequenceControl50SP2().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50SP2().getFragmentAllTags();
    protected static final Set<Integer> POS_UND_INSTRMT_GROUP_TAGS = new PosUndInstrmtGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> POSITION_QTY_GROUP_TAGS = new PositionQtyGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> POS_AMT_GROUP_TAGS = new PosAmtGroup50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(APPL_SEQ_CONTROL_COMP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(POS_UND_INSTRMT_GROUP_TAGS);
        ALL_TAGS.addAll(POSITION_QTY_GROUP_TAGS);
        ALL_TAGS.addAll(POS_AMT_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(APPL_SEQ_CONTROL_COMP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(POS_UND_INSTRMT_GROUP_TAGS);
        START_COMP_TAGS.addAll(POSITION_QTY_GROUP_TAGS);
        START_COMP_TAGS.addAll(POS_AMT_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PositionReportMsg50SP2() {
        super();
    }

    public PositionReportMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public PositionReportMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public PositionReportMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        PositionReportMsg50SP2 fixml = (PositionReportMsg50SP2) fragment;
        if (fixml.getApplicationSequenceControl() != null) {
            applicationSequenceControl = fixml.getApplicationSequenceControl();
        }
        if (fixml.getPosMaintRptID() != null) {
            posMaintRptID = fixml.getPosMaintRptID();
        }
        if (fixml.getPosReqID() != null) {
            posReqID = fixml.getPosReqID();
        }
        if (fixml.getPosReqType() != null) {
            posReqType = fixml.getPosReqType();
        }
        if (fixml.getSubscriptionRequestType() != null) {
            subscriptionRequestType = fixml.getSubscriptionRequestType();
        }
        if (fixml.getTotalNumPosReports() != null) {
            totalNumPosReports = fixml.getTotalNumPosReports();
        }
        if (fixml.getUnsolicitedIndicator() != null) {
            unsolicitedIndicator = fixml.getUnsolicitedIndicator();
        }
        if (fixml.getPosReqResult() != null) {
            posReqResult = fixml.getPosReqResult();
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
        if (fixml.getPriceType() != null) {
            priceType = fixml.getPriceType();
        }
        if (fixml.getSettlCurrency() != null) {
            settlCurrency = fixml.getSettlCurrency();
        }
        if (fixml.getMessageEventSource() != null) {
            messageEventSource = fixml.getMessageEventSource();
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
        if (fixml.getSettlPrice() != null) {
            settlPrice = fixml.getSettlPrice();
        }
        if (fixml.getSettlPriceType() != null) {
            settlPriceType = fixml.getSettlPriceType();
        }
        if (fixml.getPriorSettlPrice() != null) {
            priorSettlPrice = fixml.getPriorSettlPrice();
        }
        if (fixml.getMatchStatus() != null) {
            matchStatus = fixml.getMatchStatus();
        }
        if (fixml.getInstrumentLegs() != null && fixml.getInstrumentLegs().length > 0) {
            setInstrumentLegs(fixml.getInstrumentLegs());
        }
        if (fixml.getPosUndInstrmtGroups() != null && fixml.getPosUndInstrmtGroups().length > 0) {
            setPosUndInstrmtGroups(fixml.getPosUndInstrmtGroups());
        }
        if (fixml.getPositionQtyGroups() != null && fixml.getPositionQtyGroups().length > 0) {
            setPositionQtyGroups(fixml.getPositionQtyGroups());
        }
        if (fixml.getPosAmtGroups() != null && fixml.getPosAmtGroups().length > 0) {
            setPosAmtGroups(fixml.getPosAmtGroups());
        }
        if (fixml.getRegistStatus() != null) {
            registStatus = fixml.getRegistStatus();
        }
        if (fixml.getDeliveryDate() != null) {
            deliveryDate = fixml.getDeliveryDate();
        }
        if (fixml.getModelType() != null) {
            modelType = fixml.getModelType();
        }
        if (fixml.getPriceDelta() != null) {
            priceDelta = fixml.getPriceDelta();
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

    @XmlAttribute(name = "RptID")
    @Override
    public String getPosMaintRptID() {
        return posMaintRptID;
    }

    @Override
    public void setPosMaintRptID(String posMaintRptID) {
        this.posMaintRptID = posMaintRptID;
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

    @XmlAttribute(name = "ReqTyp")
    @Override
    public PosReqType getPosReqType() {
        return posReqType;
    }

    @Override
    public void setPosReqType(PosReqType posReqType) {
        this.posReqType = posReqType;
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

    @XmlAttribute(name = "TotRpts")
    @Override
    public Integer getTotalNumPosReports() {
        return totalNumPosReports;
    }

    @Override
    public void setTotalNumPosReports(Integer totalNumPosReports) {
        this.totalNumPosReports = totalNumPosReports;
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

    @XmlAttribute(name = "Rslt")
    @Override
    public Integer getPosReqResult() {
        return posReqResult;
    }

    @Override
    public void setPosReqResult(Integer posReqResult) {
        this.posReqResult = posReqResult;
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

    @XmlAttribute(name = "PxTyp")
    @Override
    public PriceType getPriceType() {
        return priceType;
    }

    @Override
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
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

    @XmlAttribute(name = "MsgEvtSrc")
    @Override
    public String getMessageEventSource() {
        return messageEventSource;
    }

    @Override
    public void setMessageEventSource(String messageEventSource) {
        this.messageEventSource = messageEventSource;
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

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @XmlAttribute(name = "SetPx")
    @Override
    public Double getSettlPrice() {
        return settlPrice;
    }

    @Override
    public void setSettlPrice(Double settlPrice) {
        this.settlPrice = settlPrice;
    }

    @XmlAttribute(name = "SetPxTyp")
    @Override
    public SettlPriceType getSettlPriceType() {
        return settlPriceType;
    }

    @Override
    public void setSettlPriceType(SettlPriceType settlPriceType) {
        this.settlPriceType = settlPriceType;
    }

    @XmlAttribute(name = "PriSetPx")
    @Override
    public Double getPriorSettlPrice() {
        return priorSettlPrice;
    }

    @Override
    public void setPriorSettlPrice(Double priorSettlPrice) {
        this.priorSettlPrice = priorSettlPrice;
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
            noLegs = instrumentLegs.length;
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

    @Override
    public Integer getNoUnderlyings() {
        return noUnderlyings;
    }

    @Override
    public void setNoUnderlyings(Integer noUnderlyings) {
        this.noUnderlyings = noUnderlyings;
        if (noUnderlyings != null) {
            posUndInstrmtGroups = new PosUndInstrmtGroup[noUnderlyings.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < posUndInstrmtGroups.length; i++) {
                posUndInstrmtGroups[i] = new PosUndInstrmtGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public PosUndInstrmtGroup[] getPosUndInstrmtGroups() {
        return posUndInstrmtGroups;
    }

    public void setPosUndInstrmtGroups(PosUndInstrmtGroup[] underlyingInstruments) {
        this.posUndInstrmtGroups = underlyingInstruments;
        if (underlyingInstruments != null) {
            noUnderlyings = new Integer(underlyingInstruments.length);
        }
    }

    @Override
    public PosUndInstrmtGroup addPosUndInstrmtGroup() {
        PosUndInstrmtGroup group = new PosUndInstrmtGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<PosUndInstrmtGroup> groups = new ArrayList<PosUndInstrmtGroup>();
        if (posUndInstrmtGroups != null && posUndInstrmtGroups.length > 0) {
            groups = new ArrayList<PosUndInstrmtGroup>(Arrays.asList(posUndInstrmtGroups));
        }
        groups.add(group);
        posUndInstrmtGroups = groups.toArray(new PosUndInstrmtGroup[groups.size()]);
        noUnderlyings = new Integer(posUndInstrmtGroups.length);

        return group;
    }

    @Override
    public PosUndInstrmtGroup deletePosUndInstrmtGroup(int index) {
        PosUndInstrmtGroup result = null;
        if (posUndInstrmtGroups != null && posUndInstrmtGroups.length > 0 && posUndInstrmtGroups.length > index) {
            List<PosUndInstrmtGroup> groups = new ArrayList<PosUndInstrmtGroup>(Arrays.asList(posUndInstrmtGroups));
            result = groups.remove(index);
            posUndInstrmtGroups = groups.toArray(new PosUndInstrmtGroup[groups.size()]);
            if (posUndInstrmtGroups.length > 0) {
                noUnderlyings = new Integer(posUndInstrmtGroups.length);
            } else {
                posUndInstrmtGroups = null;
                noUnderlyings = null;
            }
        }

        return result;
    }

    @Override
    public int clearPosUndInstrmtGroups() {
        int result = 0;
        if (posUndInstrmtGroups != null && posUndInstrmtGroups.length > 0) {
            result = posUndInstrmtGroups.length;
            posUndInstrmtGroups = null;
            noUnderlyings = null;
        }

        return result;
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
                positionQtyGroups[i] = new PositionQtyGroup50SP2(context);
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
        PositionQtyGroup group = new PositionQtyGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
                posAmtGroups[i] = new PosAmtGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        PosAmtGroup group = new PosAmtGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "RegStat")
    @Override
    public RegistStatus getRegistStatus() {
        return registStatus;
    }

    @Override
    public void setRegistStatus(RegistStatus registStatus) {
        this.registStatus = registStatus;
    }

    @XmlAttribute(name = "DlvDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @XmlAttribute(name = "ModelTyp")
    @Override
    public ModelType getModelType() {
        return modelType;
    }

    @Override
    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
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
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50SP2(context);
            }
            instrument.decode(tag, message);
        }
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrumentLegs = new InstrumentLeg[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrumentLeg component = new InstrumentLeg50SP2(context);
                    component.decode(message);
                    instrumentLegs[i] = component;
                }
            }
        }
        if (POS_UND_INSTRMT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                posUndInstrmtGroups = new PosUndInstrmtGroup[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    PosUndInstrmtGroup component = new PosUndInstrmtGroup50SP2(context);
                    component.decode(message);
                    posUndInstrmtGroups[i] = component;
                }
            }
        }
        if (POSITION_QTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noPositions != null && noPositions.intValue() > 0) {
                message.reset();
                positionQtyGroups = new PositionQtyGroup[noPositions.intValue()];
                for (int i = 0; i < noPositions.intValue(); i++) {
                    PositionQtyGroup group = new PositionQtyGroup50SP2(context);
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
                    PosAmtGroup group = new PosAmtGroup50SP2(context);
                    group.decode(message);
                    posAmtGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PositionReportMsg] message version [5.0SP2].";
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
