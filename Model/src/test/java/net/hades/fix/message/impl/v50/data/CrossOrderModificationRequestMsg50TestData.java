/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderModificationRequestMsg50TestData.java
 *
 * $Id: CrossOrderModificationRequestMsg50TestData.java,v 1.2 2011-10-29 09:42:06 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CrossOrderModificationRequestMsg;
import net.hades.fix.message.comp.impl.v44.DiscretionInstructions44TestData;
import net.hades.fix.message.comp.impl.v50.DisplayInstructions50TestData;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.PegInstructions50TestData;
import net.hades.fix.message.comp.impl.v50.RootParties50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50.TriggeringInstruction50TestData;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.group.impl.v43.TradingSessionGroup43TestData;
import net.hades.fix.message.group.impl.v50.SideCrossOrdModGroup50TestData;
import net.hades.fix.message.group.impl.v50.StrategyParametersGroup50TestData;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for CrossOrderModificationRequestMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/05/2011, 12:08:30 PM
 */
public class CrossOrderModificationRequestMsg50TestData extends MsgTest {

    private static final CrossOrderModificationRequestMsg50TestData INSTANCE;

    static {
        INSTANCE = new CrossOrderModificationRequestMsg50TestData();
    }

    public static CrossOrderModificationRequestMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(CrossOrderModificationRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setOrderID("ORD7765544");
        msg.setCrossID("AAA564567");
        msg.setOrigCrossID("ORIG_887733");
        msg.setHostCrossID("SOME_HOST_ID");
        msg.setCrossType(CrossType.CrossAON);
        msg.setCrossPrioritization(CrossPrioritization.BuySidePrioritized);
        
        msg.setRootParties();
        RootParties50TestData.getInstance().populate(msg.getRootParties());
        
        msg.setNoSides(2);
        SideCrossOrdModGroup50TestData.getInstance().populate1(msg.getSideCrossOrdModGroups()[0]);
        SideCrossOrdModGroup50TestData.getInstance().populate2(msg.getSideCrossOrdModGroups()[1]);
        
        msg.setInstrument();
        Instrument50TestData.getInstance().populate(msg.getInstrument());
        
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 15, 33);
        msg.setSettlDate(cal.getTime());
        msg.setHandlInst(HandlInst.ManualOrder);
        msg.setExecInst(ExecInst.CallFirst.getValue());
        msg.setMinQty(12.44d);
        msg.setMatchIncrement(23.11d);
        msg.setMaxPriceLevels(3);
        
        msg.setDisplayInstruction();
        DisplayInstructions50TestData.getInstance().populate(msg.getDisplayInstruction());
        
        msg.setMaxFloor(33.66d);
        msg.setExDestination("exchange");

        msg.setNoTradingSessions(new Integer(2));
        TradingSessionGroup43TestData.getInstance().populate1(msg.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().populate2(msg.getTradingSessionGroups()[1]);

        msg.setProcessCode(ProcessCode.Regular);
        msg.setPrevClosePx(29.45d);
        msg.setLocateReqd(Boolean.TRUE);
        cal.set(2010, 3, 14, 15, 18, 32);
        msg.setTransactTime(cal.getTime());
        cal.set(2010, 3, 15, 15, 22, 32);
        msg.setTransBkdTime(cal.getTime());

        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());

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

        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setComplianceID("compl ID");
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
        msg.setMaxShow(15.35d);
        
        msg.setPegInstructions();
        PegInstructions50TestData.getInstance().populate(msg.getPegInstructions());
        
        msg.setDiscretionInstructions();
        DiscretionInstructions44TestData.getInstance().populate(msg.getDiscretionInstructions());
        
        msg.setTargetStrategy(3);
        
        msg.setNoStrategyParameters(2);
        StrategyParametersGroup50TestData.getInstance().populate1(msg.getStrategyParametersGroups()[0]);
        StrategyParametersGroup50TestData.getInstance().populate2(msg.getStrategyParametersGroups()[1]);
        
