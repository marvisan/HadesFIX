/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportMsg43TestData.java
 *
 * $Id: TradeCaptureReportMsg43TestData.java,v 1.1 2011-10-25 08:29:21 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.OrderQtyData43TestData;
import net.hades.fix.message.group.impl.v43.TrdCapRptSideGroup43TestData;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.TradeReportTransType;

/**
 * Test utility for TradeCaptureReportMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class TradeCaptureReportMsg43TestData extends MsgTest {

    private static final TradeCaptureReportMsg43TestData INSTANCE;

    static {
        INSTANCE = new TradeCaptureReportMsg43TestData();
    }

    public static TradeCaptureReportMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradeCaptureReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setTradeReportID("REP_999999");
        msg.setTradeReportTransType(TradeReportTransType.Replace);
        msg.setTradeRequestID("REQ_1");
        msg.setExecType(ExecType.Replace);
        msg.setTradeReportRefID("REP_REF_5555");
        msg.setExecID("EXEC_111");
        msg.setSecondaryExecID("SEC_EXEC_888");
        msg.setExecRestatementReason(ExecRestatementReason.Canceled);
        msg.setPreviouslyReported(Boolean.TRUE);

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());
        
        msg.setOrderQtyData();
        OrderQtyData43TestData.getInstance().populate(msg.getOrderQtyData());
        
        msg.setLastQty(23.40d);
        msg.setLastPx(123.55d);
        msg.setLastSpotRate(23.5d);
        msg.setLastForwardPoints(24.3d);
        msg.setLastMkt("CBOT");
        cal.set(2011, 10, 20, 9, 15, 50);
        msg.setTradeDate(cal.getTime());
        cal.set(2011, 10, 20, 9, 20, 13);
        msg.setTransactTime(cal.getTime());
        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2011, 9, 21, 10, 11, 44);
        msg.setSettlDate(cal.getTime());
        msg.setMatchStatus(MatchStatus.Compared);
        msg.setMatchType(MatchType.AutoMatch);
        
        msg.setNoSides(2);
        TrdCapRptSideGroup43TestData.getInstance().populate1(msg.getTrdCapRptSideGroups()[0]);
        TrdCapRptSideGroup43TestData.getInstance().populate2(msg.getTrdCapRptSideGroups()[1]);
    }

    public void check(TradeCaptureReportMsg expected, TradeCaptureReportMsg actual) throws Exception {
        assertEquals(expected.getTradeReportID(), actual.getTradeReportID());
        assertEquals(expected.getTradeReportTransType(), actual.getTradeReportTransType());
        assertEquals(expected.getTradeRequestID(), actual.getTradeRequestID());
        assertEquals(expected.getExecType(), actual.getExecType());
        assertEquals(expected.getTradeReportRefID(), actual.getTradeReportRefID());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getSecondaryExecID(), actual.getSecondaryExecID());
        assertEquals(expected.getExecRestatementReason(), actual.getExecRestatementReason());
        assertEquals(expected.getPreviouslyReported(), actual.getPreviouslyReported());

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        OrderQtyData43TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getLastSpotRate(), actual.getLastSpotRate());
        assertEquals(expected.getLastForwardPoints(), actual.getLastForwardPoints());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        assertEquals(expected.getMatchType(), actual.getMatchType());
        
        assertEquals(expected.getNoSides(), actual.getNoSides());
        TrdCapRptSideGroup43TestData.getInstance().check(expected.getTrdCapRptSideGroups()[0], actual.getTrdCapRptSideGroups()[0]);
        TrdCapRptSideGroup43TestData.getInstance().check(expected.getTrdCapRptSideGroups()[1], actual.getTrdCapRptSideGroups()[1]);
    }
}
