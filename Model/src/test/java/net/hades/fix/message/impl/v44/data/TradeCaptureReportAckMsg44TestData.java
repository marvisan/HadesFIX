/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportAckMsg44TestData.java
 *
 * $Id: TradeCaptureReportAckMsg44TestData.java,v 1.1 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportAckMsg;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.group.impl.v44.TradeAllocGroup44TestData;
import net.hades.fix.message.group.impl.v44.TrdInstrmtLegGroup44TestData;
import net.hades.fix.message.group.impl.v44.TrdRegTimestamps44TestData;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradeReportRejectReason;
import net.hades.fix.message.type.TradeReportTransType;
import net.hades.fix.message.type.TradeReportType;
import net.hades.fix.message.type.TrdRptStatus;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;

/**
 * Test utility for TradeCaptureReportAckMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class TradeCaptureReportAckMsg44TestData extends MsgTest {

    private static final TradeCaptureReportAckMsg44TestData INSTANCE;

    static {
        INSTANCE = new TradeCaptureReportAckMsg44TestData();
    }

    public static TradeCaptureReportAckMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradeCaptureReportAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setTradeReportID("REP_999999");
        msg.setTradeReportTransType(TradeReportTransType.Replace);
        msg.setTradeReportType(TradeReportType.TradeReportCancel);
        msg.setTrdType(TrdType.Transfer);
        msg.setTrdSubType(TrdSubType.OTCQuote);
        msg.setSecondaryTrdType(SecondaryTrdType.RegularTrade);
        msg.setTransferReason("reason to transfer");
        msg.setExecType(ExecType.Replace);
        msg.setTradeReportRefID("REP_REF_5555");
        msg.setSecondaryTradeReportRefID("SEC_TRD_REF_2222");
        msg.setTrdRptStatus(TrdRptStatus.Accepted);
        msg.setTradeReportRejectReason(TradeReportRejectReason.Successful.getValue());
        msg.setSecondaryTradeReportID("SEC_RPT_8888");
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setTradeLinkID("TRD_LINK_0000");
        msg.setTrdMatchID("TRD_MATCH_8888");
        msg.setExecID("EXEC_111");
        msg.setSecondaryExecID("SEC_EXEC_888");

        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        
        cal.set(2011, 9, 20, 9, 11, 50);
        msg.setTransactTime(cal.getTime());
       
        msg.setNoLegs(2);
        TrdInstrmtLegGroup44TestData.getInstance().populate1(msg.getTrdInstrmtLegGroups()[0]);
        TrdInstrmtLegGroup44TestData.getInstance().populate2(msg.getTrdInstrmtLegGroups()[1]);
         
        msg.setNoTrdRegTimestamps(2);
        TrdRegTimestamps44TestData.getInstance().populate1(msg.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps44TestData.getInstance().populate2(msg.getTrdRegTimestampsGroups()[1]);

        msg.setResponseTransportType(ResponseTransportType.Inband);
        msg.setResponseDestination("To Someone");
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setClearingFeeIndicator(ClearingFeeIndicator.TradingOwnAcct1stYear);
        
        msg.setNoAllocs(2);
        TradeAllocGroup44TestData.getInstance().populate1(msg.getAllocGroups()[0]);
        TradeAllocGroup44TestData.getInstance().populate2(msg.getAllocGroups()[1]);
    }

    public void check(TradeCaptureReportAckMsg expected, TradeCaptureReportAckMsg actual) throws Exception {
        assertEquals(expected.getTradeReportID(), actual.getTradeReportID());
        assertEquals(expected.getTradeReportTransType(), actual.getTradeReportTransType());
        assertEquals(expected.getTradeReportType(), actual.getTradeReportType());
        assertEquals(expected.getTrdType(), actual.getTrdType());
        assertEquals(expected.getTrdSubType(), actual.getTrdSubType());
        assertEquals(expected.getSecondaryTrdType(), actual.getSecondaryTrdType());
        assertEquals(expected.getTransferReason(), actual.getTransferReason());
        assertEquals(expected.getExecType(), actual.getExecType());
        assertEquals(expected.getTradeReportRefID(), actual.getTradeReportRefID());
        assertEquals(expected.getSecondaryTradeReportRefID(), actual.getSecondaryTradeReportRefID());
        assertEquals(expected.getTrdRptStatus(), actual.getTrdRptStatus());
        assertEquals(expected.getTradeReportRejectReason(), actual.getTradeReportRejectReason());
        assertEquals(expected.getSecondaryTradeReportID(), actual.getSecondaryTradeReportID());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getTradeLinkID(), actual.getTradeLinkID());
        assertEquals(expected.getTrdMatchID(), actual.getTrdMatchID());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getSecondaryExecID(), actual.getSecondaryExecID());

        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
                  
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        TrdInstrmtLegGroup44TestData.getInstance().check(expected.getTrdInstrmtLegGroups()[0], actual.getTrdInstrmtLegGroups()[0]);
        TrdInstrmtLegGroup44TestData.getInstance().check(expected.getTrdInstrmtLegGroups()[1], actual.getTrdInstrmtLegGroups()[1]);

        assertEquals(expected.getNoTrdRegTimestamps(), actual.getNoTrdRegTimestamps());
        TrdRegTimestamps44TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[0], actual.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps44TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[1], actual.getTrdRegTimestampsGroups()[1]);

        assertEquals(expected.getResponseTransportType(), actual.getResponseTransportType());
        assertEquals(expected.getResponseDestination(), actual.getResponseDestination());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getClearingFeeIndicator(), actual.getClearingFeeIndicator());
        
        assertEquals(expected.getNoAllocs(), actual.getNoAllocs());
        TradeAllocGroup44TestData.getInstance().check(expected.getAllocGroups()[0], actual.getAllocGroups()[0]);
        TradeAllocGroup44TestData.getInstance().check(expected.getAllocGroups()[1], actual.getAllocGroups()[1]);
    }
}
