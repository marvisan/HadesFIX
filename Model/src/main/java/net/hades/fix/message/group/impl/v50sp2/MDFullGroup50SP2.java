/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDReqGroup50SP2.java
 *
 * $Id: MDFullGroup50SP2.java,v 1.10 2011-04-29 04:07:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.MDSecSizeGroup;
import net.hades.fix.message.struct.Tag;
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

import net.hades.fix.message.comp.impl.v50sp2.SpreadOrBenchmarkCurveData50SP2;
import net.hades.fix.message.comp.impl.v50sp2.YieldData50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDFullGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.RateSourceGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DealingCapacity;
import net.hades.fix.message.type.HaltReason;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MDOriginType;
import net.hades.fix.message.type.MDQuoteType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QuoteCondition;
import net.hades.fix.message.type.SecurityTradingStatus;
import net.hades.fix.message.type.TickDirection;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradeCondition;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCTimeAdapter;

/**
 * FIX 5.0 implementation of MDReqGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="Full")
@XmlType(propOrder = {"yieldData", "spreadOrBenchmarkCurveData", "rateSources", "MDSecSizeGroups", "partyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MDFullGroup50SP2 extends MDFullGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData50SP2().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS = new SpreadOrBenchmarkCurveData50SP2().getFragmentAllTags();
    protected static final Set<Integer> RATE_SOURCE_GROUP_TAGS = new RateSourceGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> MD_SECSIZE_GROUP_TAGS = new MDSecSizeGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP2().getFragmentAllTags();
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS);
        ALL_TAGS.addAll(RATE_SOURCE_GROUP_TAGS);
        ALL_TAGS.addAll(MD_SECSIZE_GROUP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(RATE_SOURCE_GROUP_TAGS);
        START_COMP_TAGS.addAll(MD_SECSIZE_GROUP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDFullGroup50SP2() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MDFullGroup50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "Px")
    @Override
    public Double getMdEntryPx() {
        return mdEntryPx;
    }

    @Override
    public void setMdEntryPx(Double mdEntryPx) {
        this.mdEntryPx = mdEntryPx;
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
    public YieldData getYieldData() {
        return yieldData;
    }

    @Override
    public void setYieldData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.yieldData = new YieldData50SP2(context);
    }

    public void setYieldData(YieldData yieldData) {
        this.yieldData = yieldData;
    }

    @Override
    public void clearYieldData() {
        this.yieldData = null;
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

    public void setSpreadOrBenchmarkCurveData(SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData) {
        this.spreadOrBenchmarkCurveData = spreadOrBenchmarkCurveData;
    }

    @Override
    public void clearSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = null;
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

    @XmlAttribute(name = "SettlCcy")
    @Override
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    @Override
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
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

    @Override
    public Integer getNoOfSecSizes() {
        return noOfSecSizes;
    }

    @Override
    public void setNoOfSecSizes(Integer noOfSecSizes) {
        this.noOfSecSizes = noOfSecSizes;
        if (noOfSecSizes != null) {
            mdSecSizeGroups = new MDSecSizeGroup[noOfSecSizes.intValue()];
            for (int i = 0; i < mdSecSizeGroups.length; i++) {
                mdSecSizeGroups[i] = new MDSecSizeGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public MDSecSizeGroup[] getMDSecSizeGroups() {
        return mdSecSizeGroups;
    }

    public void setMDSecSizeGroups(MDSecSizeGroup[] mdSecSizeGroups) {
        this.mdSecSizeGroups = mdSecSizeGroups;
        if (mdSecSizeGroups != null) {
            noOfSecSizes = new Integer(mdSecSizeGroups.length);
        }
    }

    @Override
    public MDSecSizeGroup addMDSecSizeGroup() {
        MDSecSizeGroup group = new MDSecSizeGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MDSecSizeGroup> groups = new ArrayList<MDSecSizeGroup>();
        if (mdSecSizeGroups != null && mdSecSizeGroups.length > 0) {
            groups = new ArrayList<MDSecSizeGroup>(Arrays.asList(mdSecSizeGroups));
        }
        groups.add(group);
        mdSecSizeGroups = groups.toArray(new MDSecSizeGroup[groups.size()]);
        noOfSecSizes = new Integer(mdSecSizeGroups.length);

        return group;
    }

    @Override
    public MDSecSizeGroup deleteMDSecSizeGroup(int index) {
        MDSecSizeGroup result = null;
        if (mdSecSizeGroups != null && mdSecSizeGroups.length > 0 && mdSecSizeGroups.length > index) {
            List<MDSecSizeGroup> groups = new ArrayList<MDSecSizeGroup>(Arrays.asList(mdSecSizeGroups));
            result = groups.remove(index);
            mdSecSizeGroups = groups.toArray(new MDSecSizeGroup[groups.size()]);
            if (mdSecSizeGroups.length > 0) {
                noOfSecSizes = new Integer(mdSecSizeGroups.length);
            } else {
                mdSecSizeGroups = null;
                noOfSecSizes = null;
            }
        }

        return result;
    }

    @Override
    public int clearMDSecSizeGroups() {
        int result = 0;
        if (mdSecSizeGroups != null && mdSecSizeGroups.length > 0) {
            result = mdSecSizeGroups.length;
            mdSecSizeGroups = null;
            noOfSecSizes = null;
        }

        return result;
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

    @XmlAttribute(name = "TrdgStat")
    @Override
    public SecurityTradingStatus getSecurityTradingStatus() {
        return securityTradingStatus;
    }

    @Override
    public void setSecurityTradingStatus(SecurityTradingStatus securityTradingStatus) {
        this.securityTradingStatus = securityTradingStatus;
    }

    @XmlAttribute(name = "HaltRsn")
    @Override
    public HaltReason getHaltReason() {
        return haltReason;
    }

    @Override
    public void setHaltReason(HaltReason haltReason) {
        this.haltReason = haltReason;
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

    @XmlAttribute(name = "TrdTyp")
    @Override
    public TrdType getTrdType() {
        return trdType;
    }

    @Override
    public void setTrdType(TrdType trdType) {
        this.trdType = trdType;
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

    @XmlAttribute(name = "FirstPx")
    @Override
    public Double getFirstPx() {
        return firstPx;
    }

    @Override
    public void setFirstPx(Double firstPx) {
        this.firstPx = firstPx;
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
        this.parties = new Parties50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
            ((Parties50SP2) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {

        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (YIELD_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (yieldData == null) {
                yieldData = new YieldData50SP2(context);
            }
            yieldData.decode(tag, message);
        }
        if (SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50SP2(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
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
        if (MD_SECSIZE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noOfSecSizes != null && noOfSecSizes.intValue() > 0) {
                message.reset();
                mdSecSizeGroups = new MDSecSizeGroup[noOfSecSizes.intValue()];
                for (int i = 0; i < mdSecSizeGroups.length; i++) {
                    MDSecSizeGroup group = new MDSecSizeGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
                    group.decode(message);
                    mdSecSizeGroups[i] = group;
                }
            }
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP2(context);
            }
            parties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MDFullGroup] group version [5.0SP2].";
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
