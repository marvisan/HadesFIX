/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * YieldData43TestData.java
 *
 * $Id: YieldData43TestData.java,v 1.1 2009-07-06 03:19:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.type.YieldType;

/**
 * Test utility for YieldData43 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 13/04/2009, 9:54:09 AM
 */
public class YieldData43TestData extends MsgTest {

    private static final YieldData43TestData INSTANCE;

    static {
        INSTANCE = new YieldData43TestData();
    }

    public static YieldData43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.component.YieldData component) throws UnsupportedEncodingException {
        component.setString(quickfix.field.YieldType.FIELD, quickfix.field.YieldType.CLOSING_YIELD_MOST_RECENT_MONTH);
        component.setDouble(quickfix.field.Yield.FIELD, 22.456);
    }

    public void populate(YieldData component) {
        component.setYieldType(YieldType.ClosingYieldMostRecentMonth);
        component.setYield(new Double(21.555));
    }

    public void check(YieldData expected, quickfix.fix43.component.YieldData actual) throws Exception {
        assertEquals(expected.getYieldType().getValue(), actual.getString(quickfix.field.YieldType.FIELD));
        assertEquals(expected.getYield().doubleValue(), actual.getDouble(quickfix.field.Yield.FIELD), 0.001);
    }

    public void check(quickfix.fix43.component.YieldData expected, YieldData actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.YieldType.FIELD), actual.getYieldType().getValue());
        assertEquals(expected.getDouble(quickfix.field.Yield.FIELD), actual.getYield().doubleValue(), 0.001);
    }

    public void check(YieldData expected, YieldData actual) throws Exception {
        assertEquals(expected.getYieldType().getValue(), actual.getYieldType().getValue());
        assertEquals(expected.getYield().doubleValue(), actual.getYield().doubleValue(), 0.001);
    }
}
