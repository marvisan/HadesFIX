/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStatusMsg41TestData.java
 *
 * $Id: ListStatusMsg41TestData.java,v 1.1 2011-02-04 09:58:21 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListStatusMsg;
import net.hades.fix.message.group.impl.v41.OrderStatusGroup41TestData;

/**
 * Test utility for ListStatusMsg41 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ListStatusMsg41TestData extends MsgTest {

    private static final ListStatusMsg41TestData INSTANCE;

    static {
        INSTANCE = new ListStatusMsg41TestData();
    }

    public static ListStatusMsg41TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ListStatusMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setListID("LST564567");
        msg.setWaveNo("WAVE-635454");
        msg.setNoRpts(3);
        msg.setRptSeq(1);
        msg.setNoOrders(2);
        OrderStatusGroup41TestData.getInstance().populate1(msg.getOrderStatusGroups()[0]);
        OrderStatusGroup41TestData.getInstance().populate2(msg.getOrderStatusGroups()[1]);
    }

    public void check(ListStatusMsg expected, ListStatusMsg actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getWaveNo(), actual.getWaveNo());
        assertEquals(expected.getNoRpts(), actual.getNoRpts());
        assertEquals(expected.getRptSeq(), actual.getRptSeq());
        assertEquals(expected.getNoOrders(), actual.getNoOrders());

        OrderStatusGroup41TestData.getInstance().check(expected.getOrderStatusGroups()[0], actual.getOrderStatusGroups()[0]);
        OrderStatusGroup41TestData.getInstance().check(expected.getOrderStatusGroups()[1], actual.getOrderStatusGroups()[1]);
    }
}
