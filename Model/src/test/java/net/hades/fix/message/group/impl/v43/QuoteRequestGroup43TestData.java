/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestGroup43TestData.java
 *
 * $Id: QuoteRequestGroup43TestData.java,v 1.2 2011-10-29 09:42:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.SpreadOrBenchmarkCurveData43TestData;
import net.hades.fix.message.comp.impl.v43.Stipulations43TestData;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.QuoteRequestType;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for QuoteRequestGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteRequestGroup43TestData extends MsgTest {

    private static final QuoteRequestGroup43TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestGroup43TestData();
    }

    public static QuoteRequestGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix43.QuoteRequest.NoRelatedSym msg) throws Exception {
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);

        msg.setDouble(quickfix.field.PrevClosePx.FIELD, 1.456);
        msg.setInt(quickfix.field.QuoteRequestType.FIELD, 1);
        msg.setInt(quickfix.field.QuoteType.FIELD, 1);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637478466");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "A546464645");
        msg.setUtcDateOnly(quickfix.field.TradeOriginationDate.FIELD, new Date());
        // Stipulations
        quickfix.fix43.component.Stipulations stips = new quickfix.fix43.component.Stipulations();
        Stipulations43TestData.getInstance().populate(stips);
        msg.set(stips);

        msg.setChar(quickfix.field.Side.FIELD, '3');
        msg.setInt(quickfix.field.QuantityType.FIELD, 1);
        msg.setDouble(quickfix.field.OrderQty.FIELD, 3.0);
        msg.setDouble(quickfix.field.CashOrderQty.FIELD, 4.0);
        msg.setChar(quickfix.field.SettlType.FIELD, '4');
        msg.setString(quickfix.field.SettlDate.FIELD, "20090808");
        msg.setChar(quickfix.field.OrdType.FIELD, '1');
        msg.setString(quickfix.field.SettlDate2.FIELD, "20090505");
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 4.0);
        msg.setUtcTimeStamp(quickfix.field.ExpireTime.FIELD, new Date());
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.Currency.FIELD, "USD");
        // SpreadOrBenchmarkCurveData
        quickfix.fix43.component.SpreadOrBenchmarkCurveData spread = new quickfix.fix43.component.SpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData43TestData.getInstance().populate(spread);
        msg.set(spread);

        msg.setInt(quickfix.field.PriceType.FIELD, 1);
        msg.setDouble(quickfix.field.Price.FIELD, 22.444);
        msg.setDouble(quickfix.field.Price2.FIELD, 22.555);
        // YieldData
        quickfix.fix43.component.YieldData yield = new quickfix.fix43.component.YieldData();
        YieldData43TestData.getInstance().populate(yield);
        msg.set(yield);
    }

    public void populate2(quickfix.fix43.QuoteRequest.NoRelatedSym msg) throws Exception {
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);

        msg.setDouble(quickfix.field.PrevClosePx.FIELD, 2.456);
        msg.setInt(quickfix.field.QuoteRequestType.FIELD, 2);
        msg.setInt(quickfix.field.QuoteType.FIELD, 1);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637767888");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "A546464999");
        msg.setUtcDateOnly(quickfix.field.TradeOriginationDate.FIELD, new Date());
        // Stipulations
        quickfix.fix43.component.Stipulations stips = new quickfix.fix43.component.Stipulations();
        Stipulations43TestData.getInstance().populate(stips);
        msg.set(stips);
        
        msg.setChar(quickfix.field.Side.FIELD, '2');
        msg.setInt(quickfix.field.QuantityType.FIELD, 2);
        msg.setDouble(quickfix.field.OrderQty.FIELD, 4.0);
        msg.setDouble(quickfix.field.CashOrderQty.FIELD, 6.0);
        msg.setChar(quickfix.field.SettlType.FIELD, '3');
        msg.setString(quickfix.field.SettlDate.FIELD, "20090303");
        msg.setChar(quickfix.field.OrdType.FIELD, '1');
        msg.setString(quickfix.field.SettlDate2.FIELD, "20090101");
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 4.0);
        msg.setUtcTimeStamp(quickfix.field.ExpireTime.FIELD, new Date());
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.Currency.FIELD, "AUD");
        // SpreadOrBenchmarkCurveData
        quickfix.fix43.component.SpreadOrBenchmarkCurveData spread = new quickfix.fix43.component.SpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData43TestData.getInstance().populate(spread);
        msg.set(spread);

        msg.setInt(quickfix.field.PriceType.FIELD, 2);
        msg.setDouble(quickfix.field.Price.FIELD, 33.444);
        msg.setDouble(quickfix.field.Price2.FIELD, 33.555);
        // YieldData
        quickfix.fix43.component.YieldData yield = new quickfix.fix43.component.YieldData();
        YieldData43TestData.getInstance().populate(yield);
        msg.set(yield);
    }

    public void populate1(QuoteRequestGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setPrevClosePx(new Double(1.456));
        msg.setQuoteRequestType(QuoteRequestType.Automatic);
        msg.setQuoteType(QuoteType.Indicative);
        msg.setTradingSessionID("X637478466");
        msg.setTradingSessionSubID("A546464645");
        msg.setTradeOriginationDate(new Date());
        // Stipulations
        msg.setStipulations();
        Stipulations43TestData.getInstance().populate(msg.getStipulations());

        msg.setSide(Side.AsDefined);
        msg.setQuantityType(QuantityType.BONDS);
        msg.setOrderQty(new Double(12.0));
        msg.setCashOrderQty(new Double(7.0));
        msg.setSettlType("1");
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Limit);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(4.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.UnitedStatesDollar);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData43TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setPriceType(PriceType.FixedAmount);
        msg.setPrice(new Double(22.333));
        msg.setPrice2(new Double(22.444));
        // YieldData
        msg.setYieldData();
        YieldData43TestData.getInstance().populate(msg.getYieldData());
    }

    public void populate2(QuoteRequestGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setPrevClosePx(new Double(1.356));
        msg.setQuoteRequestType(QuoteRequestType.Manual);
        msg.setQuoteType(QuoteType.Counter);
        msg.setTradingSessionID("X637478666");
        msg.setTradingSessionSubID("VB546464645");
        msg.setTradeOriginationDate(new Date());
        // Stipulations
        msg.setStipulations();
        Stipulations43TestData.getInstance().populate(msg.getStipulations());
        
        msg.setSide(Side.Buy);
        msg.setQuantityType(QuantityType.CONTRACTS);
        msg.setOrderQty(new Double(13.0));
        msg.setCashOrderQty(new Double(8.0));
        msg.setSettlType("2");
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.ForexSwap);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(5.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.AustralianDollar);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData43TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setPriceType(PriceType.Percentage);
        msg.setPrice(new Double(11.333));
        msg.setPrice2(new Double(11.444));
        // YieldData
        msg.setYieldData();
        YieldData43TestData.getInstance().populate(msg.getYieldData());
    }

    public void check(QuoteRequestGroup expected, quickfix.fix43.QuoteRequest.NoRelatedSym actual) throws Exception {
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getDouble(quickfix.field.PrevClosePx.FIELD), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getInt(quickfix.field.QuoteRequestType.FIELD));
        assertEquals(expected.getQuoteType().getValue(), actual.getInt(quickfix.field.QuoteType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
        assertDateEquals(expected.getTradeOriginationDate(), actual.getUtcDateOnly(quickfix.field.TradeOriginationDate.FIELD));
        // Stipulations
        Stipulations43TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getQuantityType().getValue(), actual.getInt(quickfix.field.QuantityType.FIELD));
        assertEquals(expected.getOrderQty().doubleValue(), actual.getDouble(quickfix.field.OrderQty.FIELD), 0.001);
        assertEquals(expected.getCashOrderQty().doubleValue(), actual.getDouble(quickfix.field.CashOrderQty.FIELD), 0.001);
        assertEquals(expected.getSettlType(), String.valueOf(actual.getChar(quickfix.field.SettlType.FIELD)));
        assertEquals(formatQFStringDate(expected.getSettlDate()), actual.getString(quickfix.field.SettlDate.FIELD));
        assertEquals(expected.getOrdType().getValue(), actual.getChar(quickfix.field.OrdType.FIELD));
        assertEquals(formatQFStringDate(expected.getSettlDate2()), actual.getString(quickfix.field.SettlDate2.FIELD));
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getDouble(quickfix.field.OrderQty2.FIELD), 0.001);
        assertTimeEquals(expected.getExpireTime(), actual.getUtcTimeStamp(quickfix.field.ExpireTime.FIELD), false);
        assertTimeEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData43TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        assertEquals(expected.getPriceType().getValue(), actual.getInt(quickfix.field.PriceType.FIELD));
        assertEquals(expected.getPrice().doubleValue(), actual.getDouble(quickfix.field.Price.FIELD), 0.001);
        assertEquals(expected.getPrice2().doubleValue(), actual.getDouble(quickfix.field.Price2.FIELD), 0.001);
        // YieldData check
        YieldData43TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
    }

    public void check(quickfix.fix43.QuoteRequest.NoRelatedSym expected, QuoteRequestGroup actual) throws Exception {
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getDouble(quickfix.field.PrevClosePx.FIELD), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.QuoteRequestType.FIELD), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getInt(quickfix.field.QuoteType.FIELD), actual.getQuoteType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.TradeOriginationDate.FIELD), actual.getTradeOriginationDate());
        // Stipulations
        Stipulations43TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getInt(quickfix.field.QuantityType.FIELD), actual.getQuantityType().getValue());
        assertEquals(expected.getDouble(quickfix.field.OrderQty.FIELD), actual.getOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.CashOrderQty.FIELD), actual.getCashOrderQty().doubleValue(), 0.001);
        assertEquals(String.valueOf(expected.getChar(quickfix.field.SettlType.FIELD)), actual.getSettlType());
        assertEquals(expected.getString(quickfix.field.SettlDate.FIELD), formatQFStringDate(actual.getSettlDate()));
        assertEquals(expected.getChar(quickfix.field.OrdType.FIELD), actual.getOrdType().getValue());
        assertEquals(expected.getString(quickfix.field.SettlDate2.FIELD), formatQFStringDate(actual.getSettlDate2()));
        assertEquals(expected.getDouble(quickfix.field.OrderQty2.FIELD), actual.getOrderQty2().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ExpireTime.FIELD), actual.getExpireTime(), false);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData43TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        assertEquals(expected.getInt(quickfix.field.PriceType.FIELD), actual.getPriceType().getValue());
        assertEquals(expected.getDouble(quickfix.field.Price.FIELD), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.Price2.FIELD), actual.getPrice2().doubleValue(), 0.001);
        // YieldData check
        YieldData43TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
    }

    public void check(QuoteRequestGroup expected, QuoteRequestGroup actual) throws Exception {
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        // Stipulations
        Stipulations43TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getQuantityType().getValue(), actual.getQuantityType().getValue());
        assertEquals(expected.getOrderQty().doubleValue(), actual.getOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getCashOrderQty().doubleValue(), actual.getCashOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData43TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        assertEquals(expected.getPriceType().getValue(), actual.getPriceType().getValue());
        assertEquals(expected.getPrice().doubleValue(), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getPrice2().doubleValue(), actual.getPrice2().doubleValue(), 0.001);
        // YieldData check
        YieldData43TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
    }
}
