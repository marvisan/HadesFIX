/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCancelRejectMsg50SP1TestData.java
 *
 * $Id: OrderCancelRejectMsg50SP1TestData.java,v 1.1 2011-01-23 10:02:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;
import java.util.Calendar;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderCancelRejectMsg;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.CxlRejReason;
import net.hades.fix.message.type.CxlRejResponseTo;
import net.hades.fix.message.type.OrdStatus;

/**
 * Test utility for OrderCancelRejectMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderCancelRejectMsg50SP1TestData extends MsgTest {

    private static final OrderCancelRejectMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new OrderCancelRejectMsg50SP1TestData();
    }

    public static OrderCancelRejectMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderCancelRejectMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setOrderID("ORD33334");
        msg.setSecondaryOrderID("SSS778877");
        msg.setSecondaryClOrdID("SEC7364883");
        msg.setClOrdID("AAA564567");
        msg.setClOrdLinkID("ORDLINK213131");
        msg.setOrigClOrdID("COD003883");
        msg.setOrdStatus(OrdStatus.Canceled);
        msg.setWorkingIndicator(Boolean.TRUE);
        cal.set(2010, 3, 12, 33, 45, 54);
        msg.setOrigOrdModTime(cal.getTime());
        msg.setListID("LST99374744");
        msg.setAccount("12735534784");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.FloorTrader);
        cal.set(2010, 4, 15, 11, 12, 13);
        msg.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 55, 55);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 56, 56);
        msg.setTransactTime(cal.getTime());
        msg.setCxlRejResponseTo(CxlRejResponseTo.OrderCancelRequest);
        msg.setCxlRejReason(CxlRejReason.BrokerOption);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(OrderCancelRejectMsg expected, OrderCancelRejectMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getOrdStatus(), actual.getOrdStatus());
        assertEquals(expected.getWorkingIndicator(), actual.getWorkingIndicator());
        assertUTCTimestampEquals(expected.getOrigOrdModTime(), actual.getOrigOrdModTime(), false);
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getCxlRejReason(), actual.getCxlRejReason());
        assertEquals(expected.getCxlRejReason(), actual.getCxlRejReason());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
