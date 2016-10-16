/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderQtyData43TestData.java
 *
 * $Id: OrderQtyData43TestData.java,v 1.1 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import quickfix.Message;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.type.RoundingDirection;

/**
 * Test utility for OrderQtyData43 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 3:30:53 PM
 */
public class OrderQtyData43TestData extends MsgTest {

    private static final OrderQtyData43TestData INSTANCE;

    static {
        INSTANCE = new OrderQtyData43TestData();
    }

    public static OrderQtyData43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderQtyData component) {
        component.setOrderQty(new Double(1.666));
        component.setCashOrderQty(new Double(1.999));
        component.setOrderPercent(new Double(55.888));
        component.setRoundingDirection(RoundingDirection.RoundDown);
        component.setRoundingModulus(new Double(2.9));
    }

    public void check(OrderQtyData expected, OrderQtyData actual) throws Exception {
        assertEquals(expected.getOrderQty().doubleValue(), actual.getOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getCashOrderQty().doubleValue(), actual.getCashOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getOrderPercent().doubleValue(), actual.getOrderPercent().doubleValue(), 0.001);
        assertEquals(expected.getRoundingDirection().getValue(), actual.getRoundingDirection().getValue());
        assertEquals(expected.getRoundingModulus().doubleValue(), actual.getRoundingModulus().doubleValue(), 0.001);
    }
}
