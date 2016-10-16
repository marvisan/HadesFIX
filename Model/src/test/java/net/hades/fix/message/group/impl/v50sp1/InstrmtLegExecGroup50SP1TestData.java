/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtLegExecGroup50SP1TestData.java
 *
 * $Id: InstrmtLegExecGroup50SP1TestData.java,v 1.3 2011-10-29 09:42:24 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1TestData;
import net.hades.fix.message.comp.impl.v44.NestedParties344TestData;
import net.hades.fix.message.group.InstrmtLegExecGroup;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for InstrmtLegExecGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class InstrmtLegExecGroup50SP1TestData extends MsgTest {

    private static final InstrmtLegExecGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new InstrmtLegExecGroup50SP1TestData();
    }

    public static InstrmtLegExecGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrmtLegExecGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg50SP1TestData.getInstance().populate1(grp.getInstrumentLeg());

        grp.setLegQty(23.50d);
        grp.setLegOrderQty(23.0d);
        grp.setLegSwapType(LegSwapType.Risk);

        grp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(grp.getLegStipulations());

        grp.setLegAllocID("ALL123");

        grp.setNoLegAllocs(2);
        LegAllocGroup50SP1TestData.getInstance().populate1(grp.getLegAllocGroups()[0]);
        LegAllocGroup50SP1TestData.getInstance().populate2(grp.getLegAllocGroups()[1]);

        grp.setLegPositionEffect(PositionEffect.Close);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Uncovered);

        grp.setNestedParties3();
        NestedParties344TestData.getInstance().populate(grp.getNestedParties3());

        grp.setLegRefID("REF2344444");
        grp.setLegSettlType(SettlType.Cash.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 4, 15, 11, 12, 13);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(22.56d);
        grp.setLegSettlCurrency(Currency.UnitedStatesDollar);
        grp.setLegLastForwardPoints(34.66d);
        grp.setLegCalculatedCcyLastQty(55.44d);
        grp.setLegGrossTradeAmt(55.22d);
        grp.setLegVolatility(1.05d);
        grp.setLegDividendYield(12.33d);
        grp.setLegCurrencyRatio(10.3d);
        grp.setLegExecInst(ExecInst.AON.getValue());
        grp.setLegLastQty(25.0d);
    }

    public void populate2(InstrmtLegExecGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg50SP1TestData.getInstance().populate2(grp.getInstrumentLeg());

        grp.setLegQty(21.50d);
        grp.setLegOrderQty(25.0d);
        grp.setLegSwapType(LegSwapType.ParForPar);

        grp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(grp.getLegStipulations());

        grp.setLegAllocID("ALL321");

        grp.setNoLegAllocs(2);
        LegAllocGroup50SP1TestData.getInstance().populate1(grp.getLegAllocGroups()[0]);
        LegAllocGroup50SP1TestData.getInstance().populate2(grp.getLegAllocGroups()[1]);

        grp.setLegPositionEffect(PositionEffect.Open);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Covered);

        grp.setNestedParties3();
        NestedParties344TestData.getInstance().populate(grp.getNestedParties3());

        grp.setLegRefID("REF2365777");
        grp.setLegSettlType(SettlType.Future.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 4, 15, 4, 5, 6);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(22.77);
        grp.setLegSettlCurrency(Currency.AustralianDollar);
        grp.setLegLastForwardPoints(22.66d);
        grp.setLegCalculatedCcyLastQty(44.44d);
        grp.setLegGrossTradeAmt(77.22d);
        grp.setLegVolatility(1.2d);
        grp.setLegDividendYield(22.33d);
        grp.setLegCurrencyRatio(16.3d);
        grp.setLegExecInst(ExecInst.DNR.getValue());
        grp.setLegLastQty(15.0d);
    }

    public void check(InstrmtLegExecGroup expected, InstrmtLegExecGroup actual) throws Exception {
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegQty(), actual.getLegQty());
        assertEquals(expected.getLegOrderQty(), actual.getLegOrderQty());
        assertEquals(expected.getLegSwapType(), actual.getLegSwapType());

        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());

        assertEquals(expected.getLegAllocID(), actual.getLegAllocID());

        assertEquals(expected.getNoLegAllocs(), actual.getNoLegAllocs());
        LegAllocGroup50SP1TestData.getInstance().check(expected.getLegAllocGroups()[0], actual.getLegAllocGroups()[0]);
        LegAllocGroup50SP1TestData.getInstance().check(expected.getLegAllocGroups()[1], actual.getLegAllocGroups()[1]);

        assertEquals(expected.getLegPositionEffect(), actual.getLegPositionEffect());
        assertEquals(expected.getLegCoveredOrUncovered(), actual.getLegCoveredOrUncovered());

        NestedParties344TestData.getInstance().check(expected.getNestedParties3(), actual.getNestedParties3());

        assertEquals(expected.getLegRefID(), actual.getLegRefID());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        assertEquals(expected.getLegLastPx(), actual.getLegLastPx());
        assertEquals(expected.getLegSettlCurrency(), actual.getLegSettlCurrency());
        assertEquals(expected.getLegLastForwardPoints(), actual.getLegLastForwardPoints());
        assertEquals(expected.getLegCalculatedCcyLastQty(), actual.getLegCalculatedCcyLastQty());
        assertEquals(expected.getLegGrossTradeAmt(), actual.getLegGrossTradeAmt());
        assertEquals(expected.getLegVolatility(), actual.getLegVolatility());
        assertEquals(expected.getLegDividendYield(), actual.getLegDividendYield());
        assertEquals(expected.getLegCurrencyRatio(), actual.getLegCurrencyRatio());
        assertEquals(expected.getLegExecInst(), actual.getLegExecInst());
        assertEquals(expected.getLegLastQty(), actual.getLegLastQty());
    }
}
