/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectGroup44.java
 *
 * $Id: QuoteRequestRejectGroup44.java,v 1.9 2011-04-14 23:44:42 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v44.SpreadOrBenchmarkCurveData44;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44;
import net.hades.fix.message.group.QuoteRequestRejectGroup;
import net.hades.fix.message.group.StipulationsGroup;
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

import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44;
import net.hades.fix.message.comp.impl.v44.Instrument44;
import net.hades.fix.message.comp.impl.v44.OrderQtyData44;
import net.hades.fix.message.comp.impl.v44.Parties44;
import net.hades.fix.message.comp.impl.v44.Stipulations44;
import net.hades.fix.message.comp.impl.v44.YieldData44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.QuoteQualifierGroup;
import net.hades.fix.message.group.QuoteRequestLegGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuotePriceType;
import net.hades.fix.message.type.QuoteRequestType;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX 4.4 implementation of QuoteRequestRejectGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="QuotReqRej")
@XmlType(propOrder = {"instrument", "financingDetails", "underlyingInstruments",
    "orderQtyData", "stipulationsGroups", "quoteRequestLegGroups", "quoteQualifierGroups",
    "spreadOrBenchmarkCurveData", "yieldData", "partyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteRequestRejectGroup44 extends QuoteRequestRejectGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7350540429470822282L;
    
    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument44().getFragmentAllTags();
    protected static final Set<Integer> FINANCING_DETAILS_COMP_TAGS = new FinancingDetails44().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument44().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData44().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations44().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS = new SpreadOrBenchmarkCurveData44().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData44().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties44().getFragmentAllTags();
    protected static final Set<Integer> QUOT_REQ_LEG_GROUP_TAGS = new QuoteRequestLegGroup44().getFragmentAllTags();
    protected static final Set<Integer> QUOTE_QUALIFIER_GROUP_TAGS = new QuoteQualifierGroup44().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(QUOT_REQ_LEG_GROUP_TAGS);
        ALL_TAGS.addAll(QUOTE_QUALIFIER_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(QUOT_REQ_LEG_GROUP_TAGS);
        START_COMP_TAGS.addAll(QUOTE_QUALIFIER_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteRequestRejectGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument44(context);
    }
    
    public QuoteRequestRejectGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        instrument = new Instrument44(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////
    
    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.financingDetails = new FinancingDetails44(context);
    }

    public void setFinancingDetails(FinancingDetails financingDetails) {
        this.financingDetails = financingDetails;
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

    @XmlAttribute(name = "PrevClsPx")
    @Override
    public Double getPrevClosePx() {
        return prevClosePx;
    }

    @Override
    public void setPrevClosePx(Double prevClosePx) {
        this.prevClosePx = prevClosePx;
    }

    @XmlAttribute(name = "ReqTyp")
    @Override
    public QuoteRequestType getQuoteRequestType() {
        return quoteRequestType;
    }

    @Override
    public void setQuoteRequestType(QuoteRequestType quoteRequestType) {
        this.quoteRequestType = quoteRequestType;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public QuoteType getQuoteType() {
        return quoteType;
    }

    @Override
    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
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

    @XmlAttribute(name = "Side")
    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.orderQtyData = new OrderQtyData44(context);
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
    }

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
    }

    @Override
    public Stipulations getStipulations() {
        return stipulations;
    }

    @Override
    public void setStipulations() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.stipulations = new Stipulations44(context);
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
            ((Stipulations44) stipulations).setStipulationsGroups(stipulationsGroups);
        }
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

    @XmlAttribute(name = "OrdTyp")
    @Override
    public OrdType getOrdType() {
        return ordType;
    }

    @Override
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
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

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
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
    public Integer getAcctIDSource() {
        return acctIDSource;
    }

    @Override
    public void setAcctIDSource(Integer acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            quoteRequestLegGroups = new QuoteRequestLegGroup[noLegs.intValue()];
            for (int i = 0; i < quoteRequestLegGroups.length; i++) {
                quoteRequestLegGroups[i] = new QuoteRequestLegGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public QuoteRequestLegGroup[] getQuoteRequestLegGroups() {
        return quoteRequestLegGroups;
    }

    public void setQuoteRequestLegGroups(QuoteRequestLegGroup[] quoteRequestLegGroups) {
        this.quoteRequestLegGroups = quoteRequestLegGroups;
        if (quoteRequestLegGroups != null) {
            noLegs = new Integer(quoteRequestLegGroups.length);
        }
    }

    @Override
    public QuoteRequestLegGroup addQuoteRequestLegGroup() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        QuoteRequestLegGroup group = new QuoteRequestLegGroup44(context);
        List<QuoteRequestLegGroup> groups = new ArrayList<QuoteRequestLegGroup>();
        if (quoteRequestLegGroups != null && quoteRequestLegGroups.length > 0) {
            groups = new ArrayList<QuoteRequestLegGroup>(Arrays.asList(quoteRequestLegGroups));
        }
        groups.add(group);
        quoteRequestLegGroups = groups.toArray(new QuoteRequestLegGroup[groups.size()]);
        noLegs = new Integer(quoteRequestLegGroups.length);

        return group;
    }

    @Override
    public QuoteRequestLegGroup deleteQuoteRequestLegGroup(int index) {
        QuoteRequestLegGroup result = null;
        if (quoteRequestLegGroups != null && quoteRequestLegGroups.length > 0 && quoteRequestLegGroups.length > index) {
            List<QuoteRequestLegGroup> groups = new ArrayList<QuoteRequestLegGroup>(Arrays.asList(quoteRequestLegGroups));
            result = groups.remove(index);
            quoteRequestLegGroups = groups.toArray(new QuoteRequestLegGroup[groups.size()]);
            if (quoteRequestLegGroups.length > 0) {
                noLegs = new Integer(quoteRequestLegGroups.length);
            } else {
                quoteRequestLegGroups = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteRequestLegGroups() {
        int result = 0;
        if (quoteRequestLegGroups != null && quoteRequestLegGroups.length > 0) {
            result = quoteRequestLegGroups.length;
            quoteRequestLegGroups = null;
            noLegs = null;
        }

        return result;
    }

    @Override
    public Integer getNoQuoteQualifiers() {
        return noQuoteQualifiers;
    }

    @Override
    public void setNoQuoteQualifiers(Integer noQuoteQualifiers) {
        this.noQuoteQualifiers = noQuoteQualifiers;
        if (noQuoteQualifiers != null) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            quoteQualifierGroups = new QuoteQualifierGroup[noQuoteQualifiers.intValue()];
            for (int i = 0; i < quoteQualifierGroups.length; i++) {
                quoteQualifierGroups[i] = new QuoteQualifierGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public QuoteQualifierGroup[] getQuoteQualifierGroups() {
        return quoteQualifierGroups;
    }

    public void setQuoteQualifierGroups(QuoteQualifierGroup[] quoteQualifierGroups) {
        this.quoteQualifierGroups = quoteQualifierGroups;
        if (quoteQualifierGroups != null) {
            noQuoteQualifiers = new Integer(quoteQualifierGroups.length);
        }
    }

    @Override
    public QuoteQualifierGroup addQuoteQualifierGroup() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        QuoteQualifierGroup group = new QuoteQualifierGroup44(context);
        List<QuoteQualifierGroup> groups = new ArrayList<QuoteQualifierGroup>();
        if (quoteQualifierGroups != null && quoteQualifierGroups.length > 0) {
            groups = new ArrayList<QuoteQualifierGroup>(Arrays.asList(quoteQualifierGroups));
        }
        groups.add(group);
        quoteQualifierGroups = groups.toArray(new QuoteQualifierGroup[groups.size()]);
        noQuoteQualifiers = new Integer(quoteQualifierGroups.length);

        return group;
    }

    @Override
    public QuoteQualifierGroup deleteQuoteQualifierGroup(int index) {
        QuoteQualifierGroup result = null;
        if (quoteQualifierGroups != null && quoteQualifierGroups.length > 0 && quoteQualifierGroups.length > index) {
            List<QuoteQualifierGroup> groups = new ArrayList<QuoteQualifierGroup>(Arrays.asList(quoteQualifierGroups));
            result = groups.remove(index);
            quoteQualifierGroups = groups.toArray(new QuoteQualifierGroup[groups.size()]);
            if (quoteQualifierGroups.length > 0) {
                noQuoteQualifiers = new Integer(quoteQualifierGroups.length);
            } else {
                quoteQualifierGroups = null;
                noQuoteQualifiers = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteQualifierGroups() {
        int result = 0;
        if (quoteQualifierGroups != null && quoteQualifierGroups.length > 0) {
            result = quoteQualifierGroups.length;
            quoteQualifierGroups = null;
            noQuoteQualifiers = null;
        }

        return result;
    }

    @XmlAttribute(name = "QuotPxTyp")
    @Override
    public QuotePriceType getQuotePriceType() {
        return quotePriceType;
    }

    @Override
    public void setQuotePriceType(QuotePriceType quotePriceType) {
        this.quotePriceType = quotePriceType;
    }

    @XmlElementRef
    @Override
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData44(context);
    }

    @Override
    public void clearSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = null;
    }

    public void setSpreadOrBenchmarkCurveData(SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData) {
         this.spreadOrBenchmarkCurveData = spreadOrBenchmarkCurveData;
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

    @XmlAttribute(name = "Px2")
    @Override
    public Double getPrice2() {
        return price2;
    }

    @Override
    public void setPrice2(Double price2) {
        this.price2 = price2;
    }

    @XmlElementRef
    @Override
    public YieldData getYieldData() {
        return yieldData;
    }

    @Override
    public void setYieldData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.yieldData = new YieldData44(context);
    }

    @Override
    public void clearYieldData() {
        this.yieldData = null;
    }

    public void setYieldData(YieldData yieldData) {
        this.yieldData = yieldData;
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (financingDetails != null) {
                bao.write(financingDetails.encode(getMsgSecureTypeForFlag(secured)));
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.PrevClosePx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PrevClosePx, prevClosePx);
            }
            if (quoteRequestType != null && MsgUtil.isTagInList(TagNum.QuoteRequestType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteRequestType, quoteRequestType.getValue());
            }
            if (quoteType != null && MsgUtil.isTagInList(TagNum.QuoteType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteType, quoteType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (MsgUtil.isTagInList(TagNum.TradeOriginationDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcDate(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (qtyType != null && MsgUtil.isTagInList(TagNum.QtyType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QtyType, qtyType.getValue());
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (stipulations != null) {
                bao.write(stipulations.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.SettlType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlType, settlType);
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate2, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate2, settlDate2);
            }
            if (MsgUtil.isTagInList(TagNum.OrderQty2, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderQty2, orderQty2);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Account, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Account, account);
            }
            if (MsgUtil.isTagInList(TagNum.AcctIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource);
            }
            if (noLegs != null) {
                if (MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                    TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                    if (quoteRequestLegGroups != null && quoteRequestLegGroups.length == noLegs.intValue()) {
                        for (int i = 0; i < noLegs.intValue(); i++) {
                            if (quoteRequestLegGroups[i] != null) {
                                bao.write(quoteRequestLegGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                            }
                        }
                    } else {
                        String error = "QuoteRequestLegGroups field has been set but there is no data or the number of groups does not match.";
                        LOGGER.severe(error);
                        throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegs.getValue(), error);
                    }
                }
            }
            if (noQuoteQualifiers != null) {
                if (MsgUtil.isTagInList(TagNum.NoQuoteQualifiers, SECURED_TAGS, secured)) {
                    TagEncoder.encode(bao, TagNum.NoQuoteQualifiers, noQuoteQualifiers);
                    if (quoteQualifierGroups != null && quoteQualifierGroups.length == noQuoteQualifiers.intValue()) {
                        for (int i = 0; i < noQuoteQualifiers.intValue(); i++) {
                            if (quoteQualifierGroups[i] != null) {
                                bao.write(quoteQualifierGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                            }
                        }
                    } else {
                        String error = "QuoteQualifierGroups field has been set but there is no data or the number of groups does not match.";
                        LOGGER.severe(error);
                        throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoQuoteQualifiers.getValue(), error);
                    }
                }
            }
            if (quotePriceType != null && MsgUtil.isTagInList(TagNum.QuotePriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuotePriceType, quotePriceType.getValue());
            }
            if (ordType != null && MsgUtil.isTagInList(TagNum.OrdType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExpireTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (priceType != null && MsgUtil.isTagInList(TagNum.PriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Price, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Price, price);
            }
            if (MsgUtil.isTagInList(TagNum.Price2, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Price2, price2);
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
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
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument44(context);
            }
            instrument.decode(tag, message);
        }
        if (FINANCING_DETAILS_COMP_TAGS.contains(tag.tagNum)) {
            if (financingDetails == null) {
                financingDetails = new FinancingDetails44(context);
            }
            financingDetails.decode(tag, message);
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
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData44(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations44(context);
            }
            stipulations.decode(tag, message);
        }
        if (QUOT_REQ_LEG_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                quoteRequestLegGroups = new QuoteRequestLegGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    QuoteRequestLegGroup group = new QuoteRequestLegGroup44(context);
                    group.decode(message);
                    quoteRequestLegGroups[i] = group;
                }
            }
        }
        if (QUOTE_QUALIFIER_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteQualifiers != null && noQuoteQualifiers.intValue() > 0) {
                message.reset();
                quoteQualifierGroups = new QuoteQualifierGroup[noQuoteQualifiers.intValue()];
                for (int i = 0; i < noQuoteQualifiers.intValue(); i++) {
                    QuoteQualifierGroup group = new QuoteQualifierGroup44(context);
                    group.decode(message);
                    quoteQualifierGroups[i] = group;
                }
            }
        }
        if (SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData44(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
        if (YIELD_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (yieldData == null) {
                yieldData = new YieldData44(context);
            }
            yieldData.decode(tag, message);
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties44(context);
            }
            parties.decode(tag, message);
        }
    }
    
    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }
    
    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteRequestRejectGroup] group version [4.4].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
