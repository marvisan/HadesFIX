/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportAckMsg50SP2TestData.java
 *
 * $Id: TradeCaptureReportAckMsg44TestData.java,v 1.1 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportAckMsg;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.RootParties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.impl.v50.PosAmtGroup50TestData;
import net.hades.fix.message.group.impl.v50.TrdRegTimestamps50TestData;
import net.hades.fix.message.group.impl.v50sp1.TrdInstrmtLegGroup50SP1TestData;
import net.hades.fix.message.group.impl.v50sp1.TrdRepIndicatorsGroup50SP1TestData;
import net.hades.fix.message.group.impl.v50sp2.TrdCapRptAckSideGroup50SP2TestData;
import net.hades.fix.message.type.AsOfIndicator;
import net.hades.fix.message.type.AvgPxIndicator;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.ShortSaleReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradeHandlingInstr;
import net.hades.fix.message.type.TradePublishIndicator;
import net.hades.fix.message.type.TradeReportRejectReason;
import net.hades.fix.message.type.TradeReportTransType;
import net.hades.fix.message.type.TradeReportType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;
import net.hades.fix.message.type.TrdRptStatus;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.type.VenueType;

/**
 * Test utility for TradeCaptureReportAckMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class TradeCaptureReportAckMsg50SP2TestData extends MsgTest {

    private static final TradeCaptureReportAckMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new TradeCaptureReportAckMsg50SP2TestData();
    }

    public static TradeCaptureReportAckMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradeCaptureReportAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setTradeReportID("REP_999999");
        msg.setTradeID("TRD_ID_2222");
        msg.setSecondaryTradeID("SEC_TRD_ID_9999");
        msg.setFirmTradeID("FIRM_TRD_ID_0000");
        msg.setSecondaryFirmTradeID("SEC_FIRM_TRD_2222");
        msg.setTradeReportTransType(TradeReportTransType.Replace);
        msg.setTradeReportType(TradeReportType.TradeReportCancel);
        msg.setTrdType(TrdType.Transfer);
        msg.setTrdSubType(TrdSubType.Adjustment);
        msg.setSecondaryTrdType(SecondaryTrdType.RegularTrade);
        msg.setTradeHandlingInstr(TradeHandlingInstr.TradeConfirmation);
        msg.setOrigTradeHandlingInstr(TradeHandlingInstr.TradeConfirmation);
        cal.set(2011, 9, 15, 9, 15, 22);
        msg.setOrigTradeDate(cal.getTime());
        msg.setOrigTradeID("ORIG_TRAD_7777");
        msg.setOrigSecondaryTradeID("ORIG_SEC_TRAD_8888");
        msg.setTransferReason("reason to transfer");
               
        msg.setRootParties();
        RootParties50SP2TestData.getInstance().populate(msg.getRootParties());

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
        msg.setExecRestatementReason(ExecRestatementReason.Canceled);
        msg.setPreviouslyReported(Boolean.TRUE);
        msg.setPriceType(PriceType.Percentage);
        msg.setUnderlyingTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setUnderlyingTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        msg.setSettlSessID("ITD");
        msg.setSettlSessSubID("SETTL_SESS_SUB_9999");
        msg.setQtyType(QtyType.Units);
        msg.setLastQty(23.40d);
        msg.setLastPx(123.55d);
        msg.setVenueType(VenueType.Electronic);
        msg.setMarketSegmentID("MY_SEG");
        msg.setMarketID("MARKET_1");

        msg.setInstrument();
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        
        msg.setLastParPx(43.66d);
        msg.setCalculatedCcyLastQty(23.56d);
        msg.setLastSwapPoints(24.3d);
        msg.setCurrency(Currency.Guarani);
        msg.setSettlCurrency(Currency.BurundiFranc);
        msg.setLastSpotRate(23.5d);
        msg.setLastForwardPoints(23.55d);
        msg.setLastMkt("CBOT");
        cal.set(2011, 10, 20, 9, 15, 50);
        msg.setTradeDate(cal.getTime());
        cal.set(2011, 9, 11, 9, 12, 50);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setAvgPx(32.66d);
        msg.setAvgPxIndicator(AvgPxIndicator.NoAveragePricing);
        msg.setMultilegReportingType(MultiLegReportingType.MultiLegSecurity);
        msg.setTradeLegRefID("LEG_REF_777777");
        cal.set(2011, 9, 20, 9, 11, 50);
        msg.setTransactTime(cal.getTime());
        msg.setSettlType(SettlType.Cash.getValue());
               
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setMatchStatus(MatchStatus.Compared);
        msg.setMatchType(MatchType.ExactMatchOnTradeDate2MinWindow);
        msg.setCopyMsgIndicator(Boolean.TRUE);
                
        msg.setNoTrdRepIndicators(2);
        TrdRepIndicatorsGroup50SP1TestData.getInstance().populate1(msg.getTrdRepIndicatorsGroups()[0]);
        TrdRepIndicatorsGroup50SP1TestData.getInstance().populate2(msg.getTrdRepIndicatorsGroups()[1]);
 
        msg.setTradePublishIndicator(TradePublishIndicator.DoNotPublish);
        msg.setShortSaleReason(ShortSaleReason.DealerSoldShort);
        
        msg.setNoLegs(2);
        TrdInstrmtLegGroup50SP1TestData.getInstance().populate1(msg.getTrdInstrmtLegGroups()[0]);
        TrdInstrmtLegGroup50SP1TestData.getInstance().populate2(msg.getTrdInstrmtLegGroups()[1]);
         
        msg.setNoTrdRegTimestamps(2);
        TrdRegTimestamps50TestData.getInstance().populate1(msg.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().populate2(msg.getTrdRegTimestampsGroups()[1]);

        msg.setResponseTransportType(ResponseTransportType.Inband);
        msg.setResponseDestination("To Someone");
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        msg.setAsOfIndicator(AsOfIndicator.TradeIsAsOf);
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setClearingFeeIndicator(ClearingFeeIndicator.TradingOwnAcct1stYear);
               
        msg.setNoPosAmt(2);
        PosAmtGroup50TestData.getInstance().populate1(msg.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().populate2(msg.getPosAmtGroups()[1]);
        
        msg.setTierCode("TIER_1");
        msg.setMessageEventSource("SRC1");
        cal.set(2011, 8, 11, 9, 20, 13);
        msg.setLastUpdateTime(cal.getTime());
        msg.setRndPx(44.77d);

        msg.setNoSides(2);
        TrdCapRptAckSideGroup50SP2TestData.getInstance().populate1(msg.getTrdCapRptAckSideGroups()[0]);
        TrdCapRptAckSideGroup50SP2TestData.getInstance().populate2(msg.getTrdCapRptAckSideGroups()[1]);
        
        msg.setRptSys("SYS_111");
        msg.setGrossTradeAmt(34.66d);
        cal.set(2012, 8, 11, 2, 20, 13);
        msg.setSettlDate(cal.getTime());
        msg.setFeeMultiplier(1.045d);
    }

    public void check(TradeCaptureReportAckMsg expected, TradeCaptureReportAckMsg actual) throws Exception {
        assertEquals(expected.getTradeReportID(), actual.getTradeReportID());
        assertEquals(expected.getTradeID(), actual.getTradeID());
        assertEquals(expected.getSecondaryTradeID(), actual.getSecondaryTradeID());
        assertEquals(expected.getFirmTradeID(), actual.getFirmTradeID());
        assertEquals(expected.getSecondaryFirmTradeID(), actual.getSecondaryFirmTradeID());
        assertEquals(expected.getTradeReportTransType(), actual.getTradeReportTransType());
        assertEquals(expected.getTradeReportType(), actual.getTradeReportType());
        assertEquals(expected.getTrdType(), actual.getTrdType());
        assertEquals(expected.getTrdSubType(), actual.getTrdSubType());
        assertEquals(expected.getSecondaryTrdType(), actual.getSecondaryTrdType());
        assertEquals(expected.getTradeHandlingInstr(), actual.getTradeHandlingInstr());
        assertEquals(expected.getOrigTradeHandlingInstr(), actual.getOrigTradeHandlingInstr());
        assertDateEquals(expected.getOrigTradeDate(), actual.getOrigTradeDate());
        assertEquals(expected.getOrigTradeID(), actual.getOrigTradeID());
        assertEquals(expected.getOrigSecondaryTradeID(), actual.getOrigSecondaryTradeID());
        assertEquals(expected.getTransferReason(), actual.getTransferReason());
               
        RootParties50SP2TestData.getInstance().check(expected.getRootParties(), actual.getRootParties());

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
        assertEquals(expected.getExecRestatementReason(), actual.getExecRestatementReason());
        assertEquals(expected.getPreviouslyReported(), actual.getPreviouslyReported());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getUnderlyingTradingSessionID(), actual.getUnderlyingTradingSessionID());
        assertEquals(expected.getUnderlyingTradingSessionSubID(), actual.getUnderlyingTradingSessionSubID());
        assertEquals(expected.getSettlSessID(), actual.getSettlSessID());
        assertEquals(expected.getSettlSessSubID(), actual.getSettlSessSubID());
        assertEquals(expected.getQtyType(), actual.getQtyType());
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getVenueType(), actual.getVenueType());
        assertEquals(expected.getMarketSegmentID(), actual.getMarketSegmentID());
        assertEquals(expected.getMarketID(), actual.getMarketID());

        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getLastParPx(), actual.getLastParPx());
        assertEquals(expected.getCalculatedCcyLastQty(), actual.getCalculatedCcyLastQty());
        assertEquals(expected.getLastSwapPoints(), actual.getLastSwapPoints());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getLastSpotRate(), actual.getLastSpotRate());
        assertEquals(expected.getLastForwardPoints(), actual.getLastForwardPoints());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
        assertEquals(expected.getAvgPxIndicator(), actual.getAvgPxIndicator());
        assertEquals(expected.getMultilegReportingType(), actual.getMultilegReportingType());
        assertEquals(expected.getTradeLegRefID(), actual.getTradeLegRefID());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getSettlType(), actual.getSettlType());
                
        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
                  
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        assertEquals(expected.getMatchType(), actual.getMatchType());
        assertEquals(expected.getCopyMsgIndicator(), actual.getCopyMsgIndicator());
               
        assertEquals(expected.getNoTrdRepIndicators(), actual.getNoTrdRepIndicators());
        TrdRepIndicatorsGroup50SP1TestData.getInstance().check(expected.getTrdRepIndicatorsGroups()[0], actual.getTrdRepIndicatorsGroups()[0]);
        TrdRepIndicatorsGroup50SP1TestData.getInstance().check(expected.getTrdRepIndicatorsGroups()[1], actual.getTrdRepIndicatorsGroups()[1]);

        assertEquals(expected.getTradePublishIndicator(), actual.getTradePublishIndicator());
        assertEquals(expected.getShortSaleReason(), actual.getShortSaleReason());
        
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        TrdInstrmtLegGroup50SP1TestData.getInstance().check(expected.getTrdInstrmtLegGroups()[0], actual.getTrdInstrmtLegGroups()[0]);
        TrdInstrmtLegGroup50SP1TestData.getInstance().check(expected.getTrdInstrmtLegGroups()[1], actual.getTrdInstrmtLegGroups()[1]);

        assertEquals(expected.getNoTrdRegTimestamps(), actual.getNoTrdRegTimestamps());
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[0], actual.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[1], actual.getTrdRegTimestampsGroups()[1]);

        assertEquals(expected.getResponseTransportType(), actual.getResponseTransportType());
        assertEquals(expected.getResponseDestination(), actual.getResponseDestination());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getAsOfIndicator(), actual.getAsOfIndicator());
        assertEquals(expected.getClearingFeeIndicator(), actual.getClearingFeeIndicator());
               
        assertEquals(expected.getNoPosAmt(), actual.getNoPosAmt());
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[0], actual.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[1], actual.getPosAmtGroups()[1]);
        
        assertEquals(expected.getTierCode(), actual.getTierCode());
        assertEquals(expected.getMessageEventSource(), actual.getMessageEventSource());
        assertUTCTimestampEquals(expected.getLastUpdateTime(), actual.getLastUpdateTime(), false);
        assertEquals(expected.getRndPx(), actual.getRndPx());

        assertEquals(expected.getNoSides(), actual.getNoSides());
        TrdCapRptAckSideGroup50SP2TestData.getInstance().check(expected.getTrdCapRptAckSideGroups()[0], actual.getTrdCapRptAckSideGroups()[0]);
        TrdCapRptAckSideGroup50SP2TestData.getInstance().check(expected.getTrdCapRptAckSideGroups()[1], actual.getTrdCapRptAckSideGroups()[1]);
        
        assertEquals(expected.getRptSys(), actual.getRptSys());
        assertEquals(expected.getGrossTradeAmt(), actual.getGrossTradeAmt());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getFeeMultiplier(), actual.getFeeMultiplier());
    }
}
