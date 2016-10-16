/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderModificationRequestMsg50SP1TestData.java
 *
 * $Id: OrderModificationRequestMsg50SP1TestData.java,v 1.2 2011-10-29 09:42:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderModificationRequestMsg;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v44.DiscretionInstructions44TestData;
import net.hades.fix.message.comp.impl.v44.OrderQtyData44TestData;
import net.hades.fix.message.comp.impl.v50.DisplayInstructions50TestData;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.PegInstructions50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.TriggeringInstruction50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.impl.v43.TradingSessionGroup43TestData;
import net.hades.fix.message.group.impl.v50.PreTradeAllocGroup50TestData;
import net.hades.fix.message.group.impl.v50.StrategyParametersGroup50TestData;
import net.hades.fix.message.group.impl.v50.TrdRegTimestamps50TestData;
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
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TargetStrategy;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for OrderModificationRequestMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderModificationRequestMsg50SP1TestData extends MsgTest {

    private static final OrderModificationRequestMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new OrderModificationRequestMsg50SP1TestData();
    }

    public static OrderModificationRequestMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderModificationRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.setOrderID("ORD33334");

        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());

        Calendar cal = Calendar.getInstance();
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        msg.setOrigClOrdID("ORIORD35835");
        msg.setClOrdID("AAA564567");
        msg.setSecondaryClOrdID("BBB363744");
        msg.setClOrdLinkID("SA88767788");
        msg.setListID("LST99374744");
        cal.set(2010, 3, 14, 33, 33, 33);
        msg.setOrigOrdModTime(cal.getTime());
        msg.setAccount("12735534784");
        msg.setAcctIDSource(AcctIDSource.Other);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setDayBookingInst(DayBookingInst.Accumulate);
        msg.setBookingUnit(BookingUnit.EachExecutionBookableUnit);
        msg.setPreallocMethod(PreallocMethod.ProRata);
        msg.setAllocID("772663HHH");

        msg.setNoAllocs(new Integer(2));
        PreTradeAllocGroup50TestData.getInstance().populate1(msg.getAllocGroups()[0]);
        PreTradeAllocGroup50TestData.getInstance().populate2(msg.getAllocGroups()[1]);
        
        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 15, 33);
        msg.setSettlDate(cal.getTime());
        msg.setCashMargin(CashMargin.Cash);
        msg.setClearingFeeIndicator(ClearingFeeIndicator.TradingOwnAcct1stYear);
        msg.setHandlInst(HandlInst.ManualOrder);
        msg.setExecInst(ExecInst.CallFirst.getValue());
        msg.setMinQty(12.44d);
        msg.setMatchIncrement(45.55d);
        msg.setMaxPriceLevels(3);

        msg.setDisplayInstruction();
        DisplayInstructions50TestData.getInstance().populate(msg.getDisplayInstruction());

        msg.setMaxFloor(33.66d);
        msg.setExDestination("exchange");
        msg.setExDestinationIDSource("C");

        msg.setNoTradingSessions(new Integer(2));
        TradingSessionGroup43TestData.getInstance().populate1(msg.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().populate2(msg.getTradingSessionGroups()[1]);

        msg.setInstrument();
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());

        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setSide(Side.Buy);
        cal.set(2010, 3, 14, 15, 18, 32);
        msg.setTransactTime(cal.getTime());
        msg.setQtyType(QtyType.Contracts);

        msg.setOrderQtyData();
        OrderQtyData44TestData.getInstance().populate(msg.getOrderQtyData());

        msg.setOrdType(OrdType.Limit);
        msg.setPriceType(PriceType.FixedAmount);
        msg.setPrice(50.67d);
        msg.setPriceProtectionScope(PriceProtectionScope.LocalExchange);
        msg.setStopPx(51.67d);

        msg.setTriggeringInstruction();
        TriggeringInstruction50TestData.getInstance().populate(msg.getTriggeringInstruction());

        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setYieldData();
        YieldData50TestData.getInstance().populate(msg.getYieldData());

        msg.setPegInstructions();
        PegInstructions50TestData.getInstance().populate(msg.getPegInstructions());

        msg.setDiscretionInstructions();
        DiscretionInstructions44TestData.getInstance().populate(msg.getDiscretionInstructions());

        msg.setTargetStrategy(TargetStrategy.Participate.getValue());

        msg.setNoStrategyParameters(2);
        StrategyParametersGroup50TestData.getInstance().populate1(msg.getStrategyParametersGroups()[0]);
        StrategyParametersGroup50TestData.getInstance().populate2(msg.getStrategyParametersGroups()[1]);
        
        msg.setTargetStrategyParameters("p1 p2 p3");
        msg.setParticipationRate(34.33d);
        msg.setPositionEffect(PositionEffect.Close);
        msg.setComplianceID("compl ID");
        msg.setSolicitedFlag(Boolean.TRUE);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 22, 22, 22);
        msg.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 16, 22, 22, 22);
        msg.setExpireDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 30, 44);
        msg.setExpireTime(cal.getTime());
        msg.setGTBookingInst(GTBookingInst.BookOutAllTrades);

        msg.setCommissionData();
        CommissionData43TestData.getInstance().populate(msg.getCommissionData());

        msg.setOrderCapacity(OrderCapacity.Proprietary);
        msg.setOrderRestrictions("A");
        msg.setPreTradeAnonymity(Boolean.TRUE);
        msg.setCustOrderCapacity(CustOrderCapacity.AllOther);
        msg.setForexReq(Boolean.FALSE);
        msg.setSettlCurrency(Currency.CanadianDollar);
        msg.setBookingType(BookingType.RegularBooking);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setSettlDate2(cal.getTime());
        msg.setOrderQty2(25.0d);
        msg.setPrice2(23.55);
        msg.setCoveredOrUncovered(CoveredOrUncovered.Uncovered);
        msg.setMaxShow(15.35d);
        msg.setLocateReqd(Boolean.TRUE);
        msg.setMoneyLaunderingStatus(MoneyLaunderingStatus.Exempt_BelowLimit);
        msg.setRegistID("67628248247");
        msg.setDesignation("test");

        msg.setManualOrderIndicator(Boolean.TRUE);
        msg.setCustDirectedOrder(Boolean.TRUE);
        msg.setReceivedDeptID("dept 1");
        msg.setCustOrderHandlingInst("AON");
        msg.setOrderHandlingInstSource(1);

        msg.setNoTrdRegTimestamps(2);
        TrdRegTimestamps50TestData.getInstance().populate1(msg.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().populate2(msg.getTrdRegTimestampsGroups()[1]);
    }

    public void check(OrderModificationRequestMsg expected, OrderModificationRequestMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());

        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());
        assertEquals(expected.getListID(), actual.getListID());
        assertUTCTimestampEquals(expected.getOrigOrdModTime(), actual.getOrigOrdModTime(), false);
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getDayBookingInst(), actual.getDayBookingInst());
        assertEquals(expected.getBookingUnit(), actual.getBookingUnit());
        assertEquals(expected.getPreallocMethod(), actual.getPreallocMethod());
        assertEquals(expected.getAllocID(), actual.getAllocID());

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

        Instrument50SP1TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getSide(), actual.getSide());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getQtyType(), actual.getQtyType());

        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        
        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getPriceProtectionScope(), actual.getPriceProtectionScope());
        assertEquals(expected.getStopPx(), actual.getStopPx());

        TriggeringInstruction50TestData.getInstance().check(expected.getTriggeringInstruction(), actual.getTriggeringInstruction());

        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        PegInstructions50TestData.getInstance().check(expected.getPegInstructions(), actual.getPegInstructions());

        DiscretionInstructions44TestData.getInstance().check(expected.getDiscretionInstructions(), actual.getDiscretionInstructions());

        assertEquals(expected.getTargetStrategy(), actual.getTargetStrategy());

        assertEquals(expected.getNoStrategyParameters(), actual.getNoStrategyParameters());
        StrategyParametersGroup50TestData.getInstance().check(expected.getStrategyParametersGroups()[0], actual.getStrategyParametersGroups()[0]);
        StrategyParametersGroup50TestData.getInstance().check(expected.getStrategyParametersGroups()[1], actual.getStrategyParametersGroups()[1]);

        assertEquals(expected.getTargetStrategyParameters(), actual.getTargetStrategyParameters());
        assertEquals(expected.getParticipationRate(), actual.getParticipationRate());
        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getSolicitedFlag(), actual.getSolicitedFlag());
        assertEquals(expected.getCurrency(), actual.getCurrency());
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
        assertEquals(expected.getLocateReqd(), actual.getLocateReqd());
        assertEquals(expected.getMoneyLaunderingStatus(), actual.getMoneyLaunderingStatus());
        assertEquals(expected.getRegistID(), actual.getRegistID());
        assertEquals(expected.getDesignation(), actual.getDesignation());

        assertEquals(expected.getNoTrdRegTimestamps(), actual.getNoTrdRegTimestamps());
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[0], actual.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[1], actual.getTrdRegTimestampsGroups()[1]);
    }
}