        msg.setTargetStrategyParameters("a1 a2 a3");
        msg.setParticipationRate(23.50d);
        msg.setCancellationRights(CancellationRights.No_ExecutionOnly);
        msg.setMoneyLaunderingStatus(MoneyLaunderingStatus.Exempt_BelowLimit);
        msg.setRegistID("67628248247");
        msg.setDesignation("test");
    }

    public void check(CrossOrderModificationRequestMsg expected, CrossOrderModificationRequestMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getCrossID(), actual.getCrossID());
        assertEquals(expected.getOrigCrossID(), actual.getOrigCrossID());
        assertEquals(expected.getHostCrossID(), actual.getHostCrossID());
        assertEquals(expected.getCrossType(), actual.getCrossType());
        assertEquals(expected.getCrossPrioritization(), actual.getCrossPrioritization());
        
        RootParties50TestData.getInstance().check(expected.getRootParties(), actual.getRootParties());
        
        assertEquals(expected.getNoSides(), actual.getNoSides());
        SideCrossOrdModGroup50TestData.getInstance().check(expected.getSideCrossOrdModGroups()[0], actual.getSideCrossOrdModGroups()[0]);
        SideCrossOrdModGroup50TestData.getInstance().check(expected.getSideCrossOrdModGroups()[1], actual.getSideCrossOrdModGroups()[1]);
        
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
        
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMatchIncrement(), actual.getMatchIncrement());
        assertEquals(expected.getMaxPriceLevels(), actual.getMaxPriceLevels());

        DisplayInstructions50TestData.getInstance().check(expected.getDisplayInstruction(), actual.getDisplayInstruction());
        
        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getExDestination(), actual.getExDestination());

        assertEquals(expected.getNoTradingSessions().intValue(), actual.getNoTradingSessions().intValue());
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[0], actual.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[1], actual.getTradingSessionGroups()[1]);

        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getLocateReqd(), actual.getLocateReqd());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertUTCTimestampEquals(expected.getTransBkdTime(), actual.getTransBkdTime(), false);

        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getPriceProtectionScope(), actual.getPriceProtectionScope());
        assertEquals(expected.getStopPx(), actual.getStopPx());
        
        TriggeringInstruction50TestData.getInstance().check(expected.getTriggeringInstruction(), actual.getTriggeringInstruction());

        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getIOIID(), actual.getIOIID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getEffectiveTime(), actual.getEffectiveTime(), false);
        assertDateEquals(expected.getExpireDate(), actual.getExpireDate());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getGTBookingInst(), actual.getGTBookingInst());
        assertEquals(expected.getMaxShow(), actual.getMaxShow());
        
        PegInstructions50TestData.getInstance().check(expected.getPegInstructions(), actual.getPegInstructions());

        DiscretionInstructions44TestData.getInstance().check(expected.getDiscretionInstructions(), actual.getDiscretionInstructions());
        
        assertEquals(expected.getTargetStrategy(), actual.getTargetStrategy());
        
        assertEquals(expected.getNoStrategyParameters(), actual.getNoStrategyParameters());
        StrategyParametersGroup50TestData.getInstance().check(expected.getStrategyParametersGroups()[0], actual.getStrategyParametersGroups()[0]);
        StrategyParametersGroup50TestData.getInstance().check(expected.getStrategyParametersGroups()[1], actual.getStrategyParametersGroups()[1]);
        
        assertEquals(expected.getTargetStrategyParameters(), actual.getTargetStrategyParameters());
        assertEquals(expected.getParticipationRate(), actual.getParticipationRate());
        assertEquals(expected.getCancellationRights(), actual.getCancellationRights());
        assertEquals(expected.getMoneyLaunderingStatus(), actual.getMoneyLaunderingStatus());
        assertEquals(expected.getRegistID(), actual.getRegistID());
        assertEquals(expected.getDesignation(), actual.getDesignation());
    }
}
