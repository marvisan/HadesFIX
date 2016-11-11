/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfirmationRequestMsg44TestData.java
 *
 * $Id: ConfirmationRequestMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ConfirmationRequestMsg;
import net.hades.fix.message.group.impl.v44.OrderAllocGroup44TestData;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AllocAccountType;
import net.hades.fix.message.type.ConfirmType;

/**
 * Test utility for ConfirmationRequestMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ConfirmationRequestMsg44TestData extends MsgTest {

    private static final ConfirmationRequestMsg44TestData INSTANCE;

    static {
        INSTANCE = new ConfirmationRequestMsg44TestData();
    }

    public static ConfirmationRequestMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ConfirmationRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setConfirmReqID("CONF_REQ_5555");
        msg.setConfirmType(ConfirmType.Confirmation);

        msg.setNoOrders(2);
        OrderAllocGroup44TestData.getInstance().populate1(msg.getOrderAllocGroups()[0]);
        OrderAllocGroup44TestData.getInstance().populate2(msg.getOrderAllocGroups()[1]);

        msg.setAllocID("ALLOC775555");
        msg.setSecondaryAllocID("SECALL2222");
        msg.setIndividualAllocID("IND_ALLOC_0000");
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setAllocAccount("72634637632");
        msg.setAllocAcctIDSource(AcctIDSource.SID);
        msg.setAllocAccountType(AllocAccountType.FloorTrader);
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(ConfirmationRequestMsg expected, ConfirmationRequestMsg actual) throws Exception {
        assertEquals(expected.getConfirmReqID(), actual.getConfirmReqID());
        assertEquals(expected.getConfirmType(), actual.getConfirmType());

        assertEquals(expected.getNoOrders(), actual.getNoOrders());
        OrderAllocGroup44TestData.getInstance().check(expected.getOrderAllocGroups()[0], actual.getOrderAllocGroups()[0]);
        OrderAllocGroup44TestData.getInstance().check(expected.getOrderAllocGroups()[1], actual.getOrderAllocGroups()[1]);

        assertEquals(expected.getAllocID(), actual.getAllocID());
        assertEquals(expected.getSecondaryAllocID(), actual.getSecondaryAllocID());
        assertEquals(expected.getIndividualAllocID(), actual.getIndividualAllocID());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getAllocAccountType(), actual.getAllocAccountType());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
