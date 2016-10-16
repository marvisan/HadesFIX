/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegQuoteRequestSymbolGroup50SP1TestData.java
 *
 * $Id: LegQuoteGroup50SP1TestData.java,v 1.1 2009-07-06 03:19:11 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.LegBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1TestData;
import net.hades.fix.message.group.LegQuoteGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegQuoteSymbolGroup50SP1 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class LegQuoteGroup50SP1TestData extends MsgTest {

    private static final LegQuoteGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new LegQuoteGroup50SP1TestData();
    }

    public static LegQuoteGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(LegQuoteGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg50SP1TestData.getInstance().populate1(comp.getInstrumentLeg());

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
        NestedParties50TestData.getInstance().populate(comp.getNestedParties());

        comp.setLegPriceType(PriceType.FixedAmount);
        comp.setLegBidPx(new Double(12.34));
        comp.setLegOfferPx(new Double(23.44));
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(comp.getLegBenchmarkCurveData());

        comp.setLegRefID("LegRef123");
        comp.setLegBidForwardPoints(new Double(2.4));
        comp.setLegOfferForwardPoints(new Double(3.4));
    }

    public void populate2(LegQuoteGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg50SP1TestData.getInstance().populate1(comp.getInstrumentLeg());

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
        NestedParties50TestData.getInstance().populate(comp.getNestedParties());

        comp.setLegPriceType(PriceType.FixedAmount);
        comp.setLegBidPx(new Double(12.64));
        comp.setLegOfferPx(new Double(23.84));
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(comp.getLegBenchmarkCurveData());

        comp.setLegRefID("LegRef321");
        comp.setLegBidForwardPoints(new Double(3.4));
        comp.setLegOfferForwardPoints(new Double(4.4));
    }

    public void check(LegQuoteGroup expected, LegQuoteGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegQty().doubleValue(), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getLegOrderQty().doubleValue(), actual.getLegOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getLegSwapType().getValue());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        // LegStipulations
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());

        assertEquals(expected.getLegPriceType().getValue(), actual.getLegPriceType().getValue());
        assertEquals(expected.getLegBidPx().doubleValue(), actual.getLegBidPx().doubleValue(), 0.001);
        assertEquals(expected.getLegOfferPx().doubleValue(), actual.getLegOfferPx().doubleValue(), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getLegRefID(), actual.getLegRefID());
        assertEquals(expected.getLegBidForwardPoints().doubleValue(), actual.getLegBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getLegOfferForwardPoints().doubleValue(), actual.getLegOfferForwardPoints().doubleValue(), 0.001);
    }
}
