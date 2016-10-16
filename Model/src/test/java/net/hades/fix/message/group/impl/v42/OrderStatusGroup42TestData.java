/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderStatusGroup42TestData.java
 *
 * $Id: OrderStatusGroup42TestData.java,v 1.1 2011-02-04 09:58:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.OrderStatusGroup;
import net.hades.fix.message.type.OrdRejReason;
import net.hades.fix.message.type.OrdStatus;

/**
 * Test utility for OrderStatusGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class OrderStatusGroup42TestData extends MsgTest {

    private static final OrderStatusGroup42TestData INSTANCE;

    static {
        INSTANCE = new OrderStatusGroup42TestData();
    }

    public static OrderStatusGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(OrderStatusGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD53773");
        grp.setCumQty(23.33d);
        grp.setOrdStatus(OrdStatus.Filled);
        grp.setLeavesQty(25.66d);
        grp.setCxlQty(23.40d);
        grp.setAvgPx(22.33d);
        grp.setOrdRejReason(OrdRejReason.BrokerOption);
        grp.setText("text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedText(encodedText);
    }

    public void populate2(OrderStatusGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD53543");
        grp.setCumQty(33.33d);
        grp.setOrdStatus(OrdStatus.New);
        grp.setLeavesQty(37.66d);
        grp.setCxlQty(34.40d);
        grp.setAvgPx(32.33d);
        grp.setOrdRejReason(OrdRejReason.ExchangeClosed);
        grp.setText("text");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 22, (byte) 66,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedText(encodedText);
    }

    public void check(OrderStatusGroup expected, OrderStatusGroup actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getCumQty(), actual.getCumQty());
        assertEquals(expected.getOrdStatus(), actual.getOrdStatus());
        assertEquals(expected.getLeavesQty(), actual.getLeavesQty());
        assertEquals(expected.getCxlQty(), actual.getCxlQty());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
        assertEquals(expected.getOrdRejReason(), actual.getOrdRejReason());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
