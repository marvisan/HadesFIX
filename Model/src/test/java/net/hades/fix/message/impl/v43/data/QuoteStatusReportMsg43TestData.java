/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusReportMsg43TestData.java
 *
 * $Id: QuoteStatusReportMsg43TestData.java,v 1.2 2011-10-29 09:42:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteStatusReportMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;

/**
 * Test utility for QuoteStatusReportMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteStatusReportMsg43TestData extends MsgTest {

    private static final QuoteStatusReportMsg43TestData INSTANCE;

    static {
        INSTANCE = new QuoteStatusReportMsg43TestData();
    }

    public static QuoteStatusReportMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.QuoteStatusReport msg) throws Exception {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.QuoteStatusReqID.FIELD, "ACD6666RRR");
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "Q63774663");
        msg.setInt(quickfix.field.QuoteType.FIELD, quickfix.field.QuoteType.INDICATIVE);
        // Parties
        quickfix.fix43.component.Parties parties = new quickfix.fix43.component.Parties();
        Parties43TestData.getInstance().populate(parties);
        msg.set(parties);

        msg.setString(quickfix.field.Account.FIELD, "54543464");
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637478466");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "FCD636744");
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);

        msg.setDouble(quickfix.field.BidPx.FIELD, 234.55);
        msg.setDouble(quickfix.field.OfferPx.FIELD, 236.55);
        msg.setDouble(quickfix.field.MktBidPx.FIELD, 34.55);
        msg.setDouble(quickfix.field.MktOfferPx.FIELD, 37.55);
        msg.setDouble(quickfix.field.MinBidSize.FIELD, 99.0);
        msg.setDouble(quickfix.field.BidSize.FIELD, 100);
        msg.setDouble(quickfix.field.MinOfferSize.FIELD, 30.0);
        msg.setDouble(quickfix.field.OfferSize.FIELD, 120.0);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setDouble(quickfix.field.BidSpotRate.FIELD, 1.024);
        msg.setDouble(quickfix.field.OfferSpotRate.FIELD, 1.202);
        msg.setDouble(quickfix.field.BidForwardPoints.FIELD, 2.0);
        msg.setDouble(quickfix.field.OfferForwardPoints.FIELD, 3.0);
        msg.setDouble(quickfix.field.MidPx.FIELD, 114.0);
        msg.setDouble(quickfix.field.BidYield.FIELD, 1.1);
        msg.setDouble(quickfix.field.MidYield.FIELD, 1.05);
        msg.setDouble(quickfix.field.OfferYield.FIELD, 1.15);
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setUtcDateOnly(quickfix.field.SettlDate.FIELD, new Date());
        msg.setChar(quickfix.field.OrdType.FIELD, quickfix.field.OrdType.FOREX_PREVIOUSLY_QUOTED);
        msg.setUtcDateOnly(quickfix.field.SettlDate2.FIELD, new Date());
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 2.876);
        msg.setDouble(quickfix.field.BidForwardPoints2.FIELD, 2.222);
        msg.setDouble(quickfix.field.OfferForwardPoints2.FIELD, 2.333);
        msg.setString(quickfix.field.Currency.FIELD, "AUD");
        msg.setDouble(quickfix.field.SettlCurrBidFxRate.FIELD, 1.09);
        msg.setDouble(quickfix.field.SettlCurrOfferFxRate.FIELD, 1.11);
        msg.setChar(quickfix.field.SettlCurrFxRateCalc.FIELD, quickfix.field.SettlCurrFxRateCalc.DIVIDE);
        msg.setChar(quickfix.field.CommType.FIELD, quickfix.field.CommType.PERCENTAGE);
        msg.setDouble(quickfix.field.Commission.FIELD, 6.99);
        msg.setInt(quickfix.field.CustOrderCapacity.FIELD, quickfix.field.CustOrderCapacity.MEMBER_TRADING_FOR_ANOTHER_MEMBER);
        msg.setString(quickfix.field.ExDestination.FIELD, "Some Destination");
        msg.setInt(quickfix.field.QuoteStatus.FIELD, quickfix.field.QuoteStatus.ACCEPTED);
    }

    public void populate(QuoteStatusReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setQuoteStatusReqID("ADFS55666TT");
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteType(QuoteType.Indicative);
        // Parties
        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        msg.setAccount("54543464");
        msg.setAccountType(AccountType.HouseTrader);
        msg.setTradingSessionID("X637478466");
        msg.setTradingSessionSubID("FCD636744");
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setBidPx(new Double(23.44));
        msg.setOfferPx(new Double(23.98));
        msg.setMktBidPx(new Double(34.55));
        msg.setMktOfferPx(new Double(37.55));
        msg.setMinBidSize(new Double(99.0));
        msg.setBidSize(new Double(100.0));
        msg.setMinOfferSize(new Double(30.0));
        msg.setOfferSize(new Double(150));
        msg.setValidUntilTime(new Date());
        msg.setBidSpotRate(new Double(9.44));
        msg.setOfferSpotRate(new Double(12.54));
        msg.setBidForwardPoints(new Double(2.44));
        msg.setOfferForwardPoints(new Double(3.44));
        msg.setMidPx(new Double(114.0));
        msg.setBidYield(new Double(1.1));
        msg.setMidYield(new Double(1.05));
        msg.setOfferYield(new Double(1.15));
        msg.setTransactTime(new Date());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.ForexMarket);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.22));
        msg.setBidForwardPoints2(new Double(2.222));
        msg.setOfferForwardPoints2(new Double(2.333));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setSettlCurrBidFxRate(new Double(1.09));
        msg.setSettlCurrOfferFxRate(new Double(1.11));
        msg.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Divide);
        msg.setCommType(CommType.CashDiscount);
        msg.setCommission(new Double(6.99));
        msg.setCustOrderCapacity(CustOrderCapacity.MemberTradingForAnotherMember);
        msg.setExDestination("Some Destination");
        msg.setQuoteStatus(QuoteStatus.Accepted);
    }

    public void check(quickfix.fix43.QuoteStatusReport expected, QuoteStatusReportMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteStatusReqID.FIELD), actual.getQuoteStatusReqID());
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getInt(quickfix.field.QuoteType.FIELD), actual.getQuoteType().getValue());
        // Parties check
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getString(quickfix.field.Account.FIELD), actual.getAccount());
        assertEquals(expected.getInt(quickfix.field.AccountType.FIELD), actual.getAccountType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getDouble(quickfix.field.BidPx.FIELD), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferPx.FIELD), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MktBidPx.FIELD), actual.getMktBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MktOfferPx.FIELD), actual.getMktOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MinBidSize.FIELD), actual.getMinBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidSize.FIELD), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MinOfferSize.FIELD), actual.getMinOfferSize().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferSize.FIELD), actual.getOfferSize().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), actual.getValidUntilTime(), false);
        assertEquals(expected.getDouble(quickfix.field.BidSpotRate.FIELD), actual.getBidSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferSpotRate.FIELD), actual.getOfferSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidForwardPoints.FIELD), actual.getBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferForwardPoints.FIELD), actual.getOfferForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MidPx.FIELD), actual.getMidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidYield.FIELD), actual.getBidYield().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MidYield.FIELD), actual.getMidYield().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferYield.FIELD), actual.getOfferYield().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate.FIELD), actual.getSettlDate());
        assertEquals(expected.getChar(quickfix.field.OrdType.FIELD), actual.getOrdType().getValue());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate2.FIELD), actual.getSettlDate2());
        assertEquals(expected.getDouble(quickfix.field.OrderQty2.FIELD), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidForwardPoints2.FIELD), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferForwardPoints2.FIELD), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        assertEquals(expected.getDouble(quickfix.field.SettlCurrBidFxRate.FIELD), actual.getSettlCurrBidFxRate().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.SettlCurrOfferFxRate.FIELD), actual.getSettlCurrOfferFxRate().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.SettlCurrFxRateCalc.FIELD), actual.getSettlCurrFxRateCalc().getValue());
        assertEquals(expected.getChar(quickfix.field.CommType.FIELD), actual.getCommType().getValue());
        assertEquals(expected.getDouble(quickfix.field.Commission.FIELD), actual.getCommission().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.CustOrderCapacity.FIELD), actual.getCustOrderCapacity().getValue());
        assertEquals(expected.getString(quickfix.field.ExDestination.FIELD), actual.getExDestination());
        assertEquals(expected.getInt(quickfix.field.QuoteStatus.FIELD), actual.getQuoteStatus().getValue());
    }

    public void check(QuoteStatusReportMsg expected, quickfix.fix43.QuoteStatusReport actual) throws Exception {
        assertEquals(expected.getQuoteStatusReqID(), actual.getString(quickfix.field.QuoteStatusReqID.FIELD));
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(expected.getQuoteType().getValue(), actual.getInt(quickfix.field.QuoteType.FIELD));
        // Parties check
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getString(quickfix.field.Account.FIELD));
        assertEquals(expected.getAccountType().getValue(), actual.getInt(quickfix.field.AccountType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getBidPx().doubleValue(), actual.getDouble(quickfix.field.BidPx.FIELD), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getDouble(quickfix.field.OfferPx.FIELD), 0.001);
        assertEquals(expected.getMktBidPx().doubleValue(), actual.getDouble(quickfix.field.MktBidPx.FIELD), 0.001);
        assertEquals(expected.getMktOfferPx().doubleValue(), actual.getDouble(quickfix.field.MktOfferPx.FIELD), 0.001);
        assertEquals(expected.getMinBidSize().doubleValue(), actual.getDouble(quickfix.field.MinBidSize.FIELD), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getDouble(quickfix.field.BidSize.FIELD), 0.001);
        assertEquals(expected.getMinOfferSize().doubleValue(), actual.getDouble(quickfix.field.MinOfferSize.FIELD), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getDouble(quickfix.field.OfferSize.FIELD), 0.001);
        assertTimestampEquals(expected.getValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getDouble(quickfix.field.BidSpotRate.FIELD), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getDouble(quickfix.field.OfferSpotRate.FIELD), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getDouble(quickfix.field.BidForwardPoints.FIELD), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getDouble(quickfix.field.OfferForwardPoints.FIELD), 0.001);
        assertEquals(expected.getMidPx().doubleValue(), actual.getDouble(quickfix.field.MidPx.FIELD), 0.001);
        assertEquals(expected.getBidYield().doubleValue(), actual.getDouble(quickfix.field.BidYield.FIELD), 0.001);
        assertEquals(expected.getMidYield().doubleValue(), actual.getDouble(quickfix.field.MidYield.FIELD), 0.001);
        assertEquals(expected.getOfferYield().doubleValue(), actual.getDouble(quickfix.field.OfferYield.FIELD), 0.001);
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertDateEquals(expected.getSettlDate(), actual.getUtcDateOnly(quickfix.field.SettlDate.FIELD));
        assertEquals(expected.getOrdType().getValue(), actual.getChar(quickfix.field.OrdType.FIELD));
        assertDateEquals(expected.getSettlDate2(), actual.getUtcDateOnly(quickfix.field.SettlDate2.FIELD));
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getDouble(quickfix.field.OrderQty2.FIELD), 0.001);
        assertEquals(expected.getBidForwardPoints2().doubleValue(), actual.getDouble(quickfix.field.BidForwardPoints2.FIELD), 0.001);
        assertEquals(expected.getOfferForwardPoints2().doubleValue(), actual.getDouble(quickfix.field.OfferForwardPoints2.FIELD), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertEquals(expected.getSettlCurrBidFxRate().doubleValue(), actual.getDouble(quickfix.field.SettlCurrBidFxRate.FIELD), 0.001);
        assertEquals(expected.getSettlCurrOfferFxRate().doubleValue(), actual.getDouble(quickfix.field.SettlCurrOfferFxRate.FIELD), 0.001);
        assertEquals(expected.getSettlCurrFxRateCalc().getValue(), actual.getChar(quickfix.field.SettlCurrFxRateCalc.FIELD), 0.001);
        assertEquals(expected.getCommType().getValue(), actual.getChar(quickfix.field.CommType.FIELD));
        assertEquals(expected.getCommission().doubleValue(), actual.getDouble(quickfix.field.Commission.FIELD), 0.001);
        assertEquals(expected.getCustOrderCapacity().getValue(), actual.getInt(quickfix.field.CustOrderCapacity.FIELD));
        assertEquals(expected.getExDestination(), actual.getString(quickfix.field.ExDestination.FIELD));
        assertEquals(expected.getQuoteStatus().getValue(), actual.getInt(quickfix.field.QuoteStatus.FIELD));
    }

    public void check(QuoteStatusReportMsg expected, QuoteStatusReportMsg actual) throws Exception {
        assertEquals(expected.getQuoteStatusReqID(), actual.getQuoteStatusReqID());
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        // Parties check
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getBidPx().doubleValue(), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getMktBidPx().doubleValue(), actual.getMktBidPx().doubleValue(), 0.001);
        assertEquals(expected.getMktOfferPx().doubleValue(), actual.getMktOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getMinBidSize().doubleValue(), actual.getMinBidSize().doubleValue(), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getMinOfferSize().doubleValue(), actual.getMinOfferSize().doubleValue(), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getOfferSize().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getValidUntilTime(), actual.getValidUntilTime(), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getBidSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getOfferSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getOfferForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getMidPx().doubleValue(), actual.getMidPx().doubleValue(), 0.001);
        assertEquals(expected.getBidYield().doubleValue(), actual.getBidYield().doubleValue(), 0.001);
        assertEquals(expected.getMidYield().doubleValue(), actual.getMidYield().doubleValue(), 0.001);
        assertEquals(expected.getOfferYield().doubleValue(), actual.getOfferYield().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints2().doubleValue(), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints2().doubleValue(), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        assertEquals(expected.getSettlCurrBidFxRate().doubleValue(), actual.getSettlCurrBidFxRate().doubleValue(), 0.001);
        assertEquals(expected.getSettlCurrOfferFxRate().doubleValue(), actual.getSettlCurrOfferFxRate().doubleValue(), 0.001);
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getCommType().getValue(), actual.getCommType().getValue());
        assertEquals(expected.getCommission().doubleValue(), actual.getCommission().doubleValue(), 0.001);
        assertEquals(expected.getCustOrderCapacity().getValue(), actual.getCustOrderCapacity().getValue());
        assertEquals(expected.getExDestination(), actual.getExDestination());
        assertEquals(expected.getQuoteStatus().getValue(), actual.getQuoteStatus().getValue());
    }
}
