/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectGroup50SP2TestData.java
 *
 * $Id: QuoteRequestRejectGroup50SP2TestData.java,v 1.3 2011-10-29 09:42:23 vrotaru Exp $
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
import net.hades.fix.message.group.QuoteRequestRejectGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQualifier;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuotePriceType;
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
public class QuoteRequestRejectGroup50SP2TestData extends MsgTest {

    private static final QuoteRequestRejectGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestRejectGroup50SP2TestData();
    }

    public static QuoteRequestRejectGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(QuoteRequestRejectGroup msg) throws UnsupportedEncodingException {
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
        msg.setSide(Side.AsDefined);
        msg.setQtyType(QtyType.Contracts);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());
        // Stipulations
        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());

        msg.setSettlType("1");
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Limit);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(4.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setAccount("83747393444");
        msg.setAcctIDSource(AcctIDSource.BIC.getValue());
        msg.setAccountType(AccountType.CustomerSide);
        // QuoteRequestLegGroups
        msg.setNoLegs(new Integer(2));
        QuoteRequestLegGroup50SP2TestData.getInstance().populate1(msg.getQuoteRequestLegGroups()[0]);
        QuoteRequestLegGroup50SP2TestData.getInstance().populate2(msg.getQuoteRequestLegGroups()[1]);
        // QuoteQualifiers
        msg.setNoQuoteQualifiers(new Integer(2));
        msg.getQuoteQualifierGroups()[0].setQuoteQualifier(IOIQualifier.AllOrNone);
        msg.getQuoteQualifierGroups()[1].setQuoteQualifier(IOIQualifier.AtTheMarket);

        msg.setQuotePriceType(QuotePriceType.Discount);
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

    public void populate2(QuoteRequestRejectGroup msg) throws UnsupportedEncodingException {
        // Instrument
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
        msg.setSide(Side.Borrow);
        msg.setQtyType(QtyType.Units);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());
        // Stipulations
        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());
        
        msg.setSettlType("2");
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.ForexSwap);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(5.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.AustralianDollar);
        msg.setAccount("5434645654");
        msg.setAcctIDSource(AcctIDSource.DTCC.getValue());
        msg.setAccountType(AccountType.FloorTrader);
         // QuoteRequestLegGroups
        msg.setNoLegs(new Integer(2));
        QuoteRequestLegGroup50SP2TestData.getInstance().populate1(msg.getQuoteRequestLegGroups()[0]);
        QuoteRequestLegGroup50SP2TestData.getInstance().populate2(msg.getQuoteRequestLegGroups()[1]);
        // QuoteQualifiers
        msg.setNoQuoteQualifiers(new Integer(2));
        msg.getQuoteQualifierGroups()[0].setQuoteQualifier(IOIQualifier.AtTheClose);
        msg.getQuoteQualifierGroups()[1].setQuoteQualifier(IOIQualifier.Limit);

        msg.setQuotePriceType(QuotePriceType.PerShare);
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

    public void check(QuoteRequestRejectGroup expected, QuoteRequestRejectGroup actual) throws Exception {
        // Instrument check
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertNotNull(actual);
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0],
            actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1],
            actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getQtyType(), actual.getQtyType());
        // OrderQtyData check
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        // Stipulations check
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        // QuoteRequestLegGroup
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        QuoteRequestLegGroup50SP2TestData.getInstance().check(expected.getQuoteRequestLegGroups()[0],
            actual.getQuoteRequestLegGroups()[0]);
        QuoteRequestLegGroup50SP2TestData.getInstance().check(expected.getQuoteRequestLegGroups()[1],
            actual.getQuoteRequestLegGroups()[1]);
        // QuoteQualifiers
        assertNotNull(actual);
        assertEquals(expected.getNoQuoteQualifiers().intValue(), actual.getNoQuoteQualifiers().intValue());
        assertEquals(expected.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue(),
            actual.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue());
        assertEquals(expected.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue(),
            actual.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue());
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
