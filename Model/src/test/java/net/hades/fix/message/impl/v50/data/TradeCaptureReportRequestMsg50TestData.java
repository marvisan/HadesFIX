/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportRequestMsg50TestData.java
 *
 * $Id: TradeCaptureReportRequestMsg50TestData.java,v 1.1 2011-10-08 08:43:04 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportRequestMsg;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.group.impl.v50.TrdCapDtGroup50TestData;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradeHandlingInstr;
import net.hades.fix.message.type.TradeRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;

/**
 * Test utility for TradeCaptureReportRequestMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class TradeCaptureReportRequestMsg50TestData extends MsgTest {

    private static final TradeCaptureReportRequestMsg50TestData INSTANCE;

    static {
        INSTANCE = new TradeCaptureReportRequestMsg50TestData();
    }

    public static TradeCaptureReportRequestMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradeCaptureReportRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setTradeRequestID("REQ_1");
        msg.setTradeID("TRD_998877");
        msg.setSecondaryTradeID("SEC_TRD_98876");
        msg.setFirmTradeID("FIRM_TRD_887766");
        msg.setSecondaryFirmTradeID("SEC_FIRM_TRD_88673565");
        msg.setTradeRequestType(TradeRequestType.AllTrades);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setTradeReportID("TRD_REP_2133");
        msg.setSecondaryTradeReportID("SEC_TRD_REP_5533");
        msg.setExecID("EXEC_111");
        msg.setExecType(ExecType.Replace);
        msg.setOrderID("ORD_123454");
        msg.setClOrdID("CLORD_12345");
        msg.setMatchStatus(MatchStatus.Compared);
        msg.setTrdType(TrdType.Transfer);
        msg.setTrdSubType(TrdSubType.Adjustment);
        msg.setTradeHandlingInstr(TradeHandlingInstr.TradeConfirmation);
        msg.setTransferReason("REASON 1");
        msg.setSecondaryTrdType(SecondaryTrdType.RegularTrade);
        msg.setTradeLinkID("TRD_LINK_1");
        msg.setTrdMatchID("MATC_3333");
        
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());

        msg.setInstrument();
        Instrument50TestData.getInstance().populate(msg.getInstrument());
        
        msg.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(msg.getInstrumentExtension());
        
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setNoLegs(2);
        InstrumentLeg50TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        
        msg.setNoDates(2);
        TrdCapDtGroup50TestData.getInstance().populate1(msg.getTrdCapDtGroups()[0]);
        TrdCapDtGroup50TestData.getInstance().populate2(msg.getTrdCapDtGroups()[1]);
       
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setTimeBracket("BRKT");
        msg.setSide(Side.Buy);
        msg.setMultilegReportingType(MultiLegReportingType.MultiLegSecurity);
        msg.setTradeInputSource("SRC_123");
        msg.setTradeInputDevice("DEV_987");
        msg.setResponseTransportType(ResponseTransportType.Inband);
        msg.setResponseDestination("DEST_9999");
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setMessageEventSource("EVNT_SRC_33");
    }

    public void check(TradeCaptureReportRequestMsg expected, TradeCaptureReportRequestMsg actual) throws Exception {
        assertEquals(expected.getTradeRequestID(), actual.getTradeRequestID());
        assertEquals(expected.getTradeID(), actual.getTradeID());
        assertEquals(expected.getSecondaryTradeID(), actual.getSecondaryTradeID());
        assertEquals(expected.getFirmTradeID(), actual.getFirmTradeID());
        assertEquals(expected.getSecondaryFirmTradeID(), actual.getSecondaryFirmTradeID());
        assertEquals(expected.getTradeRequestType(), actual.getTradeRequestType());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getTradeReportID(), actual.getTradeReportID());
        assertEquals(expected.getSecondaryTradeReportID(), actual.getSecondaryTradeReportID());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getExecType(), actual.getExecType());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        assertEquals(expected.getTrdType(), actual.getTrdType());
        assertEquals(expected.getTrdSubType(), actual.getTrdSubType());
        assertEquals(expected.getTradeHandlingInstr(), actual.getTradeHandlingInstr());
        assertEquals(expected.getTransferReason(), actual.getTransferReason());
        assertEquals(expected.getSecondaryTrdType(), actual.getSecondaryTrdType());
        assertEquals(expected.getTradeLinkID(), actual.getTradeLinkID());
        assertEquals(expected.getTrdMatchID(), actual.getTrdMatchID());
        
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());
        
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
        
        assertEquals(expected.getNoDates(), actual.getNoDates());
        TrdCapDtGroup50TestData.getInstance().check(expected.getTrdCapDtGroups()[0], actual.getTrdCapDtGroups()[0]);
        TrdCapDtGroup50TestData.getInstance().check(expected.getTrdCapDtGroups()[1], actual.getTrdCapDtGroups()[1]);
        
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getTimeBracket(), actual.getTimeBracket());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getMultilegReportingType(), actual.getMultilegReportingType());
        assertEquals(expected.getTradeInputSource(), actual.getTradeInputSource());
        assertEquals(expected.getTradeInputDevice(), actual.getTradeInputDevice());
        assertEquals(expected.getResponseTransportType(), actual.getResponseTransportType());
        assertEquals(expected.getResponseDestination(), actual.getResponseDestination());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getMessageEventSource(), actual.getMessageEventSource());
    }
}
