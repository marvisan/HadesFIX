/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DontKnowTradeMsg42TestData.java
 *
 * $Id: DontKnowTradeMsg42TestData.java,v 1.1 2011-01-16 00:47:42 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.DontKnowTradeMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.type.DKReason;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for DontKnowTradeMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DontKnowTradeMsg42TestData extends MsgTest {

    private static final DontKnowTradeMsg42TestData INSTANCE;

    static {
        INSTANCE = new DontKnowTradeMsg42TestData();
    }

    public static DontKnowTradeMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DontKnowTradeMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        msg.setOrderID("XXX9374994");
        msg.setExecID("EXEC273663");
        msg.setDKReason(DKReason.Other);
        msg.setSymbol("BHP.AX");
        msg.setSymbolSfx("CDDF");
        msg.setSecurityID("BHP");
        msg.setSecurityIDSource("BHP-src");
        msg.setSecurityType(SecurityType.Cash.getValue());
        msg.setMaturityMonthYear("112011");
        msg.setMaturityDay(3);
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(22.22d);
        msg.setOptAttribute('0');
        msg.setContractMultiplier(1.22d);
        msg.setCouponRate(1.22d);
        msg.setSecurityExchange("ASX");
        msg.setIssuer("issuer");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] encodedIssuer = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(encodedIssuer);
        msg.setSecurityDesc("description");
        msg.setEncodedSecurityDescLen(new Integer(8));
        byte[] encodedSecDesc = new byte[] {(byte) 18, (byte) 34, (byte) 45, (byte) 97,
            (byte) 177, (byte) 200, (byte) 224, (byte) 253};
        msg.setEncodedSecurityDesc(encodedSecDesc);
        msg.setSide(Side.Buy);
        msg.setOrderQty(88.45d);
        msg.setCashOrderQty(23.33d);
        msg.setLastQty(23.44d);
        msg.setLastPx(33.33d);
        msg.setText("some text here");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(DontKnowTradeMsg expected, DontKnowTradeMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getDKReason(), actual.getDKReason());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay(), actual.getMaturityDay());
        assertEquals(expected.getPutOrCall(), actual.getPutOrCall());
        assertEquals(expected.getStrikePrice(), actual.getStrikePrice());
        assertEquals(expected.getOptAttribute(), actual.getOptAttribute());
        assertEquals(expected.getContractMultiplier(), actual.getContractMultiplier());
        assertEquals(expected.getCouponRate(), actual.getCouponRate());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getCashOrderQty(), actual.getCashOrderQty());
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
