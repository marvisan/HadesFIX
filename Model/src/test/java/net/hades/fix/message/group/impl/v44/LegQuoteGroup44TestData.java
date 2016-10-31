/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegQuoteRequestSymbolGroup44TestData.java
 *
 * $Id: LegQuoteGroup44TestData.java,v 1.1 2009-07-06 03:19:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.LegBenchmarkCurveData44TestData;
import net.hades.fix.message.comp.impl.v44.LegStipulations44TestData;
import net.hades.fix.message.comp.impl.v44.NestedParties44TestData;
import net.hades.fix.message.group.LegQuoteGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegQuoteSymbolGroup44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class LegQuoteGroup44TestData extends MsgTest {

    private static final LegQuoteGroup44TestData INSTANCE;

    static {
        INSTANCE = new LegQuoteGroup44TestData();
    }

    public static LegQuoteGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix44.Quote.NoLegs comp) throws Exception {
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

        comp.setInt(quickfix.field.LegPriceType.FIELD, 3);
        comp.setDouble(quickfix.field.LegBidPx.FIELD, 33.56);
        comp.setDouble(quickfix.field.LegOfferPx.FIELD, 34.56);
        // LegBenchmarkCurveData
        quickfix.fix44.component.LegBenchmarkCurveData legbench = new quickfix.fix44.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(legbench);
        comp.set(legbench);
    }

    public void populate2(quickfix.fix44.Quote.NoLegs comp) throws Exception {
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

        comp.setInt(quickfix.field.LegPriceType.FIELD, 2);
        comp.setDouble(quickfix.field.LegBidPx.FIELD, 33.88);
        comp.setDouble(quickfix.field.LegOfferPx.FIELD, 34.77);
        // LegBenchmarkCurveData
        quickfix.fix44.component.LegBenchmarkCurveData legbench = new quickfix.fix44.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(legbench);
        comp.set(legbench);
    }

    public void populate1(quickfix.fix44.QuoteResponse.NoLegs comp) throws Exception {
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

        comp.setInt(quickfix.field.LegPriceType.FIELD, 3);
        comp.setDouble(quickfix.field.LegBidPx.FIELD, 33.56);
        comp.setDouble(quickfix.field.LegOfferPx.FIELD, 34.56);
        // LegBenchmarkCurveData
        quickfix.fix44.component.LegBenchmarkCurveData legbench = new quickfix.fix44.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(legbench);
        comp.set(legbench);
    }

    public void populate2(quickfix.fix44.QuoteResponse.NoLegs comp) throws Exception {
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

        comp.setInt(quickfix.field.LegPriceType.FIELD, 2);
        comp.setDouble(quickfix.field.LegBidPx.FIELD, 33.88);
        comp.setDouble(quickfix.field.LegOfferPx.FIELD, 34.77);
        // LegBenchmarkCurveData
        quickfix.fix44.component.LegBenchmarkCurveData legbench = new quickfix.fix44.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(legbench);
        comp.set(legbench);
    }

    public void populate1(LegQuoteGroup comp) {
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

        comp.setLegPriceType(PriceType.FixedAmount);
        comp.setLegBidPx(new Double(12.34));
        comp.setLegOfferPx(new Double(23.44));
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(comp.getLegBenchmarkCurveData());
    }

    public void populate2(LegQuoteGroup comp) {
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

        comp.setLegPriceType(PriceType.FixedAmount);
        comp.setLegBidPx(new Double(12.64));
        comp.setLegOfferPx(new Double(23.84));
        // LegBenchmarkCurveData
        comp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData44TestData.getInstance().populate(comp.getLegBenchmarkCurveData());
    }

    public void check(LegQuoteGroup expected, quickfix.fix44.Quote.NoLegs actual) throws Exception {
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

        assertEquals(expected.getLegPriceType().getValue(), actual.getInt(quickfix.field.LegPriceType.FIELD));
        assertEquals(expected.getLegBidPx().doubleValue(), actual.getDouble(quickfix.field.LegBidPx.FIELD), 0.001);
        assertEquals(expected.getLegOfferPx().doubleValue(), actual.getDouble(quickfix.field.LegOfferPx.FIELD), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData44TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());
    }

    public void check(quickfix.fix44.Quote.NoLegs expected, LegQuoteGroup actual) throws Exception {
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

        assertEquals(expected.getInt(quickfix.field.LegPriceType.FIELD), actual.getLegPriceType().getValue());
        assertEquals(expected.getDouble(quickfix.field.LegBidPx.FIELD), actual.getLegBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegOfferPx.FIELD), actual.getLegOfferPx().doubleValue(), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData44TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());
    }

    public void check(LegQuoteGroup expected, quickfix.fix44.QuoteResponse.NoLegs actual) throws Exception {
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

        assertEquals(expected.getLegPriceType().getValue(), actual.getInt(quickfix.field.LegPriceType.FIELD));
        assertEquals(expected.getLegBidPx().doubleValue(), actual.getDouble(quickfix.field.LegBidPx.FIELD), 0.001);
        assertEquals(expected.getLegOfferPx().doubleValue(), actual.getDouble(quickfix.field.LegOfferPx.FIELD), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData44TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());
    }

    public void check(quickfix.fix44.QuoteResponse.NoLegs expected, LegQuoteGroup actual) throws Exception {
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

        assertEquals(expected.getInt(quickfix.field.LegPriceType.FIELD), actual.getLegPriceType().getValue());
        assertEquals(expected.getDouble(quickfix.field.LegBidPx.FIELD), actual.getLegBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegOfferPx.FIELD), actual.getLegOfferPx().doubleValue(), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData44TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());
    }

    public void check(LegQuoteGroup expected, LegQuoteGroup actual) throws Exception {
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

        assertEquals(expected.getLegPriceType().getValue(), actual.getLegPriceType().getValue());
        assertEquals(expected.getLegBidPx().doubleValue(), actual.getLegBidPx().doubleValue(), 0.001);
        assertEquals(expected.getLegOfferPx().doubleValue(), actual.getLegOfferPx().doubleValue(), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData44TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());
    }
}
