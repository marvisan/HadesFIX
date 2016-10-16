/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegQuoteStatusGroup50TestData.java
 *
 * $Id: LegQuoteStatusGroup50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.group.LegQuoteStatusGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegQuoteSymbolGroup50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class LegQuoteStatusGroup50TestData extends MsgTest {

    private static final LegQuoteStatusGroup50TestData INSTANCE;

    static {
        INSTANCE = new LegQuoteStatusGroup50TestData();
    }

    public static LegQuoteStatusGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix50.QuoteStatusReport.NoLegs comp) throws Exception {
        // InstrumentLeg
        quickfix.fix50.component.InstrumentLeg ileg = new quickfix.fix50.component.InstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(ileg);
        comp.set(ileg);
        
        comp.setDouble(quickfix.field.LegQty.FIELD, 2.4);
        comp.setDouble(quickfix.field.LegOrderQty.FIELD, 2.9);
        comp.setInt(quickfix.field.LegSwapType.FIELD, quickfix.field.LegSwapType.MODIFIED_DURATION);
        comp.setChar(quickfix.field.LegSettlType.FIELD, '1');
        comp.setString(quickfix.field.LegSettlDate.FIELD, "20091212");
        // LegStipulations
        quickfix.fix50.component.LegStipulations legstip = new quickfix.fix50.component.LegStipulations();
        LegStipulations50TestData.getInstance().populate(legstip);
        comp.set(legstip);
        // NestedParties
        quickfix.fix50.component.NestedParties nestpart = new quickfix.fix50.component.NestedParties();
        NestedParties50TestData.getInstance().populate(nestpart);
        comp.set(nestpart);
    }

    public void populate2(quickfix.fix50.QuoteStatusReport.NoLegs comp) throws Exception {
        // InstrumentLeg
        quickfix.fix50.component.InstrumentLeg ileg = new quickfix.fix50.component.InstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(ileg);
        comp.set(ileg);

        comp.setDouble(quickfix.field.LegQty.FIELD, 2.1);
        comp.setDouble(quickfix.field.LegOrderQty.FIELD, 2.8);
        comp.setInt(quickfix.field.LegSwapType.FIELD, quickfix.field.LegSwapType.PAR_FOR_PAR);
        comp.setChar(quickfix.field.LegSettlType.FIELD, '2');
        comp.setString(quickfix.field.LegSettlDate.FIELD, "20091210");
        // LegStipulations
        quickfix.fix50.component.LegStipulations legstip = new quickfix.fix50.component.LegStipulations();
        LegStipulations50TestData.getInstance().populate(legstip);
        comp.set(legstip);
        // NestedParties
        quickfix.fix50.component.NestedParties nestpart = new quickfix.fix50.component.NestedParties();
        NestedParties50TestData.getInstance().populate(nestpart);
        comp.set(nestpart);
    }

    public void populate1(LegQuoteStatusGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(comp.getInstrumentLeg());

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
    }

    public void populate2(LegQuoteStatusGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(comp.getInstrumentLeg());

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
    }

    public void check(LegQuoteStatusGroup expected, quickfix.fix50.QuoteStatusReport.NoLegs actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegQty().doubleValue(), actual.getDouble(quickfix.field.LegQty.FIELD), 0.001);
        assertEquals(expected.getLegOrderQty().doubleValue(), actual.getDouble(quickfix.field.LegOrderQty.FIELD), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getInt(quickfix.field.LegSwapType.FIELD));
        assertEquals(expected.getLegSettlType(), String.valueOf(actual.getChar(quickfix.field.LegSettlType.FIELD)));
        assertEquals(formatQFStringDate(expected.getLegSettlDate()), actual.getString(quickfix.field.LegSettlDate.FIELD));
        // LegStipulations
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
    }

    public void check(quickfix.fix50.QuoteStatusReport.NoLegs expected, LegQuoteStatusGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getDouble(quickfix.field.LegQty.FIELD), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegOrderQty.FIELD), actual.getLegOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.LegSwapType.FIELD), actual.getLegSwapType().getValue());
        assertEquals(String.valueOf(expected.getChar(quickfix.field.LegSettlType.FIELD)), actual.getLegSettlType());
        assertEquals(expected.getString(quickfix.field.LegSettlDate.FIELD), formatQFStringDate(actual.getLegSettlDate()));
        // LegStipulations
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
    }

    public void check(LegQuoteStatusGroup expected, LegQuoteStatusGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegQty().doubleValue(), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getLegOrderQty().doubleValue(), actual.getLegOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getLegSwapType().getValue());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        // LegStipulations
        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
    }
}
