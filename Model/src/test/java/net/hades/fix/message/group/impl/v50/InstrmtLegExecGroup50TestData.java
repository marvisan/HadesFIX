/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtLegExecGroup50TestData.java
 *
 * $Id: InstrmtLegExecGroup50TestData.java,v 1.2 2011-10-29 09:42:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.LegStipulations44TestData;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.group.InstrmtLegExecGroup;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for InstrmtLegExecGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class InstrmtLegExecGroup50TestData extends MsgTest {

    private static final InstrmtLegExecGroup50TestData INSTANCE;

    static {
        INSTANCE = new InstrmtLegExecGroup50TestData();
    }

    public static InstrmtLegExecGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrmtLegExecGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(grp.getInstrumentLeg());

        grp.setLegQty(23.50d);
        grp.setLegSwapType(LegSwapType.Risk);

        grp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(grp.getLegStipulations());

        grp.setLegPositionEffect(PositionEffect.Close);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Uncovered);

        grp.setNestedParties();
        grp.getNestedParties().setNoNestedPartyIDs(2);
        NestedPartyGroup50TestData.getInstance().populate1(grp.getNestedParties().getNestedPartyIDGroups()[0]);
        NestedPartyGroup50TestData.getInstance().populate2(grp.getNestedParties().getNestedPartyIDGroups()[1]);

        grp.setLegRefID("REF2344444");
        grp.setLegPrice(22.77d);
        grp.setLegSettlType(SettlType.Cash.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 4, 15, 11, 12, 13);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(22.56d);
        grp.setLegSettlCurrency(Currency.UnitedStatesDollar);
        grp.setLegLastForwardPoints(34.66d);
        grp.setLegCalculatedCcyLastQty(55.44d);
        grp.setLegGrossTradeAmt(55.22d);
    }

    public void populate2(InstrmtLegExecGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate2(grp.getInstrumentLeg());

        grp.setLegQty(21.50d);
        grp.setLegSwapType(LegSwapType.ParForPar);

        grp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(grp.getLegStipulations());

        grp.setLegPositionEffect(PositionEffect.Open);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Covered);

        grp.setNestedParties();
        grp.getNestedParties().setNoNestedPartyIDs(2);
        NestedPartyGroup50TestData.getInstance().populate1(grp.getNestedParties().getNestedPartyIDGroups()[0]);
        NestedPartyGroup50TestData.getInstance().populate2(grp.getNestedParties().getNestedPartyIDGroups()[1]);

        grp.setLegRefID("REF2365777");
        grp.setLegPrice(22.88d);
        grp.setLegSettlType(SettlType.Future.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 4, 15, 4, 5, 6);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(22.77);
        grp.setLegSettlCurrency(Currency.AustralianDollar);
        grp.setLegLastForwardPoints(22.66d);
        grp.setLegCalculatedCcyLastQty(44.44d);
        grp.setLegGrossTradeAmt(77.22d);
    }

    public void check(InstrmtLegExecGroup expected, InstrmtLegExecGroup actual) throws Exception {
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegQty(), actual.getLegQty());
        assertEquals(expected.getLegSwapType(), actual.getLegSwapType());

        LegStipulations44TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());

        assertEquals(expected.getLegPositionEffect(), actual.getLegPositionEffect());
        assertEquals(expected.getLegCoveredOrUncovered(), actual.getLegCoveredOrUncovered());

        assertEquals(expected.getNestedParties().getNoNestedPartyIDs(), actual.getNestedParties().getNoNestedPartyIDs());
        NestedPartyGroup50TestData.getInstance().check(expected.getNestedParties().getNestedPartyIDGroups()[0],
                actual.getNestedParties().getNestedPartyIDGroups()[0]);
        NestedPartyGroup50TestData.getInstance().check(expected.getNestedParties().getNestedPartyIDGroups()[1],
                actual.getNestedParties().getNestedPartyIDGroups()[1]);

        assertEquals(expected.getLegRefID(), actual.getLegRefID());
        assertEquals(expected.getLegPrice(), actual.getLegPrice());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        assertEquals(expected.getLegLastPx(), actual.getLegLastPx());
        assertEquals(expected.getLegSettlCurrency(), actual.getLegSettlCurrency());
        assertEquals(expected.getLegLastForwardPoints(), actual.getLegLastForwardPoints());
        assertEquals(expected.getLegCalculatedCcyLastQty(), actual.getLegCalculatedCcyLastQty());
        assertEquals(expected.getLegGrossTradeAmt(), actual.getLegGrossTradeAmt());
    }
}
