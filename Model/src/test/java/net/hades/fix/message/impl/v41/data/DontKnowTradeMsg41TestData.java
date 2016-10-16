/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportMsg40TestData.java
 *
 * $Id: DontKnowTradeMsg41TestData.java,v 1.1 2011-01-16 00:47:41 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41.data;

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
 * Test utility for DontKnowTradeMsg41 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DontKnowTradeMsg41TestData extends MsgTest {

    private static final DontKnowTradeMsg41TestData INSTANCE;

    static {
        INSTANCE = new DontKnowTradeMsg41TestData();
    }

    public static DontKnowTradeMsg41TestData getInstance() {
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
        msg.setSecurityExchange("ASX");
        msg.setIssuer("issuer");
        msg.setSecurityDesc("description");
        msg.setSide(Side.Buy);
        msg.setOrderQty(88.45d);
        msg.setCashOrderQty(23.33d);
        msg.setLastQty(23.44d);
        msg.setLastPx(33.33d);
        msg.setText("some text here");
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
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getCashOrderQty(), actual.getCashOrderQty());
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getText(), actual.getText());
    }
}
