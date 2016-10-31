/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestLegGroup50SP1TestData.java
 *
 * $Id: QuoteRequestLegGroup50SP1TestData.java,v 1.1 2009-07-06 03:19:11 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.LegBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1TestData;
import net.hades.fix.message.group.QuoteRequestLegGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegQuoteRequestSymbolGroup50SP1 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class QuoteRequestLegGroup50SP1TestData extends MsgTest {

    private static final QuoteRequestLegGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestLegGroup50SP1TestData();
    }

    public static QuoteRequestLegGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(QuoteRequestLegGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg50SP1TestData.getInstance().populate1(comp.getInstrumentLeg());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties50TestData.getInstance().populate(comp.getNestedParties());
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(comp.getLegBenchmarkCurveData());

        comp.setLegQty(new Double(2.9));
        comp.setLegOrderQty(new Double(12.55));
        comp.setLegSwapType(LegSwapType.ModifiedDuration);
        comp.setLegSettlType(SettlType.Future.getValue());
        comp.setLegSettlDate(new Date());
        comp.setLegRefID("4364389653");
    }

    public void populate2(QuoteRequestLegGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg50SP1TestData.getInstance().populate2(comp.getInstrumentLeg());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties50TestData.getInstance().populate(comp.getNestedParties());
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(comp.getLegBenchmarkCurveData());

        comp.setLegQty(new Double(2.3));
        comp.setLegOrderQty(new Double(9.55));
        comp.setLegSwapType(LegSwapType.Proceeds);
        comp.setLegSettlType(SettlType.Regular.getValue());
        comp.setLegSettlDate(new Date());
        comp.setLegRefID("237348484");
    }

    public void check(QuoteRequestLegGroup expected, QuoteRequestLegGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        // LegStipulations
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
        // LegBenchmarkCurveData
        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getLegQty().doubleValue(), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getLegOrderQty().doubleValue(), actual.getLegOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getLegSwapType().getValue());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        assertEquals(expected.getLegRefID(), actual.getLegRefID());
    }
}
