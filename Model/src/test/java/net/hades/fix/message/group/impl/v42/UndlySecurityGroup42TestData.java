/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlySecurityGroup42TestData.java
 *
 * $Id: UndlySecurityGroup42TestData.java,v 1.1 2011-04-16 09:39:03 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.UndlySecurityGroup;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for UndlySecurityGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class UndlySecurityGroup42TestData extends MsgTest {

    private static final UndlySecurityGroup42TestData INSTANCE;

    static {
        INSTANCE = new UndlySecurityGroup42TestData();
    }

    public static UndlySecurityGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(UndlySecurityGroup grp) {
        grp.setUnderlyingSymbol("JAVA_1");
        grp.setUnderlyingSymbolSfx("CD");
        grp.setUnderlyingSecurityID("sec_id_1");
        grp.setUnderlyingSecurityIDSource("3");
        grp.setUnderlyingSecurityType(SecurityType.Option.getValue());
        grp.setUnderlyingMaturityMonthYear("072009");
        grp.setUnderlyingMaturityDay(2);
        grp.setUnderlyingPutOrCall(PutOrCall.Call);
        grp.setUnderlyingStrikePrice(new Double(24.15));
        grp.setUnderlyingOptAttribute(new Character('B'));
        grp.setUnderlyingContractMultiplier(new Double(1.13));
        grp.setUnderlyingCouponRate(new Double(1.023));
        grp.setUnderlyingSecurityExchange("COMEX");
        grp.setUnderlyingIssuer("NYSE_1");
        grp.setEncodedUnderlyingIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        grp.setEncodedUnderlyingIssuer(encLegIssuer);
        grp.setUnderlyingSecurityDesc("sec_desc_1");
        grp.setEncodedUnderlyingSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        grp.setEncodedUnderlyingSecurityDesc(encLegSecDesc);
        grp.setRatioQty(23.5);
        grp.setSide(Side.Buy);
    }

    public void populate2(UndlySecurityGroup grp) {
        grp.setUnderlyingSymbol("JAVA_2");
        grp.setUnderlyingSymbolSfx("CD");
        grp.setUnderlyingSecurityID("sec_id_2");
        grp.setUnderlyingSecurityIDSource("5");
        grp.setUnderlyingSecurityType(SecurityType.OptionsOnComboEquity.getValue());
        grp.setUnderlyingMaturityMonthYear("082009");
        grp.setUnderlyingMaturityDay(2);
        grp.setUnderlyingPutOrCall(PutOrCall.Call);
        grp.setUnderlyingStrikePrice(new Double(24.15));
        grp.setUnderlyingOptAttribute(new Character('C'));
        grp.setUnderlyingContractMultiplier(new Double(1.13));
        grp.setUnderlyingCouponRate(new Double(1.023));
        grp.setUnderlyingSecurityExchange("COMEX");
        grp.setUnderlyingIssuer("NYSE_2");
        grp.setEncodedUnderlyingIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        grp.setEncodedUnderlyingIssuer(encLegIssuer);
        grp.setUnderlyingSecurityDesc("sec_desc_2");
        grp.setEncodedUnderlyingSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        grp.setEncodedUnderlyingSecurityDesc(encLegSecDesc);
        grp.setRatioQty(28.5);
        grp.setSide(Side.Sell);
    }

    public void check(UndlySecurityGroup expected, UndlySecurityGroup actual) throws Exception {
        assertEquals(expected.getUnderlyingSymbol(), actual.getUnderlyingSymbol());
        assertEquals(expected.getUnderlyingSymbolSfx(), actual.getUnderlyingSymbolSfx());
        assertEquals(expected.getUnderlyingSecurityID(), actual.getUnderlyingSecurityID());
        assertEquals(expected.getUnderlyingSecurityIDSource(), actual.getUnderlyingSecurityIDSource());
        assertEquals(expected.getUnderlyingSecurityType(), actual.getUnderlyingSecurityType());
        assertEquals(expected.getUnderlyingMaturityMonthYear(), actual.getUnderlyingMaturityMonthYear());
        assertEquals(expected.getUnderlyingMaturityDay(), actual.getUnderlyingMaturityDay());
        assertEquals(expected.getUnderlyingPutOrCall(), actual.getUnderlyingPutOrCall());
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
        assertEquals(expected.getRatioQty(), actual.getRatioQty());
        assertEquals(expected.getSide(), actual.getSide());
    }
}
