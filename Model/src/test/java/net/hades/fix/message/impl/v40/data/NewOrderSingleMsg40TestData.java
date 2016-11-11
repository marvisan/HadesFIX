/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderSingleMsg40TestData.java
 *
 * $Id: NewOrderSingleMsg40TestData.java,v 1.3 2011-10-29 09:42:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewOrderSingleMsg;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for MarketDataRequest42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class NewOrderSingleMsg40TestData extends MsgTest {

    private static final NewOrderSingleMsg40TestData INSTANCE;

    static {
        INSTANCE = new NewOrderSingleMsg40TestData();
    }

    public static NewOrderSingleMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NewOrderSingleMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setClOrdID("AAA564567");
        msg.setClientID("client");
        msg.setExecBroker("broker");
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
        msg.setProcessCode(ProcessCode.Regular);
        msg.setSymbol("BHP.AX");
        msg.setSymbolSfx("CDDF");
        msg.setSecurityID("BHP");
        msg.setSecurityIDSource("BHP-src");
        msg.setIssuer("issuer");
        msg.setSecurityDesc("description");
        msg.setPrevClosePx(29.45d);
        msg.setSide(Side.Buy);
        msg.setLocateReqd(Boolean.TRUE);
        msg.setOrderQty(88.45d);
        msg.setOrdType(OrdType.Limit);
        msg.setPrice(50.67d);
        msg.setStopPx(51.67d);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setIOIID("X25388405");
        msg.setQuoteID("G93847464");
        msg.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 12, 30, 44);
        msg.setExpireTime(cal.getTime());
        msg.setCommission(1.34d);
        msg.setCommType(CommType.Absolute);
        msg.setRule80A(Rule80A.Principal);
        msg.setForexReq(Boolean.FALSE);
        msg.setText("text");
    }

    public void check(NewOrderSingleMsg expected, NewOrderSingleMsg actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getExDestination(), actual.getExDestination());
        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getPrevClosePx(), actual.getPrevClosePx());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getLocateReqd(), actual.getLocateReqd());
        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getIOIID(), actual.getIOIID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getCommission(), actual.getCommission());
        assertEquals(expected.getCommType(), actual.getCommType());
        assertEquals(expected.getRule80A(), actual.getRule80A());
        assertEquals(expected.getForexReq(), actual.getForexReq());
        assertEquals(expected.getText(), actual.getText());
    }
}
