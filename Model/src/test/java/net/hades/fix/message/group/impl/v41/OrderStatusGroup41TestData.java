/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderStatusGroup41TestData.java
 *
 * $Id: OrderStatusGroup41TestData.java,v 1.1 2011-02-04 09:58:24 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v41;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.OrderStatusGroup;

/**
 * Test utility for OrderStatusGroup41 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class OrderStatusGroup41TestData extends MsgTest {

    private static final OrderStatusGroup41TestData INSTANCE;

    static {
        INSTANCE = new OrderStatusGroup41TestData();
    }

    public static OrderStatusGroup41TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(OrderStatusGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD53773");
        grp.setCumQty(23.33d);
        grp.setLeavesQty(25.66d);
        grp.setCxlQty(23.40d);
        grp.setAvgPx(22.33d);
    }

    public void populate2(OrderStatusGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD53543");
        grp.setCumQty(33.33d);
        grp.setLeavesQty(37.66d);
        grp.setCxlQty(34.40d);
        grp.setAvgPx(32.33d);
    }

    public void check(OrderStatusGroup expected, OrderStatusGroup actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getCumQty(), actual.getCumQty());
        assertEquals(expected.getLeavesQty(), actual.getLeavesQty());
        assertEquals(expected.getCxlQty(), actual.getCxlQty());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
    }
}
