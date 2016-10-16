/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderAllocGroup44TestData.java
 *
 * $Id: OrderAllocGroup44TestData.java,v 1.2 2011-09-09 08:05:25 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.NestedParties244TestData;
import net.hades.fix.message.group.OrderAllocGroup;

/**
 * Test utility for OrderAllocGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class OrderAllocGroup44TestData extends MsgTest {

    private static final OrderAllocGroup44TestData INSTANCE;

    static {
        INSTANCE = new OrderAllocGroup44TestData();
    }

    public static OrderAllocGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(OrderAllocGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD22333");
        grp.setOrderID("ORD8374444");
        grp.setSecondaryOrderID("SEC34433");
        grp.setSecondaryClOrdID("SCLORD88222");
        grp.setListID("LST729444");

        grp.setNestedParties2();
        NestedParties244TestData.getInstance().populate(grp.getNestedParties2());

        grp.setOrderQty(12.0d);
        grp.setOrderAvgPx(14.30d);
        grp.setOrderBookingQty(15.0d);
    }

    public void populate2(OrderAllocGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD22344");
        grp.setOrderID("ORD8374455");
        grp.setSecondaryOrderID("SEC34422");
        grp.setSecondaryClOrdID("SCLORD88333");
        grp.setListID("LST729455");
        
        grp.setNestedParties2();
        NestedParties244TestData.getInstance().populate(grp.getNestedParties2());

        grp.setOrderQty(14.0d);
        grp.setOrderAvgPx(15.30d);
        grp.setOrderBookingQty(16.0d);
    }

    public void check(OrderAllocGroup expected, OrderAllocGroup actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getListID(), actual.getListID());
        
        NestedParties244TestData.getInstance().check(expected.getNestedParties2(), actual.getNestedParties2());

        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getOrderAvgPx(), actual.getOrderAvgPx());
        assertEquals(expected.getOrderBookingQty(), actual.getOrderBookingQty());
    }
}
