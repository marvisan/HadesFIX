/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DontKnowTradeMsg44TestData.java
 *
 * $Id: DontKnowTradeMsg44TestData.java,v 1.1 2011-01-16 00:47:42 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.DontKnowTradeMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.OrderQtyData44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.type.DKReason;
import net.hades.fix.message.type.Side;

/**
 * Test utility for DontKnowTradeMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DontKnowTradeMsg44TestData extends MsgTest {

    private static final DontKnowTradeMsg44TestData INSTANCE;

    static {
        INSTANCE = new DontKnowTradeMsg44TestData();
    }

    public static DontKnowTradeMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DontKnowTradeMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setOrderID("XXX9374994");
        msg.setSecondaryOrderID("SEC7374494");
        msg.setExecID("EXEC273663");
        msg.setDKReason(DKReason.Other);

        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());

        msg.setNoUnderlyings(2);
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setSide(Side.Buy);

        msg.setOrderQtyData();
        OrderQtyData44TestData.getInstance().populate(msg.getOrderQtyData());

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
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getDKReason(), actual.getDKReason());

        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getSide(), actual.getSide());

        OrderQtyData44TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
