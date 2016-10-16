/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataIncrRefreshMsg50TestData.java
 *
 * $Id: MarketDataIncrRefreshMsg50TestData.java,v 1.2 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataIncrRefreshMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v50.MDIncGroup50TestData;
import net.hades.fix.message.type.ApplQueueResolution;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.type.RoutingType;

/**
 * Test utility for MarketDataIncrRefreshMsg message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataIncrRefreshMsg50TestData extends MsgTest {

    private static final MarketDataIncrRefreshMsg50TestData INSTANCE;

    static {
        INSTANCE = new MarketDataIncrRefreshMsg50TestData();
    }

    public static MarketDataIncrRefreshMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataIncrRefreshMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setMdBookType(MDBookType.TopOfBook);
        msg.setMdFeedType("Regular");
        msg.setTradeDate(new Date());
        msg.setMdReqID("AAA564567");
        msg.setNoMDEntries(new Integer(2));
        MDIncGroup50TestData.getInstance().populate1(msg.getMDIncGroups()[0]);
        MDIncGroup50TestData.getInstance().populate2(msg.getMDIncGroups()[1]);

        msg.setApplQueueDepth(3);
        msg.setApplQueueResolution(ApplQueueResolution.EndSession);
        // RoutingIDGroup
        msg.setNoRoutingIDs(new Integer(2));
        msg.getRoutingIDGroups()[0].setRoutingID("route id 1");
        msg.getRoutingIDGroups()[0].setRoutingType(RoutingType.BlockFirm);
        msg.getRoutingIDGroups()[1].setRoutingID("route id 2");
        msg.getRoutingIDGroups()[1].setRoutingType(RoutingType.BlockList);
    }

    public void check(MarketDataIncrRefreshMsg expected, MarketDataIncrRefreshMsg actual) throws Exception {
        assertEquals(expected.getMdBookType(), actual.getMdBookType());
        assertEquals(expected.getMdFeedType(), actual.getMdFeedType());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        // MDIncGroup
        assertEquals(expected.getNoMDEntries(), actual.getNoMDEntries());
        MDIncGroup50TestData.getInstance().check(expected.getMDIncGroups()[0], actual.getMDIncGroups()[0]);
        MDIncGroup50TestData.getInstance().check(expected.getMDIncGroups()[1], actual.getMDIncGroups()[1]);

        assertEquals(expected.getApplQueueDepth(), actual.getApplQueueDepth());
        assertEquals(expected.getApplQueueResolution(), actual.getApplQueueResolution());
        // RoutingIDGroup 
        assertEquals(expected.getNoRoutingIDs().intValue(), actual.getNoRoutingIDs().intValue());
        for (int i = 0; i < expected.getNoRoutingIDs().intValue(); i++) {
            assertEquals(expected.getRoutingIDGroups()[i].getRoutingType().getValue(), actual.getRoutingIDGroups()[i].getRoutingType().getValue());
            assertEquals(expected.getRoutingIDGroups()[i].getRoutingID(), actual.getRoutingIDGroups()[i].getRoutingID());
        }
    }
}
