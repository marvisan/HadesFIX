/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SpreadOrBenchmarkCurveData44TestData.java
 *
 * $Id: SpreadOrBenchmarkCurveData44TestData.java,v 1.2 2011-10-29 09:42:32 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.type.BenchmarkCurveName;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SecurityIDSource;

/**
 * Test utility for SpreadOrBenchmarkCurveData44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/03/2009, 7:07:21 PM
 */
public class SpreadOrBenchmarkCurveData44TestData extends MsgTest {

    private static final SpreadOrBenchmarkCurveData44TestData INSTANCE;

    static {
        INSTANCE = new SpreadOrBenchmarkCurveData44TestData();
    }

    public static SpreadOrBenchmarkCurveData44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.component.SpreadOrBenchmarkCurveData msg) throws UnsupportedEncodingException {
        msg.setDouble(quickfix.field.Spread.FIELD, 21.555);
        msg.setString(quickfix.field.BenchmarkCurveCurrency.FIELD, Currency.CanadianDollar.getValue());
        msg.setString(quickfix.field.BenchmarkCurveName.FIELD, "LIBOR");
        msg.setString(quickfix.field.BenchmarkCurvePoint.FIELD, "benchmark curve point");
        msg.setDouble(quickfix.field.BenchmarkPrice.FIELD, 31.899);
        msg.setInt(quickfix.field.BenchmarkPriceType.FIELD, PriceType.FixedAmount.getValue());
        msg.setString(quickfix.field.BenchmarkSecurityID.FIELD, "benchmark sec ID");
        msg.setString(quickfix.field.BenchmarkSecurityIDSource.FIELD, SecurityIDSource.BloombergSymbol.getValue());
    }

    public void populate(SpreadOrBenchmarkCurveData component) {
        component.setSpread(new Double(21.555));
        component.setBenchmarkCurveCurrency(Currency.CanadianDollar);
        component.setBenchmarkCurveName(BenchmarkCurveName.LIBID);
        component.setBenchmarkCurvePoint("benchmark curve point");
        component.setBenchmarkPrice(new Double(21.666));
        component.setBenchmarkPriceType(PriceType.Premium);
        component.setBenchmarkSecurityID("benchmark sec ID");
        component.setBenchmarkSecurityIDSource(SecurityIDSource.ClearingHouse);
    }

    public void check(SpreadOrBenchmarkCurveData expected, quickfix.fix44.component.SpreadOrBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getSpread().doubleValue(), actual.getDouble(quickfix.field.Spread.FIELD), 0.001);
        assertEquals(expected.getBenchmarkCurveCurrency().getValue(), actual.getString(quickfix.field.BenchmarkCurveCurrency.FIELD));
        assertEquals(expected.getBenchmarkCurveName().getValue(), actual.getString(quickfix.field.BenchmarkCurveName.FIELD));
        assertEquals(expected.getBenchmarkCurvePoint(), actual.getString(quickfix.field.BenchmarkCurvePoint.FIELD));
        assertEquals(expected.getBenchmarkPrice().doubleValue(), actual.getDouble(quickfix.field.BenchmarkPrice.FIELD), 0.001);
        assertEquals(expected.getBenchmarkPriceType().getValue(), actual.getInt(quickfix.field.BenchmarkPriceType.FIELD));
        assertEquals(expected.getBenchmarkSecurityID(), actual.getString(quickfix.field.BenchmarkSecurityID.FIELD));
        assertEquals(expected.getBenchmarkSecurityIDSource().getValue(), actual.getString(quickfix.field.BenchmarkSecurityIDSource.FIELD));
    }

    public void check(quickfix.fix44.component.SpreadOrBenchmarkCurveData expected, SpreadOrBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getDouble(quickfix.field.Spread.FIELD), actual.getSpread().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.BenchmarkCurveCurrency.FIELD), actual.getBenchmarkCurveCurrency().getValue());
        assertEquals(expected.getString(quickfix.field.BenchmarkCurveName.FIELD), actual.getBenchmarkCurveName().getValue());
        assertEquals(expected.getString(quickfix.field.BenchmarkCurvePoint.FIELD), actual.getBenchmarkCurvePoint());
        assertEquals(expected.getDouble(quickfix.field.BenchmarkPrice.FIELD), actual.getBenchmarkPrice().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.BenchmarkPriceType.FIELD), actual.getBenchmarkPriceType().getValue());
        assertEquals(expected.getString(quickfix.field.BenchmarkSecurityID.FIELD), actual.getBenchmarkSecurityID());
        assertEquals(expected.getString(quickfix.field.BenchmarkSecurityIDSource.FIELD), actual.getBenchmarkSecurityIDSource().getValue());
    }

    public void check(SpreadOrBenchmarkCurveData expected, SpreadOrBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getSpread().doubleValue(), actual.getSpread().doubleValue(), 0.001);
        assertEquals(expected.getBenchmarkCurveCurrency().getValue(), actual.getBenchmarkCurveCurrency().getValue());
        assertEquals(expected.getBenchmarkCurveName().getValue(), actual.getBenchmarkCurveName().getValue());
        assertEquals(expected.getBenchmarkCurvePoint(), actual.getBenchmarkCurvePoint());
        assertEquals(expected.getBenchmarkPrice().doubleValue(), actual.getBenchmarkPrice().doubleValue(), 0.001);
        assertEquals(expected.getBenchmarkPriceType().getValue(), actual.getBenchmarkPriceType().getValue());
        assertEquals(expected.getBenchmarkSecurityID(), actual.getBenchmarkSecurityID());
        assertEquals(expected.getBenchmarkSecurityIDSource().getValue(), actual.getBenchmarkSecurityIDSource().getValue());
    }
}
