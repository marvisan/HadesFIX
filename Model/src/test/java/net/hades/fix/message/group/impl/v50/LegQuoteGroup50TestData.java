/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegQuoteRequestSymbolGroup50TestData.java
 *
 * $Id: LegQuoteGroup50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.LegBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.group.LegQuoteGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegQuoteSymbolGroup50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class LegQuoteGroup50TestData extends MsgTest {

    private static final LegQuoteGroup50TestData INSTANCE;

    static {
        INSTANCE = new LegQuoteGroup50TestData();
    }

    public static LegQuoteGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix50.Quote.NoLegs comp) throws Exception {
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

        comp.setInt(quickfix.field.LegPriceType.FIELD, 3);
        comp.setDouble(quickfix.field.LegBidPx.FIELD, 33.56);
        comp.setDouble(quickfix.field.LegOfferPx.FIELD, 34.56);
        // LegBenchmarkCurveData
        quickfix.fix50.component.LegBenchmarkCurveData legbench = new quickfix.fix50.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(legbench);
        comp.set(legbench);

        comp.setString(quickfix.field.LegRefID.FIELD, "LegRef123");
        comp.setDouble(quickfix.field.LegBidForwardPoints.FIELD, 2.5);
        comp.setDouble(quickfix.field.LegOfferForwardPoints.FIELD, 3.6);
    }

    public void populate2(quickfix.fix50.Quote.NoLegs comp) throws Exception {
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

        comp.setInt(quickfix.field.LegPriceType.FIELD, 2);
        comp.setDouble(quickfix.field.LegBidPx.FIELD, 33.88);
        comp.setDouble(quickfix.field.LegOfferPx.FIELD, 34.77);
        // LegBenchmarkCurveData
        quickfix.fix50.component.LegBenchmarkCurveData legbench = new quickfix.fix50.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(legbench);
        comp.set(legbench);

        comp.setString(quickfix.field.LegRefID.FIELD, "LegRef321");
        comp.setDouble(quickfix.field.LegBidForwardPoints.FIELD, 2.7);
        comp.setDouble(quickfix.field.LegOfferForwardPoints.FIELD, 3.8);
    }

    public void populate1(quickfix.fix50.QuoteResponse.NoLegs comp) throws Exception {
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

        comp.setInt(quickfix.field.LegPriceType.FIELD, 3);
        comp.setDouble(quickfix.field.LegBidPx.FIELD, 33.56);
        comp.setDouble(quickfix.field.LegOfferPx.FIELD, 34.56);
        // LegBenchmarkCurveData
        quickfix.fix50.component.LegBenchmarkCurveData legbench = new quickfix.fix50.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(legbench);
        comp.set(legbench);

        comp.setString(quickfix.field.LegRefID.FIELD, "LegRef123");
        comp.setDouble(quickfix.field.LegBidForwardPoints.FIELD, 2.5);
        comp.setDouble(quickfix.field.LegOfferForwardPoints.FIELD, 3.6);
    }

    public void populate2(quickfix.fix50.QuoteResponse.NoLegs comp) throws Exception {
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

        comp.setInt(quickfix.field.LegPriceType.FIELD, 2);
        comp.setDouble(quickfix.field.LegBidPx.FIELD, 33.88);
        comp.setDouble(quickfix.field.LegOfferPx.FIELD, 34.77);
        // LegBenchmarkCurveData
        quickfix.fix50.component.LegBenchmarkCurveData legbench = new quickfix.fix50.component.LegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(legbench);
        comp.set(legbench);

        comp.setString(quickfix.field.LegRefID.FIELD, "LegRef321");
        comp.setDouble(quickfix.field.LegBidForwardPoints.FIELD, 2.7);
        comp.setDouble(quickfix.field.LegOfferForwardPoints.FIELD, 3.8);
    }

    public void populate1(LegQuoteGroup comp) {
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

    public void check(LegQuoteGroup expected, quickfix.fix50.Quote.NoLegs actual) throws Exception {
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

        assertEquals(expected.getLegPriceType().getValue(), actual.getInt(quickfix.field.LegPriceType.FIELD));
        assertEquals(expected.getLegBidPx().doubleValue(), actual.getDouble(quickfix.field.LegBidPx.FIELD), 0.001);
        assertEquals(expected.getLegOfferPx().doubleValue(), actual.getDouble(quickfix.field.LegOfferPx.FIELD), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getLegRefID(), actual.getString(quickfix.field.LegRefID.FIELD));
        assertEquals(expected.getLegBidForwardPoints().doubleValue(), actual.getDouble(quickfix.field.LegBidForwardPoints.FIELD), 0.001);
        assertEquals(expected.getLegOfferForwardPoints().doubleValue(), actual.getDouble(quickfix.field.LegOfferForwardPoints.FIELD), 0.001);
    }

    public void check(quickfix.fix50.Quote.NoLegs expected, LegQuoteGroup actual) throws Exception {
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

        assertEquals(expected.getInt(quickfix.field.LegPriceType.FIELD), actual.getLegPriceType().getValue());
        assertEquals(expected.getDouble(quickfix.field.LegBidPx.FIELD), actual.getLegBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegOfferPx.FIELD), actual.getLegOfferPx().doubleValue(), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getString(quickfix.field.LegRefID.FIELD), actual.getLegRefID());
        assertEquals(expected.getDouble(quickfix.field.LegBidForwardPoints.FIELD), actual.getLegBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegOfferForwardPoints.FIELD), actual.getLegOfferForwardPoints().doubleValue(), 0.001);
    }

    public void check(LegQuoteGroup expected, quickfix.fix50.QuoteResponse.NoLegs actual) throws Exception {
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

        assertEquals(expected.getLegPriceType().getValue(), actual.getInt(quickfix.field.LegPriceType.FIELD));
        assertEquals(expected.getLegBidPx().doubleValue(), actual.getDouble(quickfix.field.LegBidPx.FIELD), 0.001);
        assertEquals(expected.getLegOfferPx().doubleValue(), actual.getDouble(quickfix.field.LegOfferPx.FIELD), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getLegRefID(), actual.getString(quickfix.field.LegRefID.FIELD));
        assertEquals(expected.getLegBidForwardPoints().doubleValue(), actual.getDouble(quickfix.field.LegBidForwardPoints.FIELD), 0.001);
        assertEquals(expected.getLegOfferForwardPoints().doubleValue(), actual.getDouble(quickfix.field.LegOfferForwardPoints.FIELD), 0.001);
    }

    public void check(quickfix.fix50.QuoteResponse.NoLegs expected, LegQuoteGroup actual) throws Exception {
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

        assertEquals(expected.getInt(quickfix.field.LegPriceType.FIELD), actual.getLegPriceType().getValue());
        assertEquals(expected.getDouble(quickfix.field.LegBidPx.FIELD), actual.getLegBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegOfferPx.FIELD), actual.getLegOfferPx().doubleValue(), 0.001);
        // LegBenchmarkCurveData
        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());

        assertEquals(expected.getString(quickfix.field.LegRefID.FIELD), actual.getLegRefID());
        assertEquals(expected.getDouble(quickfix.field.LegBidForwardPoints.FIELD), actual.getLegBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.LegOfferForwardPoints.FIELD), actual.getLegOfferForwardPoints().doubleValue(), 0.001);
    }

    public void check(LegQuoteGroup expected, LegQuoteGroup actual) throws Exception {
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
