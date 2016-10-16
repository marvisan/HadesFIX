/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionMsg41TestData.java
 *
 * $Id: AllocationInstructionMsg41TestData.java,v 1.2 2011-10-29 09:42:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.AllocationInstructionMsg;
import net.hades.fix.message.group.impl.v41.AllocGroup41TestData;
import net.hades.fix.message.group.impl.v41.ExecAllocGroup41TestData;
import net.hades.fix.message.group.impl.v41.OrderAllocGroup41TestData;
import net.hades.fix.message.type.AllocLinkType;
import net.hades.fix.message.type.AllocTransType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for AllocationInstructionMsg41 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class AllocationInstructionMsg41TestData extends MsgTest {

    private static final AllocationInstructionMsg41TestData INSTANCE;

    static {
        INSTANCE = new AllocationInstructionMsg41TestData();
    }

    public static AllocationInstructionMsg41TestData getInstance() {
        return INSTANCE;
    }

    public void populate(AllocationInstructionMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setAllocID("ALLOC775555");
        msg.setAllocTransType(AllocTransType.Calculated);
        msg.setRefAllocID("REF112233");
        msg.setAllocLinkID("LK223344");
        msg.setAllocLinkType(AllocLinkType.FXNetting);

        msg.setNoOrders(2);
        OrderAllocGroup41TestData.getInstance().populate1(msg.getOrderAllocGroups()[0]);
        OrderAllocGroup41TestData.getInstance().populate2(msg.getOrderAllocGroups()[1]);

        msg.setNoExecs(2);
        ExecAllocGroup41TestData.getInstance().populate1(msg.getExecAllocGroups()[0]);
        ExecAllocGroup41TestData.getInstance().populate2(msg.getExecAllocGroups()[1]);

        msg.setSide(Side.Buy);
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
        msg.setQuantity(23.00d);
        msg.setAvgPx(12.2d);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setAvgPxPrecision(2);
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 11, 13, 14, 22);
        msg.setSettlDate(cal.getTime());
        msg.setNetMoney(22.33d);
        msg.setPositionEffect(PositionEffect.Close);
        msg.setText("some text");
        msg.setNumDaysInterest(2);
        msg.setAccruedInterestRate(12.5D);

        msg.setNoAllocs(2);
        AllocGroup41TestData.getInstance().populate1(msg.getAllocGroups()[0]);
        AllocGroup41TestData.getInstance().populate2(msg.getAllocGroups()[1]);
    }

    public void check(AllocationInstructionMsg expected, AllocationInstructionMsg actual) throws Exception {
        assertEquals(expected.getAllocID(), actual.getAllocID());
        assertEquals(expected.getAllocTransType(), actual.getAllocTransType());
        assertEquals(expected.getRefAllocID(), actual.getRefAllocID());
        assertEquals(expected.getAllocLinkID(), actual.getAllocLinkID());
        assertEquals(expected.getAllocLinkType(), actual.getAllocLinkType());

        assertEquals(expected.getNoOrders(), actual.getNoOrders());
        OrderAllocGroup41TestData.getInstance().check(expected.getOrderAllocGroups()[0], actual.getOrderAllocGroups()[0]);
        OrderAllocGroup41TestData.getInstance().check(expected.getOrderAllocGroups()[1], actual.getOrderAllocGroups()[1]);

        assertEquals(expected.getNoExecs(), actual.getNoExecs());
        ExecAllocGroup41TestData.getInstance().check(expected.getExecAllocGroups()[0], actual.getExecAllocGroups()[0]);
        ExecAllocGroup41TestData.getInstance().check(expected.getExecAllocGroups()[1], actual.getExecAllocGroups()[1]);

        assertEquals(expected.getSide(), actual.getSide());
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
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getAvgPxPrecision(), actual.getAvgPxPrecision());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getNetMoney(), actual.getNetMoney());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getNumDaysInterest(), actual.getNumDaysInterest());
        assertEquals(expected.getAccruedInterestRate(), actual.getAccruedInterestRate());

        assertEquals(expected.getNoAllocs(), actual.getNoAllocs());
        AllocGroup41TestData.getInstance().check(expected.getAllocGroups()[0], actual.getAllocGroups()[0]);
        AllocGroup41TestData.getInstance().check(expected.getAllocGroups()[1], actual.getAllocGroups()[1]);
    }
}
