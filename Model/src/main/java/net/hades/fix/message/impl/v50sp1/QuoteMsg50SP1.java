/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteMsg50SP1.java
 *
 * $Id: QuoteMsg50SP1.java,v 1.10 2011-04-14 23:44:56 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.LegQuoteGroup;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.group.impl.v50sp1.LegQuoteGroup50SP1;
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
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v50sp1.FinancingDetails50SP1;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1;
import net.hades.fix.message.comp.impl.v50sp1.OrderQtyData50SP1;
import net.hades.fix.message.comp.impl.v50sp1.Parties50SP1;
import net.hades.fix.message.comp.impl.v50sp1.SpreadOrBenchmarkCurveData50SP1;
import net.hades.fix.message.comp.impl.v50sp1.Stipulations50SP1;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1;
import net.hades.fix.message.comp.impl.v50sp1.YieldData50SP1;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.QuoteQualifierGroup;
import net.hades.fix.message.group.impl.v50sp1.QuoteQualifierGroup50SP1;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0SP1 QuoteMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="Quot")
@XmlType(propOrder = {"header", "quoteQualifierGroups", "partyIDGroups", "instrument", "financingDetails",
    "underlyingInstruments", "orderQtyData", "stipulationsGroups", "legQuoteSymbolGroups",
    "spreadOrBenchmarkCurveData", "yieldData"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteMsg50SP1 extends QuoteMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -6758032011009277797L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> QUOTE_QUALIFIER_GROUP_TAGS = new QuoteQualifierGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP1().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP1().getFragmentAllTags();
    protected static final Set<Integer> FINANCING_DETAILS_COMP_TAGS = new FinancingDetails50SP1().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50SP1().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData50SP1().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations50SP1().getFragmentAllTags();
    protected static final Set<Integer> LEG_QUOTE_SYMBOL_GROUP_TAGS = new LegQuoteGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData50SP1().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(QUOTE_QUALIFIER_GROUP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(LEG_QUOTE_SYMBOL_GROUP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(QUOTE_QUALIFIER_GROUP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_QUOTE_SYMBOL_GROUP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public QuoteMsg50SP1() {
        super();
    }

    public QuoteMsg50SP1(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50SP1(context);
    }

    public QuoteMsg50SP1(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50SP1(context);
    }

    public QuoteMsg50SP1(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50SP1(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        QuoteMsg50SP1 fixml = (QuoteMsg50SP1) fragment;
        if (fixml.getQuoteReqID() != null) {
            quoteReqID = fixml.getQuoteReqID();
        }
        if (fixml.getQuoteID() != null) {
            quoteID = fixml.getQuoteID();
        }
        if (fixml.getQuoteMsgID() != null) {
            quoteMsgID = fixml.getQuoteMsgID();
        }
        if (fixml.getQuoteRespID() != null) {
            quoteRespID = fixml.getQuoteRespID();
        }
        if (fixml.getQuoteType() != null) {
            quoteType = fixml.getQuoteType();
        }
        if (fixml.getPrivateQuote() != null) {
            privateQuote = fixml.getPrivateQuote();
        }
        if (fixml.getQuoteQualifierGroups() != null && fixml.getQuoteQualifierGroups().length > 0) {
            setQuoteQualifierGroups(fixml.getQuoteQualifierGroups());
        }
        if (fixml.getQuoteResponseLevel() != null) {
            quoteResponseLevel = fixml.getQuoteResponseLevel();
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
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
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
        if (fixml.getSide() != null) {
            side = fixml.getSide();
        }
        if (fixml.getOrderQtyData() != null) {
            setOrderQtyData(fixml.getOrderQtyData());
        }
        if (fixml.getStipulations() != null) {
            setStipulations(fixml.getStipulations());
        }
        if (fixml.getLegQuoteSymbolGroups() != null && fixml.getLegQuoteSymbolGroups().length > 0) {
            setLegQuoteSymbolGroups(fixml.getLegQuoteSymbolGroups());
        }
        if (fixml.getBidPx() != null) {
            bidPx = fixml.getBidPx();
        }
        if (fixml.getOfferPx() != null) {
            offerPx = fixml.getOfferPx();
        }
        if (fixml.getMktBidPx() != null) {
            mktBidPx = fixml.getMktBidPx();
        }
        if (fixml.getMktOfferPx() != null) {
            mktOfferPx = fixml.getMktOfferPx();
        }
        if (fixml.getMinBidSize() != null) {
            minBidSize = fixml.getMinBidSize();
        }
        if (fixml.getBidSize() != null) {
            bidSize = fixml.getBidSize();
        }
        if (fixml.getMinOfferSize() != null) {
            minOfferSize = fixml.getMinOfferSize();
        }
        if (fixml.getOfferSize() != null) {
            offerSize = fixml.getOfferSize();
        }
        if (fixml.getMinQty() != null) {
            minQty = fixml.getMinQty();
        }
        if (fixml.getValidUntilTime() != null) {
            validUntilTime = fixml.getValidUntilTime();
        }
        if (fixml.getBidSpotRate() != null) {
            bidSpotRate = fixml.getBidSpotRate();
        }
        if (fixml.getOfferSpotRate() != null) {
            offerSpotRate = fixml.getOfferSpotRate();
        }
        if (fixml.getBidForwardPoints() != null) {
            bidForwardPoints = fixml.getBidForwardPoints();
        }
        if (fixml.getOfferForwardPoints() != null) {
            offerForwardPoints = fixml.getOfferForwardPoints();
        }
        if (fixml.getBidSwapPoints() != null) {
            bidSwapPoints = fixml.getBidSwapPoints();
        }
        if (fixml.getOfferSwapPoints() != null) {
            offerSwapPoints = fixml.getOfferSwapPoints();
        }
        if (fixml.getMidPx() != null) {
            midPx = fixml.getMidPx();
        }
        if (fixml.getBidYield() != null) {
            bidYield = fixml.getBidYield();
        }
        if (fixml.getMidYield() != null) {
            midYield = fixml.getMidYield();
        }
        if (fixml.getOfferYield() != null) {
            offerYield = fixml.getOfferYield();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getSettlType() != null) {
            settlType = fixml.getSettlType();
        }
        if (fixml.getSettlDate() != null) {
            settlDate = fixml.getSettlDate();
        }
        if (fixml.getOrdType() != null) {
            ordType = fixml.getOrdType();
        }
        if (fixml.getSettlDate2() != null) {
            settlDate2 = fixml.getSettlDate2();
        }
        if (fixml.getOrderQty2() != null) {
            orderQty2 = fixml.getOrderQty2();
        }
        if (fixml.getBidForwardPoints2() != null) {
            bidForwardPoints2 = fixml.getBidForwardPoints2();
        }
        if (fixml.getOfferForwardPoints2() != null) {
            offerForwardPoints2 = fixml.getOfferForwardPoints2();
        }
        if (fixml.getCurrency() != null) {
            currency = fixml.getCurrency();
        }
        if (fixml.getSettlCurrBidFxRate() != null) {
            settlCurrBidFxRate = fixml.getSettlCurrBidFxRate();
        }
        if (fixml.getSettlCurrOfferFxRate() != null) {
            settlCurrOfferFxRate = fixml.getSettlCurrOfferFxRate();
        }
        if (fixml.getSettlCurrFxRateCalc() != null) {
            settlCurrFxRateCalc = fixml.getSettlCurrFxRateCalc();
        }
        if (fixml.getCommType() != null) {
            commType = fixml.getCommType();
        }
        if (fixml.getCommission() != null) {
            commission = fixml.getCommission();
        }
        if (fixml.getCustOrderCapacity() != null) {
            custOrderCapacity = fixml.getCustOrderCapacity();
        }
        if (fixml.getExDestination() != null) {
            exDestination = fixml.getExDestination();
        }
        if (fixml.getExDestinationIDSource() != null) {
            exDestinationIDSource = fixml.getExDestinationIDSource();
        }
        if (fixml.getOrderCapacity() != null) {
            orderCapacity = fixml.getOrderCapacity();
        }
        if (fixml.getPriceType() != null) {
            priceType = fixml.getPriceType();
        }
        if (fixml.getSpreadOrBenchmarkCurveData() != null) {
            setSpreadOrBenchmarkCurveData(fixml.getSpreadOrBenchmarkCurveData());
        }
        if (fixml.getYieldData() != null) {
            setYieldData(fixml.getYieldData());
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

    @XmlAttribute(name = "ReqID")
    @Override
    public String getQuoteReqID() {
        return quoteReqID;
    }

    @Override
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
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

    @XmlAttribute(name = "QtMsgID")
    @Override
    public String getQuoteMsgID() {
        return quoteMsgID;
    }

    @Override
    public void setQuoteMsgID(String quoteMsgID) {
        this.quoteMsgID = quoteMsgID;
    }

    @XmlAttribute(name = "RspID")
    @Override
    public String getQuoteRespID() {
        return quoteRespID;
    }

    @Override
    public void setQuoteRespID(String quoteRespID) {
        this.quoteRespID = quoteRespID;
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

    @XmlAttribute(name = "PrvtQt")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPrivateQuote() {
        return privateQuote;
    }

    @Override
    public void setPrivateQuote(Boolean privateQuote) {
        this.privateQuote = privateQuote;
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
                quoteQualifierGroups[i] = new QuoteQualifierGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        QuoteQualifierGroup group = new QuoteQualifierGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "RspLvl")
    @Override
    public QuoteResponseLevel getQuoteResponseLevel() {
        return quoteResponseLevel;
    }

    @Override
    public void setQuoteResponseLevel(QuoteResponseLevel quoteResponseLevel) {
        this.quoteResponseLevel = quoteResponseLevel;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        this.parties = new Parties50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlElementRef
    @Override
    public FinancingDetails getFinancingDetails() {
        return financingDetails;
    }

    @Override
    public void setFinancingDetails() {
        this.financingDetails = new FinancingDetails50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "Side")
    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @XmlElementRef
    @Override
    public OrderQtyData getOrderQtyData() {
        return orderQtyData;
    }

    @Override
    public void setOrderQtyData() {
        this.orderQtyData = new OrderQtyData50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
    }

    @Override
    public Stipulations getStipulations() {
        return stipulations;
    }

    @Override
    public void setStipulations() {
        this.stipulations = new Stipulations50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            legQuoteSymbolGroups = new LegQuoteGroup[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < legQuoteSymbolGroups.length; i++) {
                legQuoteSymbolGroups[i] = new LegQuoteGroup50SP1(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public LegQuoteGroup[] getLegQuoteSymbolGroups() {
        return legQuoteSymbolGroups;
    }

    public void setLegQuoteSymbolGroups(LegQuoteGroup[] legQuoteSymbolGroups) {
        this.legQuoteSymbolGroups = legQuoteSymbolGroups;
        if (legQuoteSymbolGroups != null) {
            noLegs = new Integer(legQuoteSymbolGroups.length);
        }
    }

    @Override
    public LegQuoteGroup addLegQuoteSymbolGroup() {
        LegQuoteGroup group = new LegQuoteGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<LegQuoteGroup> groups = new ArrayList<LegQuoteGroup>();
        if (legQuoteSymbolGroups != null && legQuoteSymbolGroups.length > 0) {
            groups = new ArrayList<LegQuoteGroup>(Arrays.asList(legQuoteSymbolGroups));
        }
        groups.add(group);
        legQuoteSymbolGroups = groups.toArray(new LegQuoteGroup[groups.size()]);
        noLegs = new Integer(legQuoteSymbolGroups.length);

        return group;
    }

    @Override
    public LegQuoteGroup deleteLegQuoteSymbolGroup(int index) {
        LegQuoteGroup result = null;
        if (legQuoteSymbolGroups != null && legQuoteSymbolGroups.length > 0 && legQuoteSymbolGroups.length > index) {
            List<LegQuoteGroup> groups = new ArrayList<LegQuoteGroup>(Arrays.asList(legQuoteSymbolGroups));
            result = groups.remove(index);
            legQuoteSymbolGroups = groups.toArray(new LegQuoteGroup[groups.size()]);
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

    @XmlAttribute(name = "BidPx")
    @Override
    public Double getBidPx() {
        return bidPx;
    }

    @Override
    public void setBidPx(Double bidPx) {
        this.bidPx = bidPx;
    }

    @XmlAttribute(name = "OfrPx")
    @Override
    public Double getOfferPx() {
        return offerPx;
    }

    @Override
    public void setOfferPx(Double offerPx) {
        this.offerPx = offerPx;
    }

    @XmlAttribute(name = "MktBidPx")
    @Override
    public Double getMktBidPx() {
        return mktBidPx;
    }

    @Override
    public void setMktBidPx(Double mktBidPx) {
        this.mktBidPx = mktBidPx;
    }

    @XmlAttribute(name = "MktOfrPx")
    @Override
    public Double getMktOfferPx() {
        return mktOfferPx;
    }

    @Override
    public void setMktOfferPx(Double mktOfferPx) {
        this.mktOfferPx = mktOfferPx;
    }

    @XmlAttribute(name = "MinBidSz")
    @Override
    public Double getMinBidSize() {
        return minBidSize;
    }

    @Override
    public void setMinBidSize(Double minBidSize) {
        this.minBidSize = minBidSize;
    }

    @XmlAttribute(name = "BidSz")
    @Override
    public Double getBidSize() {
        return bidSize;
    }

    @Override
    public void setBidSize(Double bidSize) {
        this.bidSize = bidSize;
    }

    @XmlAttribute(name = "MinOfrSz")
    @Override
    public Double getMinOfferSize() {
        return minOfferSize;
    }

    @Override
    public void setMinOfferSize(Double minOfferSize) {
        this.minOfferSize = minOfferSize;
    }

    @XmlAttribute(name = "OfrSz")
    @Override
    public Double getOfferSize() {
        return offerSize;
    }

    @Override
    public void setOfferSize(Double offerSize) {
        this.offerSize = offerSize;
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

    @XmlAttribute(name = "BidSpotRt")
    @Override
    public Double getBidSpotRate() {
        return bidSpotRate;
    }

    @Override
    public void setBidSpotRate(Double bidSpotRate) {
        this.bidSpotRate = bidSpotRate;
    }

    @XmlAttribute(name = "OfrSpotRt")
    @Override
    public Double getOfferSpotRate() {
        return offerSpotRate;
    }

    @Override
    public void setOfferSpotRate(Double offerSpotRate) {
        this.offerSpotRate = offerSpotRate;
    }

    @XmlAttribute(name = "BidFwdPnts")
    @Override
    public Double getBidForwardPoints() {
        return bidForwardPoints;
    }

    @Override
    public void setBidForwardPoints(Double bidForwardPoints) {
        this.bidForwardPoints = bidForwardPoints;
    }

    @XmlAttribute(name = "OfrFwdPnts")
    @Override
    public Double getOfferForwardPoints() {
        return offerForwardPoints;
    }

    @Override
    public void setOfferForwardPoints(Double offerForwardPoints) {
        this.offerForwardPoints = offerForwardPoints;
    }

    @XmlAttribute(name = "BidSwapPnts")
    @Override
    public Double getBidSwapPoints() {
        return bidSwapPoints;
    }

    @Override
    public void setBidSwapPoints(Double bidSwapPoints) {
        this.bidSwapPoints = bidSwapPoints;
    }

    @XmlAttribute(name = "OfrSwapPnts")
    @Override
    public Double getOfferSwapPoints() {
        return offerSwapPoints;
    }

    @Override
    public void setOfferSwapPoints(Double offerSwapPoints) {
        this.offerSwapPoints = offerSwapPoints;
    }

    @XmlAttribute(name = "MidPx")
    @Override
    public Double getMidPx() {
        return midPx;
    }

    @Override
    public void setMidPx(Double midPx) {
        this.midPx = midPx;
    }

    @XmlAttribute(name = "BidYld")
    @Override
    public Double getBidYield() {
        return bidYield;
    }

    @Override
    public void setBidYield(Double bidYield) {
        this.bidYield = bidYield;
    }

    @XmlAttribute(name = "MidYld")
    @Override
    public Double getMidYield() {
        return midYield;
    }

    @Override
    public void setMidYield(Double midYield) {
        this.midYield = midYield;
    }

    @XmlAttribute(name = "OfrYld")
    @Override
    public Double getOfferYield() {
        return offerYield;
    }

    @Override
    public void setOfferYield(Double offerYield) {
        this.offerYield = offerYield;
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

    @XmlAttribute(name = "BidFwdPnts2")
    @Override
    public Double getBidForwardPoints2() {
        return bidForwardPoints2;
    }

    @Override
    public void setBidForwardPoints2(Double bidForwardPoints2) {
        this.bidForwardPoints2 = bidForwardPoints2;
    }

    @XmlAttribute(name = "OfrFwdPnts2")
    @Override
    public Double getOfferForwardPoints2() {
        return offerForwardPoints2;
    }

    @Override
    public void setOfferForwardPoints2(Double offerForwardPoints2) {
        this.offerForwardPoints2 = offerForwardPoints2;
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

    @XmlAttribute(name = "SettlCurrBidFxRt")
    @Override
    public Double getSettlCurrBidFxRate() {
        return settlCurrBidFxRate;
    }

    @Override
    public void setSettlCurrBidFxRate(Double settlCurrBidFxRate) {
        this.settlCurrBidFxRate = settlCurrBidFxRate;
    }

    @XmlAttribute(name = "SettlCurrOfrFxRt")
    @Override
    public Double getSettlCurrOfferFxRate() {
        return settlCurrOfferFxRate;
    }

    @Override
    public void setSettlCurrOfferFxRate(Double settlCurrOfferFxRate) {
        this.settlCurrOfferFxRate = settlCurrOfferFxRate;
    }

    @XmlAttribute(name = "SettlCurrFxRtCalc")
    @Override
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    @Override
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }

    @XmlAttribute(name = "CommTyp")
    @Override
    public CommType getCommType() {
        return commType;
    }

    @Override
    public void setCommType(CommType commType) {
        this.commType = commType;
    }

    @XmlAttribute(name = "Comm")
    @Override
    public Double getCommission() {
        return commission;
    }

    @Override
    public void setCommission(Double commission) {
        this.commission = commission;
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

    @XmlAttribute(name = "Cpcty")
    @Override
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    @Override
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
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
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        this.yieldData = new YieldData50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearYieldData() {
        this.yieldData = null;
    }

    public void setYieldData(YieldData yieldData) {
        this.yieldData = yieldData;
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
        if (QUOTE_QUALIFIER_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteQualifiers != null && noQuoteQualifiers.intValue() > 0) {
                message.reset();
                quoteQualifierGroups = new QuoteQualifierGroup[noQuoteQualifiers.intValue()];
                for (int i = 0; i < noQuoteQualifiers.intValue(); i++) {
                    QuoteQualifierGroup group = new QuoteQualifierGroup50SP1(context);
                    group.decode(message);
                    quoteQualifierGroups[i] = group;
                }
            }
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP1(context);
            }
            parties.decode(tag, message);
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
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData50SP1(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations50SP1(context);
            }
            stipulations.decode(tag, message);
        }
        if (LEG_QUOTE_SYMBOL_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                legQuoteSymbolGroups = new LegQuoteGroup50SP1[noLegs.intValue()];
                for (int i = 0; i < legQuoteSymbolGroups.length; i++) {
                    LegQuoteGroup component = new LegQuoteGroup50SP1(context);
                    component.decode(message);
                    legQuoteSymbolGroups[i] = component;
                }
            }
        }
        if (SPREAD_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50SP1(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
        if (YIELD_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (yieldData == null) {
                yieldData = new YieldData50SP1(context);
            }
            yieldData.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteMsg] message version [5.0SP1].";
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
