/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg44TestData.java
 *
 * $Id: MarketDataSnapshotMsg44TestData.java,v 1.2 2010-01-14 09:07:10 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataSnapshotMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.impl.v44.MDFullGroup44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;

/**
 * Test utility for MarketDataSnapshotMsg message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataSnapshotMsg44TestData extends MsgTest {

    private static final MarketDataSnapshotMsg44TestData INSTANCE;

    static {
        INSTANCE = new MarketDataSnapshotMsg44TestData();
    }

    public static MarketDataSnapshotMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataSnapshotMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setMdReqID("AAA564567");
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        msg.setFinancialStatus("fin stat 1");
        msg.setCorporateAction("corp act");
        msg.setNetChgPrevDay(new Double(22.22));
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        // MDFullGroup
        msg.setNoMDEntries(new Integer(2));
        MDFullGroup44TestData.getInstance().populate1(msg.getMdFullGroups()[0]);
        MDFullGroup44TestData.getInstance().populate2(msg.getMdFullGroups()[1]);
    }

    public void check(MarketDataSnapshotMsg expected, MarketDataSnapshotMsg actual) throws Exception {
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        assertEquals(expected.getFinancialStatus(), actual.getFinancialStatus());
        assertEquals(expected.getCorporateAction(), actual.getCorporateAction());
        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
        // MDFullGroup
        assertEquals(expected.getNoMDEntries(), actual.getNoMDEntries());
        MDFullGroup44TestData.getInstance().check(expected.getMdFullGroups()[0], actual.getMdFullGroups()[0]);
        MDFullGroup44TestData.getInstance().check(expected.getMdFullGroups()[1], actual.getMdFullGroups()[1]);
    }
}
