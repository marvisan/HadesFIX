/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportMsg50TestData.java
 *
 * $Id: TradeCaptureReportMsg50TestData.java,v 1.1 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportMsg;
import net.hades.fix.message.comp.impl.v44.YieldData44TestData;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50.RootParties50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.group.impl.v50.PosAmtGroup50TestData;
import net.hades.fix.message.group.impl.v50.TrdCapRptSideGroup50TestData;
import net.hades.fix.message.group.impl.v50.TrdInstrmtLegGroup50TestData;
import net.hades.fix.message.group.impl.v50.TrdRegTimestamps50TestData;
import net.hades.fix.message.type.AsOfIndicator;
import net.hades.fix.message.type.AvgPxIndicator;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrderCategory;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.ShortSaleReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradeHandlingInstr;
import net.hades.fix.message.type.TradeReportTransType;
import net.hades.fix.message.type.TradeReportType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;

/**
 * Test utility for TradeCaptureReportMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class TradeCaptureReportMsg50TestData extends MsgTest {

    private static final TradeCaptureReportMsg50TestData INSTANCE;

    static {
        INSTANCE = new TradeCaptureReportMsg50TestData();
    }

    public static TradeCaptureReportMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradeCaptureReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setTradeReportID("REP_999999");
        msg.setTradeID("TRD_ID_2222");
        msg.setSecondaryTradeID("SEC_TRD_ID_9999");
        msg.setFirmTradeID("FIRM_TRD_ID_0000");
        msg.setSecondaryFirmTradeID("SEC_FIRM_TRD_2222");
        msg.setTradeReportTransType(TradeReportTransType.Replace);
        msg.setTradeReportType(TradeReportType.TradeReportCancel);
        msg.setTradeRequestID("REQ_1");
        msg.setTrdType(TrdType.Transfer);
        msg.setTrdSubType(TrdSubType.B);
        msg.setSecondaryTrdType(SecondaryTrdType.RegularTrade);
        msg.setTradeHandlingInstr(TradeHandlingInstr.TradeConfirmation);
        msg.setOrigTradeHandlingInstr(TradeHandlingInstr.TradeConfirmation);
        cal.set(2011, 9, 15, 9, 15, 22);
        msg.setOrigTradeDate(cal.getTime());
        msg.setOrigTradeID("ORIG_TRAD_7777");
        msg.setOrigSecondaryTradeID("ORIG_SEC_TRAD_8888");
        msg.setTransferReason("reason to transfer");
        msg.setExecType(ExecType.Replace);
        msg.setTotNumTradeReports(4);
        msg.setLastRptRequested(Boolean.TRUE);
        msg.setUnsolicitedIndicator(Boolean.TRUE);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setTradeReportRefID("REP_REF_5555");
        msg.setSecondaryTradeReportRefID("SEC_TRD_REF_2222");
        msg.setSecondaryTradeReportID("SEC_RPT_8888");
        msg.setTradeLinkID("TRD_LINK_0000");
        msg.setTrdMatchID("TRD_MATCH_8888");
        msg.setExecID("EXEC_111");
        msg.setOrdStatus(OrdStatus.Canceled);
        msg.setSecondaryExecID("SEC_EXEC_888");
        msg.setExecRestatementReason(ExecRestatementReason.Canceled);
        msg.setPreviouslyReported(Boolean.TRUE);
        msg.setPriceType(PriceType.Percentage);
        
        msg.setRootParties();
        RootParties50TestData.getInstance().populate(msg.getRootParties());
        
        msg.setAsOfIndicator(AsOfIndicator.TradeIsAsOf);
        msg.setSettlSessID("ITD");
        msg.setSettlSessSubID("SETTL_SESS_SUB_9999");

        msg.setInstrument();
        Instrument50TestData.getInstance().populate(msg.getInstrument());
        
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());
        
        msg.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());
        
        msg.setQtyType(QtyType.Units);
        
        msg.setYieldData();
        YieldData44TestData.getInstance().populate(msg.getYieldData());
        
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        
        msg.setUnderlyingTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setUnderlyingTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        msg.setLastQty(23.40d);
        msg.setLastPx(123.55d);
        msg.setCalculatedCcyLastQty(23.56d);
        msg.setLastParPx(43.66d);
        msg.setLastSpotRate(23.5d);
        msg.setLastForwardPoints(23.55d);
        msg.setLastSwapPoints(24.3d);
        msg.setLastMkt("CBOT");
        cal.set(2011, 10, 20, 9, 15, 50);
        msg.setTradeDate(cal.getTime());
        cal.set(2011, 9, 11, 9, 12, 50);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setAvgPx(32.66d);
        
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());
        
        msg.setAvgPxIndicator(AvgPxIndicator.NoAveragePricing);
        
        msg.setNoPosAmt(2);
        PosAmtGroup50TestData.getInstance().populate1(msg.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().populate2(msg.getPosAmtGroups()[1]);
        
        msg.setMultilegReportingType(MultiLegReportingType.MultiLegSecurity);
        msg.setTradeLegRefID("LEG_REF_777777");
        
        msg.setNoLegs(2);
        TrdInstrmtLegGroup50TestData.getInstance().populate1(msg.getTrdInstrmtLegGroups()[0]);
        TrdInstrmtLegGroup50TestData.getInstance().populate2(msg.getTrdInstrmtLegGroups()[1]);
        
        cal.set(2011, 10, 20, 9, 20, 13);
        msg.setTransactTime(cal.getTime());
        
        msg.setNoTrdRegTimestamps(2);
        TrdRegTimestamps50TestData.getInstance().populate1(msg.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().populate2(msg.getTrdRegTimestampsGroups()[1]);
        
        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2011, 9, 21, 10, 11, 44);
        msg.setSettlDate(cal.getTime());
        msg.setMatchStatus(MatchStatus.Compared);
        msg.setMatchType(MatchType.ExactMatchOnTradeDate2MinWindow);
        msg.setOrderCategory(OrderCategory.Order);
        
        msg.setNoSides(2);
        TrdCapRptSideGroup50TestData.getInstance().populate1(msg.getTrdCapRptSideGroups()[0]);
        TrdCapRptSideGroup50TestData.getInstance().populate2(msg.getTrdCapRptSideGroups()[1]);
        
        msg.setCopyMsgIndicator(Boolean.TRUE);
        msg.setPublishTrdIndicator(Boolean.TRUE);
        msg.setShortSaleReason(ShortSaleReason.DealerSoldShort);
        msg.setTierCode("TIER_1");
        msg.setMessageEventSource("SRC1");
        cal.set(2011, 8, 11, 9, 20, 13);
        msg.setLastUpdateTime(cal.getTime());
        msg.setRndPx(44.77d);
        cal.set(2011, 7, 12, 10, 20, 13);
        msg.setTZTransactTime(cal.getTime());
        msg.setReportedPxDiff(Boolean.TRUE);
        msg.setGrossTradeAmt(34.88d);
    }

    public void check(TradeCaptureReportMsg expected, TradeCaptureReportMsg actual) throws Exception {
        assertEquals(expected.getTradeReportID(), actual.getTradeReportID());
        assertEquals(expected.getTradeID(), actual.getTradeID());
        assertEquals(expected.getSecondaryTradeID(), actual.getSecondaryTradeID());
        assertEquals(expected.getFirmTradeID(), actual.getFirmTradeID());
        assertEquals(expected.getSecondaryFirmTradeID(), actual.getSecondaryFirmTradeID());
        assertEquals(expected.getTradeReportTransType(), actual.getTradeReportTransType());
        assertEquals(expected.getTradeReportType(), actual.getTradeReportType());
        assertEquals(expected.getTradeRequestID(), actual.getTradeRequestID());
        assertEquals(expected.getTrdType(), actual.getTrdType());
        assertEquals(expected.getTrdSubType(), actual.getTrdSubType());
        assertEquals(expected.getSecondaryTrdType(), actual.getSecondaryTrdType());
        assertEquals(expected.getTradeHandlingInstr(), actual.getTradeHandlingInstr());
        assertEquals(expected.getOrigTradeHandlingInstr(), actual.getOrigTradeHandlingInstr());
        assertDateEquals(expected.getOrigTradeDate(), actual.getOrigTradeDate());
        assertEquals(expected.getOrigTradeID(), actual.getOrigTradeID());
        assertEquals(expected.getOrigSecondaryTradeID(), actual.getOrigSecondaryTradeID());
        assertEquals(expected.getTransferReason(), actual.getTransferReason());
        assertEquals(expected.getExecType(), actual.getExecType());
        assertEquals(expected.getTotNumTradeReports(), actual.getTotNumTradeReports());
        assertEquals(expected.getLastRptRequested(), actual.getLastRptRequested());
        assertEquals(expected.getUnsolicitedIndicator(), actual.getUnsolicitedIndicator());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getTradeReportRefID(), actual.getTradeReportRefID());
        assertEquals(expected.getSecondaryTradeReportRefID(), actual.getSecondaryTradeReportRefID());
        assertEquals(expected.getSecondaryTradeReportID(), actual.getSecondaryTradeReportID());
        assertEquals(expected.getTradeLinkID(), actual.getTradeLinkID());
        assertEquals(expected.getTrdMatchID(), actual.getTrdMatchID());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getOrdStatus(), actual.getOrdStatus());
        assertEquals(expected.getSecondaryExecID(), actual.getSecondaryExecID());
        assertEquals(expected.getExecRestatementReason(), actual.getExecRestatementReason());
        assertEquals(expected.getPreviouslyReported(), actual.getPreviouslyReported());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        
        RootParties50TestData.getInstance().check(expected.getRootParties(), actual.getRootParties());
        
        assertEquals(expected.getAsOfIndicator(), actual.getAsOfIndicator());
        assertEquals(expected.getSettlSessID(), actual.getSettlSessID());
        assertEquals(expected.getSettlSessSubID(), actual.getSettlSessSubID());

        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getQtyType(), actual.getQtyType());
        
        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
        
        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getUnderlyingTradingSessionID(), actual.getUnderlyingTradingSessionID());
        assertEquals(expected.getUnderlyingTradingSessionSubID(), actual.getUnderlyingTradingSessionSubID());
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getCalculatedCcyLastQty(), actual.getCalculatedCcyLastQty());
        assertEquals(expected.getLastParPx(), actual.getLastParPx());
        assertEquals(expected.getLastSpotRate(), actual.getLastSpotRate());
        assertEquals(expected.getLastForwardPoints(), actual.getLastForwardPoints());
        assertEquals(expected.getLastSwapPoints(), actual.getLastSwapPoints());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
        
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        
        assertEquals(expected.getAvgPxIndicator(), actual.getAvgPxIndicator());
        
        assertEquals(expected.getNoPosAmt(), actual.getNoPosAmt());
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[0], actual.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[1], actual.getPosAmtGroups()[1]);
        
        assertEquals(expected.getMultilegReportingType(), actual.getMultilegReportingType());
        assertEquals(expected.getTradeLegRefID(), actual.getTradeLegRefID());
        
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        TrdInstrmtLegGroup50TestData.getInstance().check(expected.getTrdInstrmtLegGroups()[0], actual.getTrdInstrmtLegGroups()[0]);
        TrdInstrmtLegGroup50TestData.getInstance().check(expected.getTrdInstrmtLegGroups()[1], actual.getTrdInstrmtLegGroups()[1]);
        
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        
        assertEquals(expected.getNoTrdRegTimestamps(), actual.getNoTrdRegTimestamps());
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[0], actual.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[1], actual.getTrdRegTimestampsGroups()[1]);
        
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        assertEquals(expected.getMatchType(), actual.getMatchType());
        assertEquals(expected.getOrderCategory(), actual.getOrderCategory());
        
        assertEquals(expected.getNoSides(), actual.getNoSides());
        TrdCapRptSideGroup50TestData.getInstance().check(expected.getTrdCapRptSideGroups()[0], actual.getTrdCapRptSideGroups()[0]);
        TrdCapRptSideGroup50TestData.getInstance().check(expected.getTrdCapRptSideGroups()[1], actual.getTrdCapRptSideGroups()[1]);
        
        assertEquals(expected.getCopyMsgIndicator(), actual.getCopyMsgIndicator());
        assertEquals(expected.getPublishTrdIndicator(), actual.getPublishTrdIndicator());
        assertEquals(expected.getShortSaleReason(), actual.getShortSaleReason());
        assertEquals(expected.getTierCode(), actual.getTierCode());
        assertEquals(expected.getMessageEventSource(), actual.getMessageEventSource());
        assertUTCTimestampEquals(expected.getLastUpdateTime(), actual.getLastUpdateTime(), false);
        assertEquals(expected.getRndPx(), actual.getRndPx());
        assertUTCTimestampEquals(expected.getTZTransactTime(), actual.getTZTransactTime(), false);
        assertEquals(expected.getReportedPxDiff(), actual.getReportedPxDiff());
        assertEquals(expected.getGrossTradeAmt(), actual.getGrossTradeAmt());
    }
}
