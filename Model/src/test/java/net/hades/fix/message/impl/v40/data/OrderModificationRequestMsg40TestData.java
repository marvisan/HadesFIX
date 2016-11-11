/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderModificationRequestMsg40TestData.java
 *
 * $Id: OrderModificationRequestMsg40TestData.java,v 1.2 2011-10-29 09:42:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderModificationRequestMsg;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for OrderModificationRequestMsg40 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderModificationRequestMsg40TestData extends MsgTest {

    private static final OrderModificationRequestMsg40TestData INSTANCE;

    static {
        INSTANCE = new OrderModificationRequestMsg40TestData();
    }

    public static OrderModificationRequestMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderModificationRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setOrderID("ORD33334");
        msg.setClientID("client");
        msg.setExecBroker("broker");
        msg.setOrigClOrdID("ORIORD35835");
        msg.setClOrdID("AAA564567");
        msg.setListID("LST99374744");
        msg.setAccount("12735534784");
        msg.setSettlType(SettlType.Cash.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 3, 14, 12, 15, 33);
        msg.setSettlDate(cal.getTime());
        msg.setHandlInst(HandlInst.ManualOrder);
        msg.setExecInst(ExecInst.CallFirst.getValue());
        msg.setMinQty(12.44d);
        msg.setMaxFloor(33.66d);
        msg.setExDestination("exchange");
        msg.setSymbol("BHP.AX");
        msg.setSymbolSfx("CDDF");
        msg.setSecurityID("BHP");
        msg.setSecurityIDSource("BHP-src");
        msg.setIssuer("issuer");
        msg.setSecurityDesc("description");
        msg.setSide(Side.Buy);
        msg.setOrderQty(88.45d);
        msg.setOrdType(OrdType.Limit);
        msg.setPrice(50.67d);
        msg.setStopPx(51.67d);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 12, 30, 44);
        msg.setExpireTime(cal.getTime());
        msg.setCommission(1.34d);
        msg.setCommType(CommType.Absolute);
        msg.setRule80A(Rule80A.Principal);
        msg.setForexReq(Boolean.FALSE);
        msg.setSettlCurrency(Currency.CanadianDollar);
        msg.setText("text");
    }

    public void check(OrderModificationRequestMsg expected, OrderModificationRequestMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getExDestination(), actual.getExDestination());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getCommission(), actual.getCommission());
        assertEquals(expected.getCommType(), actual.getCommType());
        assertEquals(expected.getRule80A(), actual.getRule80A());
        assertEquals(expected.getForexReq(), actual.getForexReq());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getText(), actual.getText());
    }
}
