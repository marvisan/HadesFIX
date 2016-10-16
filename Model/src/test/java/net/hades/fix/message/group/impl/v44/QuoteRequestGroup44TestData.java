/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestGroup44TestData.java
 *
 * $Id: QuoteRequestGroup44TestData.java,v 1.2 2011-10-29 09:42:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.OrderQtyData44TestData;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.comp.impl.v44.SpreadOrBenchmarkCurveData44TestData;
import net.hades.fix.message.comp.impl.v44.Stipulations44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQualifier;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuotePriceType;
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
public class QuoteRequestGroup44TestData extends MsgTest {

    private static final QuoteRequestGroup44TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestGroup44TestData();
    }

    public static QuoteRequestGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix44.QuoteRequest.NoRelatedSym msg) throws Exception {
        // Instrument
        quickfix.fix44.component.Instrument instr = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr);
        msg.set(instr);
        // FinancingDetails
        quickfix.fix44.component.FinancingDetails findet = new quickfix.fix44.component.FinancingDetails();
        FinancingDetails44TestData.getInstance().populate(findet);
        msg.set(findet);
        // UnderlyingInstrument
        msg.setInt(quickfix.field.NoUnderlyings.FIELD, 2);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(ui1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu1.set(ui1);
        grpu1.set(ui1.getUnderlyingStipulations());
        msg.addGroup(grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(ui2);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu2.set(ui2);
        grpu2.set(ui2.getUnderlyingStipulations());
        msg.addGroup(grpu2);

        msg.setDouble(quickfix.field.PrevClosePx.FIELD, 1.456);
        msg.setInt(quickfix.field.QuoteRequestType.FIELD, 1);
        msg.setInt(quickfix.field.QuoteType.FIELD, 1);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637478466");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "A546464645");
        msg.setUtcTimeStamp(quickfix.field.TradeOriginationDate.FIELD, new Date());
        // Stipulations
        quickfix.fix44.component.Stipulations stips = new quickfix.fix44.component.Stipulations();
        Stipulations44TestData.getInstance().populate(stips);
        msg.set(stips);

        msg.setChar(quickfix.field.Side.FIELD, Side.Cross.getValue());
        msg.setInt(quickfix.field.QtyType.FIELD, 1);
        // OrderQtyData
        quickfix.fix44.component.OrderQtyData orderQtyData = new quickfix.fix44.component.OrderQtyData();
        OrderQtyData44TestData.getInstance().populate(orderQtyData);
        msg.set(orderQtyData);

        msg.setChar(quickfix.field.SettlType.FIELD, '4');
        msg.setString(quickfix.field.SettlDate.FIELD, "20090808");
        msg.setChar(quickfix.field.OrdType.FIELD, '1');
        msg.setString(quickfix.field.SettlDate2.FIELD, "20090505");
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 4.0);
        msg.setUtcTimeStamp(quickfix.field.ExpireTime.FIELD, new Date());
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.Currency.FIELD, "USD");
        msg.setString(quickfix.field.Account.FIELD, "1324243535");
        msg.setInt(quickfix.field.AcctIDSource.FIELD, quickfix.field.AcctIDSource.DTCC_CODE);
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        // LegQuoteSymbolGroup
        msg.setInt(quickfix.field.NoLegs.FIELD, 2);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs leg1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs();
        QuoteRequestLegGroup44TestData.getInstance().populate1(leg1);
        msg.addGroup(leg1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs leg2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs();
        QuoteRequestLegGroup44TestData.getInstance().populate2(leg2);
        msg.addGroup(leg2);
        // QuoteQualifiers
        msg.setInt(quickfix.field.NoQuoteQualifiers.FIELD, 2);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers qq1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers();
        qq1.setChar(quickfix.field.QuoteQualifier.FIELD, 'A');
        msg.addGroup(qq1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers qq2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers();
        qq2.setChar(quickfix.field.QuoteQualifier.FIELD, 'B');
        msg.addGroup(qq2);

        msg.setInt(quickfix.field.QuotePriceType.FIELD, quickfix.field.QuotePriceType.FIXED_AMOUNT);
        msg.setUtcTimeOnly(quickfix.field.ValidUntilTime.FIELD, new Date());
        // SpreadOrBenchmarkCurveData
        quickfix.fix44.component.SpreadOrBenchmarkCurveData spread = new quickfix.fix44.component.SpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(spread);
        msg.set(spread);

        msg.setInt(quickfix.field.PriceType.FIELD, 1);
        msg.setDouble(quickfix.field.Price.FIELD, 22.444);
        msg.setDouble(quickfix.field.Price2.FIELD, 22.555);
        // YieldData
        quickfix.fix44.component.YieldData yield = new quickfix.fix44.component.YieldData();
        YieldData44TestData.getInstance().populate(yield);
        msg.set(yield);
        // Parties
        quickfix.fix44.component.Parties parties = new quickfix.fix44.component.Parties();
        Parties44TestData.getInstance().populate(parties);
        msg.set(parties);
    }

    public void populate2(quickfix.fix44.QuoteRequest.NoRelatedSym msg) throws Exception {
        // Instrument
        quickfix.fix44.component.Instrument instr = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr);
        msg.set(instr);
        // FinancingDetails
        quickfix.fix44.component.FinancingDetails findet = new quickfix.fix44.component.FinancingDetails();
        FinancingDetails44TestData.getInstance().populate(findet);
        msg.set(findet);
        // UnderlyingInstrument
        msg.setInt(quickfix.field.NoUnderlyings.FIELD, 2);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(ui1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu1.set(ui1);
        grpu1.set(ui1.getUnderlyingStipulations());
        msg.addGroup(grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(ui2);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu2.set(ui2);
        grpu2.set(ui2.getUnderlyingStipulations());
        msg.addGroup(grpu2);

        msg.setDouble(quickfix.field.PrevClosePx.FIELD, 2.456);
        msg.setInt(quickfix.field.QuoteRequestType.FIELD, 2);
        msg.setInt(quickfix.field.QuoteType.FIELD, 1);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637767888");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "A546464999");
        msg.setUtcTimeStamp(quickfix.field.TradeOriginationDate.FIELD, new Date());
        // Stipulations
        quickfix.fix44.component.Stipulations stips = new quickfix.fix44.component.Stipulations();
        Stipulations44TestData.getInstance().populate(stips);
        msg.set(stips);
        
        msg.setChar(quickfix.field.Side.FIELD, Side.Borrow.getValue());
        msg.setInt(quickfix.field.QtyType.FIELD, 0);
        // OrderQtyData
        quickfix.fix44.component.OrderQtyData orderQtyData = new quickfix.fix44.component.OrderQtyData();
        OrderQtyData44TestData.getInstance().populate(orderQtyData);
        msg.set(orderQtyData);

        msg.setChar(quickfix.field.SettlType.FIELD, '3');
        msg.setString(quickfix.field.SettlDate.FIELD, "20090303");
        msg.setChar(quickfix.field.OrdType.FIELD, '1');
        msg.setString(quickfix.field.SettlDate2.FIELD, "20090101");
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 4.0);
        msg.setUtcTimeStamp(quickfix.field.ExpireTime.FIELD, new Date());
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.Currency.FIELD, "AUD");
        msg.setString(quickfix.field.Account.FIELD, "124567777");
        msg.setInt(quickfix.field.AcctIDSource.FIELD, quickfix.field.AcctIDSource.OMGEO);
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.ACCOUNT_IS_CARRIED_ON_NON_CUSTOMER_SIDE_OF_BOOKS_AND_IS_CROSS_MARGINED);
        // LegQuoteSymbolGroup
        msg.setInt(quickfix.field.NoLegs.FIELD, 2);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs leg1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs();
        QuoteRequestLegGroup44TestData.getInstance().populate1(leg1);
        msg.addGroup(leg1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs leg2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs();
        QuoteRequestLegGroup44TestData.getInstance().populate2(leg2);
        msg.addGroup(leg2);
        // QuoteQualifiers
        msg.setInt(quickfix.field.NoQuoteQualifiers.FIELD, 2);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers qq1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers();
        qq1.setChar(quickfix.field.QuoteQualifier.FIELD, 'C');
        msg.addGroup(qq1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers qq2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers();
        qq2.setChar(quickfix.field.QuoteQualifier.FIELD, 'D');
        msg.addGroup(qq2);

        msg.setInt(quickfix.field.QuotePriceType.FIELD, quickfix.field.QuotePriceType.TED_PRICE);
        msg.setUtcTimeOnly(quickfix.field.ValidUntilTime.FIELD, new Date());
        // SpreadOrBenchmarkCurveData
        quickfix.fix44.component.SpreadOrBenchmarkCurveData spread = new quickfix.fix44.component.SpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(spread);
        msg.set(spread);

        msg.setInt(quickfix.field.PriceType.FIELD, 2);
        msg.setDouble(quickfix.field.Price.FIELD, 33.444);
        msg.setDouble(quickfix.field.Price2.FIELD, 33.555);
        // YieldData
        quickfix.fix44.component.YieldData yield = new quickfix.fix44.component.YieldData();
        YieldData44TestData.getInstance().populate(yield);
        msg.set(yield);
        // Parties
        quickfix.fix44.component.Parties parties = new quickfix.fix44.component.Parties();
        Parties44TestData.getInstance().populate(parties);
        msg.set(parties);
    }

    public void populate1(QuoteRequestGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setPrevClosePx(new Double(1.456));
        msg.setQuoteRequestType(QuoteRequestType.Automatic);
        msg.setQuoteType(QuoteType.Indicative);
        msg.setTradingSessionID("X637478466");
        msg.setTradingSessionSubID("A546464645");
        msg.setTradeOriginationDate(new Date());
        // Stipulations
        msg.setStipulations();
        Stipulations44TestData.getInstance().populate(msg.getStipulations());

        msg.setSide(Side.AsDefined);
        msg.setQtyType(QtyType.Contracts);
        msg.setSettlType("1");
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Limit);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(4.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setAccount("187263553");
        msg.setAcctIDSource(new Integer(2));
        msg.setAccountType(AccountType.CustomerSide);
        msg.setQuotePriceType(QuotePriceType.FixedAmount);
        msg.setValidUntilTime(new Date());
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData44TestData.getInstance().populate(msg.getOrderQtyData());
        // LegQuoteSymbolGroup
        msg.setNoLegs(new Integer(2));
        QuoteRequestLegGroup44TestData.getInstance().populate1(msg.getLegQuoteSymbolGroups()[0]);
        QuoteRequestLegGroup44TestData.getInstance().populate2(msg.getLegQuoteSymbolGroups()[1]);
        // QuoteQualifiers
        msg.setNoQuoteQualifiers(new Integer(2));
        msg.getQuoteQualifierGroups()[0].setQuoteQualifier(IOIQualifier.AllOrNone);
        msg.getQuoteQualifierGroups()[1].setQuoteQualifier(IOIQualifier.AtTheMarket);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setPriceType(PriceType.FixedAmount);
        msg.setPrice(new Double(22.333));
        msg.setPrice2(new Double(22.444));
        // YieldData
        msg.setYieldData();
        YieldData44TestData.getInstance().populate(msg.getYieldData());
        // Parties
        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());
    }

    public void populate2(QuoteRequestGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setPrevClosePx(new Double(1.356));
        msg.setQuoteRequestType(QuoteRequestType.Manual);
        msg.setQuoteType(QuoteType.Counter);
        msg.setTradingSessionID("X637478666");
        msg.setTradingSessionSubID("VB546464645");
        msg.setTradeOriginationDate(new Date());
        // Stipulations
        msg.setStipulations();
        Stipulations44TestData.getInstance().populate(msg.getStipulations());
        
        msg.setSide(Side.Buy);
        msg.setQtyType(QtyType.Units);
        msg.setSettlType("2");
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.ForexSwap);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(5.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.AustralianDollar);
        msg.setAccount("124456666");
        msg.setAcctIDSource(new Integer(3));
        msg.setAccountType(AccountType.HouseTrader);
        msg.setQuotePriceType(QuotePriceType.Percentage);
        msg.setValidUntilTime(new Date());
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData44TestData.getInstance().populate(msg.getOrderQtyData());
        // LegQuoteSymbolGroup
        msg.setNoLegs(new Integer(2));
        QuoteRequestLegGroup44TestData.getInstance().populate1(msg.getLegQuoteSymbolGroups()[0]);
        QuoteRequestLegGroup44TestData.getInstance().populate2(msg.getLegQuoteSymbolGroups()[1]);
        // QuoteQualifiers
        msg.setNoQuoteQualifiers(new Integer(2));
        msg.getQuoteQualifierGroups()[0].setQuoteQualifier(IOIQualifier.AtTheOpen);
        msg.getQuoteQualifierGroups()[1].setQuoteQualifier(IOIQualifier.InTouchWith);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setPriceType(PriceType.Percentage);
        msg.setPrice(new Double(11.333));
        msg.setPrice2(new Double(11.444));
        // YieldData
        msg.setYieldData();
        YieldData44TestData.getInstance().populate(msg.getYieldData());
        // Parties
        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());
    }

    public void check(QuoteRequestGroup expected, quickfix.fix44.QuoteRequest.NoRelatedSym actual) throws Exception {
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getInt(quickfix.field.NoUnderlyings.FIELD));
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        actual.getGroup(1, grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu1.get(ui1);
        ui1.set(grpu1.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], ui1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        actual.getGroup(2, grpu2);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu2.get(ui2);
        ui2.set(grpu2.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], ui2);

        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getDouble(quickfix.field.PrevClosePx.FIELD), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getInt(quickfix.field.QuoteRequestType.FIELD));
        assertEquals(expected.getQuoteType().getValue(), actual.getInt(quickfix.field.QuoteType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
        assertDateEquals(expected.getTradeOriginationDate(), actual.getUtcDateOnly(quickfix.field.TradeOriginationDate.FIELD));
        // Stipulations
        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getQtyType().getValue(), actual.getInt(quickfix.field.QtyType.FIELD));
        assertEquals(expected.getSettlType(), String.valueOf(actual.getChar(quickfix.field.SettlType.FIELD)));
        assertEquals(formatQFStringDate(expected.getSettlDate()), actual.getString(quickfix.field.SettlDate.FIELD));
        assertEquals(expected.getOrdType().getValue(), actual.getChar(quickfix.field.OrdType.FIELD));
        assertEquals(formatQFStringDate(expected.getSettlDate2()), actual.getString(quickfix.field.SettlDate2.FIELD));
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getDouble(quickfix.field.OrderQty2.FIELD), 0.001);
        assertTimeEquals(expected.getExpireTime(), actual.getUtcTimeStamp(quickfix.field.ExpireTime.FIELD), false);
        assertTimeEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        // LegQuoteSymbolGroup
        assertEquals(expected.getNoLegs().intValue(), actual.getInt(quickfix.field.NoLegs.FIELD));
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs leg1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs();
        actual.getGroup(1, leg1);
        QuoteRequestLegGroup44TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[0], leg1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs leg2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs();
        actual.getGroup(2, leg2);
        QuoteRequestLegGroup44TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[1], leg2);
        // QuoteQualifiers
        assertEquals(expected.getNoQuoteQualifiers().intValue(), actual.getInt(quickfix.field.NoQuoteQualifiers.FIELD));
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers qq1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers();
        actual.getGroup(1, qq1);
        quickfix.field.QuoteQualifier qq1f = new quickfix.field.QuoteQualifier();
        qq1.get(qq1f);
        assertEquals(expected.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue(), qq1f.getValue());
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers qq2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers();
        actual.getGroup(2, qq2);
        quickfix.field.QuoteQualifier qq2f = new quickfix.field.QuoteQualifier();
        qq2.get(qq2f);
        assertEquals(expected.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue(), qq2f.getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        assertEquals(expected.getPriceType().getValue(), actual.getInt(quickfix.field.PriceType.FIELD));
        assertEquals(expected.getPrice().doubleValue(), actual.getDouble(quickfix.field.Price.FIELD), 0.001);
        assertEquals(expected.getPrice2().doubleValue(), actual.getDouble(quickfix.field.Price2.FIELD), 0.001);
        // YieldData check
        YieldData44TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
        // Parties check
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());
    }

    public void check(quickfix.fix44.QuoteRequest.NoRelatedSym expected, QuoteRequestGroup actual) throws Exception {
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getInt(quickfix.field.NoUnderlyings.FIELD), actual.getNoUnderlyings().intValue());
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        expected.getGroup(1, grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu1.get(ui1);
        ui1.set(grpu1.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(ui1, actual.getUnderlyingInstruments()[0]);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        expected.getGroup(2, grpu2);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu2.get(ui2);
        ui2.set(grpu2.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(ui2, actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getDouble(quickfix.field.PrevClosePx.FIELD), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.QuoteRequestType.FIELD), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getInt(quickfix.field.QuoteType.FIELD), actual.getQuoteType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TradeOriginationDate.FIELD), actual.getTradeOriginationDate(), false);
        // Stipulations
        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getInt(quickfix.field.QtyType.FIELD), actual.getQtyType().getValue());
        assertEquals(String.valueOf(expected.getChar(quickfix.field.SettlType.FIELD)), actual.getSettlType());
        assertEquals(expected.getString(quickfix.field.SettlDate.FIELD), formatQFStringDate(actual.getSettlDate()));
        assertEquals(expected.getChar(quickfix.field.OrdType.FIELD), actual.getOrdType().getValue());
        assertEquals(expected.getString(quickfix.field.SettlDate2.FIELD), formatQFStringDate(actual.getSettlDate2()));
        assertEquals(expected.getDouble(quickfix.field.OrderQty2.FIELD), actual.getOrderQty2().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ExpireTime.FIELD), actual.getExpireTime(), false);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        // LegQuoteSymbolGroup
        assertEquals(expected.getInt(quickfix.field.NoLegs.FIELD), actual.getNoLegs().intValue());
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs leg1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs();
        expected.getGroup(1, leg1);
        QuoteRequestLegGroup44TestData.getInstance().check(leg1, actual.getLegQuoteSymbolGroups()[0]);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs leg2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs();
        expected.getGroup(2, leg2);
        QuoteRequestLegGroup44TestData.getInstance().check(leg2, actual.getLegQuoteSymbolGroups()[1]);
        // QuoteQualifiers
        assertEquals(expected.getInt(quickfix.field.NoQuoteQualifiers.FIELD), actual.getNoQuoteQualifiers().intValue());
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers qq1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers();
        expected.getGroup(1, qq1);
        quickfix.field.QuoteQualifier qq1f = new quickfix.field.QuoteQualifier();
        qq1.get(qq1f);
        assertEquals(qq1f.getValue(), actual.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue());
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers qq2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoQuoteQualifiers();
        expected.getGroup(2, qq2);
        quickfix.field.QuoteQualifier qq2f = new quickfix.field.QuoteQualifier();
        qq2.get(qq2f);
        assertEquals(qq2f.getValue(), actual.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        assertEquals(expected.getInt(quickfix.field.PriceType.FIELD), actual.getPriceType().getValue());
        assertEquals(expected.getDouble(quickfix.field.Price.FIELD), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.Price2.FIELD), actual.getPrice2().doubleValue(), 0.001);
        // YieldData check
        YieldData44TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
        // Parties check
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());
    }

    public void check(QuoteRequestGroup expected, QuoteRequestGroup actual) throws Exception {
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        // Stipulations
        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getQtyType().getValue(), actual.getQtyType().getValue());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
//        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
//        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        // LegQuoteSymbolGroup
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        QuoteRequestLegGroup44TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[0], actual.getLegQuoteSymbolGroups()[0]);
        QuoteRequestLegGroup44TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[1], actual.getLegQuoteSymbolGroups()[1]);
        // QuoteQualifiers
        assertEquals(expected.getNoQuoteQualifiers().intValue(), actual.getNoQuoteQualifiers().intValue());
        assertEquals(expected.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue(), actual.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue());
        assertEquals(expected.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue(), actual.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        assertEquals(expected.getPriceType().getValue(), actual.getPriceType().getValue());
        assertEquals(expected.getPrice().doubleValue(), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getPrice2().doubleValue(), actual.getPrice2().doubleValue(), 0.001);
        // YieldData check
        YieldData44TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
        // Parties check
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());
    }
}
