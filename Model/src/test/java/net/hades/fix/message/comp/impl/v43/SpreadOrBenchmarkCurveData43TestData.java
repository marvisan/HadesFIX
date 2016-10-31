/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SpreadOrBenchmarkCurveData43TestData.java
 *
 * $Id: SpreadOrBenchmarkCurveData43TestData.java,v 1.2 2011-10-29 09:42:32 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import java.io.UnsupportedEncodingException;

import quickfix.Message;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.type.BenchmarkCurveName;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for SpreadOrBenchmarkCurveData43 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/03/2009, 7:07:21 PM
 */
public class SpreadOrBenchmarkCurveData43TestData extends MsgTest {

    private static final SpreadOrBenchmarkCurveData43TestData INSTANCE;

    static {
        INSTANCE = new SpreadOrBenchmarkCurveData43TestData();
    }

    public static SpreadOrBenchmarkCurveData43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(Message msg) throws UnsupportedEncodingException {
        msg.setDouble(quickfix.field.Spread.FIELD, 21.555);
        msg.setString(quickfix.field.BenchmarkCurveCurrency.FIELD, Currency.CanadianDollar.getValue());
        msg.setString(quickfix.field.BenchmarkCurveName.FIELD, BenchmarkCurveName.TREASURY.getValue());
        msg.setString(quickfix.field.BenchmarkCurvePoint.FIELD, "benchmark curve point");
    }

    public void populate(quickfix.fix43.component.SpreadOrBenchmarkCurveData msg) throws UnsupportedEncodingException {
        msg.setDouble(quickfix.field.Spread.FIELD, 21.555);
        msg.setString(quickfix.field.BenchmarkCurveCurrency.FIELD, Currency.CanadianDollar.getValue());
        msg.setString(quickfix.field.BenchmarkCurveName.FIELD, BenchmarkCurveName.FUTURESWAP.getValue());
        msg.setString(quickfix.field.BenchmarkCurvePoint.FIELD, "benchmark curve point");
    }

    public void populate(SpreadOrBenchmarkCurveData component) {
        component.setSpread(new Double(21.555));
        component.setBenchmarkCurveCurrency(Currency.CanadianDollar);
        component.setBenchmarkCurveName(BenchmarkCurveName.SWAP);
        component.setBenchmarkCurvePoint("benchmark curve point");
    }

    public void check(SpreadOrBenchmarkCurveData expected, Message actual) throws Exception {
        assertEquals(expected.getSpread().doubleValue(), actual.getDouble(quickfix.field.Spread.FIELD), 0.001);
        assertEquals(expected.getBenchmarkCurveCurrency().getValue(), actual.getString(quickfix.field.BenchmarkCurveCurrency.FIELD));
        assertEquals(expected.getBenchmarkCurveName().getValue(), actual.getString(quickfix.field.BenchmarkCurveName.FIELD));
        assertEquals(expected.getBenchmarkCurvePoint(), actual.getString(quickfix.field.BenchmarkCurvePoint.FIELD));
    }

    public void check(SpreadOrBenchmarkCurveData expected, quickfix.fix43.component.SpreadOrBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getSpread().doubleValue(), actual.getDouble(quickfix.field.Spread.FIELD), 0.001);
        assertEquals(expected.getBenchmarkCurveCurrency().getValue(), actual.getString(quickfix.field.BenchmarkCurveCurrency.FIELD));
        assertEquals(expected.getBenchmarkCurveName().getValue(), actual.getString(quickfix.field.BenchmarkCurveName.FIELD));
        assertEquals(expected.getBenchmarkCurvePoint(), actual.getString(quickfix.field.BenchmarkCurvePoint.FIELD));
    }

    public void check(Message expected, SpreadOrBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getDouble(quickfix.field.Spread.FIELD), actual.getSpread().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.BenchmarkCurveCurrency.FIELD), actual.getBenchmarkCurveCurrency().getValue());
        assertEquals(expected.getString(quickfix.field.BenchmarkCurveName.FIELD), actual.getBenchmarkCurveName().getValue());
        assertEquals(expected.getString(quickfix.field.BenchmarkCurvePoint.FIELD), actual.getBenchmarkCurvePoint());
    }

    public void check(quickfix.fix43.component.SpreadOrBenchmarkCurveData expected, SpreadOrBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getDouble(quickfix.field.Spread.FIELD), actual.getSpread().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.BenchmarkCurveCurrency.FIELD), actual.getBenchmarkCurveCurrency().getValue());
        assertEquals(expected.getString(quickfix.field.BenchmarkCurveName.FIELD), actual.getBenchmarkCurveName().getValue());
        assertEquals(expected.getString(quickfix.field.BenchmarkCurvePoint.FIELD), actual.getBenchmarkCurvePoint());
    }

    public void check(SpreadOrBenchmarkCurveData expected, SpreadOrBenchmarkCurveData actual) throws Exception {
        assertEquals(expected.getSpread().doubleValue(), actual.getSpread().doubleValue(), 0.001);
        assertEquals(expected.getBenchmarkCurveCurrency().getValue(), actual.getBenchmarkCurveCurrency().getValue());
        assertEquals(expected.getBenchmarkCurveName(), actual.getBenchmarkCurveName());
        assertEquals(expected.getBenchmarkCurvePoint(), actual.getBenchmarkCurvePoint());
    }
}
