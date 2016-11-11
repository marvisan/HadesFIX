/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportRequestAckMsg44TestData.java
 *
 * $Id: TradeCaptureReportRequestAckMsg44TestData.java,v 1.1 2011-10-08 08:43:04 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportRequestAckMsg;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradeRequestResult;
import net.hades.fix.message.type.TradeRequestStatus;
import net.hades.fix.message.type.TradeRequestType;

/**
 * Test utility for TradeCaptureReportRequestAckMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class TradeCaptureReportRequestAckMsg44TestData extends MsgTest {

    private static final TradeCaptureReportRequestAckMsg44TestData INSTANCE;

    static {
        INSTANCE = new TradeCaptureReportRequestAckMsg44TestData();
    }

    public static TradeCaptureReportRequestAckMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradeCaptureReportRequestAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setTradeRequestID("REQ_1");
        msg.setTradeRequestType(TradeRequestType.AllTrades);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setTotNumTradeReports(3);
        msg.setTradeRequestResult(TradeRequestResult.Successful);
        msg.setTradeRequestStatus(TradeRequestStatus.Accepted);
        
        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setNoLegs(2);
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setMultilegReportingType(MultiLegReportingType.MultiLegSecurity);
        msg.setResponseTransportType(ResponseTransportType.Inband);
        msg.setResponseDestination("DEST_9999");
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(TradeCaptureReportRequestAckMsg expected, TradeCaptureReportRequestAckMsg actual) throws Exception {
        assertEquals(expected.getTradeRequestID(), actual.getTradeRequestID());
        assertEquals(expected.getTradeRequestType(), actual.getTradeRequestType());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getTotNumTradeReports(), actual.getTotNumTradeReports());
        assertEquals(expected.getTradeRequestResult(), actual.getTradeRequestResult());
        assertEquals(expected.getTradeRequestStatus(), actual.getTradeRequestStatus());

        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getMultilegReportingType(), actual.getMultilegReportingType());
        assertEquals(expected.getResponseTransportType(), actual.getResponseTransportType());
        assertEquals(expected.getResponseDestination(), actual.getResponseDestination());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
