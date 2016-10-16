/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCancelRejectMsg40TestData.java
 *
 * $Id: OrderCancelRejectMsg40TestData.java,v 1.1 2011-01-23 10:02:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderCancelRejectMsg;
import net.hades.fix.message.type.CxlRejReason;

/**
 * Test utility for OrderCancelRejectMsg40 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderCancelRejectMsg40TestData extends MsgTest {

    private static final OrderCancelRejectMsg40TestData INSTANCE;

    static {
        INSTANCE = new OrderCancelRejectMsg40TestData();
    }

    public static OrderCancelRejectMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderCancelRejectMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setOrderID("ORD33334");
        msg.setClOrdID("AAA564567");
        msg.setClientID("client");
        msg.setExecBroker("broker");
        msg.setListID("LST99374744");
        msg.setCxlRejReason(CxlRejReason.BrokerOption);
        msg.setText("text");
    }

    public void check(OrderCancelRejectMsg expected, OrderCancelRejectMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getCxlRejReason(), actual.getCxlRejReason());
        assertEquals(expected.getText(), actual.getText());
    }
}
