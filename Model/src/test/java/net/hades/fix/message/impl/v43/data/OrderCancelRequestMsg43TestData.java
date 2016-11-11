/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCancelRequestMsg43TestData.java
 *
 * $Id: OrderCancelRequestMsg43TestData.java,v 1.1 2011-01-22 09:52:24 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderCancelRequestMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.OrderQtyData43TestData;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.Side;

/**
 * Test utility for OrderCancelRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderCancelRequestMsg43TestData extends MsgTest {

    private static final OrderCancelRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new OrderCancelRequestMsg43TestData();
    }

    public static OrderCancelRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderCancelRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setOrigClOrdID("ORIORD35835");
        msg.setOrderID("ORD33334");
        msg.setClOrdID("AAA564567");
        msg.setSecondaryClOrdID("BBB363744");
        msg.setClOrdLinkID("SA88767788");
        msg.setListID("LST99374744");
        cal.set(2010, 3, 14, 33, 33, 33);
        msg.setOrigOrdModTime(cal.getTime());
        msg.setAccount("12735534784");
        msg.setAccountType(AccountType.HouseTrader);

        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setDayBookingInst(DayBookingInst.Accumulate);
        msg.setClientID("client");
        msg.setExecBroker("broker");
        msg.setSide(Side.Buy);
        cal.set(2010, 3, 14, 15, 18, 32);
        msg.setTransactTime(cal.getTime());

        msg.setOrderQtyData();
        OrderQtyData43TestData.getInstance().populate(msg.getOrderQtyData());

        msg.setComplianceID("compl ID");
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(OrderCancelRequestMsg expected, OrderCancelRequestMsg actual) throws Exception {
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());
        assertEquals(expected.getListID(), actual.getListID());
        assertUTCTimestampEquals(expected.getOrigOrdModTime(), actual.getOrigOrdModTime(), false);
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAccountType(), actual.getAccountType());

        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getDayBookingInst(), actual.getDayBookingInst());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getSide(), actual.getSide());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);

        OrderQtyData43TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
