/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderAllocGroup40TestData.java
 *
 * $Id: OrderAllocGroup40TestData.java,v 1.1 2011-02-16 11:24:34 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v40;

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
public class OrderAllocGroup40TestData extends MsgTest {

    private static final OrderAllocGroup40TestData INSTANCE;

    static {
        INSTANCE = new OrderAllocGroup40TestData();
    }

    public static OrderAllocGroup40TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(OrderAllocGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD22333");
        grp.setOrderID("ORD8374444");
        grp.setListID("LST729444");
        grp.setWaveNo("WAVE6666");
    }

    public void populate2(OrderAllocGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD22344");
        grp.setOrderID("ORD8374455");
        grp.setListID("LST729455");
        grp.setWaveNo("WAVE7777");
    }

    public void check(OrderAllocGroup expected, OrderAllocGroup actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getWaveNo(), actual.getWaveNo());
    }
}
