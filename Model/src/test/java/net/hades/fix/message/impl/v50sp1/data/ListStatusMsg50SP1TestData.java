/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStatusMsg50SP1TestData.java
 *
 * $Id: ListStatusMsg50SP1TestData.java,v 1.1 2011-02-04 09:58:24 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListStatusMsg;
import net.hades.fix.message.group.impl.v50sp1.OrderStatusGroup50SP1TestData;
import net.hades.fix.message.type.ContingencyType;
import net.hades.fix.message.type.ListOrderStatus;
import net.hades.fix.message.type.ListRejectReason;
import net.hades.fix.message.type.ListStatusType;

/**
 * Test utility for ListStatusMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ListStatusMsg50SP1TestData extends MsgTest {

    private static final ListStatusMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new ListStatusMsg50SP1TestData();
    }

    public static ListStatusMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ListStatusMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setListID("LST564567");
        msg.setListStatusType(ListStatusType.Response);
        msg.setWaveNo("WAVE-635454");
        msg.setNoRpts(3);
        msg.setListOrderStatus(ListOrderStatus.Alert);
        msg.setContingencyType(ContingencyType.OneCancelsOther);
        msg.setListRejectReason(ListRejectReason.ExchangeClosed);
        msg.setRptSeq(1);
        msg.setListStatusText("status open");
        msg.setEncodedListStatusTextLen(new Integer(8));
        byte[] encodedListStatusText = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedListStatusText(encodedListStatusText);
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setTransactTime(cal.getTime());
        msg.setTotNoOrders(3);
        msg.setLastFragment(Boolean.FALSE);
        msg.setNoOrders(2);
        
        OrderStatusGroup50SP1TestData.getInstance().populate1(msg.getOrderStatusGroups()[0]);
        OrderStatusGroup50SP1TestData.getInstance().populate2(msg.getOrderStatusGroups()[1]);
    }

    public void check(ListStatusMsg expected, ListStatusMsg actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getListStatusType(), actual.getListStatusType());
        assertEquals(expected.getNoRpts(), actual.getNoRpts());
        assertEquals(expected.getListOrderStatus(), actual.getListOrderStatus());
        assertEquals(expected.getContingencyType(), actual.getContingencyType());
        assertEquals(expected.getListRejectReason(), actual.getListRejectReason());
        assertEquals(expected.getRptSeq(), actual.getRptSeq());
        assertEquals(expected.getListStatusText(), actual.getListStatusText());
        assertEquals(expected.getEncodedListStatusTextLen(), actual.getEncodedListStatusTextLen());
        assertArrayEquals(expected.getEncodedListStatusText(), actual.getEncodedListStatusText());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getTotNoOrders(), actual.getTotNoOrders());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());
        assertEquals(expected.getNoOrders(), actual.getNoOrders());

        OrderStatusGroup50SP1TestData.getInstance().check(expected.getOrderStatusGroups()[0], actual.getOrderStatusGroups()[0]);
        OrderStatusGroup50SP1TestData.getInstance().check(expected.getOrderStatusGroups()[1], actual.getOrderStatusGroups()[1]);
    }
}
