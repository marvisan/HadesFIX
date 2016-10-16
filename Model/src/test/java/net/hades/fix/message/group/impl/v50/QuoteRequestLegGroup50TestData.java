/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegQuoteSymbolGroup50TestData.java
 *
 * $Id: QuoteRequestLegGroup50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.LegBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.group.QuoteRequestLegGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegQuoteSymbolGroup44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class QuoteRequestLegGroup50TestData extends MsgTest {

    private static final QuoteRequestLegGroup50TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestLegGroup50TestData();
    }

    public static QuoteRequestLegGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix50.QuoteRequest.NoRelatedSym.NoLegs comp) throws Exception {
        // InstrumentLeg
        quickfix.fix50.component.InstrumentLeg ileg = new quickfix.fix50.component.InstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(ileg);
        comp.set(ileg);
        // LegStipulations
        quickfix.fix50.component.LegStipulations legstip = new quickfix.fix50.component.LegStipulations();
        LegStipulations50TestData.getInstance().populate(legstip);
        comp.set(legstip);
        // NestedParties
        quickfix.fix50.component.NestedParties nestpart = new quickfix.fix50.component.NestedParties();
        NestedParties50TestData.getInstance().populate(nestpart);
        comp.set(nestpart);
        // LegBenchmarkCurveData
        quickfix.fix50.component.LegBenchmarkCurveData legbench = new quickfix.fix50.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(legbench);
        comp.set(legbench);

        comp.setDouble(quickfix.field.LegOptionRatio.FIELD, 1.4);
        comp.setDouble(quickfix.field.LegPrice.FIELD, 132.55);
        comp.setDouble(quickfix.field.LegQty.FIELD, 2.4);
        comp.setDouble(quickfix.field.LegOrderQty.FIELD, 1.55);
        comp.setInt(quickfix.field.LegSwapType.FIELD, quickfix.field.LegSwapType.MODIFIED_DURATION);
        comp.setChar(quickfix.field.LegSettlType.FIELD, '1');
        comp.setString(quickfix.field.LegSettlDate.FIELD, "20091212");
        comp.setString(quickfix.field.LegRefID.FIELD, "343353545");
    }

    public void populate2(quickfix.fix50.QuoteRequest.NoRelatedSym.NoLegs comp) throws Exception {
        // InstrumentLeg
        quickfix.fix50.component.InstrumentLeg ileg = new quickfix.fix50.component.InstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate2(ileg);
        comp.set(ileg);
        // LegStipulations
        quickfix.fix50.component.LegStipulations legstip = new quickfix.fix50.component.LegStipulations();
        LegStipulations50TestData.getInstance().populate(legstip);
        comp.set(legstip);
        // NestedParties
        quickfix.fix50.component.NestedParties nestpart = new quickfix.fix50.component.NestedParties();
        NestedParties50TestData.getInstance().populate(nestpart);
        comp.set(nestpart);
        // LegBenchmarkCurveData
        quickfix.fix50.component.LegBenchmarkCurveData legbench = new quickfix.fix50.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(legbench);
        comp.set(legbench);

        comp.setDouble(quickfix.field.LegOptionRatio.FIELD, 1.5);
        comp.setDouble(quickfix.field.LegPrice.FIELD, 44.55);
        comp.setDouble(quickfix.field.LegQty.FIELD, 2.8);
        comp.setDouble(quickfix.field.LegOrderQty.FIELD, 1.88);
        comp.setInt(quickfix.field.LegSwapType.FIELD, quickfix.field.LegSwapType.PAR_FOR_PAR);
        comp.setChar(quickfix.field.LegSettlType.FIELD, '2');
        comp.setString(quickfix.field.LegSettlDate.FIELD, "20090909");
        comp.setString(quickfix.field.LegRefID.FIELD, "653456564");
    }

    public void populate1(QuoteRequestLegGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(comp.getInstrumentLeg());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties50TestData.getInstance().populate(comp.getNestedParties());
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(comp.getLegBenchmarkCurveData());

        comp.setLegOptionRatio(new Double(22.44));
        comp.setLegPrice(new Double(45.778));
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
        InstrumentLeg50TestData.getInstance().populate2(comp.getInstrumentLeg());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties50TestData.getInstance().populate(comp.getNestedParties());
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(comp.getLegBenchmarkCurveData());

        comp.setLegOptionRatio(new Double(12.44));
        comp.setLegPrice(new Double(55.778));
        comp.setLegQty(new Double(2.1));
        comp.setLegOrderQty(new Double(2.55));
        comp.setLegSwapType(LegSwapType.ParForPar);
        comp.setLegSettlType(SettlType.NextDay.getValue());
        comp.setLegSettlDate(new Date());
        comp.setLegRefID("4546456565");
    }

    public void check(QuoteRequestLegGroup expected, quickfix.fix50.QuoteRequest.NoRelatedSym.NoLegs actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        // LegStipulations
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
        // LegBenchmarkCurveData
        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getLegOptionRatio().doubleValue(), actual.getDouble(quickfix.field.LegOptionRatio.FIELD), 0.001);
        assertEquals(expected.getLegPrice().doubleValue(), actual.getDouble(quickfix.field.LegPrice.FIELD), 0.001);
        assertEquals(expected.getLegQty().doubleValue(), actual.getDouble(quickfix.field.LegQty.FIELD), 0.001);
        assertEquals(expected.getLegOrderQty().doubleValue(), actual.getDouble(quickfix.field.LegOrderQty.FIELD), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getInt(quickfix.field.LegSwapType.FIELD));
        assertEquals(expected.getLegSettlType(), String.valueOf(actual.getChar(quickfix.field.LegSettlType.FIELD)));
        assertEquals(formatQFStringDate(expected.getLegSettlDate()), actual.getString(quickfix.field.LegSettlDate.FIELD));
        assertEquals(expected.getLegRefID(), actual.getString(quickfix.field.LegRefID.FIELD));
    }

    public void check(quickfix.fix50.QuoteRequest.NoRelatedSym.NoLegs expected, QuoteRequestLegGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        // LegStipulations
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
        // LegBenchmarkCurveData
        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getDouble(quickfix.field.LegOptionRatio.FIELD), actual.getLegOptionRatio().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegPrice.FIELD), actual.getLegPrice().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegQty.FIELD), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegOrderQty.FIELD), actual.getLegOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.LegSwapType.FIELD), actual.getLegSwapType().getValue());
        assertEquals(String.valueOf(expected.getChar(quickfix.field.LegSettlType.FIELD)), actual.getLegSettlType());
        assertEquals(expected.getString(quickfix.field.LegSettlDate.FIELD), formatQFStringDate(actual.getLegSettlDate()));
        assertEquals(expected.getString(quickfix.field.LegRefID.FIELD), actual.getLegRefID());
    }

    public void check(QuoteRequestLegGroup expected, QuoteRequestLegGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        // LegStipulations
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
        // LegBenchmarkCurveData
        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getLegOptionRatio().doubleValue(), actual.getLegOptionRatio().doubleValue(), 0.001);
        assertEquals(expected.getLegPrice().doubleValue(), actual.getLegPrice().doubleValue(), 0.001);
        assertEquals(expected.getLegQty().doubleValue(), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getLegOrderQty().doubleValue(), actual.getLegOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getLegSwapType().getValue());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        assertEquals(expected.getLegRefID(), actual.getLegRefID());
    }
}
