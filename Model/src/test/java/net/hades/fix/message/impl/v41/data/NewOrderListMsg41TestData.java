/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderListMsg41TestData.java
 *
 * $Id: NewOrderListMsg41TestData.java,v 1.2 2011-10-29 09:42:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewOrderListMsg;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustomerOrFirm;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for NewOrderListMsg41 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class NewOrderListMsg41TestData extends MsgTest {

    private static final NewOrderListMsg41TestData INSTANCE;

    static {
        INSTANCE = new NewOrderListMsg41TestData();
    }

    public static NewOrderListMsg41TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NewOrderListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setListID("LST564567");
        msg.setListExecInst("INS-SHARE");
        msg.setTotNoOrders(3);
        msg.setWaveNo("WAVE-635454");
        msg.setListSeqNo(3);
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
        msg.setSecurityType(SecurityType.Cash.getValue());
        msg.setMaturityMonthYear("022010");
        msg.setMaturityDay(2);
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(12.55d);
        msg.setOptAttribute('A');
        msg.setSecurityExchange("ASX");
        msg.setIssuer("issuer");
        msg.setSecurityDesc("description");
        msg.setPrevClosePx(29.45d);
        msg.setSide(Side.Buy);
        msg.setLocateReqd(Boolean.TRUE);
        msg.setOrderQty(88.45d);
        msg.setOrdType(OrdType.Limit);
        msg.setPrice(50.67d);
        msg.setStopPx(51.67d);
        msg.setPegOffsetValue(0.65d);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 12, 30, 44);
        msg.setExpireTime(cal.getTime());
        msg.setCommission(1.34d);
        msg.setCommType(CommType.Absolute);
        msg.setRule80A(Rule80A.Principal);
        msg.setForexReq(Boolean.FALSE);
        msg.setText("text");
        cal.set(2010, 3, 14, 17, 30, 0);
        msg.setSettlDate2(cal.getTime());
        msg.setOrderQty2(25.0d);
        msg.setPositionEffect(PositionEffect.Close);
        msg.setCoveredOrUncovered(CoveredOrUncovered.Uncovered);
        msg.setCustomerOrFirm(CustomerOrFirm.Customer);
        msg.setMaxShow(15.35d);
    }

    public void check(NewOrderListMsg expected, NewOrderListMsg actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getListExecInst(), actual.getListExecInst());
        assertEquals(expected.getTotNoOrders(), actual.getTotNoOrders());
        assertEquals(expected.getWaveNo(), actual.getWaveNo());
        assertEquals(expected.getListSeqNo(), actual.getListSeqNo());
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
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay(), actual.getMaturityDay());
        assertEquals(expected.getPutOrCall(), actual.getPutOrCall());
        assertEquals(expected.getStrikePrice(), actual.getStrikePrice());
        assertEquals(expected.getOptAttribute(), actual.getOptAttribute());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
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
        assertEquals(expected.getPegOffsetValue(), actual.getPegOffsetValue());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getCommission(), actual.getCommission());
        assertEquals(expected.getCommType(), actual.getCommType());
        assertEquals(expected.getRule80A(), actual.getRule80A());
        assertEquals(expected.getForexReq(), actual.getForexReq());
        assertEquals(expected.getText(), actual.getText());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2(), actual.getOrderQty2());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getCoveredOrUncovered(), actual.getCoveredOrUncovered());
        assertEquals(expected.getCustomerOrFirm(), actual.getCustomerOrFirm());
        assertEquals(expected.getMaxShow(), actual.getMaxShow());
    }
}
