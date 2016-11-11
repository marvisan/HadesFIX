/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg43TestData.java
 *
 * $Id: MarketDataSnapshotMsg43TestData.java,v 1.1 2009-12-03 11:19:12 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataSnapshotMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.group.impl.v43.MDFullGroup43TestData;

/**
 * Test utility for MarketDataSnapshotMsg message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataSnapshotMsg43TestData extends MsgTest {

    private static final MarketDataSnapshotMsg43TestData INSTANCE;

    static {
        INSTANCE = new MarketDataSnapshotMsg43TestData();
    }

    public static MarketDataSnapshotMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataSnapshotMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setMdReqID("AAA564567");
        Instrument43TestData.getInstance().populate(msg.getInstrument());
        msg.setFinancialStatus("fin stat 1");
        msg.setCorporateAction("corp act");
        msg.setTotalVolumeTraded(new Double(20.2));
        msg.setTotalVolumeTradedDate(new Date());
        msg.setTotalVolumeTradedTime(new Date());
        msg.setNetChgPrevDay(new Double(22.22));

        // MDFullGroup
        msg.setNoMDEntries(new Integer(2));
        MDFullGroup43TestData.getInstance().populate1(msg.getMdFullGroups()[0]);
        MDFullGroup43TestData.getInstance().populate2(msg.getMdFullGroups()[1]);
    }

    public void check(MarketDataSnapshotMsg expected, MarketDataSnapshotMsg actual) throws Exception {
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        assertEquals(expected.getFinancialStatus(), actual.getFinancialStatus());
        assertEquals(expected.getCorporateAction(), actual.getCorporateAction());
        assertEquals(expected.getTotalVolumeTraded().doubleValue(), actual.getTotalVolumeTraded().doubleValue(), 0.001);
        assertUTCDateEquals(expected.getTotalVolumeTradedDate(), actual.getTotalVolumeTradedDate());
        assertUTCTimeEquals(expected.getTotalVolumeTradedTime(), actual.getTotalVolumeTradedTime(), false);
        // MDFullGroup
        assertEquals(expected.getNoMDEntries(), actual.getNoMDEntries());
        MDFullGroup43TestData.getInstance().check(expected.getMdFullGroups()[0], actual.getMdFullGroups()[0]);
        MDFullGroup43TestData.getInstance().check(expected.getMdFullGroups()[1], actual.getMdFullGroups()[1]);
    }
}
