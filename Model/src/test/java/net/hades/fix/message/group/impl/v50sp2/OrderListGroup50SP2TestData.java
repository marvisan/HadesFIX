/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderListGroup50SP2TestData.java
 *
 * $Id: OrderListGroup50SP2TestData.java,v 1.2 2011-10-29 09:42:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v44.DiscretionInstructions44TestData;
import net.hades.fix.message.comp.impl.v50.DisplayInstructions50TestData;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50.PegInstructions50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50.TriggeringInstruction50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.OrderListGroup;
import net.hades.fix.message.group.impl.v43.TradingSessionGroup43TestData;
import net.hades.fix.message.group.impl.v50.PreTradeAllocGroup50TestData;
import net.hades.fix.message.group.impl.v50.StrategyParametersGroup50TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.RefOrderIDSource;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.SideValueInd;
import net.hades.fix.message.type.TargetStrategy;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for OrderListGroup50SP2 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class OrderListGroup50SP2TestData extends MsgTest {

    private static final OrderListGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new OrderListGroup50SP2TestData();
    }

    public static OrderListGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(OrderListGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setClOrdID("CLORD53773");
        grp.setSecondaryClOrdID("BBB363744");
        grp.setListSeqNo(1);
        grp.setClOrdLinkID("LNK737664");
        grp.setSettlInstMode(SettlInstMode.Default);

        grp.setParties();
        Parties50SP2TestData.getInstance().populate(grp.getParties());

        cal.set(2010, 3, 14, 12, 13, 13);
        grp.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 14, 13, 14, 15);
        grp.setTradeDate(cal.getTime());
        grp.setAccount("12735534784");
        grp.setAcctIDSource(AcctIDSource.Other);
        grp.setAccountType(AccountType.HouseTrader);
        grp.setDayBookingInst(DayBookingInst.Accumulate);
        grp.setBookingUnit(BookingUnit.EachExecutionBookableUnit);
        grp.setAllocID("232424GGG");
        grp.setPreallocMethod(PreallocMethod.DiscussFirst);

        grp.setNoAllocs(2);
        PreTradeAllocGroup50TestData.getInstance().populate1(grp.getAllocGroups()[0]);
        PreTradeAllocGroup50TestData.getInstance().populate2(grp.getAllocGroups()[1]);

        grp.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 15, 33);
        grp.setSettlDate(cal.getTime());
        grp.setCashMargin(CashMargin.Cash);
        grp.setClearingFeeIndicator(ClearingFeeIndicator.TradingOwnAcct1stYear);
        grp.setHandlInst(HandlInst.ManualOrder);
        grp.setExecInst(ExecInst.CallFirst.getValue());
        grp.setMinQty(12.44d);
        grp.setMatchIncrement(45.55d);
        grp.setMaxPriceLevels(3);

        grp.setDisplayInstruction();
        DisplayInstructions50TestData.getInstance().populate(grp.getDisplayInstruction());

        grp.setMaxFloor(33.66d);
        grp.setExDestination("exchange1");
        grp.setExDestinationIDSource(ExDestinationIDSource.BankIdentificationCode);

        grp.setNoTradingSessions(new Integer(2));
        TradingSessionGroup43TestData.getInstance().populate1(grp.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().populate2(grp.getTradingSessionGroups()[1]);

        grp.setProcessCode(ProcessCode.Regular);

        grp.setInstrument();
        Instrument50SP2TestData.getInstance().populate(grp.getInstrument());

        grp.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(grp.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(grp.getUnderlyingInstruments()[1]);

        grp.setPrevClosePx(29.45d);
        grp.setSide(Side.Buy);
        grp.setSideValueInd(SideValueInd.SideValue_1);
        grp.setLocateReqd(Boolean.TRUE);
        cal.set(2010, 3, 14, 15, 18, 32);
        grp.setTransactTime(cal.getTime());

        grp.setStipulations();
        Stipulations50TestData.getInstance().populate(grp.getStipulations());

        grp.setQtyType(QtyType.Contracts);

        grp.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(grp.getOrderQtyData());

        grp.setOrdType(OrdType.Limit);
        grp.setPriceType(PriceType.FixedAmount);

        grp.setTriggeringInstruction();
        TriggeringInstruction50TestData.getInstance().populate(grp.getTriggeringInstruction());

        grp.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(grp.getSpreadOrBenchmarkCurveData());

        grp.setYieldData();
        YieldData50TestData.getInstance().populate(grp.getYieldData());

        grp.setOrderQty(88.22);
        grp.setCashOrderQty(22.44d);
        grp.setOrdType(OrdType.ForexLimit);
        grp.setPrice(22.67d);
        grp.setPriceProtectionScope(PriceProtectionScope.LocalExchange);
        grp.setStopPx(44.67d);
        grp.setCurrency(Currency.AustralianDollar);
        grp.setComplianceID("compl ID 1");
        grp.setSolicitedFlag(Boolean.FALSE);
        grp.setIOIID("X25383535");
        grp.setQuoteID("G93846533");
        grp.setRefOrderID("ER43564744");
        grp.setRefOrderIDSource(RefOrderIDSource.OrderID);
        grp.setTimeInForce(TimeInForce.Day);
        cal.set(2010, 3, 14, 33, 33, 33);
        grp.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 16, 44, 44, 4);
        grp.setExpireDate(cal.getTime());
        cal.set(2010, 3, 17, 55, 55, 55);
        grp.setExpireTime(cal.getTime());
        grp.setGTBookingInst(GTBookingInst.AccumUntilVerballlyNotified);

        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

        grp.setOrderCapacity(OrderCapacity.Agency);
        grp.setOrderRestrictions("1");
        grp.setPreTradeAnonymity(Boolean.FALSE);
        grp.setCustOrderCapacity(CustOrderCapacity.ClearingFirmTrading);
        grp.setForexReq(Boolean.TRUE);
        grp.setBookingType(BookingType.RegularBooking);
        grp.setText("text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedText(encodedText);
        cal.set(2010, 3, 14, 17, 22, 0);
        grp.setSettlDate2(cal.getTime());
        grp.setOrderQty2(22.0d);
        grp.setPrice2(23.66);
        grp.setPositionEffect(PositionEffect.Open);
        grp.setCoveredOrUncovered(CoveredOrUncovered.Covered);
        grp.setMaxShow(15.25d);

        grp.setPegInstructions();
        PegInstructions50TestData.getInstance().populate(grp.getPegInstructions());

        grp.setDiscretionInstructions();
        DiscretionInstructions44TestData.getInstance().populate(grp.getDiscretionInstructions());

        grp.setTargetStrategy(TargetStrategy.Participate.getValue());

        grp.setNoStrategyParameters(2);
        StrategyParametersGroup50TestData.getInstance().populate1(grp.getStrategyParametersGroups()[0]);
        StrategyParametersGroup50TestData.getInstance().populate2(grp.getStrategyParametersGroups()[1]);

        grp.setTargetStrategyParameters("p1 p2 p3");
        grp.setParticipationRate(34.33d);
        grp.setDesignation("test");
    }

    public void populate2(OrderListGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setClOrdID("CLORD53744");
        grp.setSecondaryClOrdID("BBB363755");
        grp.setListSeqNo(2);
        grp.setClOrdLinkID("LNK737655");
        grp.setSettlInstMode(SettlInstMode.RequestReject);

        grp.setParties();
        Parties50SP2TestData.getInstance().populate(grp.getParties());

        cal.set(2010, 3, 14, 13, 14, 15);
        grp.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 14, 11, 22, 33);
        grp.setTradeDate(cal.getTime());
        grp.setAccount("127355324342");
        grp.setAcctIDSource(AcctIDSource.BIC);
        grp.setAccountType(AccountType.CustomerSide);
        grp.setDayBookingInst(DayBookingInst.CanTriggerBookingWithoutReference);
        grp.setBookingUnit(BookingUnit.AggregatePartialExecutions);
        grp.setAllocID("232424EEE");
        grp.setPreallocMethod(PreallocMethod.ProRata);
        
        grp.setNoAllocs(2);
        PreTradeAllocGroup50TestData.getInstance().populate1(grp.getAllocGroups()[0]);
        PreTradeAllocGroup50TestData.getInstance().populate2(grp.getAllocGroups()[1]);

        grp.setSettlType(SettlType.Future.getValue());
        cal.set(2010, 3, 14, 34, 11, 22);
        grp.setSettlDate(cal.getTime());
        grp.setCashMargin(CashMargin.Cash);
        grp.setClearingFeeIndicator(ClearingFeeIndicator.AllOtherOwnershipTypes);
        grp.setHandlInst(HandlInst.ManualOrder);
        grp.setExecInst(ExecInst.Held.getValue());
        grp.setMinQty(12.55d);

        grp.setMatchIncrement(45.55d);
        grp.setMaxPriceLevels(3);

        grp.setDisplayInstruction();
        DisplayInstructions50TestData.getInstance().populate(grp.getDisplayInstruction());

        grp.setMaxFloor(33.77d);
        grp.setExDestination("exchange2");
        grp.setExDestinationIDSource(ExDestinationIDSource.CustomCode);

        grp.setNoTradingSessions(new Integer(2));
        TradingSessionGroup43TestData.getInstance().populate1(grp.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().populate2(grp.getTradingSessionGroups()[1]);

        grp.setProcessCode(ProcessCode.PlanSponsor);

        grp.setInstrument();
        Instrument50SP2TestData.getInstance().populate(grp.getInstrument());

        grp.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(grp.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(grp.getUnderlyingInstruments()[1]);

        grp.setPrevClosePx(29.22d);
        grp.setSide(Side.Cross);
        grp.setSideValueInd(SideValueInd.SideValue_2);
        grp.setLocateReqd(Boolean.FALSE);
        cal.set(2010, 3, 14, 11, 22, 33);
        grp.setTransactTime(cal.getTime());

        grp.setStipulations();
        Stipulations50TestData.getInstance().populate(grp.getStipulations());

        grp.setQtyType(QtyType.Units);

        grp.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(grp.getOrderQtyData());

        grp.setOrdType(OrdType.ForexLimit);
        grp.setPriceType(PriceType.Discount);

        grp.setTriggeringInstruction();
        TriggeringInstruction50TestData.getInstance().populate(grp.getTriggeringInstruction());

        grp.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(grp.getSpreadOrBenchmarkCurveData());

        grp.setYieldData();
        YieldData50TestData.getInstance().populate(grp.getYieldData());

        grp.setOrderQty(88.45d);
        grp.setCashOrderQty(90.44d);
        grp.setOrdType(OrdType.Limit);
        grp.setPrice(50.67d);
        grp.setPriceProtectionScope(PriceProtectionScope.Global);
        grp.setStopPx(51.67d);
        grp.setCurrency(Currency.UnitedStatesDollar);
        grp.setComplianceID("compl ID");
        grp.setSolicitedFlag(Boolean.TRUE);
        grp.setIOIID("X25388405");
        grp.setQuoteID("G93847464");
        grp.setRefOrderID("ER43564711");
        grp.setRefOrderIDSource(RefOrderIDSource.MDEntryID);
        grp.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 22, 22, 22);
        grp.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 16, 22, 22, 22);
        grp.setExpireDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 30, 44);
        grp.setExpireTime(cal.getTime());
        grp.setGTBookingInst(GTBookingInst.BookOutAllTrades);

        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

        grp.setOrderCapacity(OrderCapacity.Proprietary);
        grp.setOrderRestrictions("A");
        grp.setPreTradeAnonymity(Boolean.TRUE);
        grp.setCustOrderCapacity(CustOrderCapacity.AllOther);
        grp.setForexReq(Boolean.FALSE);
        grp.setBookingType(BookingType.ContractForDifference);
        grp.setText("text");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] { (byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253 };
        grp.setEncodedText(encodedText);
        cal.set(2010, 3, 14, 17, 30, 0);
        grp.setSettlDate2(cal.getTime());
        grp.setOrderQty2(25.0d);
        grp.setPrice2(23.55);
        grp.setPositionEffect(PositionEffect.Close);
        grp.setCoveredOrUncovered(CoveredOrUncovered.Uncovered);
        grp.setMaxShow(15.35d);

        grp.setPegInstructions();
        PegInstructions50TestData.getInstance().populate(grp.getPegInstructions());

        grp.setDiscretionInstructions();
        DiscretionInstructions44TestData.getInstance().populate(grp.getDiscretionInstructions());

        grp.setTargetStrategy(TargetStrategy.MininizeMarketImpact.getValue());

        grp.setNoStrategyParameters(2);
        StrategyParametersGroup50TestData.getInstance().populate1(grp.getStrategyParametersGroups()[0]);
        StrategyParametersGroup50TestData.getInstance().populate2(grp.getStrategyParametersGroups()[1]);

        grp.setTargetStrategyParameters("q1 q2 q3");
        grp.setParticipationRate(34.22d);
        grp.setDesignation("test2");
    }

    public void check(OrderListGroup expected, OrderListGroup actual, boolean fixml) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getListSeqNo(), actual.getListSeqNo());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());
        assertEquals(expected.getSettlInstMode(), actual.getSettlInstMode());

        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getDayBookingInst(), actual.getDayBookingInst());
        assertEquals(expected.getBookingUnit(), actual.getBookingUnit());
        assertEquals(expected.getAllocID(), actual.getAllocID());
        assertEquals(expected.getPreallocMethod(), actual.getPreallocMethod());

        assertEquals(expected.getNoAllocs().intValue(), actual.getNoAllocs().intValue());
        PreTradeAllocGroup50TestData.getInstance().check(expected.getAllocGroups()[0], actual.getAllocGroups()[0]);
        PreTradeAllocGroup50TestData.getInstance().check(expected.getAllocGroups()[1], actual.getAllocGroups()[1]);

        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getCashMargin(), actual.getCashMargin());
        assertEquals(expected.getClearingFeeIndicator(), actual.getClearingFeeIndicator());
        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMatchIncrement(), actual.getMatchIncrement());
        assertEquals(expected.getMaxPriceLevels(), actual.getMaxPriceLevels());

        DisplayInstructions50TestData.getInstance().check(expected.getDisplayInstruction(), actual.getDisplayInstruction());

        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getExDestination(), actual.getExDestination());
        assertEquals(expected.getExDestinationIDSource(), actual.getExDestinationIDSource());

        assertEquals(expected.getNoTradingSessions().intValue(), actual.getNoTradingSessions().intValue());
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[0], actual.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[1], actual.getTradingSessionGroups()[1]);

        assertEquals(expected.getProcessCode(), actual.getProcessCode());

        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getPrevClosePx(), actual.getPrevClosePx());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getSideValueInd(), actual.getSideValueInd());
        assertEquals(expected.getLocateReqd(), actual.getLocateReqd());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);

        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getQtyType(), actual.getQtyType());

        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getCashOrderQty(), actual.getCashOrderQty());
        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());
        assertEquals(expected.getPriceProtectionScope(), actual.getPriceProtectionScope());

        TriggeringInstruction50TestData.getInstance().check(expected.getTriggeringInstruction(), actual.getTriggeringInstruction());

        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
        
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getSolicitedFlag(), actual.getSolicitedFlag());
        assertEquals(expected.getIOIID(), actual.getIOIID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getRefOrderID(), actual.getRefOrderID());
        assertEquals(expected.getRefOrderIDSource(), actual.getRefOrderIDSource());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getEffectiveTime(), actual.getEffectiveTime(), false);
        assertDateEquals(expected.getExpireDate(), actual.getExpireDate());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getGTBookingInst(), actual.getGTBookingInst());

        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());
        
        assertEquals(expected.getOrderCapacity(), actual.getOrderCapacity());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
        assertEquals(expected.getPreTradeAnonymity(), actual.getPreTradeAnonymity());
        assertEquals(expected.getCustOrderCapacity(), actual.getCustOrderCapacity());
        assertEquals(expected.getForexReq(), actual.getForexReq());
        assertEquals(expected.getBookingType(), actual.getBookingType());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2(), actual.getOrderQty2());
        assertEquals(expected.getPrice2(), actual.getPrice2());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getCoveredOrUncovered(), actual.getCoveredOrUncovered());
        assertEquals(expected.getMaxShow(), actual.getMaxShow());

        PegInstructions50TestData.getInstance().check(expected.getPegInstructions(), actual.getPegInstructions());

        DiscretionInstructions44TestData.getInstance().check(expected.getDiscretionInstructions(), actual.getDiscretionInstructions());

        assertEquals(expected.getTargetStrategy(), actual.getTargetStrategy());

        assertEquals(expected.getNoStrategyParameters(), actual.getNoStrategyParameters());
        StrategyParametersGroup50TestData.getInstance().check(expected.getStrategyParametersGroups()[0], actual.getStrategyParametersGroups()[0]);
        StrategyParametersGroup50TestData.getInstance().check(expected.getStrategyParametersGroups()[1], actual.getStrategyParametersGroups()[1]);

        assertEquals(expected.getTargetStrategyParameters(), actual.getTargetStrategyParameters());
        assertEquals(expected.getParticipationRate(), actual.getParticipationRate());
        assertEquals(expected.getDesignation(), actual.getDesignation());
    }
}
