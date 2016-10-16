/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderSingleMsg44TestData.java
 *
 * $Id: NewOrderSingleMsg44TestData.java,v 1.3 2011-10-29 09:42:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewOrderSingleMsg;
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
import net.hades.fix.message.group.impl.v43.TradingSessionGroup43TestData;
import net.hades.fix.message.group.impl.v44.PreTradeAllocGroup44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TargetStrategy;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for NewOrderSingleMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class NewOrderSingleMsg44TestData extends MsgTest {

    private static final NewOrderSingleMsg44TestData INSTANCE;

    static {
        INSTANCE = new NewOrderSingleMsg44TestData();
    }

    public static NewOrderSingleMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NewOrderSingleMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setClOrdID("AAA564567");
        msg.setSecondaryClOrdID("BBB363744");
        msg.setClOrdLinkID("SA88767788");
        // Parties
        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());
        
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        msg.setAccount("12735534784");
        msg.setAcctIDSource(AcctIDSource.Other);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setDayBookingInst(DayBookingInst.Accumulate);
        msg.setBookingUnit(BookingUnit.EachExecutionBookableUnit);
        msg.setPreallocMethod(PreallocMethod.ProRata);
        msg.setAllocID("772663HHH");
        // PreTradeAllocGroup
        msg.setNoAllocs(new Integer(2));
        PreTradeAllocGroup44TestData.getInstance().populate1(msg.getAllocGroups()[0]);
        PreTradeAllocGroup44TestData.getInstance().populate2(msg.getAllocGroups()[1]);

        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 15, 33);
        msg.setSettlDate(cal.getTime());
        msg.setCashMargin(CashMargin.Cash);
        msg.setClearingFeeIndicator(ClearingFeeIndicator.TradingOwnAcct1stYear);
        msg.setHandlInst(HandlInst.ManualOrder);
        msg.setExecInst(ExecInst.CallFirst.getValue());
        msg.setMinQty(12.44d);
        msg.setMaxFloor(33.66d);
        msg.setExDestination("exchange");
        // TradingSessionGroup
        msg.setNoTradingSessions(new Integer(2));
        TradingSessionGroup43TestData.getInstance().populate1(msg.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().populate2(msg.getTradingSessionGroups()[1]);

        msg.setProcessCode(ProcessCode.Regular);
        // Instrument
        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setPrevClosePx(29.45d);
        msg.setSide(Side.Buy);
        msg.setLocateReqd(Boolean.TRUE);
        cal.set(2010, 3, 14, 15, 18, 32);
        msg.setTransactTime(cal.getTime());
        // Stipulations
        msg.setStipulations();
        Stipulations44TestData.getInstance().populate(msg.getStipulations());

        msg.setQtyType(QtyType.Contracts);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData44TestData.getInstance().populate(msg.getOrderQtyData());

        msg.setOrdType(OrdType.Limit);
        msg.setPriceType(PriceType.FixedAmount);
        msg.setPrice(50.67d);
        msg.setStopPx(51.67d);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());
        // YieldData
        msg.setYieldData();
        YieldData44TestData.getInstance().populate(msg.getYieldData());

        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setComplianceID("compl ID");
        msg.setSolicitedFlag(Boolean.TRUE);
        msg.setIOIID("X25388405");
        msg.setQuoteID("G93847464");
        msg.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 22, 22, 22);
        msg.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 16, 22, 22, 22);
        msg.setExpireDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 30, 44);
        msg.setExpireTime(cal.getTime());
        msg.setGTBookingInst(GTBookingInst.BookOutAllTrades);
        // CommissionData
        msg.setCommissionData();
        CommissionData43TestData.getInstance().populate(msg.getCommissionData());

        msg.setOrderCapacity(OrderCapacity.Proprietary);
        msg.setOrderRestrictions("A");
        msg.setCustOrderCapacity(CustOrderCapacity.AllOther);
        msg.setForexReq(Boolean.FALSE);
        msg.setSettlCurrency(Currency.CanadianDollar);
        msg.setBookingType(BookingType.RegularBooking);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        cal.set(2010, 3, 14, 17, 30, 0);
        msg.setSettlDate2(cal.getTime());
        msg.setOrderQty2(25.0d);
        msg.setPrice2(23.55);
        msg.setPositionEffect(PositionEffect.Close);
        msg.setCoveredOrUncovered(CoveredOrUncovered.Uncovered);
        msg.setMaxShow(15.35d);
        // PegInstructions
        msg.setPegInstructions();
        PegInstructions44TestData.getInstance().populate(msg.getPegInstructions());
        // PegInstructions
        msg.setDiscretionInstructions();
        DiscretionInstructions44TestData.getInstance().populate(msg.getDiscretionInstructions());

        msg.setTargetStrategy(TargetStrategy.Participate.getValue());
        msg.setTargetStrategyParameters("p1 p2 p3");
        msg.setParticipationRate(34.33d);
        msg.setCancellationRights(CancellationRights.No_ExecutionOnly);
        msg.setMoneyLaunderingStatus(MoneyLaunderingStatus.Exempt_BelowLimit);
        msg.setRegistID("67628248247");
        msg.setDesignation("test");
    }

    public void check(NewOrderSingleMsg expected, NewOrderSingleMsg actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());
        // Parties
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getDayBookingInst(), actual.getDayBookingInst());
        assertEquals(expected.getBookingUnit(), actual.getBookingUnit());
        assertEquals(expected.getPreallocMethod(), actual.getPreallocMethod());
        assertEquals(expected.getAllocID(), actual.getAllocID());
        // PreTradeAllocGroup
        assertEquals(expected.getNoAllocs().intValue(), actual.getNoAllocs().intValue());
        PreTradeAllocGroup44TestData.getInstance().check(expected.getAllocGroups()[0], actual.getAllocGroups()[0]);
        PreTradeAllocGroup44TestData.getInstance().check(expected.getAllocGroups()[1], actual.getAllocGroups()[1]);

        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getCashMargin(), actual.getCashMargin());
        assertEquals(expected.getClearingFeeIndicator(), actual.getClearingFeeIndicator());

        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getExDestination(), actual.getExDestination());
        // TradingSessionGroup
        assertEquals(expected.getNoTradingSessions().intValue(), actual.getNoTradingSessions().intValue());
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[0], actual.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[1], actual.getTradingSessionGroups()[1]);

        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        // Instrument
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getLocateReqd(), actual.getLocateReqd());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        // Stipulations
        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getQtyType(), actual.getQtyType());
        // OrderQtyData
        OrderQtyData44TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());
        // SpreadOrBenchmarkCurveData
        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData
        YieldData44TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getSolicitedFlag(), actual.getSolicitedFlag());
        assertEquals(expected.getIOIID(), actual.getIOIID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getEffectiveTime(), actual.getEffectiveTime(), false);
        assertDateEquals(expected.getExpireDate(), actual.getExpireDate());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getGTBookingInst(), actual.getGTBookingInst());
        // CommissionData
        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());

        assertEquals(expected.getOrderCapacity(), actual.getOrderCapacity());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
        assertEquals(expected.getCustOrderCapacity(), actual.getCustOrderCapacity());
        assertEquals(expected.getForexReq(), actual.getForexReq());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
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
        // PegInstructions
        PegInstructions44TestData.getInstance().check(expected.getPegInstructions(), actual.getPegInstructions());
        // DiscretionInstructions
        DiscretionInstructions44TestData.getInstance().check(expected.getDiscretionInstructions(), actual.getDiscretionInstructions());

        assertEquals(expected.getTargetStrategy(), actual.getTargetStrategy());
        assertEquals(expected.getTargetStrategyParameters(), actual.getTargetStrategyParameters());
        assertEquals(expected.getParticipationRate(), actual.getParticipationRate());
        assertEquals(expected.getCancellationRights(), actual.getCancellationRights());
        assertEquals(expected.getMoneyLaunderingStatus(), actual.getMoneyLaunderingStatus());
        assertEquals(expected.getRegistID(), actual.getRegistID());
    }
}
