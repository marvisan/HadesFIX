/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderListMsg44TestData.java
 *
 * $Id: NewOrderListMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewOrderListMsg;
import net.hades.fix.message.group.impl.v44.OrderListGroup44TestData;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ListExecInstType;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.ProgRptReqs;

/**
 * Test utility for NewOrderListMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class NewOrderListMsg44TestData extends MsgTest {

    private static final NewOrderListMsg44TestData INSTANCE;

    static {
        INSTANCE = new NewOrderListMsg44TestData();
    }

    public static NewOrderListMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NewOrderListMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setListID("LST564567");
        msg.setBidID("BID8273633");
        msg.setClientBidID("ID62357383");
        msg.setProgRptReqs(ProgRptReqs.BuySide);
        msg.setBidType(BidType.Disclosed);
        msg.setProgPeriodInterval(3);
        msg.setCancellationRights(CancellationRights.No_ExecutionOnly);
        msg.setMoneyLaunderingStatus(MoneyLaunderingStatus.Exempt_BelowLimit);
        msg.setRegistID("REG7363644");
        msg.setListExecInstType(ListExecInstType.Immediate);
        msg.setListExecInst("INS-SHARE");
        msg.setEncodedListExecInstLen(new Integer(8));
        byte[] encodedListExecInst = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedListExecInst(encodedListExecInst);
        msg.setAllowableOneSidednessPct(22.55d);
        msg.setAllowableOneSidednessValue(2553.45d);
        msg.setAllowableOneSidednessCurr(Currency.UnitedStatesDollar);
        msg.setTotNoOrders(3);
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoOrders(2);
        OrderListGroup44TestData.getInstance().populate1(msg.getOrderListGroups()[0]);
        OrderListGroup44TestData.getInstance().populate2(msg.getOrderListGroups()[1]);
    }

    public void check(NewOrderListMsg expected, NewOrderListMsg actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getBidID(), actual.getBidID());
        assertEquals(expected.getClientBidID(), actual.getClientBidID());
        assertEquals(expected.getProgRptReqs(), actual.getProgRptReqs());
        assertEquals(expected.getBidType(), actual.getBidType());
        assertEquals(expected.getProgPeriodInterval(), actual.getProgPeriodInterval());
        assertEquals(expected.getCancellationRights(), actual.getCancellationRights());
        assertEquals(expected.getMoneyLaunderingStatus(), actual.getMoneyLaunderingStatus());
        assertEquals(expected.getRegistID(), actual.getRegistID());
        assertEquals(expected.getListExecInstType(), actual.getListExecInstType());
        assertEquals(expected.getListExecInst(), actual.getListExecInst());
        assertEquals(expected.getEncodedListExecInstLen().intValue(), actual.getEncodedListExecInstLen().intValue());
        assertArrayEquals(expected.getEncodedListExecInst(), actual.getEncodedListExecInst());
        assertEquals(expected.getAllowableOneSidednessPct(), actual.getAllowableOneSidednessPct());
        assertEquals(expected.getAllowableOneSidednessValue(), actual.getAllowableOneSidednessValue());
        assertEquals(expected.getAllowableOneSidednessCurr(), actual.getAllowableOneSidednessCurr());
        assertEquals(expected.getTotNoOrders(), actual.getTotNoOrders());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());

        assertEquals(expected.getNoOrders(), actual.getNoOrders());
        OrderListGroup44TestData.getInstance().check(expected.getOrderListGroups()[0], actual.getOrderListGroups()[0]);
        OrderListGroup44TestData.getInstance().check(expected.getOrderListGroups()[1], actual.getOrderListGroups()[1]);
     }
}
