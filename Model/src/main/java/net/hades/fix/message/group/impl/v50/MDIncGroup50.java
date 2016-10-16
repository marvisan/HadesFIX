/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDIncGroup50.java
 *
 * $Id: MDIncGroup50.java,v 1.7 2011-04-14 23:44:36 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.group.MDIncGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DealingCapacity;
import net.hades.fix.message.type.MDOriginType;
import net.hades.fix.message.type.TickDirection;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

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
import net.hades.fix.message.type.DeleteReason;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MDQuoteType;
import net.hades.fix.message.type.MDUpdateAction;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.QuoteCondition;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradeCondition;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCTimeAdapter;

/**
 * FIX 5.0 implementation of MDReqGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="Inc")
@XmlType(propOrder = {"instrument", "underlyingInstruments", "instrumentLegs", "partyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MDIncGroup50 extends MDIncGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50().getFragmentAllTags();
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDIncGroup50() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MDIncGroup50(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @XmlAttribute(name = "UpdtAct")
    @Override
    public MDUpdateAction getMdUpdateAction() {
        return mdUpdateAction;
    }

    @Override
    public void setMdUpdateAction(MDUpdateAction mdUpdateAction) {
        this.mdUpdateAction = mdUpdateAction;
    }

    @XmlAttribute(name = "DelRsn")
    @Override
    public DeleteReason getDeleteReason() {
        return deleteReason;
    }

    @Override
    public void setDeleteReason(DeleteReason deleteReason) {
        this.deleteReason = deleteReason;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public MDEntryType getMdEntryType() {
        return mdEntryType;
    }

    @Override
    public void setMdEntryType(MDEntryType mdEntryType) {
        this.mdEntryType = mdEntryType;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getMdEntryID() {
        return mdEntryID;
    }

    @Override
    public void setMdEntryID(String mdEntryID) {
        this.mdEntryID = mdEntryID;
    }

    @XmlAttribute(name = "RefID")
    @Override
    public String getMdEntryRefID() {
        return mdEntryRefID;
    }

    @Override
    public void setMdEntryRefID(String mdEntryRefID) {
        this.mdEntryRefID = mdEntryRefID;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50(context);
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
            noUnderlyings = underlyingInstruments.length;
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

    @XmlAttribute(name = "FinclStat")
    @Override
    public String getFinancialStatus() {
        return financialStatus;
    }

    @Override
    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    @XmlAttribute(name = "CorpActn")
    @Override
    public String getCorporateAction() {
        return corporateAction;
    }

    @Override
    public void setCorporateAction(String corporateAction) {
        this.corporateAction = corporateAction;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getMdEntryPx() {
        return mdEntryPx;
    }

    @Override
    public void setMdEntryPx(Double mdEntryPx) {
        this.mdEntryPx = mdEntryPx;
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

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @XmlAttribute(name = "Sz")
    @Override
    public Double getMdEntrySize() {
        return mdEntrySize;
    }

    @Override
    public void setMdEntrySize(Double mdEntrySize) {
        this.mdEntrySize = mdEntrySize;
    }

    @XmlAttribute(name = "Dt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getMdEntryDate() {
        return mdEntryDate;
    }

    @Override
    public void setMdEntryDate(Date mdEntryDate) {
        this.mdEntryDate = mdEntryDate;
    }

    @XmlAttribute(name = "Tm")
    @XmlJavaTypeAdapter(FixUTCTimeAdapter.class)
    @Override
    public Date getMdEntryTime() {
        return mdEntryTime;
    }

    @Override
    public void setMdEntryTime(Date mdEntryTime) {
        this.mdEntryTime = mdEntryTime;
    }

    @XmlAttribute(name = "TickDirctn")
    @Override
    public TickDirection getTickDirection() {
        return tickDirection;
    }

    @Override
    public void setTickDirection(TickDirection tickDirection) {
        this.tickDirection = tickDirection;
    }

    @XmlAttribute(name = "Mkt")
    @Override
    public String getMdMkt() {
        return mdMkt;
    }

    @Override
    public void setMdMkt(String mdMkt) {
        this.mdMkt = mdMkt;
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

    @XmlAttribute(name = "QCond")
    @Override
    public QuoteCondition getQuoteCondition() {
        return quoteCondition;
    }

    @Override
    public void setQuoteCondition(QuoteCondition quoteCondition) {
        this.quoteCondition = quoteCondition;
    }

    @XmlAttribute(name = "TrdCond")
    @Override
    public TradeCondition getTradeCondition() {
        return tradeCondition;
    }

    @Override
    public void setTradeCondition(TradeCondition tradeCondition) {
        this.tradeCondition = tradeCondition;
    }

    @XmlAttribute(name = "Orig")
    @Override
    public String getMdEntryOriginator() {
        return mdEntryOriginator;
    }

    @Override
    public void setMdEntryOriginator(String mdEntryOriginator) {
        this.mdEntryOriginator = mdEntryOriginator;
    }

    @XmlAttribute(name = "LctnID")
    @Override
    public String getLocationID() {
        return locationID;
    }

    @Override
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    @XmlAttribute(name = "DeskID")
    @Override
    public String getDeskID() {
        return deskID;
    }

    @Override
    public void setDeskID(String deskID) {
        this.deskID = deskID;
    }

    @XmlAttribute(name = "OpenClsSettlFlag")
    @Override
    public String getOpenCloseSettlFlag() {
        return openCloseSettlFlag;
    }

    @Override
    public void setOpenCloseSettlFlag(String openCloseSettlFlag) {
        this.openCloseSettlFlag = openCloseSettlFlag;
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
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getExpireTime() {
        return expireTime;
    }

    @Override
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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

    @XmlAttribute(name = "ExecInst")
    @Override
    public String getExecInst() {
        return execInst;
    }

    @Override
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    @XmlAttribute(name = "SellerDays")
    @Override
    public Integer getSellerDays() {
        return sellerDays;
    }

    @Override
    public void setSellerDays(Integer sellerDays) {
        this.sellerDays = sellerDays;
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

    @XmlAttribute(name = "EntryID")
    @Override
    public String getQuoteEntryID() {
        return quoteEntryID;
    }

    @Override
    public void setQuoteEntryID(String quoteEntryID) {
        this.quoteEntryID = quoteEntryID;
    }

    @XmlAttribute(name = "Buyer")
    @Override
    public String getMdEntryBuyer() {
        return mdEntryBuyer;
    }

    @Override
    public void setMdEntryBuyer(String mdEntryBuyer) {
        this.mdEntryBuyer = mdEntryBuyer;
    }

    @XmlAttribute(name = "Seller")
    @Override
    public String getMdEntrySeller() {
        return mdEntrySeller;
    }

    @Override
    public void setMdEntrySeller(String mdEntrySeller) {
        this.mdEntrySeller = mdEntrySeller;
    }

    @XmlAttribute(name = "NumOfOrds")
    @Override
    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    @Override
    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    @XmlAttribute(name = "PosNo")
    @Override
    public Integer getMdEntryPositionNo() {
        return mdEntryPositionNo;
    }

    @Override
    public void setMdEntryPositionNo(Integer mdEntryPositionNo) {
        this.mdEntryPositionNo = mdEntryPositionNo;
    }

    @XmlAttribute(name = "Scope")
    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
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

    @XmlAttribute(name = "NetChgPrevDay")
    @Override
    public Double getNetChgPrevDay() {
        return netChgPrevDay;
    }

    @Override
    public void setNetChgPrevDay(Double netChgPrevDay) {
        this.netChgPrevDay = netChgPrevDay;
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

    @XmlAttribute(name = "MDPxLvl")
    @Override
    public Integer getMdPriceLevel() {
        return mdPriceLevel;
    }

    @Override
    public void setMdPriceLevel(Integer mdPriceLevel) {
        this.mdPriceLevel = mdPriceLevel;
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

    @XmlAttribute(name = "MDOrigTyp")
    @Override
    public MDOriginType getMdOriginType() {
        return mdOriginType;
    }

    @Override
    public void setMdOriginType(MDOriginType mdOriginType) {
        this.mdOriginType = mdOriginType;
    }

    @XmlAttribute(name = "HighPx")
    @Override
    public Double getHighPx() {
        return highPx;
    }

    @Override
    public void setHighPx(Double highPx) {
        this.highPx = highPx;
    }

    @XmlAttribute(name = "LowPx")
    @Override
    public Double getLowPx() {
        return lowPx;
    }

    @Override
    public void setLowPx(Double lowPx) {
        this.lowPx = lowPx;
    }

    @XmlAttribute(name = "TrdVol")
    @Override
    public Double getTradeVolume() {
        return tradeVolume;
    }

    @Override
    public void setTradeVolume(Double tradeVolume) {
        this.tradeVolume = tradeVolume;
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

    @XmlAttribute(name = "MDQteTyp")
    @Override
    public MDQuoteType getMdQuoteType() {
        return mdQuoteType;
    }

    @Override
    public void setMdQuoteType(MDQuoteType mdQuoteType) {
        this.mdQuoteType = mdQuoteType;
    }

    @XmlAttribute(name = "RptSeq")
    @Override
    public Integer getRptSeq() {
        return rptSeq;
    }

    @Override
    public void setRptSeq(Integer rptSeq) {
        this.rptSeq = rptSeq;
    }

    @XmlAttribute(name = "DealingCpcty")
    @Override
    public DealingCapacity getDealingCapacity() {
        return dealingCapacity;
    }

    @Override
    public void setDealingCapacity(DealingCapacity dealingCapacity) {
        this.dealingCapacity = dealingCapacity;
    }

    @XmlAttribute(name = "MDEntrySpotRt")
    @Override
    public Double getMdEntrySpotRate() {
        return mdEntrySpotRate;
    }

    @Override
    public void setMdEntrySpotRate(Double mdEntrySpotRate) {
        this.mdEntrySpotRate = mdEntrySpotRate;
    }

    @XmlAttribute(name = "MDEntryFwdPnts")
    @Override
    public Double getMdEntryForwardPoints() {
        return mdEntryForwardPoints;
    }

    @Override
    public void setMdEntryForwardPoints(Double mdEntryForwardPoints) {
        this.mdEntryForwardPoints = mdEntryForwardPoints;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        this.parties = new Parties50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearParties() {
        this.parties = null;
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {

        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50(context);
            }
            instrument.decode(tag, message);
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
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50(context);
            }
            parties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MDIncGroup] group version [5.0].";
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
