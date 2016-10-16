/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCancelRequestMsg40TestData.java
 *
 * $Id: OrderCancelRequestMsg40TestData.java,v 1.1 2011-01-22 09:52:25 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderCancelRequestMsg;
import net.hades.fix.message.type.Side;

/**
 * Test utility for OrderCancelRequestMsg40 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderCancelRequestMsg40TestData extends MsgTest {

    private static final OrderCancelRequestMsg40TestData INSTANCE;

    static {
        INSTANCE = new OrderCancelRequestMsg40TestData();
    }

    public static OrderCancelRequestMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderCancelRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setOrigClOrdID("ORIORD35835");
        msg.setOrderID("ORD33334");
        msg.setClOrdID("AAA564567");
        msg.setListID("LST99374744");
        msg.setCxlType("3");
        msg.setClientID("client");
        msg.setExecBroker("broker");
        msg.setSymbol("BHP.AX");
        msg.setSymbolSfx("CDDF");
        msg.setSecurityID("BHP");
        msg.setSecurityIDSource("BHP-src");
        msg.setIssuer("issuer");
        msg.setSecurityDesc("description");
        msg.setSide(Side.Buy);
        msg.setOrderQty(88.45d);
        msg.setText("text");
    }

    public void check(OrderCancelRequestMsg expected, OrderCancelRequestMsg actual) throws Exception {
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getCxlType(), actual.getCxlType());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getText(), actual.getText());
    }
}
