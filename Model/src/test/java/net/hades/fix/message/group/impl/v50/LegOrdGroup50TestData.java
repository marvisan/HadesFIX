/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegOrdGroup50TestData.java
 *
 * $Id: LegOrdGroup50TestData.java,v 1.1 2011-09-09 08:05:25 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.LegStipulations44TestData;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.group.LegOrdGroup;
import net.hades.fix.message.group.impl.v44.LegAllocGroup44TestData;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegOrdGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class LegOrdGroup50TestData extends MsgTest {

    private static final LegOrdGroup50TestData INSTANCE;

    static {
        INSTANCE = new LegOrdGroup50TestData();
    }

    public static LegOrdGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(LegOrdGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        group.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(group.getInstrumentLeg());
        
        group.setLegOptionRatio(33.88d);
        group.setLegQty(34.55d);
        group.setLegSwapType(LegSwapType.ParForPar);
        
        group.setLegStipulations();
        LegStipulations44TestData.getInstance().populate(group.getLegStipulations());
        
        group.setNoLegAllocs(2);
        LegAllocGroup44TestData.getInstance().populate1(group.getLegAllocGroups()[0]);
        LegAllocGroup44TestData.getInstance().populate2(group.getLegAllocGroups()[1]);
        
        group.setLegPositionEffect(PositionEffect.Close);
        group.setLegCoveredOrUncovered(CoveredOrUncovered.Uncovered);

        group.setNestedParties();
        NestedParties50TestData.getInstance().populate(group.getNestedParties());

        group.setLegRefID("LEG_REF_1");
        group.setLegPrice(33.66d);
        group.setLegSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 13, 13);
        group.setLegSettlDate(cal.getTime());
        group.setLegOrderQty(44.33d);
    }

    public void populate2(LegOrdGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        group.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate2(group.getInstrumentLeg());
        
        group.setLegOptionRatio(44.88d);
        group.setLegQty(34.66d);
        group.setLegSwapType(LegSwapType.Proceeds);
        
        group.setLegStipulations();
        LegStipulations44TestData.getInstance().populate(group.getLegStipulations());
        
        group.setNoLegAllocs(2);
        LegAllocGroup44TestData.getInstance().populate1(group.getLegAllocGroups()[0]);
        LegAllocGroup44TestData.getInstance().populate2(group.getLegAllocGroups()[1]);
        
        group.setLegPositionEffect(PositionEffect.Open);
        group.setLegCoveredOrUncovered(CoveredOrUncovered.Covered);

        group.setNestedParties();
        NestedParties50TestData.getInstance().populate(group.getNestedParties());

        group.setLegRefID("LEG_REF_2");
        group.setLegPrice(22.66d);
        group.setLegSettlType(SettlType.NextDay.getValue());
        cal.set(2011, 3, 12, 12, 13, 13);
        group.setLegSettlDate(cal.getTime());
        group.setLegOrderQty(11.33d);
    }

    public void check(LegOrdGroup expected, LegOrdGroup actual) throws Exception {
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        
        assertEquals(expected.getLegOptionRatio(), actual.getLegOptionRatio());
        assertEquals(expected.getLegQty(), actual.getLegQty());
        assertEquals(expected.getLegSwapType(), actual.getLegSwapType());
        
        LegStipulations44TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        
        assertEquals(expected.getNoLegAllocs(), actual.getNoLegAllocs());
        LegAllocGroup44TestData.getInstance().check(expected.getLegAllocGroups()[0], actual.getLegAllocGroups()[0]);
        LegAllocGroup44TestData.getInstance().check(expected.getLegAllocGroups()[1], actual.getLegAllocGroups()[1]);
        
        assertEquals(expected.getLegPositionEffect(), actual.getLegPositionEffect());
        assertEquals(expected.getLegCoveredOrUncovered(), actual.getLegCoveredOrUncovered());

        NestedParties50TestData.getInstance().check(expected.getNestedParties(), expected.getNestedParties());

        assertEquals(expected.getLegRefID(), actual.getLegRefID());
        assertEquals(expected.getLegPrice(), actual.getLegPrice());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        assertEquals(expected.getLegOrderQty(), actual.getLegOrderQty());
    }
}
