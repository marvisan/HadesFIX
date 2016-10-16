/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataIncrRefreshMsg44TestData.java
 *
 * $Id: MarketDataIncrRefreshMsg44TestData.java,v 1.1 2010-01-14 09:07:10 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataIncrRefreshMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v44.MDIncGroup44TestData;

import net.hades.fix.message.type.ApplQueueResolution;

/**
 * Test utility for MarketDataIncrRefreshMsg message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataIncrRefreshMsg44TestData extends MsgTest {

    private static final MarketDataIncrRefreshMsg44TestData INSTANCE;

    static {
        INSTANCE = new MarketDataIncrRefreshMsg44TestData();
    }

    public static MarketDataIncrRefreshMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataIncrRefreshMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setMdReqID("AAA564567");
        // MDIncGroup
        msg.setNoMDEntries(new Integer(2));
        MDIncGroup44TestData.getInstance().populate1(msg.getMDIncGroups()[0]);
        MDIncGroup44TestData.getInstance().populate2(msg.getMDIncGroups()[1]);

        msg.setApplQueueDepth(3);
        msg.setApplQueueResolution(ApplQueueResolution.EndSession);
    }

    public void check(MarketDataIncrRefreshMsg expected, MarketDataIncrRefreshMsg actual) throws Exception {
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        // MDIncGroup
        assertEquals(expected.getNoMDEntries(), actual.getNoMDEntries());
        MDIncGroup44TestData.getInstance().check(expected.getMDIncGroups()[0], actual.getMDIncGroups()[0]);
        MDIncGroup44TestData.getInstance().check(expected.getMDIncGroups()[1], actual.getMDIncGroups()[1]);

        assertEquals(expected.getApplQueueDepth(), actual.getApplQueueDepth());
        assertEquals(expected.getApplQueueResolution(), actual.getApplQueueResolution());
    }
}
