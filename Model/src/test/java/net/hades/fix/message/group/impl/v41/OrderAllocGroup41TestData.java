/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderAllocGroup40TestData.java
 *
 * $Id: OrderAllocGroup41TestData.java,v 1.1 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v41;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.OrderAllocGroup;

/**
 * Test utility for OrderAllocGroup40 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class OrderAllocGroup41TestData extends MsgTest {

    private static final OrderAllocGroup41TestData INSTANCE;

    static {
        INSTANCE = new OrderAllocGroup41TestData();
    }

    public static OrderAllocGroup41TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(OrderAllocGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD22333");
        grp.setOrderID("ORD8374444");
        grp.setSecondaryOrderID("SEC34433");
        grp.setListID("LST729444");
        grp.setWaveNo("WAVE6666");
    }

    public void populate2(OrderAllocGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD22344");
        grp.setOrderID("ORD8374455");
        grp.setSecondaryOrderID("SEC34422");
        grp.setListID("LST729455");
        grp.setWaveNo("WAVE7777");
    }

    public void check(OrderAllocGroup expected, OrderAllocGroup actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getWaveNo(), actual.getWaveNo());
    }
}
