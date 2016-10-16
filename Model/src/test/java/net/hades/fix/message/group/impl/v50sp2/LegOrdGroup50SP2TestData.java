/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegOrdGroup50SP2TestData.java
 *
 * $Id: LegOrdGroup50SP2TestData.java,v 1.2 2011-10-29 09:42:23 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.NestedParties50SP2TestData;
import net.hades.fix.message.group.LegOrdGroup;
import net.hades.fix.message.group.impl.v50sp1.LegAllocGroup50SP1TestData;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegOrdGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class LegOrdGroup50SP2TestData extends MsgTest {

    private static final LegOrdGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new LegOrdGroup50SP2TestData();
    }

    public static LegOrdGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(LegOrdGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        group.setInstrumentLeg();
        InstrumentLeg50SP2TestData.getInstance().populate1(group.getInstrumentLeg());
        
        group.setLegQty(34.55d);
        group.setLegSwapType(LegSwapType.ParForPar);
        
        group.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(group.getLegStipulations());
        
        group.setNoLegAllocs(2);
        LegAllocGroup50SP1TestData.getInstance().populate1(group.getLegAllocGroups()[0]);
        LegAllocGroup50SP1TestData.getInstance().populate2(group.getLegAllocGroups()[1]);
        
        group.setLegAllocID("ALLOC_1");
        group.setLegPositionEffect(PositionEffect.Close);
        group.setLegCoveredOrUncovered(CoveredOrUncovered.Uncovered);

        group.setNestedParties();
        NestedParties50TestData.getInstance().populate(group.getNestedParties());

        group.setLegRefID("LEG_REF_1");
        group.setLegSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 13, 13);
        group.setLegSettlDate(cal.getTime());
        group.setLegSettlCurrency(Currency.UnitedStatesDollar);
        group.setLegOrderQty(44.33d);
        group.setLegVolatility(23.3d);
        group.setLegDividendYield(12.55);
        group.setLegCurrencyRatio(15.5d);
        group.setLegExecInst("1");
    }

    public void populate2(LegOrdGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        group.setInstrumentLeg();
        InstrumentLeg50SP2TestData.getInstance().populate2(group.getInstrumentLeg());
        
        group.setLegQty(34.66d);
        group.setLegSwapType(LegSwapType.Proceeds);
        
        group.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(group.getLegStipulations());
        
        group.setNoLegAllocs(2);
        LegAllocGroup50SP1TestData.getInstance().populate1(group.getLegAllocGroups()[0]);
        LegAllocGroup50SP1TestData.getInstance().populate2(group.getLegAllocGroups()[1]);
        
        group.setLegAllocID("ALLOC_2");
        group.setLegPositionEffect(PositionEffect.Open);
        group.setLegCoveredOrUncovered(CoveredOrUncovered.Covered);

        group.setNestedParties();
        NestedParties50SP2TestData.getInstance().populate(group.getNestedParties());

        group.setLegRefID("LEG_REF_2");
        group.setLegSettlType(SettlType.NextDay.getValue());
        cal.set(2011, 3, 12, 12, 13, 13);
        group.setLegSettlDate(cal.getTime());
        group.setLegSettlCurrency(Currency.AustralianDollar);
        group.setLegOrderQty(11.33d);
        group.setLegVolatility(13.3d);
        group.setLegDividendYield(22.55);
        group.setLegCurrencyRatio(25.5d);
        group.setLegExecInst("2");
    }

    public void check(LegOrdGroup expected, LegOrdGroup actual) throws Exception {
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        
        assertEquals(expected.getLegQty(), actual.getLegQty());
        assertEquals(expected.getLegSwapType(), actual.getLegSwapType());
        
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        
        assertEquals(expected.getNoLegAllocs(), actual.getNoLegAllocs());
        LegAllocGroup50SP1TestData.getInstance().check(expected.getLegAllocGroups()[0], actual.getLegAllocGroups()[0]);
        LegAllocGroup50SP1TestData.getInstance().check(expected.getLegAllocGroups()[1], actual.getLegAllocGroups()[1]);
        
        assertEquals(expected.getLegAllocID(), actual.getLegAllocID());
        assertEquals(expected.getLegPositionEffect(), actual.getLegPositionEffect());
        assertEquals(expected.getLegCoveredOrUncovered(), actual.getLegCoveredOrUncovered());

        NestedParties50SP2TestData.getInstance().check(expected.getNestedParties(), expected.getNestedParties());

        assertEquals(expected.getLegRefID(), actual.getLegRefID());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        assertEquals(expected.getLegSettlCurrency(), actual.getLegSettlCurrency());
        assertEquals(expected.getLegOrderQty(), actual.getLegOrderQty());
        assertEquals(expected.getLegVolatility(), actual.getLegVolatility());
        assertEquals(expected.getLegDividendYield(), actual.getLegDividendYield());
        assertEquals(expected.getLegCurrencyRatio(), actual.getLegCurrencyRatio());
        assertEquals(expected.getLegExecInst(), actual.getLegExecInst());
    }
}
