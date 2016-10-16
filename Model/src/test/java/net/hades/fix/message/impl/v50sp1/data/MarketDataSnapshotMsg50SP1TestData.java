/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataSnapshotMsg50SP1TestData.java
 *
 * $Id: MarketDataSnapshotMsg50SP1TestData.java,v 1.1 2009-12-03 11:19:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataSnapshotMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp1.ApplicationSequenceControl50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.impl.v50sp1.MDFullGroup50SP1TestData;
import net.hades.fix.message.type.ApplQueueResolution;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.type.RoutingType;

/**
 * Test utility for MarketDataSnapshotMsg message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataSnapshotMsg50SP1TestData extends MsgTest {

    private static final MarketDataSnapshotMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new MarketDataSnapshotMsg50SP1TestData();
    }

    public static MarketDataSnapshotMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataSnapshotMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        // ApplicationSequenceControl
        msg.setApplicationSequenceControl();
        ApplicationSequenceControl50SP1TestData.getInstance().populate(msg.getApplicationSequenceControl());

        msg.setTotNumReports(new Integer(2));
        msg.setMdReqID("AAA564567");
        msg.setMdReportID(new Integer(123));
        msg.setClearingBusinessDate(new Date());
        msg.setMdBookType(MDBookType.TopOfBook);
        msg.setMdSubBookType(new Integer(1));
        msg.setMarketDepth(new Integer(3));
        msg.setRefreshIndicator(Boolean.TRUE);
        msg.setMdFeedType("Feed type");
        msg.setTradeDate(new Date());
        // Instrument
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());
        msg.setFinancialStatus("fin stat 1");
        msg.setCorporateAction("corp act");
        msg.setNetChgPrevDay(new Double(22.22));
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP1TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        // MDFullGroup
        msg.setNoMDEntries(new Integer(2));
        MDFullGroup50SP1TestData.getInstance().populate1(msg.getMdFullGroups()[0]);
        MDFullGroup50SP1TestData.getInstance().populate2(msg.getMdFullGroups()[1]);

        msg.setApplQueueDepth(new Integer(10));
        msg.setApplQueueResolution(ApplQueueResolution.QueueFlushed);
        // RoutingIDGroup
        msg.setNoRoutingIDs(new Integer(2));
        msg.getRoutingIDGroups()[0].setRoutingID("Routing ID 1");
        msg.getRoutingIDGroups()[0].setRoutingType(RoutingType.TargetFirm);
        msg.getRoutingIDGroups()[1].setRoutingID("Routing ID 2");
        msg.getRoutingIDGroups()[1].setRoutingType(RoutingType.BlockFirm);
    }

    public void check(MarketDataSnapshotMsg expected, MarketDataSnapshotMsg actual) throws Exception {
        // ApplicationSequenceControl
        ApplicationSequenceControl50SP1TestData.getInstance().check(expected.getApplicationSequenceControl(),
                expected.getApplicationSequenceControl());

        assertEquals(expected.getTotNumReports(), actual.getTotNumReports());
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        assertEquals(expected.getMdReportID(), actual.getMdReportID());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getMdBookType(), actual.getMdBookType());
        assertEquals(expected.getMdSubBookType(), actual.getMdSubBookType());
        assertEquals(expected.getMarketDepth(), actual.getMarketDepth());
        assertEquals(expected.getRefreshIndicator(), actual.getRefreshIndicator());

        assertEquals(expected.getMdFeedType(), actual.getMdFeedType());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        // Instrument
        Instrument50SP1TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        assertEquals(expected.getFinancialStatus(), actual.getFinancialStatus());
        assertEquals(expected.getCorporateAction(), actual.getCorporateAction());
        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
        // MDFullGroup
        assertEquals(expected.getNoMDEntries(), actual.getNoMDEntries());
        MDFullGroup50SP1TestData.getInstance().check(expected.getMdFullGroups()[0], actual.getMdFullGroups()[0]);
        MDFullGroup50SP1TestData.getInstance().check(expected.getMdFullGroups()[1], actual.getMdFullGroups()[1]);

        assertEquals(expected.getApplQueueDepth(), actual.getApplQueueDepth());
        assertEquals(expected.getApplQueueResolution(), actual.getApplQueueResolution());
        // RoutingIDGroup
        assertEquals(expected.getNoRoutingIDs(), actual.getNoRoutingIDs());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getRoutingIDGroups()[i].getRoutingID(), actual.getRoutingIDGroups()[i].getRoutingID());
            assertEquals(expected.getRoutingIDGroups()[i].getRoutingType(), actual.getRoutingIDGroups()[i].getRoutingType());
        }
    }
}
