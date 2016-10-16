/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStrikePriceMsg42TestData.java
 *
 * $Id: ListStrikePriceMsg42TestData.java,v 1.1 2011-04-15 04:37:42 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListStrikePriceMsg;
import net.hades.fix.message.group.impl.v42.InstrmtStrikePriceGroup42TestData;

/**
 * Test utility for ListStrikePriceMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ListStrikePriceMsg42TestData extends MsgTest {

    private static final ListStrikePriceMsg42TestData INSTANCE;

    static {
        INSTANCE = new ListStrikePriceMsg42TestData();
    }

    public static ListStrikePriceMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ListStrikePriceMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setListID("LST564567");
        msg.setTotNoStrikes(3);
        msg.setNoStrikes(2);
        InstrmtStrikePriceGroup42TestData.getInstance().populate1(msg.getInstrmtStrikePriceGroups()[0]);
        InstrmtStrikePriceGroup42TestData.getInstance().populate2(msg.getInstrmtStrikePriceGroups()[1]);
    }

    public void check(ListStrikePriceMsg expected, ListStrikePriceMsg actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getTotNoStrikes(), actual.getTotNoStrikes());

        assertEquals(expected.getNoStrikes(), actual.getNoStrikes());
        InstrmtStrikePriceGroup42TestData.getInstance().check(expected.getInstrmtStrikePriceGroups()[0], actual.getInstrmtStrikePriceGroups()[0]);
        InstrmtStrikePriceGroup42TestData.getInstance().check(expected.getInstrmtStrikePriceGroups()[1], actual.getInstrmtStrikePriceGroups()[1]);
    }
}
