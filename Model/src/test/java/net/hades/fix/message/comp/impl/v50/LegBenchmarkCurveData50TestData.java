/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegBenchmarkCurveData44.java
 *
 * $Id: LegBenchmarkCurveData50TestData.java,v 1.2 2011-10-29 09:42:26 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.LegBenchmarkCurveData;
import net.hades.fix.message.type.BenchmarkCurveName;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;

/**
 * Test utility for LegBenchmarkCurveData50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/04/2009, 10:23:23 AM
 */
public class LegBenchmarkCurveData50TestData extends MsgTest {

    private static final LegBenchmarkCurveData50TestData INSTANCE;

    static {
        INSTANCE = new LegBenchmarkCurveData50TestData();
    }

    public static LegBenchmarkCurveData50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix50.component.LegBenchmarkCurveData msg) throws Exception {
        msg.setString(quickfix.field.LegBenchmarkCurveCurrency.FIELD, Currency.CanadianDollar.getValue());
        msg.setString(quickfix.field.LegBenchmarkCurveName.FIELD, "MuniAAA");
        msg.setString(quickfix.field.LegBenchmarkCurvePoint.FIELD, "benchmark curve point");
        msg.setDouble(quickfix.field.LegBenchmarkPrice.FIELD, 31.899);
        msg.setInt(quickfix.field.LegBenchmarkPriceType.FIELD, PriceType.FixedAmount.getValue());
    }

    public void populate(LegBenchmarkCurveData component) {
        component.setLegBenchmarkCurveCurrency(Currency.CanadianDollar);
        component.setLegBenchmarkCurveName(BenchmarkCurveName.EUREPO);
        component.setLegBenchmarkCurvePoint("benchmark curve point");
        component.setLegBenchmarkPrice(new Double(21.666));
        component.setLegBenchmarkPriceType(PriceType.Premium);
    }

    public void check(LegBenchmarkCurveData expected, quickfix.fix50.component.LegBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getLegBenchmarkCurveCurrency().getValue(), actual.getString(quickfix.field.LegBenchmarkCurveCurrency.FIELD));
        assertEquals(expected.getLegBenchmarkCurveName().getValue(), actual.getString(quickfix.field.LegBenchmarkCurveName.FIELD));
        assertEquals(expected.getLegBenchmarkCurvePoint(), actual.getString(quickfix.field.LegBenchmarkCurvePoint.FIELD));
        assertEquals(expected.getLegBenchmarkPrice().doubleValue(), actual.getDouble(quickfix.field.LegBenchmarkPrice.FIELD), 0.001);
        assertEquals(expected.getLegBenchmarkPriceType().getValue(), actual.getInt(quickfix.field.LegBenchmarkPriceType.FIELD));
    }

    public void check(quickfix.fix50.component.LegBenchmarkCurveData expected, LegBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.LegBenchmarkCurveCurrency.FIELD), actual.getLegBenchmarkCurveCurrency().getValue());
        assertEquals(expected.getString(quickfix.field.LegBenchmarkCurveName.FIELD), actual.getLegBenchmarkCurveName().getValue());
        assertEquals(expected.getString(quickfix.field.LegBenchmarkCurvePoint.FIELD), actual.getLegBenchmarkCurvePoint());
        assertEquals(expected.getDouble(quickfix.field.LegBenchmarkPrice.FIELD), actual.getLegBenchmarkPrice().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.LegBenchmarkPriceType.FIELD), actual.getLegBenchmarkPriceType().getValue());
    }

    public void check(LegBenchmarkCurveData expected, LegBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getLegBenchmarkCurveCurrency().getValue(), actual.getLegBenchmarkCurveCurrency().getValue());
        assertEquals(expected.getLegBenchmarkCurveName().getValue(), actual.getLegBenchmarkCurveName().getValue());
        assertEquals(expected.getLegBenchmarkCurvePoint(), actual.getLegBenchmarkCurvePoint());
        assertEquals(expected.getLegBenchmarkPrice().doubleValue(), actual.getLegBenchmarkPrice().doubleValue(), 0.001);
        assertEquals(expected.getLegBenchmarkPriceType().getValue(), actual.getLegBenchmarkPriceType().getValue());
    }
}
