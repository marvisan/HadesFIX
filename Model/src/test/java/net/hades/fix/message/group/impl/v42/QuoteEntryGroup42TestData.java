/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteEntryGroup42TestData.java
 *
 * $Id: QuoteEntryGroup42TestData.java,v 1.3 2011-10-29 09:42:26 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.QuoteEntryGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityIDSource;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for QuoteEntryGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteEntryGroup42TestData extends MsgTest {

    private static final QuoteEntryGroup42TestData INSTANCE;

    static {
        INSTANCE = new QuoteEntryGroup42TestData();
    }

    public static QuoteEntryGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries msg) throws Exception {
        msg.setString(quickfix.field.QuoteEntryID.FIELD, "ZZ23433");
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOTOROLA Shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "MOTO");
        msg.setString(quickfix.field.SecurityIDSource.FIELD, "NYSE");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "200806w2");
        msg.setInt(quickfix.field.MaturityDay.FIELD, 14);
        msg.setInt(quickfix.field.PutOrCall.FIELD, quickfix.field.PutOrCall.PUT);
        msg.setDecimal(quickfix.field.StrikePrice.FIELD, new BigDecimal("25.48"));
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'T');
        msg.setDecimal(quickfix.field.ContractMultiplier.FIELD, new BigDecimal("1.67"));
        msg.setDecimal(quickfix.field.CouponRate.FIELD, new BigDecimal("10.55"));
        msg.setString(quickfix.field.SecurityExchange.FIELD, "CBT");
        msg.setString(quickfix.field.Issuer.FIELD, "HADES");
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "One Motorola Share");
        byte[] secDescDataExp = new byte[] {(byte) 20, (byte) 35, (byte) 46, (byte) 98,
            (byte) 179, (byte) 202, (byte) 226, (byte) 250};
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(secDescDataExp, DEFAULT_CHARACTER_SET));
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
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.AfterHours.getValue());
        msg.setUtcDateOnly(quickfix.field.SettlDate.FIELD, new Date());
        msg.setChar(quickfix.field.OrdType.FIELD, quickfix.field.OrdType.FOREX_PREVIOUSLY_QUOTED);
        msg.setUtcDateOnly(quickfix.field.SettlDate2.FIELD, new Date());
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 2.876);
        msg.setString(quickfix.field.Currency.FIELD, Currency.UnitedStatesDollar.getValue());
    }

    public void populate2(quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries msg) throws Exception {
        msg.setString(quickfix.field.QuoteEntryID.FIELD, "AA353553");
        msg.setString(quickfix.field.Symbol.FIELD, "IBM");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "IBM Shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "IBMCO");
        msg.setString(quickfix.field.SecurityIDSource.FIELD, "CBE");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.CASH);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "200807w2");
        msg.setInt(quickfix.field.MaturityDay.FIELD, 12);
        msg.setInt(quickfix.field.PutOrCall.FIELD, quickfix.field.PutOrCall.CALL);
        msg.setDecimal(quickfix.field.StrikePrice.FIELD, new BigDecimal("25.42"));
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'X');
        msg.setDecimal(quickfix.field.ContractMultiplier.FIELD, new BigDecimal("1.69"));
        msg.setDecimal(quickfix.field.CouponRate.FIELD, new BigDecimal("10.59"));
        msg.setString(quickfix.field.SecurityExchange.FIELD, "CBOT");
        msg.setString(quickfix.field.Issuer.FIELD, "MARV");
        byte[] issuerDataExp = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "One IBM Share");
        byte[] secDescDataExp = new byte[] {(byte) 20, (byte) 31, (byte) 46, (byte) 98,
            (byte) 179, (byte) 203, (byte) 226, (byte) 250};
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(secDescDataExp, DEFAULT_CHARACTER_SET));
        msg.setDouble(quickfix.field.BidPx.FIELD, 234.77);
        msg.setDouble(quickfix.field.OfferPx.FIELD, 236.77);
        msg.setDouble(quickfix.field.BidSize.FIELD, 44);
        msg.setDouble(quickfix.field.OfferSize.FIELD, 120.77);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setDouble(quickfix.field.BidSpotRate.FIELD, 1.777);
        msg.setDouble(quickfix.field.OfferSpotRate.FIELD, 1.777);
        msg.setDouble(quickfix.field.BidForwardPoints.FIELD, 2.7);
        msg.setDouble(quickfix.field.OfferForwardPoints.FIELD, 3.7);
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.Afternoon.getValue());
        msg.setUtcDateOnly(quickfix.field.SettlDate.FIELD, new Date());
        msg.setChar(quickfix.field.OrdType.FIELD, quickfix.field.OrdType.FOREX_LIMIT);
        msg.setUtcDateOnly(quickfix.field.SettlDate2.FIELD, new Date());
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 2.777);
        msg.setString(quickfix.field.Currency.FIELD, Currency.AustralianDollar.getValue());
    }

    public void populate1(QuoteEntryGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteEntryID("FFF46466");
        msg.setSymbol("MOT");
        msg.setSymbolSfx("MOTOROLA Shares");
        msg.setSecurityID("MOTO");
        msg.setSecurityIDSource(SecurityIDSource.QUIK.getValue());
        msg.setSecurityType(SecurityType.MutualFund.getValue());
        msg.setMaturityMonthYear("200806w2");
        msg.setMaturityDay(new Integer(14));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(25.48));
        msg.setOptAttribute(new Character('T'));
        msg.setContractMultiplier(new Double("1.67"));
        msg.setCouponRate(new Double("10.55"));
        msg.setSecurityExchange("CO");
        msg.setIssuer("HADES");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(issuerDataExp);
        msg.setSecurityDesc("One Motorola Share");
        byte[] secDescDataExp = new byte[] {(byte) 20, (byte) 35, (byte) 46, (byte) 98,
            (byte) 179, (byte) 202, (byte) 226, (byte) 250};
        msg.setEncodedSecurityDescLen(new Integer(8));
        msg.setEncodedSecurityDesc(secDescDataExp);
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
        msg.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Stop);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.22));
        msg.setCurrency(Currency.UnitedStatesDollar);
    }

    public void populate2(QuoteEntryGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteEntryID("GGG878w7847");
        msg.setSymbol("IBM");
        msg.setSymbolSfx("IBM Shares");
        msg.setSecurityID("IBMCO");
        msg.setSecurityIDSource(SecurityIDSource.ClearingHouse.getValue());
        msg.setSecurityType(SecurityType.BankersAcceptance.getValue());
        msg.setMaturityMonthYear("200807w2");
        msg.setMaturityDay(new Integer(15));
        msg.setPutOrCall(PutOrCall.Put);
        msg.setStrikePrice(new Double(25.48));
        msg.setOptAttribute(new Character('T'));
        msg.setContractMultiplier(new Double("1.69"));
        msg.setCouponRate(new Double("10.59"));
        msg.setSecurityExchange("CBOT");
        msg.setIssuer("MARV");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 34, (byte) 44, (byte) 96,
            (byte) 177, (byte) 195, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(issuerDataExp);
        msg.setSecurityDesc("One IBM Share");
        byte[] secDescDataExp = new byte[] {(byte) 20, (byte) 37, (byte) 46, (byte) 98,
            (byte) 179, (byte) 209, (byte) 226, (byte) 250};
        msg.setEncodedSecurityDescLen(new Integer(8));
        msg.setEncodedSecurityDesc(secDescDataExp);
        msg.setBidPx(new Double(23.55));
        msg.setOfferPx(new Double(23.55));
        msg.setBidSize(new Double(100.5));
        msg.setOfferSize(new Double(444));
        msg.setValidUntilTime(new Date());
        msg.setBidSpotRate(new Double(9.55));
        msg.setOfferSpotRate(new Double(12.55));
        msg.setBidForwardPoints(new Double(2.55));
        msg.setOfferForwardPoints(new Double(3.55));
        msg.setTransactTime(new Date());
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Market);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.55));
        msg.setCurrency(Currency.AustralianDollar);
    }

    public void check(QuoteEntryGroup expected, quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries actual) throws Exception {
        assertEquals(expected.getQuoteEntryID(), actual.getString(quickfix.field.QuoteEntryID.FIELD));
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(expected.getSecurityType(), actual.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertEquals(expected.getMaturityDay().intValue(), actual.getInt(quickfix.field.MaturityDay.FIELD));
        assertEquals(expected.getPutOrCall().getValue(), actual.getInt(quickfix.field.PutOrCall.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getChar(quickfix.field.OptAttribute.FIELD));
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getDecimal(quickfix.field.ContractMultiplier.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getDecimal(quickfix.field.CouponRate.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedIssuer(), actual.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET));
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
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertDateEquals(expected.getSettlDate(), actual.getUtcDateOnly(quickfix.field.SettlDate.FIELD));
        assertEquals(expected.getOrdType().getValue(), actual.getChar(quickfix.field.OrdType.FIELD));
        assertDateEquals(expected.getSettlDate2(), actual.getUtcDateOnly(quickfix.field.SettlDate2.FIELD));
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getDouble(quickfix.field.OrderQty2.FIELD), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
    }

    public void check(quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries expected, QuoteEntryGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteEntryID.FIELD), actual.getQuoteEntryID());
        assertEquals(expected.getString(quickfix.field.Symbol.FIELD), actual.getSymbol());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.SecurityIDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType());
        assertEquals(expected.getString(quickfix.field.MaturityMonthYear.FIELD), actual.getMaturityMonthYear());
        assertEquals(expected.getInt(quickfix.field.MaturityDay.FIELD), actual.getMaturityDay().intValue());
        assertEquals(expected.getInt(quickfix.field.PutOrCall.FIELD), actual.getPutOrCall().getValue());
        assertEquals(expected.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.OptAttribute.FIELD), actual.getOptAttribute().charValue());
        assertEquals(expected.getDouble(quickfix.field.ContractMultiplier.FIELD), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.CouponRate.FIELD), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.SecurityExchange.FIELD), actual.getSecurityExchange());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getInt(quickfix.field.EncodedIssuerLen.FIELD), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET),
            actual.getEncodedIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
        assertEquals(expected.getInt(quickfix.field.EncodedSecurityDescLen.FIELD), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET),
            actual.getEncodedSecurityDesc());
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
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate.FIELD), actual.getSettlDate());
        assertEquals(expected.getChar(quickfix.field.OrdType.FIELD), actual.getOrdType().getValue());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate2.FIELD), actual.getSettlDate2());
        assertEquals(expected.getDouble(quickfix.field.OrderQty2.FIELD), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
    }

    public void check(QuoteEntryGroup expected, QuoteEntryGroup actual) throws Exception {
        assertEquals(expected.getQuoteEntryID(), actual.getQuoteEntryID());
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
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
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
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
    }
}
