/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderStatusRequestMsg43TestData.java
 *
 * $Id: OrderStatusRequestMsg43TestData.java,v 1.1 2011-01-24 10:50:02 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderStatusRequestMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.type.Side;

/**
 * Test utility for OrderStatusRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderStatusRequestMsg43TestData extends MsgTest {

    private static final OrderStatusRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new OrderStatusRequestMsg43TestData();
    }

    public static OrderStatusRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setOrderID("ORD33334");
        msg.setClOrdID("AAA564567");
        msg.setSecondaryClOrdID("BBB363744");
        msg.setClOrdLinkID("SA88767788");

        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        msg.setAccount("12735534784");

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setSide(Side.Buy);
    }

    public void check(OrderStatusRequestMsg expected, OrderStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());

        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getSide(), actual.getSide());
    }
}
