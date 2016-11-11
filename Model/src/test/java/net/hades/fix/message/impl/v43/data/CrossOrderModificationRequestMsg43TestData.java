/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderModificationRequestMsg43TestData.java
 *
 * $Id: CrossOrderModificationRequestMsg43TestData.java,v 1.2 2011-10-29 09:42:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CrossOrderModificationRequestMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.SpreadOrBenchmarkCurveData43TestData;
import net.hades.fix.message.comp.impl.v43.Stipulations43TestData;
import net.hades.fix.message.group.impl.v43.SideCrossOrdModGroup43TestData;
import net.hades.fix.message.group.impl.v43.YieldData43TestData;
import net.hades.fix.message.group.impl.v43.TradingSessionGroup43TestData;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for CrossOrderModificationRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/05/2011, 12:08:30 PM
 */
public class CrossOrderModificationRequestMsg43TestData extends MsgTest {

    private static final CrossOrderModificationRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new CrossOrderModificationRequestMsg43TestData();
    }

    public static CrossOrderModificationRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(CrossOrderModificationRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setOrderID("ORD7765544");
        msg.setCrossID("AAA564567");
        msg.setOrigCrossID("ORIG_887733");
        msg.setCrossType(CrossType.CrossAON);
        msg.setCrossPrioritization(CrossPrioritization.BuySidePrioritized);
        
        msg.setNoSides(2);
        SideCrossOrdModGroup43TestData.getInstance().populate1(msg.getSideCrossOrdModGroups()[0]);
        SideCrossOrdModGroup43TestData.getInstance().populate2(msg.getSideCrossOrdModGroups()[1]);
        
        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 15, 33);
        msg.setSettlDate(cal.getTime());
        msg.setHandlInst(HandlInst.ManualOrder);
        msg.setExecInst(ExecInst.CallFirst.getValue());
        msg.setMinQty(12.44d);
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
        Stipulations43TestData.getInstance().populate(msg.getStipulations());

        msg.setOrdType(OrdType.Limit);
        msg.setPriceType(PriceType.FixedAmount);
        msg.setPrice(50.67d);
        msg.setStopPx(51.67d);

        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData43TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setYieldData();
        YieldData43TestData.getInstance().populate(msg.getYieldData());

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
        msg.setPegOffsetValue(0.65d);
        msg.setDiscretionInst(DiscretionInst.RelatedToMarketPrice);
        msg.setDiscretionOffsetValue(24.66d);
        msg.setCancellationRights(CancellationRights.No_ExecutionOnly);
        msg.setMoneyLaunderingStatus(MoneyLaunderingStatus.Exempt_BelowLimit);
        msg.setRegistID("67628248247");
        msg.setDesignation("test");
        msg.setAccruedInterestRate(2.5d);
        msg.setAccruedInterestAmt(233.5d);
        msg.setNetMoney(222.35d);
    }

    public void check(CrossOrderModificationRequestMsg expected, CrossOrderModificationRequestMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getCrossID(), actual.getCrossID());
        assertEquals(expected.getOrigCrossID(), actual.getOrigCrossID());
        assertEquals(expected.getCrossType(), actual.getCrossType());
        assertEquals(expected.getCrossPrioritization(), actual.getCrossPrioritization());
        
        assertEquals(expected.getNoSides(), actual.getNoSides());
        SideCrossOrdModGroup43TestData.getInstance().check(expected.getSideCrossOrdModGroups()[0], actual.getSideCrossOrdModGroups()[0]);
        SideCrossOrdModGroup43TestData.getInstance().check(expected.getSideCrossOrdModGroups()[1], actual.getSideCrossOrdModGroups()[1]);
        
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getExDestination(), actual.getExDestination());

        assertEquals(expected.getNoTradingSessions().intValue(), actual.getNoTradingSessions().intValue());
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[0], actual.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[1], actual.getTradingSessionGroups()[1]);

        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getLocateReqd(), actual.getLocateReqd());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);

        Stipulations43TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());

        SpreadOrBenchmarkCurveData43TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        YieldData43TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

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
        assertEquals(expected.getPegOffsetValue(), actual.getPegOffsetValue());
        assertEquals(expected.getDiscretionInst(), actual.getDiscretionInst());
        assertEquals(expected.getDiscretionOffsetValue(), actual.getDiscretionOffsetValue());
        assertEquals(expected.getCancellationRights(), actual.getCancellationRights());
        assertEquals(expected.getMoneyLaunderingStatus(), actual.getMoneyLaunderingStatus());
        assertEquals(expected.getRegistID(), actual.getRegistID());
        assertEquals(expected.getDesignation(), actual.getDesignation());
        assertEquals(expected.getAccruedInterestRate(), actual.getAccruedInterestRate());
        assertEquals(expected.getAccruedInterestAmt(), actual.getAccruedInterestAmt());
    }
}
