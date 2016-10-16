/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * YieldData50TestData.java
 *
 * $Id: YieldData50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.YieldType;

/**
 * Test utility for YieldData50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 18/03/2009, 7:52:01 PM
 */
public class YieldData50TestData extends MsgTest {

    private static final YieldData50TestData INSTANCE;

    static {
        INSTANCE = new YieldData50TestData();
    }

    public static YieldData50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(YieldData component) {
        component.setYieldType(YieldType.BookYield);
        component.setYield(new Double(21.555));
        component.setYieldCalcDate(new Date());
        component.setYieldRedemptionDate(new Date());
        component.setYieldRedemptionPrice(new Double(12.555));
        component.setYieldRedemptionPriceType(PriceType.PerUnit);
    }

    public void populate(quickfix.fix50.component.YieldData msg) throws Exception {
        msg.setString(quickfix.field.YieldType.FIELD, "AFTERTAX");
        msg.setDouble(quickfix.field.Yield.FIELD, 32.456);
        msg.setUtcDateOnly(quickfix.field.YieldCalcDate.FIELD, new Date());
        msg.setUtcDateOnly(quickfix.field.YieldRedemptionDate.FIELD, new Date());
        msg.setDouble(quickfix.field.YieldRedemptionPrice.FIELD, 31.899);
        msg.setInt(quickfix.field.YieldRedemptionPriceType.FIELD, 1);
    }

    public void check(YieldData expected, YieldData actual) throws Exception {
        assertEquals(expected.getYieldType().getValue(), actual.getYieldType().getValue());
        assertEquals(expected.getYield().doubleValue(), actual.getYield().doubleValue(), 0.0001);
        assertDateEquals(expected.getYieldCalcDate(), actual.getYieldCalcDate());
        assertDateEquals(expected.getYieldRedemptionDate(), actual.getYieldRedemptionDate());
        assertEquals(expected.getYieldRedemptionPrice().doubleValue(), actual.getYieldRedemptionPrice().doubleValue(), 0.001);
        assertEquals(expected.getYieldRedemptionPriceType().getValue(), actual.getYieldRedemptionPriceType().getValue());
    }

    public void check(YieldData expected, quickfix.fix50.component.YieldData actual) throws Exception {
        assertEquals(expected.getYieldType().getValue(), actual.getString(quickfix.field.YieldType.FIELD));
        assertEquals(expected.getYield().doubleValue(), actual.getDouble(quickfix.field.Yield.FIELD), 0.0001);
        assertDateEquals(expected.getYieldCalcDate(), actual.getUtcDateOnly(quickfix.field.YieldCalcDate.FIELD));
        assertDateEquals(expected.getYieldRedemptionDate(), actual.getUtcDateOnly(quickfix.field.YieldRedemptionDate.FIELD));
        assertEquals(expected.getYieldRedemptionPrice().doubleValue(), actual.getDouble(quickfix.field.YieldRedemptionPrice.FIELD), 0.001);
        assertEquals(expected.getYieldRedemptionPriceType().getValue(), actual.getInt(quickfix.field.YieldRedemptionPriceType.FIELD));
    }

    public void check(quickfix.fix50.component.YieldData expected, YieldData actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.YieldType.FIELD), actual.getYieldType().getValue());
        assertEquals(expected.getDouble(quickfix.field.Yield.FIELD), actual.getYield().doubleValue(), 0.0001);
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.YieldCalcDate.FIELD), actual.getYieldCalcDate());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.YieldRedemptionDate.FIELD), actual.getYieldRedemptionDate());
        assertEquals(expected.getDouble(quickfix.field.YieldRedemptionPrice.FIELD), actual.getYieldRedemptionPrice().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.YieldRedemptionPriceType.FIELD), actual.getYieldRedemptionPriceType().getValue());
    }
}
