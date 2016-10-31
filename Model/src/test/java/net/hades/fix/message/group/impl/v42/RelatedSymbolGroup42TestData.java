/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RelatedSymbolGroup42TestData.java
 *
 * $Id: RelatedSymbolGroup42TestData.java,v 1.1 2009-07-06 03:19:17 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.RelatedSymbolGroup;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for RelatedSymbolGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class RelatedSymbolGroup42TestData extends MsgTest {

    private static final RelatedSymbolGroup42TestData INSTANCE;

    static {
        INSTANCE = new RelatedSymbolGroup42TestData();
    }

    public static RelatedSymbolGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix42.News.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.RelatdSym.FIELD, "MOT");
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
    }

    public void populate2(quickfix.fix42.News.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.RelatdSym.FIELD, "JAVA");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "JAVA shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "Sec ID 2");
        msg.setString(quickfix.field.IDSource.FIELD, "ID src 22");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTIONS_ON_FUTURES);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "042009");
        msg.setString(quickfix.field.MaturityDay.FIELD, "21");
        msg.setInt(quickfix.field.PutOrCall.FIELD, PutOrCall.Put.getValue());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 23.11);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'A');
        msg.setDouble(quickfix.field.ContractMultiplier.FIELD, 1.224);
        msg.setDouble(quickfix.field.CouponRate.FIELD, 1.771);
        msg.setString(quickfix.field.SecurityExchange.FIELD, "LMX");
        msg.setString(quickfix.field.Issuer.FIELD, "CBOE");
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 8);
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "Some JAVA shares");
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
    }

    public void populate1(quickfix.fix42.Email.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.RelatdSym.FIELD, "MOT");
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
    }

    public void populate2(quickfix.fix42.Email.NoRelatedSym msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.RelatdSym.FIELD, "JAVA");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "JAVA shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "Sec ID 2");
        msg.setString(quickfix.field.IDSource.FIELD, "ID src 22");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTIONS_ON_FUTURES);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "042009");
        msg.setString(quickfix.field.MaturityDay.FIELD, "21");
        msg.setInt(quickfix.field.PutOrCall.FIELD, PutOrCall.Put.getValue());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 23.11);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'A');
        msg.setDouble(quickfix.field.ContractMultiplier.FIELD, 1.224);
        msg.setDouble(quickfix.field.CouponRate.FIELD, 1.771);
        msg.setString(quickfix.field.SecurityExchange.FIELD, "LMX");
        msg.setString(quickfix.field.Issuer.FIELD, "CBOE");
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 8);
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "Some JAVA shares");
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
    }

    public void populate1(RelatedSymbolGroup msg) throws UnsupportedEncodingException {
        msg.setRelatedSym("MOT");
        msg.setSymbolSfx("MOT shares");
        msg.setSecurityID("Sec ID");
        msg.setSecurityIDSource("ID src");
        msg.setSecurityType(SecurityType.Option);
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
    }

    public void populate2(RelatedSymbolGroup msg) throws UnsupportedEncodingException {
        msg.setRelatedSym("JAVA");
        msg.setSymbolSfx("JAVA shares");
        msg.setSecurityID("Sec ID 2");
        msg.setSecurityIDSource("ID src 2");
        msg.setSecurityType(SecurityType.OptionsOnComboEquity);
        msg.setMaturityMonthYear("042009");
        msg.setMaturityDay(new Integer(14));
        msg.setPutOrCall(PutOrCall.Put);
        msg.setStrikePrice(new Double(23.14));
        msg.setOptAttribute(new Character('C'));
        msg.setContractMultiplier(new Double(1.234));
        msg.setCouponRate(new Double(1.661));
        msg.setSecurityExchange("LMX");
        msg.setIssuer("ASX");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(issuerDataExp);
        msg.setSecurityDesc("Some JAVA shares");
        msg.setEncodedSecurityDescLen(new Integer(8));
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setEncodedSecurityDesc(encSecExp);
    }

    public void check(RelatedSymbolGroup expected, quickfix.fix42.News.NoRelatedSym actual) throws Exception {
        assertEquals(expected.getRelatedSym(), actual.getString(quickfix.field.RelatdSym.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.IDSource.FIELD));
        assertEquals(expected.getSecurityType().getValue(), actual.getString(quickfix.field.SecurityType.FIELD));
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
    }

    public void check(quickfix.fix42.News.NoRelatedSym expected, RelatedSymbolGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.RelatdSym.FIELD), actual.getRelatedSym());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.IDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType().getValue());
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
    }

    public void check(RelatedSymbolGroup expected, quickfix.fix42.Email.NoRelatedSym actual) throws Exception {
        assertEquals(expected.getRelatedSym(), actual.getString(quickfix.field.RelatdSym.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.IDSource.FIELD));
        assertEquals(expected.getSecurityType().getValue(), actual.getString(quickfix.field.SecurityType.FIELD));
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
    }

    public void check(quickfix.fix42.Email.NoRelatedSym expected, RelatedSymbolGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.RelatdSym.FIELD), actual.getRelatedSym());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.IDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType().getValue());
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
    }
    
    public void check(RelatedSymbolGroup expected, RelatedSymbolGroup actual) throws Exception {
        assertEquals(expected.getRelatedSym(), actual.getRelatedSym());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType().getValue(), actual.getSecurityType().getValue());
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
    }
    
}
