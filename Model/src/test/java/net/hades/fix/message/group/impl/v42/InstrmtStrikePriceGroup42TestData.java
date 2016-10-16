/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtStrikePriceGroup42TestData.java
 *
 * $Id: InstrmtStrikePriceGroup42TestData.java,v 1.2 2011-10-29 09:42:27 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.InstrmtStrikePriceGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for InstrmtStrikePriceGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class InstrmtStrikePriceGroup42TestData extends MsgTest {

    private static final InstrmtStrikePriceGroup42TestData INSTANCE;

    static {
        INSTANCE = new InstrmtStrikePriceGroup42TestData();
    }

    public static InstrmtStrikePriceGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrmtStrikePriceGroup msg) throws UnsupportedEncodingException {
        msg.setSymbol("symbol");
        msg.setSymbolSfx("symbol sfx");
        msg.setSecurityID("sec ID");
        msg.setSecurityIDSource("sec ID source");
        msg.setSecurityType(SecurityType.AmendedRestated.getValue());
        msg.setMaturityMonthYear("082009");
        msg.setMaturityDay(new Integer(3));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(12.456));
        msg.setOptAttribute(new Character('Z'));
        msg.setContractMultiplier(new Double(23.44));
        msg.setCouponRate(new Double(12.999));
        msg.setSecurityExchange("NYSE");
        msg.setIssuer("ISSUER");
        msg.setEncodedIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        msg.setEncodedIssuer(encLegIssuer);
        msg.setSecurityDesc("sec desc");
        msg.setEncodedSecurityDescLen(new Integer(6));
        byte[] encEncSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        msg.setEncodedSecurityDesc(encEncSecDesc);
        msg.setPrevClosePx(44.5);
        msg.setClOrdID("CLIO-7363644");
        msg.setSecondaryClOrdID("SECORD-735353");
        msg.setSide(Side.Buy);
        msg.setPrice(46.77);
        msg.setCurrency(Currency.AustralianDollar);
        msg.setText("Some text 1");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        msg.setEncodedText(encText);
    }

    public void populate2(InstrmtStrikePriceGroup msg) throws UnsupportedEncodingException {
        msg.setSymbol("symbol 1");
        msg.setSymbolSfx("symbol sfx 1");
        msg.setSecurityID("sec ID 1");
        msg.setSecurityIDSource("sec ID source 1");
        msg.setSecurityType(SecurityType.BankDepositoryNote.getValue());
        msg.setMaturityMonthYear("082010");
        msg.setMaturityDay(new Integer(3));
        msg.setPutOrCall(PutOrCall.Put);
        msg.setStrikePrice(new Double(12.45));
        msg.setOptAttribute(new Character('X'));
        msg.setContractMultiplier(new Double(23.66));
        msg.setCouponRate(new Double(12.666));
        msg.setSecurityExchange("CBOT");
        msg.setIssuer("ISSUER 1");
        msg.setEncodedIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 12, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        msg.setEncodedIssuer(encLegIssuer);
        msg.setSecurityDesc("sec desc");
        msg.setEncodedSecurityDescLen(new Integer(6));
        byte[] encEncSecDesc = new byte[] {(byte) 29, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        msg.setEncodedSecurityDesc(encEncSecDesc);
        msg.setPrevClosePx(44.1);
        msg.setClOrdID("CLIO-7363655");
        msg.setSecondaryClOrdID("SECORD-735388");
        msg.setSide(Side.Sell);
        msg.setPrice(46.21);
        msg.setCurrency(Currency.CanadianDollar);
        msg.setText("Some text 2");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 16, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        msg.setEncodedText(encText);
    }

    public void check(InstrmtStrikePriceGroup expected, InstrmtStrikePriceGroup actual) throws Exception {
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
        assertEquals(expected.getPrevClosePx(), actual.getPrevClosePx());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
