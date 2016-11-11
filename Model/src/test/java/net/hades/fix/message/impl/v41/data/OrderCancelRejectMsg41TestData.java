/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCancelRejectMsg41TestData.java
 *
 * $Id: OrderCancelRejectMsg41TestData.java,v 1.1 2011-01-23 10:02:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderCancelRejectMsg;
import net.hades.fix.message.type.CxlRejReason;
import net.hades.fix.message.type.OrdStatus;

/**
 * Test utility for OrderCancelRejectMsg41 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderCancelRejectMsg41TestData extends MsgTest {

    private static final OrderCancelRejectMsg41TestData INSTANCE;

    static {
        INSTANCE = new OrderCancelRejectMsg41TestData();
    }

    public static OrderCancelRejectMsg41TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderCancelRejectMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        msg.setOrderID("ORD33334");
        msg.setSecondaryOrderID("SSS778877");
        msg.setClOrdID("AAA564567");
        msg.setOrigClOrdID("COD003883");
        msg.setOrdStatus(OrdStatus.Replaced);
        msg.setClientID("client");
        msg.setExecBroker("broker");
        msg.setListID("LST99374744");
        msg.setCxlRejReason(CxlRejReason.BrokerOption);
        msg.setText("text");
    }

    public void check(OrderCancelRejectMsg expected, OrderCancelRejectMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getOrdStatus(), actual.getOrdStatus());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getCxlRejReason(), actual.getCxlRejReason());
        assertEquals(expected.getText(), actual.getText());
    }
}
