/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataIncrRefreshMsg42TestData.java
 *
 * $Id: MarketDataIncrRefreshMsg42TestData.java,v 1.1 2009-12-15 09:59:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataIncrRefreshMsg;
import net.hades.fix.message.group.impl.v42.MDIncGroup42TestData;
import net.hades.fix.message.MsgTest;

/**
 * Test utility for MarketDataIncrRefreshMsg message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataIncrRefreshMsg42TestData extends MsgTest {

    private static final MarketDataIncrRefreshMsg42TestData INSTANCE;

    static {
        INSTANCE = new MarketDataIncrRefreshMsg42TestData();
    }

    public static MarketDataIncrRefreshMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataIncrRefreshMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setMdReqID("AAA564567");
        // MDIncGroup
        msg.setNoMDEntries(new Integer(2));
        MDIncGroup42TestData.getInstance().populate1(msg.getMDIncGroups()[0]);
        MDIncGroup42TestData.getInstance().populate2(msg.getMDIncGroups()[1]);
    }

    public void check(MarketDataIncrRefreshMsg expected, MarketDataIncrRefreshMsg actual) throws Exception {
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        // MDIncGroup
        assertEquals(expected.getNoMDEntries(), actual.getNoMDEntries());
        MDIncGroup42TestData.getInstance().check(expected.getMDIncGroups()[0], actual.getMDIncGroups()[0]);
        MDIncGroup42TestData.getInstance().check(expected.getMDIncGroups()[1], actual.getMDIncGroups()[1]);
    }
}
