/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetGroup42TestData.java
 *
 * $Id: QuoteSetGroup42TestData.java,v 1.2 2009-07-21 08:58:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.QuoteSetGroup;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for QuoteSetGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteSetGroup42TestData extends MsgTest {

    private static final QuoteSetGroup42TestData INSTANCE;

    static {
        INSTANCE = new QuoteSetGroup42TestData();
    }

    public static QuoteSetGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix42.MassQuote.NoQuoteSets msg) throws Exception {
        msg.setString(quickfix.field.QuoteSetID.FIELD, "ZZ23433");
        msg.setString(quickfix.field.UnderlyingSymbol.FIELD, "JAVA");
        msg.setString(quickfix.field.UnderlyingSymbolSfx.FIELD, "WI");
        msg.setString(quickfix.field.UnderlyingSecurityID.FIELD, "JAVA");
        msg.setString(quickfix.field.UnderlyingSecurityIDSource.FIELD, "2");
        msg.setString(quickfix.field.UnderlyingSecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.UnderlyingMaturityMonthYear.FIELD, "200806w2");
        msg.setInt(quickfix.field.UnderlyingMaturityDay.FIELD, 15);
        msg.setInt(quickfix.field.UnderlyingPutOrCall.FIELD, 1);
        msg.setDecimal(quickfix.field.UnderlyingStrikePrice.FIELD, new BigDecimal("25.48"));
        msg.setChar(quickfix.field.UnderlyingOptAttribute.FIELD, 'C');
        msg.setDecimal(quickfix.field.UnderlyingContractMultiplier.FIELD, new BigDecimal("1.023"));
        msg.setDecimal(quickfix.field.UnderlyingCouponRate.FIELD, new BigDecimal("1.077"));
        msg.setString(quickfix.field.UnderlyingSecurityExchange.FIELD, "NYSE");
        msg.setString(quickfix.field.UnderlyingIssuer.FIELD, "NYSE PL");
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedUnderlyingIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.UnderlyingSecurityDesc.FIELD, "Motorola shares");
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
        msg.setUtcTimeStamp(quickfix.field.QuoteSetValidUntilTime.FIELD, new Date());
        msg.setInt(quickfix.field.TotNoQuoteEntries.FIELD, 3);
        
        // QuoteEntryGroup
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 2);
        quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries();
        QuoteEntryGroup42TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries();
        QuoteEntryGroup42TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate2(quickfix.fix42.MassQuote.NoQuoteSets msg) throws Exception {
        msg.setString(quickfix.field.QuoteSetID.FIELD, "AAA34353");
        msg.setString(quickfix.field.UnderlyingSymbol.FIELD, "IBM");
        msg.setString(quickfix.field.UnderlyingSymbolSfx.FIELD, "SS");
        msg.setString(quickfix.field.UnderlyingSecurityID.FIELD, "IBM");
        msg.setString(quickfix.field.UnderlyingSecurityIDSource.FIELD, "2");
        msg.setString(quickfix.field.UnderlyingSecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.UnderlyingMaturityMonthYear.FIELD, "200806w3");
        msg.setInt(quickfix.field.UnderlyingMaturityDay.FIELD, 12);
        msg.setInt(quickfix.field.UnderlyingPutOrCall.FIELD, 1);
        msg.setDecimal(quickfix.field.UnderlyingStrikePrice.FIELD, new BigDecimal("25.44"));
        msg.setChar(quickfix.field.UnderlyingOptAttribute.FIELD, 'C');
        msg.setDecimal(quickfix.field.UnderlyingContractMultiplier.FIELD, new BigDecimal("1.666"));
        msg.setDecimal(quickfix.field.UnderlyingCouponRate.FIELD, new BigDecimal("1.666"));
        msg.setString(quickfix.field.UnderlyingSecurityExchange.FIELD, "CBOT");
        msg.setString(quickfix.field.UnderlyingIssuer.FIELD, "CBOT PL");
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 48, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedUnderlyingIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.UnderlyingSecurityDesc.FIELD, "Motorola shares");
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 66, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
        msg.setUtcTimeStamp(quickfix.field.QuoteSetValidUntilTime.FIELD, new Date());
        msg.setInt(quickfix.field.TotNoQuoteEntries.FIELD, 4);

        // QuoteEntryGroup
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 2);
        quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries();
        QuoteEntryGroup42TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries();
        QuoteEntryGroup42TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate1(QuoteSetGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("FFF46466");
        msg.setUnderlyingSymbol("JAVA_1");
        msg.setUnderlyingSymbolSfx("CD");
        msg.setUnderlyingSecurityID("sec_id_1");
        msg.setUnderlyingSecurityIDSource("3");
        msg.setUnderlyingSecurityType(SecurityType.Option.getValue());
        msg.setUnderlyingMaturityMonthYear("200806w2");
        msg.setUnderlyingMaturityDay(new Integer(25));
        msg.setUnderlyingPutOrCall(PutOrCall.Call);
        msg.setUnderlyingStrikePrice(new Double(24.15));
        msg.setUnderlyingOptAttribute(new Character('B'));
        msg.setUnderlyingContractMultiplier(new Double(1.13));
        msg.setUnderlyingCouponRate(new Double(1.023));
        msg.setUnderlyingSecurityExchange("COMEX");
        msg.setUnderlyingIssuer("NYSE_1");
        msg.setEncodedUnderlyingIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        msg.setEncodedUnderlyingIssuer(encLegIssuer);
        msg.setUnderlyingSecurityDesc("sec_desc_1");
        msg.setEncodedUnderlyingSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        msg.setEncodedUnderlyingSecurityDesc(encLegSecDesc);
        msg.setQuoteSetValidUntilTime(new Date());
        msg.setTotNoQuoteEntries(new Integer(3));

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryGroup42TestData.getInstance().populate1(msg.getQuoteEntryGroups()[0]);
        QuoteEntryGroup42TestData.getInstance().populate2(msg.getQuoteEntryGroups()[1]);
    }

    public void populate2(QuoteSetGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("GGG878w7847");
        msg.setUnderlyingSymbol("IBM");
        msg.setUnderlyingSymbolSfx("DD");
        msg.setUnderlyingSecurityID("sec_id_1");
        msg.setUnderlyingSecurityIDSource("3");
        msg.setUnderlyingSecurityType(SecurityType.Option.getValue());
        msg.setUnderlyingMaturityMonthYear("200806w2");
        msg.setUnderlyingMaturityDay(new Integer(25));
        msg.setUnderlyingPutOrCall(PutOrCall.Call);
        msg.setUnderlyingStrikePrice(new Double(24.15));
        msg.setUnderlyingOptAttribute(new Character('B'));
        msg.setUnderlyingContractMultiplier(new Double(1.13));
        msg.setUnderlyingCouponRate(new Double(1.023));
        msg.setUnderlyingSecurityExchange("GLOBEX");
        msg.setUnderlyingIssuer("CBOT");
        msg.setEncodedUnderlyingIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        msg.setEncodedUnderlyingIssuer(encLegIssuer);
        msg.setUnderlyingSecurityDesc("sec_desc_1");
        msg.setEncodedUnderlyingSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        msg.setEncodedUnderlyingSecurityDesc(encLegSecDesc);
        msg.setQuoteSetValidUntilTime(new Date());
        msg.setTotNoQuoteEntries(new Integer(3));

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryGroup42TestData.getInstance().populate1(msg.getQuoteEntryGroups()[0]);
        QuoteEntryGroup42TestData.getInstance().populate2(msg.getQuoteEntryGroups()[1]);
    }

    public void check(QuoteSetGroup expected, quickfix.fix42.MassQuote.NoQuoteSets actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getString(quickfix.field.QuoteSetID.FIELD));
        assertEquals(expected.getUnderlyingSymbol(), actual.getString(quickfix.field.UnderlyingSymbol.FIELD));
        assertEquals(expected.getUnderlyingSymbolSfx(), actual.getString(quickfix.field.UnderlyingSymbolSfx.FIELD));
        assertEquals(expected.getUnderlyingSecurityID(), actual.getString(quickfix.field.UnderlyingSecurityID.FIELD));
        assertEquals(expected.getUnderlyingSecurityIDSource(), actual.getString(quickfix.field.UnderlyingSecurityIDSource.FIELD));
        assertEquals(expected.getUnderlyingSecurityType(), actual.getString(quickfix.field.UnderlyingSecurityType.FIELD));
        assertEquals(expected.getUnderlyingMaturityMonthYear(), actual.getString(quickfix.field.UnderlyingMaturityMonthYear.FIELD));
        assertEquals(expected.getUnderlyingMaturityDay().intValue(), actual.getInt(quickfix.field.UnderlyingMaturityDay.FIELD));
        assertEquals(expected.getUnderlyingPutOrCall().getValue(), actual.getInt(quickfix.field.UnderlyingPutOrCall.FIELD));
        assertEquals(expected.getUnderlyingStrikePrice().doubleValue(), actual.getDouble(quickfix.field.UnderlyingStrikePrice.FIELD), 0.001);
        assertEquals(expected.getUnderlyingOptAttribute().charValue(), actual.getChar(quickfix.field.UnderlyingOptAttribute.FIELD));
        assertEquals(expected.getUnderlyingContractMultiplier().doubleValue(), actual.getDouble(quickfix.field.UnderlyingContractMultiplier.FIELD), 0.001);
        assertEquals(expected.getUnderlyingCouponRate().doubleValue(), actual.getDouble(quickfix.field.UnderlyingCouponRate.FIELD), 0.001);
        assertEquals(expected.getUnderlyingSecurityExchange(), actual.getString(quickfix.field.UnderlyingSecurityExchange.FIELD));
        assertEquals(expected.getUnderlyingIssuer(), actual.getString(quickfix.field.UnderlyingIssuer.FIELD));
        assertEquals(expected.getEncodedUnderlyingIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedUnderlyingIssuer(), actual.getString(quickfix.field.EncodedUnderlyingIssuer.FIELD).getBytes());
        assertEquals(expected.getUnderlyingSecurityDesc(), actual.getString(quickfix.field.UnderlyingSecurityDesc.FIELD));
        assertEquals(expected.getEncodedUnderlyingSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD));
        assertArrayEquals(expected.getEncodedUnderlyingSecurityDesc(), actual.getString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD).getBytes());
        assertUTCTimestampEquals(expected.getQuoteSetValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.QuoteSetValidUntilTime.FIELD), false);
        assertEquals(expected.getTotNoQuoteEntries().intValue(), actual.getInt(quickfix.field.TotNoQuoteEntries.FIELD));

        // QuoteEntryGroup check
        quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries();
        actual.getGroup(1, grp1);
        QuoteEntryGroup42TestData.getInstance().check(expected.getQuoteEntryGroups()[0], grp1);
        quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries();
        actual.getGroup(2, grp2);
        QuoteEntryGroup42TestData.getInstance().check(expected.getQuoteEntryGroups()[1], grp2);
    }

    public void check(quickfix.fix42.MassQuote.NoQuoteSets expected, QuoteSetGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteSetID.FIELD), actual.getQuoteSetID());
        assertEquals(actual.getUnderlyingSymbol(), expected.getString(quickfix.field.UnderlyingSymbol.FIELD));
        assertEquals(actual.getUnderlyingSymbolSfx(), expected.getString(quickfix.field.UnderlyingSymbolSfx.FIELD));
        assertEquals(actual.getUnderlyingSecurityID(), expected.getString(quickfix.field.UnderlyingSecurityID.FIELD));
        assertEquals(actual.getUnderlyingSecurityIDSource(), expected.getString(quickfix.field.UnderlyingSecurityIDSource.FIELD));
        assertEquals(actual.getUnderlyingSecurityType(), expected.getString(quickfix.field.UnderlyingSecurityType.FIELD));
        assertEquals(actual.getUnderlyingMaturityMonthYear(), expected.getString(quickfix.field.UnderlyingMaturityMonthYear.FIELD));
        assertEquals(actual.getUnderlyingMaturityDay().intValue(), expected.getInt(quickfix.field.UnderlyingMaturityDay.FIELD));
        assertEquals(actual.getUnderlyingPutOrCall().getValue(), expected.getInt(quickfix.field.UnderlyingPutOrCall.FIELD));
        assertEquals(actual.getUnderlyingStrikePrice().doubleValue(), expected.getDouble(quickfix.field.UnderlyingStrikePrice.FIELD), 0.001);
        assertEquals(actual.getUnderlyingOptAttribute().charValue(), expected.getChar(quickfix.field.UnderlyingOptAttribute.FIELD));
        assertEquals(actual.getUnderlyingContractMultiplier().doubleValue(), expected.getDouble(quickfix.field.UnderlyingContractMultiplier.FIELD), 0.001);
        assertEquals(actual.getUnderlyingCouponRate().doubleValue(), expected.getDouble(quickfix.field.UnderlyingCouponRate.FIELD), 0.001);
        assertEquals(actual.getUnderlyingSecurityExchange(), expected.getString(quickfix.field.UnderlyingSecurityExchange.FIELD));
        assertEquals(actual.getUnderlyingIssuer(), expected.getString(quickfix.field.UnderlyingIssuer.FIELD));
        assertEquals(actual.getEncodedUnderlyingIssuerLen().intValue(), expected.getInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD));
        assertArrayEquals(actual.getEncodedUnderlyingIssuer(), expected.getString(quickfix.field.EncodedUnderlyingIssuer.FIELD).getBytes());
        assertEquals(actual.getUnderlyingSecurityDesc(), expected.getString(quickfix.field.UnderlyingSecurityDesc.FIELD));
        assertEquals(actual.getEncodedUnderlyingSecurityDescLen().intValue(), expected.getInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD));
        assertArrayEquals(actual.getEncodedUnderlyingSecurityDesc(), expected.getString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD).getBytes());
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.QuoteSetValidUntilTime.FIELD), actual.getQuoteSetValidUntilTime(), false);
        assertEquals(actual.getTotNoQuoteEntries().intValue(), expected.getInt(quickfix.field.TotNoQuoteEntries.FIELD));

        // QuoteEntryGroup check
        quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries();
        expected.getGroup(1, grp1);
        QuoteEntryGroup42TestData.getInstance().check(grp1, actual.getQuoteEntryGroups()[0]);
        quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix42.MassQuote.NoQuoteSets.NoQuoteEntries();
        expected.getGroup(2, grp2);
        QuoteEntryGroup42TestData.getInstance().check(grp2, actual.getQuoteEntryGroups()[1]);
    }

    public void check(QuoteSetGroup expected, QuoteSetGroup actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getQuoteSetID());
        assertEquals(expected.getUnderlyingSymbol(), actual.getUnderlyingSymbol());
        assertEquals(expected.getUnderlyingSymbolSfx(), actual.getUnderlyingSymbolSfx());
        assertEquals(expected.getUnderlyingSecurityID(), actual.getUnderlyingSecurityID());
        assertEquals(expected.getUnderlyingSecurityIDSource(), actual.getUnderlyingSecurityIDSource());
        assertEquals(expected.getUnderlyingSecurityType(), actual.getUnderlyingSecurityType());
        assertEquals(expected.getUnderlyingMaturityMonthYear(), actual.getUnderlyingMaturityMonthYear());
        assertEquals(expected.getUnderlyingMaturityDay(), actual.getUnderlyingMaturityDay());
        assertEquals(expected.getUnderlyingStrikePrice().doubleValue(), actual.getUnderlyingStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingOptAttribute().charValue(), actual.getUnderlyingOptAttribute().charValue());
        assertEquals(expected.getUnderlyingContractMultiplier().doubleValue(), actual.getUnderlyingContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingCouponRate().doubleValue(), actual.getUnderlyingCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingSecurityExchange(), actual.getUnderlyingSecurityExchange());
        assertEquals(expected.getUnderlyingIssuer(), actual.getUnderlyingIssuer());
        assertEquals(expected.getEncodedUnderlyingIssuerLen().intValue(), actual.getEncodedUnderlyingIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedUnderlyingIssuer(), actual.getEncodedUnderlyingIssuer());
        assertEquals(expected.getUnderlyingSecurityDesc(), actual.getUnderlyingSecurityDesc());
        assertEquals(expected.getEncodedUnderlyingSecurityDescLen().intValue(), actual.getEncodedUnderlyingSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedUnderlyingSecurityDesc(), actual.getEncodedUnderlyingSecurityDesc());
        assertUTCTimestampEquals(expected.getQuoteSetValidUntilTime(), actual.getQuoteSetValidUntilTime(), false);
        assertEquals(actual.getTotNoQuoteEntries(), expected.getTotNoQuoteEntries());

        // QuoteEntryGroup check
        QuoteEntryGroup42TestData.getInstance().check(expected.getQuoteEntryGroups()[0], actual.getQuoteEntryGroups()[0]);
        QuoteEntryGroup42TestData.getInstance().check(expected.getQuoteEntryGroups()[1], actual.getQuoteEntryGroups()[1]);
    }
}
