/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtLegExecGroup43TestData.java
 *
 * $Id: InstrmtLegExecGroup43TestData.java,v 1.1 2011-01-12 11:33:57 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.InstrumentLeg43TestData;
import net.hades.fix.message.group.InstrmtLegExecGroup;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for InstrmtLegExecGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class InstrmtLegExecGroup43TestData extends MsgTest {

    private static final InstrmtLegExecGroup43TestData INSTANCE;

    static {
        INSTANCE = new InstrmtLegExecGroup43TestData();
    }

    public static InstrmtLegExecGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrmtLegExecGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg43TestData.getInstance().populate1(grp.getInstrumentLeg());

        grp.setLegPositionEffect(PositionEffect.Close);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Uncovered);

        grp.setNestedParties();
        grp.getNestedParties().setNoNestedPartyIDs(2);
        NestedPartyGroup43TestData.getInstance().populate1(grp.getNestedParties().getNestedPartyIDGroups()[0]);
        NestedPartyGroup43TestData.getInstance().populate2(grp.getNestedParties().getNestedPartyIDGroups()[1]);

        grp.setLegRefID("REF2344444");
        grp.setLegPrice(22.77d);
        grp.setLegSettlType(SettlType.Cash.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 4, 15, 11, 12, 13);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(22.56d);
    }

    public void populate2(InstrmtLegExecGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg43TestData.getInstance().populate2(grp.getInstrumentLeg());

        grp.setLegPositionEffect(PositionEffect.Open);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Covered);

        grp.setNestedParties();
        grp.getNestedParties().setNoNestedPartyIDs(2);
        NestedPartyGroup43TestData.getInstance().populate1(grp.getNestedParties().getNestedPartyIDGroups()[0]);
        NestedPartyGroup43TestData.getInstance().populate2(grp.getNestedParties().getNestedPartyIDGroups()[1]);

        grp.setLegRefID("REF2365777");
        grp.setLegPrice(22.88d);
        grp.setLegSettlType(SettlType.Future.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 4, 15, 4, 5, 6);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(22.77);
    }

    public void check(InstrmtLegExecGroup expected, InstrmtLegExecGroup actual) throws Exception {
        InstrumentLeg43TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegPositionEffect(), actual.getLegPositionEffect());
        assertEquals(expected.getLegCoveredOrUncovered(), actual.getLegCoveredOrUncovered());

        assertEquals(expected.getNestedParties().getNoNestedPartyIDs(), actual.getNestedParties().getNoNestedPartyIDs());
        NestedPartyGroup43TestData.getInstance().check(expected.getNestedParties().getNestedPartyIDGroups()[0],
                actual.getNestedParties().getNestedPartyIDGroups()[0]);
        NestedPartyGroup43TestData.getInstance().check(expected.getNestedParties().getNestedPartyIDGroups()[1],
                actual.getNestedParties().getNestedPartyIDGroups()[1]);

        assertEquals(expected.getLegRefID(), actual.getLegRefID());
        assertEquals(expected.getLegPrice(), actual.getLegPrice());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        assertEquals(expected.getLegLastPx(), actual.getLegLastPx());
    }
}
