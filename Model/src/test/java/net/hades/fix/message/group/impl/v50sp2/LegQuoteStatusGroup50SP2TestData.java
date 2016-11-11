/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegQuoteStatusGroup50SP2TestData.java
 *
 * $Id: LegQuoteStatusGroup50SP2TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.NestedParties50SP2TestData;
import net.hades.fix.message.group.LegQuoteStatusGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegQuoteSymbolGroup50SP2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class LegQuoteStatusGroup50SP2TestData extends MsgTest {

    private static final LegQuoteStatusGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new LegQuoteStatusGroup50SP2TestData();
    }

    public static LegQuoteStatusGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(LegQuoteStatusGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg50SP2TestData.getInstance().populate1(comp.getInstrumentLeg());

        comp.setLegQty(new Double(2.9));
        comp.setLegOrderQty(new Double(3.1));
        comp.setLegSwapType(LegSwapType.ModifiedDuration);
        comp.setLegSettlType(SettlType.Future.getValue());
        comp.setLegSettlDate(new Date());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties50SP2TestData.getInstance().populate(comp.getNestedParties());
    }

    public void populate2(LegQuoteStatusGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg50SP2TestData.getInstance().populate1(comp.getInstrumentLeg());

        comp.setLegQty(new Double(2.4));
        comp.setLegOrderQty(new Double(3.2));
        comp.setLegSwapType(LegSwapType.ParForPar);
        comp.setLegSettlType(SettlType.Cash.getValue());
        comp.setLegSettlDate(new Date());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties50SP2TestData.getInstance().populate(comp.getNestedParties());
    }

    public void check(LegQuoteStatusGroup expected, LegQuoteStatusGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegQty().doubleValue(), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getLegOrderQty().doubleValue(), actual.getLegOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getLegSwapType().getValue());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        // LegStipulations
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties50SP2TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
    }
}
