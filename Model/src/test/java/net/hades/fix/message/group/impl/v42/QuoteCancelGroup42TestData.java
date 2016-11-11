/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelGroup42TestData.java
 *
 * $Id: QuoteCancelGroup42TestData.java,v 1.2 2009-11-21 09:57:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.QuoteCancelGroup;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityIDSource;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for QuoteCancelGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteCancelGroup42TestData extends MsgTest {

    private static final QuoteCancelGroup42TestData INSTANCE;

    static {
        INSTANCE = new QuoteCancelGroup42TestData();
    }

    public static QuoteCancelGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix42.QuoteCancel.NoQuoteEntries msg) throws Exception {
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
            (byte) 179, (byte) 202, (byte) 226, (byte) 257};
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(secDescDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.UnderlyingSymbol.FIELD, "MOTX");
    }

    public void populate2(quickfix.fix42.QuoteCancel.NoQuoteEntries msg) throws Exception {
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
            (byte) 179, (byte) 203, (byte) 226, (byte) 257};
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(secDescDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.UnderlyingSymbol.FIELD, "IBMX");
    }

    public void populate1(QuoteCancelGroup msg) throws UnsupportedEncodingException {
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
            (byte) 179, (byte) 202, (byte) 226, (byte) 257};
        msg.setEncodedSecurityDescLen(new Integer(8));
        msg.setEncodedSecurityDesc(secDescDataExp);
        msg.setUnderlyingSymbol("MOTX");
    }

    public void populate2(QuoteCancelGroup msg) throws UnsupportedEncodingException {
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
            (byte) 179, (byte) 209, (byte) 226, (byte) 257};
        msg.setEncodedSecurityDescLen(new Integer(8));
        msg.setEncodedSecurityDesc(secDescDataExp);
        msg.setUnderlyingSymbol("IBMX");
    }

    public void check(QuoteCancelGroup expected, quickfix.fix42.QuoteCancel.NoQuoteEntries actual) throws Exception {
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
        assertEquals(expected.getUnderlyingSymbol(), actual.getString(quickfix.field.UnderlyingSymbol.FIELD));
    }

    public void check(quickfix.fix42.QuoteCancel.NoQuoteEntries expected, QuoteCancelGroup actual) throws Exception {
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
        assertEquals(expected.getString(quickfix.field.UnderlyingSymbol.FIELD), actual.getUnderlyingSymbol());
    }

    public void check(QuoteCancelGroup expected, QuoteCancelGroup actual) throws Exception {
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
        assertEquals(expected.getUnderlyingSymbol(), actual.getUnderlyingSymbol());
    }
}
