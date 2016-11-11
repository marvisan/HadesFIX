/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestLegGroup44TestData.java
 *
 * $Id: QuoteRequestLegGroup44TestData.java,v 1.1 2009-07-06 03:19:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.LegBenchmarkCurveData44TestData;
import net.hades.fix.message.comp.impl.v44.LegStipulations44TestData;
import net.hades.fix.message.comp.impl.v44.NestedParties44TestData;
import net.hades.fix.message.group.QuoteRequestLegGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegQuoteRequestSymbolGroup44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class QuoteRequestLegGroup44TestData extends MsgTest {

    private static final QuoteRequestLegGroup44TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestLegGroup44TestData();
    }

    public static QuoteRequestLegGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs comp) throws Exception {
        // InstrumentLeg
        quickfix.fix44.component.InstrumentLeg ileg = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(ileg);
        comp.set(ileg);
        // LegStipulations
        quickfix.fix44.component.LegStipulations legstip = new quickfix.fix44.component.LegStipulations();
        LegStipulations44TestData.getInstance().populate(legstip);
        comp.set(legstip);
        // NestedParties
        quickfix.fix44.component.NestedParties nestpart = new quickfix.fix44.component.NestedParties();
        NestedParties44TestData.getInstance().populate(nestpart);
        comp.set(nestpart);
        // LegBenchmarkCurveData
        quickfix.fix44.component.LegBenchmarkCurveData legbench = new quickfix.fix44.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(legbench);
        comp.set(legbench);
        
        comp.setDouble(quickfix.field.LegQty.FIELD, 2.4);
        comp.setInt(quickfix.field.LegSwapType.FIELD, quickfix.field.LegSwapType.MODIFIED_DURATION);
        comp.setChar(quickfix.field.LegSettlType.FIELD, '1');
        comp.setString(quickfix.field.LegSettlDate.FIELD, "20091212");
    }

    public void populate2(quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs comp) throws Exception {
        // InstrumentLeg
        quickfix.fix44.component.InstrumentLeg ileg = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate2(ileg);
        comp.set(ileg);
        // LegStipulations
        quickfix.fix44.component.LegStipulations legstip = new quickfix.fix44.component.LegStipulations();
        LegStipulations44TestData.getInstance().populate(legstip);
        comp.set(legstip);
        // NestedParties
        quickfix.fix44.component.NestedParties nestpart = new quickfix.fix44.component.NestedParties();
        NestedParties44TestData.getInstance().populate(nestpart);
        comp.set(nestpart);
        // LegBenchmarkCurveData
        quickfix.fix44.component.LegBenchmarkCurveData legbench = new quickfix.fix44.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(legbench);
        comp.set(legbench);

        comp.setDouble(quickfix.field.LegQty.FIELD, 2.8);
        comp.setInt(quickfix.field.LegSwapType.FIELD, quickfix.field.LegSwapType.PAR_FOR_PAR);
        comp.setChar(quickfix.field.LegSettlType.FIELD, '2');
        comp.setString(quickfix.field.LegSettlDate.FIELD, "20090909");
    }

    public void populate1(QuoteRequestLegGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(comp.getInstrumentLeg());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations44TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties44TestData.getInstance().populate(comp.getNestedParties());
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(comp.getLegBenchmarkCurveData());

        comp.setLegQty(new Double(2.9));
        comp.setLegSwapType(LegSwapType.ModifiedDuration);
        comp.setLegSettlType(SettlType.Future.getValue());
        comp.setLegSettlDate(new Date());
    }

    public void populate2(QuoteRequestLegGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate2(comp.getInstrumentLeg());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations44TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties44TestData.getInstance().populate(comp.getNestedParties());
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(comp.getLegBenchmarkCurveData());

        comp.setLegQty(new Double(2.1));
        comp.setLegSwapType(LegSwapType.ParForPar);
        comp.setLegSettlType(SettlType.NextDay.getValue());
        comp.setLegSettlDate(new Date());
    }

    public void check(QuoteRequestLegGroup expected, quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        // LegStipulations
        LegStipulations44TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties44TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
        // LegBenchmarkCurveData
        LegBenchmarkCurveData44TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getLegQty().doubleValue(), actual.getDouble(quickfix.field.LegQty.FIELD), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getInt(quickfix.field.LegSwapType.FIELD));
        assertEquals(expected.getLegSettlType(), String.valueOf(actual.getChar(quickfix.field.LegSettlType.FIELD)));
        assertEquals(formatQFStringDate(expected.getLegSettlDate()), actual.getString(quickfix.field.LegSettlDate.FIELD));
    }

    public void check(quickfix.fix44.QuoteRequest.NoRelatedSym.NoLegs expected, QuoteRequestLegGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        // LegStipulations
        LegStipulations44TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties44TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
        // LegBenchmarkCurveData
        LegBenchmarkCurveData44TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getDouble(quickfix.field.LegQty.FIELD), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.LegSwapType.FIELD), actual.getLegSwapType().getValue());
        assertEquals(String.valueOf(expected.getChar(quickfix.field.LegSettlType.FIELD)), actual.getLegSettlType());
        assertEquals(expected.getString(quickfix.field.LegSettlDate.FIELD), formatQFStringDate(actual.getLegSettlDate()));
    }

    public void check(QuoteRequestLegGroup expected, QuoteRequestLegGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        // LegStipulations
        LegStipulations44TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties44TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
        // LegBenchmarkCurveData
        LegBenchmarkCurveData44TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getLegQty().doubleValue(), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getLegSwapType().getValue());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
    }
}
