/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdInstrmtLegGroup44TestData.java
 *
 * $Id: TrdInstrmtLegGroup44TestData.java,v 1.1 2011-10-25 08:29:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.LegStipulations44TestData;
import net.hades.fix.message.comp.impl.v44.NestedParties44TestData;
import net.hades.fix.message.group.TrdInstrmtLegGroup;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for TrdInstrmtLegGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class TrdInstrmtLegGroup44TestData extends MsgTest {

    private static final TrdInstrmtLegGroup44TestData INSTANCE;

    static {
        INSTANCE = new TrdInstrmtLegGroup44TestData();
    }

    public static TrdInstrmtLegGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TrdInstrmtLegGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setInstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(grp.getInstrumentLeg());
        
        grp.setLegQty(34.5d);
        grp.setLegSwapType(LegSwapType.ParForPar);
        
        grp.setLegStipulations();
        LegStipulations44TestData.getInstance().populate(grp.getLegStipulations());
        
        grp.setLegPositionEffect(PositionEffect.Close);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Uncovered);
        
        grp.setNestedParties();
        NestedParties44TestData.getInstance().populate(grp.getNestedParties());
        
        grp.setLegRefID("LEG_REF_111");
        grp.setLegPrice(256.3d);
        grp.setLegSettlType(SettlType.Cash.getValue());
        cal.set(2011, 8, 11, 9, 17, 50);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(357.55d);
    }

    public void populate2(TrdInstrmtLegGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setInstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(grp.getInstrumentLeg());
        
        grp.setLegQty(44.5d);
        grp.setLegSwapType(LegSwapType.Proceeds);
        
        grp.setLegStipulations();
        LegStipulations44TestData.getInstance().populate(grp.getLegStipulations());
        
        grp.setLegPositionEffect(PositionEffect.Open);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Covered);
        
        grp.setNestedParties();
        NestedParties44TestData.getInstance().populate(grp.getNestedParties());
        
        grp.setLegRefID("LEG_REF_222");
        grp.setLegPrice(226.3d);
        grp.setLegSettlType(SettlType.Cash.getValue());
        cal.set(2011, 8, 12, 9, 17, 50);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(351.55d);
    }

    public void check(TrdInstrmtLegGroup expected, TrdInstrmtLegGroup actual) throws Exception {
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
                
        assertEquals(expected.getLegQty(), actual.getLegQty());
        assertEquals(expected.getLegSwapType(), actual.getLegSwapType());
        
        LegStipulations44TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        
        assertEquals(expected.getLegPositionEffect(), actual.getLegPositionEffect());
        assertEquals(expected.getLegCoveredOrUncovered(), actual.getLegCoveredOrUncovered());
        
        NestedParties44TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
        
        assertEquals(expected.getLegRefID(), actual.getLegRefID());
        assertEquals(expected.getLegPrice(), actual.getLegPrice());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        assertEquals(expected.getLegLastPx(), actual.getLegLastPx());
    }
}
