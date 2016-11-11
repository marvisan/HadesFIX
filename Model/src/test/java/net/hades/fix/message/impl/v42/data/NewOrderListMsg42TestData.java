/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderListMsg42TestData.java
 *
 * $Id: NewOrderListMsg42TestData.java,v 1.1 2011-02-02 10:03:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewOrderListMsg;
import net.hades.fix.message.group.impl.v42.OrderListGroup42TestData;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.ListExecInstType;
import net.hades.fix.message.type.ProgRptReqs;

/**
 * Test utility for NewOrderListMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class NewOrderListMsg42TestData extends MsgTest {

    private static final NewOrderListMsg42TestData INSTANCE;

    static {
        INSTANCE = new NewOrderListMsg42TestData();
    }

    public static NewOrderListMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NewOrderListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setListID("LST564567");
        msg.setBidID("BID8273633");
        msg.setClientBidID("ID62357383");
        msg.setProgRptReqs(ProgRptReqs.BuySide);
        msg.setBidType(BidType.Disclosed);
        msg.setProgPeriodInterval(3);
        msg.setListExecInstType(ListExecInstType.Immediate);
        msg.setListExecInst("INS-SHARE");
        msg.setEncodedListExecInstLen(new Integer(8));
        byte[] encodedListExecInst = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedListExecInst(encodedListExecInst);
        msg.setTotNoOrders(3);

        msg.setNoOrders(2);
        OrderListGroup42TestData.getInstance().populate1(msg.getOrderListGroups()[0]);
        OrderListGroup42TestData.getInstance().populate2(msg.getOrderListGroups()[1]);
    }

    public void check(NewOrderListMsg expected, NewOrderListMsg actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getBidID(), actual.getBidID());
        assertEquals(expected.getClientBidID(), actual.getClientBidID());
        assertEquals(expected.getProgRptReqs(), actual.getProgRptReqs());
        assertEquals(expected.getBidType(), actual.getBidType());
        assertEquals(expected.getProgPeriodInterval(), actual.getProgPeriodInterval());
        assertEquals(expected.getListExecInstType(), actual.getListExecInstType());
        assertEquals(expected.getListExecInst(), actual.getListExecInst());
        assertEquals(expected.getEncodedListExecInstLen().intValue(), actual.getEncodedListExecInstLen().intValue());
        assertArrayEquals(expected.getEncodedListExecInst(), actual.getEncodedListExecInst());
        assertEquals(expected.getTotNoOrders(), actual.getTotNoOrders());

        assertEquals(expected.getNoOrders(), actual.getNoOrders());
        OrderListGroup42TestData.getInstance().check(expected.getOrderListGroups()[0], actual.getOrderListGroups()[0]);
        OrderListGroup42TestData.getInstance().check(expected.getOrderListGroups()[1], actual.getOrderListGroups()[1]);
     }
}
