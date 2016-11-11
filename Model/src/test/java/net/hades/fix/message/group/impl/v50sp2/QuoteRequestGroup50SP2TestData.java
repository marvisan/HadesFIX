/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestGroup50SP2TestData.java
 *
 * $Id: QuoteRequestGroup50SP2TestData.java,v 1.3 2011-10-29 09:42:23 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQualifier;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuoteRequestType;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for QuoteRequestGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteRequestGroup50SP2TestData extends MsgTest {

    private static final QuoteRequestGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestGroup50SP2TestData();
    }

    public static QuoteRequestGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(QuoteRequestGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setPrevClosePx(new Double(1.456));
        msg.setQuoteRequestType(QuoteRequestType.Automatic);
        msg.setQuoteType(QuoteType.Indicative);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        msg.setTradeOriginationDate(new Date());
        // RateSources
        msg.setNoRateSources(new Integer(2));
        RateSourceGroup50SP2TestData.getInstance().populate1(msg.getRateSources()[0]);
        RateSourceGroup50SP2TestData.getInstance().populate2(msg.getRateSources()[1]);
        // Stipulations
        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());

        msg.setSide(Side.AsDefined);
        msg.setQtyType(QtyType.Contracts);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());

        msg.setMinQty(new Double(26.0));
        msg.setSettlType("1");
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Limit);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(4.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setSettlCurrency(Currency.AustralianDollar);
        msg.setAccount("267389494");
        msg.setAcctIDSource(new Integer(1));
        msg.setAccountType(AccountType.CustomerSide);
        // LegQuoteSymbolGroup
        msg.setNoLegs(new Integer(2));
        QuoteRequestLegGroup50SP2TestData.getInstance().populate1(msg.getLegQuoteSymbolGroups()[0]);
        QuoteRequestLegGroup50SP2TestData.getInstance().populate2(msg.getLegQuoteSymbolGroups()[1]);
        // QuoteQualifiers
        msg.setNoQuoteQualifiers(new Integer(2));
        msg.getQuoteQualifierGroups()[0].setQuoteQualifier(IOIQualifier.AllOrNone);
        msg.getQuoteQualifierGroups()[1].setQuoteQualifier(IOIQualifier.AtTheMarket);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setPriceType(PriceType.FixedAmount);
        msg.setPrice(new Double(22.333));
        msg.setPrice2(new Double(22.444));
        // YieldData
        msg.setYieldData();
        YieldData50TestData.getInstance().populate(msg.getYieldData());
        // Parties
        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());
    }

    public void populate2(QuoteRequestGroup msg) throws UnsupportedEncodingException {
        // Instrument50SP1TestData
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setPrevClosePx(new Double(1.356));
        msg.setQuoteRequestType(QuoteRequestType.Manual);
        msg.setQuoteType(QuoteType.Counter);
        msg.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.ContinuousTrading.getValue());
        msg.setTradeOriginationDate(new Date());
        // RateSources
        msg.setNoRateSources(new Integer(2));
        RateSourceGroup50SP2TestData.getInstance().populate1(msg.getRateSources()[0]);
        RateSourceGroup50SP2TestData.getInstance().populate2(msg.getRateSources()[1]);
        // Stipulations
        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());
        
        msg.setSide(Side.Buy);
        msg.setQtyType(QtyType.Units);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());

        msg.setMinQty(new Double(32.0));
        msg.setSettlType("2");
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.ForexSwap);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(5.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.AustralianDollar);
        msg.setSettlCurrency(Currency.Euro);
        msg.setAccount("13445667");
        msg.setAcctIDSource(new Integer(2));
        msg.setAccountType(AccountType.FloorTrader);
        // LegQuoteSymbolGroup
        msg.setNoLegs(new Integer(2));
        QuoteRequestLegGroup50SP2TestData.getInstance().populate1(msg.getLegQuoteSymbolGroups()[0]);
        QuoteRequestLegGroup50SP2TestData.getInstance().populate2(msg.getLegQuoteSymbolGroups()[1]);
        // QuoteQualifiers
        msg.setNoQuoteQualifiers(new Integer(2));
        msg.getQuoteQualifierGroups()[0].setQuoteQualifier(IOIQualifier.AtTheOpen);
        msg.getQuoteQualifierGroups()[1].setQuoteQualifier(IOIQualifier.InTouchWith);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setPriceType(PriceType.Percentage);
        msg.setPrice(new Double(11.333));
        msg.setPrice2(new Double(11.444));
        // YieldData
        msg.setYieldData();
        YieldData50TestData.getInstance().populate(msg.getYieldData());
        // Parties
        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());
    }

    public void check(QuoteRequestGroup expected, QuoteRequestGroup actual) throws Exception {
        // Instrument check
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        // RateSources
        RateSourceGroup50SP2TestData.getInstance().check(expected.getRateSources()[0], actual.getRateSources()[0]);
        RateSourceGroup50SP2TestData.getInstance().check(expected.getRateSources()[1], actual.getRateSources()[1]);
        // Stipulations
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getQtyType().getValue(), actual.getQtyType().getValue());
        // OrderQtyData
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getMinQty().doubleValue(), actual.getMinQty().doubleValue(), 0.001);
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        assertEquals(expected.getSettlCurrency().getValue(), actual.getSettlCurrency().getValue());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().intValue(), actual.getAcctIDSource().intValue());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        // LegQuoteSymbolGroup
        assertNotNull(actual);
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        QuoteRequestLegGroup50SP2TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[0], actual.getLegQuoteSymbolGroups()[0]);
        QuoteRequestLegGroup50SP2TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[1], actual.getLegQuoteSymbolGroups()[1]);
        // QuoteQualifiers
        assertNotNull(actual);
        assertEquals(expected.getNoQuoteQualifiers().intValue(), actual.getNoQuoteQualifiers().intValue());
        assertEquals(expected.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue(), actual.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue());
        assertEquals(expected.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue(), actual.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        assertEquals(expected.getPriceType().getValue(), actual.getPriceType().getValue());
        assertEquals(expected.getPrice().doubleValue(), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getPrice2().doubleValue(), actual.getPrice2().doubleValue(), 0.001);
        // YieldData check
        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
        // Parties check
        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());
    }
}
