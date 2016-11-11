/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCancelRejectMsg42TestData.java
 *
 * $Id: OrderCancelRejectMsg42TestData.java,v 1.1 2011-01-23 10:02:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;
import java.util.Calendar;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderCancelRejectMsg;
import net.hades.fix.message.type.CxlRejReason;
import net.hades.fix.message.type.CxlRejResponseTo;
import net.hades.fix.message.type.OrdStatus;

/**
 * Test utility for OrderCancelRejectMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderCancelRejectMsg42TestData extends MsgTest {

    private static final OrderCancelRejectMsg42TestData INSTANCE;

    static {
        INSTANCE = new OrderCancelRejectMsg42TestData();
    }

    public static OrderCancelRejectMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderCancelRejectMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setOrderID("ORD33334");
        msg.setSecondaryOrderID("SSS778877");
        msg.setClOrdID("AAA564567");
        msg.setOrigClOrdID("COD003883");
        msg.setOrdStatus(OrdStatus.Replaced);
        msg.setClientID("client");
        msg.setExecBroker("broker");
        msg.setListID("LST99374744");
        msg.setAccount("12735534784");
        cal.set(2010, 3, 14, 12, 56, 56);
        msg.setTransactTime(cal.getTime());
        msg.setCxlRejResponseTo(CxlRejResponseTo.OrderCancelRequest);
        msg.setCxlRejReason(CxlRejReason.BrokerOption);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
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
        assertEquals(expected.getAccount(), actual.getAccount());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getCxlRejReason(), actual.getCxlRejReason());
        assertEquals(expected.getCxlRejReason(), actual.getCxlRejReason());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
