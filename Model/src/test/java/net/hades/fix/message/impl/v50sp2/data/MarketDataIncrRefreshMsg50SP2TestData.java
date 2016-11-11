/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataIncrRefreshMsg50SP2TestData.java
 *
 * $Id: MarketDataIncrRefreshMsg50SP2TestData.java,v 1.1 2010-01-14 09:07:10 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataIncrRefreshMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp2.ApplicationSequenceControl50SP2TestData;
import net.hades.fix.message.group.impl.v50sp2.MDIncGroup50SP2TestData;
import net.hades.fix.message.type.ApplQueueResolution;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.type.RoutingType;

/**
 * Test utility for MarketDataIncrRefreshMsg message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataIncrRefreshMsg50SP2TestData extends MsgTest {

    private static final MarketDataIncrRefreshMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new MarketDataIncrRefreshMsg50SP2TestData();
    }

    public static MarketDataIncrRefreshMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataIncrRefreshMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        // ApplicationSequenceControl
        msg.setApplicationSequenceControl();
        ApplicationSequenceControl50SP2TestData.getInstance().populate(msg.getApplicationSequenceControl());
        msg.setMdBookType(MDBookType.TopOfBook);
        msg.setMdFeedType("Regular");
        msg.setTradeDate(new Date());
        msg.setMdReqID("AAA564567");
        msg.setNoMDEntries(new Integer(2));
        MDIncGroup50SP2TestData.getInstance().populate1(msg.getMDIncGroups()[0]);
        MDIncGroup50SP2TestData.getInstance().populate2(msg.getMDIncGroups()[1]);

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
        // ApplicationSequenceControl
        ApplicationSequenceControl50SP2TestData.getInstance().check(expected.getApplicationSequenceControl(),
                expected.getApplicationSequenceControl());
        assertEquals(expected.getMdBookType(), actual.getMdBookType());
        assertEquals(expected.getMdFeedType(), actual.getMdFeedType());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        // MDIncGroup
        assertEquals(expected.getNoMDEntries(), actual.getNoMDEntries());
        MDIncGroup50SP2TestData.getInstance().check(expected.getMDIncGroups()[0], actual.getMDIncGroups()[0]);
        MDIncGroup50SP2TestData.getInstance().check(expected.getMDIncGroups()[1], actual.getMDIncGroups()[1]);

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
