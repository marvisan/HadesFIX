/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelRequestMsg50SP1TestData.java
 *
 * $Id: OrderMassCancelRequestMsg50SP1TestData.java,v 1.2 2011-05-07 06:58:53 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderMassCancelRequestMsg;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.type.MassCancelRequestType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for OrderMassCancelRequestMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderMassCancelRequestMsg50SP1TestData extends MsgTest {

    private static final OrderMassCancelRequestMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new OrderMassCancelRequestMsg50SP1TestData();
    }

    public static OrderMassCancelRequestMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderMassCancelRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setClOrdID("AAA564567");
        msg.setSecondaryClOrdID("BBB363744");
        msg.setMassCancelRequestType(MassCancelRequestType.CancelAllOrders);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setMarketID("Commodities");
        msg.setMarketSegmentID("seg 1");
        
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());

        msg.setInstrument();
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());
        
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setSide(Side.Buy);
        cal.set(2010, 3, 14, 15, 18, 32);
        msg.setTransactTime(cal.getTime());
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(OrderMassCancelRequestMsg expected, OrderMassCancelRequestMsg actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getMassCancelRequestType(), actual.getMassCancelRequestType());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getMarketID(), actual.getMarketID());
        assertEquals(expected.getMarketSegmentID(), actual.getMarketSegmentID());

        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        Instrument50SP1TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getSide(), actual.getSide());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
