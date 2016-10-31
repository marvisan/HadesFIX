/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegQuoteStatusGroup44TestData.java
 *
 * $Id: LegQuoteStatusGroup44TestData.java,v 1.1 2009-07-06 03:19:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.LegStipulations44TestData;
import net.hades.fix.message.comp.impl.v44.NestedParties44TestData;
import net.hades.fix.message.group.LegQuoteStatusGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegQuoteStatusGroup44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class LegQuoteStatusGroup44TestData extends MsgTest {

    private static final LegQuoteStatusGroup44TestData INSTANCE;

    static {
        INSTANCE = new LegQuoteStatusGroup44TestData();
    }

    public static LegQuoteStatusGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix44.QuoteStatusReport.NoLegs comp) throws Exception {
        // InstrumentLeg
        quickfix.fix44.component.InstrumentLeg ileg = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(ileg);
        comp.set(ileg);
        
        comp.setDouble(quickfix.field.LegQty.FIELD, 2.4);
        comp.setInt(quickfix.field.LegSwapType.FIELD, quickfix.field.LegSwapType.MODIFIED_DURATION);
        comp.setChar(quickfix.field.LegSettlType.FIELD, '1');
        comp.setString(quickfix.field.LegSettlDate.FIELD, "20091212");
        // LegStipulations
        quickfix.fix44.component.LegStipulations legstip = new quickfix.fix44.component.LegStipulations();
        LegStipulations44TestData.getInstance().populate(legstip);
        comp.set(legstip);
        // NestedParties
        quickfix.fix44.component.NestedParties nestpart = new quickfix.fix44.component.NestedParties();
        NestedParties44TestData.getInstance().populate(nestpart);
        comp.set(nestpart);
    }

    public void populate2(quickfix.fix44.QuoteStatusReport.NoLegs comp) throws Exception {
        // InstrumentLeg
        quickfix.fix44.component.InstrumentLeg ileg = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(ileg);
        comp.set(ileg);

        comp.setDouble(quickfix.field.LegQty.FIELD, 2.5);
        comp.setInt(quickfix.field.LegSwapType.FIELD, quickfix.field.LegSwapType.PAR_FOR_PAR);
        comp.setChar(quickfix.field.LegSettlType.FIELD, '2');
        comp.setString(quickfix.field.LegSettlDate.FIELD, "20091210");
        // LegStipulations
        quickfix.fix44.component.LegStipulations legstip = new quickfix.fix44.component.LegStipulations();
        LegStipulations44TestData.getInstance().populate(legstip);
        comp.set(legstip);
        // NestedParties
        quickfix.fix44.component.NestedParties nestpart = new quickfix.fix44.component.NestedParties();
        NestedParties44TestData.getInstance().populate(nestpart);
        comp.set(nestpart);
    }

    public void populate1(LegQuoteStatusGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(comp.getInstrumentLeg());

        comp.setLegQty(new Double(2.9));
        comp.setLegSwapType(LegSwapType.ModifiedDuration);
        comp.setLegSettlType(SettlType.Future.getValue());
        comp.setLegSettlDate(new Date());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations44TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties44TestData.getInstance().populate(comp.getNestedParties());
    }

    public void populate2(LegQuoteStatusGroup comp) {
        // InstrumentLeg
        comp.setInstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(comp.getInstrumentLeg());

        comp.setLegQty(new Double(2.4));
        comp.setLegSwapType(LegSwapType.ParForPar);
        comp.setLegSettlType(SettlType.Cash.getValue());
        comp.setLegSettlDate(new Date());
        // LegStipulations
        comp.setLegStipulations();
        LegStipulations44TestData.getInstance().populate(comp.getLegStipulations());
        // NestedParties
        comp.setNestedParties();
        NestedParties44TestData.getInstance().populate(comp.getNestedParties());
    }

    public void check(LegQuoteStatusGroup expected, quickfix.fix44.QuoteStatusReport.NoLegs actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegQty().doubleValue(), actual.getDouble(quickfix.field.LegQty.FIELD), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getInt(quickfix.field.LegSwapType.FIELD));
        assertEquals(expected.getLegSettlType(), String.valueOf(actual.getChar(quickfix.field.LegSettlType.FIELD)));
        assertEquals(formatQFStringDate(expected.getLegSettlDate()), actual.getString(quickfix.field.LegSettlDate.FIELD));
        // LegStipulations
        LegStipulations44TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties44TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
    }

    public void check(quickfix.fix44.QuoteStatusReport.NoLegs expected, LegQuoteStatusGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getDouble(quickfix.field.LegQty.FIELD), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.LegSwapType.FIELD), actual.getLegSwapType().getValue());
        assertEquals(String.valueOf(expected.getChar(quickfix.field.LegSettlType.FIELD)), actual.getLegSettlType());
        assertEquals(expected.getString(quickfix.field.LegSettlDate.FIELD), formatQFStringDate(actual.getLegSettlDate()));
        // LegStipulations
        LegStipulations44TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties44TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
    }

    public void check(LegQuoteStatusGroup expected, LegQuoteStatusGroup actual) throws Exception {
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegQty().doubleValue(), actual.getLegQty().doubleValue(), 0.001);
        assertEquals(expected.getLegSwapType().getValue(), actual.getLegSwapType().getValue());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
        // LegStipulations
        LegStipulations44TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());
        // NestedParties
        NestedParties44TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());
    }
}
