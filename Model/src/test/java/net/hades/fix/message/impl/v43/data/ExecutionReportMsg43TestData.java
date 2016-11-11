/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportMsg42TestData.java
 *
 * $Id: ExecutionReportMsg43TestData.java,v 1.3 2011-10-29 09:42:29 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.ExecutionReportMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.OrderQtyData43TestData;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.comp.impl.v43.SpreadOrBenchmarkCurveData43TestData;
import net.hades.fix.message.comp.impl.v43.Stipulations43TestData;
import net.hades.fix.message.group.impl.v40.MiscFeeGroup40TestData;
import net.hades.fix.message.group.impl.v43.ContAmtGroup43TestData;
import net.hades.fix.message.group.impl.v43.ContraBrokerGroup43TestData;
import net.hades.fix.message.group.impl.v43.InstrmtLegExecGroup43TestData;
import net.hades.fix.message.group.impl.v43.YieldData43TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.ExecPriceType;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.LastCapacity;
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
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for ExecutionReportMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ExecutionReportMsg43TestData extends MsgTest {

    private static final ExecutionReportMsg43TestData INSTANCE;

    static {
        INSTANCE = new ExecutionReportMsg43TestData();
    }

    public static ExecutionReportMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ExecutionReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setOrderID("XXX9374994");
        msg.setSecondaryOrderID("SSS778877");
        msg.setSecondaryClOrdID("SEC7364883");
        msg.setSecondaryExecID("SECEXEC2142343");
        msg.setClOrdID("AAA564567");
        msg.setOrigClOrdID("COD003883");
        msg.setClOrdLinkID("ORDLINK213131");

        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

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
        msg.setExecType(ExecType.Replace);
        msg.setOrdStatus(OrdStatus.Replaced);
        msg.setWorkingIndicator(Boolean.TRUE);
        msg.setOrdRejReason(OrdRejReason.DuplicateOrder);
        msg.setExecRestatementReason(ExecRestatementReason.BrokerOption);
        msg.setAccount("12735534784");
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
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setSide(Side.Buy);

        msg.setStipulations();
        Stipulations43TestData.getInstance().populate(msg.getStipulations());

        msg.setQuantityType(QuantityType.CURRENCY);

        msg.setOrderQtyData();
        OrderQtyData43TestData.getInstance().populate(msg.getOrderQtyData());

        msg.setOrdType(OrdType.Limit);
        msg.setPriceType(PriceType.Percentage);
        msg.setPrice(50.67d);
        msg.setStopPx(51.67d);
        msg.setPegOffsetValue(0.33d);
        msg.setDiscretionInst(DiscretionInst.RelatedToMarketPrice);
        msg.setDiscretionOffsetValue(0.22d);
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
        msg.setOrderRestrictions(OrderRestrictions.Algorithmic.getValue());
        msg.setCustOrderCapacity(CustOrderCapacity.AllOther);
        msg.setRule80A(Rule80A.Principal);
        msg.setLastQty(23.44d);
        msg.setUnderlyingLastQty(33.88d);
        msg.setLastPx(33.33d);
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
        SpreadOrBenchmarkCurveData43TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setYieldData();
        YieldData43TestData.getInstance().populate(msg.getYieldData());

        msg.setGrossTradeAmt(35.44d);
        msg.setNumDaysInterest(3);
        cal.set(2010, 3, 7, 3, 4, 5);
        msg.setExDate(cal.getTime());
        msg.setAccruedInterestRate(4.55d);
        msg.setAccruedInterestAmt(24.67d);
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

        msg.setNoContAmts(2);
        ContAmtGroup43TestData.getInstance().populate1(msg.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().populate2(msg.getContAmtGroups()[1]);

        msg.setNoLegs(2);
        InstrmtLegExecGroup43TestData.getInstance().populate1(msg.getInstrmtLegExecGroups()[0]);
        InstrmtLegExecGroup43TestData.getInstance().populate2(msg.getInstrmtLegExecGroups()[1]);

        msg.setNoMiscFees(2);
        MiscFeeGroup40TestData.getInstance().populate1(msg.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().populate1(msg.getMiscFeeGroups()[1]);
    }

    public void check(ExecutionReportMsg expected, ExecutionReportMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getSecondaryExecID(), actual.getSecondaryExecID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());

        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

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
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getDayBookingInst(), actual.getDayBookingInst());
        assertEquals(expected.getBookingUnit(), actual.getBookingUnit());
        assertEquals(expected.getPreallocMethod(), actual.getPreallocMethod());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getCashMargin(), actual.getCashMargin());
        assertEquals(expected.getClearingFeeIndicator(), actual.getClearingFeeIndicator());

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getSide(), actual.getSide());
        
        Stipulations43TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getQuantityType(), actual.getQuantityType());
        
        OrderQtyData43TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());
        assertEquals(expected.getPegOffsetValue(), actual.getPegOffsetValue());
        assertEquals(expected.getDiscretionInst(), actual.getDiscretionInst());
        assertEquals(expected.getDiscretionOffsetValue(), actual.getDiscretionOffsetValue());
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
        assertEquals(expected.getRule80A(), actual.getRule80A());
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getUnderlyingLastQty(), actual.getUnderlyingLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
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

        SpreadOrBenchmarkCurveData43TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        YieldData43TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getGrossTradeAmt(), actual.getGrossTradeAmt());
        assertEquals(expected.getNumDaysInterest(), actual.getNumDaysInterest());
        assertDateEquals(expected.getExDate(), actual.getExDate());
        assertEquals(expected.getAccruedInterestRate(), actual.getAccruedInterestRate());
        assertEquals(expected.getAccruedInterestAmt(), actual.getAccruedInterestAmt());
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

        assertEquals(expected.getNoContAmts(), actual.getNoContAmts());
        ContAmtGroup43TestData.getInstance().check(expected.getContAmtGroups()[0], actual.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().check(expected.getContAmtGroups()[1], actual.getContAmtGroups()[1]);

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrmtLegExecGroup43TestData.getInstance().check(expected.getInstrmtLegExecGroups()[0], actual.getInstrmtLegExecGroups()[0]);
        InstrmtLegExecGroup43TestData.getInstance().check(expected.getInstrmtLegExecGroups()[1], actual.getInstrmtLegExecGroups()[1]);

        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        MiscFeeGroup40TestData.getInstance().check(expected.getMiscFeeGroups()[0], actual.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().check(expected.getMiscFeeGroups()[1], actual.getMiscFeeGroups()[1]);
    }
}
