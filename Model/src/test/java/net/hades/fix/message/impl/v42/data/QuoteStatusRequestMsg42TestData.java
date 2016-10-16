/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusRequestMsg42TestData.java
 *
 * $Id: QuoteStatusRequestMsg42TestData.java,v 1.1 2009-07-06 03:19:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteStatusRequestMsg;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for QuoteStatusRequestMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteStatusRequestMsg42TestData extends MsgTest {

    private static final QuoteStatusRequestMsg42TestData INSTANCE;

    static {
        INSTANCE = new QuoteStatusRequestMsg42TestData();
    }

    public static QuoteStatusRequestMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix42.QuoteStatusRequest msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX42HeaderAll(msg);
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
        msg.setDouble(quickfix.field.ContractMultiplier.FIELD, 1.034);
        msg.setDouble(quickfix.field.CouponRate.FIELD, 1.221);
        msg.setString(quickfix.field.SecurityExchange.FIELD, "NYSE");
        msg.setString(quickfix.field.Issuer.FIELD, "SUN & Co");
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 8);
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "SUN options");
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
        msg.setChar(quickfix.field.Side.FIELD, quickfix.field.Side.BUY);
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.Afternoon.getValue());
    }

    public void populate(QuoteStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
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
        msg.setContractMultiplier(new Double(1.034));
        msg.setCouponRate(new Double(1.221));
        msg.setSecurityExchange("NYSE");
        msg.setIssuer("MOTOROLA");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(issuerDataExp);
        msg.setSecurityDesc("MOT shares desc");
        msg.setEncodedSecurityDescLen(new Integer(8));
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setEncodedSecurityDesc(encSecExp);
        msg.setSide(Side.Buy);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
    }

    public void check(quickfix.fix42.QuoteStatusRequest expected, QuoteStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
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
        assertEquals(expected.getDouble(quickfix.field.ContractMultiplier.FIELD), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.CouponRate.FIELD), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.SecurityExchange.FIELD), actual.getSecurityExchange());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getInt(quickfix.field.EncodedIssuerLen.FIELD), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
        assertEquals(expected.getInt(quickfix.field.EncodedSecurityDescLen.FIELD), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedSecurityDesc());
        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
    }

    public void check(QuoteStatusRequestMsg expected, quickfix.fix42.QuoteStatusRequest actual) throws Exception {
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
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getDouble(quickfix.field.ContractMultiplier.FIELD), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getDouble(quickfix.field.CouponRate.FIELD), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedIssuer(), actual.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
    }

    public void check(QuoteStatusRequestMsg expected, QuoteStatusRequestMsg actual) {
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
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
    }
}
