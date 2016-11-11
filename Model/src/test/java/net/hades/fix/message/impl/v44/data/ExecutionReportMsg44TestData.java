/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportMsg44TestData.java
 *
 * $Id: ExecutionReportMsg44TestData.java,v 1.4 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.ExecutionReportMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v44.DiscretionInstructions44TestData;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.OrderQtyData44TestData;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.comp.impl.v44.PegInstructions44TestData;
import net.hades.fix.message.comp.impl.v44.SpreadOrBenchmarkCurveData44TestData;
import net.hades.fix.message.comp.impl.v44.Stipulations44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.comp.impl.v44.YieldData44TestData;
import net.hades.fix.message.group.impl.v43.ContAmtGroup43TestData;
import net.hades.fix.message.group.impl.v43.ContraBrokerGroup43TestData;
import net.hades.fix.message.group.impl.v44.InstrmtLegExecGroup44TestData;
import net.hades.fix.message.group.impl.v44.MiscFeeGroup44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.ExecPriceType;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.LastCapacity;
import net.hades.fix.message.type.LastLiquidityInd;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdRejReason;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrderRestrictions;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.PriorityIndicator;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TargetStrategy;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for ExecutionReportMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ExecutionReportMsg44TestData extends MsgTest {

    private static final ExecutionReportMsg44TestData INSTANCE;

    static {
        INSTANCE = new ExecutionReportMsg44TestData();
    }

    public static ExecutionReportMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ExecutionReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setOrderID("XXX9374994");
        msg.setSecondaryOrderID("SSS778877");
        msg.setSecondaryClOrdID("SEC7364883");
        msg.setSecondaryExecID("SECEXEC2142343");
        msg.setClOrdID("AAA564567");
        msg.setOrigClOrdID("COD003883");
        msg.setClOrdLinkID("ORDLINK213131");
        msg.setQuoteRespID("QUOTE784844");
        msg.setOrdStatusReqID("STS223942943");
        msg.setMassStatusReqID("MASS92363");
        msg.setTotNumReports(3);
        msg.setLastRptRequested(Boolean.TRUE);

        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());

        Calendar cal = Calendar.getInstance();
        cal.set(2010, 4, 15, 11, 12, 13);
        msg.setTradeOriginationDate(cal.getTime());

        msg.setNoContraBrokers(2);
        ContraBrokerGroup43TestData.getInstance().populate1(msg.getContraBrokers()[0]);
        ContraBrokerGroup43TestData.getInstance().populate2(msg.getContraBrokers()[1]);

        msg.setListID("LST44555");
        msg.setCrossID("CROSS534333");
        msg.setOrigCrossID("ORIGCROSS2424234");
        msg.setCrossType(CrossType.CrossOneSide);
        msg.setExecID("EXEC273663");
        msg.setExecRefID("EXEC836684");
        msg.setExecType(ExecType.New);
        msg.setOrdStatus(OrdStatus.New);
        msg.setWorkingIndicator(Boolean.TRUE);
        msg.setOrdRejReason(OrdRejReason.DuplicateOrder);
        msg.setExecRestatementReason(ExecRestatementReason.BrokerOption);
        msg.setAccount("12735534784");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.FloorTrader);
        msg.setDayBookingInst(DayBookingInst.Accumulate);
        msg.setBookingUnit(BookingUnit.EachExecutionBookableUnit);
        msg.setPreallocMethod(PreallocMethod.ProRata);
        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 15, 33);
        msg.setSettlDate(cal.getTime());
        msg.setCashMargin(CashMargin.Cash);
        msg.setClearingFeeIndicator(ClearingFeeIndicator.CBOEMember);

        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());

        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());

        msg.setNoUnderlyings(2);
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setSide(Side.Buy);

        msg.setStipulations();
        Stipulations44TestData.getInstance().populate(msg.getStipulations());

        msg.setQtyType(QtyType.Units);

        msg.setOrderQtyData();
        OrderQtyData44TestData.getInstance().populate(msg.getOrderQtyData());

        msg.setOrdType(OrdType.Limit);
        msg.setPriceType(PriceType.Percentage);
        msg.setPrice(50.67d);
        msg.setStopPx(51.67d);

        msg.setPegInstructions();
        PegInstructions44TestData.getInstance().populate(msg.getPegInstructions());

        msg.setDiscretionInstructions();
        DiscretionInstructions44TestData.getInstance().populate(msg.getDiscretionInstructions());

        msg.setPeggedPrice(33.33d);
        msg.setDiscretionPrice(45.44d);
        msg.setTargetStrategy(TargetStrategy.Participate.getValue());
        msg.setTargetStrategyParameters("24234235325");
        msg.setParticipationRate(23.30d);
        msg.setTargetStrategyParameters("T8243423235");
        
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setComplianceID("COMP_ID");
        msg.setSolicitedFlag(Boolean.TRUE);
        msg.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 11, 11, 11);
        msg.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 15, 12, 12, 12);
        msg.setExpireDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 30, 44);
        msg.setExpireTime(cal.getTime());
        msg.setExecInst(ExecInst.CallFirst.getValue());
        msg.setOrderCapacity(OrderCapacity.Proprietary);
        msg.setOrderRestrictions(OrderRestrictions.ProgramTrade.getValue());
        msg.setCustOrderCapacity(CustOrderCapacity.AllOther);
        msg.setLastQty(23.44d);
        msg.setUnderlyingLastQty(33.88d);
        msg.setLastPx(33.33d);
        msg.setUnderlyingLastPx(33.77d);
        msg.setLastParPx(43.55d);
        msg.setLastSpotRate(13.12d);
        msg.setLastForwardPoints(2.22d);
        msg.setLastMkt("ASX");
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Opening.getValue());
        msg.setLastCapacity(LastCapacity.Principal);
        msg.setLeavesQty(23.66d);
        msg.setCumQty(39.44d);
        msg.setAvgPx(33.44d);
        msg.setDayOrderQty(22.34d);
        msg.setDayCumQty(123.45d);
        msg.setDayAvgPx(23.35d);
        msg.setGTBookingInst(GTBookingInst.BookOutAllTrades);
        cal.set(2010, 3, 14, 12, 55, 55);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 56, 56);
        msg.setTransactTime(cal.getTime());
        msg.setReportToExch(Boolean.TRUE);

        msg.setCommissionData();
        CommissionData43TestData.getInstance().populate(msg.getCommissionData());

        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setYieldData();
        YieldData44TestData.getInstance().populate(msg.getYieldData());

        msg.setGrossTradeAmt(35.44d);
        msg.setNumDaysInterest(3);
        cal.set(2010, 3, 7, 3, 4, 5);
        msg.setExDate(cal.getTime());
        msg.setAccruedInterestRate(4.55d);
        msg.setAccruedInterestAmt(24.67d);
        msg.setInterestAtMaturity(54.33d);
        msg.setEndAccruedInterestAmt(65.33d);
        msg.setStartCash(33.12d);
        msg.setEndCash(35.66d);
        msg.setTradedFlatSwitch(Boolean.FALSE);
        cal.set(2010, 3, 8, 9, 10, 11);
        msg.setBasisFeatureDate(cal.getTime());
        msg.setConcession(2.56d);
        msg.setTotalTakedown(13.55d);
        msg.setNetMoney(44.45d);
        msg.setSettlCurrAmt(22.34d);
        msg.setSettlCurrency(Currency.AustralianDollar);
        msg.setSettlCurrFxRate(23.33d);
        msg.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Multiply);
        msg.setHandlInst(HandlInst.ManualOrder);
        msg.setMinQty(23.50d);
        msg.setMaxFloor(24.5d);
        msg.setPositionEffect(PositionEffect.Close);
        msg.setMaxShow(25.60d);
        msg.setBookingType(BookingType.RegularBooking);
        msg.setText("some text here");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        cal.set(2010, 3, 24, 11, 22, 33);
        msg.setSettlDate2(cal.getTime());
        msg.setOrderQty2(33.78d);
        msg.setMultilegReportingType(MultiLegReportingType.MultiLegSecurity);
        msg.setCancellationRights(CancellationRights.No_ExecutionOnly);
        msg.setMoneyLaunderingStatus(MoneyLaunderingStatus.Exempt_BelowLimit);
        msg.setRegistID("REG8633444");
        msg.setDesignation("DES8474638");
        cal.set(2010, 3, 14, 16, 27, 38);
        msg.setTransBkdTime(cal.getTime());
        cal.set(2010, 3, 8, 9, 10, 11);
        msg.setExecValuationPoint(cal.getTime());
        msg.setExecPriceType(ExecPriceType.BidPrice);
        msg.setExecPriceAdjustment(22.56d);
        msg.setPriorityIndicator(PriorityIndicator.PriorityUnchanged);
        msg.setPriceImprovement(16.77d);
        msg.setLastLiquidityInd(LastLiquidityInd.AddedLiquidity);

        msg.setNoContAmts(2);
        ContAmtGroup43TestData.getInstance().populate1(msg.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().populate2(msg.getContAmtGroups()[1]);

        msg.setNoLegs(2);
        InstrmtLegExecGroup44TestData.getInstance().populate1(msg.getInstrmtLegExecGroups()[0]);
        InstrmtLegExecGroup44TestData.getInstance().populate2(msg.getInstrmtLegExecGroups()[1]);

        msg.setCopyMsgIndicator(Boolean.TRUE);

        msg.setNoMiscFees(2);
        MiscFeeGroup44TestData.getInstance().populate1(msg.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().populate1(msg.getMiscFeeGroups()[1]);
    }

    public void check(ExecutionReportMsg expected, ExecutionReportMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getSecondaryExecID(), actual.getSecondaryExecID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());
        assertEquals(expected.getQuoteRespID(), actual.getQuoteRespID());
        assertEquals(expected.getOrdStatusReqID(), actual.getOrdStatusReqID());
        assertEquals(expected.getMassStatusReqID(), actual.getMassStatusReqID());
        assertEquals(expected.getTotNumReports(), actual.getTotNumReports());
        assertEquals(expected.getLastRptRequested(), actual.getLastRptRequested());

        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        ContraBrokerGroup43TestData.getInstance().check(expected.getContraBrokers()[0], actual.getContraBrokers()[0]);
        ContraBrokerGroup43TestData.getInstance().check(expected.getContraBrokers()[1], actual.getContraBrokers()[1]);

        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getCrossID(), actual.getCrossID());
        assertEquals(expected.getOrigCrossID(), actual.getOrigCrossID());
        assertEquals(expected.getCrossType(), actual.getCrossType());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getExecRefID(), actual.getExecRefID());
        assertEquals(expected.getExecType(), actual.getExecType());
        assertEquals(expected.getOrdStatus(), actual.getOrdStatus());
        assertEquals(expected.getWorkingIndicator(), actual.getWorkingIndicator());
        assertEquals(expected.getOrdRejReason(), actual.getOrdRejReason());
        assertEquals(expected.getExecRestatementReason(), actual.getExecRestatementReason());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getDayBookingInst(), actual.getDayBookingInst());
        assertEquals(expected.getBookingUnit(), actual.getBookingUnit());
        assertEquals(expected.getPreallocMethod(), actual.getPreallocMethod());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getCashMargin(), actual.getCashMargin());
        assertEquals(expected.getClearingFeeIndicator(), actual.getClearingFeeIndicator());

        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getSide(), actual.getSide());
        
        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getQuantityType(), actual.getQuantityType());
        
        OrderQtyData44TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());

        PegInstructions44TestData.getInstance().check(expected.getPegInstructions(), actual.getPegInstructions());

        DiscretionInstructions44TestData.getInstance().check(expected.getDiscretionInstructions(), actual.getDiscretionInstructions());

        assertEquals(expected.getPeggedPrice(), actual.getPeggedPrice());
        assertEquals(expected.getDiscretionPrice(), actual.getDiscretionPrice());
        assertEquals(expected.getTargetStrategy(), actual.getTargetStrategy());
        assertEquals(expected.getTargetStrategyParameters(), actual.getTargetStrategyParameters());
        assertEquals(expected.getParticipationRate(), actual.getParticipationRate());
        assertEquals(expected.getTargetStrategyParameters(), actual.getTargetStrategyParameters());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getSolicitedFlag(), actual.getSolicitedFlag());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getEffectiveTime(), actual.getEffectiveTime(), false);
        assertDateEquals(expected.getExpireDate(), actual.getExpireDate());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getOrderCapacity(), actual.getOrderCapacity());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
        assertEquals(expected.getCustOrderCapacity(), actual.getCustOrderCapacity());
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getUnderlyingLastQty(), actual.getUnderlyingLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getUnderlyingLastPx(), actual.getUnderlyingLastPx());
        assertEquals(expected.getLastParPx(), actual.getLastParPx());
        assertEquals(expected.getLastSpotRate(), actual.getLastSpotRate());
        assertEquals(expected.getLastForwardPoints(), actual.getLastForwardPoints());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getLastCapacity(), actual.getLastCapacity());
        assertEquals(expected.getLeavesQty(), actual.getLeavesQty());
        assertEquals(expected.getCumQty(), actual.getCumQty());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
        assertEquals(expected.getDayOrderQty(), actual.getDayOrderQty());
        assertEquals(expected.getDayCumQty(), actual.getDayCumQty());
        assertEquals(expected.getDayAvgPx(), actual.getDayAvgPx());
        assertEquals(expected.getGTBookingInst(), actual.getGTBookingInst());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getReportToExch(), actual.getReportToExch());

        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());

        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        YieldData44TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getGrossTradeAmt(), actual.getGrossTradeAmt());
        assertEquals(expected.getNumDaysInterest(), actual.getNumDaysInterest());
        assertDateEquals(expected.getExDate(), actual.getExDate());
        assertEquals(expected.getAccruedInterestRate(), actual.getAccruedInterestRate());
        assertEquals(expected.getAccruedInterestAmt(), actual.getAccruedInterestAmt());
        assertEquals(expected.getInterestAtMaturity(), actual.getInterestAtMaturity());
        assertEquals(expected.getEndAccruedInterestAmt(), actual.getEndAccruedInterestAmt());
        assertEquals(expected.getStartCash(), actual.getStartCash());
        assertEquals(expected.getEndCash(), actual.getEndCash());
        assertEquals(expected.getTradedFlatSwitch(), actual.getTradedFlatSwitch());
        assertDateEquals(expected.getBasisFeatureDate(), actual.getBasisFeatureDate());
        assertEquals(expected.getConcession(), actual.getConcession());
        assertEquals(expected.getTotalTakedown(), actual.getTotalTakedown());
        assertEquals(expected.getNetMoney(), actual.getNetMoney());
        assertEquals(expected.getSettlCurrAmt(), actual.getSettlCurrAmt());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getSettlCurrFxRate(), actual.getSettlCurrFxRate());
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getMaxShow(), actual.getMaxShow());
        assertEquals(expected.getBookingType(), actual.getBookingType());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2(), actual.getOrderQty2());
        assertEquals(expected.getMultilegReportingType(), actual.getMultilegReportingType());
        assertEquals(expected.getCancellationRights(), actual.getCancellationRights());
        assertEquals(expected.getMoneyLaunderingStatus(), actual.getMoneyLaunderingStatus());
        assertEquals(expected.getRegistID(), actual.getRegistID());
        assertEquals(expected.getDesignation(), actual.getDesignation());
        assertUTCTimestampEquals(expected.getTransBkdTime(), actual.getTransBkdTime(), false);
        assertUTCTimestampEquals(expected.getExecValuationPoint(), actual.getExecValuationPoint(), false);
        assertEquals(expected.getExecPriceType(), actual.getExecPriceType());
        assertEquals(expected.getExecPriceAdjustment(), actual.getExecPriceAdjustment());
        assertEquals(expected.getPriorityIndicator(), actual.getPriorityIndicator());
        assertEquals(expected.getPriceImprovement(), actual.getPriceImprovement());
        assertEquals(expected.getLastLiquidityInd(), actual.getLastLiquidityInd());

        assertEquals(expected.getNoContAmts(), actual.getNoContAmts());
        ContAmtGroup43TestData.getInstance().check(expected.getContAmtGroups()[0], actual.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().check(expected.getContAmtGroups()[1], actual.getContAmtGroups()[1]);

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrmtLegExecGroup44TestData.getInstance().check(expected.getInstrmtLegExecGroups()[0], actual.getInstrmtLegExecGroups()[0]);
        InstrmtLegExecGroup44TestData.getInstance().check(expected.getInstrmtLegExecGroups()[1], actual.getInstrmtLegExecGroups()[1]);

        assertEquals(expected.getCopyMsgIndicator(), actual.getCopyMsgIndicator());

        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[0], actual.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[1], actual.getMiscFeeGroups()[1]);
    }
}
