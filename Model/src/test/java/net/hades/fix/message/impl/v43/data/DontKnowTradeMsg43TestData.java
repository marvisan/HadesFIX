/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DontKnowTradeMsg43TestData.java
 *
 * $Id: DontKnowTradeMsg43TestData.java,v 1.1 2011-01-16 00:47:41 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.DontKnowTradeMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.OrderQtyData43TestData;
import net.hades.fix.message.type.DKReason;
import net.hades.fix.message.type.Side;

/**
 * Test utility for DontKnowTradeMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DontKnowTradeMsg43TestData extends MsgTest {

    private static final DontKnowTradeMsg43TestData INSTANCE;

    static {
        INSTANCE = new DontKnowTradeMsg43TestData();
    }

    public static DontKnowTradeMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DontKnowTradeMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        msg.setOrderID("XXX9374994");
        msg.setExecID("EXEC273663");
        msg.setDKReason(DKReason.Other);

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setSide(Side.Buy);

        msg.setOrderQtyData();
        OrderQtyData43TestData.getInstance().populate(msg.getOrderQtyData());

        msg.setLastQty(23.44d);
        msg.setLastPx(33.33d);
        msg.setText("some text here");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(DontKnowTradeMsg expected, DontKnowTradeMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getDKReason(), actual.getDKReason());

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getSide(), actual.getSide());

        OrderQtyData43TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
