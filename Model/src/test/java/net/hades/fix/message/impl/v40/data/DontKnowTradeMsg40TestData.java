/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportMsg40TestData.java
 *
 * $Id: DontKnowTradeMsg40TestData.java,v 1.1 2011-01-16 00:47:42 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.DontKnowTradeMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.type.DKReason;
import net.hades.fix.message.type.Side;

/**
 * Test utility for DontKnowTradeMsg40 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DontKnowTradeMsg40TestData extends MsgTest {

    private static final DontKnowTradeMsg40TestData INSTANCE;

    static {
        INSTANCE = new DontKnowTradeMsg40TestData();
    }

    public static DontKnowTradeMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DontKnowTradeMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setOrderID("XXX9374994");
        msg.setExecID("EXEC273663");
        msg.setDKReason(DKReason.Other);
        msg.setSymbol("BHP.AX");
        msg.setIssuer("issuer");
        msg.setSecurityDesc("description");
        msg.setSide(Side.Buy);
        msg.setOrderQty(88.45d);
        msg.setLastQty(23.44d);
        msg.setLastPx(33.33d);
        msg.setText("some text here");
    }

    public void check(DontKnowTradeMsg expected, DontKnowTradeMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getDKReason(), actual.getDKReason());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getText(), actual.getText());
    }
}
