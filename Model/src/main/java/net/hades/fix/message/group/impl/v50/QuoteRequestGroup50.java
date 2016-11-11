/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestGroup50.java
 *
 * $Id: QuoteRequestGroup50.java,v 1.9 2011-04-14 23:44:34 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;

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

import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50;
import net.hades.fix.message.comp.impl.v50.Instrument50;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50;
import net.hades.fix.message.comp.impl.v50.Parties50;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50;
import net.hades.fix.message.comp.impl.v50.Stipulations50;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50;
import net.hades.fix.message.comp.impl.v50.YieldData50;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.QuoteRequestLegGroup;
import net.hades.fix.message.group.QuoteQualifierGroup;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuotePriceType;
import net.hades.fix.message.type.QuoteRequestType;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX 5.0 implementation of QuoteRequestGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="QuotReq")
@XmlType(propOrder = {"instrument", "financingDetails", "underlyingInstruments",
    "orderQtyData", "stipulationsGroups", "legQuoteSymbolGroups", "quoteQualifierGroups",
    "spreadOrBenchmarkCurveData", "yieldData", "partyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteRequestGroup50 extends QuoteRequestGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -3617735526203431402L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50().getFragmentAllTags();
    protected static final Set<Integer> FINANCING_DETAILS_COMP_TAGS = new FinancingDetails50().getFragmentAllTags();
    protected static final Set<Integer> UNDLY_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations50().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData50().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS = new SpreadOrBenchmarkCurveData50().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData50().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50().getFragmentAllTags();
    protected static final Set<Integer> LEG_QUOTE_SYMBOL_GROUP_TAGS = new QuoteRequestLegGroup50().getFragmentAllTags();
    protected static final Set<Integer> QUOTE_QUALIFIER_GROUP_TAGS = new QuoteQualifierGroup50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        ALL_TAGS.addAll(UNDLY_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(LEG_QUOTE_SYMBOL_GROUP_TAGS);
        ALL_TAGS.addAll(QUOTE_QUALIFIER_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDLY_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_QUOTE_SYMBOL_GROUP_TAGS);
        START_COMP_TAGS.addAll(QUOTE_QUALIFIER_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public QuoteRequestGroup50() {
        instrument = new Instrument50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public QuoteRequestGroup50(FragmentContext context) {
        super(context);
        instrument = new Instrument50(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @XmlElementRef
    @Override
    public FinancingDetails getFinancingDetails() {
        return financingDetails;
    }

    public void setFinancingDetails(FinancingDetails financingDetails) {
        this.financingDetails = financingDetails;
    }

    @Override
    public void setFinancingDetails() {
        this.financingDetails = new FinancingDetails50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
            for (int i = 0; i < underlyingInstruments.length; i++) {
                underlyingInstruments[i] = new UnderlyingInstrument50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @Override
    public Stipulations getStipulations() {
        return stipulations;
    }

    @Override
    public void setStipulations() {
        this.stipulations = new Stipulations50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
            ((Stipulations50) stipulations).setStipulationsGroups(stipulationsGroups);
        }
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
        this.orderQtyData = new OrderQtyData50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
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

    @XmlAttribute(name = "AcctTyp")
    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            legQuoteSymbolGroups = new QuoteRequestLegGroup[noLegs.intValue()];
            for (int i = 0; i < legQuoteSymbolGroups.length; i++) {
                legQuoteSymbolGroups[i] = new QuoteRequestLegGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public QuoteRequestLegGroup[] getLegQuoteSymbolGroups() {
        return legQuoteSymbolGroups;
    }

    public void setLegQuoteSymbolGroups(QuoteRequestLegGroup[] legQuoteSymbolGroups) {
        this.legQuoteSymbolGroups = legQuoteSymbolGroups;
        if (legQuoteSymbolGroups != null) {
            noLegs = new Integer(legQuoteSymbolGroups.length);
        }
    }

    @Override
    public QuoteRequestLegGroup addLegQuoteSymbolGroup() {
        QuoteRequestLegGroup group = new QuoteRequestLegGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<QuoteRequestLegGroup> groups = new ArrayList<QuoteRequestLegGroup>();
        if (legQuoteSymbolGroups != null && legQuoteSymbolGroups.length > 0) {
            groups = new ArrayList<QuoteRequestLegGroup>(Arrays.asList(legQuoteSymbolGroups));
        }
        groups.add(group);
        legQuoteSymbolGroups = groups.toArray(new QuoteRequestLegGroup[groups.size()]);
        noLegs = new Integer(legQuoteSymbolGroups.length);

        return group;
    }

    @Override
    public QuoteRequestLegGroup deleteLegQuoteSymbolGroup(int index) {
        QuoteRequestLegGroup result = null;
        if (legQuoteSymbolGroups != null && legQuoteSymbolGroups.length > 0 && legQuoteSymbolGroups.length > index) {
            List<QuoteRequestLegGroup> groups = new ArrayList<QuoteRequestLegGroup>(Arrays.asList(legQuoteSymbolGroups));
            result = groups.remove(index);
            legQuoteSymbolGroups = groups.toArray(new QuoteRequestLegGroup[groups.size()]);
            if (legQuoteSymbolGroups.length > 0) {
                noLegs = new Integer(legQuoteSymbolGroups.length);
            } else {
                legQuoteSymbolGroups = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearLegQuoteSymbolGroups() {
        int result = 0;
        if (legQuoteSymbolGroups != null && legQuoteSymbolGroups.length > 0) {
            result = legQuoteSymbolGroups.length;
            legQuoteSymbolGroups = null;
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
            quoteQualifierGroups = new QuoteQualifierGroup[noQuoteQualifiers.intValue()];
            for (int i = 0; i < quoteQualifierGroups.length; i++) {
                quoteQualifierGroups[i] = new QuoteQualifierGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        QuoteQualifierGroup group = new QuoteQualifierGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "ValidUntilTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getValidUntilTime() {
        return validUntilTime;
    }

    @Override
    public void setValidUntilTime(Date validUntilTime) {
        this.validUntilTime = validUntilTime;
    }

    @XmlElementRef
    @Override
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        this.yieldData = new YieldData50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public void setYieldData(YieldData yieldData) {
        this.yieldData = yieldData;
    }

    @Override
    public void clearYieldData() {
        this.yieldData = null;
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
        if (FINANCING_DETAILS_COMP_TAGS.contains(tag.tagNum)) {
            if (financingDetails == null) {
                financingDetails = new FinancingDetails50(context);
            }
            financingDetails.decode(tag, message);
        }
        if (UNDLY_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument group = new UnderlyingInstrument50(context);
                    group.decode(message);
                    underlyingInstruments[i] = group;
                }
            }
        }
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations50(context);
            }
            stipulations.decode(tag, message);
        }
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData50(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (LEG_QUOTE_SYMBOL_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                legQuoteSymbolGroups = new QuoteRequestLegGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    QuoteRequestLegGroup group = new QuoteRequestLegGroup50(context);
                    group.decode(message);
                    legQuoteSymbolGroups[i] = group;
                }
            }
        }
        if (QUOTE_QUALIFIER_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteQualifiers != null && noQuoteQualifiers.intValue() > 0) {
                message.reset();
                quoteQualifierGroups = new QuoteQualifierGroup[noQuoteQualifiers.intValue()];
                for (int i = 0; i < noQuoteQualifiers.intValue(); i++) {
                    QuoteQualifierGroup group = new QuoteQualifierGroup50(context);
                    group.decode(message);
                    quoteQualifierGroups[i] = group;
                }
            }
        }
        if (SPREAD_BENCHMARK_CURVE_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
        if (YIELD_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (yieldData == null) {
                yieldData = new YieldData50(context);
            }
            yieldData.decode(tag, message);
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
        return "This tag is not supported in [QuoteRelatedSymbolGroup] group version [5.0].";
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
