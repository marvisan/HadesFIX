/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportRequestMsg43TestData.java
 *
 * $Id: TradeCaptureReportRequestMsg43TestData.java,v 1.1 2011-10-08 08:43:04 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportRequestMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.group.impl.v43.TrdCapDtGroup43TestData;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradeRequestType;

/**
 * Test utility for TradeCaptureReportRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class TradeCaptureReportRequestMsg43TestData extends MsgTest {

    private static final TradeCaptureReportRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new TradeCaptureReportRequestMsg43TestData();
    }

    public static TradeCaptureReportRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradeCaptureReportRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setTradeRequestID("REQ_1");
        msg.setTradeRequestType(TradeRequestType.AllTrades);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setExecID("EXEC_111");
        msg.setOrderID("ORD_123454");
        msg.setClOrdID("CLORD_12345");
        msg.setMatchStatus(MatchStatus.Compared);
        
        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());
        
        msg.setNoDates(2);
        TrdCapDtGroup43TestData.getInstance().populate1(msg.getTrdCapDtGroups()[0]);
        TrdCapDtGroup43TestData.getInstance().populate2(msg.getTrdCapDtGroups()[1]);
       
        msg.setSide(Side.Buy);
        msg.setTradeInputSource("SRC_123");
        msg.setTradeInputDevice("DEV_987");
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(TradeCaptureReportRequestMsg expected, TradeCaptureReportRequestMsg actual) throws Exception {
        assertEquals(expected.getTradeRequestID(), actual.getTradeRequestID());
        assertEquals(expected.getTradeRequestType(), actual.getTradeRequestType());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getNoDates(), actual.getNoDates());
        TrdCapDtGroup43TestData.getInstance().check(expected.getTrdCapDtGroups()[0], actual.getTrdCapDtGroups()[0]);
        TrdCapDtGroup43TestData.getInstance().check(expected.getTrdCapDtGroups()[1], actual.getTrdCapDtGroups()[1]);
        
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getTradeInputSource(), actual.getTradeInputSource());
        assertEquals(expected.getTradeInputDevice(), actual.getTradeInputDevice());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
