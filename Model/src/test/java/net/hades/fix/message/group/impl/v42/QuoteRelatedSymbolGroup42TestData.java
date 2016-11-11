/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRelatedSymbolGroup42TestData.java
 *
 * $Id: QuoteRelatedSymbolGroup42TestData.java,v 1.3 2011-10-29 09:42:27 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.QuoteRequestType;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for QuoteRequestGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteRelatedSymbolGroup42TestData extends MsgTest {

    private static final QuoteRelatedSymbolGroup42TestData INSTANCE;

    static {
        INSTANCE = new QuoteRelatedSymbolGroup42TestData();
    }

    public static QuoteRelatedSymbolGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix42.QuoteRequest.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOT shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "Sec ID");
        msg.setString(quickfix.field.IDSource.FIELD, "ID src");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "032009");
        msg.setString(quickfix.field.MaturityDay.FIELD, "30");
        msg.setInt(quickfix.field.PutOrCall.FIELD, PutOrCall.Call.getValue());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 23.24);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'B');
        msg.setDouble(quickfix.field.ContractMultiplier.FIELD, 1.034);
        msg.setDouble(quickfix.field.CouponRate.FIELD, 1.221);
        msg.setString(quickfix.field.SecurityExchange.FIELD, "CBOE");
        msg.setString(quickfix.field.Issuer.FIELD, "NYSE");
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 8);
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "Some MOT shares");
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
        msg.setDouble(quickfix.field.PrevClosePx.FIELD, 1.456);
        msg.setInt(quickfix.field.QuoteRequestType.FIELD, 1);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637478466");
        msg.setChar(quickfix.field.Side.FIELD, '3');
        msg.setDouble(quickfix.field.OrderQty.FIELD, 3.0);
        msg.setString(quickfix.field.SettlDate.FIELD, "20090808");
        msg.setChar(quickfix.field.OrdType.FIELD, '1');
        msg.setString(quickfix.field.SettlDate2.FIELD, "20090505");
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 4.0);
        msg.setUtcTimeStamp(quickfix.field.ExpireTime.FIELD, new Date());
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.Currency.FIELD, "USD");
    }

    public void populate2(quickfix.fix42.QuoteRequest.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.Symbol.FIELD, "JAVA");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "JAVA shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "JAVA ID");
        msg.setString(quickfix.field.IDSource.FIELD, "JAVA ID src");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTIONS_ON_FUTURES);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "042009");
        msg.setString(quickfix.field.MaturityDay.FIELD, "6");
        msg.setInt(quickfix.field.PutOrCall.FIELD, PutOrCall.Put.getValue());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 20.24);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'C');
        msg.setDouble(quickfix.field.ContractMultiplier.FIELD, 2.034);
        msg.setDouble(quickfix.field.CouponRate.FIELD, 2.221);
        msg.setString(quickfix.field.SecurityExchange.FIELD, "CBOEX");
        msg.setString(quickfix.field.Issuer.FIELD, "NYSEX");
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 8);
        byte[] issuerDataExp = new byte[] {(byte) 19, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 250};
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "Some MOT shares");
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        byte[] encSecExp = new byte[] {(byte) 17, (byte) 34, (byte) 47, (byte) 97,
            (byte) 170, (byte) 200, (byte) 223, (byte) 254};
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
        msg.setDouble(quickfix.field.PrevClosePx.FIELD, 2.456);
        msg.setInt(quickfix.field.QuoteRequestType.FIELD, 2);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637767888");
        msg.setChar(quickfix.field.Side.FIELD, '2');
        msg.setDouble(quickfix.field.OrderQty.FIELD, 4.0);
        msg.setString(quickfix.field.SettlDate.FIELD, "20090303");
        msg.setChar(quickfix.field.OrdType.FIELD, '1');
        msg.setString(quickfix.field.SettlDate2.FIELD, "20090101");
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 4.0);
        msg.setUtcTimeStamp(quickfix.field.ExpireTime.FIELD, new Date());
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.Currency.FIELD, "AUD");
    }

    public void populate1(QuoteRequestGroup msg) throws UnsupportedEncodingException {
        msg.setSymbol("MOT");
        msg.setSymbolSfx("MOT shares");
        msg.setSecurityID("Sec ID");
        msg.setSecurityIDSource("ID src");
        msg.setSecurityType(SecurityType.Option.getValue());
        msg.setMaturityMonthYear("032009");
        msg.setMaturityDay(new Integer(12));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(23.24));
        msg.setOptAttribute(new Character('B'));
        msg.setContractMultiplier(new Double(1.034));
        msg.setCouponRate(new Double(1.221));
        msg.setSecurityExchange("CBOE");
        msg.setIssuer("NYSE");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(issuerDataExp);
        msg.setSecurityDesc("Some MOT shares");
        msg.setEncodedSecurityDescLen(new Integer(8));
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setEncodedSecurityDesc(encSecExp);
        msg.setPrevClosePx(new Double(1.456));
        msg.setQuoteRequestType(QuoteRequestType.Automatic);
        msg.setTradingSessionID("X637478466");
        msg.setSide(Side.AsDefined);
        msg.setOrderQty(new Double(12.0));
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Limit);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(4.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.UnitedStatesDollar);
    }

    public void populate2(QuoteRequestGroup msg) throws UnsupportedEncodingException {
        msg.setSymbol("JAVA");
        msg.setSymbolSfx("JAVA shares");
        msg.setSecurityID("JAVA Sec ID");
        msg.setSecurityIDSource("JAVA ID src");
        msg.setSecurityType(SecurityType.OptionsOnComboEquity.getValue());
        msg.setMaturityMonthYear("092009");
        msg.setMaturityDay(new Integer(3));
        msg.setPutOrCall(PutOrCall.Put);
        msg.setStrikePrice(new Double(21.24));
        msg.setOptAttribute(new Character('A'));
        msg.setContractMultiplier(new Double(1.234));
        msg.setCouponRate(new Double(1.321));
        msg.setSecurityExchange("CBOEX");
        msg.setIssuer("NYSEX");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 36, (byte) 44, (byte) 96,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(issuerDataExp);
        msg.setSecurityDesc("Some JAVA shares");
        msg.setEncodedSecurityDescLen(new Integer(8));
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 32, (byte) 45, (byte) 97,
            (byte) 178, (byte) 204, (byte) 225, (byte) 254};
        msg.setEncodedSecurityDesc(encSecExp);
        msg.setPrevClosePx(new Double(1.356));
        msg.setQuoteRequestType(QuoteRequestType.Manual);
        msg.setTradingSessionID("X637478666");
        msg.setSide(Side.Buy);
        msg.setOrderQty(new Double(13.0));
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.ForexSwap);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(5.0));
        msg.setExpireTime(new Date());
        msg.setTransactTime(new Date());
        msg.setCurrency(Currency.AustralianDollar);
    }

    public void check(QuoteRequestGroup expected, quickfix.fix42.QuoteRequest.NoRelatedSym actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.IDSource.FIELD));
        assertEquals(expected.getSecurityType(), actual.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertEquals(expected.getMaturityDay().toString(), actual.getString(quickfix.field.MaturityDay.FIELD));
        assertEquals(expected.getPutOrCall().getValue(), actual.getInt(quickfix.field.PutOrCall.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDouble(quickfix.field.StrikePrice.FIELD), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getChar(quickfix.field.OptAttribute.FIELD));
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getDouble(quickfix.field.ContractMultiplier.FIELD), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getDouble(quickfix.field.CouponRate.FIELD), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedIssuer(), actual.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getDouble(quickfix.field.PrevClosePx.FIELD), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getInt(quickfix.field.QuoteRequestType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getOrderQty().doubleValue(), actual.getDouble(quickfix.field.OrderQty.FIELD), 0.001);
        assertEquals(formatQFStringDate(expected.getSettlDate()), actual.getString(quickfix.field.SettlDate.FIELD));
        assertEquals(expected.getOrdType().getValue(), actual.getChar(quickfix.field.OrdType.FIELD));
        assertEquals(formatQFStringDate(expected.getSettlDate2()), actual.getString(quickfix.field.SettlDate2.FIELD));
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getDouble(quickfix.field.OrderQty2.FIELD), 0.001);
        assertTimestampEquals(expected.getExpireTime(), actual.getUtcTimeStamp(quickfix.field.ExpireTime.FIELD), false);
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
    }

    public void check(quickfix.fix42.QuoteRequest.NoRelatedSym expected, QuoteRequestGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.Symbol.FIELD), actual.getSymbol());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.IDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType());
        assertEquals(expected.getString(quickfix.field.MaturityMonthYear.FIELD), actual.getMaturityMonthYear());
        assertEquals(expected.getString(quickfix.field.MaturityDay.FIELD), actual.getMaturityDay().toString());
        assertEquals(expected.getInt(quickfix.field.PutOrCall.FIELD), actual.getPutOrCall().getValue());
        assertEquals(expected.getDouble(quickfix.field.StrikePrice.FIELD), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.OptAttribute.FIELD), actual.getOptAttribute().charValue());
        assertEquals(expected.getDouble(quickfix.field.ContractMultiplier.FIELD), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.CouponRate.FIELD), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.SecurityExchange.FIELD), actual.getSecurityExchange());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getInt(quickfix.field.EncodedIssuerLen.FIELD), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
        assertEquals(expected.getInt(quickfix.field.EncodedSecurityDescLen.FIELD), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedSecurityDesc());
        assertEquals(expected.getDouble(quickfix.field.PrevClosePx.FIELD), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.QuoteRequestType.FIELD), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getDouble(quickfix.field.OrderQty.FIELD), actual.getOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.SettlDate.FIELD), formatQFStringDate(actual.getSettlDate()));
        assertEquals(expected.getChar(quickfix.field.OrdType.FIELD), actual.getOrdType().getValue());
        assertEquals(expected.getString(quickfix.field.SettlDate2.FIELD), formatQFStringDate(actual.getSettlDate2()));
        assertEquals(expected.getDouble(quickfix.field.OrderQty2.FIELD), actual.getOrderQty2().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ExpireTime.FIELD), actual.getExpireTime(), false);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
    }

    public void check(QuoteRequestGroup expected, QuoteRequestGroup actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay().toString(), actual.getMaturityDay().toString());
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
        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getOrderQty().doubleValue(), actual.getOrderQty().doubleValue(), 0.001);
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
    }
}
