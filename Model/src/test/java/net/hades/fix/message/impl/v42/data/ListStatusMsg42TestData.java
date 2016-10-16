/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStatusMsg42TestData.java
 *
 * $Id: ListStatusMsg42TestData.java,v 1.1 2011-02-04 09:58:24 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListStatusMsg;
import net.hades.fix.message.group.impl.v42.OrderStatusGroup42TestData;
import net.hades.fix.message.type.ListOrderStatus;
import net.hades.fix.message.type.ListStatusType;

/**
 * Test utility for ListStatusMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ListStatusMsg42TestData extends MsgTest {

    private static final ListStatusMsg42TestData INSTANCE;

    static {
        INSTANCE = new ListStatusMsg42TestData();
    }

    public static ListStatusMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ListStatusMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setListID("LST564567");
        msg.setListStatusType(ListStatusType.Response);
        msg.setWaveNo("WAVE-635454");
        msg.setNoRpts(3);
        msg.setListOrderStatus(ListOrderStatus.Alert);
        msg.setRptSeq(1);
        msg.setListStatusText("status open");
        msg.setEncodedListStatusTextLen(new Integer(8));
        byte[] encodedListStatusText = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedListStatusText(encodedListStatusText);
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setTransactTime(cal.getTime());
        msg.setTotNoOrders(3);
        msg.setNoOrders(2);
        OrderStatusGroup42TestData.getInstance().populate1(msg.getOrderStatusGroups()[0]);
        OrderStatusGroup42TestData.getInstance().populate2(msg.getOrderStatusGroups()[1]);
    }

    public void check(ListStatusMsg expected, ListStatusMsg actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getListStatusType(), actual.getListStatusType());
        assertEquals(expected.getWaveNo(), actual.getWaveNo());
        assertEquals(expected.getNoRpts(), actual.getNoRpts());
        assertEquals(expected.getListOrderStatus(), actual.getListOrderStatus());
        assertEquals(expected.getRptSeq(), actual.getRptSeq());
        assertEquals(expected.getListStatusText(), actual.getListStatusText());
        assertEquals(expected.getEncodedListStatusTextLen(), actual.getEncodedListStatusTextLen());
        assertArrayEquals(expected.getEncodedListStatusText(), actual.getEncodedListStatusText());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getTotNoOrders(), actual.getTotNoOrders());
        assertEquals(expected.getNoOrders(), actual.getNoOrders());

        OrderStatusGroup42TestData.getInstance().check(expected.getOrderStatusGroups()[0], actual.getOrderStatusGroups()[0]);
        OrderStatusGroup42TestData.getInstance().check(expected.getOrderStatusGroups()[1], actual.getOrderStatusGroups()[1]);
    }
}
