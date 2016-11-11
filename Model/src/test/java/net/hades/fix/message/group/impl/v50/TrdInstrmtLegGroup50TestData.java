/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdInstrmtLegGroup50TestData.java
 *
 * $Id: TrdInstrmtLegGroup50TestData.java,v 1.2 2011-10-29 09:42:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.group.TrdInstrmtLegGroup;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for TrdInstrmtLegGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class TrdInstrmtLegGroup50TestData extends MsgTest {

    private static final TrdInstrmtLegGroup50TestData INSTANCE;

    static {
        INSTANCE = new TrdInstrmtLegGroup50TestData();
    }

    public static TrdInstrmtLegGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TrdInstrmtLegGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(grp.getInstrumentLeg());
        
        grp.setLegQty(34.5d);
        grp.setLegSwapType(LegSwapType.ParForPar);
        grp.setLegReportID("LEG_REP_222");
        
        grp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(grp.getLegStipulations());
        
        grp.setLegPositionEffect(PositionEffect.Close);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Uncovered);
        
        grp.setNestedParties();
        NestedParties50TestData.getInstance().populate(grp.getNestedParties());
        
        grp.setLegRefID("LEG_REF_111");
        grp.setLegPrice(256.3d);
        grp.setLegSettlType(SettlType.Cash.getValue());
        cal.set(2011, 8, 11, 9, 17, 50);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(357.55d);
        grp.setLegSettlCurrency(Currency.UnitedStatesDollar);
        grp.setLegLastForwardPoints(44.55d);
        grp.setLegCalculatedCcyLastQty(55.66d);
        grp.setLegGrossTradeAmt(22.33d);
    }

    public void populate2(TrdInstrmtLegGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(grp.getInstrumentLeg());
        
        grp.setLegQty(44.5d);
        grp.setLegSwapType(LegSwapType.Proceeds);
        grp.setLegReportID("LEG_REP_333");
        
        grp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(grp.getLegStipulations());
        
        grp.setLegPositionEffect(PositionEffect.Open);
        grp.setLegCoveredOrUncovered(CoveredOrUncovered.Covered);
        
        grp.setNestedParties();
        NestedParties50TestData.getInstance().populate(grp.getNestedParties());
        
        grp.setLegRefID("LEG_REF_222");
        grp.setLegPrice(226.3d);
        grp.setLegSettlType(SettlType.Cash.getValue());
        cal.set(2011, 8, 12, 9, 17, 50);
        grp.setLegSettlDate(cal.getTime());
        grp.setLegLastPx(351.55d);
        grp.setLegSettlCurrency(Currency.AustralianDollar);
        grp.setLegLastForwardPoints(44.22);
        grp.setLegCalculatedCcyLastQty(55.33);
        grp.setLegGrossTradeAmt(22.44);
    }

    public void check(TrdInstrmtLegGroup expected, TrdInstrmtLegGroup actual) throws Exception {
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
                
        assertEquals(expected.getLegQty(), actual.getLegQty());
        assertEquals(expected.getLegSwapType(), actual.getLegSwapType());
        assertEquals(expected.getLegReportID(), actual.getLegReportID());
        
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        
        assertEquals(expected.getLegPositionEffect(), actual.getLegPositionEffect());
        assertEquals(expected.getLegCoveredOrUncovered(), actual.getLegCoveredOrUncovered());
        
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
        
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
