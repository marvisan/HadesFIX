/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteMsg41TestData.java
 *
 * $Id: QuoteMsg41TestData.java,v 1.1 2009-07-06 03:19:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for QuoteMsg41 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteMsg41TestData extends MsgTest {

    private static final QuoteMsg41TestData INSTANCE;

    static {
        INSTANCE = new QuoteMsg41TestData();
    }

    public static QuoteMsg41TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix41.Quote msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX41HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setString(quickfix.field.Symbol.FIELD, "SUN");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "SUN");
        msg.setString(quickfix.field.SecurityID.FIELD, "SUN");
        msg.setString(quickfix.field.IDSource.FIELD, "SUN Microsystems");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "072009");
        msg.setInt(quickfix.field.MaturityDay.FIELD, new Integer(4));
        msg.setInt(quickfix.field.PutOrCall.FIELD, PutOrCall.Call.getValue());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 22.334);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'C');
        msg.setString(quickfix.field.SecurityExchange.FIELD, "NYSE");
        msg.setString(quickfix.field.Issuer.FIELD, "SUN & Co");
        msg.setString(quickfix.field.SecurityDesc.FIELD, "SUN options");
        msg.setDouble(quickfix.field.BidPx.FIELD, 234.55);
        msg.setDouble(quickfix.field.OfferPx.FIELD, 236.55);
        msg.setDouble(quickfix.field.BidSize.FIELD, 100);
        msg.setDouble(quickfix.field.OfferSize.FIELD, 120.0);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setDouble(quickfix.field.BidSpotRate.FIELD, 1.024);
        msg.setDouble(quickfix.field.OfferSpotRate.FIELD, 1.202);
        msg.setDouble(quickfix.field.BidForwardPoints.FIELD, 2.0);
        msg.setDouble(quickfix.field.OfferForwardPoints.FIELD, 3.0);
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setUtcDateOnly(quickfix.field.SettlDate.FIELD, new Date());
        msg.setChar(quickfix.field.OrdType.FIELD, quickfix.field.OrdType.FOREX_PREVIOUSLY_QUOTED);
        msg.setUtcDateOnly(quickfix.field.SettlDate2.FIELD, new Date());
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 2.876);
    }

    public void populate(QuoteMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setSymbol("SUN");
        msg.setSymbolSfx("MOT Shares");
        msg.setSecurityID("MOTS");
        msg.setSecurityIDSource("NASDAQ");
        msg.setSecurityType(SecurityType.AmendedRestated.getValue());
        msg.setMaturityMonthYear("092009");
        msg.setMaturityDay(new Integer(3));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(12.44));
        msg.setOptAttribute(new Character('A'));
        msg.setSecurityExchange("NYSE");
        msg.setIssuer("MOTOROLA");
        msg.setSecurityDesc("MOT shares desc");
        msg.setBidPx(new Double(23.44));
        msg.setOfferPx(new Double(23.98));
        msg.setBidSize(new Double(100.0));
        msg.setOfferSize(new Double(150));
        msg.setValidUntilTime(new Date());
        msg.setBidSpotRate(new Double(9.44));
        msg.setOfferSpotRate(new Double(12.54));
        msg.setBidForwardPoints(new Double(2.44));
        msg.setOfferForwardPoints(new Double(3.44));
        msg.setTransactTime(new Date());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.ForexMarket);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.22));
    }

    public void check(quickfix.fix41.Quote expected, QuoteMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getString(quickfix.field.Symbol.FIELD), actual.getSymbol());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.IDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType());
        assertEquals(expected.getString(quickfix.field.MaturityMonthYear.FIELD), actual.getMaturityMonthYear());
        assertEquals(expected.getInt(quickfix.field.MaturityDay.FIELD), actual.getMaturityDay().intValue());
        assertEquals(expected.getInt(quickfix.field.PutOrCall.FIELD), actual.getPutOrCall().getValue());
        assertEquals(expected.getDouble(quickfix.field.StrikePrice.FIELD), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.OptAttribute.FIELD), actual.getOptAttribute().charValue());
        assertEquals(expected.getString(quickfix.field.SecurityExchange.FIELD), actual.getSecurityExchange());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
        assertEquals(expected.getDouble(quickfix.field.BidPx.FIELD), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferPx.FIELD), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidSize.FIELD), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferSize.FIELD), actual.getOfferSize().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), actual.getValidUntilTime(), false);
        assertEquals(expected.getDouble(quickfix.field.BidSpotRate.FIELD), actual.getBidSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferSpotRate.FIELD), actual.getOfferSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidForwardPoints.FIELD), actual.getBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferForwardPoints.FIELD), actual.getOfferForwardPoints().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate.FIELD), actual.getSettlDate());
        assertEquals(expected.getChar(quickfix.field.OrdType.FIELD), actual.getOrdType().getValue());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate2.FIELD), actual.getSettlDate2());
        assertEquals(expected.getDouble(quickfix.field.OrderQty2.FIELD), actual.getOrderQty2().doubleValue(), 0.001);
    }

    public void check(QuoteMsg expected, quickfix.fix41.Quote actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.IDSource.FIELD));
        assertEquals(expected.getSecurityType(), actual.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertEquals(expected.getMaturityDay().intValue(), actual.getInt(quickfix.field.MaturityDay.FIELD));
        assertEquals(expected.getPutOrCall().getValue(), actual.getInt(quickfix.field.PutOrCall.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDouble(quickfix.field.StrikePrice.FIELD), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getChar(quickfix.field.OptAttribute.FIELD));
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getBidPx().doubleValue(), actual.getDouble(quickfix.field.BidPx.FIELD), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getDouble(quickfix.field.OfferPx.FIELD), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getDouble(quickfix.field.BidSize.FIELD), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getDouble(quickfix.field.OfferSize.FIELD), 0.001);
        assertTimestampEquals(expected.getValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getDouble(quickfix.field.BidSpotRate.FIELD), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getDouble(quickfix.field.OfferSpotRate.FIELD), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getDouble(quickfix.field.BidForwardPoints.FIELD), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getDouble(quickfix.field.OfferForwardPoints.FIELD), 0.001);
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertDateEquals(expected.getSettlDate(), actual.getUtcDateOnly(quickfix.field.SettlDate.FIELD));
        assertEquals(expected.getOrdType().getValue(), actual.getChar(quickfix.field.OrdType.FIELD));
        assertDateEquals(expected.getSettlDate2(), actual.getUtcDateOnly(quickfix.field.SettlDate2.FIELD));
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getDouble(quickfix.field.OrderQty2.FIELD), 0.001);
    }

    public void check(QuoteMsg expected, QuoteMsg actual) {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay().intValue(), actual.getMaturityDay().intValue());
        assertEquals(expected.getPutOrCall().getValue(), actual.getPutOrCall().getValue());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getOptAttribute().charValue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getBidPx().doubleValue(), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getOfferSize().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getValidUntilTime(), actual.getValidUntilTime(), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getBidSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getOfferSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getOfferForwardPoints().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
    }
}
