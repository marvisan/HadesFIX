/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderQtyData44TestData.java
 *
 * $Id: OrderQtyData44TestData.java,v 1.1 2009-07-06 03:18:51 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import java.io.UnsupportedEncodingException;

import quickfix.Message;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.type.RoundingDirection;

/**
 * Test utility for OrderQtyData44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 3:30:53 PM
 */
public class OrderQtyData44TestData extends MsgTest {

    private static final OrderQtyData44TestData INSTANCE;

    static {
        INSTANCE = new OrderQtyData44TestData();
    }

    public static OrderQtyData44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(Message msg) throws UnsupportedEncodingException {
        msg.setDouble(quickfix.field.OrderQty.FIELD, 4.555);
        msg.setDouble(quickfix.field.CashOrderQty.FIELD, 33.55);
        msg.setDouble(quickfix.field.OrderPercent.FIELD, 19.999);
        msg.setChar(quickfix.field.RoundingDirection.FIELD, '0');
        msg.setDouble(quickfix.field.RoundingModulus.FIELD, 9.999);
    }

    public void populate(quickfix.fix44.component.OrderQtyData msg) throws UnsupportedEncodingException {
        msg.setDouble(quickfix.field.OrderQty.FIELD, 4.555);
        msg.setDouble(quickfix.field.CashOrderQty.FIELD, 33.55);
        msg.setDouble(quickfix.field.OrderPercent.FIELD, 19.999);
        msg.setChar(quickfix.field.RoundingDirection.FIELD, '0');
        msg.setDouble(quickfix.field.RoundingModulus.FIELD, 9.999);
    }

    public void populate(OrderQtyData component) {
        component.setOrderQty(new Double(1.666));
        component.setCashOrderQty(new Double(1.999));
        component.setOrderPercent(new Double(55.888));
        component.setRoundingDirection(RoundingDirection.RoundDown);
        component.setRoundingModulus(new Double(2.9));
    }

    public void check(OrderQtyData expected, Message actual) throws Exception {
        assertEquals(expected.getOrderQty().doubleValue(), actual.getDouble(quickfix.field.OrderQty.FIELD), 0.001);
        assertEquals(expected.getCashOrderQty().doubleValue(), actual.getDouble(quickfix.field.CashOrderQty.FIELD), 0.001);
        assertEquals(expected.getOrderPercent().doubleValue(), actual.getDouble(quickfix.field.OrderPercent.FIELD), 0.001);
        assertEquals(expected.getRoundingDirection().getValue(), actual.getChar(quickfix.field.RoundingDirection.FIELD));
        assertEquals(expected.getRoundingModulus().doubleValue(), actual.getDouble(quickfix.field.RoundingModulus.FIELD), 0.001);
    }

    public void check(OrderQtyData expected, quickfix.fix44.component.OrderQtyData actual) throws Exception {
        assertEquals(expected.getOrderQty().doubleValue(), actual.getDouble(quickfix.field.OrderQty.FIELD), 0.001);
        assertEquals(expected.getCashOrderQty().doubleValue(), actual.getDouble(quickfix.field.CashOrderQty.FIELD), 0.001);
        assertEquals(expected.getOrderPercent().doubleValue(), actual.getDouble(quickfix.field.OrderPercent.FIELD), 0.001);
        assertEquals(expected.getRoundingDirection().getValue(), actual.getChar(quickfix.field.RoundingDirection.FIELD));
        assertEquals(expected.getRoundingModulus().doubleValue(), actual.getDouble(quickfix.field.RoundingModulus.FIELD), 0.001);
    }

    public void check(Message expected, OrderQtyData actual) throws Exception {
        assertEquals(expected.getDouble(quickfix.field.OrderQty.FIELD), actual.getOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.CashOrderQty.FIELD), actual.getCashOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OrderPercent.FIELD), actual.getOrderPercent().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.RoundingDirection.FIELD), actual.getRoundingDirection().getValue());
        assertEquals(expected.getDouble(quickfix.field.RoundingModulus.FIELD), actual.getRoundingModulus().doubleValue(), 0.001);
    }

    public void check(quickfix.fix44.component.OrderQtyData expected, OrderQtyData actual) throws Exception {
        assertEquals(expected.getDouble(quickfix.field.OrderQty.FIELD), actual.getOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.CashOrderQty.FIELD), actual.getCashOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OrderPercent.FIELD), actual.getOrderPercent().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.RoundingDirection.FIELD), actual.getRoundingDirection().getValue());
        assertEquals(expected.getDouble(quickfix.field.RoundingModulus.FIELD), actual.getRoundingModulus().doubleValue(), 0.001);
    }

    public void check(OrderQtyData expected, OrderQtyData actual) throws Exception {
        assertEquals(expected.getOrderQty().doubleValue(), actual.getOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getCashOrderQty().doubleValue(), actual.getCashOrderQty().doubleValue(), 0.001);
        assertEquals(expected.getOrderPercent().doubleValue(), actual.getOrderPercent().doubleValue(), 0.001);
        assertEquals(expected.getRoundingDirection().getValue(), actual.getRoundingDirection().getValue());
        assertEquals(expected.getRoundingModulus().doubleValue(), actual.getRoundingModulus().doubleValue(), 0.001);
    }
}
